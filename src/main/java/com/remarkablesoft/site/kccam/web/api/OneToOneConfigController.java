package com.remarkablesoft.site.kccam.web.api;

import java.util.List;

import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.CookieUtils;
import com.remarkablesoft.site.kccam.service.AmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.web.controller.BaseController;
import com.remarkablesoft.site.kccam.service.onetoone.config.model.OneToOneConfigService;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigCnd;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigInfo;

import javax.servlet.http.HttpServletRequest;

@WEBLog
@RestController
@RequestMapping( "/kccam/api/onetooneconfig" )
public class OneToOneConfigController extends BaseController {

	@Autowired
	protected OneToOneConfigService oneToOneConfigService;

	/**
	 * 1대1 문의 설정 정보를 저장합니다.
	 *
	 * @param info
	 * @return OneToOneConfigInfo
	 * @author 남윤재
	 */
	@RequestMapping( value = "/onetooneconfig_save" )
	public ResponseEntity<OneToOneConfigInfo> save( @RequestBody OneToOneConfigInfo info ) {

		OneToOneConfigInfo newInfo = oneToOneConfigService.insertOrUpdate( info );
		return ResponseEntity.ok( newInfo );
	}
	
	/**
	 *  1대1 문의 설정 정보 사용여부를 변경합니다.
	 *
	 *  @param  info
	 *  @return OneToOneConfigInfo
	 *  @author 남윤재
	 */
	@RequestMapping( value = "/onetooneconfig_delete_flag" )
	public ResponseEntity<Integer> changeFlag( @RequestBody OneToOneConfigInfo info ) {
			
		return ResponseEntity.ok( oneToOneConfigService.deleteFlagUpdate( info ) );
	}
	
	/**
	 * 1대1 문의 설정 정보를 삭제합니다.
	 *
	 * @param info
	 * @return OneToOneConfigInfo
	 * @author 남윤재
	 */
	@RequestMapping( value = "/onetooneconfig_delete" )
	public ResponseEntity<Integer> delete( @RequestBody OneToOneConfigInfo info ) {

		return ResponseEntity.ok( oneToOneConfigService.delete( info.getOid() ) );
	}
		
		/**
		 * 1:1 문의 설정 정보를 가져옵니다.
		 * ( 유저 화면용. 쿠키값으로 다국어별 정보를 조회하기 위함 )
		 *
		 * @param cnd
		 * @return OneToOneConfigInfo
		 * @author 남윤재
		 */
		@RequestMapping( value = "/onetooneconfig_listAll" )
		public ResponseEntity<List<OneToOneConfigInfo>> configListAll( HttpServletRequest request, @RequestBody OneToOneConfigCnd cnd ) {
				
				String cookieLang = "";
				
				cookieLang = CookieUtils.getCookieValue( request, AmConstants.COOKIE_LANG_NAME );
				
				if ( StringUtils.isNotEmpty( cookieLang ) && StringUtils.isEmpty( cnd.getLang() ) ) {
						cnd.setLang( cookieLang.toUpperCase() );
				}
				
				List<OneToOneConfigInfo> list = oneToOneConfigService.listAll( cnd );
				return ResponseEntity.ok( list );
		}
		
		/**
		 * 1:1 문의 설정 정보를 가져옵니다.
		 * ( 관리자용. 다른 관리자 화면과 달리 1대1은
		 * 한 화면에서 전환없이 전체 언어에 대한 정보를 관리해야하기 때문에
		 * 쿠키값을 전달받지 않기 위함 )
		 *
		 * @param cnd
		 * @return OneToOneConfigInfo
		 * @author 남윤재
		 */
		@RequestMapping( value = "/onetooneconfig_listAll_mgmt" )
		public ResponseEntity<List<OneToOneConfigInfo>> configListAllMgmt( @RequestBody OneToOneConfigCnd cnd ) {
				
				List<OneToOneConfigInfo> list = oneToOneConfigService.listAll( cnd );
				return ResponseEntity.ok( list );
		}

}
