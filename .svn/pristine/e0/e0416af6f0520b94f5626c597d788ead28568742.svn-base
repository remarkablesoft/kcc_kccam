package com.remarkablesoft.framework.service.mgnt.batch.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	mgnt - batch
 * 		@프로그램 ID		:	BatchProcessInfo
 * 		@프로그램 개요 		:   배치 처리결과
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 6. 11.	:	james	-	신규생성
 * 		============================================================================
 */


@Getter
@Setter
@Accessors( chain = true )
@ToString
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class BatchProcessInfo extends Entity {


		/**
		 *
		 */
		private static final long serialVersionUID = 3845419553960648761L;


		private String oid ;											// OID
		private String batchName;										// 배치명
		private String threadName;										// 배치 스레드명 - 로그에서 오류 찾을수 있도록.
		private String className;										// 배치 클래스명
		private String apiName;											// 배치 API명

		private String executeYn ;										// 배치 실행 성공 여부
		private String successYn ;										// 배치 성공 여부
		private int totalCnt = 0;										// 총 카운트
		private int successCnt = 0;										// 성공 카운트
		private int failCnt = 0;										// 실패 카운트

		private String startTime ;										// 시작시간
		private long elapsedTime = 0;									// 걸린시간 Millis
		private String descr ;											// 설명
		private LocalDateTime inputDate = LocalDateTime.now();			// 등록일



}
