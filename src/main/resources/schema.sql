CREATE SCHEMA IF NOT EXISTS achievements;

CREATE  TABLE achievements.achievement (
	id                   integer  NOT NULL ,
	name                 varchar(100)   ,
	image                text   ,
	"type"               integer  NOT NULL ,
	CONSTRAINT pk_achievement_id_0 PRIMARY KEY ( id )
 );

CREATE  TABLE achievements.award (
	id                   integer  NOT NULL ,
	name                 varchar(100)  NOT NULL ,
	cost                 integer  NOT NULL ,
	"type"               integer  NOT NULL ,
	image                text   ,
	CONSTRAINT pk_award_id PRIMARY KEY ( id )
 );

CREATE  TABLE achievements.member (
	id                   serial  NOT NULL ,
	email                varchar(100)  NOT NULL ,
	name                 varchar(100)   ,
	avatar               text   ,
	CONSTRAINT pk_member_id PRIMARY KEY ( id ),
	CONSTRAINT idx_member UNIQUE ( email )
 );

CREATE  TABLE achievements.purchase (
	id                   serial  NOT NULL ,
	award_id             integer  NOT NULL ,
	member_d             integer  NOT NULL ,
	dt                   date  NOT NULL ,
	cost                 integer  NOT NULL ,
	note                 text ,
	status               integer,
	CONSTRAINT pk_purchase_id PRIMARY KEY ( id ),
	CONSTRAINT fk_purchase_award FOREIGN KEY ( award_id ) REFERENCES achievements.award( id )   ,
	CONSTRAINT fk_purchase_member FOREIGN KEY ( member_d ) REFERENCES achievements.member( id )
 );

CREATE  TABLE achievements.winner (
	id                   serial  NOT NULL ,
	member_id            integer  NOT NULL ,
	"year"               integer  NOT NULL ,
	"month"              integer  NOT NULL ,
	achievement_id       integer  NOT NULL ,
	note                 text   ,
	points               integer   ,
	"type"               integer  NOT NULL ,
	CONSTRAINT pk_achievement_id PRIMARY KEY ( id ),
	CONSTRAINT fk_winner_achievement FOREIGN KEY ( achievement_id ) REFERENCES achievements.achievement( id )   ,
	CONSTRAINT fk_winner_member FOREIGN KEY ( member_id ) REFERENCES achievements.member( id )
 );
