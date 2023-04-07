package com.remarkablesoft.config;

import java.time.LocalDateTime;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.web.module.authentication.exception.AuthenticationException;

/**
 *
 *  SPA 형태에서는 ModelAndView 가 아닌 ResponseEntity 형태로 데이터를 반환하도록 처리.
 *
 * @author james
 *
 */
@ControllerAdvice
public class ErrorController  {


		/**
		 * BLO 오류
		 *
		 * @param request
		 * @param response
		 * @param handler
		 * @param ex
		 * @return
		 */
		@ExceptionHandler( BLORuntimeException.class)
		public ResponseEntity<HashMap<String, Object>>  bLORuntimeException( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) {

				HashMap<String, Object> view = new HashMap<String, Object>();
				view.put( "error", HttpStatus.INTERNAL_SERVER_ERROR );
				view.put( "message", ex.getMessage() );
				view.put( "path", request.getRequestURI() );
				view.put( "status", HttpStatus.INTERNAL_SERVER_ERROR.value()  );
				view.put( "timestamp", LocalDateTime.now());
				view.put( "trace", ExceptionUtils.getFullStackTrace( ex ) );

				return new ResponseEntity<>( view, HttpStatus.INTERNAL_SERVER_ERROR );
		}


		/**
		 * 로그인관련 오류
		 *
		 * @param request
		 * @param response
		 * @param handler
		 * @param ex
		 * @return
		 */
		@ExceptionHandler( AuthenticationException.class)
		public ResponseEntity<HashMap<String, Object>>  athenticationException( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) {

				HashMap<String, Object> view = new HashMap<String, Object>();
				view.put( "error", HttpStatus.UNAUTHORIZED );
				view.put( "message", ex.getMessage() );
				view.put( "path", request.getRequestURI() );
				view.put( "status", HttpStatus.UNAUTHORIZED.value()  );
				view.put( "timestamp", LocalDateTime.now());
				view.put( "trace", ExceptionUtils.getFullStackTrace( ex ) );

				return new ResponseEntity<>( view, HttpStatus.UNAUTHORIZED );
		}




}
