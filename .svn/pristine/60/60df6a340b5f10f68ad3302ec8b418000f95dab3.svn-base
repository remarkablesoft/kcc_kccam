<template>
	<div>
		<!-- 보기 타입 :: 카드형 -->
		<div class="board-list card-type">
			<ul class="card-list">
				<li v-for="(item, i) in cardItemList" :key="i" class="list-item">
					<div class="card-item">
						<div class="img-area">
							<!-- 체크박스 -->
							<div class="custom-checkbox">
								<input :id="item.id" type="checkbox"/>
								<label :for="item.id">
									<i></i>
								</label>
							</div>
							<div class="thumbnail">
								<!-- 썸네일 이미지 -->
								<div class="thumb-img-area">
									<span class="thumb">
										<img alt="이미지/동영상 썸네일"
											 src="@/assets/lms/images/common/sample/sample_text.png"/>
									</span>
								</div>
								<!-- 이미지 개수 -->
								<div v-if="item.imgCntYn" class="label-box img-cnt">
									<span v-text="item.imgCnt"></span>+
								</div>
								<!-- 동영상 시간 -->
								<div v-if="item.playTimeYn"
									 class="label-box video-play-time">
									<i class="material-icons">play_arrow</i>
									<span v-text="item.playTime"></span>
								</div>
							</div>
						</div>
						<div class="txt-area">
							<div class="tit" v-text="item.title"></div>
							<v-clamp :max-lines="2" autoresize class="txt">{{
									item.text
								}}
							</v-clamp>
						</div>
						<div class="sub-info-area">
							<div class="cnt-group">
								<span class="cnt-item">
									<i class="material-icons">visibility</i>
									<span class="cnt" v-text="item.viewCnt"></span>
								</span>
								<span class="cnt-item">
									<i class="material-icons">thumb_up_alt</i>
									<span class="cnt" v-text="item.recommendCnt"></span>
								</span>
							</div>
							<div class="label-box info-txt">
								<span class="writer" v-text="item.writer">서경덕(seo)</span>
								<span class="date" v-text="item.date">2020-01-26</span>
							</div>
						</div>
					</div>
				</li>
			</ul>
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
import VClamp from "vue-clamp";

export default {
	components : {
		VClamp
	},
	props      : {
		boardOid  : {
			type    : String,
			default : "",
		},
		boardType : {
			type    : String,
			default : "",
		},
		boardName : {
			type    : String,
			default : "",
		},
	},
	data() {
		return {
			//검색 필터 구성 Data
			searchFilterData : [
				{
					type      : "period",
					title     : "등록일",
					paramName : 'searchDate',
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
			postingList          : [],
			pageSize             : 20,
			currentPage          : 1,
			thisPage             : 1,

			listCount   : 0,
			orderByList : [],
			lastPage : 0,									// 마지막 페이지


			// 게시판 목록 :: 카드형
			cardItemList : [
				{
					id           : "2",
					imgCntYn     : true,
					imgCnt       : "3",
					playTimeYn   : false,
					playTime     : "2:30",
					title        : "천사 고아원 봉사할동",
					text         : "감사한 분들 덕분에 잘 다녀왔습니다. 준비한 음식들을 아이들이 너무 맛있게 먹어줘서 더욱 더 보람을 느꼈던 하루였던 거 같습니다. 준비한 음식들을 아이들이 너무 맛있게 먹어줘서 더욱 더 보람을 느꼈던 하루였던 거 같습니다. 준비한 음식들을 아이들이 너무 맛있게 먹어줘서 더욱 더 보람을 느꼈던 하루였던 거 같습니다. 준비한 음식들을 아이들이 너무 맛있게 먹어줘서 더욱 더 보람을 느꼈던 하루였던 거 같습니다.",
					viewCnt      : "162",
					recommendCnt : "12",
					writer       : "서경덕(seo)",
					date         : "2020-01-26",
				},
				{
					id           : "1",
					imgCntYn     : false,
					imgCnt       : "3",
					playTimeYn   : true,
					playTime     : "2:30",
					title        : "천사 고아원 봉사할동",
					text         : "감사한 분들 덕분에 잘 다녀왔습니다. 준비한 음식들을 아이들이 너무 맛있게 먹어줘서 더욱 더 보람을 느꼈던 하루였던 거 같습니다. 준비한 음식들을 아이들이 너무 맛있게 먹어줘서 더욱 더 보람을 느꼈던 하루였던 거 같습니다. 준비한 음식들을 아이들이 너무 맛있게 먹어줘서 더욱 더 보람을 느꼈던 하루였던 거 같습니다. 준비한 음식들을 아이들이 너무 맛있게 먹어줘서 더욱 더 보람을 느꼈던 하루였던 거 같습니다.",
					viewCnt      : "162",
					recommendCnt : "12",
					writer       : "서경덕(seo)",
					date         : "2020-01-26",
				},
			],

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

		// 등록 페이지로 이동.
		goEdit() {
//             if (this.$constant.BOARD_TYPE.NOTICE.TYPE === this.boardType) {
//                 this.$router.push("/survey/notice/surveyNotice_edit");
//             }
//             else if (this.$constant.BOARD_TYPE.DATA.TYPE === this.boardType) {
//                 this.$router.push("/survey/dataRoom/surveyDataRoom_edit");
//             }

			this.$router.push( '/lms/boardTemplate/boardEdit/boardEdit' );
		},

		// table row click
		goView( oid ) {

//             if (this.$constant.BOARD_TYPE.NOTICE.TYPE === this.boardType) {
//                 this.$router.push({
//                     path: "/survey/notice/surveyNotice_view",
//                     query: { oid },
//                 });
//             }
//             else if (this.$constant.BOARD_TYPE.DATA.TYPE === this.boardType) {
//                 this.$router.push({
//                     path: "/survey/dataRoom/surveyDataRoom_view",
//                     query: { oid },
//                 });
//             }
			this.$router.push( {
				path  : '/lms/boardTemplate/boardView/boardView',
				query : { oid },
			} );
		},

		onChangePage( page ) {
			this.currentPage = page;
			this.thisPage = ( parseInt( this.currentPage ) - 1 ) * parseInt( this.pageSize ) + 1;
			this.getPostingList( this.thisPage, this.boardOid );
		},

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
