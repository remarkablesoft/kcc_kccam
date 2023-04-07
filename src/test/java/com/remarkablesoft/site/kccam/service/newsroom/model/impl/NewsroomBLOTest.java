package com.remarkablesoft.site.kccam.service.newsroom.model.impl;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomCnd;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomInfo;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsroomBLOTest extends BaseModelTest {
		
		@Autowired
		NewsroomBLO newsroomBLO;
		
		@Test
		public void 뉴스정보_등록_테스트(){
				NewsroomInfo info = new NewsroomInfo();
				
				info.setOid( OIDGenerator.generateOID() );
				info.setLang( "KO" );
				info.setTitle( "한국 뉴스 제목 제품 포함" );
				
				List<ProductInfo> productList = new ArrayList<>();
				ProductInfo product1 = new ProductInfo();
				product1.setOid( "1SWKK0D1024" );
				
				productList.add( product1 );
				
				ProductInfo product2 = new ProductInfo();
				product2.setOid( "1SWKK0D102B" );
				
				productList.add( product2 );
				
				info.setProductList( productList );
				
				newsroomBLO.insert( info );
		}
		
		@Test
		public void 뉴스정보_수정_테스트(){
				NewsroomInfo info = new NewsroomInfo();
				info.setOid( "1SaU7A6A000" );
				info.setLang( "KO" );
				info.setTitle( "한국어 수정 제목" );
				
				List<ProductInfo> productList = new ArrayList<>();
				ProductInfo product1 = new ProductInfo();
				product1.setOid( "1SWKK0D1024" );
				
				productList.add( product1 );
				
				ProductInfo product2 = new ProductInfo();
				product2.setOid( "1SWKK0D102B" );
				
				productList.add( product2 );
				
				info.setProductList( productList );
				
				System.out.println( newsroomBLO.update( info ).toString() );
		}
		
		@Test
		public void 뉴스정보_삭제날짜_업데이트(){
				NewsroomInfo info = new NewsroomInfo();
				info.setOid( "1SaU7A6A000" );
				
				newsroomBLO.deleteFlagUpdate( info );
		}
		
		@Test
		public void 뉴스단건가져오기_GET(){
				NewsroomCnd cnd = new NewsroomCnd();
				cnd.setOid( "1SaYNH8Z000" );
				
				System.out.println( newsroomBLO.get( cnd ).toString() );
		}
		
		@Test
		public void 뉴스단건가져오기_조회수증가_이전글_다음글_포함(){
				NewsroomCnd cnd = new NewsroomCnd();
				cnd.setOid( "1SaYNH8Z000" );
				cnd.setLang( "KO" );
				
				System.out.println( newsroomBLO.viewWithPrevAndNext( cnd ).toString() );
		}
		
		@Test
		public void 뉴스페이지리스트(){
				NewsroomCnd cnd = new NewsroomCnd();
				cnd.setLang( "EN" );
				cnd.setPageSize( 5 );
				cnd.setStartIndex( 1 );
				
				System.out.println( newsroomBLO.list( cnd ).toString() );
		}
		
		@Test
		public void 모든뉴스리스트_가져오기(){
				NewsroomCnd cnd = new NewsroomCnd();
				
				System.out.println( newsroomBLO.listAll( cnd ).toString() );
		}
}
