<template>
    <div class="inner-wrapper">
        <!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--            <the-loading />-->
        <!--        </div>-->
        <!-- inner-container sub -->
        <div class="inner-container sub">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header product-support">
                <div class="inner">
                    <div class="tit-area">
                        <h2 class="tit">{{ $t(`productSupport_productCatalog_tit`) }}</h2>
                        <!-- <p class="sub-txt">KCC소개 상세내용입니다.</p> -->
                    </div>
                </div>
            </div>
            <!-- content-body -->
            <div class="content-body">
                <!-- 메뉴 경로 -->
                <the-breadcrumb />
                <!-- sub-content -->
                <div class="sub-content catalog">
                    <!-- 내용은 여기에. -->
                    <!-- inner-content -->
                    <div class="inner-content">
                        <div class="default-w">
                            <div class="search-area right">
                                <div class="search-box sub">
                                    <el-button type="icon-only" class="btn-search">
                                        <span class="icon custom-icon-search"></span>
                                    </el-button>
                                    <el-input
                                        :placeholder="$t(`user_common_placeHolder_catalogName`)"
                                        v-model="searchTitle"
                                        clearable
                                        @keypress.enter.native="search()"
                                    ></el-input>
                                </div>
                            </div>
                            <div class="tit-area is-border" v-if="$common.isNotEmpty(searchedTitle)">
                                <p class="sub-txt alt sm">
                                    <strong>'{{ searchedTitle }}'</strong>
                                    {{ $t(`productSupport_productCatalog_resultTxt1`) }}
                                    <strong v-text="totalCount"></strong>
                                    {{ $t(`productSupport_productCatalog_resultTxt2`) }}
                                </p>
                            </div>
                            <div class="tit-area is-border" v-if="$common.isEmpty(searchedTitle) && searchFlag">
                                <p class="sub-txt alt sm">
                                    {{ $t(`productSupport_productCatalog_resultTxtAll`) }}
                                    <strong v-text="totalCount"></strong>
                                    {{ $t(`productSupport_productCatalog_resultTxt2`) }}
                                </p>
                            </div>
                            <div class="catalog-img-list">
                                <!-- no-data(loading) -->
                                <div class="no-data" v-if="0 < loadingIndicator && 1 > totalCount">
                                    <div class="loading-sm">
                                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                    </div>
                                    <p>{{ $t(`user_common_nowLoading`) }}</p>
                                </div>
                                <!-- no-data -->
                                <div class="no-data" v-if="0 === totalCount">
                                    <span class="material-icons">error_outline</span>
                                    <span>{{ $t(`user_common_noData`) }}</span>
                                </div>
                                <!-- list-item -->
                                <div class="list-item" v-for="(item, index) in catalogList" :key="item.oid">
                                    <div class="inner">
                                        <div class="img-box-wrap">
                                            <div class="img-box">
                                                <img :src="getImageUrl(item)" alt="카탈로그 관련 이미지" class="img" @error="$common.imageError" />
                                            </div>
                                            <!-- 다운로드 버튼 -->
                                            <el-button type="primary" size="circle" class="is-box-shadow" @click="fileDownload(item)">
                                                <span class="material-icons">download</span>
                                            </el-button>
                                        </div>
                                        <div class="tit" v-text="item.title"><!-- Power Module - Full Molding Catalogue.pdf --></div>
                                        <div class="date">
                                            <span class="label label-lgray label-sm">{{ $t(`productSupport_productCatalog_publishedDate`) }}</span>
                                            <span v-text="$common.formatDate(item.inputDate)"><!-- 2020.11.20 --></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- pagination -->
                            <the-pagination
                                v-if="0 < totalCount"
                                :current-page="currentPage"
                                :page-size="pageSize"
                                :total-count="totalCount"
                                :visible-buttons-count="5"
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
import theBreadcrumb from "~/components/kccam/user/breadcrumb/TheBreadcrumb.vue";
import thePagination from "~/components/common/ThePagination.vue";

export default {
    head: {
        title: "KCC AM - 제품 카탈로그",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "제품 카탈로그 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - 제품 카탈로그" },
            { name: "twitter:title", content: "KCC AM - 제품 카탈로그" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        thePagination,
    },
    data() {
        return {
            // 검색어 인풋
            searchTitle: "",
            searchedTitle: "",

            // 카탈로그 목록
            catalogList: [],

            lastPage: 1,
            currentPage: 1,
            pageSize: 12,
            thisPage: 1,
            totalCount: 0,

            searchFlag: false, // ''검색으로 인한 전체 검색인지 표시용
        };
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    watch: {
        $route() {
            if (this.$route.query.page) {
                this.onChangePage(parseInt(this.$route.query.page));
            } else {
                this.onChangePage(1);
            }
        },
    },
    async fetch() {
        await this.getList(1);
    },
    methods: {
        onChangePage(page) {
            this.currentPage = page;
            this.thisPage = (parseInt(this.currentPage) - 1) * parseInt(this.pageSize) + 1;
            this.getList(this.thisPage);
        },

        async getList(startIndex) {
            this.searchedTitle = this.searchTitle;

            let param = {
                startIndex: startIndex,
                pageSize: this.pageSize,
                docType: this.$amConstant.SFA_DOC_KIND_KEY.CATALOG,
                title: this.searchTitle,
                fillFile: true,
            };

            const url = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.LIST;

            await this.$axios.post(url, param).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.totalCount = 0;
                    return;
                }

                this.catalogList = res.data.list;
                this.totalCount = res.data.totalCount;
                this.lastPage = this.$common.getLastPage(this.totalCount, this.pageSize);

                this.catalogList.forEach(catalog => {
                    catalog.imageUrl = this.getImageUrl(catalog);
                });
            });
        },

        search() {
            this.searchFlag = true;
            this.getList(1);
        },

        getImageUrl(item) {
            if (
                this.$common.isEmpty(item) ||
                this.$common.isEmpty(item.currentDocVersionInfo) ||
                this.$common.isEmpty(item.currentDocVersionInfo.iconFileInfo)
            ) {
                return "";
            }

            let fileInfo = item.currentDocVersionInfo.iconFileInfo;

            return "/thumbnail_image/" + fileInfo.storageFileUid + "/00/" + true;
        },

        /* 다운로드 버튼 클릭시 */
        fileDownload(item) {
            if (this.$common.isEmpty(item.currentDocVersionInfo)) {
                return;
            }

            if (this.$common.isNotEmpty(item.currentDocVersionInfo.docFileInfo)) {
                let file = item.currentDocVersionInfo.docFileInfo;

                location = "/storage/storageFile_fileDown/" + file.fileName + "/" + file.storageFileUid;
            } else {
                // location = item.currentDocVersionInfo.outLinkUrl;

                this.downloadFile(item.currentDocVersionInfo.outLinkUrl, item.customField2);
            }
        },

        downloadFile(outLinkUrl, fileName) {
            this.$amCommon.downloadExternalLinkFile(outLinkUrl, fileName);
        },
    },
};
</script>
<style></style>
