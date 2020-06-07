package space.crowdlytics.store;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import space.crowdlytics.CrowdService;
import spark.Request;
import spark.Response;
import spark.Route;

public class SubscribeRoute implements Route {

    private CrowdService context;

    public SubscribeRoute(CrowdService context) {
        this.context = context;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        JsonObject root = JsonParser.parseString(request.body()).getAsJsonObject();
        context.getLoginManager().getLoggedInStores().get(
                root.get("owner_id").getAsString()
        ).getEmails().add(root.get("email").getAsString());
        return true;
    }
}
