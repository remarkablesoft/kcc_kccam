<template>
    <!-- 아이템 코드 추가 모달 -->
    <el-dialog title="Application 선택" :visible.sync="dialogVisible" width="80rem">
        <div class="inner-content">
            <!--search area -->
            <div class="search-area">
                <div class="input-row">
                    <div class="data">
                        <el-input size="large" placeholder="Application명 검색" prefix-icon="el-icon-search" v-model="searchInput" @keypress.enter.native="search" clearable> </el-input>
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
                                    <th><span>Application 명</span></th>
                                    <th><span>소재명</span></th>
                                    <th><span>설명</span></th>
                                </tr>
                            </thead>

                            <tbody>
                                <!-- no-data :: tr -->
                                <tr v-if="$common.isEmpty( applicationList )">
                                    <td colspan="4">
                                        <!-- no-data(loading) -->
                                        <div class="no-data" v-if="applicationLoading">
                                            <div class="loading-sm">
                                                <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                            </div>
                                            <p>데이터 로딩중입니다.</p>
                                        </div>
                                        <!-- no-data -->
                                        <div class="no-data" v-if="!applicationLoading">
                                            <i class="material-icons">error_outline</i>
                                            <p>데이터가 없습니다.</p>
                                        </div>
                                    </td>
                                </tr>
                                <tr v-for="(item, i) in applicationList" :key="i" class="list-item">
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
										<span v-text="$common.isNotEmpty( item.materialInfo ) ? item.materialInfo.name : '-'"></span>
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
                            page-comp="applicationPage"
                        />
                    </div>
                </div>
            </div>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button size="default" type="primary" @click="getApplication">완료</el-button>
            <el-button size="default" @click="dialogVisible = false">취소</el-button>
        </div>
    </el-dialog>
</template>

<script>
import thePagination from "~/components/common/ThePagination.vue";
import VClamp from "vue-clamp";

export default {
    props: {
    	applicationInfo: {
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

            // 마켓 목록
            applicationList : [],
            selectedItem : {},   // 선택된 제품

			page : 0,
            startIndex : 1,
            listCount : 0,
            pageSize : 10,
            currentPage : 1,
	        applicationLoading : false,
	        getTotal : false,
        };
    },
    created() {},
    mounted() {},
    watch: {
        $route() {
        	if ( this.$route.query.applicationPage ) {
        		this.onChangePage( parseInt ( this.$route.query.applicationPage ) );
			}
        	else {
        		this.onChangePage(1);
			}
        }
    },
    methods: {
        open() {
            this.dialogVisible = true;
        },
		/**
		 * 검색
		 */
		search() {
			this.getTotal = false;
			if ( this.$common.isEmpty( this.searchInput ) ){
				this.$common.swalWithOptions("검색어를 입력해주세요.", "", "warning");
				return;
			}
			this.onChangePage( 1 );
		},
	    /**
	     * 전체 리스트를 가져옵니다.
	     */
	    getTotalList() {
		    this.getTotal = true;
		    this.getApplicationList(1);
	    },
		/**
		 *  페이지 이동 처리
		 */
		onChangePage( page ) {
			this.currentPage = page;
			this.thisPage = ( parseInt ( this.currentPage ) - 1) * parseInt( this.pageSize ) + 1;
			this.getApplicationList( this.thisPage );
		},

		getApplicationList( startIndex ){

        	const urlList = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST;

        	let exceptCategoryOidList = [ this.$amConstant.ROOT_CATEGORY_OID.APPLICATION ];
            if ( this.$common.isNotEmpty( this.applicationInfo ) ){
                exceptCategoryOidList.push( this.applicationInfo.oid );
            }
			if ( this.getTotal ) {
				this.searchInput = "";
			}

        	let cnd = {
				categoryType: this.$amConstant.CATEGORY_TYPE.APPLICATION,
				searchText: this.searchInput,
				exceptCategoryOidList : exceptCategoryOidList,
				startIndex : startIndex,
				pageSize : this.pageSize,
				fillMaterial : true,
			};

        	if ( this.$common.isNotEmpty( this.searchInput ) ){
        		cnd.searchText = this.searchInput;
			}
			this.applicationList = [];
			this.applicationLoading = true;
			this.$axios.post(urlList, cnd).then(res => {
				this.listCount = res.data.totalCount;

				if ( this.$common.isEmpty(res.data) ) {
			        this.applicationLoading = false;
					this.applicationList = [];
					return;
				}
		        this.applicationLoading = false;
        		this.applicationList = res.data.list;
			}).catch( error => {
		        console.log( error );
		        this.applicationLoading = false;
	        });

		},

        /**
         * 제품 구분 리스트에서 선택된 제품을 가져옵니다.
         */
		getApplication() {
            this.$emit( "getApplication", this.selectedItem );
            this.dialogVisible = false;
        },
		setSelected( item ) {
			this.selectedItem = item;
		}
    },
};
</script>

<style></style>
