INSERT INTO sala (id, nome, codigo, predio_id, departamento_id)
VALUES ((SELECT nextval('sala_id_seq')), ?, ?, ?, ?)
