create table usuario(
	id_usuario bigint primary key auto_increment,
	nome_usuario varchar(100) not null unique,
	data_nascimento date default '0001-01-01',
	senha varchar (50) not null
);

create table fornecedor(
	id_fornecedor bigint primary key auto_increment,
    nome_fornecedor varchar(100) not null unique,
    endereco_fornecedor varchar(200),
    cidade varchar(100),
    bairro varchar(100),
    cep varchar(50),
    uf varchar (2),
    tel int not null,
    cnpj varchar(100)
);

create table cliente(
	id_cliente bigint primary key auto_increment,
    nome_cliente varchar (200) not null,
    endereco_cliente varchar(200) not null,
    cidade varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(50) not null,
    uf varchar (2) not null,
    tel int not null,
    cpf varchar(100)
);

create table produto(
	id_produto bigint primary key auto_increment,
    nome_produto varchar(100) not null unique,
    marca_produto varchar(100),
    preco_produto decimal(10,2) not null,
    lote int,
    id_fornecedor bigint not null,
    quantidade int not null,
    constraint fk_id_fornecedor foreign key (id_fornecedor) references fornecedor(id_fornecedor)
);

create table entrada(
	id_entrada bigint primary key auto_increment,
    data_entrada datetime default '0001-01-01 00:00:00' not null,
    id_produto bigint not null,
    nome_produto varchar(150) not null,
    id_fornecedor bigint not null,
    id_usuario bigint not null,
    preco_produto decimal (10,2) not null,
    quantidade int not null,
    lote int not null,
    constraint fk_id_produto foreign key (id_produto) references produto(id_produto),
    constraint fk_nome_produto foreign key (nome_produto) references produto(nome_produto),
    constraint fk_id_fornecedor foreign key (id_fornecedor) references fornecedor(id_fornecedor),
    constraint fk_id_usuario foreign key (id_usuario) references usuario(id_usuario),
    constraint fk_preco_produto foreign key (preco_produto) references produto(preco_produto),
    constraint fk_lote foreign key (lote) references produto(lote),
    constraint fk_quantidade foreign key (quantidade) references produto(quantidade)
);

create table saida(
	id_saida bigint primary key auto_increment,
    data_saida datetime default '0001-01-01 00:00:00',
    preco_saida decimal (10,2) not null,
    id_produto bigint not null,
    nome_produto varchar(150) not null,
    id_cliente bigint not null,
    nome_cliente varchar(150) not null,
    quantidade int not null,
    id_usuario bigint not null,
    lote int not null,
	constraint fk_id_produto foreign key (id_produto) references produto(id_produto),
    constraint fk_nome_produto foreign key (nome_produto) references produto(nome_produto),
    constraint fk_id_cliente foreign key (id_cliente) references cliente(id_cliente),
    constraint fk_nome_cliente foreign key (nome_cliente) references cliente(nome_cliente),
    constraint fk_id_usuario foreign key (id_usuario) references usuario(id_usuario),
    constraint fk_lote foreign key (lote) references produto(lote),
    constraint fk_quantidade foreign key (quantidade) references produto(quantidade)
);