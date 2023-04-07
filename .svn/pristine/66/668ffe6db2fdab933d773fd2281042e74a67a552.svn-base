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
                        <h2 class="tit">{{ $t(`user_intro_newsroom_header_tit`) }}</h2>
                        <!--                        <p class="sub-txt">{{ $t(`user_intro_newsroom_header_subtxt`) }}</p>-->
                    </div>
                </div>
            </div>

            <!-- content-body -->
            <div class="content-body">
                <!-- 메뉴 경로 -->
                <the-breadcrumb />

                <!-- sub-content -->
                <div class="sub-content newsroom">
                    <!-- inner-content -->
                    <div class="inner-content">
                        <div class="default-w">
                            <div class="tit-area is-border">
                                <h3 class="tit">{{ $t(`user_intro_newsroom_header_tit`) }}</h3>
                            </div>

                            <!-- no-data(loading) -->
                            <div class="board-view" v-if="$common.isEmpty(newsInfo)">
                                <li class="no-data" v-if="$fetchState.pending">
                                    <div class="loading-sm">
                                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                    </div>
                                    <p>{{ $t(`user_common_nowLoading`) }}</p>
                                </li>
                                <!-- no-data -->
                                <li class="no-data" v-if="true !== $fetchState.pending">
                                    <span class="material-icons">error_outline</span>
                                    <span>{{ $t(`user_common_noData`) }}</span>
                                </li>
                                <!-- <hr> -->
                                <div class="view-body" style="border-top: 1px solid #ccc">
                                    <div class="btn-area">
                                        <el-button type="primary" size="medium" class="md" @click="goList()">{{
                                            $t(`user_intro_newsroom_view_list`)
                                        }}</el-button>
                                    </div>
                                </div>
                            </div>

                            <div class="board-view" v-else>
                                <div class="view-header">
                                    <div class="tit-area">
                                        <span class="tit" v-text="newsInfo.title"></span>
                                    </div>
                                    <div class="sub-info-area">
                                        <div class="info-item">
                                            <span class="item-label">{{ $t(`user_intro_newsroom_view_relatedProduct`) }}</span>
                                            <span class="data" v-text="setProductText(newsInfo)"></span>
                                        </div>
                                        <div class="sub-info-group">
                                            <div class="info-item">
                                                <span class="item-label">{{ $t(`user_intro_newsroom_view_regDate`) }}</span>
                                                <span class="data" v-text="$common.formatDate(newsInfo.inputDate)"></span>
                                            </div>
                                            <div class="info-item">
                                                <span class="item-label item-label-small">{{ $t(`user_intro_newsroom_view_hits`) }}</span>
                                                <span class="data" v-text="newsInfo.viewCnt"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="view-body">
                                    <div class="cont-area">
                                        <div class="common-pre-wrap">
                                            <span class="data" v-html="newsInfo.newsroomContents"></span>
                                        </div>
                                    </div>
                                    <div class="list-area">
                                        <ul class="list">
                                            <li
                                                v-if="$common.isEmpty(newsInfo.prevNewsInfo) && $common.isEmpty(newsInfo.nextNewsInfo)"
                                                class="item-group no-post"
                                            >
                                                <div class="item date">
                                                    <span>이전, 다음 글이 없습니다.</span>
                                                </div>
                                            </li>
                                            <li v-if="newsInfo.prevNewsInfo" class="item-group" @click="changeNewsInfo(newsInfo.prevNewsInfo)">
                                                <div class="item btn">
                                                    <el-button type="icon-only" size="small">
                                                        <span class="material-icons">keyboard_arrow_up</span>
                                                    </el-button>
                                                </div>
                                                <div class="item tit">
                                                    <span class="" v-text="newsInfo.prevNewsInfo.title"></span>
                                                </div>
                                                <div class="item date">
                                                    <span class="" v-text="$common.formatDate(newsInfo.prevNewsInfo.inputDate)"></span>
                                                </div>
                                            </li>
                                            <li v-if="newsInfo.nextNewsInfo" class="item-group" @click="changeNewsInfo(newsInfo.nextNewsInfo)">
                                                <div class="item btn">
                                                    <el-button type="icon-only" size="small">
                                                        <span class="material-icons">keyboard_arrow_down</span>
                                                    </el-button>
                                                </div>
                                                <div class="item tit">
                                                    <span class="" v-text="newsInfo.nextNewsInfo.title"></span>
                                                </div>
                                                <div class="item date">
                                                    <span class="" v-text="$common.formatDate(newsInfo.nextNewsInfo.inputDate)"></span>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="btn-area">
                                        <el-button type="primary" size="medium" class="md" @click="goList()">{{
                                            $t(`user_intro_newsroom_view_list`)
                                        }}</el-button>
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

