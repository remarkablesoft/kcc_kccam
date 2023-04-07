package com.remarkablesoft.site.kccam.service.datasheet.model.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetTargetItemInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	kccam 	                   
 * 		@서브 시스템		:	datasheet - datasheetTargetItem
 * 		@프로그램 ID		:	DatasheetTargetItemBLO
 * 		@프로그램 개요		:	DatasheetTargetItem BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@BLO
public class DatasheetTargetItemBLO {

    @Autowired
    protected DatasheetTargetItemDAO datasheetTargetItemDAO;

    /**
     * 데이터시트 타겟 정보를 저장합니다.
     * 
     * @param info
     * @return
     * @author 최원준
     */
    public DatasheetTargetItemInfo insert( DatasheetTargetItemInfo info ) {

        if ( StringUtils.isEmpty( info.getOid() ) ) {
        		info.setOid( OIDGenerator.generateOID() );
        }

        return datasheetTargetItemDAO.insert( info ) > 0 ? info : null;
    }

    /**
     * 데이터시트 타겟 정보를 업데이트합니다.
     *
     * @param info
     * @return
     * @author 최원준
     */
    public DatasheetTargetItemInfo update( DatasheetTargetItemInfo info ) {

        if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
        		return null;
        }

        return datasheetTargetItemDAO.update( info ) > 0 ? info : null;
    }

    /**
     * 데이터시트 타겟 정보를 삭제합니다.
     *
     * @param oid
     * @return
     * @author 최원준
     */
    public int delete( DatasheetCnd cnd ) {

        if ( StringUtils.isEmpty( cnd.getOid() ) && StringUtils.isEmpty( cnd.getDatasheetOid() ) ) {
        		return 0;
        }

        return datasheetTargetItemDAO.delete( cnd );
    }

    /**
    * 데이터시트 타겟 단건 정보를 가져옵니다.
    *
    * @param cnd
    * @return DatasheetTargetItemInfo
    * @author 최원준
    */
    public DatasheetTargetItemInfo get( DatasheetCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return datasheetTargetItemDAO.get( cnd );
    }

    /**
    * 데이터시트 타겟 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<DatasheetTargetItemInfo>
    * @author 최원준
    */
    public List<DatasheetTargetItemInfo> listAll( DatasheetCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return datasheetTargetItemDAO.listAll( cnd );
    }


}
