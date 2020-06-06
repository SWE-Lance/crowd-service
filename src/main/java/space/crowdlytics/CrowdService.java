package space.crowdlytics;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import space.crowdlytics.auth.LoginManager;
import space.crowdlytics.auth.LoginRoute;
import space.crowdlytics.counter.CountRoute;
import space.crowdlytics.store.StoreRoute;

import static spark.Spark.patch;
import static spark.Spark.post;
import static spark.Spark.put;

public class CrowdService {

    private LoginManager loginManager;
    private Gson gson;

    public CrowdService() {
        loginManager = new LoginManager();
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    public void start() {
        System.out.println("Initializing service...");
        registerRoutes();
    }

    public void registerRoutes() {
        post("/login", "application/json", new LoginRoute(this), gson::toJson);
        patch("/api/store", "application/json", new StoreRoute(this), gson::toJson);
        put("/api/store/count", "application/json", new CountRoute(this), gson::toJson);
    }

    public LoginManager getLoginManager() {
        return loginManager;
    }

    public Gson getGson() {
        return gson;
    }
}
