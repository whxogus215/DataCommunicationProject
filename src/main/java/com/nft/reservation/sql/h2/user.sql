drop table admin if exists;
create table admin (
                        admin_id integer not null auto_increment,
                        admin_loginid varchar(100) not null unique,
                        admin_name varchar(20) not null unique,
                        admin_password varchar(100) not null unique,
                        created_date timestamp,
                        modified_date timestamp,
                        primary key (admin_id)
);
insert into admin(admin_loginid, admin_name, admin_password) values ('test', 'test admin', '7663200');
