<template>
    <div class="inner-wrapper">

        <!-- manager-content-body -->
        <div class="manager-content-body">
            <div class="content-title">
                <div class="sub-title">
                    <h2>제품상세 목록</h2>
                </div>
				<div class="btn">
                    <el-button type="primary" size="small" @click="goEdit()">신규 제품 등록</el-button>
				</div>
            </div>
            <!-- table -->
            <div class="content-detail">
                <!--search area -->
                <div class="search-area">
                    <!-- Depth -->
	                <div class="input-row">
		                <div class="label">
			                <span class="input-tit">검색항목</span>
		                </div>
		                <div class="data">
			                <el-select size="sm" v-model="searchType" placeholder="선택">
				                <el-option v-for="item in searchOptions" :key="item.value" :label="item.label" :value="item.value"> </el-option>
			                </el-select>
			                <el-input size="large" placeholder="검색어 입력" prefix-icon="el-icon-search" v-model="searchText"
									  @keypress.enter.native="search" clearable> </el-input>
		                </div>
	                </div>
	                <div class="input-row">
		                <div class="label">
			                <span class="input-tit">노출여부</span>
		                </div>
		                <div class="data">
			                <div class="input-area" style="padding:1rem;">
				                <el-radio-group class="ml10" v-model="releaseValue">
					                <el-radio :label="1">전체</el-radio>
					                <el-radio :label="2">노출</el-radio>
					                <el-radio :label="3">비노출</el-radio>
				                </el-radio-group>
			                </div>
		                </div>
	                </div>
	                <div class="input-row">
		                <div class="label">
			                <span class="input-tit">소재구분</span>
		                </div>
		                <div class="data">
			                <el-select size="sm" v-model="selectedMaterial" v-if="materialOptions">
				                <el-option v-for="item in materialOptions" :key="item.value" :label="item.label" :value="item.value"> </el-option>
			                </el-select>
		                </div>
	                </div>

                    <!-- 검색일 -->
                    <div class="input-row">
                        <div class="label">
                            <span class="input-tit">검색일</span>
                        </div>
                        <div class="data">
                            <el-select size="sm" v-model="searchDateType" placeholder="선택">
                                <el-option v-for="item in searchDateOptions" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                            </el-select>
	                        <el-date-picker size="large" v-model="pickedDate" type="daterange" align="right" start-placeholder="시작일"
	                                        end-placeholder="종료일" :default-time="['00:00:00', '23:59:59']">
	                        </el-date-picker>
                        </div>
                    </div>

                    <!-- 검색 버튼 -->
                    <div class="btn-wrap">
                        <el-button class="search-btn" type="gray" size="default" @click="search">검색</el-button>
                    </div>
                </div>

                <!-- 등록 버튼 -->
                <!-- <div class="btn btn-regist">
                    <el-button type="gray" size="small" @click="goEdit()">신규 제품 등록</el-button>
                </div> -->
                <!-- table -->
                <div class="manager-table-normal">
                    <table>
                        <colgroup>
                            <col style="width:10%" />
                            <col style="width:15%" />
                            <col style="width:10%" />
                            <col style="width:10%" />
                            <col style="width:10%" />
                            <col style="width:10%" />
                            <col style="width:10%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th scope="col"><span>번호</span></th>
                                <th scope="col"><span>제품명</span></th>
                                <th scope="col"><span>아이템 코드</span></th>
                                <th scope="col"><span>소재 구분</span></th>
                                <th scope="col"><span>노출여부</span></th>
                                <th scope="col"><span>등록일</span></th>
                                <th scope="col"><span>최종 수정일</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if="$common.isEmpty(productViewList)">
                                <td colspan="6">
                                    <!-- no-data(loading) -->
                                    <div class="no-data" v-if="productViewListLoading">
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
                            <tr v-if="$common.isNotEmpty(productViewList)" v-for="(item, i) in productViewList" :key="i" class="list-item" @click="goEdit( item )">
                                <td>
                                    <span v-text="item.no"></span>
                                </td>
                                <td>
                                    <span v-text="item.name"></span>
                                </td>
                                <td>
                                    <span v-text="item.productCode"></span>
                                </td>
                                <td>
                                    <span v-text="$common.isEmpty(item.materialInfo) ? '-' : item.materialInfo.name" ></span>
                                </td>
                                <td>
                                    <span v-text="'Y' == item.releaseYn ? '노출' : '비노출'"></span>
                                </td>
	                            <td>
                                    <span v-text="$common.formatDate(item.inputDate)"></span>
                                </td>
                                <td>
                                    <span v-text="$common.isEmpty(item.modDate) ? '-' : $common.formatDate(item.modDate)"></span>
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
import { mapActions } from "vuex";

