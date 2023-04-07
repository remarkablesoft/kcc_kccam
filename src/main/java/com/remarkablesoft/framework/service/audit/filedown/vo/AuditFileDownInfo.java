package com.remarkablesoft.framework.service.audit.filedown.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	audit - filedown
 * 		@프로그램 ID		:	AuditFileDownInfo
 * 		@프로그램 개요 		:	파일 다운로드 이력 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 11.	:	james	-	신규생성
 * 		1.1  2020. 11. 01.	:	james	-	audit로 패키지 변경, 클래스명 변경 FileDownAuditInfo -> AuditFileDownInfo
 * 		============================================================================
 */
public class AuditFileDownInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 72311361722956454L;

		private String oid;											// OID
		private String fileOid;										// 파일 OID
		private String postingOid;									// POSTING OID
		private String boardOid;									// BOARD OID

		private String downUser;									// 다운로드한 사용자 OID
		private LocalDateTime downDate = LocalDateTime.now();		// 다운로드한 일시

		public String getOid() {
				return oid;
		}

		public void setOid( String oid ) {
				this.oid = oid;
		}

		public String getFileOid() {
				return fileOid;
		}

		public void setFileOid( String fileOid ) {
				this.fileOid = fileOid;
		}

		public String getPostingOid() {
				return postingOid;
		}

		public void setPostingOid( String postingOid ) {
				this.postingOid = postingOid;
		}

		public String getBoardOid() {
				return boardOid;
		}

		public void setBoardOid( String boardOid ) {
				this.boardOid = boardOid;
		}

		public String getDownUser() {
				return downUser;
		}

		public void setDownUser( String downUser ) {
				this.downUser = downUser;
		}

		public LocalDateTime getDownDate() {
				return downDate;
		}

		public void setDownDate( LocalDateTime downDate ) {
				this.downDate = downDate;
		}

		@Override
		public String toString() {
				StringBuilder builder = new StringBuilder();
				builder.append( "FileDownAuditInfo [oid=" ).append( oid ).append( ", fileOid=" ).append( fileOid ).append( ", postingOid=" ).append( postingOid ).append( ", boardOid=" ).append( boardOid ).append( ", downUser=" ).append( downUser ).append( ", downDate=" ).append( downDate ).append( "]" );
				return builder.toString();
		}

}
