<template>
    <div class="inner-wrapper">
		<div v-if="loadingIndicator > 0" class="loading-container">
			<loading/>
		</div>
		<div class="inner-container sub">
			<!-- content-header :: 클래스로 배경 변경. -->
			<div class="content-header sub courseRegist">
				<div class="inner default-w">
					<div class="tit-area">
						<h3 class="tit" v-text="boardName">공통 게시판 상세</h3>
					</div>
				</div>
            </div>

            <!-- content-body -->
            <div class="content-body courseRegist">
                <!-- <div class="tit-area">
                    <div class="inner default-w">
                        <h3 class="tit">신규강좌 개설요청 상세</h3>
                    </div>
                </div> -->

                <!-- sub-content -->
                <div class="sub-content">
                    <div class="inner default-w">
                        <div class="inner-content">
                            <!-- 내용은 여기에. -->
                            <div class="div-table md-alt board board-view">
                                <div class="table-head">
                                    <div class="item-group">
                                        <div class="item md">
                                            <!-- 일반글일 경우 :: 제목 텍스트 -->
                                            <span style="display: none;">제목</span>
                                            <!-- 공지글일 경우 :: 공지 라벨 -->
                                            <span class="label fill-bg">공지</span>
                                        </div>
                                        <div class="item tit">
                                            <span class="tit" v-text="title">정보화 보안 강좌 개설요청합니다.</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-body">
                                    <div class="sub-info-area">
                                        <span class="post-author">작성자 <em v-text="$common.isEmpty(inputUser) ? 'admin' : inputUser"></em></span>
                                        <span class="post-date">등록일 <em v-text="inputDate"></em></span>
                                        <span class="post-view-cnt">조회수 <em v-text="viewCount"></em></span>
										<span class="post-view-cnt">추천수 <em v-text="recommendCount"></em></span>
                                    </div>

                                    <!-- 작성한 내용 -->
                                    <div class="board-cont-area">
                                        <pre class="common-pre-wrap">
                                        	<p v-html="contents"></p>
                                        </pre>
                                    </div>

                                    <!-- 추가기능 -->
									<div class="add-function-area">
										<!-- 추천 버튼 -->
										<button :class="{ 'active' : isActive }" class="btn-recommend" title="" type=""
												@click="recommendToggle">
											<i :class="[ isActive ? 'material-icons' : 'material-icons-outlined' ]"
											   class="sm">thumb_up_alt</i>
											<span class="txt">추천</span>
										</button>
									</div>

                                    <!-- 파일 -->
									<div v-show="fileList.length > 0" class="board-file-area">
										<div class="common-file-count">
											<strong class="file-tit">첨부파일 :</strong>
											<span class="cnt">
                                                <em class="main-color">2</em>개
                                            </span>
										</div>
										<ul class="common-file-list">
											<!-- 반복되는 부분 li를 클릭하면 다운 -->
											<li v-for="(file, index) in fileList" :key="index" class="table-down-file"
												@click="fileDownload(file)">
												<div class="name-area">
                                                    <i class="material-icons">get_app</i>
                                                    <em class="file-name" v-text="file.name">교육자료1.pdf</em>
                                                    <span class="file-size">(2MB)</span>
                                                </div>
                                                <!-- <el-button type="primary" size= "small">다운로드</el-button> -->
                                            </li>
                                        </ul>
                                    </div>

									<the-comment />

                                    <!-- 이전글/다음글 -->
                                    <div class="board-list-area">
                                        <ul class="board-list">
                                            <li class="item-group">
                                                <div class="item md">이전글</div>
                                                <div class="item tit">
													<span class="tit"
														  @click="goView(prevPosting === null ? null : prevPosting.oid)"
														  v-text="prevPosting === null ? '이전 글이 존재하지 않습니다.' : prevPosting.title">이전글이 없습니다.</span>
                                                </div>
                                            </li>
                                            <li class="item-group">
                                                <div class="item md">다음글</div>
                                                <div class="item tit">
													<span class="tit"
														  @click="goView(nextPosting === null ? null : nextPosting.oid)"
														  v-text="nextPosting === null ? '다음 글이 존재하지 않습니다.' : nextPosting.title">동영상 제작 강좌 요청드립니다.</span>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>

                                </div>
                            </div>

                            <!-- btn -->
							<div class="btn-area right">
								<el-button size="regular" type="st" @click="goEdit()">답글</el-button>
								<el-button size="regular" type="primary" @click="modify()">수정</el-button>
								<el-button size="regular" type="st red" @click="deletePosting()">삭제</el-button>
								<el-button size="regular" type="st" @click="goList()">목록</el-button>
							</div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import loading from "~/components/common/TheLoading.vue";
import "@/assets/lms/css/module/comment/comment.css"; // 댓글 css
import theComment from "~/components/common/comment/TheComment.vue";

const URL_PAN = "/lms/api/v1/lmsBoardApi_viewWithPrevAndNext";
const URL_DELETE = "/lms/api/v1/lmsBoardApi_deletePosting";
const URL_RECOMMEND_ACTION = "/lms/api/v1/lmsBoardApi_recommendAction";

