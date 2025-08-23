INSERT INTO movie (id, title, director, release_year, synopsis, duration, trailer_url, image, category, language, price_rental, price_purchase, visible)
VALUES
(1, 'Matrix', 'The Wachowskis', 1999, 'Un hacker descubre la verdad sobre su realidad y su papel en la guerra contra sus controladores.', '136 min', 'https://www.youtube.com/embed/vKQi3bBA1y8', 'https://image.tmdb.org/t/p/w300_and_h450_bestv2/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg', 'Ciencia Ficción', 'Inglés', 40, 150, true),
(2, 'El Origen', 'Christopher Nolan', 2010, 'Un ladrón roba secretos corporativos a través de la tecnología de los sueños.', '148 min', 'https://www.youtube.com/embed/YoHD9XEInc0', 'https://image.tmdb.org/t/p/w300_and_h450_bestv2/edv5CZvWj09upOsy2Y6IwDhK8bt.jpg', 'Ciencia Ficción', 'Inglés', 45, 160, true),
(3, 'Gladiador', 'Ridley Scott', 2000, 'Un general romano es traicionado y se convierte en gladiador para vengarse.', '155 min', 'https://www.youtube.com/embed/owK1qxDselE', 'https://image.tmdb.org/t/p/w300_and_h450_bestv2/ty8TGRuvJLPUmAR1H1nRIsgwvim.jpg', 'Acción', 'Inglés', 35, 140, true),
(4, 'Parásitos', 'Bong Joon-ho', 2019, 'Una familia pobre se infiltra en la vida de una familia rica.', '132 min', 'https://www.youtube.com/embed/5xH0HfJHsaY', 'https://image.tmdb.org/t/p/w300_and_h450_bestv2/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg', 'Drama', 'Coreano', 50, 170, true),
(5, 'Blade Runner 2049', 'Denis Villeneuve', 2017, 'Un joven blade runner descubre un secreto que podría desestabilizar la sociedad.', '164 min', 'https://www.youtube.com/embed/gCcx85zbxz4', 'https://image.tmdb.org/t/p/w300_and_h450_bestv2/aMpyrCizvSdc0UIMblJ1srVgAEF.jpg', 'Ciencia Ficción', 'Inglés', 50, 180, true),
(6, 'Interstellar', 'Christopher Nolan', 2014, 'Un grupo de astronautas viaja a través de un agujero de gusano en busca de un nuevo hogar para la humanidad.', '169 min', 'https://www.youtube.com/embed/zSWdZVtXT7E', 'https://image.tmdb.org/t/p/w300_and_h450_bestv2/rAiYTfKGqDCRIIqo664sY9XZIvQ.jpg', 'Ciencia Ficción', 'Inglés', 45, 160, true),
(7, 'Frozen', 'Chris Buck', 2013, 'La princesa Anna se une a un montañero para encontrar a su hermana Elsa.', '102 min', 'https://www.youtube.com/embed/TbQm5doF_Uc', 'https://image.tmdb.org/t/p/w300_and_h450_bestv2/kgwjIb2JDHRhNk13lmSxiClFjVk.jpg', 'Animación', 'Español', 30, 130, true),
(8, 'Avengers: Endgame', 'Anthony y Joe Russo', 2019, 'Los Vengadores se reúnen para derrotar a Thanos.', '181 min', 'https://www.youtube.com/embed/TcMBFSGVi1c', 'https://image.tmdb.org/t/p/w300_and_h450_bestv2/ulzhLuWrPK07P1YkdWQLZnQh1JL.jpg', 'Acción', 'Inglés', 55, 200, true),
(9, 'La La Land', 'Damien Chazelle', 2016, 'Un músico y una actriz se enamoran persiguiendo sus sueños en Los Ángeles.', '128 min', 'https://www.youtube.com/embed/0pdqf4P9MB8', 'https://image.tmdb.org/t/p/w300_and_h450_bestv2/uDO8zWDhfWwoFdKS4fzkUJt0Rf0.jpg', 'Musical', 'Inglés', 40, 150, true),
(10, 'Coco', 'Lee Unkrich', 2017, 'Un niño mexicano viaja al mundo de los muertos para descubrir el legado de su familia.', '105 min', 'https://www.youtube.com/embed/xlnPHQ3TLX8', 'https://image.tmdb.org/t/p/w300_and_h450_bestv2/gGEsBPAijhVUFoiNpgZXqRVWJt2.jpg', 'Animación', 'Español', 35, 140, true);

-- Ahora insertamos los actores en la tabla intermedia:

INSERT INTO movie_actors (movie_id, actors) VALUES
(1, 'Keanu Reeves'),
(2, 'Leonardo DiCaprio'),
(3, 'Russell Crowe'),
(4, 'Song Kang-ho'),
(5, 'Ryan Gosling'),
(6, 'Matthew McConaughey'),
(7, 'Kristen Bell'),
(8, 'Robert Downey Jr.'),
(9, 'Emma Stone'),
(10, 'Anthony Gonzalez');
