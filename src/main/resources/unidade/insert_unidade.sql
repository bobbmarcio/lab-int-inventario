INSERT INTO unidade (id, nome, endereco, cidade, uf, cep)
VALUES ((SELECT nextval('ordem_servico_seq')), ?, ?, ?, ?, ?)
