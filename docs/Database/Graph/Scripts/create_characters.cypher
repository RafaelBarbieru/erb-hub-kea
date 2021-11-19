LOAD CSV WITH HEADERS FROM "file:///characters.csv" AS row 
WITH row.`name` as name, 
     row.`description` as description,  
     row.`image` as image
MERGE (c:Character {name: name}) 
ON CREATE SET c.description = description,
              c.image = image