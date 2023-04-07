package com.remarkablesoft.site.kccam.service.datasheet.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetInfo;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;



/**                                                            
 * 	                                                           
 * 		@주시스템		:	framework-web
 * 		@서브 시스템		:	datasheet - datasheet
 * 		@프로그램 ID		:	DatasheetService
 * 		@프로그램 개요	:	Datasheet Service
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public interface DatasheetService {

    /**
    * 데이터시트 정보를 저장합니다.
    *
    * @param info
    * @return DatasheetInfo
    * @author 최원준
    */
    public DatasheetInfo insert( DatasheetInfo info );

    /**
    * 데이터시트 정보를 수정합니다.
    *
    * @param info
    * @return DatasheetInfo
    * @author 최원준
    */
    public DatasheetInfo update( DatasheetInfo info );

    /**
    *  oid를 기준으로 Datasheet 정보를 삭제합니다.
    *
    * @param :
    * @return :
    * @author 최원준
    */
    public int delete( String oid );

    /**
    * 데이터시트 단건 정보를 가져옵니다.
    *
    * @param cnd
    * @return DatasheetInfo
    * @author 최원준
    */
    public DatasheetInfo get( DatasheetCnd cnd );

    /**
    * 데이터시트 페이지 리스트를 가져옵니다.
    *
    * @param cnd
    * @return PageList<DatasheetInfo>
    * @author 최원준
    */
    public PageList<DatasheetInfo> list( DatasheetCnd cnd );

    /**
    *  데이터시트 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<DatasheetInfo>
    * @author 최원준
    */
    public List<DatasheetInfo> listAll( DatasheetCnd cnd );
    
}
