create schema if not exists shopping;

create table shopping.shop (
    id bigserial primary key,
    user_identifier varchar(100) not null,
    data timestamp not null,
    total float not null
);

create table shopping.item (
    shop_id bigserial references shopping.shop(id),
    product_identifier varchar(100) not null,
    preco float not null
);
