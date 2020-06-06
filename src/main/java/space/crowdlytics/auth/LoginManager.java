package space.crowdlytics.auth;

import space.crowdlytics.store.StoreModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginManager {

    private Map<String, StoreModel> loggedInStores;

    public LoginManager() {
        loggedInStores = new HashMap<>();
        //pull users with stores from DB here
    }

    public Map<String, StoreModel> getLoggedInStores() {
        return loggedInStores;
    }

    public StoreModel login(OwnerInformation ownerInfo) {
        if(!loggedInStores.containsKey(ownerInfo.getId())) {
            loggedInStores.put(ownerInfo.getId(), new StoreModel(
                    ownerInfo.getId(),
                    UUID.randomUUID().toString(),
                    "store-" + UUID.randomUUID().toString(),
                    new ArrayList<>(),
                    0,
                    50,
                    0.0,
                    0.0
                )
            );
        }
        return loggedInStores.get(ownerInfo.getId());
    }

}
