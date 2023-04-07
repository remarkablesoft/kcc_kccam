package com.remarkablesoft.site.kccam.web.api;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.annotation.WEBLog;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.org.branch.model.BranchService;
import com.remarkablesoft.framework.service.org.branch.vo.BranchCnd;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.user.model.UserService;
import com.remarkablesoft.framework.web.controller.BaseController;



@WEBLog
@RestController
@RequestMapping( "/kccam/api/branch" )
public class BranchController extends BaseController {

	@Autowired
	protected BranchService branchService;

	@Autowired
	protected UserService userService;

	/**
	* 지점 리스트를 가져옵니다.
	*
	* @param cnd
	* @return List<BranchInfo>
	* @author 최원준
	*/
	@RequestMapping( value = "/branch_listAll" )
	public ResponseEntity<List<BranchInfo>> listAll( @RequestBody BranchCnd cnd ) {

		List<BranchInfo> listAll = branchService.listAll( cnd );
		return ResponseEntity.ok( listAll );
	}

	/**
	 *  지점 리스트 페이지 리스트를 가져옵니다.
	 *
	 * @param BranchCnd
	 * @return List<BranchInfo>
	 * @author 남윤재
	 */
	@RequestMapping( value = "/branch_list" )
	public ResponseEntity<HashMap<String, Object>> list( @RequestBody BranchCnd cnd ) {

		HashMap<String, Object> resultMap = new HashMap<>();
		PageList<BranchInfo> list = branchService.list( cnd );

		resultMap.put( "list", list );
		resultMap.put( "totalCount", CollectionUtils.isEmpty( list ) ? 0 : list.getTotalCount() );

		return ResponseEntity.ok( resultMap );
	}

	/**
	 * 지점 정보를 추가합니다.
	 *
	 * @param BranchInfo
	 * @return BranchInfo
	 * @author 남윤재
	*/
	@RequestMapping( value = "/branch_save" )
	public ResponseEntity<BranchInfo> save( @RequestBody BranchInfo info ) {

		info = branchService.insert( info );

		return ResponseEntity.ok( info );
	}

	/**
	 * 지점 정보 단 건을 가져옵니다.
	 *
	 * @param BranchCnd
	 * @return HashMap<String, Object>
	 * @author 남윤재
	*/
	@RequestMapping( value = "/branch_get" )
	public ResponseEntity<BranchInfo> get( @RequestBody BranchCnd cnd) {

		BranchInfo branch = branchService.get( cnd );

		return ResponseEntity.ok( branch );
	}
	/**
	 * 지점 정보를 수정합니다.
	 *
	 * @param BranchInfo
	 * @return The number of rows affected by the update
	 * @author 남윤재
	*/
	@RequestMapping( value = "/branch_update" )
	public ResponseEntity<Integer> update( @RequestBody BranchInfo info ) {

		int result = branchService.update( info );

		return ResponseEntity.ok( result );
	}

	/**
	 * 지점 정보를 삭제합니다.
	 *
	 * @param BranchInfo
	 * @return The number of rows affected by the delete
	 * @author 남윤재
	*/
	@RequestMapping( value = "/branch_delete" )
	public ResponseEntity<Integer> delete( @RequestBody BranchInfo info ) {

		int result = branchService.delete( info );
		return ResponseEntity.ok( result );
	}

}
