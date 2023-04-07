---------------------------------------------------------------
--  VS02_MATERIAL : 소재 구분 - 소재 구분의 다국어 테이블 뷰
---------------------------------------------------------------
CREATE OR REPLACE VIEW VS02_MATERIAL AS
SELECT
    M.OID               AS OID,
    M.SYSTEM_OID		AS SYSTEM_OID,
    L.LANG              AS LANG,

    L.NAME              AS NAME,
    L.CLASS_NAME        AS CLASS_NAME,
    L.DESCR				AS DESCR,
    
    L.INPUT_USER        AS INPUT_USER,
    L.INPUT_DATE        AS INPUT_DATE,

    L.MOD_USER          AS MOD_USER,
    L.MOD_DATE          AS MOD_DATE

FROM TS02_AM_MATERIAL M
JOIN TS02_AM_MATERIAL_LANG L
ON M.OID = L.OID;