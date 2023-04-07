---------------------------------------------------------------
-- VS02_DOC_REL  : 문서정보 - Rel 테이블 조인 뷰
---------------------------------------------------------------
CREATE
OR REPLACE VIEW VS02_DOC_REL AS
SELECT DOC.OID                  AS OID
     , DOC.COMPANY_OID          AS COMPANY_OID
     , DOC.SYSTEM_OID           AS SYSTEM_OID
     , DOC.CATEGORY_OID         AS CATEGORY_OID
     , DOC.TITLE                AS TITLE

     , DOC.DOC_TYPE             AS DOC_TYPE
     , DOC.CURRENT_VERSION_OID  AS CURRENT_VERSION_OID
     , DOC.SECRET_YN            AS SECRET_YN
     , DOC.PWD                  AS PWD
     , DOC.INPUT_USER_INFO_LIST AS INPUT_USER_INFO_LIST

     , DOC.INPUT_USER           AS INPUT_USER
     , DOC.INPUT_DATE           AS INPUT_DATE
     , DOC.MOD_USER             AS MOD_USER
     , DOC.MOD_DATE             AS MOD_DATE
     , DOC.DEL_USER             AS DEL_USER

     , DOC.DEL_DATE             AS DEL_DATE
     , DOC.CUSTOM_FIELD1        AS CUSTOM_FIELD1
     , DOC.CUSTOM_FIELD2        AS CUSTOM_FIELD2
     , DOC.CUSTOM_FIELD3        AS CUSTOM_FIELD3
     , DOC.CUSTOM_FIELD4        AS CUSTOM_FIELD4
     , DOC.CUSTOM_FIELD5        AS CUSTOM_FIELD5

     , REL.TARGET_OID           AS TARGET_OID
     , REL.TARGET_OBJECT        AS TARGET_OBJECT
     , REL.LAST_OPEN_DATE       AS LAST_OPEN_DATE
FROM TS02_TB_DOC DOC
         JOIN TS02_TB_DOC_REL REL
              ON DOC.OID = REL.DOC_OID;
