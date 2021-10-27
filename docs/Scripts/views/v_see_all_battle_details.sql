USE `erb_hub`;

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