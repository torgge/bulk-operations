

CREATE SCHEMA bulk_db;

CREATE TABLE IF NOT EXISTS po (
   order_id INT NOT NULL,
   created_at timestamp,
   PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS poi (
    order_item_id INT NOT NULL,
    order_id int not null,
    description varchar(250) NOT NULL,
    quantity numeric,
    PRIMARY KEY (order_item_id),
    CONSTRAINT fk_order
        FOREIGN KEY(order_id)
        REFERENCES po(order_id)
);
