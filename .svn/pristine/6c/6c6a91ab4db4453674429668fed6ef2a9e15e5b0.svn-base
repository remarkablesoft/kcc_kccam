package com.remarkablesoft.framework.web.util.jwt;

import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.web.util.CookieUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.function.Function;

/**
 * 
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module - jwt
 * 		@프로그램 ID		:	JwtRefreshTokenUtils
 * 		@프로그램 개요 		:	refreshToken의 유틸클래스
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 9. 21	:	james	-	신규생성
 * 		============================================================================
 */
public class JwtRefreshTokenUtils {


		public static String getSubejctFromRefreshToken( String token ) {
				return getClaimFromRefreshToken( token, Claims::getSubject );
		}


		public static Date getIssuedAtDateFromRefreshToken( String token ) {
				return getClaimFromRefreshToken( token, Claims::getIssuedAt );
		}


		public static Date getExpirationDateFromRefreshToken( String token ) {
				return getClaimFromRefreshToken( token, Claims::getExpiration );
		}


		public static <T> T getClaimFromRefreshToken( String token, Function<Claims, T> claimsResolver ) {
				final Claims claims = getAllClaimsFromRefreshToken( token );
				return claimsResolver.apply( claims );
		}


		public static Claims getAllClaimsFromRefreshToken( String token ) {
				return Jwts.parser().setSigningKey( JwtConstants.REFRESH_TOKEN_SECRET_KEY ).parseClaimsJws( token ).getBody();
		}
		
		/**
		 * 해당토큰에서 키로 값을 확인.
		 * 
		 * @author james
		 * @param token
		 * @param key
		 * @return
		 */
		public static String getClaimsFromRefreshToken( String token, String key ) {
				
				Claims claims = getAllClaimsFromRefreshToken( token);
				
				if ( claims == null ) {
					return "";
				}
				
				return (String) claims.get( key );
		}


		public static String generateRefreshToken( String userOid ) {

				return generateRefreshToken( userOid, SystemConstants.FLAG_NO );
		}

		public static String generateRefreshToken( String userOid, String loginKeep ) {

				return Jwts.builder()
						   .claim( JwtConstants.TOKEN_TYPE, JwtConstants.REFRESH_TOKEN )
						   .claim( JwtConstants.KEEP_ME_LOGGED_IN, loginKeep )
						   .setSubject( userOid )
						   .setIssuedAt( new Date( System.currentTimeMillis() ) )
						   .setExpiration( new Date( System.currentTimeMillis() + JwtConstants.REFRESH_TOKEN_VALIDITY ) )
						   .signWith( SignatureAlgorithm.HS512, JwtConstants.REFRESH_TOKEN_SECRET_KEY )
						   .compact();
		}


		public static Boolean isValidateRefreshToken( String token, String id ) {
				final String subject = getSubejctFromRefreshToken( token );
				return (subject.equals( id ) && !isRefreshTokenExpired( token ));
		}


		public static Boolean isRefreshTokenExpired( String token ) {
				final Date expiration = getExpirationDateFromRefreshToken( token );
				return expiration.before( new Date() );
		}
		
		/**
		 * token을 refreshToken에 저장.
		 * 
		 * @author james
		 * @param refreshToken
		 * @param maxAge
		 * @param response
		 */
		public static void generateRefreshTokenCookie( String refreshToken, int maxAge, HttpServletResponse response ) {

				CookieUtils.addCookieEnc( response, maxAge, JwtConstants.REFRESH_TOKEN, refreshToken );
		}
}