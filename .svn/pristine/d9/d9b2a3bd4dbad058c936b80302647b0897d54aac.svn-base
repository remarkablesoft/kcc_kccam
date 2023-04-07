<!-- 댓글 관련 컴포넌트 -->
<template>
	 <!-- 댓글 영역 -->
	<div class="comment-area">
	    <div class="common-comment-wrap posting-comment-wrap no-user-img"><!-- 사용자 이미지 사용안할 시 no-user-img -->
	        <p class="tit">댓글<span class="comment-cnt">({{listCount}})</span></p>
	        <!-- 댓글 그룹 -->
	        <div class="comment-group">
	            <!-- 댓글 목록 -->
	            <div class="comment-list-wrap">

				<!-- no-data -->
				<div class="no-data" v-if="1 > listCount">
				   <i class="material-icons">error_outline</i>
				   <p>아직 댓글이 없습니다.</p>
				</div>
                 <!--
	                - 댓글 하나 :: comment-list-row 반복(수정일 경우만 다름. / 클래스로 색, 여백, 답글 아이콘 유무 구분)
	                - 댓글/답글 입력창 :: comment-input-wrap 구조 동일
                -->
					<div v-for="(comment, index) in commentList">
		                <!-- 댓글 하나(comment-list-row) :: 내가 댄 닷글 my-comment 클래스 추가 / 답글(대댓글) re-comment 클래스 추가 -->
		                <div class="comment-list-row" :class="getRowClass(comment)" :style="getPadding(comment)">
		                    <!-- 댓글 박스 -->
		                    <div class="comment-box">
		                        <div class="user-info-wrap">
		                            <div class="user-wrap">
		                                <img src="http://placehold.it/40x40" class="user-img"/>
		                                <span class="user-name" v-text="comment.userInfo.name"><!-- user001 --></span>
		                            </div>
		                            <div class="date-wrap">
		                                <span class="date" v-text="getInputDateString( comment )"><!-- 2019. 11. 20 --></span>
		                            </div>
		                            <div class="btn-wrap">
		                                <button type="button" class="btn btn-reply" @click="createCommentInput(comment.oid)" :disabled="!isLogin">답글 달기</button>
		                            </div>
		                            <!-- 본인이 쓴 글에만 보이기 -->
		                            <div class="btn-edit-wrap" ><!-- v-show="isWriter( comment.inputUser )"  -->
		                                <button type="button" class="btn btn-edit-comment" @click="modify( comment )">수정</button>
		                                <button type="button" class="btn btn-delete-comment" @click="deleteComment(comment.oid,index)">삭제</button>
		                            </div>
		                        </div>
		                        <div class="user-comment-wrap">
		                            <div class="contents-wrap">
		                                <p class="" v-text="comment.contents"><!-- 내가 쓴 댓글 내용입니다. 내가 쓴 댓글 내용입니다. 내가 쓴 댓글 내용입니다. --></p>
		                            </div>
		                        </div>
		                    </div>
		                </div>

		                <!-- 답글 입력창 -->
		                <div class="comment-input-wrap" v-show="entering===comment.oid">
		                    <div class="comment-row">
		                        <div class="comment-input">
		                            <textarea cols="40" rows="5" maxlength="500" class="textarea" v-model="recommentContents" placeholder="답글을 입력하세요."></textarea>
		                            <p class="length"><span v-text="recommentContents.length"></span>/500</p>
		                        </div>
		                        <div class="btn-wrap">
		                            <button type="button" class="btn btn-submit" @click="saveReComment(comment.oid,index)" :disabled="recommentContents===''">{{btnText}}</button>
		                        </div>
		                    </div>
		                </div>
					</div>
	                <!-- 페이지네이션 :: 댓글용 -->
                    <the-pagination v-if="0 < listCount" :visible-buttons-count="5" :total-count="listCount" :page-size="pageSize" :current-page="currentPage" pageComp="commentPage"/>
	            </div>

	            <!-- 댓글 입력창 -->
	            <div class="comment-input-wrap">
	                <div class="comment-row">
	                    <div class="comment-input">
	                        <textarea cols="40" rows="5" maxlength="500" class="textarea" :placeholder="placeholderText" v-model="commentContents" :disabled="!isLogin"></textarea>
	                        <p class="length"><span v-text="commentContents.length"></span>/500</p>
	                    </div>
	                    <div class="btn-wrap">
	                        <button type="button" class="btn btn-submit" @click="saveComment()" :disabled="commentContents===''">댓글 등록</button>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- // 댓글 영역 -->
</template>

<script>
import thePagination from "~/components/common/ThePagination.vue";

const URL_LIST = "/lms/api/v1/lmsBoardApi_listComment";
const URL_SAVE = "/lms/api/v1/lmsBoardApi_insertOrUpdateComment";
const URL_DELETE = "/lms/api/v1/lmsBoardApi_deleteComment";

const INSERT = "등록";
const MODIFY = "수정";

