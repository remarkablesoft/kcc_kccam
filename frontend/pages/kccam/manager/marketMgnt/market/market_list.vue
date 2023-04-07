<template>
    <div class="inner-wrapper">
        <!-- manager-content-body -->
        <div class="manager-content-body">
            <div class="content-title mt">
                <div class="sub-title">
                    <h2>Market 목록</h2>
                </div>
                <div class="btn">
                    <el-button type="st st-primary" size="small" @click="goAllView()">Market 전체 보기</el-button>
                    <el-button type="primary" size="small" @click="goEdit()">신규 Market 등록</el-button>
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
                            <!-- <el-select size="sm" v-model="depthValue" placeholder="전체">
                                <el-option v-for="item in marketDepth" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                            </el-select> -->
                            <el-input size="large" placeholder="Market명 검색" @keypress.enter.native="search()" prefix-icon="el-icon-search" v-model="searchText" clearable>
                            </el-input>
                        </div>
                    </div>
                    <!-- 검색 버튼 -->
                    <div class="btn-wrap">
                        <el-button class="search-btn" type="gray" size="default" @click="search()">검색</el-button>
                    </div>
                </div>

                <!-- 신규 Market 등록 버튼 -->
                <!-- <div class="btn btn-regist">
                    <el-button type="gray" size="small" @click="goEdit()">신규 Market 등록</el-button>
                </div> -->

                <!-- table -->
                <div class="manager-table-normal">
                    <table>
                        <colgroup>
                            <col style="width:8%" />
                            <col style="width:%" />
                            <col style="width:17%" />
                            <col style="width:17%" />
                            <!-- <col style="width:17%" /> -->
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th><span>번호</span></th>
                                <th><span>Market명</span></th>
                                <!-- <th><span>Depth</span></th> -->
                                <th><span>등록일</span></th>
                                <th><span>최종 수정일</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if="$common.isEmpty(marketList)">
                                <td colspan="5">
                                    <!-- no-data(loading) -->
                                    <div class="no-data" v-if="marketListLoading">
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
                            <tr v-for="(item, i) in marketList" :key="i" class="list-item" @click="goEdit(item)">
                                <td>
                                    <span v-text="item.no"></span>
                                </td>
                                <td>
                                    <span v-text="item.name"></span>
                                </td>
                                <!-- <td>
									<span v-text="item.depthVC"></span>
								</td> -->
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
                    v-if="0 < listCount"
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
            //Depth
            depthValue: "",
            marketDepth: [
                {
                    value: "전체",
                    label: "전체",
                },
                {
                    value: "Option2",
                    label: "Option2",
                },
                {
                    value: "Option3",
                    label: "Option3",
                },
            ],
            //검색어
            searchText: "",

            //Market 목록 - table
            marketList: [],
			marketListLoading : false,

            // 페이지네이션
            startIndex: 1,
			pageSize: 10,
			currentPage: 1,
            thisPage: 1,
            lastPage: 0, // 마지막 페이지
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
			const url_edit = this.$urlConstant.MENU_URL_PREFIX.MANAGER_MARKET_MGNT + this.$urlConstant.MENU_URL_SUFFIX.MARKET.MARKET_EDIT;
			let pushObj = {
				path : url_edit,
				query : {
                    editPage : 1
                }
			};

			if( item ){ //수정 페이지에서 필요
				pushObj.query.oid = item.oid;
			}

			if ( this.searchText ){
				pushObj.query.searchText = this.searchText;
			}

			this.$router.push( this.localePath( pushObj ) );
        },
        //마켓 리스트 불러오기
        async getList( startIndex ) {
            let cnd = {
                startIndex: startIndex,
                pageSize: this.pageSize,
                categoryType: this.$amConstant.CATEGORY_TYPE.MARKET,
                exceptCategoryOidList : [ this.$amConstant.ROOT_CATEGORY_OID.MARKET ],
	            orderByInputDate : true,
            };
            //검색하는 경우
            if (this.searchText) {
                cnd.searchText = this.searchText;
            }

            const urlList = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST;
			this.marketList = [];
			this.marketListLoading = true;
            await this.$axios.post( urlList, cnd ).then( res => {

            	this.listCount = res.data.totalCount;

                if ( this.$common.isEmpty( res.data.list ) ) {
                    this.marketList = [];
					this.marketListLoading = false;
                    return;
                }
				this.marketList = res.data.list;
				this.marketListLoading = false;

				res.data.list.forEach( ( item, index ) => {
					item.no = this.$common.settingNo( this.listCount, startIndex, index );
				});
            });
        },

        // Market 전체보기 페이지로 이동
        goAllView() {
            this.$router.push( this.localePath( "/kccam/manager/marketMgnt/market/market_allView" ) );
        },
    },
};
</script>

<style scoped></style>
