<template>
    <div class="inner-wrapper">
        <!--		<div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--			<the-loading />-->
        <!--		</div>-->
        <!-- inner-container sub -->
        <div class="inner-container sub">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header market">
                <div class="inner">
                    <div class="tit-area">
                        <h2 class="tit">{{ $t(`user_market_main_function_tit`) }}</h2>
                        <p class="sub-txt">{{ $t(`user_market_main_function_descr1`) }}</p>
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
                                    <h3 class="tit">{{ $t(`user_market_main_header_tit`) }}</h3>
                                </div>
                                <!-- market img list -->
                                <div class="market-img-list">
                                    <!-- no-data(loading) -->
                                    <div v-if="0 === marketList.length" class="w100">
                                        <div class="no-data" v-if="$fetchState.pending">
                                            <div class="loading-sm">
                                                <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                            </div>
                                            <p>{{ $t(`user_common_nowLoading`) }}</p>
                                        </div>
                                        <!-- no-data -->
                                        <div class="no-data" v-if="!$fetchState.pending">
                                            <span class="material-icons">error_outline</span>
                                            <span>{{ $t(`user_common_noData`) }}</span>
                                        </div>
                                    </div>
                                    <!-- list-item -->
                                    <div class="list-item" v-for="(item, index) in marketList" :key="index">
                                        <div class="tit" v-text="item.title">
                                            <!-- Semiconductor -->
                                        </div>
                                        <div class="img-detail-group" :class="item.active ? 'active' : ''">
                                            <!-- 해당하는 인덱스숫자를 넣어주시면 됩니다 -->
                                            <div class="img-box" @click="goRouter(0, item)">
                                                <img :src="setImgSrc(item)" alt="market 관련 이미지" class="img no-img" @error="$common.imageError" />
                                            </div>
                                            <!-- detail -->
                                            <div class="detail-box">
                                                <!-- btn -->
                                                <button type="button" class="btn-expand-detail" @click="item.active = !item.active">
                                                    <span class="material-icons">keyboard_arrow_up</span>
                                                </button>
                                                <!-- contents -->
                                                <div class="detail-content">
                                                    <!-- scroll area -->
                                                    <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                                        <!-- el1 -->
                                                        <div>
                                                            <!-- 수정된 퍼블리싱 소스 -->
                                                            <ul class="detail-list">
                                                                <li v-if="item.detailMarket.content != ''">
                                                                    <div class="label label-sm label-black" v-text="item.detailMarket.label"></div>
                                                                    <p class="txt" v-text="item.detailMarket.content"></p>
                                                                </li>
                                                                <li>
                                                                    <div class="label label-sm label-black" v-text="item.material.label"></div>
                                                                    <!-- p tag v-for -->
                                                                    <p
                                                                        class="txt"
                                                                        v-for="(material, materialIndex) in item.material.detailList"
                                                                        :key="materialIndex"
                                                                        v-text="material.content"
                                                                        @click="goMaterialPage(material)"
                                                                    ></p>
                                                                </li>
                                                            </ul>
                                                            <!-- el2 -->
                                                            <ul class="detail-list">
                                                                <li v-for="(detailItem, index) in item.marketDetailList" :key="index">
                                                                    <div class="label label-sm label-black" v-text="detailItem.label">
                                                                        <!-- Detail Market -->
                                                                    </div>
                                                                    <p class="txt" v-text="detailItem.contents">
                                                                        <!-- Mobile Semiconductor -->
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
import theSideNav from "~/components/kccam/user/sideNav/TheSideNav.vue";

// market 샘플 이미지
import marketImg01 from "@/assets/images/contents/market/contents_market_semiconductor.png"; // semiconductor
import nodataImg from "@/assets/images/contents/sample/contents_catalog_no_image.png";

export default {
    head: {
        title: "KCC AM - Market 목록",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Market 목록 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Market 목록" },
            { name: "twitter:title", content: "KCC AM - Market 목록" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        theSideNav,
    },
    data() {
        return {
            marketList: [],
            defaultImg: nodataImg,
        };
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },

    async fetch() {
        await this.categoryListData(this.$amConstant.ROOT_CATEGORY_OID.MARKET, this.$amConstant.CATEGORY_TYPE.MARKET);
    },
    methods: {
        setImgSrc(item) {
            if (this.$common.isEmpty(item) || this.$common.isEmpty(item.storageFileUid)) {
                return nodataImg;
            }

            return "/storage/storageFile_fileView/" + item.storageFileUid;
        },

        async categoryListData(parentOid, categoryType) {
            let param = {
                parentOid: parentOid,
                categoryType: categoryType,
                fillProduct: true,
                fillIconFile: true,
            };

            const url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST_ALL;

            let _self = this;

            await this.$axios.post(url, param).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }

                res.data.forEach((item, index) => {
                    var obj = {
                        oid: item.oid,
                        title: item.name,
                        // storageFileUid: item.iconFile.storageFileUid,
                        active: false, // slide active
                        //  linkUrl: "/kccam/user/market/market/market_view",

                        detailMarket: {
                            label: "Detail Market",
                            content: "",
                        },

                        material: {
                            label: "Material",
                            detailList: [],
                        },
                    };

                    if (this.$common.isNotEmpty(item.iconFile)) {
                        obj.storageFileUid = item.iconFile.storageFileUid;
                    }

                    item.relateMaterialList.forEach((material, materialIndex) => {
                        obj.material.detailList.push({
                            content: material.name,
                            urlLink:
                                _self.$urlConstant.MENU_URL_PREFIX.USER_PRODUCT +
                                _self.$urlConstant.MENU_URL_SUFFIX.PRODUCT.MATERIAL_VIEW +
                                "?depth=1&partOid=" +
                                material.oid,
                        });
                    });

                    this.marketList.push(obj);
                });
            });
        },

        goRouter(index, item) {
            item.itemActive = false;
            let pathName =
                this.$urlConstant.MENU_URL_PREFIX.USER_MARKET +
                this.$urlConstant.MENU_URL_SUFFIX.MARKET.MARKET_VIEW +
                "?depth=2&classificationOid=" +
                item.oid +
                "&classification=market";
            this.$router.push(this.localePath(pathName));
        },

        goMaterialPage(material) {
            if (this.$common.isEmpty(material) || this.$common.isEmpty(material.urlLink)) {
                return;
            }

            this.$router.push(this.localePath(material.urlLink));
        },
    },
};
</script>
<style></style>
