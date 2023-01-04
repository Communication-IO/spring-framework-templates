# Chat App Backend Implementation & Notes from Email



## Development Dependencies

###JAVA Version 17
###Maven
###MySQL
###MYSQLWorkbench
###AWS Account
###An IDE either Eclipse or other
###Images to Upload

##System Configuration
###Ensure that the ~/.aws/config file is present.    The application reads from this file for uploading non-text messages
###Add a file at ~/chat_text/test.properties with one entry.  Note your path will vary.   This will point to the folder where 
	will have the several images that the test application will upload.
	media-root=/Users/douglasahlquist/.chat_test/
	
###In the folder listed above copy the images files into.
	Edit the test/src/main/java/TestApp.java file to include the media files and their mime-types.
	Edit specifically member variable such as 'public final static String smallImage2' and the MEDIA array
	
	
###Edit the microservice/src/main/resourses/application.properties files key value pairs to match your test enviornment

spring.datasource.url=jdbc:mysql://localhost:3306/testapp
spring.datasource.username=root
spring.datasource.password=password

Create the chat schema from the tables below or /microservice/src/main/resources/sql/schema.sql.   Note if you name the schema other then `testapp`, 
you will have the edit the schema below


DROP TABLE `testapp`.`user`;
CREATE TABLE `testapp`.`user` (
  `uuid` VARCHAR(36) PRIMARY KEY NOT NULL,
  `username` VARCHAR(64) NOT NULL UNIQUE,
  `password` VARCHAR(32) NOT NULL,
  `token`  VARCHAR(128) NULL
) ENGINE=INNODB;

ALTER TABLE user ALTER COLUMN token VARCHAR(128);
ALTER TABLE user RENAME COLUMN id to uuid;
commit;

DROP TABLE `testapp`.`message`;
CREATE TABLE `testapp`.`message` (
  `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `timestamp` TIMESTAMP NOT NULL,
  `sender` VARCHAR(36) NOT NULL,
  `recipient` VARCHAR(36) NOT NULL,
  `contenttype`  VARCHAR(32) NOT NULL,
  `contenttext`  VARCHAR(512) NOT NULL
 ) ENGINE=INNODB;
 
-- note no foreign key constraints have been add now indexes at this time

    
    
##Running the SpringBoot Application.
	From within the microservice folder execute the command `mvn spring-boot:run`
	
##Running the Test Application 
	From within Eclipse run the java application from the test subproject 'TestApp'
	
	
	
	

## Snippets from emails

Questions coming later today as well as changes in the design approach….  Specifically deployment and messaging changes.

Sent from my iPhone

On Dec 19, 2022
------------------------------------------------

Hi Doug,
As discussed, as a next step, we'd like you to complete our Backend Engineering project, which you can do from home and send over by Sunday if that's ok with you. Then after we review it, we'll have you present it to a few members of our engineering team. You can do it in Go, Python, Java or NodeJS.

You can find/download our challenge project here in our challenge project gdrive. Please, let us know if you have any issues downloading/viewing the drive.

User: asapp
Password: makeithappen

Once you’ve completed the project, please send it as a reply to this email. You can submit it in a number of ways: a gzip or zip file, a Google Drive (or similar) link. Please do not send your project in a new email thread. If for some reason you are unable to send it through a reply to this email, please host it on your own google drive and send us a link to download it.

We’re really excited to be going through the challenge process with you. We look forward to seeing what you come up with!

Mike

------------------------------------------------

Michael,
I noticed that there are possibilities to do this in several languages though in JAVA the skeleton is in Spark.  Can it be implemented in Spring Framework as an alternative?   I see that there is some flexibility in terms of the database...   

Douglas K. Ahlquist
------------------------------------------------
Hi Doug, you can most definitely implement with Java/Spring or any language and frameworks you're most experienced with!

------------------------------------------------
Michael,
This was a fun project.   It's not finished yet,  There are some design issues that I would like top discuss before its completed.
The first of which is the use of Integers (per the OpenApi doc).   I think that that is a security flaw.   Let's talk about that tomorrow or sometime this week.

Other than that.
The Spring APO/Interceptor is not implemented as well as the token generation, that hangs on the ID scheme that we use.
The ID clarification/security change has its 'fingers' in all of the controllers, services, models and repositories.   This is big.
Not that said, this is a coding challenge.   I spent maybe 15 hours overall on this.   I'd like to finish this after the discussion.  
I will probably do that anyway.

There are more Spring Framework templates that I have created/posted on one of my GitHub sites.
https://github.com/Communication-IO/spring-framework-templates.

I have attached the zip file.   In the archive there are two folders; monolith and test.   'test' is obviously the beginnings of a test application that will hit the endpoints of the challenge application.   Monolith (while probably named poorly) is where the near 80% complete code exists.    At the root folder is a pom file that builds and executes both projects.   Navigate into the 'monolith' folder and execute that maven pom, with the `mvn clean install` command to both (for the future) clean and then build the code.

To run the application execute `mvn spring-boot;run` to start .    All the controllers are present.   
(GET) http://127.0.0.1:8080/check and (POST) http://127.0.0.1:8080/user both work currently, and much of 'message' also works.
just waiting on direction for the IDs

Another issue deals with the 'DispatchServlet' which complain 'Mapping Not Found'


I would appreciate to hear your thoughts.

Douglas K. Ahlquist

------------------------------------------------


Michael,
Awesome

------------------------------------------------

I forgot one thing.  I built this on top of a MySQL database.   The configuration is in src/main/resourses/application.properties and the schema is defined in data.sql.


Douglas K. Ahlquist

------------------------------------------------
Hi Douglas, thanks for taking the time to send this over, much appreciated. If it’s not done yet, I won’t submit it to the hiring team yet. Let me know what specific questions you have via email and I will make sure to have the questions answered (if you do have questions).

Thanks again!

------------------------------------------------

Michael,

I have uploaded the configuration README.md file.
I noticed that the 'google drive share was on the previous zip file.   So here is one for the entire project instead.   
https://drive.google.com/drive/folders/1pqJPrjhSf0Od32f8BH0zr7qgSnZ4e1SA?usp=share_link

There is one RESTful endpoint that was not discussed that could help retrieving non-text messages from AWS.   If desired by the team I will implement that as well.   
Here is an example of what is returned in the GetMessages API call.  Note that message "18" refers to a PNG file.   My thoughts were embedding the entire object in the message would be very costly in time time and since the default message count is '100' and if you have many images of 5MB, this call would take forever...  
I would love to discuss this implementation with the team, if they'd like.   And maybe even demo it, to save them time.
I spent about 40 hours on the whole project over the last few weeks.
{

"id": 17,

"timestamp": "2023-01-03T21:17:55Z",

"sender": "ce01051f-c247-40a6-b3fc-b5e52f1fd0df",

"recipient": "ce01051f-c247-40a6-b3fc-b5e52f1fd0df",

"content": {

"type": "plain/text",

"text": "1672809474613 Lot\u0027s and Lot\u0027s and Lot\u0027s of fun and crazy text"

}

},

{

"id": 18,

"timestamp": "2023-01-03T21:18:05Z",

"sender": "ce01051f-c247-40a6-b3fc-b5e52f1fd0df",

"recipient": "ce01051f-c247-40a6-b3fc-b5e52f1fd0df",

"content": {

"type": "image/png",

"text": "ce01051f-c247-40a6-b3fc-b5e52f1fd0df-1672809475741_3c776e71-f133-44cb-80ba-636d33c67a6a.1"

}

}



Douglas K. Ahlquist


	



   
   
   
   



