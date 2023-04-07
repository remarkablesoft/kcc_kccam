package com.remarkablesoft.framework.service.link.relationship.vo;

import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	link - relationship
 * 		@프로그램 ID		:	RelationshipCnd
 * 		@프로그램 개요 		:	관련정보 검색 조건 객체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 17.	:	최원준	-	신규생성
 * 		============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class RelationshipCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -7785350953377110228L;

		private String oid = "";							// 해당 OID
		private String object = "";							// 해당 객체
		private String relationshipOid = "";				// 관련정보 OID
		private String relationshipObject = "";				// 관련정보 객체
		private String relationshipType = "";				// 관련정보 타입(관련 게시글, 관련 앱, 관련댓글 등)

		private List<String> oidList = null;				// OID 리스트


}
