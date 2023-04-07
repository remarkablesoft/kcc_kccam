package com.remarkablesoft.site.kccam.service.product.model.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.product.model.ProductService;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;



/**
 *
 * 		@주시스템		:	kccam
 * 		@서브 시스템		:	product - product
 * 		@프로그램 ID		:	ProductServiceImpl
 * 		@프로그램 개요	:	제품정보 Service Impl
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    protected ProductBLO productBLO;

	@Override
    public ProductInfo insert( ProductInfo info ) {

        return productBLO.insert( info );
    }

	@Override
    public ProductInfo update( ProductInfo info ) {

        return productBLO.update( info );
    }

	@Override
    public int delete( ProductCnd cnd) {

        return productBLO.delete( cnd );
    }

	@Override
    public void deleteForUpdate( ProductInfo info ) {
    	productBLO.deleteForUpdate( info );
	}

	@Override
    public ProductInfo get( ProductCnd cnd ) {

        return productBLO.get( cnd );
    }

	@Override
    public PageList<ProductInfo> list( ProductCnd cnd ) {

        return productBLO.list( cnd );
    }

	@Override
	public PageList<ProductInfo> listByTargetOid(ProductCnd cnd) {
		return productBLO.listByTargetOid( cnd );
	}

	@Override
    public List<ProductInfo> listAll( ProductCnd cnd ) {

        return productBLO.listAll( cnd );
    }

	@Override
    public List<ProductInfo> viewListAll( ProductCnd cnd ) {

    	return productBLO.viewListAll( cnd );
	}

	@Override
	public List<ProductInfo> targetExceptList(ProductCnd cnd) {

		return productBLO.targetExceptList( cnd );
	}

    @Override
	public int addViewCnt( ProductInfo info ) {
		return productBLO.addViewCnt( info );
	}


}
