package com.remarkablesoft.site.kccam.service.onetoone.onetoone.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.Entity;

import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.site.kccam.service.AmConstants;
import com.remarkablesoft.site.kccam.service.onetoone.config.vo.OneToOneConfigInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;



/**                                                            
 * 	                                                           
 * 		@주시스템			:	framework-web 	                   
 * 		@서브 시스템		:	onetoone - onetoone
 * 		@프로그램 ID		:	OneToOneInfo
 * 		@프로그램 개요		:	OneToOne 정보 객체
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
public class OneToOneInfo extends Entity {

    private static final long serialVersionUID = -3609214305096312025L;

    private String oid;														// OID
    private String title;													// 제목
    private String configOid;												// 문의 설정 OID
    private String inputUser;												// 문의자 OID
    private LocalDateTime inputDate = LocalDateTime.now();					// 문의일시

	private String descr;													// 내용
	private String answer;													// 답변 내용
	
	private String receiverEmail;											// 수신자 이메일
    private String customField1;											// 커스텀 필드1
    private String customField2;											// 커스텀 필드2
    private String customField3;											// 커스텀 필드3
		
    private String customField4;											// 커스텀 필드4
    private String customField5;											// 커스텀 필드5

	private UserInfo inputUserInfo;											// 등록자 정보
	private List<FileInfo> fileList;										// 첨부파일
	private List<OneToOneDetailInfo> oneToOneDetailList = new ArrayList<>();
	private List<OneToOneConfigInfo> receiverEmailList = new ArrayList<>();
	
	public static String getObjectType() {
		return AmConstants.OBJECT_AM_ONE_TO_ONE.getKey();
	}
	
	public void addOneToOneDetailList( OneToOneDetailInfo info ) {
		this.oneToOneDetailList.add( info );
	}
	
}
