<template>
    <div class="inner-wrapper">
        <div class="loading-container" v-if="loadingIndicator > 0">
            <the-loading />
        </div>
        <!-- inner-container sub -->
        <div class="inner-container sub" v-if="!$fetchState.pending">
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
                                <!-- label-group 새로 추가. -->
                                <div class="label-group">
                                    <!--
                                        소재 버튼/라벨 색상 변경 관련
                                        버튼은 일반 el-button 클래스 가짐.
                                        일반표시는 label 클래스 가짐.
                                        ---------------------------------------------------
                                        label-material 클래스는 버튼/일반표시 동일하게 가짐.
                                        아래 소재에 따른 클래스 추가.
                                        ---------------------------------------------------
                                        EMC :: emc
                                        Adhesive :: adhesive
                                        Metalized Ceramics :: metalized-ceramics
                                        Ceramic Substrates :: ceramic-substrates
                                        Glass Fiber :: glass-fiber
                                    -->
                                    <span
                                        class="label label-material"
                                        :class="$store.state.menu.materialMap[$route.query.partOid].materialClass"
                                        v-text="$store.state.menu.materialMap[$route.query.partOid].materialName"
                                        >EMC</span
                                    >
                                    <span class="sub-txt xlg">{{ $t(`user_common_productClassification`) }}</span>
                                </div>
                                <h3 class="tit lg" v-text="classificationInfo.name"></h3>
                            </div>
                            <div class="descr" v-if="$common.isNotEmpty(classificationInfo.descr)">
                                <ul class="square-dot-list variable-context">
                                    <li v-html="classificationInfo.descr"></li>
                                </ul>
                                <!--																<ul class="square-dot-list">-->
                                <!--																	<li v-for="(item, index) in classificationDescrList" :key="index">-->
                                <!--																		<p class="txt" v-text="item.descr"></p>-->
                                <!--																	</li>-->
                                <!--																</ul>-->
                            </div>
                        </div>
                    </div>

                    <div class="inner-content">
                        <el-collapse v-model="applicationViewCollapseActive" class="el-collapse-normal default-w">
                            <!-- Related Application -->
                            <el-collapse-item name="1" v-if="$common.isNotEmpty(classificationInfo.childClassificationList)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_common_RelatedApplication`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <ul class="square-dot-list row">
                                        <li v-for="(item, index) in classificationInfo.childClassificationList" :key="index">
                                            <p class="txt" v-text="item.name"></p>
                                        </li>
                                    </ul>
                                </div>
                            </el-collapse-item>
                            <!-- Product Datasheet -->
                            <el-collapse-item
                                v-if="$common.isNotEmpty(classificationInfo.relateMaterialList)"
                                v-for="(item, index) in classificationInfo.relateMaterialList"
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
                                    <!-- data-sheet -->
                                    <the-data-sheet
                                        :classification="$route.query.classification"
                                        :classification-oid="$route.query.classificationOid"
                                        :product-list="item.productList"
                                    />
                                </div>
                            </el-collapse-item>
                            <!-- References v-if="false" -->
                            <el-collapse-item :name="3" v-if="$common.isNotEmpty(classificationInfo.addContentsList)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_market_references`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <!-- 설명 이미지 -->
                                    <the-description-plot :add-contents-list="classificationInfo.addContentsList" />
                                </div>
                            </el-collapse-item>
                            <!-- Related Document v-if="false" -->
                            <el-collapse-item name="4" v-if="false">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_market_relatedDocu_tit`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <!-- document -->
                                    <the-related-document />
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
import theDataSheet from "~/components/common/board/TheDataSheet.vue";
import theDescriptionPlot from "~/components/common/TheDescriptionPlot.vue";
import theRelatedDocument from "~/components/common/board/TheRelatedDocument.vue";

export default {
    head: {
        title: "KCC AM - Product Classification 상세",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Product Classification 상세 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Product Classification 상세" },
            { name: "twitter:title", content: "KCC AM - Product Classification 상세" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        theDataSheet,
        theDescriptionPlot,
        theRelatedDocument,
    },
    data() {
        return {
            // collapse default active
            applicationViewCollapseActive: [3],

            classificationInfo: {},
        };
    },
    async fetch() {
        if (this.$common.isEmpty(this.$route.query.classificationOid) || this.$common.isEmpty(this.$route.query.classification)) {
            return;
        }

        this.classificationInfo = await this.getClassificationInfo();

        // console.log("classificationInfo", this.classificationInfo)
    },
    watch: {
        $route() {
            this.$fetch();
        },
    },
    computed: {
        loadingIndicator() {
            return $nuxt.$root.$loading.percent;
        },
    },
    methods: {
        async getClassificationInfo() {
            const urlGet = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.GET;

            let reqParam = {
                oid: this.$route.query.classificationOid,
                categoryType: this.$amConstant.CATEGORY_TYPE.PRODUCT,
                fillChildClassification: true,
                fillRelateMaterial: true,
                fillDoc: true,
            };

            return await this.$axios.post(urlGet, reqParam).then(res => {
                if (200 === res.status && this.$common.isNotEmpty(res.data)) {
                    if (this.$common.isNotEmpty(res.data.relateMaterialList)) {
                        this.getProductListInfo(res.data.relateMaterialList[0]);
                        this.applicationViewCollapseActive.push("dataSheet0");
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
                        classificationOid: item.classificationOid,
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
                targetObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_PRODUCT,
                targetOid: this.$route.query.classificationOid,
                materialOid: material.oid,
                fillDatasheet: true,
            };

            await this.$axios.post(urlViewListAll, cnd).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    // console.log( "getProductListInfo 데이터시트용", res.data );
                    return;
                }

                this.classificationInfo.relateMaterialList.forEach(relateMaterial => {
                    if (relateMaterial.oid === material.oid) {
                        relateMaterial.productList = res.data;
                    }
                });

                this.$forceUpdate();
                // console.log( "getProductListInfo 데이터시트용", res.data );
                // console.log( "relateMaterialList", this.relateMaterialList );
            });
        },
    },
};
</script>
<style></style>
