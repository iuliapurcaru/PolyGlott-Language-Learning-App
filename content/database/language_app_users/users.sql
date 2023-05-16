create table users
(
    username   varchar(255)  not null
        primary key,
    email      varchar(255)  not null,
    password   varchar(255)  not null,
    user_level int default 0 not null
);

