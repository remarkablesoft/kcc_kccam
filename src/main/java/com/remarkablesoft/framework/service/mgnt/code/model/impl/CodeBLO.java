package com.remarkablesoft.framework.service.mgnt.code.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeCnd;
import com.remarkablesoft.framework.service.mgnt.code.vo.CodeInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;

/**
*
* 		@주시스템			:	framework-web
* 		@서브 시스템		:	mgnt-code
* 		@프로그램 ID		:	CodeBLO
* 		@프로그램 개요 	:	공통코드 BLO
*
* 		@변경이력
*      ============================================================================
* 		1.0  2019. 10. 3.	:	james	-	신규생성
* 		============================================================================
*/
@BLO
public class CodeBLO {

		
		@Autowired
		protected CodeDAO itemCodeDAO;
		

		/**
		 * 공통 옵션 정보를 저장합니다.
		 * 해당 직책에 대한 Role을 생성
		 *
		 *
		 * @param info
		 * @return
		 */
		@CacheEvict( value = "code", allEntries = true )
		public int insert( CodeInfo info ) {

				if( StringUtils.isEmpty( info.getOid() ) ) {
						info.setOid( OIDGenerator.generateOID() );
				}

				return itemCodeDAO.insert( info );
		}


		/**
		 * 대량 Insert
		 *
		 * @param list
		 * @return
		 */
		@CacheEvict( value = "code", allEntries = true )
		public int insertBulk( List<CodeInfo> list ) {

			 if ( CollectionUtils.isEmpty( list )) {
				return 0;
			 }

			 list.forEach( code -> {
					 if( StringUtils.isEmpty( code.getOid() ) ) {
							 code.setOid( OIDGenerator.generateOID() );
					 }
			 });

			 return itemCodeDAO.insertBulk( list );
		}

		/**
		 * 공통 옵션 정보를 수정합니다.
		 * @param info
		 * @return
		 */
		@CacheEvict( value = "code", allEntries = true )
		public int update( CodeInfo info ) {

				if( StringUtils.isEmpty( info.getOid() ) ) {
						return 0;
				}


				return itemCodeDAO.update( info );
		}

		/**
		 * 공통 옵션 여러개를 한번에 업데이트
		 *
		 * @param list
		 * @return
		 */
		@CacheEvict( value = "code", allEntries = true )
		public int updateBulk( List<CodeInfo> list) {

				if( CollectionUtils.isEmpty( list ) ) {
						return 0;
				}

				return itemCodeDAO.updateBulk( list );
		}

		/**
		 * 공통 옵션 정보를 삭제합니다.
		 *
		 * 삭제시 직책이면 직책의 Role도 삭제한다.
		 *
		 * @param oid
		 * @return
		 */
		@CacheEvict( value = "code", allEntries = true )
		public int delete( String oid ) {

				CodeInfo option = get ( oid );

				if ( option == null ) {
					return 0;
				}

				return itemCodeDAO.delete( oid );
		}

		/**
		 * 공통 옵션 여러개를 한번에 지울때 사용
		 *
		 * @param list
		 * @return
		 */
		@CacheEvict( value = "code", allEntries = true )
		public int deleteByOids( List<CodeInfo> list ) {

				if ( CollectionUtils.isEmpty( list )) {
						return 0;
				}

				return itemCodeDAO.deleteByOids( list );
		}


		/**
		 * 공통옵션을 OID로 가져온다
		 *
		 * @param oid
		 * @return
		 */
		@Cacheable( value = "code" , keyGenerator = "cacheKeyGenerator")
		public CodeInfo get( String oid ){

				return itemCodeDAO.get( oid );
		}
		
		/**
		 * cnd 객체로 가져오기
		 * 
		 * @author pc
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "code" , keyGenerator = "cacheKeyGenerator")
		public CodeInfo getByCnd( CodeCnd cnd ){
				
				return itemCodeDAO.getByCnd( cnd );
		}
		
		/**
		 * 공통 옵션 정보를 등록/수정합니다.
		 * @param info
		 * @return
		 */
		public int save( CodeInfo info ) {
				
				int result = 0;
				if( StringUtils.isEmpty( info.getOid() ) ) {
						result = insert( info );
				}
				else {
						result = update( info );
				}

				return result;
		}



		/**
		 * 조건에 맞는 공통옵션 리스트를 반환합니다.
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "code" , keyGenerator = "cacheKeyGenerator")
		public List<CodeInfo> list( CodeCnd cnd ) {

				List<CodeInfo> list = itemCodeDAO.list( cnd );

				return list;

		}

		/**
		 * 조건에 맞는 공통옵션 페이지리스트를 반환합니다.
		 * @param cnd
		 * @return
		 */
		@Cacheable( value = "code" , keyGenerator = "cacheKeyGenerator")
		public PageList<CodeInfo> pageList( CodeCnd cnd ) {

				PageList<CodeInfo> list = itemCodeDAO.pageList( cnd );

				return list;
		}



}
