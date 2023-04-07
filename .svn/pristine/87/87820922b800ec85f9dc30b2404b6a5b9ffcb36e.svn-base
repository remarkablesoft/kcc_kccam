<template>
    <div class="inner-wrapper">
        <!-- loading -->
<!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
<!--            <the-loading />-->
<!--        </div>-->

        <!-- manager-content-body -->
        <div class="manager-content-body">
            <div class="content-title mt">
                <div class="sub-title">
                    <h2>고객정보 목록</h2>
                </div>
                <div class="right-area">
                    <el-button type="primary" size="small" @click="goEdit(1)">신규 고객 등록</el-button>
                </div>
            </div>
            <!--  content-detail -->
            <div class="content-detail">
                <!-- search area -->
                <div class="search-area">
                    <!-- 검색 항목 -->
                    <div class="input-row">
                        <div class="label">
                            <span class="input-tit">검색항목</span>
                        </div>
                        <div class="data">
                            <el-select size="sm" v-model="searchType" placeholder="선택">
                                <el-option v-for="item in optionList" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                            </el-select>
                            <el-input size="large" placeholder="검색어 입력" @keypress.enter.native="search" prefix-icon="el-icon-search" v-model="searchText" clearable>
                            </el-input>
                        </div>
                    </div>

                    <!-- 등록일 -->
                    <div class="input-row">
                        <div class="label">
                            <span class="input-tit">등록일</span>
                        </div>
                        <div class="data">
                            <el-date-picker
                                size="large"
                                v-model="pickedDate"
                                format="yyyy-MM-dd"
                                type="daterange"
                                align="right"
                                start-placeholder="시작일"
                                end-placeholder="종료일"
                                :default-time="['00:00:00', '23:59:59']">
                            >
                            </el-date-picker>
                        </div>
                    </div>

                    <!-- 검색 버튼 -->
                    <div class="btn-wrap">
                        <el-button class="search-btn" type="gray" size="default" @click="getClientList">검색</el-button>
                    </div>
                </div>
                <!-- <the-search :searchData="searchFilterData" @searchObj="getSearchObj" /> -->
                <div class="manager-table-normal">
                    <table>
                        <colgroup>
                            <col style="width: 8%" />
                            <col style="width: 12%" />
                            <col style="width: 15%" />
                            <col style="width: auto" />
                            <col style="width: 15%" />
                            <col style="width: 12%" />
                            <col style="width: 12%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th><span>번호</span></th>
                                <th><span>이름</span></th>
                                <th><span>회사</span></th>
                                <th><span>이메일</span></th>
                                <th><span>휴대번호</span></th>
                                <th><span>등록일</span></th>
                                <th><span>최종수정일</span></th>
                            </tr>
                        </thead>
                        <tbody>
	                        <!-- no-data :: tr -->
	                        <tr v-if="$common.isEmpty(clientList)">
		                        <td colspan="6">
			                        <!-- no-data(loading) -->
			                        <div class="no-data" v-if="clientListLoading">
				                        <div class="loading-sm">
					                        <img src="@/assets/images/loading/loading_sm.svg" alt="Loading"/>
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
                            <tr v-for="(item, index) in clientList" :key="index" class="list-item" @click="goEdit(item.oid)">
                                <td>
                                    <span v-text="item.no"></span>
                                </td>
	                            <td>
                                    <span v-text="item.name"></span>
                                </td>
                                <td>
                                    <span v-if="item.organizationName" v-text="item.organizationName"></span>
                                    <span v-else></span>
                                </td>
                                <td>
                                    <span v-text="item.email"></span>
                                </td>
                                <td>
                                    <span v-text="item.phone"></span>
                                </td>
                                <td>
                                    <span v-text="$common.formatDate( item.inputDate )"></span>
                                </td>
								<td>
									<span v-if="item.updateDate" v-text="$common.formatDate(item.updateDate)"></span>
									<span v-else>-</span>
								</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- 페이지네이션 -->
                <thePagination
                    v-if="listCount > 0"
                    :visible-buttons-count="5"
                    :total-count="listCount"
                    :page-size="pageSize"
                    :current-page="currentPage"
                    pageComp="page"
                />
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
            // 검색 유형
            optionList: [
                {
                    label: "이름",
                    value: "name",
                },
                {
                    label: "회사",
                    value: "organizationName",
                },
            ],
            // 검색어
            searchText: "",

            // 검색 유형
            searchType : "",

            //등록일 - calendar
            pickedDate: "", //[ "2021-05-13T15:00:00.000Z", "2021-06-16T14:59:59.000Z" ]

            // 고객 리스트 & pagination
            clientList: [],
	        clientListLoading : false,

            lastPage: 1,
	        thisPage : 1,
            currentPage: 1,
            pageSize : 6,
            listCount : 1,

            // 유저 타입
            userType : this.$amConstant.USER_TYPE.CLIENT,

        };
    },
    watch: {
        $route() {
        	if ( this.$route.query.page ) {
        		this.onChangePage( parseInt ( this.$route.query.page ) );
	        }
        	else {
        		this.onChangePage(1);
	        }
        }
    },
    computed : {
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
            if (this.pickedDate ) {
                let result = '';
                result = this.pickedDate[1].getFullYear()
                 + '-' + (this.pickedDate[1].getMonth()+ 1)
                 + '-' + this.pickedDate[1].getDate();
                return result;
            }
        }
    },
    async fetch() {

        /**
         * 리스트 초기화
         */
        await this.getClientList(1);

        this.searchType = this.optionList[0].value;

    },
    methods: {

        /**
         * 신규 등록 & 수정 페이지로 이동
         */
        goEdit( oid ) {
            const url =
                this.$urlConstant.MENU_URL_PREFIX.MANAGER_CUSTOMER_SUPPORT_MGNT +
                this.$urlConstant.MENU_URL_SUFFIX.CUSTOMER_SUPPORT_MGNT.INFORMATION_EDIT;

            let queryParam = {
                path : url,
                query : {},
            }
            if ( oid != 1 ) {
                queryParam.query.oid = oid;
            }
            this.$router.push( this.localePath( queryParam ) );
        },
		/**
		 * 검색
		 */
		search() {
			this.onChangePage( 1 );
		},
		/**
		 *  페이지 이동 처리
		 */
		onChangePage( page ) {
			this.currentPage = page;
			this.thisPage = ( parseInt ( this.currentPage ) - 1) * parseInt( this.pageSize ) + 1;
			this.getClientList( this.thisPage );
		},

        /**
         *  고객 리스트 호출 요청
         */
        async getClientList( startIndex ) {

	        const url = this.$urlConstant.API_URL_PREFIX.USER + this.$urlConstant.API_URL_SUFFIX.USER.LIST;
	        let cnd = {
		        userType : this.userType,
		        startIndex: startIndex,
		        pageSize: this.pageSize,
	        }
	        if( this.searchType ) {
		        switch( this.searchType ) {
			        case 'name' :
				        cnd.name = this.searchText;
				        break;
			        case 'organizationName' :
				        cnd.organizationName = this.searchText;
				        break;
		        }
		        cnd.likeSearch = true;
		        cnd.createDateFrom = this.createDateFrom;
		        cnd.createDateTo = this.createDateTo;
	        }
	        this.clientList = [];
	        this.clientListLoading = true;
	        await this.$axios.post( url, cnd ).then( res => {
	        	this.listCount = res.data.totalCount;
		        if ( 200 == res.status && this.$common.isNotEmpty( res.data ) ) {
			        this.clientList = this.setNo( res.data.list, startIndex );
		        }
	        }).catch( error => {
		        console.log( error );
	        })
	        this.clientListLoading = false;
        },
	    setNo( list, startIndex ) {
        	let _self = this;
            list.forEach( ( item, index ) => {
	            item.no = _self.$common.settingNo( _self.listCount, startIndex, index );
            });
		    return list;
	    },
    },

};
</script>

<style scoped></style>
