package com.remarkablesoft.framework.service.board.popup.model.impl;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.remarkablesoft.framework.web.util.AutheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.board.contents.model.impl.ContentsBLO;
import com.remarkablesoft.framework.service.board.contents.vo.ContentsInfo;
import com.remarkablesoft.framework.service.board.popup.vo.PopupCnd;
import com.remarkablesoft.framework.service.board.popup.vo.PopupInfo;
import com.remarkablesoft.framework.service.org.user.model.impl.UserBLO;
import com.remarkablesoft.framework.service.org.user.vo.UserCnd;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.file.model.impl.FileBLO;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.framework.util.StringUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board - popup
 * 		@프로그램 ID		:	PopupBLO
 * 		@프로그램 개요		:	Popup BLO
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 03. 05. : 안희홍 - 신규생성
 *      ============================================================================
 */
@BLO
public class PopupBLO {

	@Autowired
	protected PopupBLO _self;

    @Autowired
    protected PopupDAO popupDAO;

    @Autowired
    protected ContentsBLO contentsBLO;

    @Autowired
    protected FileBLO fileBLO;

    @Autowired
	protected UserBLO userBLO;


	@CacheEvict( value = "popup", allEntries = true )
    public PopupInfo insertOrUpdate( PopupInfo info ) {

    	if ( StringUtils.isEmpty( info.getOid() ) ) {
    		return _self.insert( info );
    	}
    	else {

    		return _self.update( info );
    	}

    }

	@CacheEvict( value = "popup", allEntries = true )
    public PopupInfo insert( PopupInfo info ) {

        if ( StringUtils.isEmpty( info.getOid() ) ) {
        		info.setOid( OIDGenerator.generateOID() );
        }

        if ( StringUtils.isEmpty( info.getInputUser() ) ) {
        		info.setInputUser( AutheUtils.getLoginUserOid() );
		}

		if ( StringUtils.isNotEmpty( info.getContents() ) ) {

			contentsBLO.insert( convertContentsInfo( info ) );
		}

		if ( CollectionUtils.isNotEmpty( info.getFileList() ) ) {
				
				fileBLO.insertList( info.getFileList(), PopupInfo.getObjectType(), info.getOid() );
		}

		popupDAO.insert( info );

        return info;
    }
		
		@CacheEvict( value = "popup", allEntries = true )
    public PopupInfo update( PopupInfo info ) {

		if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
				return null;
		}

		// 처음에 insert할때, 에디터 팝업이었다면?
		if ( StringUtils.isNotEmpty( info.getContents() ) ) {

				// 계속 에디터 팝업이라면?
				if ( PopupInfo.POPUP_CONTENTS_TYPE_FLAG_EDITOR.equals( info.getPopupContentsTypeFlag() ) ) {

						// 처음에 에디터 팝업이 아니었다면, contents가 없기 때문에 insertOrUpdate로 변경
						contentsBLO.save( convertContentsInfo( info ) );
				}
				// 이미지 팝업으로 바꿨다면 ? contents 삭제해주기
				else {

						contentsBLO.deleteByTarget( info.getOid(), PopupInfo.getObjectType() );
				}

		}

		// 기존의 파일은 삭제 후 다시 입력
		fileBLO.deleteAndInsert( info.getFileList(), PopupInfo.getObjectType(), info.getOid(), "", info.getPartOid(), info.getInputUser() );

		popupDAO.update( info );

