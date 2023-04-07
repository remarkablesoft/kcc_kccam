package com.remarkablesoft.site.kccam.service.docshareaudit.model.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.doc.doc.model.impl.DocBLO;
import com.remarkablesoft.framework.service.doc.doc.model.impl.DocVersionBLO;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocVersionInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.WebUtils;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditCnd;
import com.remarkablesoft.site.kccam.service.docshareaudit.vo.DocShareAuditInfo;
import com.remarkablesoft.site.kccam.service.product.model.impl.ProductBLO;
import com.remarkablesoft.site.kccam.service.product.vo.ProductCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@BLO
public class DocShareAuditBLO {
		
		@Autowired
		protected DocShareAuditDAO docShareAuditDAO;
		
		@Autowired
		protected DocVersionBLO docVersionBLO;
		
		@Autowired
		protected DocBLO docBLO;
		
		@Autowired
		protected ProductBLO productBLO;
		
		/**
		 * 문서 다운/공유 기능 사용자의 로그를 저장합니다.
		 *
		 * @param info
		 * @return DocShareAuditInfo
		 * @author zero
		 */
		public DocShareAuditInfo insert( DocShareAuditInfo info ) {
				
				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}
				
				String userIp = WebUtils.getClientIp();
				info.setUserIp( userIp );
				
				docShareAuditDAO.insert( info );
				
				return info;
		}
		
		/**
		 * 문서 다운/공유 기록 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList
		 * @author zero
		 */
		public PageList<DocShareAuditInfo> list( DocShareAuditCnd cnd ) {
				
				PageList<DocShareAuditInfo> docShareList = docShareAuditDAO.list( cnd );
				
				if ( CollectionUtils.isEmpty( docShareList ) ) {
						return null;
				}
				
				filltargetInfoList( docShareList );
				
				return docShareList;
		}
		
		private void filltargetInfoList( List<DocShareAuditInfo> list ){
				
				if( CollectionUtils.isEmpty( list ) ){
						return;
				}
				
				for ( DocShareAuditInfo info : list ) {
						String shareType = info.getShareType();
						// 일반 문서 다운/공유일 경우
						if ( shareType.equals( DocShareAuditInfo.SHARE_TYPE_SHARE ) || shareType.equals( DocShareAuditInfo.SHARE_TYPE_DOWNLOAD ) ) {
								
								DocCnd cnd = new DocCnd();
								cnd.setOid( info.getTargetOidList() );
								DocVersionInfo docVersionInfo = docVersionBLO.get( cnd );
								
								if( docVersionInfo==null ){
										continue;
								}
								
								cnd.setOid( docVersionInfo.getDocOid() );
								Map<String,Object> map = new HashMap<>();
								map.put( info.getShareType(), docBLO.get( cnd ) );
								info.setTargetInfoMap( map );
								
						}
						// 제품 데이터 시트 엑셀 다운일경우
						else if ( shareType.equals( DocShareAuditInfo.SHARE_TYPE_EXCEL_DATASHEET ) ) {
								
								String[] oidList = info.getTargetOidList().split( "," );
								List<ProductInfo> productList = new ArrayList<>();
								
								for( String productOid : oidList ){
										ProductCnd cnd = new ProductCnd();
										cnd.setOid( productOid );
										ProductInfo product = productBLO.get( cnd );
										if( product != null ){
												productList.add( product );
										}
								}
								
								Map<String,Object> map = new HashMap<>();
								map.put( info.getShareType(), productList );
								info.setTargetInfoMap( map );
						}
				}
				
		}
}
