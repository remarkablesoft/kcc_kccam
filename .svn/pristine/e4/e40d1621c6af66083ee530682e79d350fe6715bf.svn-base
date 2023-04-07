package com.remarkablesoft.site.kccam.service.onetoone.config.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.util.CollectionUtils;
import com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo.OneToOneDetailInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.velocity.util.ArrayListWrapper;


/**
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	onetoone - config
 * 		@프로그램 ID		:	OneToOneConfigInfo
 * 		@프로그램 개요		:	OneToOneConfig 정보 객체
 * 	                                                           
 * 		@변경이력    		                                       
 *      ============================================================================
 *      1.0 2021. 02. 25. : 최원준 - 신규생성
 *      ============================================================================
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
public class OneToOneConfigInfo extends Entity {

    private static final long serialVersionUID = -888566121385658224L;

    public static final String CONFIG_TYPE_QUESTION = "AMCTQUST";			// 설정 타입 문의
    public static final String CONFIG_TYPE_DETAIL_QUESTION = "AMCTDQST";	// 설정 타입 추가문의
    public static final String CONFIG_TYPE_RECEIVER_EMAIL = "AMCTRCEM";		// 설정 타입 수신자 이메일
    
    private String oid;					// OID
	private String lang; 				// 언어
	private String parentOid;			// 1대1 문의 부모 설정정보 OID
	private String configType;			// 설정 타입
	private String configQuestion;		// 설정 질문
	private String configAnswer;		// 설정 답변
	
	private String configReceiverEmail;	// 설정 이메일
	private String useYn;              // 사용 여부

    private List<OneToOneConfigInfo> detailConfigQuestionList = new ArrayList<>();  // 추가 문의 리스트
    private List<OneToOneConfigInfo> receiverEmailList = new ArrayList<>();         // 담당자 이메일 리스트

    public void addDetailConfigInfo( OneToOneConfigInfo info ) {
            this.detailConfigQuestionList.add( info );
    }
    public void addEmailConfigInfo( OneToOneConfigInfo info ) {
            this.receiverEmailList.add( info );
    }

}