export default {
    components: {
        loading,
        theComment
    },
    props: {
        boardOid: {
            type: String,
            default: "",
        },
        boardType: {
            type: String,
            default: "",
        },
        boardName: {
            type: String,
            default: "",
        },
    },
    data() {
        return {
            oid: this.$route.query.oid,
            title: "",
            contents: "",
            inputUser: "",
            inputDate: "",

            fileList: [],
            viewCount: 0,
			recommendCount : 0,
            prevPosting: null,
            nextPosting: null,

			isActive: false,			 // 추천 버튼 관련
        };
    },
    computed: {
        loadingIndicator() {
            // window는 ssr에서는 사용하지 않기 위해
            if (!process.browser) {
                return;
            }

            return this.$nuxt.$root.$loading.percent;
        },
    },
    mounted() {
        this.getData();
    },
    methods: {
        /* 첨부파일  버튼 클릭시 */
        fileDownload(file) {
            location = "/storage/storageFile_fileDown/" + file.name + "/" + file.storageFileUid;
        },

        /**
         * 목록보기 클릭시 리스트로 이동합니다.
         */
        goList() {
//             if (this.$constant.BOARD_TYPE.NOTICE.TYPE === this.boardType) {
//                 this.$router.push("/survey/notice/surveyNotice_list");
//             }
//             else if (this.$constant.BOARD_TYPE.DATA.TYPE === this.boardType) {
//                 this.$router.push("/survey/dataRoom/surveyDataRoom_list");
//             }

				this.$router.push('/lms/boardTemplate/boardList/boardList');
        },

		// 추천 버튼 클릭 이벤트
        recommendToggle() {

            this.isActive = !this.isActive;

			let param = {

				boardOid : this.$route.query.boardOid,
				postingOid : this.oid,
			};

			this.$axios.post( URL_RECOMMEND_ACTION, param ).then( (res) => {

				if( this.$common.isEmpty( res.data ) ){
					return;
				}

				if( this.$constant.POSTING_ACTION_FLAG.ACTION_FLAG_INSERT === res.data.actionFlag ){

					this.recommendCount++;
				}
				else if( this.$constant.POSTING_ACTION_FLAG.ACTION_FLAG_CANCEL === res.data.actionFlag ){

					this.recommendCount--;
				}
			});
		},

        /**
         * 해당 데이터를 가져옵니다.
         */
        getData() {
            let param = {
                oid: this.$route.query.oid,
                boardOid: this.boardOid,
                viewUserOid: "user", //로그인 유저로 변경할 것!!!
            };

            this.$axios.post(URL_PAN, param).then((res) => {

                if ( this.$common.isEmpty( res.data ) ) {
                    return;
                }

                this.settingData(res.data);
            });
        },

        /* 가져온 게시물의 데이터를 화면에 세팅해줍니다. */
        settingData(data) {
            this.oid = data.oid;
            this.title = data.title;
            this.inputUser = data.inputUserInfo.id;
            this.inputDate = this.$common.formatDate(data.inputDate);
            this.viewCount = data.viewCount;
            this.recommendCount = data.recommendCount;
            this.contents = data.contents;

            data.fileList.forEach((item, index) => {
                let obj = {
                    storageFileUid: item.storageFileUid,
                    name: item.fileName,
                    inputDate: this.$common.formatDate(data.inputDate),
                    size: item.fileSize + "MB",
                };

                this.fileList.push(obj);
            });

            if (this.$common.isNotEmpty(data.prevPosting)) {
                this.prevPosting = data.prevPosting;
            }

            if (this.$common.isNotEmpty(data.nextPosting)) {
                this.nextPosting = data.nextPosting;
            }
        },

        /**
         * 이전글, 다음글 클릭시 상세보기로 이동
         */
        goView(oid) {
            if (this.$common.isEmpty(oid)) {
                return;
            }

            if (this.$constant.BOARD_TYPE.NOTICE.TYPE === this.boardType) {
//                 location.href = "/survey/notice/surveyNotice_view?oid=" + oid;
            } else if (this.$constant.BOARD_TYPE.DATA.TYPE === this.boardType) {
//                 location.href = "/survey/dataRoom/surveyDataRoom_view?oid=" + oid;
            }
        },

        /* 게시판을 삭제합니다. */
        deletePosting() {
            let param = {
                oid: this.oid,
                delUser: "1SFw5uYk000", //작성자로 변경할 것!!!!
            };

            this.$common.swalWithOptions("", "해당 게시물을 정말 삭제하시겠습니까?", "warning").then((willDelete) => {
                if (willDelete) {
                    this.$axios.post(URL_DELETE, param).then((res) => {
                        this.$common.confirmSwal("삭제 성공!", "성공적으로 삭제 되었습니다.", "success");
                        this.goList();
                    });
                } else {
                    return;
                }
            });
        },

        /* 수정버튼 클릭시 */
        modify() {
            let oid = this.oid;

            if (this.$constant.BOARD_TYPE.NOTICE.TYPE === this.boardType) {
                this.$router.push({
//                     path: "/survey/notice/surveyNotice_edit",
                    query: { oid },
                });
            } else if (this.$constant.BOARD_TYPE.DATA.TYPE === this.boardType) {
                this.$router.push({
//                     path: "/survey/dataRoom/surveyDataRoom_edit",
                    query: { oid },
                });
            }
        },
    },
};
</script>

