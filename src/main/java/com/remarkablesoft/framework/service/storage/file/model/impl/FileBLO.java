package com.remarkablesoft.framework.service.storage.file.model.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.exception.BLORuntimeException;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.service.storage.storagefile.model.impl.StorageFileBLO;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import com.remarkablesoft.framework.web.util.AutheUtils;

@BLO
public class FileBLO {

		@Autowired
		protected FileDAO fileDAO;
		
		@Autowired
		protected StorageFileBLO storageFileBLO;



		public int insert( UserInfo user ) {

				if ( user == null || user.getFileList().size() <= 0 ) {
						return 0;
				}

				String containerOid= user.getGroupOid();
				String targetOid= user.getOid();
				String targetObject = UserInfo.getObjectType();
				String inputUser = user.getInputUser();

				if ( StringUtils.isEmpty( inputUser)) {
						inputUser = AutheUtils.getLoginUserOid();
				}

				return insertList( user.getFileList(), containerOid, targetOid, targetObject, inputUser );
		}

		public int insert( List<FileInfo> fileInfoList, String targetObject, String targetOid, String containerOid ) {

				if ( fileInfoList == null || fileInfoList.size() <= 0 )
						return 0;
				
				for ( FileInfo fileInfo : fileInfoList ) {
						
						insert( fileInfo, targetObject, targetOid, containerOid );
				}

				return fileInfoList.size();
		}
		
		public int insertWithOrderNo( List<FileInfo> fileInfoList, String targetObject, String targetOid, String containerOid ) {

			if ( fileInfoList == null || fileInfoList.size() <= 0 )
					return 0;
			
			int orderNo = 1;
			
			for ( FileInfo fileInfo : fileInfoList ) {
					
					fileInfo.setOrderNo( orderNo++ );
					
					insert( fileInfo, targetObject, targetOid, containerOid );
			}

			return fileInfoList.size();
		}


		public FileInfo insert( FileInfo fileInfo ) {

				if ( fileInfo == null ) {
					return null;
				}

				fileDAO.insert( fileInfo );

				return fileInfo;
		}


		
		/**
		 * <pre>
		 * 해당 메소드를 사용하기 보다는
		 * insert(PostingInfo posting) 처럼 해당 객체로 처리하는게 좋음
		 * 아니면 insert(fileInfo, targetObject, targetOid, fileType, containerOid) 메소드 사용
		 * </pre>
		 *
		 *
		 * @author james
		 * @param fileInfo
		 * @param targetObject
		 * @param targetOid
		 * @return
		 */
		public FileInfo insert( FileInfo fileInfo, String targetObject, String targetOid ) {

				return insert( fileInfo, targetObject, targetOid, null);
		}


		/**
		 * 해당 메소드를 사용하기 보다는
		 * insert(PostingInfo posting) 처럼 해당 객체로 처리하는게 좋음
		 *
		 *
		 * @author james
		 * @param fileInfo
		 * @param targetObject
		 * @param targetOid
		 * @param containerOid
		 * @return
		 */
		public FileInfo insert( FileInfo fileInfo, String targetObject, String targetOid, String containerOid ) {

				if ( fileInfo == null ) {
						return null;
				}
				
				assertThat( fileInfo.getStorageFileUid() ).as( "StorageFileUid가 없습니다." ).isNotEmpty();

				if ( StringUtils.isEmpty( fileInfo.getOid() ) ) {
						fileInfo.setOid( OIDGenerator.generateOID() );
				}

				if ( StringUtils.isEmpty( fileInfo.getInputUser())) {
						fileInfo.setInputUser( AutheUtils.getLoginUserOid() );
				}

				fileInfo.setTargetObject( targetObject );
				fileInfo.setTargetOid( targetOid );
				fileInfo.setContainerOid( containerOid );

				insert( fileInfo);

				return fileInfo;
		}
		
		/**
		 * 삭제 후 등록
		 *
		 * @author james
		 * @param fileList
		 * @param containerOid
		 * @param oid
		 * @param objectType
		 * @param loginUserOid
		 */
		public void deleteAndInsert( List<FileInfo> fileList, String objectType, String oid, String fileType, String containerOid, String loginUserOid ) {
				
				// 삭제
				deleteByTarget( oid, objectType );
				
				// 등록
				insertList( fileList, objectType, oid, fileType, containerOid, loginUserOid );
				
		}


