<template>
    <div class="inner-wrapper">
        <div class="loading-container" v-if="loadingIndicator > 0 || pageLoading">
            <the-loading />
        </div>
        <!-- inner-container sub -->
        <div class="inner-container sub">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header market">
                <div class="inner">
                    <div class="tit-area">
                        <h2 class="tit">{{ $t(`user_market_main_header_tit`) }}</h2>
                        <p class="sub-txt" v-html="$t(`user_market_view_header_subtxt`)"></p>
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
                                <div class="tit-area column">
                                    <span class="sub-txt xlg">{{ $t(`user_market_main_header_tit`) }}</span>
                                    <h3 class="tit lg" v-text="marketInfo.name"></h3>
                                </div>
                                <div class="img-descr-group">
                                    <!-- img -->
                                    <div class="img-box">
                                        <!-- 샘플 이미지 -->
                                        <img
                                            v-if="storageFileUid.length !== 0"
                                            :src="'/storage/storageFile_fileView/' + storageFileUid"
                                            alt="Market 관련 이미지"
                                            class="img"
                                            @error="$common.imageError"
                                        />
                                        <img v-else :src="nodataImg" alt="Market 관련 이미지" class="img" />
                                    </div>
                                    <!-- descr -->
                                    <div class="descr alt">
                                        <p class="txt" v-text="marketInfo.descr"></p>
                                    </div>
                                </div>
                            </div>
                            <!-- inner-content -->
                            <div class="inner-content">
                                <el-collapse v-model="marketViewCollapseActive" class="el-collapse-normal">
                                    <!-- Products Datasheet-->
                                    <el-collapse-item :name="index" v-for="(item, index) in filledProductList" :key="item.oid">
                                        <!-- 타이틀 -->
                                        <template slot="title">
                                            <h4 class="tit md" v-text="item.name + ' ' + $t(`user_common_product_productDataSheet`)"></h4>
                                        </template>
                                        <!-- 내용 -->
                                        <div class="content-wrap">
                                            <!-- data-sheet -->
                                            <the-data-sheet
                                                :classification="$route.query.classification"
                                                :classification-oid="$route.query.classificationOid"
                                                :product-list="item.productList"
                                                :list-name="item.name"
                                            />
                                        </div>
                                    </el-collapse-item>
                                    <!-- Related Document -->
                                    <el-collapse-item name="5" v-if="false">
                                        <!-- 타이틀 -->
                                        <template slot="title">
                                            <h4 class="tit md">
                                                {{ $t(`user_market_relatedDocu_tit`) }}
                                            </h4>
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
                                                                    <div class="no-data" v-if="$fetchState.pending">
                                                                        <div class="loading-sm">
                                                                            <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                                                        </div>
                                                                        <p>
                                                                            {{ $t(`user_common_nowLoading`) }}
                                                                        </p>
                                                                    </div>
                                                                    <!-- no-data -->
                                                                    <div
                                                                        class="no-data"
                                                                        v-if="partList.length === 0 && detailList === 0 && !$fetchState.pending"
                                                                    >
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
                                    <!-- References -->
                                    <el-collapse-item name="6" v-if="$common.isNotEmpty(addContentsList)">
                                        <!-- 타이틀 -->
                                        <template slot="title">
                                            <h4 class="tit md">{{ $t(`user_market_references`) }}</h4>
                                        </template>
                                        <!-- 내용 -->
                                        <div class="content-wrap">
                                            <the-description-plot :add-contents-list="addContentsList" />
                                            <!-- <div class="img-wrap"> -->
                                            <!-- 샘플 이미지 -->
                                            <!-- <img src="@/assets/images/contents/sample/contents_sample_reference2.png" alt="참고 이미지" /> -->
                                            <!-- </div> -->
                                        </div>
                                    </el-collapse-item>
                                </el-collapse>
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
import theDataSheet from "~/components/common/board/TheDataSheet.vue";
import theSideNav from "~/components/kccam/user/sideNav/TheSideNav.vue";
import nodataImg from "@/assets/images/contents/sample/contents_catalog_no_image.png";

