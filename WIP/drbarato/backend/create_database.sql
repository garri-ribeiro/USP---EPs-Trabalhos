-- Cria banco de dados

CREATE DATABASE drBarato;

use drBarato;

-- Cria tabelas

CREATE TABLE usuario(
	ID_usuario 			INT NOT NULL AUTO_INCREMENT,
	email	 			VARCHAR(50) NOT NULL UNIQUE,
	tipo_ID				VARCHAR(50) NOT NULL,
	senha				VARCHAR(15) NOT NULL,
	nome 				VARCHAR(60) NOT NULL,
	dt_nascimento		DATE NOT NULL,
	telefone 			VARCHAR(10) NOT NULL,
	PRIMARY KEY(ID_usuario)
);

CREATE TABLE medico(
	ID_usuario			INT NOT NULL,
	formacao			VARCHAR(100),
	anos_experiencia 	INT, 
	bio					VARCHAR(255),
	PRIMARY KEY(ID_usuario),
	FOREIGN KEY(ID_usuario) REFERENCES usuario(ID_usuario)
);

CREATE TABLE especialidade(
	ID_especialidade 	INT NOT NULL AUTO_INCREMENT,
	especialidade		VARCHAR(50) NOT NULL,
	PRIMARY KEY(ID_especialidade)
);

CREATE TABLE especialidade_medico(
	ID_especialidade_medico INT NOT NULL AUTO_INCREMENT,
	ID_especialidade 	INT NOT NULL,
	ID_medico 			INT NOT NULL,
	PRIMARY KEY(ID_especialidade_medico),
	FOREIGN KEY(ID_especialidade) REFERENCES especialidade(ID_especialidade),
	FOREIGN KEY(ID_medico) REFERENCES usuario(ID_usuario)
);

CREATE TABLE documento(
	ID_documento 		INT NOT NULL AUTO_INCREMENT,
	documento 			VARCHAR(60) NOT NULL,
	tipo_doc 			VARCHAR(20),
	ID_usuario			INT NOT NULL,
	PRIMARY KEY(ID_documento),
	FOREIGN KEY(ID_usuario) REFERENCES usuario(ID_usuario)
);

create table lugar(
	ID_lugar			INT NOT NULL AUTO_INCREMENT,
	ID_usuario			INT NOT NULL,
	estado				VARCHAR(2) NOT NULL,
	cidade				VARCHAR(100) NOT NULL,
	logradouro 			VARCHAR(250),
	bairro				VARCHAR(250),
	complemento			VARCHAR(250),
	CEP 				VARCHAR(8),
	PRIMARY KEY(ID_lugar),
	FOREIGN KEY(ID_usuario) REFERENCES usuario(ID_USUARIO)
);

create table agendamento(
	ID_agendamento 		INT NOT NULL AUTO_INCREMENT,
	ID_medico 			INT NOT NULL,
	cadastro			INT NOT NULL,
	inicio 				DATETIME NOT NULL,
	fim 				DATETIME NOT NULL,
	in_status			INT NOT NULL, 
	voucher				VARCHAR(6),
	ID_paciente	 		INT,
	valor				DOUBLE,
	PRIMARY KEY(ID_agendamento),
	FOREIGN KEY (ID_medico) REFERENCES usuario(ID_usuario),
	FOREIGN KEY (ID_paciente) REFERENCES usuario(ID_usuario)
);
