package com.remarkablesoft.site.kccam.web.api;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.web.controller.BaseController;
import com.remarkablesoft.site.kccam.service.product.model.ProductService;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;

@WEBLog
@RestController
@RequestMapping( "/kccam/api/product" )
public class ProductController extends BaseController {

	@Autowired
	protected ProductService productService;

	/**
	 * 제품 정보를 추가합니다.
	 *
	 * @param info
	 * @return ProductInfo
	 * @author 최원준
	 */
	@RequestMapping( value = "/product_insert" )
	public ResponseEntity<ProductInfo> insert( @RequestBody ProductInfo info ) {

		info = productService.insert(info);
		return ResponseEntity.ok( info );
	}

	/**
	 * 분류 정보를 수정합니다
	 *
	 * @param info
	 * @return ProductInfo
	 * @author 최원준
	 */
	@RequestMapping( value = "/product_update" )
	public ResponseEntity<ProductInfo> update( @RequestBody ProductInfo info ) {

		productService.deleteForUpdate( info );
		info = productService.update( info );

		return ResponseEntity.ok( info );
	}

	/**
	 * 제품 단건 정보를 가져옵니다.
	 *
	 * @param productCnd
	 * @return ProductInfo
	 */
	@RequestMapping( value = "/product_get" )
	public ResponseEntity<ProductInfo> get( @RequestBody ProductCnd productCnd ) {

		ProductInfo info = productService.get( productCnd );
		return ResponseEntity.ok( info );
	}

	/**
	* 제품 리스트를 가져옵니다.
	*
	* @param productCnd
	* @return HashMap<String, Object>
	* @author 황지영
	*/
	@RequestMapping( value = "/product_list" )
	public ResponseEntity<HashMap<String, Object>> list( @RequestBody ProductCnd productCnd ) {

		HashMap<String, Object> resultMap = new HashMap<>();
		PageList<ProductInfo> list = productService.list( productCnd );

		resultMap.put( "list" , list );
		resultMap.put( "totalCount" , CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

		return ResponseEntity.ok( resultMap );
	}

	@RequestMapping( value = "/product_listByTargetOid" )
	public ResponseEntity<HashMap<String, Object>> listByTargetOid( @RequestBody ProductCnd productCnd ){

		HashMap<String, Object> resultMap = new HashMap<>();
		PageList<ProductInfo> list = productService.listByTargetOid( productCnd );

		resultMap.put( "list" , list );
		resultMap.put( "totalCount" , CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

		return ResponseEntity.ok( resultMap );
	}

	/**
	* 제품 리스트를 가져옵니다.
	*
	* @param productCnd
	* @return List<ProductInfo>
	* @author 최원준
	*/
	@RequestMapping( value = "/product_listAll" )
	public ResponseEntity<List<ProductInfo>> listAll( @RequestBody ProductCnd productCnd ) {

		List<ProductInfo> list = productService.listAll( productCnd );
		return ResponseEntity.ok( list );
	}

	/**
	* 제품 - 제품Rel 로 만든 View에서 리스트를 가져옵니다.
	*
	* @param productCnd
	* @return List<ProductInfo>
	* @author 최원준
	*/
	@RequestMapping( value = "/product_viewListAll" )
	public ResponseEntity<List<ProductInfo>> viewListAll( @RequestBody ProductCnd productCnd ) {

		List<ProductInfo> list = productService.viewListAll( productCnd );
		return ResponseEntity.ok( list );
	}

	/**
	* 제품 - 제품 REL 에서 타겟 OID에 속하지 않은 제품 리스트를 반환합니다.
	*
	* @param cnd
	* @return List<ProductInfo>
	* @author 최원준
	*/
	@RequestMapping( value = "/product_targetExceptList" )
	public ResponseEntity<List<ProductInfo>> targetExceptList( @RequestBody ProductCnd productCnd ){

		List<ProductInfo> list = productService.targetExceptList( productCnd );
		return ResponseEntity.ok( list );
	}

	/**
	 * 제품 조회수 정보를 더해줍니다.
	 *
	 * @param info
	 * @return int
	 */
	@RequestMapping( value = "/product_addViewCnt" )
	public ResponseEntity<Integer> addViewCnt( @RequestBody ProductInfo info ) {

		int addCnt = productService.addViewCnt( info );
		return ResponseEntity.ok( addCnt );
	}

	/**
	* 제품 정보를 삭제합니다.
	*
	* @param oid
	* @return int
	* @author 최원준
	*/
	@RequestMapping( value = "/product_remove" )
	public ResponseEntity<Integer> remove( @RequestBody ProductInfo info ) {

		int result = productService.delete( new ProductCnd().setOid( info.getOid() ).setLang( info.getLang() ) );
		return ResponseEntity.ok( result );

	}

}
