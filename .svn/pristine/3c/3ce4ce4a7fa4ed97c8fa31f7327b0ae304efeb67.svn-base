package com.remarkablesoft.site.kccam.service.datasheet.model.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetInfo;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**                                                            
 * 	                                                           
 * 		@주시스템		:	framework-web 	                   
 * 		@서브 시스템		:	datasheet - datasheetItem
 * 		@프로그램 ID		:	DatasheetItemBLO
 * 		@프로그램 개요	:	데이터시트 항목 정보 BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@BLO
public class DatasheetItemBLO {

    @Autowired
    protected DatasheetItemDAO datasheetItemDAO;

    /**
    * 데이터시트를 이루는 항목 정보를 저장합니다. 
    *
    * @param info
    * @return DatasheetIteminfo
    * @author 최원준
    */
    public DatasheetItemInfo insert( DatasheetItemInfo info ) {

        if ( StringUtils.isEmpty( info.getOid() ) ) {
        		info.setOid( OIDGenerator.generateOID() );
        }
        
        return datasheetItemDAO.insert( info ) > 0 ? info : null;
    }

    /**
    * DatasheetInfo로 DatasheetItem정보를 저장합니다.
    *
    * @param info
    * @return int
    * @author 최원준
    */
    public int insert( DatasheetInfo info ) {
    
    	if ( CollectionUtils.isEmpty( info.getDatasheetItemList() ) || StringUtils.isEmpty( info.getOid() ) ) {
    		return 0;
		}
    	
    	info.getDatasheetItemList().forEach( item -> {
    		item.setOid( OIDGenerator.generateOID() );
    		
    		if ( StringUtils.isEmpty( item.getDatasheetOid() ) || !info.getOid().equals( item.getDatasheetOid() ) ) {
    			item.setDatasheetOid( info.getOid() );
			}
		} );
    	
    	return insertBulk( info.getDatasheetItemList() );
	}
    
	/**
	* 다수의 DatasheetItem 정보를 저장합니다.
	*
	* @param list
	* @return int
	* @author 최원준
	*/
	public int insertBulk( List<DatasheetItemInfo> list ) {
    	
    	if ( CollectionUtils.isEmpty( list ) ) {
    		return 0;
		}
    	
    	return datasheetItemDAO.insertBulk( list );
	}
	
    /**
    * 데이터시트 항목 정보를 수정합니다.
    *
    * @param info
    * @return DatasheetItemInfo
    * @author 최원준
    */
    public DatasheetItemInfo update( DatasheetItemInfo info ) {

        if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
        		return null;
        }

        return datasheetItemDAO.update( info ) > 0 ? info : null;
    }

    /**
    * 데이터시트 항목 정보를 삭제합니다.
    *
    * @param cnd
    * @return int
    * @author 최원준
    */
    public int delete( DatasheetCnd cnd ) {

        if ( StringUtils.isEmpty( cnd.getOid() ) && StringUtils.isEmpty( cnd.getDatasheetOid() ) ) {
        		return 0;
        }

        return datasheetItemDAO.delete( cnd );
    }

    /**
    * 데이터시트 단건 정보를 가져옵니다.
    *
    * @param cnd
    * @return DatasheetIteminfo
    * @author 최원준
    */
    public DatasheetItemInfo get( DatasheetCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return datasheetItemDAO.get( cnd );
    }

    /**
    * 데이터시트 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<DatasheetItemInfo>
    * @author 최원준
    */
    public List<DatasheetItemInfo> listAll( DatasheetCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return datasheetItemDAO.listAll( cnd );
    }

}
