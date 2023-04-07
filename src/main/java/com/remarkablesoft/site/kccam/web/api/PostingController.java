package com.remarkablesoft.site.kccam.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.posting.model.PostingService;
import com.remarkablesoft.framework.service.board.posting.vo.PostingCnd;
import com.remarkablesoft.framework.service.board.posting.vo.PostingInfo;
import com.remarkablesoft.framework.web.controller.BaseController;
import com.remarkablesoft.framework.web.util.CookieUtils;
import com.remarkablesoft.site.kccam.service.AmConstants;

@WEBLog
@RestController
@RequestMapping("/kccam/api/posting")
public class PostingController extends BaseController {

		@Autowired
		protected PostingService postingService;


		/**
		 * 글 등록, 수정
		 *
		 * @param info
		 * @return PostingInfo
		 * @author 황지영
		 */
		@RequestMapping( value = "/posting_insertOrUpdate")
		public ResponseEntity<PostingInfo> insertOrUpdate( @RequestBody PostingInfo info ){

				info = postingService.insertOrUpdate( info );
				return ResponseEntity.ok( info );
		}

		/**
		 * 단건 포스팅 정보를 가져옵니다
		 *
		 * @param cnd
		 * @return PostingInfo
		 * @author 황지영
		 */
		@RequestMapping( value = "/posting_get")
		public ResponseEntity<PostingInfo> get( HttpServletRequest request, @RequestBody PostingCnd cnd ){
				
				String cookieLang = "";
				
				cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );
	
				if ( StringUtils.isNotEmpty( cookieLang ) ) {			
					cnd.setLang( cookieLang.toUpperCase() );
				};
			
				PostingInfo info = postingService.get( cnd );
				return new ResponseEntity<>( info, HttpStatus.OK );
		}

		/**
		 * 이전글과 다음글을 포함한 게시글 단건을 가져옵니다.
		 *
		 * @param cnd
		 * @return PostingInfo
		 * @author 최원준
		 */
		@RequestMapping(value = "/posting_getWithPrevAndNext")
		public ResponseEntity<PostingInfo> getWithPrevAndNext( HttpServletRequest request, @RequestBody PostingCnd cnd) {
			
				String cookieLang = "";
				
				cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );
	
				if ( StringUtils.isNotEmpty( cookieLang ) ) {			
					cnd.setLang( cookieLang.toUpperCase() );
				};

				PostingInfo info = postingService.getWithPrevAndNext( cnd );
				return ResponseEntity.ok( info );
		}
		
		/**
		 * 조회수를 증가시키면서 이전글과 다음글을 포함한 게시글 단건을 가져옵니다.
		 *
		 * @param cnd
		 * @return PostingCnd
		 * @author 황지영
		 */
		@RequestMapping(value = "/posting_viewWithPrevAndNext")
		public ResponseEntity<PostingInfo> viewWithPrevAndNext( HttpServletRequest request, @RequestBody PostingCnd cnd ){
			
				String cookieLang = "";
				
				cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );
	
				if ( StringUtils.isNotEmpty( cookieLang ) ) {			
					cnd.setLang( cookieLang.toUpperCase() );
				};
				
				PostingInfo info = postingService.viewWithPrevAndNext( cnd );
				return ResponseEntity.ok( info );
		}

		/**
		 * 게시글 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<PostingInfo>
		 * @author 최원준
		 */
		@RequestMapping(value = "/posting_list")
		public ResponseEntity<Map<String, Object>> list( HttpServletRequest request, @RequestBody PostingCnd cnd ) {
			
				String cookieLang = "";
				
				cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );
	
				if ( StringUtils.isNotEmpty( cookieLang ) && StringUtils.isEmpty( cnd.getLang() )) {
					cnd.setLang( cookieLang.toUpperCase() );
				};			

				PageList<PostingInfo> list = postingService.list(cnd);
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put( "list", list);
				resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

				return ResponseEntity.ok( resultMap );
		}
	
		/**
		 * 게시글을 삭제합니다
		 * @param cnd
		 * @return int
		 * @author 황지영
		 * **/
		@RequestMapping(value = "/posting_delete")
		public ResponseEntity<Integer> delete( @RequestBody PostingCnd cnd ){

				return ResponseEntity.ok( postingService.delete( cnd.getOid(), cnd.getDelUser() ) );
		}
		

}
