package uk.me.graphe.server.database;

import java.net.UnknownHostException;
import java.util.List;

import uk.me.graphe.server.database.dbitems.UserDB;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class UserDatabase {

	private Mongo mMongo;
    private Morphia mMorphia = new Morphia();
    private Datastore mData;
    DBCollection mCollection;
    
    public UserDatabase() {
        try {
            mMongo = new Mongo();
        } catch (UnknownHostException e) {
            return;
        }
        mData = mMorphia.createDatastore(mMongo, "users");
        mCollection = mMongo.getDB("users").getCollection("UserDB");
    }
    
    public String[] getUserIDs() {
    	List<UserDB> users = mData.find(UserDB.class).asList();
    	int size = (int) mCollection.count();
    	String[] userIDs = new String[size];
		int i = 0;
    	for (UserDB item : users) {
    		userIDs[i] = item.getId();
    		i++;
    	}
    	return userIDs;
    }
    
    public List<String> getGraphs(String userID) {
    	List<UserDB> users = mData.find(UserDB.class, "mUserID = ", userID).asList();
    	if (users == null) {
    		return null;
    	}
    	return (users.get(0)).getKeys();
    }
    
	public void newUser(String key) {
		UserDB user = new UserDB(key);
		mData.save(user);
	}
	
	public void addGraph(String userID, String graphID) {
		Query<UserDB> query = mData.createQuery(UserDB.class).filter("mUserID =", userID);
		UpdateOperations<UserDB> update = mData.createUpdateOperations(UserDB.class).add("mKeys", graphID, true);
		mData.update(query, update);
	}
}
