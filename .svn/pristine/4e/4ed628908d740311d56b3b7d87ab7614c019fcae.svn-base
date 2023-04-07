package com.remarkablesoft.site.kccam.service.datasheet.model.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetItemInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetInfo;
import com.remarkablesoft.site.kccam.service.datasheet.vo.DatasheetCnd;
import com.remarkablesoft.framework.util.StringUtils;
import org.springframework.util.CollectionUtils;


/**                                                            
 * 	                                                           
 * @주시스템			:	framework-web
 * @서브 시스템		:	datasheet - datasheet
 * @프로그램 ID		:	DatasheetBLO
 * @프로그램 개요		:	데이터시트 BLO
 * 	                                                           
 * @변경이력    		                                       
 * ============================================================================
 * 1.0 2021. 02. 25. : 최원준 - 신규생성
 * ============================================================================
 */
@BLO
public class DatasheetBLO {

    @Autowired
    protected DatasheetDAO datasheetDAO;

    @Autowired
	protected DatasheetItemBLO datasheetItemBLO;
    
    /**
    * 데이터시트 정보를 저장합니다.
    *
    * @param info
    * @return DatasheetInfo
    * @author 최원준
    */
    public DatasheetInfo insert( DatasheetInfo info ) {
    	
        if ( StringUtils.isEmpty( info.getOid() ) ) {
        		info.setOid( OIDGenerator.generateOID() );
        }
		
        datasheetItemBLO.insert( info );
        
        return datasheetDAO.insert( info ) > 0 ? info : null;
    }

    /**
    * 데이터시트 정보를 업데이트합니다.
    *
    * @param info
    * @return DatasheetInfo
    * @author 최원준
    */
    public DatasheetInfo update( DatasheetInfo info ) {

        if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
            return null;
        }
    
        if ( info.getModDate() == null ) {
            info.setModDate( LocalDateTime.now() );
        }
        
        updateDatasheetItem( info );

        return datasheetDAO.update( info ) > 0 ? info : null;
    }

    /**
    * 데이터시트 정보를 삭제합니다.
    *
    * @param oid
    * @return int
    * @author 최원준
    */
    public int delete( String oid ) {

        if ( StringUtils.isEmpty( oid ) ) {
        		return 0;
        }

        DatasheetCnd cnd = new DatasheetCnd();
        cnd.setDatasheetOid( oid );
        datasheetItemBLO.delete( cnd );
        
        return datasheetDAO.delete( oid );
    }

    /**
    * 데이터시트 단건 정보를 가져옵니다.
    *
    * @param cnd
    * @return DatasheetInfo
    * @author 최원준
    */
    public DatasheetInfo get( DatasheetCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }
        
        DatasheetInfo info = datasheetDAO.get( cnd );
        if ( info == null ) {
        	return null;
		}
        
        fillDatasheetItem( info );

        return info;
    }

    /**
    * 데이터시트 페이지 리스트를 가져옵니다.
    *
    * @param cnd
    * @return PageList<datasheetInfo>
    * @author 최원준
    */
    public PageList<DatasheetInfo> list( DatasheetCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return datasheetDAO.list( cnd );
    }

    /**
    * 데이터시트 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<DatasheetInfo>
    * @author 최원준
    */
    public List<DatasheetInfo> listAll( DatasheetCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }
        
        List<DatasheetInfo> list = datasheetDAO.listAll( cnd );
        if ( CollectionUtils.isEmpty( list ) ) {
        	return null;
		}
        
        fillDatasheetItem( list );
        
        return list;
    }
	
    /**
    * DatasheetItem을 수정합니다.
    *
    * @param info
    * @author 최원준
    */
    private void updateDatasheetItem( DatasheetInfo info ) {
    
    	if ( CollectionUtils.isEmpty( info.getDatasheetItemList() ) ) {
    		return;
		}
    	
    	// 삭제 후 등록
    	DatasheetCnd cnd = new DatasheetCnd();
    	cnd.setDatasheetOid( info.getOid() );
    	datasheetItemBLO.delete( cnd );
    	datasheetItemBLO.insert( info );
    	
	}
	
	/**
	* DatasheetItem을 채워줍니다.
	*
	* @param info
	* @author 최원준
	*/
	private void fillDatasheetItem( DatasheetInfo info ) {
    	
		DatasheetCnd cnd = new DatasheetCnd();
		cnd.setDatasheetOid( info.getOid() );
		List<DatasheetItemInfo> datasheetItemList = datasheetItemBLO.listAll( cnd );
		if ( CollectionUtils.isEmpty( datasheetItemList ) ) {
			return;
		}
		
		info.setDatasheetItemList( datasheetItemList );
		
	}
	
	/**
	* 리스트에 DatasheetItem을 채워줍니다.
	*
	* @param list
	* @author 최원준
	*/
	private void fillDatasheetItem( List<DatasheetInfo> list ) {

		List<String> datasheetOidList = list.stream().map( DatasheetInfo::getOid ).collect( Collectors.toList());
		DatasheetCnd cnd = new DatasheetCnd();
		cnd.setDatasheetOidList( datasheetOidList );
		
		List<DatasheetItemInfo> datasheetItemList = datasheetItemBLO.listAll( cnd );
		
		if ( CollectionUtils.isEmpty( datasheetItemList ) ) {
			return;
		}
		
		Map<String, List<DatasheetItemInfo>> groupedMap = datasheetItemList.stream()
																  .collect( Collectors.groupingBy( DatasheetItemInfo::getDatasheetOid, Collectors.toList() ) );
	
		list.forEach( datasheet -> datasheet.setDatasheetItemList( groupedMap.get( datasheet.getOid() ) ) );
		
	}
	
}
