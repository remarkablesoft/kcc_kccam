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
                                <h3 class="tit lg" v-text="materialInfo.name"></h3>
                                <span class="sub-txt lg" v-text="materialInfo.className"></span>
                            </div>
                            <div class="img-descr-group">
                                <!-- img -->
                                <div class="img-box" v-if="materialInfo.mainImg">
                                    <img
                                        :src="'/storage/storageFile_fileView/' + materialInfo.mainImg.storageFileUid"
                                        alt="소재정보 이미지"
                                        @error="$common.imageError"
                                        class="img"
                                    />
                                </div>
                                <!-- descr -->
                                <div class="descr">
                                    <ul class="square-dot-list variable-context" v-if="materialInfo.descr">
                                        <li class="txt" v-text="materialInfo.descr"></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- inner-content -->
                    <div class="inner-content">
                        <el-collapse v-model="materialViewCollpaseActive" class="el-collapse-normal default-w">
                            <!-- Application -->
                            <el-collapse-item name="1" v-if="$common.isNotEmpty(materialInfo.applicationList)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_common_application`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <!-- 아래 product-img-list 목록 메인에 있는 목록과 퍼블리싱 구조 동일 -->
                                    <div class="product-img-list">
                                        <!-- no-data(loading) -->
                                        <!--										<div class="no-data" v-if="false">-->
                                        <!--											<div class="loading-sm">-->
                                        <!--												<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>-->
                                        <!--											</div>-->
                                        <!--											<p>데이터 로딩중입니다.</p>-->
                                        <!--										</div>-->
                                        <!--										&lt;!&ndash; no-data &ndash;&gt;-->
                                        <!--										<div class="no-data" v-if="false">-->
                                        <!--											<span class="material-icons">error_outline</span>-->
                                        <!--											<span>데이터가 없습니다.</span>-->
                                        <!--										</div>-->
                                        <!-- list-item -->
                                        <div
                                            class="list-item"
                                            v-for="(item, itemIndex) in materialInfo.applicationList"
                                            :key="itemIndex"
                                            @mouseover="onMouseOver( item )"
                                            @mouseleave="onMouseLeave( item )"
                                        >
                                            <div class="inner">
                                                <!-- 1depth :: 목록 타이틀 버튼 -->
                                                <div class="tit-area" :class="item.active ? 'active' : ''">
                                                    <span class="tit" v-text="item.name"><!-- EMC --></span>
                                                    <span
                                                        class="material-icons"
                                                        @click="
                                                            item.active = !item.active;
                                                            $forceUpdate();
                                                        "
                                                        >expand_more</span
                                                    >
                                                </div>
                                                <!-- 제품 목록 -->
                                                <slide-up-down :active="item.active" :duration="300" class="inner-list-box">
                                                    <!-- scroll area -->
                                                    <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                                        <!-- el1 -->
                                                        <div>
                                                            <!-- el2 -->
                                                            <!--  -->
                                                            <div
                                                                class="inner-list-item"
                                                                v-for="(innerItem, innerItemIndex) in item.childCategoryList"
                                                                :key="innerItemIndex"
                                                            >
                                                                <!-- 2depth :: 목록 타이틀 버튼 -->
                                                                <div
                                                                    class="tit-area"
                                                                    @click="
                                                                        innerItem.active = !innerItem.active;
                                                                        $forceUpdate();
                                                                    "
                                                                    :class="innerItem.active ? 'active' : ''"
                                                                >
                                                                    <span class="txt" v-text="innerItem.name"><!-- Application --></span>
                                                                    <span class="material-icons">arrow_drop_down</span>
                                                                </div>
                                                                <!-- 상세 목록 -->
                                                                <slide-up-down :active="innerItem.active" :duration="300" class="detail-list-box">
                                                                    <ul class="detail-list">
                                                                        <!-- 상세 list-item -->
                                                                        <li
                                                                            v-for="(detailItem, detailItemIndex) in innerItem.childCategoryList"
                                                                            :key="detailItemIndex"
                                                                        >
                                                                            <nuxt-link
                                                                                :to="
                                                                                    localePath(
                                                                                        '/kccam/user/product/product_applicationView?depth=2&partOid=' +
                                                                                            $route.query.partOid +
                                                                                            '&classificationOid=' +
                                                                                            detailItem.oid +
                                                                                            '&classification=application',
                                                                                    )
                                                                                "
                                                                                class="txt-link"
                                                                                v-text="detailItem.name"
                                                                            >
                                                                                <!-- Memory --></nuxt-link
                                                                            >
                                                                        </li>
                                                                    </ul>
                                                                </slide-up-down>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </slide-up-down>
                                                <!-- img -->
                                                <div class="img-box" @click="goApplicationView(item)">
                                                    <img
                                                        :src="getImageUrl(item)"
                                                        @error="$common.imageError"
                                                        alt="제품 관련 이미지"
                                                        class="img"
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </el-collapse-item>
                            <!-- Product Classification -->
                            <el-collapse-item name="2" v-if="$common.isNotEmpty(materialInfo.productClassificationList)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_common_productClassification`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <div class="classification-list-group">
                                        <div class="group-item" v-for="(item, index) in materialInfo.productClassificationList" :key="index">
                                            <div class="tit">
                                                <nuxt-link
                                                    :to="
                                                        localePath(
                                                            '/kccam/user/product/product_classificationView?depth=2&partOid=' +
                                                                $route.query.partOid +
                                                                '&classificationOid=' +
                                                                item.oid +
                                                                '&classification=product',
                                                        )
                                                    "
                                                    v-text="item.name"
                                                ></nuxt-link>
                                            </div>
                                            <ul class="inner-list">
                                                <li v-for="(innerItem, index) in item.innerList" :key="index">
                                                    <span v-text="innerItem.title"></span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </el-collapse-item>
                            <!-- References -->
                            <el-collapse-item name="3" v-if="$common.isNotEmpty(materialInfo.addContentsList)">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_market_references`) }}</h4>
                                </template>
                                <!-- 내용 -->
                                <div class="content-wrap">
                                    <!-- 설명 이미지 -->
                                    <the-description-plot :add-contents-list="materialInfo.addContentsList" />
                                </div>
                            </el-collapse-item>
                            <!-- KTMC series selection guide(LSI devices) -->
                            <el-collapse-item name="4" v-if="false">
                                <!-- 타이틀 -->
                                <template slot="title">
                                    <h4 class="tit md">{{ $t(`user_product_guide_tit`) }}</h4>
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
                                                    KTMC series selection guide(LSI devices)
                                                </caption>
                                                <!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
                                                <colgroup>
                                                    <col style="width: 8%" />
                                                    <col style="width: 20%" />
                                                    <col style="width: 20%" />
                                                    <col style="width: 12%" />
                                                    <col style="width: 12%" />
                                                </colgroup>
                                                <tbody>
                                                    <tr>
                                                        <!-- th에만 해당 scope="" col(열) / row(행) -->
                                                        <th scope="col" rowspan="4">
                                                            <span
                                                                >{{ $t(`user_product_guide_LSI`) }}<br />{{ $t(`user_product_guide_Devices`) }}</span
                                                            >
                                                        </th>
                                                        <th scope="col" colspan="2">
                                                            <span>{{ $t(`user_product_guide_Type`) }}</span>
                                                        </th>
                                                        <th scope="col" rowspan="2">
                                                            <span>{{ $t(`user_product_guide_Package`) }}</span>
                                                        </th>
                                                        <th scope="col" rowspan="2">
                                                            <span>{{ $t(`user_product_guide_Product`) }}</span>
                                                        </th>
                                                        <th scope="col" rowspan="2">
                                                            <span>{{ $t(`user_product_guide_Features`) }}</span>
                                                        </th>
                                                    </tr>
                                                    <tr>
                                                        <!-- th에만 해당 scope="" col(열) / row(행) -->
                                                        <th scope="col">
                                                            <span>{{ $t(`user_product_LeadFrame`) }}</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span>{{ $t(`user_product_Substract`) }}</span>
                                                        </th>
                                                    </tr>
                                                    <!-- no-data -->
                                                    <tr v-if="false">
                                                        <!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
                                                        <td colspan="6">
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
                                                            <!-- 동그라미 표시 -->
                                                            <span class="mark-circle"></span>
                                                        </td>
                                                        <td>
                                                            <!-- - 표시 -->
                                                            <span class="mark-hyphen"></span>
                                                        </td>
                                                        <td>
                                                            <span>SOIC</span>
                                                        </td>
                                                        <td>
                                                            <span>KTMC - 5800GQS</span>
                                                        </td>
                                                        <td>
                                                            <span>MSL 1 &amp; Applicable to Cu wire</span>
                                                        </td>
                                                    </tr>
                                                    <!-- list-item -->
                                                    <tr>
                                                        <td>
                                                            <!-- 동그라미 표시 -->
                                                            <span class="mark-circle"></span>
                                                        </td>
                                                        <td>
                                                            <!-- - 표시 -->
                                                            <span class="mark-hyphen"></span>
                                                        </td>
                                                        <td>
                                                            <span>SOIC</span>
                                                        </td>
                                                        <td>
                                                            <span>KTMC - 5800GQS</span>
                                                        </td>
                                                        <td>
                                                            <span>MSL 1 &amp; Applicable to Cu wire</span>
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
import theDescriptionPlot from "~/components/common/TheDescriptionPlot.vue";

// Application 이미지
// 제품 이미지
import productImg01 from "@/assets/images/contents/product/contents_product_emc.png"; // emc
import productImg02 from "@/assets/images/contents/product/contents_product_adhesive_02.png"; // 전자소재
import productImg03 from "@/assets/images/contents/product/contents_product_mc_02.png"; // Metallized Ceramics
import productImg04 from "@/assets/images/contents/product/contents_product_cs_02.png"; // Ceramic Substrates
import productImg05 from "@/assets/images/contents/product/contents_product_lf.png";
import nodataImg from "@/assets/images/contents/sample/contents_catalog_no_image.png";

export default {
    head: {
        title: "KCC AM - Product Material 상세",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "Product Material 상세 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Product Material 상세" },
            { name: "twitter:title", content: "KCC AM - Product Material 상세" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        theProductVisual,
        theDescriptionPlot,
    },
    data() {
        return {
            // collapse default active
            materialViewCollpaseActive: ["1", "2", "3", "4"],

            materialInfo: {},
        };
    },
    watch: {
        $route() {
            this.$fetch();
        },
    },
    async fetch() {
        this.materialInfo = await this.getMaterialInfo();
    },
    computed: {
        loadingIndicator() {
            return this.$root.$loading.percent;
        },
    },
    methods: {
        goApplicationView(item) {
            this.$router.push(
                this.localePath({
                    path: "/kccam/user/product/product_applicationView?depth=1&classificationOid=1ST7e1vx000",
                    query: {
                        depth: 2,
                        partOid: this.$route.query.partOid,
                        classificationOid: item.oid,
                        classification: "application",
                    },
                }),
            );
        },

        async getMaterialInfo() {
            const getProductInfo = this.$urlConstant.API_URL_PREFIX.MATERIAL + this.$urlConstant.API_URL_SUFFIX.MATERIAL.GET;

            let reqParam = {
                oid: this.$route.query.partOid,
                // categoryType            : this.$amConstant.CATEGORY_TYPE.APPLICATION,
                // fillChildClassification : true
            };

            return await this.$axios.post(getProductInfo, reqParam).then(res => {
                if (200 === res.status && this.$common.isNotEmpty(res.data)) {
                    let materialInfo = res.data;

                    if (this.$common.isNotEmpty(materialInfo.applicationList)) {
                        materialInfo.applicationList.forEach(app => {
							if ( !this.isImgExist( app ) ) {
								app.active = true;
							}
                            if (this.$common.isNotEmpty(app.childCategoryList)) {
                                app.childCategoryList.forEach((child, index) => {
                                    if (0 === index) {
                                        child.active = true;
                                    }
                                });
                            }
                        });
                    }
                    return materialInfo;
                }
            });
        },

	    /**
	     * Image의 경로를 가져옵니다.
	     *
	     * @param item
	     * @returns {string|*}
	     */
        getImageUrl( item ) {

			if ( !this.isImgExist( item ) ) {
				return nodataImg;
			}
	        return "/storage/storageFile_fileView/" + item?.iconFile?.storageFileUid;
        },

	    /**
	     * application 이미지가 존재하는지 확인합니다.
	     *
	     * @param application
	     * @returns {*}
	     */
	    isImgExist( application ) {
			return this.$common.isNotEmpty( application?.iconFile?.storageFileUid );
	    },
	    /**
	     * application 이미지에 마우스를 올리거나 내렸을 때 발생하는 이벤트입니다.
	     * application 이미지가 없으면 이벤트가 발동하지 않습니다.
	     *
	     * @param application
	     */
	    onMouseOver( application ) {
		    if ( !this.isImgExist(application) ) {
				return;
		    }
		    application.active = true;
			this.$forceUpdate();
	    },
	    onMouseLeave( application ) {
		    if ( !this.isImgExist(application) ) {
			    return;
		    }
		    application.active = false;
			this.$forceUpdate();
	    }
    },
};
</script>
<style></style>
