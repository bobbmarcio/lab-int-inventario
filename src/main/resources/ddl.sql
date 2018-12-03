CREATE TABLE IF NOT EXISTS unidade (
	id       BIGSERIAL PRIMARY KEY,
	nome     TEXT,
	endereco TEXT,
	cidade   TEXT,
	uf       VARCHAR(2),
	cep      TEXT
);
 CREATE TABLE IF NOT EXISTS predio (
	id         BIGSERIAL PRIMARY KEY,
	nome       TEXT,
	unidade_id BIGINT REFERENCES unidade
);
 CREATE TABLE IF NOT EXISTS departamento (
	id   BIGSERIAL PRIMARY KEY,
	nome TEXT
);
 CREATE TABLE IF NOT EXISTS sala (
	id              BIGSERIAL PRIMARY KEY,
	nome            TEXT,
	codigo          TEXT,
	predio_id       BIGINT REFERENCES predio,
	departamento_id BIGINT REFERENCES departamento
);
 CREATE TABLE IF NOT EXISTS usuario (
	id      BIGSERIAL PRIMARY KEY,
	nome    TEXT,
	email   TEXT,
	senha   TEXT,
	sala_id BIGINT REFERENCES sala
);
 CREATE TABLE IF NOT EXISTS usuario_papel (
	usuario_id BIGINT REFERENCES usuario,
	papel      VARCHAR(50),
	PRIMARY KEY (usuario_id, papel)
);
 CREATE TABLE IF NOT EXISTS bem_patrimonial (
	id                   BIGSERIAL PRIMARY KEY,
	data_aquisicao       DATE,
	numero_tombamento    TEXT,
	numero_nota_fiscal   TEXT,
	vida_util            BIGINT,
	especificacao        TEXT,
	data_garantia        DATE,
	marca                TEXT,
	grupo                VARCHAR(50),
	valor_compra         NUMERIC(20, 2),
	incorporado          BOOLEAN,
	localizacao_atual_id BIGINT REFERENCES sala
);
 CREATE TABLE IF NOT EXISTS baixa (
	id                 BIGSERIAL PRIMARY KEY,
	"data"             DATE,
	comentario         TEXT,
	bem_patrimonial_id BIGINT REFERENCES bem_patrimonial
);
 CREATE TABLE IF NOT EXISTS movimentacao (
	id                     BIGSERIAL PRIMARY KEY,
	origem_id              BIGINT REFERENCES sala,
	destion_id             BIGINT REFERENCES sala,
	autorizador_saida_id   BIGINT REFERENCES usuario,
	autorizador_entrada_id BIGINT REFERENCES usuario,
	situacao               VARCHAR(50)
);
 CREATE TABLE IF NOT EXISTS cancelamento (
	id              BIGSERIAL PRIMARY KEY,
	"data"          DATE,
	motivo          TEXT,
	movimentacao_id BIGINT REFERENCES movimentacao
);
 CREATE TABLE IF NOT EXISTS ordem_servico (
	id               BIGSERIAL PRIMARY KEY,
	data_criacao     DATE,
	situacao         VARCHAR(50),
	inicio_servico   DATE,
	fim_servico      DATE,
	responsavel_id   BIGINT REFERENCES usuario,
	ordem_servico_id BIGINT REFERENCES ordem_servico
);
ALTER TABLE departamento
ADD IF NOT EXISTS chefe BIGINT REFERENCES usuario;
ALTER TABLE ordem_servico
ADD IF NOT EXISTS bem_patrimonial_id BIGINT REFERENCES bem_patrimonial;
INSERT INTO usuario_papel (usuario_id, papel)
SELECT * FROM (SELECT 1, 'admin') AS tmp
WHERE NOT EXISTS (
    SELECT usuario_id FROM usuario_papel WHERE usuario_id = 1
) LIMIT 1;
INSERT INTO usuario (id, nome, email, senha, sala_id)
SELECT * FROM (SELECT 1, 'admin', 'admin@admin.com', 'admin', 1) AS tmp
WHERE NOT EXISTS (
    SELECT nome FROM usuario WHERE nome = 'admin'
) LIMIT 1;
