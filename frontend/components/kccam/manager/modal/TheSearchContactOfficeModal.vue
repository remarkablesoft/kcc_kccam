<template>
    <!-- Contact Office 찾기 Modal -->
    <el-dialog title="Contact Office 찾기" :visible.sync="visible" @close="close" width="60rem">
        <div class="inner-content">
            <div class="search-area">
                <!-- 검색 항목 -->
                <div class="input-row">
                    <div class="data">
                        <el-select size="sm" v-model="optionSelected" placeholder="지역 선택">
                            <el-option v-for="(item, index) in areaList" :key="index" :label="item.key" :value="item.value"> </el-option>
                        </el-select>
                        <el-input size="large" @keypress.enter.native="search" placeholder="Office명 검색" prefix-icon="el-icon-search" v-model="inputText" clearable> </el-input>
                    </div>
                </div>

                <!-- 검색 버튼 -->
                <div class="btn-wrap">
                    <el-button class="search-btn" type="gray" size="default" @click="search">검색</el-button>
                </div>
            </div>
            <div class="table-area">
                <div class="table-body">
                    <div class="manager-table-normal">
                        <table>
                            <colgroup>
                                <col style="width:10%" />
                                <col style="width:45%" />
                                <col style="width:45%" />
                            </colgroup>
                            <thead>
                                <tr class="bg-lgray">
                                    <th scope="col"><span>선택</span></th>
                                    <th scope="col"><span>지역</span></th>
                                    <th scope="col"><span>Contact Office 명</span></th>
                                </tr>
                            </thead>

                            <tbody>
	                            <!-- no-data :: tr -->
	                            <tr v-if="$common.isEmpty( searchList )">
		                            <td colspan="6">
			                            <!-- no-data(loading) -->
			                            <div class="no-data" v-if="searchListLoading">
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
                                <tr v-for="(item, i) in searchList" :key="i" class="list-item">
                                    <td>
                                        <div class="custom-checkbox">
                                            <input type="radio" :id="item.oid" name="office" :value="item.name" v-model="officeVal"/>
                                            <label :for="item.oid">
                                                <i></i>
                                            </label>
                                        </div>
                                    </td>
                                    <td>
                                        <span v-text="item.areaName"></span>
                                    </td>
                                    <td>
                                        <span v-text="item.name"></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- 페이지네이션 -->
        <thePagination
            v-if="listCount > 0"
            :visible-buttons-count="5"
            :total-count="listCount"
            :page-size="pageSize"
            :current-page="currentPage"
        />
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" size="default" @click="confirm">확인</el-button>
            <el-button size="default" @click="close">취소</el-button>
        </div>
    </el-dialog>
</template>

<script>
import thePagination from "~/components/common/ThePagination.vue";

export default {
    props: {
        searchOfficeVisible: {
            type: Boolean,
            required: true,
        },
    },
    components: {
        thePagination,
    },
    data() {
        return {
            // 선택한 옵션
            optionSelected : "",

            officeVal : "",

            visible : this.searchOfficeVisible,

            // option 값
            areaList : [],

            // 검색어 입력 내용
            inputText : "",

            // 검색결과 + pagination
            searchList: [],
	        searchListLoading : false,
            startIndex: 1,
            listCount: 0,
            currentPage: 1,
            pageSize : 10,

        };
    },
    created() {},
    mounted() {},
    async fetch() {

        /**
         * select Option 에 값 초기화
         */
        await this.getListArea();

	    this.optionSelected = this.areaList[0].value;

		await this.searchByOffice();

    },
    watch: {
        searchOfficeVisible() {
            this.visible = this.searchOfficeVisible;
        },
        $route() {
        	if ( this.$route.query.page ) {
        		let page = parseInt( this.$route.query.page );
        		if ( this.currentPage !== parseInt( page ) ) {
        			this.onChangePage( parseInt ( page ) );
		        }
	        }
        },
    },
    methods: {
        close() {
            this.visible = false;
            this.$emit("close");
        },
        confirm() {
            this.$emit("addSelectedOne", this.officeVal );
        },
	    /**
	     * 페이지 이동
	     */
	    onChangePage( page ) {
		    this.currentPage = page;
		    this.startIndex = ( this.currentPage - 1 ) * this.pageSize + 1;
		    this.searchByOffice();
	    },

	    /**
	     * select Option 값 초기화
	     */
        async getListArea() {
	        const areaMap = this.$amConstant.CONTACT_AREA_MAP;
	        let arr = [];
	        arr.push({
		        value : "all",
		        key : "전체",
	        });
	        // json key value 를 loop
	        for( const [key, value] of Object.entries( areaMap ) ) {
		        let obj = {};
		        obj.key = value;
		        obj.value = key;
		        arr.push(obj);
	        }
	        this.areaList = arr;
        },

	    /**
	     * 검색 전
	     */
	    search() {
		    // 검색은 항상 currentPage 를 1페이지로.
		    this.onChangePage(1);
	    },
        /**
         * 검색 결과 요청
        */
       async searchByOffice() {

	        const url = this.$urlConstant.API_URL_PREFIX.BRANCH + this.$urlConstant.API_URL_SUFFIX.BRANCH.LIST;
            let param = {
                startIndex : this.startIndex,
                pageSize : this.pageSize,
            };
	        if ( this.inputText ) {
	        	param.name = this.inputText;
	        }
	        switch( this.optionSelected ) {
		        case ( 'all' ) :
			        break;
		        default :
			        param.areaCode = this.optionSelected;
			        break;
	        }
            this.searchList = [];
            this.searchListLoading = true;

            await this.$axios.post( url, param ).then( res => {

            	this.listCount = res.data.totalCount;

                if( 200 == res.status && this.$common.isNotEmpty( res.data ) ) {
                    this.searchList = this.setAreaName( res.data.list );
                }

            }).catch( error => {
	            console.log( error );
            });
            this.searchListLoading = false;
       },
	    /**
	     * 목록에 지역명을 넣어줍니다.
	     */
        setAreaName( list ) {
        	const _self = this;
	        list.forEach( (item ) => {
		        item.areaName = _self.$amConstant.CONTACT_AREA_MAP[item.areaCode];
	        });
	        return list;
	    },
    }
};
</script>

<style></style>
