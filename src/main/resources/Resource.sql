CREATE TYPE EMPLOYEE_TYPE AS ENUM('NORMAL','MANAGER');
CREATE TYPE TRANSACTION_TYPE AS ENUM('WITHDREW','DEPOSIT');
CREATE TYPE ACC_STATUS AS ENUM( 'OPEN','BANNED');
create table if not exists users(national_code varchar(50) primary key);
create table if not exists accs(accId serial primary key,
    Status acc_status,
    password varchar(50),
    amount integer ,
    branchName varchar(50),
    userID VARCHAR (50) ,
    CONSTRAINT fk_customer
    FOREIGN KEY(userId) REFERENCES users(national_code) );
create table if not exists bank(branchName varchar(50) UNIQUE );
create table if not exists noramlEmployee(national_code varchar(50),jobtype EMPLOYEE_TYPE,branchName varchar(50),
    CONSTRAINT fk_customer
    FOREIGN KEY(branchName) REFERENCES bank(branchName));
create table if not exists manager(national_code varchar(50),jobtype EMPLOYEE_TYPE,branchName varchar(50) unique ,
                                          CONSTRAINT fk_customer
                                              FOREIGN KEY(branchName) REFERENCES bank(branchName));
create table if not exists creditCard(cardId serial primary key ,cvv2 varchar(50),password varchar(50),expireDate DATE ,status ACC_STATUS,accId INTEGER,
                                      CONSTRAINT fk_customer
                                          FOREIGN KEY(accId) REFERENCES accs(accId));

create table if not exists transactions(transactionId serial primary key ,transactionType transaction_type,amount INTEGER,accId INTEGER,
                                        CONSTRAINT fk_customer
                                            FOREIGN KEY(accId) REFERENCES accs(accId));
create table if not exists transfer(transferId serial primary key ,amount INTEGER,senderId INTEGER,reciverId INTEGER, CONSTRAINT fk_customer
    FOREIGN KEY(senderId) REFERENCES accs(accId),
    FOREIGN KEY(reciverId) REFERENCES accs(accId)
                                   )