export default {
	components : {

		thePagination,
	},
	props : {

		targetOid : {
			type : String,
			required : true,
		}
	},
	data : function() {
		return {
			rowClass : {
				myComment : "my-comment",	//내가 단 댓글에 적용할 클래스
				reComment : "re-comment",	//대댓글에 적용할 클래스
			},
			oid : this.$route.query.oid,
			commentList : [],
			pageSize: 10,
            currentPage: 1,

            thisPage: 1,
		    listCount : 0,
		    commentContents : "",	//새 댓글
		    recommentContents : "",	// 새 대댓글
		    entering : "",	//입력중인 댓글

		    btnText : INSERT,
		    isLogin : false,
		    placeholderText : "",

		}
	},
	computed : {

	},
	watch: {
        $route() {
            if (this.$route.query.commentPage) {
                this.onChangePage(parseInt(this.$route.query.commentPage));
            } else {
                this.onChangePage(1);
            }
        },

		targetOid(){

        	this.$fetch();
		},
    },
	async fetch () {

		this.confirmLogin();
		this.setListData( );
	},
	methods : {

		onChangePage(page) {
            this.currentPage = page;
            this.thisPage = (parseInt(this.currentPage) - 1) * parseInt(this.pageSize) + 1;
            this.setListData(this.thisPage);
        },

		/* 로그인하지 않은 사람은 입력창 disabled */
		confirmLogin(){

			let isLogin = this.$store.state.login.loginUser.userOid;

			if( !isLogin ){

				this.placeholderText = "로그인 후 이용이 가능합니다.";
				return;
			}
			this.placeholderText = "댓글을 입력하세요.";
			this.isLogin = true;
		},

		/* 동적클래스 가져오기 */
		getRowClass( comment ) {

			let classArr = [];
			if ( this.$store.state.login.loginUser.userOid === comment.inputUser ) {	//내가 단 댓글인 경우
				classArr.push( this.rowClass.myComment );
			}
			if( this.$common.isNotEmpty( comment.threadParentOid ) ) {	// 대댓글인 경우
				classArr.push( this.rowClass.reComment );
			}

			return classArr;
	    },

	    /* 댓글 리스트를 가져옵니다. */
	    setListData( startIndex ) {

			let param = {
					startIndex : startIndex,
					targetOid : this.targetOid,
			}

			this.$axios.post( URL_LIST, param ).then( (res) => {

				 if ( this.$common.isEmpty( res.data ) ) {
	                    return;
               	 }

				this.commentList = res.data.list;
				this.listCount = res.data.listCount;

			} );
		},

		/* 글을 쓴 사람이 로그인한 사람인지 확인 후, 수정, 삭제 버튼을 보여줍니다.  */
		isWriter( inputUser ){

			let userOid  = this.$store.state.login.loginUser.userOid;

			if( userOid === inputUser ){
				return true;
			}
		},

		/* 등록일 format YYYY-MM-DD */
		getInputDateString( comment ) {

			return this.$common.formatDate( comment.inputDate );
		},

		/* 답글, 수정시 입력창 열기 */
	    createCommentInput( oid ) {
	    	this.entering = oid;
	    	this.recommentContents = "";
	    	this.btnText = INSERT;
	    },

	    /* 댓글 등록 */
	    saveComment(){

	    	let param = {};

	    	param.contents = this.commentContents;
    		param.targetOid = this.targetOid;				//posting oid
    		param.inputUser = this.$store.state.login.loginUser.userOid;

	    	this.$axios.post( URL_SAVE, param ).then( (res) => {

	    		if( this.$common.isEmpty( res.data ) ) {
	    			return;
	    		}

	    		this.entering = "";	//입력중인 댓글 초기화
	    		this.commentContents = "";
	    		this.recommentContents = "";
	    		this.setListData();
			} );
	    },

		/* 대댓글 INSERT or MODIFY 버튼클릭시 */
	    saveReComment( oid, index ) {

	    	let param = {};

    		param.contents = this.recommentContents;
    		param.targetOid = this.oid;		//posting oid
    		param.inputUser = this.$store.state.login.loginUser.userOid;

	    	// 댓글 INSERT ajax
	    	if ( INSERT == this.btnText ) {

	    		param.threadParentOid = oid;

		    	this.$axios.post( URL_SAVE, param ).then( (res) => {

		    		if( this.$common.isEmpty( res.data ) ) {

						this.$common.confirmSwal("댓글 등록 실패", "", "error" );
		    			return;
		    		}
		    		this.entering = "";	//입력중인 댓글 초기화

		    		if( this.$common.isEmpty( oid ) ) {
		    			this.commentContents = "";
		    		}
		    		else {
		    			this.recommentContents = "";
		    		}

		    		this.setListData();

				} );

	    	} // 댓글 MODIFY
	    	else if ( MODIFY == this.btnText ){

	    		param.oid = oid;

				this.$axios.post( URL_SAVE, param ).then( (res) => {

		    		if( this.$common.isEmpty( res.data ) ) {

						this.$common.confirmSwal("댓글 수정 실패", "", "error" );
		    			return;
		    		}
		    		this.entering = "";	//입력중인 댓글 초기화

		    		if( this.$common.isEmpty( oid ) ) {
		    			this.commentContents = "";
		    		}
		    		else {
		    			this.recommentContents = "";
		    		}

		    		this.setListData();

				} );
	    	}
	    },

		/* 댓글 삭제 */
	    deleteComment( oid, index ) {

	    	this.$common
			.swalWithOptions( "", "댓글을 정말 삭제하시겠습니까?", "warning", "삭제")
			.then((willDelete) => {
				if (willDelete) {
					this.$axios.post( URL_DELETE, {oid : oid} ).then((res) => {

						this.setListData();
					});
				}
				else {

					return;
				}
			});
	    },

		/**MODIFY 버튼 클릭시*/
		modify( comment ){

			this.createCommentInput( comment.oid );
			this.recommentContents = comment.contents;
			this.btnText = MODIFY;
		},

		/* 대댓글 padding */
		getPadding ( comment ) {

			if( $( "body" ).width() > 1200 && comment.threadDepth > 1 ) {
				let leftPadding = ( comment.threadDepth - 1 ) * 5;
				return {
					'padding-left' : leftPadding + '%'
				};
			}
		},

	},
}
</script>
