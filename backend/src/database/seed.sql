-- Professor padrão
INSERT INTO professor (nome, titulacao, email, lattes, orcid, bio, foto)
VALUES (
    'Dr. João Silva',
    'PhD em Ciência da Computação',
    'joao.silva@universidade.edu',
    'http://lattes.cnpq.br/1234567890',
    '0000-0001-2345-6789',
    'Professor com experiência em IA, segurança da informação e sistemas web.',
    '/uploads/fotos/joao-silva.jpg'
);

-- Usuário administrador
INSERT INTO usuario (nome, email, senha, perfil)
VALUES (
    'Administrador',
    'admin@siteprofessor.com',
    '$2a$10$6TbjoRuOsayV63dAIbldSeJ.PiMvKFVzEcZUm.RLbCz2NiswNHcR6'
    'ADMINISTRADOR'
);

-- Áreas de pesquisa
INSERT INTO area_pesquisa (nome, descricao, professor_id) VALUES
('Inteligência Artificial', 'Estudo de algoritmos de aprendizado de máquina.', 1),
('Segurança da Informação', 'Criptografia, autenticação e privacidade.', 1);

-- Projeto
INSERT INTO projeto (titulo, objetivo, idealizadores, data_criacao, local_criacao, detalhes, imagem, professor_id)
VALUES (
    'Análise de Algoritmos de IA',
    'Comparar algoritmos de machine learning.',
    'João Silva, Aluno X',
    '2023-01-15',
    'Laboratório 3',
    'Análise estatística dos resultados em datasets reais.',
    '/uploads/projetos/algoritmos.jpg',
    1
); 
 
INSERT INTO usuario (nome, email, senha, perfil)
VALUES (
    'Administrador',
    'admin@siteprofessor.com',
    '$2a$10$6TbjoRuOsayV63dAIbldSeJ.PiMvKFVzEcZUm.RLbCz2NiswNHcR6',
    'ADMINISTRADOR'
);
