package com.remarkablesoft.framework.web.module.notification.email.impl.kcc;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

import com.remarkablesoft.framework.util.ApplicationPropertiesUtils;
import com.remarkablesoft.framework.util.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.context.annotation.Primary;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.log.CommonLoggerFactory;
import com.remarkablesoft.framework.service.notification.message.message.vo.MessageInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.web.module.notification.MessageSendProvider;
import com.remarkablesoft.framework.web.module.notification.email.impl.kcc.exception.KccMailException;

/**
 * 		@주시스템		:	framework-web
 * 		@서브 시스템		:	module - notification - email
 * 		@프로그램 ID		:	KccEmailSendProvider
 * 		@프로그램 개요 	:	KCC 이메일 발송 구현체
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 05. 07.	:	choi	-	생성
 * 		============================================================================
 */

@BLO
@Primary
public class KccEmailSendProvider implements MessageSendProvider  {

	protected static final Logger logger = CommonLoggerFactory.getLogger( "EmailSend" );

	private final static String host = ApplicationPropertiesUtils.getValue("mail.hostName");
	private final static String port = ApplicationPropertiesUtils.getValue("mail.port");
	private final static String userName = ApplicationPropertiesUtils.getValue("mail.auth.userName");
	private final static String password = ApplicationPropertiesUtils.getValue("mail.auth.password");
	private final static String EMAIL_FROM = ApplicationPropertiesUtils.getValue("mail.displaySendMail");

	private final static String EMAIL_NAME = ApplicationPropertiesUtils.getValue("mail.displaySendName");

	@Override
	public void send( MessageInfo message, UserInfo user ) {

		sendMail( EMAIL_NAME, EMAIL_FROM, user.getEmail(), "", message.getName(), message.getContents(), "", new String[]{} );

	}

	
	public int sendMail( HashMap< String, Object > paramMap ) throws KccMailException {
		
		String name = paramMap.get( "name" ).toString();
		String from = paramMap.get( "from" ).toString();
		String to = paramMap.get( "to" ).toString();
		String cc = paramMap.get( "cc" ).toString();
		String title = paramMap.get( "title" ).toString();
		String content = paramMap.get( "content" ).toString();
		
		String tar = paramMap.get( "tar" ).toString();
		List<String> fileList = new ArrayList<>();

		if ( paramMap.containsKey( "fileList" ) ) {
			fileList = (List<String>)paramMap.get( "fileList" );
		}

		String[] filenames = fileList.toArray( new String[fileList.size()]);
		
		return sendMail(name, from, to, cc, title, content, tar, filenames);
	}
	
	/**
	 * KCC 이메일을 발송합니다. 구현 소스는 KCC에서 전달해준 소스입니다.
	 * 
	 * @param name		발신자명
	 * @param from		발신자 이메일
	 * @param to		수신자 이메일
	 * @param cc		참조 이메일
	 * @param title		메일 제목
	 * @param content	메일 내용
	 * @param tar
	 * @param filenames 
	 */
	public int sendMail( String name, String from, String to, String cc,
						 String title, String content, String tar, String[] filenames) throws KccMailException {
		Transport transport  = null;
		int resultCount = 0;
		try {
			logger.debug("start mail function");
			Properties prop = System.getProperties();
			prop.setProperty( "mail.smtp.host", host );
			prop.setProperty( "mail.smtp.port", port );
			prop.setProperty( "mail.smtp.auth", "false" );
//			prop.setProperty( "mail.smtp.ehlo", "false");
			prop.setProperty( "mail.debug", "true" );

			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication( userName, password );
				}
			};
			
			Session session = Session.getDefaultInstance( prop, auth );
			
			MimeMessage msg = new MimeMessage( session );
			String temp = MimeUtility.encodeText(name + "<" + from + ">", "UTF-8", "B");
			
			InternetAddress[] fromAddress = InternetAddress.parse( from );
			fromAddress[0].setPersonal( name, "UTF-8" );
			msg.setFrom(fromAddress[0] );
			
			InternetAddress[] toAddress = InternetAddress.parse( to );
			msg.setRecipients( Message.RecipientType.TO, toAddress );
			
			InternetAddress[] ccAddress = InternetAddress.parse( cc );
			msg.setRecipients( Message.RecipientType.CC, ccAddress );
			
			msg.setSubject( title, "UTF-8");
			MimeBodyPart mbp1 = new MimeBodyPart();
			
			if ( tar.equals( "html" ) ) {
				mbp1.setContent( content, "text/html" );
			}
			else {
				// mbp1.setText(content.replaceAll(" ","&nbsp;"),"UTF-8");
					mbp1.setText( content, "UTF-8" );
				
			}
			
			//String 은 되는데 html template이 text로 가는 문제가 있어서.
			//byte로 변경해서 발송
			byte[] bytes = content.getBytes(); 
			DataSource dataSourceHtml= new ByteArrayDataSource(bytes, "text/html");
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setDataHandler(new DataHandler(dataSourceHtml));

			//원래 소스 
		//	MimeMultipart mimeMultipart = new MimeMultipart();
		//	mimeMultipart.addBodyPart(bodyPart);
			
			// Multipart - 몸의 각부분(= MimeBodyPart)를 하나로 합친다. part부분의 대소문자 주의!!..
			// MimeMultipart(part - 소문자),MimeBodyPart(Part - 대문자)
			Multipart mp = new MimeMultipart();
			mp.addBodyPart( bodyPart );
			
			logger.debug("file size : "+filenames.length);
			for ( String filename : filenames ) {
				logger.debug( "mail file name : " + filename );
				filename = fileSize( filename ); // 파일 사이즈를 구한다.
				MimeBodyPart mbp2 = new MimeBodyPart();
				FileDataSource fds = new FileDataSource( filename );
				
				mbp2.setDataHandler( new DataHandler( fds ) );
				mbp2.setFileName( MimeUtility.encodeText( fds.getName(), "UTF-8", "B" ) );
				if ( !filename.equals( "" ) ) {
					mp.addBodyPart( mbp2 );
				}
			}
			
			MailcapCommandMap mc = ( MailcapCommandMap ) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			CommandMap.setDefaultCommandMap(mc);
			msg.setContent(mp);
			
			transport = session.getTransport("smtp");
			transport.connect( host, userName, password );
			
			transport.sendMessage( msg, msg.getAllRecipients() );
		} 
		catch ( UnsupportedEncodingException | MessagingException e ) {
			logger.debug( e.getMessage().toString() );
			throw new KccMailException( e.getMessage() );
		} 
		finally {
			try {
				if ( transport != null ) {
					transport.close();
					resultCount += 1;
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return resultCount;
	}
	
	private static String fileSize(String filename1) {
		if (filename1.length() > (1024 * 1024 * 2.5)) {
			filename1 = "";
		}
		
		return filename1;
	}
	
}
