-- Usuario

insert into usuario values( 0,'gabriel@email.com','CPF','senha','Gabriel', '2015-01-01', '964371735');
insert into usuario values( 0,'eler@email.com','RG','senha','Eler', '2015-01-01', '964371735');
insert into usuario values( 0,'jose@email.com','RG','senha','jose', '2015-01-01', '964371735');
insert into usuario values( 0,'medico@email.com','usuario','senha','Dr. Médico', '2015-01-01', '964371735');

insert into usuario values( 0,'gabs19@email.com','usuario','senha','Diogo', '2015-01-01', '964371735');
insert into usuario values( 0,'joao8@email.com','CPF','senha','joao', '2015-01-01', '964371735');
insert into usuario values( 0,'paciente@email.com','usuario','senha','Paciente', '2015-01-01', '964371735');


-- Medico

insert into medico values( 1,'USP - Universidade de SP.',5,'Sou médico e gostaria de ajudar pessoas carentes.');
insert into medico values( 2,'UNIFESP',2,'Sou bem profissional mesmo.');
insert into medico values( 3,'USP - Universidade de SP.',10,'Estou a seu dispor!');
insert into medico values( 4,'UNICID',2,'Muito legal esse projeto.');

-- Especialidade

insert into especialidade values
( 0, 'Acupuntura'),
( 0, 'Imunologista'),
( 0, 'Anestesiologia'),
( 0, 'Angiologia'),
( 0, 'Cardiologia'),
( 0, 'Cirurgia Cardiovascular'),
( 0, 'Cirurgia da Mão'),
( 0, 'Cirurgia de cabeça e pescoço'),
( 0, 'Cirurgia do Aparelho Digestivo'),
( 0, 'Cirurgia Geral'),
( 0, 'Cirurgia Pediátrica'),
( 0, 'Cirurgia Plástica'),
( 0, 'Cirurgia Torácica'),
( 0, 'Cirurgia Vascular'),
( 0, 'Coloproctologia'),
( 0, 'Dermatologia'),
( 0, 'Gastroenterologia'),
( 0, 'Endocrinologia'),
( 0, 'Geriatria'),
( 0, 'Endoscopia'),
( 0, 'Hematologia'),
( 0, 'Genética médica'),
( 0, 'Infectologia'),
( 0, 'Ginecologia'),
( 0, 'Medicina de Família e Comunidade'),
( 0, 'Homeopatia'),
( 0, 'Medicina do Tráfego'),
( 0, 'Mastologia'),
( 0, 'Medicina física e reabilitação'),
( 0, 'Medicina do Trabalho'),
( 0, 'Medicina Nuclear'),
( 0, 'Medicina Esportiva'),
( 0, 'Nefrologia'),
( 0, 'Medicina Intensiva'),
( 0, 'Neurologia'),
( 0, 'Médico sanitarista'),
( 0, 'Obstetrícia'),
( 0, 'Neurocirurgia'),
( 0, 'Ortopedia'),
( 0, 'Nutrologia'),
( 0, 'Patologia'),
( 0, 'Oftalmologia'),
( 0, 'Pediatria'),
( 0, 'Otorrinolaringologia'),
( 0, 'Psiquiatria'),
( 0, 'Patologia Clínica'),

( 0, 'Pneumologia'),
( 0, 'Radiologia'),
( 0, 'Radioterapia'),
( 0, 'Reumatologia'),
( 0, 'Urologia');

-- Especidalidade medico

insert into especialidade_medico values(0,2, 1);
insert into especialidade_medico values(0,1, 2);
insert into especialidade_medico values(0,1, 3);
insert into especialidade_medico values(0,11,4);

-- Lugar

insert into lugar values(0,1,'SP','São Paulo','Rua Major Sertório', 'Vl. Buarque', 'numero 23 sala 1', '01223000');
insert into lugar values(0,2,'SP','São Paulo','Rua Marquês de Itu', 'Vl. Buarque', 'numero 446 sala 1106', '01223000');
insert into lugar values(0,3,'SP','São Paulo','Pra lá da zona leste', 'Pq. Boturussu', 'numero 20 bloco B', '01223000');
insert into lugar values(0,4,'SP','São Paulo','Rua Major Sertório', 'Vl. Buarque', 'numero 23 sala 1', '01223000');
insert into lugar values(0,1,'SP','São Paulo','Rua Major Sertório', 'Vl. Buarque', 'numero 23 sala 1', '01223000');


-- Agendamento

insert into agendamento values(0, 1, 32, '2017-10-25 10:00:00','2017-10-25 11:00:00',0,NULL,7,32.2);
