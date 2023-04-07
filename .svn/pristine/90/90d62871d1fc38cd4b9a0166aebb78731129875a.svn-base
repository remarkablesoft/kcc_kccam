<template>
    <div class="inner-wrapper">
        <!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--            <the-loading />-->
        <!--        </div>-->

        <!-- inner-container sub -->
        <div class="inner-container sub mb0">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header market">
                <div class="inner">
                    <div class="tit-area">
                        <h2 class="tit">{{ $t(`user_market_main_header_tit`) }}</h2>
                        <p class="sub-txt" v-html="$t(`user_market_view_header_subtxt`)"></p>
                    </div>
                </div>
            </div>

            <!-- content-body -->
            <div class="content-body market">
                <!-- 메뉴 경로 -->
                <the-breadcrumb />

                <!-- sub-content -->
                <div class="sub-content market-main">
                    <!-- 내용은 여기에. -->

                    <!-- inner-content :: Market -->
                    <div class="inner-content" v-for="(item, index) in MarketMainContent" :key="index">
                        <div class="default-w">
                            <!-- market-slide-item : active 클래스 추가 시 액션 동작 -->
                            <div class="market-slide-item" :class="{ active: item.slideActive }">
                                <!-- 설명 영역 -->
                                <div class="descr-area">
                                    <div class="visible-area">
                                        <h3 class="tit" v-text="item.title"><!-- Market --></h3>

                                        <ul class="square-dot-list">
                                            <li v-for="(descrItem, descrIndex) in item.descrList" :key="descrIndex">
                                                <p class="txt" v-text="descrItem.descr">
                                                    <!-- Lorem ipsum dolor sit amet, consectetur adipisicing elit,-->
                                                </p>
                                            </li>
                                        </ul>

                                        <!-- 화면 이동 버튼 -->
                                        <el-button
                                            type="st st-primary"
                                            size="large"
                                            class="is-arrow-icon no-radius"
                                            @click="$router.push(localePath(item.btnUrl))"
                                        >
                                            <span class="txt" v-text="item.btnText"><!-- Market 화면으로 이동 --></span>
                                            <span class="material-icons">chevron_right</span>
                                        </el-button>

                                        <!-- 화살표 버튼 -->
                                        <div class="btn-arrow">
                                            <el-button type="circle-arrow" @click="item.slideActive = !item.slideActive">
                                                <span class="material-icons">arrow_right_alt</span>
                                            </el-button>
                                        </div>
                                    </div>

                                    <!-- 숨겨져있다 나오는 부분 -->
                                    <div class="hidden-area">
                                        <!-- img -->
                                        <div class="img-box">
                                            <!-- scroll area -->
                                            <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                                <!-- el1 -->
                                                <div>
                                                    <!-- 내용은 이 안으로. -->
                                                    <!-- 하위레벨 메뉴 -->
                                                    <ul class="hover-txt-list">
                                                        <li v-for="(marketItem, marketItemIndex) in item.marketItemList" :key="marketItemIndex">
                                                            <span
                                                                class="txt"
                                                                v-text="marketItem.title"
                                                                @click="$router.push(localePath(marketItem.linkUrl))"
                                                                @mouseover="item.imgUrl = getImageUrl(item.title, marketItemIndex)"
                                                            >
                                                            </span>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>

                                            <!-- market item mouseover 시 바뀌는 이미지 -->
                                            <img :src="item.imgUrl" alt="market 관련 이미지" class="img" />
                                        </div>
                                    </div>
                                </div>

                                <!-- 기본 이미지 영역 -->
                                <div class="default-img-area">
                                    <div class="img-box">
                                        <!-- img :: css 배경으로 처리. -->
                                        <div class="img"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapActions } from "vuex";

import theLoading from "~/components/common/loading/TheLoading.vue";
import theBreadcrumb from "~/components/kccam/user/breadcrumb/TheBreadcrumb.vue";

