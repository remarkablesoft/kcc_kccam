package com.remarkablesoft.framework.service.audit.view.model.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewCnd;
import com.remarkablesoft.framework.service.audit.view.vo.AuditViewInfo;
import com.remarkablesoft.framework.util.DateUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.AutheUtils;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	audit - view
 * 		@프로그램 ID		:	AuditViewBLO
 * 		@프로그램 개요 		:   보기 카운트를 체크하는 BLO
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 12. 12.	:	james	-	신규생성
 * 		============================================================================
 */
@BLO
public class AuditViewBLO {

		@Autowired
		protected AuditViewDAO histViewDAO;

		public AuditViewInfo insert( AuditViewInfo info ) {

				if ( !StringUtils.hasText( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				// 이미 있다면 Pass => View User OID가 없으므로 로직 주석처리
//				if ( histViewDAO.exist( info ) ) {
//						return info;
//				}

				if ( info.getViewDate() != null ) {
					Map<String, String> viewDateMap = DateUtils.getDate( info.getViewDate() );
					info.setViewYear( viewDateMap.get( DateUtils.DF_PARAM_YEAR ) )
							.setViewMonth( viewDateMap.get( DateUtils.DF_PARAM_MONTH ) )
							.setViewWeek( viewDateMap.get( DateUtils.DF_PARAM_WEEK ) )
							.setViewDay( viewDateMap.get( DateUtils.DF_PARAM_DAY ) );
				}
				
				histViewDAO.insert( info );

				return info;
		}

		public int getCountByTargetObject( AuditViewCnd cnd ) {
				return histViewDAO.getCountByTargetObject( cnd );
		}

		public List<AuditViewInfo> listByTargetObjectCount( String targetObject, List<String> targetOidList ) {
				
				AuditViewCnd cnd = new AuditViewCnd();
				cnd.setTargetObject( targetObject );
				cnd.setTargetOidList( targetOidList );

				return listByTargetObjectCount( cnd );
		}

		public List<AuditViewInfo> listByTargetObjectCount( AuditViewCnd cnd ) {
				return histViewDAO.listByTargetObjectCount( cnd );
		}

		public List<AuditViewInfo> listByUserCount( AuditViewCnd cnd ) {
				return histViewDAO.listByUserCount( cnd );
		}

		public List<AuditViewInfo> listByLoginUserReadYn( String targetObject, List<String> targetOidList ) {

				String loginUser = AutheUtils.getLoginUserOid();

				if ( StringUtils.isEmpty( loginUser )) {
					return null;
				}

				AuditViewCnd cnd = new AuditViewCnd();
				cnd.setTargetObject( targetObject );
				cnd.setTargetOidList( targetOidList );
				cnd.setLoginUserVC( loginUser );

				return histViewDAO.listByLoginUserReadYn( cnd );
		}

		/**
		* Cnd의 groupByList를 사용하여 년/월/주/일 별 카운트를 가져옵니다. 
		*
		* @param cnd
		* @return List<AuditViewInfo>
		* @author 최원준
		*/
		public List<AuditViewInfo> groupByCountList( AuditViewCnd cnd ) {
			
			if ( CollectionUtils.isEmpty( cnd.getGroupByList() ) ) {
				return null;
			}
			
			List<AuditViewInfo> list = histViewDAO.groupByCountList( cnd );
			return list;
		}

}
