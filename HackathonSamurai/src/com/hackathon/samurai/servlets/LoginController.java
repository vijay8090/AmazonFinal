package com.hackathon.samurai.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.hackathon.samurai.utils.EncryptionUtils;
import com.hackathon.samurai.utils.MongoDBUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * This File is Created for Amazon Hackathon
 * Date : Sep 20, 2015
 */

/**
 * @author Vijayakumar Anbu
 * @project : Hackathon
 * @Date :Sep 20, 2015
 * @java Version : Java 8
 */
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String email = request.getParameter("inputEmail");
		String password = request.getParameter("inputPassword");

		String command = request.getParameter("btn_action");

		String contextPath = request.getContextPath();

		StringBuffer requestURL = request.getRequestURL();

		contextPath = requestURL.toString();

		System.out.println("Host = " + request.getServerName());
		System.out.println("Port = " + request.getServerPort());

		contextPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		contextPath = "";

		String nextJSP = contextPath + "/index.jsp";

		if ("login".equals(command)) {
			if (validateLogin(email, password)) {
				request.getSession().setAttribute("logggedinUser", email);
				nextJSP = contextPath + "/subscription.jsp";
			}

		} else if ("register".equals(command)) {

			if (registerUser(email, password)) {

				nextJSP = contextPath + "/index.jsp";
			}

		} else if ("logout".equals(command)) {
			request.getSession().invalidate();

		} else if ("saveSubscription".equals(command)) {

			String jsonStr = request.getParameter("conditionStr");

			System.out.println("jsonStr : " + jsonStr);

			if (request.getSession().getAttribute("logggedinUser") == null) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				try {
					/* TODO output your response here. */
					out.println("Session timed out, please login");
				} finally {
					out.close();
				}

				return;
			}

			// nextJSP = contextPath + "/subscription.jsp";
			email = (String) request.getSession().getAttribute("logggedinUser");

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			if (saveUserSubscription(email, jsonStr)) {
				try {
					/* TODO output your response here. */
					out.println("Successfully saved");
				} finally {
					out.close();
				}
			} else {
				try {
					/* TODO output your response here. */
					out.println("Faile to Save");
				} finally {
					out.close();
				}
			}

			return;

		} else if ("getNotification".equals(command)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			MongoClient mongoClient = null;

			try {
				mongoClient = MongoDBUtil.getConnection();

				DB db = mongoClient.getDB(MongoDBUtil.MONGODB_DEFAULT_DB);

				/*DBCollection table = db.getCollection(MongoDBUtil.MONGODB_NOTIFICATION_TABLE);

				BasicDBObject searchQuery = new BasicDBObject().append("_id",  );

				DBCursor cursor2 = table.find(searchQuery);

				while (cursor2.hasNext()) {
					DBObject dbo = cursor2.next();
					for (String key : dbo.keySet()) {

					}
				}*/

				try {

					List<Map<String, String>> outputList = new LinkedList<Map<String, String>>();
					
					int limit = randInt(4,15)	;
					for (int i = 0; i < limit; i++) {
						Map<String, String> notificationMap = new LinkedHashMap<String, String>();
						notificationMap.put("notification", "You have notification -" + i);
						outputList.add(notificationMap);
					}

					Gson gson = new Gson();

					String output = gson.toJson(outputList).toString();

					/* TODO output your response here. */
					out.println(output);
				} finally {
					out.close();
				}

				MongoDBUtil.closeConnection(mongoClient);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
			return;
		} else if ("productQuery".equals(command)) {

			nextJSP = contextPath + "/productQuery.jsp";

			String productId = request.getParameter("productId");

			Map<String, String> productMap = fetchProductDetails(productId);
			request.setAttribute("productMap", productMap);

		}

		// response.sendRedirect(nextJSP);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	private Map<String, String> fetchProductDetails(String productId) {
		MongoClient mongoClient = null;

		Map<String, String> productMap = null;

		try {
			mongoClient = MongoDBUtil.getConnection();

			DB db = mongoClient.getDB(MongoDBUtil.MONGODB_DEFAULT_DB);

			DBCollection table = db.getCollection(MongoDBUtil.MONGODB_NOTIFICATION_TABLE);

			BasicDBObject searchQuery = new BasicDBObject().append("_id", productId);

			DBCursor cursor2 = table.find(searchQuery);

			productMap = new LinkedHashMap<String, String>();

			while (cursor2.hasNext()) {
				DBObject dbo = cursor2.next();
				for (String key : dbo.keySet()) {

					if (!"_id".equals(key)) {
						if ("value".equals(key)) {
							boolean secondAttrPresent = false;
							DBObject attrDbo = (DBObject) dbo.get(key);
							BasicDBList attrArray = (BasicDBList) attrDbo.get("attributes");
							BasicDBObject attObj = (BasicDBObject) attrArray.get(0);
							for (String akey : attObj.keySet()) {
								if ("attributes".equals(akey)) {
									secondAttrPresent = true;
								} else {
									productMap.put(akey, (String) attObj.get(akey));
								}
							}
							if (secondAttrPresent) {

								BasicDBList attrArray1 = (BasicDBList) attObj.get("attributes");
								BasicDBObject attObj1 = (BasicDBObject) attrArray1.get(0);
								for (String akey1 : attObj1.keySet()) {
									productMap.put(akey1, (String) attObj1.get(akey1));
								}
							}
						}
					}

					if ("attributes".equals(key)) {

						DBObject dbo1 = cursor2.next();
						for (String key1 : dbo1.keySet()) {
							productMap.put(key1, (String) dbo1.get(key1));
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

	private boolean saveUserSubscription(String email, String conditionStr) {

		boolean result = false;
		MongoClient mongoClient = null;

		try {
			mongoClient = MongoDBUtil.getConnection();

			DB db = mongoClient.getDB(MongoDBUtil.MONGODB_DEFAULT_DB);

			DBCollection table = db.getCollection(MongoDBUtil.MONGODB_USER_TABLE);

			BasicDBObject searchQuery = new BasicDBObject().append("email", email);

			DBCursor cursor2 = table.find(searchQuery);

			String userId = null;

			while (cursor2.hasNext()) {
				DBObject dbo = cursor2.next();
				for (String key : dbo.keySet()) {
					if (key.equals("_id")) {

						ObjectId objId = (ObjectId) dbo.get(key);

						userId = objId.toString();

						break;
					}
				}
			}

			if (userId != null) {

				table = db.getCollection(MongoDBUtil.MONGODB_USER_SUBSCRIPTION_TABLE);

				DBObject document = new BasicDBObject();

				document.put("userid", userId);
				Gson gson = new Gson();
				// gson.toJson(conditionStr);
				Map<String, String> map = gson.fromJson(conditionStr, LinkedHashMap.class);
				// JSONObject json = (JSONObject)new
				// JSONParser().parse(conditionStr);

				document.put("conditionStr", map);
				table.insert(document);
				result = true;
			}

			MongoDBUtil.closeConnection(mongoClient);

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {

		}
		return result;

	}

	/**
	 * 
	 * This Method is to register user
	 * 
	 * @param email
	 * @param password
	 */
	private boolean registerUser(String email, String password) {

		boolean result = false;
		MongoClient mongoClient = null;

		try {
			mongoClient = MongoDBUtil.getConnection();

			DB db = mongoClient.getDB(MongoDBUtil.MONGODB_DEFAULT_DB);

			DBCollection table = db.getCollection(MongoDBUtil.MONGODB_USER_TABLE);

			DBObject document = new BasicDBObject();

			document.put("email", email);
			document.put("password", EncryptionUtils.enCrypt(password));
			table.insert(document);
			result = true;

			MongoDBUtil.closeConnection(mongoClient);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}

	/**
	 * 
	 * This Method is to
	 * 
	 * @param email
	 * @param password
	 */
	private boolean validateLogin(String email, String password) {

		boolean result = false;

		MongoClient mongoClient = null;

		Map<String, String> productMap = null;

		try {
			mongoClient = MongoDBUtil.getConnection();

			DB db = mongoClient.getDB(MongoDBUtil.MONGODB_DEFAULT_DB);

			DBCollection table = db.getCollection(MongoDBUtil.MONGODB_USER_TABLE);

			BasicDBObject searchQuery = new BasicDBObject().append("email", email);

			DBCursor cursor2 = table.find(searchQuery);

			while (cursor2.hasNext()) {
				DBObject dbo = cursor2.next();
				for (String key : dbo.keySet()) {
					if (key.equals("password")) {
						if (password.equals(EncryptionUtils.deCrypt((String) dbo.get(key)))) {
							result = true;
						}
					}
				}
			}

			MongoDBUtil.closeConnection(mongoClient);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}

}