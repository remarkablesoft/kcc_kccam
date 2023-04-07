<template>
    <!-- 제품 추가 모달 -->
    <el-dialog title="제품 추가" :visible.sync="dialogVisible" width="60rem">
        <div class="inner-content">
            <!-- loading -->
            <div class="loading-container" v-if="loadingIndicator > 0">
                <the-loading />
            </div>

            <!--search area -->
            <div class="search-area">
                <div class="input-row">
                    <div class="data">
                        <el-select size="sm" v-model="searchType" placeholder="선택">
                            <el-option v-for="item in searchOptions" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                        </el-select>
                        <el-input size="large" placeholder="제품명/소재구분 검색" prefix-icon="el-icon-search" @keypress.enter.native="search()" v-model="searchText" clearable> </el-input>
                        <!-- 검색 버튼 -->
                        <div class="btn-wrap">
                            <el-button class="search-btn" type="gray" size="default" @click="search()">검색</el-button>
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
                                <col style="width:30%" />
                                <col style="width:%" />
                            </colgroup>
                            <thead>
                                <tr class="bg-lgray">
                                    <th scope="col"><span>선택</span></th>
                                    <th scope="col"><span>제품명</span></th>
                                    <th scope="col"><span>소재구분</span></th>
                                </tr>
                            </thead>
                        </table>

                            <!-- scroll area 1-->
                            <div v-bar="{ preventParentScroll: true }" style="height: 230px;" class="scroll-element">
                                <!-- el1 -->
                                <div>
                                    <!-- el2 -->
                                    <table>
                                        <colgroup>
                                            <col style="width:8%" />
                                            <col style="width:30%" />
                                            <col style="width:%" />
                                        </colgroup>
                                        <tbody>
                                            <!-- no-data :: tr -->
                                            <tr v-if="$common.isEmpty(productList)">
                                                <td colspan="3">
                                                    <!-- no-data(loading) -->
                                                    <div class="no-data" v-if="productListLoading">
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
                                            <tr v-for="(item, i) in productList" :key="i" class="list-item">
                                                <td>
                                                    <div class="custom-checkbox">
                                                        <input :id="item.oid" type="radio" v-model="selectedProduct" :value="item"/>
                                                        <label :for="item.oid">
                                                            <i></i>
                                                            <!-- <span>텍스트</span> -->
                                                        </label>
                                                    </div>
                                                </td>
	                                            <td @click="setChecked( item )">
                                                    <span v-text="item.name"></span>
                                                </td>
	                                            <td @click="setChecked( item )">
                                                    <span v-text="item.materialInfo.name"></span>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                    </div>
                </div>
            </div>

        </div>
        <div slot="footer" class="dialog-footer">
            <el-button size="default" type="primary" @click="addProduct()">완료</el-button>
            <el-button size="default" @click="dialogVisible = false">취소</el-button>
        </div>
    </el-dialog>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";

export default {
    props: {
        // productCheckType: {
        //     type: String,
        //     required: true,
        //     defult: "check",
        // },
        // productList: {
        //     type: Object,
        //     required: false,
        //     defult: {},
        // },
    },
    components: {
        theLoading,
        thePagination,
    },
    data() {
        return {
            dialogVisible: false,

	        // 제품 리스트
	        productList: [],
	        productListLoading : false,

	        // 선택된 제품
	        selectedProduct: {},

            // 선택
            searchOptions: [
                {
                    value: "전체",
                    label: "전체",
                },
                {
                    value: "제품명",
                    label: "제품명",
                },
                {
                    value: "소재구분",
                    label: "소재구분",
                },
            ],
            searchType: "",

            // 검색창
            searchText: "",

	        // 전체 검색용
	        getTotal : false,



        };
    },
    mounted (){},
    watch: {},
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    fetch() {
	    this.searchType = this.searchOptions[0].value;
    },
    methods: {
        open() {
            this.dialogVisible = true;
        },
	    /**
	     * 전체 리스트를 가져옵니다.
	     */
	    getTotalList() {
		    this.getTotal = true;
		    this.getList();
	    },

        //완료 버튼 클릭시
        addProduct(){

            if ( this.$common.isEmpty( this.selectedProduct ) ){
	            this.$common.confirmSwal("추가할 관련 제품을 선택해주세요", "", "warning");
            	return;
            }

            this.$emit( "addRelatedProduct", this.selectedProduct );

            this.productList = [];
            this.selectedProduct = {};
            this.searchText = "";

            this.dialogVisible = false;
        },

        //제품 검색
        search(){
            if( this.$common.isEmpty( this.searchText ) && !( this.getTotal ) ){
                this.$common.confirmSwal("검색어를 입력해주세요.", "", "warning");
                return;
            }
            if ( this.$common.isEmpty(this.searchType) && !( this.getTotal ) ){
                this.$common.confirmSwal("검색 카테고리를 선택해주세요.", "", "warning");
                return;
            }
            this.getList();
        },

	    /**
	     * 체크표시
	     */
		setChecked( item ){
		    item.isCheckedDoc = !item.isCheckedDoc;
		    this.$forceUpdate();
		},

        //검색어로 제품 리스트 불러오기
        getList(){
            const TargetExceptList = this.$urlConstant.API_URL_PREFIX.PRODUCT + this.$urlConstant.API_URL_SUFFIX.PRODUCT.TARGET_EXCEPT_LIST;

            let cnd = {
                likeSearch : true,
                fillMaterial : true,
                targetObject : "AMCM",
            };

	        if ( this.getTotal ) {
		        this.searchText = "";
	        }
            switch ( this.searchType ){
                case '제품명':
                    cnd.name =  this.searchText;
                    break;
                case '소재구분' :
                    cnd.materialName = this.searchText;
                    break;
                case '전체' :
                    cnd.name =  this.searchText;
                    cnd.materialName = this.searchText;
                    break;
            }
	        this.productListLoading = true;
	        this.$axios.post( TargetExceptList, cnd ).then( res => {
                if ( this.$common.isEmpty(res.data) ) {
	                this.productListLoading = false;
                    this.productList = [];
                    this.getTotal = false;
                    return;
                }
                this.productList = res.data;
                this.productListLoading = false;
		        this.getTotal = false;
            });
        },
    },
};
</script>

<style scoped>
.scroll-element.vb.vb-visible{
	height : 38rem !important;
}
</style>
