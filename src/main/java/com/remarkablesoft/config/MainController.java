package com.remarkablesoft.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * SPA일때 스프링에서 해당 URL을 명시하면 에러오류가 나지 않아서
 * 필요한 URL을 선언해서 사용
 *
 * @author james
 *
 */
@Controller
public class MainController implements ErrorController {
		
		@RequestMapping( { "/kccam/**" } )
		public String entry() {
				
				return "forward:/index.html";
		}
		
		@RequestMapping( { "/en/kccam/**" } )
		public String entryEn() {
				
				return "forward:/en/index.html";
		}
		
		@RequestMapping( { "/cn/kccam/**" } )
		public String entryCn() {
				
				return "forward:/cn/index.html";
		}
		
		@RequestMapping( { "/manager" } )
		public String entryManager() {
				
				return "forward:/kccam/manager/login/manager_login/index.html";
		}
		
		@RequestMapping( { "/error" } )
		public String entryError() {
				
				return "forward:/index.html";
		}
		
		@Override public String getErrorPath() {
				return "forward:/index.html";
		}
}
