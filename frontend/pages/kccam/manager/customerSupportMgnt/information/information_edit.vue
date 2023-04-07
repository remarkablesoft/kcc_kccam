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
                    <h2 v-if="$route.query.oid">고객정보 수정</h2>
                    <h2 v-else>고객정보 등록</h2>
                </div>
            </div>
            <!--  content-detail -->
            <div class="content-detail">
                <!-- 언어 탭 버튼  -->
                <div class="btn-group bb btn-only">
                    <div class="btn-wrap">
                        <el-button type="primary" size="default" @click="validation">저장</el-button>
                        <el-button type="gray" size="default" v-if="$route.query.oid" @click="deleteInfo">삭제</el-button>
                        <el-button type="st st-primary" size="default" @click="goList()">목록</el-button>
                    </div>
                </div>
                <div class="input-area">
                    <div class="input-row-md lr" v-show="this.$route.query.oid">
                        <div class="left-area">
                            <div class="input-label">
                                <span>등록일</span>
                            </div>
                            <div class="input-data">
                                <span v-text="$common.formatDate(clientInfo.inputDate)"></span>
                            </div>
                        </div>
                        <div class="right-area">
                            <div class="input-label">
                                <span>최종 수정일</span>
                            </div>
                            <div class="input-data">
                                <span v-if="clientInfo.updateDate" v-text="$common.formatDate(clientInfo.updateDate)"></span>
                                <span v-else>-</span>
                            </div>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>이름</span><em class="required">*</em>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="clientInfo.name" clearable> </el-input>
                        </div>
                    </div>
                    <div class="input-row-md">
                        <div class="input-label">
                            <span>EMAIL</span><em class="required">*</em>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="clientInfo.email" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>휴대번호</span><em class="required">*</em>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="'-'으로 구분. ex) 010-1234-5678, 각 최대 4자리 / 총 12자리" v-model="clientInfo.phone" clearable> </el-input>
                        </div>
                    </div>
                    <div class="input-row-md">
                        <div class="input-label">
                            <span>회사명</span>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="clientInfo.organizationName" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>부서</span>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="clientInfo.customField1" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>전화번호</span>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="'-'으로 구분. ex) 070-1234-5678, 각 최대 4자리 / 총 12자리" v-model="clientInfo.tel" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>국가</span>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="clientInfo.customField2" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>도시</span>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="clientInfo.customField3" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>비고</span>
                        </div>
                        <div class="input-data">
                            <el-input type="textarea" :rows="5" placeholder="내용을 입력하세요." v-model="clientInfo.descr"> </el-input>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theDropzone from "~/components/common/dropzone/TheDropZone.vue";

