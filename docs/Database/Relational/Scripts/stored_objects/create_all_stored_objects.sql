USE `erb_hub`;

-- FUNCTIONS
SET GLOBAL log_bin_trust_function_creators = 1;

DROP FUNCTION IF EXISTS f_calculate_total_duration_of_battles;

DELIMITER //

CREATE FUNCTION f_calculate_total_duration_of_battles()
RETURNS CHAR(8)

BEGIN
	-- DECLARATIONS
	DECLARE v_total_time CHAR(8) DEFAULT 0;
    -- ============
    
    -- EXECUTION
    SELECT cast(date_format(sec_to_time(sum(time_to_sec(str_to_date(duration, '%i:%s')))), '%H:%i:%s') as CHAR)
    INTO v_total_time FROM battles b;
    RETURN v_total_time;
    -- =========
END;//

DELIMITER ;


-- PROCEDURES
DROP PROCEDURE IF EXISTS p_get_battle_characters;

DELIMITER //

CREATE PROCEDURE p_get_battle_characters(battle_id INT)
BEGIN
	SELECT * FROM v_see_battles_with_characters v WHERE v.id = battle_id;
END; //

DELIMITER ;

DROP PROCEDURE IF EXISTS p_get_character_actors;

DELIMITER //

CREATE PROCEDURE p_get_character_actors(character_id INT)
BEGIN
	SELECT * FROM v_see_characters_with_actors v WHERE v.character_id = character_id;
END; //

DELIMITER ;

DROP PROCEDURE IF EXISTS p_get_actor_characters;

DELIMITER //

CREATE PROCEDURE p_get_actor_characters(actor_id INT)
BEGIN
	SELECT * FROM v_see_characters_with_actors v WHERE v.actor_id = actor_id;
END; //

DELIMITER ;

DROP PROCEDURE IF EXISTS p_get_actor_social_media;

DELIMITER //

CREATE PROCEDURE p_get_actor_social_media(actor_id INT)
BEGIN
	SELECT * FROM v_see_actors_with_social_media v WHERE v.actor_id = actor_id;
END; //

DELIMITER ;

DROP PROCEDURE IF EXISTS p_get_all_battle_details;

DELIMITER //

CREATE PROCEDURE p_get_all_battle_details(battle_id INT)
BEGIN
	SELECT * FROM v_see_all_battle_details v WHERE v.battle_id = battle_id;
END; //

DELIMITER ;

-- VIEWS
CREATE OR REPLACE VIEW v_see_characters_with_actors AS
	SELECT 
		c.id as character_id, 
        c.name as character_name, 
        a.id as actor_id,
        a.name as actor_name, 
        a.alias, 
        a.description 
	FROM characters c
    JOIN actors_characters ac ON c.id = ac.characters_id
    JOIN actors a ON ac.actors_id = a.id
    ORDER BY c.id ASC;
    
CREATE OR REPLACE VIEW v_see_actors_with_social_media AS
	SELECT 
		a.id as actor_id, 
        a.name as actor_name, 
        a.alias, 
        sm.name,
        asm.link
	FROM actors a
    JOIN actors_social_media asm ON a.id = asm.actors_id
    JOIN social_media sm ON asm.social_media_id= sm.id
    ORDER BY a.id ASC;
    
CREATE OR REPLACE VIEW v_see_all_battle_details AS
	SELECT 
		b.id as battle_id, b.name as battle_name, b.duration, b.publication_date, b.lyrics, b.youtube_link, b.spotify_link, b.cover_image,
        c.id as character_id, c.name as character_name, c.description as character_description, c.image,
        a.id as actor_id, a.name as actor_name, a.alias, a.description as actor_description,          
        sm.name as social_media_name,
        asm.link
	FROM battles b
    JOIN battles_characters bc on b.id = bc.battles_id
    JOIN characters c on bc.characters_id = c.id
    JOIN actors_characters ac on c.id = ac.characters_id
    JOIN actors a on ac.actors_id = a.id
    JOIN actors_social_media asm on a.id = asm.actors_id
    JOIN social_media sm on asm.social_media_id = sm.id
    ORDER BY b.id ASC;
    
CREATE OR REPLACE VIEW v_see_battles_with_characters AS
	SELECT 
		b.id, b.name as battle_name, 
        c.name as character_name, 
        c.description, 
        c.image 
	FROM battles b
    JOIN battles_characters bc ON b.id = bc.battles_id
    JOIN characters c ON bc.characters_id = c.id;