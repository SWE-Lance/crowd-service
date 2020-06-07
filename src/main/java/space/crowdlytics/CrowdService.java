package space.crowdlytics;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import space.crowdlytics.auth.LoginManager;
import space.crowdlytics.auth.LoginRoute;
import space.crowdlytics.counter.CountRoute;
import space.crowdlytics.storage.SessionStore;
import space.crowdlytics.store.StoreInfoRoute;
import space.crowdlytics.store.StoreListRoute;
import space.crowdlytics.store.StoreRoute;
import space.crowdlytics.store.SubscribeRoute;
import spark.Spark;

import static spark.Spark.*;

public class CrowdService {

    private LoginManager loginManager;
    private SessionStore sessionStore;
    private Gson gson;

    public CrowdService() {
        loginManager = new LoginManager();
        sessionStore = new SessionStore();
        //init + connects to db
        sessionStore.begin();
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    public void start() {
        System.out.println("Initializing service...");
        System.out.println("Currently serving " + sessionStore.getUserCount() + " users!");
        Spark.exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
        });
        registerRoutes();
    }

    public void registerRoutes() {
        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));
        post("/login", "application/json", new LoginRoute(this), gson::toJson);
        patch("/api/store", "application/json", new StoreRoute(this), gson::toJson);
        get("/api/store", "application/json", new StoreInfoRoute(this), gson::toJson);
        get("/api/store/list", "application/json", new StoreListRoute(this), gson::toJson);
        post("/api/store/subscribe", "application/json", new SubscribeRoute(this));
        post("/api/store/count", "application/json", new CountRoute(this), gson::toJson);
    }

    public LoginManager getLoginManager() {
        return loginManager;
    }

    public Gson getGson() {
        return gson;
    }
}
