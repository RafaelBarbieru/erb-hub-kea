MATCH (b:Battle), (c:Character) 
WHERE b.name CONTAINS "Richard" AND c.name CONTAINS "Richard" 
CREATE (c)-[r:participates_in]->(b);

MATCH (b:Battle), (c:Character)
WHERE b.name CONTAINS "Ragnar" AND c.name CONTAINS "Ragnar"
CREATE (c)-[r:participates_in]->(b);

MATCH (b:Battle), (c:Character)
WHERE b.name CONTAINS "Thanos" AND c.name CONTAINS "Thanos"
CREATE (c)-[r:participates_in]->(b);

MATCH (b:Battle), (c:Character)
WHERE b.name CONTAINS "Robert" AND c.name CONTAINS "Robert"
CREATE (c)-[r:participates_in]->(b);

MATCH (b:Battle), (c:Character)
WHERE b.name CONTAINS "Joker" AND c.name CONTAINS "Joker"
CREATE (c)-[r:participates_in]->(b);

MATCH (b:Battle), (c:Character)
WHERE b.name CONTAINS "Penny" AND c.name CONTAINS "Penny"
CREATE (c)-[r:participates_in]->(b);

MATCH (b:Battle), (c:Character)
WHERE b.name CONTAINS "Harry" AND c.name CONTAINS "Harry"
CREATE (c)-[r:participates_in]->(b);

MATCH (b:Battle), (c:Character)
WHERE b.name CONTAINS "Luke" AND c.name CONTAINS "Luke"
CREATE (c)-[r:participates_in]->(b);

MATCH (b:Battle), (c:Character)
WHERE b.name CONTAINS "Donald" AND c.name CONTAINS "Donald"
CREATE (c)-[r:participates_in]->(b);

MATCH (b:Battle), (c:Character)
WHERE b.name CONTAINS "Joe" AND c.name CONTAINS "Joe"
CREATE (c)-[r:participates_in]->(b);

MATCH (a:Actor), (c:Character), (b:Battle)
WHERE a.name = "Lloyd Ahlquist" AND c.name = "Ragnar Lodbrok" AND b.name CONTAINS "Ragnar"
CREATE (a)-[r:participates_in]->(b)
CREATE (a)-[r2:plays]->(c);

MATCH (a:Actor), (c:Character), (b:Battle)
WHERE a.name = "Lloyd Ahlquist" AND c.name = "Donald Trump" AND b.name CONTAINS "Donald"
CREATE (a)-[r:plays]->(c)
CREATE (a)-[r2:participates_in]->(b);

MATCH (a:Actor), (c:Character), (b:Battle)
WHERE a.name = "Lloyd Ahlquist" AND c.name = "Thanos" AND b.name CONTAINS "Thanos"
CREATE (a)-[r2:participates_in]->(b)
CREATE (a)-[r:plays]->(c);

MATCH (a:Actor), (c:Character), (b:Battle)
WHERE a.name = "Lloyd Ahlquist" AND c.name = "Pennywise" AND b.name CONTAINS "Pennywise"
CREATE (a)-[r2:participates_in]->(b)
CREATE (a)-[r:plays]->(c);

MATCH (a:Actor), (c:Character), (b:Battle)
WHERE a.name = "Peter Shukoff" AND c.name = "Richard The Lionheart" AND b.name CONTAINS "Richard"
CREATE (a)-[r2:participates_in]->(b)
CREATE (a)-[r:plays]->(c);

MATCH (a:Actor), (c:Character), (b:Battle)
WHERE a.name = "Peter Shukoff" AND c.name = "Luke Skywalker" AND b.name CONTAINS "Luke"
CREATE (a)-[r2:participates_in]->(b)
CREATE (a)-[r:plays]->(c);

MATCH (a:Actor), (c:Character), (b:Battle)
WHERE a.name = "Peter Shukoff" AND c.name = "Joe Biden" AND b.name CONTAINS "Joe"
CREATE (a)-[r2:participates_in]->(b)
CREATE (a)-[r:plays]->(c);

MATCH (a:Actor), (c:Character), (b:Battle)
WHERE a.name = "Peter Shukoff" AND c.name = "J Robert Oppenheimer" AND b.name CONTAINS "J Robert"
CREATE (a)-[r2:participates_in]->(b)
CREATE (a)-[r:plays]->(c);

MATCH (a:Actor), (c:Character), (b:Battle)
WHERE a.name = "Peter Shukoff" AND c.name = "The Joker" AND b.name CONTAINS "Joker"
CREATE (a)-[r2:participates_in]->(b)
CREATE (a)-[r:plays]->(c);

MATCH (a:Actor), (c:Character), (b:Battle)
WHERE a.name = "David Brown" AND c.name = "Harry Potter" AND b.name CONTAINS "Harry"
CREATE (a)-[r2:participates_in]->(b)
CREATE (a)-[r:plays]->(c);