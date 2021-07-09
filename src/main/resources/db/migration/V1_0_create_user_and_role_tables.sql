/* create role table */
create table role(
id int(11) NOT NULL AUTO_INCREMENT,
name varchar(225),
created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id)
);

 /* create user table */
create table `user` (
id int(11) NOT NULL auto_increment,
first_name varchar(225),
second_name varchar(225),
email varchar(224),
role_id int(11) NOT NULL,
created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id),
FOREIGN KEY (role_id) REFERENCES role (id)
);