create table hall (
    hall_id integer not null auto_increment,
    hall_name varchar(20) not null,
    hall_capacity integer not null,
    hall_row integer not null,
    hall_column integer not null,

    primary key (hall_id)
)