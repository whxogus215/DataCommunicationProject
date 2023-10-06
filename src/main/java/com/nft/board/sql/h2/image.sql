CREATE TABLE image
(
    image_id int primary key auto_increment,
    name    varchar(512),
    type    varchar(128),
    pic_byte MEDIUMBLOB
);