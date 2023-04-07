<template>
    <div class="inner-wrapper">
        <!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--            <the-loading />-->
        <!--        </div>-->

        <!-- inner-container sub -->
        <div class="inner-container sub">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header customer-support">
                <div class="inner">
                    <div class="tit-area">
                        <h2 class="tit">{{ $t("user_customerSupportInquiry_header_tit") }}</h2>
                        <!-- <p class="sub-txt">KCC소개 상세내용입니다.</p> -->
                    </div>
                </div>
            </div>

            <!-- content-body -->
            <div class="content-body">
                <!-- 메뉴 경로 -->
                <the-breadcrumb />

                <!-- sub-content -->
                <div class="sub-content inquiry">
                    <!-- 내용은 여기에. -->

                    <!-- inner-content :: 1대1 문의 -->
                    <div class="inner-content input-form-content" id="one-to-one-area">
                        <form class="input-form default-w">
                            <div class="input-group column-content">
                                <div class="tit-area">
                                    <h3 class="tit">{{ $t("user_customerSupportInquiry_header_tit") }}</h3>
                                </div>
                                <div class="input-area">
                                    <!-- input-row -->
                                    <div class="input-row">
                                        <!-- input-item -->
                                        <div class="input-item column">
                                            <div class="input-label">
                                                {{ $t("user_customerSupportInquiry_header_tit") }}
                                            </div>
                                            <div class="input-data">
                                                <el-select
                                                    class="bb-only"
                                                    v-model="selectedQuestionOid"
                                                    :placeholder="$t('user_customerSupportInquiry_oneToOne_placeholder')"
                                                    popper-class=""
                                                    :popper-append-to-body="true"
                                                >
                                                    <el-option
                                                        v-for="question in questionList"
                                                        :key="question.oid"
                                                        :label="question.configQuestion"
                                                        :value="question.oid"
                                                    >
                                                    </el-option>
                                                </el-select>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- input-row -->
                                    <div
                                        class="input-row"
                                        v-for="(detailQuestion, index) in selectedDetailQuestionMap[selectedQuestionOid]"
                                        :key="index"
                                    >
                                        <!-- input-item -->
                                        <div class="input-item column">
                                            <div class="input-label" v-text="detailQuestion.configQuestion"></div>
                                            <div class="input-data">
                                                <div class="el-input bb-only">
                                                    <input
                                                        title="입력창"
                                                        class="el-input__inner"
                                                        :placeholder="detailQuestion.configAnswer"
                                                        v-model="detailQuestion.completeAnswer"
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- 다음 버튼 -->
                            <div class="btn-wrap center">
                                <el-button type="st st-primary" size="xlarge" @click="[goContent('required-input')]">{{
                                    $t("user_common_next")
                                }}</el-button>
                            </div>
                        </form>
                    </div>

                    <!-- inner-content :: 필수입력/선택입력 -->
                    <div class="inner-content input-form-content" id="required-input">
                        <form class="input-form default-w">
                            <!-- input-group :: 필수입력 -->
                            <div class="input-group column-content">
                                <div class="tit-area">
                                    <div>
                                        <h3 class="tit">{{ $t("user_customerSupportInquiry_essential_tit_header") }}<em class="required">*</em></h3>
                                        <p class="sub-txt main-color">
                                            {{ $t("user_customerSupportInquiry_essential_tit_subTxt") }}
                                        </p>
                                    </div>
                                </div>
                                <div class="input-area">
                                    <!-- input-row -->
                                    <div class="input-row">
                                        <!-- input-item -->
                                        <div class="input-item column">
                                            <div class="input-label">
                                                {{ $t("user_customerSupportInquiry_essential_email_label") }}
                                            </div>
                                            <div class="input-data">
                                                <el-input
                                                    title="이메일 아이디 입력창"
                                                    class="bb-only"
                                                    :placeholder="$t('user_customerSupportInquiry_essential_email_placeholder1')"
                                                    v-model="emailInput"
                                                ></el-input>
                                                <div class="divider main-color">@</div>
                                                <el-input
                                                    title="이메일 서버 입력창"
                                                    class="bb-only"
                                                    :placeholder="$t('user_customerSupportInquiry_essential_email_placeholder2')"
                                                    v-model="emailInput2"
                                                ></el-input>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- input-row -->
                                    <div class="input-row">
                                        <!-- input-item -->
                                        <div class="input-item column">
                                            <div class="input-label">
                                                {{ $t("user_customerSupportInquiry_essential_company_label") }}
                                            </div>
                                            <div class="input-data">
                                                <el-input
                                                    title="회사명 입력창"
                                                    class="bb-only"
                                                    :placeholder="$t('user_customerSupportInquiry_essential_company_placeholder')"
                                                    v-model="oneToOneInfo.inputUserInfo.organizationName"
                                                ></el-input>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- input-group :: 선택입력 -->
                            <div class="input-group column-content">
                                <div class="tit-area">
                                    <div>
                                        <h3 class="tit">{{ $t("user_customerSupportInquiry_elective_tit") }}</h3>
                                    </div>
                                </div>
                                <div class="input-area">
                                    <!-- input-row -->
                                    <div class="input-row">
                                        <!-- input-item -->
                                        <div class="input-item column">
                                            <div class="input-label">
                                                {{ $t("user_customerSupportInquiry_elective_phone_label") }}
                                            </div>
                                            <div class="input-data">
                                                <el-input
                                                    title="휴대폰 번호 입력창"
                                                    class="bb-only"
                                                    placeholder="000"
                                                    v-model="oneToOneInfo.inputUserInfo.phonePart1"
                                                ></el-input>
                                                <div class="main-color">-</div>
                                                <el-input
                                                    title="휴대폰 번호 입력창"
                                                    class="bb-only"
                                                    placeholder="0000"
                                                    v-model="oneToOneInfo.inputUserInfo.phonePart2"
                                                ></el-input>
                                                <div class="main-color">-</div>
                                                <el-input
                                                    title="휴대폰 번호 입력창"
                                                    class="bb-only"
                                                    placeholder="0000"
                                                    v-model="oneToOneInfo.inputUserInfo.phonePart3"
                                                ></el-input>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- input-row -->
                                    <div class="input-row">
                                        <!-- input-item -->
                                        <div class="input-item column">
                                            <div class="input-label">
                                                {{ $t("user_customerSupportInquiry_elective_name_label") }}
                                            </div>
                                            <div class="input-data">
                                                <el-input
                                                    title="성 입력창"
                                                    class="bb-only"
                                                    :placeholder="$t('user_customerSupportInquiry_elective_name_placeholder1')"
                                                    v-model="familyNameInput"
                                                ></el-input>
                                                <el-input
                                                    title="이름 입력창"
                                                    class="bb-only"
                                                    :placeholder="$t('user_customerSupportInquiry_elective_name_placeholder2')"
                                                    v-model="firstNameInput"
                                                ></el-input>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- input-row -->
                                    <div class="input-row">
                                        <!-- input-item -->
                                        <div class="input-item column">
                                            <div class="input-label">
                                                {{ $t("user_customerSupportInquiry_elective_department_label") }}
                                            </div>
                                            <div class="input-data">
                                                <el-input
                                                    title="부서 입력창"
                                                    class="bb-only"
                                                    :placeholder="$t('user_customerSupportInquiry_elective_department_placeholder')"
                                                    v-model="oneToOneInfo.inputUserInfo.customField1"
                                                ></el-input>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- input-row -->
                                    <div class="input-row">
                                        <!-- input-item -->
                                        <div class="input-item column">
                                            <div class="input-label">
                                                {{ $t("user_customerSupportInquiry_elective_tel_label") }}
                                            </div>
                                            <div class="input-data">
                                                <el-input
                                                    title="전화번호 입력창"
                                                    class="bb-only"
                                                    placeholder="0000"
                                                    v-model="oneToOneInfo.inputUserInfo.telPart1"
                                                ></el-input>
                                                <div class="main-color">-</div>
                                                <el-input
                                                    title="전화번호 입력창"
                                                    class="bb-only"
                                                    placeholder="0000"
                                                    v-model="oneToOneInfo.inputUserInfo.telPart2"
                                                ></el-input>
                                                <div class="main-color">-</div>
                                                <el-input
                                                    title="전화번호 입력창"
                                                    class="bb-only"
                                                    placeholder="0000"
                                                    v-model="oneToOneInfo.inputUserInfo.telPart3"
                                                ></el-input>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- input-row -->
                                    <div class="input-row">
                                        <!-- input-item -->
                                        <div class="input-item column">
                                            <div class="input-label">
                                                {{ $t("user_customerSupportInquiry_elective_country_label") }}
                                            </div>
                                            <div class="input-data">
                                                <el-input
                                                    title="국가 입력창"
                                                    class="bb-only"
                                                    :placeholder="$t('user_customerSupportInquiry_elective_country_placeholder')"
                                                    v-model="oneToOneInfo.inputUserInfo.customField2"
                                                ></el-input>
                                            </div>
                                        </div>
                                        <!-- input-item -->
                                        <div class="input-item column">
                                            <div class="input-label">
                                                {{ $t("user_customerSupportInquiry_elective_city_label") }}
                                            </div>
                                            <div class="input-data">
                                                <el-input
                                                    title="도시 입력창"
                                                    class="bb-only"
                                                    :placeholder="$t('user_customerSupportInquiry_elective_city_placeholder')"
                                                    v-model="oneToOneInfo.inputUserInfo.customField3"
                                                ></el-input>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- 다음 버튼 -->
                            <div class="btn-wrap center">
                                <el-button type="st st-primary" size="xlarge" @click="[goContent('attachment')]">{{
                                    $t("user_common_next")
                                }}</el-button>
                            </div>
                        </form>
                    </div>

                    <!-- inner-content :: 문의내용/첨부파일 -->
                    <div class="inner-content input-form-content" id="attachment">
                        <form class="input-form default-w">
                            <!-- input-group :: 문의내용 -->
                            <div class="input-group column-content">
                                <div class="tit-area">
                                    <h3 class="tit">{{ $t("user_customerSupportInquiry_context_tit") }}</h3>
                                </div>
                                <div class="input-area">
                                    <!-- input-row -->
                                    <div class="input-row">
                                        <!-- input-item -->
                                        <div class="input-item column">
                                            <div class="input-label">
                                                {{ $t("user_customerSupportInquiry_context_label") }}
                                            </div>
                                            <div class="input-data">
                                                <el-input
                                                    title="문의제목 입력창"
                                                    class="bb-only"
                                                    :placeholder="$t('user_customerSupportInquiry_context_placeholder1')"
                                                    v-model="oneToOneInfo.title"
                                                ></el-input>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- input-row -->
                                    <div class="input-row">
                                        <el-input
                                            title="문의내용 입력창"
                                            type="textarea"
                                            :rows="10"
                                            :placeholder="$t('user_customerSupportInquiry_context_placeholder2')"
                                            v-model="oneToOneInfo.descr"
                                        >
                                        </el-input>
                                    </div>
                                </div>
                            </div>

                            <!-- input-group :: 첨부파일 -->
                            <div class="input-group column-content">
                                <div class="tit-area">
                                    <h3 class="tit">{{ $t("user_customerSupportInquiry_attach_tit") }}</h3>
                                </div>
                                <div class="input-area">
                                    <!-- dropzone -->
                                    <the-dropzone
                                        ref="dropzoneFile"
                                        @setUploadFile="setStorageFileList"
                                        :fileType="$amConstant.OBJECT_TYPE.ONE_TO_ONE"
                                    />
                                </div>
                            </div>

                            <!-- 완료 버튼 -->
                            <div class="btn-wrap center">
                                <el-button type="primary" size="xlarge" @click="save">{{ $t("user_common_complete") }}</el-button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import theBreadcrumb from "~/components/kccam/user/breadcrumb/TheBreadcrumb.vue";
