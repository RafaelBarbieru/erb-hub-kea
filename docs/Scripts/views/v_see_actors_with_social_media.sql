USE `erb_hub`;

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
