CREATE TABLE IF NOT EXISTS `product` (

    `product_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(350)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `review` (

    `review_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `author` varchar(350),
    `content` text NOT NULL,
    `product_id_fk` int NOT NULL,
    FOREIGN KEY (`product_id_fk`) REFERENCES `product`(`product_id`)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `comment` (

    `comment_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `author` varchar(350),
    `content` text NOT NULL,
    `review_id_fk` int NOT NULL,
    FOREIGN KEY (`review_id_fk`) REFERENCES `review`(`review_id`)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
