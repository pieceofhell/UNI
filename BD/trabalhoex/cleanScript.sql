SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP SCHEMA IF EXISTS `mydb` ;

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

DROP TABLE IF EXISTS `mydb`.`Universidade` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Universidade` (
  `idUniversidade` INT NOT NULL,
  `Nome` VARCHAR(80) NULL,
  PRIMARY KEY (`idUniversidade`))
ENGINE = InnoDB;


DROP TABLE IF EXISTS `mydb`.`Biblioteca` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Biblioteca` (
  `idBiblioteca` INT NOT NULL,
  `Nome` VARCHAR(75) NULL,
  `Endereco` VARCHAR(50) NULL,
  `universidade_id` INT NOT NULL,
  PRIMARY KEY (`idBiblioteca`, `universidade_id`),
  INDEX `fk_Biblioteca_Universidade1_idx` (`universidade_id` ASC) VISIBLE,
  CONSTRAINT `fk_Biblioteca_Universidade1`
    FOREIGN KEY (`universidade_id`)
    REFERENCES `mydb`.`Universidade` (`idUniversidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


DROP TABLE IF EXISTS `mydb`.`Funcionario` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Funcionario` (
  `CPF` CHAR(11) NOT NULL,
  `biblioteca_id` INT NOT NULL,
  `universidade_id` INT NOT NULL,
  `Nome` VARCHAR(45) NULL,
  PRIMARY KEY (`CPF`, `biblioteca_id`, `universidade_id`),
  INDEX `fk_Funcionário_Biblioteca1_idx` (`biblioteca_id` ASC, `universidade_id` ASC) VISIBLE,
  CONSTRAINT `fk_Funcionário_Biblioteca1`
    FOREIGN KEY (`biblioteca_id` , `universidade_id`)
    REFERENCES `mydb`.`Biblioteca` (`idBiblioteca` , `universidade_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


DROP TABLE IF EXISTS `mydb`.`Livro` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Livro` (
  `idLivro` INT NOT NULL,
  `dataPublicacao` DATE NULL,
  `Preco` FLOAT NULL,
  `Genero` VARCHAR(30) NULL,
  PRIMARY KEY (`idLivro`))
ENGINE = InnoDB;


DROP TABLE IF EXISTS `mydb`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `CPF` CHAR(11) NOT NULL,
  `Nome` VARCHAR(80) NULL,
  `Telefone` VARCHAR(18) NULL,
  `Endereco` VARCHAR(50) NULL,
  PRIMARY KEY (`CPF`))
ENGINE = InnoDB;


DROP TABLE IF EXISTS `mydb`.`Compra` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Compra` (
  `dataCompra` DATE NOT NULL,
  `CPF_Funcionario` CHAR(11) NOT NULL,
  `livro_id` INT NOT NULL,
  `CPF_Usuario` CHAR(11) NOT NULL,
  PRIMARY KEY (`dataCompra`, `CPF_Funcionario`, `livro_id`, `CPF_Usuario`),
  INDEX `fk_Compra_Funcionário1_idx` (`CPF_Funcionario` ASC) VISIBLE,
  INDEX `fk_Compra_Livro1_idx` (`livro_id` ASC) VISIBLE,
  INDEX `fk_Compra_Usuário1_idx` (`CPF_Usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Compra_Funcionário1`
    FOREIGN KEY (`CPF_Funcionario`)
    REFERENCES `mydb`.`Funcionario` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_Livro1`
    FOREIGN KEY (`livro_id`)
    REFERENCES `mydb`.`Livro` (`idLivro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_Usuário1`
    FOREIGN KEY (`CPF_Usuario`)
    REFERENCES `mydb`.`Usuario` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


DROP TABLE IF EXISTS `mydb`.`Emprestimo` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Emprestimo` (
  `CPF_Funcionario` CHAR(11) NOT NULL,
  `CPF_Usuario` CHAR(11) NOT NULL,
  `livro_id` INT NOT NULL,
  `dataLocacao` DATE NOT NULL,
  `dataDevolucao` DATE NULL,
  `valorPagar` FLOAT NULL,
  PRIMARY KEY (`CPF_Funcionario`, `CPF_Usuario`, `livro_id`, `dataLocacao`),
  INDEX `fk_Empréstimo_Funcionário1_idx` (`CPF_Funcionario` ASC) VISIBLE,
  INDEX `fk_Empréstimo_Livro1_idx` (`livro_id` ASC) VISIBLE,
  INDEX `fk_Empréstimo_Usuário1_idx` (`CPF_Usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Empréstimo_Funcionário1`
    FOREIGN KEY (`CPF_Funcionario`)
    REFERENCES `mydb`.`Funcionario` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Empréstimo_Livro1`
    FOREIGN KEY (`livro_id`)
    REFERENCES `mydb`.`Livro` (`idLivro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Empréstimo_Usuário1`
    FOREIGN KEY (`CPF_Usuario`)
    REFERENCES `mydb`.`Usuario` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


DROP TABLE IF EXISTS `mydb`.`Personagem` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Personagem` (
  `idPersonagem` INT NOT NULL,
  `Nome` VARCHAR(45) NULL,
  `Sexo` CHAR(1) NULL,
  PRIMARY KEY (`idPersonagem`))
ENGINE = InnoDB;


DROP TABLE IF EXISTS `mydb`.`LivrosEmBibliotecas` ;

CREATE TABLE IF NOT EXISTS `mydb`.`LivrosEmBibliotecas` (
  `livro_id` INT NOT NULL,
  `biblioteca_id` INT NOT NULL,
  `Quantidade` INT NULL,
  PRIMARY KEY (`livro_id`, `biblioteca_id`),
  INDEX `fk_Livro_has_Biblioteca_Biblioteca1_idx` (`biblioteca_id` ASC) VISIBLE,
  INDEX `fk_Livro_has_Biblioteca_Livro_idx` (`livro_id` ASC) VISIBLE,
  CONSTRAINT `fk_Livro_has_Biblioteca_Livro`
    FOREIGN KEY (`livro_id`)
    REFERENCES `mydb`.`Livro` (`idLivro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Livro_has_Biblioteca_Biblioteca1`
    FOREIGN KEY (`biblioteca_id`)
    REFERENCES `mydb`.`Biblioteca` (`idBiblioteca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


DROP TABLE IF EXISTS `mydb`.`PersonagensEmLivros` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PersonagensEmLivros` (
  `livro_id` INT NOT NULL,
  `personagem_id` INT NOT NULL,
  PRIMARY KEY (`livro_id`, `personagem_id`),
  INDEX `fk_Livro_has_Personagem_Personagem1_idx` (`personagem_id` ASC) VISIBLE,
  INDEX `fk_Livro_has_Personagem_Livro1_idx` (`livro_id` ASC) VISIBLE,
  CONSTRAINT `fk_Livro_has_Personagem_Livro1`
    FOREIGN KEY (`livro_id`)
    REFERENCES `mydb`.`Livro` (`idLivro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Livro_has_Personagem_Personagem1`
    FOREIGN KEY (`personagem_id`)
    REFERENCES `mydb`.`Personagem` (`idPersonagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
