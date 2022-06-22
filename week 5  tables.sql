drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `second_name` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items`(
`items_id` INT(11) NOT NULL UNIQUE AUTO_INCREMENT,
`items_name` VARCHAR(40) DEFAULT NULL,
`value` INT(40) NOT NULL,
PRIMARY KEY (`items_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
fk_id INT NOT NULL,
order_id INT NOT NULL,
PRIMARY KEY (order_id, fk_id),
FOREIGN KEY (fk_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
fk_order_id INT NOT NULL,
fk_items_id INT NOT NULL,
order_items_id INT NOT NULL,

PRIMARY KEY (fk_order_id, fk_items_id, order_items_id),
FOREIGN KEY (fk_order_id) REFERENCES orders(order_id),
FOREIGN KEY (fk_items_id) REFERENCES items(items_id)
);

INSERT INTO `customers` (`first_name`, `second_name`) VALUES ('Omar', 'Malik'), ('lee', 'Harvey');