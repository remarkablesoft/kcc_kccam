<template>
    <div class="inner-wrapper">
        <div class="loading-container" v-if="loadingIndicator > 0">
            <the-loading />
        </div>
        <!-- inner-container main -->
        <div class="inner-container main">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header">
                <!-- 메인 비주얼 스와이퍼 -->
                <the-main-visual />
            </div>
            <!-- content-body -->
            <div class="content-body">
                <!-- Youtube -->
                <div class="youtube">
                    <div class="youtube-tit">
                        <img src="@/assets/images/icon/icon_youtube.png" alt="유투브 로고" />
                        <p>
                            {{ $t(`user_main_youtube_tit`) }}
                        </p>
                        <!--다국어 test						-->
                        <!--                        						<br/>-->
                        <!--                        						<p>-->
                        <!--                        							{{ $t( 'welcome' ) }}-->
                        <!--                        						</p>-->
                        <!--다국어 test						-->
                    </div>
                    <el-button type="st st-white" size="small bar" round @click="dialogVisible = true">{{ $t(`user_main_youtube_play`) }} </el-button>
                </div>
                <!-- 메인 컨텐츠 앵커 -->
                <div id="main-content-anchor" class="main-content-anchor" :class="{ sticky: scroll }">
                    <div class="inner default-w">
                        <!-- 클릭 시 active 클래스 추가 -->
                        <!-- <a class="anchor active"  @click="goMainContent('products-content')">Products</a> -->
                        <a class="anchor" :class="{ active: selected === 'a' }" @click="[goTop(), (selected = 'a')]"
                            >{{ $t("user_common_product") }}
                        </a>
                        <a class="anchor" :class="{ active: selected === 'b' }" @click="[goMainContent('market-content'), (selected = 'b')]"
                            >{{ $t("user_common_market") }}
                        </a>
                        <a class="anchor" :class="{ active: selected === 'c' }" @click="[goMainContent('global-network-content'), (selected = 'c')]"
                            >{{ $t("user_common_kccIntro_globalNetwork") }}
                        </a>
                    </div>
                </div>
                <!-- main-content :: Products -->
                <div id="products-content" class="main-content products" v-if="!$fetchState.pending">
                    <div class="inner default-w">
                        <!-- 타이틀 -->
                        <div class="tit-area">
                            <h3 class="tit main-tit">{{ $t("user_common_product") }}</h3>
                        </div>
                        <!-- 목록 -->
                        <div class="main-list-swiper">
                            <swiper class="swiper" :options="mainListSwiperOption">
                                <swiper-slide class="list-item" v-for="(item, index) in materialList" :key="index">
                                    <!-- 버튼 클릭 시 active 클래스 추가. -->
                                    <!--
										소재 버튼/라벨 색상 변경 관련
										버튼은 일반 el-button 클래스 가짐.
										일반표시는 label 클래스 가짐.
										---------------------------------------------------
										label-material 클래스는 버튼/일반표시 동일하게 가짐.
										아래 소재에 따른 클래스 추가.
										---------------------------------------------------
										EMC :: emc
										Adhesive :: adhesive
										Metalized Ceramics :: metalized-ceramics
										Ceramic Substrates :: ceramic-substrates
										Glass Fiber :: glass-fiber
									-->
                                    <el-button
                                        type="lgray"
                                        size="small"
                                        round
                                        v-text="item.subMenuName"
                                        :class="[
                                            item.subMenuName === selectedMaterial.subMenuName ? 'active' : '',
                                            classToKebabCase(item.subMenuName),
                                        ]"
                                        v-on:click="selectedMaterial = item"
                                        class="label-material"
                                    ></el-button>
                                </swiper-slide>
                                <div class="swiper-button-prev" slot="button-prev">
                                    <span class="material-icons">arrow_back_ios</span>
                                </div>
                                <div class="swiper-button-next" slot="button-next">
                                    <span class="material-icons">arrow_forward_ios</span>
                                </div>
                            </swiper>
                        </div>
                        <!-- 내용 -->
                        <div class="inner-content">
                            <!-- 이미지 -->
                            <div class="img-box">
                                <!-- 이미지 경로
									emc : "@/assets/images/contents/product/contents_product_emc.png"
									전자소재 : "@/assets/images/contents/product/contents_product_adhesive.png"
									Metallized Ceramics : "@/assets/images/contents/product/contents_product_cs.png"
									Ceramic Substrates : "@/assets/images/contents/product/contents_product_mc.png"
								-->
                                <button>
	                                <img
                                        :src="materialImg"
                                        alt="제품 관련 이미지"
                                        class="img"
                                        @click="$router.push(localePath(selectedMaterial.subMenuUrl))"
                                    />
                                </button>
                                <div class="txt-area">
                                    <div class="tit" v-text="selectedMaterial.subMenuName"></div>
                                    <p class="sub-txt"></p>
                                </div>
                                <!-- 더 알아보기 버튼 :: 웹용 -->
                                <div class="btn-area web-visible">
                                    <button type="button" class="btn-more" @click="$router.push(localePath(selectedMaterial.subMenuUrl))">
                                        <span class="txt">{{ $t("user_common_product_mouseover_txt") }}</span>
                                        <span class="material-icons">arrow_forward_ios</span>
                                    </button>
                                </div>
                                <!-- 더 알아보기 버튼 :: 모바일용 -->
                                <div class="btn-area mob-visible">
                                    <button
                                        type="button"
                                        class="btn-more-mob mob-visible"
                                        @click="$router.push(localePath(selectedMaterial.subMenuUrl))"
                                    >
                                        <span class="txt">{{ $t("user_common_product_mouseover_txt") }}</span>
                                        <span class="material-icons">arrow_forward_ios</span>
                                    </button>
                                </div>
                            </div>
                            <!-- depth menu -->
                            <div class="product-list-group">
                                <div class="group-item">
                                    <div class="tit-bb">
                                        {{ $t("user_common_productClassification") }}
                                    </div>
                                    <!-- scroll area -->
                                    <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                        <!-- el1 -->
                                        <div>
                                            <!-- 내용은 이 안으로. -->
                                            <ul class="product-list">
                                                <li v-for="(item, index) in selectedMaterial.detailMenuItem[0].detailMenuList" :key="index">
                                                    <nuxt-link :to="localePath(item.detailMenuUrl)" v-text="item.detailMenuName"
                                                        >1000 SERIES
                                                    </nuxt-link>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="group-item">
                                    <div class="tit-bb">{{ $t("user_common_application") }}</div>
                                    <!-- scroll area -->
                                    <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                        <!-- el1 -->
                                        <div>
                                            <!-- 내용은 이 안으로. -->
                                            <ul class="product-list">
                                                <li v-for="(item, index) in selectedMaterial.detailMenuItem[1].detailMenuList" :key="index">
                                                    <nuxt-link :to="localePath(item.detailMenuUrl)" v-text="item.detailMenuName">Memory </nuxt-link>
                                                </li>
                                                <li style="display:none;">
                                                    <nuxt-link to="#">Power</nuxt-link>
                                                </li>
                                                <li style="display:none;">
                                                    <nuxt-link to="#">System IC</nuxt-link>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- main-content :: Market -->
                <div id="market-content" class="main-content market">
                    <div class="inner default-w">
                        <!-- 타이틀 -->
                        <div class="tit-area">
                            <h3 class="tit main-tit">{{ $t("user_common_market") }}</h3>
                        </div>
                        <!-- 페이지 바로가기 -->
                        <nuxt-link
                            :to="localePath('/kccam/user/market/market/market_list?depth=1&classification=market')"
                            class="tit-btn el-button el-button--xsmall is-round"
                        >
                            <span class="txt">{{ $t("user_common_market") }}</span>
                            <!-- <span class="material-icons">chevron_right</span> -->
                        </nuxt-link>
                        <div class="inner-content">
                            <div class="market-list-group">
                                <div class="detail-box">
                                    <!-- title 추가 -->
                                    <div class="tit-wrap">
                                        <span class="tit" v-text="$common.isNotEmpty(selectedMarket) ? selectedMarket.title : ''"></span>
                                    </div>
                                    <div class="descr-wrap">
                                        <!-- scroll area -->
                                        <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                            <!-- el1 -->
                                            <div>
                                                <!-- 내용은 이 안으로. -->
                                                <p v-html="$common.isNotEmpty(selectedMarket) ? selectedMarket.descr : ''"></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="main-list-swiper">
                                    <!--  v-show="false" -->
                                    <swiper class="swiper" :options="marketListswiperOption" ref="marketListSwiper">
                                        <!-- 개발 들어간 것 -->
	                                    <swiper-slide class="list-item" v-for="(market, marketIndex) in this.marketList" :key="marketIndex">
                                            <el-button type="white" size="small" v-text="market.title"></el-button>
                                        </swiper-slide>
                                        <!-- 이전/다음 버튼 -->
                                        <div class="swiper-button-prev" slot="button-prev">
                                            <span class="material-icons">arrow_back_ios</span>
                                        </div>
                                        <div class="swiper-button-next" slot="button-next">
                                            <span class="material-icons">arrow_forward_ios</span>
                                        </div>
                                    </swiper>
                                </div>
                            </div>
                            <div class="img-swiper-group">
                                <div class="img-box-wrap">
                                    <div class="img-box">
                                        <!-- 샘플 이미지 경로
									Automotive : "@/assets/images/contents/market/contents_market_automotive.png"
									Smartphone : "@/assets/images/contents/market/contents_market_smartphone.png"
									Industrial : "@/assets/images/contents/market/contents_market_industrial.png"
								-->
                                        <swiper class="swiper" :options="marketImgSwiperOption" ref="marketImgSwiper">
                                            <swiper-slide class="img-item" v-for="(market, marketIdx) in marketList" :key="marketIdx">
                                                <img
                                                    v-if="$common.isNotEmpty(market.storageFileUid)"
                                                    :src="'/storage/storageFile_fileView/' + market.storageFileUid"
                                                    alt="마켓 관련 이미지"
                                                    class="img"
                                                    @error="$common.imageError"
                                                />
                                                <img
                                                    v-else
                                                    src="@/assets/images/contents/market/contents_market_automotive.png"
                                                    alt="마켓 관련 이미지"
                                                    class="img"
                                                />
                                            </swiper-slide>
                                            <!-- 이전/다음 버튼 -->
                                            <div class="swiper-button-prev" slot="button-prev">
                                                <span class="material-icons">arrow_back_ios</span>
                                            </div>
                                            <div class="swiper-button-next" slot="button-next">
                                                <span class="material-icons">arrow_forward_ios</span>
                                            </div>
                                        </swiper>
                                        <!-- 더 알아보기 버튼 -->
                                        <div class="btn-area">
                                            <el-button
                                                type="st st-white"
                                                size="medium"
                                                class="btn-more is-arrow-icon is-round-alt"
                                                @click="
                                                    $router.push(localePath('/kccam/user/market/market/market_list?depth=1&classification=market'))
                                                "
                                            >
                                                <span class="txt">{{ $t("user_common_product_mouseover_txt") }}</span>
                                                <span class="material-icons">chevron_right</span>
                                            </el-button>
                                        </div>
                                    </div>
                                </div>
                                <!-- 숫자 페이지네이션 -->
                                <div class="swiper-pagination-fraction" slot="pagination"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- main-content :: Function -->
                <div id="function-content" class="main-content function">
                    <div class="inner default-w">
                        <!-- 타이틀 -->
                        <div class="tit-area mob-visible">
                            <h3 class="tit mob-lg">{{ $t("user_common_function") }}</h3>
                        </div>
                        <!-- 페이지 바로가기 -->
                        <nuxt-link
                            :to="localePath('/kccam/user/market/function/function_list?depth=1&classification=function')"
                            class="tit-btn el-button is-round"
                        >
                            <span class="txt">{{ $t("user_common_function") }}</span>
                            <!-- <span class="material-icons">chevron_right</span> -->
                        </nuxt-link>
                        <div class="inner-content">
                            <!-- 슬라이드형 function 목록 -->
                            <div class="product-img-list">
                                <!-- no-data(loading) -->
                                <div class="no-data" v-show="false">
                                    <div class="loading-sm">
                                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                    </div>
                                    <p>{{ $t(`user_common_nowLoading`) }}</p>
                                </div>
                                <!-- no-data -->
                                <div class="no-data" v-show="false">
                                    <span class="material-icons">error_outline</span>
                                    <span>{{ $t(`user_common_noData`) }}</span>
                                </div>
                                <!-- list-item -->
                                <div class="list-item" v-for="(item, index) in slideFunctionList" :key="index">
                                    <div class="inner">
                                        <!-- 1depth :: 목록 타이틀 버튼 -->
                                        <div
                                            class="tit-area"
                                            :class="item.active ? 'active' : ''"
                                            @click="
                                                item.active = !item.active;
                                                $forceUpdate();
                                            "
                                        >
                                            <span class="tit" v-text="item.title"><!--EMC--></span>
                                            <span class="material-icons">expand_more</span>
                                        </div>
                                        <!-- 제품 목록 -->
                                        <slide-up-down :active="item.active" :duration="300" class="inner-list">
                                            <!-- scroll area -->
                                            <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                                <!-- el1 -->
                                                <div>
                                                    <!-- contents -->
                                                    <div class="detail-content">
                                                        <ul class="detail-list alt">
                                                            <li
                                                                v-for="(detailItem, index) in item.functionDetailList"
                                                                :key="index"
                                                                @click="$router.push(localePath(detailItem.linkUrl))"
                                                            >
                                                                <p class="txt" v-text="detailItem.contents">
                                                                    <!-- High Adhesion -->
                                                                </p>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </slide-up-down>
                                        <!-- img -->
                                        <!-- <div class="img-box" @click="goMaterialView(item.subMenuUrl)">
											<img :src="getImageUrl(itemIndex)" alt="제품 관련 이미지" class="img" />
										</div> -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- main-content :: Global Network -->
                <div id="global-network-content" class="main-content global-network">
                    <div class="inner default-w">
                        <!-- 타이틀 -->
                        <div class="tit-area">
                            <h3 class="tit main-tit">{{ $t("user_common_kccIntro_globalNetwork") }}</h3>
                        </div>
                        <!-- 글로벌 네트워크 -->
                        <the-global-network :sub-contents-visible="false"></the-global-network>
                        <div class="btn-wrap bottom right">
                            <!-- 상세보기 :: 버튼형 -->
                            <el-button type="primary" size="large" class="is-round-alt is-shadow-primary" @click="goGlobalNetwork()">
                                <span class="txt">{{ $t("user_main_globalNetwork") }}</span>
                                <span class="material-icons">chevron_right</span>
                            </el-button>
                            <!-- 상세보기 :: 링크형 -->
                            <!-- <nuxt-link to="/kccam/user/intro/intro_globalNetwork?depth=1" class="txt-link-r">
								<span class="txt">{{ $t("user_main_globalNetwork") }}</span>
								<span class="material-icons">chevron_right</span>
							</nuxt-link> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 팝업 -->
        <!--        <the-main-popup v-if="popupShowYn" :main-popup-visible="popupShowYn" @close="closePopup()" />-->
        <!-- 유튜브 모달 ::TheYoutubeModal -->
        <!-- 유튜브 동영상 보여주기 모달 -->
        <el-dialog :visible.sync="dialogVisible" @close="dialogClose" width="80rem" class="video-container">
            <el-button class="btn-close" @click="dialogClose()">
                <span class="material-icons cl"> cancel </span>
            </el-button>
            <the-youtube-modal ref="youtubeModal" :youtube-id="youtubeId" />
        </el-dialog>

        <!-- 팝업 (레이어) -->
	    <the-layer-popup :visible="true"/>
        <!--popup zone 팝업-->
	    <the-popup-zone/>
    </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

