package com.remarkablesoft.framework.service.doc.doc.model.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocRelInfo;
import com.remarkablesoft.framework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;



/**                                                            
 * 	                                                           
 * 		@주시스템		:	framework-web
 * 		@서브 시스템		:	doc - docRel
 * 		@프로그램 ID		:	DocRelBLO
 * 		@프로그램 개요	:	문서 관계정보 BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@BLO
public class DocRelBLO {

    @Autowired
    protected DocRelDAO docRelDAO;

    /**
    * 문서 관계정보를 저장합니다.
    *
    * @param info
    * @return DocRelInfo
    * @author 최원준
    */
    public DocRelInfo insert( DocRelInfo info ) {

        if ( StringUtils.isEmpty( info.getDocOid() ) || StringUtils.isEmpty( info.getTargetOid() ) ) {
        		return null;
        }

        return docRelDAO.insert( info ) > 0 ? info : null;
    }
    
    /**
    * 문서 정보로부터 문서 관계정보를 저장합니다.
    *
    * @param docInfo
    * @return DocRelInfo
    * @author 최원준
    */
    public DocRelInfo insert( DocInfo docInfo ) {
        
        if ( docInfo.getDocRelInfo() == null ) {
            return null;
        }
        
        DocRelInfo docRelInfo = docInfo.getDocRelInfo();
        docRelInfo.setDocOid( docInfo.getOid() );
        
        return insert( docRelInfo );
    }

    /**
    * 문서 관계정보를 수정합니다.
    *
    * @param info
    * @return DocRelInfo
    * @author 최원준
    */
    public DocRelInfo update( DocRelInfo info ) {

        if ( info == null || StringUtils.isEmpty( info.getDocOid() ) || StringUtils.isEmpty( info.getTargetOid() ) ) {
        		return null;
        }

        return docRelDAO.update( info ) > 0 ? info : null;
    }

    /**
    * 문서 관계정보를 삭제합니다.
    *
    * @param cnd
    * @return int
    * @author 최원준
    */
    public int delete( DocCnd cnd ) {

        if ( StringUtils.isEmpty( cnd.getDocOid() ) && StringUtils.isEmpty( cnd.getTargetOid() ) ) {
        		return 0;
        }

        return docRelDAO.delete( cnd );
    }
    
    /**
    * 다수의 문서 관계정보를 삭제합니다.
    *
    * @param cnd
    * @author 최원준
    */
    public int deleteTargetBulk( DocCnd cnd ) {
        
        if ( CollectionUtils.isEmpty( cnd.getDocOidList() ) && CollectionUtils.isEmpty( cnd.getTargetOidList() ) ) {
            return 0;
        }
        
        return delete( cnd );
    }
    
    /**
    * 문서 관계정보를 가져옵니다.
    *
    * @param cnd
    * @return DocRelInfo
    * @author 최원준
    */
    public DocRelInfo get( DocCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return docRelDAO.get( cnd );
    }

    /**
    * 문서 관계정보 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<DocRelInfo>
    * @author 최원준
    */
    public List<DocRelInfo> listAll( DocCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return docRelDAO.listAll( cnd );
    }
    
    /**
     * 문서 관계정보 유무를 확인합니다.
     *
     * @param oid
     * @return
     */
    public boolean exist( String oid ) {

        if ( StringUtils.isEmpty( oid ) ) {
        		return false;
        }

        return docRelDAO.exist( oid );
    }

}
