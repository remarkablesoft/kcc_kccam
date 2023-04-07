<template>
    <div class="inner-wrapper">
        <!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--            <the-loading />-->
        <!--        </div>-->
        <!-- inner-container sub -->
        <div class="inner-container sub">
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
                            <div class="tit-area column">
                                <div class="label-group">
                                    <span
                                        class="label label-material"
                                        :class="$store.state.menu.materialMap[$route.query.partOid].materialClass"
                                        v-text="$store.state.menu.materialMap[$route.query.partOid].materialName"
                                    ></span>
                                    <span class="sub-txt xlg">{{ $t(`user_common_application`) }}</span>
                                </div>
                            </div>
                            <div class="img-descr-group">
                                <!-- img -->
                                <div class="img-box">
                                    <!-- 샘플 이미지 -->
                                    <img
                                        src="@/assets/images/contents/product/contents_product_adhesive.png"
                                        alt="application 관련 이미지"
                                        class="img"
                                    />
                                </div>
                                <!-- descr -->
                                <div class="descr">
                                    <h3 class="tit lg" v-text="productInfo.name"></h3>
                                    <ul class="square-dot-list variable-context">
                                        <li v-text="productInfo.descr"></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- inner-content -->
                    <div class="inner-content">
                        <el-collapse v-model="applicationViewCollpaseActive" class="el-collapse-normal default-w">
                            <!-- Detail Application -->
                            <el-collapse-item name="1" v-if="$common.isNotEmpty(productInfo.childClassificationList)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_common_detail_application`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <ul class="square-dot-list row">
                                        <li v-for="(item, index) in productInfo.childClassificationList" :key="index">
                                            <a class="txt-link-underline main-color" v-text="item.name" @click="goDetailAppPage(item)"></a>
                                        </li>
                                    </ul>
                                </div>
                            </el-collapse-item>
                            <!-- Product Datasheet -->
                            <el-collapse-item
                                v-if="$common.isNotEmpty(productInfo.relateMaterialList)"
                                v-for="(item, index) in productInfo.relateMaterialList"
                                :name="'dataSheet' + index"
                                :key="item.oid"
                                @click.native="getProductListInfo(item)"
                            >
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md" v-text="item.name + ' ' + $t(`user_common_product_productDataSheet`)"></h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <the-data-sheet
                                        :classification="$route.query.classification"
                                        :classification-oid="$route.query.classificationOid"
                                        :product-list="item.productList"
                                    />
                                </div>
                            </el-collapse-item>
                            <!-- References -->
                            <el-collapse-item name="3" v-if="$common.isNotEmpty(productInfo.addContentsList)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_market_references`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <the-description-plot :add-contents-list="productInfo.addContentsList" />
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
                                                    <col style="" />
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
                        </el-collapse>
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
import theDataSheet from "~/components/common/board/TheDataSheet.vue";

export default {
    head: {
        title: "KCC AM - Product Application 상세",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Product Application 상세 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Product Application 상세" },
            { name: "twitter:title", content: "KCC AM - Product Application 상세" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        theProductVisual,
        theDataSheet,
    },
    data() {
        return {
            // collapse default active
            applicationViewCollpaseActive: ["1", "2", "3", "4"],
            productInfo: {},

            relateMaterialList: [],
        };
    },
    async fetch() {
        if (this.$common.isEmpty(this.$route.query.classificationOid) || this.$common.isEmpty(this.$route.query.classification)) {
            return;
        }

        this.productInfo = await this.getProductInfo();
    },

    watch: {
        $route() {
            this.$fetch();
        },
    },
    computed: {
        loadingIndicator() {
            return this.$root.$loading.percent;
        },
    },
    methods: {
        async getProductInfo() {
            const urlGet = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.GET;

            let reqParam = {
                oid: this.$route.query.classificationOid,
                categoryType: this.$amConstant.CATEGORY_TYPE.APPLICATION,
                classificationObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_APPLICATION,
                fillChildClassification: true,
                fillRelateMaterial: true,
                fillDoc: true,
            };

            return await this.$axios.post(urlGet, reqParam).then(res => {
                if (200 === res.status && this.$common.isNotEmpty(res.data)) {
                    // console.log("res.data",res.data);

                    if (this.$common.isNotEmpty(res.data.relateMaterialList)) {
                        this.getProductListInfo(res.data.relateMaterialList[0]);
                        this.applicationViewCollpaseActive.push("dataSheet0");
                    }
                    return res.data;
                }
            });
        },

        goDetailAppPage(item) {
            this.$router.push(
                this.localePath({
                    path: this.$router.currentRoute.fullPath,
                    query: {
                        partOid: item.partOid,
                        classificationOid: item.oid,
                        classification: this.$route.query.classification,
                    },
                }),
            );
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
                targetObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_APPLICATION,
                targetOid: this.$route.query.classificationOid,
                materialOid: material.oid,
                fillDatasheet: true,
            };

            await this.$axios.post(urlViewListAll, cnd).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }

                this.productInfo.relateMaterialList.forEach(relateMaterial => {
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
