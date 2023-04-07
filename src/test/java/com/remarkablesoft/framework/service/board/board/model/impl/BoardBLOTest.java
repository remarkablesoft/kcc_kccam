package com.remarkablesoft.framework.service.board.board.model.impl;

import com.remarkablesoft.framework.common.constants.StatusType;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.config.BaseModelTest;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.board.vo.BoardCnd;
import com.remarkablesoft.framework.service.board.board.vo.BoardInfo;

import java.time.LocalDateTime;

/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	board - board
 * 		@프로그램 ID		:	BoardBLOTest
 * 		@프로그램 개요		:	Board BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 07. : 안병현 - 신규 생성( 테스트 RUN X )
 *      ============================================================================
 */

public class BoardBLOTest extends BaseModelTest{
	
	@Autowired
	BoardBLO boardBLO;
	
	/*
	 * 게시판 정보를 입력하는 페이지
	 * */
	@Test
	public void insert_테스트(){
		
		BoardInfo info = SystemFactory.getBoardInfo();
		info.setOid( OIDGenerator.generateOID() );			//테스트를 할 수 없어서 임시로 부여한 OID
		info.setTargetObject( SystemConstants.OBJECT_FW_TYPE_USER.getKey() );
		info.setTargetOid( "11111111111" );
		info.setBoardTypeFlag( BoardInfo.BOARD_TYPE_FLAG_GENERAL );
		
		info.setStatusTypeFlag( StatusType.APPROVAL );
		info.setName( "TESTBOARD" );
		info.setDescr( "DESCR" );
		info.setOwnerUser( "11111111111" );
		info.setOwnerDate( LocalDateTime.now() );
		
		info.setInputUser( "22222222222" );
		info.setInputDate( LocalDateTime.now() );
		
		info.setCustomField1( "2" );
		info.setCustomField2( "3" );
		info.setCustomField3( "4" );
		info.setCustomField4( "5" );
		info.setCustomField5( "6" );
		
		info = boardBLO.insert( info );
		
		System.out.println( info.toString() );
		
	}
	
	/*
	 * 게시판 정보를 업데이트
	 * */
	@Test
	public void update_테스트(){
		
		BoardInfo info = SystemFactory.getBoardInfo();
		info.setOid( "1SR9FfAz000" );
		info.setName( "이름변경" );
		info = boardBLO.update( info );
		
		System.out.println( info.toString() );
	}
	
	/*
	 * 메세지 예약 정보를 가져오는 메소드 테스트
	 * */
	@Test
	public void get_테스트(){
		
		BoardInfo info = boardBLO.get( "1SR9FfAz000" );
		System.out.println( info.toString() );
	}
	
	/*
	 * 메세지 예약 정보 리스트를 가져오는 메소드 테스트
	 * */
	@Test
	public void list_테스트(){
		
		BoardCnd cnd = new BoardCnd();
		cnd.setBoardTypeFlag( BoardInfo.BOARD_TYPE_FLAG_GENERAL );
		
		PageList<BoardInfo> list = boardBLO.list( cnd );
		
		list.forEach( s -> System.out.println( s ) );
		
	}
	
	/*
	 * 메세지 예약 정보 리스트를 모두 가져오는 메소드 테스트
	 * */
	@Test
	public void listAll_테스트(){
		
		BoardCnd cnd = new BoardCnd();
		
		PageList<BoardInfo> list = boardBLO.list( cnd );
		
		list.forEach( s -> System.out.println( s ) );
		
	}
	
	/*
	 * 삭제 테스트
	 * */
	@Test
	public void delete_테스트(){
		
		int result = boardBLO.delete( "1SR9G5Tv000" );
		
		String resultStr = ( 0 <= result ) ? "삭제 성공" : "삭제 실패";
		System.out.println( resultStr );
	}

}
