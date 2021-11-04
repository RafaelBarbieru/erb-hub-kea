USE `erb_hub`;

DROP PROCEDURE IF EXISTS p_get_actor_characters;

DELIMITER //

CREATE PROCEDURE p_get_actor_characters(actor_id INT)
BEGIN
	SELECT * FROM v_see_characters_with_actors v WHERE v.actor_id = actor_id;
END; //

DELIMITER ;