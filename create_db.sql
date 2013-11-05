SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Task`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Task` (
  `ID` INT NOT NULL ,
  `Name` VARCHAR(45) NULL DEFAULT 'Name' ,
  `StartNodeName` CHAR NULL ,
  `EndNodeName` CHAR NULL ,
  `Timestamp` DATETIME NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Solution`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Solution` (
  `ID` INT NOT NULL ,
  `Task_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_Solution_Task1_idx` (`Task_ID` ASC) ,
  CONSTRAINT `fk_Solution_Task1`
    FOREIGN KEY (`Task_ID` )
    REFERENCES `mydb`.`Task` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Connection`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Connection` (
  `ID` INT NOT NULL ,
  `StartNodeName` CHAR NOT NULL ,
  `EndNodeName` CHAR NOT NULL ,
  `Delay` DOUBLE NOT NULL ,
  `Task_ID` INT NULL ,
  `Solution_ID` INT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_Connection_Task_idx` (`Task_ID` ASC) ,
  INDEX `fk_Connection_Solution1_idx` (`Solution_ID` ASC) ,
  CONSTRAINT `fk_Connection_Task`
    FOREIGN KEY (`Task_ID` )
    REFERENCES `mydb`.`Task` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Connection_Solution1`
    FOREIGN KEY (`Solution_ID` )
    REFERENCES `mydb`.`Solution` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
