package space.crowdlytics.counter;

import com.google.gson.JsonObject;
import space.crowdlytics.CrowdService;
import space.crowdlytics.store.StoreModel;
import spark.Request;
import spark.Response;
import spark.Route;

public class CountRoute implements Route {

    private CrowdService context;

    public CountRoute(CrowdService context) {
        this.context = context;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        JsonObject root = context.getGson().toJsonTree(request.body()).getAsJsonObject();
        StoreModel store = context.getLoginManager().getLoggedInStores().get(root.get("owner_id").getAsString());
        System.out.println(store);
        System.out.println(store.getCount());
        store.setCount(store.getCount() + 1);
        return new Count(store.getCount());
    }
}
