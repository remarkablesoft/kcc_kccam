<template>
    <!-- Function 추가 모달 -->
    <el-dialog title="Function 추가" :visible.sync="dialogVisible" width="80rem">
        <div class="inner-content">
            <!--search area -->
            <div class="search-area">
                <div class="input-row">
                    <div class="data">
                        <el-input size="large" placeholder="Function명 검색" prefix-icon="el-icon-search" v-model="functionNameInput" @keypress.enter.native="getFunctionList(1)" clearable>
                        </el-input>
                        <!-- 검색 버튼 -->
                        <div class="btn-wrap">
                            <el-button class="search-btn" type="gray" size="default" @click="getFunctionList(1)">검색</el-button>
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
                                <col style="width:46%" />
                                <col style="width:46%" />
                            </colgroup>
                            <thead>
                                <tr class="bg-lgray">
                                    <th><span>선택</span></th>
                                    <th><span>Function명</span></th>
                                    <th><span>소재구분</span></th>
                                </tr>
                            </thead>

                            <tbody>
                                <!-- no-data :: tr -->
                                <tr v-if="$common.isEmpty(functionList)">
                                    <td colspan="3">
                                        <!-- no-data(loading) -->
                                        <div class="no-data" v-if="functionLoading">
                                            <div class="loading-sm">
                                                <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                            </div>
                                            <p>데이터 로딩중입니다.</p>
                                        </div>
                                        <!-- no-data -->
                                        <div class="no-data" v-if="!functionLoading">
                                            <i class="material-icons">error_outline</i>
                                            <p>데이터가 없습니다.</p>
                                        </div>
                                    </td>
                                </tr>
                                <tr v-for="(item, i) in functionList" :key="i" class="list-item">
                                    <td>
                                        <div class="custom-checkbox">
                                            <input :id="item.oid" v-model="item.isChecked" type="checkbox" name="listCheckboxGroup"/>
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
                        <!-- 페이지네이션 -->
                        <thePagination
                            v-if="listCount > 0"
                            :visible-buttons-count="5"
                            :total-count="listCount"
                            :page-size="pageSize"
                            :current-page="currentPage"
                            page-comp="functionPage"
                        />
                    </div>
                </div>
            </div>

            <!-- list area -->
            <div class="list-area" v-if="removeFunctionList.length > 0">
                <div class="manager-table-normal">
                    <div class="table-body">
                        <table>
                            <colgroup>
                                <col style="width:8%" />
                                <col style="width:46%" />
                                <col style="width:46%" />
                            </colgroup>
                            <thead>
                            <tr class="bg-lgray">
                                <th><span>선택</span></th>
                                <th><span>Function명</span></th>
                                <th><span>소재구분</span></th>
                            </tr>
                            </thead>

                            <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if="false">
                                <td colspan="3">
                                    <!-- no-data(loading) -->
                                    <div class="no-data">
                                        <div class="loading-sm">
                                            <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                        </div>
                                        <p>데이터 로딩중입니다.</p>
                                    </div>
                                    <!-- no-data -->
                                    <div class="no-data" v-show="false">
                                        <i class="material-icons">error_outline</i>
                                        <p>데이터가 없습니다.</p>
                                    </div>
                                </td>
                            </tr>
                            <tr v-for="(item, i) in removeFunctionList" :key="i" class="list-item">
                                <td>
                                    <div class="custom-checkbox">
                                        <input :id="item.oid" v-model="item.isChecked" type="checkbox" name="listCheckboxGroup"/>
                                        <label :for="item.oid">
                                            <i></i>
                                            <!-- <span>텍스트</span> -->
                                        </label>
                                    </div>
                                </td>
                                <td @click="setRemoveChecked( item )">
									<span v-text="item.name"></span>
                                </td>
								<td @click="setRemoveChecked( item )">
									<span v-text="$common.isEmpty( item.materialInfo ) ? '-' : item.materialInfo.name"></span>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button size="default" type="primary" @click="addFunctionList">완료</el-button>
            <el-button size="default" @click="dialogVisible = false">취소</el-button>
        </div>
    </el-dialog>
