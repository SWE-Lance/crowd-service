package space.crowdlytics.auth;

import space.crowdlytics.CrowdService;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginRoute implements Route {

    private CrowdService context;

    public LoginRoute(CrowdService context) {
        this.context = context;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        OwnerInformation ownerInfo = context.getGson().fromJson(request.body(), OwnerInformation.class);
        return context.getLoginManager().login(ownerInfo);
    }
}
