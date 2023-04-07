<template>
    <div class="inner-wrapper">
        <!-- swiper -->
        <swiper class="swiper visual-swiper" :options="mainVisualSwiperOption">
            <!-- visual-item -->
            <swiper-slide v-for="(item, index) in mainVisualList" :key="index">
                <div class="visual-item" :style="{ backgroundImage: 'url(' + item.imageUrl + ')' }">
                    <div class="inner">
                        <div class="txt-area pagination-bottom">
                            <!-- swiper pagination -->
                            <div class="swiper-pagination" slot="pagination"></div>

                            <!-- txt -->
                            <div class="tit" v-html="item.title"></div>
                            <p class="sub-txt" v-html="item.descr"></p>
                            <!-- <el-button type="st st-white" size="small bar" round
								>more</el-button
							> -->
                        </div>
                    </div>
                </div>
            </swiper-slide>
        </swiper>
    </div>
</template>

<script>
// 비주얼 이미지
import visualImg01 from "@/assets/images/bg/mainVisual/bg_main_visual_01.png";
import visualImg02 from "@/assets/images/bg/mainVisual/bg_main_visual_02.png";
import visualImg03 from "@/assets/images/bg/mainVisual/bg_main_visual_03.png";

export default {
    data() {
        return {
            // 메인 비주얼 스와이퍼 목록
            mainVisualList: [
                {
                    imageUrl: visualImg01,
                    title: this.$t(`user_main_visual01_tit`),
                    descr: this.$t(`user_main_visual01_descr`),
                },
                {
                    imageUrl: visualImg02,
                    title: this.$t(`user_main_visual02_tit`),
                    descr: this.$t(`user_main_visual02_descr`),
                },
                {
                    imageUrl: visualImg03,
                    title: this.$t(`user_main_visual03_tit`),
                    descr: this.$t(`user_main_visual03_descr`),
                },
            ],

            // 메인 비주얼 스와이퍼 옵션
            mainVisualSwiperOption: {
                spaceBetween: 30,
                effect: "fade",
                loop: true,
                autoplay: {
                    delay: 4000,
                    disableOnInteraction: false,
                },
                pagination: {
                    el: ".swiper-pagination",
                    clickable: true,
                },
            },
        };
    },
};
</script>

<style></style>
