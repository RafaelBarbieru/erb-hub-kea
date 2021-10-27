USE `erb_hub`;

DROP PROCEDURE IF EXISTS p_get_actor_social_media;

DELIMITER //

CREATE PROCEDURE p_get_actor_social_media(actor_id INT)
BEGIN
	SELECT * FROM v_see_actors_with_social_media v WHERE v.actor_id = actor_id;
END; //

DELIMITER ;