package com.remarkablesoft.framework.web.info;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.web.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	web
 * 		@프로그램 ID		:	ServerInfoController
 * 		@프로그램 개요 	:	서버의 정보를 반환
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 01. 14.	:	james	-	접속한 서버 아이피 반환
 * 		============================================================================
 */
@WEBLog
@RestController
@RequestMapping( "/server" )
public class ServerInfoController extends BaseController {

		@RequestMapping( value = "/getServerInfo", method = RequestMethod.GET )
		public ResponseEntity<?> getServerInfo() {


				InetAddress local;
				String ip = "";
				try {
						local = InetAddress.getLocalHost();
						ip = local.getHostAddress();
						ip = "Connected Server Ip : " + ip;
				}
				catch ( UnknownHostException e1 ) {
						e1.printStackTrace();
				}


				return new ResponseEntity<>( ip, HttpStatus.OK ) ;

		}

}
