package com.remarkablesoft.framework.service.board.board.model.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.common.constants.StatusType;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.board.vo.BoardCnd;
import com.remarkablesoft.framework.service.board.board.vo.BoardInfo;
import com.remarkablesoft.framework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board-board
 * 		@프로그램 ID		:	BoardBLO
 * 		@프로그램 개요 		:	board는 포스팅의 부모로만 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */
@BLO
public class BoardBLO {

		@Autowired
		protected BoardDAO boardDAO;
		
		public BoardInfo insert( BoardInfo info ) {

				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				info.setInputDate( LocalDateTime.now() );

				if ( StringUtils.isNotEmpty( info.getOwnerUser() ) && info.getOwnerDate() == null) {
						info.setOwnerDate( LocalDateTime.now() );
				}

				if ( StringUtils.isEmpty( info.getBoardTypeFlag() )) {
						info.setBoardTypeFlag( BoardInfo.BOARD_TYPE_FLAG_GENERAL );
				}
				
				if ( StringUtils.isEmpty( info.getStatusTypeFlag() )) {
						info.setStatusTypeFlag( StatusType.USE );
				}

				boardDAO.insert( info );
				
				return info;
		}

		public BoardInfo get( BoardCnd cnd ) {

				BoardInfo info = boardDAO.get( cnd );
				return info;
		}

		public BoardInfo get( String oid ) {

				BoardCnd cnd = new BoardCnd();
				cnd.setOid( oid );

				return get( cnd );
		}

		public BoardInfo update( BoardInfo info ) {

				boardDAO.update( info );
				
				return info;
		}

		public int delete( String oid ) {

				return boardDAO.delete( oid );
		}
		
		public int deleteFlagUpdate( BoardInfo info ) {

				if ( info.getDelDate() == null ) {
						info.setDelDate( LocalDateTime.now() );
				}
				
				return boardDAO.deleteFlagUpdate( info );
		}
		

		public PageList<BoardInfo> list( BoardCnd cnd ) {

				return boardDAO.list( cnd );
		}

		public List<BoardInfo> listAll( BoardCnd cnd ) {

				return boardDAO.listAll( cnd );
		}

}
