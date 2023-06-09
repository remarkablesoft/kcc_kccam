INSERT INTO TS02_TB_CATEGORY_LANG
( OID, LANG, NAME, DESCR, INPUT_USER, INPUT_DATE, MOD_USER,MOD_DATE )
SELECT OID, 'KO', NAME, DESCR, INPUT_USER, INPUT_DATE, MOD_USER, MOD_DATE FROM TS02_TB_CATEGORY;


-- 백업용 생성
CREATE TABLE TS02_TB_CATEGORY_BAKUP
AS
SELECT * FROM TS02_TB_CATEGORY;


-- 실제는 제일 나중에 작업 다 한후에 작업처리할 것.
-- TS02_TB_CATEGORY 에서 TS02_TB_CATEGORY_LANG에 있는 컬럼 제거

ALTER TABLE TS02_TB_CATEGORY DROP COLUMN NAME;
ALTER TABLE TS02_TB_CATEGORY DROP COLUMN DESCR;
ALTER TABLE TS02_TB_CATEGORY DROP COLUMN INPUT_USER;
ALTER TABLE TS02_TB_CATEGORY DROP COLUMN INPUT_DATE;
ALTER TABLE TS02_TB_CATEGORY DROP COLUMN MOD_USER;
ALTER TABLE TS02_TB_CATEGORY DROP COLUMN MOD_DATE;
ALTER TABLE TS02_TB_CATEGORY DROP COLUMN DEL_USER;
ALTER TABLE TS02_TB_CATEGORY DROP COLUMN DEL_DATE;