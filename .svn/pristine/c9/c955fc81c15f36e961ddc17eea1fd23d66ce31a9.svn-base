<template>
    <div class="inner-wrapper">
        <div class="loading-container" v-if="loadingIndicator > 0 || loadingFlag">
            <the-loading />
        </div>

        <!-- inner-container sub -->
        <div class="inner-container sub">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header intro">
                <div class="inner">
                    <div class="tit-area">
                        <h2 class="tit">{{ $t(`user_intro_introKcc_header_tit`) }}</h2>
                        <!--                        <p class="sub-txt">{{ $t(`user_intro_introKcc_header_subtxt`) }}</p>-->
                    </div>
                </div>
            </div>

            <!-- content-body -->
            <div class="content-body">
                <!-- 메뉴 경로 -->
                <the-breadcrumb />

                <!-- sub-content -->
                <div class="sub-content">
                    <!-- 내용은 여기에. -->
                    <!-- inner-content -->
                    <div class="inner-content">
                        <div class="default-w">
                            <div class="tit-area center">
                                <img :src="logoImg" alt="kcc 메인 로고" class="kcc-logo-img" :class="addImgClass ? 'global-logo' : ''" />
                            </div>
                            <div class="report-area">
                                <ul class="item-group">
                                    <li class="item">
                                        <span class="icon material-icons-outlined">fmd_good</span>
                                        <div class="txt">
                                            <span
                                                ><strong>{{ $t(`user_intro_introKcc_introSection_addr`) }}</strong></span
                                            >
                                        </div>
                                        <el-button type="lgray" round>{{ $t(`user_intro_introKcc_introSection_roundSpan_addr`) }}</el-button>
                                    </li>
                                    <li class="item">
                                        <span class="icon material-icons-outlined">business</span>
                                        <div class="txt">
                                            <span
                                                ><strong>{{ $t(`user_intro_introKcc_introSection_roundSpan_establish_contxt`) }}</strong></span
                                            >
                                        </div>
                                        <el-button type="lgray" round>{{ $t(`user_intro_introKcc_introSection_roundSpan_establish`) }}</el-button>
                                    </li>
                                    <li class="item">
                                        <span class="icon material-icons-outlined">person_outline</span>
                                        <div class="txt">
                                            <span
                                                ><strong>4,024{{ $t(`user_intro_introKcc_introSection_roundSpan_employees_number`) }}</strong></span
                                            >
                                            <span>{{ $t(`user_intro_introKcc_introSection_roundSpan_criteriaAsDate1`) }}</span>
                                        </div>
                                        <el-button type="lgray" round>{{ $t(`user_intro_introKcc_introSection_roundSpan_employees`) }}</el-button>
                                    </li>
                                    <li class="item">
                                        <span class="icon material-icons-outlined">monetization_on</span>
                                        <div class="txt">
                                            <span><strong>3,011,933</strong></span>
                                            <span
                                                >{{ $t(`user_intro_introKcc_introSection_roundSpan_criteriaAsDate2`) }} /
                                                {{ $t(`user_intro_introKcc_introSection_roundSpan_unit`) }}</span
                                            >
                                        </div>
                                        <el-button type="lgray" round>{{ $t(`user_intro_introKcc_introSection_roundSpan_sales`) }}</el-button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <!-- inner-content -->
                    <div class="inner-content">
                        <div class="default-w">
                            <div class="tit-area">
                                <h3 class="tit">{{ $t(`user_intro_introKcc_detail_tit`) }}</h3>
                            </div>
                            <div class="business-area">
                                <ul class="business-img-list">
                                    <li class="list-item">
                                        <div class="tit-bb">{{ $t(`user_intro_introKcc_detail_header1_tit`) }}</div>
                                        <div class="img-box">
                                            <img src="@/assets/images/contents/intro/contents_intro_kcc_01.png" alt="건축내장재 이미지" class="img" />
                                            <div class="label label-opacity">
                                                {{ $t(`user_intro_introKcc_detail_header1_contxt1`) }}
                                            </div>
                                        </div>
                                    </li>
                                    <li class="list-item">
                                        <div class="tit-bb">{{ $t(`user_intro_introKcc_detail_header2_tit`) }}</div>
                                        <div class="img-box">
                                            <img src="@/assets/images/contents/intro/contents_intro_kcc_02.png" alt="창호재 이미지" class="img" />
                                            <div class="label label-opacity">
                                                {{ $t(`user_intro_introKcc_detail_header2_contxt1`) }}
                                            </div>
                                        </div>
                                    </li>
                                    <li class="list-item">
                                        <div class="tit-bb">{{ $t(`user_intro_introKcc_detail_header3_tit`) }}</div>
                                        <div class="img-box">
                                            <img src="@/assets/images/contents/intro/contents_intro_kcc_03.png" alt="도료 이미지" class="img" />
                                            <div class="label label-opacity">
                                                {{ $t(`user_intro_introKcc_detail_header3_contxt1`) }}
                                            </div>
                                            <div class="label label-opacity">
                                                {{ $t(`user_intro_introKcc_detail_header3_contxt2`) }}
                                            </div>
                                        </div>
                                    </li>
                                    <li class="list-item">
                                        <div class="tit-bb">{{ $t(`user_intro_introKcc_detail_header4_tit`) }}</div>
                                        <div class="img-box">
                                            <img src="@/assets/images/contents/intro/contents_intro_kcc_04.png" alt="첨단소재 이미지" class="img" />
                                            <div class="label label-opacity">
                                                {{ $t(`user_intro_introKcc_detail_header4_contxt1`) }}
                                            </div>
                                            <div class="label label-opacity">
                                                {{ $t(`user_intro_introKcc_detail_header4_contxt2`) }}
                                            </div>
                                        </div>
                                    </li>
                                </ul>
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
import theBreadcrumb from "~/components/kccam/user/breadcrumb/TheBreadcrumb.vue";
import logoImgKO from "@/assets/images/logo/logo_kcc_blue.png";
import logoImgEN from "@/assets/images/logo/logo_kcc_blue_eng.jpg";
import cookie from "js-cookie";

export default {
    head: {
        title: "KCC AM - KCC 소개",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "KCC 소개 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - KCC 소개" },
            { name: "twitter:title", content: "KCC AM - KCC 소개" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
    },
    data() {
        return {
            logoImg: logoImgKO,
            addImgClass: false,

            loadingFlag: false,
        };
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    watch: {
        $route() {
            this.$fetch();
        },
    },
    fetch() {
        this.loadingFlag = true;
        this.setLogoImg();
        this.loadingFlag = false;
    },
    methods: {
        setLogoImg() {
            let lang = cookie.get(this.$amConstant.AM_I18N_COOKIE_KEY);
            if (lang === this.$amConstant.LANG.KO) {
                this.addImgClass = false;
                this.logoImg = logoImgKO;
            } else {
                this.addImgClass = true;
                this.logoImg = logoImgEN;
            }
        },
    },
};
</script>
