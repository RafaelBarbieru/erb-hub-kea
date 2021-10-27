USE `erb_hub`;

DROP PROCEDURE IF EXISTS p_get_all_battle_details;

DELIMITER //

CREATE PROCEDURE p_get_all_battle_details(battle_id INT)
BEGIN
	SELECT * FROM v_see_all_battle_details v WHERE v.battle_id = battle_id;
END; //

DELIMITER ;