		public FileInfo get( String oid ) {

				FileCnd cnd = new FileCnd();
				cnd.setOid( oid );

				return getByCnd( cnd );
		}

		public FileInfo getByStorageFileUid( String storageFileUid ) {

				FileCnd cnd = new FileCnd();
				cnd.setStorageFileUid( storageFileUid );

				return getByCnd( cnd );
		}

		public FileInfo getByTarget( String targetObject, String targetOid ) {

				FileCnd cnd = new FileCnd();
				cnd.setTargetObject( targetObject );
				cnd.setTargetOid( targetOid );

				return getByCnd( cnd );
		}

		public FileInfo getByCnd( FileCnd cnd ) {

				return fileDAO.getByCnd( cnd );
		}

		public List<FileInfo> listByTarget( String targetObject, String targetOid ) {

				return listByTarget( targetObject, targetOid, null );
		}
		
		public List<FileInfo> listByTarget( String targetObject, String targetOid, String fileType ) {

				FileCnd cnd = new FileCnd();
				cnd.setTargetObject( targetObject );
				cnd.setTargetOid( targetOid );
				cnd.setFileType( fileType );

				return listAll( cnd );
		}

		public List<FileInfo> listByTarget( String targetObject, List<String> targetOidList ) {

				return listByTarget( targetObject, targetOidList, null );
		}
		
		public List<FileInfo> listByTarget( String targetObject, List<String> targetOidList, String fileType ) {

				FileCnd cnd = new FileCnd();
				cnd.setTargetObject( targetObject );
				cnd.setTargetOidList( targetOidList );
				cnd.setFileType( fileType );

				return listAll( cnd );
		}

		public List<FileInfo> listAll( FileCnd cnd ) {

				List<FileInfo> fileList = fileDAO.listAll( cnd );

				return fileList != null ? fileList : new ArrayList<>();
		}



		public void deleteByTarget( String targetOid, String targetObject ) {

				deleteByTarget( targetOid, targetObject, null );
		}

		public void deleteByTarget( String targetOid, String targetObject, String fileType ) {

				FileCnd cnd = new FileCnd();
				cnd.setTargetOid( targetOid );
				cnd.setTargetObject( targetObject );
				cnd.setFileType( fileType );

				deleteByCnd( cnd );
		}


		public int deleteByStorageFileUid( String storageFileUid ) {

				if ( StringUtils.isEmpty( storageFileUid ) ) {
						return 0;
				}

				FileCnd cnd = new FileCnd();
				cnd.setStorageFileUid( storageFileUid );

				return deleteByCnd( cnd );
		}

		public int deleteByStorageFileUidList( List<String> storageFileUidList ) {

				if ( CollectionUtils.isEmpty( storageFileUidList ) ) {
						return 0;
				}

				FileCnd cnd = new FileCnd();
				cnd.setStorageFileUidList( storageFileUidList );

				return deleteByCnd( cnd );
		}

		public int deleteByStorageFileUids( String[] storageFileUidLists ) {

				if ( storageFileUidLists == null ) {
						return 0;
				}

				List<String> storageFileUidList = CollectionUtils.arrayToList( storageFileUidLists );
				CollectionUtils.nullRemove( storageFileUidList );

				return deleteByStorageFileUidList( storageFileUidList );
		}

