USE `erb_hub`;

CREATE TABLE IF NOT EXISTS	`battles`(

	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`duration` int NOT NULL,
	`publication_date` date NOT NULL,
	`lyrics` text	NOT NULL,
	`youtube_link` varchar(255) DEFAULT NULL,
	`spotify_link` varchar(255) DEFAULT	NULL,
	`cover_image` VARCHAR(255)	DEFAULT NULL,	
	`created_at` timestamp DEFAULT NULL,
	`updated_at` timestamp DEFAULT NULL,
	
	PRIMARY KEY (`id`)
	
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `characters` (	

	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`description` text DEFAULT NULL,
	`image` varchar(255) DEFAULT NULL,
	`created_at` timestamp DEFAULT NULL,
	`updated_at` timestamp DEFAULT NULL,
	
	PRIMARY KEY (`id`)	 
	
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `battles_characters` (

	`battle_id` int NOT NULL,
	`character_id` int NOT NULL,
	
	PRIMARY KEY (`battle_id`,`character_id`),
	CONSTRAINT `fk_battle_id` FOREIGN KEY (`battle_id`) REFERENCES `battles` (`id`) ON UPDATE CASCADE,
	CONSTRAINT `fk_character_id` FOREIGN KEY (`character_id`) REFERENCES `characters` (`id`) ON UPDATE CASCADE
	
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `actors` (

	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`alias` varchar(255) NULL,   
	`description` text NULL,
	`image` varchar(255) NULL,
	`created_at` timestamp DEFAULT NULL,
	`updated_at` timestamp DEFAULT NULL,
	
	PRIMARY KEY (`id`)
	
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `actors_characters` (

	`actor_id` int NOT NULL ,
	`character_id` int NOT NULL ,	 
	
	PRIMARY KEY (`actor_id`,`character_id`),	 
	CONSTRAINT `fk_actor_id` FOREIGN KEY (`actor_id`) REFERENCES `actors` (`id`),
	CONSTRAINT `fk_character_id_ac` FOREIGN KEY (`character_id`) REFERENCES `characters` (`id`) ON UPDATE CASCADE
	
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `social_media` (

	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,	 
	`created_at` timestamp DEFAULT NULL,
	`updated_at` timestamp DEFAULT NULL,
	
	PRIMARY KEY (`id`)	 
	
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `actors_social_media` (

	`actor_id` int NOT NULL ,
	`social_media_id` int NOT NULL ,
	`link` varchar(255) NULL,
		 
	PRIMARY KEY (`actor_id`,`social_media_id`),
	
	CONSTRAINT `fk_actor_id_asm` FOREIGN KEY (`actor_id`) REFERENCES `actors` (`id`) ON UPDATE CASCADE,
	CONSTRAINT `fk_social_id` FOREIGN KEY (`social_media_id`) REFERENCES `social_media` (`id`) ON UPDATE CASCADE
	
) ENGINE=InnoDB AUTO_INCREMENT=1;


