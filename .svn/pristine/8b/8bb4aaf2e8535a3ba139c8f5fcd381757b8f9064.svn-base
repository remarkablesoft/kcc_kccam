<template>
    <div class="inner-content">
<!--        <div v-if="loadingIndicator > 0 || $fetchState.pending" class="loading-container">-->
<!--            <the-loading />-->
<!--        </div>-->

        <!-- 게시판 기본 목록 -->
        <!-- 검색영역 -->
        <!--							<the-search :searchData='searchFilterData' @searchObj="getSearchObj"/>-->
        <!-- 목록 영역 -->
        <div class="list-area">
            <div class="list-header">
                <div class="left-area">
                    <!--등록하기 버튼-->
                    <!--										v-if="$common.getCheck( roleRightList[0].aclValue, $constant.ACL_VALUE.CREATE )"-->
                    <el-button size="regular" type="primary" @click="goEdit()">등록</el-button>
                </div>
                <div class="right-area">
                    <!-- 페이지 개수 선택 -->
                    <the-page-cnt-option :page-size="pageSize" @pageCntOptionsValue="setPageSize" />
                    <!-- 정렬 -->
                    <the-list-sort-option @listSortOptionsValue="setOrderByList" />
                    <!-- 보기 타입 선택 아이콘 버튼 -->
                    <!--										<the-list-view-type-btn :view-type="viewType"/>-->
                </div>
            </div>
            <div class="list-body">
                <!-- 보기 타입 :: 리스트형 -->
                <!-- 보기 타입 :: 리스트형 -->
                <div class="div-table board">
                    <div class="table-head">
                        <div class="item-group">
                            <!--												<div class="item xxsm">-->
                            <!--													&lt;!&ndash; <el-checkbox v-model="checkAll"></el-checkbox> &ndash;&gt;-->
                            <!--													&lt;!&ndash; element ui checkbox 가 v-for list에서 문제가 있다고 해서 예전 쓰던 방식으로 만들었습니다. 문제시 말씀해주세요. &ndash;&gt;-->
                            <!--													<div class="custom-checkbox block">-->
                            <!--														<input id="checkAll" type="checkbox"/>-->
                            <!--														<label for="checkAll">-->
                            <!--															<i></i>-->
                            <!--														</label>-->
                            <!--													</div>-->
                            <!--												</div>-->
                            <div class="item sm">NO</div>
                            <div class="item tit">제목</div>
                            <div class="item sm">첨부파일</div>
                            <div class="item sm">등록일</div>
                            <div class="item sm">등록자</div>
                            <div class="item sm">조회수</div>
                        </div>
                    </div>
                    <div class="table-body">
                        <!-- no-data(loading) -->
                        <div v-if="0 < loadingIndicator && 1 > listCount" class="no-data">
                            <div class="loading-sm">
                                <img alt="Loading" src="~/assets/lms/images/common/loading_sm.svg" />
                            </div>
                            <p>데이터 로딩중입니다.</p>
                        </div>
                        <!-- no-data -->
                        <div v-if="1 > listCount" class="no-data">
                            <i class="material-icons">error_outline</i>
                            <p>데이터가 없습니다.</p>
                        </div>
                        <!-- list-item // 답글일 경우 list-item에 reply 클래스 추가. -->
                        <div
                            v-for="(item, i) in postingList"
                            v-if="0 < listCount"
                            :key="i"
                            :class="{ reply: item.replyList }"
                            class="list-item"
                            @click="goView(item.oid)"
                        >
                            <!-- :class="[isActive ? activeClass : '']" -->
                            <div class="item-group">
                                <!--													<div class="item xxsm">-->
                                <!--														&lt;!&ndash; <el-checkbox v-model="item.checkbox"></el-checkbox> &ndash;&gt;-->
                                <!--														&lt;!&ndash; element ui checkbox 가 v-for list에서 문제가 있다고 해서 예전 쓰던 방식으로 만들었습니다. 문제시 말씀해주세요. &ndash;&gt;-->
                                <!--														<div class="custom-checkbox block">-->
                                <!--															<input :id="item.id" type="checkbox"/>-->
                                <!--															<label :for="item.id">-->
                                <!--																<i></i>-->
                                <!--															</label>-->
                                <!--														</div>-->
                                <!--													</div>-->
                                <div class="item sm">
                                    <!-- no. -->
                                    <span v-html="settingNo(item.noticeLevel, listCount, thisPage, i)"></span>
                                    <!-- 공지 라벨 -->
                                    <!-- <span class="label fill-bg" >공지</span> @click="goView()" -->
                                </div>
                                <div class="item tit">
                                    <!-- 답글 표시 아이콘 : 클래스로 보였다 안보였다 처리. -->
                                    <i class="material-icons icon-reply">subdirectory_arrow_right</i>
                                    <!-- 제목 -->
                                    <!-- 본글에 saw 추가시 회색으로 변함 -->
                                    <span :class="getReadYn(item)" class="tit" v-text="item.title"></span>
                                    <!-- 댓글 수 -->
                                    <span v-if="item.commentYn" class="cnt">(<span v-text="item.commentCount"></span>)</span>
                                    <!-- 비밀글 표시 아이콘 -->
                                    <i v-if="item.lockYn" class="material-icons sm">lock</i>
                                    <!-- new -->
                                    <div v-html="setNewLabel(item.newYnVC)"></div>
                                </div>
                                <div class="item sm">
                                    <i v-if="isFileList(item.fileList)" class="material-icons">attach_file</i>
                                </div>
                                <div class="item sm">
                                    <span v-text="formatDate(item.inputDate)"></span>
                                </div>
                                <div class="item sm">
                                    <span v-text="item.inputUserInfo.id || 'admin'"></span>
                                </div>
                                <div class="item sm">
                                    <span v-text="item.viewCount"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--									<board-list-list :board-info="boardInfo" :posting-list="postingList"/>-->
                <!-- 보기 타입 :: 이미지형 -->
                <!--									<board-image-list />-->
                <!-- 보기 타입 :: 카드형 -->
                <!--									<board-card-list />-->
                <!-- 페이지 정보 -->
                <the-page-cnt-info :current-page="currentPage" :last-page="lastPage" :list-count="listCount" />
                <!-- pagination -->
                <the-pagination
                    v-if="0 < listCount"
                    :current-page="currentPage"
                    :page-size="pageSize"
                    :total-count="listCount"
                    :visible-buttons-count="5"
                    pageComp="postingPage"
                />
            </div>
        </div>

        <!-- 비밀글일 시 : 비밀번호 입력 모달 -->
        <el-dialog :visible.sync="passwordInputDialogVisible" title="비밀번호 입력" width="40rem">
            <div class="input-group square-dot">
                <div class="input-row">
                    <div class="item-label">비밀번호</div>
                    <div class="item-data">
                        <el-input v-model="passwordInput" placeholder="" show-password></el-input>
                    </div>
                </div>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="passwordInputDialogVisible = false">확인 </el-button>
                <el-button @click="passwordInputDialogVisible = false">닫기</el-button>
            </div>
        </el-dialog>

        <!-- 게시글 상세보기 모달 -->
        <the-posting-view-modal
            :posting-oid="postingOid"
            :board-oid="boardOid"
            v-if="postingViewVisible"
            :open-modal="postingViewVisible"
            :board-info="boardInfo"
            @close-modal="closePostingViewModal"
        />
        <!-- 게시글 등록/수정 모달 -->
        <the-posting-edit-modal
            :board-info="boardInfo"
            v-if="postingEditVisible"
            :open-modal="postingEditVisible"
            board-title="게시글 등록"
            @close-modal="closePostingEditModal"
        />
    </div>
