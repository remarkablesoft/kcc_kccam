package com.remarkablesoft.framework.web.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.util.gson.GsonLocalDateTimeAdapter;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DevicePlatform;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 설명 : 웹관련 Utils
 * </pre>
 * @author james
 * @since 2015. 5. 9.
 *
 */
public abstract class WebUtils {

		public static final String ENCODING = "UTF-8";

		// public static final Gson GSON = new GsonBuilder().setDateFormat( "yyyy-MM-dd HH:mm:ss" ).create();

		// Gson은 날짜타입을 java8에 있는 LocalTime, LocalDate, LocalDateTime을 지원하지 않기 때문에
		// 지원할수 있도록 처리
		public static final Gson GSON = new GsonBuilder()
								//														 .registerTypeAdapter( LocalDateTime.class, (JsonDeserializer<LocalDateTime>) ( json, type, jsonDeserializationContext ) -> LocalDateTime.parse( json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME ) )
														 .registerTypeAdapter( LocalDateTime.class, new GsonLocalDateTimeAdapter() )
														 .registerTypeAdapter( LocalDate.class, (JsonDeserializer<LocalDate>) ( json, type, jsonDeserializationContext ) -> LocalDate.parse( json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE ) )
														 .registerTypeAdapter( LocalTime.class, (JsonDeserializer<LocalTime>) ( json, type, jsonDeserializationContext ) -> LocalTime.parse( json.getAsString(), DateTimeFormatter.ISO_LOCAL_TIME ) )
														 .create();

		public static List<String> appAgent = new ArrayList<String>();

		static {

				appAgent.add( "galaxyTab" );
				appAgent.add( "ipod" );
				appAgent.add( "iphone" );
				appAgent.add( "ipad" );
				appAgent.add( "android" );
		}

		private WebUtils() {

		}

		/**
		 * URL ENCODE
		 * @param param
		 * @return
		 */
		public static String URLEncode( String param ) {
				String result = "";

				try {
						result = URLEncoder.encode( param, ENCODING );
				}
				catch ( Exception e ) {

				}

				return result;
		}

		/**
		 * URL DECODE
		 * @param param
		 * @return
		 */
		public static String URLDecode( String param ) {
				String result = "";

				try {
						result = URLDecoder.decode( param, ENCODING );
				}
				catch ( Exception e ) {

				}

				return result;
		}

		/**
		 * request 없이 접속자 IP를 체크하기 위해
		 * 
		 *
		 * @param request
		 * @return
		 */
		public static String getClientIp() {

				HttpServletRequest request = null;
				try {
						request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
				}
				catch ( NullPointerException e ) {
						return "127.0.0.1";
				}

				return getClientIp( request );
		}

		/**
		 * request.getRemoteAddr() 으로 정확하지 않은 IP가 나올 수 있어서
		 * 좀 더 정확한 IP를 체크하기 위해.
		 *
		 * @param request
		 * @return
		 */
		public static String getClientIp( HttpServletRequest request ) {

				//				String clientIP = request.getHeader( "Proxy-Client-IP" );
				//				if ( clientIP == null ) {
				//						clientIP = request.getHeader( "WL-Proxy-Client-IP" );
				//						if ( clientIP == null ) {
				//								clientIP = request.getHeader( "X-Forwared-For" );
				//								if ( clientIP == null ) {
				//										clientIP = request.getRemoteAddr();
				//								}
				//						}
				//				}

				String ip = request.getHeader( "X-Forwarded-For" );

				if ( StringUtils.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip ) ) {
						ip = request.getHeader( "X-FORWARDED-FOR" );
				}

