insert into produto (id, nome, preco, dataCriacao, descricao) values (1, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto (id, nome, preco, dataCriacao, descricao) values (3, 'Câmera GoPro Hero 7', 1400.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');

insert into cliente (id, nome) values (1, 'John Doe');
insert into cliente (id, nome) values (2, 'Roger Rabbit');

insert into pedido (id, cliente_id, data_criacao, total, status) values(1, 1, sysdate(), 998.0, 'AGUARDANDO');
insert into pedido (id, cliente_id, data_criacao, total, status) values(2, 1, sysdate(), 499.0, 'AGUARDANDO');

insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 499, 2);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499, 1);

insert into pagamento (pedido_id, status, numero_cartao, tipo_pagamento) values(2, 'PROCESSANDO', '123', 'cartao');
insert into categoria (id, nome) values (1, 'Eletrônicos');