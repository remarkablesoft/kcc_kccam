package com.remarkablesoft.framework.service.mgnt.system.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemCnd;
import com.remarkablesoft.framework.service.mgnt.system.vo.SystemInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	link - system
* 		@프로그램 ID		:	SystemService
* 		@프로그램 개요 		:	시스템객체 서비스
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 12. 4.	:	james	-	신규생성
* 		============================================================================
*/
public interface SystemService {

		/**
		 *  시스템 가져오기
		 *
		 * @param cnd
		 * @return
		 */
		public SystemInfo get( SystemCnd cnd );

		/**
		 * 시스템 페이징 리스트 가져오기
		 *
		 * @param cnd
		 * @return
		 */
		public PageList<SystemInfo> list( SystemCnd cnd );

		/**
		 * 시스템 전체 리스트 가져오기
		 *
		 * @param cnd
		 * @return
		 */
		public List<SystemInfo> listAll(SystemCnd cnd);

		/**
		 * 시스템 저장하기
		 *
		 * @param info
		 * @return
		 */
		public SystemInfo insertOrUpdate( SystemInfo info );

		/**
		 * 시스템 삭제하기
		 *
		 * @param oid
		 * @return
		 */
		public int delete( String oid );

		/**
		 * 특정 시스템을 가장 최상위 순서로 올리기
		 *
		 * @param cnd
		 */
		public void moveTop( SystemCnd cnd );

}
