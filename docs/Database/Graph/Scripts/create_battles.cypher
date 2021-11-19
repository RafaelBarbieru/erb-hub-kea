LOAD CSV WITH HEADERS FROM "file:///battles.csv" AS row 
WITH row.`name` as name, 
     row.`duration` as duration, 
     row.`lyrics` as lyrics, 
     row.`publication_date` as publication_date, 
     row.`youtube_link` as youtube_link,
     row.`spotify_link` as spotify_link,
     row.`cover_image` as cover_image
MERGE (b:Battle {duration: toInteger(duration) }) 
ON CREATE SET b.name = name,
              b.lyrics = lyrics,
              b.publication_date = publication_date,
              b.youtube_link = youtube_link,
              b.spotify_link = spotify_link,
              b.cover_image = cover_image;