CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `account_id` varchar(50) default NULL,
  `token` char(36) default NULL,
  `gmt_create` bigint(11) default NULL,
  `gmt_modified` bigint(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;