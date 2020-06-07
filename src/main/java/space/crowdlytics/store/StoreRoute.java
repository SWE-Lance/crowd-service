package space.crowdlytics.store;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import space.crowdlytics.CrowdService;
import spark.Request;
import spark.Response;
import spark.Route;

public class StoreRoute implements Route {

    private CrowdService context;

    public StoreRoute(CrowdService context) {
        this.context = context;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        JsonObject root = JsonParser.parseString(request.body()).getAsJsonObject();
        StoreModel store = context.getLoginManager().getLoggedInStores().get(root.get("owner_id").getAsString());
        store.setMax(root.get("max").getAsInt());
        store.setLocation(root.get("latitude").getAsDouble(), root.get("longitude").getAsDouble());
        store.setStoreName(root.get("store_name").getAsString());
        return store;
    }
}
