---------------------------------------------------------------
--  VS02_IS_PRODUCT : 통합검색 제품 리스트 뷰
---------------------------------------------------------------
CREATE OR REPLACE VIEW VS02_IS_PRODUCT AS
WITH PRODUCT_CLASSIFICATION_OIDS
         AS (SELECT PRODUCT_OID,
                    LISTAGG(TARGET_OID, ',')
                            WITHIN GROUP ( ORDER BY TARGET_OID ) AS OIDS
             FROM   TS02_AM_PRODUCT_REL
             WHERE  TARGET_OBJECT = 'AMCP'
             GROUP  BY PRODUCT_OID),
     APPLICATION_OIDS
         AS (SELECT PRODUCT_OID,
                    LISTAGG(TARGET_OID, ',')
                            WITHIN GROUP ( ORDER BY TARGET_OID ) AS OIDS
             FROM   TS02_AM_PRODUCT_REL
             WHERE  TARGET_OBJECT = 'AMCA'
             GROUP  BY PRODUCT_OID),
     MARKET_OIDS
         AS (SELECT PRODUCT_OID,
                    LISTAGG(TARGET_OID, ',')
                            WITHIN GROUP ( ORDER BY TARGET_OID ) AS OIDS
             FROM   TS02_AM_PRODUCT_REL
             WHERE  TARGET_OBJECT = 'AMCM'
             GROUP  BY PRODUCT_OID),
     FUNCTION_OIDS
         AS (SELECT PRODUCT_OID,
                    LISTAGG(TARGET_OID, ',')
                            WITHIN GROUP ( ORDER BY TARGET_OID ) AS OIDS
             FROM   TS02_AM_PRODUCT_REL
             WHERE  TARGET_OBJECT = 'AMCF'
             GROUP  BY PRODUCT_OID)
SELECT
    PRODUCT.*,
    P.OIDS AS PRODUCT_CLASSIFICATION_OIDS,
    A.OIDS AS APPLICATION_OIDS,
    M.OIDS AS MARKET_OIDS,
    F.OIDS AS FUNCTION_OIDS
FROM   VS02_PRODUCT PRODUCT
           LEFT OUTER JOIN PRODUCT_CLASSIFICATION_OIDS P
                           ON PRODUCT.OID = P.PRODUCT_OID
           LEFT OUTER JOIN APPLICATION_OIDS A
                           ON PRODUCT.OID = A.PRODUCT_OID
           LEFT OUTER JOIN MARKET_OIDS M
                           ON PRODUCT.OID = M.PRODUCT_OID
           LEFT OUTER JOIN FUNCTION_OIDS F
                           ON PRODUCT.OID = F.PRODUCT_OID;


---------------------------------------------------------------
--  VS02_IS_APPLICATION : 통합검색 어플리케이션 뷰
---------------------------------------------------------------
CREATE OR REPLACE VIEW VS02_IS_APPLICATION AS
WITH DETAIL_APPLICATION_ITEMS
         AS (SELECT PARENT_OID,
                    LISTAGG(OID, ',') WITHIN GROUP ( ORDER BY OID ) AS OIDS
FROM   VS02_CATEGORY
WHERE  CATEGORY_TYPE = 'AMCFAPPL'
  AND OID != '00000000002'
  AND OID != '00000000000'
GROUP  BY PARENT_OID
    ),
    PRODUCT_OIDS
    AS (
SELECT TARGET_OID,
    LISTAGG( PRODUCT_OID, ',' ) WITHIN GROUP ( ORDER BY PRODUCT_OID ) AS OIDS
FROM TS02_AM_PRODUCT_REL
WHERE TARGET_OBJECT = 'AMCA'
GROUP BY TARGET_OID
    )
SELECT
    CATEGORY.*,
    A.OIDS AS DETAIL_APPLICATION_OIDS,
    P.OIDS AS PRODUCT_OIDS
FROM VS02_CATEGORY CATEGORY
         LEFT OUTER JOIN DETAIL_APPLICATION_ITEMS A
                         ON CATEGORY.OID = A.PARENT_OID
         LEFT OUTER JOIN PRODUCT_OIDS P
                         ON CATEGORY.OID = P.TARGET_OID
WHERE CATEGORY_TYPE = 'AMCFAPPL'
  AND OID != '00000000002';


---------------------------------------------------------------
--  VS02_IS_MARKET : 통합검색 마켓 뷰
---------------------------------------------------------------
CREATE OR REPLACE VIEW VS02_IS_MARKET AS
WITH PRODUCT_OIDS
         AS (
         SELECT TARGET_OID,
                LISTAGG( PRODUCT_OID, ',' ) WITHIN GROUP ( ORDER BY PRODUCT_OID ) AS OIDS
         FROM TS02_AM_PRODUCT_REL
         WHERE TARGET_OBJECT = 'AMCM'
         GROUP BY TARGET_OID
     ), MATERIAL_NAMES 
     	AS (
        SELECT
            TARGET_OID,
            LISTAGG( MN, ',') WITHIN GROUP( ORDER BY MN ) AS NAMES
        FROM (
            SELECT TARGET_OID, MATERIAL_NAME AS MN, COUNT(*)
            FROM VS02_PRODUCT_REL
            WHERE TARGET_OBJECT = 'AMCM'
            GROUP BY TARGET_OID, MATERIAL_NAME
            ) 
        GROUP BY TARGET_OID
        )
SELECT
    CATEGORY.*,
    P.OIDS AS PRODUCT_OIDS,
    N.NAMES AS MATERIAL_NAMES
FROM VS02_CATEGORY CATEGORY
         LEFT OUTER JOIN PRODUCT_OIDS P
                         ON CATEGORY.OID = P.TARGET_OID
         LEFT OUTER JOIN MATERIAL_NAMES N 
         				 ON CATEGORY.OID = N.TARGET_OID
WHERE CATEGORY_TYPE = 'AMCFMRKT'
  AND OID != '00000000003';

---------------------------------------------------------------
--  VS02_IS_FUNCTION : 통합검색 Function 뷰
---------------------------------------------------------------
CREATE OR REPLACE VIEW VS02_IS_FUNCTION AS
WITH PRODUCT_OIDS
         AS (
         SELECT TARGET_OID,
                LISTAGG( PRODUCT_OID, ',' ) WITHIN GROUP ( ORDER BY PRODUCT_OID ) AS OIDS
         FROM TS02_AM_PRODUCT_REL
         WHERE TARGET_OBJECT = 'AMCF'
         GROUP BY TARGET_OID
     )
SELECT
    CATEGORY.*,
    P.OIDS AS PRODUCT_OIDS
FROM VS02_CATEGORY CATEGORY
         LEFT OUTER JOIN PRODUCT_OIDS P
                         ON CATEGORY.OID = P.TARGET_OID
WHERE CATEGORY_TYPE = 'AMCFFUNC'
  AND OID != '00000000004';