package com.remarkablesoft.framework.service.doc.doc.model.impl;

import java.io.StringWriter;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.model.impl.MessageSendBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.web.module.notification.email.impl.kcc.exception.KccMailException;
import com.remarkablesoft.site.kccam.service.AmConstants;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.common.constants.StatusType;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocRelInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocVersionInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.notification.email.impl.kcc.KccEmailSendProvider;
import com.remarkablesoft.framework.web.util.AutheUtils;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;

/**
 *
 *        @주시스템        :	framework-web
 *        @서브 시스템        :	doc - doc
 *        @프로그램 ID        :	DocBLO
 *        @프로그램 개요    :	문서정보 BLO
 *
 *        @변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

@BLO
public class DocBLO {
		
		@Autowired
		protected DocDAO docDAO;
		
		@Autowired
		protected DocRelBLO docRelBLO;
		
		@Autowired
		protected DocVersionBLO docVersionBLO;
		
		@Autowired
		protected UserBLO userBLO;
		
		@Autowired
		KccEmailSendProvider kccEmailSendProvider;
		
		@Autowired
		protected MessageSendBLO messageSendBLO;
		
		/**
		 * 문서정보를 저장합니다.
		 *
		 * @param info
		 * @return DocInfo
		 * @author 최원준
		 */
		public DocInfo insert( DocInfo info ) {
				
				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}
				
				if ( StringUtils.isEmpty( info.getInputUser() ) ) {
						info.setInputUser( AutheUtils.getLoginUserOid() )
									.setInputUserInfoList( userBLO.convertUserInfoList( AutheUtils.getLoginUserOid() ) );
				}
				
				// 문서 관계정보를 저장합니다.
				info.setDocRelInfo( docRelBLO.insert( info ) );
				// 문서 버전정보를 저장합니다.
				DocVersionInfo docVersionInfo = docVersionBLO.insert( info );
				info.setCurrentDocVersionInfo( docVersionInfo );
				if ( docVersionInfo != null ) {
						info.setCurrentVersionOid( docVersionInfo.getOid() );
				}
				
				return docDAO.insert( info ) > 0 ? info : null;
		}
		
		/**
		 * 타겟 정보와 함께 문서를 저장합니다.
		 *
		 * @param info
		 * @param targetOid
		 * @param targetObject
		 * @return DocInfo
		 * @author 최원준
		 */
		public DocInfo insert( DocInfo info, String targetOid, String targetObject ) {
				
				if ( StringUtils.isEmpty( targetOid ) || StringUtils.isEmpty( targetObject ) ) {
						return null;
				}
				
				DocRelInfo docRelInfo = SystemFactory.getDocRelInfo();
				docRelInfo.setTargetOid( targetOid ).setTargetObject( targetObject );
				info.setDocRelInfo( docRelInfo );
				
				// 관계정보만 저장합니다.
				if ( info.isOnlyDocRel() && StringUtils.hasText( info.getOid() ) ) {
						docRelBLO.insert( info );
						return info;
				}
				
				return insert( info );
		}
		
		/**
		 * 문서정보를 수정합니다.
		 *
		 * @param info
		 * @return DocInfo
		 * @author 최원준
		 */
		public DocInfo update( DocInfo info ) {
				
				if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}
				
				info.setModDate( LocalDateTime.now() );
				
				if ( StringUtils.isEmpty( info.getModUser() ) ) {
						info.setModUser( AutheUtils.getLoginUserOid() );
				}
				
				info.setCurrentDocVersionInfo( docVersionBLO.update( info ) );
				
				return docDAO.update( info ) > 0 ? info : null;
		}
		
		public DocInfo insertOrUpdate( DocInfo info ) {
				
				if ( info == null ) {
						return null;
				}
				
				if ( StringUtils.isEmpty( info.getOid() ) ) {
						
						info.setOid( OIDGenerator.generateOID() );
						
						insert( info );
				}
				else {
						
						update( info );
				}
				
				return info;
		}
		
		/**
		 * 문서 버전을 업데이트 합니다.
		 *
		 * @param info
		 * @return DocInfo
		 * @author 최원준
		 */
		public DocInfo updateVersion( DocInfo info ) {
				
				if ( info == null || StringUtils.isEmpty( info.getOid() ) || info.getCurrentDocVersionInfo() == null ) {
						return null;
				}
				
				// 기존의 문서 버전정보들을 중지 상태로 변경합니다.
				DocVersionInfo stopDocVersionInfo = SystemFactory.getDocVersionInfo();
				stopDocVersionInfo.setDocOid( info.getOid() ).setStatusTypeFlag( StatusType.STOP.getKey() );
				docVersionBLO.update( stopDocVersionInfo );
				
				// 새로운 문서 정보를 저장합니다.
				DocVersionInfo currentDocVersionInfo = docVersionBLO.insert( info );
				if ( currentDocVersionInfo == null ) {
						info.setCurrentVersionOid( currentDocVersionInfo.getOid() )
									.setCurrentDocVersionInfo( currentDocVersionInfo );
				}
				
				return info;
		}
		
		/**
		 * 문서정보를 삭제합니다.
		 *
		 * @param oid
		 * @return int
		 * @author 최원준
		 */
		public int delete( String oid ) {
				
				if ( StringUtils.isEmpty( oid ) ) {
						return 0;
				}
				
				// 문서 관련 정보를 삭제합니다.
				DocCnd doc = new DocCnd();
				doc.setDocOid( oid );
				docRelBLO.delete( doc );
				
				docVersionBLO.delete( doc );
				
				return docDAO.delete( oid );
		}
		
		/**
		 * 문서정보를 삭제상태로 업데이트합니다.
		 *
		 * @param oid
		 * @return
		 */
		public int deleteFlagUpdate( String oid ) {
				
				DocCnd cnd = new DocCnd();
				cnd.setOid( oid );
				DocInfo info = get( cnd );
				
				DocVersionInfo docVersionInfo = info.getCurrentDocVersionInfo();
				if ( info == null || docVersionInfo == null ) {
						return 0;
				}
				
				docVersionInfo.setStatusTypeFlag( StatusType.STOP.getKey() );
				docVersionInfo = docVersionBLO.update( info.getCurrentDocVersionInfo() );
				
				return docVersionInfo == null ? 0 : 1;
		}
		
		/**
		 * 문서의 관계정보만 삭제합니다.
		 *
		 * @param oid
		 * @return int
		 * @author 최원준
		 */
		public int deleteRelOnly( String oid, String targetOid, String targetObject ) {
				
				if ( StringUtils.isEmpty( oid ) || StringUtils.isEmpty( targetOid ) || StringUtils.isEmpty( targetObject ) ) {
						return 0;
				}
				
				DocCnd cnd = new DocCnd();
				cnd.setDocOid( oid ).setTargetOid( targetOid ).setTargetObject( targetObject );
				return docRelBLO.delete( cnd );
				
		}
		
		/**
		 *  다수의 문서 관계정보를 삭제합니다.
		 *
		 * @param list
		 * @param targetOid
		 * @param targetObject
		 * @return int
		 * @author 최원준
		 */
		public int deleteRelOnly( List<DocInfo> list, String targetOid, String targetObject ) {
				
				if ( CollectionUtils.isEmpty( list ) ) {
						return 0;
				}
				
				DocCnd cnd = new DocCnd();
				cnd.setTargetOid( targetOid ).setTargetObject( targetObject );
				List<String> docOidList = list.stream().map( DocInfo::getOid ).collect( Collectors.toList() );
				cnd.setDocOidList( docOidList );
				
				return docRelBLO.deleteTargetBulk( cnd );
				
		}
		
		/**
		 * 문서 정보를 가져옵니다.
		 *
		 * @param cnd
		 * @return DocInfo
		 * @author 최원준
		 */
		public DocInfo get( DocCnd cnd ) {
				
				if ( cnd == null ) {
						return null;
				}
				
				DocInfo info = docDAO.get( cnd );
				
				fillDocRel( info );
				fillDocVersion( info );
				
				return info;
		}
		
		/**
		 * 문서정보 페이지리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList<DocInfo>
		 * @author 최원준
		 */
		public PageList<DocInfo> list( DocCnd cnd ) {
				
				if ( cnd == null ) {
						return null;
				}
				
				PageList<DocInfo> list = docDAO.list( cnd );
				
				// 문서의 관계정보를 채워줍니다.
				fillDocRelInfo( list );
				
				fillDocVersion( list, cnd );
				
				return list;
		}
		
		/**
		 * 문서정보 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<DocInfo>
		 * @author 최원준
		 */
		public List<DocInfo> listAll( DocCnd cnd ) {
				
				if ( cnd == null ) {
						return null;
				}
				
				List<DocInfo> list = docDAO.listAll( cnd );
				if ( CollectionUtils.isEmpty( list ) ) {
						return null;
				}
				
				fillDocVersion( list, cnd );
				
				return list;
		}
		
		/**
		 * Doc-DocRel View 에서 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<DocInfo>
		 * @author 최원준
		 */
		public List<DocInfo> viewList( DocCnd cnd ) {
				
				if ( cnd == null ) {
						return null;
				}
				
				List<DocInfo> list = docDAO.viewList( cnd );
				
				fillDocVersion( list, cnd );
				
				return list;
		}
		
		/**
		 * 문서의 관계정보를 채워줍니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void fillDocRel( DocInfo info ) {
				
				if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
						return;
				}
				
				DocCnd cnd = new DocCnd();
				cnd.setDocOid( info.getOid() );
				List<DocRelInfo> docRelList = docRelBLO.listAll( cnd );
				
				if ( CollectionUtils.isEmpty( docRelList ) ) {
						return;
				}
				
				info.setDocRelList( docRelList );
				
		}
		
		/**
		 * 문서의 관계정보를 채워줍니다.
		 *
		 * @param list
		 * @author yunsung.s
		 */
		private void fillDocRelInfo( List<DocInfo> list ) {
				
				if ( CollectionUtils.isEmpty( list ) ) {
						return;
				}
				
				List<String> docOidList = list.stream().map( DocInfo::getOid ).distinct().collect( Collectors.toList() );
				
				DocCnd cnd = new DocCnd();
				cnd.setDocOidList( docOidList );
				
				List<DocRelInfo> docRelList = docRelBLO.listAll( cnd );
				
				if ( CollectionUtils.isEmpty( docRelList ) ) {
						return;
				}
				
				list.forEach( doc -> docRelList.stream()
												 .filter( rel -> StringUtils.hasText( rel.getDocOid() ) )
												 .filter( rel -> doc.getOid().equals( rel.getDocOid() ) )
												 .forEach( doc::setDocRelInfo ) );
		}
		
		/**
		 * 문서의 버전정보를 채워줍니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void fillDocVersion( DocInfo info ) {
				
				if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
						return;
				}
				
				DocCnd cnd = new DocCnd();
				cnd.setDocOid( info.getOid() );
				cnd.setFillFile( true );
				List<DocVersionInfo> docVersionList = docVersionBLO.listAll( cnd );
				
				if ( CollectionUtils.isEmpty( docVersionList ) ) {
						return;
				}
				
				info.setDocVersionList( docVersionList );
				docVersionList.forEach( docVersion -> {
						if ( docVersion.getOid().equals( info.getCurrentVersionOid() ) ) {
								info.setCurrentDocVersionInfo( docVersion );
						}
				} );
				
		}
		
		/**
		 * 문서 리스트의 버전정보를 채워줍니다.
		 *
		 * @param list
		 * @param cnd
		 * @author 최원준
		 */
		private void fillDocVersion( List<DocInfo> list, DocCnd cnd ) {
				
				DocCnd docVersionCnd = new DocCnd();
				List<String> oidList = new ArrayList<>();
				
				if ( cnd.isOnlyCurrentVersion() ) {
						oidList = list.stream().map( DocInfo::getCurrentVersionOid ).collect( Collectors.toList() );
						docVersionCnd.setOidList( oidList );
				}
				else {
						oidList = list.stream().map( DocInfo::getOid ).collect( Collectors.toList() );
						docVersionCnd.setDocOidList( oidList );
				}
				
				docVersionCnd.setFillFile( cnd.isFillFile() );
				List<DocVersionInfo> docVersionList = docVersionBLO.listAll( docVersionCnd );
				if ( CollectionUtils.isEmpty( docVersionList ) ) {
						return;
				}
				
				Map<String, List<DocVersionInfo>> groupedMap = docVersionList.stream()
																		   .collect( Collectors.groupingBy( DocVersionInfo::getDocOid ) );
				
				list.forEach( doc -> {
						List<DocVersionInfo> docOidMatchList = groupedMap.get( doc.getOid() );
						
						if( CollectionUtils.isEmpty( docOidMatchList )){
								return;
						}
						
						doc.setDocVersionList( docOidMatchList );
						docOidMatchList.stream()
									.filter( docVersion -> doc.getCurrentVersionOid().equals( docVersion.getOid() ) )
									.forEach( docVersion -> doc.setCurrentDocVersionInfo( docVersion ) );
				} );
				
		}
		
		public int sendEmailWithDocFile( HashMap<String, Object> paramMap ) {
				
				//파일과 함께 메일 전송
				return kccEmailSendProvider.sendMail( paramMap );
		}
		
		/*
		 * 유저가 입력한 메일주소로 파일 다운로드 링크를 담은 메일을 보냅니다. (문서 공유)
		 * @param info
		 * @author 황지영
		 * */
		public void sendEmailWithDocLink( DocInfo info ) {
				if ( info == null || StringUtils.isEmpty( info.getShareEmailAddress() ) ) {
						return;
				}
				
				AtomicReference<String> receiverMail = new AtomicReference<>( "" );
				receiverMail.set( info.getShareEmailAddress() );
				
				MessageInfo message = setMessage( info );
				
				UserInfo user = SystemFactory.getUserInfo();
				user.setEmail( info.getShareEmailAddress() );
				
				messageSendBLO.send( message, user, null );
		}
		
		/*
		 *  메세지 정보를 설정합니다
		 *
		 * @param info
		 * @return MessageInfo
		 * @author 황지영
		 * */
		private MessageInfo setMessage( DocInfo info ) {
				String contents = makeEmailTemplate( info );
				
				MessageInfo message = SystemFactory.getMessageInfo();
				
				message.setMsgTypeMode( MessageInfo.MESSAGE_TYPE_MODE_EMAIL )
							.setName( "KCC Advanced Materials 파일 공유 메일입니다." )
							.setContents( contents )
							.setSendTypeFlag( MessageInfo.MESSAGE_SEND_TYPE_FLAG_IMMEDIATE );
				
				return message;
		}
		
		/*
		 * 이메일 템플릿에 내용을 적용합니다
		 *
		 * @param info
		 * @return String
		 * */
		private String makeEmailTemplate( DocInfo info ) {
				
				StringWriter writer = new StringWriter();
				
				try {
						VelocityEngine ve = new VelocityEngine();
						ve.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
						ve.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
						ve.init();
						
						String templateName = MessageFormat.format( "/templates/docShareTemplate.html", Locale.getDefault() );
						VelocityContext context = new VelocityContext();
						
						context.put( "title", "KCC Advanced Materials 문서 공유" );
						context.put( "docTitle", StringUtils.hasText( info.getTitle() ) ? info.getTitle() : AmConstants.HYPHEN );
						context.put( "docLink", StringUtils.hasText( info.getShareDocLink() ) ? info.getShareDocLink() : AmConstants.HYPHEN );
						
						Template template = ve.getTemplate( templateName, "UTF-8" );
						template.merge( context, writer );
				}
				catch ( KccMailException e ) {
						e.printStackTrace();
				}
				
				return writer.toString();
		}
}
