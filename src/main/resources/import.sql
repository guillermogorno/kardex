-- creo las tablas:

create sequence category_sequence start with 1 increment by 1
create sequence product_sequence start with 1 increment by 1

-- inserto categorias:

insert into category (id, name) values ((select category_sequence.nextval from dual), 'Marvel');

insert into category (id, name) values ((select category_sequence.nextval from dual), 'DC Comics');

-- inserto productos:
insert into product (id, name, stock, category_id) values ((select product_sequence.nextval from dual), 'Vasos Marvel', 50, 1);
insert into product (id, name, stock, category_id) values ((select product_sequence.nextval from dual), 'Velas Marvel', 30, 1);
insert into product (id, name, stock, category_id) values ((select product_sequence.nextval from dual), 'Muniecos Marvel', 500, 1);

insert into product (id, name, stock, category_id) values ((select product_sequence.nextval from dual), 'Muniecos DC Comics', 100, 2);
insert into product (id, name, stock, category_id) values ((select product_sequence.nextval from dual), 'Remeras DC Comics', 200, 2);
insert into product (id, name, stock, category_id) values ((select product_sequence.nextval from dual), 'Pulseras DC Comics', 50, 2);

