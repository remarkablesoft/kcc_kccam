package com.remarkablesoft.framework.service.board.contents.vo;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 설명 : Contents 객체.
 * ex) Posting의 내용
 *
 * </pre>
 *
 * @author James
 * @since 2016. 2. 04.
 *
 */

@Getter
@Setter
@Accessors( chain = true)
@ToString
public class ContentsInfo extends Entity {

		/**
		 *
		 */
		private static final long serialVersionUID = -4180124331558658747L;

		private String oid;
		private String targetOid;
		private String targetObject;
		private String contents;							// 컨텐츠
		private int contentsSize;						// 컨텐츠 사이즈
	
		private String containerOid;						// 컨테이너 OID - Posting일 경우 boardOid
		private String inputUser;							// 등록자
	
		// KCCAM 신규추가
		private String contentsTitle;                      // 컨텐츠 제목
		private int orderNo;                               // 컨텐츠 순서
		private String lang;                               // 언어 ( 타켓이 다국어일 때 사용 )


}
