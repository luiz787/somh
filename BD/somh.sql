-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 17-Set-2017 às 17:08
-- Versão do servidor: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `somh`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `acessorio`
--

CREATE TABLE `acessorio` (
  `cod_acessorio` bigint(20) UNSIGNED NOT NULL,
  `nom_acessorio` char(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cep`
--

CREATE TABLE `cep` (
  `cod_UF` char(2) COLLATE utf8_bin NOT NULL,
  `cod_cidade` int(11) NOT NULL,
  `nro_cep` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `cep`
--

INSERT INTO `cep` (`cod_UF`, `cod_cidade`, `nro_cep`) VALUES
('MG', 7, 3033),
('MG', 9, 21312),
('MG', 8, 30367),
('MG', 11, 30392),
('MG', 10, 231321),
('MG', 1, 30350610);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidade`
--

CREATE TABLE `cidade` (
  `cod_UF` char(2) COLLATE utf8_bin NOT NULL,
  `cod_cidade` int(11) NOT NULL,
  `nom_cidade` char(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `cidade`
--

INSERT INTO `cidade` (`cod_UF`, `cod_cidade`, `nom_cidade`) VALUES
('MG', 1, 'Belo Horizonte'),
('BA', 2, 'Porto'),
('MG', 7, 'betim'),
('MG', 8, 'itatiaia'),
('MG', 9, 'qdqw'),
('MG', 10, '123'),
('MG', 11, 'belo');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `cod_cpf_cnpj` char(14) COLLATE utf8_bin NOT NULL,
  `nro_cep` int(11) NOT NULL,
  `cod_cidade` int(11) NOT NULL,
  `cod_UF` char(2) COLLATE utf8_bin NOT NULL,
  `nom_cliente` char(30) COLLATE utf8_bin NOT NULL,
  `des_email` char(30) COLLATE utf8_bin NOT NULL,
  `nro_tel_cel` int(11) NOT NULL,
  `des_endereco` char(30) COLLATE utf8_bin NOT NULL,
  `nro_tel_fixo` int(11) DEFAULT NULL,
  `nro_tel_cel_2` int(11) DEFAULT NULL,
  `nro_endereco` int(11) DEFAULT NULL,
  `des_complemento` char(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`cod_cpf_cnpj`, `nro_cep`, `cod_cidade`, `cod_UF`, `nom_cliente`, `des_email`, `nro_tel_cel`, `des_endereco`, `nro_tel_fixo`, `nro_tel_cel_2`, `nro_endereco`, `des_complemento`) VALUES
('10213683695', 30350610, 1, 'MG', 'rafael', 'rafaelneves.99@hotmail.com', 999408550, 'tobias moscoso', NULL, NULL, NULL, NULL),
('7777', 30392, 11, 'MG', 'antonio', '@antonio', 99999, 'toto', 33333, NULL, 56, 'ap 801');

-- --------------------------------------------------------

--
-- Estrutura da tabela `equipamento`
--

CREATE TABLE `equipamento` (
  `seq_equipto` bigint(20) UNSIGNED NOT NULL,
  `des_equipto` char(30) COLLATE utf8_bin NOT NULL,
  `des_marca` char(30) COLLATE utf8_bin NOT NULL,
  `des_modelo` char(30) COLLATE utf8_bin DEFAULT NULL,
  `nro_serie` int(11) DEFAULT NULL,
  `des_componentes` char(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `os`
--

CREATE TABLE `os` (
  `nro_OS` bigint(20) UNSIGNED NOT NULL,
  `cod_cpf_cnpj` char(14) COLLATE utf8_bin NOT NULL,
  `seq_equipto` bigint(20) UNSIGNED NOT NULL,
  `txt_reclamacao` char(200) COLLATE utf8_bin NOT NULL,
  `txt_observacao_acessorios` char(200) COLLATE utf8_bin DEFAULT NULL,
  `vlr_desconto` float DEFAULT NULL,
  `per_desconto` float DEFAULT NULL,
  `vlr_frete` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `osacessorio`
--

CREATE TABLE `osacessorio` (
  `nro_OS` bigint(20) UNSIGNED NOT NULL,
  `cod_acessorio` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ositempeca`
--

CREATE TABLE `ositempeca` (
  `nro_OS` bigint(20) UNSIGNED NOT NULL,
  `cod_peca` bigint(20) UNSIGNED NOT NULL,
  `qtd_peca` int(11) NOT NULL,
  `vlr_venda` float NOT NULL,
  `idt_situacao` char(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ositemservico`
--

CREATE TABLE `ositemservico` (
  `nro_OS` bigint(20) UNSIGNED NOT NULL,
  `cod_servico` bigint(20) UNSIGNED NOT NULL,
  `qtd_servico` int(11) DEFAULT NULL,
  `vlr_servico` float DEFAULT NULL,
  `idt_situacao` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `osstatus`
--

CREATE TABLE `osstatus` (
  `nro_OS` bigint(20) UNSIGNED NOT NULL,
  `dat_ocorrencia` datetime NOT NULL,
  `cod_usuario` bigint(20) UNSIGNED NOT NULL,
  `cod_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `peca`
--

CREATE TABLE `peca` (
  `cod_peca` bigint(20) UNSIGNED NOT NULL,
  `des_peca` char(30) COLLATE utf8_bin NOT NULL,
  `prc_venda` float NOT NULL,
  `des_marca` char(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfil`
--

CREATE TABLE `perfil` (
  `cod_perfil` int(11) NOT NULL,
  `des_perfil` char(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `perfil`
--

INSERT INTO `perfil` (`cod_perfil`, `des_perfil`) VALUES
(1, 'adm'),
(2, 'atendente'),
(3, 'telefonista'),
(4, 'técnico');

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfilacesso`
--

CREATE TABLE `perfilacesso` (
  `cod_perfil` int(11) NOT NULL,
  `cod_programa` int(11) NOT NULL,
  `nro_ordem_menu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `programa`
--

CREATE TABLE `programa` (
  `cod_programa` int(11) NOT NULL,
  `nom_programa` char(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `servico`
--

CREATE TABLE `servico` (
  `cod_servico` bigint(20) UNSIGNED NOT NULL,
  `des_servico` char(30) COLLATE utf8_bin NOT NULL,
  `qtd_tempo_servico` time NOT NULL,
  `vlr_servico` char(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `status`
--

CREATE TABLE `status` (
  `cod_status` int(11) NOT NULL,
  `des_status` char(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `status`
--

INSERT INTO `status` (`cod_status`, `des_status`) VALUES
(1, 'Em orçamento'),
(2, 'Orçado'),
(3, 'Aguardando cliente'),
(4, 'Recusado'),
(5, 'Aprovado'),
(6, 'Aguardando peça'),
(7, 'Pronto'),
(8, 'Avisado'),
(9, 'Entregue'),
(10, 'Garantia');

-- --------------------------------------------------------

--
-- Estrutura da tabela `uf`
--

CREATE TABLE `uf` (
  `cod_UF` char(2) COLLATE utf8_bin NOT NULL,
  `nom_UF` char(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `uf`
--

INSERT INTO `uf` (`cod_UF`, `nom_UF`) VALUES
('AC', 'Acre'),
('AL', 'Alagoas'),
('AM', 'Amazonas'),
('AP', 'Amapá'),
('BA', 'Bahia'),
('CE', 'Ceará'),
('DF', 'Distrito Federal'),
('ES', 'Espírito Santo'),
('GO', 'Goiás'),
('MA', 'Maranhão'),
('MG', 'Minas Gerais'),
('MS', 'Mato Grosso do Sul'),
('MT', 'Mato Grosso'),
('PA', 'Pará'),
('PB', 'Paraíba'),
('PE', 'Pernambuco'),
('PI', 'Piauí'),
('PR', 'Paraná'),
('RJ', 'Rio de Janeiro'),
('RN', 'Rio Grande do Norte'),
('RO', 'Rondônia'),
('RR', 'Roraima'),
('RS', 'Rio Grande do Sul'),
('SC', 'Santa Catarina'),
('SE', 'Sergipe'),
('SP', 'São Paulo'),
('TO', 'Tocantins');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `cod_usuario` bigint(20) UNSIGNED NOT NULL,
  `cod_perfil` int(11) NOT NULL,
  `nom_usuario` char(30) COLLATE utf8_bin NOT NULL,
  `txt_senha` char(100) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`cod_usuario`, `cod_perfil`, `nom_usuario`, `txt_senha`) VALUES
(1, 1, 'admin', '21232f297a57a5a743894a0e4a801fc3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acessorio`
--
ALTER TABLE `acessorio`
  ADD PRIMARY KEY (`cod_acessorio`);

--
-- Indexes for table `cep`
--
ALTER TABLE `cep`
  ADD PRIMARY KEY (`nro_cep`,`cod_cidade`,`cod_UF`),
  ADD KEY `Relationship28` (`cod_cidade`,`cod_UF`);

--
-- Indexes for table `cidade`
--
ALTER TABLE `cidade`
  ADD PRIMARY KEY (`cod_cidade`,`cod_UF`),
  ADD KEY `Relationship27` (`cod_UF`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cod_cpf_cnpj`),
  ADD KEY `IX_Relationship4` (`nro_cep`,`cod_cidade`,`cod_UF`);

--
-- Indexes for table `equipamento`
--
ALTER TABLE `equipamento`
  ADD PRIMARY KEY (`seq_equipto`);

--
-- Indexes for table `os`
--
ALTER TABLE `os`
  ADD PRIMARY KEY (`nro_OS`),
  ADD KEY `IX_Relationship25` (`cod_cpf_cnpj`),
  ADD KEY `IX_Relationship29` (`seq_equipto`);

--
-- Indexes for table `osacessorio`
--
ALTER TABLE `osacessorio`
  ADD PRIMARY KEY (`nro_OS`,`cod_acessorio`),
  ADD KEY `Relationship15` (`cod_acessorio`);

--
-- Indexes for table `ositempeca`
--
ALTER TABLE `ositempeca`
  ADD PRIMARY KEY (`nro_OS`),
  ADD KEY `IX_Relationship21` (`cod_peca`);

--
-- Indexes for table `ositemservico`
--
ALTER TABLE `ositemservico`
  ADD PRIMARY KEY (`nro_OS`),
  ADD KEY `IX_Relationship24` (`cod_servico`);

--
-- Indexes for table `osstatus`
--
ALTER TABLE `osstatus`
  ADD PRIMARY KEY (`nro_OS`,`dat_ocorrencia`),
  ADD KEY `IX_Relationship9` (`cod_usuario`),
  ADD KEY `IX_Relationship10` (`cod_status`);

--
-- Indexes for table `peca`
--
ALTER TABLE `peca`
  ADD PRIMARY KEY (`cod_peca`);

--
-- Indexes for table `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`cod_perfil`);

--
-- Indexes for table `perfilacesso`
--
ALTER TABLE `perfilacesso`
  ADD PRIMARY KEY (`cod_perfil`,`cod_programa`),
  ADD KEY `Relationship6` (`cod_programa`);

--
-- Indexes for table `programa`
--
ALTER TABLE `programa`
  ADD PRIMARY KEY (`cod_programa`);

--
-- Indexes for table `servico`
--
ALTER TABLE `servico`
  ADD PRIMARY KEY (`cod_servico`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`cod_status`);

--
-- Indexes for table `uf`
--
ALTER TABLE `uf`
  ADD PRIMARY KEY (`cod_UF`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cod_usuario`),
  ADD UNIQUE KEY `nom_usuario` (`nom_usuario`),
  ADD KEY `IX_Relationship1` (`cod_perfil`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acessorio`
--
ALTER TABLE `acessorio`
  MODIFY `cod_acessorio` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cidade`
--
ALTER TABLE `cidade`
  MODIFY `cod_cidade` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `equipamento`
--
ALTER TABLE `equipamento`
  MODIFY `seq_equipto` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `os`
--
ALTER TABLE `os`
  MODIFY `nro_OS` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `peca`
--
ALTER TABLE `peca`
  MODIFY `cod_peca` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `servico`
--
ALTER TABLE `servico`
  MODIFY `cod_servico` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `cod_usuario` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `cep`
--
ALTER TABLE `cep`
  ADD CONSTRAINT `Relationship28` FOREIGN KEY (`cod_cidade`,`cod_UF`) REFERENCES `cidade` (`cod_cidade`, `cod_UF`);

--
-- Limitadores para a tabela `cidade`
--
ALTER TABLE `cidade`
  ADD CONSTRAINT `Relationship27` FOREIGN KEY (`cod_UF`) REFERENCES `uf` (`cod_UF`);

--
-- Limitadores para a tabela `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `Relationship4` FOREIGN KEY (`nro_cep`,`cod_cidade`,`cod_UF`) REFERENCES `cep` (`nro_cep`, `cod_cidade`, `cod_UF`);

--
-- Limitadores para a tabela `os`
--
ALTER TABLE `os`
  ADD CONSTRAINT `Relationship25` FOREIGN KEY (`cod_cpf_cnpj`) REFERENCES `cliente` (`cod_cpf_cnpj`),
  ADD CONSTRAINT `Relationship29` FOREIGN KEY (`seq_equipto`) REFERENCES `equipamento` (`seq_equipto`);

--
-- Limitadores para a tabela `osacessorio`
--
ALTER TABLE `osacessorio`
  ADD CONSTRAINT `Relationship14` FOREIGN KEY (`nro_OS`) REFERENCES `os` (`nro_OS`),
  ADD CONSTRAINT `Relationship15` FOREIGN KEY (`cod_acessorio`) REFERENCES `acessorio` (`cod_acessorio`);

--
-- Limitadores para a tabela `ositempeca`
--
ALTER TABLE `ositempeca`
  ADD CONSTRAINT `Relationship19` FOREIGN KEY (`nro_OS`) REFERENCES `os` (`nro_OS`),
  ADD CONSTRAINT `Relationship21` FOREIGN KEY (`cod_peca`) REFERENCES `peca` (`cod_peca`);

--
-- Limitadores para a tabela `ositemservico`
--
ALTER TABLE `ositemservico`
  ADD CONSTRAINT `Relationship22` FOREIGN KEY (`nro_OS`) REFERENCES `os` (`nro_OS`),
  ADD CONSTRAINT `Relationship24` FOREIGN KEY (`cod_servico`) REFERENCES `servico` (`cod_servico`);

--
-- Limitadores para a tabela `osstatus`
--
ALTER TABLE `osstatus`
  ADD CONSTRAINT `Relationship10` FOREIGN KEY (`cod_status`) REFERENCES `status` (`cod_status`),
  ADD CONSTRAINT `Relationship7` FOREIGN KEY (`nro_OS`) REFERENCES `os` (`nro_OS`),
  ADD CONSTRAINT `Relationship9` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`cod_usuario`);

--
-- Limitadores para a tabela `perfilacesso`
--
ALTER TABLE `perfilacesso`
  ADD CONSTRAINT `Relationship5` FOREIGN KEY (`cod_perfil`) REFERENCES `perfil` (`cod_perfil`),
  ADD CONSTRAINT `Relationship6` FOREIGN KEY (`cod_programa`) REFERENCES `programa` (`cod_programa`);

--
-- Limitadores para a tabela `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `Relationship1` FOREIGN KEY (`cod_perfil`) REFERENCES `perfil` (`cod_perfil`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
