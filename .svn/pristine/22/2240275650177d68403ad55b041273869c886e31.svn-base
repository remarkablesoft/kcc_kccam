package com.remarkablesoft.framework.service.board.posting.model.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.board.posting.vo.PostingCnd;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	board - posting
 * 		@프로그램 ID		:	PostingAuditViewBLOTest
 * 		@프로그램 개요		:	PostingAuditView BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 07. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

public class PostingBLOTest extends BaseModelTest {

	@Autowired
	PostingBLO postingBLO;

	@Autowired
	UserBLO userBLO;


	@Test
	public void insertOrUpdate_테스트() {
		PostingInfo info = SystemFactory.getPostingInfo();
		info.setTitle( "test new 제목 테스트2222" );
		info.setContents( "임시 내용2222" );
		
		info.setNoticeLevel( PostingInfo.NOTICE_LEVEL_NOTI );
		info = postingBLO.insertOrUpdate( info );
		System.out.println(info.toString());
		
	}
	/*
	 * 포스팅 INSERT
	 * */
	@Test
	public void insert_테스트() {
		
		UserInfo user = SystemFactory.getUserInfo();
		String userOid = OIDGenerator.generateOID();
		user.setOid( userOid );
		user.setUserId( "AAAAA" );
		
		//유저 
		PostingInfo info = SystemFactory.getPostingInfo();
		info.setBoardOid( OIDGenerator.generateOID() );
		info.setTitle( "KCC, 고기능성 화장품용 실리콘 개발" );
		info.setNoticeLevel( PostingInfo.NOTICE_LEVEL_NOTI );
		info.setTempYn( SystemConstants.FLAG_YES );
		
		info.setFromDate( LocalDateTime.now() );
		info.setToDate( LocalDateTime.now() );
		info.setInputUserInfoList( userBLO.convertUserInfoList( user ) );
		info.setInputUser( userOid );
		info.setContents("- EWG Green 등급의 피부 자극 없는 화장품용 실리콘 5종 신규 개발\r\n"
				+ "- 다양한 화장품 제형에 적용 가능하며 실크처럼 부드러운 사용감 구현\r\n"
				+ "\r\n"
				+ "KCC(대표: 정몽익)가 고기능성 화장품용 실리콘을 개발하며 글로벌 무대로 시장 공략을 가속화하고 있다.\r\n"
				+ "\r\n"
				+ "KCC가 이번에 선보인 화장품용 실리콘은 ▲SeraSilk EL81/PD82 ▲SeraSol EL98/EL99 ▲SeraSol SC95 등 5종이다. SeraSilk EL81과 PD82는 화장품에 실크처럼 부드러운 사용감을 제공하는 것이 특징이다. 주로 스킨케어나 선케어, 색조 화장품 등에 적용되며, 화장품의 끈적임은 상쇄시키고 고급스러우면서 부드러운 사용감을 부여한다. 특히 두 제품은 모두 EWG(The Environmental Working Group) 그린 등급의 원료로만 구성한 제품으로 자극이 없어 예민한 피부도 안심하고 사용할 수 있다.\r\n"
				+ "\r\n"
				+ "SeraSol EL 98과 EL99는 화장품 제조 과정에서 물과 실리콘이 잘 혼합될 수 있도록 용해를 돕는 제품이다. 다양한 종류의 유기 오일과 혼합해 사용할 수 있으며, 젤리 같은 고체 형태의 겔 블렌드 제품이기 때문에 특유의 부드러운 사용감도 느낄 수 있다. 최근 소비자들 사이에서 인기를 끌고 있는 워터 드롭 크림(피부에 바르면 물방울이 생겨 수분을 보충해 주는 스킨케어 제품)과 같은 특이 제형에서는 단독 유화제로도 사용할 수가 있어 화장품 제조사들로부터 많은 관심을 받고 있다.\r\n"
				+ "\r\n"
				+ "SeraSol SC 95는 저점도 제형용 실리콘 유화제다. 다양한 유기 오일과의 상용성이 뛰어날 뿐만 아니라 부드럽고 산뜻한 사용감을 부여한다. 주로 쿠션팩트, BB크림, CC크림, 파운데이션 등에 쓰인다. 특히 쿠션 팩트와 같이 저점도로 유화되는 색조 화장품에서 우수한 유화력과 저장 안정성을 제공한다.\r\n"
				+ "\r\n"
				+ "한편, KCC는 지난 4월 프랑스 파리에서 열린 ‘인코스메틱스 글로벌 2019’와 5월 한국에서 열린 ‘인코스메틱 코리아 2019’ 등 글로벌 화장품 원료 전시회에 참가해 K-뷰티 트렌드를 반영한 여러 실리콘 원료들을 선보여 글로벌 화장품 제조사들로부터 많은 관심을 받은 바 있다. 또한 각 기업의 요구에 빠르게 대응해 화장품용 실리콘을 제공하는 ‘고객 주문형’ 제품 개발 전략으로 국내외 시장에서 탄탄한 입지를 구축하고 있다.\r\n"
				+ "\r\n"
				+ "KCC 관계자는 “앞으로도 국내를 넘어 세계 화장품용 실리콘 시장을 공략하기 위해 고기능성 제품을 지속적으로 개발해나갈 것”이라며, “고객 맞춤형 솔루션을 통해 피부에 안전하고 친환경적인 KCC만의 특징적인 제품 라인을 개발해 나가며 세계적인 업체가 벤치마킹하는 위치에 설 수 있도록 집중할 계획이다”고 전했다. ");
		
//		info.setThreadOid( OIDGenerator.generateOID() ); //답변에 대한 것
//		info.setThreadParentOid( OIDGenerator.generateOID() );
//		info.setThreadFullPathOid( OIDGenerator.generateOID() );
//		info.setThreadDepth( 0 );
//		info.setThreadOrderNo( 2 );
	
		info.setReplyCount( 10 );
		info.setCommentCount( 11 );
		info.setRecommendCount( 12 );
		info.setLikeCount( 13 );
		info.setViewCount( 14 );
		
		info.setAnonymousPwd( "123123123" );
		info.setPickYn( SystemConstants.FLAG_NO );
		info.setCustomField1( "11" );
		info.setCustomField2( "22" );
		info.setCustomField3( "33" );
		info.setCustomField4( "44" );
		info.setCustomField5( "55" );
		
		//INSERT
		info = postingBLO.insert( info );
		
		System.out.println( info.toString() );
	}
	
