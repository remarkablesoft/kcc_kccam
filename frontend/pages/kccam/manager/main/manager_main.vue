<template>
	<div class="inner-wrapper">
		<!-- loading -->
<!--		<div class="loading-container" v-if="loadingIndicator > 0">-->
<!--			<the-loading />-->
<!--		</div>-->

		<!-- manager-content-body -->
		<div class="manager-content-body">
			<!-- contents 내용 -->
			<!-- <div class="content-title">
				<div class="sub-title">
					<h2>타이틀</h2>
				</div>
			</div> -->

			<div class="content-detail">
				<div class="main-txt-group">
<!--					<p class="tit md">-->
<!--						<strong class="main-color">김형준</strong>님은-->
<!--						<strong class="main-color">슈퍼관리자</strong>-->
<!--						등급입니다.-->
<!--					</p>-->
					<p class="txt">
                        <strong class="main-color">KCC Advanced Material</strong> 관리자 화면입니다.<br />
						기타 이용 문의에 대해서는 KCC 소재 마케팅팀 담당자에게 문의
						주시기 바랍니다.<br />
						KCC 소재 마케팅팀 홍길동 031-288-3253
					</p>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import { mapActions } from "vuex";

export default {
	layout: "managerLayout",
	components: {
		theLoading
	},
	data() {
		return {};
	},
	async fetch () {
		await this.getMaterialMap();
		// this.setMenuList();
	},
	computed : {
		// store 의 materialMap 가져오기
		getMap () {
			return this.$store.state.menu.materialMap;
		},

	},
	methods : {
		/**
		 *  제품 서브 메뉴 리스트를 만들어줍니다.
		 */
		...mapActions({
			getMaterialMap : "menu/getMaterialMap",
		}),
	},
};
</script>

<style scoped></style>
