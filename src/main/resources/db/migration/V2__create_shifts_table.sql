create table if not exists shifts (
id integer primary key AUTOINCREMENT,
waiter_id integer,day_id integer, FOREIGN KEY (waiter_id) REFERENCES waiters(id),
FOREIGN KEY (day_id) REFERENCES days(id));
















