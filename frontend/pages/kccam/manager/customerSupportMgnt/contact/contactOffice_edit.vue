<template>
    <div class="inner-wrapper">
        <!-- manager-content-body -->
        <div class="manager-content-body">
            <div class="content-title mt">
                <div class="sub-title">
                    <h2 v-if="$route.query.oid">Contact Office 수정</h2>
                    <h2 v-else>Contact Office 등록</h2>
                </div>
            </div>
            <!--  content-detail -->
            <div class="content-detail">
                <!-- 언어 탭 버튼  -->
                <div class="btn-group bb">
                    <div class="btn-wrap tab-style">
                        <!-- 잠시 기능 막아두었습니다 -->
                        <!-- 클릭한 버튼에 activ 클래스 추가 -->
                        <!-- <el-button type="st st-gray" size="default" class="active">KOR</el-button>
                        <el-button type="st st-gray" size="default">ENG</el-button>
                        <el-button type="st st-gray" size="default">CHN</el-button> -->
                    </div>
                    <div class="btn-wrap">
                        <el-button type="primary" size="default" @click="regist()">저장</el-button>
                        <el-button type="gray" size="default" @click="deleteBranch()" v-if="$route.query.oid">삭제</el-button>
                        <el-button type="st st-primary" size="default" @click="goList()">목록</el-button>
                    </div>
                </div>
                <div class="input-area">
                    <div class="input-row-md lr" v-if="$route.query.oid">
                        <div class="left-area">
                            <div class="input-label">
                                <span>등록일</span>
                            </div>
                            <div class="input-data">
                                <span v-text="$common.formatDate(inputDate)"></span>
                            </div>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>Contact Office명</span><em class="required">*</em>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="officeNameInput" ref="officeNameInput" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>지역</span><em class="required">*</em>
                        </div>
                        <div class="input-data">
                            <el-select size="sm" v-model="locationValue" ref="locationValue">
                                <el-option v-for="item in locationValueOption" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                            </el-select>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>주소</span><em class="required">*</em>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="addressInput" ref="addressInput" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>우편번호</span>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="postNumInput" ref="postNumInput" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>전화번호</span><em class="required">*</em>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="phoneNumInput" ref="phoneNumInput" clearable> </el-input>
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>비고</span>
                        </div>
                        <div class="input-data">
                            <el-input type="textarea" :rows="5" placeholder="내용을 입력하세요." ref="descr" v-model="descr"> </el-input>
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
import swal from "sweetalert";

