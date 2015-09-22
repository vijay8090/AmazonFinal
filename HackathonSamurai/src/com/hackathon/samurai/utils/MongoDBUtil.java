package com.hackathon.samurai.utils;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * 
 * @author Vijayakumar Anbu
 * @project : Hackathon
 * @Date :Sep 19, 2015
 * @java Version : Java 8
 */
public class MongoDBUtil {

	public static final String MONGODB_DEFAULT_DB = "samurai";
	public static final int MONGODB_PORT = 27017;
	public static final String MONGODB_HOST = "localhost";
	public static final String MONGODB_PRODUCT_ATTR_TABLE = "productAttr";
	public static final String MONGODB_NOTIFICATION_TABLE = "notificationData";
	public static final String MONGODB_USER_TABLE = "userData";
	public static final String MONGODB_USER_SUBSCRIPTION_TABLE = "userSubscriptionTable";
	
	public static final String MONGODB_USER_NOTIFICATION_TABLE = "userNotificationTable";
	static String myUserName = null;
	static String myPassword = null;

	public static MongoClient getConnection() {

		MongoClient mongoClient = null;

		try {
			// To connect to mongodb server
			mongoClient = new MongoClient(MONGODB_HOST, MONGODB_PORT);
			// Now connect to your databases
			DB db = mongoClient.getDB(MONGODB_DEFAULT_DB);
			System.out.println("Connect to database successfully");

			// boolean auth = db..authenticate(myUserName, myPassword);
			// System.out.println("Authentication: "+auth);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return mongoClient;
	}

	public static boolean closeConnection(MongoClient mongoClient) {

		mongoClient.close();

		return true;
	}

	/*
	 * public static void createCollection() { try { // To connect to mongodb
	 * server MongoClient mongoClient = new MongoClient(MONGODB_HOST, 27017); //
	 * Now connect to your databases DB db =
	 * mongoClient.getDB(MONGODB_DEFAULT_DB); System.out.println(
	 * "Connect to database successfully"); // boolean auth =
	 * db.authenticate(myUserName, myPassword); // System.out.println(
	 * "Authentication: "+auth); DBCollection coll =
	 * db.createCollection("mycol", null); System.out.println(
	 * "Collection created successfully"); } catch (Exception e) {
	 * System.err.println(e.getClass().getName() + ": " + e.getMessage()); } }
	 * 
	 * public static void selectCol() { try { // To connect to mongodb server
	 * MongoClient mongoClient = new MongoClient(MONGODB_HOST, 27017); // Now
	 * connect to your databases DB db = mongoClient.getDB(MONGODB_DEFAULT_DB);
	 * System.out.println("Connect to database successfully"); // boolean auth =
	 * db.auth(myUserName, myPassword); // System.out.println("Authentication: "
	 * +auth); DBCollection coll = db.createCollection("mycol", null);
	 * System.out.println("Collection created successfully"); DBCollection coll1
	 * = db.getCollection("mycol"); System.out.println(
	 * "Collection mycol selected successfully"); } catch (Exception e) {
	 * System.err.println(e.getClass().getName() + ": " + e.getMessage()); } }
	 * 
	 * public static void insertDoc() { try { // To connect to mongodb server
	 * MongoClient mongoClient = new MongoClient(MONGODB_HOST, 27017); // Now
	 * connect to your databases DB db = mongoClient.getDB(MONGODB_DEFAULT_DB);
	 * System.out.println("Connect to database successfully"); // boolean auth =
	 * db.authenticate(myUserName, myPassword); // System.out.println(
	 * "Authentication: "+auth); DBCollection coll = db.getCollection("mycol");
	 * System.out.println("Collection mycol selected successfully");
	 * BasicDBObject doc = new BasicDBObject("title",
	 * "MongoDB").append("description", "database") .append("likes",
	 * 100).append("url", "http://www.tutorialspoint.com/mongodb/")
	 * .append("by", "tutorials point"); coll.insert(doc); System.out.println(
	 * "Document inserted successfully"); } catch (Exception e) {
	 * System.err.println(e.getClass().getName() + ": " + e.getMessage()); } }
	 * 
	 * public static void retrieveAllDocs() { try { // To connect to mongodb
	 * server MongoClient mongoClient = new MongoClient(MONGODB_HOST, 27017); //
	 * Now connect to your databases DB db =
	 * mongoClient.getDB(MONGODB_DEFAULT_DB); System.out.println(
	 * "Connect to database successfully"); // boolean auth =
	 * db.authenticate(myUserName, myPassword); // System.out.println(
	 * "Authentication: "+auth); DBCollection coll = db.getCollection("mycol");
	 * System.out.println("Collection mycol selected successfully"); DBCursor
	 * cursor = coll.find(); int i = 1; while (cursor.hasNext()) {
	 * System.out.println("Inserted Document: " + i);
	 * System.out.println(cursor.next()); i++; } } catch (Exception e) {
	 * System.err.println(e.getClass().getName() + ": " + e.getMessage()); } }
	 * 
	 * public static void updateDocs() { try { // To connect to mongodb server
	 * MongoClient mongoClient = new MongoClient(MONGODB_HOST, 27017); // Now
	 * connect to your databases DB db = mongoClient.getDB(MONGODB_DEFAULT_DB);
	 * System.out.println("Connect to database successfully"); // boolean auth =
	 * db.authenticate(myUserName, myPassword); // System.out.println(
	 * "Authentication: "+auth); DBCollection coll = db.getCollection("mycol");
	 * System.out.println("Collection mycol selected successfully"); DBCursor
	 * cursor = coll.find(); while (cursor.hasNext()) { DBObject updateDocument
	 * = cursor.next(); updateDocument.put("likes", "200"); coll.update(null,
	 * updateDocument); } System.out.println("Document updated successfully");
	 * cursor = coll.find(); int i = 1; while (cursor.hasNext()) {
	 * System.out.println("Updated Document: " + i);
	 * System.out.println(cursor.next()); i++; } } catch (Exception e) {
	 * System.err.println(e.getClass().getName() + ": " + e.getMessage()); } }
	 * 
	 * public static void deleteFirstDoc() { try { // To connect to mongodb
	 * server MongoClient mongoClient = new MongoClient(MONGODB_HOST, 27017); //
	 * Now connect to your databases DB db =
	 * mongoClient.getDB(MONGODB_DEFAULT_DB); System.out.println(
	 * "Connect to database successfully"); // boolean auth =
	 * db.authenticate(myUserName, myPassword); // System.out.println(
	 * "Authentication: "+auth); DBCollection coll = db.getCollection("mycol");
	 * System.out.println("Collection mycol selected successfully"); DBObject
	 * myDoc = coll.findOne(); coll.remove(myDoc); DBCursor cursor =
	 * coll.find(); int i = 1; while (cursor.hasNext()) { System.out.println(
	 * "Inserted Document: " + i); System.out.println(cursor.next()); i++; }
	 * System.out.println("Document deleted successfully"); } catch (Exception
	 * e) { System.err.println(e.getClass().getName() + ": " + e.getMessage());
	 * } }
	 */

}