		return info;
    }

	@CacheEvict( value = "popup", allEntries = true )
    public int delete( String oid ) {

        if ( StringUtils.isEmpty( oid ) ) {
        		return 0;
        }

		fileBLO.deleteByTarget( oid, PopupInfo.getObjectType() );
		contentsBLO.deleteByTarget( oid, PopupInfo.getObjectType() );

        return popupDAO.delete( oid );
    }

	@Cacheable( value = "popup" , keyGenerator = "cacheKeyGenerator")
    public PopupInfo get( PopupCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }

        PopupInfo popupInfo = popupDAO.get( cnd );
		List<PopupInfo> list = new ArrayList<>();
		list.add( popupInfo );

		fillFileList( list );

		String contents = contentsBLO.getContents( convertContentsInfo( popupInfo ) );

		if ( StringUtils.isNotEmpty( contents ) ) {

			popupInfo.setContents( contents );
		}

        return popupInfo;
    }

	@Cacheable( value = "popup" , keyGenerator = "cacheKeyGenerator")
	public PopupInfo getWithPrevAndNext( PopupCnd cnd ) {

		if ( StringUtils.isEmpty( cnd.getOid() ) ) {
				return null;
		}

		PopupInfo popupInfo = get( cnd );

		if ( popupInfo == null) {
				return null;
		}

		popupInfo.setPrevPopupInfo( viewPrev( cnd ) );
		popupInfo.setNextPopupInfo( viewNext( cnd ) );

		return popupInfo;
	}

	@Cacheable( value = "popup" , keyGenerator = "cacheKeyGenerator")
	public PopupInfo viewPrev( PopupCnd cnd ) {
			return popupDAO.getPrev( cnd );
	}

	@Cacheable( value = "popup" , keyGenerator = "cacheKeyGenerator")
	public PopupInfo viewNext( PopupCnd cnd ) {
			return popupDAO.getNext( cnd );
	}

	@Cacheable( value = "popup" , keyGenerator = "cacheKeyGenerator")
    public PageList<PopupInfo> list( PopupCnd cnd ) {

		PageList<PopupInfo> list = popupDAO.list( cnd );

        return (PageList<PopupInfo>) commonList( list, cnd );
    }


	@Cacheable( value = "popup" , keyGenerator = "cacheKeyGenerator")
    public List<PopupInfo> listAll( PopupCnd cnd ) {

        List<PopupInfo> list = popupDAO.listAll( cnd );

        return commonList( list, cnd );
    }

	@Cacheable( value = "popup" , keyGenerator = "cacheKeyGenerator")
    public boolean exist( String oid ) {

        if ( StringUtils.isEmpty( oid ) ) {
        		return false;
        }

        return popupDAO.exist( oid );
    }

	@CacheEvict( value = "popup", allEntries = true )
    public PopupInfo updateUseYn( PopupInfo info ) {

    	popupDAO.updateUseYn( info );
        return info;
    }

    /**
     * useYn 사용중('Y')이고, 오늘 날짜가 포함된 리스트만 가져오기
     *
     * @param
     * @author sirena
     * @작성일 2021-09-07
    **/
    @Cacheable( value="popup", keyGenerator = "cacheKeyGenerator" )
	public List<PopupInfo> operationList( PopupCnd cnd ) {

		List<PopupInfo> list = popupDAO.listAll( cnd.setUseYn( SystemConstants.USE_Y.getKey() ).setToday( LocalDate.now() ) );

		return commonList( list, cnd );
	}

	/**
	 * 노출 기간이 지나면, 팝업 노출 여부를 비노출로 변경합니다.
	 * 배치로직에서 사용
	 *
	 * @param
	 * @author sirena
	 * @작성일 2022-01-20
	 **/
	@CacheEvict( value = "popup", allEntries = true )
	public int updateNotUseStatusTypeFlagBatch( LocalDate now ) {

			int result = 0;

			List<PopupInfo> list = popupDAO.listAll( new PopupCnd().setUseYn( SystemConstants.USE_Y.getKey() ).setExpirationDate( now ) );

			if ( CollectionUtils.isEmpty( list ) ) {
					return result;
			}

			for ( PopupInfo popup : list ) {

					PopupInfo popupInfo = SystemFactory.getPopupInfo();

					PopupInfo resultInfo = updateUseYn( popupInfo.setOid( popup.getOid() ).setUseYn( SystemConstants.USE_N.getKey() ) );

					if ( resultInfo != null ) {
							result++;
					}
			}

			return result;
	}


	protected List<PopupInfo> commonList( List<PopupInfo> list, PopupCnd cnd ) {

		if ( CollectionUtils.isEmpty( list ) ) {
				return Collections.emptyList();
		}

        fillFileList( list );

        fillContentsList( list );

        fillInputUserName( list );

		return list;
	}

    protected ContentsInfo convertContentsInfo( PopupInfo popupInfo ) {

		ContentsInfo info = SystemFactory.getContentsInfo();

		info.setContents( popupInfo.getContents() );
		info.setTargetObject( PopupInfo.getObjectType() );
		info.setTargetOid( popupInfo.getOid() );
		info.setInputUser( popupInfo.getInputUser() );
		info.setContainerOid( popupInfo.getPartOid());

		// get에서 사용하는경우 targetOid와 targetObject만으로 contentsInfo객체를 만드는 경우가 있어 추가
		if ( StringUtils.hasText( popupInfo.getContents() ) ) {
				info.setContentsSize( popupInfo.getContents().getBytes().length );
		}

		return info;
	}

    protected void fillFileList( List<PopupInfo> list ) {

    	List<String> targetOidList = list.stream().map( PopupInfo :: getOid ).collect( Collectors.toList() );

    	List<FileInfo> fileList = fileBLO.listByTarget( PopupInfo.getObjectType() , targetOidList, "" );

    	list.forEach( popup -> fileList.stream().filter( file -> popup.getOid().equals( file.getTargetOid() ) )
    											.forEach( file -> popup.addFile( file ) ) );

    }

    protected void fillContentsList( List<PopupInfo> list ) {

    	List<String> targetOidList = list.stream().map( PopupInfo :: getOid ).collect( Collectors.toList() );

    	List<ContentsInfo> contentsList = contentsBLO.list( targetOidList, PopupInfo.getObjectType() );

    	list.forEach( popup -> contentsList.stream().filter( contents -> popup.getOid().equals( contents.getTargetOid() ) )
													.forEach( contents -> popup.setContents( contents.getContents() ) ) );
    }

	/**
	 * inputUser로 이름을 찾아줍니다.
	 *
	 * @param list
	 * @author sirena
	 * @작성일 2021-09-07
	**/
	protected void fillInputUserName( List<PopupInfo> list ) {

		List<String> userOidList = list.stream().map( PopupInfo::getInputUser ).distinct().collect( Collectors.toList() );

		if ( CollectionUtils.isEmpty( userOidList ) ) {
			return;
		}

		List<UserInfo> userInfoList = userBLO.listAll( new UserCnd().setUserOidList( userOidList ) );

		if ( CollectionUtils.isEmpty( userInfoList ) ) {
			return;
		}

		list.forEach( popup -> userInfoList.stream()
									   .filter( user -> popup.getInputUser() != null )
										.filter( user -> popup.getInputUser().equals( user.getOid() ) )
										.forEach( user -> popup.setExtraInfoMap( "inputUserName", user.getName() ) ) );
	}

}
