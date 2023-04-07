<template>
    <div class="inner-wrapper">
        <!-- loading -->
<!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
<!--            <the-loading />-->
<!--        </div>-->

        <!-- manager-content-body -->
        <div class="manager-content-body">
            <div class="content-title">
                <div class="sub-title">
                    <h2>첨부문서 목록</h2>
                </div>
<!--                <div class="btn">-->
<!--                    <el-button type="st st-primary" size="small" @click="docTypeSettingModalOpen()">문서타입설정</el-button>-->
<!--                </div>-->
            </div>
            <div class="content-detail">
                <!--search area -->
                <div class="search-area">
                    <!-- 문서타입 -->
                    <div class="input-row">
                        <div class="label">
                            <span class="input-tit">문서타입</span>
                        </div>
                        <div class="data">
                            <el-select size="sm" v-model="docType">
                                <el-option v-for="item in docTypeMap" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                            </el-select>
                            <el-input size="large" placeholder="문서명 검색" prefix-icon="el-icon-search" @keypress.enter.native="search" v-model="docName" clearable>
                            </el-input>
                            <el-input size="large" placeholder="첨부대상 검색" prefix-icon="el-icon-search" @keypress.enter.native="search"v-model="attachTarget" clearable>
                            </el-input>
                        </div>
                    </div>
                    <!-- 검색일 -->
                    <div class="input-row">
                        <div class="label">
                            <span class="input-tit">검색일</span>
                        </div>
                        <div class="data">
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
                <!-- table -->
                <div class="manager-table-normal">
                    <table>
                        <colgroup>
                            <col style="width:8%" />
                            <col style="" />
                            <col style="width:15%" />
                            <col style="width:15%" />
                            <col style="width:15%" />
                            <col style="width:15%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th><span>번호</span></th>
                                <th><span>문서명</span></th>
                                <th><span>문서타입</span></th>
                                <th><span>첨부대상</span></th>
                                <th><span>등록일</span></th>
                                <th><span>최종 수정일</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if="$common.isEmpty( attachmentList )">
                                <td colspan="6">
                                    <!-- no-data(loading) -->
                                    <div class="no-data" v-if="attachmentListLoading">
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
                            <tr v-for="(item, i) in attachmentList" :key="i" class="list-item" @click="goEdit( item.oid )">
                                <td>
                                    <span v-text="item.no"></span>
                                </td>
                                <td>
                                    <span v-text="item.title"></span>
                                </td>
                                <td>
                                    <span v-text="$amConstant.SFA_DOC_KIND[item.docType]"></span>
                                </td>
                                <td>
                                    <span v-text="item.docRelInfo ? item.docRelInfo.targetObject : ''"></span>
                                </td>
                                <td>
                                    <span v-text="$common.isNotEmpty(item.inputDate) ? $common.formatDate(item.inputDate) : ''"></span>
                                </td>
                                <td>
                                    <span v-text="$common.isNotEmpty(item.modDate) ? $common.formatDate(item.modDate) : ''"></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- 페이지네이션 -->
                <the-pagination
                    v-if="0 < listCount"
                    :current-page="currentPage"
                    :page-size="pageSize"
                    :total-count="listCount"
                    :visible-buttons-count="5"
                />
            </div>
        </div>

        <!-- 문서타입설정 모달 -->
        <the-doc-type-setting-modal ref="docTypeSettingModal"></the-doc-type-setting-modal>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theDocTypeSettingModal from "~/components/kccam/manager/modal/TheDocTypeSettingModal.vue";

