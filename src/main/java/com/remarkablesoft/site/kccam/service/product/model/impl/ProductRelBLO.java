package com.remarkablesoft.site.kccam.service.product.model.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.site.kccam.service.product.vo.ProductRelInfo;
import com.remarkablesoft.framework.util.StringUtils;



/**                                                            
 * 	                                                           
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	product - productRel
 * 		@프로그램 ID		:	ProductRelBLO
 * 		@프로그램 개요	:	제품 관계정보 BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@BLO
public class ProductRelBLO {

    @Autowired
    protected ProductRelDAO productRelDAO;

    /**
    * 제품 관계정보를 저장합니다.
    *
    * @param info
    * @return ProductRelInfo
    * @author 최원준
    */
    public ProductRelInfo insert( ProductRelInfo info ) {

        if ( StringUtils.isEmpty( info.getProductOid() ) || StringUtils.isEmpty( info.getTargetOid() ) ) {
            return null;
        }

        return productRelDAO.insert( info ) > 0 ? info : null;
    }
    
    /**
    * 제품 관계정보를 제품정보를 통해 저장합니다.
    *
    * @param info
    * @author 최원준
    */
    public ProductInfo insert( ProductInfo info, String targetOid, String targetObject ) {
        
        if ( info == null || StringUtils.isEmpty( info.getOid() )
            || CollectionUtils.isEmpty( info.getProductRelList() ) || StringUtils.isEmpty( targetOid )
            || StringUtils.isEmpty( targetObject ) ) {
            return null;
        }
        
        info.getProductRelList().forEach( rel -> rel.setProductOid( info.getOid() )
                                                         .setTargetOid( targetOid )
                                                         .setTargetObject( targetObject ) );
        
        int result = insertBulk( info.getProductRelList() );
        
        return result > 0 ? info : null;
    }
    
    /**
    * 다수의 제품정보를 한번에 저장합니다.
    *
    * @param list
    * @author 최원준
    */
    public int insertBulk( List<ProductRelInfo> list ) {
        
        if ( CollectionUtils.isEmpty( list ) ) {
            return 0;
        }
        
        return productRelDAO.insertBulk( list );
    }

    /**
    * 제품 관계정보를 삭제합니다.
    *
    * @param cnd
    * @return int
    * @author 최원준
    */
    public int delete( ProductCnd cnd ) {

        if ( StringUtils.isEmpty( cnd.getProductOid() ) && StringUtils.isEmpty( cnd.getTargetOid() ) ) {
        	return 0;
        }

        return productRelDAO.delete( cnd );
    }
    
    /**
     * target과 연결된 다수의 제품 관계정보를 삭제합니다.
     *
     * @param cnd
     * @return int
     * @author 황지영
     */
    public int deleteByTarget( ProductCnd cnd ) {
    	
    	if ( StringUtils.isEmpty( cnd.getTargetOid() ) && StringUtils.isEmpty( cnd.getTargetObject() )) {
    		return 0;
    	}
    	
    	return productRelDAO.delete( cnd );
    }
    
    /**
    * 제품 관계정보를 가져옵니다.
    *
    * @param cnd
    * @return ProductRelInfo
    * @author 최원준
    */
    public ProductRelInfo get( ProductCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return productRelDAO.get( cnd );
    }

    /**
    * 제품 관계정보 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<ProductRelInfo>
    * @author 최원준
    */
    public List<ProductRelInfo> listAll( ProductCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        return productRelDAO.listAll( cnd );
    }

    /**
    * 관계정보가 있는지 체크합니다.
    *
    * @param cnd
    * @return boolean
    * @author 최원준
    */
    public boolean exist( ProductCnd cnd ) {
    	if ( cnd == null ) {
    		return false;
		}
    	return productRelDAO.exist( cnd );
	}
}
