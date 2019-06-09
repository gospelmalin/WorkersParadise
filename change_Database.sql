alter table education
modify is_program boolean;

alter table phone
modify primary_contact_number boolean;

alter table user
modify column password varchar(255);

ALTER TABLE user
  RENAME TO users;
  
ALTER TABLE education
  ADD education_description VARCHAR(1000) AFTER education_name,
  ADD school VARCHAR(255)
  ;

ALTER TABLE education
  ADD education_end_date DATE AFTER education_start_date
  ;
  
  ALTER TABLE education
  CHANGE school school VARCHAR(255) AFTER education_description
  ;
  
    ALTER TABLE course
   ADD school VARCHAR(255) AFTER course_description
  ;
  
  ALTER TABLE professional_experience
  MODIFY description VARCHAR(1000)
  ;

-- TODO: koppling id_contact -> person
ALTER TABLE project
  ADD id_contact INT NOT NULL,
  ADD project_title VARCHAR(255) NOT NULL,
  ADD project_description VARCHAR(1000),
  ADD id_profession_category INT NOT NULL,
  ADD profession VARCHAR(255),
  ADD id_location INT NOT NULL DEFAULT 1,
  ADD date_published timestamp DEFAULT current_timestamp,
  ADD apply_before_date date,
  ADD INDEX `fk_project_profession_category1_idx` (`id_profession_category` ASC),
  ADD INDEX `fk_project_location_idx` (`id_location` ASC),
  ADD CONSTRAINT `fk_project_profession_category`
    FOREIGN KEY (`id_profession_category`)
    REFERENCES `wpd`.`profession_category` (`id_profession_category`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_project_location`
    FOREIGN KEY (`id_location`)
    REFERENCES `wpd`.`location` (`id_location`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE;

   ALTER TABLE address
   ADD primary_address boolean AFTER id_type
  ;
  
    ALTER TABLE email
   ADD primary_email boolean
  ;
-- -----------------------------------------------------
-- Table `wpd`.`county`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`county` (
  `id_county` INT NOT NULL AUTO_INCREMENT,
  `county` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_county`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `wpd`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`location` (
  `id_location` INT NOT NULL AUTO_INCREMENT,
  `id_county` INT NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_location`),
  INDEX `fk_location_county1_idx` (`id_county` ASC),
  CONSTRAINT `fk_location_county1`
    FOREIGN KEY (`id_county`)
    REFERENCES `wpd`.`county` (`id_county`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `wpd`.`profession_area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`profession_area` (
  `id_profession_area` INT NOT NULL AUTO_INCREMENT,
  `profession_area_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_profession_area`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wpd`.`profession_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`profession_category` (
  `id_profession_category` INT NOT NULL AUTO_INCREMENT,
  `id_profession_area` INT NOT NULL,
  `profession_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_profession_category`),
  INDEX `fk_profession_category_profession_area1_idx` (`id_profession_area` ASC),
  CONSTRAINT `fk_profession_category_profession_area1`
    FOREIGN KEY (`id_profession_area`)
    REFERENCES `wpd`.`profession_area` (`id_profession_area`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `wpd`.`requirement_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`requirement_type` (
  `id_requirement_type` INT NOT NULL AUTO_INCREMENT,
  `requirement_type_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_requirement_type`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `wpd`.`project_competence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`project_competence` (
  `id_project_competence` INT NOT NULL AUTO_INCREMENT,
  `id_project` INT NOT NULL,
  `id_competence` INT NOT NULL,
  `id_requirement_type` INT NOT NULL,
  PRIMARY KEY (`id_project_competence`),
  INDEX `fk_project_competence_project1_idx` (`id_project` ASC),
  INDEX `fk_project_competence_competence1_idx` (`id_competence` ASC),
  CONSTRAINT `fk_project_competence_project1`
    FOREIGN KEY (`id_project`)
    REFERENCES `wpd`.`project` (`id_project`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_project_competence_competence1`
    FOREIGN KEY (`id_competence`)
    REFERENCES `wpd`.`competence` (`id_competence`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_project_competence_requirement_type1`
    FOREIGN KEY (`id_requirement_type`)
    REFERENCES `wpd`.`requirement_type` (`id_requirement_type`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

 -- -----------------------------------------------------
-- Table `wpd`.`about`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wpd`.`about` (
  `id_about` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `about_summary` VARCHAR(1000),
  `interests` VARCHAR(1000),
  PRIMARY KEY (`id_about`),
   INDEX `fk_about_person1_idx` (`id_person` ASC),
  CONSTRAINT `fk_about_person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `wpd`.`person` (`id_person`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


alter table competence
drop primary key,
add primary key(id_competence) ;

alter table competence
drop foreign key fk_competence_person1;

alter table competence
drop foreign key fk_competence_competence_category1;

alter table competence
drop column id_person;

alter table competence
drop column id_competence_category;

alter table competence
modify column competence_name varchar(100) NOT NULL;

drop table competence_category;


create table if not exists person_competence (
 id_person_competence INT NOT NULL AUTO_INCREMENT,
 id_person INT NOT NULL,
 id_competence INT NOT NULL,
 PRIMARY KEY (id_person_competence),
 INDEX `fk_person_competence_person1_idx` (`id_person` ASC),
 INDEX `fk_person_competence_competence1_idx` (`id_competence` ASC),
  CONSTRAINT `fk_person_competence_person1` FOREIGN KEY (`id_person`) REFERENCES `person` (`id_person`) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT `fk_person_competence_competence1` FOREIGN KEY (`id_competence`) REFERENCES `competence` (`id_competence`) ON UPDATE CASCADE ON DELETE RESTRICT
  )
ENGINE = InnoDB;