// market 샘플 이미지
import marketImg1 from "@/assets/images/contents/market/contents_market_semiconductor.png"; // semiconductor
import marketImg2 from "@/assets/images/contents/market/contents_market_electronicsIndustry.png"; // 전자산업
import marketImg3 from "@/assets/images/contents/market/contents_market_smartphone.png"; // Mobile
import marketImg4 from "@/assets/images/contents/market/contents_market_satellite.png"; // 위성
import marketImg5 from "@/assets/images/contents/market/contents_market_industrial.png"; // 일반산업
import marketImg6 from "@/assets/images/contents/market/contents_market_electronicAppliance.png"; // 전기전자가전
import marketImg7 from "@/assets/images/contents/market/contents_market_automotive.png"; // AutoMotive
import marketImg8 from "@/assets/images/contents/market/contents_market_ship.png"; // 선박/해양플랜트
import marketImg9 from "@/assets/images/contents/market/contents_market_homeAppliance.png"; // 가전
import marketImg10 from "@/assets/images/contents/market/contents_market_architecture.png"; // 건축
import marketImg11 from "@/assets/images/contents/market/contents_market_medical.png"; // 의료용
import marketImg12 from "@/assets/images/contents/market/contents_market_chemistry.png"; // 화학/산업자재

// function 샘플 이미지
import functionImg1 from "@/assets/images/contents/product/contents_product_emc.png"; // emc
import functionImg2 from "@/assets/images/contents/product/contents_product_adhesive_02.png"; // 전자소재
import functionImg3 from "@/assets/images/contents/product/contents_product_mc_02.png"; // Metallized Ceramics

export default {
    head: {
        title: "KCC AM - Market 메인",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Market 메인 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Market 메인" },
            { name: "twitter:title", content: "KCC AM - Market 메인" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
    },
    data() {
        return {
            // 마켓 메인 컨텐트
            MarketMainContent: [
                // Market
                {
                    // 슬라이드
                    slideActive: false,

                    // 타이틀
                    title: this.$t(`user_market_main_header_tit`),

                    // descr
                    descrList: [
                        {
                            descr: this.$t(`user_market_main_descr1`),
                        },
                        {
                            descr: this.$t(`user_market_main_descr2`),
                        },
                    ],

                    // 버튼
                    btnText: this.$t(`user_market_main_button_market`),
                    btnUrl: "/kccam/user/market/market/market_list?depth=1&classification=market",

                    // default img
                    imgUrl: marketImg1,

                    // market
                    marketItemList: [],
                },

                // Function
                {
                    // 슬라이드
                    slideActive: false,

                    // 타이틀
                    title: this.$t(`user_market_main_function_tit`),

                    // descr
                    descrList: [
                        {
                            descr: this.$t(`user_market_main_function_descr1`),
                        },
                    ],

                    // 버튼
                    btnText: this.$t(`user_market_main_button_function`),
                    btnUrl: "/kccam/user/market/function/function_list?depth=1&classification=function",

                    // default img
                    imgUrl: functionImg1,

                    marketItemList: [],
                },
            ],
        };
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },

        functionList() {
            return this.$store.state.classification.functionList;
        },

        marketList() {
            return this.$store.state.classification.marketList;
        },
    },

    mounted() {
        this.MarketMainContent[0].marketItemList = this.marketList;

        this.MarketMainContent[1].marketItemList = this.functionList;
    },

    watch: {
        marketList: function() {
            //console.log("1");
            this.MarketMainContent[0].marketItemList = this.marketList;
        },

        functionList: function() {
            //console.log("2");
            this.MarketMainContent[1].marketItemList = this.functionList;
        },
    },

    methods: {
        //store 이벤트
        ...mapActions({
            // setClassificationList: "classification/setClassificationListData",
            getMarketList: "classification/getMarketList",
        }),

        getImageUrl(title, index) {
            if (isNaN(index)) {
                return;
            }

            if (title == "Market") {
                switch (index) {
                    case 0:
                        return marketImg1;
                    case 1:
                        return marketImg2;
                    case 2:
                        return marketImg3;
                    case 3:
                        return marketImg4;
                    case 4:
                        return marketImg5;
                    case 5:
                        return marketImg6;
                    case 6:
                        return marketImg7;
                    case 7:
                        return marketImg8;
                    default:
                        return marketImg12;
                }
            } else if (title == "Function") {
                switch (index) {
                    case 0:
                        return functionImg1;
                    case 1:
                        return functionImg2;
                    case 2:
                        return functionImg3;
                    default:
                        return functionImg3;
                }
            }
        },
    },
};
</script>

<style></style>
