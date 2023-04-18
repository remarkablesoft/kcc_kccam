<template>
	<div class="inner-wrapper">
		<!-- inner-container main -->
		<div class="manager-inner-container sub">
			<!-- content-body -->
			<div class="content-body">
				<!-- main-content -->
				<div class="sub-content popup-mgnt">
					<div class="inner">
						<div class="inner-content">
							<div class="inner-content search-area">
								<div class="filter-search-box">
									<div class="input-group">
										<div class="input-row">
											<div class="item-label">노출여부</div>
											<div class="item-data">
												<div class="check-wrap">
													<el-radio v-model="statusTypeFlag" label="">전체</el-radio>
													<el-radio v-model="statusTypeFlag" :label="$amConstant.FLAG_YN.YES">노출</el-radio>
													<el-radio v-model="statusTypeFlag" :label="$amConstant.FLAG_YN.NO">비노출</el-radio>
												</div>
											</div>
										</div>
										<div class="input-row">
											<div class="input-item">
												<div class="item-label">키워드</div>
												<div class="item-data">
													<el-select v-model="selectedKeyword" placeholder="선택" class="size-xsm">
														<el-option v-for="ref in selectedKeywordList" :value="ref.value" :label="ref.label"
														           :key="ref.value"></el-option>
													</el-select>
													<el-input v-model="searchText" class="size-md" placeholder=""
													          @keypress.enter.native="search()"></el-input>
												</div>
											</div>
										</div>
									</div>
									<div class="btn-area right">
										<el-button size="small" type="gray" @click="reset()">초기화</el-button>
										<el-button size="small" type="primary" @click="search()">검색</el-button>
									</div>
								</div>
							</div>
							<!-- list-area -->
							<div class="list-area">
								<div class="list-header">
									<div class="left-area">
									</div>
									<div class="right-area">
										<el-button
											class="mr05"
											size="xregular"
											type="default"
											@click="goEdit()">
											<span class="material-icons">assignment_turned_in</span>
											<span>등록</span>
										</el-button>
										<el-button size="xregular" type="default" @click="deletePopup()">
											<span class="material-icons">delete_outline</span>
											<span>삭제</span>
										</el-button>
									</div>
								</div>
								<div class="list-body">
									<!-- table-center -->
									<!-- boardLeftListData.size = col 갯수 결정 -->
									<div class="table-custom-theme theme-gray">
										<table class="table-normal">
											<caption class="hidden">
												팝업 관리
											</caption>
											<colgroup>
												<col style="width: 5%"/>
												<col style="width: 5%"/>
												<col style="width: auto"/>
												<col style="width: 15%"/>
												<col style="width: 20%"/>
												<!-- 5 -->
												<col style="width: 8%"/>
												<col style="width: 10%"/>
												<col style="width: 9%"/>
											</colgroup>
											<thead>
											<tr>
												<th scope="col">
													<el-checkbox v-model="allCheck" @change="setAllChecked()"></el-checkbox>
												</th>
												<th scope="col">No.</th>
												<th scope="col">제목</th>
												<th scope="col">노출기간</th>
												<th scope="col">노출여부</th>
												<th scope="col">보기 타입</th>
												<th scope="col">등록자</th>
												<th scope="col">등록일</th>
											</tr>
											</thead>
											<tbody>
											<tr class="no-data" v-if="$fetchState.pending || 1 > listCount">
												<td colspan="8">
													<!-- no-data(loading) -->
													<div class="no-data" v-if="$fetchState.pending">
														<div class="loading-sm">
															<img src="~/assets/images/loading/loading_sm.svg" alt="Loading"/>
														</div>
														<p>데이터 로딩중입니다.</p>
													</div>
													<!-- no-data -->
													<div class="no-data" v-if="0 === listCount && !$fetchState.pending">
														<i class="material-icons">error_outline</i>
														<p>데이터가 없습니다.</p>
													</div>
												</td>
											</tr>
											<tr class="list-item" v-for="item in popupList" :key="item.oid">
												<!-- check -->
												<td>
													<el-checkbox-group v-model="checkList">
														<el-checkbox :label="item" class="no-label">{{ }}</el-checkbox>
													</el-checkbox-group>
												</td>
												<!-- no -->
												<td>
													<span v-text="item.no"></span>
												</td>
												<!-- title -->
												<td class="tl active txt-hover" @click="goModify(item)">
													<span v-text="item.name"></span>
												</td>
												<!-- 노출기간 -->
												<td>
													<span v-text="displayPeriodDate(item)"></span>
												</td>
												<!-- 노출여부 -->
												<td>
													<el-switch
														:value="displayUseYn(item)"
														active-text="노출"
														inactive-text="비노출"
														style="display: block"
														@change="updateStatusTypeFlag(item)"
													>
													</el-switch>
												</td>
												<td>
													<span v-text="setPopupViewTypeFlag(item)"></span>
												</td>
												<!-- 등록자 -->
												<td>
													<span v-text="setInputUser( item )">유재석</span>
												</td>
												<!-- 등록일 -->
												<td>
													<span v-text="$common.formatDate(item.inputDate)"></span>
												</td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
								<the-pagination
									v-if="0 < listCount"
									:current-page="currentPage"
									:page-size="pageSize"
									:total-count="listCount"
									:visible-buttons-count="5"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import TheSearch from "~/components/common/search/TheSearch";
