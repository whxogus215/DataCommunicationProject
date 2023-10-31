create table seat (
                      seat_id integer not null auto_increment,
                      seat_row integer not null,
                      seat_column integer not null,
                      is_book boolean,
                      concert_id integer,

                      primary key (seat_id),
                      foreign key (concert_id)
                          references concert(concert_id) on delete cascade
);