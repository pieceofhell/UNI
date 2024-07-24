USE `mydb`;

INSERT INTO
    `Universidade` (`idUniversidade`, `Nome`)
VALUES
    (6, 'Universidade Federal de Minas Gerais'),
    (
        7,
        'Pontifícia Universidade Católica de Minas Gerais'
    ),
    (8, 'Universidade de São Paulo'),
    (9, 'Universidade Estadual de Campinas'),
    (10, 'Universidade Federal do Rio de Janeiro');

INSERT INTO
    `Biblioteca` (
        `idBiblioteca`,
        `Nome`,
        `Endereco`,
        `universidade_id`
    )
VALUES
    (
        6,
        'Biblioteca Central da UFMG',
        'Av. Antônio Carlos, 6627',
        6
    ),
    (
        7,
        'Biblioteca PUC Minas',
        'Rua Dom José Gaspar, 500',
        7
    ),
    (
        8,
        'Biblioteca da USP',
        'Av. Prof. Luciano Gualberto, 71',
        8
    ),
    (
        9,
        'Biblioteca da Unicamp',
        'Rua Sérgio Buarque de Holanda, 251',
        9
    ),
    (
        10,
        'Biblioteca Central da UFRJ',
        'Av. Pedro Calmon, 550',
        10
    );

INSERT INTO
    `Funcionario` (
        `CPF`,
        `biblioteca_id`,
        `universidade_id`,
        `Nome`
    )
VALUES
    ('89012345678', 6, 6, 'Carlos Henrique'),
    ('90123456789', 7, 7, 'Mariana Santos'),
    ('01234567890', 8, 8, 'Fernando Almeida'),
    ('12345678901', 9, 9, 'Ana Clara'),
    ('23456789012', 10, 10, 'João Pedro');

INSERT INTO
    `Livro` (
        `idLivro`,
        `dataPublicacao`,
        `Preco`,
        `Genero`,
        `Titulo`,
        `Autor`
    )
VALUES
    (
        6,
        '2017-07-15',
        55.90,
        'Literatura',
        'O Senhor dos Anéis',
        'J.R.R. Tolkien'
    ),
    (
        7,
        '2019-10-23',
        60.00,
        'História',
        'Sapiens: Uma Breve História da Humanidade',
        'Yuval Noah Harari'
    ),
    (
        8,
        '2020-05-18',
        45.50,
        'Ciência',
        'Breves Respostas para Grandes Questões',
        'Stephen Hawking'
    ),
    (
        9,
        '2021-11-30',
        50.00,
        'Tecnologia',
        'O Código Da Vinci',
        'Dan Brown'
    ),
    (
        10,
        '2022-03-10',
        35.00,
        'Matemática',
        'O Universo numa Casca de Noz',
        'Stephen Hawking'
    );

INSERT INTO
    `Usuario` (`CPF`, `Nome`, `Telefone`, `Endereco`)
VALUES
    (
        '34567890123',
        'Lucas Silva',
        '(31) 91234-5678',
        'Rua da Paz, 101'
    ),
    (
        '45678901234',
        'Maria Fernanda',
        '(21) 92345-6789',
        'Av. dos Andradas, 202'
    ),
    (
        '56789012345',
        'José Ricardo',
        '(11) 93456-7890',
        'Rua XV de Novembro, 303'
    ),
    (
        '67890123456',
        'Paula Martins',
        '(19) 94567-8901',
        'Av. Paulista, 404'
    ),
    (
        '78901234567',
        'Renata Souza',
        '(51) 95678-9012',
        'Rua das Flores, 505'
    );

INSERT INTO
    `Compra` (
        `dataCompra`,
        `CPF_Funcionario`,
        `livro_id`,
        `CPF_Usuario`
    )
VALUES
    ('2023-05-01', '89012345678', 6, '34567890123'),
    ('2023-06-15', '90123456789', 7, '45678901234'),
    ('2023-07-20', '01234567890', 8, '56789012345'),
    ('2023-08-25', '12345678901', 9, '67890123456'),
    ('2023-09-30', '23456789012', 10, '78901234567');

INSERT INTO
    `Emprestimo` (
        `CPF_Funcionario`,
        `CPF_Usuario`,
        `livro_id`,
        `dataLocacao`,
        `dataDevolucao`,
        `valorPagar`
    )
VALUES
    (
        '89012345678',
        '34567890123',
        6,
        '2023-05-01',
        '2023-05-15',
        5.00
    ),
    (
        '90123456789',
        '45678901234',
        7,
        '2023-06-15',
        '2023-06-30',
        7.00
    ),
    (
        '01234567890',
        '56789012345',
        8,
        '2023-07-20',
        '2023-08-03',
        6.00
    ),
    (
        '12345678901',
        '67890123456',
        9,
        '2023-08-25',
        '2023-09-10',
        8.00
    ),
    (
        '23456789012',
        '78901234567',
        10,
        '2023-09-30',
        '2023-10-15',
        9.00
    );

INSERT INTO
    `Personagem` (`idPersonagem`, `Nome`, `Sexo`)
VALUES
    (6, 'Alice', 'F'),
    (7, 'Bob', 'M'),
    (8, 'Clara', 'F'),
    (9, 'Daniel', 'M'),
    (10, 'Eva', 'F');

INSERT INTO
    `LivrosEmBibliotecas` (`livro_id`, `biblioteca_id`, `Quantidade`)
VALUES
    (6, 6, 5),
    (7, 7, 4),
    (8, 8, 6),
    (9, 9, 3),
    (10, 10, 7);

INSERT INTO
    `PersonagensEmLivros` (`livro_id`, `personagem_id`)