export default {
    head: {
        title: "KCC AM - Market 상세",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Market 상세 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Market 상세" },
            { name: "twitter:title", content: "KCC AM - Market 상세" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        theDataSheet,
        theSideNav,
    },
    data() {
        return {
            // collapse default active
            marketViewCollapseActive: [],

            marketOid: "",
            storageFileUid: "",

            partList: [],
            detailList: [],
            addContentsList: [],

            //로딩 indicator
            pageLoading: false,

            marketInfo: {},
            relateMaterialList: [],
            nodataImg,

            allProductList: [],
            filledProductList: [],
        };
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },

    fetch() {
        this.dataInit();
    },
    watch: {
        $route() {
            this.dataInit();
        },
    },

    methods: {
        /**
         *  data sheet 데이터를 준비합니다.
         */
        async dataInit() {
            if (this.$common.isEmpty(this.$route.query.classificationOid)) {
                return;
            }
            this.marketViewCollapseActive = [];
            await this.getAllProductListInfo();
            await this.getMarketData(this.$route.query.classificationOid);
        },

        /**
         * 해당 화면의 Market 관련 제품 정보를 전부 가져옵니다.
         */
        async getAllProductListInfo() {
            this.pageLoading = true;

            const urlViewListAll = this.$urlConstant.API_URL_PREFIX.PRODUCT + this.$urlConstant.API_URL_SUFFIX.PRODUCT.VIEW_LIST_All;

            let cnd = {
                targetObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_MARKET,
                targetOid: this.$route.query.classificationOid,
                fillDatasheet: true,
            };
            await this.$axios.post(urlViewListAll, cnd).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }
                this.allProductList = res.data;
            });
        },

        // market 정보 가져오기
        async getMarketData(oid) {
            const urlGet = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.GET;

            let param = {
                oid: oid,
                fillIconFile: true,
                fillContents: true,
                fillMaterial: true,
                classificationObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_MARKET,
                fillRelateMaterial: true,
            };

            await this.$axios.post(urlGet, param).then(async res => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }
                this.marketInfo = res.data;
                this.relateMaterialList = res.data.relateMaterialList;
                this.marketOid = res.data.oid;
                this.storageFileUid = res.data.iconFile ? res.data.iconFile.storageFileUid : "";
                this.addContentsList = res.data.addContentsList;

                if (this.$common.isNotEmpty(this.relateMaterialList)) {
                    // productList 를 채워줍니다.
                    this.filledProductList = await this.fillProductInfo(this.relateMaterialList);
                    // 첫번째 DataSheet 를 펼쳐줍니다.
                    this.marketViewCollapseActive = [0];
                }
            });

            this.pageLoading = false;
        },
        /***
         * 전체 마켓 제품 정보에 해당 소재별 productList 를 채워줍니다.
         */
        fillProductInfo(list) {
            let _self = this;
            list.forEach((material, index) => {
                let tempArr = [];
                _self.allProductList.forEach(all => {
                    if (all.materialOid == material.oid) {
                        tempArr.push(all);
                    }
                });
                if (this.$common.isNotEmpty(tempArr)) {
                    material.productList = tempArr;
                }
            });
            // console.log( " 3 - 1  fillProductInfo : ", list );
            return list;
        },

        async getProductListInfo(material) {
            if (this.$common.isNotEmpty(material.productList)) {
                return;
            }

            if (this.$common.isEmpty(material.oid)) {
                return;
            }

            const urlViewListAll = this.$urlConstant.API_URL_PREFIX.PRODUCT + this.$urlConstant.API_URL_SUFFIX.PRODUCT.VIEW_LIST_All;

            let cnd = {
                targetObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_MARKET,
                targetOid: this.$route.query.classificationOid,
                materialOid: material.oid,
                fillDatasheet: true,
            };
            await this.$axios.post(urlViewListAll, cnd).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }
                this.relateMaterialList.forEach((relateMaterial, index) => {
                    if (relateMaterial.oid === material.oid) {
                        relateMaterial.productList = res.data;
                    }
                });
                this.$forceUpdate();
            });
        },
    },
};
</script>
<style></style>