import theDropzone from "~/components/common/dropzone/TheDropZone.vue";

export default {
    head: {
        title: "KCC AM - 1:1 문의",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "고객지원 1:1 문의 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - 1:1 문의" },
            { name: "twitter:title", content: "KCC AM - 1:1 문의" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        theDropzone,
    },
    data() {
        return {
            // 문의종류 선택
            questionList: [], // 1대1 문의 질문 목록
            selectedQuestionOid: "", // 1대1 문의 선택 질문 OID
            selectedDetailQuestionMap: {}, // 선택된 질문 하위 질문 목록 맵
            questionNameOidMap: {},

            // 이름 입력창
            familyNameInput: "", // 성
            firstNameInput: "", // 이름

            // 이메일 입력창
            emailInput: "",
            emailInput2: "",

            oneToOneInfo: {
                title: "",
                configOid: "",
                descr: "",

                inputUserInfo: {
                    name: "",
                    userType: this.$amConstant.USER_TYPE.CLIENT,
                    email: "",

                    phonePart1: "",
                    phonePart2: "",
                    phonePart3: "",

                    telPart1: "",
                    telPart2: "",
                    telPart3: "",

                    organizationName: "", // 회사명
                    customField1: "", // 부서명
                    customField2: "", // 국가명
                    customField3: "", // 도시명
                },

                fileList: [],
                oneToOneDetailList: [],
                receiverEmailList: [],
            },
        };
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    async fetch() {
        await this.getOneToOneConfigList();
    },
    methods: {
        // 앵커 메뉴 클릭 시 각 영역으로 스크롤
        goContent(area) {
            let domId = "#" + area;

            let scrollToElement = document.querySelector(domId);

            const scrollOptions = {
                verticalOffset: -120, // header 높이 + 메인 앵커 높이 + 여백 조금
            };

            animateScrollTo(scrollToElement, scrollOptions);
        },

        /**
         * 1대1 문의 설정 정보를 불러옵니다.
         */
        async getOneToOneConfigList() {
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE_CONFIG + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE_CONFIG.LIST_ALL;
            const param = {
                useYn: "Y",
            };
            await this.$axios.post(url, param).then(res => {
                this.groupingByCategoryType(res.data);
            });
        },

        /**
         * 문의 설정 타입으로 문의 정보를 나눠줍니다.
         */
        groupingByCategoryType(list) {
            if (this.$common.isEmpty(list)) {
                return;
            }

            const _self = this;
            let questionArr = [];
            let detailQuestionArr = [];
            let receiverEmailArr = [];

            _.each(list, function(item) {
                if (item.configType == _self.$amConstant.ONE_TO_ONE_CONFIG_TYPE.QUESTION) {
                    questionArr.push(item);
                }
                if (_self.$common.isNotEmpty(item.detailConfigQuestionList)) {
                    _.each(item.detailConfigQuestionList, function(dQuestion) {
                        detailQuestionArr.push(dQuestion);
                    });
                }
                if (_self.$common.isNotEmpty(item.receiverEmailList)) {
                    _.each(item.receiverEmailList, function(email) {
                        receiverEmailArr.push(email);
                    });
                }
            });
            this.setDetailQuestionList(questionArr, detailQuestionArr);
            this.oneToOneInfo.receiverEmailList = receiverEmailArr;
        },

        /**
         * 각 질문에 알맞는 상세질문을 설정해줍니다.
         */
        setDetailQuestionList(questionArr, detailQuestionArr) {
            if (this.$common.isEmpty(questionArr) || this.$common.isEmpty(detailQuestionArr)) {
                return;
            }

            const _self = this;
            _.each(questionArr, function(question, index) {
                let mappedDetailQuestionArr = [];
                _.each(detailQuestionArr, function(detailQuestion, detailIndex) {
                    if (question.oid === detailQuestion.parentOid) {
                        detailQuestion.completeAnswer = "";
                        mappedDetailQuestionArr.push(detailQuestion);
                    }
                });
                _self.selectedDetailQuestionMap[question.oid] = mappedDetailQuestionArr;
                _self.questionNameOidMap[question.oid] = question.configQuestion;
            });

            this.questionList = questionArr;
        },

        /**
         * 파일 정보를 설정해줍니다.
         * @param data
         */
        setStorageFileList(data) {
            this.oneToOneInfo.fileList = data;
        },

        /**
         * 1대1 문의 정보를 저장합니다.
         */
        save() {
            const _self = this;
            this.oneToOneInfo.oneToOneDetailList = this.setOtoDetailList();
            let validationResult = this.validationOtoInfo();
            if (!this.$common.isEmpty(validationResult)) {
                this.goContent(validationResult);
                return;
            }

            this.oneToOneInfo.inputUserInfo.name = this.familyNameInput + this.firstNameInput;
            this.oneToOneInfo.configOid = this.selectedQuestionOid;

            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE.SAVE;

            let reqParam = this.oneToOneInfo;
            this.$axios
                .post(url, reqParam)
                .then(res => {
                    if (!_self.$common.isEmpty(res.data.oid)) {
                        _self.$common.confirmSwal(
                            _self.$t("user_customerSupportInquiry_swal_inquiry_save"),
                            _self.$t("user_customerSupportInquiry_swal_save_success"),
                            "success",
                        );
                        _self.$fetch();
                    } else {
                        _self.$common.confirmSwal(
                            _self.$t("user_customerSupportInquiry_swal_inquiry_save"),
                            _self.$t("user_customerSupportInquiry_swal_save_error"),
                            "warning",
                        );
                    }
                })
                .catch(error => {
                    console.log(error);
                });
        },

        /**
         * 1대1 문의 세부정보를 설정합니다.
         */
        setOtoDetailList() {
            const _self = this;
            const detailQuestionList = this.selectedDetailQuestionMap[this.selectedQuestionOid];

            let oneToOneDetailList = [];
            _.each(detailQuestionList, function(item, index) {
                let otoDetailInfo = {
                    detailType: _self.$amConstant.ONE_TO_ONE_CONFIG_TYPE.DETAIL_QUESTION,
                    detailQuestion: item.configQuestion,
                    detailAnswer: item.completeAnswer,
                };

                oneToOneDetailList.push(otoDetailInfo);
            });

            return oneToOneDetailList;
        },

        /**
         * 1대1 문의정보 필수값 입력 체크
         */
        validationOtoInfo() {
            const _self = this;

            if (_self.$common.isEmpty(_self.selectedQuestionOid)) {
                _self.$common.confirmSwal(
                    _self.$t("user_customerSupportInquiry_swal_inquiry"),
                    _self.$t("user_customerSupportInquiry_swal_inquiry_needCategory"),
                    "warning",
                );
                return "one-to-one-area";
            } else {
                let detailQuestionList = _self.selectedDetailQuestionMap[_self.selectedQuestionOid];
                let returnResult = true;
                _.each(detailQuestionList, function(item, index) {
                    if (_self.$common.isEmpty(item.completeAnswer)) {
                        _self.$common.confirmSwal(
                            _self.$t("user_customerSupportInquiry_swal_inquiry"),
                            _self.$t("user_customerSupportInquiry_swal_inquiry_needlQuestion"),
                            "warning",
                        );
                        returnResult = false;
                        return;
                    }
                });

                if (!returnResult) {
                    return "one-to-one-area";
                }
            }

            if (_self.$common.isEmpty(_self.emailInput) || _self.$common.isEmpty(_self.emailInput2)) {
                _self.$common.confirmSwal(
                    _self.$t("user_customerSupportInquiry_swal_inquiry_needEssential"),
                    _self.$t(user_customerSupportInquiry_swal_inquiry_needEmail),
                    "warning",
                );
                return "required-input";
            } else {
                _self.oneToOneInfo.inputUserInfo.email = _self.emailInput + "@" + _self.emailInput2;
            }

            if (_self.$common.isEmpty(_self.oneToOneInfo.inputUserInfo.organizationName)) {
                _self.$common.confirmSwal(
                    _self.$t("user_customerSupportInquiry_swal_inquiry_needEssential"),
                    _self.$t("user_customerSupportInquiry_swal_inquiry_needCompany"),
                    "warning",
                );
                return "required-input";
            }

            if (_self.$common.isEmpty(_self.oneToOneInfo.title)) {
                _self.$common.confirmSwal(
                    _self.$t("user_customerSupportInquiry_swal_inquiry_needContext"),
                    _self.$t("user_customerSupportInquiry_swal_inquiry_needTitle"),
                    "warning",
                );
                return "attachment";
            }
        },
    },
};
</script>

<style></style>
