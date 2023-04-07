<template>
    <div class="inner-wrapper">
        <!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--            <the-loading />-->
        <!--        </div>-->
        <!-- inner-container sub -->
        <div class="inner-container sub">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div id="content-header" class="content-header is-swiper">
                <!-- 제품 비주얼 스와이퍼 -->
                <the-product-visual />
            </div>
            <!-- content-body -->
            <div class="content-body" v-if="!$fetchState.pending">
                <!-- 메뉴 경로 -->
                <the-breadcrumb />
                <!-- sub-content -->
                <div class="sub-content is-contact-content">
                    <!-- 내용은 여기에. -->
                    <!-- inner-content -->
                    <div class="inner-content">
                        <div class="default-w">
                            <div class="tit-area column">
                                <div class="label-group">
                                    <span
                                        class="label label-material"
                                        :class="$store.state.menu.materialMap[$route.query.partOid].materialClass"
                                        v-text="$store.state.menu.materialMap[$route.query.partOid].materialName"
                                        >EMC</span
                                    >
                                    <span class="sub-txt xlg">{{ $t(`user_common_product`) }}</span>
                                </div>
                                <h3 class="tit lg" v-text="productInfo.name"></h3>
                            </div>
                            <div class="division-area product-view">
                                <div class="division-item">
                                    <div class="tit-area">
                                        <h4 class="tit md">{{ $t(`user_common_application`) }}</h4>
                                    </div>
                                    <div class="content-wrap sm" v-for="(item, itemIndex) in productInfo.applicationList" :key="itemIndex">
                                        <span v-text="getProductFullPath(item.fullPathNameVC)"></span>
                                    </div>
                                </div>
                                <div class="division-item">
                                    <div class="tit-area">
                                        <h4 class="tit md">{{ $t(`user_common_productClassification`) }}</h4>
                                    </div>
                                    <!--                                    <div class="content-wrap sm">-->
                                    <div class="content-wrap sm" v-if="$common.isNotEmpty(productInfo.productClassificationInfo)">
                                        <span v-text="getProductFullPath(productInfo.productClassificationInfo.fullPathNameVC)"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- inner-content -->
                    <div class="inner-content">
                        <el-collapse v-model="productViewCollpaseActive" class="el-collapse-normal default-w">
                            <!-- Markets -->
                            <el-collapse-item name="1">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_common_market`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap sm">
                                    <ul class="square-dot-list row">
                                        <li v-for="(item, index) in productInfo.marketList" :key="index">
                                            <nuxt-link
                                                :to="
                                                    localePath(
                                                        '/kccam/user/market/market/market_view?depth=2&classificationOid=' +
                                                            item.oid +
                                                            '&classification=market',
                                                    )
                                                "
                                                class="txt-link-underline main-color"
                                                v-text="item.name"
                                            ></nuxt-link>
                                        </li>
                                    </ul>
                                </div>
                            </el-collapse-item>
                            <!-- Function -->
                            <el-collapse-item name="2" v-if="$common.isNotEmpty(productInfo.functionList)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_common_function`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap sm">
                                    <ul class="square-dot-list first-li-special">
                                        <li v-for="(item, index) in productInfo.functionList" :key="index">
                                            <p class="txt" v-text="item.name"></p>
                                        </li>
                                    </ul>
                                </div>
                            </el-collapse-item>
                            <!-- Description -->
                            <el-collapse-item name="3" v-if="$common.isNotEmpty(productInfo.descr)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_product_Description`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap sm">
                                    <ul class="square-dot-list variable-context">
                                        <li>
                                            <p class="txt" v-text="productInfo.descr"></p>
                                        </li>
                                    </ul>
                                </div>
                            </el-collapse-item>
                            <!-- Related Document -->
                            <el-collapse-item name="4" v-if="false">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_market_relatedDocu_tit`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <div class="table-tit">
                                        <!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
                                    </div>
                                    <div class="table-wrap">
                                        <div class="inner-body">
                                            <!--
											table 태그 공통 클래스 : table
											스타일 구분 클래스 일반(th 검정) : table-normal
											스타일 구분 클래스 th 회색 : table-gray
											-->
                                            <table class="table table-normal thead-lg">
                                                <!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
                                                <caption>
                                                    관련 문서 표
                                                </caption>
                                                <!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
                                                <colgroup>
                                                    <col style="width:" />
                                                    <col style="width: 15%" />
                                                    <col style="width: 12%" />
                                                    <col style="width: 12%" />
                                                    <col style="width: 12%" />
                                                </colgroup>
                                                <thead>
                                                    <tr>
                                                        <!-- th에만 해당 scope="" col(열) / row(행) -->
                                                        <th scope="col">
                                                            <span>{{ $t(`user_market_relatedDocu_docuName`) }}</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span>{{ $t(`user_market_relatedDocu_docuType`) }}</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span>{{ $t(`user_market_relatedDocu_fileType`) }}</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span>{{ $t(`user_market_relatedDocu_download`) }}</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span>{{ $t(`user_market_relatedDocu_share`) }}</span>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <!-- no-data -->
                                                    <tr v-if="false">
                                                        <!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
                                                        <td colspan="5">
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
                                                        </td>
                                                    </tr>
                                                    <!-- list-item -->
                                                    <tr>
                                                        <td>
                                                            <span>{{ $t(`user_product_introduction_tit`) }}</span>
                                                        </td>
                                                        <td>
                                                            <span>{{ $t(`user_product_introduction`) }}</span>
                                                        </td>
                                                        <td>
                                                            <span>Psd</span>
                                                        </td>
                                                        <td>
                                                            <el-button type="icon-only">
                                                                <span class="material-icons">arrow_circle_down</span>
                                                            </el-button>
                                                        </td>
                                                        <td>
                                                            <el-button type="icon-only">
                                                                <span class="material-icons">{{ $t(`user_market_relatedDocu_share`) }}</span>
                                                            </el-button>
                                                        </td>
                                                    </tr>
                                                    <!-- list-item -->
                                                    <tr>
                                                        <td>
                                                            <span>{{ $t(`user_product_introduction_tit`) }}</span>
                                                        </td>
                                                        <td>
                                                            <span>{{ $t(`user_product_introduction`) }}</span>
                                                        </td>
                                                        <td>
                                                            <span>Psd</span>
                                                        </td>
                                                        <td>
                                                            <el-button type="icon-only">
                                                                <span class="material-icons">arrow_circle_down</span>
                                                            </el-button>
                                                        </td>
                                                        <td>
                                                            <el-button type="icon-only">
                                                                <span class="material-icons">{{ $t(`user_market_relatedDocu_share`) }}</span>
                                                            </el-button>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </el-collapse-item>
                            <!-- Technical Datasheet -->
                            <el-collapse-item name="5" v-if="$common.isNotEmpty(productInfo.datasheetInfo)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_common_product_technicalDataSheet`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <div class="table-tit">
                                        <!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
                                    </div>
                                    <div class="table-wrap">
                                        <div class="inner-body">
                                            <!-- Properties -->
                                            <table class="table table-gray no-scroll">
                                                <!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
                                                <caption>
                                                    Technical Datasheet Properties 표
                                                </caption>
                                                <!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
                                                <colgroup v-if="isTypicalCol">
                                                    <col style="width: 28%" />
                                                    <col style="width: 18%" />
                                                    <col style="width: 18%" />
                                                    <col style="width: 18%" />
                                                    <col style="width: 18%" />
                                                </colgroup>
                                                <colgroup v-else>
                                                    <col style="width: 50%" />
                                                    <col style="width: 25%" />
                                                    <col style="width: 25%" />
                                                </colgroup>
                                                <thead>
                                                    <tr>
                                                        <th
                                                            scope="col"
                                                            :colspan="isTypicalCol ? 5 : 3"
                                                            class="bg-lblue"
                                                            v-text="isEmptyDatasheet ? '-' : productInfo.datasheetInfo.title"
                                                        >
                                                            Title
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <!-- no-data -->
                                                    <tr v-if="isEmptyDatasheet">
                                                        <!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
                                                        <td :colspan="isTypicalCol ? 5 : 3">
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
                                                        </td>
                                                    </tr>
                                                    <!-- list-item -->
                                                    <tr>
                                                        <th scope="row">
                                                            <span>{{ $t(`user_product_TestItem`) }}</span>
                                                        </th>
                                                        <th scope="row">
                                                            <span>{{ $t(`user_product_Unit`) }}</span>
                                                        </th>
                                                        <th scope="row" v-if="isTypicalCol">
                                                            <span>{{ $t(`user_product_Typical`) }}</span>
                                                        </th>
                                                        <th scope="row" v-if="isTypicalCol">
                                                            <span>{{ $t(`user_product_TestMethod`) }}</span>
                                                        </th>
                                                        <th scope="row">
                                                            <span>{{ $t(`user_product_Value`) }}</span>
                                                        </th>
                                                    </tr>
                                                    <!-- list-item -->
                                                    <tr v-for="datasheetItem in productInfo.datasheetInfo.datasheetItemList">
                                                        <th scope="row">
                                                            <span v-text="datasheetItem.name">Electrical conductivity </span>
                                                        </th>
                                                        <td>
                                                            <span v-text="datasheetItem.itemUnit">μS/cm</span>
                                                        </td>
                                                        <td v-if="isTypicalCol">
                                                            <span v-text="datasheetItem.typical">28</span>
                                                        </td>
                                                        <td v-if="isTypicalCol">
                                                            <span v-text="datasheetItem.testMethod">Thermal conductivity</span>
                                                        </td>
                                                        <td>
                                                            <span v-text="datasheetItem.itemValue">1.7</span>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </el-collapse-item>
                            <!-- 관련 문서 -->
                            <el-collapse-item name="6" v-if="$common.isNotEmpty(productInfo.docList)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_market_relatedDocu_tit`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <div class="table-wrap">
                                        <div class="inner-head">
                                            <!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
                                        </div>
                                        <div class="inner-body">
                                            <!--
											table 태그 공통 클래스 : table
											스타일 구분 클래스 일반(th 검정) : table-normal
											스타일 구분 클래스 th 회색 : table-gray
											-->
                                            <table class="table table-normal">
                                                <!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
                                                <caption>
                                                    유사 제품 목록
                                                </caption>
                                                <!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
                                                <colgroup>
                                                    <col style="width: 40%" />
                                                    <col style="width: 20%" />
                                                    <col style="width: 13%" />
                                                    <col style="width: 10%" />
                                                    <col style="width: 10%" />
                                                </colgroup>
                                                <thead>
                                                    <tr>
                                                        <!-- th에만 해당 scope="" col(열) / row(행) -->
                                                        <th scope="col">
                                                            <span>{{ $t(`user_market_relatedDocu_docuName`) }}</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span>{{ $t(`user_market_relatedDocu_docuType`) }}</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span>{{ $t(`user_market_relatedDocu_fileType`) }}</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span>{{ $t(`user_market_relatedDocu_download`) }}</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span>{{ $t(`user_market_relatedDocu_share`) }}</span>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <!-- no-data -->
                                                    <tr v-if="productInfo.docList.length === 0">
                                                        <!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
                                                        <td colspan="5">
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
                                                        </td>
                                                    </tr>
                                                    <!-- list-item -->
                                                    <tr v-else v-for="doc in productInfo.docList">
                                                        <td>
                                                            <span v-text="doc.title">1</span>
                                                        </td>
                                                        <td>
                                                            <span v-text="$amConstant.SFA_DOC_KIND[doc.docType]"></span>
                                                        </td>
                                                        <td>
                                                            <span v-text="getFileType(doc)">5900GB</span>
                                                        </td>
                                                        <td>
                                                            <a @click="setDownloadDialog(doc)" class="txt-link-underline">
                                                                {{ $t(`user_market_relatedDocu_download`) }}
                                                            </a>
                                                        </td>
                                                        <td>
                                                            <el-button type="icon-only" @click="shareDialogVisible = true">
                                                                <span class="material-icons" @click="setShareDialog(doc)">share</span>
                                                            </el-button>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </el-collapse-item>
                            <el-collapse-item name="7" v-if="$common.isNotEmpty(productInfo.addContentsList)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_market_references`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <!-- 설명 이미지 -->
                                    <the-description-plot :add-contents-list="productInfo.addContentsList" />
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                    </div>
                    <!-- Contact -->
                    <the-contact :branch-list="productInfo.branchList" :manager-list="productInfo.branchManagerList" />
                </div>
            </div>
        </div>
        <!-- 공유 모달 :: TheShareModal.vue -->
        <the-share-modal :shareDialogVisible="shareDialogVisible" :share-doc-info="shareDocInfo" @close="shareDialogVisible = false">
        </the-share-modal>

        <!-- 다운로드 모달 :: TheShareModal.vue	-->
        <the-download-modal
            :download-dialog-visible="downloadDialogVisible"
            :download-doc-info="downloadDocInfo"
            @close="downloadDialogVisible = false"
        ></the-download-modal>
    </div>
