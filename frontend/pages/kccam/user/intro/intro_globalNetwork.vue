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
                        <h2 class="tit">{{ $t(`user_intro_globalNetwork_header_tit`) }}</h2>
                        <!--                        <p class="sub-txt">{{ $t(`user_intro_globalNetwork_header_subtxt`) }}</p>-->
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
                            <!-- 글로벌 네트워크 -->
                            <the-global-network ref="map" />
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
import theGlobalNetworkMap from "~/components/kccam/user/globalNetwork/TheGlobalNetwork.vue";
import Cookie from "js-cookie";

export default {
    head: {
        title: "KCC AM - Global Network",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Global Network 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Global Network" },
            { name: "twitter:title", content: "KCC AM - Global Network" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        theGlobalNetworkMap,
    },
    data() {
        return {
            cookieLan: "",
        };
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    async fetch() {
        // await this.setCookieLan();
    },

    methods: {},
};
</script>

<style></style>
