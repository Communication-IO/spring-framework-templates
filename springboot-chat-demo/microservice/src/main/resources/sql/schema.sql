
DROP DATABASE `testapp`;
CREATE DATABASE `testapp`;
USE `testapp`;


CREATE TABLE `testapp`.`user` (
CREATE TABLE `testapp`.`user` (
  `uuid` VARCHAR(36) PRIMARY KEY NOT NULL,
  `username` VARCHAR(64) NOT NULL UNIQUE,
  `password` VARCHAR(32) NOT NULL,
  `token`  VARCHAR(128) NULL
) ENGINE=INNODB;


DROP TABLE `testapp`.`messsage`;
CREATE TABLE `testapp`.`message` (
  `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `timestamp` TIMESTAMP NOT NULL,
  `sender` VARCHAR(36) NOT NULL,
  `recipient` VARCHAR(36) NOT NULL,
  `contenttype`  VARCHAR(32) NOT NULL,
  `contenttext`  VARCHAR(512) NOT NULL
 ) ENGINE=INNODB;
