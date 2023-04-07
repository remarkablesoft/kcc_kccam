package com.remarkablesoft.framework.web.controller;

import com.remarkablesoft.framework.module.oid.OIDGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( value = "/common/" )
public class CommonController extends BaseController {

		
		/**
		 * 클라이언트에서 새로 oid 를 만들어야 할 때 사용한다.
		 * 
		 * @author max
		 * @return OIDGenerator.generateOID()
		 */
		@GetMapping( value = "common_generateOid" )
		public @ResponseBody String generateOid() {

				return OIDGenerator.generateOID();
		}

}