export default {
    head: {
        title: "KCC AM - Newsroom 상세",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Newsroom 상세 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Newsroom 상세" },
            { name: "twitter:title", content: "KCC AM - Newsroom 상세" },
        ],
    },
    layout: "userLayout",
    data() {
        return {
            newsInfo: {},
        };
    },

    components: {
        theLoading,
        theBreadcrumb,
    },

    computed: {
        loadingIndicator() {
            return this.$root.$loading.percent;
        },
    },

    async fetch() {
        await this.getNewsInfo();
    },

    methods: {
        //이전 or 다음글 게시글 데이터로 현재 뉴스 정보를 불러옵니다.
        async changeNewsInfo(newsInfo) {
            const url_get = this.$urlConstant.API_URL_PREFIX.NEWSROOM + this.$urlConstant.API_URL_SUFFIX.NEWSROOM.VIEW_WITH_PREV_AND_NEXT;

            let cnd = {
                oid: newsInfo.oid,
            };

            if (this.$route.query.searchQuery) {
                cnd.searchQuery = this.$route.query.searchQuery;
                cnd.searchQueryType = this.$amConstant.SEARCH_QUERY_TYPE.TITLE;
            }

            await this.$axios.post(url_get, cnd).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.newsInfo = {};
                    return;
                }
                // console.log( res.data );
                this.newsInfo = res.data;
            });
        },

        // 목록 페이지로 이동
        async goList() {
            const url = this.$urlConstant.MENU_URL_PREFIX.USER_INTRO + this.$urlConstant.MENU_URL_SUFFIX.INTRO.NEWSROOM.LIST;
            let pushObj = {
                path: url,
                query: {},
            };
            if (this.$route.query.searchQuery) {
                //검색어 가진 경우
                pushObj.query.searchQuery = this.$route.query.searchQuery;
            }
            this.$router.push(this.localePath(pushObj));
        },

        // 게시글 데이터를 불러옵니다.
        async getNewsInfo() {
            const url_get = this.$urlConstant.API_URL_PREFIX.NEWSROOM + this.$urlConstant.API_URL_SUFFIX.NEWSROOM.VIEW_WITH_PREV_AND_NEXT;

            let cnd = {
                oid: this.$route.query.oid,
                fillProductList: true,
            };

            if (this.$route.query.searchQuery) {
                cnd.searchQuery = this.$route.query.searchQuery;
                cnd.searchQueryType = this.$amConstant.SEARCH_QUERY_TYPE.TITLE;
            }

            await this.$axios.post(url_get, cnd).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.newsInfo = {};
                    return;
                }

                this.newsInfo = res.data;
                console.log("res.data>>>>>", res.data);
                //console.log( "prevPosting", res.data.prevPosting );
            });
        },

        setProductText(newsInfo) {
            if (this.$common.isEmpty(newsInfo)) {
                return "-";
            }
            if (this.$common.isEmpty(newsInfo.productList)) {
                return "-";
            }

            let text = "";
            _.forEach(newsInfo.productList, product => {
                text += product.name + ", ";
            });

            return text.substring(0, text.length - 2);
        },
    },
};
</script>

<style></style>
