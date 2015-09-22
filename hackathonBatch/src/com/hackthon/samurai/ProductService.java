package com.hackthon.samurai;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hackthon.samurai.util.MongoDBUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * 
 * @author Vijayakumar Anbu
 * @project : Hackathon
 * @Date :Sep 20, 2015
 * @java Version : Java 8
 */
public class ProductService {
	
	public ProductService() {
		
	}
	
	/**
	 * 
	 * This Method is to Query Mongo Db and get Product details
	 * @param productId
	 * @return
	 */
	public Map<String,String> getProduct(String productId){
		
		MongoClient mongoClient = null;
		
		Map<String,String> productMap = null;

		try {
			mongoClient = MongoDBUtil.getConnection();

			DB db = mongoClient.getDB(MongoDBUtil.MONGODB_DEFAULT_DB);

			DBCollection table = db.getCollection(MongoDBUtil.MONGODB_NOTIFICATION_TABLE);
			
			BasicDBObject searchQuery = new BasicDBObject().append("_id", productId);

			DBCursor cursor2 = table.find(searchQuery);
			
			productMap = new LinkedHashMap<String,String>();

			while (cursor2.hasNext()) {
				DBObject dbo = cursor2.next();
				for ( String key : dbo.keySet() ) {
					
					if(!"_id".equals(key)){
						if("value".equals(key)){
							boolean secondAttrPresent =false;
							DBObject attrDbo = (DBObject) dbo.get( key );
							BasicDBList attrArray = (BasicDBList) attrDbo.get("attributes");
							BasicDBObject attObj = (BasicDBObject)attrArray.get(0);
							for ( String akey : attObj.keySet() ) {
								if("attributes".equals(akey)){
									secondAttrPresent = true;
								}else {
									productMap.put(akey, (String) attObj.get( akey ));									
								}
							}
							if(secondAttrPresent){
								
							BasicDBList attrArray1 = (BasicDBList) attObj.get("attributes");
							BasicDBObject attObj1 = (BasicDBObject)attrArray1.get(0);
							for ( String akey1 : attObj1.keySet() ) {
								productMap.put(akey1, (String) attObj1.get( akey1 ));
							}
							}
						}
					}
					
					if("attributes".equals(key)){
						
						DBObject dbo1 = cursor2.next();
						for ( String key1 : dbo1.keySet() ) {
							productMap.put(key1, (String) dbo1.get( key1 ));
						}
						
					}
		        }
				
				
			}
			System.out.println(productMap);
			
			MongoDBUtil.closeConnection(mongoClient);
			
			
			   
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return productMap;
			
	}
	
	public static void main(String[] args) {
		ProductService prodService = new ProductService();
		
		prodService.getProduct("10007");
	}

}
