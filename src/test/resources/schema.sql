DROP TABLE IF EXISTS CONTINENT;
DROP TABLE IF EXISTS COUNTRY;

CREATE TABLE `continent` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `datecreated` DATETIME NOT NULL,
  `dateupdated` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)
);

CREATE TABLE `country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(445) NOT NULL,
  `flag` VARCHAR(255) NOT NULL,
  `continent` INT NULL,
  `datecreated` DATETIME NOT NULL,
  `dateupdated` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id1_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name1_UNIQUE` (`name` ASC),
  UNIQUE INDEX `flag1_UNIQUE` (`flag` ASC));

alter table Country  add constraint fk foreign key (continent ) references continent;