</template>
<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import theBreadcrumb from "~/components/kccam/user/breadcrumb/TheBreadcrumb.vue";
import theProductVisual from "~/components/kccam/user/product/TheProductVisual.vue";
import theContact from "~/components/kccam/user/contact/TheContact.vue";
import theDescriptionPlot from "~/components/common/TheDescriptionPlot.vue";
import theDownloadModal from "~/components/kccam/user/modal/TheDownloadModal.vue";
import theShareModal from "~/components/kccam/user/modal/TheShareModal.vue";
import Cookie from "js-cookie";

export default {
    head: {
        title: "KCC AM - Product 상세",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Product 상세 페이지입니다.",
            },
                        { property: "og:title", content: "KCC AM - Product 상세" },
            { name: "twitter:title", content: "KCC AM - Product 상세" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        theProductVisual,
        theContact,
        theDescriptionPlot,

        theShareModal,
    },
    data() {
        return {
            // collapse default active
            productViewCollpaseActive: ["1", "2", "3", "4", "5", "6"],

            // market
            marketList: [
                {
                    market: "Automotive > EMC",
                    to: "",
                },
                {
                    market: "Mobile > EMC",
                    to: "",
                },
            ],

            // function
            functionList: [
                {
                    function: "제품의 대표 특징이 들어갑니다.",
                },
                {
                    function: "제품의 특징이 들어갑니다.1",
                },
                {
                    function: "제품의 특징이 들어갑니다.2",
                },
            ],

            // description
            descriptionList: [
                {
                    description: "제품의 설명이 들어갑니다.1",
                },
                {
                    description: "제품의 설명이 들어갑니다.2",
                },
            ],

            productInfo: {},
            isTypicalCol: false,
            isEmptyDatasheet: false,

            // 공유 모달
            shareDialogVisible: false,
            shareDocInfo: {},
            // 다운 모달
            downloadDialogVisible: false,
            downloadDocInfo: {},
        };
    },
    watch: {
        $route() {
            this.$fetch();
        },
    },
    async fetch() {
        if (this.$common.isEmpty(this.$route.query.productOid)) {
            return;
        }

        this.productInfo = await this.getProductInfo();
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    methods: {
        async getProductInfo() {
            const _self = this;
            const getClassificationInfo = this.$urlConstant.API_URL_PREFIX.PRODUCT + this.$urlConstant.API_URL_SUFFIX.PRODUCT.GET;

            let lang = this.setLang();

            let reqParam = {
                oid: this.$route.query.productOid,
                lang: lang,
            };

            return await this.$axios.post(getClassificationInfo, reqParam).then(res => {
                if (200 === res.status && this.$common.isNotEmpty(res.data)) {
                    if (_self.$common.isNotEmpty(res.data.datasheetInfo)) {
                        _self.checkTypicalDatasheet(res.data.datasheetInfo);
                    }
                    this.addViewCount(res.data);
                    this.setMainFuctionList(res.data);
                    // console.log( res.data );
                    return res.data;
                }
            });
        },

        // Cookie lang 전달
        setLang() {
            let lang = Cookie.get(this.$amConstant.AM_I18N_COOKIE_KEY);
            switch (lang) {
                case "ko":
                    lang = "KO";
                    break;
                case "en":
                    lang = "EN";
                    break;
                case "cn":
                    lang = "CN";
                    break;
            }
            return lang;
        },

        // 메인 function 정렬
        async setMainFuctionList(productInfo) {
            if (
                this.$common.isEmpty(productInfo) ||
                this.$common.isEmpty(productInfo.mainFuncInfo) ||
                this.$common.isEmpty(productInfo.functionList)
            ) {
                return;
            }

            let index = await productInfo.functionList.findIndex((func, index) => {
                return func.oid === productInfo.mainFuncInfo.oid;
            });

            let mainFunc = productInfo.functionList.splice(index, 1);

            productInfo.functionList.unshift(mainFunc[0]);
        },

        /**
         * 제품 조회수를 증가시킵니다.
         */
        addViewCount(info) {
            const url = this.$urlConstant.API_URL_PREFIX.PRODUCT + this.$urlConstant.API_URL_SUFFIX.PRODUCT.ADD_VIEW_CNT;

            this.$axios.post(url, info).then(res => {
                // console.log(res);
            });
        },

        /**
         * 각 분류정보의 RootCategory를 제외한 FullPath를 보여줍니다.
         */
        getProductFullPath(fullPath) {
            if (this.$common.isEmpty(fullPath)) {
                return;
            }

            return fullPath.toString().substring(fullPath.indexOf(">") + 1);
        },

        /**
         * Typical, 시험항목 데이터를 가지고 있는 데이터시트인지 체크합니다.
         * @param datasheetInfo
         */
        checkTypicalDatasheet(datasheetInfo) {
            if (this.$common.isEmpty(datasheetInfo) || this.$common.isEmpty(datasheetInfo.datasheetItemList)) {
                this.isEmptyDatasheet = true;
                return;
            }

            const _self = this;
            _.each(datasheetInfo.datasheetItemList, function(datasheetItem) {
                if (!_self.$common.isEmpty(datasheetItem.typical) || !_self.$common.isEmpty(datasheetItem.testMethod)) {
                    _self.isTypicalCol = true;
                }
            });
        },

        /**
         * 문서 다운로드 URL을 설정합니다.
         */
        docFileDown(currentVersion) {
            if (this.$common.isEmpty(currentVersion.docFileInfo)) {
                location = currentVersion.outLinkUrl;
            }
            location = "/storage/storageFile_fileDown/" + currentVersion.docFileInfo.fileName + "/" + currentVersion.docFileInfo.storageFileUid;
        },
        /**
         * 파일 형식을 변환하여 표시해줍니다.
         * @param doc
         */
        getFileType(doc) {
            if (this.$common.isNotEmpty(doc.currentDocVersionInfo.docFileInfo)) {
                // 내부 파일인 경우
                return doc.currentDocVersionInfo.docFileInfo.fileExt.toUpperCase();
            }
            if (this.$common.isEmpty(doc.currentDocVersionInfo.docFileInfo)) {
                // 외부 파일인 경우
                return this.$t(`filetype_externalFile`);
            }
        },
        //공유 정보를 설정합니다.
        setShareDialog(doc) {
            if (this.$common.isEmpty(doc)) {
                return;
            }
            this.shareDocInfo = doc;
            this.shareDialogVisible = true;
        },

        //다운로드 정보를 설정합니다.
        setDownloadDialog(doc) {
            if (this.$common.isEmpty(doc)) {
                return;
            }
            this.downloadDocInfo = doc;
            this.downloadDialogVisible = true;
        },
    },
};
</script>
<style></style>
