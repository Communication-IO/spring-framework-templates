package com.ahlquist.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.json.JSONObject;

import com.ahlquist.util.TestAppConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Test Application that exercises all of the RESTful end points
 * @see com.ahlquist.test.CheckHealth
 * @see com.ahlquist.test.CreateUser
 * @see com.ahlquist.test.GetMessages
 * @see com.ahlquist.test.GetSingleMessageUrl
 * @see com.ahlquist.test.SendMessageAsMedia
 * @see com.ahlquist.test.SendMessageAsText
 * @see com.ahlquist.test.UserLogin
 * @see com.ahlquist.test.UserLogout
 * 
 * @author douglasahlquist
 */
public class TestApp {
	
	public final static String HOST = "http://127.0.0.1:8080";

	public final static long time = System.currentTimeMillis();

	//public final static String bigImage = "/images/bigImage-20MB.jpg";
	public final static String smallImage = "/images/tweet_bird.png";
	//public final static String smallImage = "/images/bart.png";
	public final static String smallImage2 = "/images/bart2.png";
	public final static String smallImage3 = "/images/homer1.png";
	public final static String smallImage4 = "/images/homer2.png";
	//public final static String smallImage5 = "/images/Spritz-Cookes2.webp";
	//public final static String smallVideo = "/movies/sample_960x400_ocean_with_audio.mov";
	
	//default catch all password.  What the password is is not important for these tests.
	public final static String password = "password";
	

	public static void main(String[] args) throws IOException {
		
		// must update the path to properly work.  This go be resolved in Java but.....
		final String rootDir = TestAppConfig.getInstance().getValue("media-root");

		final String[][] MEDIA = {
				// type, 			text	
				//{rootDir+bigImage, MimeTypes.JPG},   //1
				{rootDir+smallImage, MimeTypes.PNG}, //2
				//{rootDir+smallImage2, MimeTypes.PNG},//3
				//{rootDir+smallImage3, MimeTypes.PNG},//4
				//{rootDir+smallImage4, MimeTypes.PNG},//5
				//{rootDir+smallImage5, MimeTypes.PNG},//6
				//{rootDir+smallVideo, MimeTypes.MOV}, //7
			};


		System.out.println("Starting app...");
		String username1 = "douglas_";
		int next = 0; 
		
		List<String> uuidList = new ArrayList<>();

		try {
			for (int i = 0; i < 3; i++) {
				// Check Health
				System.out.println("----------------------------------------------------");
				System.out.println("Check health ==>");
				String checkResult = CheckHealth.checkHealth();
				System.out.println("Check Health: \n" + beautify(checkResult));
				
				// Create User
				System.out.println("----------------------------------------------------");
				System.out.println("User Create ==>");
				Random randomNum = new Random();
				next = randomNum.nextInt(10000);
				String tempUser = username1 + next;
				System.out.println("creating user " + tempUser);
				String resultString = CreateUser.createUser(tempUser, password);
				System.out.println("User Creation result: \n" + beautify(resultString));
				
				// User Login
				System.out.println("----------------------------------------------------");
				System.out.println("User Login ==>");
				String loginResult = UserLogin.login(tempUser, password);
				JSONObject json = new JSONObject(loginResult);
				String uuid = json.getString("uuid");
				uuidList.add(uuid);
				String token = json.getString("token");
				System.out.println("User Login result: \n" + beautify(loginResult));

				// ~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-
				// ~- Send Message only on the 2nd User creation/login                 ~-
				// ~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-
				if (i > 0) {
					// Send Message As Text
					System.out.println("----------------------------------------------------");
					System.out.println("Send Text Message ==>");

					String sender = uuidList.get(i - 1);
					String recipient = uuidList.get(i);
					System.out.println("sender:\t\t" + sender + "\nrecipient:\t" + recipient);
					String messageTextResult = SendMessageAsText.sentMessageAsText(token, sender, recipient);
					System.out.println("Send Text message: \n" + beautify(messageTextResult));
					
					// Send Media Message
					System.out.println("----------------------------------------------------");
					System.out.println("Send Media Message ==>");
					
					int mediaIndex = 0; //randomImageIndex.nextInt(MEDIA.length-1);
					final String filepath = MEDIA[mediaIndex][0];
					final String type = MEDIA[mediaIndex][1];
					
					String getMediaResult = SendMessageAsMedia.pushMedia(token, sender, recipient,
							type, filepath);
					System.out.println("Get Messages result: \n");
					System.out.println(beautify(getMediaResult));
					
					// Get Messages
					System.out.println("----------------------------------------------------");
					System.out.println("Get Message ==>");
					String getMessageResult = GetMessages.getMessages(token, recipient, 0, 5);
					System.out.println("Get Messages result: \n");
					System.out.println(beautify(getMessageResult));
					
					// Get Single Message

				}
				
				// Logout
//				System.out.println("----------------------------------------------------");
//				System.out.println("User Logout ==>");
//				String getLogoutResult = UserLogout.userLogout(token, UUID.fromString(uuid));
//				System.out.println("User Logout result: \n" + beautify(getLogoutResult));
//				
				
				System.out.println("****************************************************");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String beautify(String uglyJsonString){
		try {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement je = JsonParser.parseString(uglyJsonString);
		return gson.toJson(je);
		}catch(Exception e) {
			return uglyJsonString;
		}
	}

}
