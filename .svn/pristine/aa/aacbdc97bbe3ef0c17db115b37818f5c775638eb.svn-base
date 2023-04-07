package com.remarkablesoft.framework.service.storage.thumbnail.vo;

import java.util.ArrayList;
import java.util.List;

import com.remarkablesoft.framework.model.vo.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 *
 * <pre>
 * 설명 : 썸네일 파일 관계 테이블 검색 조건 객체입니다.
 * </pre>
 * @author hong
 * @since 2020. 7. 27.
 *
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
@EqualsAndHashCode( callSuper = true )
public class ThumbnailCnd extends Entity {


		/**
		 * 
		 */
		private static final long serialVersionUID = 808006545007587756L;
		
		private String targetObject;
		private String targetOid;
		private String storageFileUid;
		private String thumbnailType;
		private String useYn ;

		private List<String> targetOidList = new ArrayList<>();


}
