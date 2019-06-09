-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema wpd
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema wpd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wpd` DEFAULT CHARACTER SET utf8 ;
USE `wpd` ;

-- -----------------------------------------------------
-- Table `wpd`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`person` (
  `id_person` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `middle_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `gender` VARCHAR(2) NULL DEFAULT 'NE',
  `birthdate` DATE NULL,
  PRIMARY KEY (`id_person`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`type` (
  `id_type` INT NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(20) NOT NULL COMMENT 'MOBILE, HOME, WORK, OTHER, LINKEDIN, HOMEPAGE, GITHUB',
  `type_no` INT NULL,
  PRIMARY KEY (`id_type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`address` (
  `id_address` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `id_type` INT NOT NULL,
  `street_address` VARCHAR(45) NULL,
  `CO` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `zip_code` INT(5) NULL,
  `county` VARCHAR(60) NULL,
  PRIMARY KEY (`id_address`, `id_person`),
  INDEX `fk_address_type1_idx` (`id_type` ASC),
  INDEX `fk_address_person1_idx` (`id_person` ASC),
  CONSTRAINT `fk_address_type1`
    FOREIGN KEY (`id_type`)
    REFERENCES `wpd`.`type` (`id_type`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_address_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`company` (
  `id_company` INT NOT NULL AUTO_INCREMENT,
  `company_name` VARCHAR(80) NOT NULL,
  `company_description` VARCHAR(100) NULL,
  PRIMARY KEY (`id_company`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`professional_experience`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`professional_experience` (
  `id_prof_experience` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `id_company` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(45) NULL,
  `start_year` YEAR(4) NULL,
  `start_month` INT(2) NULL,
  `start_day` INT(2) NULL,
  `end_year` YEAR(4) NULL,
  `end_month` INT(2) NULL,
  `end_day` INT(2) NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  PRIMARY KEY (`id_prof_experience`, `id_person`),
  INDEX `fk_professional_experience_company1_idx` (`id_company` ASC),
  INDEX `fk_professional_experience_person1_idx` (`id_person` ASC),
  CONSTRAINT `fk_professional_experience_company1`
    FOREIGN KEY (`id_company`)
    REFERENCES `wpd`.`company` (`id_company`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_professional_experience_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `date_created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `enabled` VARCHAR(3) NULL,
  PRIMARY KEY (`id_user`),
  INDEX `fk_user_person1_idx` (`id_person` ASC),
  CONSTRAINT `fk_user_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`competence_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`competence_category` (
  `id_competence_category` INT NOT NULL AUTO_INCREMENT,
  `competence_category_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_competence_category`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`competence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`competence` (
  `id_competence` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `id_competence_category` INT NOT NULL,
  `competence_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_competence`, `id_person`),
  INDEX `fk_competence_person1_idx` (`id_person` ASC),
  INDEX `fk_competence_competence_category1_idx` (`id_competence_category` ASC),
  CONSTRAINT `fk_competence_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_competence_competence_category1`
    FOREIGN KEY (`id_competence_category`)
    REFERENCES `wpd`.`competence_category` (`id_competence_category`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`relation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`relation` (
  `id_relation` INT NOT NULL AUTO_INCREMENT,
  `relation_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_relation`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`reference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`reference` (
  `id_reference` INT NOT NULL AUTO_INCREMENT,
  `id_relation` INT NOT NULL,
  `id_person` INT NOT NULL,
  `id_reference_person_for` INT NOT NULL,
  `relation_description` VARCHAR(255) NULL,
  PRIMARY KEY (`id_reference`, `id_person`),
  INDEX `fk_reference_relation1_idx` (`id_relation` ASC),
  INDEX `fk_reference_person1_idx` (`id_person` ASC),
  INDEX `fk_reference_person2_idx` (`id_reference_person_for` ASC),
  CONSTRAINT `fk_reference_relation1`
    FOREIGN KEY (`id_relation`)
    REFERENCES `wpd`.`relation` (`id_relation`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reference_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reference_person2`
    FOREIGN KEY (`id_reference_person_for`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`certification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`certification` (
  `id_certification` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `certification_name` VARCHAR(45) NOT NULL,
  `certification_description` VARCHAR(100) NULL,
  PRIMARY KEY (`id_certification`, `id_person`),
  INDEX `fk_certification_person1_idx` (`id_person` ASC),
  CONSTRAINT `fk_certification_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`education` (
  `id_education` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `education_name` VARCHAR(100) NOT NULL,
  `education_start_year` YEAR(4) NULL,
  `education_start_month` TINYINT(2) NULL,
  `education_start_day` TINYINT(2) NULL,
  `education_start_date` DATE NULL,
  `education_end_year` YEAR(4) NULL,
  `education_end_month` TINYINT(2) NULL,
  `education_end_day` TINYINT(2) NULL,
  `education_level` VARCHAR(45) NULL,
  `is_program` TINYINT NULL,
  `education_grade` VARCHAR(45) NULL,
  PRIMARY KEY (`id_education`, `id_person`),
  INDEX `fk_education_person1_idx` (`id_person` ASC),
  CONSTRAINT `fk_education_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`course` (
  `id_course` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `id_education` INT NULL,
  `course_name` VARCHAR(80) NOT NULL,
  `course_description` VARCHAR(100) NULL,
  `course_start_year` YEAR(4) NULL,
  `course_start_month` TINYINT(2) NULL,
  `course_start_day` TINYINT(2) NULL,
  `course_start_date` DATE NULL,
  `course_end_year` YEAR(4) NULL,
  `course_end_month` TINYINT(2) NULL,
  `course_end_day` TINYINT(2) NULL,
  `course_end_date` DATE NULL,
  `course_level` VARCHAR(45) NULL,
  `course_grade` VARCHAR(45) NULL,
  PRIMARY KEY (`id_course`, `id_person`),
  INDEX `fk_course_education1_idx` (`id_education` ASC),
  INDEX `fk_course_person1_idx` (`id_person` ASC),
  CONSTRAINT `fk_course_education1`
    FOREIGN KEY (`id_education`)
    REFERENCES `wpd`.`education` (`id_education`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`webpage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`webpage` (
  `id_webpage` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `id_type` INT NOT NULL,
  `webpage_name` VARCHAR(100) NOT NULL,
  `link` VARCHAR(200) NOT NULL,
  `webpage_description` VARCHAR(200) NULL,
  PRIMARY KEY (`id_webpage`, `id_person`),
  INDEX `fk_webpage_type1_idx` (`id_type` ASC),
  INDEX `fk_webpage_person1_idx` (`id_person` ASC),
  CONSTRAINT `fk_webpage_type1`
    FOREIGN KEY (`id_type`)
    REFERENCES `wpd`.`type` (`id_type`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_webpage_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`phone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`phone` (
  `id_phone` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `id_type` INT NOT NULL,
  `phone_number` INT NOT NULL,
  `country_prefix` VARCHAR(4) NULL,
  `id_phone_category` INT NULL,
  `primary_contact_number` TINYINT(3) NULL,
  PRIMARY KEY (`id_phone`, `id_person`),
  INDEX `fk_phone_type1_idx` (`id_type` ASC),
  INDEX `fk_phone_person1_idx` (`id_person` ASC),
  CONSTRAINT `fk_phone_type1`
    FOREIGN KEY (`id_type`)
    REFERENCES `wpd`.`type` (`id_type`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_phone_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`email`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`email` (
  `id_email` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `id_type` INT NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_email`, `id_person`),
  INDEX `fk_email_type1_idx` (`id_type` ASC),
  INDEX `fk_email_person1_idx` (`id_person` ASC),
  CONSTRAINT `fk_email_type1`
    FOREIGN KEY (`id_type`)
    REFERENCES `wpd`.`type` (`id_type`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_email_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`project` (
  `id_project` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_project`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
