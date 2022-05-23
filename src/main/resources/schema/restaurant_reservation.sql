drop database if exists restaurant_reservation;

create database restaurant_reservation;

use restaurant_reservation;

create table restaurants (
  Restaurant_Name varchar(64) not null,
  table_count int,
  currently_filled int default 0,
  rest_description varchar(500),
  rest_location varchar(256),

  primary key(Restaurant_Name)
);
create table users (
  Account_Id varchar(8),
  account_name varchar(64),
  account_password varchar(64),
  account_role VARCHAR(8),
  -- Field specifying restaurant managed by management account --
  account_restaurant varchar(64) default null,

  primary key (Account_Id),
  constraint fk_account_restaurant
    foreign key (account_restaurant)
    references restaurants(Restaurant_Name)
);

create table restaurant_tables (
  restaurant_name varchar(64) not null,
  seat_count int not null,
  
  constraint fk_restaurant_name
  foreign key (restaurant_name)
    references restaurants(Restaurant_Name)

);

create table reservations (
  Reservation_Id varchar(8),
  restaurant_name varchar(64) not null,
  customer_name varchar(20) not NULL, 
  customer_pax INTEGER not NULL,
  reservation_date DATE not NULL,
  reservation_time TIME NOT NULL,
  created_datetime DATETIME DEFAULT CURRENT_TIMESTAMP,
  reservation_status BIT default 0,

  primary key(Reservation_Id),
  constraint fk_resv_restaurant_name foreign key (restaurant_name) references restaurants(Restaurant_Name)
);

