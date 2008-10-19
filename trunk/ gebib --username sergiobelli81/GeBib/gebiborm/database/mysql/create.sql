--
-- Database structure for database 'biblioteca'
--
CREATE DATABASE IF NOT EXISTS biblioteca;

USE biblioteca;

--
-- Table structure for table 'autori'
--
CREATE TABLE IF NOT EXISTS autori (
  ID_AUTORE int(10) unsigned NOT NULL auto_increment,
  COGNOME varchar(255) NOT NULL default '',
  NOME varchar(255) NOT NULL default '',
  PRIMARY KEY  (ID_AUTORE),
  UNIQUE KEY ID (ID_AUTORE),
  KEY ID_2 (ID_AUTORE)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table 'libri'
--
CREATE TABLE IF NOT EXISTS libri (
  ID_LIBRO int(10) unsigned NOT NULL auto_increment,
  TITOLO varchar(255) NOT NULL default '',
  ISBN varchar(17) default NULL,
  PRIMARY KEY  (ID_LIBRO),
  UNIQUE KEY ID (ID_LIBRO),
  KEY ID_2 (ID_LIBRO)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table 'properties'
--
CREATE TABLE IF NOT EXISTS properties (
  ID tinyint(3) unsigned NOT NULL auto_increment,
  KEY varchar(255) NOT NULL default '',
  VALUE varchar(255) NOT NULL default '',
  PRIMARY KEY  (ID),
  UNIQUE KEY ID (ID,KEY),
  KEY ID_2 (ID)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table 'publicazioni'
--
CREATE TABLE IF NOT EXISTS publicazioni (
  ID_LIBRO int(10) unsigned NOT NULL default '0',
  ID_AUTORE int(10) unsigned NOT NULL default '0',  
  PRIMARY KEY PK_PUBLICAZIONI (ID_AUTORE,ID_LIBRO),  
  FOREIGN KEY FK_LIBRO_ID (ID_LIBRO) REFERENCES LIBRI(ID_LIBRO),
  FOREIGN KEY FK_AUTORE_ID (ID_AUTORE) REFERENCES AUTORI(ID_AUTORE)  
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
