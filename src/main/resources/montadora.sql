create database montadora;
use montadora;


CREATE TABLE IF NOT EXISTS montadora (
  idMontadora INT AUTO_INCREMENT PRIMARY KEY,
  nomeMontadora VARCHAR(255) NOT NULL,
  nomePais VARCHAR(255) NOT NULL,
  nomePresidente VARCHAR(255) NOT NULL,
  dataFundacao DATE NOT NULL
);


INSERT INTO montadora (nomeMontadora, nomePais, nomePresidente, dataFundacao)
VALUES ('Fiat', 'Itália', 'Gianni Agnelli', '1899-07-11');

INSERT INTO montadora (nomeMontadora, nomePais, nomePresidente, dataFundacao)
VALUES ('Volkswagen', 'Alemanha', 'Ferdinand Porsche', '1937-05-28');


CREATE TABLE IF NOT EXISTS carro (
  idCarro INT AUTO_INCREMENT PRIMARY KEY,
  modelo VARCHAR(255) NOT NULL,
  placa VARCHAR(255) NOT NULL UNIQUE, -- Make placa unique to avoid duplicates
  idMontadora INT NOT NULL,
  ano INT NOT NULL,
  valor DOUBLE NOT NULL,
  FOREIGN KEY (idMontadora) REFERENCES montadora(idMontadora)
);


INSERT INTO carro (modelo, placa, idMontadora, ano, valor)
VALUES ('Uno', 'ABC-1834', 1, 1990, 5000.00);

INSERT INTO carro (modelo, placa, idMontadora, ano, valor)
VALUES ('Gol', 'DEF-5608', 2, 2015, 25000.00);


SELECT * FROM CARRO;
SELECT * FROM MONTADORA;