</template>
<script>
import theLoading from "~/components/common/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import thePageCntOption from "~/components/common/pageCnt/ThePageCntOption.vue";
import thePageCntInfo from "~/components/common/pageCnt/ThePageCntInfo.vue";
import theListSortOption from "~/components/common/list/TheListSortOption.vue";

import theListViewTypeBtn from "~/components/common/list/TheListViewTypeBtn.vue";
import VClamp from "vue-clamp";
import theSearch from "~/components/common/search/TheSearch";
import boardCardList from "~/components/common/board/BoardCardList";
import boardImageList from "~/components/common/board/BoardImageList";

import boardListList from "~/components/common/board/BoardListList";
import thePostingViewModal from "~/components/lms/manager/modal/posting/ThePostingViewModal";
import thePostingEditModal from "~/components/lms/manager/modal/posting/ThePostingEditModal";

const URL_LIST = "/lms/api/v1/lmsBoardApi_listPosting";
const GET_BOARD = "/lms/api/v1/lmsBoardApi_getBoardInfo";

export default {
    components: {
        theLoading,
        thePagination,
        thePageCntOption,
        thePageCntInfo,
        theListSortOption,

        theListViewTypeBtn,
        VClamp,
        theSearch,
        boardCardList,
        boardImageList,

        boardListList,
        thePostingViewModal,
        thePostingEditModal,
    },
    props: {
        boardOid: {
            type: String,
            required: true,
            default: "",
        },
    },
    data() {
        return {
            //검색 필터 구성 Data
            searchFilterData: [
                {
                    type: "period",
                    title: "등록일",
                    paramName: "searchDate",
                },
                {
                    type: "text",
                    title: "검색명",
                    paramName: "searchQuery",
                    searchTypeOptions: [
                        {
                            value: "name",
                            label: "게시판명",
                        },
                    ],
                },
                {
                    type: "radio",
                    title: "게시판상태",
                    paramName: "radioParam",
                    data: [
                        {
                            value: "",
                            label: "전체",
                        },
                        {
                            value: this.$constant.ENUM_STATUS_TYPE_FLAG.USE.KEY,
                            label: this.$constant.ENUM_STATUS_TYPE_FLAG.USE.NAME,
                        },
                        {
                            value: this.$constant.ENUM_STATUS_TYPE_FLAG.STOP.KEY,
                            label: this.$constant.ENUM_STATUS_TYPE_FLAG.STOP.NAME,
                        },
                        {
                            value: this.$constant.ENUM_STATUS_TYPE_FLAG.CLOSE.KEY,
                            label: this.$constant.ENUM_STATUS_TYPE_FLAG.CLOSE.NAME,
                        },
                    ],
                },
            ],

            postingList: [],
            pageSize: 10,
            currentPage: 1,
            thisPage: 1,

            listCount: 0,
            orderByList: [],
            lastPage: 0, // 마지막 페이지

            // 비밀번호 입력 모달
            passwordInputDialogVisible: false,
            passwordInput: "",

            boardInfo: {},
            viewType: {
                isList: false,
                isImage: false,
                isCard: false,
            },
            // roleRightList : [],								// 로그인한 user와 db에서 가져온 roleRight와 비교한 리스트
            postingOid: "",
            postingViewVisible: false,
            postingEditVisible: false,
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
    watch: {
        $route() {
            if (this.$route.query.postingPage) {
                this.onChangePage(parseInt(this.$route.query.postingPage));
            } else {
                this.onChangePage(1);
            }
        },

        boardOid() {
            this.$fetch();
        },
    },
    mounted() {
        if (!process.browser) {
            return;
        }
    },
    async fetch() {
        await this.getPostingList(1);
        await this.getBoardInfo();
    },
    methods: {
        /* login user의 role을 체크해 권한 설정을 해줍니다. */
        // aclValueCheck( boardInfo ){
        //
        // 	if( this.$common.isEmpty( boardInfo ) ){
        // 		return;
        // 	}
        //
        // 	let checkedRight = [];
        //
        // 	boardInfo.rightInfoList.forEach( (right, index) => {
        //
        // 		console.log( "right", right );
        // 		this.$constant.ACL_VALUE_LIST.forEach( (acl, index) => {
        //
        // 			console.log( "acl", acl );
        // 			const result = this.$common.getCheck( right.aclValue, acl );
        //
        // 			if( result ){
        //
        // 				checkedRight.push( right );
        // 			}
        // 		});
        //
        // 		console.log( "checkedRight", checkedRight );
        // 		return checkedRight;
        // 	});

        // let roleOids = this.$store.state.login.loginUser.userRoleOids.split(',');
        // boardInfo.rightInfoList.forEach( (right, index) => {
        //
        // 	roleOids.forEach( (role, index) => {
        //
        // 		if( right.roleOid.indexOf( role ) !== -1 ){
        //
        // 			this.roleRightList.push( right );
        // 		}
        // 	});
        // });
        // },

        /* 게시판별 설정을 위한 boardInfo를 가져옵니다. */
        getBoardInfo() {
            let param = {
                oid: this.boardOid,
            };

            this.$axios.post(GET_BOARD, param).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }

                this.boardInfo = res.data;
                // this.setViewType( this.boardInfo.viewType );
                // this.aclValueCheck( this.boardInfo );
            });
        },

        /* 자식 modal이 닫혀진 시점을 받는다. */
        closePostingViewModal(data) {
            this.postingViewVisible = data;
        },

        /* 자식 modal이 닫혀진 시점을 받는다. */
        closePostingEditModal(data) {
            this.postingEditVisible = data;
        },

        // 등록 페이지로 이동.
        goEdit() {
            // this.roleRightList.forEach( (roleRight, index) => {
            //
            // 	this.$constant.ACL_VALUE_LIST.forEach( (acl, index) => {
            //
            // 		const result = this.$common.getCheck( roleRight.aclValue, acl );
            //
            // 		if( result ){
            //
            // 			this.$router.push( {
            //
            // 				path : '/lms/boardTemplate/boardEdit/boardEdit',
            // 				query : {
            // 					noticeUseYn : this.boardInfo.property.noticeUseYn,
            // 					noticeMustUseYn : this.boardInfo.property.noticeMustUseYn,
            // 					anonymousUseYn : this.boardInfo.property.anonymousUseYn,
            // 				}
            // 			} );
            // 		}
            // 	});

            this.postingEditVisible = true;
        },

        // table row click
        goView(oid) {
            this.postingOid = oid;
            this.postingViewVisible = true;
        },

        onChangePage(page) {
            this.currentPage = page;
            this.thisPage = (parseInt(this.currentPage) - 1) * parseInt(this.pageSize) + 1;
            this.getPostingList(this.thisPage);
        },

        /* 각 boardOid의 리스트를 가져옵니다. */
        getPostingList(startIndex, searchObj) {
            let param = {
                startIndex: startIndex,
                pageSize: this.pageSize,
                boardOid: this.boardOid,
                orderByList: this.orderByList,
            };

            this.addParam(param, searchObj);

            this.$axios.post(URL_LIST, param).then(res => {
                if (this.$common.isEmpty(res.data.list)) {
                    this.listCount = 0;
                    return;
                }

                this.postingList = res.data.list;
                this.listCount = res.data.listCount;
                this.lastPage = this.$common.getLastPage(this.listCount, this.pageSize);
            });

            this.orderByList = [];
        },

        /* 조건이 맞는다면 param을 추가해줍니다. */
        addParam(param, searchObj) {
            if (searchObj) {
                if (this.$common.isNotEmpty(searchObj.inputDateFrom)) {
                    param.inputDateFrom = searchObj.inputDateFrom;
                }
                if (this.$common.isNotEmpty(searchObj.inputDateTo)) {
                    param.inputDateTo = searchObj.inputDateTo;
                }

                if (this.$common.isNotEmpty(searchObj.searchQuery)) {
                    param.searchQuery = searchObj.searchQuery.searchQuery;
                    param.searchQueryType = searchObj.searchQuery.searchQueryType;
                }
            }

            return param;
        },

        /* 등록일 format setting */
        formatDate(inputDate) {
            return this.$common.formatDateToString(new Date(inputDate));
        },

        /* 파일이 있을경우, 첨부파일 아이콘을 setting */
        isFileList(fileList) {
            return fileList.length > 0 ? "<i class='material-icons custom-icon-sh color-gray'>attach_file</i>" : "";
        },

        /* noticeLevel이 1일 경우? 공지라벨 : 숫자 */
        settingNo(noticeLevel, totalCount, startIndex, index) {
            let num = 1 === noticeLevel ? "<span class='label fill-bg'>공지</span>" : this.$common.settingNo(totalCount, startIndex, index);
            return num;
        },

        /* ThePageCntOption.vue에서 받아온 페이지 보기 개수 */
        setPageSize(pageCnt) {
            this.pageSize = pageCnt;
            this.getPostingList(1);
        },

        /* TheListSortOption.vue에서 받아온 정렬 */
        setOrderByList(orderByList) {
            this.orderByList.push(orderByList);
            this.getPostingList(1);
        },

        /* ThePeriodAndTextSearch.vue에서 받아온 검색조건 */
        getSearchObj(searchObj) {
            this.getPostingList(1, searchObj);
        },

        /* 등록일, 수정일로부터 3일을 체크하여 new 라벨을 붙여줍니다. */
        setNewLabel(newYn) {
            let label = this.$constant.FLAG_YN.YES === newYn ? "<div class='new-label'>N</div>" : "";
            return label;
            ``;
        },

        getReadYn(data) {
            let addClass = "";

            if (this.$constant.FLAG_YN.NO === this.boardType) {
                //내가 아직 안 본 글일경우 BOLD체 : 일반
                addClass = this.$constant.FLAG_YN.NO === data.readYnVC ? "fw700" : "";
            } else {
                //내가 이미 본 글일경우 회색 : 일반
                addClass = this.$constant.FLAG_YN.YES === data.readYnVC ? "saw" : "";
            }

            return addClass;
        },

        /* 보기타입을 세팅해줍니다. */
        setViewType(viewType) {
            if (viewType.indexOf(this.$constant.VIEW_TYPE.LIST) !== -1) {
                this.viewType.isList = true;
            }

            if (viewType.indexOf(this.$constant.VIEW_TYPE.IMAGE) !== -1) {
                this.viewType.isImage = true;
            }

            if (viewType.indexOf(this.$constant.VIEW_TYPE.CARD) !== -1) {
                this.viewType.isCard = true;
            }
        },
    },
};
</script>
