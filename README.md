##SpringBoot Starter Templates

###How to run the application in your local machine?

####Step 1: Install 'git', 'Maven' and 'Java 17 on you system.  These are the base requirements to download, build and run the projects.   Note, without the database configured properely, you will have some projects fail in testing.

####Step 2: Clone or download the code.  Use the command `git clone https://github.com/Communication-IO/spring-framework-templates.git`

####Step 3: Follow the configuration steps for MongoDb, MySqlDb, and PostgressDb.  Other configuration steps will be needed for other SpringBoot in the future. 
After installing the MySQL DB, execute the following command inside the mysql console:
CREATE  mvn_test database;
CREATE USER 'testuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
GRANT ALL PRIVILEGES ON mvn_test.* TO 'testuser'@'localhost';




####Step 4: Import the projects in your favorite IDE and set the build function to Automatic.
####   Or from the local repository root folder run the command `mvn install`.  To run through all of the projects without running the tests, run this command line instead 'mvn install -Dmaven.test.skip=true'.

#### dependecy: To have 'java' installed and added to you systems path for versions upto Java17.  Note: Not all projects require version 17 at this point in time

