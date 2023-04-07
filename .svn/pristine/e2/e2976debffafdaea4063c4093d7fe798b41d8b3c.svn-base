package com.remarkablesoft.framework.service.org.branch.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.remarkablesoft.framework.service.org.group.vo.GroupInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.org.branch.vo.BranchCnd;
import com.remarkablesoft.framework.service.org.branch.vo.BranchInfo;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.file.model.impl.FileBLO;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:
 * 		@프로그램 ID		:	BranchBLO.java
 * 		@프로그램 개요 		:	지점 정보 비즈니스 로직
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 3. 20.	:	choi	-	신규생성
 * 		============================================================================
 */
@BLO
public class BranchBLO {

		@Autowired
		protected BranchDAO branchDAO;

		@Autowired
		protected UserBLO userBLO;
		
		@Autowired
		protected FileBLO fileBLO;
		
		/**
		 * 지점 정보를 입력합니다.
		 * @param info
		 * @return
		 */
		public BranchInfo insert( BranchInfo info ) {
				if( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}
				return  branchDAO.insert( info ) > 0 ? info : null;
		}


		/**
		 * 지점 정보를 삭제합니다.
		 * @param oid
		 * @return
		 */
		public int delete( String oid ) {
				if ( StringUtils.isEmpty( oid ) ) {
					return 0;
				}

				return branchDAO.delete( oid );
		}


		/**
		 * 지점 리스트를 넘겨서 등록, 수정, 삭제를 처리합니다.
		 *
		 * @param info
		 * @return
		 */
		public int saveForBranchList( BranchInfo info ) {

				int result = 0;
				if( StringUtils.isEmpty( info.getBranchList() ) || StringUtils.isEmpty(  info.getDeleteBranchList() )) {
						return 0;
				}


				for ( BranchInfo branch : info.getBranchList() ) {

					int count = 0;
					if( StringUtils.isEmpty( branch.getOid() ) ) {

							BranchInfo info2 = insert( branch );
							if ( info2 != null) {
									count++;
							}
					}
					else {
							count = update( branch );
					}

					result += count;
				}

				for ( BranchInfo branch : info.getDeleteBranchList() ) {

						if ( StringUtils.hasText(  branch.getOid() )) {
								result += delete ( branch.getOid());
						}

				}

				return result;
		}

		/**
		 * 지점 정보를 수정합니다.
		 * @param info
		 * @return
		 */
		public int update( BranchInfo info ) {

				if( StringUtils.isEmpty( info.getOid() ) ) {
						return 0;
				}

				return branchDAO.update( info );
		}

		/**
		 * 지점 정보를 반환합니다.
		 * @param cnd
		 * @return
		 */
		public BranchInfo get( String branchOid ) {

				BranchCnd branchCnd = new BranchCnd();
				branchCnd.setOid( branchOid );

				return get ( branchCnd);
		}

		/**
		 * 지점 정보를 반환합니다.
		 * @param cnd
		 * @return
		 */
		public BranchInfo get( BranchCnd cnd ) {

				BranchInfo info  = branchDAO.get( cnd );

				if( info == null ) {
					return SystemFactory.getBranchInfo();
				}

				return info;
		}

		/**
		 * 지점 정보를 모두 가져옵니다.
		 *
		 * @param cnd        
		 * @return
		 */
		public List<BranchInfo> listAll( BranchCnd cnd ) {

				List<BranchInfo> list = branchDAO.listAll( cnd );
     			
				if ( CollectionUtils.isEmpty( list ) ) {
					return null;
				}
				
				fillBranchUser( list, cnd );
				
				return list;
		}

		/**
		 * 지점 페이지 리스트를 반환합니다.
		 * @param cnd
		 * @return
		 */
		public PageList<BranchInfo> list( BranchCnd cnd ) {

				return branchDAO.list( cnd );
		}
	
		/**
		* 지점에 속한 유저정보를 채워줍니다.
		*
		* @param list
		* @author 최원준
		*/
		private void fillBranchUser( List<BranchInfo> list, BranchCnd branchCnd ) {
			
			if ( !branchCnd.isFillUser() ) {
				return;
			}
			
			List<String> branchOidList = list.stream().map( BranchInfo::getOid ).collect( Collectors.toList() );
			UserCnd userCnd = new UserCnd();
			userCnd.setBranchOidList( branchOidList );
			
			List<UserInfo> userList = userBLO.listAll( userCnd );
			if ( CollectionUtils.isEmpty( userList ) ) {
				return;
			}
			
			Map<String, List<UserInfo>> groupedMap = userList.stream().collect( Collectors.groupingBy( UserInfo::getBranchOid, Collectors.toList() ) );
			list.forEach( branch -> branch.setBranchUserList( groupedMap.get( branch.getOid() ) ) );
			
		}
	
		/**
		* 그룹리스트에 지점정보를 채워줍니다.
		*
		* @param list
		* @author 최원준
		*/
		public void fillBranchInfo( List<GroupInfo> list ) {
			
			if( CollectionUtils.isEmpty( list ) ) {
				return;
			}
			BranchCnd branchCnd = new BranchCnd();
			for ( GroupInfo groupInfo : list ) {
				if( StringUtils.hasText( groupInfo.getBranchOid() ) ) {
					if( !branchCnd.getBranchOidList().contains( groupInfo.getBranchOid() ) ) {
						branchCnd.addBranchOid( groupInfo.getBranchOid() );
						break;
					}
				}
			}
			
			List<BranchInfo> branchList = listAll( branchCnd );
			if( CollectionUtils.isEmpty( branchList ) ) {
				return;
			}
			
			for ( GroupInfo groupInfo : list ) {
				for ( BranchInfo branchInfo : branchList ) {
					if( StringUtils.hasText( groupInfo.getBranchOid() ) && groupInfo.getBranchOid().equals( branchInfo.getOid() ) ) {
						groupInfo.setBranchInfo( branchInfo );
						break;
					}
				}
			}
		}
		
		/**
		 * Branch 정보가 필요한 데이터는 채운다.
		 *
		 * @param userList
		 */
		public void fillBranch( List<UserInfo> userList ) {

				List<String> branchOids  = userList.stream().map( UserInfo::getBranchOid ).collect( Collectors.toList() );

				if ( CollectionUtils.isEmpty(  branchOids ) ) {
					return ;
				}

				BranchCnd cnd = new BranchCnd();
				cnd.setBranchOidList( branchOids );
				List<BranchInfo> branchList = listAll( cnd );

				if ( CollectionUtils.isEmpty( branchList )) {
					return;
				}
				
				// list에서 null 제거
				CollectionUtils.nullRemove( userList );
				CollectionUtils.nullRemove( branchList );
				
				userList.stream()
				.filter( user -> user.getBranchOid() != null && StringUtils.hasText( user.getBranchOid() ) )
				.forEach( user -> branchList.stream()
										.filter( branch -> user.getBranchOid().equals( branch.getOid() ) )
										.forEach( branch -> user.setBranchName( branch.getName() ) )
										);

		}

}
