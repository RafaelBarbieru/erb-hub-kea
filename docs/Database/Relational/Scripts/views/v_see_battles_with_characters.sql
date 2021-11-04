USE `erb_hub`;

CREATE OR REPLACE VIEW v_see_battles_with_characters AS
	SELECT 
		b.id, b.name as battle_name, 
        c.name as character_name, 
        c.description, 
        c.image 
	FROM battles b
    JOIN battles_characters bc ON b.id = bc.battles_id
    JOIN characters c ON bc.characters_id = c.id;
