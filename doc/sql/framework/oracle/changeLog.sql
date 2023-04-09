-- 초기 설정 이후 site에 필요한 퀴리 추가해주시면 됩니다.
-- 2021.04.19 TS02_TB_CATEGORY 수정일, 수정자 컬럼 추가
ALTER TABLE TS02_TB_CATEGORY ADD MOD_DATE DATE NULL;
ALTER TABLE TS02_TB_CATEGORY ADD MOD_USER VARCHAR2(11) NULL;

COMMENT ON COLUMN TS02_TB_CATEGORY.MOD_DATE IS '수정일';
COMMENT ON COLUMN TS02_TB_CATEGORY.MOD_USER IS '수정자';

-- 2021.04.20 CategorySQL update부분 수정일 조건 추가
<if test="modDate != null"> MOD_DATE = #{modDate}, </if>
<if test="modDate == null"> MOD_DATE = SYSDATE, </if>

-- 2021.04.27 TS02_ 접두사 추가한 테이블명으로 변경
ALTER TABLE TB_API_KEY RENAME TO TS02_TB_API_KEY;
ALTER TABLE TB_AUDIT_FILE_DOWN RENAME TO TS02_TB_AUDIT_FILE_DOWN;
ALTER TABLE TB_AUDIT_VIEW RENAME TO TS02_TB_AUDIT_VIEW;
ALTER TABLE TB_AUDIT_VISIT RENAME TO TS02_TB_AUDIT_VISIT;
ALTER TABLE TB_BATCH_PROCESS RENAME TO TS02_TB_BATCH_PROCESS;

ALTER TABLE TB_BOARD RENAME TO TS02_TB_BOARD;
ALTER TABLE TB_BRANCH RENAME TO TS02_TB_BRANCH;
ALTER TABLE TB_CATEGORY RENAME TO TS02_TB_CATEGORY;
ALTER TABLE TB_CODE RENAME TO TS02_TB_CODE;
ALTER TABLE TB_COMPANY RENAME TO TS02_TB_COMPANY;

ALTER TABLE TB_CONTENTS RENAME TO TS02_TB_CONTENTS;
ALTER TABLE TB_DEVICE RENAME TO TS02_TB_DEVICE;
ALTER TABLE TB_DOC RENAME TO TS02_TB_DOC;
ALTER TABLE TB_DOC_REL RENAME TO TS02_TB_DOC_REL;
ALTER TABLE TB_DOC_VERSION RENAME TO TS02_TB_DOC_VERSION;

ALTER TABLE TB_DRAW_CELL RENAME TO TS02_TB_DRAW_CELL;
ALTER TABLE TB_FILE RENAME TO TS02_TB_FILE;
ALTER TABLE TB_GROUP RENAME TO TS02_TB_GROUP;
ALTER TABLE TB_MSG RENAME TO TS02_TB_MSG;
ALTER TABLE TB_MSG_BOOKING RENAME TO TS02_TB_MSG_BOOKING;

ALTER TABLE TB_MSG_GROUP RENAME TO TS02_TB_MSG_GROUP;
ALTER TABLE TB_MSG_GROUP_USER_REL RENAME TO TS02_TB_MSG_GROUP_USER_REL;
ALTER TABLE TB_MSG_SEND_HIST RENAME TO TS02_TB_MSG_SEND_HIST;
ALTER TABLE TB_MSG_SEND_RESULT RENAME TO TS02_TB_MSG_SEND_RESULT;
ALTER TABLE TB_MSG_TEMPLATE RENAME TO TS02_TB_MSG_TEMPLATE;

ALTER TABLE TB_OBJECT_REL RENAME TO TS02_TB_OBJECT_REL;
ALTER TABLE TB_PART RENAME TO TS02_TB_PART;
ALTER TABLE TB_POSTING RENAME TO TS02_TB_POSTING;
ALTER TABLE TB_POSTING_AUDIT_VIEW RENAME TO TS02_TB_POSTING_AUDIT_VIEW;
ALTER TABLE TB_RELATIONSHIP RENAME TO TS02_TB_RELATIONSHIP;

