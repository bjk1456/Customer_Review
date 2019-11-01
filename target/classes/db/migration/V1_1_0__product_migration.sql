CREATE TABLE IF NOT EXISTS `product` (

    `product_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(350)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE IF NOT EXISTS `comment` (

    `comment_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `author` varchar(350),
    `content` text NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
