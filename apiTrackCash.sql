DROP DATABASE IF EXISTS apiTrackCash;

CREATE DATABASE apiTrackCash DEFAULT CHARACTER SET UTF8MB4 DEFAULT COLLATE UTF8MB4_GENERAL_CI;

USE apiTrackCash;


DROP TABLE IF EXISTS `defaultchannel`;

CREATE TABLE `defaultchannel` (
  `channel_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `type` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`channel_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;


DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `document` VARCHAR(255) NOT NULL,
  `type_adm` ENUM('0', '1') NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;


DROP TABLE IF EXISTS `registeredChannelLogin`;

CREATE TABLE `registeredChannelLogin` (
  `registeredChannelLogin_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `channel_id` INT NOT NULL,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`registeredChannelLogin_id`),
  KEY `fk_channel_user` (`user_id`),
  KEY `fk_registeredChannelLogin_channel` (`channel_id`),
  CONSTRAINT `fk_registeredChannelLogin_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_registeredChannelLogin_channel` FOREIGN KEY (`channel_id`) REFERENCES `defaultchannel` (`channel_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;

DROP TABLE IF EXISTS `registeredChannelToken`;

CREATE TABLE `registeredChannelToken` (
  `registeredChannelToken_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `channel_id` INT NOT NULL,
  `token` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`registeredChannelToken_id`),
  KEY `fk_channel_user` (`user_id`),
  KEY `fk_registeredChannelToken_channel` (`channel_id`),
  CONSTRAINT `fk_registeredChannelToke_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_registeredChannelToken_channel` FOREIGN KEY (`channel_id`) REFERENCES `defaultchannel` (`channel_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;
