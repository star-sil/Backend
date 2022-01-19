drop table if exists Stable CASCADE;
create table stable(
    id BIGINT not null auto_increment primary key,
    bike SMALLINT not null,
    stat CHAR(2) not null,
    soc TINYINT not null,
    volt DECIMAL(3, 1),
    temp TINYINT not null,
    lat DECIMAL(8, 5) not null,
    lon DECIMAL(8, 5) not null,
    pow BOOLEAN not null,
    shoc BOOLEAN not null
);