package com.remarkablesoft.site.kccam.service.docshareaudit.vo;

import com.remarkablesoft.framework.model.vo.SearchCnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Setter
@Getter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class DocShareAuditCnd extends SearchCnd {
		
		private String oid;                                         // oid
		private String searchQueryType;								// 질의어 검색 타입
		private String searchQuery;									// 질의어
		
}
