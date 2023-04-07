package com.remarkablesoft.framework.service.mgnt.code.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeCnd;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeInfo;


/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	mgnt-code
* 		@프로그램 ID		:	CodeService
* 		@프로그램 개요 		:	공통코드 서비스
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 10. 3.	:	james	-	신규생성
* 		============================================================================
*/
public interface CodeService {

		public int save( CodeInfo info );

		public int saveForOptionList( CodeInfo info );

		public int updateBulk( List<CodeInfo> list );
		
		public List<CodeInfo> list( CodeCnd cnd );

		public PageList<CodeInfo> pageList( CodeCnd cnd );

}