import theLoading from "~/components/common/loading/TheLoading.vue";
import theMainVisual from "~/components/kccam/user/main/TheMainVisual.vue";
import theGlobalNetwork from "~/components/kccam/user/globalNetwork/TheGlobalNetwork.vue";
import theMainPopup from "~/components/kccam/user/modal/TheMainPopup.vue";
import theYoutubeModal from "~/components/kccam/user/modal/TheYoutubeModal.vue";
import TheLayerPopup from "~/components/common/popup/TheLayerPopup.vue";
import ThePopupZone from "~/components/common/popup/ThePopupZone.vue";
import Cookie from "js-cookie";
import nodataImg from "~/assets/images/contents/sample/contents_catalog_no_image.png";

export default {
    head: {
        title: "KCC AM - 메인",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "KCC AM 메인 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - 메인" },
            { name: "twitter:title", content: "KCC AM - 메인" },
        ],
    },
    layout: "userLayout",
    components: {
	    TheLayerPopup,
        theLoading,
        theMainVisual,
        theGlobalNetwork,
        theMainPopup,
        theYoutubeModal,
	    ThePopupZone
    },
    data() {
        return {
            // scroll
            scroll: false,

            // main anchor select
            selected: "a",

            // 메인 앵커 선택
            activeYn: false,
            activeClass: "active",

            // 메인 목록 스와이퍼
            mainListSwiperOption: {
                slidesPerView: "auto",
                spaceBetween: 0,
                // slidesPerGroup: '1',
                // centeredSlides: true,
                slideToClickedSlide: true,
                observer: true,
                observeParents: true,
                navigation: {
                    nextEl: ".swiper-button-next",
                    prevEl: ".swiper-button-prev",
                },
            },

            // 소재 목록
            // materialList: this.$store.state.menu.menuList[1].subMenuList,
            // materialList : [],

            // 선택된 소재 목록
            selectedMaterial: {},
            popupShowYn: true,
            selectedMarket: {
                title: "",
                descr: "",
            },

            // market list swiper
            marketListswiperOption: {
                loop: true,
                loopedSlides: 4, // looped slides should be the same
                spaceBetween: 0,
                centeredSlides: true,
                slidesPerView: "4",
                // touchRatio: 0.2,
                slideToClickedSlide: true,
                navigation: {
                    nextEl: ".swiper-button-next",
                    prevEl: ".swiper-button-prev",
                },
                breakpoints: {
                    1024: {
                        // 여기서부터 태블릿 세로/모바일.
                        loopedSlides: 0,
                        slidesPerView: "auto",
                        // centeredSlides: false,
                    },
                },
            },

            // market img swiper
            marketImgSwiperOption: {
                loop: true,
                loopedSlides: 4, // looped slides should be the same
                spaceBetween: 0,
                pagination: {
                    el: ".swiper-pagination-fraction",
                    type: "fraction",
                },
                navigation: {
                    nextEl: ".swiper-button-next",
                    prevEl: ".swiper-button-prev",
                },
                breakpoints: {
                    1024: {
                        // 여기서부터 태블릿 세로/모바일.
                        loopedSlides: 0,
                        slidesPerView: "auto",
                        // centeredSlides: false,
                    },
                },
            },

            // function 목록
            slideFunctionList: [],

            // 유튜브 dialog 모달
            dialogVisible: false,

            // 유튜브 영상 id
            youtubeId: "",
        };
    },
    async fetch() {
        if (!process.client) {
            return;
        }

        this.popupShowYn = await this.getPopupShow();

        await this.functionListData();
    },
    create() {},
    mounted() {
        if (!process.client) {
            return;
        }

        // 스크롤 관련
        window.addEventListener("scroll", this.onScroll);

        // market list/img swiper 같이 움직임
        this.$nextTick(() => {
            const marketListSwiper = this.$refs.marketListSwiper.swiper;
            const marketImgSwiper = this.$refs.marketImgSwiper.swiper;

            marketListSwiper.controller.control = marketImgSwiper;
            marketImgSwiper.controller.control = marketListSwiper;
        });
        this.setYoutubePath();

        this.swiper.on("slideChange", () => {
            this.onSwipe(this);
        });
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
            if (!process.client) {
                return;
            }
            return this.$nuxt.$root.$loading.percent;
        },

        materialList() {
            this.selectedMaterial = this.menuList[1].subMenuList[0];
            return this.menuList[1].subMenuList;
        },

        marketList() {
            if (!process.client) {
                return;
            }

            return this.$store.state.classification.marketList;
        },

        materialMenuList() {
            // console.log("computed materialMenuList");
            return this.$store.state.menu.materialMenuList;
        },

        swiper() {
            return this.$refs.marketListSwiper.swiper;
        },

        menuList() {
            return this.getMenuList();
        },

	    materialImg() {

			if ( this.$common.isNotEmpty( this.selectedMaterial.mainImg.storageFileUid ) ) {
				return "/storage/storageFile_fileView/" + this.selectedMaterial.mainImg.storageFileUid;
			}

			return nodataImg;
	    },
    },
    watch: {
        materialMenuList() {
            this.functionListData();
        },

	    selectedMaterial() {

			const relativeMarket = this.findRelativeMarket( this.selectedMaterial );
			this.selectedMarket = this.$common.isNotEmpty( relativeMarket ) ? relativeMarket : this.marketList[0];

			this.slideTo( this.selectedMarket );
	    }
    },

    methods: {
        //store 이벤트
        ...mapActions({
            getMaterialMenuList: "menu/getMaterialMenuList",
        }),

        ...mapGetters({
            getMenuList: "menu/menuList",
        }),

        // 메인 앵커 메뉴 클릭 시 각 영역으로 스크롤
        goMainContent(area) {
            let domId = "#" + area;

            let scrollToElement = document.querySelector(domId);

            const scrollOptions = {
                verticalOffset: -120, // header 높이 + 메인 앵커 높이 + 여백 조금
            };

            animateScrollTo(scrollToElement, scrollOptions);
        },

        // 스크롤 관련
        onScroll(e) {
            let scrollPosition = document.querySelector("html").scrollTop;
            let contentHeaderHeight = document.querySelector(".content-header").offsetHeight;

            if (scrollPosition >= contentHeaderHeight) {
                this.scroll = true;
            } else {
                this.scroll = false;
            }

            if (scrollPosition == 0) {
                this.selected = "a";
            }
        },

        // 스크롤 top 으로
        goTop() {
            animateScrollTo(0);
        },

        //메인 팝업 보여줄지 여부
        getPopupShow() {
            if (this.$common.isEmpty(Cookie.get("KAMMPSY")) || "N" === Cookie.get("mainPopup")) {
                return true;
            }

            return false;
        },
        closePopup() {
            this.popupShowYn = false;
        },

        classToKebabCase(itemName) {
            return _.kebabCase(_.lowerCase(itemName));
        },

        goGlobalNetwork() {
            this.$router.push(this.localePath("/kccam/user/intro/intro_globalNetwork?depth=1"));
        },

        async functionListData() {
            let materialMenuList = _.cloneDeep(await this.getMaterialMenuList());

            this.slideFunctionList = [];
            materialMenuList.forEach(item => {
                let obj = {
                    title: item.name,
                    active: true, // slide active

                    // detail 목록
                    functionDetailList: [],
                };

                let functionDetailList = [];

                item.functionList.forEach((func, funcIndex) => {
                    let obj = {
                        oid: func.oid,
                        contents: func.name,
                        linkUrl: "/kccam/user/market/function/function_view?depth=2&classificationOid=" + func.oid + "&classification=function",
                    };

                    functionDetailList.push(obj);
                });

                obj.functionDetailList = functionDetailList;
                this.slideFunctionList.push(obj);
            });
        },

        dialogClose() {
            this.dialogVisible = false;
            this.$refs.youtubeModal.stopVideo();
        },

        setYoutubePath() {
	        let lang = Cookie.get(this.$amConstant.AM_I18N_COOKIE_KEY);

            // 한국어일 경우 : 한글 영상
	        if ( lang == 'ko' ) {
		        this.youtubeId = "XUmrLpGNIpM";
	        }
	        else {
	        // 영어 또는 중국어일 경우 : 영어 영상
		        this.youtubeId = "kIno6n61_Xw";
	        }
        },
	    onSwipe(variable) {
            let index = variable.swiper.realIndex;
            this.selectedMarket = this.marketList[index];
        },

	    /**
	     * selectedMarket 에 해당하는 슬라이드로 이동합니다.
	     */
	    slideTo( selectedMarket ) {

			if ( this.$common.isEmpty( this.swiper ) ) {
				return;
			}
		    const activeIndex = this.swiper.activeIndex - this.swiper.realIndex;
		    const marketListActiveIndex = this.marketList.indexOf( selectedMarket ) + activeIndex;
		    this.swiper.slideTo( marketListActiveIndex );
	    },

	    findRelativeMarket( selectedMaterial ) {
			if ( this.$common.isEmpty( selectedMaterial.subMenuName ) ) {
				return this.marketList[0];
			}

		    const lang = Cookie.get(this.$amConstant.AM_I18N_COOKIE_KEY);
		    const relativeMarketTitle =  this.$amConstant.MATERIAL_MARKET_MAP[ selectedMaterial.subMenuName ][lang];
		    return this.marketList.find( market => market.title === relativeMarketTitle );
	    }
    },
};
</script>
<style lang="scss"></style>
