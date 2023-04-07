package com.remarkablesoft.framework.service.mgnt.code.model.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.code.model.CodeService;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeCnd;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeInfo;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	mgnt-code
* 		@프로그램 ID		:	CodeServiceImpl
* 		@프로그램 개요 		:	공통코드 서비스 구현체
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 10. 3.	:	james	-	신규생성
* 		============================================================================
*/
@Service
@Transactional
public class CodeServiceImpl implements CodeService {

		@Autowired
		protected CodeBLO itemCodeBLO;

		@Override
		public int save( CodeInfo info ) {
				return itemCodeBLO.save( info );
		}

		@Override
		public int updateBulk( List<CodeInfo> list ) {
				return itemCodeBLO.updateBulk( list );
		}

		@Override
		public int saveForOptionList( CodeInfo info ) {

				int result = 0;

				if ( CollectionUtils.isNotEmpty( info.getCodeList() )) {
					result = itemCodeBLO.insertBulk( info.getCodeList() );
				}

				if ( CollectionUtils.isNotEmpty( info.getDeleteCodeList() )) {
						result = itemCodeBLO.deleteByOids( info.getDeleteCodeList() );
				}

				return result;
		}

		@Override
		public List<CodeInfo> list( CodeCnd cnd ) {
				return itemCodeBLO.list( cnd );
		}

		@Override
		public PageList<CodeInfo> pageList( CodeCnd cnd ) {
				return itemCodeBLO.pageList( cnd );
		}


}
