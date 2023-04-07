<template>
    <!-- 제품 추가 모달 -->
    <el-dialog title="제품 추가" :visible.sync="dialogVisible" width="60rem">
        <div class="inner-content">

            <!--search area -->
            <div class="search-area">
                <div class="input-row">
                    <div class="data">
                        <el-select size="sm" v-model="searchType" placeholder="선택" style="width:12rem;">
                            <el-option v-for="item in searchOptions" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                        </el-select>
                        <el-input size="large" placeholder="제품명/소재구분 검색" prefix-icon="el-icon-search" v-model="searchText"
								  @keypress.enter.native="search" clearable> </el-input>
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
                        <p>DB에서 검색한 리스트</p>
                        <table>
                            <colgroup>
                                <col style="width:8%" />
                                <col style="width:30%" />
                                <col style="" />
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
                                            <tr v-if="$common.isEmpty( productAddableList )">
                                                <td colspan="3">
                                                    <!-- no-data(loading) -->
                                                    <div class="no-data" v-if="productAddableLoading">
                                                        <div class="loading-sm">
	                                                        <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                                        </div>
                                                        <p>데이터 로딩중입니다.</p>
                                                    </div>
                                                    <!-- no-data -->
                                                    <div class="no-data" v-if="!productAddableLoading">
                                                        <i class="material-icons">error_outline</i>
                                                        <p>데이터가 없습니다.</p>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr v-for="(item, i) in productAddableList" :key="i" class="list-item">
                                                <td>
                                                    <div class="custom-checkbox">
