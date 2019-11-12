DROP TABLE IF EXISTS transport;
CREATE TABLE transport (
	id 			UUID 			PRIMARY KEY NOT NULL,
 	name		VARCHAR(155) 	NOT NULL,
 	latitude	FLOAT 			NOT NULL,
 	longitude	FLOAT 			NOT NULL,
 	workload	INT				NOT NULL
);

DROP TABLE IF EXISTS card;
CREATE TABLE card (
	card_id 	VARCHAR(155) 	PRIMARY KEY NOT NULL,
 	password	VARCHAR(155) 	NOT NULL
);

DROP TABLE IF EXISTS bus_stop;
CREATE TABLE bus_stop (
	bus_stop_id 	VARCHAR(155) 	PRIMARY KEY NOT NULL,
 	bus_stop_name	VARCHAR(155) 	NOT NULL,
 	latitude		VARCHAR(20)		NOT NULL,
 	longitude		VARCHAR(20)		NOT NULL
);

DROP TABLE IF EXISTS validation;
CREATE TABLE validation (
	bus_stop_id 	VARCHAR(155) 	PRIMARY KEY NOT NULL,
 	card_id 		VARCHAR(155) 	NOT NULL
);