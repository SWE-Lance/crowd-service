package space.crowdlytics.store;

import space.crowdlytics.CrowdService;
import spark.Request;
import spark.Response;
import spark.Route;

public class StoreListRoute implements Route {

    private CrowdService context;

    public StoreListRoute(CrowdService context) {
        this.context = context;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return context.getLoginManager().getLoggedInStores();
    }
}