export default {
    layout: "managerLayout",
    components: {
        theLoading,
        thePagination,
    },
    data() {
        return {
            // 지역 Select
            locationValue: "",
            locationValueOption: [
                {
                    label: "대한민국",
                    value: this.$amConstant.CONTACT_AREA.KOREA,
                },
                {
                    label: "중국",
                    value: this.$amConstant.CONTACT_AREA.CHINA,
                },
                {
                    label: "일본",
                    value: this.$amConstant.CONTACT_AREA.JAPAN,
                },
            ],
            // Contact Office명 input
            officeNameInput: "",

            // 주소
            addressInput: "",

            // 우편번호
            postNumInput: "",

            // 전화번호
            phoneNumInput: "",

            // 비고
            descr: "",

            managerOID: "",

            // 등록할 정보 객체
            officeInfo: {},

            // 등록일
            inputDate: "",

        };
    },
    created() {
        if (this.$route.query.oid) {
            this.getOfficeInfo();
        }
        else {
            this.locationValue = this.locationValueOption[0].value;
        }

    },
    methods: {
        /**
         * 목록으로 이동
         */

        goList() {
            this.$router.push( this.localePath("/kccam/manager/customerSupportMgnt/contact/contact_list") ) ;
        },
        /**
         * branchOid 가 있는 경우 정보 가져오기
         */
        getOfficeInfo() {
            const url = this.$urlConstant.API_URL_PREFIX.BRANCH + this.$urlConstant.API_URL_SUFFIX.BRANCH.GET;
            let param = {
                oid: this.$route.query.oid,
            };
            this.$axios
                .post(url, param)
                .then(res => {
                    if (this.$common.isEmpty(res.data)) {
                        this.officeInfo = {};
                        return;
                    }
                    this.inputDate = res.data.inputDate;
                    this.officeNameInput = res.data.name;
                    this.addressInput = res.data.addr;
                    this.postNumInput = res.data.postCode;
                    this.phoneNumInput = res.data.tel;
                    this.descr = res.data.descr;
                    this.locationValue = res.data.areaCode;

                    this.officeInfo = {
                        name: this.officeNameInput,
                        addr: this.addressInput,
                        postCode: this.postNumInput,
                        tel: this.phoneNumInput,
                        descr: this.descr,
                        inputUser: this.managerOID,
                        areaCode: this.locationValue,
                    };
                })
                .catch(error => {
                    console.log(error);
                });
        },
        /**
         * 등록 및 수정 처리
         */

        regist() {
            // 값 초기화 및 선언 + 유효성 검사
            if (this.$common.isEmpty(this.officeNameInput)) {
                this.$common.confirmSwal("Contact Office 명을 입력해주세요.", "", "warning");
                return;
            }
            if (this.$common.isEmpty(this.locationValue)) {
                this.$common.confirmSwal("지역을 선택해주세요.", "", "warning");
                return;
            }
            if (this.$common.isEmpty(this.addressInput)) {
                this.$common.confirmSwal("주소를 입력해주세요.", "", "warning");
                return;
            }
            if (this.$common.isEmpty(this.phoneNumInput)) {
                this.$common.confirmSwal("전화번호을 입력해주세요.", "", "warning");
                return;
            }

            if (this.$common.isEmpty(this.officeInfo)) {
                this.insertInfo();
            } else {
                this.officeInfo.oid = this.$route.query.oid;
                this.updateInfo();
            }
        },
        /**
         * 신규 => insert
         */

        insertInfo() {
            const url = this.$urlConstant.API_URL_PREFIX.BRANCH + this.$urlConstant.API_URL_SUFFIX.BRANCH.SAVE;

            let params = {
                areaCode: this.locationValue,
                name: this.officeNameInput,
                addr: this.addressInput,
                postCode: this.postNumInput,
                tel: this.phoneNumInput,
                descr: this.descr,
                inputUser: this.$store.login.loginUser.userOid,
            };
	        this.$axios.post(url, params).then(res => {
				if (this.$common.isEmpty(res.data)) {
					this.officeInfo = {};
					return;
				}
				this.$common.confirmSwal("등록 완료", "신규 브랜치 등록이 정상적으로 처리되었습니다.", "success");
				this.goList();
			})
			.catch(error => {
				console.log(error);
			});
        },

        /**
         * 브랜치 정보 수정
         */

        updateInfo() {
            const url = this.$urlConstant.API_URL_PREFIX.BRANCH + this.$urlConstant.API_URL_SUFFIX.BRANCH.UPDATE;

            let params = {
                oid: this.$route.query.oid,
                areaCode: this.locationValue,
                name: this.officeNameInput,
                addr: this.addressInput,
                postCode: this.postNumInput,
                tel: this.phoneNumInput,
                descr: this.descr,
	            inputUser: this.$store.state.login.loginUser.userOid,
            };
	        this.$axios.post(url, params).then(res => {
				if (1 != res.data) {
					this.officeInfo = {};
					return;
				}
				this.$common.confirmSwal("수정 완료", "수정 요청이 정상적으로 처리되었습니다.", "success");
				this.goList();
			}).catch(error => {
				console.log(error);
			});
        },

        /**
         * 브랜치 정보 삭제
         */
        deleteBranch() {
            this.$common.swalWithOptions("확인", "해당 브랜치 정보를 삭제하시겠습니까?", "info").then(willDelete => {
                if (willDelete) {
                    const url = this.$urlConstant.API_URL_PREFIX.BRANCH + this.$urlConstant.API_URL_SUFFIX.BRANCH.DELETE;
                    let param = {
                        oid : this.$route.query.oid,
                    };
                    this.$axios.post(url, param).then(res => {
						if (1 === res.data) {
							this.goList();
						}
						else {
							console.log(res);
							return;
						}
					}).catch(error => {
						console.log(error);
					});
                }
                else {
                    return;
                }
            });
        },
    },
};
</script>

<style scoped></style>
