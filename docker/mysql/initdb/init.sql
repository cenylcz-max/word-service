CREATE DATABASE cenylcz;
DROP TABLE IF EXISTS `cenylcz`.`words`;
create table `cenylcz`.`words` (
    `word_key` INT NOT NULL AUTO_INCREMENT,
    `word` VARCHAR(32),
    `meaning` VARCHAR(1024),
    `frequency` INTEGER NOT NULL DEFAULT 1,
    `example` TEXT,
    PRIMARY KEY(`word_key`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;