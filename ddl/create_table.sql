CREATE TABLE `m_employee` (
  `employee_id` INT NOT NULL,
  `employee_nm` VARCHAR(100) NOT NULL,
  `email_address` VARCHAR(100) NOT NULL,
  `valid_flg` VARCHAR(1) NOT NULL,
  `create_date` DATETIME NOT NULL,
  `update_date` DATETIME NOT NULL,
  PRIMARY KEY (`employee_id`));


  CREATE TABLE `wk_respondent` (
    `wk_respondent_id` int(11) NOT NULL AUTO_INCREMENT,
    `employee_id` varchar(100) NOT NULL,
    `respondent_status` varchar(2) NOT NULL,
    `comment` varchar(256) NULL,
    `create_date` datetime NOT NULL,
    `update_date` datetime NOT NULL,
    PRIMARY KEY (`wk_respondent_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  CREATE TABLE `t_respondent` (
    `wk_respondent_id` int(11) NOT NULL,
    `employee_id` varchar(100) NOT NULL,
    `respondent_status` varchar(2) NOT NULL,
    `comment` varchar(256) DEFAULT NULL,
    `create_date` datetime NOT NULL,
    `update_date` datetime NOT NULL,
    PRIMARY KEY (`wk_respondent_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  CREATE TABLE `t_respondent` (
    `respondent_id` int(11) NOT NULL,
    `employee_id` varchar(100) NOT NULL,
    `respondent_status` varchar(2) NOT NULL,
    `comment` varchar(256) DEFAULT NULL,
    `create_date` datetime NOT NULL,
    `update_date` datetime NOT NULL,
    PRIMARY KEY (`respondent_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  CREATE TABLE `m_event` (
    `event_id` int(8) NOT NULL AUTO_INCREMENT,
    `event_name` varchar(100) NOT NULL,
    `hold_start_date` varchar(8) DEFAULT NULL,
    `hold_end_date` varchar(8) DEFAULT NULL,
    `publish_start_date` varchar(8) NOT NULL,
    `publish_end_date` varchar(8) NOT NULL,
    `input_end_date` varchar(8) DEFAULT NULL,
    `message` varchar(512) DEFAULT NULL,
    `valid_flg` varchar(1) NOT NULL,
    `create_date` datetime NOT NULL,
    `update_date` datetime NOT NULL,
    PRIMARY KEY (`event_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  CREATE TABLE `m_translate` (
    `key_id` varchar(10) NOT NULL,
    `code` varchar(10) NOT NULL,
    `text` varchar(100) NOT NULL,
    `create_date` datetime NOT NULL,
    `update_date` datetime NOT NULL,
    UNIQUE KEY `key_id_UNIQUE` (`key_id`,`code`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;






     select
   res.respondent_id,
   res.employee_id,
   emp.employee_nm,
   res.respondent_status as status_code,
   (select
      text
   from
      m_translate
   where
      key_id = 'RESPOND_ST'
   and
     code = res.respondent_status) as status_text,
   res.comment,
   res.create_date,
   res.update_date
 from
   t_respondent res,
   m_employee emp
 where    res.employee_id = emp.employee_id
