package com.remarkablesoft.framework.web.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.function.Function;

/**
 * 
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	module - jwt
 * 		@프로그램 ID		:	JwtAccessTokenUtils
 * 		@프로그램 개요 		:	accessToken의 유틸클래스
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2020. 9. 21	:	james	-	신규생성
 * 		============================================================================
 */
public class JwtAccessTokenUtils {

		public static String getSubejctFromAccessToken( String token ) {
				return getClaimFromAccessToken( token, Claims::getSubject );
		}


		public static Date getIssuedAtDateFromAccessToken( String token ) {
				return getClaimFromAccessToken( token, Claims::getIssuedAt );
		}


		public static Date getExpirationDateFromAccessToken( String token ) {
				return getClaimFromAccessToken( token, Claims::getExpiration );
		}


		public static <T> T getClaimFromAccessToken( String token, Function<Claims, T> claimsResolver ) {
				final Claims claims = getAllClaimsFromAccessToken( token );
				return claimsResolver.apply( claims );
		}


		public static Claims getAllClaimsFromAccessToken( String token ) {
				return Jwts.parser().setSigningKey( JwtConstants.ACCESS_TOKEN_SECRET_KEY ).parseClaimsJws( token ).getBody();
		}

		
		/**
		 * 해당토큰에서 키로 값을 확인.
		 * 
		 * @author james
		 * @param token
		 * @param key
		 * @return
		 */
		public static String getClaimsFromAccessToken( String token, String key ) {
				
				Claims claims = getAllClaimsFromAccessToken( token);
				
				if ( claims == null ) {
					return "";
				}
				
				return (String) claims.get( key );
		}

		public static String generateAccessToken( String userOid ) {

				return Jwts.builder()
						   .claim( JwtConstants.TOKEN_TYPE, JwtConstants.ACCESS_TOKEN )
						   .setSubject( userOid )
						   .setIssuedAt( new Date( System.currentTimeMillis() ) )
						   .setExpiration( new Date( System.currentTimeMillis() + JwtConstants.ACCESS_TOKEN_VALIDITY ) )
						   .signWith( SignatureAlgorithm.HS512, JwtConstants.ACCESS_TOKEN_SECRET_KEY )
						   .compact();
		}


		public static Boolean isValidateAccessToken( String token, String id ) {
				final String subject = getSubejctFromAccessToken( token );
				return (subject.equals( id ) && !isAccessTokenExpired( token ));
		}


		public static Boolean isAccessTokenExpired( String token ) {
				final Date expiration = getExpirationDateFromAccessToken( token );
				return expiration.before( new Date() );
		}

}