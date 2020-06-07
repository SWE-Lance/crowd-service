package space.crowdlytics.storage;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

public class SessionStore {
    private MongoClient mongoClient;
    private MongoCollection<Document> lastStoredSessions;

    public void begin() {
        MongoClientURI atlasConnectionString = new MongoClientURI("PRIVATE STUFF!");
        mongoClient = new MongoClient(atlasConnectionString);
        MongoDatabase database = mongoClient.getDatabase("sessions");
        lastStoredSessions = database.getCollection("last");
    }

    public MongoCollection<Document> getStoredSessions() {
        return lastStoredSessions;
    }

    public long getUserCount() {
        return lastStoredSessions.count();
    }
}