import ThePagination from "~/components/common/ThePagination";

export default {
	layout: "managerLayout",
	components : { TheSearch, ThePagination },
	data() {
		return {

			allCheck  : false,                    // 전체 선택 여부
			checkList : [],                      // 삭제할 리스트

			popupList : [],

			pageSize    : 20,
			currentPage : 1,
			thisPage    : 1,
			listCount   : 0,
			lastPage    : 1,

			searchDate          : [], // 검색 일자
			statusTypeFlag      : "",
			selectedKeyword     : "",
			selectedKeywordList : [
				{ label : "제목", value : "name" },
				{ label : "등록자", value : "inputUser" },
			],
			searchText          : "",
		};
	},
	computed : {},
	async fetch() {
		this.selectedKeyword = this.selectedKeywordList[0].value;
		await this.getList( 1 );
	},
	watch   : {

		$route() {
			if ( this.$route.query.page ) {
				this.onChangePage( parseInt( this.$route.query.page ) );
			}
			else {
				this.onChangePage( 1 );
			}
		},
	},
	methods : {
		/* ThePageCntOption.vue에서 받아온 페이지 보기 개수 */
		setPageSize( pageCnt ) {
			this.pageSize = pageCnt;
		},

		// 등록기간 설정
		updateDate( data ) {
			this.searchDate = data.searchDate;
		},

		// edit 창으로 이동
		goEdit() {
			this.$router.push( this.$urlConstant.MENU_URL_PREFIX.MANAGER_POPUP_MGNT + this.urlConstant.MENU_URL_SUFFIX.POPUP_MGNT.POPUP_EDIT );
		},

		// view 창으로 이동
		goModify( popupInfo ) {

			this.$router.push( {
				path  : '/kccam/manager/popupMgnt/popup/popupMgnt_edit',
				query : { popupOid : popupInfo.oid }
			} );
		},

		/* 체크박스 전체 선택 또는 해제 */
		setAllChecked() {

			this.popupList.forEach( popup => {

				if ( this.allCheck ) {

					this.checkList.push( popup );
				}
				else {

					this.checkList = [];
				}
			} );
		},

		displayPeriodDate( item ) {
			return (
				this.$common.formatDate( item.startDate ) +
				" ~ " +
				this.$common.formatDate( item.endDate )
			);
		},

		displayUseYn( item ) {
			return this.$amConstant.FLAG_YN.YES === item.useYn ? true : false;
		},

		getList( startIndex ) {
			let param = {
				startIndex     : startIndex,
				pageSize       : this.pageSize,
				statusTypeFlag : this.statusTypeFlag,
			};

			if ( this.$common.isNotEmpty( this.searchDate ) ) {

				param.startDate = this.$common.formatDateToString( this.searchDate[ 0 ] );
				param.endDate = this.$common.formatDateToString( this.searchDate[ 1 ] );
			}

			if ( this.$common.isNotEmpty( this.searchText ) && this.$common.isNotEmpty( this.selectedKeyword ) ) {

				param[ this.selectedKeyword ] = this.searchText;
			}

			this.$axios.post( this.$urlConstant.API_URL_PREFIX.POPUP + this.$urlConstant.API_URL_SUFFIX.POPUP.LIST, param ).then( res => {

				if ( this.$common.isEmpty( res.data.list ) ) {
					this.popupList = [];
					this.listCount = 0;
					return;
				}

				this.popupList = res.data.list;
				this.listCount = res.data.listCount;
				this.lastPage = this.$common.getLastPage( this.listCount, this.pageSize );

				this.popupList.forEach( ( item, index ) => {
					item.no = this.$common.settingNo( this.listCount, startIndex, index );
				} );
			} );
		},

		/* 삭제버튼 클릭시 */
		deletePopup() {

			if ( this.$common.isEmpty( this.checkList ) ) {
				this.$common.confirmSwal(
					"필수 체크",
					"한 개 이상의 삭제할 팝업을 선택해주세요.",
					"warning",
				);
				return;
			}

			let portalPopupList = this.checkList.filter( popup => this.isPortalPopup( popup ) );

			if ( portalPopupList.length > 0 ) {
				this.$common.confirmSwal( "삭제 불가",
					"통합 팝업이 포함되었습니다.\n통합 팝업은 포탈에서 삭제 가능합니다.",
					"warning" );
				return;
			}

			this.$common
				.swalWithOptions( "삭제 확인", "정말 삭제하시겠습니까?", "warning" )
				.then( willEdit => {
					if ( !willEdit ) {
						return;
					}
					this.$axios.post( this.$urlConstant.API_URL_PREFIX.POPUP + this.$urlConstant.API_URL_SUFFIX.POPUP.DELETE, this.checkList.map( popup => popup.oid ) ).then( res => {
						if ( res.data < 1 ) {
							return;
						}
						this.getList( 1 );
					} );
				} );
		},

		onChangePage( page ) {
			this.currentPage = page;
			let thisPage = ( parseInt( this.currentPage ) - 1 ) * parseInt( this.pageSize ) + 1;
			this.getList( thisPage );
		},

		updateStatusTypeFlag( item ) {

			let param = {

				oid            : item.oid,
				statusTypeFlag : this.$amConstant.FLAG_YN.YES === item.statusTypeFlag ? this.$amConstant.FLAG_YN.NO : this.$amConstant.FLAG_YN.YES,
			}

			this.$axios.post( this.$urlConstant.API_URL_PREFIX.POPUP + this.$urlConstant.API_URL_SUFFIX.POPUP.UPDATE_STATUS, param ).then( res => {

				if ( this.$common.isEmpty( res.data ) ) {
					return;
				}

				this.$fetch();
			} );
		},

		setPopupViewTypeFlag( item ) {

			if ( this.$amConstant.POPUP_TYPE.VIEW.LIST.KEY === item.popupViewTypeFlag ) {

				return this.$amConstant.POPUP_TYPE.VIEW.LIST.VALUE;
			}
			else if ( this.$amConstant.POPUP_TYPE.VIEW.GENERAL.KEY === item.popupViewTypeFlag ) {

				return this.$amConstant.POPUP_TYPE.VIEW.GENERAL.VALUE;
			}
		},

		// 초기화
		reset() {

			this.statusTypeFlag = "";
			this.selectedKeyword = "";
			this.searchText = "";
		},

		search() {
			this.getList( 1 );
		},
		setInputUser( popupInfo ) {

			if ( this.isPortalPopup( popupInfo ) ) {
				return "포탈시스템";
			}
			if ( this.$common.isNotEmpty( popupInfo.extraInfoMap ) && this.$common.isNotEmpty( popupInfo.extraInfoMap.inputUserName ) ) {
				return popupInfo.extraInfoMap.inputUserName;
			}
		},

		isPortalPopup( popupInfo ) {
			return this.$common.isNotEmpty( popupInfo.customField4 );
		},
	},
}
</script>
<style scoped>
</style>
