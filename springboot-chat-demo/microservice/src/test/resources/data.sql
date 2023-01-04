-- DROP DATABASE `testapp`;
-- CREATE DATABASE `testapp`;
-- USE `testapp`;

DROP TABLE IF EXISTS `uniqueid` CASCADE;
CREATE TABLE `uniqueid` (
   `uniqueid` VARCHAR(36) NOT NULL UNIQUE
);


DROP TABLE IF EXISTS "user" CASCADE;
CREATE TABLE "user" (
  `id`  VARCHAR(36) NOT NULL  UNIQUE,
   `username` VARCHAR(64) NOT NULL UNIQUE,
  `password` VARCHAR(32) NOT NULL,
 `token`  VARCHAR(84) NOT NULL
);


DROP TABLE IF EXISTS `messsage` CASCADE;
CREATE TABLE `messsage` (
   `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `timestamp` TIMESTAMP NOT NULL,
   `sender` VARCHAR(36) NOT NULL,
   `recepiant` VARCHAR(36) NOT NULL,
   `contenttype`  VARCHAR(32) NOT NULL,
   `contenttext`  VARCHAR(512) NOT NULL
);


