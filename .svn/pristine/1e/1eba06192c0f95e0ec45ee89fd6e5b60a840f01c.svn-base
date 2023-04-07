package com.remarkablesoft.framework.service.board.board.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.board.vo.BoardCnd;
import com.remarkablesoft.framework.service.board.board.vo.BoardInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board-board
 * 		@프로그램 ID		:	BoardService
 * 		@프로그램 개요 		:	board는 포스팅의 부모로만 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */
public interface BoardService {

		
		public BoardInfo insert( BoardInfo info );

		public BoardInfo get( BoardCnd cnd );
		
		public BoardInfo update( BoardInfo info );
		
		public int delete( BoardInfo info );
		
		public PageList<BoardInfo> list( BoardCnd cnd );
		
		public List<BoardInfo> listAll( BoardCnd cnd );
}
