-- site에 필요한 퀴리 추가해주시면 됩니다.
-- Migration 진행 시 중복 데이터로 넘버링 해준 데이터 원복
-- UPDATE TS02_TB_CATEGORY

-- TS02_AM_PRODUCT STD_PRODUCT_CODE 컬럼 추가
ALTER TABLE TS02_AM_PRODUCT ADD STD_PRODUCT_CODE VARCHAR2(20 BYTE) NULL;

-- 2021.04.27 TS02_ 접두사 추가한 테이블명으로 변경
ALTER TABLE AM_DATASHEET RENAME TO TS02_AM_DATASHEET;
ALTER TABLE AM_DATASHEET_ITEM RENAME TO TS02_AM_DATASHEET_ITEM;
ALTER TABLE AM_DATASHEET_TARGET_ITEM RENAME TO TS02_AM_DATASHEET_TARGET_ITEM;
ALTER TABLE AM_LANG RENAME TO TS02_AM_LANG;
ALTER TABLE AM_LANG_ITEM RENAME TO TS02_AM_LANG_ITEM;

ALTER TABLE AM_ONE_TO_ONE RENAME TO TS02_AM_ONE_TO_ONE;
ALTER TABLE AM_ONE_TO_ONE_CONFIG RENAME TO TS02_AM_ONE_TO_ONE_CONFIG;
ALTER TABLE AM_ONE_TO_ONE_DETAIL RENAME TO TS02_AM_ONE_TO_ONE_DETAIL;
ALTER TABLE AM_PRODUCT RENAME TO TS02_AM_PRODUCT;
ALTER TABLE AM_PRODUCT_REL RENAME TO TS02_AM_PRODUCT_REL;

-- 2021.04.27 INPUT_DATE default SYSDATE() 설정
ALTER TABLE TS02_AM_DATASHEET MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_AM_LANG MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_AM_ONE_TO_ONE MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_AM_PRODUCT MODIFY (INPUT_DATE DEFAULT SYSDATE);
ALTER TABLE TS02_AM_PRODUCT_REL MODIFY (INPUT_DATE DEFAULT SYSDATE);

--2021.05.06 VS02_PRODUCT_REL MATERIAL_NAME 컬럼추가
PART.NAME			AS MATERIAL_NAME


-- 2021.05.19 다국어 처리 by james
-- category_lang 테이블 생성 product_lang 테이블 생성
-- lang 테이블에 값을 넣고 category와 product의 불필요한 컬럼은 삭제해야 하는데 이건 제일 나중에 처리한다.

1. TS02_AM_PRODUCT_LANG 생성 ( KCCAM_CREATE_PRODUCT_TABLE.sql 참조)
2. TS02_TB_CATEGORY_LANG 생성 ( Remarkable_CREATE_MGNT_TABLE.sql 참조)
3. product_다국어처리.sql와 category_다국어처리.sql 참조로 테이블 데이터 생성
4. 기존 TS02_AM_PRODUCT, TS02_TB_CATEGORY 두 테이블의 백업을 만들어둔다  ( product_다국어처리.sql와 category_다국어처리.sql  파일 참조)
5. VS02_CATEGORY 뷰 생성(Remarkable_CREATE_MGNT_VIEW.sql 참조)

6. product와 category 테이블 생성 다 되면 KCCAM_CREATE_INTEGRATED_SEARCH_VIEW.sql에 있는 뷰 다시 재생성.
7. 모두 다 정상적으로 생성 되었다면 TS02_AM_PRODUCT와 TS02_TB_CATEGORY에 있는 컬럼을 제거.
   ( product_다국어처리.sql와 category_다국어처리.sql  파일 참조)

-- 2021.05.26 다국어 처리 Woong
-- TS02_AM_MATERIAL , TS02_AM_MATERIAL_LANG 테이블 생성

1. TS02_AM_MATERIAL 생성 ( KCCAM_CREATE_MATERIAL_TABLE.sql 참조)
2. TS02_AM_MATERIAL_LANG 생성 ( KCCAM_CREATE_MATERIAL_TABLE.sql 참조)
3. material_다국어처리.sql 참조하여 데이터 생성
4. VS02_MATERIAL 뷰 생성(KCCAM_CREATE_MATERIAL_VIEW.sql 참조)

-- 2021.05.27 다국어 기본값 insert Woong
다국어_기본값_insert.sql 참조하여 기본 데이터 생성

-- 2021.06.61 운영DB 다국어_기본값 INSERT (yoonjae)
-- 다국어_기본값_insert.sql 참조하여 운영DB INSERT ( material, product, category )


-- 2021.06.03 포스팅 관련 다국어 처리 Woong
-- TS02_TB_POSTING_LANG 테이블 생성

1. TS02_TB_POSTING_LANG 테이블 생성 ( Remarkable_CREATE_BOARD_TABLE.sql 참조 )
2. posting_다국어처리.sql 참조하여 데이터 생성
3. VS02_POSTING 뷰 생성 ( Remarkable_CREATE_BOARD_VIEW.sql 참조 )
4. 다국어_기본값_insert.sql 참조하여 포스팅 관련 기본 데이터 생성

-- 2021.06.11 통합 검색 관련 VS02_IS_MARKET 뷰 변경 Woong
KCCAM_CREATE_INTEGRATED_SEARCH_VIEW.sql 참조하여 VS02_IS_MARKET 뷰 재생성

-- 2021.06.30 문서 공유/다운 이력 관련 테이블 컬럼추가
ALTER TABLE TS02_AM_DOC_SHARE_AUDIT ADD ACCESS_URL VARCHAR2(1000);
ALTER TABLE TS02_AM_DOC_SHARE_AUDIT ADD USER_IP VARCHAR2(20);


