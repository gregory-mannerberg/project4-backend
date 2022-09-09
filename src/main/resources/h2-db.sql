drop database if exists `spyglass`;

create database `spyglass`;

use `spyglass`;

create table `users`
(
	`username` varchar(50) not null primary key,
    `password` varchar(100) not null ,
    `enabled` boolean not null
);

create table `authorities`
(
	`username` varchar(50) not null,
    `authority` varchar(50) not null,
    primary key(`username`, `authority`),
	foreign key(`username`) references `users`(`username`)
);

create table `goal`
(
	`id` int auto_increment primary key,
    `name` varchar(255),
    `description` text,
    `image_url` varchar(255),
    `target_date` date,
    `target_amount` float,
    `current_amount` float,
    `username` varchar(50),
    foreign key(`username`) references `users`(`username`)
);