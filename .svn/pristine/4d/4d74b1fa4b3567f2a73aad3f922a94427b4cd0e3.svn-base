package com.remarkablesoft.site.kccam.web.api;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.user.model.UserService;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.web.controller.BaseController;

@WEBLog
@RestController
@RequestMapping( "/kccam/api/user" )
public class UserController extends BaseController {

	@Autowired
	protected UserService userService;

	/**
	 *  유저 리스트 페이지 리스트를 가져옵니다.
	 *
	 * @param cnd
	 * @return HashMap<String, Object>
	 * @author 남윤재
	 */
	@RequestMapping( value = "/user_list")
	public ResponseEntity<HashMap<String, Object>> list( @RequestBody UserCnd cnd ) {

		HashMap<String, Object> resultMap = new HashMap<>();
		PageList<UserInfo> list= userService.list( cnd );

		resultMap.put( "list", list );
		resultMap.put( "totalCount" , CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

		return ResponseEntity.ok( resultMap );
	}

	/**
	* 유저 리스트를 가져옵니다.
	*
	* @param cnd
	* @return List<UserInfo>
	* @author 최원준
	*/
	@RequestMapping( value = "/user_listAll")
	public ResponseEntity<List<UserInfo>> listAll( @RequestBody UserCnd cnd ) {

		List<UserInfo> list= userService.listAll( cnd );
		return ResponseEntity.ok( list );
	}

    /**
     * 신규 유저 정보를 등록합니다.
     *
     * @param UserInfo
     * @return UserInfo
     * @author 남윤재
    */
	@RequestMapping( value ="/user_insert")
	public ResponseEntity<UserInfo> insert( @RequestBody UserInfo info ) {

		info = userService.insert( info );
		return ResponseEntity.ok( info );
	}

    /**
     * 유저 정보 한 건을 가져옵니다.
     *
     * @param oid
     * @return UserInfo
     * @author 남윤재
    */
	@RequestMapping( value ="/user_get")
	public ResponseEntity<UserInfo> get( @RequestBody UserCnd cnd ) {

		return ResponseEntity.ok( userService.getUser( cnd ) );
	}

	/**
	 * 유저 정보를 수정합니다.
     * @param UserInfo
     * @return The number of rows affected by the update
     * @author 남윤재
     */
    @RequestMapping( value = "/user_update")
	public ResponseEntity<UserInfo> update( @RequestBody UserInfo info ) {

		return ResponseEntity.ok( userService.update(info) );
	}
	/**
	 * 유저 정보를 삭제합니다.
     * @param UserInfo
     * @return The number of rows affected by the delete
     * @author 남윤재
     */
    @RequestMapping( value = "/user_delete")
	public ResponseEntity<Integer> delete ( @RequestBody UserInfo info ) {

		int result = userService.delete( info.getOid() );
		return ResponseEntity.ok( result );
	}

}
