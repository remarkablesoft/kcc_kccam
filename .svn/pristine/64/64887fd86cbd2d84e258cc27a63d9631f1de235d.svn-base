package com.remarkablesoft.site.kccam.service.onetoone.config.model.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.OneToOne;

import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.BLO;
import com.remarkablesoft.framework.module.oid.OIDGenerator;
import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.util.AssertUtils;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigInfo;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigCnd;
import com.remarkablesoft.framework.util.StringUtils;



/**                                                            
 * 	                                                           
 * 		@주시스템		:	framework-web
 * 		@서브 시스템		:	onetoone - config
 * 		@프로그램 ID		:	OneToOneConfigBLO
 * 		@프로그램 개요	:	1대1 설정 BLO
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */


@BLO
public class OneToOneConfigBLO {

    @Autowired
    protected OneToOneConfigDAO oneToOneConfigDAO;

    @Autowired
    protected OneToOneConfigBLO _self;

    /**
    * 1대1문의 설정 정보를 저장합니다.
    *
    * @param info
    * @return OneToOneConfigInfo
     * @author 최원준
     */
    public OneToOneConfigInfo insert( OneToOneConfigInfo info ) {

        if ( StringUtils.isEmpty( info.getOid() ) ) {
            info.setOid( OIDGenerator.generateOID() );
        }
        oneToOneConfigDAO.insert( info );

        return info;
    }

//    /**

    /**
    *  1대1 문의 설정 정보를 수정합니다.
    *
    * @param info
    * @return OneToOneConfig
    * @author 남윤재
    */
    public OneToOneConfigInfo update( OneToOneConfigInfo info ) {

        if ( info == null || StringUtils.isEmpty( info.getOid() ) ) {
        		return null;
        }
        oneToOneConfigDAO.update( info );
        return info;
    }


    /**
    *  1대1 문의 설정 정보를 삭제합니다.
    *
    * @param oid
    * @return int
    * @author 남윤재
    */
    public int delete( String oid ) {

        if ( StringUtils.isEmpty( oid ) ) {
        		return 0;
        }

        return oneToOneConfigDAO.delete( oid );
    }

    /**
    * 1대1 설정정보를 가져옵니다.
    *
    * @param cnd
    * @return OneToOneConfigInfo
    * @author 최원준
    */
    public OneToOneConfigInfo get( OneToOneConfigCnd cnd ) {

        if ( cnd == null ) {
        		return null;
        }
        return oneToOneConfigDAO.get( cnd );
    }

    /**
    * 1대1 설정정보 리스트를 가져옵니다.
    *
    * @param cnd
    * @return List<OneToOneConfigInfo>
    * @author 최원준
    */
    public List<OneToOneConfigInfo> listAll( OneToOneConfigCnd cnd ) {

        List<OneToOneConfigInfo> list = oneToOneConfigDAO.listAll( cnd );

        if ( CollectionUtils.isEmpty( list ) ) {
            return null;
        }

        // 하위 리스트들을 찾아서 채운다.
        List<OneToOneConfigInfo> result = fillChildConfig( list );

        if( CollectionUtils.isEmpty( result ) ) {
            return null;
        }
        return result;
    }
    /**
     *  1대 1 설정 정보에 하위 리스트를 채워줍니다.
     *
     *  @param  list<OneToOneConfigInfo>
     *  @author 남윤재
     */

    private List<OneToOneConfigInfo> fillChildConfig(List<OneToOneConfigInfo> list ) {

        if( CollectionUtils.isEmpty( list ) ) {
            return null;
        }
        // 타입 : 최상위 질문 리스트
        List<OneToOneConfigInfo> parentList =
                    list.stream().filter( head -> head.getConfigType().equals( OneToOneConfigInfo.CONFIG_TYPE_QUESTION ) )
                                .collect( Collectors.toList());

        // 타입 : 세부 질문 리스트
        List<OneToOneConfigInfo> childList =
                    list.stream().filter( child -> child.getConfigType().equals( OneToOneConfigInfo.CONFIG_TYPE_DETAIL_QUESTION ) )
                                .collect( Collectors.toList());

        // 타입 : 담당자 이메일
        List<OneToOneConfigInfo> receiverList =
                    list.stream().filter( receiver -> receiver.getConfigType().equals( OneToOneConfigInfo.CONFIG_TYPE_RECEIVER_EMAIL ) )
                                .collect( Collectors.toList());
        
        // 부모 질문 리스트에서 oid 와
        // 자식 질문 리스트의 parentOid 가 일치하는 목록들을 부모의 detailConfigQuestionList 에 추가
        parentList.forEach( parentInfo -> {

            List<OneToOneConfigInfo> filteredChildList =
                        childList.stream().filter( child -> child.getParentOid().equals( parentInfo.getOid()) ).collect(Collectors.toList());
            parentInfo.setDetailConfigQuestionList( filteredChildList );

            List<OneToOneConfigInfo> filteredReceiverList =
                        receiverList.stream().filter( receiver -> receiver.getParentOid().equals( parentInfo.getOid()) ).collect(Collectors.toList());
            parentInfo.setReceiverEmailList( filteredReceiverList );
        });
        parentList = parentList.stream().sorted( Comparator.comparing( OneToOneConfigInfo::getLang ) ).collect( Collectors.toList());
        return parentList;
    }

    /**
     *  1대1 설정 정보를 신규 저장하거나 수정합니다.
     *
     *  @param  info
     *  @return info
     *  @author 남윤재
     */
    public OneToOneConfigInfo insertOrUpdate(OneToOneConfigInfo info) {

        OneToOneConfigInfo configInfo = null;

        if( StringUtils.isEmpty( info.getOid() ) ) {
            configInfo = _self.insert( info );
        }
        else {
            configInfo = _self.update( info );
        }

        return configInfo;
    }
    /**
     *  1대1 설정 정보 사용 유무를 변경합니다.
     *
     *  @param
     *  @return
     *  @author 남윤재
     */
    public int deleteFlagUpdate( OneToOneConfigInfo info ) {
        if ( StringUtils.isEmpty( info.getOid() ) ) {
            return 0;
        }
        return oneToOneConfigDAO.deleteFlagUpdate( info );
    }
}
