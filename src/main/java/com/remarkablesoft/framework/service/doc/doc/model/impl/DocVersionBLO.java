package com.remarkablesoft.framework.service.doc.doc.model.impl;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.common.constants.StatusType;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.doc.doc.vo.DocCnd;
import com.remarkablesoft.framework.service.doc.doc.vo.DocInfo;
import com.remarkablesoft.framework.service.doc.doc.vo.DocVersionInfo;
import com.remarkablesoft.framework.service.storage.file.model.impl.FileBLO;
import com.remarkablesoft.framework.service.storage.file.vo.FileCnd;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 	                                                           
 * 		@주시스템		:	framework-web
 * 		@서브 시스템		:	doc - doc
 * 		@프로그램 ID		:	DocBLO
 * 		@프로그램 개요	:	문서 버전 BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 03. 11. : 최원준 - 신규생성
 *      ============================================================================
 */


@BLO
public class DocVersionBLO {

    @Autowired
    protected DocVersionDAO docVersionDAO;

    @Autowired
    protected FileBLO fileBLO;
    
    /**
    * 문서 버전정보를 저장합니다.
    *
    * @param info
    * @return DocVersionInfo
    * @author 최원준
    */
    public DocVersionInfo insert( DocVersionInfo info ) {

        if ( StringUtils.isEmpty( info.getOid() ) ) {
            info.setOid( OIDGenerator.generateOID() );
        }
        
        if ( StringUtils.isEmpty( info.getStatusTypeFlag() ) ) {
            info.setStatusTypeFlag( StatusType.RELEASE.getKey() );
        }

        saveFile( info );
        
        return docVersionDAO.insert( info ) > 0 ? info : null;
    }

    /**
    * 문서 정보로 문서 버전 정보를 저장합니다.
    *
    * @param docInfo
    * @return DocVersionInfo
    * @author 최원준
    */
    public DocVersionInfo insert( DocInfo docInfo ) {
    
        if ( StringUtils.isEmpty( docInfo.getOid() ) ) {
            return null;
        }
        DocVersionInfo docVersionInfo = docInfo.getCurrentDocVersionInfo();
        if ( docVersionInfo == null ) {
            docVersionInfo = SystemFactory.getDocVersionInfo();
        }
        
        docVersionInfo.setDocOid( docInfo.getOid() ).setInputUser( docInfo.getInputUser() );
        
        if ( StringUtils.hasText( docInfo.getModUser() ) ) {
            docVersionInfo.setInputUser( docInfo.getModUser() );
        }
        
        return insert( docVersionInfo );
    }
    
    /**
    * 문서 버전정보를 수정합니다.
    *
    * @param info
    * @return DocVersionInfo
    * @author 최원준
    */
    public DocVersionInfo update( DocVersionInfo info ) {

        if ( info == null
            || ( StringUtils.isEmpty( info.getOid() ) && StringUtils.isEmpty( info.getDocOid() ) ) ) {
        	return null;
        }


    	info.setModDate( LocalDateTime.now() );
        
        saveFile( info );

        return docVersionDAO.update( info ) > 0 ? info : null;
    }

    /**
    * 문서 버전정보를 문서정보를 통해 수정합니다.
    *
    * @param docInfo
    * @return DocVersionInfo
    * @author 최원준
    */
    public DocVersionInfo update( DocInfo docInfo ) {
    
        if ( docInfo == null || StringUtils.isEmpty( docInfo.getOid() ) ) {
            return null;
        }
        
        DocVersionInfo info = docInfo.getCurrentDocVersionInfo();
        info.setModUser( docInfo.getModUser() );
        info.setModDate( docInfo.getModDate() );
        
        return update( info );
    }
    
    /**
    * 문서 버전정보를 삭제합니다.
    *
    * @param cnd
    * @return int
    * @author 최원준
    */
    public int delete( DocCnd cnd ) {

        if ( StringUtils.isEmpty( cnd.getOid() ) && StringUtils.isEmpty( cnd.getDocOid() ) ) {
        		return 0;
        }
        
        // 관련파일을 삭제합니다.
//        fileBLO.deleteByTarget( cnd.getDocOid(), DocInfo.getObjectType() );
        // targetObject가 DocInfo, containerOid가 DocInfo의 Oid인 파일들 일괄 삭제
        fileBLO.deleteByCnd( new FileCnd().setContainerOid( cnd.getDocOid() ).setTargetObject( DocInfo.getObjectType() ) );

        return docVersionDAO.delete( cnd );
    }

    /**
    * 문서 버전정보를 가져옵니다.
    *
    * @param cnd
    * @return DocVersionInfo
    * @author 최원준
    */
    public DocVersionInfo get( DocCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }
    
        DocVersionInfo info = docVersionDAO.get( cnd );
        
        if ( cnd.isFillFile() ) {
            fillFile( info );
        }
        
