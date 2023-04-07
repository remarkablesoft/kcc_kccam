<template>
    <div class="inner-wrapper">
        <!-- loading -->
<!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
<!--            <the-loading />-->
<!--        </div>-->

        <!-- manager-content-body -->
        <div class="manager-content-body">
            <div class="content-title mt">
                <div class="sub-title">
                    <h2>Newsroom 관리 목록</h2>
                </div>
                <div class="btn">
                    <el-button type="primary" size="small" @click="goEdit()">신규 NEWS 등록</el-button>
                </div>
            </div>
            <!--  content-detail -->
            <div class="content-detail">
                <!-- search area -->
                <div class="search-area">
                    <!-- 검색 항목 -->
                    <div class="input-row">
                        <div class="label">
                            <span class="input-tit">검색항목</span>
                        </div>
                        <div class="data">
                            <el-select size="sm" v-model="searchType" placeholder="전체">
                                <el-option v-for="item in searchTypeList" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                            </el-select>
                            <el-input size="large" @keypress.enter.native="getNewsList" placeholder="검색어를 입력하세요." prefix-icon="el-icon-search" v-model="searchInput" clearable> </el-input>
                        </div>
                    </div>

                    <!-- 검색 버튼 -->
                    <div class="btn-wrap">
                        <el-button class="search-btn" type="gray" size="default" @click="getNewsList()">검색</el-button>
                    </div>
                </div>

				<!-- 신규 Market 등록 버튼 -->
				<!-- <div class="btn btn-regist">
					<el-button type="gray" size="small" @click="goEdit()">신규 NEWS 등록</el-button>
				</div> -->

                <div class="manager-table-normal">
                    <table>
                        <colgroup>
	                        <col style="width: 13%" />
                            <col style="width: auto" />
                            <col style="width: 25%" />
                            <col style="width: 13%" />
                            <col style="width: 13%" />
                            <col style="width: 13%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
	                            <th><span>언어</span></th>
                                <th><span>NEWS 명</span></th>
                                <th><span>관련제품</span></th>
                                <th><span>조회수</span></th>
                                <th><span>등록일</span></th>
                                <th><span>최종 수정일</span></th>
                            </tr>
                        </thead>

                        <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if=" $common.isEmpty( newsList ) ">
                                <td colspan="5">
                                    <!-- no-data(loading) -->
                                    <div class="no-data" v-if=" $fetchState.pending ">
                                        <div class="loading-sm">
                                            <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                        </div>
                                        <p>데이터 로딩중입니다.</p>
                                    </div>
                                    <!-- no-data -->
                                    <div class="no-data" v-if=" true !== $fetchState.pending ">
                                        <i class="material-icons">error_outline</i>
                                        <p>데이터가 없습니다.</p>
                                    </div>
                                </td>
                            </tr>
                            <tr v-for="(item, index) in newsList" :key="index" class="list-item" @click="goEdit( item )">
	                            <td>
		                            <span v-text="setLangText( item.lang )"></span>
	                            </td>
                                <td>
                                    <span v-text="item.title"></span>
                                </td>
                                <td>
                                    <span v-text="setProductText( item )"></span>
                                </td>
                                <td>
                                    <span v-text="item.viewCnt"></span>
                                </td>
                                <td>
                                    <span v-text="$common.formatDate( item.inputDate )"></span>
                                </td>
                                <td>
                                    <span v-text="$common.isEmpty( item.modDate )? '-' : $common.formatDate( item.modDate ) "></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- 페이지네이션 -->
                <the-pagination
					v-if=" 0 < listCount "
					:visible-buttons-count="5"
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
            //검색타입
            searchTypeList: [
                {
                    value: "전체",
                    label: "전체",
                },
                {
                    value: "제목",
                    label: "제목",
                },
                {
                    value: "관련제품",
                    label: "관련제품",
                },
            ],
            //검색
			searchType: "전체",
			searchInput: "",

	        lastSearchInput: "",

            // 목록
            newsList: [],

			// 페이지네이션
			listCount: 0,
			pageSize: 10,
			currentPage: 1,

			lastPage: 0, // 마지막 페이지
        };
    },

	watch: {
		$route(){
			this.$fetch();
		}
	},

	async fetch(){
    	await this.getNewsList();
	},

    methods: {
        //  페이지로 이동
        goEdit( item ) {
			const urlEdit = this.$urlConstant.MENU_URL_PREFIX.MANAGER_CUSTOMER_SUPPORT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.CUSTOMER_SUPPORT_MGNT.NEWSROOM_EDIT;
            let pushObj = {
            	path : urlEdit,
				query : {},
			};

            if( this.$common.isNotEmpty( item ) ){
            	pushObj.query.oid = item.oid;
			}

			this.$router.push( this.localePath( pushObj ) );
        },
		//뉴스 리스트 불러오기
	    async getNewsList(){
		    const urlList = this.$urlConstant.API_URL_PREFIX.NEWSROOM + this.$urlConstant.API_URL_SUFFIX.NEWSROOM.LIST;

		    let startIndex = 1;

		    if ( this.$common.isNotEmpty( this.$route.query.page ) ){
		    	if( this.searchInput !== this.lastSearchInput ){
		    		this.currentPage = 1;
		    		this.lastSearchInput = this.searchInput;
			    }
		    	else {
				    startIndex = this.getStartIndexFromPage( this.$route.query.page );
				    this.currentPage = Number( this.$route.query.page );
		    	}
			}

			let cnd = {
				startIndex : startIndex,
				pageSize : this.pageSize,
				allLang : true,
				fillProductList: true,
			};

			if ( this.$common.isNotEmpty( this.searchInput ) ){
				if( this.searchType === this.searchTypeList[0].value ){
					cnd.searchQueryType = this.$amConstant.SEARCH_QUERY_TYPE.TITLE_OR_TITLE_PRODUCT;
				}
			if( this.searchType ===  this.searchTypeList[1].value ){
				cnd.searchQueryType = this.$amConstant.SEARCH_QUERY_TYPE.TITLE;
			}
				if( this.searchType === this.searchTypeList[2].value ){
					cnd.searchQueryType = this.$amConstant.SEARCH_QUERY_TYPE.TITLE_PRODUCT;
				}
				cnd.searchQuery = this.searchInput;
			}
		    // console.log( cnd );
			// return;
			await this.$axios.post( urlList, cnd ).then( res => {
				if ( res.data.totalCount === 0 ) {
					this.newsList = [];
					this.listCount = 0;
					return;
				}

				this.newsList = res.data.list;
				this.listCount = res.data.totalCount;
				this.lastPage = this.$common.getLastPage(this.listCount, this.pageSize);
			});
		},

		/**
		 * 페이지 번호로 StartIndex를 가져옵니다.
		 */
		getStartIndexFromPage(page) {
			let startIndex = (page - 1) * this.pageSize + 1;
			return startIndex;
		},

		setLangText( lang ){

			if( this.$common.isEmpty(lang) ){
				return '';
			}

			switch ( lang ){
				case 'KO' : return 'KOR'; break;
				case 'EN' : return 'ENG'; break;
				case 'CN' : return 'CHN'; break;
			}
		},

	    setProductText( item ){
			if( this.$common.isEmpty( item ) || this.$common.isEmpty( item.productList ) ){
				return '-';
			}
			// console.log( item.productList );
			let text = item.productList[0].name;

			if( item.productList.length > 1 ){
				text += ' 외 '+ (item.productList.length - 1) + '개';
			}
			return text;
	    },
    },
};
</script>

<style scoped></style>
