INSERT INTO `usuario`(`userNome`, `email`, `senha`, `dataNasci`, `userLocalizacao`, `Longitude`, `Latitude`, `dataIngresso`) VALUES
('Gabriel Ribeiro', 'ribeiro@gmail.com', 'Q1O0V0T0', '1995-03-01', 'Jundiai', -83, 28, '2015-01-19'),
('Gabriel Naressi', 'naressi@gmail.com', 'Q1O0V0T0', '1996-02-05', 'Sao Jose', -83, 28, '2015-01-19'),
('Larissa Kaori', 'kaori@gmail.com', 'Q1O0V0T0', '1997-03-05', 'Sao Paulo', -83, 28, '2015-01-19'),
('Larissa Ashley', 'ashley@gmail.com', 'Q1O0V0T0', '1998-03-05', 'Peruibe', -83, 28, '2015-01-19'),
('Marco Modena', 'modena@gmail.com', 'Q1O0V0T0', '1999-02-05', 'Indaiatuba', -83, 28, '2015-01-19');

INSERT INTO `relacionamento` (`relacTipo`, `IDusuario`, `R_U_IDusuario`) VALUES ('1', '1', '2');
INSERT INTO `relacionamento` (`relacTipo`, `IDusuario`, `R_U_IDusuario`) VALUES ('1', '1', '3');
INSERT INTO `relacionamento` (`relacTipo`, `IDusuario`, `R_U_IDusuario`) VALUES ('1', '1', '4');
INSERT INTO `relacionamento` (`relacTipo`, `IDusuario`, `R_U_IDusuario`) VALUES ('1', '1', '5');

INSERT INTO `post` (`IDusuario`, `postConteudo`, `postData`, `postMusica`, `postPlaylist`, `postEvento`, `postUsuario`) VALUES ('2', 'muito loca esse rede social!!', '2017-06-13 23:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `post` (`IDusuario`, `postConteudo`, `postData`, `postMusica`, `postPlaylist`, `postEvento`, `postUsuario`) VALUES ('2', 'nada pra fazer.. vou ouvir musica', '2017-06-12 15:40:00', NULL, NULL, NULL, NULL);
INSERT INTO `post` (`IDusuario`, `postConteudo`, `postData`, `postMusica`, `postPlaylist`, `postEvento`, `postUsuario`) VALUES ('2', 'nada pra ouvir.. vo fazer alguma coisa', '2017-06-12 15:30:00', NULL, NULL, NULL, NULL);
INSERT INTO `post` (`IDusuario`, `postConteudo`, `postData`, `postMusica`, `postPlaylist`, `postEvento`, `postUsuario`) VALUES ('2', 'bom dia familia', '2017-06-11 21:23:00', NULL, NULL, NULL, NULL);



INSERT INTO `categoria`(`categNome`)  VALUES
('MPB'),
('Indie'),
('Pop'),
('EDM'),
('k-pop'),
('Rock'),
('Rap'),
('Instrumental');


INSERT INTO `evento` (`eventoNome`, `eventoData`, `eventoHorario`, `eventoLocalizacao`, `eventoDescricao`) VALUES
('Lollapaloza', '2008-02-11', '2013-09-27', 'overland park', 'Eae jao'),
('Coala Festival', '2008-02-11', '2013-09-27', 'overland park', 'Eae jao'),
('Roberto Carlos 99 anos', '2008-02-11', '2013-09-27', 'overland park', 'Eae jao');

INSERT INTO `artista`(`artistaNome`, `tipo`, `biografia`)  VALUES
('Ceu', 'Group', 'biografia'),
('Jair Naves', 'Single', 'biografia'),
('O terno', 'Group', 'biografia'),
('Supercombo', 'Group', 'biografia'),
('Green Day', 'Single', 'biografia'),
('Lana Del Rey', 'Single', 'biografia'),
('Disclosure', 'Group', 'biografia'),
('The National', 'Group', 'biografia');

INSERT INTO `album`(`albumNome`, `albumData`) VALUES
('Ceu ', '2005-01-22'),
('E voce se sente numa cela escura	', '2012-01-16'),
('Culpa', '2016-02-24'),
('Rogerio', '2016-02-24'),
('Bullet in a Bible', '2009-02-24'),
('Born to die', '2012-02-24'),
('Settle', '2014-02-24'),
('Trouble will find me', '2014-02-24');


INSERT INTO `musica` (`musicaNome`, `tempo`, `IDcategoria`, `IDalbum`, `IDartista`) VALUES
('Malemolencia', 629, 1, 1, 1),
('A meu ver', 629, 1, 2, 2),
('Culpa', 629, 1, 6, 3),
('Grao de areia', 629, 2, 4, 4),
('Boulevard Of Broken Dreams', 629, 6, 5, 5),
('This Is the Last Time', 629, 6, 6, 6),
('Blue Jeans', 629, 1, 7, 7),
('White Noise (Album Version)', 629, 1, 3, 8);
