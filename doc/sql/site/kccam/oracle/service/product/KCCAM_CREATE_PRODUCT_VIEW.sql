---------------------------------------------------------------
--  VS02_PRODUCT : 제품 - 제품의 다국어테이블 뷰
---------------------------------------------------------------
CREATE OR REPLACE VIEW VS02_PRODUCT AS
SELECT
    P.OID               AS OID,
    L.LANG              AS LANG,
    P.PRODUCT_CODE      AS PRODUCT_CODE,
    P.STD_PRODUCT_CODE  AS STD_PRODUCT_CODE,
    L.NAME              AS NAME,
    P.MATERIAL_OID      AS MATERIAL_OID,

    P.RELEASE_YN        AS RELEASE_YN,
    P.MAIN_FUNC_OID     AS MAIN_FUNC_OID,

    L.DESCR             AS DESCR,
    L.INPUT_USER        AS INPUT_USER,
    L.INPUT_DATE        AS INPUT_DATE,

    L.MOD_USER          AS MOD_USER,
    L.MOD_DATE          AS MOD_DATE,

    P.CUSTOM_FIELD1     AS CUSTOM_FIELD1,
    P.CUSTOM_FIELD2     AS CUSTOM_FIELD2,
    P.CUSTOM_FIELD3     AS CUSTOM_FIELD3,
    P.CUSTOM_FIELD4     AS CUSTOM_FIELD4,
    P.CUSTOM_FIELD5     AS CUSTOM_FIELD5

FROM TS02_AM_PRODUCT P
JOIN TS02_AM_PRODUCT_LANG L
ON P.OID = L.OID;



---------------------------------------------------------------
--  TS02_AM_PRODUCT_REL : 제품 - 제품 관계테이블 뷰
---------------------------------------------------------------
CREATE OR REPLACE VIEW VS02_PRODUCT_REL AS
SELECT
    P.OID               AS OID,
    P.LANG              AS LANG,
    P.PRODUCT_CODE      AS PRODUCT_CODE,
    P.STD_PRODUCT_CODE  AS STD_PRODUCT_CODE,
    P.NAME              AS NAME,
    P.MATERIAL_OID      AS MATERIAL_OID,

    P.RELEASE_YN        AS RELEASE_YN,
    P.MAIN_FUNC_OID     AS MAIN_FUNC_OID,
    P.DESCR             AS DESCR,
    P.INPUT_USER        AS INPUT_USER,
    P.INPUT_DATE        AS INPUT_DATE,

    P.MOD_USER          AS MOD_USER,
    P.MOD_DATE          AS MOD_DATE,

    P.CUSTOM_FIELD1     AS CUSTOM_FIELD1,
    P.CUSTOM_FIELD2     AS CUSTOM_FIELD2,
    P.CUSTOM_FIELD3     AS CUSTOM_FIELD3,
    P.CUSTOM_FIELD4     AS CUSTOM_FIELD4,
    P.CUSTOM_FIELD5     AS CUSTOM_FIELD5,

    REL.PRODUCT_OID     AS PRODUCT_OID,
    REL.TARGET_OID      AS TARGET_OID,
    REL.TARGET_OBJECT   AS TARGET_OBJECT,
    PART.NAME			AS MATERIAL_NAME
FROM VS02_PRODUCT P
JOIN TS02_AM_PRODUCT_REL REL
ON P.OID = REL.PRODUCT_OID
INNER JOIN TS02_TB_PART PART
ON P.MATERIAL_OID = PART.OID;