-- Dropa tabelas anteriores
DROP TABLE IF EXISTS Acesso;
DROP TABLE IF EXISTS Usuario;

-- Cria as tabelas do banco de dados
CREATE TABLE IF NOT EXISTS Usuario
(
    codigo        INTEGER PRIMARY KEY AUTO_INCREMENT,
    login         VARCHAR(255) NOT NULL UNIQUE,
    senha         VARCHAR(255) NOT NULL,
    nome          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    cargo         VARCHAR(255),
    ativo         BOOLEAN   DEFAULT TRUE,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE IF NOT EXISTS Acesso
(
    usuario_codigo INTEGER,
    data_acesso    TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    data_saida     TIMESTAMP,
    FOREIGN KEY (usuario_codigo) REFERENCES Usuario (codigo)
);

-- Insere dados na tabela Usuario
INSERT INTO Usuario (login, senha, nome, email, cargo, ativo, data_cadastro)
VALUES ('sophie', 'ss#123', 'Sophie Campbell', 'sophie.ti@email.com', 'Suporte TI', TRUE,
        DATEADD('DAY', -FLOOR(RAND() * 365), CURRENT_DATE())),
       ('alex', 'aa#123', 'Alexander Kensington', 'alex.ken@email.com', 'Diretor', TRUE,
        DATEADD('DAY', -FLOOR(RAND() * 365), CURRENT_DATE())),
       ('eleanor', 'ee#123', 'Eleanor Thornton', 'elea.thorn.chief@email.com', 'Curadora Chefe', TRUE,
        DATEADD('DAY', -FLOOR(RAND() * 365), CURRENT_DATE())),
       ('william', 'ww#123', 'William Smith', 'will.smith@email.com', 'Segurança', TRUE,
        DATEADD('DAY', -FLOOR(RAND() * 365), CURRENT_DATE())),
       ('james', 'jj#123', 'James Turner', 'turner@email.com', 'Zelador', TRUE,
        DATEADD('DAY', -FLOOR(RAND() * 365), CURRENT_DATE())),
       ('detetive', '123', 'Detetive', 'detetive@email.com', 'Detetive', TRUE,
        CURRENT_DATE());

-- Insere dados na tabela Acesso com a data de acesso
INSERT INTO Acesso (usuario_codigo, data_acesso)
SELECT codigo, DATEADD('HOUR', FLOOR(RAND() * 24), data_cadastro)
FROM Usuario;

-- Atualiza a tabela Acesso para adicionar a data de saída
UPDATE Acesso
SET data_saida = DATEADD('HOUR', FLOOR(RAND() * 24) + 1, data_acesso)
WHERE data_saida IS NULL;