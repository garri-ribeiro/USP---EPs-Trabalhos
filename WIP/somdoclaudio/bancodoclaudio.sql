create table Album (
     IDalbum int not null auto_increment,
     albumNome varchar(500) not null,
     albumData date not null,
     constraint ID_Album_ID primary key (IDalbum)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Artista (
     IDartista int not null auto_increment,
     artistaNome varchar(200) not null,
     tipo varchar(50) not null,
     biografia varchar(500) not null,
     constraint ID_Artista_ID primary key (IDartista)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Categoria (
     IDcategoria int not null auto_increment,
     categNome varchar(200) not null,
     constraint ID_Categoria_ID primary key (IDcategoria)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Comentario (
     IDcomentario int not null auto_increment,
     Data date not null,
     IDpost int not null,
     IDusuario int not null,
     Conteudo varchar(800) not null,
     constraint ID_Comentario_ID primary key (IDcomentario)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Cria (
     IDevento int not null,
     IDusuario int not null,
     constraint ID_Cria_ID primary key (IDusuario, IDevento)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Escuta (
     IDmusica int not null,
     IDusuario int not null,
     constraint ID_Escuta_ID primary key (IDmusica, IDusuario)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Evento (
     IDevento int not null auto_increment,
     eventoNome varchar(500) not null,
     eventoData date not null,
     eventoHorario date not null,
     eventoLocalizacao varchar(500) not null,
     eventoDescricao varchar(500) not null,
     constraint ID_Evento_ID primary key (IDevento)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Curtir (
     IDcurtir int not null auto_increment,
     curtirData date not null,
     IDusuario int not null,
     IDpost int not null,
     constraint ID_Like_ID primary key (IDcurtir)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Mensagem (
     IDmensagem int not null auto_increment,
     msgData date not null,
     msgConteudo varchar(1000) not null,
     IDusuario int not null,
     Env_IDusuario int not null,
     constraint ID_Mensagem_ID primary key (IDmensagem)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Musica (
     IDmusica int not null auto_increment,
     musicaNome varchar(200) not null,
     tempo int not null,
     IDcategoria int not null,
     IDalbum int,
     IDartista int,
     constraint ID_Musica_ID primary key (IDmusica)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Participa (
     IDevento int not null,
     IDusuario int not null,
     constraint ID_Participa_ID primary key (IDusuario, IDevento)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Pertence (
     IDmusica int not null,
     IDplaylist int not null,
     constraint ID_Pertence_ID primary key (IDmusica, IDplaylist)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Playlist (
     IDplaylist int not null auto_increment,
     playlistNome varchar(500) not null,
     IDusuario int not null,
     constraint ID_Playlist_ID primary key (IDplaylist)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Post (
     IDpost int not null auto_increment,
	 IDusuario int not null,
     postConteudo varchar(1000) not null,
     postData datetime not null,
     postMusica int,
     postPlaylist int,
     postEvento int,
     postUsuario int,
     constraint ID_Post_ID primary key (IDpost)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table postEvento (
     IDpost int not null,
     IDevento int not null,
     constraint FKPos_pos_1_ID primary key (IDpost)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table postMusica (
     IDpost int not null,
     caminhoMusica varchar(500) not null,
     constraint FKPos_pos_3_ID primary key (IDpost)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table postPlaylist (
     IDpost int not null,
     IDplaylist int not null,
     constraint FKPos_pos_2_ID primary key (IDpost)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table postUsuario (
     IDpost int not null,
     IDusuario int not null,
     constraint FKPos_pos_ID primary key (IDpost)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Produz (
     IDalbum int not null,
     IDartista int not null,
     constraint ID_Produz_ID primary key (IDartista, IDalbum)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Relacionamento (
     IDrelacionamento int not null auto_increment,
     Acao int not null,
     relacTipo int not null,
     IDusuario int not null,
     R_U_IDusuario int not null,
     constraint ID_Relacionamento_ID primary key (IDrelacionamento)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Segue (
     IDartista int not null,
     IDusuario int not null,
     constraint ID_Segue_ID primary key (IDartista, IDusuario)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Usuario (
     IDusuario int not null auto_increment,
     userNome char(50) not null,
     email varchar(50) not null,
     senha varchar(50) not null,
     dataNasci date not null,
     userLocalizacao varchar(50) not null,
     Longitude bigint not null,
     Latitude bigint not null,
     dataIngresso date not null,
     constraint ID_Usuario_ID primary key (IDusuario),
     constraint SID_Usuario_ID unique (email)) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Constraints Section
-- ___________________

-- Not implemented
-- alter table Album add constraint ID_Album_CHK
--     check(exists(select * from Produz
--                  where Produz.IDalbum = IDalbum));

alter table Comentario add constraint FKFeito_um_FK
     foreign key (IDpost)
     references Post (IDpost);

alter table Comentario add constraint FKComenta_FK
     foreign key (IDusuario)
     references Usuario (IDusuario);

alter table Cria add constraint FKCri_Usu
     foreign key (IDusuario)
     references Usuario (IDusuario);

alter table Cria add constraint FKCri_Eve_FK
     foreign key (IDevento)
     references Evento (IDevento);

alter table Escuta add constraint FKEsc_Usu_FK
     foreign key (IDusuario)
     references Usuario (IDusuario);

alter table Escuta add constraint FKEsc_Mus
     foreign key (IDmusica)
     references Musica (IDmusica);

-- Not implemented
-- alter table Evento add constraint ID_Evento_CHK
--     check(exists(select * from Cria
--                  where Cria.IDevento = IDevento));

alter table Curtir add constraint FKCurtiu_FK
     foreign key (IDusuario)
     references Usuario (IDusuario);

alter table Curtir add constraint FKCurte_FK
     foreign key (IDpost)
     references Post (IDpost);

alter table Mensagem add constraint FKRecebida_por_FK
     foreign key (IDusuario)
     references Usuario (IDusuario);

alter table Mensagem add constraint FKEnviada_por_FK
     foreign key (Env_IDusuario)
     references Usuario (IDusuario);

alter table Musica add constraint FKFaz_parte_FK
     foreign key (IDcategoria)
     references Categoria (IDcategoria);

alter table Musica add constraint FKEsta_em_FK
     foreign key (IDalbum)
     references Album (IDalbum);

alter table Musica add constraint FKCompoe_FK
     foreign key (IDartista)
     references Artista (IDartista);

alter table Participa add constraint FKPar_Usu
     foreign key (IDusuario)
     references Usuario (IDusuario);

alter table Participa add constraint FKPar_Eve_FK
     foreign key (IDevento)
     references Evento (IDevento);

alter table Pertence add constraint FKPer_Pla_FK
     foreign key (IDplaylist)
     references Playlist (IDplaylist);

alter table Pertence add constraint FKPer_Mus
     foreign key (IDmusica)
     references Musica (IDmusica);

-- Not implemented
-- alter table Playlist add constraint ID_Playlist_CHK
--     check(exists(select * from Pertence
--                  where Pertence.IDplaylist = IDplaylist));

alter table Playlist add constraint FKFaz_FK
     foreign key (IDusuario)
     references Usuario (IDusuario);

alter table Post add constraint EXTONE_Post
     check((postUsuario is not null and postEvento is null and postPlaylist is null and postMusica is null)
           or (postUsuario is null and postEvento is not null and postPlaylist is null and postMusica is null)
           or (postUsuario is null and postEvento is null and postPlaylist is not null and postMusica is null)
           or (postUsuario is null and postEvento is null and postPlaylist is null and postMusica is not null));

alter table Post add constraint FKPost_Usuario_FK
     foreign key (IDusuario)
     references Usuario(IDusuario);		   
		   
alter table postEvento add constraint FKPosta_FK
     foreign key (IDevento)
     references Evento (IDevento);

alter table postEvento add constraint FKPos_pos_1_FK
     foreign key (IDpost)
     references Post (IDpost);

alter table postMusica add constraint FKPos_pos_3_FK
     foreign key (IDpost)
     references Post (IDpost);

alter table postPlaylist add constraint FKPos_pos_2_FK
     foreign key (IDpost)
     references Post (IDpost);

alter table postPlaylist add constraint FKPlayPostada_FK
     foreign key (IDplaylist)
     references Playlist (IDplaylist);

alter table postUsuario add constraint FKPos_pos_FK
     foreign key (IDpost)
     references Post (IDpost);

alter table Produz add constraint FKPro_Art
     foreign key (IDartista)
     references Artista (IDartista);

alter table Produz add constraint FKPro_Alb_FK
     foreign key (IDalbum)
     references Album (IDalbum);

alter table Relacionamento add constraint FKUsuario2_FK
     foreign key (IDusuario)
     references Usuario (IDusuario);

alter table Relacionamento add constraint FKRel_Usu_FK
     foreign key (R_U_IDusuario)
     references Usuario (IDusuario);

alter table Segue add constraint FKSeg_Usu_FK
     foreign key (IDusuario)
     references Usuario (IDusuario);

alter table Segue add constraint FKSeg_Art
     foreign key (IDartista)
     references Artista (IDartista);


-- Index Section
-- _____________

create unique index ID_Album_IND
     on Album (IDalbum);

create unique index ID_Artista_IND
     on Artista (IDartista);

create unique index ID_Categoria_IND
     on Categoria (IDcategoria);

create unique index ID_Comentario_IND
     on Comentario (IDcomentario);

create index FKFeito_um_IND
     on Comentario (IDpost);

create index FKComenta_IND
     on Comentario (IDusuario);

create unique index ID_Cria_IND
     on Cria (IDusuario, IDevento);

create index FKCri_Eve_IND
     on Cria (IDevento);

create unique index ID_Escuta_IND
     on Escuta (IDmusica, IDusuario);

create index FKEsc_Usu_IND
     on Escuta (IDusuario);

create unique index ID_Evento_IND
     on Evento (IDevento);

create unique index ID_Like_IND
     on Curtir (IDcurtir);

create index FKCurtiu_IND
     on Curtir (IDusuario);

create index FKCurte_IND
     on Curtir (IDpost);

create unique index ID_Mensagem_IND
     on Mensagem (IDmensagem);

create index FKRecebida_por_IND
     on Mensagem (IDusuario);

create index FKEnviada_por_IND
     on Mensagem (Env_IDusuario);

create unique index ID_Musica_IND
     on Musica (IDmusica);

create index FKFaz_parte_IND
     on Musica (IDcategoria);

create index FKEsta_em_IND
     on Musica (IDalbum);

create index FKCompoe_IND
     on Musica (IDartista);

create unique index ID_Participa_IND
     on Participa (IDusuario, IDevento);

create index FKPar_Eve_IND
     on Participa (IDevento);

create unique index ID_Pertence_IND
     on Pertence (IDmusica, IDplaylist);

create index FKPer_Pla_IND
     on Pertence (IDplaylist);

create unique index ID_Playlist_IND
     on Playlist (IDplaylist);

create index FKFaz_IND
     on Playlist (IDusuario);

create unique index ID_Post_IND
     on Post (IDpost);

create index FKPosta_IND
     on postEvento (IDevento);

create unique index FKPos_pos_1_IND
     on postEvento (IDpost);

create unique index FKPos_pos_3_IND
     on postMusica (IDpost);

create unique index FKPos_pos_2_IND
     on postPlaylist (IDpost);

create index FKPlayPostada_IND
     on postPlaylist (IDplaylist);

create index FKuserPosta_IND
     on postUsuario (IDusuario);

create unique index FKPos_pos_IND
     on postUsuario (IDpost);

create unique index ID_Produz_IND
     on Produz (IDartista, IDalbum);

create index FKPro_Alb_IND
     on Produz (IDalbum);

create unique index ID_Relacionamento_IND
     on Relacionamento (IDrelacionamento);

create index FKUsuario2_IND
     on Relacionamento (IDusuario);

create index FKRel_Usu_IND
     on Relacionamento (R_U_IDusuario);

create unique index ID_Segue_IND
     on Segue (IDartista, IDusuario);

create index FKSeg_Usu_IND
     on Segue (IDusuario);

create unique index ID_Usuario_IND
     on Usuario (IDusuario);

create unique index SID_Usuario_IND
     on Usuario (email);
