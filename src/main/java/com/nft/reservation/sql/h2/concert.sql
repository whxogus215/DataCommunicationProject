create table concert (
                         concert_id integer not null auto_increment,
                         concert_title varchar(45) not null,
                         concert_date timestamp,
                         running_time integer,
                         concert_place varchar(50),
                         rank_id integer,
                         hall_id integer,
                         primary key (concert_id),
                         foreign key (rank_id)
                             references rank(rank_id) on update cascade,
                         foreign key (hall_id)
                             references hall(hall_id) on update cascade on delete set null
)