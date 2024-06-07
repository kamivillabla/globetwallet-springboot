CREATE DATABASE dbwallet;
USE dbwallet;

/* Tabla de Usuarios */
CREATE TABLE Users (
    UserID int AUTO_INCREMENT PRIMARY KEY,
    UserName varchar(45) NOT NULL,
    UserLastName varchar(45) NOT NULL,
    UserEmail varchar(45) UNIQUE NOT NULL,
    UserPassword varchar(255) NOT NULL,
    UserRUT varchar(10) UNIQUE NOT NULL
);

/* Tabla de Monedas */
CREATE TABLE Currencies (
    CurrencyID int AUTO_INCREMENT PRIMARY KEY,
    CurrencyName varchar(45) NOT NULL,
    CurrencySymbol varchar(10) NOT NULL,
    CurrencyValue float NOT NULL CHECK (CurrencyValue > 0)
);

/* Tabla de Cuentas */
CREATE TABLE Accounts (
    AccountID int AUTO_INCREMENT PRIMARY KEY,
    UserID int NOT NULL,
    AccountBalance decimal(15,2) CHECK (AccountBalance >= 0),
    CurrencyID int NOT NULL,
    AccountType varchar(45) NOT NULL,
    BankName varchar(45) NOT NULL,
    AccountNumber varchar(45) NOT NULL,
    ExpiryDate varchar(5) NOT NULL, -- Formato MM/YY
    AccountHolderName varchar(45) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (CurrencyID) REFERENCES Currencies(CurrencyID)
);

/* Insertar tipos de cuenta predeterminados */
CREATE TABLE AccountTypes (
    AccountTypeID int AUTO_INCREMENT PRIMARY KEY,
    AccountTypeName varchar(45) NOT NULL
);

INSERT INTO AccountTypes (AccountTypeName) VALUES
('Cuenta Corriente'),
('Cuenta Vista');

/* Tabla de Transacciones */
CREATE TABLE Transactions (
    TransactionID int AUTO_INCREMENT PRIMARY KEY,
    SenderAccountID int NULL,
    ReceiverAccountID int NULL,
    TransactionAmount decimal(15,2) NOT NULL CHECK (TransactionAmount >= 0),
    TransactionDate date NOT NULL,
    TransactionType varchar(45) NOT NULL,
    transactionCurrency VARCHAR(10) NOT NULL,
    FOREIGN KEY (SenderAccountID) REFERENCES Accounts(AccountID),
    FOREIGN KEY (ReceiverAccountID) REFERENCES Accounts(AccountID)
);
