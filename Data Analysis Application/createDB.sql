
CREATE TABLE YELP_USER
(
    YELPING_SINCE VARCHAR2(10),
    VOTES_FUNNY NUMBER,
    VOTES_USEFUL NUMBER,
    VOTES_COOL NUMBER,
    REVIEWS_COUNT NUMBER,
    USERNAME VARCHAR2(255),
    USER_ID VARCHAR2(30),
    FANS NUMBER,
    AVERAGE_STARS FLOAT,
    USER_TYPE VARCHAR2(10),
    COMPLIMENTS_FUNNY NUMBER,
    COMPLIMENTS_COOL NUMBER,
    COMPLIMENTS_WRITER NUMBER,
    COMPLIMENTS_PHOTOS NUMBER,
    COMPLIMENTS_HOT NUMBER,
    COMPLIMENTS_MORE NUMBER,
    COMPLIMENTS_PLAIN NUMBER,
    COMPLIMENTS_NOTE NUMBER,
    COMPLIMENTS_PROFILE NUMBER,
    COMPLIMENTS_CUTE NUMBER,
    COMPLIMENTS_LIST NUMBER,
    PRIMARY KEY (USER_ID)
);



CREATE TABLE FRIENDS
(
    USER_ID VARCHAR2(30),
    FRIEND_ID VARCHAR2(30),
    PRIMARY KEY (USER_ID, FRIEND_ID),
    FOREIGN KEY (USER_ID) REFERENCES YELP_USER (USER_ID) ON DELETE CASCADE
);



CREATE TABLE ELITE
(
    USER_ID varchar2(30),
    ELITE number,
    PRIMARY KEY (USER_ID,ELITE),
    FOREIGN KEY (USER_ID) REFERENCES YELP_USER (USER_ID) ON DELETE CASCADE
);


CREATE TABLE BUSINESS
(
    BUSINESS_ID VARCHAR2(30),
    FULL_ADDRESS VARCHAR2(255),
    OPEN Number ,
    CITY VARCHAR2(255),
    REVIEWS_COUNT NUMBER,
    BUSINESS_NAME VARCHAR2(255),
    NEIGHBORHOODS VARCHAR2(255),
    LONGITUDE FLOAT,
    STATE VARCHAR2(255),
    STARS FLOAT,
    LATITUDE FLOAT,
    BUSINESS_TYPE VARCHAR2(20),
    PRIMARY KEY (BUSINESS_ID)
);


CREATE TABLE BUSINESS_HOURS
(
    DAY_OF_WEEK VARCHAR2(20),
    FROM_HOUR FLOAT,
    TO_HOUR FLOAT,
    BUSINESS_ID VARCHAR2(30),
    PRIMARY KEY (BUSINESS_ID, DAY_OF_WEEK),
    FOREIGN KEY (BUSINESS_ID) REFERENCES BUSINESS (BUSINESS_ID) ON DELETE CASCADE
);


CREATE TABLE NEIGHBORHOODS
(
	NEIGHBOR_NAME VARCHAR2(100),
	BUSINESS_ID VARCHAR2(30),
	PRIMARY KEY (BUSINESS_ID, NEIGHBOR_NAME),
    FOREIGN KEY (BUSINESS_ID) REFERENCES BUSINESS (BUSINESS_ID) ON DELETE CASCADE
);


CREATE TABLE MAIN_CATEGORIES
(
    CATEGORY_NAME VARCHAR2(255),
    BUSINESS_ID VARCHAR2(30),
    PRIMARY KEY (BUSINESS_ID, CATEGORY_NAME),
    FOREIGN KEY (BUSINESS_ID) REFERENCES BUSINESS (BUSINESS_ID) ON DELETE CASCADE
);


CREATE TABLE SUB_CATEGORIES
(
    CATEGORY_NAME VARCHAR2(255),
    BUSINESS_ID VARCHAR2(30),
    PRIMARY KEY (BUSINESS_ID, CATEGORY_NAME),
    FOREIGN KEY (BUSINESS_ID) REFERENCES BUSINESS (BUSINESS_ID) ON DELETE CASCADE
);


CREATE TABLE BUSINESS_ATTRIBUTES
(
    ATTRIBUTE_NAME VARCHAR2(255),
    BUSINESS_ID VARCHAR2(30),
    PRIMARY KEY (BUSINESS_ID, ATTRIBUTE_NAME),
    FOREIGN KEY (BUSINESS_ID) REFERENCES BUSINESS (BUSINESS_ID) ON DELETE CASCADE
);


CREATE TABLE CHECKIN
(
    CHECKIN_DAY VARCHAR2(30),
    CHECKIN_HOUR NUMBER,
    CHECKIN_COUNT NUMBER,
    BUSINESS_ID VARCHAR2(30),
    FOREIGN KEY(BUSINESS_ID) REFERENCES BUSINESS (BUSINESS_ID) ON DELETE CASCADE
);


CREATE TABLE REVIEWS
(
    REVIEWS_ID VARCHAR2(30),
    USER_ID VARCHAR2(30),
    BUSINESS_ID VARCHAR2(30),
    VOTES_FUNNY NUMBER,
    VOTES_USEFUL NUMBER,
    VOTES_COOL NUMBER,
    STARS NUMBER,
    REVIEW_DATE VARCHAR2(20),
    REVIEW_TEXT CLOB,
    REVIEW_TYPE VARCHAR2(20),
    PRIMARY KEY (REVIEWS_ID),
    FOREIGN KEY (BUSINESS_ID) REFERENCES BUSINESS (BUSINESS_ID) ON DELETE CASCADE,
    FOREIGN KEY (USER_ID) REFERENCES YELP_USER(USER_ID) ON DELETE CASCADE
);

CREATE INDEX INDEX_REVIEWS_YELP_USER
ON REVIEWS (USER_ID);

CREATE INDEX INDEX_REVIEWS_BUSINESS
ON REVIEWS (BUSINESS_ID);


