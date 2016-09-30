-- import.sql for TicketManor project

insert into venues(id, name, streetAddress, city, provState, country, lat, lng) values(1, 'Carnegie Hall', '881 Seventh Avenue', 'New York', 'NY', 'US', 40.764938, -73.979897);
insert into venues(id, name, streetAddress, city, provState, country, lat, lng) values(2, 'Roy Thompson Hall', '60 Simcoe Street', 'Toronto', 'ON', 'CA', 43.646596, -79.386413);

insert into acts(id, title, year) values(-1, 'Rush', 1980);
insert into acts(id, title, year) values(-2, 'Beach Boys', 0);
insert into acts(id, title, year) values(-3, 'Boston Pops', 0);
insert into acts(id, title, year) values(-4, 'Eddie Money ', 0);
insert into acts(id, title, year) values(-5, 'Eric Clapton', 0);
insert into acts(id, title, year) values(-6, 'Foo Fighters', 0);
insert into acts(id, title, year) values(-7, 'Garth Brooks', 0);
insert into acts(id, title, year) values(-8, 'Justin Bieber', 0);
insert into acts(id, title, year) values(-9, 'Kenny Chesney', 0);
insert into acts(id, title, year) values(-10, 'Lady Gaga', 0);
insert into acts(id, title, year) values(-11, 'Leonard Cohen', 0);
insert into acts(id, title, year) values(-12, 'London Symphony', 0);
insert into acts(id, title, year) values(-13, 'Los Angeles Philharmonic', 0);
insert into acts(id, title, year) values(-14, 'Maroon 5', 0);
insert into acts(id, title, year) values(-15, 'Meghan Trainor', 0);
insert into acts(id, title, year) values(-16, 'Neil Diamond', 0);
insert into acts(id, title, year) values(-17, 'Rod Stewart', 0);
insert into acts(id, title, year) values(-18, 'Taylor Swift', 0);
insert into acts(id, title, year) values(-19, 'The Clash', 0);
insert into acts(id, title, year) values(-20, 'The Eagles', 0);
insert into acts(id, title, year) values(-21, 'The Script', 0);
insert into acts(id, title, year) values(-22, 'The Twinkies', 0);
insert into acts(id, title, year) values(-23, 'The Who', 0);
insert into acts(id, title, year) values(-24, 'Toronto Symphony Orchestra', 0);
insert into acts(id, title, year) values(-25, 'U2', 0);
insert into acts(id, title, year) values(-26, 'Wynonna Judd', 0);
insert into acts(id, title, year) values(-27, 'Ariana Grande', 0);
insert into acts(id, title, year) values(-28, 'Bette Midler', 0);
insert into acts(id, title, year) values(-29, 'Billy Joel', 0);
insert into acts(id, title, year) values(-30, 'Bob Seger', 0);
insert into acts(id, title, year) values(-31, 'Fleetwood Mac', 0);
insert into acts(id, title, year) values(-32, 'Foo Fighters', 0);
insert into acts(id, title, year) values(-33, 'Garth Brooks', 0);
insert into acts(id, title, year) values(-34, 'Houston Rodeo', 0);
insert into acts(id, title, year) values(-35, 'Kenny Chesney', 0);
insert into acts(id, title, year) values(-36, 'Lana Del Rey', 0);
insert into acts(id, title, year) values(-37, 'Luke Bryan', 0);
insert into acts(id, title, year) values(-38, 'One Direction', 0);
insert into acts(id, title, year) values(-39, 'Metallica', 0);
insert into acts(id, title, year) values(-49, 'Bruce Springsteen', 0);
insert into acts(id, title, year) values(-41, 'AC/DC', 0);
insert into acts(id, title, year) values(-42, 'Coldplay', 0);
insert into acts(id, title, year) values(-43, 'Bob Sieger', 0);

insert into acts (id, title, type, year) values (-100, 'Star Wars Bloopers', 0, 2001);
insert into movies (id, director) values (-3, 'George Lucas');

-- ert into events (id, what_id, date_time, venue_id) values (-1, -3, '2016-06-22 14:00:00', null);
insert into events (id, what_id, date_time, venue_id) values (-1, -3, null, null);

insert into people(id, firstName, lastName) values(-1, 'System', 'Administrator');
insert into members(id) values(-1);


