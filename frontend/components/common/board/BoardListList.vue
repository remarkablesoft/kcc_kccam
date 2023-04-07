<template>
	<div>
		<!-- 보기 타입 :: 리스트형 -->
		<div class="div-table board">
			<div class="table-head">
				<div class="item-group">
					<div class="item xxsm">
						<!-- <el-checkbox v-model="checkAll"></el-checkbox> -->
						<!-- element ui checkbox 가 v-for list에서 문제가 있다고 해서 예전 쓰던 방식으로 만들었습니다. 문제시 말씀해주세요. -->
						<div class="custom-checkbox block">
							<input id="checkAll" type="checkbox"/>
							<label for="checkAll">
								<i></i>
							</label>
						</div>
					</div>
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
<!--				<div v-if="showLoadingIndicator || 1 > listCount" class="no-data">-->
<!--					<div class="loading-sm">-->
<!--						<img alt="Loading" src="@/assets/lms/images/common/loading_sm.svg"/>-->
<!--					</div>-->
<!--					<p>데이터 로딩중입니다.</p>-->
<!--				</div>-->
				<!-- no-data -->
				<div v-if="false === showLoadingIndicator && 1 > listCount" class="no-data">
					<i class="material-icons">error_outline</i>
					<p>데이터가 없습니다.</p>
				</div>
				<!-- list-item // 답글일 경우 list-item에 reply 클래스 추가. -->
				<div v-for="(item, i) in postingList" v-if="0 < listCount" :key="i" :class="{ reply : item.replyList }" class="list-item" @click="goView(item.oid)">
					<!-- :class="[isActive ? activeClass : '']" -->
					<div class="item-group">
						<div class="item xxsm">
							<!-- <el-checkbox v-model="item.checkbox"></el-checkbox> -->
							<!-- element ui checkbox 가 v-for list에서 문제가 있다고 해서 예전 쓰던 방식으로 만들었습니다. 문제시 말씀해주세요. -->
							<div class="custom-checkbox block">
								<input :id="item.id" type="checkbox"/>
								<label :for="item.id">
									<i></i>
								</label>
							</div>
						</div>
						<div class="item sm">
							<!-- no. -->
							<span
								v-html="settingNo(item.noticeLevel, listCount, thisPage, i)"></span>
							<!-- 공지 라벨 -->
							<!-- <span class="label fill-bg" >공지</span> @click="goView()" -->
						</div>
						<div class="item tit">
							<!-- 답글 표시 아이콘 : 클래스로 보였다 안보였다 처리. -->
							<i class='material-icons icon-reply'>subdirectory_arrow_right</i>
							<!-- 제목 -->
							<!-- 본글에 saw 추가시 회색으로 변함 -->
							<span :class="getReadYn(item)" class="tit"
								  v-text="item.title"></span>
							<!-- 댓글 수 -->
							<span v-if="item.commentYn" class="cnt">(<span
								v-text="item.commentCnt"></span>)</span>
							<!-- 비밀글 표시 아이콘 -->
							<i v-if="item.lockYn" class="material-icons sm">lock</i>
							<!-- new -->
							<div v-html="setNewLabel(item.newYnVC)"></div>
						</div>
						<div class="item sm">
							<i v-if="isFileList(item.fileList)" class="material-icons">attach_file</i>
						</div>
						<div class="item sm">
							<span v-text="farmatDate(item.inputDate)"></span>
						</div>
						<div class="item sm">
							<span v-text="item.writer"></span>
						</div>
						<div class="item sm">
							<span v-text="item.viewCnt"></span>
						</div>
					</div>
				</div>
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
				<el-button type="primary" @click="passwordInputDialogVisible = false">확인
				</el-button>
				<el-button @click="passwordInputDialogVisible = false">닫기</el-button>
			</div>
		</el-dialog>
	</div>
</template>
<script>

