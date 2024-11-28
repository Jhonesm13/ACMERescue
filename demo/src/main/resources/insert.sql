INSERT INTO equipamentos (nome, custoDiario) VALUES ('Equipamento 1', 15.00);
INSERT INTO equipamentos (nome, custoDiario) VALUES ('Equipamento 2', 25.00);
INSERT INTO equipamentos (nome, custoDiario) VALUES ('Equipamento 3', 35.00);
INSERT INTO equipamentos (nome, custoDiario) VALUES ('Equipamento 4', 45.00);
INSERT INTO equipamentos (nome, custoDiario) VALUES ('Equipamento 5', 15.00);
INSERT INTO equipamentos (nome, custoDiario) VALUES ('Equipamento 6', 25.00);
INSERT INTO equipamentos (nome, custoDiario) VALUES ('Equipamento 7', 35.00);
INSERT INTO equipamentos (nome, custoDiario) VALUES ('Equipamento 8', 45.00);
INSERT INTO equipamentos (nome, custoDiario) VALUES ('Equipamento 9', 15.00);
INSERT INTO equipamentos (nome, custoDiario) VALUES ('Equipamento 10', 25.00);


INSERT INTO atendimentos (cod, status, inicio, duracao, codigo_evento) VALUES (1,'CANCELADO', '2020-12-12', 5, 1);
INSERT INTO atendimentos (cod, status, inicio, duracao, codigo_evento) VALUES (2, 'PENDENTE', '2021-01-15', 3, 2);
INSERT INTO atendimentos (cod, status, inicio, duracao, codigo_evento) VALUES (3, 'EXECUTANDO', '2021-03-22', 7, 3);
INSERT INTO atendimentos (cod, status, inicio, duracao, codigo_evento) VALUES (4, 'FINALIZADO', '2022-07-10', 4, 4);
INSERT INTO atendimentos (cod, status, inicio, duracao, codigo_evento) VALUES (5, 'CANCELADO', '2022-09-05', 2, 5);

INSERT INTO equipes (numero, quantidade_membros, latitude, longitude) VALUES (1, 10, -23.5505, -46.6333);
INSERT INTO equipes (numero, quantidade_membros, latitude, longitude) VALUES (2, 8, -22.9068, -43.1729);
INSERT INTO equipes (numero, quantidade_membros, latitude, longitude) VALUES (3, 15, 40.7128, -74.0060);
INSERT INTO equipes (numero, quantidade_membros, latitude, longitude) VALUES (4, 12, 48.8566, 2.3522);
INSERT INTO equipes (numero, quantidade_membros, latitude, longitude) VALUES (5, 20, 35.6895, 139.6917);

INSERT INTO eventos (codigo, descricao, data, latitude, longitude) VALUES (1, 'Ciclone em Floripa', '2024-11-20', -10.1234, 45.6789);
INSERT INTO eventos (codigo, descricao, data, latitude, longitude) VALUES (2, 'Enchente em Porto Alegre', '2024-11-21', -3.1234, -60.6789);
INSERT INTO eventos (codigo, descricao, data, latitude, longitude) VALUES (3, 'Terremoto em Tókio', '2024-11-22', -33.4628, -70.6483);
INSERT INTO eventos (codigo, descricao, data, latitude, longitude) VALUES (4, 'Deslizamento na Costa Rica', '2024-11-23', 9.9822, -84.0346);
INSERT INTO eventos (codigo, descricao, data, latitude, longitude) VALUES (5, 'Furacão em Orlando', '2024-11-24', 18.2208, -67.1462);