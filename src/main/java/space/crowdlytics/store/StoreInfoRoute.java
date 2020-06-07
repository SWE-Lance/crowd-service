package space.crowdlytics.store;

import com.google.gson.JsonObject;
import space.crowdlytics.CrowdService;
import spark.Request;
import spark.Response;
import spark.Route;

public class StoreInfoRoute implements Route {

    private CrowdService context;

    public StoreInfoRoute(CrowdService context) {
        this.context = context;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        JsonObject root = context.getGson().toJsonTree(request.body()).getAsJsonObject();
        return context.getLoginManager().getLoggedInStores().get(
                root.get("owner_id").getAsString()
        );
    }
}
