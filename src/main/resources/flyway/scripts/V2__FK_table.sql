ALTER TABLE `osbb3`.`apartaments`
ADD INDEX `fk_apartaments_on_buildings_idx` (`buildings_id` ASC) VISIBLE;

ALTER TABLE `osbb3`.`apartaments`
ADD CONSTRAINT `fk_apartaments_on_buildings`
  FOREIGN KEY (`buildings_id`)
  REFERENCES `osbb3`.`buildings` (`id_buildings`);


ALTER TABLE `osbb3`.`apartment_teants`
ADD INDEX `fk_apartments_idx` (`apartment_id` ASC) VISIBLE;

ALTER TABLE `osbb3`.`apartment_teants`
ADD CONSTRAINT `fk_apartments`
  FOREIGN KEY (`apartment_id`)
  REFERENCES `osbb3`.`apartaments` (`id_apartaments`);


ALTER TABLE `osbb3`.`apartment_owners`
ADD INDEX `fk_owner_on_apartments_idx` (`apartment_id` ASC) VISIBLE,
ADD INDEX `fk_owner_on_rols_idx` (`people_rols_id` ASC) VISIBLE;

ALTER TABLE `osbb3`.`apartment_owners`
ADD CONSTRAINT `fk_owner_on_apartments`
  FOREIGN KEY (`apartment_id`)
  REFERENCES `osbb3`.`apartaments` (`id_apartaments`),
ADD CONSTRAINT `fk_owner_on_rols`
  FOREIGN KEY (`people_rols_id`)
  REFERENCES `osbb3`.`people_rols` (`id_people_rols`),
ADD CONSTRAINT `fk_owner_on_teants`
  FOREIGN KEY (`id_apartment_owners`)
  REFERENCES `osbb3`.`apartment_teants` (`id_teants`);







