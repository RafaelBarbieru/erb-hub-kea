USE `erb_hub`;

DROP PROCEDURE IF EXISTS p_get_character_actors;

DELIMITER //

CREATE PROCEDURE p_get_character_actors(character_id INT)
BEGIN
	SELECT * FROM v_see_characters_with_actors v WHERE v.character_id = character_id;
END; //

DELIMITER ;