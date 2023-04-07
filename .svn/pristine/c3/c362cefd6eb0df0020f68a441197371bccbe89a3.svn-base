<template>
	<div class="inner-wrapper">
		<!-- swiper -->
		<swiper
			class="swiper visual-swiper"
			:options="productVisualSwiperOption"
		>
			<!-- visual-item -->
			<swiper-slide
				v-for="(item, index) in productVisualList"
				:key="index"
			>
				<div
					class="visual-item"
					:style="{ backgroundImage: 'url(' + item.imageUrl + ')' }"
				>
					<div class="inner">
						<div class="txt-area pagination-bottom">
							<!-- txt -->
							<div class="tit fw500" v-html="item.title"></div>
							<!--                            <p class="mid-txt" v-html="item.midText"></p>-->
							<p class="sub-txt" v-html="item.descr"></p>

							<!-- swiper pagination -->
							<div
								class="swiper-pagination"
								slot="pagination"
							></div>
						</div>
					</div>
				</div>
			</swiper-slide>
		</swiper>
	</div>
</template>

<script>
// 비주얼 이미지
import visualImg01 from "@/assets/images/bg/productVisual/bg_product_visual_01.png";
import visualImg02 from "@/assets/images/bg/productVisual/bg_product_visual_02.png";
import visualImg03 from "@/assets/images/bg/productVisual/bg_product_visual_03.png";
import visualImg04 from "@/assets/images/bg/productVisual/bg_product_visual_04.png";
import visualImg05 from "@/assets/images/bg/productVisual/bg_product_visual_05.png";

export default {
	data() {
		return {
			// 제품 비주얼 스와이퍼 목록
			productVisualList: [
				{
					imageUrl: visualImg01,
					title: this.$t(`product_main_visual01_tit`),
					descr: this.$t(`product_main_visual01_descr`),
				},
				{
					imageUrl: visualImg02,
					title: this.$t(`product_main_visual02_tit`),
					descr: this.$t(`product_main_visual02_descr`),
				},
				{
					imageUrl: visualImg03,
					title: this.$t(`product_main_visual03_tit`),
					descr: this.$t(`product_main_visual03_descr`),
				},
				{
					imageUrl: visualImg04,
					title: this.$t(`product_main_visual04_tit`),
					descr: this.$t(`product_main_visual04_descr`),
				},
				{
					imageUrl: visualImg05,
					title: this.$t(`product_main_visual05_tit`),
					descr: this.$t(`product_main_visual05_descr`),
				}
			],

			// 제품 비주얼 스와이퍼 옵션
			productVisualSwiperOption: {
				spaceBetween: 30,
				effect: "fade",
				loop: true,
				autoplay: {
					delay: 4000,
					disableOnInteraction: false
				},
				pagination: {
					el: ".swiper-pagination",
					clickable: true
				}
			}
		};
	}
};
</script>

<style></style>
