PRAGMA foreign_keys = ON;


-- Tabela USER
CREATE TABLE USER (
    UserId INTEGER PRIMARY KEY AUTOINCREMENT,
    Username TEXT NOT NULL UNIQUE,
    Password TEXT NOT NULL,
    Salary REAL
);

-- Tabela CATEGORY
CREATE TABLE CATEGORY (
    CategoryId INTEGER PRIMARY KEY AUTOINCREMENT,
    Category TEXT NOT NULL UNIQUE
);

-- Tabela PAYMENT_WAY
CREATE TABLE PAYMENT_WAY (
    PaymentWayId INTEGER PRIMARY KEY AUTOINCREMENT,
    PaymentWay TEXT NOT NULL UNIQUE
);


-- Tabela PERSON
CREATE TABLE PERSON (
    PersonId INTEGER PRIMARY KEY AUTOINCREMENT,
    Person TEXT NOT NULL UNIQUE
);

-- Tabela BRAND
CREATE TABLE BRAND (
    BrandId INTEGER PRIMARY KEY AUTOINCREMENT,
    Brand TEXT NOT NULL UNIQUE
);

-- Tabela CREDIT_CARD
CREATE TABLE CREDIT_CARD (
    CreditCardId INTEGER PRIMARY KEY AUTOINCREMENT,
    idBrand INTEGER NOT NULL,
    Name TEXT NOT NULL UNIQUE,
    ClosingDay INTEGER NOT NULL,
    FOREIGN KEY (idBrand) REFERENCES BRAND (BrandId)
);

-- Table ITEM_PURCHASED
CREATE TABLE ITEM_PURCHASED(
    ItemPurchasedId INTEGER PRIMARY KEY AUTOINCREMENT,
    dayOfPurchased DATE NOT NULL,
    idCategory INTEGER NOT NULL,
    Price REAL NOT NULL,
    idPaymentWay INTEGER NOT NULL,
    Description TEXT NOT NULL,
    PayForPerson INTEGER NOT NULL DEFAULT 0, -- 0 PARA FALSE, 1 PARA TRUE
    idPerson INTEGER,
    FOREIGN KEY (idCategory) REFERENCES CATEGORY (CategoryId),
    FOREIGN KEY (idPaymentWay) REFERENCES PAYMENT_WAY (PaymentWayId)
    FOREIGN KEY (idPerson) REFERENCES PERSON (PersonId)
);

-- Table FIXED_VALUE
CREATE TABLE FIXED_VALUE(
    FixedValueId INTEGER PRIMARY KEY AUTOINCREMENT,
    idCategory INTEGER NOT NULL,
    Price REAL NOT NULL,
    idPaymentWay INTEGER NOT NULL,
    PayForPerson INTEGER NOT NULL DEFAULT 0, -- 0 PARA FALSE, 1 PARA TRUE
    Variable INTEGER NOT NULL DEFAULT 0, -- 0 PARA FALSE, 1 PARA TRUE
    Description TEXT NOT NULL,
    idPerson INTEGER,
    FOREIGN KEY (idCategory) REFERENCES CATEGORY(CategoryId),
    FOREIGN KEY (idPaymentWay) REFERENCES PAYMENT_WAY(PaymentWayId)
    FOREIGN KEY (idPerson) REFERENCES PERSON (PersonId)
);

--Table PARCEL_VALUES
CREATE TABLE PARCEL_VALUES(
    ParcelValuesId INTEGER PRIMARY KEY AUTOINCREMENT,
    TotalValue REAL NOT NULL,
    IdCategory INTEGER NOT NULL,
    Parcels INTEGER NOT NULL,
    Description TEXT NOT NULL,
    PayForPerson INTEGER NOT NULL DEFAULT 0, -- 0 PARA FALSE, 1 PARA TRUE
    idPerson INTEGER,
    FOREIGN KEY (IdCategory) REFERENCES CATEGORY (CategoryId)
    FOREIGN KEY (idPerson) REFERENCES PERSON (PersonId)
);

-- Table VALUE_TO_RECEIVE
CREATE TABLE VALUE_TO_RECEIVE(
    ValueToReceiveId INTEGER PRIMARY KEY AUTOINCREMENT,
    PersonToReceive INTEGER NOT NULL,
    TotalPrice REAL NOT NULL,
    IdPaymentWay INTEGER NOT NULL,
    Type INTEGER NOT NULL DEFAULT 0, -- 0: UNIQUE, 1: FIXED, 2: PARCEL
    Parcels INTEGER NOT NULL DEFAULT 1,
    Description TEXT NOT NULL,
    FOREIGN KEY (IdPaymentWay) REFERENCES PAYMENT_WAY (PaymentWayId)
);

-- Table MONTH_VALUES
CREATE TABLE MONTH_VALUES(
    Month INTEGER NOT NULL,
    Year INTEGER NOT NULL,
    TotalToPay REAL NOT NULL DEFAULT 0,
    TotalParcel REAL NOT NULL DEFAULT 0,
    PRIMARY KEY (Month, Year)
);

