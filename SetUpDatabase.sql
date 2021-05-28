ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '15023173315';

-- create schemas
DROP DATABASE  IF EXISTS `coursereg`;

CREATE DATABASE IF NOT EXISTS `coursereg`;
USE `coursereg`;

-- student
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `email` varchar(45) NOT NULL,
    
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `students`
VALUES
 (1, 'Lingrong', 'Hu', 'lingrong@gmail.com');

-- instructor
DROP TABLE IF EXISTS `instructors`;
CREATE TABLE `instructors`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `email` varchar(45) NOT NULL,
    
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `instructors`
VALUES
 (2, 'Marcel', 'Ginestre', 'marcel@gmail.com');
 
 -- spring security login
 -- users
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- password: 12345
INSERT INTO `users` 
VALUES 
('marcel@gmail.com','{bcrypt}$2a$10$6unxgtPiF6bq.tn/2NxgSOGhcVHHQKtTwdrJRPTDnbvpbXITfHTGi',1),
('lingrong@gmail.com','{bcrypt}$2a$10$6unxgtPiF6bq.tn/2NxgSOGhcVHHQKtTwdrJRPTDnbvpbXITfHTGi',1);

 -- authorities
 DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  `instructor_id` int(11) NULL,
  `student_id` int(11) NULL,
	PRIMARY KEY (`id`),
    
  CONSTRAINT `authorities_ibfk_1` 
  FOREIGN KEY (`username`) 
  REFERENCES `users` (`username`),
  
  CONSTRAINT `authorities_inst`
    FOREIGN KEY (`instructor_id`)
    REFERENCES `instructors` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    
    CONSTRAINT `authorities_stu`
	FOREIGN KEY (`student_id`)
    REFERENCES `students` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `authorities` 
VALUES 
(1, 'lingrong@gmail.com','ROLE_STUDENT', NULL, 1),
(2, 'marcel@gmail.com', 'ROLE_INSTRUCTOR', 2, NULL);

-- course
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(128) NOT NULL,
    `instructor_id` int(11) NOT NULL,
    
    -- set primary key
    PRIMARY KEY (`id`),
	
    -- set foreign key
    CONSTRAINT `FK_COURSE_INSTRUCTOR`  -- name of the foreign key
    FOREIGN KEY(`instructor_id`)
    REFERENCES `instructors` (`id`) 
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- review
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `rating` decimal(1) DEFAULT NULL,
    `comment` varchar(256) DEFAULT NULL,
    `course_id` int(11) NOT NULL,
    `student_id` int(11) NOT NULL,
    
    PRIMARY KEY(`id`),
        
    CONSTRAINT `FK_REVIEW_COURSE`
    FOREIGN KEY (`course_id`)
    REFERENCES `courses` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    
    CONSTRAINT `FK_REVIEW_STUDENT`
    FOREIGN KEY (`student_id`)
    REFERENCES `students` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- course_student
DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`course_id` int(11) NOT NULL,
    `student_id` int(11) NOT NULL,
    
    PRIMARY KEY(`id`),
    
    CONSTRAINT `FK_COURSE_STUDENT_COURSE`
    FOREIGN KEY (`course_id`)
    REFERENCES `courses` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    
    CONSTRAINT `FK_COURSE_STUDENT_STUDENT`
    FOREIGN KEY (`student_id`)
    REFERENCES `students` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=latin1;