		public int deleteByCnd( FileCnd cnd ) {

				if ( StringUtils.isEmpty( cnd.getStorageFileUid() ) && CollectionUtils.isEmpty( cnd.getStorageFileUidList() )
										&& StringUtils.isEmpty( cnd.getTargetOid() ) && StringUtils.isEmpty( cnd.getContainerOid() )) {

						throw new BLORuntimeException( "삭제할 파라메터가 맞지 않습니다." );
				}

				String storageFileUid = cnd.getStorageFileUid();

				List<String> storageFileUidList = new ArrayList<>();
				if ( StringUtils.hasText( cnd.getTargetObject() ) && ( StringUtils.hasText( cnd.getTargetOid() ) || StringUtils.hasText( cnd.getContainerOid() )) ) {

						List<FileInfo> fileList = listAll( cnd );

						if( CollectionUtils.isNotEmpty( fileList ) ) {
								fileList.forEach( file -> {
										if ( StringUtils.hasText( file.getStorageFileUid() ) ) {
												storageFileUidList.add( file.getStorageFileUid() );
										}
								});
								cnd.setStorageFileUidList( storageFileUidList );
						}
				}

				// 스토리지 파일에서 해당 storage 파일카운트를 하나 낮춘다
				if ( StringUtils.hasText( storageFileUid ) ) {
						storageFileBLO.fileCountDecrease( storageFileUid );
				}

				// 스토리지 파일에서 해당 storage들의 파일카운트를 하나 낮춘다
				if ( CollectionUtils.isNotEmpty( cnd.getStorageFileUidList() ) ) {
						storageFileBLO.fileCountDecrease( cnd.getStorageFileUidList()  );
				}

				return fileDAO.deleteByCnd( cnd );
		}


		/**
		 * 사용자의 프로필을 가져옵니다.
		 * @param userList
		 * @param fileInfoList
		 */
		public void fillProfile( List<UserInfo> userList ) {

				if ( CollectionUtils.isEmpty( userList ) ) {
						return;
				}

				List<String> userOids = userList.stream().map( UserInfo::getOid ).collect( Collectors.toList() );

				List<FileInfo> fileInfoList = listByTarget( UserInfo.getObjectType(), userOids );

				userList.forEach( user -> fileInfoList.stream()
										.filter( file -> StringUtils.isNotEmpty(  file.getTargetOid() ) )
										.filter( file -> file.getTargetOid().equals( user.getOid() ) )
										.forEach( file -> user.setProfile( file ) )
								);

		}
		
		public int insertList( List<FileInfo> fileList, String targetObject, String targetOid ) {
				
				return insertList( fileList, targetObject, targetOid, "" );
		}
		
		public int insertList( List<FileInfo> fileList, String targetObject, String targetOid, String fileType ) {
				
				return insertList( fileList, targetObject, targetOid, fileType, "" );
		}


		public int insertList( List<FileInfo> fileList, String containerOid, String targetOid, String targetObject, String inputUser ) {

				int result = 0 ;

				for ( FileInfo fileInfo : fileList ) {

						assertThat( fileInfo.getStorageFileUid() ).as( "StorageFileUid가 없습니다." ).isNotEmpty();
						
						fileInfo.setContainerOid( containerOid );
						fileInfo.setOid( OIDGenerator.generateOID() );
						fileInfo.setTargetObject( targetObject );
						fileInfo.setTargetOid( targetOid );
						fileInfo.setInputUser( inputUser );
						fileInfo.setInputDate( LocalDateTime.now() );
						
						result += fileDAO.insert( fileInfo );
				}

				return result;
		}
		
		public int insertList( List<FileInfo> fileList, String targetObject, String targetOid, String fileType, String containerOid, String inputUser ) {
				
				if ( CollectionUtils.isEmpty( fileList ) ) {
						return 0;
				}
				
				int result = 0;
				
				for ( FileInfo fileInfo : fileList ) {
						
						assertThat( fileInfo.getStorageFileUid() ).as( "StorageFileUid가 없습니다." ).isNotEmpty();
						
						fileInfo.setOid( OIDGenerator.generateOID() );
						fileInfo.setTargetObject( targetObject );
						fileInfo.setTargetOid( targetOid );
						fileInfo.setContainerOid( StringUtils.isNotEmpty( containerOid ) ? containerOid : fileInfo.getContainerOid() );
						fileInfo.setInputUser( StringUtils.isNotEmpty( inputUser ) ? inputUser : fileInfo.getInputUser() );
						fileInfo.setFileType( StringUtils.isNotEmpty( fileType ) ? fileType : fileInfo.getFileType() );
						fileInfo.setInputDate( LocalDateTime.now() );
						
						result += fileDAO.insert( fileInfo );
				}
				
				return result;
		}

}

