<template>
    <!-- 아이템 코드 추가 모달 -->
    <el-dialog title="제품 구분 추가" :visible.sync="dialogVisible" width="80rem">
        <div class="inner-content">
            <!--search area -->
            <div class="search-area">
                <div class="input-row">
                    <div class="data">
                        <el-input size="large" placeholder="제품구분명 검색" prefix-icon="el-icon-search" v-model="searchInput" @keypress.enter.native="search" clearable> </el-input>
                        <!-- 검색 버튼 -->
                        <div class="btn-wrap">
                            <el-button class="search-btn" type="gray" size="default" @click="search">검색</el-button>
							<el-button class="search-btn" type="primary" size="default" @click="getTotalList">전체목록</el-button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- list area -->
            <div class="list-area">
                <div class="manager-table-normal">
                    <div class="table-body">
                        <table>
                            <colgroup>
                                <col style="width:8%" />
                                <col style="width:20%" />
                                <col style="width:20%" />
                                <col style="width:auto" />
                            </colgroup>
                            <thead>
                                <tr class="bg-lgray">
                                    <th><span>선택</span></th>
                                    <th><span>제품구분명</span></th>
                                    <th><span>소재명</span></th>
                                    <th><span>설명</span></th>
                                </tr>
                            </thead>

                            <tbody>
                                <!-- no-data :: tr -->
                                <tr v-if="$common.isEmpty(list)">
                                    <td colspan="4">
                                        <!-- no-data(loading) -->
                                        <div class="no-data" v-if="listLoading">
                                            <div class="loading-sm">
                                                <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                            </div>
                                            <p>데이터 로딩중입니다.</p>
                                        </div>
                                        <!-- no-data -->
                                        <div class="no-data" v-if="!listLoading">
                                            <i class="material-icons">error_outline</i>
                                            <p>데이터가 없습니다.</p>
                                        </div>
                                    </td>
                                </tr>
                                <tr v-for="(item, i) in list" :key="i" class="list-item">
                                    <td>
                                        <div class="custom-radio">
                                            <input :id="item.oid" :value="item" v-model="selectedItem" type="radio" :key="item.oid" name="listRadioGroup" />
                                            <label :for="item.oid">
                                                <i></i>
                                                <!-- <span>텍스트</span> -->
                                            </label>
                                        </div>
                                    </td>
                                    <td @click="setMaterialSelected(item)">
										<span v-text="item.name"></span>
                                    </td>
									<td @click="setMaterialSelected(item)">
										<span v-text="$common.isNotEmpty( item.materialInfo ) ? item.materialInfo.name : '-'"></span>
                                    </td>
									<td @click="setMaterialSelected(item)">
										<v-clamp :max-lines="2" class="txt">{{item.descr}}</v-clamp>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <!-- 페이지네이션 -->
                        <thePagination
                            v-if="listCount > 0"
                            :visible-buttons-count="5"
                            :total-count="listCount"
                            :page-size="pageSize"
                            :current-page="currentPage"
                            page-comp="classificationPage"
                        />
                    </div>
                </div>
            </div>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button size="default" type="primary" @click="getProductClassification">완료</el-button>
            <el-button size="default" @click="dialogVisible = false">취소</el-button>
        </div>
    </el-dialog>
</template>

<script>
import thePagination from "~/components/common/ThePagination.vue";
import VClamp from "vue-clamp";

export default {
    props: {},
    components: {
        thePagination,
        VClamp
    },
    data() {
        return {
            dialogVisible: false,

            // 제품 검색창
            searchInput: "",

            // 제품 추가 목록
            list : [],
            selectedItem : {},   // 선택된 제품

            startIndex : 1,
            listCount : 0,
            pageSize : 10,
            currentPage : 1,
	        listLoading : false,
			getTotal : false,
	        thisPage : 1,
        };
    },
    created() {},
    mounted() {},
    watch: {
        $route() {
            if ( this.$route.query.classificationPage && ( parseInt( this.$route.query.classificationPage ) !== this.currentPage ) ) {
	            this.onChangePage( parseInt ( this.$route.query.classificationPage ) );
            }
        }
    },
    methods: {
        open() {
            this.dialogVisible = true;
        },
	    /**
	     *  페이지 이동 처리
	     */
	    onChangePage( page ) {
		    this.currentPage = page;
		    this.thisPage = ( parseInt ( this.currentPage ) - 1) * parseInt( this.pageSize ) + 1;
		    this.getProductClassificationList( this.thisPage );
	    },
		/**
		 * 전체 리스트를 가져옵니다.
		 */
		getTotalList() {
			this.getTotal = true;
			this.getProductClassificationList(1);
		},
		search() {
			this.getTotal = false;
			if ( this.$common.isEmpty( this.searchInput ) && !( this.getTotal ) ) {
				this.$common.swalWithOptions("검색어를 입력해주세요.", "", "warning");
				return;
			}
			this.getProductClassificationList(1);
		},

        /**
         * 제품구분 검색 리스트를 가져옵니다.
         */
        getProductClassificationList(page) {
            if ( page < 1 ) {
                return;
            }
            if ( this.$common.isNotEmpty( this.$route.query.classificationPage ) ) {
                this.startIndex = ( this.$route.query.classificationPage -1 ) * this.pageSize + 1;
                this.currentPage = Number( this.$route.query.classificationPage );
            }
            // 제품구분 Root 카테고리, 자기 자신 제품 제외
            let exceptCategoryOidList = [this.$amConstant.ROOT_CATEGORY_OID.PRODUCT];

            if( this.$common.isNotEmpty( this.$route.query.oid ) ){
            	exceptCategoryOidList.push( this.$route.query.oid );
            }

	        const url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST;
	        if ( this.getTotal ) {
	        	this.searchInput = "";
			}
            let param = {
                startIndex : this.startIndex,
                pageSize : this.pageSize,
                categoryType : this.$amConstant.CATEGORY_TYPE.PRODUCT,
                exceptCategoryOidList : exceptCategoryOidList,
                fillMaterial : true,
                searchText : this.searchInput
            };
			this.list = [];
			this.listLoading = true;
			// console.log( param );
            this.$axios.post( url, param ).then( res => {
                if ( this.$common.isEmpty( res.data ) ) {
	                this.listLoading = false;
                    this.list = [];
                    this.listCount = 0;
                    return;
                }
	            this.listLoading = false;
                this.list = res.data.list;
                this.listCount = res.data.totalCount;
            }).catch( error => {
	            this.listLoading = false;
	            console.log( error );
            });

        },

        /**
         * 제품 구분 리스트에서 선택된 제품을 가져옵니다.
         */
        getProductClassification() {
            this.$emit( "getProductClassification", this.selectedItem );
            this.dialogVisible = false;
        },
		setMaterialSelected( item ) {
        	this.selectedItem = item;
		}
    },
};
</script>

<style></style>
