package com.remarkablesoft.framework.service.board.posting.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.org.user.vo.UserInfo;
import com.remarkablesoft.framework.service.storage.file.vo.FileInfo;
import com.remarkablesoft.framework.service.storage.thumbnail.vo.ThumbnailInfo;
import com.remarkablesoft.framework.util.CollectionUtils;

import com.remarkablesoft.site.kccam.service.product.vo.ProductInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 설명 : Posting 객체. Board 객체의 키를 부모로 가지고 있는다.
 * </pre>
 *
 * @author James
 * @since 2015. 4. 13.
 *
 */

@Getter
@Setter
@Accessors( chain = true )
@ToString
public class PostingInfo extends Entity {

		public static final int NOTICE_LEVEL_NO = 0;	// 공지 아님.
		public static final int NOTICE_LEVEL_NOTI = 1;	// 공지.
		public static final int NOTICE_LEVEL_MUST = 2;	// 필독.
		
		public static final String SUB_PICK = "pickInfo";	// 자신의 하위 채택된 포스팅
		
		public static final String FILE_TYPE_GENERAL = "FWPO00GL";								// 일반 첨부파일 리스트
		public static final String FILE_TYPE_CONTENTS = "FWEB00CO";								// 본문 컨텐츠의 파일 리스트
		public static final String FILE_TYPE_ICON = "FWPO00IC";									// 대표 이미지 아이콘

		/**
		 *
		 */
		private static final long serialVersionUID = -6967691532403959228L;

		private String oid;											// OID
		private String title;										// 제목
		private String contents;									// 내용
		private String lang;										// 언어
		private String boardOid;
		private int noticeLevel;									// 공지 레벨 1-일반공지, 2-필수

		private UserInfo inputUserInfo = SystemFactory.getUserInfo();	// 등록자 - inputUserInfoList로 해당 inputUserInfo를 만든다

		private List<PostingInfo> currentPosting = null;			// 현재글의 대한 정보	
		private PostingInfo prevPosting = null;						// 이전글 정보
		private PostingInfo nextPosting = null;						// 다음글 정보

		private String inputUser;									// 등록자
		private String modUser;										// 수정자
		private String delUser;										// 삭제자

		private LocalDateTime inputDate = LocalDateTime.now();		// 등록일
		private LocalDateTime modDate = null;						// 수정일
		private LocalDateTime delDate = null;						// 삭제일
		private String tempYn = SystemConstants.FLAG_NO;			// 임시저장 여부

		private LocalDateTime fromDate = null;						// 공지 시작일
		private LocalDateTime toDate = null;						// 공지 종료일
		private String inputUserInfoList;							// 등록자 정보 리스트

		private String threadOid; 									// 스레드 OID - 답변시 사용
		private String threadFullPathOid;							// 스레드 전체 OID Path - 답변 삭제시 사용
		private String threadParentOid;								// 스레드 부모 OID - 답변시 사용
		private int threadDepth = 0; 								// 스레드 단계 - 답변시 사용
		private int threadOrderNo = 0;	 							// 스레드 정렬순서 - 답변시 사용

		private int replyCount = 0;	 								// 답글 카운트 -  PostingBLO 자체로 처리
		private int commentCount = 0;								// 코멘트 카운트 - CommentBLO 처리
		private int recommendCount = 0;								// 추천 카운트   - 아직 없음
		private int likeCount = 0;	 								// 좋아요 카운트 - PostingLikeHistBLO 처리
		private int viewCount = 0;									// 조회수 카운트 - PostingViewAuditBLO에서는 이력처리.
		

		private String anonymousPwd;								// 비밀글 비밀번호
		private String pickYn = SystemConstants.FLAG_NO;			// 채택여부 Y,N ex) 질문Q&A 베스트답변 채택

		private String customField1;								// 커스텀 필드1
		private String customField2;								// 커스텀 필드2
		private String customField3;								// 커스텀 필드3
		private String customField4;								// 커스텀 필드4
		private String customField5;								// 커스텀 필드5

		private String readYnVC = SystemConstants.FLAG_NO;			// 로그인한 사용자가 읽었는지 여부
		private String likeYnVC = SystemConstants.FLAG_NO;			// 로그인한 사용자가 좋아요 했는지 여부
		private int numberVC = 0; 									// 순번표시용

		private String newYnVC = SystemConstants.FLAG_NO;			// 시스템환경변수(디폴트3일)에 따라 New인지를 나타낼때 사용.

		private List<String> receiveUserOidList = new ArrayList<>();
		private List<String> tagNameList = new ArrayList<>();							// tag name리스트
		private int receiveUserCountVC = 0;

		
		private List<FileInfo> fileList = new LinkedList<FileInfo>();					// 첨부파일 리스트
		private List<FileInfo> contentsFileList = new LinkedList<FileInfo>();			// 본문내 파일리스트
		private List<PostingInfo> langPostingList = new ArrayList<>();
		
		private List<ThumbnailInfo> thumnnailList = new LinkedList<ThumbnailInfo>(); 	// 썸네일 리스트
		private Map<String, Object> objectMap = new HashMap<String, Object>();			// 객체 MAP - 포스팅에 부가적인 정보를 넣고 싶을 때 사용		


		private ProductInfo productInfo;							// 관련 제품 정보
		private FileInfo iconFile;									// 대표 이미지 아이콘
		
		public void addThumnnail( ThumbnailInfo thumbnailInfo ) {
				this.thumnnailList.add( thumbnailInfo );
		}

		/**
		 * 첨부파일 추가
		 * 
		 * @author james
		 * @param info
		 */
		public void addFile( FileInfo info ) {
				info.setTargetObject( getObjectType() );
				info.setTargetOid( oid );
				info.setFileType( FILE_TYPE_GENERAL );
				
				if ( CollectionUtils.isEmpty( fileList )) {
						fileList = new LinkedList<FileInfo>();
				}
				fileList.add( info );
		}
		
		/**
		 * 본문내 파일 추가
		 * 
		 * @author james
		 * @param info
		 */
		public void addContentsFile( FileInfo info ) {
				info.setTargetObject( getObjectType() );
				info.setTargetOid( oid );
				info.setFileType( FILE_TYPE_CONTENTS );
				
				if ( CollectionUtils.isEmpty( contentsFileList )) {
						contentsFileList = new LinkedList<FileInfo>();
				}
				contentsFileList.add( info );
		}
		
		
		public void addObjectMap( String key, Object obj) {
				this.objectMap.put( key, obj );
		}

		public void removeFile( FileInfo info ) {
				fileList.remove( info );
		}

		/**
		 * 자신의 타입을 반환.
		 *
		 * @return
		 */
		public static String getObjectType() {
				return SystemConstants.OBJECT_FW_TYPE_POSTING.getKey();
		}

}
