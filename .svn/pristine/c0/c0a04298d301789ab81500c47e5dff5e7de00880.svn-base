package com.remarkablesoft.framework.service.mgnt.batch.model;

import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessCnd;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessInfo;


/**
 *
 * 		@주시스템			:	FrameWork
 * 		@서브 시스템		:
 * 		@프로그램 ID		:	BatchProcessService.java
 * 		@프로그램 개요 		:	배치 프로세스 서비스 - 배치 Aspect에서 사용
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 6. 11.	:	james	-	신규생성
 * 		============================================================================
 */
public interface BatchProcessService {


		/**
		 * 배치 상황 등록
		 *
		 * @param info
		 * @return
		 */
		public int insert( BatchProcessInfo info );


        /**
         * 해당 배치가 있는지 확인
         * 현재는 DAY만 확인
         *
         * @param cnd
         * @return
         */
		public boolean exist( BatchProcessCnd cnd );
}
