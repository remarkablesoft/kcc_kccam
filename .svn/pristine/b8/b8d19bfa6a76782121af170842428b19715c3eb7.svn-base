<template>
    <div class="inner-wrapper">
        <!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--            <the-loading />-->
        <!--        </div>-->

        <!-- inner-container sub -->
        <div class="inner-container sub">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header intro">
                <div class="inner">
                    <div class="tit-area">
                        <h2 class="tit">{{ $t(`user_intro_introAM_header_tit`) }}</h2>
                        <!--                        <p class="sub-txt">{{ $t(`user_intro_introAM_header_subtxt`) }}</p>-->
                    </div>
                </div>
            </div>

            <!-- content-body -->
            <div class="content-body">
                <!-- 메뉴 경로 -->
                <the-breadcrumb />

                <!-- sub-content -->
                <div class="sub-content material">
                    <div class="default-w">
                        <!-- inner-content -->
                        <div class="inner-content tc">
                            <h3 class="tit main-tit">
                                {{ $t(`user_intro_introAM_body_tit`) }}
                            </h3>
                            <p class="descr" v-html="$t(`user_intro_introAM_body_subtxt1`)"></p>
                        </div>

                        <div class="inner-content-group box-gray">
                            <div class="box-white">
                                <!-- inner-content -->
                                <div class="inner-content">
                                    <div class="tit-area center">
                                        <h3 class="tit">{{ $t(`user_common_market`) }}</h3>
                                        <!--                                <span class="sub-txt">소재</span>-->
                                    </div>
                                    <div class="content-area">
                                        <ul class="material-img-list">
                                            <li class="list-item" v-for="(item, index) in industyList" :key="index">
                                                <div class="img-box">
                                                    <img
                                                        :src="'/storage/storageFile_fileView/' + item.storageFileUid"
                                                        alt="market 관련 이미지"
                                                        class="img"
                                                        @click="goPage(item.linkUrl)"
                                                        @error="$common.imageError"
                                                    />
                                                </div>
                                                <div class="explain">
                                                    <span class="inner-tit" v-text="item.tit"></span>
                                                    <div class="inner-txt">
                                                        <span v-text="item.material01"></span>
                                                        <span v-text="item.material02"></span>
                                                        <span v-text="item.material03"></span>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <!-- inner-content -->
                            <div class="inner-content">
                                <h3 class="tit is-bar">
                                    {{ $t(`user_intro_introAM_body_content1_tit`) }}
                                </h3>
                                <p class="descr" v-html="$t(`user_intro_introAM_body_content1_subtxt`)"></p>
                            </div>

                            <!-- inner-content -->
                            <div class="inner-content">
                                <h3 class="tit is-bar">
                                    {{ $t(`user_intro_introAM_body_content2_tit`) }}
                                </h3>
                                <p class="descr" v-html="$t(`user_intro_introAM_body_content2_subtxt`)"></p>
                            </div>
                            <!-- inner-content -->
                            <div class="inner-content">
                                <h3 class="tit is-bar">
                                    {{ $t(`user_intro_introAM_body_content3_tit`) }}
                                </h3>
                                <p class="descr" v-html="$t(`user_intro_introAM_body_content3_subtxt`)"></p>
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

// 적용산업 소재 이미지
import materialImg00 from "@/assets/images/contents/intro/contents_intro_am_00.png";
import materialImg01 from "@/assets/images/contents/intro/contents_intro_am_01.png";
import materialImg02 from "@/assets/images/contents/intro/contents_intro_am_02.png";
import materialImg03 from "@/assets/images/contents/intro/contents_intro_am_03.png";
import materialImg04 from "@/assets/images/contents/intro/contents_intro_am_04.png";
import materialImg05 from "@/assets/images/contents/intro/contents_intro_am_05.png";
import materialImg06 from "@/assets/images/contents/intro/contents_intro_am_06.png";
import materialImg07 from "@/assets/images/contents/intro/contents_intro_am_07.png";
import materialImg08 from "@/assets/images/contents/intro/contents_intro_am_08.png";
import materialImg09 from "@/assets/images/contents/intro/contents_intro_am_09.png";

export default {
    head: {
        title: "KCC AM - KCC Advanced Materials 소개",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "KCC Advanced Materials 소개 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - KCC Advanced Materials 소개" },
            { name: "twitter:title", content: "KCC AM - KCC Advanced Materials 소개" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
    },
    data() {
        return {
            industyList: [],
        };
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },

    mounted() {
        this.categoryListData(this.$amConstant.ROOT_CATEGORY_OID.MARKET, this.$amConstant.CATEGORY_TYPE.MARKET);
    },

    methods: {
        categoryListData(parentOid, categoryType) {
            let param = {
                parentOid: parentOid,
                categoryType: categoryType,
                fillIconFile: true,
            };

            const url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST_ALL;
            this.$axios.post(url, param).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }

                let _self = this;

                res.data.forEach((item, index) => {
                    let data = {
                        tit: item.name,
                        linkUrl: "/kccam/user/market/market/market_view?depth=2&classificationOid=" + item.oid + "&classification=market",
                    };

                    if (_self.$common.isNotEmpty(item.iconFile) && _self.$common.isNotEmpty(item.iconFile.storageFileUid)) {
                        data.storageFileUid = item.iconFile.storageFileUid;
                    }
                    this.industyList.push(data);
                });
            });
        },
        goPage(url) {
            this.$router.push(this.localePath(url));
        },
    },
};
</script>

<style></style>
