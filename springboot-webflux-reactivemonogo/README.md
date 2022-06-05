Install MongoDB Community Edition
Prerequisites
Ensure your system meets each of the following prerequisites. You only need to perform each prerequisite step once on your system. If you have already performed the prerequisite steps as part of an earlier MongoDB installation using Homebrew, you can skip to the installation procedure.

Install Xcode Command-Line Tools
Homebrew requires the Xcode command-line tools from Apple’s Xcode.

Install the Xcode command-line tools by running the following command in your macOS Terminal:

xcode-select --install
Install Homebrew
macOS does not include the Homebrew brew package by default.

Install brew using the official Homebrew installation instructions.
Installing MongoDB 4.2 Community Edition
Follow these steps to install MongoDB Community Edition using Homebrew’s brew package manager. Be sure that you have followed the installation prerequisites above before proceeding.

Tap the MongoDB Homebrew Tap to download the official Homebrew formula for MongoDB and the Database Tools, by running the following command in your macOS Terminal:

brew tap mongodb/brew
If you have already done this for a previous installation of MongoDB, you can skip this step.

To update Homebrew and all existing formulae:

brew update
To install MongoDB, run the following command in your macOS Terminal application:

brew install mongodb-community@4.2
Alternatively, you can specify a previous version of MongoDB if desired. You can also maintain multiple versions of MongoDB side by side in this manner.

TIP

If you have previously installed an older version of the formula, you may encounter a ChecksumMismatchError. To resolve, see Troubleshooting ChecksumMismatchError.

The installation includes the following binaries:

The mongod server
The mongos sharded cluster query router
The mongo shell
In addition, the installation creates the following files and directories at the location specified below, depending on your Apple hardware:

Intel Processor	Apple M1 Processor
configuration file	/usr/local/etc/mongod.conf	/opt/homebrew/etc/mongod.conf
log directory	/usr/local/var/log/mongodb	/opt/homebrew/var/log/mongodb
data directory	/usr/local/var/mongodb	/opt/homebrew/var/mongodb
See Apple’s documentation for the current list of Apple hardware using the M1 processor. You can also run the following command to check where brew has installed these files and directories:

brew --prefix
Run MongoDB Community Edition
Follow these steps to run MongoDB Community Edition. These instructions assume that you are using the default settings.

You can run MongoDB as a macOS service using brew, or you can run MongoDB manually as a background process. It is recommended to run MongoDB as a macOS service, as doing so sets the correct system ulimit values automatically (see ulimit settings for more information).

To run MongoDB (i.e. the mongod process) as a macOS service, issue the following:

brew services start mongodb-community@4.2
To stop a mongod running as a macOS service, use the following command as needed:

brew services stop mongodb-community@4.2
To run MongoDB (i.e. the mongod process) manually as a background process, issue the following:

For macOS running Intel processors:

mongod --config /usr/local/etc/mongod.conf --fork
For macOS running on Apple M1 processors:

mongod --config /opt/homebrew/etc/mongod.conf --fork
To stop a mongod running as a background process, connect to the mongod from the mongo shell, and issue the shutdown command as needed.

Both methods use the mongod.conf file created during the install. You can add your own MongoDB configuration options to this file as well.

MACOS PREVENTS MONGOD FROM OPENING

macOS may prevent mongod from running after installation. If you receive a security error when starting mongod indicating that the developer could not be identified or verified, do the following to grant mongod access to run:

Open System Preferences
Select the Security and Privacy pane.
Under the General tab, click the button to the right of the message about mongod, labelled either Open Anyway or Allow Anyway depending on your version of macOS.
To verify that MongoDB is running, perform one of the following:

If you started MongoDB as a macOS service:

brew services list
You should see the service mongodb-community listed as started.

If you started MongoDB manually as a background process:

ps aux | grep -v grep | grep mongod
You should see your mongod process in the output.

You can also view the log file to see the current status of your mongod process: /usr/local/var/log/mongodb/mongo.log.

Connect and Use MongoDB
To begin using MongoDB, connect a mongo shell to the running instance. From a new terminal, issue the following:

mongo
MACOS PREVENTS MONGO FROM OPENING

macOS may prevent the mongo shell from running after installation. If you receive a security error when starting the mongo shell indicating that the developer could not be identified or verified, do the following to grant the mongo shell access to run:

Open System Preferences
Select the Security and Privacy pane.
Under the General tab, click the button to the right of the message about the mongo shell, labelled either Open Anyway or Allow Anyway depending on your version of macOS.


Installing the MongoDB Database Tools
The MongoDB Database Tools are a collection of command-line utilities for working with a MongoDB deployment, including data backup and import/export tools like mongoimport and mongodump as well as monitoring tools like mongotop.

Follow these steps to install the MongoDB Database Tools using Homebrew’s brew package manager.

Verify that your system meets all the installation prerequisites by running the following command in your macOS Terminal:

brew tap | grep mongodb
You should see the MongoDB brew tap listed. If you do not, or you receive an error, return to the Prerequisites section.

To install the MongoDB Database Tools, run the following command in your macOS Terminal application:

brew install mongodb-database-tools
Once installed, the Database Tools are available directly from the command line in your macOS Terminal application.

For example you could run mongotop against a running MongoDB instance by invoking it in your macOS Terminal like so:

mongotop
It should start up, connect to your running mongod, and start reporting usage statistics.

See the MongoDB Database Tools Documentation for usage information for each of the Database Tools.

Additional Information
Localhost Binding by Default
By default, MongoDB launches with bindIp set to 127.0.0.1, which binds to the localhost network interface. This means that the mongod can only accept connections from clients that are running on the same machine. Remote clients will not be able to connect to the mongod, and the mongod will not be able to initialize a replica set unless this value is set to a valid network interface.

This value can be configured either:

in the MongoDB configuration file with bindIp, or
via the command-line argument --bind_ip

For more information on configuring bindIp, see IP Binding.

Troubleshooting ChecksumMismatchError
If you have previously installed an older version of the formula, you may encounter a ChecksumMismatchError resembling the following:

Error: An exception occurred within a child process:

  ChecksumMismatchError: SHA256 mismatch

Expected: c7214ee7bda3cf9566e8776a8978706d9827c1b09017e17b66a5a4e0c0731e1f

  Actual: 6aa2e0c348e8abeec7931dced1f85d4bb161ef209c6af317fe530ea11bbac8f0

Archive: /Users/kay/Library/Caches/Homebrew/downloads/a6696157a9852f392ec6323b4bb697b86312f0c345d390111bd51bb1cbd7e219--mongodb-macos-x86_64-4.2.0.tgz

To retry an incomplete download, remove the file above.
To fix:

Remove the downloaded .tgz archive.

Retap the formula.

brew untap mongodb/brew && brew tap mongodb/brew
Retry the install.

brew install mongodb-community@4.2
