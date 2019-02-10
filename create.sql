DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`
(
  `id`       int(0)         NOT NULL AUTO_INCREMENT,
  `title`    varchar(255)   NOT NULL,
  `author`   varchar(255)   NOT NULL,
  `price`    decimal(10, 2) NOT NULL,
  `year`     varchar(255)   NULL DEFAULT '',
  `category` varchar(255)   NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;