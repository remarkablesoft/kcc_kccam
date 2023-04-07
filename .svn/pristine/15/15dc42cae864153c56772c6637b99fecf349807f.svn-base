package com.remarkablesoft.framework.web.util;

import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.audit.visit.model.AuditVisitService;
import com.remarkablesoft.framework.service.audit.visit.vo.AuditVisitInfo;
import com.remarkablesoft.framework.service.mgnt.config.model.EnvConfigService;
import com.remarkablesoft.framework.service.mgnt.config.vo.EnvConfigInfo;
import com.remarkablesoft.framework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 		@주시스템		:	KCCAM
 * 		@서브 시스템		:	common
 * 		@프로그램 ID		:	VisitWebUtils
 * 		@프로그램 개요 	:	방문자의 이력 ( IP, 시간, URL ) 이력 남기는 프로그램
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 5. 16.	:	james	-	신규생성
 * 			 2021. 7. 21.	:	남윤재	-	쿠키값을 비교하지 않고 항상 누적하는 insertVisit 메소드 추가
 * 		============================================================================
 */
@Component
public class VisitWebUtils {

		@Autowired
		protected AuditVisitService auditVisitService;
		
		@Autowired
		protected EnvConfigService envConfigService;
		
		
		/**
		 * 방문했다는 쿠키가 있다면 pass 없다면 등록 후 방문했다는 쿠키를 생성
		 *
		 * @param request
		 * @param response
		 */
		public void checkVisit( HttpServletRequest request, HttpServletResponse response ) {
				
				// 유저가 방문 했는지 확인
				String visitCookie = CookieUtils.getCookieValue( request, AuditVisitInfo.VISIT_FLAG );
				
				if ( StringUtils.isNotEmpty( visitCookie ) ) {
						return;
				}
				
				//System.out.println ("@@ visitCookie : " + visitCookie);
				//System.out.println ("@@ referer : " + request.getHeader("referer"));
				
				EnvConfigInfo env = envConfigService.get( "system.audit.visit.expiration.period" );
				
				// 방문했다는 쿠키가 있다면 pass 없다면 등록 후 방문했다는 쿠키를 생성
				int cookieExpiration = Integer.parseInt( env.getEnvValue() );
				AuditVisitInfo info = getAuditVisitInfo( request );
				auditVisitService.insert( info );
				CookieUtils.addCookie( response, cookieExpiration, AuditVisitInfo.VISIT_FLAG, "true" );
				
		}
		
		/**
		 * 방문 이력을 저장하되 info객체 ( visitUrl 을 저장하기 위해 )을 인자값으로 가집니다.
		 *
		 * @author 남윤재
		 * @param request
		 * @param paramInfo
		 */
		public void insertVisit( HttpServletRequest request, AuditVisitInfo paramInfo ) {
				AuditVisitInfo insertInfo = getAuditVisitInfo( request );
				insertInfo.setVisitURL( paramInfo.getVisitURL() );
				insertInfo.setVisitPageName( paramInfo.getVisitPageName() );
				auditVisitService.insert( insertInfo );
		}
		
		private AuditVisitInfo getAuditVisitInfo( HttpServletRequest request ) {

				return SystemFactory.getAuditVisitInfo()
								   					.setDefaultInfo()
								   					.setUserIp( WebUtils.getClientIp( request ) )
													.setUserAgent( WebUtils.getUserBrowser( request ) );
		}

}
