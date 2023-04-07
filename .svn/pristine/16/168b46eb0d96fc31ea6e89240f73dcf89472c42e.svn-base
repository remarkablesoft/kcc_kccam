package com.remarkablesoft.framework.service.storage.storagefile.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framwork-web
 * 		@서브 시스템		:	storage - storagefile
 * 		@프로그램 ID		:	StorageFileInfo
 * 		@프로그램 개요 		:	스토리지 파일 객체.
 * 							파일 업로드시 스토리지파일 테이블에서 hash값을 체크해서 같은 hash값이 있다면 같은 파일로 처리.
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 06.	:	james	-	신규생성
 * 		============================================================================
 */
@Getter
@Setter
@Accessors( chain = true )
@ToString
public class StorageFileInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 9057294549797103977L;

		private String storageFileUid;								// 스토리지파일 키
		private String hashValue;									// hash값
		private LocalDateTime inputDate;							// 등록일

		private int fileCount = 0;									// 해당 스토리지를 사용하는 파일 카운트
		private String customField1;								// 커스텀필드1
		private String customField2;								// 커스텀필드2
		private String customField3;								// 커스텀필드3


}
