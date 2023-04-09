---------------------------------------------------------------
-- TS02_AM_NEWSROOM  : 뉴스룸 테이블
---------------------------------------------------------------
CREATE TABLE TS02_AM_NEWSROOM(

    OID                                 VARCHAR2(11)    NOT NULL,
    LANG                                VARCHAR2(2 BYTE)        NOT NULL,
    TITLE                               VARCHAR2(225 BYTE)      NULL,
    NEWSROOM_CONTENTS                   CLOB                    NULL,

    INPUT_DATE                          DATE                    DEFAULT SYSDATE,
    MOD_DATE                            DATE                    NULL,
    DEL_DATE                            DATE                    NULL,

    VIEW_CNT							NUMBER(10)  			DEFAULT '0' NULL,

    CUSTOM_FIELD1 						VARCHAR2(255) 			NULL,
    CUSTOM_FIELD2 						VARCHAR2(255) 			NULL,
    CUSTOM_FIELD3 						VARCHAR2(255) 			NULL,
    CUSTOM_FIELD4 						VARCHAR2(255) 			NULL,
    CUSTOM_FIELD5 						VARCHAR2(255) 			NULL,

    CONSTRAINT TS02_AM_NEWSROOM PRIMARY KEY( OID )

);

COMMENT ON TABLE TS02_AM_NEWSROOM IS '뉴스룸 테이블';

COMMENT ON COLUMN TS02_AM_NEWSROOM.OID IS 'OID';
COMMENT ON COLUMN TS02_AM_NEWSROOM.LANG IS 'LANG 한글 KO, 영어 EN, 중국어 CN';
COMMENT ON COLUMN TS02_AM_NEWSROOM.TITLE IS '뉴스명';
COMMENT ON COLUMN TS02_AM_NEWSROOM.NEWSROOM_CONTENTS IS '뉴스 내용';

COMMENT ON COLUMN TS02_AM_NEWSROOM.VIEW_CNT IS '조회수';

COMMENT ON COLUMN TS02_AM_NEWSROOM.INPUT_DATE IS '등록일시';
COMMENT ON COLUMN TS02_AM_NEWSROOM.MOD_DATE IS '수정일시';
COMMENT ON COLUMN TS02_AM_NEWSROOM.DEL_DATE IS '삭제일시';

COMMENT ON COLUMN TS02_AM_NEWSROOM.CUSTOM_FIELD1 IS '커스텀 필드1';
COMMENT ON COLUMN TS02_AM_NEWSROOM.CUSTOM_FIELD2 IS '커스텀 필드2';
COMMENT ON COLUMN TS02_AM_NEWSROOM.CUSTOM_FIELD3 IS '커스텀 필드3';
COMMENT ON COLUMN TS02_AM_NEWSROOM.CUSTOM_FIELD4 IS '커스텀 필드4';
COMMENT ON COLUMN TS02_AM_NEWSROOM.CUSTOM_FIELD5 IS '커스텀 필드5';
