-- Apaga todas as tabelas com segurança
DROP TABLE IF EXISTS mensagem_contato;
DROP TABLE IF EXISTS area_pesquisa;
DROP TABLE IF EXISTS hobby;
DROP TABLE IF EXISTS publicacao;
DROP TABLE IF EXISTS projeto;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS professor;

-- Cria novamente
SOURCE schema.sql;
SOURCE seed.sql;
