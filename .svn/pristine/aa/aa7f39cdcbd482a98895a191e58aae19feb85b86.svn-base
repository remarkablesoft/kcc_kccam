<template>
	<!-- 사용자 레이아웃 -->
	<div class="wrapper">
		<!-- 전체 페이지 로딩 -->
		<!--		<div v-if="0 < $store.state.loading.loadingList.length" class="loading-container">-->
		<!--			<the-loading />-->
		<!--		</div>-->
		<!-- 주 컨테이너 :: *( 사용자 ) -->
		<div id class="main-container user-container">
			<!-- 1대1 문의 안내 배너 :: 1대1 문의 페이지에서만 보임. -->
			<div class="notice-banner" v-if="noticeBanner">
				<div class="inner default-w">
					<div class="tit-area">
						<span class="icon"></span>
						<!-- <span class="tit">1대1 문의 이용안내</span> -->
						<span class="tit">{{
							$t("user_customerSupportInquiry_noticeBanner_tit")
						}}</span>
					</div>
					<div class="info-area">
						<p class="txt">
							{{
								$t(
									"user_customerSupportInquiry_noticeBanner_txt1"
								)
							}}
							<br />
							{{
								$t(
									"user_customerSupportInquiry_noticeBanner_txt2"
								)
							}}
							<a
								href="/kccam/user/policy/policy_privacy"
								target="_blank"
								class="txt-link-underline main-color"
							>
								{{
									$t(
										"user_customerSupportInquiry_noticeBanner_txt3"
									)
								}}
							</a>
							{{
								$t(
									"user_customerSupportInquiry_noticeBanner_txt4"
								)
							}}
							<!-- <p class="txt">
								해당 게시판 성격에 맞지 않는 내용, 욕설, 광고 등은
								사전고지 없이 관리자에 의해 삭제될 수있습니다.<br />
								1:1 문의에 대한 답변은 이메일로만 발송되며 개인정보
								관련 정책은
								<a
									href="/kccam/user/policy/policy_privacy"
									target="_blank"
									class="txt-link-underline main-color"
									>개인정보처리방침</a
								>을 확인 바랍니다. -->
						</p>
					</div>
					<div class="btn-area">
						<el-button type="icon-only white" class="btn-close">
							<span
								class="material-icons"
								@click="noticeBanner = false"
								>clear</span
							>
						</el-button>
					</div>
				</div>
			</div>
			<!-- header -->
			<the-header />
			<section class="content-section">
				<h2 class="hidden">컨텐츠 섹션</h2>
				<!-- 페이지별 내용 -->
				<Nuxt />
			</section>
			<!-- footer -->
			<the-footer />
			<!-- top btn -->
			<transition name="fade">
				<div class="floating-btn" v-if="scroll">
					<button type="button" class="btn btn-top" @click="goTop()">
						<i class="material-icons">keyboard_tab</i>
					</button>
				</div>
			</transition>
		</div>
	</div>
</template>
<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import theHeader from "~/components/kccam/user/layout/TheHeader.vue";
import theFooter from "~/components/kccam/user/layout/TheFooter.vue";

export default {
	components: {
		theLoading,
		theHeader,
		theFooter
	},
	data() {
		return {
			// scroll
			scroll: false,

			// 1:1 문의 안내 배너
			noticeBanner: false
		};
	},
	mounted() {
		if (!process.client) {
			return;
		}
		// 스크롤 관련
		window.addEventListener("scroll", this.onScroll);

		// 반응형 적용할 html 구분 ( 관리자 아닌 사용자에만 적용. )
		document.querySelector("html").classList.add("responsive-html");

		// 1:1 문의 안내 배너
		this.noticeBannerVisible(this.$route.path);
	},
	beforeDestroy() {
		if (!process.client) {
			return;
		}
		// 스크롤 관련
		window.removeEventListener("scroll", this.onScroll);
	},
	computed: {
		loadingIndicator() {
			return this.$nuxt.$root.$loading.percent;
		}
	},
	watch: {
		$route() {
			// 1:1 문의 안내 배너
			this.noticeBannerVisible(this.$route.path);
			// 방문 이력 저장
			this.insertHistView( this.$route.fullPath );
		}
	},
	methods: {
		// 스크롤 top 으로
		goTop() {
			animateScrollTo(0);
		},
		/**
		 * 방문 이력을 남깁니다.
		 */
		async insertHistView( url ) {
			if ( this.$common.isEmpty( url ) ) {
				return;
			}
			const HIST_VIEW_INSERT_URL = this.$urlConstant.API_URL_PREFIX.HIST_VISIT
										+ this.$urlConstant.API_URL_SUFFIX.HIST_VISIT.INSERT;
			let visitPageName = this.getVisitPageName( this.$route.fullPath );
			let info = {
				visitURL        : this.$route.fullPath,
				visitPageName    : visitPageName,
			}
			await this.$axios.post( HIST_VIEW_INSERT_URL, info ).then( res => {
				// console.log( res );
			}).catch( error => {
				console.log( error );
			});
		},
		getVisitPageName( url ) {
			if( this.$common.isEmpty( url ) ) {
				return '-';
			}
			let urlPrefixObj = this.$urlConstant.MENU_URL_PREFIX;
			let urlSuffixObj = this.$urlConstant.MENU_URL_SUFFIX;
			let str = '';
			const _self = this;
			if ( url.indexOf( "kccam/user/main/user_main") != -1 ) {
				str += "메인 페이지";
				return str;
			}
			// 1뎁스 만들기 ( prefix )
			for( let preFixKey in urlPrefixObj ) {
				if ( url.indexOf( urlPrefixObj[ preFixKey ]) !=-1 ) {
					let matchKey = preFixKey.split('USER_')[1];
					str += _self.$amConstant.MENU_NAME[matchKey].TITLE;
					break;
				}
			}
			// 2뎁스 만들기 ( suffix )
			for( let suffixKey in urlSuffixObj ) {
				let deepObj = urlSuffixObj[suffixKey];
				for( let deepKey in deepObj ) {
					let deepValue = deepObj[deepKey];
					if ( typeof( deepValue ) == 'string' ) {
						let compareStr = deepValue.split('?').length > 1 ? deepValue.split('?')[0]
																				: deepValue.split('?')[0];

						if ( url.indexOf( compareStr ) != -1 ) {
							if ( _self.$common.isEmpty(_self.$amConstant.MENU_NAME[suffixKey] ) ) {
								break;
							}
							if( typeof _self.$amConstant.MENU_NAME[suffixKey][deepKey] != 'undefined') {
								str += ' > ';
								str += _self.$amConstant.MENU_NAME[suffixKey][deepKey];
							}
						}
					}
					else if ( typeof( deepValue ) == 'object' ) {
						if ( url.indexOf( "newsroom" ) != -1 ) {
							str += ' > ';
							str += "뉴스룸";
						}

					}
				}
			}
			return str;
		},

		// 스크롤 관련
		onScroll(e) {
			// let scrollPosition = window.scrollY || document.documentElement.scrollTop;

			let scrollPosition = document.querySelector("html").scrollTop;
			let headerHeight = document.getElementById("header").scrollHeight;

			if (scrollPosition > headerHeight) {
				this.scroll = true;
			} else {
				this.scroll = false;
			}
		},

		// 1:1 문의 안내 배너
		noticeBannerVisible(path) {
			if (-1 < path.indexOf("/customerSupport_inquiry")) {
				this.noticeBanner = true;
			} else {
				this.noticeBanner = false;
			}
		}
	}
};
</script>
<style scoped></style>
