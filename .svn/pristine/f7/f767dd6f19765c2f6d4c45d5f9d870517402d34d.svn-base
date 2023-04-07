package com.remarkablesoft.site.kccam.web.api;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneCnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.web.controller.BaseController;
import com.remarkablesoft.site.kccam.service.onetoone.config.model.OneToOneConfigService;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.model.OneToOneService;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneInfo;

import java.util.Collection;
import java.util.HashMap;

@WEBLog
@RestController
@RequestMapping( "/kccam/api/onetoone" )
public class OneToOneController extends BaseController {
		
		@Autowired
		protected OneToOneService oneToOneService;
		
		@Autowired
		protected OneToOneConfigService oneToOneConfigService;
		
		/**
		 * 1대1 문의 정보를 저장합니다.
		 *
		 * @param oneToOneInfoJson
		 * @return OneToOneInfo
		 * @author 최원준
		 */
		@RequestMapping( value = "/onetoone_save" )
		public ResponseEntity<OneToOneInfo> save( @RequestBody OneToOneInfo info ) {
				
				info = oneToOneService.insert( info );
				return ResponseEntity.ok( info );
		}
		
		/**
		 * 1대1 문의 정보 페이지 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return HashMap<String, Object>
		 * @author 남윤재
		 */
		@RequestMapping( value = "onetoone_list" )
		public ResponseEntity<HashMap<String, Object>> list( @RequestBody OneToOneCnd cnd ) {
				
				HashMap<String, Object> resultMap = new HashMap<>();
				PageList<OneToOneInfo> list = oneToOneService.list( cnd );
				resultMap.put( "list", list );
				resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );
				
				return ResponseEntity.ok( resultMap );
		}
		
		/**
		 *  1대1 문의 상세 내용을 가져옵니다.
		 *
		 *  @param  cnd
		 *  @return info
		 *  @author 남윤재
		 */
		@RequestMapping( value = "onetoone_get" )
		public ResponseEntity<OneToOneInfo> get( @RequestBody OneToOneCnd cnd ) {
				
				return ResponseEntity.ok( oneToOneService.get( cnd ) );
		}
		
		/**
		 *
		 * 문의자에게 메일을 보냅니다.
		 *
		 * @param info
		 * @return
		 */
		@RequestMapping( value = "onetoone_sendEmail" )
		public void sendEmail( @RequestBody OneToOneInfo info ) {
				oneToOneService.sendEmailToCustomer( info );
		}
}
