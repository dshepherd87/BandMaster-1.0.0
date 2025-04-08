-- Drop tables if they exist
DROP TABLE IF EXISTS instrument;
DROP TABLE IF EXISTS instrumentType;
DROP TABLE IF EXISTS instrumentSubType;
DROP TABLE IF EXISTS personnel;

-- Create personnel table
CREATE TABLE personnel (
    personnel_UID INTEGER PRIMARY KEY AUTOINCREMENT,
    realWorld_ID TEXT,
    firstName TEXT,
    lastName TEXT,
    middleName TEXT,
    title TEXT
);

-- Create instrumentType table
CREATE TABLE instrumentType (
    instrumentType_UID INTEGER PRIMARY KEY AUTOINCREMENT,
    description TEXT
);

-- Create instrumentSubType table
CREATE TABLE instrumentSubType (
    instrumentSubType_UID INTEGER PRIMARY KEY AUTOINCREMENT,
    description TEXT
);

-- Create instrument table
CREATE TABLE instrument (
    instrument_UID INTEGER PRIMARY KEY AUTOINCREMENT,
    serialNumber TEXT,
    manufacturer TEXT NOT NULL,
    model TEXT NOT NULL,
    type_UID INTEGER NOT NULL,
    subType_UID INTEGER,
    personnel_UID INTEGER,
    FOREIGN KEY (type_UID) REFERENCES instrumentType (instrumentType_UID) ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (subType_UID) REFERENCES instrumentSubType (instrumentSubType_UID) ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (personnel_UID) REFERENCES personnel (personnel_UID) ON DELETE CASCADE ON UPDATE RESTRICT
);
