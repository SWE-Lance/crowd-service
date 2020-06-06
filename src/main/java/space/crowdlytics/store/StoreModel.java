package space.crowdlytics.store;

import java.util.List;

public class StoreModel {

    private String ownerId;
    private String storeId;
    private String storeName;
    private List<String> emails;
    private int count;
    private int max;
    private double latitude;
    private double longitude;

    public StoreModel(String ownerId, String storeId, String storeName, List<String> emails,
                      int count, int max, double latitude, double
                       longitude) {
        this.ownerId = ownerId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.emails = emails;
        this.count = count;
        this.max = max;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private String getOwnerId() {
        return ownerId;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public List<String> getEmails() {
        return emails;
    }

    public int getCount() {
        return count;
    }

    public int getMax() {
        return max;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
