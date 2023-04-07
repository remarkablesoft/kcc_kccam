package com.remarkablesoft.site.kccam.service.onetoone.onetoone.model.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneCnd;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



/**                                                            
 * 	                                                           
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	onetoone - onetooneDetail
 * 		@프로그램 ID		:	OneToOneDetailBLO
 * 		@프로그램 개요	:	1대1 문의 상세 BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@BLO
public class OneToOneDetailBLO {

    @Autowired
    protected OneToOneDetailDAO oneToOneDetailDAO;

    /**
    * 1대1 문의 상세 정보를 저장합니다.
    *
    * @param info
    * @return OneToOneDetailInfo
    * @author 최원준
    */
    public OneToOneDetailInfo insert( OneToOneDetailInfo info ) {

        if ( StringUtils.isEmpty( info.getOid() ) ) {
        		info.setOid( OIDGenerator.generateOID() );
        }
        
        return oneToOneDetailDAO.insert( info ) > 0 ? info : null;
    }

    /**
    * 1대1 문의 상세 정보를 수정합니다.
    *
    * @param info
    * @return OneToOneDetailInfo
    * @author 최원준
    */
    public OneToOneDetailInfo update( OneToOneDetailInfo info ) {

        if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
        		return null;
        }

        return oneToOneDetailDAO.update( info ) > 0 ? info : null;
    }

    /**
    * 1대1 문의 정보를 삭제합니다.
    *
    * @param oid
    * @return int
    * @author 최원준
    */
    public int delete( OneToOneCnd cnd ) {

        if ( StringUtils.isEmpty( cnd.getOid() ) && StringUtils.isEmpty( cnd.getOtoOid() ) ) {
            return 0;
        }

        return oneToOneDetailDAO.delete( cnd );
    }

    /**
    * 1대1 문의 상세 정보를 가져옵니다.
    *
    * @param cnd
    * @return OneToOneDetailInfo
    * @author 최원준
    */
    public OneToOneDetailInfo get( OneToOneCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return oneToOneDetailDAO.get( cnd );
    }

    /**
    * 1대1 문의 상세정보 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<OneToOneDetailInfo>
    * @author 최원준
    */
    public List<OneToOneDetailInfo> listAll( OneToOneCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return oneToOneDetailDAO.listAll( cnd );
    }

}
