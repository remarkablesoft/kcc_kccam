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
                        <!--                        <p class="sub-txt" v-text="$t(`user_intro_newsroom_header_subtxt`)"></p>-->
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
                                <div class="search-box sub">
                                    <el-button v-on:click="getList()" type="icon-only" class="btn-search">
                                        <span class="icon custom-icon-search"></span>
                                    </el-button>
                                    <el-input
                                        :placeholder="$t(`user_intro_newsroom_search_placeholder`)"
                                        v-model="titSearchInput"
                                        clearable
                                        @keypress.enter.native="getList()"
                                    >
                                    </el-input>
                                </div>
                            </div>
                            <div class="list-area">
                                <ul class="list-group">
                                    <!-- no-data(loading) -->
                                    <div v-if="$common.isEmpty(newsList)">
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
                                    </div>

                                    <li class="list-item" v-for="(item, i) in newsList" :key="i" @click="goView(item)" v-else>
                                        <div class="img-box">
                                            <img
                                                v-if="isEmptyIconFile(item)"
                                                src="@/assets/images/contents/sample/contents_catalog_no_image.png"
                                                alt="목록 썸네일"
                                                class="no-data"
                                            />

                                            <img v-else :src="setImgSrc(item, i)" @error="imageError" alt="목록 썸네일" class="img" />
                                        </div>
                                        <div class="explain">
                                            <div class="tit" v-text="item.title"><span></span></div>
                                            <div class="label label-gray" v-if="item.labelVisible" v-text="item.label">
                                                <span></span>
                                            </div>
                                            <v-clamp :max-lines="5" autoresize class="txt">{{ item.newsroomContents }}</v-clamp>
                                            <div
                                                class="date"
                                                v-text="$common.isEmpty(item.inputDate) ? '' : $common.formatDate(item.inputDate)"
                                            ></div>
                                        </div>
                                    </li>
                                </ul>
                            </div>

                            <!-- 페이지네이션 -->
                            <thePagination
                                v-if="0 < listCount"
                                :visible-buttons-count="5"
                                :total-count="listCount"
                                :page-size="pageSize"
                                :current-page="currentPage"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theBreadcrumb from "~/components/kccam/user/breadcrumb/TheBreadcrumb.vue";

import vue from "vue";
import VClamp from "vue-clamp";
//noImg
import nodataImg from "~/assets/images/contents/sample/contents_catalog_no_image.png";

export default {
    head: {
        title: "KCC AM - Newsroom 목록",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Newsroom 목록 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Newsroom 목록" },
            { name: "twitter:title", content: "KCC AM - Newsroom 목록" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        thePagination,
        VClamp,
    },
    data() {
        return {
            // tit search
            titSearchInput: "",

            // news List
            newsList: [],

            // 페이지네이션
            startIndex: 1,
            listCount: 0,
            pageSize: 10,
            currentPage: 1,

            thisPage: 1,
            lastPage: 0, // 마지막 페이지
        };
    },
    watch: {
        $route() {
            this.$fetch();
        },
    },

    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },

    async fetch() {
        //검색어 가진 newsroom_view에서 넘어온 경우
        if (this.$route.query.searchQuery) {
            this.titSearchInput = this.$route.query.searchQuery;
        }
        await this.getList();
    },

    methods: {
        // 조회 페이지로 이동
        goView(item) {
            let pushObj = {
                path: this.$urlConstant.MENU_URL_PREFIX.USER_INTRO + this.$urlConstant.MENU_URL_SUFFIX.INTRO.NEWSROOM.VIEW,
                query: {
                    oid: item.oid,
                },
            };
            if (this.titSearchInput) {
                pushObj.query.searchQuery = this.titSearchInput;
            }
            this.$router.push(this.localePath(pushObj));
            //console.log( "뉴스 아이템", { newsItem } );
        },

        /* 뉴스 리스트를 불러옵니다. */
        async getList() {
            const url = this.$urlConstant.API_URL_PREFIX.NEWSROOM + this.$urlConstant.API_URL_SUFFIX.NEWSROOM.LIST;

            let startIndex = 1;
            if (this.$common.isNotEmpty(this.$route.query.page)) {
                if (this.currentPage === Number(this.$route.query.page)) {
                    return;
                }

                startIndex = this.getStartIndexFromPage(this.$route.query.page);
                this.currentPage = Number(this.$route.query.page);
            }

            let cnd = {
                startIndex: startIndex,
                pageSize: this.pageSize,
                orderByList: ["INPUT_DATE DESC"],
                fillIconFile: true,
                removeHtmlTag: true,
            };

            //뉴스 제목을 검색하는 경우
            if (this.titSearchInput) {
                cnd.searchQuery = this.titSearchInput;
                cnd.searchQueryType = this.$amConstant.SEARCH_QUERY_TYPE.TITLE;
            }
            // console.log( "cnd", cnd );
            await this.$axios.post(url, cnd).then(res => {
                if (res.data.totalCount === 0) {
                    this.newsList = [];
                    this.listCount = 0;
                    return;
                }
                // console.log("newsList",this.newsList);
                this.setNewsList(res.data.list);
                this.listCount = res.data.totalCount;
                this.lastPage = this.$common.getLastPage(this.listCount, this.pageSize);

                // console.log( res.data.list );
            });
        },

        setNewsList(newsList) {
            if (this.$common.isEmpty(newsList)) {
                return;
            }

            // contents에 들어있는 html를 없애줍니다.
            let objStrip = new RegExp();
            objStrip = /<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/gi;
            let _self = this;

            newsList.forEach(newsItem => {
                if (_self.$common.isNotEmpty(newsItem.newsroomContents)) {
                    let withoutTag = newsItem.newsroomContents.replace(objStrip, "");
                    newsItem.newsroomContents = withoutTag;
                }
            });

            this.newsList = newsList;
        },

        /**
         * 페이지 번호로 StartIndex를 가져옵니다.
         */
        getStartIndexFromPage(page) {
            let startIndex = (page - 1) * this.pageSize + 1;
            return startIndex;
        },

        setImgSrc(item) {
            return "/storage/storageFile_fileView/" + item.iconFile.storageFileUid;
        },

        isEmptyIconFile(newsInfo) {
            if (this.$common.isEmpty(newsInfo) || this.$common.isEmpty(newsInfo.iconFile)) {
                return true;
            }
            if (this.$common.isEmpty(newsInfo.iconFile.storageFileUid)) {
                return true;
            }
            return false;
        },

        // 이미지 에러 처리
        imageError(e) {
            e.target.src = require("~/assets/images/contents/sample/contents_catalog_no_image.png");
            e.target.className = "no-data";
        },
    },
};
</script>

<style></style>
