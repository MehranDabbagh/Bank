create table if not exists bank(branchName varchar(50) UNIQUE);
create table if not exists manager(national_code varchar(50),jobtype varchar(50),branchName varchar(50) unique , CONSTRAINT fk_customer FOREIGN KEY(branchName) REFERENCES bank(branchName));
create table if not exists noramlEmployee(national_code varchar(50),jobtype varchar (50),branchName varchar(50) not null);
create table if not exists users(national_code varchar(50) unique not null ,password  varchar(50) not null ,branchName varchar(50) );
CREATE TABLE if not exists Account
(
    accId            VARCHAR(255) NOT NULL,
    status           INTEGER,
    password         VARCHAR(255),
    amount           INTEGER,
    branchName       VARCHAR(255),
    foul             INTEGER,
    userNationalCode VARCHAR(255),
    CONSTRAINT pk_account PRIMARY KEY (accId)
);
create table if not exists creditCard(cardId varchar(50) primary key,cvv2 varchar(50) not null ,password varchar(50) not null,expireDate DATE not null ,status varchar(50) not null,accId varchar ,foul integer not null,
                                                     CONSTRAINT fk_customer
                                                        FOREIGN KEY(accId) REFERENCES Account(accId));
create table if not exists transactions(transactionId varchar (50) primary key ,transactionType varchar(50) not null,amount INTEGER  not null,accId varchar(50)  not null ,transactionDate DATE ,
                                                      CONSTRAINT fk_customer
                                                          FOREIGN KEY(accId) REFERENCES Account(accId));
create table if not exists transfer(transferId varchar(50) primary key ,amount INTEGER not null,senderId varchar(50) not null,reciverId varchar(50) not null,transferDate DATE NOT NULL  , CONSTRAINT fk_customer
                   FOREIGN KEY(senderId) REFERENCES creditCard(cardId),
                   FOREIGN KEY(reciverId) REFERENCES creditCard(cardId)
                                                  );