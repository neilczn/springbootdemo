CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `title` varchar(30) DEFAULT 'My Blog',
  `author_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8