export default {
    layout: "managerLayout",
    components: {
        theLoading,
        thePagination,
        theDropzone,
    },
    data() {
        return {

            clientInfo : {

                // 이름
                name : "",

                // 메일
                email: "",

                // 휴대번호
                phone : "",
                phonePart1 : "",
                phonePart2 : "",
                phonePart3 : "",

                // 회사명
                organizationName : "",

                // 전화번호
                tel: "",
                telPart1 : "",
                telPart2 : "",
                telPart3 : "",

                // 부서
                customField1 : "",

                // 국가
                customField2 : "",

                // 도시
                customField3 : "",

                // 비고
                descr : "",

                // 유저 타입
                userType : this.$amConstant.USER_TYPE.CLIENT,

                //등록일
                inputDate : "",

                // 최종수정일
                updateDate : "",
            },
        };
    },
    created() {
        if( this.$route.query.oid ) {
            this.fillClientInfo();
        }
    },
    methods: {

        /**
         * 페이지 이동
         */
        goList() {
            this.$router.push( this.localePath("/kccam/manager/customerSupportMgnt/information/information_list"));
        },

        /**
         * 등록 및 수정 전 유효성 검사
         */
        validation() {
            /**
             * input 태그 유효성 검사
             */
            // 이름
            if ( this.$common.isEmpty( this.clientInfo.name ) ) {
                this.$common.confirmSwal( "이름을 입력해주세요.", "", "warning");
                return false;
            }

            // 이메일
            if ( this.$common.isEmpty( this.clientInfo.email ) ) {
                this.$common.confirmSwal( "이메일 계정을 입력해주세요.", "", "warning");
                return false;
            }

            let regExp = /^\d{1,4}-\d{1,4}-\d{1,4}$/;
            // 휴대전화
            if ( this.$common.isEmpty( this.clientInfo.phone ) ) {
                this.$common.confirmSwal( "휴대전화 번호를 입력해주세요.", "", "warning");
                return false;
            }
            else if ( !regExp.test(this.clientInfo.phone ) ) {
                this.$common.confirmSwal( "휴대번호 확인", "휴대번호는 '-'(하이픈) 으로 구분, 각 4자리 / 총 12자리로 제한되어 있습니다.", "warning");
                return false;
            }
            else {
                this.clientInfo.phonePart1 = this.clientInfo.phone.split('-')[0];
                this.clientInfo.phonePart2 = this.clientInfo.phone.split('-')[1];
                this.clientInfo.phonePart3 = this.clientInfo.phone.split('-')[2];
            }

            // 전화번호
            if ( this.$common.isNotEmpty( this.clientInfo.tel ) ) {
                if( !regExp.test( this.clientInfo.tel ) ) {
                    this.$common.confirmSwal( "전화번호 확인", "전화번호는 '-'(하이픈) 으로 구분, 각 4자리 / 총 12자리로 제한되어 있습니다.", "warning");
                    return false;
                }
                else {
                    this.clientInfo.telPart1 = this.clientInfo.tel.split('-')[0];
                    this.clientInfo.telPart2 = this.clientInfo.tel.split('-')[1];
                    this.clientInfo.telPart3 = this.clientInfo.tel.split('-')[2];
                }
            }
            if( this.$route.query.oid ) {
                this.updateInfo();
            }
            else {
                this.insertInfo();
            }
        },
        /**
         * Oid 로 고객 정보 한 건 가져오기
         */
        fillClientInfo() {
            // oid 필요
            const url = this.$urlConstant.API_URL_PREFIX.USER + this.$urlConstant.API_URL_SUFFIX.USER.GET;
            let cnd = {
                oid : this.$route.query.oid,
                userType : this.userType,
            };
            let _self =this;
            this.$axios.post( url, cnd ).then( res => {
                if( 200 == res.status && _self.$common.isNotEmpty( res.data ) ) {
                    _self.clientInfo = res.data;
                }
            }).catch( error => { console.log( error ); });


        },
        /**
         *  신규 고객 Insert
         */
        insertInfo() {
            const url = this.$urlConstant.API_URL_PREFIX.USER + this.$urlConstant.API_URL_SUFFIX.USER.INSERT;
            let info = this.clientInfo;

            this.$axios.post( url, info ).then( res => {
                if( 200 === res.status ) {
                    this.$common.confirmSwal("등록 완료", "신규 고객 등록이 정상적으로 처리되었습니다.", "success");
                    this.goList();
                }
            }).catch( (error) => {
                console.log( error );
            });
        },
        /**
         *  고객 정보 수정 Update
         */
        updateInfo() {
            const url = this.$urlConstant.API_URL_PREFIX.USER + this.$urlConstant.API_URL_SUFFIX.USER.UPDATE;
            let info = this.clientInfo;

            this.$axios.post( url, info ).then( res => {
                if( 200 === res.status ) {
                    this.$common.confirmSwal("수정 완료", "고객 정보 수정이 정상적으로 처리되었습니다.", "success");
                    this.goList();
                }
            }).catch( (error) => {
                console.log( error );
            });
        },
        /**
         *  고객 정보 삭제 Delete
         */
        deleteInfo() {
            this.$common.swalWithOptions("삭제 확인", "해당 고객 정보를 삭제하시겠습니까?", "info").then(( willDelete ) => {
                if ( willDelete ) {
                    const url = this.$urlConstant.API_URL_PREFIX.USER + this.$urlConstant.API_URL_SUFFIX.USER.DELETE;
                    let info = {
                        oid : this.$route.query.oid,
                    };
                    this.$axios.post( url, info ).then( res => {
                        if( 1 === res.data ) {
                            this.goList();
                        }
                    }).catch( (error) => {
                        console.log( error );
                    });
                }
                else {
                    return false;
                }
            });
        },
    },
};
</script>

<style scoped></style>
