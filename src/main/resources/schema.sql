DROP TABLE IF EXISTS movie_actors;
DROP TABLE IF EXISTS movie;

CREATE TABLE movie (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  director VARCHAR(255) NOT NULL,
  release_year  INT NOT NULL,
  synopsis VARCHAR(2000),
  duration VARCHAR(50),
  trailer_url VARCHAR(500),
  image VARCHAR(500),
  category VARCHAR(100),
  language VARCHAR(100),
  price_rental INT,
  price_purchase INT,
  visible BOOLEAN DEFAULT TRUE
);

CREATE TABLE movie_actors (
  movie_id BIGINT NOT NULL,
  actors VARCHAR(255),
  CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES movie(id)
);
