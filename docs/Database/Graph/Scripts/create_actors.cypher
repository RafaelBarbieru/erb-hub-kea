LOAD CSV WITH HEADERS FROM "file:///actors.csv" AS row 
WITH row.`name` as name, 
     row.`description` as description, 
     row.`image` as image
MERGE (a:Actor {name: name}) 
ON CREATE SET a.description = description,
              a.alias = alias,
              a.image = image