				if ( StringUtils.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip ) ) {
						ip = request.getHeader( "HTTP_X_FORWARDED_FOR" );
				}

				if ( StringUtils.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip ) ) {
						ip = request.getHeader( "Proxy-Client-IP" );
				}

				if ( StringUtils.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip ) ) {
						ip = request.getHeader( "WL-Proxy-Client-IP" );
				}

				if ( StringUtils.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip ) ) {
						ip = request.getHeader( "HTTP_CLIENT_IP" );
				}

				if ( StringUtils.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip ) ) {
						ip = request.getHeader( "X-Real-IP" );
				}

				if ( StringUtils.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip ) ) {
						ip = request.getHeader( "X-RealIP" );
				}

				if ( StringUtils.isEmpty( ip ) || "unknown".equalsIgnoreCase( ip ) ) {
						ip = request.getRemoteAddr();
				}

				if ( "0:0:0:0:0:0:0:1".equals( ip ) ) {
						ip = "127.0.0.1";
				}

				return ip;
		}

		
		
		/**
		 * 사용자의 브라우져명을 반환
		 * 
		 * @author james
		 * @param request
		 * @return
		 */
		public static String getUserBrowser( HttpServletRequest request ) {

				String userAgent = request.getHeader( "user-agent" );
				String browser = "";

				if ( userAgent.indexOf( "Trident" ) > -1 || userAgent.indexOf( "MSIE" ) > -1 ) { //IE

						if ( userAgent.indexOf( "Trident/7" ) > -1 ) {
								browser = "IE 11";
						}
						else if ( userAgent.indexOf( "Trident/6" ) > -1 ) {
								browser = "IE 10";
						}
						else if ( userAgent.indexOf( "Trident/5" ) > -1 ) {
								browser = "IE 9";
						}
						else if ( userAgent.indexOf( "Trident/4" ) > -1 ) {
								browser = "IE 8";
						}
						else if ( userAgent.indexOf( "edge" ) > -1 ) {
								browser = "IE edge";
						}

				}
				else if ( userAgent.indexOf( "Whale" ) > -1 ) { //네이버 WHALE
						browser = "WHALE " + userAgent.split( "Whale/" )[1].toString().split( " " )[0].toString();
				}
				else if ( userAgent.indexOf( "Opera" ) > -1 || userAgent.indexOf( "OPR" ) > -1 ) { //오페라
						if ( userAgent.indexOf( "Opera" ) > -1 ) {
								browser = "OPERA " + userAgent.split( "Opera/" )[1].toString().split( " " )[0].toString();
						}
						else if ( userAgent.indexOf( "OPR" ) > -1 ) {
								browser = "OPERA " + userAgent.split( "OPR/" )[1].toString().split( " " )[0].toString();
						}
				}
				else if ( userAgent.indexOf( "Firefox" ) > -1 ) { //파이어폭스
						browser = "FIREFOX " + userAgent.split( "Firefox/" )[1].toString().split( " " )[0].toString();
				}
				else if ( userAgent.indexOf( "Safari" ) > -1 && userAgent.indexOf( "Chrome" ) == -1 ) { //사파리
						browser = "SAFARI " + userAgent.split( "Safari/" )[1].toString().split( " " )[0].toString();
				}
				else if ( userAgent.indexOf( "Chrome" ) > -1 ) { //크롬
						browser = "CHROME " + userAgent.split( "Chrome/" )[1].toString().split( " " )[0].toString();
				}

				return browser;
		}
		
		/**
		 * 모바일이면 M(Mobile), 테블릿이면 T(Tablet),
		 * 일반적인 웹이면 N(Normal)
		 * 
		 * 
		 * @author james
		 * @param request
		 * @return
		 */
		public static String getUserDeviceType( HttpServletRequest request ) {

				Device device = DeviceUtils.getCurrentDevice( request );
				
				if ( device.isMobile()) {
						
					return SystemConstants.DEVICE_TYPE_MOBILE.getKey();
				}
				else if ( device.isTablet()) {
						
					return SystemConstants.DEVICE_TYPE_TABLET.getKey();
				}

				return SystemConstants.DEVICE_TYPE_NORMAL.getKey();
		}
		

		/**
		 * IOS이면 I, 안드로이드면 A,
		 * 그외는 전부 U(Unkown)
		 * 
		 * 
		 * @author james
		 * @param request
		 * @return
		 */
		public static String getUserDeviceOS( HttpServletRequest request ) {

				Device device = DeviceUtils.getCurrentDevice( request );
				
				if ( DevicePlatform.IOS.equals( device.getDevicePlatform() )) {
						
					return SystemConstants.OS_IOS.getKey();
				}
				else if ( DevicePlatform.ANDROID.equals( device.getDevicePlatform() )) {
						
						return SystemConstants.OS_ANDROID.getKey();
				}

				return SystemConstants.OS_UNKNOWN.getKey();
		}
		

		public static String getUserAgent( HttpServletRequest request ) {

				String userAgent = request.getHeader( "User-Agent" );

				if ( userAgent == null ) {
						return "etc";
				}

				userAgent = userAgent.toLowerCase();

				if ( userAgent.indexOf( "shw" ) >= 0 ) {
						return "galaxyTab";
				}
				else if ( userAgent.indexOf( "ipod" ) >= 0 ) {
						return "ipod";
				}
				else if ( userAgent.indexOf( "iphone" ) >= 0 ) {
						return "iphone";
				}
				else if ( userAgent.indexOf( "ipad" ) >= 0 ) {
						return "ipad";
				}
				else if ( userAgent.indexOf( "android" ) >= 0 ) {
						return "android";
				}
				else if ( userAgent.indexOf( "msie" ) >= 0 ) {
						return "ie";
				}
				else if ( userAgent.indexOf( "firefox" ) >= 0 ) {
						return "firefox";
				}
				else if ( userAgent.indexOf( "applewebkit" ) >= 0 ) {
						return "chrome";
				}
				else if ( userAgent.indexOf( "opera" ) >= 0 ) {
						return "opera";
				}

				else {
						return "etc";
				}
		}

		public static boolean isAppAgent( HttpServletRequest request ) {

				return appAgent.contains( getUserAgent( request ) );

		}

		/**
		 * 뒤에서 길이만큼 '*'로 마스킹 합니다.
		 * @param str
		 * @param maskingLength
		 * @return
		 */
		public static String maskingString( String str, int maskingLength ) {
				final char maskingChar = '*';

				if ( str.length() < maskingLength ) {
						return str;
				}

				StringBuilder builder = new StringBuilder( str.substring( 0, str.length() - maskingLength ) );
				for ( int i = 0; i < maskingLength; ++i ) {
						builder.append( maskingChar );
				}

				return builder.toString();
		}

		public static String maskingCardNumber( String cardNumber ) {

				if ( StringUtils.isEmpty( cardNumber ) ) {
						return "";
				}

				return cardNumber.substring( 0, 4 ) + "-****-****-****";

		}

		/**
		 * 유저 개인정보를 삭제합니다.
		 * @param userInfo
		 * @param isMaskingId
		 */
		public static void removePersonalData( UserInfo info, boolean isMaskingId ) {

				if ( info == null ) {
						return;
				}

				if ( isMaskingId ) {
						info.setUserId( maskingString( info.getUserId(), 2 ) );
				}

				info.setPwd( null );
				info.setEmail( null );
				info.setTelPart1( null );
				info.setTelPart2( null );
				info.setTelPart3( null );
				info.setPhonePart1( null );
				info.setPhonePart2( null );
				info.setPhonePart3( null );
		}

		/**
		 * 유저의 암호만 삭제합니다.
		 * @param userInfo
		 * @param isMaskingId
		 */
		public static void removeOnlyPwdPersonalData( List<UserInfo> list ) {

				if ( CollectionUtils.isEmpty( list ) ) {
						return;
				}

				for ( UserInfo info : list ) {
						removeOnlyPwdPersonalData( info );
				}

		}

		/**
		 * 유저의 암호만 삭제합니다.
		 * @param userInfo
		 * @param isMaskingId
		 */
		public static void removeOnlyPwdPersonalData( UserInfo info ) {

				if ( info == null ) {
						return;
				}

				info.setPwd( null );

		}

		/**
		 * 유저 개인정보를 삭제합니다.
		 * @param userInfo
		 * @param isMaskingId
		 */
		public static void removePersonalData( List<UserInfo> list, boolean isMaskingId ) {

				if ( CollectionUtils.isEmpty( list ) ) {
						return;
				}

				for ( UserInfo info : list ) {
						removePersonalData( info, isMaskingId );
				}

		}
}