ALTER TABLE TB_RIGHT RENAME TO TS02_TB_RIGHT;
ALTER TABLE TB_ROLE RENAME TO TS02_TB_ROLE;
ALTER TABLE TB_ROLE_USER_REL RENAME TO TS02_TB_ROLE_USER_REL;
ALTER TABLE TB_STORAGE_FILE RENAME TO TS02_TB_STORAGE_FILE;
ALTER TABLE TB_SYSTEM_DETAIL RENAME TO TS02_TB_SYSTEM_DETAIL;

ALTER TABLE TB_THUMBNAIL RENAME TO TS02_TB_THUMBNAIL;
ALTER TABLE TB_USER RENAME TO TS02_TB_USER;
ALTER TABLE TB_USER_DEVICE_REL RENAME TO TS02_TB_USER_DEVICE_REL;

-- 2021.04.27 TS02_TB_RELATIONSHIP, TS02_TB_RIGHT 데이터타입 변경
ALTER TABLE TS02_TB_RELATIONSHIP MODIFY OID VARCHAR2(11);
ALTER TABLE TS02_TB_RELATIONSHIP MODIFY OBJECT VARCHAR2(4);
ALTER TABLE TS02_TB_RELATIONSHIP MODIFY RELATIONSHIP_OID VARCHAR2(11);
ALTER TABLE TS02_TB_RELATIONSHIP MODIFY RELATIONSHIP_OBJECT VARCHAR2(4);
ALTER TABLE TS02_TB_RELATIONSHIP MODIFY RELATIONSHIP_TYPE VARCHAR2(8);

ALTER TABLE TS02_TB_RIGHT MODIFY OID VARCHAR2(11);
ALTER TABLE TS02_TB_RIGHT MODIFY TARGET_OBJECT VARCHAR2(4);
ALTER TABLE TS02_TB_RIGHT MODIFY TARGET_OID VARCHAR2(11);

-- 2021.04.27 INPUT_DATE default SYSDATE() 설정
ALTER TABLE TS02_TB_API_KEY MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_BATCH_PROCESS MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_BOARD MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_BRANCH MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_CATEGORY MODIFY (INPUT_DATE DEFAULT SYSDATE);

ALTER TABLE TS02_TB_COMPANY MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_DEVICE MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_DOC MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_DOC_REL MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_DOC_VERSION MODIFY (INPUT_DATE DEFAULT SYSDATE);

ALTER TABLE TS02_TB_FILE MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_GROUP MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_MSG MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_MSG_BOOKING MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_MSG_GROUP MODIFY (INPUT_DATE DEFAULT SYSDATE);

ALTER TABLE TS02_TB_MSG_GROUP_USER_REL MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_MSG_SEND_HIST MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_MSG_SEND_RESULT MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_MSG_TEMPLATE MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_OBJECT_REL MODIFY (INPUT_DATE DEFAULT SYSDATE);

ALTER TABLE TS02_TB_PART MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_POSTING MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_RELATIONSHIP MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_ROLE MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_ROLE_USER_REL MODIFY (INPUT_DATE DEFAULT SYSDATE);

ALTER TABLE TS02_TB_STORAGE_FILE MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_THUMBNAIL MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_USER MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_TB_USER_DEVICE_REL MODIFY (INPUT_DATE DEFAULT SYSDATE);

-- 2021.04.27 YEAR, MONTH, WEEK, DAY 컬럼명 변경
ALTER TABLE TS02_TB_AUDIT_VISIT RENAME COLUMN YEAR TO VISIT_YEAR;
ALTER TABLE TS02_TB_AUDIT_VISIT RENAME COLUMN MONTH TO VISIT_MONTH;
ALTER TABLE TS02_TB_AUDIT_VISIT RENAME COLUMN WEEK TO VISIT_WEEK;
ALTER TABLE TS02_TB_AUDIT_VISIT RENAME COLUMN DAY TO VISIT_DAY;

