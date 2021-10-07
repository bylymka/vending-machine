CREATE TABLE IF NOT EXISTS `users` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `status` enum ('ACTIVE', 'DELETED'),
    `first_name` varchar(50),
    `last_name` varchar(50),
    `email` varchar(255) UNIQUE,
    `password` varchar(50)
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `roles` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `role` varchar(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `user_roles` (
    `user_id` bigint NOT NULL,
    `role_id` int NOT NULL,
    CONSTRAINT fk_vm_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_vm_role FOREIGN KEY (role_id) REFERENCES roles (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `vending_machines` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `status` enum ('ACTIVE', 'DELETED'),
    `balance` decimal,
    `address` varchar(250),
    `type` enum ('SMALL', 'MEDIUM', 'BIG')
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `product_types` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100),
    `price` decimal
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE IF NOT EXISTS `products` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product_type_id` bigint NOT NULL,
    `vending_machine_id` bigint NOT NULL,
    CONSTRAINT fk_vending_machine FOREIGN KEY (vending_machine_id) REFERENCES vending_machines (id),
    CONSTRAINT fk_product_types FOREIGN KEY (product_type_id) REFERENCES product_types (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;