export default {
    layout: "managerLayout",
    components: {
        theLoading,
        thePagination,
        theDocTypeSettingModal,
    },
    data() {
        return {

	        // 문서타입 Map
	        docTypeMap : {},

	        // 검색 타입 선택값
	        docType : "",

	        // 등록일 - calendar
	        pickedDate : "",

            //문서명
            docName: "",

            //첨부대상
            attachTarget: "",

	        attachmentList: [],
	        attachmentListLoading : false,

            listCount: 0,

            // 페이지네이션
            pageSize: 10,
            currentPage: 1,
            thisPage: 1,
        };
    },
    async fetch() {
		await this.getDocListData(1);

		this.setDocumentMap();

		this.docType = this.docTypeMap[0].value;
	},
    watch: {
        $route() {
            if ( this.$route.query.page ) {
                this.onChangePage(parseInt( this.$route.query.page ) );
            }
            else {
                this.onChangePage(1);
            }
        },
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
			if ( this.pickedDate ) {
				let result = '';
				result = this.pickedDate[1].getFullYear()
					+ '-' + (this.pickedDate[1].getMonth()+ 1)
					+ '-' + this.pickedDate[1].getDate();
				return result;
			}
		}
	},
    methods: {

        // 수정 페이지로 이동
        goEdit( oid ) {
        	const url = this.$urlConstant.MENU_URL_PREFIX.MANAGER_DOC_MGNT +
		                this.$urlConstant.MENU_URL_SUFFIX.DOC_MGNT.ATTACHMENT_EDIT;

	        let queryParam = {
		        path : url,
		        query : {
			        oid : oid,
		        },
	        }
	        this.$router.push( this.localePath( queryParam ) );
        },
	    /**
	     * 검색
	     */
	    search() {
			this.onChangePage( 1 );
	    },

        // 문서타입설정 모달 열기
        // docTypeSettingModalOpen() {
        //     this.$refs.docTypeSettingModal.open();
        // },

	    /**
	     *  페이지 이동 처리
	     */
        onChangePage( page ) {
            this.currentPage = page;
            this.thisPage = ( parseInt ( this.currentPage ) - 1) * parseInt( this.pageSize ) + 1;
            this.getDocListData( this.thisPage );
        },
		/**
		 *  첨부문서 리스트를 불러옵니다.
		 */
        async getDocListData( startIndex ) {
            let cnd = {
                startIndex: startIndex,
                pageSize: this.pageSize,
                fillFile: true,
				onlyCurrentVersion: true,
	            docType : this.docType,
	            likeSearch : true,
			};
            if( this.$common.isNotEmpty( this.docName ) ) {
            	// 문서명 검색일 경우
	            cnd.title = this.docName;
            }
            if( this.$common.isNotEmpty( this.attachTarget ) ) {
            	// 첨부대상 검색인 경우
				cnd.searchText = this.attachTarget;
            }
	        if( this.$common.isNotEmpty( this.pickedDate ) ) {
		        cnd.inputDateFrom = this.createDateFrom;
		        cnd.inputDateTo = this.createDateTo;
	        }
	        this.attachmentList = [];
	        this.attachmentListLoading = true;
            const url = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.LIST;
            await this.$axios.post( url, cnd ).then( res => {

                this.listCount = res.data.totalCount;

                if (this.$common.isEmpty( res.data.list ) ) {
                    this.attachmentList = [];
	                this.attachmentListLoading = false;
					return;
				}
                this.attachmentList = res.data.list;
	            this.attachmentListLoading = false;

                res.data.list.forEach( ( item, index ) => {
					item.no = this.$common.settingNo( this.listCount, startIndex, index );
			    });
			});
        },
	    /**
	     *  문서타입 Map 을 생성합니다.
	     */
	    setDocumentMap() {
	        let tempObj = this.$amConstant.SFA_DOC_KIND;
	        let tempList = [];
	        tempList.push({
		        value : "",
		        label : "전체"
	        });
	        for( let items in tempObj ) {
	        	let tempArr = {};
	        	tempArr.value = items;
	        	tempArr.label = tempObj[ items ];
	        	tempList.push( tempArr );
	        }
	        this.docTypeMap = tempList;
	    },
    },
};
</script>

<style scoped></style>
