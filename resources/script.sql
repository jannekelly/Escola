create database escola;

create table escola(
id int not null auto_increment primary key,
nome varchar (40),
)

create table endereço(
logadouro varchar(40),
complemeto varchar(40),
bairro varchar(400),
cidade varchar(40),
estado varchar(40),
id int not null auto_increment primary key
)

create table aluno(
id int not null auto_increment primary key,
nome varchar(50),
datade nascimento date)

create table turma(
id int not null auto_increment primary key,
nome varchar(50),
capacidade int)


ALTER TABLE  'escola' add CONSTRAINT 'fk_endereco' foreign key ('id') referenes endereco ('id');

