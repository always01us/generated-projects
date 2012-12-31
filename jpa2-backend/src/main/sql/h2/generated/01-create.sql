--
-- (c) Copyright 2005-2012 JAXIO, http://www.jaxio.com
-- Source code generated by Celerio, a Jaxio product
-- Want to use Celerio within your company? email us at info@jaxio.com
-- Follow us on twitter: @springfuse
-- Template pack-backend-jpa:src/main/sql/h2/01-create.p.vm.sql
--
DROP ALL objects;

CREATE TABLE ACCOUNT (
	ID CHAR(32) not null
	,LOGIN VARCHAR(100) not null
	,PASSWORD VARCHAR(100) not null
	,EMAIL VARCHAR(100) not null
	,IS_ENABLED BOOLEAN(1)
	,CIVILITY CHAR(2)
	,FIRST_NAME VARCHAR(100)
	,LAST_NAME VARCHAR(100)
	,BIRTH_DATE TIMESTAMP
	,DESCRIPTION VARCHAR(255)
	,ADDRESS_ID INTEGER(10)
	,CREATION_DATE TIMESTAMP
	,CREATION_AUTHOR VARCHAR(200)
	,LAST_MODIFICATION_DATE TIMESTAMP
	,LAST_MODIFICATION_AUTHOR VARCHAR(200)
	,VERSION INTEGER(10) default 0
);
CREATE UNIQUE INDEX IF NOT EXISTS ACCOUNT_UNIQUE_1_INDEX_E
	ON ACCOUNT (LOGIN);
CREATE UNIQUE INDEX IF NOT EXISTS ACCOUNT_UNIQUE_2_INDEX_E
	ON ACCOUNT (EMAIL);
CREATE INDEX IF NOT EXISTS ACCOUNT_FK_1_INDEX_E
	ON ACCOUNT (ADDRESS_ID);
CREATE PRIMARY KEY 
	ON ACCOUNT (ID);

CREATE TABLE ACCOUNT_ROLE (
	ACCOUNT_ID CHAR(32) not null
	,ROLE_ID INTEGER(10) not null
);
CREATE INDEX IF NOT EXISTS ACCOUNT_ROLE_FK_1_INDEX_C
	ON ACCOUNT_ROLE (ACCOUNT_ID);
CREATE INDEX IF NOT EXISTS ACCOUNT_ROLE_FK_2_INDEX_C
	ON ACCOUNT_ROLE (ROLE_ID);
CREATE PRIMARY KEY 
	ON ACCOUNT_ROLE (ACCOUNT_ID,ROLE_ID);

CREATE TABLE ADDRESS (
	ID INTEGER(10) not null
	,STREET_NAME VARCHAR(100)
	,CITY VARCHAR(100) not null
	,VERSION INTEGER(10) default 0
);
ALTER TABLE ADDRESS ALTER COLUMN ID IDENTITY;

CREATE TABLE BOOK (
	ID INTEGER(10) not null
	,ACCOUNT_ID CHAR(32)
	,TITLE VARCHAR(100) not null
	,NUMBER_OF_PAGES INTEGER(10) not null
	,VERSION INTEGER(10) default 0
);
CREATE INDEX IF NOT EXISTS BOOK_FK_1_INDEX_1
	ON BOOK (ACCOUNT_ID);
ALTER TABLE BOOK ALTER COLUMN ID IDENTITY;

CREATE TABLE DOCUMENT (
	ID CHAR(32) not null
	,ACCOUNT_ID CHAR(32) not null
	,DOCUMENT_CONTENT_TYPE VARCHAR(100) not null
	,DOCUMENT_SIZE INTEGER(10) not null
	,DOCUMENT_FILE_NAME VARCHAR(100) not null
	,DOCUMENT_BINARY VARBINARY(2147483647)
	,VERSION INTEGER(10) default 0
);
CREATE INDEX IF NOT EXISTS DOCUMENT_FK_1_INDEX_6
	ON DOCUMENT (ACCOUNT_ID);
CREATE PRIMARY KEY 
	ON DOCUMENT (ID);

CREATE TABLE LEGACY (
	NAME VARCHAR(16) not null
	,CODE VARCHAR(8) not null
	,DEPT INTEGER(10) not null
	,EXTRA_INFO VARCHAR(100) not null
);
CREATE PRIMARY KEY 
	ON LEGACY (CODE,DEPT,NAME);

CREATE TABLE MORE_TYPES_DEMO (
	ID INTEGER(10) not null
	,NUMBER_INT INTEGER(10)
	,NUMBER_LONG BIGINT(19)
	,NUMBER_DOUBLE DOUBLE(17)
	,NUMBER_FLOAT REAL(7)
	,NUMBER_BIG_INTEGER DECIMAL(20)
	,NUMBER_BIG_DECIMAL DECIMAL(20,2)
	,DATE_JAVA_TEMPORAL_DATE DATE
	,DATE_JAVA_TEMPORAL_TIMESTAMP TIMESTAMP
	,DATE_JODA DATE
	,DATE_TIME_JODA TIMESTAMP
	,VERSION INTEGER(10) default 0
);
ALTER TABLE MORE_TYPES_DEMO ALTER COLUMN ID IDENTITY;

CREATE TABLE ROLE (
	ID INTEGER(10) not null
	,ROLE_NAME VARCHAR(100) not null
);
CREATE UNIQUE INDEX IF NOT EXISTS ROLE_UNIQUE_1_INDEX_2
	ON ROLE (ROLE_NAME);
ALTER TABLE ROLE ALTER COLUMN ID IDENTITY;


ALTER TABLE ACCOUNT 
	ADD CONSTRAINT ACCOUNT_FK_1
		FOREIGN KEY (ADDRESS_ID)
			REFERENCES ADDRESS (ID);
ALTER TABLE ACCOUNT_ROLE 
	ADD CONSTRAINT ACCOUNT_ROLE_FK_2
		FOREIGN KEY (ROLE_ID)
			REFERENCES ROLE (ID);
ALTER TABLE ACCOUNT_ROLE 
	ADD CONSTRAINT ACCOUNT_ROLE_FK_1
		FOREIGN KEY (ACCOUNT_ID)
			REFERENCES ACCOUNT (ID);
ALTER TABLE BOOK 
	ADD CONSTRAINT BOOK_FK_1
		FOREIGN KEY (ACCOUNT_ID)
			REFERENCES ACCOUNT (ID);
ALTER TABLE DOCUMENT 
	ADD CONSTRAINT DOCUMENT_FK_1
		FOREIGN KEY (ACCOUNT_ID)
			REFERENCES ACCOUNT (ID);
