---------------------------------------------------------------
--  TS02_AM_LANG : 다국어 테이블
---------------------------------------------------------------
CREATE TABLE TS02_AM_LANG
(
    OID                 VARCHAR2(11)        NOT NULL,
    TARGET_OID          VARCHAR2(11)        NOT NULL,
    TARGET_TYPE         VARCHAR2(8)         NULL,
    LANG                VARCHAR2(4)         NOT NULL,
 	INPUT_DATE 			DATE DEFAULT SYSDATE NOT NULL,

    CONSTRAINT TS02_AM_LANG PRIMARY KEY( OID )
);

COMMENT ON TABLE TS02_AM_LANG IS '다국어 테이블';

COMMENT ON COLUMN TS02_AM_LANG.OID IS 'OID';
COMMENT ON COLUMN TS02_AM_LANG.TARGET_OID IS '타겟 OID';
COMMENT ON COLUMN TS02_AM_LANG.TARGET_TYPE IS '타겟 타입';
COMMENT ON COLUMN TS02_AM_LANG.LANG IS '언어';
COMMENT ON COLUMN TS02_AM_LANG.INPUT_DATE IS '등록일시';



---------------------------------------------------------------
--  TS02_AM_LANG_ITEM : 다국어 아이템 테이블
---------------------------------------------------------------
CREATE TABLE TS02_AM_LANG_ITEM
(
    LANG_OID        VARCHAR2(11)            NOT NULL,
    LANG_KEY        VARCHAR2(11)            NOT NULL,
    LANG_VAL        VARCHAR2(4000)          NULL,
    CONSTRAINT TS02_AM_LANG_ITEM PRIMARY KEY( LANG_OID, LANG_KEY )
);

COMMENT ON TABLE TS02_AM_LANG_ITEM IS '다국어 아이템 테이블';

COMMENT ON COLUMN TS02_AM_LANG_ITEM.LANG_OID IS '다국어 테이블 OID';
COMMENT ON COLUMN TS02_AM_LANG_ITEM.LANG_KEY IS '다국어 키';
COMMENT ON COLUMN TS02_AM_LANG_ITEM.LANG_VAL IS '다국어 값';
