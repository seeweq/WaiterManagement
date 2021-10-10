create table if not exists shifts (
id integer primary key AUTOINCREMENT,
waiter_id integer,
day_id integer, FOREIGN KEY (id) REFERENCES Waiters(id),
FOREIGN KEY (id) REFERENCES days(id));