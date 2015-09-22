package com.hackthon.samurai;

import com.hackthon.samurai.util.MongoDBUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;

public class NotificationDataLoader {

	public NotificationDataLoader() {
		// TODO Auto-generated constructor stub
		//List<String> distinctProductIdList = getAllDistinctProductId();
		/*List<String> distinctProductIdList = new ArrayList<String>();
		distinctProductIdList.add("" + 84482);*/
		// distinctProductIdList.add(e);
		//getAllProductDetails(distinctProductIdList);
		NOtificationDataMapReducer();
	}
	
	
	public void NOtificationDataMapReducer(){
		
		MongoClient mongoClient = null;

		try {
			mongoClient = MongoDBUtil.getConnection();

			DB db = mongoClient.getDB(MongoDBUtil.MONGODB_DEFAULT_DB);

			DBCollection table = db.getCollection(MongoDBUtil.MONGODB_PRODUCT_ATTR_TABLE);
		
		String map = " function() { "+
				" emit(this.productid, this); "+
				" } ";
	   
	   String reduce = " function(key, values) { "+
		  " var newVal = []; "+
		   "var newObj ={}; "+
		  " values.forEach(function(doc) { "+
		            " for(var key in doc){ "+
		              " var attrName = key; "+
		              " var attrValue = doc[key]; "+
		              " 	if(key != '_id' && key != 'productid'){ "+
		              " 		newObj[attrName] = attrValue; "+
		             "  	} "+
		          " 	} "+
		 "  }); "+
		 "   newVal.push(newObj); "+

 "   return {attributes: newVal}; "+
 "   }  ";
	   
	  // BasicDBObject searchQuery2 = new BasicDBObject().append("productid", prodId);
	  // DBCollection productDetails = db.getCollection("productDetails");
	   MapReduceCommand cmd = new MapReduceCommand(table, map, reduce,
			   MongoDBUtil.MONGODB_NOTIFICATION_TABLE, MapReduceCommand.OutputType.REPLACE, null);
	   
	   //MapReduceCommand cmd1 = new MapReduceCommand(inputCollection, map, reduce, outputCollection, type, query)

	   MapReduceOutput out = table.mapReduce(cmd);

	   for (DBObject o : out.results()) {
	    System.out.println(o.toString());
	   }
	   
		MongoDBUtil.closeConnection(mongoClient);
	   
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		
	}
	
	
/*
	public List<String> getAllDistinctProductId() {

		List<String> distinctProductIdList = null;

		MongoClient mongoClient = null;

		try {
			mongoClient = MongoDBUtil.getConnection();

			DB db = mongoClient.getDB(MongoDBUtil.MONGODB_DEFAULT_DB);

			DBCollection table = db.getCollection("ITEM_ATTR");

			distinctProductIdList = table.distinct("productid");

			System.out.println("-----------------------");

			for (int i = 0; i < distinctProductIdList.size(); i++) {
				System.out.println(i + " : " + distinctProductIdList.get(i));
			}

			MongoDBUtil.closeConnection(mongoClient);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return distinctProductIdList;
	}

	public void getAllProductDetails(List<String> distinctProductIdList) {

		MongoClient mongoClient = null;

		try {
			mongoClient = MongoDBUtil.getConnection();

			DB db = mongoClient.getDB(MongoDBUtil.MONGODB_DEFAULT_DB);

			DBCollection readTable = db.getCollection("ITEM_ATTR");

			for (String prodId : distinctProductIdList) {

				BasicDBObject searchQuery2 = new BasicDBObject().append("productid", prodId);

				//BasicDBObject orderBy = new BasicDBObject("productid", -1);
				DBCursor cursor2 = readTable.find(searchQuery2);
				
				Map<String,String> productMap = new HashMap<String,String>();

				while (cursor2.hasNext()) {
					DBObject dbo = cursor2.next();
					for ( String key : dbo.keySet() ) {
						
						if(!"_id".equals(key)){
			           // System.out.println( "key: " + key + " value: " + dbo.get( key ) );
						productMap.put(key, (String) dbo.get( key ));
						}
			        }
					
					
				}
				System.out.println(productMap);
			}

			MongoDBUtil.closeConnection(mongoClient);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}*/

	public static void main(String[] args) {
		new NotificationDataLoader();
	}

}
