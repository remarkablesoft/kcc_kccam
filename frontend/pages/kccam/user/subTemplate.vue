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
						<h2 class="tit">{{ $t(`user_intro_introKcc_header_tit`) }}</h2>
                        <p class="sub-txt">{{ $t(`user_intro_introKcc_header_subtxt`) }}</p>
					</div>
				</div>
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
                            <div class="tit-area">
                                <!-- <h3 class="tit">미래산업의 좌표, KCC 첨단소재에서 찾다</h3> -->
                            </div>
                            <div class="">

                            </div>
                        </div>
                    </div>
                    <!-- inner-content -->
                    <div class="inner-content">
                        <div class="default-w">
                            <div class="tit-area">
                                <!-- <h3 class="tit">미래산업의 좌표, KCC 첨단소재에서 찾다</h3> -->
                            </div>
                            <div class="">

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
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
};
</script>

<style></style>
