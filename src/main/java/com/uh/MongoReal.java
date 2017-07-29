package com.uh;

import java.util.StringTokenizer;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoReal {
    


    public static void main(String[] args) {

        MongoClient client = new MongoClient("db-01.mongo.mten.dev.law.caltesting.org", 27017);
        DB db = client.getDB("ncea-tss");
        DBCollection table = db.getCollection("items");
        
        BasicDBObject searchQuery = new BasicDBObject();
        
        BasicDBObject query = new BasicDBObject();
        DBCursor cursor = table.find(query);

        int i =0;
        while (cursor.hasNext()) {
            DBObject next = cursor.next();
            if(next.containsField("itemKey")){
                String object = (String)next.get("itemKey");
                if(!object.contains("2016") && object.contains("IFG")){
                    StringTokenizer st = new StringTokenizer(object, ".");
                    while (st.hasMoreElements()) {
                      String object2 = (String) st.nextElement();
                      if(object2.startsWith("IFG")){
                          String updated = "DEFAULT-POOL.IFG" + "_2016" + object2.substring(3);
                          System.out.println("DEFAULT-POOL." + object2 +" ---> " + updated);
                          BasicDBObject query1 = new BasicDBObject();
                          query1.put("itemKey", object);

                          BasicDBObject newDocument = new BasicDBObject();
                          newDocument.put("itemKey", updated);
                          
                          BasicDBObject updateObj = new BasicDBObject();
                          updateObj.put("$set", newDocument);

                          //table.update(query1, updateObj);
                      }
                      
                  }
                    
                   // System.out.println("DEFAULT-POOL.IFG" + "_2016" + object.substring(3));
                   /* String updated = "IFG" + "_2016" + object.substring(3);
                    BasicDBObject query1 = new BasicDBObject();
                    query1.put("itemIdentifier", object);

                    BasicDBObject newDocument = new BasicDBObject();
                    newDocument.put("itemIdentifier", updated);
                    
                    BasicDBObject updateObj = new BasicDBObject();
                    updateObj.put("$set", newDocument);

                    table.update(query1, updateObj); */
                }
            }
          }
        
        
    }



}
