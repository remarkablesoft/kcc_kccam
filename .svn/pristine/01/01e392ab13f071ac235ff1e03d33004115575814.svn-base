<template>
	<header
		id="header"
		class="header"
		:class="{
			'is-menu': isMenu,
			'is-search': isSearch,
			sticky: scroll,
			active: menuMouseOver
		}"
	>
		<!-- 메뉴 -->
		<div class="menu-area">
			<!-- logo -->
			<h1 class="logo">
				<nuxt-link :to="localePath('/kccam/user/main/user_main')">
					<img
						:src="logoImg ? defaultImg : onScrollImg"
						alt="KCC AM 로고"
					/><!-- :src="logoImg ? defaultImg : onScrollImg" -->
				</nuxt-link>
			</h1>
			<!-- gnb :: 사이트맵과 구조는 약간 다르나 data는 같이 씀. -->
			<nav class="gnb">
				<!--                <ul class="gnb-menu" v-if="!$fetchState.pending">-->
				<ul class="gnb-menu">
					<!-- 1depth -->
					<!--                    <li v-for="(menu, index) in menuList" :key="index">-->
					<li
						v-for="(menu, index) in menuList"
						:key="index"
						@mouseover="menuMouseOver = true"
						@mouseout="menuMouseOver = false"
					>
						<nuxt-link
							:to="localePath(menu.menuUrl)"
							class="main-menu-link"
							v-text="menu.menuName"
						></nuxt-link>
						<!-- 2depth -->
						<div class="gnb-sub-menu">
							<swiper
								class="swiper"
								:options="subMenuSwiperOption"
							>
								<swiper-slide
									class="list-item"
									v-for="(subMenu, index) in menu.subMenuList"
									:key="index"
								>
									<nuxt-link
										:to="localePath(subMenu.subMenuUrl)"
										class="sub-menu-link"
										v-text="subMenu.subMenuName"
									></nuxt-link>
									<!-- 3depth -->
									<div
										class="detail-menu-box"
										v-show="subMenu.detailMenuBox"
									>
										<!-- Product Classification -->
										<div
											class="detail-menu-item"
											v-for="(menuItem,
											index) in subMenu.detailMenuItem"
											:key="index"
										>
											<span
												class="menu-tit"
												v-text="menuItem.menuTitle"
											></span>
											<ul class="gnb-detail-menu">
												<li
													v-for="(detailMenu,
													index) in menuItem.detailMenuList"
													:key="index"
												>
													<nuxt-link
														:to="
															localePath(
																detailMenu.detailMenuUrl
															)
														"
														class="detail-menu-link"
														v-text="
															detailMenu.detailMenuName
														"
													></nuxt-link>
												</li>
											</ul>
										</div>
									</div>
								</swiper-slide>
								<div
									class="swiper-button-prev"
									slot="button-prev"
								>
									<span class="material-icons"
										>arrow_back_ios</span
									>
								</div>
								<div
									class="swiper-button-next"
									slot="button-next"
								>
									<span class="material-icons"
										>arrow_forward_ios</span
									>
								</div>
							</swiper>
						</div>
					</li>
				</ul>
			</nav>
			<!-- buttons -->
			<div class="btn-area">
				<!-- 검색 버튼 -->
				<el-button
					type="icon-only white"
					class="btn-search"
					@click="openSearch()"
				>
					<span class="custom-icon-search"></span>
				</el-button>
				<!-- 언어 (추후 다국어 기능 완료 시 오픈)-->
				<el-select
					class="el-select-lang"
					v-model="langOptionsValue"
					placeholder="언어"
					popper-class="el-select-lang-popper"
					:popper-append-to-body="true"
				>
					<el-option
						v-for="locale in availableLocales"
						:key="locale.code"
						:label="locale.name"
						:value="locale.code"
					></el-option>
				</el-select>
				<!-- 사이트맵 버튼 -->
				<el-button
					type="primary"
					class="btn-sitemap"
					round
					@click="sitemapVisible = true"
				>
					<span class="material-icons">clear_all</span>
					<span class="txt">SITE MAP</span>
				</el-button>
			</div>
		</div>
		<!-- 통합검색창 -->
		<div class="top-search-area">
			<!-- 검색창 -->
			<div class="search-box">
				<span class="icon custom-icon-search"></span>
				<el-input
					title="통합검색창"
					ref="search"
					:placeholder="$t('integratedSearch_inputSearch')"
					v-model="topSearchInput"
					clearable
					@keypress.enter.native="integratedSearch"
				></el-input>
				<el-button
					type="icon-only"
					class="btn-close-search"
					@click="closeSearch()"
				>
					<span class="icon material-icons">clear</span>
				</el-button>
			</div>
			<!-- 빠른링크 -->
			<div class="quick-link-box" v-if="false">
				<span class="label label-xsm label-black">{{
					$t("integratedSearch_quickLink")
				}}</span>
				<div class="mob-scroll-area">
					<ul class="quick-link-list">
						<li v-for="(item, index) in quickLinkList" :key="index">
							<a href="#" v-text="item.linkTitle"></a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 사이트맵 -->
		<transition name="fade">
			<div class="sitemap" v-if="sitemapVisible">
				<!-- 닫기 버튼 -->
				<el-button
					type="icon-only white"
					class="btn-close"
					@click="sitemapVisible = false"
				>
					<span class="icon material-icons">clear</span>
				</el-button>
				<!-- gnb -->
				<nav class="gnb">
					<ul class="gnb-menu">
						<!-- 1depth -->
						<li
							v-for="(menu, index) in $store.state.menu.menuList"
							:key="index"
						>
							<nuxt-link
								:to="localePath(menu.menuUrl)"
								class="main-menu-link"
								v-text="menu.menuName"
							></nuxt-link>
							<!-- 2depth -->
							<div
								class="gnb-sub-menu"
								v-show="menu.sitemapSubMenuVisible"
							>
								<!-- scroll area -->
								<div
									v-bar="{ preventParentScroll: true }"
									class="scroll-element"
								>
									<!-- el1 -->
									<div>
										<!-- el2 -->
										<div
											class="list-item"
											v-for="(subMenu,
											index) in menu.subMenuList"
											:key="index"
										>
											<nuxt-link
												:to="
													localePath(
														subMenu.subMenuUrl
													)
												"
												class="sub-menu-link"
												v-text="subMenu.subMenuName"
											></nuxt-link>
										</div>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</nav>
			</div>
		</transition>
	</header>
