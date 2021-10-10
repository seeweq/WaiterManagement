create table if not exists days (
id integer primary key AUTOINCREMENT,
day text);


insert into days(day) values ('Monday');
insert into days (day) values ('Tuesday');
insert into days (day) values ('Wednesday');
insert into days (day) values ('Thursday');
insert into days (day) values ('Friday');
insert into days (day) values ('Saturday');
insert into days (day) values ('Sunday');