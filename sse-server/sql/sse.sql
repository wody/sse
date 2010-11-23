SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `sse` DEFAULT CHARACTER SET latin1 ;
USE `sse` ;

-- -----------------------------------------------------
-- Table `sse`.`bill`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sse`.`bill` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `amount` DECIMAL(19,2) NULL DEFAULT NULL ,
  `date` DATETIME NULL DEFAULT NULL ,
  `roomNumber` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sse`.`room`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sse`.`room` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `occupancy` INT(11) NULL DEFAULT NULL ,
  `rateDouble` DECIMAL(19,2) NULL DEFAULT NULL ,
  `rateDoubleOneChild` DECIMAL(19,2) NULL DEFAULT NULL ,
  `rateSingle` DECIMAL(19,2) NULL DEFAULT NULL ,
  `rateSingleOneChild` DECIMAL(19,2) NULL DEFAULT NULL ,
  `rateSingleTwoChildren` DECIMAL(19,2) NULL DEFAULT NULL ,
  `rateTriple` DECIMAL(19,2) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sse`.`customer`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sse`.`customer` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `billingAdress` VARCHAR(255) NULL DEFAULT NULL ,
  `company` VARCHAR(255) NULL DEFAULT NULL ,
  `discount` DOUBLE NULL DEFAULT NULL ,
  `eMail` VARCHAR(255) NULL DEFAULT NULL ,
  `fax` VARCHAR(255) NULL DEFAULT NULL ,
  `note` VARCHAR(255) NULL DEFAULT NULL ,
  `phone` VARCHAR(255) NULL DEFAULT NULL ,
  `web` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sse`.`reservation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sse`.`reservation` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `discount` DOUBLE NULL DEFAULT NULL ,
  `fromDate` DATETIME NULL DEFAULT NULL ,
  `roomRate` DECIMAL(19,2) NULL DEFAULT NULL ,
  `toDate` DATETIME NULL DEFAULT NULL ,
  `bill_fk` BIGINT(20) NULL DEFAULT NULL ,
  `customer_fk` BIGINT(20) NULL DEFAULT NULL ,
  `room_fk` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FKA2D543CCD9CF3F52` (`bill_fk` ASC) ,
  INDEX `FKA2D543CC7552C4F2` (`customer_fk` ASC) ,
  INDEX `FKA2D543CC32A21ED2` (`room_fk` ASC) ,
  CONSTRAINT `FKA2D543CC32A21ED2`
    FOREIGN KEY (`room_fk` )
    REFERENCES `sse`.`room` (`id` ),
  CONSTRAINT `FKA2D543CC7552C4F2`
    FOREIGN KEY (`customer_fk` )
    REFERENCES `sse`.`customer` (`id` ),
  CONSTRAINT `FKA2D543CCD9CF3F52`
    FOREIGN KEY (`bill_fk` )
    REFERENCES `sse`.`bill` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sse`.`bill_reservation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sse`.`bill_reservation` (
  `bill_id` BIGINT(20) NOT NULL ,
  `reservations_id` BIGINT(20) NOT NULL ,
  UNIQUE INDEX `reservations_id` (`reservations_id` ASC) ,
  INDEX `FK9B6EF3947CFF26F1` (`reservations_id` ASC) ,
  INDEX `FK9B6EF394D9CF3FA8` (`bill_id` ASC) ,
  CONSTRAINT `FK9B6EF394D9CF3FA8`
    FOREIGN KEY (`bill_id` )
    REFERENCES `sse`.`bill` (`id` ),
  CONSTRAINT `FK9B6EF3947CFF26F1`
    FOREIGN KEY (`reservations_id` )
    REFERENCES `sse`.`reservation` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sse`.`customer_reservation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sse`.`customer_reservation` (
  `customer_id` BIGINT(20) NOT NULL ,
  `reservations_id` BIGINT(20) NOT NULL ,
  UNIQUE INDEX `reservations_id` (`reservations_id` ASC) ,
  INDEX `FK4E3C44EB7CFF26F1` (`reservations_id` ASC) ,
  INDEX `FK4E3C44EB7552C548` (`customer_id` ASC) ,
  CONSTRAINT `FK4E3C44EB7552C548`
    FOREIGN KEY (`customer_id` )
    REFERENCES `sse`.`customer` (`id` ),
  CONSTRAINT `FK4E3C44EB7CFF26F1`
    FOREIGN KEY (`reservations_id` )
    REFERENCES `sse`.`reservation` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sse`.`room_reservation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sse`.`room_reservation` (
  `room_id` BIGINT(20) NOT NULL ,
  `reservations_id` BIGINT(20) NOT NULL ,
  UNIQUE INDEX `reservations_id` (`reservations_id` ASC) ,
  INDEX `FK63CDB2687CFF26F1` (`reservations_id` ASC) ,
  INDEX `FK63CDB26832A21F28` (`room_id` ASC) ,
  CONSTRAINT `FK63CDB26832A21F28`
    FOREIGN KEY (`room_id` )
    REFERENCES `sse`.`room` (`id` ),
  CONSTRAINT `FK63CDB2687CFF26F1`
    FOREIGN KEY (`reservations_id` )
    REFERENCES `sse`.`reservation` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
