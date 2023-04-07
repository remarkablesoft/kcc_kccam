package com.remarkablesoft.framework.service.storage.thumbnail.vo;

import java.time.LocalDateTime;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 썸네일 파일 관계 테이블
 * @author HONG
 * @since 2020. 7. 27.
 *
 */
@Getter
@Setter
@Accessors( chain = true )
@ToString
public class ThumbnailInfo extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1961405735211124020L;

	private String oid ;
	private String targetObject ;
	private String targetOid ;
	private String storageFileUid ;
	private String thumbnailType ;
	
	private String thumbnailBase64 ;
	private String useYn = SystemConstants.FLAG_YES;	
	private String inputUser ;
	private LocalDateTime inputDate = LocalDateTime.now();


}
