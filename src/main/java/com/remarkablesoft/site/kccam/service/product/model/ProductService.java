package com.remarkablesoft.site.kccam.service.product.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;



/**
 *
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	product - product
 * 		@프로그램 ID		:	ProductService
 * 		@프로그램 개요	:	제품정보 Service
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public interface ProductService {


    /**
    * 제품정보를 저장합니다.
    *
    * @param info
    * @return ProductInfo
    * @author 최원준
    */
    ProductInfo insert( ProductInfo info );


    /**
    * 제품정보를 수정합니다.
    *
    * @param info
    * @return ProductInfo
    * @author 최원준
    */
    ProductInfo update( ProductInfo info );


    /**
    * 제품정보를 삭제합니다.
    *
    * @param cnd
    * @return int
    * @author 최원준
    */
    int delete( ProductCnd cnd );

    /**
    * update 시 트랜잭션 단위로 인해 발생하는 무결성 에러를 처리하기 위한 삭제
    *
    * @param info
    * @author 최원준
    */
    void deleteForUpdate( ProductInfo info );

    /**
    * 제품정보를 가져옵니다.
    *
    * @param cnd
    * @return ProductInfo
    * @author 최원준
    */
    ProductInfo get( ProductCnd cnd );


    /**
    * 제품정보 페이지 리스트를 가져옵니다.
    *
    * @param cnd
    * @return PageList<ProductInfo>
    * @author 최원준
    */
    PageList<ProductInfo> list( ProductCnd cnd );

    /**
    * 타겟 OID를 조건으로 하는 페이지리스트를 가져옵니다.
    *
    * @param cnd
    * @return PageList<ProductInfo>
    * @author 황지영
    */
    PageList<ProductInfo> listByTargetOid( ProductCnd cnd );

    /**
    * 제품정보 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<ProductInfo>
    * @author 최원준
    */
    List<ProductInfo> listAll( ProductCnd cnd );


    /**
    * 제품 - 제품Rel 로 만든 View에서 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<ProductInfo>
    * @author 최원준
    */
    List<ProductInfo> viewListAll( ProductCnd cnd );

    /**
	* 제품 - 제품 REL 에서 타겟 OID에 속하지 않은 제품 리스트를 반환합니다.
	*
	* @param cnd
	* @return List<ProductInfo>
	* @author 최원준
	*/
    List<ProductInfo> targetExceptList( ProductCnd cnd );

    /**
    * 제품 조회수 카운트를 증가시킵니다.
    *
    * @param info
    * @author 최원준
    */
    int addViewCnt( ProductInfo info );

}