</template>
<script>
// 로고 이미지
import defaultLogo from "@/assets/images/logo/logo_am_white.png";
import onScrollLogo from "@/assets/images/logo/logo_am_blue.png";
import { mapActions, mapGetters } from "vuex";
import Cookie from "js-cookie";

export default {
	props: {},
	data() {
		return {
			// sitemap
			sitemapVisible: false,

			// sitemap submenu
			// sitemapSubMenu: true,

			// scroll
			scroll: false,

			// mouseover
			menuMouseOver: false,

			// 서브메뉴 스와이퍼
			subMenuSwiperOption: {
				slidesPerView: "auto",
				spaceBetween: 0,
				// slidesPerGroup: '6',
				// centeredSlides: true,
				observer: true,
				observeParents: true,
				navigation: {
					nextEl: ".swiper-button-next",
					prevEl: ".swiper-button-prev"
				}
			},

			// logo img
			logoImg: true,
			defaultImg: defaultLogo,
			onScrollImg: onScrollLogo,

			// header search 관련 클래스
			isMenu: false,
			isSearch: false,

			// search input
			topSearchInput: "",

			// topSearchVisible: false,

			menuList: [],

			// 빠른 링크
			quickLinkList: [
				{
					linkTitle: "BOC"
				},
				{
					linkTitle: "Compression Mold"
				},
				{
					linkTitle: "Full pack TR"
				},
				{
					linkTitle: "Full molding"
				}
			],

			// 언어 선택
			langOptions: [
				{
					value: "KOR",
					label: "KOR"
				},
				{
					value: "ENG",
					label: "ENG"
				},
				{
					value: "CHN",
					label: "CHN"
				}
			],
			langOptionsValue: Cookie.get(this.$amConstant.AM_I18N_COOKIE_KEY),
		};
	},
	created() {
		if (!process.client) {
			return;
		}
		window.addEventListener("resize", this.onResize);
	},
	mounted() {
		if (!process.client) {
			return;
		}
		// 스크롤 관련
		window.addEventListener("scroll", this.onScroll);
	},
	destroyed() {
		if (!process.client) {
			return;
		}
		window.removeEventListener("resize", this.onResize);
	},

	beforeDestroy() {
		if (!process.client) {
			return;
		}
		// 스크롤 관련
		window.removeEventListener("scroll", this.onScroll);
	},
	watch: {
		// 페이지 이동 시
		$route() {
			this.manageStoreData();
			// 사이트맵 닫기
			this.sitemapVisible = false;
		},

		// 모달처럼 열릴 때 스크롤 막기 by lje
		sitemapVisible: function() {
			if (this.sitemapVisible) {
				document.documentElement.style.overflow = "hidden";
				return;
			}
			document.documentElement.style.overflow = "auto";
		},

		langOptionsValue(newVal,currentVal) {
			$nuxt.$i18n.setLocale(newVal);
			// this.setMenuList([]);

			this.setMaterialMenuListEmpty();
			this.setClassificationListEmpty();
			this.$fetch();
			this.$forceUpdate();

			document.body.classList.remove(currentVal);
			document.body.classList.add(newVal);
			// console.log("langOptionsValue", this.langOptionsValue)
		},

		// 메뉴 마우스 오버 시 로고 색 변경
		menuMouseOver() {
			if (!this.scroll) {
				this.logoImg = !this.menuMouseOver;
			}
		}
	},

	async fetch() {

		await this.manageStoreData();

	},
	computed: {
		availableLocales() {
			return this.$i18n.locales;
		}
	},

	methods: {
		//store 이벤트
		...mapActions({
			setMaterialMenuListEmpty : "menu/setMaterialMenuListEmpty",
			setMenuList: "menu/setMenuList",

			setClassificationListEmpty: "classification/setClassificationListEmpty",
			getFunctionListSidebar : "classification/getFunctionListSidebar",

			//menu test
			getMaterialMenuList: "menu/getMaterialMenuList",
			getMaterialMap: "menu/getMaterialMap",

			//
			getMarketList: "classification/getMarketList",
			getFunctionList: "classification/getFunctionList",
		}),

		...mapGetters({
			getMenuList: "menu/menuList",

		}),

		async manageStoreData(){
			await this.getMaterialMenuList();
			await this.getMaterialMap();
			await this.getMarketList();
			await this.checkMenuList();
			await this.getFunctionListSidebar();
			await this.getFunctionList();
		},

		async checkMenuList(){
			//세팅 필요

			let materialMenuList = await this.getMaterialMenuList();
			let productSubMenuList = this.setProductSubMenuList( _.cloneDeep( materialMenuList ) );

			let marketList = await this.getMarketList();
			let marketSubMenuList = this.setMarketSubMenuList( _.cloneDeep( marketList ) );

			let menuList = await this.fillMenuList( productSubMenuList, marketSubMenuList );

			this.menuList = menuList;

			await this.setMenuList( menuList );

			return this.getMenuList();
		},


		// 검색창 열기
		openSearch() {
			this.isMenu = false;
			this.isSearch = true;
			// this.topSearchVisible = true;

			// this.$refs.search.$el.getElementsByTagName("input")[0].focus(); // 자동 포커스 - 동작 안함.. 다시 찾아보기.
			let windowWidth = document.documentElement.clientWidth;

			// 모달처럼 열릴 때 스크롤 막기 by lje
			if (windowWidth <= 1024) {
				document.documentElement.style.overflow = "hidden";
			}
		},

		// 검색창 닫기
		closeSearch() {
			this.isMenu = true;
			this.isSearch = false;
			// this.topSearchVisible = false;
			let windowWidth = document.documentElement.clientWidth;

			// 스크롤 풀기 by lje
			if (windowWidth <= 1024) {
				document.documentElement.style = "auto";
			}
		},

		// 스크롤 관련
		onScroll(e) {
			// let scrollPosition = window.scrollY || document.documentElement.scrollTop;
			// console.log(scrollPosition, headerTop);

			let scrollPosition = document.querySelector("html").scrollTop;
			let headerHeight = document.getElementById("header").scrollHeight;

			// header scroll fixed
			if (scrollPosition > headerHeight) {
				this.scroll = true;
				this.logoImg = false;
			} else {
				this.scroll = false;
				this.logoImg = true;
			}
		},

		/**
		 * 메뉴리스트에 데이터를 채워줍니다.
		 */
		fillMenuList(productSubMenuList, marketSubMenuList) {
			const menuPrefix = this.$urlConstant.MENU_URL_PREFIX;
			const menuSuffix = this.$urlConstant.MENU_URL_SUFFIX;

			const menuList = [
				{
					menuUrl: menuPrefix.USER_INTRO + menuSuffix.INTRO.KCC,
					menuName: this.$t(`user_menu_kccIntro`),

					// sub menu
					subMenuList: [
						{
							subMenuUrl:
								menuPrefix.USER_INTRO + menuSuffix.INTRO.KCC,
							subMenuName: this.$t(`user_common_kccIntro`),

							// detail menu
							detailMenuBox: false,
							detailMenuItem: []
						},
						{
							subMenuUrl:
								menuPrefix.USER_INTRO + menuSuffix.INTRO.AM,
							subMenuName: this.$t(`user_common_kccIntro_kccam`),

							// detail menu
							detailMenuBox: false,
							detailMenuItem: []
						},
						{
							subMenuUrl:
								menuPrefix.USER_INTRO +
								menuSuffix.INTRO.GLOBAL_NETWORK,
							subMenuName: this.$t(`user_common_kccIntro_globalNetwork`),

							// detail menu
							detailMenuBox: false,
							detailMenuItem: []
						},
						{
							subMenuUrl:
								menuPrefix.USER_INTRO +
								menuSuffix.INTRO.NEWSROOM.LIST,
							subMenuName: this.$t(`user_common_kccIntro_newsRoom`),

							// detail menu
							detailMenuBox: false,
							detailMenuItem: []
						}
					],

					// sitemap submenu
					sitemapSubMenuVisible: true
				},
				{
					menuUrl: menuPrefix.USER_PRODUCT + menuSuffix.PRODUCT.MAIN,
					menuName: this.$t(`user_menu_product`),

					// sub menu
					subMenuList: productSubMenuList,

					// sitemap submenu
					sitemapSubMenuVisible: true
				},
				{
					menuUrl: menuPrefix.USER_MARKET + menuSuffix.MARKET.MAIN,
					menuName: this.$t(`user_menu_market`),

					// sub menu
					subMenuList: marketSubMenuList,

					// sitemap submenu
					sitemapSubMenuVisible: true
				},
				{
					menuUrl:
						menuPrefix.USER_PRODUCT_SUPPORT +
						menuSuffix.PRODUCT_SUPPORT.INTEGRATED_SEARCH,
					menuName: this.$t(`user_menu_productSupport`),

					// sub menu
					subMenuList: [
						{
							subMenuUrl:
								menuPrefix.USER_PRODUCT_SUPPORT +
								menuSuffix.PRODUCT_SUPPORT.INTEGRATED_SEARCH,
							subMenuName:
								this.$t(`integratedSearch_tit`),

							// detail menu
							detailMenuBox: false,
							detailMenuItem: []
						},
						{
							subMenuUrl:
								menuPrefix.USER_PRODUCT_SUPPORT +
								menuSuffix.PRODUCT_SUPPORT.TECHNICAL_SUPPORT,
							subMenuName:
								this.$t(`integratedSearch_tecSupport_tit`),

							// detail menu
							detailMenuBox: false,
							detailMenuItem: []
						},
						{
							subMenuUrl:
								menuPrefix.USER_PRODUCT_SUPPORT +
								menuSuffix.PRODUCT_SUPPORT.PRODUCT_CATALOG,
							subMenuName:
								this.$t(`productSupport_productCatalog_tit`),

							// detail menu
							detailMenuBox: false,
							detailMenuItem: []
						}
					],

					// sitemap submenu
					sitemapSubMenuVisible: true
				},
				{
					menuUrl:
						menuPrefix.USER_CUSTOMER_SUPPORT +
						menuSuffix.CUSTOMER_SUPPORT.INQUIRY,
					menuName: this.$t(`user_menu_customerSupport`),

					// sub menu
					subMenuList: [
						{
							subMenuUrl:
								menuPrefix.USER_CUSTOMER_SUPPORT +
								menuSuffix.CUSTOMER_SUPPORT.INQUIRY,
							subMenuName: this.$t(`user_customerSupportInquiry_swal_inquiry`),

							// detail menu
							detailMenuBox: false,
							detailMenuItem: []
						},
						{
							subMenuUrl:
								menuPrefix.USER_CUSTOMER_SUPPORT +
								menuSuffix.CUSTOMER_SUPPORT.CONTACT,
							subMenuName: this.$t(`user_customerSupportContact_header_tit`),

							// detail menu
							detailMenuBox: false,
							detailMenuItem: []
						}
					],

					// sitemap submenu
					sitemapSubMenuVisible: true
				}
			];

			return menuList;
		},

		/**
		 * 제품 서브메뉴 리스트를 만들어줍니다.
		 */
		setProductSubMenuList(data) {
			let productSubMenuList = [];
			let materialMap = {};
			let _self = this;

			_.each(data, function(item) {
				let detailMenuItem = [],
					productDetailMenuObject = {},
					applicationDetailMenuObject = {};

				if (item.productClassificationList.length > 0) {
					productDetailMenuObject = _self.setProductDetailMenuObject(
						item.productClassificationList,
						item.oid
					);
					detailMenuItem.push(productDetailMenuObject);
				}

				if (item.applicationList.length > 0) {
					applicationDetailMenuObject = _self.setApplicationDetailMenuObject(
						item.applicationList,
						item.oid
					);
					detailMenuItem.push(applicationDetailMenuObject);
				}

				const subMenuUrl =
					_self.$store.$urlConstant.MENU_URL_PREFIX.USER_PRODUCT +
					_self.$store.$urlConstant.MENU_URL_SUFFIX.PRODUCT
						.MATERIAL_VIEW;
				let subMenuObject = {
					subMenuUrl: subMenuUrl + "?depth=1&partOid=" + item.oid,
					subMenuName: item.name,
					detailMenuBox: false,
					detailMenuItem: detailMenuItem,
					mainImg : item.mainImg,
				};

				materialMap[item.oid] = {
					materialClass: _.kebabCase(_.lowerCase(item.name)),
					materialName: item.name
				};
				productSubMenuList.push(subMenuObject);
			});

			return productSubMenuList;
		},

		/**
		 *제품 상세메뉴 제품구분 리스트를 만들어줍니다.
		 */
		setProductDetailMenuObject(productClassificationList, partOid) {
			let productDetailMenuObject = {
				menuTitle: this.$store.$amConstant.CLASSIFICATION
					.PRODUCT_CLASSIFICATION,
				detailMenuList: []
			};

			if (productClassificationList.length === 0) {
				return {};
			}

			let detailMenuList = [];
			const url =
				this.$store.$urlConstant.MENU_URL_PREFIX.USER_PRODUCT +
				this.$store.$urlConstant.MENU_URL_SUFFIX.PRODUCT
					.CLASSIFICATION_VIEW;
			_.each(productClassificationList, function(item) {
				let detailMenuObject = {
					detailMenuUrl : url + "?depth=2&partOid=" + partOid + "&classificationOid=" + item.oid + "&classification=product",
					detailMenuName: item.name
				};

				detailMenuList.push(detailMenuObject);
			});

			productDetailMenuObject.detailMenuList = detailMenuList;

			return productDetailMenuObject;
		},

		/**
		 * 제품 상세메뉴 Application 리스트를 만들어줍니다.
		 *
		 * @param applicationList
		 * @returns {{}}
		 */
		setApplicationDetailMenuObject(applicationList, partOid) {
			let applicationDetailMenuObject = {
				menuTitle: this.$store.$amConstant.CLASSIFICATION.APPLICATION,
				detailMenuList: []
			};

			if (applicationList.length === 0) {
				return {};
			}

			let detailMenuList = [];
			const url =
				this.$store.$urlConstant.MENU_URL_PREFIX.USER_PRODUCT +
				this.$store.$urlConstant.MENU_URL_SUFFIX.PRODUCT
					.APPLICATION_VIEW;
			_.each(applicationList, function(item) {
				let detailMenuObject = {
					detailMenuUrl: url + "?depth=2&partOid=" + partOid + "&classificationOid=" + item.oid + "&classification=application",
					detailMenuName: item.name
				};

				detailMenuList.push(detailMenuObject);
			});

			applicationDetailMenuObject.detailMenuList = detailMenuList;

			return applicationDetailMenuObject;
		},

		/**
		 * 마켓 서브메뉴 리스트
		 * @param data
		 * @returns {[]}
		 */
		setMarketSubMenuList(data) {
			let marketSubMenuList = [];
			let _self = this;

			_.each(data, function(item) {
				let detailMenuItem = [];

				let subMenuObject = {
					subMenuUrl: item.linkUrl,
					subMenuName: item.title,
					detailMenuBox: false,
					detailMenuItem: detailMenuItem
				};

				marketSubMenuList.push(subMenuObject);
			});

			return marketSubMenuList;
		},

		// 반응형 이벤트
		onResize(e) {
			let windowWidth = document.documentElement.clientWidth;

			// 모바일 사이즈일 경우 서브메뉴 가리기 (메인메뉴 클릭 시 밑으로 나오게 변경.)
			// if (windowWidth <= 1024) {
			//     this.menuList.forEach((item, index) => {
			//         item.sitemapSubMenuVisible = false;
			//     });
			// } else {
			//     this.menuList.forEach((item, index) => {
			//         item.sitemapSubMenuVisible = true;
			//     });
			// }
		},

		integratedSearch() {
			const _self = this;

			if (_self.$common.isEmpty(_self.topSearchInput)) {
				this.$common.confirmSwal( this.$t( `user_common_confirmSwal_noSearchInput` ), this.$t( `user_common_confirmSwal_noSearchInput_desc` ), "warning" );
				return;
			}

			_self.$router.push(
				this.localePath({
					path:
						_self.$urlConstant.MENU_URL_PREFIX
							.USER_PRODUCT_SUPPORT +
						_self.$urlConstant.MENU_URL_SUFFIX.PRODUCT_SUPPORT
							.INTEGRATED_SEARCH,
					query: {
						searchInput: _self.topSearchInput,

						depth: 1,
						productListPage: 1,
						applicationListPage: 1,
						marketListPage: 1,
						functionListPage: 1
					}
				})
			);
		}
	}
};
</script>
<style></style>
