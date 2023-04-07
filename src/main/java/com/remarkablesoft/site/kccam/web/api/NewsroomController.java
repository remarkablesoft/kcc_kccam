package com.remarkablesoft.site.kccam.web.api;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.controller.BaseController;
import com.remarkablesoft.framework.web.util.CookieUtils;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.newsroom.model.NewsroomService;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomCnd;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@WEBLog
@RestController
@RequestMapping( "/kccam/api/newsroom/" )
public class NewsroomController extends BaseController {
		
		@Autowired
		NewsroomService newsroomService;
		
		
		/**
		 * 뉴스룸 등록, 수정
		 *
		 * @param info
		 * @return NewsroomInfo
		 * @author 황지영
		 */
		@RequestMapping( value = "/newsroom_insertOrUpdate")
		public ResponseEntity<NewsroomInfo> insertOrUpdate( @RequestBody NewsroomInfo info ){
		
				info = newsroomService.insertOrUpdate( info );
				return ResponseEntity.ok( info );
		}
		
		/**
		 * 뉴스룸 deleteFlagUpdate 처리
		 *
		 * @param info
		 * @author 황지영
		 */
		@RequestMapping( value = "/newsroom_deleteFlagUpdate")
		public ResponseEntity<Integer> deleteFlagUpdate( @RequestBody NewsroomInfo info ){
				
				return ResponseEntity.ok( newsroomService.deleteFlagUpdate( info ) );
		}
		
		/**
		 * 뉴스룸 단건 정보 가져오기
		 *
		 * @param cnd
		 * @return NewsroomInfo
		 * @author 황지영
		 */
		@RequestMapping( value = "newsroom_get" )
		public ResponseEntity<NewsroomInfo> get( @RequestBody NewsroomCnd cnd ){
				
				NewsroomInfo info = newsroomService.get( cnd );
				return new ResponseEntity<>( info, HttpStatus.OK );
		}
		
		/**
		 * 이전글과 다음글을 포함한 게시글 단건을 가져옵니다.
		 *
		 * @param cnd
		 * @return NewsroomInfo
		 * @author 황지영
		 */
		@RequestMapping( value = "newsroom_getWithPrevAndNext" )
		public ResponseEntity<NewsroomInfo> getWithPrevAndNext( HttpServletRequest request, @RequestBody NewsroomCnd cnd ){
				
				String cookieLang = "";
				
				cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );
				
				if ( StringUtils.isNotEmpty( cookieLang ) ) {
						cnd.setLang( cookieLang.toUpperCase() );
				}
				
				NewsroomInfo info = newsroomService.getWithPrevAndNext( cnd );
				return ResponseEntity.ok( info );
		}
		
		/**
		 * 조회수를 증가시키면서 이전글과 다음글을 포함한 게시글 단건을 가져옵니다.
		 *
		 * @param cnd
		 * @return NewsroomInfo
		 * @author 황지영
		 */
		@RequestMapping( value = "newsroom_viewWithPrevAndNext" )
		public ResponseEntity<NewsroomInfo> viewWithPrevAndNext( HttpServletRequest request, @RequestBody NewsroomCnd cnd ){
				
				String cookieLang = "";
				
				cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );
				
				if ( StringUtils.isNotEmpty( cookieLang ) ) {
						cnd.setLang( cookieLang.toUpperCase() );
				}
				
				NewsroomInfo info = newsroomService.viewWithPrevAndNext( cnd );
				return ResponseEntity.ok( info );
		}
		
		/**
		 * 뉴스 리스트를 가져옵니다.
		 *
		 * @param cnd
		 * @return List<NewsroomInfo>
		 * @author 황지영
		 */
		@RequestMapping( value = "newsroom_list" )
		public ResponseEntity<Map<String, Object>> list( HttpServletRequest request, @RequestBody NewsroomCnd cnd ){
				
				String cookieLang = "";
				
				cookieLang = CookieUtils.getCookieValue( request , AmConstants.COOKIE_LANG_NAME );
				
				if ( StringUtils.isNotEmpty( cookieLang ) && StringUtils.isEmpty( cnd.getLang() )) {
						cnd.setLang( cookieLang.toUpperCase() );
				};
				
				PageList<NewsroomInfo> list = newsroomService.list( cnd );
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put( "list", list );
				resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );
				
				return ResponseEntity.ok( resultMap );
		}
		
		
}
