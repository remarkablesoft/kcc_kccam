<template>
	<div class="inner-wrapper">

		<!-- manager-content-body -->
		<div class="manager-content-body">
			<div class="content-title">
				<div class="sub-title">
					<h2>화면 조회 이력</h2>
				</div>
			</div>
			<div class="content-detail">
				<!--search area -->
				<div class="search-area">
					<!-- 카탈로그명 검색 -->
					<div class="input-row">
						<div class="label">
							<span class="input-tit">조회 화면</span>
						</div>
						<div class="data">
							<el-input size="large" placeholder="조회 화면 검색" @keypress.enter.native="search()" prefix-icon="el-icon-search" v-model="searchText" clearable>
							</el-input>
						</div>
					</div>
					<!-- 검색일 -->
					<div class="input-row">
						<div class="label">
							<span class="input-tit">조회 기간</span>
						</div>
						<div class="data">
							<el-date-picker
								size="large"
								v-model="searchDateRange"
								type="daterange"
								align="right"
								start-placeholder="시작일"
								end-placeholder="종료일"
								value-format="yyyy-MM-dd"
							>
							</el-date-picker>
						</div>
					</div>
					<!-- 검색 버튼 -->
					<div class="btn-wrap">
						<el-button class="search-btn" type="gray" size="default" @click="search()">검색</el-button>
					</div>
				</div>
				<!-- table -->
				<div class="manager-table-normal">
					<table>
						<colgroup>
							<col style="width:10%" />
							<col style="width:10%" />
							<col style="width:20%" />
							<col style="width:45%" />
							<col style="width:15%" />
						</colgroup>
						<thead>
						<tr class="bg-lgray">
							<th><span>번호</span></th>
							<th><span>접근 IP</span></th>
							<th><span>조회 화면</span></th>
							<th><span>조회 URL</span></th>
							<th><span>조회 일시</span></th>
						</tr>
						</thead>
						<tbody>
						<!-- no-data :: tr -->
						<tr v-if="$common.isEmpty( histVisitList )">
							<td colspan="5">
								<!-- no-data(loading) -->
								<div class="no-data" v-if="histVisitListLoading">
									<div class="loading-sm">
										<img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
									</div>
									<p>데이터 로딩중입니다.</p>
								</div>
								<!-- no-data -->
								<div class="no-data" v-else>
									<i class="material-icons">error_outline</i>
									<p>데이터가 없습니다.</p>
								</div>
							</td>
						</tr>
						<tr v-for="(item, i) in histVisitList" :key="i" class="list-item">
							<td>
								<span v-text="item.no"></span>
							</td>
							<td>
								<span v-text="item.userIp"></span>
							</td>
							<td>
								<span v-text="item.visitPageName"></span>
							</td>
							<td>
								<span v-text="item.visitURL"></span>
							</td>
							<td>
								<span v-text="item.inputDate"></span>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				<!-- 페이지네이션 -->
				<the-pagination
					v-if=" 0 < listCount "
					:visible-buttons-count="10"
					:total-count="listCount"
					:page-size="pageSize"
					:current-page="currentPage"
				/>
			</div>
		</div>
	</div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";

export default {
	layout: "managerLayout",
	components: {
		theLoading,
		thePagination,
	},
	data() {
		return {

			histVisitList : [],
			histVisitListLoading : false,

			listCount: 0,
			pageSize: 10,
			currentPage: 1,
			thisPage : 1,
			startIndex : 1,

			// 검색어
			searchText : "",
			// 검색일 - calendar
			searchDateRange : null,


		};
	},
	async fetch() {
		this.histVisitList = [];
		await this.getHistVisitList( 1 );
	},
	watch: {
		$route() {
			if ( this.$route.query.page ) {
				this.onChangePage( parseInt( this.$route.query.page ) );
			} else {
				this.onChangePage( 1 );
			}
		},
	},
	computed : {
	},
	methods: {

		search() {
			this.currentPage = 1;
			this.startIndex = 1;
			this.getHistVisitList( this.startIndex );
		},

		// 페이지 이동 처리
		onChangePage( page ) {
			this.currentPage = page;
			this.thisPage = ( parseInt( this.currentPage ) - 1 ) * parseInt( this.pageSize ) + 1;
			this.getHistVisitList( this.thisPage );
		},

		/**
		 * 방문 이력 목록을 호출합니다.
		 */
		async getHistVisitList( startIndex ) {

			this.histVisitListLoading = true;
			this.histVisitList = [];

			const HIST_VISIT_LIST = this.$urlConstant.API_URL_PREFIX.HIST_VISIT
									+ this.$urlConstant.API_URL_SUFFIX.HIST_VISIT.LIST;

			let cnd = {
				startIndex : startIndex,
				pageSize : this.pageSize,
			};

			if ( this.$common.isNotEmpty( this.searchText ) ) {
				cnd.searchText = this.searchText;
			}
			if ( this.$common.isNotEmpty( this.searchDateRange ) ) {
				cnd.fromDate = this.searchDateRange[0];
				cnd.toDate = this.searchDateRange[1];
			}

			await this.$axios.post( HIST_VISIT_LIST, cnd ).then( res => {
				this.listCount = res.data.totalCount;
				if ( this.$common.isEmpty( res.data.list ) ) {
					this.histVisitList = [];
					return;
				}
				this.histVisitList = res.data.list;
				res.data.list.forEach( ( item, index ) => {
					item.no = this.$common.settingNo( this.listCount, startIndex, index );
				});

			} );
			this.histVisitListLoading = false;
		},

	},
};
</script>

<style scoped>
.manager-container .manager-table-normal table tr:hover {
	cursor : auto;
}
.list-item td span {
	word-break: break-word;
}
</style>
