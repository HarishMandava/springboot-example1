CREATE DATABASE courseApi;

use courseApi;

CREATE TABLE IF NOT EXISTS `Topics` (
  `id` varchar(128),
  `name` varchar(128),
  `description` varchar(128),
  PRIMARY KEY (`id`(4))
);

