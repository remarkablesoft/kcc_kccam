<template>
    <div class="inner-wrapper">
        <!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--            <the-loading />-->
        <!--        </div>-->

        <!-- inner-container sub -->
        <div class="inner-container sub">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header market">
                <div class="inner">
                    <div class="tit-area">
                        <h2 class="tit">{{ $t(`user_common_function`) }}</h2>
                        <p class="sub-txt">{{ $t(`user_market_function_detail_tit`) }}</p>
                    </div>
                </div>
            </div>

            <!-- content-body -->
            <div class="content-body">
                <!-- 메뉴 경로 -->
                <the-breadcrumb />

                <!-- sub-content -->
                <div class="sub-content is-side-nav-content">
                    <!-- 내용은 여기에. -->
                    <div class="default-w">
                        <!-- fixed side nav 컴포넌트 -->
                        <the-side-nav />

                        <div class="content-area">
                            <!-- inner-content -->
                            <div class="inner-content">
                                <div class="tit-area is-border">
                                    <h3 class="tit">{{ $t(`user_common_function`) }}</h3>
                                </div>
                                <div class="market-img-list no-btn">
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
                                    <div class="list-item" v-for="(item, index) in functionList" :key="index">
                                        <div class="tit" v-text="item.title"><!-- Semiconductor --></div>
                                        <div class="img-detail-group" :class="item.active ? 'active' : ''">
                                            <!-- class="item.active ? 'active' : ''" -->
                                            <div class="img-box">
                                                <img :src="getImageUrl(index)" alt="market 관련 이미지" class="img" />
                                            </div>
                                            <!-- detail -->
                                            <div class="detail-box">
                                                <!-- btn -->
                                                <!-- <button type="button" class="btn-expand-detail" @click="item.active = !item.active">
                                                <span class="material-icons">keyboard_arrow_up</span>
                                            </button> -->
                                                <!-- contents -->
                                                <div class="detail-content">
                                                    <div class="label label-sm label-black">
                                                        {{ $t(`user_common_function`) }}
                                                    </div>
                                                    <!-- scroll area -->
                                                    <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                                        <!-- el1 -->
                                                        <div>
                                                            <!-- el2 -->
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
                                            </div>
                                        </div>
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
import theLoading from "~/components/common/loading/TheLoading.vue";
import theBreadcrumb from "~/components/kccam/user/breadcrumb/TheBreadcrumb.vue";

// function 샘플 이미지
import functionImg01 from "@/assets/images/contents/product/contents_product_emc.png"; // emc
import functionImg02 from "@/assets/images/contents/product/contents_product_adhesive_02.png"; // 전자소재
import functionImg03 from "@/assets/images/contents/product/contents_product_mc_02.png"; // Metallized Ceramics
import functionImg04 from "@/assets/images/contents/product/contents_product_lf.png"; // 장섬유

export default {
    head: {
        title: "KCC AM - Function 목록",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Function 목록 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Function 목록" },
            { name: "twitter:title", content: "KCC AM - Function 목록" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
    },
    data() {
        return {
            functionList: [],
        };
    },

    mounted() {
        this.menuListData();
    },

    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },

    methods: {
        goView(oid) {
            let urlView = "/kccam/user/market/function/function_view?oid=" + oid;
            this.$router.push(this.localePath(urlView));
        },

        menuListData() {
            let param = {};

            const url = this.$urlConstant.API_URL_PREFIX.MATERIAL + this.$urlConstant.API_URL_SUFFIX.MATERIAL.MENU_LIST;
            this.$axios.post(url, param).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }

                //console.log( res.data );

                res.data.forEach((item, index) => {
                    let obj = {
                        title: item.name,
                        //           imgUrl: functionImg04,
                        active: true, // slide active
                        //           linkUrl: "/kccam/user/market/function/function_view",

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

                    this.functionList.push(obj);
                });
            });
        },

        getImageUrl(index) {
            if (isNaN(index)) {
                return;
            }

            switch (index) {
                case 0:
                    // return functionImg01;
                    return functionImg02;
                case 1:
                    return functionImg02;
                case 2:
                    return functionImg03;
                case 3:
                    // return functionImg04;
                    return functionImg01;
                default:
                    return functionImg04;
            }
        },
    },
};
</script>

<style></style>
