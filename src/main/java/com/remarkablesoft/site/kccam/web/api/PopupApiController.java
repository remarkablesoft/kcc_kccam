package com.remarkablesoft.site.kccam.web.api;

import com.remarkablesoft.framework.service.board.popup.model.PopupService;
import com.remarkablesoft.framework.service.board.popup.vo.PopupCnd;
import com.remarkablesoft.framework.service.board.popup.vo.PopupInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RestController
@RequestMapping( "/kccam/api/popup/" )
public class PopupApiController {
		
		@Autowired
		protected PopupService popupService;
		
		@GetMapping( "/popup_save" )
		public ResponseEntity<?> save( PopupInfo popupInfo ) {
				
				return ResponseEntity.ok( popupService.insertOrUpdate( popupInfo ) );
		}
		
		/**
		 * useYn 사용중('Y')이고, 오늘 날짜가 포함된 리스트만 가져오기
		 *
		 * @param
		 * @author sirena
		 * @작성일 2021-09-07
		 **/
		@RequestMapping( value = "/popup_operationList" )
		public ResponseEntity<?> operationList( @RequestBody PopupCnd popupCnd ) {
				
				List<PopupInfo> list = popupService.operationList( popupCnd );
				
				return ResponseEntity.ok( list );
		}
}
