INSERT INTO TB_PESSOA (NOME, DATA_INICIAL, DATA_FINAL, OBSERVACOES) VALUES ('Jon Doe', TIMESTAMP WITH TIME ZONE '2023-01-01T20:20:00', TIMESTAMP WITH TIME ZONE '2023-10-10T20:20:00', 'Observacao exemplo de teste');

insert into tb_propriedades(pessoa_id, nome_propriedade) values (1, 'Propriedade Teste');

insert into tb_pessoa_propriedade(pessoa_id, propriedade_id) values (1,1);

insert into tb_laboratorio(pessoa_id, nome_laboratorio) values(1, 'Laboratorio Teste');

insert into tb_pessoa_lab(pessoa_id, laboratorio_id) values (1,1);