export default {
	components : {
	},
	props      : {
		boardInfo : {
			type : Object,
			required : true,
		},
		postingList : {
			type : Array,
			required : true,
		}
	},
	data() {
		return {
			//검색 필터 구성 Data
			searchFilterData : [
				{
					type      : "period",
					title     : "등록일",
				},
				{
					type      : "text",
					title     : "검색명",
					paramName : 'searchQuery',
					searchTypeOptions : [
						{
							value : "name",
							label : "게시판명",
						}
					]
				},
				{
					type      : "radio",
					title     : "게시판상태",
					paramName : 'radioParam',
					data      : [
						{
							value : "",
							label : "전체"
						},
						{
							value : this.$constant.ENUM_STATUS_TYPE_FLAG.USE.KEY,
							label : this.$constant.ENUM_STATUS_TYPE_FLAG.USE.NAME
						},
						{
							value : this.$constant.ENUM_STATUS_TYPE_FLAG.STOP.KEY,
							label : this.$constant.ENUM_STATUS_TYPE_FLAG.STOP.NAME
						},
						{
							value : this.$constant.ENUM_STATUS_TYPE_FLAG.CLOSE.KEY,
							label : this.$constant.ENUM_STATUS_TYPE_FLAG.CLOSE.NAME
						},
					]
				},
			],

			showLoadingIndicator : false,
			pageSize             : 20,
			currentPage          : 1,
			thisPage             : 1,

			listCount   : 0,
			orderByList : [],
			lastPage : 0,									// 마지막 페이지

			// 비밀번호 입력 모달
			passwordInputDialogVisible : false,
			passwordInput              : "",
		};
	},
	async fetch() {
		if ( !process.browser ) {
			return;
		}
		this.showLoadingIndicator = true;
	},
	computed   : {
		loadingIndicator() {
			// window는 ssr에서는 사용하지 않기 위해
			if ( !process.browser ) {
				return;
			}

			return this.$nuxt.$root.$loading.percent;
		},
	},
	watch      : {
	},
	mounted() {
	},
	methods    : {

		/* 등록일 format setting */
		farmatDate( inputDate ) {

			return this.$common.formatDateToString( new Date( inputDate ) );
		},

		/* 파일이 있을경우, 첨부파일 아이콘을 setting */
		isFileList( fileList ) {

			return fileList.length > 0 ? "<i class='material-icons custom-icon-sh color-gray'>attach_file</i>" : "";
		},

		/* noticeLevel이 1일 경우? 공지라벨 : 숫자 */
		settingNo( noticeLevel, totalCount, startIndex, index ) {

			let num = 1 === noticeLevel ? "<span class='label fill-bg'>공지</span>" : this.$common.settingNo( totalCount, startIndex, index );
			return num;
		},

		/* ThePageCntOption.vue에서 받아온 페이지 보기 개수 */
		setPageSize( pageCnt ) {

			this.pageSize = pageCnt;
			this.getPostingList( 1, this.boardOid );
		},

		/* TheListSortOption.vue에서 받아온 정렬 */
		setOrderByList( orderByList ) {

			this.orderByList.push( orderByList );
			this.getPostingList( 1, this.boardOid );
		},

		/* ThePeriodAndTextSearch.vue에서 받아온 검색조건 */
		getSearchObj( searchObj ) {

			this.getPostingList( 1, this.boardOid, searchObj );
		},

		/* 등록일, 수정일로부터 3일을 체크하여 new 라벨을 붙여줍니다. */
		setNewLabel( newYn ) {

			let label = this.$constant.FLAG_YN.YES === newYn ? "<div class='new-label'>N</div>" : "";
			return label;``
		},

		getReadYn( data ) {

			let addClass = "";

			if ( this.$constant.FLAG_YN.NO === this.boardType ) {
				//내가 아직 안 본 글일경우 BOLD체 : 일반
				addClass = this.$constant.FLAG_YN.NO === data.readYnVC ? "fw700" : "";
			}
			else {
				//내가 이미 본 글일경우 회색 : 일반
				addClass = this.$constant.FLAG_YN.YES === data.readYnVC ? "saw" : "";
			}

			return addClass;
		}

	},
};
</script>
