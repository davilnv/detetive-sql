-- Dropa tabelas anteriores
DROP TABLE IF EXISTS Acesso_Camera;
DROP TABLE IF EXISTS Camera;
DROP TABLE IF EXISTS Registro_Exclusao;
DROP TABLE IF EXISTS Item_Museu;
DROP TABLE IF EXISTS Acesso;
DROP TABLE IF EXISTS Usuario;

-- Cria as tabelas do banco de dados

/****************************************************************************************/
/******************************** TABELA USUARIO ***************************************/
/****************************************************************************************/

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
       ('lucy', 'lucy#123', 'Lucy Bennett', 'lucy@email.com', 'Visitante', TRUE,
        DATEADD('DAY', -FLOOR(RAND() * 365), CURRENT_DATE())),
       ('isa', 'isa#123', 'Isabella Kensington', 'isabella@email.com', 'Visitante', TRUE,
        DATEADD('DAY', -FLOOR(RAND() * 365), CURRENT_DATE())),
       ('detetive', '123', 'Detetive', 'detetive@email.com', 'Detetive', TRUE,
        CURRENT_DATE());

/****************************************************************************************/
/******************************** TABELA ACESSO ****************************************/
/****************************************************************************************/

CREATE TABLE IF NOT EXISTS Acesso
(
    usuario_codigo INTEGER,
    data_acesso    TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    data_saida     TIMESTAMP,
    FOREIGN KEY (usuario_codigo) REFERENCES Usuario (codigo)
);

-- Insere dados na tabela Acesso com a data de acesso para todos os usuários
INSERT INTO Acesso (usuario_codigo, data_acesso)
SELECT codigo, DATEADD('DAY', -FLOOR(RAND() * DATEDIFF('DAY', DATA_CADASTRO, CURRENT_TIMESTAMP)), CURRENT_TIMESTAMP())
FROM Usuario;

-- Create a temporary table to store the randomly selected user codes
CREATE TABLE UsuariosAleatorios (codigo INTEGER);

-- Insert the randomly selected user codes into the temporary table
INSERT INTO UsuariosAleatorios
SELECT codigo
FROM Usuario
WHERE login <> 'detetive'
ORDER BY RAND()
LIMIT 5;

-- Update the access date of the randomly selected users
UPDATE Acesso
SET data_acesso = DATEADD('HOUR', -FLOOR(RAND() * 24), CURRENT_TIMESTAMP())
WHERE usuario_codigo IN (SELECT codigo FROM UsuariosAleatorios);

-- Drop the temporary table
DROP TABLE UsuariosAleatorios;

-- Atualiza a data de acesso do detetive para a data atual
UPDATE Acesso
SET data_acesso = CURRENT_TIMESTAMP()
WHERE usuario_codigo = (SELECT codigo FROM Usuario WHERE login = 'detetive');

-- Atualiza a tabela Acesso para adicionar a data de saída
UPDATE Acesso
SET data_saida = DATEADD('HOUR', FLOOR(RAND() * 24) + 1, data_acesso)
WHERE data_saida IS NULL;


/****************************************************************************************/
/************************** *TABELA CAMERA ********************************************/
/****************************************************************************************/

CREATE TABLE IF NOT EXISTS Camera
(
    codigo_camera    INTEGER PRIMARY KEY AUTO_INCREMENT,
    descricao        VARCHAR(255) NOT NULL,
    localizacao      VARCHAR(255) NOT NULL
);

INSERT INTO Camera (descricao, localizacao)
VALUES ('Câmera 1', 'Sala de TI'),
       ('Câmera 2', 'Sala da Diretoria'),
       ('Câmera 3', 'Salão principal'),
       ('Câmera 4', 'Sala de exposições 1'),
       ('Câmera 5', 'Sala de exposições 2');

/****************************************************************************************/
/************************** TABELA ACESSO_CAMERA *************************************/
/****************************************************************************************/

CREATE TABLE IF NOT EXISTS Acesso_Camera
(
    usuario_acesso    INTEGER,
    codigo_camera    INTEGER,
    usuario_autorizacao INTEGER,
    data_acesso    TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    FOREIGN KEY (usuario_acesso) REFERENCES Usuario (codigo),
    FOREIGN KEY (codigo_camera) REFERENCES Camera (codigo_camera),
    FOREIGN KEY (usuario_autorizacao) REFERENCES Usuario (codigo)
);

-- Insere os usuários que acessaram o sistema nas últimas 24 horas na tabela Acesso_Camera
INSERT INTO Acesso_Camera (usuario_acesso, codigo_camera, data_acesso, usuario_autorizacao)
SELECT
    USU.CODIGO,
    CAM.CODIGO_CAMERA,
    ACE.DATA_ACESSO,
    CASE
        WHEN ROW_NUMBER() OVER (ORDER BY ACE.DATA_ACESSO DESC) <= 3 AND CAM.CODIGO_CAMERA = 3 THEN NULL
        ELSE (SELECT CODIGO FROM USUARIO WHERE CODIGO <> USU.CODIGO ORDER BY RAND() LIMIT 1)
        END
FROM ACESSO ACE
         INNER JOIN USUARIO USU ON ACE.USUARIO_CODIGO = USU.CODIGO
         INNER JOIN Camera CAM ON CAM.CODIGO_CAMERA = 3
WHERE ACE.DATA_ACESSO >= CURRENT_TIMESTAMP - INTERVAL '24' HOUR
  AND USU.LOGIN <> 'detetive'
ORDER BY ACE.DATA_ACESSO DESC
LIMIT 5;

/****************************************************************************************/
/**************************** TABELA ITEM_MUSEU ***************************************/
/****************************************************************************************/

-- Cria a tabela Item_Museu
CREATE TABLE IF NOT EXISTS Item_Museu
(
    codigo_item        INTEGER PRIMARY KEY AUTO_INCREMENT,
    descricao          VARCHAR(255) NOT NULL,
    data_cadastro      TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    usuario_cadastro   INTEGER,
    usuario_alteracao  INTEGER,
    FOREIGN KEY (usuario_cadastro) REFERENCES Usuario (codigo),
    FOREIGN KEY (usuario_alteracao) REFERENCES Usuario (codigo)
);

/****************************************************************************************/
/************************ TABELA REGISTRO_EXCLUSAO ***********************************/
/****************************************************************************************/

-- Cria a tabela Registro_Exclusao
CREATE TABLE IF NOT EXISTS Registro_Exclusao
(
    codigo_registro    INTEGER PRIMARY KEY AUTO_INCREMENT,
    codigo_item        INTEGER,
    data_exclusao      TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    usuario_exclusao   INTEGER,
    FOREIGN KEY (codigo_item) REFERENCES Item_Museu (codigo_item),
    FOREIGN KEY (usuario_exclusao) REFERENCES Usuario (codigo)
);

