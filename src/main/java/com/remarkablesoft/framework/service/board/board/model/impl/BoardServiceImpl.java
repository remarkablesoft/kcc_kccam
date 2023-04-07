package com.remarkablesoft.framework.service.board.board.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.board.model.BoardService;
import com.remarkablesoft.framework.service.board.board.vo.BoardCnd;
import com.remarkablesoft.framework.service.board.board.vo.BoardInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board-board
 * 		@프로그램 ID		:	BoardServiceImpl
 * 		@프로그램 개요 		:	board는 포스팅의 부모로만 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	hong	-	신규생성
 * 		============================================================================
 */
@Service
@Transactional
public class BoardServiceImpl implements BoardService{

		@Autowired
		protected BoardBLO boardBLO;
		
		@Override
		public BoardInfo insert( BoardInfo info ) {
				
				return boardBLO.insert( info );
		}

		@Override
		public BoardInfo get( BoardCnd cnd ) {
				
				return boardBLO.get( cnd );
		}

		@Override
		public BoardInfo update( BoardInfo info ) {
				
				return boardBLO.update( info );
		}

		@Override
		public int delete( BoardInfo info ) {
				
				return boardBLO.deleteFlagUpdate( info );
		}

		@Override
		public PageList<BoardInfo> list( BoardCnd cnd ) {
				
				return boardBLO.list( cnd );
		}

		@Override
		public List<BoardInfo> listAll( BoardCnd cnd ) {
				
				return boardBLO.listAll( cnd );
		}

		
		
}
