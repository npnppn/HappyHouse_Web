CREATE TABLE IF NOT EXISTS `happyhouse`.`article` (
  `articleno` INT NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(16) NOT NULL,
  `subject` VARCHAR(100) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `regtime` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  INDEX `article_userid_FK_idx` (`userid` ASC) VISIBLE,
  PRIMARY KEY (`articleno`),
  CONSTRAINT `article_userid_FK`
    FOREIGN KEY (`userid`)
    REFERENCES `happyhouse`.`ssafy_member` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

use happyhouse;
insert into article (userid, subject, content, regtime) values ('ssafy', '제목제목제목', '글글글글', now());