	/*
	 * 포스팅 UPDATE
	 * */
	@Test
	public void update_테스트() {
	
		PostingInfo info = new PostingInfo();
		info.setOid( "1SR9R5LE005" );
		info.setTitle( "TESTTITLE22" );
		info.setContents( "asdasd22" );
		
		info = postingBLO.update( info );
		
		System.out.println( info );
	}
	
	
	/*
	 * 포스팅 get: 해당 포스팅 정보만 반환: 조회카운트는 증가시키지 않음
	 * */
	@Test
	public void get_테스트() {
		PostingCnd cnd = new PostingCnd();	
		cnd.setOid( "1SUcforW002" );
		
		PostingInfo info = postingBLO.get( cnd );
		System.out.println( info.toString() );
	}
	
	/*
	 * 포스팅 getWithPrevAndNext: 이전, 다음글 정보를 가진 포스팅 정보 반환
	 * */
	@Test
	public void getWithPrevAndNext_테스트() {
		PostingCnd cnd = new PostingCnd();	
		cnd.setOid( "1SUcforW002" );
//		cnd.setTempYn("Y");
		
		PostingInfo info = postingBLO.getWithPrevAndNext( cnd );
		System.out.println( "PrevPosting" + info.getPrevPosting().toString() );
		System.out.println( "NextPosting" + info.getNextPosting().toString() );
		System.out.println( info.toString() );
	}
	
	/*
	 * 보기시 조회카운트를 증가시키는 테스트
	 * */
	@Test
	public void 카운트_증가_테스트() {
		
		PostingCnd cnd = new PostingCnd();	
		cnd.setOid( "1SR9R5LE005" );
		
		//postingViewAuditBLO와 연동
		PostingInfo info = postingBLO.view( cnd );
		System.out.println( info.toString() );
		
	}
	
	/*
	*
	* */
	@Test
	public void viewWithPrevAndNext_테스트() {
			PostingCnd cnd = new PostingCnd();
			cnd.setOid( "1SUcgRzl003" );
			cnd.setFillContents( true );
			
			PostingInfo info = postingBLO.viewWithPrevAndNext( cnd );
			System.out.println( info.toString() );
	}
	
	@Test
	public void list_테스트() {
		
		PostingCnd cnd = new PostingCnd();
		//cnd.setTempYn("Y");
		//cnd.setSearchQueryType(PostingCnd.SEARCH_QUERY_TYPE_TITLE);
		//cnd.setSearchQuery("선박");
		
		PageList<PostingInfo> list = postingBLO.list( cnd );
		cnd.setPageSize( 20 );
		cnd.setStartIndex( 1 );
		cnd.setSearchQuery( "aaa" );
		cnd.setFillIconFile( true );
		
		list.forEach( s -> System.out.println( s ) );
		
	}
	
	@Test
	public void listAll_테스트() {
		
		PostingCnd cnd = new PostingCnd();
		cnd.setTempYn("Y");
		List<PostingInfo> list = postingBLO.listAll( cnd );
		
		list.forEach( s -> System.out.println( s ) );
	}
	
