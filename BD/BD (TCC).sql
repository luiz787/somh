CREATE TABLE `Usuario`
(
  `cod_usuario` Serial NOT NULL,
  `cod_perfil` Int NOT NULL,
  `nom_usuario` Char(30) NOT NULL,
  `txt_senha` Char(100) NOT NULL
)
;

CREATE INDEX `IX_Relationship1` ON `Usuario` (`cod_perfil`)
;

ALTER TABLE `Usuario` ADD  PRIMARY KEY (`cod_usuario`)
;

ALTER TABLE `Usuario` ADD UNIQUE `nom_usuario` (`nom_usuario`)
;

CREATE TABLE `Perfil`
(
  `cod_perfil` Int NOT NULL,
  `des_perfil` Char(20) NOT NULL
)
;

ALTER TABLE `Perfil` ADD  PRIMARY KEY (`cod_perfil`)
;



CREATE TABLE `Cliente`
(
  `cod_cpf_cnpj` Char(14) NOT NULL,
  `nro_cep` Int NOT NULL,
  `cod_cidade` Int NOT NULL,
  `cod_UF` Char(2) NOT NULL,
  `nom_cliente` Char(30) NOT NULL,
  `des_email` Char(30) NOT NULL,
  `nro_tel_cel` Int NOT NULL,
  `des_endereco` Char(30) NOT NULL,
  `nro_tel_fixo` Int,
  `nro_tel_cel_2` Int,
  `nro_endereco` Int,
  `des_complemento` Char(20)
)
;

CREATE INDEX `IX_Relationship4` ON `Cliente` (`nro_cep`,`cod_cidade`,`cod_UF`)
;

ALTER TABLE `Cliente` ADD  PRIMARY KEY (`cod_cpf_cnpj`)
;



CREATE TABLE `CEP`
(
  `cod_UF` Char(2) NOT NULL,
  `cod_cidade` Int NOT NULL,
  `nro_cep` Int NOT NULL
)
;

ALTER TABLE `CEP` ADD  PRIMARY KEY (`nro_cep`,`cod_cidade`,`cod_UF`)
;



CREATE TABLE `UF`
(
  `cod_UF` Char(2) NOT NULL,
  `nom_UF` Char(20) NOT NULL
)
;

ALTER TABLE `UF` ADD  PRIMARY KEY (`cod_UF`)
;



CREATE TABLE `Cidade`
(
  `cod_UF` Char(2) NOT NULL,
  `cod_cidade` Int NOT NULL,
  `nom_cidade` Char(30) NOT NULL
)
;

ALTER TABLE `Cidade` ADD  PRIMARY KEY (`cod_cidade`,`cod_UF`)
;



CREATE TABLE `OS`
(
  `nro_OS` Serial NOT NULL,
  `cod_cpf_cnpj` Char(14) NOT NULL,
  `seq_equipto` Int NOT NULL,
  `txt_reclamacao` Char(200) NOT NULL,
  `txt_observacao_acessorios` Char(200),
  `vlr_desconto` Float,
  `per_desconto` Float,
  `vlr_frete` Float
)
;

CREATE INDEX `IX_Relationship25` ON `OS` (`cod_cpf_cnpj`)
;

CREATE INDEX `IX_Relationship29` ON `OS` (`seq_equipto`)
;

ALTER TABLE `OS` ADD  PRIMARY KEY (`nro_OS`)
;


CREATE TABLE `Status`
(
  `cod_status` Int NOT NULL,
  `des_status` Char(20) NOT NULL
)
;

ALTER TABLE `Status` ADD  PRIMARY KEY (`cod_status`)
;


CREATE TABLE `Equipamento`
(
  `seq_equipto` Serial NOT NULL,
  `des_equipto` Char(30) NOT NULL,
  `des_marca` Char(30) NOT NULL,
  `des_modelo` Char(30),
  `nro_serie` Int,
  `des_componentes` Char(20)
)
;

ALTER TABLE `Equipamento` ADD  PRIMARY KEY (`seq_equipto`)
;


CREATE TABLE `Acessorio`
(
  `cod_acessorio` Serial NOT NULL,
  `nom_acessorio` Char(20) NOT NULL
)
;

ALTER TABLE `Acessorio` ADD  PRIMARY KEY (`cod_acessorio`)
;


CREATE TABLE `Peca`
(
  `cod_peca` Serial NOT NULL,
  `des_peca` Char(30) NOT NULL,
  `prc_venda` Float NOT NULL,
  `des_marca` Char(20) NOT NULL
)
;

ALTER TABLE `Peca` ADD  PRIMARY KEY (`cod_peca`)
;


CREATE TABLE `Servico`
(
  `cod_servico` Serial NOT NULL,
  `des_servico` Char(30) NOT NULL,
  `qtd_tempo_servico` Time NOT NULL,
  `vlr_servico` Char(20) NOT NULL
)
;

ALTER TABLE `Servico` ADD  PRIMARY KEY (`cod_servico`)
;


CREATE TABLE `Programa`
(
  `cod_programa` Int NOT NULL,
  `nom_programa` Char(20) NOT NULL
)
;

