<template>
    <!-- 관리자 레이아웃 -->
    <div class="wrapper">
        <!-- 전체 페이지 로딩 -->
<!--        <div v-if="loadingIndicator > 0" class="loading-container">-->
<!--            <the-loading />-->
<!--        </div>-->
        <!-- 주 컨테이너 :: *( 관리자 ) -->
        <div id class="main-container manager-container">
            <!-- header -->
            <div class="manager-header">
                <div class="header-top-wrap">
                    <div class="header-top">
                        <h1 class="logo pull-left">
                            <nuxt-link :to="localePath('/kccam/manager/main/manager_main')">
                                <img src="@/assets/images/logo/logo_am_white.png" alt="KCC AM LOGO" />
                            </nuxt-link>
                        </h1>
                        <div class="auth-group pull-right">
                            <ul class="auth-info">
                                <li><span v-text="formatToday()"></span></li>
                                <!--                                <li><span>김형준</span></li>-->
                                <li>
                                    <a class="txt-link" @click="logoutUser">로그아웃</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <nav>
                        <ul class="menu-list">
                            <li @click="getTopMenuKeyUrl()" :class="getMenuActive('/productMgnt/', 0)">
                                <nuxt-link :to="localePath('/kccam/manager/productMgnt/materialClassification/materialClassification_list')">제품관리</nuxt-link>
                            </li>
                            <li @click="getTopMenuKeyUrl()" :class="getMenuActive('/marketMgnt/', 1)">
                                <nuxt-link :to="localePath('/kccam/manager/marketMgnt/market/market_list')">마켓관리</nuxt-link>
                            </li>
                            <li @click="getTopMenuKeyUrl()" :class="getMenuActive('/documentMgnt/', 2)">
                                <nuxt-link :to="localePath('/kccam/manager/documentMgnt/attachment/attachment_list')">문서관리</nuxt-link>
                            </li>
                            <li @click="getTopMenuKeyUrl()" :class="getMenuActive('/customerSupportMgnt/', 3)">
                                <nuxt-link :to="localePath('/kccam/manager/customerSupportMgnt/personalEnquiry/personalEnquiry_list')">고객지원관리</nuxt-link>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>

            <section class="manager-content-section">
                <h2 class="hidden">컨텐츠 섹션</h2>

                <!-- sidebar component -->
                <the-manager-side-nav :top-menu-key-url="sideMenuProp" :side-name="sideName" v-if="sideNav"></the-manager-side-nav>

                <!-- 페이지별 내용 -->
                <div class="content-area">
                    <Nuxt />
                </div>
            </section>

            <!-- top btn -->
            <transition name="fade">
                <div class="floating-btn" v-if="scroll">
                    <button type="button" class="btn-top" @click="goTop()">
                        <i class="material-icons">keyboard_tab</i>
                    </button>
                </div>
            </transition>
        </div>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import theManagerSideNav from "~/components/kccam/manager/sideNav/TheManagerSideNav.vue";
import {mapActions} from "vuex";

export default {
    components: {
        theLoading,
        theManagerSideNav,
    },
    data() {
        return {
            // scroll
            scroll: false,
            sideMenuProp: "",

            sideNameArray: ["제품관리", "마켓관리", "문서관리", "고객지원관리"],
            sideName: "",

            topMenuProp: "",

            // sidebar 보이기 여부 (메인에선 안보임)
            sideNav: false,
        };
    },
    mounted() {
        if (!process.client) {
            return;
        }
        // 스크롤 관련
        window.addEventListener("scroll", this.onScroll);
        // console.log(this.sideMenuProp);

        // sidebar 보이기 여부 (메인에선 안보임)
        this.sideNavVisible(this.$route.path);

        // 페이지가 시작될때 url을 가져옴
        this.getTopMenuKeyUrl();
        // console.log("this.getTopMenuKeyUrl() :", this.getTopMenuKeyUrl());
    },
    beforeDestroy() {
        if (!process.client) {
            return;
        }
        // 스크롤 관련
        window.removeEventListener("scroll", this.onScroll);
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    watch: {
        $route() {
	        // sidebar 보이기 여부 (메인에선 안보임)
            this.sideNavVisible(this.$route.path);
	        // 페이지가 시작될때 url을 가져옴
	        this.getTopMenuKeyUrl();
        },
    },
    methods: {
        // 스크롤 top 으로
        goTop() {
            animateScrollTo(0);
        },

        // 스크롤 관련
        onScroll(e) {
            // let scrollPosition = window.scrollY || document.documentElement.scrollTop;
            // let scrollPosition = document.querySelector("html").scrollTop;
            // let headerTop = document.getElementById("header").offsetTop;
            // if (scrollPosition > headerTop) {
            //     this.scroll = true;
            // } else {
            //     this.scroll = false;
            // }
        },
        activeMment() {},

        // 현재페이지의 3번째mgnt를 가져옵니다
        getTopMenuKeyUrl() {
            const keyUrl = this.$route.fullPath.split("/")[3];
            this.sideMenuProp = keyUrl;
        },

        getMenuActive(currentUrl, index) {
            let pathUrl = this.$route.fullPath;
            if (-1 < pathUrl.indexOf(currentUrl)) {
                this.getActiveName(index);
                return "active";
            } else {
                return "";
            }
        },

        getActiveName(index) {
            this.sideName = this.sideNameArray[index];
        },

        // setSideTit(index) {
        //     let value = this.sideNameArray[index];
        //     console.log(this.sideNameArray);
        //     return value;
        // },

        // sidebar 보이기 여부 (메인에선 안보임)
        sideNavVisible(path) {
            if (-1 < path.indexOf("/main")) {
                this.sideNav = false;
            } else {
                this.sideNav = true;
            }
        },
		formatToday(){
        	let date = new Date();
			let dayStrArr = ['일','월','화','수','목','금','토','일'];
			let today = this.$common.leadingZeros( date.getFullYear(), 4 ) +
				"-" + this.$common.leadingZeros( date.getMonth() + 1, 2 ) +
				"-" + this.$common.leadingZeros( date.getDate(), 2 ) +
				" " + '[' + dayStrArr[date.getDay()] + ']';
			return today;
		},
		...mapActions({
			logoutUser: "login/logout",
		}),

    },
};
</script>

<style scoped></style>