	@Test
	public void delete_테스트() {
		
		int result = postingBLO.delete( "1SR9R5LE005", "admin" );
		String resultStr = ( 0 <= result ) ? "삭제 성공" : "삭제 실패";
		System.out.println( resultStr );
		
	}
	
	@Test
	public void 다국어_insertOrUpdate_테스트() {
		PostingInfo info = new PostingInfo();
		
		info.setOid( "1SZ8olBK000" );
		info.setBoardOid( "1SYzT4IC008" );
		info.setNoticeLevel( 0 );
		info.setTempYn( "Y" );
		info.setFromDate( LocalDateTime.now() );
		info.setToDate( LocalDateTime.now().plusYears(1) );
		
		List<PostingInfo> langList = new ArrayList<>();
		
		info = new PostingInfo();
		info.setOid( "1SZ8olBK000" );
		info.setLang( SystemConstants.LANG_KOR.getKey() );
		info.setTitle( "다국어 테스트 중 - 한국어 - 제목 수정되어야함" );
		info.setContents( "다국어 테스트 중 - 한국어 - 내용 수정되어야함" );
		langList.add( info );
		
		info = new PostingInfo();
		info.setOid( "1SZ8olBK000" );
		info.setLang( SystemConstants.LANG_ENG.getKey() );
		info.setTitle( "다국어 테스트 중 - 영어 - 제목 수정되어야함" );
		info.setContents( "다국어 테스트 중 - 영어 - 내용 수정되어야함" );
		langList.add( info );
		
		info = new PostingInfo();
		info.setOid( "1SZ8olBK000" );
		info.setLang( SystemConstants.LANG_CHN.getKey() );
		info.setTitle( "다국어 테스트 중 - 중국어 - 신규 등록" );
		info.setContents( "다국어 테스트 중 - 중국어 - 신규 등록" );
		langList.add( info );
		
		info.setLangPostingList( langList );

		postingBLO.insertOrUpdate( info );
	}
	
	@Test
	public void 다국어_insert_테스트() {
		PostingInfo masterinfo = new PostingInfo();
		
		String oid = OIDGenerator.generateOID();
		
		masterinfo.setOid( oid );
		masterinfo.setBoardOid( "1SYzT4IC008" );
		masterinfo.setNoticeLevel( 0 );
		masterinfo.setTempYn( "N" );
		masterinfo.setFromDate( LocalDateTime.now() );
		masterinfo.setToDate( LocalDateTime.now().plusYears(1) );
		masterinfo.setThreadParentOid( "1SZ8olBK000" );
		
		List<PostingInfo> langList = new ArrayList<>();
		
		PostingInfo info = new PostingInfo();

		info.setLang( SystemConstants.LANG_KOR.getKey() );
		info.setTitle( "한국어 - 신규" );
		info.setContents( "한국어 - 신규" );
		langList.add( info );
		
		info = new PostingInfo();

		info.setLang( SystemConstants.LANG_ENG.getKey() );
		info.setTitle( "영어 - 신규" );
		info.setContents( "영어 - 신규" );
		langList.add( info );
		
		info = new PostingInfo();

		info.setLang( SystemConstants.LANG_CHN.getKey() );
		info.setTitle( "중국어 - 신규" );
		info.setContents( "중국어 - 신규" );
		langList.add( info );
		
		masterinfo.setLangPostingList( langList );

		postingBLO.insert( masterinfo );
	}
	
	@Test
	public void get_테스트2() {
		PostingCnd cnd = new PostingCnd();
		cnd.setOid( "1SZ8olBK000" );
		cnd.setLang( SystemConstants.LANG_CHN.getKey() );
		
		System.out.println( postingBLO.get( cnd ) );
	}
	
	@Test
	public void getWithPrevAndNext_테스트2() {
		PostingCnd cnd = new PostingCnd();
		cnd.setOid( "1SYJZq01000" );
//		cnd.setLang( SystemConstants.LANG_CHN.getKey() );
		
		PostingInfo info = postingBLO.getWithPrevAndNext( cnd );
		
		System.out.println( "이전 포스팅 : " + info.getPrevPosting() );
		System.out.println( "다음 포스팅 : " + info.getNextPosting() );
	}
	
	@Test
	public void delete_테스트2() {
		postingBLO.delete( "1SYJZq01000" , "Woong");
		
	}
	
	@Test
	public void delete_테스트3() {
		postingBLO.delete( "1SYJDWZ7005" , "Woong");
		
	}
	
	@Test
	public void xxx() {
		List<PostingInfo> list = new ArrayList<>();
		
		System.out.println( CollectionUtils.isEmpty( list ) );
		System.out.println( CollectionUtils.isNotEmpty( list ) );
	}
}
