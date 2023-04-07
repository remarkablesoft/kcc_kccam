package com.remarkablesoft.framework.service.storage.file.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 설명 : 파일객체
 *
 * @author james
 * @since 2014. 5. 15.
 *
 */
@Getter
@Setter
@Accessors( chain = true )
@ToString
public class FileInfo extends Entity {
	
		/**
		 * 
	 	*/
		private static final long serialVersionUID = -2703455494029806705L;


		public static final String FILE_FLAG_USE = "Y";
		public static final String FILE_FLAG_USE_NOT = "N";
	
		private String oid;
		private String storageFileUid;
		private String fileType;				// 파일 타입이 어떤 타입인지( Ex. 첨부파일, 썸네일 )
		private String targetOid; 				// 타겟 객체의 Oid
		private String targetObject; 			// 타겟 객체의 타입

		private String containerOid;			// 컨테이너 OID - Posting일 경우 boardOid
		private String fileName;
		private long fileSize = 0;
		private String fileExt; 				// 파일 확장자
		private String fileUrl;					// 파일이 가지고 있는 URL

		private LocalDateTime filePlayTime;		// 파일 재생시간
		private String useYn = FILE_FLAG_USE;
		private String thumbYn = FILE_FLAG_USE_NOT;
		private String inputUser;
		private LocalDateTime inputDate = LocalDateTime.now();
		private int orderNo = 0; 				// 파일 업로드 순서

		private String customField1;
		private String customField2;
		private String customField3;
		private String customField4;
		private String customField5;

		/**
    	 * 삭제시 사용 : oid로 체크.
    	 */
		@Override
		public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((oid == null) ? 0 : oid.hashCode());
				return result;
		}

		/**
		 * 삭제시 사용 : oid로 체크.
		 */
		@Override
		public boolean equals( Object obj ) {
				if ( this == obj )
						return true;
				if ( obj == null )
						return false;
				if ( getClass() != obj.getClass() )
						return false;
				FileInfo other = (FileInfo) obj;
				if ( oid == null ) {
						if ( other.oid != null )
								return false;
				}
				else if ( !oid.equals( other.oid ) )
						return false;
				return true;
		}



}
