package com.uh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDelete {
    
    public static void main(String[] args) {

    MongoClient client = new MongoClient("db-01.mongo.mten.dev.law.caltesting.org", 27017);
    DB db = client.getDB("ncea-tss");
    DBCollection table = db.getCollection("items");
    
    BasicDBObject searchQuery = new BasicDBObject();
    
    BasicDBObject query = new BasicDBObject();
    DBCursor cursor = table.find(query);
    
    Map<String, Integer> map = new HashMap<String, Integer>();
    
    Map<String, List<String>> classMap = new HashMap<String, List<String>>();
    
    int i =0;
    
    while(cursor.hasNext()){
        DBObject next = cursor.next();
        String object = (String)next.get("itemIdentifier");
        if (object.contains("IFG")) {
            if (!map.containsKey(object)) {
                map.put(object, 1);
                i++;
            } else {
                map.put(object, map.get(object) + 1);
                i++;
            }
            
            String cls = (String)next.get("_class");
            if(!classMap.containsKey(object)){
                List<String> classes = new ArrayList<String>();
                classes.add(cls);
                classMap.put(object, classes);
            }else{
                List<String> list = classMap.get(object);
                list.add(cls);
                classMap.put(object, list);
            }
        }
    }
    
    int k=0, l=0, m=0;
    Set<Entry<String, List<String>>> entrySet2 = classMap.entrySet();
    for (Entry<String, List<String>> entry : entrySet2) {
        List<String> value = entry.getValue();
        System.out.println(entry.getKey());
        for (String string : value) {
            if(string.equals("org.caltesting.tss.model.Item") && value.size() == 1){
                k++;
            }else if(string.equals("org.caltesting.tss.model.ItemBean") && value.size() == 1){
                l++;
            }else if(value.size() == 2){
                m++;
            }
            System.out.println(string);
        }
        System.out.println("**************");
    }
    
    System.out.println(map.size() + "--" + "--" + i + "---" + k + "--" + l + "--" + m/2);
    
    
    for (Entry<String, List<String>> entry : entrySet2) {
        List<String> value = entry.getValue();
        if (value.size() == 2) {
            for (String string : value) {
                if(string.equals("org.caltesting.tss.model.Item")){
                    BasicDBObject document = new BasicDBObject();
                    document.put("itemIdentifier", entry.getKey());
                    document.put("_class", string);
                   // table.remove(document);
                }
            }
        }

    }
    
    }
    
}
