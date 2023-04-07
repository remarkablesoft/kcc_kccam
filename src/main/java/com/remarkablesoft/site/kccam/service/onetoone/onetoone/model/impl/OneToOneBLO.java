package com.remarkablesoft.site.kccam.service.onetoone.onetoone.model.impl;

import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.web.module.storage.StorageUtils;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneDetailInfo;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.apache.commons.mail.HtmlEmail;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.notification.message.send.model.impl.MessageSendBLO;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.model.impl.UserDAO;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.file.model.impl.FileBLO;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.module.notification.email.impl.kcc.exception.KccMailException;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.onetoone.config.model.impl.OneToOneConfigDAO;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigCnd;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigInfo;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneCnd;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneInfo;

/**
 *
 *        @주시스템        :	kccam
 *        @서브 시스템        :	onetoone - onetoone
 *        @프로그램 ID        :	OneToOneBLO
 *        @프로그램 개요    :	1대1 문의 BLO
 *
 *        @변경이력
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

@BLO
public class OneToOneBLO {
		
		@Autowired
		protected OneToOneDAO oneToOneDAO;
		
		@Autowired
		protected OneToOneConfigDAO oneToOneConfigDAO;
		
		@Autowired
		protected OneToOneDetailBLO oneToOneDetailBLO;
		
		@Autowired
		protected UserBLO userBLO;
		
		@Autowired
		protected FileBLO fileBLO;
		
		@Autowired
		protected MessageSendBLO messageSendBLO;
		
		/**
		 * 1대1 문의 정보를 저장합니다.
		 *
		 * @param info
		 * @return OneToOneInfo
		 * @author 최원준
		 */
		public OneToOneInfo insert( OneToOneInfo info ) {
				
				if ( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}
				OneToOneConfigCnd configCnd = new OneToOneConfigCnd();
				configCnd.setOid( info.getConfigOid() );
				OneToOneConfigInfo questionInfo = oneToOneConfigDAO.get( configCnd );
				info.setCustomField1( questionInfo.getConfigQuestion() );
				
				insertUser( info );
				insertOneToOneDetail( info );
				insertFile( info );
				sendEmail( info );
				
				return oneToOneDAO.insert( info ) > 0 ? info : null;
		}
		
		/**
		 * 1대1 문의 정보를 수정합니다.
		 *
		 * @param info
		 * @return OneToOneInfo
		 * @author 최원준
		 */
		public OneToOneInfo update( OneToOneInfo info ) {
				
				if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
						return null;
				}
				
				return oneToOneDAO.update( info ) > 0 ? info : null;
		}
		
		/**
		 * 1대1 문의 정보를 삭제합니다.
		 *
		 * @param oid
		 * @return int
		 * @author 최원준
		 */
		public int delete( String oid ) {
				
				if ( StringUtils.isEmpty( oid ) ) {
						return 0;
				}
				
				return oneToOneDAO.delete( oid );
		}
		
		/**
		 *  특정 작성자의 1대1 문의정보를 삭제합니다.
		 *
		 *  @param oid
		 *  @return int
		 *  @author 남윤재
		 */
		public int deleteByInputUser( String oid ) {
				if ( StringUtils.isEmpty( oid ) ) {
						return 0;
				}
				return oneToOneDAO.deleteByInputUser( oid );
				
		}
		
		
		/**
		 * 1대1 문의 정보를 가져옵니다.
		 *
		 * @param cnd
		 * @return OneToOneInfo
		 * @author 최원준
		 */
		public OneToOneInfo get( OneToOneCnd cnd ) {
				
				if ( cnd == null ) {
						return null;
				}
				OneToOneInfo info = oneToOneDAO.get( cnd );
				
				if ( cnd.isFillDetail() ) {
						fillDetail( info );
				}
				return info;
		}
		
		/**
		 *  1대1 문의 목록 정보를 채워줍니다.
		 *
		 *  @param info
		 *  @author 남윤재
		 */
		public void fillDetail( OneToOneInfo info ) {
				
				// 문의한 고객의 정보를 OneToOneInfo 에 채워주기
				UserInfo userInfo = userBLO.getOnlyUser( info.getInputUser() );
				info.setInputUserInfo( userInfo );
				
				// 첨부파일 리스트 채우기
				FileCnd fileCnd = new FileCnd();
				fileCnd.setTargetOid( info.getOid() );
				List<FileInfo> fileList = fileBLO.listAll( fileCnd );
				info.setFileList( fileList );
				
				// 추가문의 종류와 추가 문의 내용 채우기
				OneToOneCnd detailCnd = new OneToOneCnd();
				detailCnd.setOtoOid( info.getOid() );
				List<OneToOneDetailInfo> detailInfo = oneToOneDetailBLO.listAll( detailCnd );
				info.setOneToOneDetailList( detailInfo );
				
				// 수신자 이메일 리스트 채우기
				OneToOneConfigCnd receiverCnd = new OneToOneConfigCnd();
				receiverCnd.setParentOid( info.getConfigOid() );
				receiverCnd.setConfigType( OneToOneConfigInfo.CONFIG_TYPE_RECEIVER_EMAIL );
				List<OneToOneConfigInfo> receiverList = oneToOneConfigDAO.listAll( receiverCnd );
				info.setReceiverEmailList( receiverList );
				
		}
		
		/**
		 * 1대1 문의 페이지 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return PageList<OneToOneInfo>
		 * @author 최원준
		 */
		public PageList<OneToOneInfo> list( OneToOneCnd cnd ) {
				
				if ( cnd == null ) {
						return null;
				}
				PageList<OneToOneInfo> list = oneToOneDAO.list( cnd );
				
				list.forEach( item -> item.setInputUserInfo( userBLO.getOnlyUser( item.getInputUser() ) ) );
				
				list.forEach( item -> {
						OneToOneConfigCnd configCnd = new OneToOneConfigCnd();
						configCnd.setOid( item.getConfigOid() );
						OneToOneConfigInfo questionInfo = oneToOneConfigDAO.get( configCnd );
						item.setCustomField1( questionInfo.getConfigQuestion() );
						
						OneToOneConfigCnd receiverCnd = new OneToOneConfigCnd();
						receiverCnd.setParentOid( item.getConfigOid() );
						receiverCnd.setConfigType( OneToOneConfigInfo.CONFIG_TYPE_RECEIVER_EMAIL );
						List<OneToOneConfigInfo> receiverList = oneToOneConfigDAO.listAll( receiverCnd );
						item.setReceiverEmail( receiverList.get( 0 ).getConfigReceiverEmail() );
						
				} );
				
				return list;
		}
		
		/**
		 * 1대1문의 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<OneToOneInfo>
		 * @author 최원준
		 */
		public List<OneToOneInfo> listAll( OneToOneCnd cnd ) {
				
				if ( cnd == null ) {
						return null;
				}
				
				return oneToOneDAO.listAll( cnd );
		}
		
		/**
		 * 등록자 정보를 저장합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertUser( OneToOneInfo info ) {
				
				if ( info.getInputUserInfo() == null || StringUtils.isEmpty( info.getInputUserInfo().getEmail() ) ) {
						return;
				}
				
				UserCnd userCnd = new UserCnd();
				userCnd.setEmail( info.getInputUserInfo().getEmail() );
				UserInfo user = userBLO.getUser( userCnd );
				
				if ( user == null || StringUtils.isEmpty( user.getOid() ) ) {
						user = userBLO.insert( info.getInputUserInfo() );
				}
				
				info.setInputUser( user.getOid() );
		}
		
		/**
		 * 1:1 문의 세부항목을 저장합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertOneToOneDetail( OneToOneInfo info ) {
				
				if ( CollectionUtils.isEmpty( info.getOneToOneDetailList() ) ) {
						return;
				}
				
				info.getOneToOneDetailList().forEach( otoDetail -> {
						otoDetail.setOtoOid( info.getOid() );
						oneToOneDetailBLO.insert( otoDetail );
				} );
				
		}
		
		/**
		 * 파일 정보를 저장합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		private void insertFile( OneToOneInfo info ) {
				
				if ( CollectionUtils.isEmpty( info.getFileList() ) ) {
						return;
				}
				
				info.getFileList().forEach( file -> {
						file.setInputUser( info.getInputUser() );
						fileBLO.insert( file, OneToOneInfo.getObjectType(), info.getOid(), info.getConfigOid() );
				} );
				
		}
		
		/**
		 * 수신 담당자에게 이메일을 발송합니다.
		 *
		 * @param info
		 * @author 최원준
		 */
		public void sendEmail( OneToOneInfo info ) {
				
				if ( CollectionUtils.isEmpty( info.getReceiverEmailList() ) ) {
						return;
				}
				
				AtomicReference<String> receiverEmail = new AtomicReference<>( "" );
				info.getReceiverEmailList().forEach( otoConfig -> {
						receiverEmail.set( receiverEmail.get().concat( otoConfig.getConfigReceiverEmail() + "," ) );
						
						UserInfo receiveUser = SystemFactory.getUserInfo();
						
						// OneToOneInfo 구조가 변경되면서 setEmail 인자값 변경함
						// ( 수신자 정보가 masterInfo 에서 detailInfo 로 옮겨감 - 남윤재 5/23)
						// receiveUser.setEmail( info.getReceiverEmail() );
						receiveUser.setEmail( otoConfig.getConfigReceiverEmail() );
						MessageInfo message = setMessage( info );
						messageSendBLO.send( message, receiveUser, null );
				} );
				
				String result = receiverEmail.get().substring( 0, receiverEmail.get().length() - 1 );
				info.setReceiverEmail( result );
				
		}
		
		/**
		 * 1:1 문의자에게 이메일을 발송합니다
		 *
		 * @param info
		 */
		public void sendEmailToCustomer( OneToOneInfo info ){
		
				if( StringUtils.isEmpty( info.getReceiverEmail() ) ){
						return;
				}
				
				AtomicReference<String> receiverEmail = new AtomicReference<>( "" );
				
				UserInfo receiveUser = SystemFactory.getUserInfo();
				
				receiveUser.setEmail( info.getReceiverEmail() );
				MessageInfo message = setMessageForCustomer( info );
				
				messageSendBLO.send( message, receiveUser, null );
				
		}
		
		
		public void sendEmailTemplate( OneToOneInfo info){
		
		}
		
		/**
		 * 메세지 정보를 설정합니다.
		 *
		 * @param info
		 *
		 * @return MessageInfo
		 * @author 최원준
		 */
		private MessageInfo setMessage( OneToOneInfo info ) {
				
				String contents = makeEmailTemplate( info );
				
				MessageInfo message = SystemFactory.getMessageInfo();
				
				message.setMsgTypeMode( MessageInfo.MESSAGE_TYPE_MODE_EMAIL )
							.setName( info.getTitle() )
							.setContents( contents )
							.setSendTypeFlag( MessageInfo.MESSAGE_SEND_TYPE_FLAG_IMMEDIATE )
							.setInputUser( info.getInputUser() )
							.setInputDate( info.getInputDate() );
				
				return message;
		}
		
		private MessageInfo setMessageForCustomer( OneToOneInfo info ) {
				
				String contents = makeEmailTemplateForCustomer( info );
				
				MessageInfo message = SystemFactory.getMessageInfo();
				
				message.setMsgTypeMode( MessageInfo.MESSAGE_TYPE_MODE_EMAIL )
							.setName( info.getTitle() )
							.setContents( contents )
							.setSendTypeFlag( MessageInfo.MESSAGE_SEND_TYPE_FLAG_IMMEDIATE )
							.setInputUser( info.getInputUser() )
							.setInputDate( info.getInputDate() );
				
				return message;
		}
		
		/**
		 * 이메일 템플릿에 내용을 적용합니다.
		 *
		 * @param info
		 * @return
		 */
		private String makeEmailTemplate( OneToOneInfo info ) {
				
				StringWriter writer = new StringWriter();
				
				try {
						VelocityEngine ve = new VelocityEngine();
						ve.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
						ve.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
						ve.init();
						
						String templateName = MessageFormat.format( "/templates/oneToOneTemplate.html", Locale.getDefault() );
						VelocityContext context = new VelocityContext();
						
						setOtoQuestion( info, context );
						UserInfo user = info.getInputUserInfo();
						
						context.put( "title", "KCC Advanced Materials 1:1 문의" );
						context.put( "email", StringUtils.hasText( user.getEmail() ) ? user.getEmail() : AmConstants.HYPHEN );
						context.put( "company", StringUtils.hasText( user.getOrganizationName() ) ? user.getOrganizationName() : AmConstants.HYPHEN );
						context.put( "phone", StringUtils.hasText( user.getPhone() ) ? user.getPhone() : AmConstants.HYPHEN );
						context.put( "name", StringUtils.hasText( user.getName() ) ? user.getName() : AmConstants.HYPHEN );
						
						context.put( "part", StringUtils.hasText( user.getCustomField1() ) ? user.getCustomField1() : AmConstants.HYPHEN );
						context.put( "tel", StringUtils.hasText( user.getTel() ) ? user.getTel() : AmConstants.HYPHEN );
						context.put( "nation", StringUtils.hasText( user.getCustomField2() ) ? user.getCustomField2() : AmConstants.HYPHEN );
						context.put( "city", StringUtils.hasText( user.getCustomField3() ) ? user.getCustomField3() : AmConstants.HYPHEN );
						context.put( "otoAddQuestion", info.getTitle() );
						
						context.put( "otoAddContents", info.getDescr() );
						
						Template template = ve.getTemplate( templateName, "UTF-8" );
						template.merge( context, writer );
				}
				catch ( KccMailException e ) {
						e.printStackTrace();
				}
				
				return writer.toString();
		}
		
		private String makeEmailTemplateForCustomer( OneToOneInfo info ) {
				
				StringWriter writer = new StringWriter();
				
				try {
						VelocityEngine ve = new VelocityEngine();
						ve.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
						ve.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
						ve.init();
						
						String templateName = MessageFormat.format( "/templates/oneToOneCustomerTemplate.html", Locale.getDefault() );
						VelocityContext context = new VelocityContext();
						
						setOtoQuestion( info, context );
						UserInfo user = info.getInputUserInfo();
						
						context.put( "title", "KCC Advanced Materials 1:1 문의" );
						
						context.put( "otoAddQuestion", info.getTitle() );
						context.put( "otoAddContents", info.getDescr() );
						context.put( "otoAddContentsAnswer", info.getAnswer() );
						
						Template template = ve.getTemplate( templateName, "UTF-8" );
						template.merge( context, writer );
				}
				catch ( KccMailException e ) {
						e.printStackTrace();
				}
				
				return writer.toString();
		}
		
		/**
		 * 질문자에 따라 변경되는 문의 내용을 만들어줍니다.
		 *
		 * @param info
		 * @param context
		 */
		private void setOtoQuestion( OneToOneInfo info, VelocityContext context ) {
				
				if ( CollectionUtils.isEmpty( info.getOneToOneDetailList() ) ) {
						return;
				}
				
				info.getOneToOneDetailList().forEach( otoDetail -> {
						String question = "";
						String answer = "";
						String html = "<div>"
												  + "<div style='display: flex; align-items: flex-start; margin: 10px;'>"
												  + "<div style='display: inline-block; min-width: 74px; padding: 3px 18px;"
												  + "background: #ebebeb; border-radius: 30px; color: #161616;"
												  + "font-size: 13px; font-weight: 700;	white-space: nowrap;'>";
						
						if ( OneToOneConfigInfo.CONFIG_TYPE_QUESTION.equals( otoDetail.getDetailType() ) ) {
								question = "문의종류";
								answer = otoDetail.getDetailQuestion();
								html += question
													+ "</div>"
													+ "<div style='margin: 3px 0 3px 7px; font-size: 14px; font-weight: 700; text-align: left;'>"
													+ "<div>" + answer + "</div>"
													+ "</div>"
													+ "</div>";
								context.put( "otoQuestion", html );
						}
						else {
								question = otoDetail.getDetailQuestion();
								answer = otoDetail.getDetailAnswer();
								html += question
													+ "</div>"
													+ "<div style='margin: 3px 0 3px 7px; font-size: 14px; font-weight: 700; text-align: left;'>"
													+ "<div>" + answer + "</div>"
													+ "</div>"
													+ "</div>";
								context.put( "otoDetailQuestion", html );
						}
						
				} );
				
		}
		
}
