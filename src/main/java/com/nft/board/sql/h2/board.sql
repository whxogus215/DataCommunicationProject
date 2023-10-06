create table board (
                       board_id integer not null auto_increment,
                       title varchar(45) not null,
                       content text,
                       price integer,
                       created_date timestamp,
                       modified_date timestamp,
                       admin_id int,
                       image_id int,
                       primary key (board_id),
                       foreign key (admin_id)
                           references admin(admin_id) on update cascade,
                       foreign key (image_id)
                           references image(image_id) on update cascade on delete set null
)