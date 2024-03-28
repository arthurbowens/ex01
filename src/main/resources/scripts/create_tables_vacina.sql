CREATE DATABASE exemplos;
USE exemplos;
 
 create table Pais (
	id int not null primary key auto_increment
    , nome varchar(200) not null
    , sigla varchar(3) not null
    );
    
    
CREATE TABLE Pessoa (
id int not null primary key auto_increment
, nome varchar(100) not null
, cpf char(11) unique
, dataNascimento date not null
, tipoDePessoa int not null comment '1- PESQUISADOR, 2- VOLUNTÁRIO, 3- PÚBLICO EM GERAL'	
, sexo char not null
, idPais int not null 
, foreign key (idPais) references Pais (id)
);
 
CREATE TABLE Vacina (
	id int not null primary key auto_increment
    , nome varchar(200) not null
    , idPais int not null
    , idPesquisador int not null
    , estagio int not null comment '1- INICIAL, 2- TESTES, 3- APLICAÇÃO'
    , dataDeInicioDaPesquisa date not null
    , FOREIGN KEY (idPesquisador) references Pessoa (id)
    , foreign key (idPais) references  Pais (id)
    );

 
 
CREATE TABLE Aplicacao (
	id int not null auto_increment primary key
    , idPessoa int not null
    , idVacina int not null
    , dataAplicacao date not null
    , avaliacao int not null
    , foreign key (idPessoa) references Pessoa (id)
    , foreign key (idVacina) references Vacina (id) 
    );
    
    
select * from Pessoa;
select * from pais;