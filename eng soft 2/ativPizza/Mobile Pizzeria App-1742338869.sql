CREATE TABLE [Pizzas] (
	[pizza_id] int NOT NULL,
	[name] nvarchar(max) NOT NULL DEFAULT '100',
	[size] nvarchar(max) NOT NULL DEFAULT '10',
	[price] decimal(18,0) NOT NULL,
	[description] nvarchar(max),
	PRIMARY KEY ([pizza_id])
);

CREATE TABLE [Clientes] (
	[cliente_id] int NOT NULL,
	[nome] nvarchar(max) NOT NULL DEFAULT '100',
	[email] nvarchar(max) NOT NULL DEFAULT '100',
	[telefone] nvarchar(max) NOT NULL DEFAULT '15',
	[endereco] nvarchar(max) NOT NULL DEFAULT '255',
	PRIMARY KEY ([cliente_id])
);

CREATE TABLE [Pedidos] (
	[pedido_id] int NOT NULL,
	[cliente_id] int NOT NULL,
	[data_pedido] datetime NOT NULL,
	[valor_total] decimal(18,0) NOT NULL,
	[status] nvarchar(max) NOT NULL DEFAULT '20',
	PRIMARY KEY ([pedido_id])
);

CREATE TABLE [Itens_Pedido] (
	[quantidade] int NOT NULL,
	[saches_ketchup] bit NOT NULL,
	[pedido_id] int NOT NULL,
	[pizza_id] int NOT NULL,
	[bebida_id] int NOT NULL
);

CREATE TABLE [Pagamentos] (
	[metodo_pagamento] nvarchar(max) NOT NULL DEFAULT '50',
	[pagamento_id] int NOT NULL,
	[pedido_id] int NOT NULL,
	[data_pagamento] datetime NOT NULL,
	[valor] decimal(18,0) NOT NULL,
	PRIMARY KEY ([pagamento_id])
);

CREATE TABLE [Entregas] (
	[entrega_id] int NOT NULL,
	[pedido_id] int NOT NULL,
	[endereco_entrega] nvarchar(max) NOT NULL DEFAULT '255',
	[hora_entrega] datetime NOT NULL,
	[status_entrega] nvarchar(max) NOT NULL DEFAULT '20',
	PRIMARY KEY ([entrega_id])
);

CREATE TABLE [Bebidas] (
	[bebida_id] int NOT NULL,
	[nome] nvarchar(max) DEFAULT '100',
	[tamanho] nvarchar(max) DEFAULT '10',
	[preco] decimal(18,0) NOT NULL,
	[calorias] int,
	PRIMARY KEY ([bebida_id])
);



ALTER TABLE [Pedidos] ADD CONSTRAINT [Pedidos_fk1] FOREIGN KEY ([cliente_id]) REFERENCES [Clientes]([cliente_id]);
ALTER TABLE [Itens_Pedido] ADD CONSTRAINT [Itens_Pedido_fk2] FOREIGN KEY ([pedido_id]) REFERENCES [Pedidos]([pedido_id]);

ALTER TABLE [Itens_Pedido] ADD CONSTRAINT [Itens_Pedido_fk3] FOREIGN KEY ([pizza_id]) REFERENCES [Pizzas]([pizza_id]);

ALTER TABLE [Itens_Pedido] ADD CONSTRAINT [Itens_Pedido_fk4] FOREIGN KEY ([bebida_id]) REFERENCES [Bebidas]([bebida_id]);
ALTER TABLE [Pagamentos] ADD CONSTRAINT [Pagamentos_fk2] FOREIGN KEY ([pedido_id]) REFERENCES [Pedidos]([pedido_id]);
ALTER TABLE [Entregas] ADD CONSTRAINT [Entregas_fk1] FOREIGN KEY ([pedido_id]) REFERENCES [Pedidos]([pedido_id]);
