package com.remarkablesoft.framework.service.org.branch.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.branch.vo.BranchCnd;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;

public interface BranchService {

		/**
		 * 지점 정보를 입력합니다.
		 * @param info
		 * @return
		 */
		public BranchInfo insert( BranchInfo info );

		/**
		 * 지점 정보를 수정합니다.
		 * @param info
		 * @return
		 */
		public int update( BranchInfo info );

		/**
		 * 지점 정보를 삭제합니다.
		 * @param info
		 * @return
		 */
		public int delete ( BranchInfo info );


		/**
		 * 지점 정보를 반환합니다.
		 * @param cnd
		 * @return
		 */
		public BranchInfo get( BranchCnd cnd );

		/**
		 * 지점 정보를 모두 가져옵니다.
		 *
		 * @param cnd
		 * @return
		 */
		public List<BranchInfo> listAll ( BranchCnd cnd );

		/**
		 * 지점 페이지 리스트를 반환합니다.
		 * @param cnd
		 * @return
		 */
		public PageList<BranchInfo> list ( BranchCnd cnd );


		/**
		 * 지점 리스트를 넘겨서 등록, 수정, 삭제를 처리합니다.
		 *
		 * @param info
		 * @return
		 */
		public int saveForBranchList( BranchInfo info );
}
