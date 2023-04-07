package com.remarkablesoft.framework.service.board.posting.model.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.posting.model.PostingService;
import com.remarkablesoft.framework.service.board.posting.vo.PostingCnd;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;

/**
 * <pre>
 * 설명 : 게시판 서비스 구현객체
 * </pre>
 *
 * @author James
 * @since 2015. 4. 13.
 *
 */
@Service
@Transactional
public class PostingServiceImpl implements PostingService {

		
		protected final PostingBLO postingBLO;
		
		@Autowired
		public PostingServiceImpl( PostingBLO postingBLO ) {
				super();
				this.postingBLO = postingBLO;
		}

		@Override
		public PostingInfo insertOrUpdate( PostingInfo posting ) {
				return postingBLO.insertOrUpdate( posting );
		}

		@Override
		public PostingInfo get( String oid ) {
				return postingBLO.get( oid );
		}


		@Override
		public PostingInfo get( PostingCnd cnd ) {
				return postingBLO.get( cnd );
		}

		@Override
		public PostingInfo getWithPrevAndNext( PostingCnd cnd ) {
				return postingBLO.getWithPrevAndNext( cnd );
		}

		@Override
		public PostingInfo view( PostingCnd cnd ) {
				return postingBLO.view( cnd );
		}

		@Override
		public PostingInfo viewWithPrevAndNext( PostingCnd cnd ) {
				return postingBLO.viewWithPrevAndNext( cnd );
		}

		@Override
		public PageList<PostingInfo> list( PostingCnd cnd ) {
				return postingBLO.list( cnd );
		}

		@Override
		public PageList<PostingInfo> listPostingExcludeSub( PostingCnd cnd ) {
				return postingBLO.listPostingExcludeSub( cnd );
		}

		@Override
		public PageList<PostingInfo> listWithFile( PostingCnd cnd ) {
				return postingBLO.listPostingWithFile( cnd );
		}

		@Override
		public int delete( String oid, String deleteUser ) {
				return postingBLO.delete( oid, deleteUser );
		}

		@Override
		public int listPostingCount( PostingCnd cnd ) {
				return postingBLO.listPostingCount( cnd );
		}

		@Override
		public boolean checkAnonymousPwd( String oid, String password ) {
				return postingBLO.checkAnonymousPwd( oid, password );
		}

		@Override
		public boolean checkAnonymousPwd( PostingInfo info ) {
				return postingBLO.checkAnonymousPwd( info );
		}
		
		@Override
		public List<Map<String, Object>> listByGroupBy( PostingCnd cnd ) {
				return postingBLO.listRank( cnd );
		}

		@Override
		public PageList<PostingInfo> listMyAnswerOfParent(PostingCnd cnd) {
				return postingBLO.listMyAnswerOfParent( cnd );
		}

		@Override
		public PageList<PostingInfo> listPick( PostingCnd cnd ) {
				return postingBLO.listPick( cnd );
		}
}