        return info;
    }

    /**
    * 문서 버전 페이지리스트를 가져옵니다.
    *
    * @param cnd
    * @return PageList<DocVersionInfo>
    * @author 최원준
    */
    public PageList<DocVersionInfo> list( DocCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }
        
        PageList<DocVersionInfo> list = docVersionDAO.list( cnd );
        if ( cnd.isFillFile() ) {
            fillFile( list );
        }

        return list;
    }

    /**
    * 문서 버전 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<DocVersionInfo>
    * @author 최원준
    */
    public List<DocVersionInfo> listAll( DocCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }
        
        List<DocVersionInfo> list = docVersionDAO.listAll( cnd );
        if ( cnd.isFillFile() ) {
            fillFile( list );
        }

        return list;
    }

    /**
    * 문서 버전정보 유무를 확인힙니다.
    *
    * @param oid
    * @return boolean
    * @author 최원준
    */
    public boolean exist( String oid ) {

        if ( StringUtils.isEmpty( oid ) ) {
        		return false;
        }

        return docVersionDAO.exist( oid );
    }
    
    /**
    * 문서의 파일 정보를 저장합니다.
    *
    * @param info
    * @return FileInfo
    * @author 최원준
    */
    private void saveFile( DocVersionInfo info ) {

        // 삭제 후 등록합니다.
        fileBLO.deleteByTarget( info.getOid(), DocInfo.getObjectType() );
        
        // 직접 등록한 파일일 경우 파일 등록
        if ( info.getDocFileInfo() != null && StringUtils.isNotEmpty( info.getDocFileInfo().getStorageFileUid() ) ) {
        	// 첨부문서 등록
            info.getDocFileInfo().setOid( OIDGenerator.generateOID() );
            info.getDocFileInfo().setFileType( DocInfo.FILE_TYPE_GENERAL );        
            FileInfo insertDoc = fileBLO.insert( info.getDocFileInfo(), DocInfo.getObjectType(), info.getOid(), info.getDocOid() );
            
            if ( insertDoc != null ) {
            	info.setDocFileInfo( insertDoc );
            }
        }        
        
        // 대표 이미지 등록
        if ( info.getIconFileInfo() != null && StringUtils.isNotEmpty( info.getIconFileInfo().getStorageFileUid() ) ) {  	
            info.getIconFileInfo().setOid( OIDGenerator.generateOID() );
            info.getIconFileInfo().setFileType( DocInfo.FILE_TYPE_ICON );           
            FileInfo insertIcon = fileBLO.insert( info.getIconFileInfo(), DocInfo.getObjectType(), info.getOid(), info.getDocOid() );
            
            if ( insertIcon != null ) {
            	info.setIconFileInfo( insertIcon );
            }
        }
    }
    
    /**
    * 문서 버정정보의 파일을 채워줍니다.
    *
    * @param info
    * @author 최원준
    */
    private void fillFile( DocVersionInfo info ) {
    
        if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
            return;
        }
    
        // info.setDocFileInfo(  fileBLO.getByTarget( info.getOid(), DocInfo.getObjectType() ) );
        
        List<FileInfo> list = fileBLO.listByTarget( DocInfo.getObjectType() , info.getOid() );
        
        // 일반 첨부파일 세팅
    	list.stream().filter( file -> file.getFileType().equals( DocInfo.FILE_TYPE_GENERAL ) )
    				.forEach( file -> info.setDocFileInfo( file ) );
    	// 대표 이미지 파일 세팅
    	list.stream().filter( file -> file.getFileType().equals( DocInfo.FILE_TYPE_ICON ) )
    				.forEach( file -> info.setIconFileInfo( file ) );
    }

    /**
    * 문서 버전 리스트의 파일을 채워줍니다.
    *
    * @param list
    * @author 최원준
    */
    private void fillFile( List<DocVersionInfo> list ) {
    
        if ( CollectionUtils.isEmpty( list ) ) {
            return;
        }
        
        List<String> targetOidList = list.stream().map( DocVersionInfo::getOid ).collect( Collectors.toList() );
        
        FileCnd fileCnd = new FileCnd();
        fileCnd.setTargetOidList( targetOidList );
        fileCnd.setTargetObject( DocInfo.getObjectType() );
        List<FileInfo> fileList = fileBLO.listAll( fileCnd );
        
        if ( CollectionUtils.isEmpty( fileList ) ) {
            return;
        }
        
        list.forEach( docVersion -> {
        	fileList.stream().filter( file -> docVersion.getOid().equals( file.getTargetOid() ) )
        					 .filter( file -> DocInfo.FILE_TYPE_GENERAL.equals( file.getFileType() ) )
                             .forEach( file -> docVersion.setDocFileInfo( file ) );
        	
        	fileList.stream().filter( file -> docVersion.getOid().equals( file.getTargetOid() ) )
        					.filter( file -> DocInfo.FILE_TYPE_ICON.equals( file.getFileType() ) )
            				.forEach( file -> docVersion.setIconFileInfo( file ) );
        });
        
    }

}
