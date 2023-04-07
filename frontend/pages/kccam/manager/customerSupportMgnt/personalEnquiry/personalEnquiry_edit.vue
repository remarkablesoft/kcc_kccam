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
                    <h2>1:1 문의 설정</h2>
                </div>
            </div>
            <!-- table -->
            <div class="content-detail">
                <!-- table -->
                <div class="table-area">
                    <div class="table-row-md">
                        <div class="table-header" style="padding-right:1.8rem;">
                            <div class="left-area">
                                <span class="tit">1:1 문의 종류 설정</span>
                            </div>
                            <div class="right-area">
								<el-button type="primary" size="small" @click="addHeadQuestion">상위 문의 추가</el-button>
                                <el-button type="st st-primary" size="small" @click="goList()">목록</el-button>
                            </div>
                        </div>
                        <div class="table-body">
                            <div class="manager-table-custom">
                                <table>
                                    <colgroup>
                                        <col style="" />
                                        <col style="width:5%" />
                                        <col style="width:10%" />
                                        <col style="width:20%" />
                                        <col style="width:8%" />
                                        <col style="width:5%" />
                                        <col style="width:5%" />
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th><span>상위 문의 제목</span></th>
                                            <th><span>언어</span></th>
                                            <th><span>세부 항목 수</span></th>
                                            <th><span>담당자 이메일</span></th>
                                            <th><span>세부 설정</span></th>
                                            <th><span>삭제</span></th>
                                            <th><span>저장</span></th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <!-- no-data -->
                                        <tr v-if="$common.isEmpty(questionList)">
                                            <!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
                                            <td colspan="5">
                                                <!-- no-data(loading) -->
                                                <div class="no-data" v-if="listLoading">
                                                    <div class="loading-sm">
                                                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                                    </div>
                                                    <p>데이터 로딩중입니다.</p>
                                                </div>
                                                <!-- no-data -->
                                                <div class="no-data" v-else>
                                                    <span class="material-icons">error_outline</span>
                                                    <span>데이터가 없습니다.</span>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr v-for="(item, index) in questionList" :key="index" class="list-item">
                                            <td>
                                                <el-input v-model="item.configQuestion" placeholder="문의 제목을 입력하세요." size="hsm"></el-input>
                                            </td>
	                                        <td>
		                                        <el-select size="sm" v-model="item.lang" placeholder="언어선택">
			                                        <el-option v-for="langItem in langList" :key="langItem.index" :label="langItem.label" :value="langItem.value"> </el-option>
		                                        </el-select>
	                                        </td>
                                            <td>
                                                <div class="icon-wrap-center">
                                                    <span><em v-text="item.detailConfigQuestionList.length"></em> 개</span>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="icon-wrap-center">
                                                    <span v-if="$common.isNotEmpty(item.receiverEmailList[0])">
	                                                    {{ item.receiverEmailList[0].configReceiverEmail }}
                                                    </span>
                                                    <span v-else>지정 안됨</span>
                                                    <span v-if="item.receiverEmailList.length > 1"> 외 {{ item.receiverEmailList.length - 1 }}</span>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="icon-wrap-center">
                                                    <el-button class="icon" type="icon-only" @click="detailListToggle(item.oid)">
                                                        <i class="material-icons">settings_applications</i>
                                                    </el-button>
                                                </div>
                                            </td>
                                            <td>
                                                <el-button type="gray" size="small" @click="confirmDelete(item)">삭제</el-button>
                                            </td>
                                            <td>
                                                <el-button type="st st-primary" size="small" @click="saveHeadQuestion(item.oid)">저장</el-button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- 세부 문의 항목 설정 -->
                    <div class="table-row-md" v-show="isDetailToggle">
						<div class="table-header" style="padding-right:1.8rem;">
                            <div class="left-area">
                                <span class="tit">세부 항목 설정</span>
                            </div>
                            <div class="right-area">
								<el-button type="primary" size="small" @click="addDetailQuestion">세부 항목 추가</el-button>
                            </div>
                        </div>
                        <div class="table-body">
                            <div class="manager-table-custom">
                                <table>
                                    <colgroup>
                                        <col style="width:40%" />
                                        <col style="width:40%" />
                                        <col style="width:10%" />
                                        <col style="width:10%" />
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th><span>문의 제목</span></th>
                                            <th><span>예시 문구</span></th>
                                            <th><span>삭제</span></th>
                                            <th><span>저장</span></th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <!-- no-data -->
                                        <tr v-if="$common.isEmpty(additionalList)">
                                            <!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
                                            <td colspan="3">
                                                <!-- no-data(loading) -->
                                                <div class="no-data" v-if="listLoading">
                                                    <div class="loading-sm">
                                                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                                    </div>
                                                    <p>데이터 로딩중입니다.</p>
                                                </div>
                                                <!-- no-data -->
                                                <div class="no-data" v-else>
                                                    <span class="material-icons">error_outline</span>
                                                    <span>데이터가 없습니다.</span>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr v-for="(item, index) in additionalList" :key="index" class="list-item">
                                            <td>
                                                <el-input v-model="item.configQuestion" placeholder="제목을 입력해주세요." size="hsm"></el-input>
                                            </td>
                                            <td>
                                                <el-input v-model="item.configAnswer" placeholder="예시 문구를 입력해주세요." size="hsm"></el-input>
                                            </td>
                                            <td>
                                                <el-button type="gray" size="small" @click="deleteDetailQuestion(item.oid)">삭제</el-button>
                                            </td>
                                            <td>
                                                <el-button type="st st-primary" size="small" @click="saveDetailQuestion(item.oid)">저장</el-button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <!-- 담당자 이메일 설정 -->
                    <div class="table-row-md" v-show="isDetailToggle">
						<div class="table-header" style="padding-right:1.8rem;">
                            <div class="left-area">
                                <span class="tit">담당자 이메일 설정</span>
                            </div>
                            <div class="right-area">
								<el-button type="primary" size="small" @click="addEmailReceiver">담당자 추가</el-button>
                            </div>
                        </div>
                        <div class="table-body">
                            <div class="manager-table-custom">
                                <table>
                                    <colgroup>
                                        <col style="width:80%" />
                                        <col style="width:10%" />
                                        <col style="width:10%" />
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th><span>담당자 이메일</span></th>
                                            <th><span>삭제</span></th>
                                            <th><span>저장</span></th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <!-- no-data :: tr -->
                                        <tr v-if="$common.isEmpty(emailList)">
                                            <td colspan="2">
                                                <!-- no-data(loading) -->
                                                <div class="no-data" v-if="listLoading">
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
                                        <tr v-for="(item, index) in emailList" :key="index" class="list-item">
                                            <td>
                                                <el-input v-model="item.configReceiverEmail" placeholder="이메일을 입력해주세요." size="hsm"></el-input>
                                            </td>
                                            <td>
                                                <el-button type="gray" size="small" @click="deleteEmailReceiver(item.oid)">삭제</el-button>
                                            </td>
                                            <td>
                                                <el-button type="st st-primary" size="small" @click="saveEmailReceiver(item.oid)">저장</el-button>
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
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";

