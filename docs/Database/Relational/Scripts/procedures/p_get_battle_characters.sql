USE `erb_hub`;

DROP PROCEDURE IF EXISTS p_get_battle_characters;

DELIMITER //

CREATE PROCEDURE p_get_battle_characters(battle_id INT)
BEGIN
	SELECT * FROM v_see_battles_with_characters v WHERE v.id = battle_id;
END; //

DELIMITER ;