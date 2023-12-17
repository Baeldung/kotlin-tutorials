create table items(
  id int not null primary key auto_increment,
  description varchar(255) not null
);

create table customers(
  id int not null primary key auto_increment,
  email varchar(255) not null,
  UNIQUE KEY(email)
);

create table orders(
  id int not null primary key auto_increment,
  item_id int not null,
  customer_id int not null,
  free_text_card varchar(255) null,
  amount decimal(10,2),
  last_update timestamp(3)
);
