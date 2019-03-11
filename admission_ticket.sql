DROP TABLE IF EXISTS `admission_ticket`;
CREATE TABLE `admission_ticket`
(
  `id`               int(11)                                                  NOT NULL AUTO_INCREMENT,
  `name`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
  `candidate_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
  `id_number`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
  `gender`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
  `subject`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
  `address`          varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time`             varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
  `seat`             varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
  `email`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;