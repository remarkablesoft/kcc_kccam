package com.remarkablesoft.framework.web.module.notification.push.ios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module-notification-push
 * 		@프로그램 ID		:	CertUtil
 * 		@프로그램 개요 	:	인증서 관리 유틸
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 10. 7.	:	choi	-	주석 추가
 * 		============================================================================
 */
public class CertUtil {

		/***
		 * 푸시 알림 인증서와 인증서 비밀번호 체크 기능 (파일 경로 방식)
		 * @param filePath
		 * @param password
		 * @return 체크 결과 (boolean)
		 */
		public static boolean checkCertPasswordFromFilePath( String filePath, String password ) {
				KeyStore p12;
				try {
						p12 = KeyStore.getInstance( "pkcs12" );
						p12.load( new FileInputStream( filePath ), password.toCharArray() );
						Enumeration<String> e = p12.aliases();
						while (e.hasMoreElements()) {
								String alias = e.nextElement();
								X509Certificate c = (X509Certificate) p12.getCertificate( alias );
								Principal subject = c.getSubjectDN();
								if ( subject == null || subject.toString().length() == 0 ) {
										return false;
								}
						}
				}
				catch ( KeyStoreException e1 ) {
						e1.printStackTrace();
						return false;
				}
				catch ( NoSuchAlgorithmException e1 ) {
						e1.printStackTrace();
						return false;
				}
				catch ( CertificateException e1 ) {
						e1.printStackTrace();
						return false;
				}
				catch ( FileNotFoundException e1 ) {
						e1.printStackTrace();
						return false;
				}
				catch ( Exception e1 ) {
						e1.printStackTrace();
						return false;
				}

				return true;
		}

		/***
		 * 푸시 알림 인증서와 인증서 비밀번호 체크 기능 (Resource 경로 방식)
		 * @param filePath
		 * @param password
		 * @return 체크 결과 (boolean)
		 */
		public static boolean checkCertPasswordFromResourcePath( String resourcePath, String password ) {
				KeyStore p12;
				try {
						p12 = KeyStore.getInstance( "pkcs12" );
						p12.load( CertUtil.class.getResourceAsStream( resourcePath ), password.toCharArray() );
						Enumeration<String> e = p12.aliases();
						while (e.hasMoreElements()) {
								String alias = e.nextElement();
								X509Certificate c = (X509Certificate) p12.getCertificate( alias );
								Principal subject = c.getSubjectDN();
								if ( subject == null || subject.toString().length() == 0 ) {
										return false;
								}
						}
				}
				catch ( KeyStoreException e1 ) {
						e1.printStackTrace();
						return false;
				}
				catch ( NoSuchAlgorithmException e1 ) {
						e1.printStackTrace();
						return false;
				}
				catch ( CertificateException e1 ) {
						e1.printStackTrace();
						return false;
				}
				catch ( FileNotFoundException e1 ) {
						e1.printStackTrace();
						return false;
				}
				catch ( Exception e1 ) {
						e1.printStackTrace();
						return false;
				}

				return true;
		}

		/***
		 * 푸시 알림 인증서에 등록한 APSP UserId 값 비교 기능 (파일 경로 방식)
		 * @param filePath
		 * @param password
		 * @return 체크 결과 (boolean)
		 */
		public static boolean isAPSPUserIdMatchFromFilePath( String filePath, String password, String apspUserId ) {

				if ( apspUserId == null ) {
						return false;
				}

				String certApspUserId = getAPSPUUserIdFromFilePath( filePath, password );

				if ( certApspUserId == null ) {
						return false;
				}

				return certApspUserId.equalsIgnoreCase( apspUserId );
		}

		/***
		 * 푸시 알림 인증서에 등록한 APSP UserId 값 비교 기능 (Resource 경로 방식)
		 * @param filePath
		 * @param password
		 * @return 체크 결과 (boolean)
		 */
		public static boolean isAPSPUserIdMatchFromResourcePath( String resourcePath, String password, String apspUserId ) {

				if ( apspUserId == null ) {
						return false;
				}

				String certApspUserId = getAPSPUUserIdFromResource( resourcePath, password );

				if ( certApspUserId == null ) {
						return false;
				}

				return certApspUserId.equalsIgnoreCase( apspUserId );
		}

		/***
		 * 푸시 알림 인증서에 등록한 APSP UserId 불러오기 기능 (파일 경로 방식)
		 * @param filePath
		 * @param password
		 * @return APSP UserId 값 (String)
		 */
		public static String getAPSPUUserIdFromFilePath( String filePath, String password ) {
				KeyStore p12;
				try {
						p12 = KeyStore.getInstance( "pkcs12" );

						p12.load( new FileInputStream( filePath ), password.toCharArray() );
						Enumeration e = p12.aliases();
						while (e.hasMoreElements()) {
								String alias = (String) e.nextElement();
								X509Certificate c = (X509Certificate) p12.getCertificate( alias );
								Principal subject = c.getSubjectDN();
								String subjectArray[] = subject.toString().split( "," );
								String[] str;
								String key;
								for ( String s : subjectArray ) {
										str = s.trim().split( "=" );
										if ( str.length >= 2 ) {
												key = str[0];
												if ( key.equalsIgnoreCase( "UID" ) ) {
														return str[1];
												}
										}
								}
						}
				}
				catch ( KeyStoreException e1 ) {
						e1.printStackTrace();
				}
				catch ( NoSuchAlgorithmException e1 ) {
						e1.printStackTrace();
				}
				catch ( CertificateException e1 ) {
						e1.printStackTrace();
				}
				catch ( FileNotFoundException e1 ) {
						e1.printStackTrace();
				}
				catch ( Exception e1 ) {
						e1.printStackTrace();
				}

				return null;
		}

		/***
		 * 푸시 알림 인증서에 등록한 APSP UserId 불러오기 기능 (Resource 경로 방식)
		 * @param filePath
		 * @param password
		 * @return APSP UserId 값 (String)
		 */
		public static String getAPSPUUserIdFromResource( String resourcePath, String password ) {
				KeyStore p12;
				try {
						p12 = KeyStore.getInstance( "pkcs12" );
						p12.load( CertUtil.class.getResourceAsStream( resourcePath ), password.toCharArray() );
						Enumeration<String> e = p12.aliases();
						while (e.hasMoreElements()) {
								String alias = e.nextElement();
								X509Certificate c = (X509Certificate) p12.getCertificate( alias );
								Principal subject = c.getSubjectDN();
								String subjectArray[] = subject.toString().split( "," );
								String[] str;
								String key;
								for ( String s : subjectArray ) {
										str = s.trim().split( "=" );
										if ( str.length >= 2 ) {
												key = str[0];
												if ( key.equalsIgnoreCase( "UID" ) ) {
														return str[1];
												}
										}
								}
						}
				}
				catch ( KeyStoreException e1 ) {
						e1.printStackTrace();
				}
				catch ( NoSuchAlgorithmException e1 ) {
						e1.printStackTrace();
				}
				catch ( CertificateException e1 ) {
						e1.printStackTrace();
				}
				catch ( FileNotFoundException e1 ) {
						e1.printStackTrace();
				}
				catch ( Exception e1 ) {
						e1.printStackTrace();
				}

				return null;
		}

}
