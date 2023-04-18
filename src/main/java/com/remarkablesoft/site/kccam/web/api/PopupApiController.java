package com.remarkablesoft.site.kccam.web.api;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.popup.model.PopupService;
import com.remarkablesoft.framework.service.board.popup.vo.PopupCnd;
import com.remarkablesoft.framework.service.board.popup.vo.PopupInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 		@주시스템			:	Portal
 * 		@서브 시스템		:	portal - web - api - user - v1
 * 		@프로그램 ID		:	PortalUserPopupAPIController
 * 		@프로그램 개요 	:	사용자 - 팝업존 컨트롤러
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2021. 12. 01.	:	sirena	-	신규생성
 * 		============================================================================
 */
@WEBLog
@RestController
@RequestMapping( "/kccam/api/popup/" )
public class PopupApiController {
		
		@Autowired
		protected PopupService popupService;
		
		@PostMapping( "/popupApi_save" )
		public ResponseEntity<?> save( @RequestBody PopupInfo popupInfo ) {
				
				return ResponseEntity.ok( popupService.insertOrUpdate( popupInfo ) );
		}
		
		/**
		 * useYn 사용중('Y')이고, 오늘 날짜가 포함된 리스트만 가져오기
		 *
		 * @param
		 * @author sirena
		 * @작성일 2021-09-07
		 **/
		@RequestMapping( value = "/popupApi_operationList" )
		public ResponseEntity<?> operationList( @RequestBody PopupCnd popupCnd ) {
				
				List<PopupInfo> list = popupService.operationList( popupCnd );
				
				return ResponseEntity.ok( list );
		}
		
		@RequestMapping( value = "/popupApi_get" )
		public ResponseEntity<?> getPopup( @RequestBody PopupCnd popupCnd  ) throws Exception {
				
				PopupInfo popupInfo = popupService.get( popupCnd );
				return ResponseEntity.ok( popupInfo );
		}
		
		@PostMapping( "/popupApi_list" )
		public ResponseEntity<?> list( @RequestBody PopupCnd popupCnd ) throws Exception {
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				
				PageList<PopupInfo> list = popupService.list( popupCnd );
				resultMap.put( "list", list );
				resultMap.put( "listCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );
				
				return ResponseEntity.ok( resultMap );
		}
		
		/**
		 * oid 리스트에 해당하는 팝업들을 삭제합니다.
		 *
		 * @author 윤건희
		 * @작성일 2023-04-18
		 *
		 * @param deleteOidList
		 * @return
		 * @throws Exception
		 */
		@PostMapping( value = "/popupApi_delete" )
		public ResponseEntity<?> deletePopup( @RequestBody List<String> deleteOidList ) {
				
				int result = 0;
				
				for ( String deleteOid : deleteOidList ) {
						
						result += popupService.delete( deleteOid );
				}
				
				return ResponseEntity.ok( result );
		}
}
