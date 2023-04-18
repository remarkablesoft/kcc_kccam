<template>
	<div class="related-sites popup-zone" :class="[isPopupVisible ? 'visible' : '']" v-if="isPopupVisible" >
		<!-- inner-content -->
		<div class="inner-content">
			<!-- 목록 -->
			<div class="detail-content">
				<div class="tit-area">
					<div class="tit-item">
						<h2>
							POPUP ZONE
						</h2>
					</div>
					<div class="web-visible">
						<!-- navigation btn -->
						<div class="navigation-btn-group">
							<!-- swiper navigation -->
							<div class="swiper-button-prev site-swiper-prev" slot="button-prev">
								<span class="material-icons-outlined">arrow_back_ios</span>
							</div>
							<div class="sub-icon">
								<span>|</span>
							</div>
							<!-- btn autoplay pause / play -->
							<div class="swiper-button-next site-swiper-next" slot="button-next">
								<span class="material-icons-outlined">arrow_forward_ios</span>
							</div>
						</div>
						<div class="button-play">
							<!-- pause -->
							<button v-show="popupSwiperPlay" class="swiper-button-pause" @click="popupSwiperPlay = false">
								<span class="material-icons-outlined">stop</span>
							</button>
							<!-- play -->
							<button v-show="!popupSwiperPlay" class="swiper-button-pause" @click="popupSwiperPlay = true">
								<span class="material-icons-round">
									play_arrow
								</span>
							</button>
						</div>
					</div>
				</div>
				<div class="list-area">
					<div class="list-wrapper">
						<swiper
							class="swiper list-swiper related-site-list"
							:options="relatedSitesListSwiperOption"
							ref="popupSwiper"
						>
							<!-- list-item -->
							<swiper-slide v-for="item in popupList" :key="item.oid">
								<div class="list-item">
									<a @click="setImgUrl(item)">
										<img alt="" :src="getImage(item)"/>
									</a>
								</div>
							</swiper-slide>
						</swiper>
					</div>
				</div>
			</div>
			<div class="content-footer">
				<div class="btn-row">
					<button class="btn-close-round" @click="hideToday()">
						<span class="txt">오늘 하루 열지 않기</span>
						<i class="material-icons">close</i>
					</button>
					<button class="btn-close-round" @click="closePopup()">
						<span class="txt"> 닫기</span>
						<i class="material-icons">close</i>
					</button>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import VClamp from "vue-clamp";
import theNoData from "~/components/common/nodata/TheNoData";
import Cookies from "js-cookie";


export default {
	components: {
		VClamp,
		theNoData,
	},
	props: {},

	data() {
		return {
			// 관련 사이트 목록 스와이퍼 옵션
			relatedSitesListSwiperOption: {
				// spaceBetween: 20,
				slidesPerView: 3,
				slidesPerGroup: 3,
				centerInsufficientSlides: true, // 센터 정렬
				// slidesPerView: "auto",
				// centeredSlides: true,
				loopFillGroupWithBlank: true,
				autoplay: {
					delay: 5000,
					disableOnInteraction: true,
				},
				// loop: true,
				// loopedSlides: 1,
				// pagination : {
				// 	el        : ".site-swiper-pagination",
				// 	clickable : true,
				// },
				navigation: {
					nextEl: ".site-swiper-next",
					prevEl: ".site-swiper-prev",
				},

				breakpoints: {
					1024: {
						slidesPerView: 3,
						slidesPerGroup: 3,
					},

					865: {
						slidesPerView: 2.2,
						slidesPerGroup: 1,
						centeredSlides: false,
					},
					430: {
						slidesPerView: 1.3,
						slidesPerGroup: 1,
						centeredSlides: false,
					}

				}
			},

			popupList: [],

			// swiper autoplay
			popupSwiperPlay: true,
			isPopupVisible: false,
		};
	},
	async fetch() {
		await this.listPopup();
	},
	computed: {},
	watch: {
		// swiper autoplay
		popupSwiperPlay() {
			if (true === this.popupSwiperPlay) {
				this.$refs.popupSwiper.swiper.autoplay.start();
			}
			else {
				this.$refs.popupSwiper.swiper.autoplay.stop();
			}
		},

		isPopupVisible() {
			this.setScrollOverflow();
		},
	},
	mounted() {
		this.setScrollOverflow();
	},
	methods: {

		getImage(item) {
			if (this.$common.isEmpty(item) || this.$common.isEmpty(item.fileList)) {

				return;
			}

			return this.$common.getThumbnailPath( item.fileList[ 0 ].storageFileUid, item.width + "_" + item.height );
		},

		closePopup() {

			this.isPopupVisible = false;
		},

		setScrollOverflow() {

			if (this.isPopupVisible) {
				document.querySelector('.wrapper').style.overflow = "hidden"
			}
			else {
				document.querySelector('.wrapper').style.overflow = ""
			}
		},

		// 오늘 하루 열지 않기
		hideToday() {

			Cookies.set(this.$amConstant.POPUP_HIDE_TODAY_LIST, true, {expires: 1});
			this.closePopup();
		},

		checkCookie() {

			if (Cookies.get(this.$amConstant.POPUP_HIDE_TODAY_LIST)) {
				this.closePopup();
			}
		},

		async listPopup() {

			let param = {
				popupViewTypeFlag: this.$amConstant.POPUP_TYPE.VIEW.LIST.KEY,
			};

			await this.$axios.post(this.$urlConstant.API_URL_PREFIX.POPUP + this.$urlConstant.API_URL_SUFFIX.POPUP.OPERATION_LIST, param).then(res => {

				if (this.$common.isEmpty(res.data)) {
					this.isPopupVisible = false;
					return;
				}

				this.popupList = res.data;

				this.isPopupVisible = true;
				this.checkCookie();
			});
		},

		/* 링크 타입이 새창인지, 페이지 이동인지 구분하여 이동 */
		setImgUrl(item) {

			if (this.$common.isEmpty(item) || this.$common.isEmpty(item.linkUrl)) {
				return;
			}

			// 페이지 이동
			if (this.$amConstant.POPUP_LINK_TYPE.PAGE_MOVE === item.linkTypeFlag) {

				window.location.href = item.linkUrl;
			}
			else if (this.$amConstant.POPUP_LINK_TYPE.NEW === item.linkTypeFlag) {  // 새창

				window.open( item.linkUrl );
			}
		},
	},
};
</script>
<style></style>
