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
                    <h2>Function 목록</h2>
                </div>
                <div class="btn">
                    <el-button type="primary" size="small" @click="goEdit()">신규 Funtion 등록</el-button>
                </div>
            </div>
            <div class="content-detail">
                <!--search area -->
                <div class="search-area">
                    <!-- Depth -->
                    <div class="input-row">
                        <!-- <div class="label">
                            <span class="input-tit">Depth</span>
                        </div> -->
                        <div class="data">
                            <el-input size="large" placeholder="Function명 검색" @keypress.enter.native="search()" prefix-icon="el-icon-search" v-model="searchText" clearable>
                            </el-input>
                        </div>
                    </div>
                    <!-- 검색 버튼 -->
                    <div class="btn-wrap">
                        <el-button class="search-btn" type="gray" size="default" @click="search()">검색</el-button>
                    </div>
                </div>

                <!-- 신규 Funtion 등록 버튼 -->
                <!-- <div class="btn btn-regist">
                    <el-button type="gray" size="small" @click="goEdit()">신규 Funtion 등록</el-button>
                </div> -->

                <!-- table -->
                <div class="manager-table-normal">
                    <table>
                        <colgroup>
                            <col style="width:8%" />
                            <col style="width:%" />
                            <col style="width:15%" />
                            <col style="width:15%" />
                            <col style="width:15%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th><span>번호</span></th>
                                <th><span>Function 명</span></th>
                                <th><span>소재</span></th>
                                <th><span>등록일</span></th>
                                <th><span>최종 수정일</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if="$common.isEmpty( functionList )">
                                <td colspan="4">
                                    <!-- no-data(loading) -->
                                    <div class="no-data" v-if="functionListLoading">
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
                            <tr v-for="(item, i) in functionList" :key="i" class="list-item" @click="goEdit(item)">
                                <td>
                                    <span v-text="item.no"></span>
                                </td>
                                <td>
                                    <span v-text="item.name"></span>
                                </td>
                                <td>
                                    <span v-text="item.materialInfo.name"></span>
                                </td>
                                <td>
                                    <span v-text="$common.formatDate(item.inputDate)"></span>
                                </td>
                                <td>
                                    <span v-if="item.modDate" v-text="$common.formatDate(item.modDate)"></span>
                                    <span v-else>-</span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- 페이지네이션 -->
                <thePagination
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
            // function List
            functionList: [],
			functionListLoading : true,

            //검색어
            searchText: "",

            // 페이지네이션
            startIndex: 1,
            pageSize: 10,
            currentPage: 1,
            thisPage: 1,
			lastPage : 0,
            listCount: 0,
        };
    },
    watch: {
        $route() {
            if (this.$route.query.page) {
                this.onChangePage(parseInt(this.$route.query.page));
            } else {
                this.onChangePage(1);
            }
        },
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    async fetch() {

    	await this.getList(1);

    },
    methods: {
		/**
		 * 검색
		 */
		search() {
			this.onChangePage( 1 );
		},

        onChangePage( page ) {
            this.currentPage = page;
            this.thisPage = (parseInt(this.currentPage) - 1) * parseInt(this.pageSize) + 1;
            this.getList(this.thisPage);
        },
        // 수정/등록 페이지로 이동
		goEdit( item ) {
            const urlEdit = this.$urlConstant.MENU_URL_PREFIX.MANAGER_MARKET_MGNT + this.$urlConstant.MENU_URL_SUFFIX.MARKET.FUNCTION_EDIT;
            let pushObj = {
                path: urlEdit,
                query : {}
            };

            if ( item ){ //수정페이지용
                pushObj.query.oid = item.oid;
            }
            if ( this.searchText ){
                pushObj.query.searchQuery = this.searchText;
            }
            if ( this.$route.query.page ){
                pushObj.query.page = this.$route.query.page;
            }
			this.$router.push( this.localePath( pushObj ) );
		},

        async getList( startIndex ){
            let cnd = {
                startIndex : startIndex,
                pageSize : this.pageSize,
                categoryType : this.$amConstant.CATEGORY_TYPE.FUNCTION,
                exceptCategoryOidList : [ this.$amConstant.ROOT_CATEGORY_OID.FUNCTION ],
                fillMaterial : true,
	            orderByInputDate : true,
            };
            //function 명을 검색하는 경우
            if ( this.searchText ){
                cnd.searchText = this.searchText;
            }

            const url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST;
			this.functionList = [];
			this.functionListLoading = true;
            await this.$axios.post( url, cnd ).then( res => {
				this.listCount = res.data.totalCount;

                if ( this.$common.isEmpty( res.data.list ) ) {
                	this.functionListLoading = false;
                    return;
                }
                this.functionList = res.data.list;
				this.functionListLoading = false;
				res.data.list.forEach( ( item, index ) => {
					item.no = this.$common.settingNo( this.listCount, startIndex, index );
				});
            });
        },
    },
};
</script>

<style scoped></style>
