DROP TABLE IF EXISTS mulu;
CREATE TABLE IF NOT EXISTS mulu(pageId int, pageName VARCHAR(255), parentId int, s1 VARCHAR(255), s2 VARCHAR(255)) as SELECT * FROM CSVREAD('/home/oracle/loaddata/mulu.log', 'pageId	pageName	parentId	s1	s2',  'charset=UTF-8 fieldSeparator=' || CHAR(9));
CREATE INDEX idxPageId on mulu(pageId);
SELECT COUNT(*) FROM mulu;