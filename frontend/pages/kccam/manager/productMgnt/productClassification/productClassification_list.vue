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
                    <h2>제품구분 관리 목록</h2>
                </div>
                <div class="btn">
                    <el-button type="st st-primary" size="small" @click="goAllView()">제품구분 전체 보기</el-button>
                    <el-button type="primary" size="small" @click="goEdit()">신규 제품구분 등록</el-button>
                </div>
            </div>
            <!-- table -->
            <div class="content-detail">
                <!--search area -->
                <div class="search-area">
                    <!-- Depth -->
                    <div class="input-row">
                        <div class="label">
                            <span class="input-tit">소재 구분</span>
                        </div>
                        <div class="data">
                            <el-select size="sm" v-model="searchType" placeholder="선택">
                                <el-option v-for="item in searchOptions" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                            </el-select>
                            <el-input size="large" placeholder="검색어 입력" prefix-icon="el-icon-search" v-model="searchInput" clearable @keypress.enter.native="searchList"> </el-input>
                        </div>
                    </div>

                    <!-- 검색 버튼 -->
                    <div class="btn-wrap">
                        <el-button class="search-btn" type="gray" size="default" @click="getList(1)">검색</el-button>
                    </div>
                </div>

                <!-- 등록 버튼 -->
                <!-- <div class="btn btn-regist">
                    <el-button type="gray" size="small" @click="goEdit()">신규 제품구분 등록</el-button>
                </div> -->

                <!-- table -->
                <div class="manager-table-normal">
                    <table>
                        <colgroup>
                            <col style="width:8%" />
                            <col style="width:19.25%" />
                            <col style="width:19.25%" />
                            <col style="width:19.25%" />
                            <col style="width:19.25%" />
                            <col style="width:15%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th scope="col"><span>번호</span></th>
                                <th scope="col"><span>제품구분명</span></th>
                                <th scope="col"><span>소재구분</span></th>
                                <!-- <th scope="col"><span>상위 제품 구분</span></th> -->
                                <th scope="col"><span>제품 수</span></th>
                                <th scope="col"><span>등록일</span></th>
                                <th scope="col"><span>최종 수정일</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if="$common.isEmpty(productClassificationList)">
                                <td colspan="6">
                                    <!-- no-data(loading) -->
                                    <div class="no-data" v-if="$fetchState.pending">
                                        <div class="loading-sm">
                                            <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
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
                            <tr v-for="(item, i) in productClassificationList" :key="i" class="list-item" @click="goEdit( item )">
                                <td>
                                    <span v-text="item.no"></span>
                                </td>
                                <td>
                                    <span v-text="item.name"></span>
                                </td>
                                <td>
                                    <span v-text="getMaterialName(item)"></span>
                                </td>
                                <td>
                                    <span v-text="item.productCnt"></span>
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
                <thePagination v-if="0 < listCount" :visible-buttons-count="2" :total-count="listCount" :page-size="pageSize" :current-page="currentPage" />
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
            // 선택
			searchOptions : [],

            searchType : "",

            searchInput: "",

            // 제품구분 관리 목록
            productClassificationList: [],

            // 페이지네이션
            startIndex: 1,

            thisPage: 1,
            listCount: 0,
            pageSize: 10,
            currentPage: 1,

        };
    },
    watch: {
        $route() {
        	if( this.$route.query.page ) {
        		this.onChangePage( parseInt( this.$route.query.page ) );
			}
        	else {
				this.$fetch();
			}
        },
    },

    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
		getMaterialMap () {
			return this.$store.state.menu.materialMap;
		},
    },

    async fetch() {

        if( this.$route.query.searchInput ) {
            this.searchInput = this.$route.query.searchInput;
        }
        if( this.$route.query.page ) {
        	await this.onChangePage( parseInt( this.$route.query.page ) );
		}
        else {
        	await this.getList(1);
		}

		// 검색옵션 리스트 생성
		this.searchOptions = this.reformMaterialMap();

    },

    methods: {

        onChangePage( page ) {
            this.currentPage = page;
            this.thisPage = (  this.currentPage - 1 ) *  this.pageSize + 1;
            this.getList( this.thisPage );
        },
		async searchList() {
        	this.$router.push(this.localePath({
				path : "/kccam/manager/productMgnt/productClassification/productClassification_list",
				query : {
					searchInput : this.searchInput,
				}
			}));
		},
		/**
		 * 페이지 번호로 StartIndex를 가져옵니다.
		 */
		getStartIndexFromPage( page ) {
			let startIndex = ( page - 1 ) * this.pageSize + 1;
			return startIndex;
		},
		/**
		 * 제품구분 전체보기 페이지로 이동
		 */
		goAllView() {
			this.$router.push(this.localePath("/kccam/manager/productMgnt/productClassification/productClassification_allView"));
		},

		/**
		 * 수정/등록 페이지로 이동
		 */
		goEdit( item ) {

			const url = this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT
			 			+ this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.PRODUCT_CLASSIFICATION_EDIT;

			if( this.$common.isEmpty( item ) ) {
				this.$router.push( this.localePath(url));
			}
			else {
				this.$router.push(this.localePath({
					path : url,
					query : {
						oid : item.oid,
					}
				}));
			}
		},

        /**
		 * 제품구분 리스트 불러오기
		 */
        async getList( startIndex ) {

            let cnd = {
                startIndex: startIndex,
                pageSize: this.pageSize,
                categoryType: this.$amConstant.CATEGORY_TYPE.PRODUCT,
                exceptCategoryOidList: [this.$amConstant.ROOT_CATEGORY_OID.PRODUCT],
                fillMaterial: true,
                fillProductCnt: true,
	            orderByInputDate : true,
            };

            // 검색 경우
            if ( this.searchInput || this.searchType ) {
				cnd.partOid = this.searchType;
                cnd.searchText = this.searchInput;
            }
            const url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST;
	        await this.$axios.post( url, cnd ).then( res => {
            	if( this.$common.isEmpty( res.data.list ) ) {
            		this.productClassificationList = [];
            		this.listCount = 0;
            		return;
				}
                this.productClassificationList = res.data.list;
                this.listCount = res.data.totalCount;
                this.productClassificationList.forEach( (item,i) => {
                   item.no = this.$common.settingNo( this.listCount, startIndex, i );
               } );

            }).catch( error => { console.log(error); });
        },

		/**
		 * 소재 구분 옵션 리스트
		 */
		reformMaterialMap() {
			let data = this.getMaterialMap;
			let tempArr = [];
			tempArr.push({
				label : "전체",
				value : "",
			})
			_.each( data, function( item, index ) {
				let tempObj = {
					label : item.materialName,
					value : index,
				};
				tempArr.push(tempObj);
			});
			return tempArr;
		},

        getMaterialName(item) {
            if ( this.$common.isEmpty( item ) || this.$common.isEmpty( item.materialInfo ) || this.$common.isEmpty( item.materialInfo.name ) ) {
                return "-";
            }

            return item.materialInfo.name;
        }
    },
};
</script>

<style scoped></style>
