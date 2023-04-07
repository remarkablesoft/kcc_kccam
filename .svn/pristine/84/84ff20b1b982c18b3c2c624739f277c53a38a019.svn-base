<template>
    <!-- 아이템 코드 추가 모달 -->
    <el-dialog title="아이템 코드 추가" :visible.sync="dialogVisible" width="80rem">
        <div class="inner-content">
            <!--search area -->
            <div class="search-area">
                <div class="input-row">
                    <div class="data">
                        <el-input size="large" placeholder="제품명 검색" prefix-icon="el-icon-search" v-model="productInput" @keypress.enter.native="getSFAItemList(1)" clearable> </el-input>
                        <!-- 검색 버튼 -->
                        <div class="btn-wrap">
                            <el-button class="search-btn" type="gray" size="default" @click="getSFAItemList(1)">검색</el-button>
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
                                <col style="width:15%" />
                                <col style="width:15%" />
                                <col style="width:auto" />
                            </colgroup>
                            <thead>
                                <tr class="bg-lgray">
                                    <th><span>선택</span></th>
                                    <th><span>제품명</span></th>
                                    <th><span>제품코드</span></th>
                                    <th><span>설명</span></th>
                                </tr>
                            </thead>

                            <tbody>
                                <!-- no-data :: tr -->
                                <tr v-if="$common.isEmpty( productInfoList )">
                                    <td colspan="4">
                                        <!-- no-data(loading) -->
                                        <div class="no-data" v-if="productLoading">
                                            <div class="loading-sm">
                                                <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                            </div>
                                            <p>데이터 로딩중입니다.</p>
                                        </div>
                                        <!-- no-data -->
                                        <div class="no-data" v-if="!productLoading">
                                            <i class="material-icons">error_outline</i>
                                            <p>데이터가 없습니다.</p>
                                        </div>
                                    </td>
                                </tr>
                                <tr v-for="(item, i) in productInfoList" :key="i" class="list-item">
                                    <td>
                                        <div class="custom-radio">
                                            <input :id="item.com_base_seq" :value="item" v-model="selectedProduct" type="radio" :key="item.com_base_seq" name="listRadioGroup" />
                                            <label :for="item.com_base_seq">
                                                <i></i>
                                                <!-- <span>텍스트</span> -->
                                            </label>
                                        </div>
                                    </td>
                                    <td @click="setSelectedProduct( item )">
										<span v-text="item.main_items_nm"></span>
                                    </td>
									<td @click="setSelectedProduct( item )">
										<span v-text="item.items"></span>
                                    </td>
									<td @click="setSelectedProduct( item )">
										<v-clamp :max-lines="2" class="txt">{{item.items_out_line}}</v-clamp>
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
                            page-comp="itemCodePage"
                        />
                    </div>
                </div>
            </div>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button size="default" type="primary" @click="getSelectedSFAProduct">완료</el-button>
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
            productInput: "",

            // 제품 추가 목록
            productInfoList: [],
            selectedProduct : {},   // 선택된 제품

            startIndex : 1,
            listCount : 0,
            pageSize : 5,
            currentPage : 1,
	        productLoading : false,
        };
    },
    created() {},
    mounted() {},
    watch: {
        $route() {
            let page = 0;
            if ( this.$common.isNotEmpty( this.$route.query.itemCodePage ) ) {
                page = Number( this.$route.query.itemCodePage );
            }
            this.getSFAItemList(page);
        }
    },
    methods: {
        open() {
            this.dialogVisible = true;
        },


        /**
         * 제품정보 제품 검색 리스트를 가져옵니다.
         */
        getSFAItemList(page) {

            if ( page < 1 ) {
                return;
            }

            if ( this.$common.isEmpty( this.productInput ) ) {
                this.$common.swalWithOptions("검색어를 입력해주세요.", "", "warning");
                return;
            }

            if ( this.$common.isNotEmpty( this.$route.query.itemCodePage ) ) {
                this.currentPage = Number( this.$route.query.itemCodePage );
            }

            let sfaSearchParam = {
                pageNum : this.currentPage,
                pageSize : this.pageSize,
                colLang : "kor",
                searchData : this.productInput,
                isFavSearch : false
            }


            let url = this.$urlConstant.KCC_SFA_URL.ITEM_SEARCH;
            url += "?param=" + JSON.stringify( sfaSearchParam );
            this.productInfoList = [];
	        this.productLoading = true;
            this.$axios.post( url ).then( res => {
                if ( 200 === res.status && this.$common.isNotEmpty( res.data.SCN_SMB_ITEMS_DESCR ) ) {
                    this.productInfoList = res.data.SCN_SMB_ITEMS_DESCR;
                    this.listCount = Number( res.data.SCN_SMB_ITEMS_DESCR_TOTAL_CNT );
	                this.productLoading = false;
                }
            }).catch( error => {
	            console.log( error );
	            this.productLoading = false;
            });

        },

        /**
         * SFA 제품정보에서 선택된 제품을 가져옵니다.
         */
        getSelectedSFAProduct() {
            this.$emit( "getSelectedSFAProduct", this.selectedProduct );
            this.dialogVisible = false;
        },
		setSelectedProduct( item ) {
        	this.selectedProduct = item;
        }
    },
};
</script>

<style></style>
