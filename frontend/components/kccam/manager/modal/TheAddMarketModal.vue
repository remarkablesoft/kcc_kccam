<template>
    <!-- 아이템 코드 추가 모달 -->
    <el-dialog title="Market 선택" :visible.sync="dialogVisible" width="80rem">
        <div class="inner-content">
            <!--search area -->
            <div class="search-area">
                <div class="input-row">
                    <div class="data">
                        <el-input size="large" placeholder="Market명 검색" prefix-icon="el-icon-search" v-model="searchInput"
								  @keypress.enter.native="search" clearable> </el-input>
                        <!-- 검색 버튼 -->
                        <div class="btn-wrap">
                            <el-button class="search-btn" type="gray" size="default" @click="search()">검색</el-button>
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
                                <col style="width:auto" />
                            </colgroup>
                            <thead>
                                <tr class="bg-lgray">
                                    <th><span>선택</span></th>
                                    <th><span>마켓명</span></th>
                                    <th><span>설명</span></th>
                                </tr>
                            </thead>

                            <tbody>
                                <!-- no-data :: tr -->
                                <tr v-if="$common.isEmpty( marketList )">
                                    <td colspan="4">
	                                    <!-- no-data -->
	                                    <div class="no-data" v-if="$common.isEmpty( searchText )">
		                                    <i class="material-icons">error_outline</i>
		                                    <p>Market명을 검색해주세요</p>
	                                    </div>
                                        <!-- no-data(loading) -->
                                        <div class="no-data" v-else-if="$fetchState.pending">
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
                                <tr v-for="(item, i) in marketList" :key="i" class="list-item">
                                    <td>
                                        <div class="custom-radio">
                                            <input :id="item.oid" :value="item" v-model="selectedItem" type="radio" :key="item.oid" name="listRadioGroup" />
                                            <label :for="item.oid">
                                                <i></i>
                                                <!-- <span>텍스트</span> -->
                                            </label>
                                        </div>
                                    </td>
                                    <td @click="setSelected( item )">
                                        <span v-text="item.name"></span>
                                    </td>
									<td @click="setSelected( item )">
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
            <el-button size="default" type="primary" @click="getMarketClassification">완료</el-button>
            <el-button size="default" @click="dialogVisible = false">취소</el-button>
        </div>
    </el-dialog>
</template>

<script>
import thePagination from "~/components/common/ThePagination.vue";
import VClamp from "vue-clamp";

export default {
    props: {
    	marketInfo: {
    		type: Object,
			required: false,
			default: {},
		}
	},
    components: {
        thePagination,
        VClamp
    },
    data() {
        return {
            dialogVisible: false,

            // 마켓 검색창
            searchInput: "",

	        searchText: "",

            // 마켓 목록
            marketList : [],
            selectedItem : {},   // 선택된 제품

            startIndex : 1,
            listCount : 0,
            pageSize : 5,
            currentPage : 1
        };
    },
    created() {},
    mounted() {},
    watch: {
	    searchText() {
	    	this.$fetch();
        }
    },
	async fetch(){
    	await this.getMarketList();
	},
    methods: {
        open() {
            this.dialogVisible = true;
        },
		search(){
			if ( this.$common.isEmpty( this.searchInput ) ){
				this.$common.swalWithOptions("검색어를 입력해주세요.", "", "warning");
				return;
			}
			this.searchText = this.searchInput;
		},
		getMarketList(){
        	if ( this.$common.isEmpty( this.searchText ) ){
        		return;
			}

        	const urlList = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST;

        	let exceptCategoryOidList = [ this.$amConstant.ROOT_CATEGORY_OID.MARKET ];
        	if ( this.$common.isNotEmpty( this.marketInfo ) ){
				exceptCategoryOidList.push( this.marketInfo.oid );
			}

        	let cnd = {
				categoryType: this.$amConstant.CATEGORY_TYPE.MARKET,
				searchText: this.searchInput,
				exceptCategoryOidList : exceptCategoryOidList,
			};

        	if ( this.$common.isNotEmpty( this.searchInput ) ){
        		cnd.searchText = this.searchInput;
			}

        	this.$axios.post(urlList, cnd).then(res => {
        		if ( this.$common.isEmpty(res.data) ) {
					this.marketList = [];
					return;
				}

        		this.marketList = res.data.list;
			});

		},

        /**
         * 제품 구분 리스트에서 선택된 제품을 가져옵니다.
         */
		getMarketClassification() {
            this.$emit( "getMarketClassification", this.selectedItem );
            this.dialogVisible = false;
        },
		setSelected( item ) {
			this.selectedItem = item;
		}
    },
};
</script>

<style></style>
