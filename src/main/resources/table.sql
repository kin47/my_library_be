create database my_library;

create table book(
    book_id int(11) NOT NULL,
    title text NOT NULL,
    content text NOT NULL,
    image varchar(300),
    description text,
    create_at bigint,
    update_at bigint
)

create table category(
    category_id int(11) NOT NULL,
    name varchar(50) NOT NULL,
)

create table users(
    uid int(11) not null primary key,
    name nvarchar(70),
    phone varchar(15),
    address nvarchar(200),
    email varchar(30) unique,
    username varchar(20) unique,
    password varchar(20),
    role int,
    create_at bigint
)

create table liked(
    uid int(11),
    book_id int(11),
    FOREIGN KEY (uid) REFERENCES users(uid),
    FOREIGN KEY (book_id) REFERENCES book(book_id)
)

create table comments(
    uid int(11),
    book_id int(11),
    content text,
    create_at bigint,
    FOREIGN KEY (uid) REFERENCES users(uid),
    FOREIGN KEY (book_id) REFERENCES book(book_id)
)

create table bookcategories(
    book_id int(11),
    category_id int(11),
    FOREIGN KEY (category_id) REFERENCES category(category_id),
    FOREIGN KEY (book_id) REFERENCES book(book_id)
)

ALTER TABLE book
    ADD PRIMARY KEY (book_id);
ALTER TABLE book
    MODIFY book_id int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;COMMIT;

ALTER TABLE category
    ADD PRIMARY KEY (category_id);
ALTER TABLE category
    MODIFY category int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;COMMIT;

ALTER TABLE users
    MODIFY uid int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;COMMIT;