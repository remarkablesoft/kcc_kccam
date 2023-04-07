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
                    <h2>Contact 목록</h2>
                </div>
            </div>
            <!-- Contact Office 목록 -->
            <div class="content-detail">
                <div class="tit-area-inner">
                    <div class="left-area">
                        <span class="tit">Contact Office 목록</span>
                    </div>
                    <div class="right-area">
                        <el-button type="primary" size="small" @click="newOffice">신규 Contact Office 등록</el-button>
                    </div>
                </div>
                <!-- search area -->
                <div class="search-area">
                    <!-- 검색 항목 -->
                    <div class="input-row">
                        <div class="label">
                            <span class="input-tit">검색지역</span>
                        </div>
                        <div class="data">
                            <el-select size="sm" v-model="searchType">
                                <el-option v-for="(item, index) in areaList" :key="index" :label="item.key" :value="item.value"> </el-option>
                            </el-select>
                            <el-input size="large" @keypress.enter.native="branchSearch()" placeholder="Office명 검색" prefix-icon="el-icon-search" v-model="brcSrch" clearable> </el-input>
                        </div>
                    </div>
                    <!-- 검색 버튼 -->
                    <div class="btn-wrap">
                        <el-button class="search-btn" type="gray" size="default" @click="branchSearch()">검색</el-button>
                        <el-button class="search-btn second" type="st st-primary" size="default" @click="getAllBranchList">전체보기</el-button>
                    </div>
                </div>
                <div class="manager-table-normal">
                    <table>
                        <colgroup>
                            <col style="width: 13%" />
                            <col style="width: auto" />
                            <col style="width: 15%" />
                            <col style="width: 15%" />
                            <col style="width: 15%" />
                            <col style="width: 15%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th><span>번호</span></th>
                                <th><span>Contact Office 명</span></th>
                                <th><span>지역</span></th>
                                <th><span>연락처</span></th>
                                <th><span>등록일</span></th>
                                <th><span>담당자 목록</span></th>
                            </tr>
                        </thead>

                        <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if="$common.isEmpty( branchList )">
                                <td colspan="6">
	                                <!-- no-data(loading) -->
	                                <div class="no-data" v-if="brcListLoading">
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
                            <tr v-for="(item, index) in branchList" :key="index" class="list-item">
                                <td @click="goOfficeEdit(item.oid)">
                                    <span v-text="item.no"></span>
                                </td>
	                            <td @click="goOfficeEdit(item.oid)">
		                            <span v-text="item.name"></span>
	                            </td>
                                <td @click="goOfficeEdit(item.oid)">
                                    <span v-text="item.areaName"></span>
                                </td>
                                <td @click="goOfficeEdit(item.oid)">
                                    <span v-text="item.tel"></span>
                                </td>
                                <td @click="goOfficeEdit(item.oid)">
                                    <span v-text="$common.formatDate(item.inputDate)"></span>
                                </td>
                                <td>
                                    <el-button @click="getActiveManager(item)" :class="item.active ? 'active' : ''"
                                               type="st st-primary" size="small">목록보기</el-button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- 페이지네이션 -->
                <thePagination
                    v-if="brcListCount > 0"
                    :visible-buttons-count="5"
                    :total-count="brcListCount"
                    :page-size="pageSize"
                    :current-page="brcCurrentPage"
                    pageComp="brcPage"
                />
            </div>

            <!-- Contact Manager 목록 -->
            <div class="content-detail">
                <div class="tit-area-inner">
                    <div class="left-area">
                        <span class="tit">Contact Manager 목록</span>
                    </div>
                    <div class="right-area">
                        <div class="btn">
                            <el-button type="primary" size="small" @click="newManager">신규 Contact Manager 등록</el-button>
                        </div>
                    </div>
                </div>
                <!-- search area -->
                <div class="search-area">
                    <!-- 검색 항목 -->
                    <div class="input-row">
                        <div class="data">
                            <el-input size="large" placeholder="이름 검색" @keypress.enter.native="managerSearch()"
                                      prefix-icon="el-icon-search" v-model="mgrSrch" clearable> </el-input>
                        </div>
                        <!-- 검색 버튼 -->
                        <div class="btn-wrap">
                            <el-button class="search-btn" type="gray" size="default" @click="managerSearch()">검색</el-button>
                            <el-button class="search-btn second" type="st st-primary" size="default" @click="getAllMgrList">전체보기</el-button>
                        </div>
                    </div>
                </div>
                <div class="manager-table-normal">
                    <table>
                        <colgroup>
	                        <col style="width: 13%" />
	                        <col style="width: auto" />
	                        <col style="width: 15%" />
	                        <col style="width: 15%" />
	                        <col style="width: 15%" />
	                        <col style="width: 15%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th><span>번호</span></th>
                                <th><span>Contact Manager 명</span></th>
                                <th><span>Contact Office 명</span></th>
                                <th><span>지역</span></th>
                                <th><span>연락처</span></th>
                                <th><span>등록일</span></th>
                            </tr>
                        </thead>

                        <tbody>
                        <!-- no-data :: tr -->
                        <tr v-if="$common.isEmpty( managerList )">
	                        <td colspan="6">
		                        <!-- no-data(loading) -->
		                        <div class="no-data" v-if="mgrListLoading">
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
                            <tr v-for="(item, i) in managerList" :key="i" @click="goMgntEdit(item.oid)">
	                            <td>
		                            <span v-text="item.no"></span>
	                            </td>
	                            <td>
		                            <span v-text="item.name"></span>
	                            </td>
                                <td>
                                    <span v-text="item.branchName"></span>
                                </td>
                                <td>
                                    <span v-text="item.areaName"></span>
                                </td>
                                <td>
                                    <span v-if="item.tel" v-text="item.tel"></span>
                                    <span v-else>-</span>
                                </td>
                                <td>
                                    <span v-text="$common.formatDate(item.inputDate)"></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- 페이지네이션 -->
                <thePagination
                    v-if="mgrListCount > 0"
                    :visible-buttons-count="5"
                    :total-count="mgrListCount"
                    :page-size="pageSize"
                    :current-page="mgrCurrentPage"
                    pageComp="mgrPage"
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

            // Contact Office 검색
            // select option List : 초기화는 첫번째 값으로 하고 리스트 만들기
            areaList : [],
            // select Type v-model
            searchType : "",
            // 검색어 v-model
            brcSrch : "",

            // Contact Manager 검색
            // 검색어 v-model
            mgrSrch : "",

            //브랜치 리스트 & pagination
            branchList: [],
            brcStartIndex: 1,
            brcListCount: 0,
            brcCurrentPage: 1,
	        brcListLoading : false,

            // 관리자 리스트 & pagination
            managerList: [],
            mgrStartIndex: 1,
            mgrListCount: 0,
            mgrCurrentPage: 1,
	        mgrListLoading : false,

            pageSize : 6,
	        currentBranchTargetOid : "",

        };
    },
    watch: {
        $route() {
        	if( this.$route.query.brcPage ) {
		        if ( parseInt ( this.$route.query.brcPage ) !== parseInt( this.brcCurrentPage ) ) {
			        this.onChangeBranchPage( parseInt ( this.$route.query.brcPage ) );
		        }
	        }
        	if ( this.$route.query.mgrPage ) {
		        if ( parseInt ( this.$route.query.mgrPage ) !== parseInt( this.mgrCurrentPage ) ) {
			        this.onChangeManagerPage( parseInt ( this.$route.query.mgrPage ) );
		        }
	        }

        },
    },
    computed: {
    },
    fetch() {

    	this.makeAreaList();

    	this.displayList();

		this.searchType = this.areaList[0].value;
    },
    methods: {

	    /**
	     * 매니저 지역 검색 select Option 값 초기화
	     */
	    makeAreaList() {
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
	     *  브랜치 목록 + 첫번째 브랜치의 매니저 목록 호출
	     */
	    async displayList() {

		    await this.getBranchList(1);
		    await this.initManagerList();
	    },

    	/**
	     * 브랜치 페이지 이동
	     */
	    onChangeBranchPage( page ) {
		    this.brcCurrentPage = page;
		    this.brcStartIndex = ( this.brcCurrentPage - 1 ) *  this.pageSize + 1;
		    this.getBranchList( this.brcStartIndex );
	    },

	    /**
	     * 매니저 페이지 이동
	     */
	    onChangeManagerPage( page ) {
		    this.mgrCurrentPage = page;
		    this.mgrStartIndex = ( this.mgrCurrentPage - 1 ) *  this.pageSize + 1;
		    this.getManagerList();
	    },

	    /**
	     * 브랜치 검색을 누른 경우
	     */
	    branchSearch() {
		    this.onChangeBranchPage(1);
	    },

	    /**
	     * 매니저 검색을 누른 경우
	     */
	    managerSearch() {
		    this.onChangeManagerPage(1);
	    },
	    /**
	     * 신규 Contact Office 등록 화면으로 이동
	     */
	    newOffice() {
		    this.$router.push( this.localePath (
		    	this.$urlConstant.MENU_URL_PREFIX.MANAGER_CUSTOMER_SUPPORT_MGNT
			    + this.$urlConstant.MENU_URL_SUFFIX.CUSTOMER_SUPPORT_MGNT.CONTACT_OFFICE_EDIT ) );
	    },

        /**
         * 브랜치 수정 페이지로 이동
         */
        goOfficeEdit( branchOid ) {
            const url =
                this.$urlConstant.MENU_URL_PREFIX.MANAGER_CUSTOMER_SUPPORT_MGNT +
                this.$urlConstant.MENU_URL_SUFFIX.CUSTOMER_SUPPORT_MGNT.CONTACT_OFFICE_EDIT;

            let queryParam = {
                path : url,
                query : {
                	oid : branchOid,
                },
            };
            this.$router.push( this.localePath( queryParam ) );
        },

	    /**
	     * 신규 Contact Manager 등록 화면으로 이동
	     */
	    newManager() {
		    this.$router.push( this.localePath (
			    this.$urlConstant.MENU_URL_PREFIX.MANAGER_CUSTOMER_SUPPORT_MGNT
			    + this.$urlConstant.MENU_URL_SUFFIX.CUSTOMER_SUPPORT_MGNT.CONTACT_MANAGER_EDIT ) );
	    },

        /**
         * Contact Manager 수정 화면으로 이동
         */
        goMgntEdit( oid ) {
            const url =
                this.$urlConstant.MENU_URL_PREFIX.MANAGER_CUSTOMER_SUPPORT_MGNT +
                this.$urlConstant.MENU_URL_SUFFIX.CUSTOMER_SUPPORT_MGNT.CONTACT_MANAGER_EDIT;

            let queryParam = {
                path : url,
                query : {
	                oid : oid,
                }
            };
	        this.$router.push( this.localePath( queryParam ) );
        },

        /**
         * 브랜치 페이지 리스트 불러오기
         */
        async getBranchList( startIndex ) {

            const url = this.$urlConstant.API_URL_PREFIX.BRANCH + this.$urlConstant.API_URL_SUFFIX.BRANCH.LIST;
            let cnd = {
                startIndex: startIndex,
                pageSize: this.pageSize,
            };
            switch( this.searchType ) {
	            case ( 'all' ) :
		            break;
	            default :
		            cnd.areaCode = this.searchType;
		            break;
            }
            if( this.brcSrch ) {
                cnd.name = this.brcSrch;
            }

			this.brcListLoading = true;
			this.branchList = [];
	        await this.$axios.post( url, cnd ).then( res => {

                this.brcListCount = res.data.totalCount;
                if ( 200 === res.status && this.$common.isNotEmpty( res.data ) ) {
                    this.branchList = this.setNameNumActive( res.data.list, startIndex );
                }
            }).catch(error => {
            	console.log(error);
            });
	        this.brcListLoading = false;
        },

		/**
		 * API 로 받아온 목록에 '지역', '번호', 'active( 현재 선택된 DOM 에 class 주기위함 )값' 를 넣어줍니다.
		 */
	    setNameNumActive( list, startIndex ) {
	    	let _self = this;
            _.each( list, function ( item, index ) {
	            item.areaName = _self.$amConstant.CONTACT_AREA_MAP[ item.areaCode ];
	            item.no = _self.$common.settingNo( _self.brcListCount, startIndex, index );
	            item.active = false;
            });
            return list;
	    },

        /**
         * 전체 브랜치 리스트 불러오기
         */
        getAllBranchList() {
            const url = this.$urlConstant.API_URL_PREFIX.BRANCH + this.$urlConstant.API_URL_SUFFIX.BRANCH.LIST;
            let cnd = {
                startIndex: 1,
                pageSize: this.pageSize,
            };
	        this.brcListLoading = true;
	        this.branchList = [];
            this.$axios.post( url, cnd ).then( res => {

                this.brcListCount = res.data.totalCount;
                if ( 200 === res.status && this.$common.isNotEmpty( res.data ) ) {
	                this.branchList = this.setNameNumActive( res.data.list, 1 );
                }
            }).catch( error => {
            	console.log(error);
            });
	        this.brcListLoading = false;
        },

	    /**
	     * 페이지 로드 시 Office 첫번쨰 목록의 매니저 목록을 보여줍니다.
	     */
	    async initManagerList() {
		    if( this.$common.isEmpty( this.branchList ) ) {
		    	return;
		    }
		    await this.getActiveManager( this.branchList[0] );
	    },

	    /**
	     * 선택된 Office 의 해당 매니저 목록을 호출하고,
	     * 현재 선택된 목록보기 버튼에만 active 효과를 줍니다.
	     */
	    async getActiveManager( item ) {
	    	if ( this.$common.isEmpty( item ) ) {
	    		return;
		    }
	    	this.branchList.forEach( branch => {
	    		branch.active = false;
		    } );
		    item.active = true;
		    this.mgrStartIndex = 1;
		    this.mgrCurrentPage = 1;
		    this.currentBranchTargetOid = item.oid;
	    	await this.getManagerList();
	    },

        /**
         * 매니저 페이지 리스트 불러오기
         */
        async getManagerList() {

            const url = this.$urlConstant.API_URL_PREFIX.USER + this.$urlConstant.API_URL_SUFFIX.USER.LIST;

            let cnd = {
                startIndex: this.mgrStartIndex,
                pageSize: this.pageSize,
                userType : this.$amConstant.USER_TYPE.CONTACT_MANAGER,
            };
            if( this.$common.isNotEmpty( this.currentBranchTargetOid ) ) {
                cnd.branchOid = this.currentBranchTargetOid;
            }
            if( this.mgrSrch ) {
                cnd.likeSearch = true,
                cnd.name = this.mgrSrch;
            }
	        this.mgrListLoading = true;
	        this.managerList = [];
	        await this.$axios.post(url, cnd).then( res => {

	        	this.mgrListCount = res.data.totalCount;

                if ( 200 === res.status && this.$common.isNotEmpty( res.data ) ) {
	                this.managerList = this.setAreaNameNo( res.data.list, this.mgrStartIndex );
	                this.mgrListCount = res.data.totalCount;
                }
            }).catch(error => {
            	console.log(error);
            });
	        this.mgrListLoading = false;
        },
	    /**
	     * 브랜치 리스트에 '지역(areaName)', '번호' 를 넣어줍니다.
	     */
	    setAreaNameNo( list, startIndex ) {
	    	if( this.$common.isEmpty( list ) ) {
	    		return;
		    }
	    	const _self = this;
        	list.forEach( ( item, index ) => {
                item.areaName = _self.$amConstant.CONTACT_AREA_MAP[item.region];
		        item.no = _self.$common.settingNo( _self.mgrListCount, startIndex, index );
	        });
        	return list;
	    },

        /**
         * 전체 매니저 리스트 불러오기
         */
        getAllMgrList() {
        	this.mgrCurrentPage = 1;
			this.currentBranchTargetOid = "";
			this.branchList.forEach( ( item ) => {
				item.active = false;
			});
        	this.getManagerList();
        },
    },
};
</script>

<style scoped></style>
