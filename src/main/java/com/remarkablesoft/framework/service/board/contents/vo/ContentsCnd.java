package com.remarkablesoft.framework.service.board.contents.vo;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.SearchCnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 설명 : 내용 검색
 *
 * @author 정지호
 * @since 2017. 6. 21.
 *
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class ContentsCnd extends SearchCnd {

		/**
		 *
		 */
		private static final long serialVersionUID = -2819854032376916316L;
		
		private String oid;
		
		private String targetOid;
		private String targetObject;
		private String containerOid;						// 컨테이너 OID - Posting일 경우 boardOid
		private String lang;

		private List<String> targetOidList = new ArrayList<String>();


}