VALUES
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10);

USE `mydb`;

INSERT INTO
    `Universidade` (`idUniversidade`, `Nome`)
VALUES
    (11, 'Universidade Federal do Paraná'),
    (12, 'Universidade Federal da Bahia'),
    (13, 'Universidade Federal do Ceará'),
    (14, 'Universidade Federal de Santa Catarina'),
    (15, 'Universidade Estadual de Londrina');

INSERT INTO
    `Biblioteca` (
        `idBiblioteca`,
        `Nome`,
        `Endereco`,
        `universidade_id`
    )
VALUES
    (
        11,
        'Biblioteca Central da UFPR',
        'Rua General Carneiro, 460',
        11
    ),
    (
        12,
        'Biblioteca Central da UFBA',
        'Rua Barão de Jeremoabo, 147',
        12
    ),
    (
        13,
        'Biblioteca Universitária da UFC',
        'Av. da Universidade, 2853',
        13
    ),
    (
        14,
        'Biblioteca Universitária da UFSC',
        'Campus Reitor João David Ferreira Lima',
        14
    ),
    (
        15,
        'Biblioteca Central da UEL',
        'Rodovia Celso Garcia Cid, PR 445',
        15
    );

INSERT INTO
    `Funcionario` (
        `CPF`,
        `biblioteca_id`,
        `universidade_id`,
        `Nome`
    )
VALUES
    ('34567890122', 11, 11, 'Laura Costa'),
    ('45678901233', 12, 12, 'Gabriel Nunes'),
    ('56789012344', 13, 13, 'Eduardo Lima'),
    ('67890123455', 14, 14, 'Juliana Rocha'),
    ('78901234566', 15, 15, 'Matheus Oliveira');

INSERT INTO
    `Livro` (
        `idLivro`,
        `dataPublicacao`,
        `Preco`,
        `Genero`,
        `Titulo`,
        `Autor`
    )
VALUES
    (
        11,
        '2018-01-12',
        40.00,
        'Ficção',
        '1984',
        'George Orwell'
    ),
    (
        12,
        '2020-08-05',
        30.50,
        'Romance',
        'Orgulho e Preconceito',
        'Jane Austen'
    ),
    (
        13,
        '2015-03-15',
        75.00,
        'História',
        'A História do Brasil',
        'Boris Fausto'
    ),
    (
        14,
        '2019-11-10',
        25.00,
        'Ciência',
        'Uma Breve História do Tempo',
        'Stephen Hawking'
    ),
    (
        15,
        '2021-06-30',
        80.00,
        'Filosofia',
        'O Mundo de Sofia',
        'Jostein Gaarder'
    );

INSERT INTO
    `Usuario` (`CPF`, `Nome`, `Telefone`, `Endereco`)
VALUES
    (
        '89012345677',
        'Antônio Pereira',
        '(41) 98765-4321',
        'Rua XV de Novembro, 123'
    ),
    (
        '90123456788',
        'Camila Ramos',
        '(71) 97654-3210',
        'Av. Sete de Setembro, 456'
    ),
    (
        '01234567899',
        'Rafael Azevedo',
        '(85) 96543-2109',
        'Rua Coronel Linhares, 789'
    ),
    (
        '12345678900',
        'Fernanda Carvalho',
        '(48) 95432-1098',
        'Rua Lauro Linhares, 1011'
    ),
    (
        '23456789011',
        'Thiago Martins',
        '(43) 94321-0987',
        'Av. Higienópolis, 1213'
    );

INSERT INTO
    `Compra` (
        `dataCompra`,
        `CPF_Funcionario`,
        `livro_id`,
        `CPF_Usuario`
    )
VALUES
    ('2023-10-01', '34567890122', 11, '89012345677'),
    ('2023-10-15', '45678901233', 12, '90123456788'),
    ('2023-11-20', '56789012344', 13, '01234567899'),
    ('2023-12-25', '67890123455', 14, '12345678900'),
    ('2024-01-05', '78901234566', 15, '23456789011');

INSERT INTO
    `Emprestimo` (
        `CPF_Funcionario`,
        `CPF_Usuario`,
        `livro_id`,
        `dataLocacao`,
        `dataDevolucao`,
        `valorPagar`
    )
VALUES
    (
        '34567890122',
        '89012345677',
        11,
        '2023-10-01',
        '2023-10-15',
        4.00
    ),
    (
        '45678901233',
        '90123456788',
        12,
        '2023-10-15',
        '2023-10-30',
        3.00
    ),
    (
        '56789012344',
        '01234567899',
        13,
        '2023-11-20',
        '2023-12-05',
        7.50
    ),
    (
        '67890123455',
        '12345678900',
        14,
        '2023-12-25',
        '2024-01-10',
        2.50
    ),
    (
        '78901234566',
        '23456789011',
        15,
        '2024-01-05',
        '2024-01-20',
        8.00
    );

INSERT INTO
    `Personagem` (`idPersonagem`, `Nome`, `Sexo`)
VALUES
    (11, 'Greg', 'M'),
    (12, 'Hannah', 'F'),
    (13, 'Isabel', 'F'),
    (14, 'Jack', 'M'),
    (15, 'Kate', 'F');

INSERT INTO
    `LivrosEmBibliotecas` (`livro_id`, `biblioteca_id`, `Quantidade`)
VALUES
    (11, 11, 2),
    (12, 12, 3),
    (13, 13, 4),
    (14, 14, 5),
    (15, 15, 6);

INSERT INTO
    `PersonagensEmLivros` (`livro_id`, `personagem_id`)
VALUES
    (11, 11),
    (12, 12),
    (13, 13),
    (14, 14),
    (15, 15);