DROP DATABASE IF EXISTS apiTrackCash;
CREATE DATABASE apiTrackCash DEFAULT CHARACTER SET UTF8MB4 DEFAULT COLLATE UTF8MB4_UNICODE_CI;
USE apiTrackCash;

CREATE TABLE `defaultChannels` (
    `channel_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `type` ENUM('Marketplace', 'Plataforma/ERP', 'Meio de Pagamento') NOT NULL,
    `auth` ENUM('TOKEN', 'LOGIN') NOT NULL,
    PRIMARY KEY (`channel_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_UNICODE_CI;

INSERT INTO defaultChannels (`name`, `type`, `auth`) VALUES ('Mercado Livre', 'Marketplace', 'TOKEN'),
                                                            ('Lojas Americanas', 'Marketplace', 'LOGIN'),
                                                            ('Hub2b', 'Plataforma/ERP', 'TOKEN'),
                                                            ('Amazon', 'Marketplace', 'TOKEN'),
                                                            ('B2w', 'Marketplace', 'LOGIN'),
                                                            ('Dafiti', 'Marketplace', 'LOGIN'),
                                                            ('GetNet', 'Meio de Pagamento', 'TOKEN'),
                                                            ('Carrefour', 'Marketplace', 'TOKEN'),
                                                            ('Bling', 'Plataforma/ERP', 'LOGIN'),
                                                            ('Magazine Luiza', 'Marketplace', 'LOGIN');

CREATE TABLE `users` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(255) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `document` VARCHAR(255) NOT NULL,
    `type_adm` ENUM('0', '1') DEFAULT '0' NOT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_UNICODE_CI;

INSERT INTO users (email, password, phone, name, document, type_adm) VALUES ('usuario01@gmail.com', 'usuario01p', '00 12345-6789', 'Usuário 01', '123.456.789-00', DEFAULT),
                                                                            ('usuario02@gmail.com', 'usuario02p', '00 12345-6789', 'Usuário 02', '123.456.789-00', DEFAULT),
                                                                            ('usuario03@gmail.com', 'usuario03p', '00 12345-6789', 'Usuário 03', '123.456.789-00', DEFAULT),
                                                                            ('admin', 'admin', 'admin', 'admin', 'admin', 1);

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
    CONSTRAINT `fk_registeredChannelLogin_channel` FOREIGN KEY (`channel_id`) REFERENCES `defaultChannels` (`channel_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_UNICODE_CI;

CREATE TABLE `registeredChannelToken` (
    `registeredChannelToken_id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `channel_id` INT NOT NULL,
    `token` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`registeredChannelToken_id`),
    KEY `fk_channel_user` (`user_id`),
    KEY `fk_registeredChannelToken_channel` (`channel_id`),
    CONSTRAINT `fk_registeredChannelToke_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
    CONSTRAINT `fk_registeredChannelToken_channel` FOREIGN KEY (`channel_id`) REFERENCES `defaultChannels` (`channel_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_UNICODE_CI;
