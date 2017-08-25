use springweb;
CREATE TABLE `reservation` (
  `user_id` varchar(20),
  `confirmation_no` varchar(20),
  `flight_no` varchar(20),
  `no_of_seat` varchar(20),
  `amount` double,
  PRIMARY KEY (`confirmation_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

use springweb;
CREATE TABLE `routing` (
  `origin` varchar(20),
  `destination` varchar(20),
  `flight_no` varchar(20),
  `no_of_seats` int,
  PRIMARY KEY (`flight_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

use springweb;
CREATE TABLE `user` (
  `username` varchar(20) not null,
  `phone_no` int,
  `email` varchar(20),
  `age` int,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

use springweb;
ALTER TABLE `routing`
ADD no_of_seat int;

use springweb;
ALTER TABLE `routing`
drop id;

use springweb;
ALTER TABLE `reservation`
modify no_of_seat int;

use springweb;
DELETE FROM routing
WHERE no_of_seat = 10;

use springweb;
ALTER TABLE `routing`
ADD Date date;

use springweb;
ALTER TABLE `routing`
ADD arrival_time time;

use springweb;
ALTER TABLE `routing`
ADD price double;

UPDATE routing
SET Date='2017-12-03', departure_time='02:30:00', arrival_time='04:00:00', price = 100.0
WHERE flight_no='B';

use springweb;
ALTER TABLE `routing`
ADD id int;

UPDATE routing
SET id = 3
WHERE flight_no='B';
  
use springweb;
ALTER TABLE routing DROP PRIMARY KEY;

use springweb;
ALTER TABLE routing
ADD PRIMARY KEY (id);

use springweb;
ALTER TABLE `routing` CHANGE `origin` `source` varchar(20);

use springweb;
ALTER TABLE `routing`
modify id int auto_increment;