export default {
    layout: "managerLayout",
    components: {
        theLoading,
        thePagination,
    },
    data() {
        return {

        	// 검색어
	        searchText: "",

	        // 검색 항목 선택 값
	        searchType: "",

            // 검색 항목 옵션
            searchOptions: [
                {
                    value: "name",
                    label: "제품명",
                },
                {
                    value: "productCode",
                    label: "아이템 코드",
                },
                {
                    value: "materialOid",
                    label: "소재구분",
                },
            ],

	        // 소재 구분 선택값
	        selectedMaterial : "",

	        // 소재 구분 옵션
	        materialOptions : [],

	        // 등록일 또는 수정일 선택값
	        searchDateType : "",

	        // 검색 시간 옵션
	        searchDateOptions : [
		        {
			        value : "inputDate",
			        label : "등록일",
		        },
		        {
			        value : "modDate",
			        label : "수정일",
		        }
	        ],

			// 노출여부 값
	        releaseValue : "",

	        // 캘린더 선택값
	        pickedDate : "",

            // 제품상세 목록
            productViewList: [],
	        productViewListLoading : false,

	        // 페이징 처리
            startIndex : 1,
            listCount : 0,
            pageSize : 10,
            currentPage : 1,

        };
    },
    computed: {
	    createDateFrom() {
		    if( this.pickedDate ) {
			    let result = '';
			    result = this.pickedDate[0].getFullYear()
				    + '-' + (this.pickedDate[0].getMonth()+ 1)
				    + '-' + this.pickedDate[0].getDate();
			    return result;
		    }
	    },
	    createDateTo(){
		    if ( this.pickedDate ) {
			    let result = '';
			    result = this.pickedDate[1].getFullYear()
				    + '-' + (this.pickedDate[1].getMonth()+ 1)
				    + '-' + this.pickedDate[1].getDate();
			    return result;
		    }
	    },
	    // 소재구분 Map 불러오기
	    // getMaterialMap() {
		//     return this.$store.state.menu.materialMap;
	    // },
    },
    watch : {
        $route() {
            if( this.$route.query.page ) {
            	this.onChangePage( parseInt( this.$route.query.page ) );
            }
            else {
            	this.onChangePage( 1 );
            }
        },
    },

    async fetch() {

    	// 전체 목록 요청
        await this.getList(1);

        // 검색 관련 설정값 초기화
        this.searchOptionInit();
    },

    methods: {
	    //store 이벤트
        /**
         * 등록/수정 페이지로 이동
         */
        goEdit( item ) {

            const url = this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT
	             + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.PRODUCT_EDIT;

            if ( this.$common.isEmpty( item ) ) {
                this.$router.push( this.localePath ( url ) );
            }
            else {
                this.$router.push( this.localePath ({
                    path : url,
                    query : {
                        oid : item.oid
                    }
                }));
            }
        },

        /**
         * 제품 상세 리스트를 가져옵니다.
         */
        async getList( startIndex ) {

            const url = this.$urlConstant.API_URL_PREFIX.PRODUCT + this.$urlConstant.API_URL_SUFFIX.PRODUCT.LIST;
            let param = {
                pageSize : this.pageSize,
                startIndex : startIndex,
                fillMaterial : true,
            }
            // parameter 를 가공합니다.
            param = this.addCondition( param );

            this.productViewList = [];
	        this.productViewListLoading = true;
            await this.$axios.post( url, param ).then( res => {
            	this.listCount = res.data.totalCount;
				this.productViewList = res.data.list;
				this.productViewList.forEach( (item,i) => {
					item.no = this.$common.settingNo( this.listCount, startIndex, i );
				});
            }).catch( error => {
	            console.log( error );
            });
	        this.productViewListLoading = false;
        },

	    /**
	     * 페이지 이동 처리
	     */
	    onChangePage( page ) {
		    this.currentPage = page;
		    this.thisPage = ( parseInt ( this.currentPage ) - 1 ) * parseInt( this.pageSize ) + 1;
		    this.getList( this.thisPage );
	    },

	     /**
	     * 검색
	     */
	    search() {
	    	this.currentPage = 1;
	    	this.startIndex = 1;
			this.getList( this.startIndex );
	    },

	    /**
	     * select option 용으로 사용할 소재 구분 리스트를 요청합니다.
	     */
	    getProductClassificationMap() {

		    let param = {};
		    let _self = this;
		    const urlList = this.$urlConstant.API_URL_PREFIX.MATERIAL + this.$urlConstant.API_URL_SUFFIX.MATERIAL.MENU_LIST;
		    this.$axios.post( urlList, param ).then( res => {
		    	if( 200 === res.status && _self.$common.isNotEmpty( res.data ) ) {
				    _self.materialOptions = _self.makeList( res.data );
				    _self.selectedMaterial = _self.materialOptions[0].value;
			    }
		    }).catch( error => {
			    console.log( error );
		    });
	    },

	    /**
	     *  getProductClassificationMap() 에서 받아온
	     *  소재 구분 리스트를 가공하여 완성합니다. ( 마지막 )
	     */
	    makeList( list ) {
		    let tempArr = [];
		    tempArr.push({
			    value : "all",
			    label : "전체",
		    });
		    _.each( list , function(item, index) {
			    let tempObj = {
				    value : item.oid,
				    label : item.name,
			    };
			    tempArr.push(tempObj);
		    });
		    return tempArr;
	    },

	    /**
	     * 검색조건에 추가 조건을 더합니다.
	     */
	    addCondition( param ) {
		    // 검색어 있을 경우
		    if ( this.searchText ) {
			    param.likeSearch = true;
		    }
		    switch( this.searchType ) {
			    case 'name'
			    : param.name = this.searchText;
				    break;
			    case 'productCode'
			    : param.productCode = this.searchText;
				    break;
			    case 'materialOid'
			    : param.searchText = this.searchText;
				    break;
		    }
		    // 노출여부
		    switch ( this.releaseValue ) {
			    case 2
			    : param.releaseYn = 'Y';
				    break;
			    case 3
			    : param.releaseYn = 'N';
				    break;
		    }
		    // 소재구분 처리
		    switch ( this.selectedMaterial ) {
			    case 'all' : break;
			    default : param.materialOid = this.selectedMaterial;
		    }
		    // 검색일을 설정한 경우
		    if ( this.pickedDate ) {
			    switch ( this.searchDateType ) {
				    case 'inputDate' :
					    param.inputDateFrom = this.createDateFrom;
					    param.inputDateTo = this.createDateTo;
				    case 'modDate' :
					    param.modDateFrom = this.createDateFrom;
					    param.modDateTo = this.createDateTo;
			    }
		    }
		    return param;
	    },

		/**
		 * 검색 관련 값 초기화
		 */
		searchOptionInit() {
			// 소재구분 옵션 리스트 생성 요청
			this.getProductClassificationMap();
			// 검색항목 옵션값 초기화
			this.searchType = this.searchOptions[0].value;
			// 검색일 옵션값 초기화
			this.searchDateType = this.searchDateOptions[0].value;
			// 노출여부 옵션값 '전체'로 초기화
			this.releaseValue = 1;
		}
	},
};
</script>

<style scoped></style>
