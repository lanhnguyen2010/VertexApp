DROP TABLE IF EXISTS users;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE users(
  ID SERIAL PRIMARY KEY,
  username VARCHAR(100) NOT NULL,
  password varchar(200) DEFAULT NULL,
  firstname varchar(45) DEFAULT NULL,
  lastname VARCHAR(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  phone varchar(45) DEFAULT NULL
);

