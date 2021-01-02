create table [user] (
       id bigint identity not null,
        email varchar(255) not null,
        password varchar(255) not null,
        role varchar(255) not null,
        primary key (id)
    )