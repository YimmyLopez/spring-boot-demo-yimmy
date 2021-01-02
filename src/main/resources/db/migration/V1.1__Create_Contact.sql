 create table contact (
       id bigint identity not null,
        email varchar(255) not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        status varchar(255) not null,
        company_id bigint,
        primary key (id)
    )