ALTER TABLE `Programa` ADD  PRIMARY KEY (`cod_programa`)
;


CREATE TABLE `PerfilAcesso`
(
  `cod_perfil` Int NOT NULL,
  `cod_programa` Int NOT NULL,
  `nro_ordem_menu` Int NOT NULL
)
;

ALTER TABLE `PerfilAcesso` ADD  PRIMARY KEY (`cod_perfil`,`cod_programa`)
;


CREATE TABLE `OSStatus`
(
  `nro_OS` Int NOT NULL,
  `dat_ocorrencia` Datetime NOT NULL,
  `cod_usuario` Int NOT NULL,
  `cod_status` Int NOT NULL
)
;

CREATE INDEX `IX_Relationship9` ON `OSStatus` (`cod_usuario`)
;

CREATE INDEX `IX_Relationship10` ON `OSStatus` (`cod_status`)
;

ALTER TABLE `OSStatus` ADD  PRIMARY KEY (`nro_OS`,`dat_ocorrencia`)
;


CREATE TABLE `OSAcessorio`
(
  `nro_OS` Int NOT NULL,
  `cod_acessorio` Int NOT NULL
)
;

ALTER TABLE `OSAcessorio` ADD  PRIMARY KEY (`nro_OS`,`cod_acessorio`)
;

CREATE TABLE `OSItemPeca`
(
  `nro_OS` Int NOT NULL,
  `cod_peca` Int NOT NULL,
  `qtd_peca` Int NOT NULL,
  `vlr_venda` Float NOT NULL,
  `idt_situacao` Char(20) NOT NULL
)
;

CREATE INDEX `IX_Relationship21` ON `OSItemPeca` (`cod_peca`)
;

ALTER TABLE `OSItemPeca` ADD  PRIMARY KEY (`nro_OS`)
;

CREATE TABLE `OSItemServico`
(
  `nro_OS` Int NOT NULL,
  `cod_servico` Int NOT NULL,
  `qtd_servico` Int,
  `vlr_servico` Float,
  `idt_situacao` Bool
)
;

CREATE INDEX `IX_Relationship24` ON `OSItemServico` (`cod_servico`)
;

ALTER TABLE `OSItemServico` ADD  PRIMARY KEY (`nro_OS`)
;

ALTER TABLE `Usuario` ADD CONSTRAINT `Relationship1` FOREIGN KEY (`cod_perfil`) REFERENCES `Perfil` (`cod_perfil`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Cliente` ADD CONSTRAINT `Relationship4` FOREIGN KEY (`nro_cep`, `cod_cidade`, `cod_UF`) REFERENCES `CEP` (`nro_cep`, `cod_cidade`, `cod_UF`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `PerfilAcesso` ADD CONSTRAINT `Relationship5` FOREIGN KEY (`cod_perfil`) REFERENCES `Perfil` (`cod_perfil`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `PerfilAcesso` ADD CONSTRAINT `Relationship6` FOREIGN KEY (`cod_programa`) REFERENCES `Programa` (`cod_programa`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `OSStatus` ADD CONSTRAINT `Relationship7` FOREIGN KEY (`nro_OS`) REFERENCES `OS` (`nro_OS`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `OSStatus` ADD CONSTRAINT `Relationship9` FOREIGN KEY (`cod_usuario`) REFERENCES `Usuario` (`cod_usuario`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `OSStatus` ADD CONSTRAINT `Relationship10` FOREIGN KEY (`cod_status`) REFERENCES `Status` (`cod_status`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `OSAcessorio` ADD CONSTRAINT `Relationship14` FOREIGN KEY (`nro_OS`) REFERENCES `OS` (`nro_OS`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `OSAcessorio` ADD CONSTRAINT `Relationship15` FOREIGN KEY (`cod_acessorio`) REFERENCES `Acessorio` (`cod_acessorio`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `OSItemPeca` ADD CONSTRAINT `Relationship19` FOREIGN KEY (`nro_OS`) REFERENCES `OS` (`nro_OS`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `OSItemPeca` ADD CONSTRAINT `Relationship21` FOREIGN KEY (`cod_peca`) REFERENCES `Peca` (`cod_peca`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `OSItemServico` ADD CONSTRAINT `Relationship22` FOREIGN KEY (`nro_OS`) REFERENCES `OS` (`nro_OS`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `OSItemServico` ADD CONSTRAINT `Relationship24` FOREIGN KEY (`cod_servico`) REFERENCES `Servico` (`cod_servico`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `OS` ADD CONSTRAINT `Relationship25` FOREIGN KEY (`cod_cpf_cnpj`) REFERENCES `Cliente` (`cod_cpf_cnpj`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Cidade` ADD CONSTRAINT `Relationship27` FOREIGN KEY (`cod_UF`) REFERENCES `UF` (`cod_UF`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `CEP` ADD CONSTRAINT `Relationship28` FOREIGN KEY (`cod_cidade`, `cod_UF`) REFERENCES `Cidade` (`cod_cidade`, `cod_UF`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `OS` ADD CONSTRAINT `Relationship29` FOREIGN KEY (`seq_equipto`) REFERENCES `Equipamento` (`seq_equipto`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

