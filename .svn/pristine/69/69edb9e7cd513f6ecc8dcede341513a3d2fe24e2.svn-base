package com.remarkablesoft.framework.service.link.relationship.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	link - relationship
 * 		@프로그램 ID		:	RelationshipInfo
 * 		@프로그램 개요 	:		관련정보 객체
 * 							- 일부러 관계를 맺을때에는 RelationShipInfo를 사용, 시스템의 내부적으로 사용할때에는 ObjectRelInfo 사용 
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 17.	:	최원준	-	신규생성
 * 		============================================================================
 */
@Getter
@Setter
@Accessors( chain = true)
@ToString
@NoArgsConstructor
public class RelationshipInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = 1047455774929356936L;

		public static final String RELATIONSHIP_TYPE_APP = "FWRELT01";		// 관련 앱 타입

		private String oid;													// 해당 OID => PK
		private String object;												// 해당  객체
		private String relationshipOid;										// 관련정보 OID => PK
		private String relationshipObject;									// 관련정보 객체
		private String relationshipType;									// 관련정보 타입(관련 게시글, 관련 앱, 관련 댓글 등)

		private LocalDateTime inputDate = LocalDateTime.now();				// 등록일시



}
