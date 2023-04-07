---------------------------------------------------------------
--  TS02_AM_PRODUCT : 제품 테이블
---------------------------------------------------------------
CREATE TABLE TS02_AM_PRODUCT
(
    OID                 VARCHAR2(11)        NOT NULL,
    PRODUCT_CODE        VARCHAR2(20)        NULL,
    STD_PRODUCT_CODE    VARCHAR2(20)        NULL,
    MATERIAL_OID        VARCHAR2(11)        NULL,

    RELEASE_YN          CHAR(1)             	NULL,
    MAIN_FUNC_OID       VARCHAR2(11)        	NULL,

    CUSTOM_FIELD1       VARCHAR2(255)       NULL,
    CUSTOM_FIELD2       VARCHAR2(255)       NULL,
    CUSTOM_FIELD3       VARCHAR2(255)       NULL,
    CUSTOM_FIELD4       VARCHAR2(255)       NULL,
    CUSTOM_FIELD5       VARCHAR2(255)       NULL,

    CONSTRAINT TS02_AM_PRODUCT PRIMARY KEY( OID )
);

COMMENT ON TABLE TS02_AM_PRODUCT IS '제품 테이블';

COMMENT ON COLUMN TS02_AM_PRODUCT.OID IS 'OID';
COMMENT ON COLUMN TS02_AM_PRODUCT.PRODUCT_CODE IS '제품코드';
COMMENT ON COLUMN TS02_AM_PRODUCT.STD_PRODUCT_CODE IS '기준 제품코드';
COMMENT ON COLUMN TS02_AM_PRODUCT.MATERIAL_OID IS '소재구분 OID(EMC, CERAMIC SUBSTRATES 등)';

COMMENT ON COLUMN TS02_AM_PRODUCT.RELEASE_YN IS '노출여부';
COMMENT ON COLUMN TS02_AM_PRODUCT.MAIN_FUNC_OID IS 'MAIN FUNCTION OID';

COMMENT ON COLUMN TS02_AM_PRODUCT.CUSTOM_FIELD1 IS '커스텀 필드1';
COMMENT ON COLUMN TS02_AM_PRODUCT.CUSTOM_FIELD2 IS '커스텀 필드2';
COMMENT ON COLUMN TS02_AM_PRODUCT.CUSTOM_FIELD3 IS '커스텀 필드3';
COMMENT ON COLUMN TS02_AM_PRODUCT.CUSTOM_FIELD4 IS '커스텀 필드4';
COMMENT ON COLUMN TS02_AM_PRODUCT.CUSTOM_FIELD5 IS '커스텀 필드5';



---------------------------------------------------------------
--  TS02_AM_PRODUCT_LANG : 제품 다국어 테이블
---------------------------------------------------------------
CREATE TABLE TS02_AM_PRODUCT_LANG
(
    OID                 VARCHAR2(11)        	NOT NULL,
    LANG                VARCHAR2(2 BYTE) 		NOT NULL,
    NAME                VARCHAR2(255)       	NULL,
    DESCR               VARCHAR2(4000)      	NULL,
    INPUT_USER          VARCHAR2(11)        	NULL,
    INPUT_DATE          DATE DEFAULT SYSDATE	NULL,

    MOD_USER            VARCHAR2(11)        	NULL,
    MOD_DATE            DATE                	NULL,

    CONSTRAINT TS02_AM_PRODUCT_LANG PRIMARY KEY( OID, LANG )
);

COMMENT ON TABLE TS02_AM_PRODUCT_LANG IS '제품 다국어 테이블';

COMMENT ON COLUMN TS02_AM_PRODUCT_LANG.OID IS 'OID';
COMMENT ON COLUMN TS02_AM_PRODUCT_LANG.LANG IS 'LANG 한글 ko, 영어 en';
COMMENT ON COLUMN TS02_AM_PRODUCT_LANG.NAME IS '제품명';
COMMENT ON COLUMN TS02_AM_PRODUCT_LANG.DESCR IS '설명';

COMMENT ON COLUMN TS02_AM_PRODUCT_LANG.INPUT_USER IS '등록자';
COMMENT ON COLUMN TS02_AM_PRODUCT_LANG.INPUT_DATE IS '등록일시';

COMMENT ON COLUMN TS02_AM_PRODUCT_LANG.MOD_USER IS '수정자';
COMMENT ON COLUMN TS02_AM_PRODUCT_LANG.MOD_DATE IS '수정일시';


---------------------------------------------------------------
--  TS02_AM_PRODUCT_REL : 제품분류(App/Market) - 제품 관계 테이블
---------------------------------------------------------------
CREATE TABLE TS02_AM_PRODUCT_REL
(
    PRODUCT_OID         VARCHAR2(11)        	NOT NULL,
    TARGET_OID          VARCHAR2(11)        	NOT NULL,
    TARGET_OBJECT       VARCHAR2(4)         	NULL,
    INPUT_DATE          DATE DEFAULT SYSDATE	NULL,

    CONSTRAINT TS02_AM_PRODUCT_REL PRIMARY KEY( PRODUCT_OID, TARGET_OID )
);

COMMENT ON TABLE TS02_AM_PRODUCT_REL IS '제품분류(APP/MARKET) - 제품 관계 테이블';

COMMENT ON COLUMN TS02_AM_PRODUCT_REL.PRODUCT_OID IS '관계정보 주체 OID';
COMMENT ON COLUMN TS02_AM_PRODUCT_REL.TARGET_OID IS '관계정보 타겟 OID';
COMMENT ON COLUMN TS02_AM_PRODUCT_REL.TARGET_OBJECT IS '관계정보 타겟 오브젝트';
COMMENT ON COLUMN TS02_AM_PRODUCT_REL.INPUT_DATE IS '등록일시';