export default {
    layout: "managerLayout",
    components: {
        theLoading,
    },
    data() {
        return {
            // 1:1 문의 설정 전체 목록
            questionList: [],
	        listLoading : false,

            // 해당 문의 세부 문의 항목 설정
            additionalList: [],

            // 추가문의 항목 설정 탭 토글 관리값
            isDetailToggle: false,

            // 담당자 이메일 설정
            emailList: [],

            // 현재 부모 문의 상태 관리용
            parentOid: "",

	        lang : "",

	        langList : [
		        {
		        	label : "한국어",
			        value : this.$amConstant.LANG.KO.toUpperCase(),
		        },
		        {
			        label : "중국어",
			        value : this.$amConstant.LANG.CN.toUpperCase(),
		        },
		        {
			        label : "영어",
			        value : this.$amConstant.LANG.EN.toUpperCase(),
		        }
	        ],

        };
    },
    async fetch() {
        await this.getData();
    },
    methods: {
        /**
         * 목록 페이지로 이동
         */
        goList() {
            this.$router.push(this.localePath("/kccam/manager/customerSupportMgnt/personalEnquiry/personalEnquiry_list"));
        },

        /**
         * 전체 데이터를 가져옵니다.
         */
        async getData() {
            await this.getQuestionListAll();
        },

        /**
         * 1:1 문의 설정 전체 목록을 가져옵니다.
         */
        async getQuestionListAll() {
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE_CONFIG + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE_CONFIG.LIST_ALL_MGMT;
            this.listLoading = true;
            let oid = this.parentOid;
            let cnd = {
            	useYn : 'Y',
            };
            await this.$axios.post( url, cnd ).then( res => {
                if ( this.$common.isEmpty( res.data ) ) {
                    return;
                }
	            this.questionList = res.data;
	            this.additionalList = res.data.filter(item => item.oid.includes(oid))[0].detailConfigQuestionList;
	            this.emailList = res.data.filter(item => item.oid.includes(oid))[0].receiverEmailList;
            }).catch( error => {
                console.log(error);
            });
            this.listLoading = false;
        },

        /**
         * 세부 문의 항목 설정 탭 on/off
         */
        detailListToggle( oid ) {
            if (this.$common.isEmpty( oid ) ) {
                this.additionalList = [];
                this.emailList = [];
                return;
            }
            this.parentOid = oid;
            this.isDetailToggle ? (this.isDetailToggle = false) : (this.isDetailToggle = true);
            this.additionalList = this.questionList.filter(item => item.oid.includes(oid))[0].detailConfigQuestionList;
            this.emailList = this.questionList.filter(item => item.oid.includes(oid))[0].receiverEmailList;
        },

        /**
         *	1:1 문의 종류 설정 한 줄 추가
         */
        addHeadQuestion() {
            let info = {
                configType : this.$amConstant.ONE_TO_ONE_CONFIG_TYPE.QUESTION,
	            lang       : this.$amConstant.LANG.KO.toUpperCase(),
            };
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE_CONFIG + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE_CONFIG.SAVE;
            this.postInsertOrUpdate(url, info);
        },

        /**
         * 1:1 문의 종류 설정 정보 저장
         */
        saveHeadQuestion(oid) {
            if (!this.headValidation(oid)) {
                return false;
            }
            let info = {
                oid             : this.questionList.filter( item => item.oid.includes( oid ) )[0].oid,
                configQuestion  : this.questionList.filter( item => item.oid.includes( oid ) )[0].configQuestion,
	            lang            : this.questionList.filter( item => item.oid.includes( oid ) )[0].lang,
            };
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE_CONFIG + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE_CONFIG.SAVE;
            this.postInsertOrUpdate(url, info);
        },

        /**
         * 1:1 문의 종류 유효성 검사
         */
        headValidation( oid ) {
            const index = this.questionList.findIndex( function( item ) {
                return item.oid === oid;
            });
            if ( this.$common.isEmpty( this.questionList[index].configQuestion ) ) {
                this.$common.confirmSwal("문의 제목을 입력해주세요.", "", "warning");
                return false;
            }
            return true;
        },

        /**
         * 1:1 문의 종류 설정 삭제 확인
         */
        confirmDelete(item) {
            if ( this.questionList.length < 2 ) {
                this.$common.confirmSwal("1:1 문의는 최소 1개 이상이어야 합니다.", "", "warning");
                return false;
            }

            //데이터 없을 경우 알람창 없이 바로 삭제합니다.
            if( this.$common.isEmpty( item.configAnswer ) &&
	            this.$common.isEmpty( item.configQuestion ) &&
	            this.$common.isEmpty( item.configReceiverEmail ) ) {
	            this.deleteHeadQuestion(item.oid);
	            return;
            }

            this.$common.swalWithOptions("삭제 확인", "해당 문의 및 세부 항목 설정을 삭제하시겠습니까?", "info").then( willDelete => {
                if ( willDelete ) {
                    this.deleteHeadQuestion( item.oid );
                }
            });
        },

        /**
         * 1:1 문의 종류 설정 ( 상위 ) 삭제
         */
        deleteHeadQuestion( oid ) {
            let info = {
                oid     : this.questionList.filter( item => item.oid.includes( oid ) )[0].oid,
	            useYn   : 'N'
            };
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE_CONFIG + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE_CONFIG.DELETE_FLAG_UPDATE;
            this.postDelete(url, info);
            this.isDetailToggle = false;
        },

        /**
         * 세부 문의 항목 설정 한 줄 추가
         */
        addDetailQuestion() {
        	let _self = this;
            let info = {
                oid         : "",
                configType  : this.$amConstant.ONE_TO_ONE_CONFIG_TYPE.DETAIL_QUESTION,
                parentOid   : this.parentOid,
	            lang        : this.questionList.filter( item => item.oid.includes( _self.parentOid ) )[0].lang,
            };
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE_CONFIG + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE_CONFIG.SAVE;
            this.postInsertOrUpdate(url, info);
        },

        /**
         *	세부 문의 항목 저장
         */
        saveDetailQuestion(oid) {
	        if (!this.subQuestionValidation(oid)) {
                return false;
            }
            let info = {
                oid             : oid,
                parentOid       : this.parentOid,
                configQuestion  : this.additionalList.filter(item => item.oid.includes(oid))[0].configQuestion,
                configAnswer    : this.additionalList.filter(item => item.oid.includes(oid))[0].configAnswer,
                configType      : this.$amConstant.ONE_TO_ONE_CONFIG_TYPE.DETAIL_QUESTION,
            };
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE_CONFIG + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE_CONFIG.SAVE;
	        this.postInsertOrUpdate( url, info );
        },

        /**
         * 추가 항목 설정 유효성 검사
         */
        subQuestionValidation(oid) {
            const index = this.additionalList.findIndex(function(item) {
                return item.oid === oid;
            });
            if (this.$common.isEmpty(this.additionalList[index].configQuestion)) {
                this.$common.confirmSwal("세부 문의 제목이 비어있습니다.", "", "warning");
                return false;
            }
            if (this.$common.isEmpty(this.additionalList[index].configAnswer)) {
                this.$common.confirmSwal("세부 문의 예시 문구 항목이 비어있습니다.", "", "warning");
                return false;
            }
            return true;
        },

        /**
         * 	세부 문의 항목 설정 한 줄 삭제
         */
        deleteDetailQuestion(oid) {
            if (this.additionalList.length == 1) {
                this.$common.confirmSwal("하위 항목은 최소 1개 이상이어야 합니다.", "", "warning");
                return false;
            }
            let info = {
                oid     : this.additionalList.filter(item => item.oid.includes(oid))[0].oid,
	            useYn   : 'N'
            };
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE_CONFIG + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE_CONFIG.DELETE_FLAG_UPDATE;
            this.postDelete(url, info);
        },

        /**
         * 담당자 이메일 설정 한 줄 추가
         */
        addEmailReceiver() {
        	let _self = this;
            let info = {
                oid         : "",
                configType  : this.$amConstant.ONE_TO_ONE_CONFIG_TYPE.RECEIVER_EMAIL,
                parentOid   : this.parentOid,
	            lang        : this.questionList.filter( item => item.oid.includes( _self.parentOid ) )[0].lang,
            };
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE_CONFIG + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE_CONFIG.SAVE;
            this.postInsertOrUpdate(url, info);
        },

        /**
         *  담당자 이메일 설정 저장
         */
        saveEmailReceiver( oid ) {

            if ( !this.receiverValidation( oid ) ) {
                return false;
            }

            let info = {
                oid                 : oid,
                parentOid           : this.parentOid,
                configReceiverEmail : this.emailList.filter(item => item.oid.includes(oid))[0].configReceiverEmail,
                configType          : this.$amConstant.ONE_TO_ONE_CONFIG_TYPE.RECEIVER_EMAIL,
            };
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE_CONFIG + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE_CONFIG.SAVE;
            this.postInsertOrUpdate(url, info);
        },

        /**
         * 담당자 이메일 설정 삭제
         */
        deleteEmailReceiver(oid) {
            if (this.emailList.length == 1) {
	            this.$common.confirmSwal("하위 항목은 최소 1개 이상이어야 합니다.", "", "warning");
                return false;
            }
            let targetOid = this.emailList.filter(item => item.oid.includes(oid))[0].oid;
            let info = {
                oid : targetOid,
	            useYn : 'N'
            };
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE_CONFIG + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE_CONFIG.DELETE_FLAG_UPDATE;
            this.postDelete(url, info);
        },

        /**
         * 이메일 담당자 유효성 검사
         */
        receiverValidation(oid) {
            let target = this.emailList.filter(item => item.oid.includes(oid))[0].configReceiverEmail;
            if (this.$common.isEmpty(target)) {
                this.$common.confirmSwal("담당자 이메일을 입력해주세요.", "", "warning");
                return false;
            }
            return true;
        },

        /**
         * url 과 info 를 받아 API Request ( insert Or Update 용 )
         */
        postInsertOrUpdate( url, info ) {
            this.$axios.post( url, info ).then( res => {
                if ( 200 === res.status && this.$common.isNotEmpty( res.data ) ) {
                    this.getData();
                }
            }).catch( error => {
                console.log( error );
            });
        },

        /**
         * url 과 info 를 받아 API Request ( Delete 용 )
         */
        postDelete( url, info ) {
            this.$axios.post( url, info ).then( res => {
                if ( 200 === res.status && 1 == res.data ) {
                    this.getData();
                }
            }).catch(error => {
                console.log(error);
            });
        },
    },
};
</script>

<style scoped></style>
