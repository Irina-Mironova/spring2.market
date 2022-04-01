create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       numeric(8,2) not null,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);


insert into products (title, price)
values ('Горбуша', 250.25),
       ('Кета', 300.10),
       ('Сыр Гауда', 210.00),
       ('Сыр Голландский', 160.00),
       ('Дыня', 100.00),
       ('Арбуз', 55.00),
       ('Слива', 280.00),
       ('Ананас',100.00);


create table orders
(
    id          bigserial primary key,
    username    varchar(255) not null,
    total_price numeric(8,2) not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product numeric(8,2) not null,
    price             numeric(8,2) not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);




