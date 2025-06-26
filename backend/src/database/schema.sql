CREATE TABLE professor (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    titulacao VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    lattes VARCHAR(255),
    orcid VARCHAR(100),
    bio TEXT,
    foto VARCHAR(255),
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255),
    perfil VARCHAR(50) NOT NULL,
    google_id VARCHAR(100) UNIQUE
);

CREATE TABLE projeto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    objetivo TEXT NOT NULL,
    idealizadores VARCHAR(255),
    data_criacao DATE NOT NULL,
    local_criacao VARCHAR(255),
    detalhes TEXT,
    imagem VARCHAR(255),
    professor_id BIGINT NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

CREATE TABLE publicacao (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    url VARCHAR(255),
    professor_id BIGINT NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

CREATE TABLE hobby (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    professor_id BIGINT NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

CREATE TABLE area_pesquisa (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    professor_id BIGINT NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

CREATE TABLE mensagem_contato (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email_remetente VARCHAR(150) NOT NULL,
    tipo_mensagem VARCHAR(50) NOT NULL,
    assunto VARCHAR(255),
    mensagem TEXT NOT NULL,
    data_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
