package com.remarkablesoft.site.kccam.service.onetoone.onetoone.model;

import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneInfo;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneCnd;

/**
 *
 *        @주시스템            :	framework-web
 *        @서브 시스템        :	onetoone - onetoone
 *        @프로그램 ID        :	OneToOneService
 *        @프로그램 개요        :	OneToOne Service
 *
 *        @변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

public interface OneToOneService {
		
		/**
		 *  1대1 문의정보를 저장합니다.
		 *
		 * @param info
		 * @return OneToOneInfo
		 * @author 최원준
		 */
		public OneToOneInfo insert( OneToOneInfo info );
		
		/**
		 *  1대1 정보를 수정합니다.
		 *
		 * @param info
		 * @return OneToOneInfo
		 * @author 최원준
		 */
		public OneToOneInfo update( OneToOneInfo info );
		
		/**
		 *  1대1 정보를 삭제합니다.
		 *
		 * @param oid
		 * @return int
		 * @author 최원준
		 */
		public int delete( String oid );
		
		/**
		 *  1대1 정보를 가져옵니다.
		 *
		 * @param cnd
		 * @return OneToOneInfo
		 * @author 최원준
		 */
		public OneToOneInfo get( OneToOneCnd cnd );
		
		/**
		 *  1대1 정보 페이지리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList<OneToOneInfo>
		 * @author 최원준
		 */
		public PageList<OneToOneInfo> list( OneToOneCnd cnd );
		
		/**
		 *  1대1 정보 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<OneToOneInfo>
		 * @author 최원준
		 */
		public List<OneToOneInfo> listAll( OneToOneCnd cnd );
		
		/**
		 * 수신 담당자에게 이메일을 발송합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		public void sendEmail( OneToOneInfo info );
		
		/**
		 * 문의자에게 메일을 보냅니다.
		 *
		 * @param info
		 */
		public void sendEmailToCustomer( OneToOneInfo info );
}
