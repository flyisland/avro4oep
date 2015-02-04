DROP TABLE IF EXISTS area;
CREATE TABLE IF NOT EXISTS area(userId  VARCHAR(255), areaId  VARCHAR(255)) as SELECT * FROM CSVREAD('/home/oracle/loaddata/user.log', 'userId	areaId',  'charset=UTF-8 fieldSeparator=' || CHAR(9));
CREATE INDEX idxUserId on area(userId);
SELECT COUNT(*) FROM area;
