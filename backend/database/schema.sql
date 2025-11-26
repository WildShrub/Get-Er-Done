CREATE TABLE userSchedules (
    username VARCHAR(50) PRIMARY KEY NOT NULL,
    userSchedule JSON
);

CREATE TABLE userTasklist (
    username VARCHAR(50) PRIMARY KEY NOT NULL,
    userTasklist JSON
);

CREATE TABLE userProfile (
    username VARCHAR(50) PRIMARY KEY NOT NULL,
    email VARCHAR(100) NOT NULL,
    profile picture BYTEA
);

CREATE TABLE userGroup (
    username VARCHAR(50) PRIMARY KEY NOT NULL,
    groupIDList JSON
);

CREATE TABLE userLogin (
    username VARCHAR(50) PRIMARY KEY NOT NULL,
    passwordHash VARCHAR(256) NOT NULL
);

CREATE TABLE groupTasklists (
    groupID VARCHAR(50) PRIMARY KEY NOT NULL,
    groupTasklist JSON
);

CREATE TABLE groupSchedules (
    groupID VARCHAR(50) PRIMARY KEY NOT NULL,
    groupSchedule JSON
);

CREATE TABLE groupProfile (
    groupID VARCHAR(50) PRIMARY KEY NOT NULL,
    groupName VARCHAR(100) NOT NULL
);

CREATE TABLE groupUsers (
    groupID VARCHAR(50) PRIMARY KEY NOT NULL,
    usersAndPermissions JSON
);

CREATE TABLE notifications (
    notificationKey VARCHAR(100) PRIMARY KEY NOT NULL,
    setTime TIMESTAMP NOT NULL,
    email VARCHAR(100) NOT NULL,
);