<!--                                                        <input :id="item.oid" type="checkbox" v-model="item.isChecked" :value="item"/>-->
<!--                                                        <label :for="item.oid">-->
                                                        <input :id="i" type="checkbox" v-model="item.isChecked"/>
                                                        <label :for="i">
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

            <!-- list area -->
            <div class="list-area" style="margin-top: 20px;">
                <div class="manager-table-normal">
                    <div class="table-body">

                        <p>목록에서 삭제된 리스트</p>

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

                            <!-- scroll area 2-->
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
                                                    <div class="no-data" v-if="false">
                                                        <i class="material-icons">error_outline</i>
                                                        <p>데이터가 없습니다.</p>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr v-for="(item, i) in removedProductList" :key="item.oid + i" class="list-item">
                                                <td>
                                                    <div class="custom-checkbox">
                                                        <input :id="item.oid" type="checkbox" v-model="item.isChecked" :value="item"/>
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
        productCheckType: {
            type: String,
            required: true,
            defult: "check",
        },
        productList: {
            type: Array,
            required: true,
            defult: [],
        },
        removedProductList:{
            type: Array,
            required: true,
            default: [],
        },
		currentLang : {
        	type : String,
			required : false,
			default : "",
		}
    },
    components: {
        theLoading,
        thePagination,
    },
    data() {
        return {
            dialogVisible: false,

            productAddableList: [], //추가 가능한 제품들
            exceptOidList: [], //모달에서 추가됐던 제품들 제외용

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

	        productAddableLoading : false,

	        getTotal : false,

        };
    },
    mounted (){},
    watch: {},
    computed: {
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

            let checkedProductList = this.getCheckedList( this.productAddableList );
            let checkedRemovedProductList = this.getCheckedList( this.removedProductList );
            let addProductList = [];

            if ( this.$common.isEmpty( checkedProductList ) && this.$common.isEmpty( checkedRemovedProductList ) ){
                return;
            }

            if ( this.$common.isNotEmpty( checkedProductList ) && this.$common.isNotEmpty( checkedRemovedProductList )){
                addProductList = checkedProductList.concat( checkedRemovedProductList );
            }
            else if( this.$common.isNotEmpty( checkedProductList ) ){
                addProductList = checkedProductList
            }
            else {
                addProductList = checkedRemovedProductList
            }

            addProductList = this.setIsCheckedFalse( addProductList );

            this.$emit( "addProductList", addProductList );

            this.setExceptOidList( checkedProductList );
            this.setRemovedProductList( this.removedProductList );
            this.productAddableList = [];
            this.searchText = "";

            this.$common.confirmSwal("제품 구성", "목록에 추가 완료", "success");
            this.dialogVisible = false;
        },

        getCheckedList( list ){
            if( this.$common.isEmpty(list) ){
                return;
            }

            let checkedList = [];
            list.forEach( function( product ){
                if ( product.isChecked ) {
                    checkedList.push( product );
                }
            } );

            return checkedList;
        },

        //isChecked 속성을 false로 부여해줍니다.
        setIsCheckedFalse( checkedProductList ){
            if ( this.$common.isEmpty(checkedProductList) ){
                return;
            }
            let _self = this;
            checkedProductList.forEach( function( product ){
                if( _self.$common.isNotEmpty( product ) && product.isChecked ){
                    product.isChecked = false;
                }
            });

            return checkedProductList;
        },

        setRemovedProductList( removedProductList ){
            //추가 완료 후 removedProductList를 재설정해줍니다. (추가된 제품을 제외해줍니다.)
            if( this.$common.isEmpty( removedProductList ) ){
                return;
            }

            let tempList = [];

            removedProductList.forEach( function( product ){
                if ( !product.isChecked ){
                    tempList.push( product );
                }
            } );

            this.removedProductList = tempList;
        },

        setExceptOidList( productList ){
            //db 검색시 제외할 oidList를 설정해줍니다. (modal창에서 제품 추가 후, 다시 modal에서 검색하는 경우 제외 용)
            if( this.$common.isEmpty( productList ) ){
                return;
            }
            let _self = this;
            productList.forEach( product => {
                _self.exceptOidList.push( product.oid );
            });
        },

        //검색 버튼 누를시
        async search(){
            if( this.$common.isEmpty( this.searchText ) ){
                this.$common.confirmSwal("검색어를 입력해주세요.", "", "warning");
                return;
            }
            if ( this.$common.isEmpty(this.searchType) ){
                this.$common.confirmSwal("검색 카테고리를 선택해주세요.", "", "warning");
                return;
            }
            await this.getList();
            this.$forceUpdate();
        },

        //검색어로 제품 리스트 불러오기
        async getList(){
            const TargetExceptList = this.$urlConstant.API_URL_PREFIX.PRODUCT +
				this.$urlConstant.API_URL_SUFFIX.PRODUCT.TARGET_EXCEPT_LIST;

            let cnd = {
                likeSearch : true,
                oidNotIn : true,
                fillMaterial : true,
                targetObject : "AMCM",
                targetOidInOidNotIn : this.$route.query.oid,
                exceptOidList : this.exceptOidList,
				lang : this.currentLang.toUpperCase(),
            };

            if ( this.$common.isEmpty( this.$route.query.oid ) ){
                cnd.oidNotIn = false;
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
			this.productAddableList = [];
            this.productAddableLoading = true;
            await this.$axios.post( TargetExceptList, cnd ).then( res => {
                if ( this.$common.isEmpty(res.data) ) {
                    this.productAddableList = [];
                    this.productListTotalCnt = 0;
	                this.productAddableLoading = false;
                    return;
                }
                this.productAddableList = res.data;
                this.addCheckedPropertyAtProductAddableList( this.productAddableList );
				this.productAddableLoading = false;
			}).catch( error => {
	            console.log( error );
	            this.productAddableLoading = false;
            });
        },
		setChecked( item ) {
			item.isChecked = !item.isChecked;
			this.$forceUpdate();
		},
		setRemoveChecked( item ) {
			item.isChecked = !item.isChecked;
			this.$forceUpdate();
        },
		addCheckedPropertyAtProductAddableList( list ) {
        	if( this.$common.isEmpty( list ) ) {
        		return;
			}
        	_.each( list, function ( item ) {
        		item.isChecked = false;
			} );
		},
    },
};
</script>

<style>
	.manager-container .el-input--sm, .manager-container .el-select--sm, .manager-container .el-range-editor--sm {
		width: 12rem;
	}

	/* v-bar fix */
	.vb > .vb-dragger > .vb-dragger-styler {
		width: 100%;
		height: 100%;
		margin: 0;
	}
	.scroll-element vb vb-visible {
		height : 300px;
	}
	.scroll-element vb vb-invisible {
		height : 110px;
	}

</style>
