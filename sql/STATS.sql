-- --------------------------------------------------------
-- Сервер:                       127.0.0.1
-- Версія сервера:               5.5.23 - MySQL Community Server (GPL)
-- ОС сервера:                   Win64
-- HeidiSQL Версія:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for statscollector
DROP DATABASE IF EXISTS `statscollector`;
CREATE DATABASE IF NOT EXISTS `statscollector` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `statscollector`;


-- Dumping structure for таблиця statscollector.account
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `CURRENCY_ID` bigint(20) unsigned DEFAULT NULL,
  `ORGANIZATION_ID` bigint(20) unsigned DEFAULT NULL,
  `CUSTOM_ID` varchar(256) DEFAULT NULL,
  `NAME` varchar(256) NOT NULL DEFAULT 'NoName',
  `DESCRIPTION` text,
  `VALUE` double NOT NULL DEFAULT '0',
  `START_DATE` datetime DEFAULT NULL,
  `END_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IS_ON_HAND` tinyint(1) NOT NULL DEFAULT '0',
  `IS_OWN` tinyint(1) NOT NULL DEFAULT '0',
  `ENABLED` tinyint(1) NOT NULL DEFAULT '1',
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for таблиця statscollector.account_state
DROP TABLE IF EXISTS `account_state`;
CREATE TABLE IF NOT EXISTS `account_state` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` bigint(20) unsigned DEFAULT NULL,
  `VALUE` double DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `BEFORE_OPERATION_ID` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for таблиця statscollector.currency
DROP TABLE IF EXISTS `currency`;
CREATE TABLE IF NOT EXISTS `currency` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(128) DEFAULT NULL,
  `ABBREVIATION` varchar(16) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `IS_DEFAULT` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for таблиця statscollector.exchange_rates
DROP TABLE IF EXISTS `exchange_rates`;
CREATE TABLE IF NOT EXISTS `exchange_rates` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `CURRENCY_ID` bigint(20) unsigned DEFAULT NULL,
  `DEFAULT_CURRENCY_ID` bigint(20) unsigned DEFAULT NULL,
  `OPERATION_ID` bigint(20) unsigned DEFAULT NULL,
  `VALUE` double NOT NULL DEFAULT '1',
  `BUY_RATE` double DEFAULT NULL,
  `SELL_RATE` double DEFAULT NULL,
  `MB_RATE` double DEFAULT NULL,
  `IS_BUY_OR_SELL` tinyint(1) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for таблиця statscollector.operation
DROP TABLE IF EXISTS `operation`;
CREATE TABLE IF NOT EXISTS `operation` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `START_CURRENCY_ID` bigint(20) unsigned DEFAULT NULL,
  `END_CURRENCY_ID` bigint(20) unsigned DEFAULT NULL,
  `FROM_ACCOUNT_ID` bigint(20) unsigned DEFAULT NULL,
  `TO_ACCOUNT_ID` bigint(20) unsigned DEFAULT NULL,
  `CUSTOM_OPERATION_ID` bigint(20) unsigned DEFAULT NULL,
  `START_VALUE` double DEFAULT NULL,
  `END_VALUE` double DEFAULT NULL,
  `OPERATION_TYPE` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `DATE` date DEFAULT NULL,
  `UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for таблиця statscollector.operation_class
DROP TABLE IF EXISTS `operation_class`;
CREATE TABLE IF NOT EXISTS `operation_class` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for таблиця statscollector.operation_custom
DROP TABLE IF EXISTS `operation_custom`;
CREATE TABLE IF NOT EXISTS `operation_custom` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `OPERATION_CLASS_ID` bigint(20) unsigned DEFAULT NULL,
  `NAME` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for таблиця statscollector.organization
DROP TABLE IF EXISTS `organization`;
CREATE TABLE IF NOT EXISTS `organization` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(128) DEFAULT NULL,
  `DESCRIPTION` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
