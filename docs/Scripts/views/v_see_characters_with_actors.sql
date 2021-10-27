USE `erb_hub`;

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
