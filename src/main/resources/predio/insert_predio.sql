INSERT INTO predio (id, nome, unidade_id)
VALUES ((SELECT nextval('predio_id_seq')), ?, ?)
