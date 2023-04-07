package com.remarkablesoft.site.kccam.service.onetoone.onetoone.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigInfo;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneCnd;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneDetailInfo;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneInfo;

/**
 *
 *        @주시스템        :	kccam
 *        @서브 시스템        :	onetoone - onetoone
 *        @프로그램 ID        :	OneToOneBLOTest
 *        @프로그램 개요    :	1대1 문의 BLO Test
 *
 *        @변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public class OneToOneBLOTest extends BaseModelTest {
		
		@Autowired
		protected OneToOneBLO oneToOneBLO;
		
		@Autowired
		protected UserBLO userBLO;
		
		@Test
		public void insert_테스트() {
				for ( int i = 0; i < 3; i++ ) {
						
						OneToOneInfo info = new OneToOneInfo();
						
						info.setTitle( "문의제목Test" + i );
						info.setOid( OIDGenerator.generateOID() );
						info.setInputUser( OIDGenerator.generateOID() );
						info.setConfigOid( "1SY0fI2M00B" );
						info.setDescr( "문의 상세 내용" + i );
						
						UserInfo userInfo = new UserInfo();
						userInfo = userBLO.getOnlyUser( "1SadxiK0000" );
						
						List<OneToOneDetailInfo> detailList = new ArrayList<>();
						
						OneToOneDetailInfo addDetailInfo = new OneToOneDetailInfo();
						addDetailInfo.setDetailType( "AMCTDQST" );
						addDetailInfo.setDetailAnswer( "사이트 문의 Test" + i );
						addDetailInfo.setDetailQuestion( "사이트 정보 확인 관련" + i );
						detailList.add( addDetailInfo );
						
						info.setOneToOneDetailList( detailList );
						info = oneToOneBLO.insert( info );
						System.out.println( info.toString() );
						
				}
		}
		
		@Test
		public void update_테스트() {
				
				OneToOneInfo info = new OneToOneInfo();
				info.setOid( "1SRFV5Jp001" );
				info.setTitle( "title2" );
				
				info.setCustomField1( "cc1" );
				info.setCustomField2( "cc2" );
				info.setCustomField3( "cc3" );
				info.setCustomField4( "cc4" );
				info.setCustomField5( "cc5" );
				
				info = oneToOneBLO.update( info );
				System.out.println( info.toString() );
				
		}
		
		@Test
		public void delete_테스트() {
				
				int result = oneToOneBLO.delete( "1SRFV5Jp001" );
				System.out.println( result );
		}
		
		@Test
		public void get_테스트() {
				OneToOneCnd cnd = new OneToOneCnd();
				cnd.setOid( "1SYJte4s000" );
				oneToOneBLO.get( cnd );
		}
		
		@Test
		public void list_테스트() {
				
				OneToOneCnd cnd = new OneToOneCnd();
				PageList<OneToOneInfo> list = oneToOneBLO.list( cnd );
				
				list.forEach( System.out::println );
		}
		
		@Test
		public void listAll_테스트() {
				
				OneToOneCnd cnd = new OneToOneCnd();
				List<OneToOneInfo> list = oneToOneBLO.listAll( cnd );
				
				list.forEach( System.out::println );
		}
		
		@Test
		public void 이메일발송_테스트() {
				
				OneToOneInfo otoInfo = new OneToOneInfo();
				otoInfo.setTitle( "메일발송 테스트" );
				otoInfo.setDescr( "설명설명" );
				otoInfo.setInputUser( "11111111111" );
				otoInfo.setInputDate( LocalDateTime.now() );
				otoInfo.setReceiverEmail( "nyj9349@hanmail.net" );
				
				OneToOneConfigInfo otoConfigInfo = new OneToOneConfigInfo();
				otoConfigInfo.setConfigReceiverEmail( "nyj9349@hanmail.net" );
				
				List<OneToOneConfigInfo> configList = new ArrayList<>();
				configList.add( otoConfigInfo );
				
				otoInfo.setReceiverEmailList( configList );
				
				OneToOneDetailInfo oneToOneDetailInfo = new OneToOneDetailInfo();
				oneToOneDetailInfo.setDetailType( OneToOneConfigInfo.CONFIG_TYPE_QUESTION );
				oneToOneDetailInfo.setDetailQuestion( "제품정보문의" );
				otoInfo.addOneToOneDetailList( oneToOneDetailInfo );
				
				OneToOneDetailInfo oneToOneDetailInfo2 = new OneToOneDetailInfo();
				oneToOneDetailInfo2.setDetailType( OneToOneConfigInfo.CONFIG_TYPE_DETAIL_QUESTION );
				oneToOneDetailInfo2.setDetailQuestion( "상세질문1" );
				oneToOneDetailInfo2.setDetailAnswer( "상세대답1" );
				otoInfo.addOneToOneDetailList( oneToOneDetailInfo2 );
				
				UserInfo user = SystemFactory.getUserInfo();
				user.setEmail( "nyj9349@hanmail.net" );
				user.setOrganizationName( "리마커블소프트" );
				user.setPhonePart1( "010" ).setPhonePart2( "1234" ).setPhonePart3( "5678" );
				user.setName( "질문자" );
				user.setCustomField1( "부서명" );
				
				user.setTelPart1( "02" ).setTelPart2( "123" ).setTelPart3( "456" );
				user.setCustomField2( "대한민국" );
				user.setCustomField3( "서울" );
				
				otoInfo.setInputUserInfo( user );
				
				oneToOneBLO.sendEmail( otoInfo );
		}
		
}
