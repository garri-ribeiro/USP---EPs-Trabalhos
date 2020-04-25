-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 23-Nov-2016 às 03:31
-- Versão do servidor: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `universidade`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `acervo`
--

CREATE TABLE `acervo` (
  `IDBiblioteca` int(11) NOT NULL,
  `RegBiblio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `acervo`
--

INSERT INTO `acervo` (`IDBiblioteca`, `RegBiblio`) VALUES
(1, 5482647),
(1, 5739454),
(2, 7583746),
(2, 7584756),
(3, 8463743);

-- --------------------------------------------------------

--
-- Estrutura da tabela `administrativo`
--

CREATE TABLE `administrativo` (
  `CPF` bigint(20) NOT NULL,
  `CargoAdm` char(150) NOT NULL,
  `Unidade` char(150) NOT NULL,
  `Superior` bigint(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `administrativo`
--

INSERT INTO `administrativo` (`CPF`, `CargoAdm`, `Unidade`, `Superior`) VALUES
(21793280606, 'Gerente', 'Recursos Humanos', 55671266700),
(21818660085, 'Analista', 'Recursos Humanos', 55671266700),
(39671822312, 'Encarregado', 'Recursos Humanos', 55671266700),
(55671266700, 'Supervisor', 'Recursos Humanos', NULL),
(71547439483, 'Assistente', 'Recursos Humanos', 55671266700);

-- --------------------------------------------------------

--
-- Estrutura da tabela `advertencia`
--

CREATE TABLE `advertencia` (
  `OcNumero` int(11) NOT NULL,
  `AdvTipo` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `advertencia`
--

INSERT INTO `advertencia` (`OcNumero`, `AdvTipo`) VALUES
(54645345, 'Média'),
(75435456, 'Média'),
(78573345, 'Leve'),
(83746374, 'Leve'),
(97434565, 'Grave');

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno`
--

CREATE TABLE `aluno` (
  `CPF` bigint(20) NOT NULL,
  `IDAluno` int(11) NOT NULL,
  `RegTrabalho` int(11) DEFAULT NULL,
  `Ori_CPF` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aluno`
--

INSERT INTO `aluno` (`CPF`, `IDAluno`, `RegTrabalho`, `Ori_CPF`) VALUES
(12121132467, 192105, 958473, NULL),
(12121212311, 192107, 958474, 12121235523),
(12121232399, 192106, 958475, 12129232323),
(12121244344, 192109, 958476, 12121235524),
(12221132467, 192100, NULL, 12345678910),
(19233341921, 192102, NULL, 21223345690),
(21377898790, 192103, NULL, 12345678910),
(23454367590, 192101, NULL, 21223345690),
(43123132468, 192110, 958477, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `alunobolsa`
--

CREATE TABLE `alunobolsa` (
  `CPF` bigint(20) NOT NULL,
  `TipoBolsa` char(150) NOT NULL,
  `DataInicio` date NOT NULL,
  `DataFim` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `alunobolsa`
--

INSERT INTO `alunobolsa` (`CPF`, `TipoBolsa`, `DataInicio`, `DataFim`) VALUES
(12221132467, 'Bolsa Moradia', '2016-09-07', '2017-09-07'),
(12221132467, 'Desconto 100%', '2016-09-07', '2017-09-07'),
(43123132468, 'Desconto 20%', '2016-05-01', '2017-02-28'),
(12121244344, 'Desconto 50%', '2015-10-06', '2017-10-18'),
(19233341921, 'Monitoria graduação', '2016-03-07', '2017-10-18'),
(23454367590, 'Vale transporte', '2016-02-17', '2017-02-07');

-- --------------------------------------------------------

--
-- Estrutura da tabela `alunoext`
--

CREATE TABLE `alunoext` (
  `CPF` bigint(20) NOT NULL,
  `NomeProj` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `alunoext`
--

INSERT INTO `alunoext` (`CPF`, `NomeProj`) VALUES
(12121232399, 'Grupo de estudo de escultura e modelagem de personagem'),
(21377898790, 'Automação residencia arduino'),
(23454367590, 'Estratégica de Marketing nos meios digitais'),
(23454367590, 'Webdesenvolvimento focado em frontend'),
(43123132468, 'Apresentações Impactantes');

-- --------------------------------------------------------

--
-- Estrutura da tabela `alunopesq`
--

CREATE TABLE `alunopesq` (
  `CPF` bigint(20) NOT NULL,
  `NomeProj` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `alunopesq`
--

INSERT INTO `alunopesq` (`CPF`, `NomeProj`) VALUES
(12121212311, 'Economia Industrial, Trabalho e Tecnologia'),
(12121232399, 'Grupo de Pesquisas em Midia Impressa'),
(12121244344, 'Estudos da Tradução e da Interpretação'),
(19233341921, 'Automóveis inteligentes'),
(21377898790, 'Tecnologias Digitais na Educação do ensino fundamental'),
(23454367590, 'Inteligência artificial e saúde'),
(43123132468, 'Multiplicação de Leads via adwords');

-- --------------------------------------------------------

--
-- Estrutura da tabela `apoioprof`
--

CREATE TABLE `apoioprof` (
  `ID` int(11) NOT NULL,
  `NomeProj` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `apoioprof`
--

INSERT INTO `apoioprof` (`ID`, `NomeProj`) VALUES
(2, 'Estudos da Tradução e da Interpretação'),
(4, 'Grupo de Pesquisas em Midia Impressa'),
(5, 'Inteligência artificial e saúde'),
(1, 'Moeda, Finanças e Desenvolvimento Econômico');

-- --------------------------------------------------------

--
-- Estrutura da tabela `apoiouni`
--

CREATE TABLE `apoiouni` (
  `NomeUni` char(150) NOT NULL,
  `NomeProj` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `apoiouni`
--

INSERT INTO `apoiouni` (`NomeUni`, `NomeProj`) VALUES
('Universidade de São Paulo', 'Automóveis inteligentes'),
('Faculdade de Artes e design grafico', 'Distúrbios de Linguagem, Corpo e Psiquismo'),
('Faculdade Getulio Vargas', 'Economia Industrial, Trabalho e Tecnologia'),
('Universidade de São Francisco', 'Multiplicação de Leads via adwords'),
('Universidade de São Paulo', 'Tecnologias Digitais na Educação do ensino fundamental');

-- --------------------------------------------------------

--
-- Estrutura da tabela `artigo`
--

CREATE TABLE `artigo` (
  `IDArtigo` int(11) NOT NULL,
  `ArtTitulo` char(150) NOT NULL,
  `ArtDescricao` char(150) NOT NULL,
  `RevCodigo` int(11) DEFAULT NULL,
  `ConfNome` char(150) DEFAULT NULL,
  `ConfData` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `artigo`
--

INSERT INTO `artigo` (`IDArtigo`, `ArtTitulo`, `ArtDescricao`, `RevCodigo`, `ConfNome`, `ConfData`) VALUES
(493728392, 'Computação móvel', 'Novas oportunidades para a computação móvel', 6546456, 'Conferência de Computação Aplicada', '2005-09-20'),
(738493829, 'Análise do Marketing Industrial', 'Redefinição da importância do marketing industrial', 1743632, 'Conferência de Marketing Social', '2015-05-23'),
(839483948, 'Inovação em Design Thinking', 'Vantagens ganhas na experiência do consumidor', NULL, 'Conferência de Design Thinking', '2016-03-14'),
(857492839, 'Fisioterapia respiratória', 'Difusão de técnicas e conceitos', 7645344, 'Conferência Nacional de Fisioterapia', '2010-10-01'),
(948573748, 'Biotecnologia agrícola', 'Mitos, riscos e alternativas', 9374528, 'Conferência de Biotecnologia Ambiental', '2008-12-04');

-- --------------------------------------------------------

--
-- Estrutura da tabela `atividade`
--

CREATE TABLE `atividade` (
  `NomeAtiv` char(150) NOT NULL,
  `DataAtiv` date NOT NULL,
  `LocalAtiv` char(150) NOT NULL,
  `DescricaoAtiv` char(150) NOT NULL,
  `NomeProj` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `atividade`
--

INSERT INTO `atividade` (`NomeAtiv`, `DataAtiv`, `LocalAtiv`, `DescricaoAtiv`, `NomeProj`) VALUES
('HTML e CSS', '2016-08-23', 'Sala 110 - Prédio de Computação', 'Ensino basico das linguagens HTML e CSS', 'Webdesenvolvimento focado em frontend'),
('Inicio modelagem de escultura', '2016-09-06', 'Sala 201 - Prédio de Artes', 'Aula inicial e introdução de como realizar a modelagem de uma esucltura', 'Grupo de estudo de escultura e modelagem de personagem'),
('Linguagem Arduino', '2016-05-10', 'Sala 110 - Prédio de Computação', '', 'Automação residencia arduino'),
('Montagem de circuitos', '2016-05-21', 'Sala 110 - Prédio de Computação', '', 'Automação residencia arduino'),
('utilização do boostrap', '2016-09-01', 'Sala 110 - Prédio de Computação', 'Ensino basico para utilização do framework bootstrap 3', 'Webdesenvolvimento focado em frontend');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ausencia`
--

CREATE TABLE `ausencia` (
  `OcNumero` int(11) NOT NULL,
  `TipoAusen` char(150) NOT NULL,
  `AfInicio` date NOT NULL,
  `AfFim` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ausencia`
--

INSERT INTO `ausencia` (`OcNumero`, `TipoAusen`, `AfInicio`, `AfFim`) VALUES
(24363427, 'Afastamento remunerado', '2005-04-01', '2005-05-01'),
(25376384, ' Afastamento remunerado', '2005-06-01', '2005-07-01'),
(34235342, ' Afastamento remunerado', '2006-02-01', '2006-03-01'),
(45434234, 'Licença', '2006-10-02', '2006-10-02'),
(52436372, ' Afastamento remunerado', '2007-07-01', '2007-08-02'),
(62342343, 'Férias', '2010-02-01', '2010-02-01'),
(62536152, ' Afastamento remunerado', '2010-03-01', '2010-04-01');

-- --------------------------------------------------------

--
-- Estrutura da tabela `autor`
--

CREATE TABLE `autor` (
  `RegAutor` int(11) NOT NULL,
  `NomeAutor` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `autor`
--

INSERT INTO `autor` (`RegAutor`, `NomeAutor`) VALUES
(34534, 'Philip Kotler'),
(53523, 'Hamilton Luiz Guidorizzi'),
(54350, 'Kevin Lane Keller'),
(64362, 'José Geraldo Dângelo'),
(64564, 'Émile Benveniste'),
(65432, 'William Stallings'),
(76574, 'Carlo Américo Fattini');

-- --------------------------------------------------------

--
-- Estrutura da tabela `autor_1`
--

CREATE TABLE `autor_1` (
  `IDArtigo` int(11) NOT NULL,
  `CPF` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `autor_1`
--

INSERT INTO `autor_1` (`IDArtigo`, `CPF`) VALUES
(493728392, 12345678910),
(738493829, 23212321300),
(738493829, 23212321333),
(839483948, 21223345690),
(948573748, 12121235523);

-- --------------------------------------------------------

--
-- Estrutura da tabela `bem`
--

CREATE TABLE `bem` (
  `Identificador` int(11) NOT NULL,
  `TipoBem` char(150) NOT NULL,
  `IDCompra` char(150) NOT NULL,
  `Movel` int(11) DEFAULT NULL,
  `Imovel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `bem`
--

INSERT INTO `bem` (`Identificador`, `TipoBem`, `IDCompra`, `Movel`, `Imovel`) VALUES
(100, 'Carteira sala de aula', '1000', NULL, NULL),
(101, 'Carteira sala de aula', '1002', NULL, NULL),
(102, 'Carteira sala de aula', '1003', NULL, NULL),
(103, 'Carteira sala de aula', '1001', NULL, NULL),
(110, 'Material Escritorio', '1003', NULL, NULL),
(111, 'Material Escritorio', '1003', NULL, NULL),
(112, 'Material Escritorio', '1003', NULL, NULL),
(300, 'Relógio de parede', '1300', NULL, NULL),
(1000, 'Prédio de computação', '2000', NULL, NULL),
(1001, 'Prédio de Adiministração', '2001', NULL, NULL),
(1002, 'Prédio de Artes', '2002', NULL, NULL),
(1003, 'Prédio de Marketing', '2003', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `biblioteca`
--

CREATE TABLE `biblioteca` (
  `IDBiblioteca` int(11) NOT NULL,
  `Localizacao` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `biblioteca`
--

INSERT INTO `biblioteca` (`IDBiblioteca`, `Localizacao`) VALUES
(1, 'Câmpus sul'),
(2, 'Câmpus norte'),
(3, 'Câmpus principal'),
(4, 'Câmpus norte'),
(5, 'Câmpus sul');

-- --------------------------------------------------------

--
-- Estrutura da tabela `bibliotecario`
--

CREATE TABLE `bibliotecario` (
  `CPF` bigint(20) NOT NULL,
  `CargoBibli` char(150) NOT NULL,
  `IDBiblioteca` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `bibliotecario`
--

INSERT INTO `bibliotecario` (`CPF`, `CargoBibli`, `IDBiblioteca`) VALUES
(15175358596, 'Recepção', 3),
(21121235523, 'Master', 1),
(33343567864, 'Master', 2),
(44812620104, 'Recepção', 1),
(98347734288, 'Master', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `bolsa`
--

CREATE TABLE `bolsa` (
  `TipoBolsa` char(150) NOT NULL,
  `ValorBolsa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `bolsa`
--

INSERT INTO `bolsa` (`TipoBolsa`, `ValorBolsa`) VALUES
('Bolsa alimentação', 300),
('Bolsa Moradia', 800),
('Desconto 100%', 100),
('Desconto 20%', 20),
('Desconto 50%', 50),
('Monitoria graduação', 500),
('Monitoria pós graduação', 500),
('Vale transporte', 200);

-- --------------------------------------------------------

--
-- Estrutura da tabela `compra`
--

CREATE TABLE `compra` (
  `IDCompra` char(150) NOT NULL,
  `DataAquisicao` date NOT NULL,
  `ValorCompra` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `compra`
--

INSERT INTO `compra` (`IDCompra`, `DataAquisicao`, `ValorCompra`) VALUES
('1000', '2016-08-10', 500),
('1001', '2016-08-10', 500),
('1002', '2016-08-10', 500),
('1003', '2016-08-10', 500),
('1100', '2016-08-10', 100),
('1101', '2016-08-10', 100),
('1102', '2016-08-10', 100),
('1300', '2016-09-01', 50),
('1400', '2016-09-01', 800),
('2000', '2016-01-01', 1000000),
('2001', '2015-12-01', 500000),
('2002', '2015-12-07', 800000),
('2003', '2016-02-10', 950000);

-- --------------------------------------------------------

--
-- Estrutura da tabela `conferencia`
--

CREATE TABLE `conferencia` (
  `ConfNome` char(150) NOT NULL,
  `ConfData` date NOT NULL,
  `ConfLocal` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `conferencia`
--

INSERT INTO `conferencia` (`ConfNome`, `ConfData`, `ConfLocal`) VALUES
('Conferência de Biotecnologia Ambiental', '2008-12-04', 'Campinas'),
('Conferência de Computação Aplicada', '2005-09-20', 'Fortaleza'),
('Conferência de Design Thinking', '2016-03-14', 'Curitiba'),
('Conferência de Marketing Social', '2015-05-23', 'São Paulo'),
('Conferência Nacional de Fisioterapia', '2010-10-01', 'Rio De Janeiro');

-- --------------------------------------------------------

--
-- Estrutura da tabela `coordpesq`
--

CREATE TABLE `coordpesq` (
  `NomeProj` char(150) NOT NULL,
  `CPF` bigint(20) NOT NULL,
  `BolsaPesq` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `coordpesq`
--

INSERT INTO `coordpesq` (`NomeProj`, `CPF`, `BolsaPesq`) VALUES
('Automóveis inteligentes', 21223345690, 4000),
('Distúrbios de Linguagem, Corpo e Psiquismo', 12121235524, 3000),
('Economia Industrial, Trabalho e Tecnologia', 12121235523, 3000),
('Estudos da Tradução e da Interpretação', 12121235524, 2000),
('Grupo de Pesquisas em Midia Impressa', 12129232323, 2000),
('Inteligência artificial e saúde', 12345678910, 3000),
('Moeda, Finanças e Desenvolvimento Econômico', 12121235523, 5000),
('Multiplicação de Leads via adwords', 23212321333, 2100);

-- --------------------------------------------------------

--
-- Estrutura da tabela `curso`
--

CREATE TABLE `curso` (
  `CodCurso` int(11) NOT NULL,
  `NomeCurso` char(150) NOT NULL,
  `Mensalidade` int(11) NOT NULL,
  `DescriCurso` char(150) NOT NULL,
  `StrictuSensu` int(11) DEFAULT NULL,
  `LatuSensu` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `curso`
--

INSERT INTO `curso` (`CodCurso`, `NomeCurso`, `Mensalidade`, `DescriCurso`, `StrictuSensu`, `LatuSensu`) VALUES
(100, 'Administração', 850, '', 1, NULL),
(155, 'Design', 1000, '', 1, NULL),
(160, 'Direito', 1050, '', 1, NULL),
(165, 'Economia', 980, '', 1, NULL),
(235, 'Letras', 700, '', 1, NULL),
(240, 'Marketing', 900, '', 1, NULL),
(470, 'Fisioterapia', 920, '', 1, NULL),
(710, 'Computação', 950, '', 1, NULL),
(911, 'Engenharia nuclear', 1800, '', NULL, 1),
(922, 'Business Intelligence', 1500, '', NULL, 1),
(933, 'Engenharia Aeronautica', 1300, '', NULL, 1),
(955, 'Animação 3D', 1400, '', NULL, 1),
(999, 'Biotecnologia', 1300, '', NULL, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplina`
--

CREATE TABLE `disciplina` (
  `DiscCodigo` int(11) NOT NULL,
  `DiscDescricao` char(150) NOT NULL,
  `NumCreditos` int(11) NOT NULL,
  `NumHoras` int(11) NOT NULL,
  `Bibliografia` char(150) NOT NULL,
  `CodCurso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `disciplina`
--

INSERT INTO `disciplina` (`DiscCodigo`, `DiscDescricao`, `NumCreditos`, `NumHoras`, `Bibliografia`, `CodCurso`) VALUES
(100, 'Banco de dados', 4, 40, 'Sistemas de Banco de Dados, Elmasri,Ramez/Navathe', 710),
(101, 'Sistemas Operacionais', 4, 40, 'Sistemas de Banco de Dados, Elmasri,Ramez/Navathe', 710),
(202, 'Métrica em midias sociais', 4, 48, '#Socialholic - Tudo o Que Você Precisa Saber Sobre Marketing Nas Mídias Sociais', 240),
(203, 'Estratégia de Marketing', 4, 50, '', 240),
(300, 'Introdução a Economia', 2, 28, 'Introdução À Economia - Toneto Júnior,Rudinei', 165),
(400, 'Teoria das cores', 6, 40, 'A psicologia das cores: Como as cores afetam a emoçâo e a razâo - Heller,Eva', 155),
(401, 'Design de logotipo', 2, 28, '', 155),
(500, 'Introdução a Administração', 2, 28, 'Geração de Valor – Compartilhando Inspiração - Silva, Flávio Augusto Da', 100),
(501, 'Empreendedorismo', 4, 48, 'A menina do vale - Pesce, Bel', 100),
(600, 'Linguistica', 4, 40, 'Introdução À Linguística - I. Objetos Teóricos', 235),
(700, 'Direito do consumidor', 4, 48, 'Direitos do Consumidor Endividado II - Col. Biblioteca de Dirieto do Consumidor', 710),
(800, 'Fisioterapia esportiva', 6, 54, '', 470),
(900, 'Genética IV', 4, 48, 'Genética - Um Enfoque Conceitual- Pierce,Benjamin A.', 710),
(910, 'Efeitos especiais V', 6, 54, '', 955),
(920, 'Métodos de decisões qualificadas', 2, 20, '', 922),
(930, 'Mecânica dos Fluídos', 6, 54, '', 911),
(940, 'Mecânica de Estruturas Aeronáuticas', 2, 24, '', 933);

-- --------------------------------------------------------

--
-- Estrutura da tabela `escrito`
--

CREATE TABLE `escrito` (
  `RegAutor` int(11) NOT NULL,
  `ISBN` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `escrito`
--

INSERT INTO `escrito` (`RegAutor`, `ISBN`) VALUES
(65432, 848966022),
(64362, 857379070),
(76574, 857379070),
(34534, 857605001),
(54350, 857605001),
(53523, 978852161),
(64564, 978857113);

-- --------------------------------------------------------

--
-- Estrutura da tabela `estagio`
--

CREATE TABLE `estagio` (
  `CNPJEmpresa` int(11) NOT NULL,
  `Empresa` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `estagio`
--

INSERT INTO `estagio` (`CNPJEmpresa`, `Empresa`) VALUES
(1222, 'Microsoft'),
(1234, 'Google'),
(1333, '99jobs'),
(1444, 'Nubank'),
(1555, 'Twitter');

-- --------------------------------------------------------

--
-- Estrutura da tabela `exemplar`
--

CREATE TABLE `exemplar` (
  `RegBiblio` int(11) NOT NULL,
  `Edicao` int(11) NOT NULL,
  `DataAqui` date NOT NULL,
  `Retiravel` char(1) NOT NULL,
  `ISBN` int(11) NOT NULL,
  `CPF` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `exemplar`
--

INSERT INTO `exemplar` (`RegBiblio`, `Edicao`, `DataAqui`, `Retiravel`, `ISBN`, `CPF`) VALUES
(5482647, 7, '2011-05-18', '0', 857379070, NULL),
(5739454, 5, '2016-09-10', '1', 978852161, NULL),
(7583746, 6, '2005-05-03', '1', 848966022, NULL),
(7584756, 3, '2000-10-04', '1', 857605001, NULL),
(8463743, 5, '2003-04-23', '0', 978857113, 12221132467);

-- --------------------------------------------------------

--
-- Estrutura da tabela `extensao`
--

CREATE TABLE `extensao` (
  `NomeProj` char(150) NOT NULL,
  `Financiamento` int(11) NOT NULL,
  `TipoFinan` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `extensao`
--

INSERT INTO `extensao` (`NomeProj`, `Financiamento`, `TipoFinan`) VALUES
('Apresentações Impactantes', 500, 'Privado'),
('Automação residencia arduino', 900, 'Publico'),
('Estratégica de Marketing nos meios digitais', 1000, 'Publico'),
('Grupo de estudo de escultura e modelagem de personagem', 1000, 'Privado'),
('Webdesenvolvimento focado em frontend', 400, 'Publico');

-- --------------------------------------------------------

--
-- Estrutura da tabela `folhapag`
--

CREATE TABLE `folhapag` (
  `CodigoID` int(11) NOT NULL,
  `ValorPag` int(11) NOT NULL,
  `DataPag` date NOT NULL,
  `CPF` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `folhapag`
--

INSERT INTO `folhapag` (`CodigoID`, `ValorPag`, `DataPag`, `CPF`) VALUES
(203940, 2000, '2016-11-05', 21121235523),
(203941, 2000, '2016-11-05', 33343567864),
(203942, 2500, '2016-11-05', 98347734288),
(203943, 2500, '2016-11-05', 12129232323),
(203944, 2000, '2016-11-05', 12121235523);

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcaocargo`
--

CREATE TABLE `funcaocargo` (
  `NomeOcupacao` char(150) NOT NULL,
  `TipoOcup` char(150) NOT NULL,
  `SetorAdm` char(150) NOT NULL,
  `ValorRecebido` int(11) NOT NULL,
  `InicOcup` date NOT NULL,
  `FimOcup` date NOT NULL,
  `CPF` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcaocargo`
--

INSERT INTO `funcaocargo` (`NomeOcupacao`, `TipoOcup`, `SetorAdm`, `ValorRecebido`, `InicOcup`, `FimOcup`, `CPF`) VALUES
('Coordenador de Computação', 'Funçao Gratificada', 'Computação', 1000, '2015-01-25', '2017-01-25', 12345678910),
('Coordenador de Economia', 'Funçao Gratificada', 'Economia', 1000, '2015-02-20', '2017-02-20', 12121235523),
('Professor computação', 'Cargo comissionado', 'Computação', 2500, '2015-12-07', '2018-02-25', 12345678910),
('Supervisor de estagios Computação', 'Funçao Gratificada', 'Computação', 1000, '2015-02-20', '2017-02-20', 21223345690),
('Supervisor de estagios Design', 'Funçao Gratificada', 'Artes', 1000, '2016-02-20', '2018-02-25', 12129232323);

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcext`
--

CREATE TABLE `funcext` (
  `NomeProj` char(150) NOT NULL,
  `CPF` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcext`
--

INSERT INTO `funcext` (`NomeProj`, `CPF`) VALUES
('Grupo de estudo de escultura e modelagem de personagem', 12129232323),
('Webdesenvolvimento focado em frontend', 12345678910),
('Automação residencia arduino', 21223345690),
('Estratégica de Marketing nos meios digitais', 23212321300),
('Apresentações Impactantes', 23212321312);

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `CPF` bigint(20) NOT NULL,
  `Professor` int(11) DEFAULT NULL,
  `Bibliotecario` int(11) DEFAULT NULL,
  `Administrativo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`CPF`, `Professor`, `Bibliotecario`, `Administrativo`) VALUES
(12121235523, 1, NULL, NULL),
(12121235524, 1, NULL, NULL),
(12129232323, 1, NULL, NULL),
(12345678910, 1, NULL, NULL),
(15175358596, NULL, 1, NULL),
(21121235523, NULL, 1, NULL),
(21223345690, 1, NULL, NULL),
(21793280606, NULL, NULL, 1),
(21818660085, NULL, NULL, 1),
(23212321300, 1, NULL, NULL),
(23212321312, 1, NULL, NULL),
(23212321333, 1, NULL, NULL),
(33343567864, NULL, 1, NULL),
(39671822312, NULL, NULL, 1),
(44812620104, NULL, 1, NULL),
(55671266700, NULL, NULL, 1),
(71547439483, NULL, NULL, 1),
(98347734288, NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `imovel`
--

CREATE TABLE `imovel` (
  `Identificador` int(11) NOT NULL,
  `End_Logradouro` char(150) NOT NULL,
  `End_Numero` int(11) NOT NULL,
  `End_Bairro` char(150) NOT NULL,
  `End_Cidade` char(150) NOT NULL,
  `End_Estado` char(150) NOT NULL,
  `End_CEP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `imovel`
--

INSERT INTO `imovel` (`Identificador`, `End_Logradouro`, `End_Numero`, `End_Bairro`, `End_Cidade`, `End_Estado`, `End_CEP`) VALUES
(1000, 'Av. Prof. Almeida Prado', 666, 'Butantã', 'São Paulo', 'SP', 21212300),
(1001, 'Av. Prof. Almeida Prado', 200, 'Butantã', 'São Paulo', 'SP', 21212300),
(1002, 'Av. Prof. Almeida Prado', 100, 'Butantã', 'São Paulo', 'SP', 21212300),
(1003, 'Av. Prof. Almeida Prado', 999, 'Butantã', 'São Paulo', 'SP', 21212300);

-- --------------------------------------------------------

--
-- Estrutura da tabela `individuo`
--

CREATE TABLE `individuo` (
  `CPF` bigint(20) NOT NULL,
  `Nome` varchar(150) NOT NULL,
  `Sexo` varchar(150) NOT NULL,
  `Nascimento` date NOT NULL,
  `Telefone` bigint(11) NOT NULL,
  `Email` varchar(150) NOT NULL,
  `End_Logradouro` varchar(150) NOT NULL,
  `End_Numero` int(11) NOT NULL,
  `End_Bairro` varchar(150) NOT NULL,
  `End_Cidade` varchar(150) NOT NULL,
  `End_Estado` varchar(150) NOT NULL,
  `End_CEP` bigint(11) NOT NULL,
  `Funcionario` int(11) DEFAULT NULL,
  `Aluno` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `individuo`
--

INSERT INTO `individuo` (`CPF`, `Nome`, `Sexo`, `Nascimento`, `Telefone`, `Email`, `End_Logradouro`, `End_Numero`, `End_Bairro`, `End_Cidade`, `End_Estado`, `End_CEP`, `Funcionario`, `Aluno`) VALUES
(12121132467, 'Carolina Sousa Almeida', 'Feminino', '1996-03-06', 1113345328, 'carolina.almeida@email.com', 'Quadra SHIS QI 26 Conjunto 13', 231, 'Lago Sul', 'Goiania', 'GO', 1234006782, NULL, 192104),
(12121212311, 'Kaua Castro', 'Masculino', '1998-11-14', 1136743338, 'kaua@email.com', 'Rua Monte Castelo', 477, 'Vezuvio', 'São Paulo', 'SP', 22378754, NULL, NULL),
(12121232399, 'Beatrice Pereira Pinto', 'Feminino', '1995-02-01', 1136745436, 'beatrice.pinto@email.com', 'Rua Paulista Martins', 234, 'Itaim Bibi', 'São Paulo', 'SP', 12378654, NULL, NULL),
(12121235523, 'Matheus Rodrigues Ferreira', 'Masculino', '1995-08-01', 1133245328, 'matheus.ferreira@email.com', 'Rua Ulisses Salles Coelho', 111, 'Itaim Paulista', 'São Paulo', 'SP', 32378654, NULL, NULL),
(12121235524, 'Gabriela Helena', 'Feminino', '1994-05-01', 1137565328, 'gabi.helena@email.com', 'Rua Jamelo Tete', 121, 'Vila Madalena', 'São Paulo', 'SP', 12376590, NULL, NULL),
(12121244344, 'Theo Alan', 'Masculino', '1988-10-14', 2236743338, 'theo@email.com', 'Rua Monte verde', 477, 'Vila MAriana', 'São Paulo', 'SP', 66378754, NULL, NULL),
(12129232323, 'Bruno André Carlos', 'Masculino', '1990-02-01', 1134345328, 'bruno.andre@email.com', 'Rua Fuad Arida', 464, 'Sumarezinho', 'São Paulo', 'SP', 111234555, NULL, NULL),
(12221132467, 'Jair das Neves', 'Masculino', '1995-11-12', 1113345678, 'jairzao@email.com', 'Rua Alencar de Mello', 21, 'Jd. dos Exemplos', 'Exemplolandia', 'EX', 123456782, NULL, 192100),
(12345678910, 'Maria Antonieta', 'Feminino', '1998-11-02', 1112345678, 'antonietinha@email.com', 'Avenida Jurunas', 211, 'Mato velho', 'Manaus', 'AM', 132234543, 1, NULL),
(15175358596, 'Thaís Araujo Rodrigues', 'Feminino', '1945-09-03', 81928116, 'ThaisAraujoRodrigues@teleworm.us', 'Rua José Zeferino Cardoso', 1727, 'Jardim Três Marias', 'São José', 'SC', 88115253, 1, NULL),
(19233341921, 'Antone Rodrigues', 'Masculino', '2000-11-02', 1123456789, 'antone@email.com', 'Rua Coronel Sílvio da Silva', 23, 'Marajó', 'Belo Horizonte', 'MG', 546766890, NULL, 192102),
(21121235523, 'Solange Gomes', 'Feminino', '1975-08-01', 1112567892, 'solange@email.com', 'Rua Cabral de Nunes', 666, 'Maia', 'Guarulhos', 'SP', 12354678, NULL, NULL),
(21223345690, 'Ágatha Araujo Ferreira', 'Feminino', '1979-03-04', 2122345678, 'agata.fefe@email.com', 'Rua Janete Joana Bauer', 8137, 'Jardim da Cerva', 'Blumenau', 'SC', 213668978, NULL, NULL),
(21377898790, 'Leonardo Marcondes', 'Masculino', '1988-01-09', 1388345676, 'leo.maracondes@email.com', 'Avenida Brasil ', 213, 'Avenidona', 'Rio de Janeiro', 'RJ', 213221567, NULL, 192103),
(21793280606, ' José Sousa Fernandes', ' Masculino', '1964-04-03', 93876145, 'JoseSousaFernandes@rhyta.com', 'Rua Bárbara Maix', 1015, 'Verde', 'Santana do Livramento', 'RS', 97576190, 1, NULL),
(21818660085, 'José Ribeiro Castro', 'Masculino', '1968-08-30', 29579478, 'JoseRibeiroCastro@armyspy.com', 'Rua Adolfo Assunção', 1490, 'Jardim Cambuy', 'Tatuí', 'SP', 18272141, 1, NULL),
(23212321300, 'Bruna Fernandes Pereira', 'Feminino', '1975-08-20', 1321234654, 'bruninha.pereira@email.com', 'Rua Raul Veiga Jardim', 666, 'Heliponto', 'Aparecida de Goiânia', 'GO', 200668979, NULL, NULL),
(23212321312, 'Guilherme Ribeiro', 'Masculino', '1975-09-18', 1023435678, 'gui.ribeiro@email.com', 'Alameda dos Salgueiros', 666, 'Penha', 'São Paulo', 'SP', 5187632, NULL, NULL),
(23212321333, 'Anna Oliveira Cunha', 'Feminino', '1975-08-04', 1321234654, 'anna.cunha@email.com', 'Rua Janete Joana Bauer', 81, 'Jardim da Cerva', 'Blumenau', 'SC', 213668979, NULL, NULL),
(23454367590, 'Rebbeca Tomanik', 'Feminino', '1999-03-04', 1533324356, 'rebetik@email.com', 'Rua Ana Jesus', 837, 'Jd. Angelo', 'Nova iguaçu', 'RJ', 111232010, NULL, 192101),
(33343567864, 'Patricia Leão', 'Feminino', '1987-07-11', 1123134567, 'pat.leao@email.com', 'Avenia Abedi Lua', 123, 'Sumarezinho', 'São Paulo', 'SP', 12354235, NULL, NULL),
(39671822312, 'Marina Rocha Almeida', 'Feminino', '1962-05-31', 48823778, 'MarinaRochaAlmeida@armyspy.com', 'Quadra QNO 19 Conjunto 24', 723, 'Rincão', 'Ceilândia', 'DF', 72261024, 1, NULL),
(43123132468, 'Patricia Leia', 'Feminino', '1986-03-16', 1121345328, 'patricia.leia@email.com', 'Rua Saci Tombamento', 231, 'Parque Jeronimo', 'Indaiatuba', 'SP', 1221006782, NULL, 192105),
(44812620104, 'Gabrielle Cardoso Araujo', 'Feminino', '1979-08-05', 28878908, 'GabrielleCardosoAraujo@jourrapide.com', 'Alameda Garopaba', 990, 'Cidade Nova', 'Barueri', 'SP', 6429310, 1, NULL),
(55671266700, 'Marina Azevedo Barros', 'Feminino', '1976-06-11', 26519450, 'MarinaAzevedoBarros@teleworm.us', 'Rua Pedestre M', 1522, 'Diácono João Luiz Pozzobon', ' Governador Valadares', 'MG', 35024370, 1, NULL),
(71547439483, 'Matheus Fernandes Cardoso', ' Masculino', '1975-03-23', 95904449, 'MatheusFernandesCardoso@jourrapide.com', 'Rua E', 90, 'Jardim Macarengo', 'Vilha Velha', 'ES', 29126150, 1, NULL),
(98347734288, 'Erik Fagundes', 'Masculino', '1979-09-01', 1199332458, 'erik.fag@email.com', 'Rua Larissa de Pira', 234, 'Santa Cruz', 'São Paulo', 'SP', 12352279, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `inscrito`
--

CREATE TABLE `inscrito` (
  `CPF` bigint(20) NOT NULL,
  `NumeroTurma` int(11) NOT NULL,
  `Frequencia` int(11) NOT NULL,
  `Notas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `inscrito`
--

INSERT INTO `inscrito` (`CPF`, `NumeroTurma`, `Frequencia`, `Notas`) VALUES
(12221132467, 194, 75, 8),
(23454367590, 195, 80, 5),
(12121212311, 295, 93, 1),
(12121232399, 494, 100, 6),
(19233341921, 594, 72, 8);

-- --------------------------------------------------------

--
-- Estrutura da tabela `intercambio`
--

CREATE TABLE `intercambio` (
  `NumInter` int(11) NOT NULL,
  `NomeInter` char(150) CHARACTER SET latin1 NOT NULL,
  `TipoInter` char(150) CHARACTER SET latin1 NOT NULL,
  `NumVagas` int(11) NOT NULL,
  `Pais` char(150) CHARACTER SET latin1 NOT NULL,
  `Universidade` char(150) CHARACTER SET latin1 NOT NULL,
  `InterInicio` date NOT NULL,
  `InterFim` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `intercambio`
--

INSERT INTO `intercambio` (`NumInter`, `NomeInter`, `TipoInter`, `NumVagas`, `Pais`, `Universidade`, `InterInicio`, `InterFim`) VALUES
(1, 'University of Sydney', 'IndicaÃ§Ã£o', 21, 'Australia', 'University of Sydney', '2016-11-01', '2017-11-01'),
(2, 'Programa Language Education', 'Bolsas - PrÃ¡ticas de Ensino	', 1, 'EUA', 'Massachusetts Institute of Technology', '2016-09-01', '2017-09-01'),
(3, 'Harvard Business', 'Curso Presencial - Language Education	', 5, 'EUA', 'Harvard University', '2016-01-01', '2017-01-01'),
(5, 'Africa do Sul - Mobilidade Internacional Alunos', 'Bolsa', 50, 'Africa do Sul', 'University of South Africa', '2016-11-30', '2017-05-24'),
(6, 'Intercambio Sistemas de InformaÃ§Ã£o', 'Bolsa', 1, 'Brasil', 'USP', '2016-01-01', '2017-01-01');

-- --------------------------------------------------------

--
-- Estrutura da tabela `intercambista`
--

CREATE TABLE `intercambista` (
  `CPF` bigint(20) NOT NULL,
  `NumInter` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `intercambista`
--

INSERT INTO `intercambista` (`CPF`, `NumInter`) VALUES
(12121132467, 1),
(12121132467, 2),
(12121232399, 1),
(12121232399, 5),
(12121244344, 3),
(21377898790, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `latusensu`
--

CREATE TABLE `latusensu` (
  `CodCurso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `latusensu`
--

INSERT INTO `latusensu` (`CodCurso`) VALUES
(100),
(155),
(160),
(165),
(235),
(240),
(470),
(710);

-- --------------------------------------------------------

--
-- Estrutura da tabela `livro`
--

CREATE TABLE `livro` (
  `ISBN` int(11) NOT NULL,
  `Titulo` char(150) NOT NULL,
  `CDD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `livro`
--

INSERT INTO `livro` (`ISBN`, `Titulo`, `CDD`) VALUES
(848966022, 'Sistemas Operativos', 0),
(857379070, 'Anatomia Humana', 610),
(857605001, 'Administração de marketing', 380),
(978852161, 'Um Curso de Cálculo', 500),
(978857113, 'Problemas de linguistica geral', 410);

-- --------------------------------------------------------

--
-- Estrutura da tabela `matricula`
--

CREATE TABLE `matricula` (
  `CPF` bigint(20) NOT NULL,
  `MatriData` date NOT NULL,
  `Media` int(11) NOT NULL,
  `Creditos` int(11) NOT NULL,
  `Isencao` char(1) NOT NULL,
  `CodCurso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `matricula`
--

INSERT INTO `matricula` (`CPF`, `MatriData`, `Media`, `Creditos`, `Isencao`, `CodCurso`) VALUES
(12121132467, '2016-01-30', 0, 20, 'S', 999),
(12121212311, '2015-01-28', 0, 200, 'N', 240),
(12121232399, '2015-01-20', 0, 240, 'S', 155),
(12221132467, '2016-02-01', 0, 240, 'S', 710),
(19233341921, '2015-02-02', 0, 240, 'N', 710),
(21377898790, '2015-01-28', 0, 240, 'S', 710),
(23454367590, '2016-01-30', 0, 240, 'N', 710),
(43123132468, '2015-01-28', 0, 240, 'N', 235);

-- --------------------------------------------------------

--
-- Estrutura da tabela `movel`
--

CREATE TABLE `movel` (
  `Identificador` int(11) NOT NULL,
  `Loc_Identificador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `movel`
--

INSERT INTO `movel` (`Identificador`, `Loc_Identificador`) VALUES
(100, 1001),
(101, 1001),
(102, 1001),
(103, 1001),
(110, 1001),
(111, 1001),
(112, 1001),
(300, 1002);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ocorrencia`
--

CREATE TABLE `ocorrencia` (
  `OcNumero` int(11) NOT NULL,
  `OcData` date NOT NULL,
  `OcDescricao` char(150) NOT NULL,
  `Ausencia` int(11) DEFAULT NULL,
  `Advertencia` int(11) DEFAULT NULL,
  `CPF` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ocorrencia`
--

INSERT INTO `ocorrencia` (`OcNumero`, `OcData`, `OcDescricao`, `Ausencia`, `Advertencia`, `CPF`) VALUES
(24363427, '2005-03-23', '', 1, NULL, 23212321312),
(25376384, '2005-05-30', '', 1, NULL, 12345678910),
(34235342, '2006-01-29', '', 1, NULL, 23212321300),
(45434234, '2006-09-01', '', 1, NULL, 23212321333),
(52436372, '2007-07-01', '', 1, NULL, 21223345690),
(54645345, '2009-10-21', 'Ausência em reunião', NULL, 1, 21223345690),
(62342343, '2010-01-09', '', 1, NULL, 23212321312),
(62536152, '2010-02-27', '', 1, NULL, 23212321333),
(75435456, '2011-03-24', 'Falta não-autorizada', NULL, 1, 23212321300),
(78573345, '2011-05-03', 'Atraso em entrega de documentos', NULL, 1, 23212321333),
(83746374, '2012-04-19', 'Atraso em serviço', NULL, 1, 12345678910),
(97434565, '2014-01-15', 'Investigação em andamento', NULL, 1, 23212321312);

-- --------------------------------------------------------

--
-- Estrutura da tabela `parceriaprof`
--

CREATE TABLE `parceriaprof` (
  `ID` int(11) NOT NULL,
  `NomeProf` char(150) NOT NULL,
  `UniTrabalha` char(150) NOT NULL,
  `Formacao` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `parceriaprof`
--

INSERT INTO `parceriaprof` (`ID`, `NomeProf`, `UniTrabalha`, `Formacao`) VALUES
(1, 'Fred Albuquerque', 'Harvard Business', 'Economia'),
(2, 'Joelma Mariana', 'Universidade de São Paulo', 'Letras'),
(3, 'Beatrice Pereira Pinto', 'Faculdade do Itajai', 'Marketing'),
(4, 'Gabriela Helena', 'University of Sydney', 'Sistemas de Informação'),
(5, 'Erika Teresa', 'University of Sydney', 'Design');

-- --------------------------------------------------------

--
-- Estrutura da tabela `parceriauni`
--

CREATE TABLE `parceriauni` (
  `NomeUni` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `parceriauni`
--

INSERT INTO `parceriauni` (`NomeUni`) VALUES
('Faculdade de Artes e design grafico'),
('Faculdade Getulio Vargas'),
('Universidade de Campinas'),
('Universidade de São Francisco'),
('Universidade de São Paulo');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pesquisa`
--

CREATE TABLE `pesquisa` (
  `NomeProj` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pesquisa`
--

INSERT INTO `pesquisa` (`NomeProj`) VALUES
('Automóveis inteligentes'),
('Distúrbios de Linguagem, Corpo e Psiquismo'),
('Economia Industrial, Trabalho e Tecnologia'),
('Estudos da Tradução e da Interpretação'),
('Grupo de Pesquisas em Midia Impressa'),
('Inteligência artificial e saúde'),
('Moeda, Finanças e Desenvolvimento Econômico'),
('Multiplicação de Leads via adwords'),
('Tecnologias Digitais na Educação do ensino fundamental');

-- --------------------------------------------------------

--
-- Estrutura da tabela `professor`
--

CREATE TABLE `professor` (
  `CPF` bigint(20) NOT NULL,
  `Carreira` char(150) NOT NULL,
  `NivelCarreira` char(150) NOT NULL,
  `SalaPessoal` int(11) NOT NULL,
  `Predio` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `professor`
--

INSERT INTO `professor` (`CPF`, `Carreira`, `NivelCarreira`, `SalaPessoal`, `Predio`) VALUES
(12121235523, 'Titular', 'C', 320, 'Economia'),
(12121235524, 'Titular', 'B', 410, 'Letras'),
(12129232323, 'Graduado', 'A', 300, 'Artes'),
(12345678910, 'Graduado', 'B', 212, 'Computação'),
(21223345690, 'Titular', 'A', 200, 'Computação'),
(23212321300, 'Graduado', 'C', 201, 'Marketing'),
(23212321312, 'Livre docente', 'D', 219, 'Marketing'),
(23212321333, 'Doutor', 'B', 203, 'Marketing');

-- --------------------------------------------------------

--
-- Estrutura da tabela `projeto`
--

CREATE TABLE `projeto` (
  `NomeProj` char(150) NOT NULL,
  `ProjDescricao` char(150) DEFAULT NULL,
  `Objetivo` char(150) DEFAULT NULL,
  `Orcamento` int(11) NOT NULL,
  `ProjInicio` date NOT NULL,
  `ProjFim` date NOT NULL,
  `Pesquisa` char(150) DEFAULT NULL,
  `Extensao` char(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `projeto`
--

INSERT INTO `projeto` (`NomeProj`, `ProjDescricao`, `Objetivo`, `Orcamento`, `ProjInicio`, `ProjFim`, `Pesquisa`, `Extensao`) VALUES
('Apresentações Impactantes', NULL, NULL, 8000, '2016-08-01', '2017-10-13', NULL, '1'),
('Automação residencia arduino', NULL, NULL, 10000, '2016-08-01', '2017-10-13', NULL, '1'),
('Automóveis inteligentes', NULL, NULL, 800000, '2015-07-16', '2017-10-25', '1', NULL),
('Distúrbios de Linguagem, Corpo e Psiquismo', NULL, NULL, 50000, '2016-08-01', '2017-10-13', '1', NULL),
('Economia Industrial, Trabalho e Tecnologia', NULL, NULL, 30000, '2016-08-01', '2017-10-13', '1', NULL),
('Estratégica de Marketing nos meios digitais', NULL, NULL, 5000, '2016-08-01', '2017-10-13', NULL, '1'),
('Estudos da Tradução e da Interpretação', NULL, NULL, 28010, '2016-08-01', '2017-10-13', '1', NULL),
('Grupo de estudo de escultura e modelagem de personagem', NULL, NULL, 12000, '2016-08-01', '2017-10-13', NULL, '1'),
('Grupo de Pesquisas em Midia Impressa', NULL, NULL, 20500, '2016-08-01', '2017-10-13', '1', NULL),
('Inteligência artificial e saúde', NULL, NULL, 1000000, '2015-09-01', '2017-12-14', '1', NULL),
('Moeda, Finanças e Desenvolvimento Econômico', NULL, NULL, 31005, '2016-08-01', '2017-10-13', '1', NULL),
('Multiplicação de Leads via adwords', NULL, NULL, 100000, '2015-06-08', '2017-10-09', '1', NULL),
('Tecnologias Digitais na Educação do ensino fundamental', NULL, NULL, 200000, '2016-03-01', '2017-04-27', '1', NULL),
('Webdesenvolvimento focado em frontend', NULL, NULL, 10000, '2016-08-01', '2017-10-13', NULL, '1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `remuneracao`
--

CREATE TABLE `remuneracao` (
  `ValorRem` int(11) NOT NULL,
  `OcNumero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `remuneracao`
--

INSERT INTO `remuneracao` (`ValorRem`, `OcNumero`) VALUES
(1000, 24363427),
(1200, 25376384),
(2000, 34235342),
(1100, 52436372),
(1500, 62536152);

-- --------------------------------------------------------

--
-- Estrutura da tabela `revista`
--

CREATE TABLE `revista` (
  `RevCodigo` int(11) NOT NULL,
  `RevNome` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `revista`
--

INSERT INTO `revista` (`RevCodigo`, `RevNome`) VALUES
(1743632, 'Revista de Administração e Marketing'),
(5482648, 'Boletim da Literatura Moderna'),
(6546456, 'Revista de Tecnologia'),
(7645344, 'Revista de Pesquisas Biológicas'),
(9374528, 'Economia e Sociedade');

-- --------------------------------------------------------

--
-- Estrutura da tabela `strictusensu`
--

CREATE TABLE `strictusensu` (
  `CodCurso` int(11) NOT NULL,
  `StrictuTipo` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `strictusensu`
--

INSERT INTO `strictusensu` (`CodCurso`, `StrictuTipo`) VALUES
(911, 'Mestrado'),
(922, 'Mestrado'),
(933, 'Doutorado'),
(955, 'Pós Graduação'),
(999, 'Doutorado');

-- --------------------------------------------------------

--
-- Estrutura da tabela `trabalha`
--

CREATE TABLE `trabalha` (
  `CPF` bigint(20) NOT NULL,
  `CNPJEmpresa` int(11) NOT NULL,
  `Remuneracao` int(11) NOT NULL,
  `InicioEst` date NOT NULL,
  `FimEst` date NOT NULL,
  `Obrigatorio` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `trabalha`
--

INSERT INTO `trabalha` (`CPF`, `CNPJEmpresa`, `Remuneracao`, `InicioEst`, `FimEst`, `Obrigatorio`) VALUES
(12121132467, 1222, 1550, '2015-03-10', '2016-09-01', 'N'),
(12221132467, 1234, 1300, '2016-10-01', '2017-04-13', 'S'),
(23454367590, 1234, 1300, '2015-10-02', '2016-08-12', 'S'),
(21377898790, 1333, 1600, '2015-05-05', '2016-10-13', 'N');

-- --------------------------------------------------------

--
-- Estrutura da tabela `trabalhofinal`
--

CREATE TABLE `trabalhofinal` (
  `RegTrabalho` int(11) NOT NULL,
  `Tema` char(150) NOT NULL,
  `DataTrabalho` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `trabalhofinal`
--

INSERT INTO `trabalhofinal` (`RegTrabalho`, `Tema`, `DataTrabalho`) VALUES
(958473, 'Big Data', '2016-12-20'),
(958474, 'Otimização de testes de software', '2016-12-21'),
(958475, 'Segurança de redes', '2016-12-22'),
(958476, 'Tecnologia na medicina', '2016-12-23'),
(958477, 'Literatura moderna brasileira', '2016-12-24');

-- --------------------------------------------------------

--
-- Estrutura da tabela `turma`
--

CREATE TABLE `turma` (
  `NumeroTurma` int(11) NOT NULL,
  `Sala` int(11) NOT NULL,
  `Predio` char(150) NOT NULL,
  `HorarioAula` char(150) NOT NULL,
  `Semestre` char(150) NOT NULL,
  `NumAlunos` int(11) NOT NULL,
  `DiscCodigo` int(11) NOT NULL,
  `CPF` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `turma`
--

INSERT INTO `turma` (`NumeroTurma`, `Sala`, `Predio`, `HorarioAula`, `Semestre`, `NumAlunos`, `DiscCodigo`, `CPF`) VALUES
(194, 3, 'H1', 'Noturno', '2semestre', 60, 100, 12345678910),
(195, 3, 'H1', 'Noturno', '2semestre', 60, 101, 12345678910),
(295, 22, 'H2', 'Matutino', '2semestre', 50, 203, 12345678910),
(494, 21, 'H4', 'Matutino', '1semestre', 50, 400, 23212321333),
(499, 22, 'H4', 'Matutino', '2semestre', 50, 401, 23212321333),
(594, 50, 'H1', 'Noturno', '4semestre', 50, 500, 21223345690);

-- --------------------------------------------------------

--
-- Estrutura da tabela `verba`
--

CREATE TABLE `verba` (
  `IDVerba` int(11) NOT NULL,
  `ValorVerba` int(11) NOT NULL,
  `DescricaoVerba` char(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `verba`
--

INSERT INTO `verba` (`IDVerba`, `ValorVerba`, `DescricaoVerba`) VALUES
(14534, 50000, 'Verba para sala de informática'),
(23749, 20000, 'Verba para reparos'),
(34232, 40000, 'Verba para equipamentos de laboratório'),
(73232, 30000, 'Verba para manutenção de veículos'),
(94543, 100000, 'Verba para construção de dormitório');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acervo`
--
ALTER TABLE `acervo`
  ADD PRIMARY KEY (`RegBiblio`,`IDBiblioteca`),
  ADD KEY `FKAce_Bib_FK` (`IDBiblioteca`);

--
-- Indexes for table `administrativo`
--
ALTER TABLE `administrativo`
  ADD PRIMARY KEY (`CPF`);

--
-- Indexes for table `advertencia`
--
ALTER TABLE `advertencia`
  ADD PRIMARY KEY (`OcNumero`);

--
-- Indexes for table `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`CPF`),
  ADD UNIQUE KEY `SID_Aluno_ID` (`IDAluno`),
  ADD KEY `FKConclusao_FK` (`RegTrabalho`),
  ADD KEY `FKOrientacao_FK` (`Ori_CPF`);

--
-- Indexes for table `alunobolsa`
--
ALTER TABLE `alunobolsa`
  ADD PRIMARY KEY (`TipoBolsa`,`CPF`),
  ADD KEY `FKAlu_Alu_2_FK` (`CPF`);

--
-- Indexes for table `alunoext`
--
ALTER TABLE `alunoext`
  ADD PRIMARY KEY (`NomeProj`,`CPF`),
  ADD KEY `FKAlu_Alu_1_FK` (`CPF`);

--
-- Indexes for table `alunopesq`
--
ALTER TABLE `alunopesq`
  ADD PRIMARY KEY (`NomeProj`,`CPF`),
  ADD KEY `FKAlu_Alu_FK` (`CPF`);

--
-- Indexes for table `apoioprof`
--
ALTER TABLE `apoioprof`
  ADD PRIMARY KEY (`ID`,`NomeProj`),
  ADD KEY `FKApo_Pes_1_FK` (`NomeProj`);

--
-- Indexes for table `apoiouni`
--
ALTER TABLE `apoiouni`
  ADD PRIMARY KEY (`NomeUni`,`NomeProj`),
  ADD KEY `FKApo_Pes_FK` (`NomeProj`);

--
-- Indexes for table `artigo`
--
ALTER TABLE `artigo`
  ADD PRIMARY KEY (`IDArtigo`),
  ADD KEY `FKPublicacao_FK` (`RevCodigo`),
  ADD KEY `FKArtigoConf_FK` (`ConfNome`,`ConfData`);

--
-- Indexes for table `atividade`
--
ALTER TABLE `atividade`
  ADD PRIMARY KEY (`NomeAtiv`),
  ADD UNIQUE KEY `SID_Atividade_ID` (`DataAtiv`),
  ADD KEY `FKAtivProj_FK` (`NomeProj`);

--
-- Indexes for table `ausencia`
--
ALTER TABLE `ausencia`
  ADD PRIMARY KEY (`OcNumero`);

--
-- Indexes for table `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`RegAutor`);

--
-- Indexes for table `autor_1`
--
ALTER TABLE `autor_1`
  ADD PRIMARY KEY (`CPF`,`IDArtigo`),
  ADD KEY `FKAut_Art_FK` (`IDArtigo`);

--
-- Indexes for table `bem`
--
ALTER TABLE `bem`
  ADD PRIMARY KEY (`Identificador`),
  ADD KEY `FKProduto_FK` (`IDCompra`);

--
-- Indexes for table `biblioteca`
--
ALTER TABLE `biblioteca`
  ADD PRIMARY KEY (`IDBiblioteca`);

--
-- Indexes for table `bibliotecario`
--
ALTER TABLE `bibliotecario`
  ADD PRIMARY KEY (`CPF`),
  ADD KEY `FKTrabBiblio_FK` (`IDBiblioteca`);

--
-- Indexes for table `bolsa`
--
ALTER TABLE `bolsa`
  ADD PRIMARY KEY (`TipoBolsa`);

--
-- Indexes for table `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`IDCompra`);

--
-- Indexes for table `conferencia`
--
ALTER TABLE `conferencia`
  ADD PRIMARY KEY (`ConfNome`,`ConfData`);

--
-- Indexes for table `coordpesq`
--
ALTER TABLE `coordpesq`
  ADD PRIMARY KEY (`NomeProj`,`CPF`),
  ADD KEY `FKCoo_Pro_FK` (`CPF`);

--
-- Indexes for table `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`CodCurso`);

--
-- Indexes for table `disciplina`
--
ALTER TABLE `disciplina`
  ADD PRIMARY KEY (`DiscCodigo`),
  ADD KEY `FKGrade_FK` (`CodCurso`);

--
-- Indexes for table `escrito`
--
ALTER TABLE `escrito`
  ADD PRIMARY KEY (`RegAutor`,`ISBN`),
  ADD KEY `FKEsc_Liv_FK` (`ISBN`);

--
-- Indexes for table `estagio`
--
ALTER TABLE `estagio`
  ADD PRIMARY KEY (`CNPJEmpresa`);

--
-- Indexes for table `exemplar`
--
ALTER TABLE `exemplar`
  ADD PRIMARY KEY (`RegBiblio`),
  ADD KEY `FKExemplares_FK` (`ISBN`),
  ADD KEY `FKRetira_FK` (`CPF`);

--
-- Indexes for table `extensao`
--
ALTER TABLE `extensao`
  ADD PRIMARY KEY (`NomeProj`);

--
-- Indexes for table `folhapag`
--
ALTER TABLE `folhapag`
  ADD PRIMARY KEY (`CodigoID`),
  ADD KEY `FKPagFunc_FK` (`CPF`);

--
-- Indexes for table `funcaocargo`
--
ALTER TABLE `funcaocargo`
  ADD PRIMARY KEY (`NomeOcupacao`),
  ADD KEY `FKProfCar_FK` (`CPF`);

--
-- Indexes for table `funcext`
--
ALTER TABLE `funcext`
  ADD PRIMARY KEY (`NomeProj`,`CPF`),
  ADD KEY `FKFun_Fun_FK` (`CPF`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`CPF`);

--
-- Indexes for table `imovel`
--
ALTER TABLE `imovel`
  ADD PRIMARY KEY (`Identificador`);

--
-- Indexes for table `individuo`
--
ALTER TABLE `individuo`
  ADD PRIMARY KEY (`CPF`);

--
-- Indexes for table `inscrito`
--
ALTER TABLE `inscrito`
  ADD PRIMARY KEY (`NumeroTurma`,`CPF`),
  ADD KEY `FKIns_Alu_FK` (`CPF`);

--
-- Indexes for table `intercambio`
--
ALTER TABLE `intercambio`
  ADD PRIMARY KEY (`NumInter`);

--
-- Indexes for table `intercambista`
--
ALTER TABLE `intercambista`
  ADD PRIMARY KEY (`NumInter`,`CPF`),
  ADD KEY `FKInt_Alu_FK` (`CPF`);

--
-- Indexes for table `latusensu`
--
ALTER TABLE `latusensu`
  ADD PRIMARY KEY (`CodCurso`);

--
-- Indexes for table `livro`
--
ALTER TABLE `livro`
  ADD PRIMARY KEY (`ISBN`);

--
-- Indexes for table `matricula`
--
ALTER TABLE `matricula`
  ADD PRIMARY KEY (`CPF`),
  ADD KEY `FKMat_Cur_FK` (`CodCurso`);

--
-- Indexes for table `movel`
--
ALTER TABLE `movel`
  ADD PRIMARY KEY (`Identificador`),
  ADD KEY `FKLocalMovel_FK` (`Loc_Identificador`);

--
-- Indexes for table `ocorrencia`
--
ALTER TABLE `ocorrencia`
  ADD PRIMARY KEY (`OcNumero`),
  ADD KEY `FKFuncOrr_FK` (`CPF`);

--
-- Indexes for table `parceriaprof`
--
ALTER TABLE `parceriaprof`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `parceriauni`
--
ALTER TABLE `parceriauni`
  ADD PRIMARY KEY (`NomeUni`);

--
-- Indexes for table `pesquisa`
--
ALTER TABLE `pesquisa`
  ADD PRIMARY KEY (`NomeProj`);

--
-- Indexes for table `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`CPF`);

--
-- Indexes for table `projeto`
--
ALTER TABLE `projeto`
  ADD PRIMARY KEY (`NomeProj`);

--
-- Indexes for table `remuneracao`
--
ALTER TABLE `remuneracao`
  ADD PRIMARY KEY (`OcNumero`);

--
-- Indexes for table `revista`
--
ALTER TABLE `revista`
  ADD PRIMARY KEY (`RevCodigo`);

--
-- Indexes for table `strictusensu`
--
ALTER TABLE `strictusensu`
  ADD PRIMARY KEY (`CodCurso`);

--
-- Indexes for table `trabalha`
--
ALTER TABLE `trabalha`
  ADD PRIMARY KEY (`CNPJEmpresa`,`CPF`),
  ADD KEY `FKTra_Alu_FK` (`CPF`);

--
-- Indexes for table `trabalhofinal`
--
ALTER TABLE `trabalhofinal`
  ADD PRIMARY KEY (`RegTrabalho`);

--
-- Indexes for table `turma`
--
ALTER TABLE `turma`
  ADD PRIMARY KEY (`NumeroTurma`),
  ADD KEY `FKOferecimento_FK` (`DiscCodigo`),
  ADD KEY `FKMinistra_FK` (`CPF`);

--
-- Indexes for table `verba`
--
ALTER TABLE `verba`
  ADD PRIMARY KEY (`IDVerba`);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `acervo`
--
ALTER TABLE `acervo`
  ADD CONSTRAINT `FKAce_Bib_FK` FOREIGN KEY (`IDBiblioteca`) REFERENCES `biblioteca` (`IDBiblioteca`),
  ADD CONSTRAINT `FKAce_Exe` FOREIGN KEY (`RegBiblio`) REFERENCES `exemplar` (`RegBiblio`);

--
-- Limitadores para a tabela `administrativo`
--
ALTER TABLE `administrativo`
  ADD CONSTRAINT `FKFun_Adm_FK` FOREIGN KEY (`CPF`) REFERENCES `funcionario` (`CPF`);

--
-- Limitadores para a tabela `advertencia`
--
ALTER TABLE `advertencia`
  ADD CONSTRAINT `FKOco_Adv_FK` FOREIGN KEY (`OcNumero`) REFERENCES `ocorrencia` (`OcNumero`);

--
-- Limitadores para a tabela `aluno`
--
ALTER TABLE `aluno`
  ADD CONSTRAINT `FKConclusao_FK` FOREIGN KEY (`RegTrabalho`) REFERENCES `trabalhofinal` (`RegTrabalho`),
  ADD CONSTRAINT `FKInd_Alu_FK` FOREIGN KEY (`CPF`) REFERENCES `individuo` (`CPF`),
  ADD CONSTRAINT `FKOrientacao_FK` FOREIGN KEY (`Ori_CPF`) REFERENCES `professor` (`CPF`);

--
-- Limitadores para a tabela `alunobolsa`
--
ALTER TABLE `alunobolsa`
  ADD CONSTRAINT `FKAlu_Alu_2_FK` FOREIGN KEY (`CPF`) REFERENCES `aluno` (`CPF`),
  ADD CONSTRAINT `FKAlu_Bol` FOREIGN KEY (`TipoBolsa`) REFERENCES `bolsa` (`TipoBolsa`);

--
-- Limitadores para a tabela `alunoext`
--
ALTER TABLE `alunoext`
  ADD CONSTRAINT `FKAlu_Alu_1_FK` FOREIGN KEY (`CPF`) REFERENCES `aluno` (`CPF`),
  ADD CONSTRAINT `FKAlu_Ext` FOREIGN KEY (`NomeProj`) REFERENCES `extensao` (`NomeProj`);

--
-- Limitadores para a tabela `alunopesq`
--
ALTER TABLE `alunopesq`
  ADD CONSTRAINT `FKAlu_Alu_FK` FOREIGN KEY (`CPF`) REFERENCES `aluno` (`CPF`),
  ADD CONSTRAINT `FKAlu_Pes` FOREIGN KEY (`NomeProj`) REFERENCES `pesquisa` (`NomeProj`);

--
-- Limitadores para a tabela `apoioprof`
--
ALTER TABLE `apoioprof`
  ADD CONSTRAINT `FKApo_Par_1` FOREIGN KEY (`ID`) REFERENCES `parceriaprof` (`ID`),
  ADD CONSTRAINT `FKApo_Pes_1_FK` FOREIGN KEY (`NomeProj`) REFERENCES `pesquisa` (`NomeProj`);

--
-- Limitadores para a tabela `apoiouni`
--
ALTER TABLE `apoiouni`
  ADD CONSTRAINT `FKApo_Par` FOREIGN KEY (`NomeUni`) REFERENCES `parceriauni` (`NomeUni`),
  ADD CONSTRAINT `FKApo_Pes_FK` FOREIGN KEY (`NomeProj`) REFERENCES `pesquisa` (`NomeProj`);

--
-- Limitadores para a tabela `artigo`
--
ALTER TABLE `artigo`
  ADD CONSTRAINT `FKArtigoConf_FK` FOREIGN KEY (`ConfNome`,`ConfData`) REFERENCES `conferencia` (`ConfNome`, `ConfData`),
  ADD CONSTRAINT `FKPublicacao_FK` FOREIGN KEY (`RevCodigo`) REFERENCES `revista` (`RevCodigo`);

--
-- Limitadores para a tabela `atividade`
--
ALTER TABLE `atividade`
  ADD CONSTRAINT `FKAtivProj_FK` FOREIGN KEY (`NomeProj`) REFERENCES `projeto` (`NomeProj`);

--
-- Limitadores para a tabela `ausencia`
--
ALTER TABLE `ausencia`
  ADD CONSTRAINT `FKOco_Aus_FK` FOREIGN KEY (`OcNumero`) REFERENCES `ocorrencia` (`OcNumero`);

--
-- Limitadores para a tabela `autor_1`
--
ALTER TABLE `autor_1`
  ADD CONSTRAINT `FKAut_Art_FK` FOREIGN KEY (`IDArtigo`) REFERENCES `artigo` (`IDArtigo`),
  ADD CONSTRAINT `FKAut_Pro` FOREIGN KEY (`CPF`) REFERENCES `professor` (`CPF`);

--
-- Limitadores para a tabela `bem`
--
ALTER TABLE `bem`
  ADD CONSTRAINT `FKProduto_FK` FOREIGN KEY (`IDCompra`) REFERENCES `compra` (`IDCompra`);

--
-- Limitadores para a tabela `bibliotecario`
--
ALTER TABLE `bibliotecario`
  ADD CONSTRAINT `FKFun_Bib_FK` FOREIGN KEY (`CPF`) REFERENCES `funcionario` (`CPF`),
  ADD CONSTRAINT `FKTrabBiblio_FK` FOREIGN KEY (`IDBiblioteca`) REFERENCES `biblioteca` (`IDBiblioteca`);

--
-- Limitadores para a tabela `coordpesq`
--
ALTER TABLE `coordpesq`
  ADD CONSTRAINT `FKCoo_Pes` FOREIGN KEY (`NomeProj`) REFERENCES `pesquisa` (`NomeProj`),
  ADD CONSTRAINT `FKCoo_Pro_FK` FOREIGN KEY (`CPF`) REFERENCES `professor` (`CPF`);

--
-- Limitadores para a tabela `disciplina`
--
ALTER TABLE `disciplina`
  ADD CONSTRAINT `FKGrade_FK` FOREIGN KEY (`CodCurso`) REFERENCES `curso` (`CodCurso`);

--
-- Limitadores para a tabela `escrito`
--
ALTER TABLE `escrito`
  ADD CONSTRAINT `FKEsc_Aut` FOREIGN KEY (`RegAutor`) REFERENCES `autor` (`RegAutor`),
  ADD CONSTRAINT `FKEsc_Liv_FK` FOREIGN KEY (`ISBN`) REFERENCES `livro` (`ISBN`);

--
-- Limitadores para a tabela `exemplar`
--
ALTER TABLE `exemplar`
  ADD CONSTRAINT `FKExemplares_FK` FOREIGN KEY (`ISBN`) REFERENCES `livro` (`ISBN`),
  ADD CONSTRAINT `FKRetira_FK` FOREIGN KEY (`CPF`) REFERENCES `aluno` (`CPF`);

--
-- Limitadores para a tabela `extensao`
--
ALTER TABLE `extensao`
  ADD CONSTRAINT `FKPro_Ext_FK` FOREIGN KEY (`NomeProj`) REFERENCES `projeto` (`NomeProj`);

--
-- Limitadores para a tabela `folhapag`
--
ALTER TABLE `folhapag`
  ADD CONSTRAINT `FKPagFunc_FK` FOREIGN KEY (`CPF`) REFERENCES `funcionario` (`CPF`);

--
-- Limitadores para a tabela `funcaocargo`
--
ALTER TABLE `funcaocargo`
  ADD CONSTRAINT `FKProfCar_FK` FOREIGN KEY (`CPF`) REFERENCES `professor` (`CPF`);

--
-- Limitadores para a tabela `funcext`
--
ALTER TABLE `funcext`
  ADD CONSTRAINT `FKFun_Ext` FOREIGN KEY (`NomeProj`) REFERENCES `extensao` (`NomeProj`),
  ADD CONSTRAINT `FKFun_Fun_FK` FOREIGN KEY (`CPF`) REFERENCES `funcionario` (`CPF`);

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `FKInd_Fun_FK` FOREIGN KEY (`CPF`) REFERENCES `individuo` (`CPF`);

--
-- Limitadores para a tabela `imovel`
--
ALTER TABLE `imovel`
  ADD CONSTRAINT `FKBem_Imo_FK` FOREIGN KEY (`Identificador`) REFERENCES `bem` (`Identificador`);

--
-- Limitadores para a tabela `inscrito`
--
ALTER TABLE `inscrito`
  ADD CONSTRAINT `FKIns_Alu_FK` FOREIGN KEY (`CPF`) REFERENCES `aluno` (`CPF`),
  ADD CONSTRAINT `FKIns_Tur` FOREIGN KEY (`NumeroTurma`) REFERENCES `turma` (`NumeroTurma`);

--
-- Limitadores para a tabela `intercambista`
--
ALTER TABLE `intercambista`
  ADD CONSTRAINT `FKInt_Alu_FK` FOREIGN KEY (`CPF`) REFERENCES `aluno` (`CPF`),
  ADD CONSTRAINT `FKInt_Int` FOREIGN KEY (`NumInter`) REFERENCES `intercambio` (`NumInter`);

--
-- Limitadores para a tabela `latusensu`
--
ALTER TABLE `latusensu`
  ADD CONSTRAINT `FKCur_Lat_FK` FOREIGN KEY (`CodCurso`) REFERENCES `curso` (`CodCurso`);

--
-- Limitadores para a tabela `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `FKMat_Alu_FK` FOREIGN KEY (`CPF`) REFERENCES `aluno` (`CPF`),
  ADD CONSTRAINT `FKMat_Cur_FK` FOREIGN KEY (`CodCurso`) REFERENCES `curso` (`CodCurso`);

--
-- Limitadores para a tabela `movel`
--
ALTER TABLE `movel`
  ADD CONSTRAINT `FKBem_Mov_FK` FOREIGN KEY (`Identificador`) REFERENCES `bem` (`Identificador`),
  ADD CONSTRAINT `FKLocalMovel_FK` FOREIGN KEY (`Loc_Identificador`) REFERENCES `imovel` (`Identificador`);

--
-- Limitadores para a tabela `ocorrencia`
--
ALTER TABLE `ocorrencia`
  ADD CONSTRAINT `FKFuncOrr_FK` FOREIGN KEY (`CPF`) REFERENCES `funcionario` (`CPF`);

--
-- Limitadores para a tabela `pesquisa`
--
ALTER TABLE `pesquisa`
  ADD CONSTRAINT `FKPro_Pes_FK` FOREIGN KEY (`NomeProj`) REFERENCES `projeto` (`NomeProj`);

--
-- Limitadores para a tabela `professor`
--
ALTER TABLE `professor`
  ADD CONSTRAINT `FKFun_Pro_FK` FOREIGN KEY (`CPF`) REFERENCES `funcionario` (`CPF`);

--
-- Limitadores para a tabela `remuneracao`
--
ALTER TABLE `remuneracao`
  ADD CONSTRAINT `FKRemunerado_FK` FOREIGN KEY (`OcNumero`) REFERENCES `ausencia` (`OcNumero`);

--
-- Limitadores para a tabela `strictusensu`
--
ALTER TABLE `strictusensu`
  ADD CONSTRAINT `FKCur_Str_FK` FOREIGN KEY (`CodCurso`) REFERENCES `curso` (`CodCurso`);

--
-- Limitadores para a tabela `trabalha`
--
ALTER TABLE `trabalha`
  ADD CONSTRAINT `FKTra_Alu_FK` FOREIGN KEY (`CPF`) REFERENCES `aluno` (`CPF`),
  ADD CONSTRAINT `FKTra_Est` FOREIGN KEY (`CNPJEmpresa`) REFERENCES `estagio` (`CNPJEmpresa`);

--
-- Limitadores para a tabela `turma`
--
ALTER TABLE `turma`
  ADD CONSTRAINT `FKMinistra_FK` FOREIGN KEY (`CPF`) REFERENCES `professor` (`CPF`),
  ADD CONSTRAINT `FKOferecimento_FK` FOREIGN KEY (`DiscCodigo`) REFERENCES `disciplina` (`DiscCodigo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
