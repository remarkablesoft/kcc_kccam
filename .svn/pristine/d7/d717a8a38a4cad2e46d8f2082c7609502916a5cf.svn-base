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
                    <h2 v-if="$route.query.oid">Contact Manager 수정</h2>
                    <h2 v-else>Contact Manager 등록</h2>
                </div>
            </div>
            <!--  content-detail -->
            <div class="content-detail">
                <!-- 언어 탭 버튼  -->
                <div class="btn-group bb">
                    <div class="btn-wrap tab-style">
                        <!-- 클릭한 버튼에 activ 클래스 추가 -->
                       <!--  <el-button type="st st-gray" size="default" class="active">KOR</el-button>
                        <el-button type="st st-gray" size="default">ENG</el-button>
                        <el-button type="st st-gray" size="default">CHN</el-button> -->
                    </div>
                    <div class="btn-wrap">
                        <el-button type="primary" size="default" @click="validate">저장</el-button>
                        <el-button type="gray" size="default" @click="deleteUser" v-if="$route.query.oid">삭제</el-button>
                        <el-button type="st st-primary" size="default" @click="goList">목록</el-button>
                    </div>
                </div>
                <div class="input-area">
                    <div class="input-row-md lr" v-if="$route.query.oid">
                        <div class="left-area">
                            <div class="input-label">
                                <span>등록일</span>
                            </div>
                            <div class="input-data">
                                <span v-text="$common.formatDate(managerInfo.inputDate)"></span>
                            </div>
                        </div>
                        <div class="right-area">
                            <div class="input-label">
                                <span>최종 수정일</span>
                            </div>
                            <div class="input-data">
                                <span v-if="managerInfo.updateDate" v-text="$common.formatDate(managerInfo.updateDate)"></span>
                                <span v-else>-</span>
                            </div>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>이름</span><em class="required">*</em>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="managerInfo.name" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>Contact Office</span><em class="required">*</em>
                        </div>
                        <div class="input-data">
                            <div class="manager-search-box">
                                <el-input placeholder="" v-model="managerInfo.branchName" readonly clearable> </el-input>
                                <el-button type="gray" class="btn-search" @click="searchOfficeVisible = true">
                                    <span class="icon custom-icon-search"></span>
                                </el-button>
                            </div>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>EMAIL</span><em class="required">*</em>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="managerInfo.email" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>전화번호</span><em class="required">*</em>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="'-'으로 구분하여 입력하세요. ex) 010-1234-5678, 각 최대 4자리, 숫자 12자리" v-model="managerInfo.tel" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>비고</span>
                        </div>
                        <div class="input-data">
                            <el-input type="textarea" :rows="5" placeholder="내용을 입력하세요." v-model="managerInfo.descr"> </el-input>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- contactOffice 모달 -->
        <the-search-contact-office-modal :searchOfficeVisible="searchOfficeVisible" @addSelectedOne="fillOfficeInput" @close="searchOfficeVisible = false" />
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theDropzone from "~/components/common/dropzone/TheDropZone.vue";
import theSearchContactOfficeModal from "~/components/kccam/manager/modal/TheSearchContactOfficeModal.vue";
export default {
    layout: "managerLayout",
    components: {
        theLoading,
        thePagination,
        theDropzone,
        theSearchContactOfficeModal,
    },
    data() {
        return {

            // Contact Office 찾기 모달
            searchOfficeVisible: false,

            // 매니저 정보
            managerInfo : {

                // 매니저 oid
                oid : "",

                // 매니저 이름
                name: "",

                // Contact Office
                branchName: "",
                branchOid : "",

                // Email
                email : "",

                // 전화번호
                tel : "",

                // 등록일
                inputDate : "",

                // 최종 수정일
                updateDate : "",

                // 비고
                descr: "",

	            // 지역
                region : "",

            },
        };
    },
    created () {
        if( this.$route.query.oid ) {
            this.oid = this.$route.query.oid;
            this.fillManagerInfo();
        }
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    methods: {

        /**
         * 목록으로 이동
         */
        goList() {
            this.$router.push( this.localePath( "/kccam/manager/customerSupportMgnt/contact/contact_list") );
        },

        /**
         * 등록 전 유효성 검사
         */
        validate() {

            if ( this.$common.isEmpty( this.managerInfo.name ) ) {
                this.$common.confirmSwal("이름을 입력해주세요.", "", "warning");
                return;
            }
            if ( this.$common.isEmpty( this.managerInfo.branchName ) ) {
                this.$common.confirmSwal( "지점을 선택해주세요.", "", "warning");
                return;
            }
            if ( this.$common.isEmpty( this.managerInfo.email ) ) {
                this.$common.confirmSwal( "이메일 주소를 입력해주세요.", "", "warning");
                return;
            }
            if ( this.$common.isEmpty( this.managerInfo.tel ) ) {
                this.$common.confirmSwal( "연락처를 입력해주세요.", "", "warning");
                return;
            }
            else if ( this.managerInfo.tel.length > 14) {
                this.$common.confirmSwal( "전화번호 길이 확인", "연락처는 '-'(하이픈) 제외 총 12자리, 각 최대 4자리로 제한되어 있습니다.", "warning");
                return;
            }
            let checkResult = this.checkTelSizeForRow( this.managerInfo.tel );
			if( !checkResult ) {
				return;
			}
            if( this.$common.isEmpty( this.$route.query.oid ) ) {
                this.insertUser();
            }
            else {
                this.updateUser();
            }
        },
		checkTelSizeForRow( tel ) {
        	let eachNum = tel.split('-');
			let result = true;
        	eachNum.forEach( ( item ) => {
				if( item.length > 4) {
					this.$common.confirmSwal( "전화번호 길이 제한", "연락처는 '-'(하이픈) 제외 총 12자리, 각 최대 4자리로 제한되어 있습니다.", "warning");
					result = false;
				}
			} );
        	return result;
		},

        /**
         * 수정 GET : oid 로 매니저 정보 가져오기
         */
        fillManagerInfo() {
            const url = this.$urlConstant.API_URL_PREFIX.USER + this.$urlConstant.API_URL_SUFFIX.USER.GET;
            let param = {
                oid : this.oid,
                branchSearch : true,
            }
            let _self = this;
            this.$axios.post( url, param ).then(res => {
                if( 200 === res.status && _self.$common.isNotEmpty( res.data ) ) {
                	this.managerInfo = res.data;
                }
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 수정 POST : 매니저 정보 수정 요청
         */
        updateUser() {
            const url = this.$urlConstant.API_URL_PREFIX.USER + this.$urlConstant.API_URL_SUFFIX.USER.UPDATE;
            let tel = this.managerInfo.tel.split('-');
            let info = this.managerInfo;
            info["telPart1"] = tel[0];
            info["telPart2"] = tel[1];
            info["telPart3"] = tel[2];
			this.$axios.post( url, info ).then( res => {
                if( this.$common.isEmpty( res.data ) ) {
                    return;
                }
                if( 200 === res.status ) {
                    this.$common.confirmSwal("수정 완료", "회원 정보 수정이 정상적으로 처리되었습니다.", "success");
                    this.goList();
                }
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 신규 : 매니저 정보 INSERT
         */
        insertUser() {
            const url = this.$urlConstant.API_URL_PREFIX.USER + this.$urlConstant.API_URL_SUFFIX.USER.INSERT;
            let tel = this.managerInfo.tel.split('-');
            let info = {
                name : this.managerInfo.name, //이름
                branchOid : this.managerInfo.branchOid, // contact Office
                email : this.managerInfo.email, // 이메일
                telPart1 : tel[0],
                telPart2 : tel[1],
                telPart3 : tel[2],
                descr : this.managerInfo.descr, // 비고
                region : this.managerInfo.region, // 지역 구분
                userType : this.$amConstant.USER_TYPE.CONTACT_MANAGER,
            }
            this.$axios.post( url, info ).then( res => {
                if( this.$common.isEmpty( res.data ) ) {
                    return;
                }
                if( 200 === res.status ) {
                    this.$common.confirmSwal("등록 완료", "신규 회원 등록이 정상적으로 처리되었습니다.", "success");
                    this.goList();
                }
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 삭제 : 매니저 삭제
         */
        deleteUser() {
            this.$common.swalWithOptions("삭제 확인", "해당 회원 정보를 삭제하시겠습니까?", "info").then((willDelete) => {
                if (willDelete) {
                    const url = this.$urlConstant.API_URL_PREFIX.USER + this.$urlConstant.API_URL_SUFFIX.USER.DELETE;
                    let info = {
                        oid : this.managerInfo.oid,
                    }
                    this.$axios.post( url, info ).then( res => {
                        if( 1 == res.data ) {
                            this.goList();
                        }
                    }).catch((error) => {
                        console.log(error);
                    });
                }
                else {
                    return;
                }
            });
        },

        /**
         * Modal : contact office 선택값 가져오기
         */
        fillOfficeInput( data ) {

	        this.managerInfo.branchName = data;
            const url = this.$urlConstant.API_URL_PREFIX.BRANCH + this.$urlConstant.API_URL_SUFFIX.BRANCH.GET;
            let cnd = {
                name : data,
            };
            let _self = this;
            this.$axios.post( url, cnd ).then( res => {
                if( 200 === res.status ) {
                    _self.managerInfo.branchOid = res.data.oid;
                    _self.managerInfo.region = res.data.areaCode;
                }
            }).catch((error) => {
                console.log(error);
            });
            this.searchOfficeVisible = false;
        },

    },
};
</script>

<style scoped></style>
