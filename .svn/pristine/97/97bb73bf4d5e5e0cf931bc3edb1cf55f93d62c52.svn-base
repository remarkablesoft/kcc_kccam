<template>
    <div class="inner-wrapper">
        <!-- loading -->
        <!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--            <the-loading />-->
        <!--        </div>-->

        <!-- manager-content-body -->
        <div class="manager-content-body">
            <div class="content-title">
                <div class="sub-title">
                    <h2>Market 관리 전체</h2>
                </div>
                <div class="btn">
                    <el-button type="st st-primary" size="small" @click="goList()">Market 목록 보기</el-button>
                    <el-button type="primary" size="small" @click="goEdit()">신규 Market 등록</el-button>
                </div>
            </div>
            <!-- table -->
            <div class="content-detail">
                <!-- <div class="btn-wrap-md">
                    <el-button type="gray" size="small" @click="goEdit()">신규 Market 등록</el-button>
                </div> -->
                <div></div>
                <!-- tree -->
                <div class="tree-area">
                    <the-tree ref="tree" editType="view" :part-oid="partOid" :type="applicationType"></the-tree>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";

export default {
    layout: "managerLayout",
    components: {
        theLoading,
    },
    data() {
        return {
            /*트리관련 data start*/
            applicationType: this.$amConstant.CATEGORY_TYPE.MARKET,
            partOid: "",
            /*트리관련 data end*/
        };
    },
    methods: {
        // 목록 페이지로 이동
        goList() {
            this.$router.push(this.localePath("/kccam/manager/marketMgnt/market/market_list"));
        },

        // 수정/등록 페이지로 이동
        goEdit() {
            this.$router.push(this.localePath("/kccam/manager/marketMgnt/market/market_edit"));
        },
    },
};
</script>

<style scoped></style>
