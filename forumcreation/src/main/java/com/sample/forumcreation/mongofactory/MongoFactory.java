package com.sample.forumcreation.mongofactory;


import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
 
/**
 * MongoFactory has the method for mongo db connection.
 * @author Gayatri
 *
 */
@SuppressWarnings("deprecation")
public class MongoFactory {
    private static Logger log = Logger.getLogger(MongoFactory.class);
    private static Mongo mongo;
    private MongoFactory() { }
 
    /**
     * getMongo method returns a mongo instance.
     * @return Mongo
     */
    public static Mongo getMongo() {
        int port_no = 27017;
        String hostname = "localhost";      
        if (mongo == null) {
            try {
                mongo = new Mongo(hostname, port_no);                                                                       
            } catch (MongoException ex) {
                log.error(ex);
            }
        }
        return mongo;
    }
 
    /**
     * getDB method Fetches the mongo database.
     * @param db_name
     * @return DB
     */
    public static DB getDB(String db_name) {        
        return getMongo().getDB(db_name);
    }
 
    /**
     * getCollection method Fetches the collection from the mongo database.
     * @param db_name
     * @param db_collection
     * @return DBCollection
     */
    public static DBCollection getCollection(String db_name, String db_collection) {
        return getDB(db_name).getCollection(db_collection);
    }
}
