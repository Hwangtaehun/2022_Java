DROP DATABASE IF EXISTS  madang;
DROP USER IF EXISTS  madang@localhost;
use mysql;
create user madang@localhost identified by 'madang';
create database madang;
grant all privileges on madang.* to madang@localhost with grant option;
commit;
 
USE madang;

CREATE TABLE Manager (
  manid INTEGER PRIMARY KEY,
  title VARCHAR(30)
);

CREATE TABLE Connection (
  conid  INTEGER PRIMARY KEY,
  title  VARCHAR(30)
);

CREATE TABLE Banks (
  id       INTEGER PRIMARY KEY,
  manid    INTEGER,
  price    INTEGER,
  date     DATE,
  conid    INTEGER,
  inform   VARCHAR(50),
  balance  INTEGER,
  FOREIGN KEY (manid) REFERENCES Manager(manid),
  FOREIGN KEY (conid) REFERENCES Connection(conid)
);

INSERT INTO Manager VALUES(10100, 'food');
INSERT INTO Manager VALUES(10101, 'eat out');
INSERT INTO Manager VALUES(10102, 'delivery');
INSERT INTO Manager VALUES(10200, 'housing');
INSERT INTO Manager VALUES(10201, 'home lant');
INSERT INTO Manager VALUES(10202, 'electricity');
INSERT INTO Manager VALUES(10203, 'gas');
INSERT INTO Manager VALUES(10204, 'water');
INSERT INTO Manager VALUES(10205, 'maintenance');
INSERT INTO Manager VALUES(10206, 'loan rate');
INSERT INTO Manager VALUES(10300, 'clothes');
INSERT INTO Manager VALUES(10301, 'bags');
INSERT INTO Manager VALUES(10302, 'watch');
INSERT INTO Manager VALUES(10303, 'shoes');
INSERT INTO Manager VALUES(10400, 'necessities');
INSERT INTO Manager VALUES(10401, 'cleaning');
INSERT INTO Manager VALUES(10402, 'washing');
INSERT INTO Manager VALUES(10500, 'communication expense');
INSERT INTO Manager VALUES(10501, 'cell phone');
INSERT INTO Manager VALUES(10502, 'internet');
INSERT INTO Manager VALUES(10503, 'TV');
INSERT INTO Manager VALUES(10504, 'home phone');
INSERT INTO Manager VALUES(10505, 'monthly installment');
INSERT INTO Manager VALUES(10600, 'personal');
INSERT INTO Manager VALUES(10601, 'transportation fee');
INSERT INTO Manager VALUES(10602, 'hobby');
INSERT INTO Manager VALUES(10603, 'travel');
INSERT INTO Manager VALUES(10604, 'cultural life');
INSERT INTO Manager VALUES(10605, 'exercise');
INSERT INTO Manager VALUES(10700, 'tax');
INSERT INTO Manager VALUES(10800, 'insurance');
INSERT INTO Manager VALUES(10801, 'actual insurance');
INSERT INTO Manager VALUES(10802, 'whole life insurance');
INSERT INTO Manager VALUES(10803, 'cancer insurance');
INSERT INTO Manager VALUES(10900, 'raise');
INSERT INTO Manager VALUES(10901, 'guide');
INSERT INTO Manager VALUES(10902, 'raise hospital');
INSERT INTO Manager VALUES(10903, 'school');
INSERT INTO Manager VALUES(10904, 'private educational institute');
INSERT INTO Manager VALUES(11000, 'save');
INSERT INTO Manager VALUES(11100, 'family event');
INSERT INTO Manager VALUES(11101, 'parent allowance');
INSERT INTO Manager VALUES(20100, 'month pay');
INSERT INTO Manager VALUES(20200, 'insurance payback');
INSERT INTO Manager VALUES(20300, 'bonus');
INSERT INTO Manager VALUES(20301, 'allowance');
INSERT INTO Manager VALUES(20302, 'company bonus');
INSERT INTO Manager VALUES(999999, 'null');

INSERT INTO Connection VALUES(10000, 'online');
INSERT INTO Connection VALUES(10100, 'openmarket');
INSERT INTO Connection VALUES(10200, 'socialcommerce');
INSERT INTO Connection VALUES(10300, 'portal site');
INSERT INTO Connection VALUES(10400, 'secondhand');
INSERT INTO Connection VALUES(10500, 'online synthesis mall');
INSERT INTO Connection VALUES(10600, 'online wholesale');
INSERT INTO Connection VALUES(20000, 'offline');
INSERT INTO Connection VALUES(20100, 'wholesale');
INSERT INTO Connection VALUES(20200, 'retailsales');
INSERT INTO Connection VALUES(20300, 'department store');
INSERT INTO Connection VALUES(20400, 'supermarket');
INSERT INTO Connection VALUES(20500, 'traditional market');
INSERT INTO Connection VALUES(30000, 'country');
INSERT INTO Connection VALUES(30100, 'national');
INSERT INTO Connection VALUES(30200, 'local');
INSERT INTO Connection VALUES(40000, 'family');
INSERT INTO Connection VALUES(999999, 'null');

INSERT INTO Banks VALUES(1, 20301, 50000, STR_TO_DATE('2023-01-01','%Y-%m-%d'), 40000, 'bonus money', 50000);
INSERT INTO Banks VALUES(2, 10602, 1000, STR_TO_DATE('2023-01-02','%Y-%m-%d'), 10300, 'rent', null);