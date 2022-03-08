create table users
(
   id  bigserial primary key,
   username varchar(30) not null unique,
   password varchar(80) not null,
   first_name varchar(30),
   last_name varchar(30),
   email varchar(255),
   created_at timestamp default current_timestamp,
   updated_at timestamp default current_timestamp
);


create table roles
(
  id bigserial primary key,
  name varchar(50) not null
);

create table users_roles
(
  user_id bigint,
  role_id bigint,
  primary key(user_id, role_id),
  foreign key (user_id) references users(id),
  foreign key (role_id) references roles(id)
);

insert into roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users(username, password, first_name, last_name, email)
values ('bob', '$2a$12$uY2t0/L2eQ4cCiyCuQ36FOT7S3e/jzTte.aIVXYkD6HqPG6wTl826', 'Борис', 'Иванов', 'bob123@mail.ru'),
       ('ali', '$2a$12$MsuaCpjj2DVyukZmKPdbPOnLvZ.b712lGz.yjT2522w9RGCYtgBs6', 'Александр','Сергеев','ali1234@mail.ru');

insert into users_roles (user_id, role_id)
values  (1, 2),
        (2, 1);
