CREATE TABLE `osbb3`.`buildings` (
  `id_buildings` INT NOT NULL AUTO_INCREMENT,
  `adress` VARCHAR(45) NULL,
  `house_number` VARCHAR(45) NULL,
  PRIMARY KEY (`id_buildings`));

CREATE TABLE `osbb3`.`apartaments` (
  `id_apartaments` INT NOT NULL,
  `number_apartaments` INT NULL,
  `square` FLOAT NULL,
  `buildings_id` INT NULL,
  PRIMARY KEY (`id_apartaments`));

CREATE TABLE `osbb3`.`apartment_teants` (
    `id_teants` INT NOT NULL AUTO_INCREMENT,
    `full_name` VARCHAR(45) NOT NULL,
    `email` VARCHAR(45) NULL,
    `right_of_way` BOOLEAN NULL,
    `apartment_id` INT NOT NULL,
    PRIMARY KEY (`id_teants`));

CREATE TABLE `osbb3`.`people_rols` (
  `id_people_rols` INT NOT NULL,
  `rois` VARCHAR(45) NULL,
  `owner_id` INT NOT NULL,
  PRIMARY KEY (`id_people_rols`));

CREATE TABLE `osbb3`.`apartment_owners` (
  `id_apartment_owners` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `people_rols_id` INT NOT NULL,
  `apartment_id` INT NOT NULL,
  PRIMARY KEY (`id_apartment_owners`));