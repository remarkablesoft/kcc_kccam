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
					<h2>1:1 문의 목록</h2>
				</div>
				<div class="btn">
					<el-button type="st st-primary" size="small" @click="goConfig()">1:1문의 설정</el-button>
				</div>
			</div>
			<!-- table -->
			<div class="content-detail">
				<!-- search area -->
				<div class="search-area">
					<!-- 검색 항목 -->
					<div class="input-row">
						<div class="label">
							<span class="input-tit">검색항목</span>
						</div>
						<div class="data">
							<el-select size="sm" v-model="searchType" placeholder="선택">
								<el-option v-for="item in searchOptions" :key="item.value" :label="item.label"
								           :value="item.value"></el-option>
							</el-select>
							<el-input size="large" placeholder="검색어 입력" prefix-icon="el-icon-search" v-model="searchText"
							         @keypress.enter.native="search" clearable></el-input>
						</div>
					</div>
					<!-- 문서타입 -->
					<div class="input-row">
						<!-- 문의일 -->
						<div class="input-row">
							<div class="label">
								<span class="input-tit">문의일</span>
							</div>
							<div class="data">
								<el-date-picker size="large" v-model="pickedDate" type="daterange" align="right" start-placeholder="시작일"
								                end-placeholder="종료일" :default-time="['00:00:00', '23:59:59']">
								</el-date-picker>
							</div>
						</div>
					</div>
					<!-- 검색 버튼 -->
					<div class="btn-wrap">
						<el-button class="search-btn" type="gray" size="default" @click="search">검색</el-button>
					</div>
				</div>
				<div class="manager-table-normal">
					<table>
						<colgroup>
							<col style="width:8%"/>
							<col style="width:28%"/>
							<col style="width:14%"/>
							<col style="width:17%"/>
							<col style="width:17%"/>
							<col style="width:11%"/>
						</colgroup>
						<thead>
						<tr class="bg-lgray">
							<th>
								<span>번호</span>
							</th>
							<th>
								<span>문의제목</span>
							</th>
							<th>
								<span>문의종류</span>
							</th>
							<th>
								<span>문의자 이메일</span>
							</th>
							<th>
								<span>담당자 이메일</span>
							</th>
							<th>
								<span>문의일</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<!-- no-data :: tr -->
						<tr v-if="$common.isEmpty(oneToOneEnquiryList)">
							<td colspan="6">
								<!-- no-data(loading) -->
								<div class="no-data" v-if="oneToOneEnquiryListLoading">
									<div class="loading-sm">
										<img src="@/assets/images/loading/loading_sm.svg" alt="Loading"/>
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
						<tr v-for="(item, index) in oneToOneEnquiryList" :key="index" class="list-item" @click="goEdit( item.oid )">
							<td>
								<span v-text="item.no"></span>
							</td>
							<td>
								<span v-text="item.title"></span>
							</td>
							<td>
								<span v-text="item.customField1"></span>
							</td>
							<td>
								<span v-text="item.inputUserInfo.email"></span>
							</td>
							<td>
								<span v-text="item.receiverEmail"></span>
							</td>
							<td>
								<span v-text="$common.formatDate(item.inputDate)"></span>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				<!-- 페이지네이션 -->
				<thePagination v-if="0 < listCount" :visible-buttons-count="5" :total-count="listCount" :page-size="pageSize"
				               :current-page="currentPage"/>
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

			// 검색 옵션
			searchOptions : [],

			// 검색 타입
			searchType : "",

			// 검색어
			searchText : "",

			// 등록일 - calendar
			pickedDate : "",

			// 고객 문의 목록
			oneToOneEnquiryList : [],
			oneToOneEnquiryListLoading : false,

			// 페이지네이션
			startIndex  : 1,
			thisPage    : 1,
			listCount   : 0,
			pageSize    : 10,
			currentPage : 1,
		};
	},
	async fetch() {

		await this.getList(1);

		this.makeSearchOption();

		this.searchType = this.searchOptions[0].value;
	},
	watch : {

		$route() {
			if ( this.$route.query.page ) {
				this.onChangePage( parseInt( this.$route.query.page ) );
			}
			else {
				this.onChangePage( 1 );
			}
		},
	},
	computed : {
		createDateFrom() {
			if( this.pickedDate ) {
				let result = '';
				result = this.pickedDate[0].getFullYear()
					+ '-' + (this.pickedDate[0].getMonth()+ 1)
					+ '-' + this.pickedDate[0].getDate();
				return result;
			}
		},
		createDateTo(){
			if (this.pickedDate ) {
				let result = '';
				result = this.pickedDate[1].getFullYear()
					+ '-' + (this.pickedDate[1].getMonth()+ 1)
					+ '-' + this.pickedDate[1].getDate();
				return result;
			}
		}
	},
	methods : {

		//  해당 문의 상세 페이지로 이동
		goEdit( oid ) {
			const url = this.$urlConstant.MENU_URL_PREFIX.MANAGER_CUSTOMER_SUPPORT_MGNT +
						this.$urlConstant.MENU_URL_SUFFIX.CUSTOMER_SUPPORT_MGNT.PERSONAL_EDIT;
			let queryParam = {
				path : url,
				query : {
					oid : oid,
				},
			}
			this.$router.push( this.localePath( queryParam ) );
		},

		//  1대1 문의 설정 페이지로 이동
		goConfig() {
			this.$router.push( this.localePath( "/kccam/manager/customerSupportMgnt/personalEnquiry/personalEnquiry_edit" ) );
		},

		/**
		 * 페이지 이동 처리
		 */
		onChangePage( page ) {
			this.currentPage = page;
			this.thisPage = ( parseInt ( this.currentPage ) - 1 ) * parseInt( this.pageSize ) + 1;
			this.getList( this.thisPage );
		},
		/**
		 * 검색 옵션 생성
		 */
		makeSearchOption() {
			const enquiryType = this.$amConstant.ONE_TO_ONE_ENQUIRY_TYPE;
			let arr = [];
			arr.push({
				value : "ALL",
				label : "전체",
			})
			// json key value 를 loop
			for( const [key, value] of Object.entries( enquiryType ) ) {
				let obj = {};
				obj.value = key;
				obj.label = value;
				arr.push(obj);
			}
			this.searchOptions = arr;
		},

		search() {
			//검색
			this.currentPage = 1;
			this.thisPage = 1;
			this.startIndex = 1;
			this.getList( this.startIndex );
		},

		/**
		 * 1:1 문의 목록을 불러옵니다.
		 */
		async getList( startIndex ) {
			let cnd = {
				startIndex : startIndex,
				pageSize   : this.pageSize,
				likeSearch : true,
			}
			if( this.searchText ) {
				switch ( this.searchType ) {
					case `TITLE` :
						cnd.title = this.searchText;
						break;
					case `CONFIG_QUESTION` :
						cnd.customField1 = this.searchText;
						break;
					case `EMAIL` :
						cnd.userEmail = this.searchText;
						break;
					case 'ALL' :
						cnd.totalSearch = true;
						cnd.title = this.searchText;
						cnd.customField1 = this.searchText;
						cnd.userEmail = this.searchText;
						break;
				}
				cnd.createDateFrom = this.createDateFrom;
				cnd.createDateTo = this.createDateTo;
			}
			const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE.LIST;

			this.oneToOneEnquiryList = [];
			this.oneToOneEnquiryListLoading = true;

			await this.$axios.post( url, cnd ).then( res => {
				this.listCount = res.data.totalCount;
				if ( 200 === res.status && this.$common.isNotEmpty( res.data.list ) ) {
					this.oneToOneEnquiryList = res.data.list;
					res.data.list.forEach( ( item, index ) => {
						item.no = this.$common.settingNo( this.listCount, startIndex, index );
					});
				}
			} ).catch( error => {
				console.log( error );
			} );
			this.oneToOneEnquiryListLoading = false;
		},
	},
};
</script>
<style scoped></style>
