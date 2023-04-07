package com.remarkablesoft.framework.service.board.posting.model;

import java.util.List;
import java.util.Map;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.posting.vo.PostingCnd;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;

/**
 * <pre>
 * 설명 : 기본 게시판 서비스
 * </pre>
 *
 * @author James
 * @since 2015. 4. 13.
 *
 */
public interface PostingService {

		public PostingInfo insertOrUpdate( PostingInfo posting );

		/**
		 * get은 조회수 증가 없이 보는것
		 *
		 * @param oid
		 * @return
		 */
		public PostingInfo get( String oid );

		public PostingInfo get( PostingCnd cnd );

		public PostingInfo getWithPrevAndNext( PostingCnd cnd );

		/**
		 * view는 조회수가 하나 증가된다
		 *
		 * @param oid
		 * @return
		 */
		public PostingInfo view( PostingCnd cnd );

		/**
		 * 조회수 증가 되고 이전과 다음의 Posting도 같이 가져온다.
		 *
		 * @param cnd
		 * @return
		 */
		public PostingInfo viewWithPrevAndNext( PostingCnd cnd );

		public int delete( String oid, String deleteUser );

		public PageList<PostingInfo> list( PostingCnd cnd );

		/**
		 * 리스트를 가져올때 다른 정보는 추가하지 않고 가져온다.
		 * @param cnd
		 * @return
		 */
		public PageList<PostingInfo> listPostingExcludeSub( PostingCnd cnd );

		/**
		 * <pre>
		 * 리스트 가져올때 파일도 같이 가져온다. 파일을 같이 사용할때에만 이용.
		 * 일반적으로 사용시엔 listPosting을 이용한다
		 * </pre>
		 *
		 * @param cnd
		 * @return
		 */
		public PageList<PostingInfo> listWithFile( PostingCnd cnd );

		/**
		 * <pre>
		 * 리스트의 전체 카운터만 가져옵니다.
		 * </pre>
		 *
		 * @param cnd
		 * @return
		 */
		public int listPostingCount( PostingCnd cnd );

		/**
		 * 패스워드가 맞는지 확인합니다.
		 * 
		 * @author sirena
		 * @param oid
		 * @param password
		 * @return
		 */
		public boolean checkAnonymousPwd( String oid, String password );

		public boolean checkAnonymousPwd( PostingInfo info );
		
		/**
		 * 원하는 조건에 따라 동적으로 칼럼을 생성하여
		 * grouptby 로 불러옴
		 * 
		 * @author max
		 * @param cnd
		 * @return
		 */
		public List<Map<String, Object>> listByGroupBy ( PostingCnd cnd );
		
		/**
		 * 내가 답변한 질문 목록을 가져온다
		 * @param cnd
		 * @return
		 */
		public PageList<PostingInfo> listMyAnswerOfParent ( PostingCnd cnd );
		
		/**
		 * 채택여부에 따라 리스트를 가져옵니다.
		 * 
		 * @author sirena
		 * @param cnd
		 * @return
		 */
		public PageList<PostingInfo> listPick ( PostingCnd cnd );
}
