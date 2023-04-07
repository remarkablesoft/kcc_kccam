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
                        <h2 class="tit">{{ $t(`user_common_function`) }}</h2>
                        <p class="sub-txt" v-html="$t(`user_market_function_detail_tit`)"></p>
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
                                    <span class="sub-txt xlg">{{ $t(`user_common_function`) }}</span>
                                    <h3 class="tit lg" v-text="functionInfo.name"></h3>
                                </div>
                                <!-- descr -->
                                <div class="descr variable-context">
                                    <p class="txt" v-text="functionInfo.descr"></p>
                                </div>
                            </div>

                            <!-- inner-content -->
                            <div class="inner-content">
                                <el-collapse v-model="functionViewCollapseActive" class="el-collapse-normal">
                                    <!-- EMC Products Datasheet -->
                                    <el-collapse-item
                                        :name="index"
                                        v-for="(item, index) in relateMaterialList"
                                        :key="item.oid"
                                        @click.native="getProductListInfo(item)"
                                    >
                                        <!-- 타이틀 -->
                                        <template slot="title">
                                            <h4 class="tit md" v-text="item.name + ' / ' + $t(`user_common_product_productDataSheet`)"></h4>
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

                                    <!-- References -->
                                    <el-collapse-item name="6" v-if="$common.isNotEmpty(functionInfo.addContentsList)">
                                        <!-- 타이틀 -->
                                        <template slot="title">
                                            <h4 class="tit md">{{ $t(`user_market_references`) }}</h4>
                                        </template>
                                        <!-- 내용 -->
                                        <div class="content-wrap">
                                            <the-description-plot :add-contents-list="functionInfo.addContentsList" />
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

export default {
    head: {
        title: "KCC AM - Function 상세",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Function 상세 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Function 상세" },
            { name: "twitter:title", content: "KCC AM - Function 상세" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        theDataSheet,
    },
    data() {
        return {
            pageLoading: false,
            // collapse default active
            functionViewCollapseActive: [],

            partList: [],
            detailList: [],

            name: "",
            descr: "",
            functionOid: "",

            functionInfo: {},
            relateMaterialList: [],
        };
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },

    async fetch() {
        if (this.$common.isEmpty(this.$route.query.classificationOid)) {
            return;
        }
        this.pageLoading = true;
        this.functionViewCollapseActive = [];
        // this.getPartData();
        // this.getData(this.$route.query.classificationOid);
        await this.getFunctionData(this.$route.query.classificationOid);

        this.pageLoading = false;
    },

    watch: {
        async $route() {
            this.functionViewCollapseActive = [];

            if (this.$common.isNotEmpty(this.$route.query.classificationOid)) {
                // this.getData(this.$route.query.classificationOid);
                await this.getFunctionData(this.$route.query.classificationOid);
            }
        },
    },

    methods: {
        // function 정보 가져오기
        async getFunctionData(oid) {
            const urlGet = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.GET;

            let param = {
                oid: oid,
                fillIconFile: true,
                fillContents: true,
                fillMaterial: true,
                classificationObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_FUNCTION,
                fillRelateMaterial: true,
            };
            this.pageLoading = true;
            await this.$axios.post(urlGet, param).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }
                this.functionInfo = res.data;
                this.storageFileUid = res.data.iconFile ? res.data.iconFile.storageFileUid : "";
                // console.log( res.data );
                this.relateMaterialList = res.data.relateMaterialList;
                // console.log( "1 : relateMaterial 만들어짐 " );
                // console.log( this.relateMaterialList );
                this.functionOid = res.data.oid;

                if (this.$common.isNotEmpty(this.relateMaterialList)) {
                    this.getProductListInfo(this.relateMaterialList[0]);
                    this.functionViewCollapseActive = [0];
                }
            });

            this.pageLoading = false;
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
                targetObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_FUNCTION,
                targetOid: this.$route.query.classificationOid,
                materialOid: material.oid,
                fillDatasheet: true,
            };

            this.$axios.post(urlViewListAll, cnd).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }

                this.relateMaterialList.forEach(relateMaterial => {
                    if (relateMaterial.oid === material.oid) {
                        relateMaterial.productList = res.data;
                    }
                });
                // console.log( "getProductListInfo", res.data );
                this.$forceUpdate();
                // console.log( "relateMaterialList", this.relateMaterialList );
            });
        },

        setProductUrl(item) {
            let url = this.$urlConstant.MENU_URL_PREFIX.USER_PRODUCT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT.PRODUCT_VIEW;
            url += "?depth=3&productOid=" + item.oid + "&classificationOid=" + this.functionOid + "&classification=function";
            return url;
        },
    },
};
</script>

<style></style>
