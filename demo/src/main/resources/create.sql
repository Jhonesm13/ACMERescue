DROP TABLE IF EXISTS equipamentos;
CREATE TABLE equipamentos (
    id INT AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    custoDiario DOUBLE NOT NULL CHECK (custoDiario >= 0),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS atendimentos;
CREATE TABLE atendimentos (cod int,
                        status VARCHAR(255),
                        inicio DATE,
                        duracao int,
                        codigo_evento int,
                        PRIMARY KEY(cod));

DROP TABLE IF EXISTS equipes;
CREATE TABLE equipes (
    numero INT,
    quantidade_membros INT,
    latitude DOUBLE,
    longitude DOUBLE,
    PRIMARY KEY (numero)
);

DROP TABLE IF EXISTS eventos;
CREATE TABLE eventos (
    codigo BIGINT PRIMARY KEY,
    descricao VARCHAR(255),
    data VARCHAR(20),  
    latitude DOUBLE,
    longitude DOUBLE
);

DROP TABLE IF EXISTS equipe_equipamentos;
CREATE TABLE equipe_equipamentos (
    equipe_id INT,
    equipamento_id INT,
    PRIMARY KEY (equipe_id, equipamento_id),
    FOREIGN KEY (equipe_id) REFERENCES equipes(numero) ON DELETE CASCADE,
    FOREIGN KEY (equipamento_id) REFERENCES equipamentos(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS equipe_atendimento;
CREATE TABLE equipe_atendimento (
    equipe_id INT,
    cod_atendimento INT,
    PRIMARY KEY (equipe_id, cod_atendimento),
    FOREIGN KEY (equipe_id) REFERENCES equipes(numero) ON DELETE CASCADE,
    FOREIGN KEY (cod_atendimento) REFERENCES atendimentos(cod) ON DELETE CASCADE
);