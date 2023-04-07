<template>
    <div class="inner-wrapper">
        <!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--            <the-loading />-->
        <!--        </div>-->
        <!-- inner-container sub -->
        <div class="inner-container sub mb0">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header is-swiper">
                <!-- 제품 비주얼 스와이퍼 -->
                <the-product-visual />
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
                                <div
                                    class="list-item"
                                    v-for="(item, itemIndex) in productList"
                                    :key="itemIndex"
                                    @mouseover="
                                        item.active = true;
                                        $forceUpdate();
                                    "
                                    @mouseleave="
                                        item.active = false;
                                        $forceUpdate();
                                    "
                                >
                                    <div class="inner">
                                        <!-- 1depth :: 목록 타이틀 버튼 -->
                                        <div class="tit-area" :class="item.active ? 'active' : ''">
                                            <span class="tit" v-text="item.subMenuName" @click="goMaterialView(item.subMenuUrl)"><!-- EMC --></span>
                                            <span
                                                class="material-icons"
                                                @click="
                                                    item.active = !item.active;
                                                    $forceUpdate();
                                                "
                                                >expand_more</span
                                            >
                                        </div>
                                        <!-- 제품 목록 -->
                                        <slide-up-down :active="item.active" :duration="300" class="inner-list-box">
                                            <!-- scroll area -->
                                            <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                                <!-- el1 -->
                                                <div>
                                                    <!-- el2 -->
                                                    <div
                                                        class="inner-list-item"
                                                        v-for="(innerItem, innerItemIndex) in item.detailMenuItem"
                                                        :key="innerItemIndex"
                                                    >
                                                        <!-- 2depth :: 목록 타이틀 버튼 -->
                                                        <div
                                                            class="tit-area"
                                                            @click="
                                                                innerItem.active = !innerItem.active;
                                                                $forceUpdate();
                                                            "
                                                            :class="innerItem.active ? 'active' : ''"
                                                        >
                                                            <span class="txt" v-text="innerItem.menuTitle"><!-- Application --></span>
                                                            <span class="material-icons">arrow_drop_down</span>
                                                        </div>
                                                        <!-- 상세 목록 -->
                                                        <slide-up-down :active="innerItem.active" :duration="300" class="detail-list-box">
                                                            <ul class="detail-list">
                                                                <!-- 상세 list-item -->
                                                                <li
                                                                    v-for="(detailItem, detailItemIndex) in innerItem.detailMenuList"
                                                                    :key="detailItemIndex"
                                                                >
                                                                    <nuxt-link
                                                                        :to="localePath(detailItem.detailMenuUrl)"
                                                                        class="txt-link"
                                                                        v-text="detailItem.detailMenuName"
                                                                    >
                                                                        <!-- Memory --></nuxt-link
                                                                    >
                                                                </li>
                                                            </ul>
                                                        </slide-up-down>
                                                    </div>
                                                </div>
                                            </div>
                                        </slide-up-down>
                                        <!-- img -->
                                        <div class="img-box">
                                            <img :src="getImageUrl(itemIndex, item)" @error="$common.imageError" alt="제품 관련 이미지" class="img" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- inner-content -->
                    <div class="inner-content">
                        <div class="icon-link-group">
                            <div class="inner default-w">
                                <nuxt-link :to="localePath('/kccam/user/market/market/market_list')" class="icon-link">
                                    <!-- <span class="material-icons">{{ $t(`user_common_store`) }}</span> -->
                                    <span class="material-icons">store</span>
                                    <span class="txt">
                                        <span>
                                            {{ $t(`user_common_View`) }} {{ $t(`user_common_product`) }}
                                            <strong>{{ $t(`user_common_By`) }} {{ $t(`user_common_market`) }}</strong>
                                        </span>
                                    </span>
                                </nuxt-link>
                                <nuxt-link :to="localePath('/kccam/user/market/function/function_list')" class="icon-link">
                                    <span class="material-icons">graphic_eq</span>
                                    <span class="txt">
                                        <span>
                                            {{ $t(`user_common_View`) }} {{ $t(`user_common_product`) }}
                                            <strong>{{ $t(`user_common_By`) }} {{ $t(`user_common_function`) }}</strong>
                                        </span>
                                    </span>
                                </nuxt-link>
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
import theProductVisual from "~/components/kccam/user/product/TheProductVisual.vue";

//noImg
import nodataImg from "@/assets/images/contents/sample/contents_catalog_no_image.png";

export default {
    head: {
        title: "KCC AM - Product 메인",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Product 메인 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Product 메인" },
            { name: "twitter:title", content: "KCC AM - Product 메인" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        theProductVisual,
    },
    data() {
        return {};
    },
    async fetch() {
        // await this.getProductList();
        // console.log("product", this.productList);
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },

        // ...mapGetters( {
        // 	productList : "menu/productMenu",
        // } ),
        productList() {
            let productList = this.$store.getters["menu/productMenu"];
            productList.forEach(product => {
                if (this.$common.isNotEmpty(product.detailMenuItem)) {
                    product.detailMenuItem.forEach((detailMenu, index) => {
                        if (0 === index) {
                            detailMenu.active = true;
                        }
                    });
                }
            });
            return productList;
        },
    },
    methods: {
        goMaterialView(url) {
            this.$router.push(this.localePath(url));
        },

        getImageUrl(index, item) {
            if (this.$common.isEmpty(item.mainImg) || this.$common.isEmpty(item.mainImg.storageFileUid)) {
                return nodataImg; //디폴트 이미지
            }

            return "/storage/storageFile_fileView/" + item.mainImg.storageFileUid;
        },
    },
};
</script>
<style></style>