ALTER TABLE TS02_TB_POSTING_AUDIT_VIEW RENAME COLUMN YEAR TO VIEW_YEAR;
ALTER TABLE TS02_TB_POSTING_AUDIT_VIEW RENAME COLUMN MONTH TO VIEW_MONTH;
ALTER TABLE TS02_TB_POSTING_AUDIT_VIEW RENAME COLUMN WEEK TO VIEW_WEEK;
ALTER TABLE TS02_TB_POSTING_AUDIT_VIEW RENAME COLUMN DAY TO VIEW_DAY;
ALTER TABLE TS02_TB_POSTING_AUDIT_VIEW RENAME COLUMN HOUR TO VIEW_HOUR;

ALTER TABLE TS02_TB_USER RENAME COLUMN YEAR TO BIRTH_YEAR;
ALTER TABLE TS02_TB_USER RENAME COLUMN MONTH TO BIRTH_MONTH;
ALTER TABLE TS02_TB_USER RENAME COLUMN DAY TO BIRTH_DAY;

-- 2021.04.27 TS02_TB_USER ID 컬럼 USER_ID로 변경
ALTER TABLE TS02_TB_USER RENAME COLUMN ID TO USER_ID;

-- 2021.04.30 TS02_TB_AUDIT_VIEW 컬럼 추가
ALTER TABLE TS02_TB_AUDIT_VIEW ADD VIEW_YEAR VARCHAR2(4 BYTE) NULL;
ALTER TABLE TS02_TB_AUDIT_VIEW ADD VIEW_MONTH VARCHAR2(2 BYTE) NULL;
ALTER TABLE TS02_TB_AUDIT_VIEW ADD VIEW_WEEK VARCHAR2(1 BYTE) NULL;
ALTER TABLE TS02_TB_AUDIT_VIEW ADD VIEW_DAY VARCHAR2(2 BYTE) NULL;

-- 2021.05.03 INPUT_DATE 컬럼 DEFAULT SYSDATE() 설정 -- 남윤재
ALTER TABLE TS02_TB_FILE MODIFY ( INPUT_DATE DEFAULT SYSDATE );
ALTER TABLE TS02_TB_BRANCH MODIFY ( INPUT_DATE DEFAULT SYSDATE );
ALTER TABLE TS02_TB_MSG_BOOKING MODIFY ( INPUT_DATE DEFAULT SYSDATE );
ALTER TABLE TS02_TB_API_KEY MODIFY ( INPUT_DATE DEFAULT SYSDATE );
ALTER TABLE TS02_TB_RELATIONSHIP MODIFY ( INPUT_DATE DEFAULT SYSDATE );
ALTER TABLE TS02_TB_DOC MODIFY ( INPUT_DATE DEFAULT SYSDATE );
ALTER TABLE TS02_TB_DOC_REL MODIFY ( INPUT_DATE DEFAULT SYSDATE );
ALTER TABLE TS02_TB_DOC_VERSION MODIFY ( INPUT_DATE DEFAULT SYSDATE );

-- 2021.06.04 TS02_TB_FILE 테이블 FILE_SIZE 컬럼 NUMBER(10,2)에서 NUMBER(15,2)로 컬럼 크기 변경
ALTER TABLE TS02_TB_FILE MODIFY FILE_SIZE NUMBER(15,2);

-- 2021.06.25 TS02_TB_CONTENTS 에 LANG 컬럼 추가
ALTER TABLE TS02_TB_CONTENTS ADD LANG VARCHAR2(2 BYTE);
COMMENT ON COLUMN TS02_TB_CONTENTS.LANG IS '다국어';

-- 2021.07.21 방문 이력 관련 테이블 컬럼 VISIT_URL 추가 -- 남윤재
ALTER TABLE TS02_TB_AUDIT_VISIT ADD ( VISIT_URL VARCHAR2(255));
COMMENT ON COLUMN TS02_TB_AUDIT_VISIT.VISIT_URL IS '방문 URL';

-- 2021.07.22 방문 이력 관련 테이블 컬럼 VISIT_PAGE_NAME 추가 -- 남윤재
ALTER TABLE TS02_TB_AUDIT_VISIT ADD( VISIT_PAGE_NAME VARCHAR2(255));
COMMENT ON COLUMN TS02_TB_AUDIT_VISIT.VISIT_PAGE_NAME IS '해당화면';