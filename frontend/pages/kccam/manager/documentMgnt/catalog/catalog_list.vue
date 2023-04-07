<template>
	<div class="inner-wrapper">
		<!-- loading -->
		<!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
		<!--            <the-loading />-->
		<!--        </div>-->
		<!-- manager-content-body -->
		<div class="manager-content-body">
			<div class="content-title">
				<div class="sub-title">
					<h2>카탈로그 목록</h2>
				</div>
			</div>
			<div class="content-detail">
				<!--search area -->
				<div class="search-area">
					<!-- 카탈로그명 검색 -->
					<div class="input-row">
						<div class="label">
							<span class="input-tit">카탈로그</span>
						</div>
						<div class="data">
							<el-input size="large" placeholder="카탈로그명 검색" @keypress.enter.native="search()" prefix-icon="el-icon-search" v-model="catalogName" clearable>
							</el-input>
						</div>
					</div>
					<!-- 검색일 -->
					<div class="input-row">
						<div class="label">
							<span class="input-tit">등록일</span>
						</div>
						<div class="data">
							<el-select size="sm" v-model="inputDateSelect" placeholder="선택">
								<el-option v-for="item in searchDate" :key="item.value" :label="item.label" :value="item.value"></el-option>
							</el-select>
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
						<el-button class="search-btn" type="gray" size="default" @click="$fetch()">검색</el-button>
					</div>
				</div>
				<!-- table -->
				<div class="manager-table-normal">
					<table>
						<colgroup>
							<col style="width:8%"/>
							<col style=""/>
							<col style="width:20%"/>
							<col style="width:20%"/>
						</colgroup>
						<thead>
						<tr class="bg-lgray">
							<th>
								<span>번호</span>
							</th>
							<th>
								<span>카탈로그명</span>
							</th>
							<th>
								<span>등록일</span>
							</th>
							<th>
								<span>최종 수정일</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<!-- no-data :: tr -->
						<tr v-if="$common.isEmpty(catalogList)">
							<td colspan="4">
								<!-- no-data(loading) -->
								<div class="no-data" v-if="$fetchState.pending">
									<div class="loading-sm">
										<img src="@/assets/images/loading/loading_sm.svg" alt="Loading"/>
									</div>
									<p>데이터 로딩중입니다.</p>
								</div>
								<!-- no-data -->
								<div class="no-data" v-if="!$fetchState.pending">
									<i class="material-icons">error_outline</i>
									<p>데이터가 없습니다.</p>
								</div>
							</td>
						</tr>
						<tr v-for="(item, i) in catalogList" :key="i" class="list-item" @click="goEdit(item.oid)">
							<td>
								<span v-text="$common.settingNo(catalogListCnt,currentPage,i)"></span>
							</td>
							<td>
								<span v-text="item.title"></span>
							</td>
							<td>
								<span v-text="$common.formatDate(item.inputDate)"></span>
							</td>
							<td>
								<span v-text="$common.formatDate(item.modDate)"></span>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				<!-- 페이지네이션 -->
				<thePagination
					v-if=" 0 < catalogListCnt "
					:visible-buttons-count="10"
					:total-count="catalogListCnt"
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
	layout     : "managerLayout",
	components : {
		theLoading,
		thePagination,
	},
	data() {
		return {
			//카탈로그명
			catalogName : "",

			//검색일
			searchDate : [
				{
					value : "",
					label : "전체",
				},
				{
					value : "INPUT_DATE",
					label : "등록일",
				},
				{
					value : "EDIT_DATE",
					label : "최종 수정일",
				},

			],
			//검색일 - calendar
			inputDateSelect : "",
			searchDateRange : null,

			//카탈로그 목록 - table
			catalogList    : [],
			catalogListLoading : false,
			catalogListCnt : 0,

			// 페이지네이션
			startIndex: 1,
			pageSize: 10,
			currentPage: 1,
			thisPage: 1,
			lastPage : 0,
		};
	},
	async fetch() {

		await this.getCatalogList(1);

	},
	watch   : {
		$route() {
			if (this.$route.query.page) {
				this.onChangePage(parseInt(this.$route.query.page));
			} else {
				this.onChangePage(1);
			}
		},
		searchDateRange( newVal ) {
			console.log( "searchDateRange", newVal )
		}
	},
	methods : {
		/**
		 * 검색
		 */
		search() {
			this.onChangePage( 1 );
		},
		onChangePage( page ) {
			this.currentPage = page;
			this.thisPage = (parseInt(this.currentPage) - 1) * parseInt(this.pageSize) + 1;
			this.getCatalogList(this.thisPage);
		},
		// 수정/등록 페이지로 이동
		goEdit( oid ) {
			this.$router.push( this.localePath( "/kccam/manager/documentMgnt/catalog/catalog_edit?oid=" + oid ) );
		},

		async getCatalogList( startIndex ) {
			const URL_LIST = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.LIST

			let param = {
				startIndex : startIndex,
				pageSize : this.pageSize,
				docType    : this.$amConstant.SFA_DOC_KIND_KEY.CATALOG
			}

			// 카탈로그 명 검색 세팅
			if ( this.$common.isNotEmpty( this.catalogName ) ) {
				param.title = this.catalogName
			}

			// 날짜 검색 세팅
			if ( this.$common.isNotEmpty( this.inputDateSelect ) && this.$common.isNotEmpty( this.searchDateRange ) ) {
				param.searchDateType = this.inputDateSelect;
				param.fromDate = this.searchDateRange[ 0 ]
				param.toDate = this.searchDateRange[ 1 ]
			}

			// console.log("param", param)
			this.catalogList = [];
			this.catalogListLoading = true;
			await this.$axios.post( URL_LIST, param ).then( ( res ) => {

				this.catalogListCnt = res.data.totalCount;

				if( this.$common.isEmpty( res.data.list ) ) {
					this.catalogListLoading = false;
					this.catalogList = [];
					return;
				}
				this.catalogList = res.data.list;
				this.catalogListLoading = false;
				res.data.list.forEach( ( item, index ) => {
					item.no = this.$common.settingNo( this.catalogListCnt, startIndex, index );
				});
			} ).catch();
		}


	},
};
</script>
<style scoped></style>