</template>

<script>
import thePagination from "~/components/common/ThePagination.vue";

export default {
    props: {
        exceptFunctionList : {
            type : Array,
            required : false,
            default : function() {
                return [];
            }
        },

        removeFunctionList : {
            type : Array,
            required : false,
            default : function() {
                return [];
            }
        },
    },
    components: {
        thePagination,
    },
    data() {
        return {
            dialogVisible: false,

            // function명 검색창
            functionNameInput: "",

            // function 추가 목록
            functionList: [],

            startIndex : 1,
            listCount : 0,
            pageSize : 5,
            currentPage : 1,
	        functionLoading : false,
        };
    },
    created() {},
    mounted() {},
    watch: {
        $route() {
            let page = 0;
            if ( this.$common.isNotEmpty( this.$route.query.functionPage ) ) {
                page = Number( this.$route.query.functionPage );
            }
            this.getFunctionList(page);
        }
    },
    computed : {
        loadingIndicator() {
            return this.$root.$loading.percent;
        },
    },
    methods: {

        open() {
            this.dialogVisible = true;
            this.functionNameInput = "";
            this.functionList = [];
            this.listCount = 0;
        },

        /**
         * function 리스트를 가져옵니다.
         */
        getFunctionList( page ) {

            if ( page < 1 ) {
                return;
            }

            //function 명을 검색하는 경우
            if ( this.$common.isEmpty( this.functionNameInput ) ){
                this.$common.swalWithOptions("검색어를 입력해주세요.", "", "warning");
                return;
            }

            if ( this.$common.isNotEmpty( this.$route.query.functionPage ) ) {
                this.startIndex = ( this.$route.query.functionPage -1 ) * this.pageSize + 1;
                this.currentPage = Number( this.$route.query.functionPage );
            }

            // 이미 추가되어있는 function 제외
            let exceptCategoryOidList = [this.$amConstant.ROOT_CATEGORY_OID.FUNCTION];
            if ( this.$common.isNotEmpty( this.exceptFunctionList ) ) {
                let exceptFunctionOidList = [];

                _.each( this.exceptFunctionList, function( item ) {
                    exceptFunctionOidList.push( item.oid );
                });

                exceptCategoryOidList = [...exceptCategoryOidList, ...exceptFunctionOidList];
            }

            const url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST;
            let param = {
                startIndex : this.startIndex,
                pageSize : this.pageSize,
                categoryType : this.$amConstant.CATEGORY_TYPE.FUNCTION,
                exceptCategoryOidList : exceptCategoryOidList,
                fillMaterial : true,

                searchText : this.functionNameInput
            };
            this.functionList = [];
			this.functionLoading = true;
            this.$axios.post( url, param ).then( res => {
                if ( this.$common.isEmpty( res.data ) ) {
                    this.functionList = [];
                    this.listCount = 0;
                    return;
                }

                _.each( res.data.list, function( item ) {
                    item['isChecked'] = false;
                });

                this.functionList = res.data.list;
                this.listCount = res.data.totalCount;
                this.functionLoading = false;

            }).catch( error => {
	            console.log( error );
	            this.functionLoading = false;
            });

        },

        /**
         * 함수 리스트를 반환합니다.
         */
        addFunctionList() {

            let checkedList = [];

            _.each( this.functionList, function( item ) {
                if ( item.isChecked ) {
                    checkedList.push( item );
                }
            });

            this.$emit("addFunctionList", checkedList );
            this.dialogVisible = false;
        },
		setRemoveChecked( item ) {
			item.isChecked = !item.isChecked;
		},
		setChecked( item ) {
			item.isChecked = !item.isChecked;
		}

    },
};
</script>

<style></style>
