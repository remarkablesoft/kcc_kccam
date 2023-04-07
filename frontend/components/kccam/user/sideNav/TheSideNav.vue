<template>
	<div>
		<!-- **모바일** Contact 오픈 버튼 -->
		<div class="floating-btn mob-visible btn-open" @click="openSideNav()">
			<button type="button" class="btn">
                <span class="material-icons">
                    <!-- graphic_eq -->
                    list
                </span>
			</button>
		</div>
		<div class="side-nav-container" :class="{ active: scroll, open: clickOpen }" @click.self="closeSideNav()">
			<aside class="side-nav">
				<!-- 모바일에서만 사용되는 X 버튼 -->
				<div class="btn-wrap mob-visible">
					<el-button type="icon-only" class="btn-close" @click="closeSideNav()">
						<span class="icon material-icons">clear</span>
					</el-button>
				</div>
				<!-- scroll area -->
				<div v-bar="{ preventParentScroll: true }" class="scroll-element">
					<!-- el1 -->
					<div>
						<!-- el2 -->
						<el-menu :default-openeds="['1','2']"
						         class="el-menu-vertical-demo"
						         @open="handleOpen"
						         @close="handleClose"
						>
							<el-submenu v-for="(item, index) in marketSideNav"
							            :index="item.index"
							            :key="item.index"
							>
								<template slot="title">
									<span v-text="item.title"></span>
								</template>
								<el-submenu v-for="(menuItem, menuIndex) in item.innerMenuList"
								            :index="'menuItem' + menuItem.title + menuItem.oid"
								            :key="'menuItem' + menuItem.title + menuItem.oid"
								            :class="getOpenedClass(menuItem)"
								>
									<template slot="title">
										<span v-text="menuItem.title" @click="goPage(menuItem.linkUrl)"></span>
									</template>
									<el-menu-item v-for="(detailItem, detailItemIndex) in menuItem.detailList"
									              :index="'detailItem' + detailItem.oid"
									              :key="'detailItem' + detailItem.oid"
									              @click="goPage(detailItem.linkUrl)"
									              :class="getOpenedClass(detailItem)"
									>
										<span v-text="detailItem.title"></span>
									</el-menu-item>
									<!-- Semiconductor -->
								</el-submenu>
							</el-submenu>
						</el-menu>
					</div>
				</div>
			</aside>
		</div>
	</div>
</template>
<script>
export default {
	data() {
		return {
			// scroll
			scroll : false,

			// open
			clickOpen : false,

			// side navigation menu
			marketSideNav : [
				// Market menu
				{
					index         : "1",
					title         : "Market",
					innerMenuList : [],
				},

				// Functions menu
				{
					index         : "2",
					title         : "Function",
					innerMenuList : [],
				},
			],

			menuDefaultOpenIndex : [ "1", "2" ],
		};
	},

	computed : {
		functionListSidebar() {
			return this.$store.state.classification.functionListSidebar;
		},

		marketList() {
			return this.$store.state.classification.marketList;
		},

		functionList() {
			return this.$store.state.classification.functionListSidebar;
		},
	},

	async mounted() {
		// this.closeSideNav();

		if ( !process.client ) {
			return;
		}

		// 스크롤 관련
		window.addEventListener( "scroll", this.onScroll );

		this.marketSideNav[ 0 ].innerMenuList = this.marketList;
		this.marketSideNav[ 1 ].innerMenuList = this.functionList;
	},

	watch : {
		marketList : function () {
			// console.log( "marketLIst" );
			if ( this.$common.isEmpty( this.marketList ) ) {
				return;
			}
			this.marketSideNav[ 0 ].innerMenuList = this.marketList;

		},

		functionList : function () {
			// console.log( "functionLIst" );
			if ( this.$common.isEmpty( this.functionList ) ) {
				return;
			}
			this.marketSideNav[ 1 ].innerMenuList = this.functionList;
		},

		$route() {
			this.closeSideNav();
		},
	},

	beforeDestroy() {

		if ( !process.client ) {
			return;
		}
		// 스크롤 관련
		window.removeEventListener( "scroll", this.onScroll );
	},
	methods : {
		//store 이벤트
		// ...mapActions( {
		// 	setFunctionListSidebar : "classification/setFunctionListSidebar",
		// } ),

		handleOpen( key, keyPath ) {
			// console.log(key, keyPath);
		},
		handleClose( key, keyPath ) {
			// console.log(key, keyPath);
		},

		// 스크롤 관련
		onScroll( e ) {
			// let scrollPosition = window.scrollY || document.documentElement.scrollTop;

			// let footerHeight = 177; // footer 높이 기준
			let scrollPosition = document.querySelector( "html" ).scrollTop;
			let subPageDefaultY = 220;
			let windowWidth = document.documentElement.clientWidth;

			if ( scrollPosition >= subPageDefaultY && windowWidth >= 1024 ) {
				this.scroll = true;
			}
			else {
				// debugger;
				this.scroll = false;

			}
		},

		openSideNav() {
			this.clickOpen = true;
			let windowWidth = document.documentElement.clientWidth;

			// 모달처럼 열릴 때 스크롤 막기 by lje
			if ( windowWidth <= 1024 ) {
				document.documentElement.style.overflow = "hidden";
			}
		},

		closeSideNav() {
			// console.log("close!!");
			this.clickOpen = false;

			let windowWidth = document.documentElement.clientWidth;

			// 스크롤 풀기 by lje
			if ( windowWidth <= 1024 ) {
				document.documentElement.style = "auto";
			}

			this.$forceUpdate();
		},

		goPage( linkUrl ) {
			if ( this.$common.isEmpty( linkUrl ) ) {
				return;
			}

			this.$router.push( this.localePath( linkUrl ) );

		},

		getOpenedClass( item ) {
			if ( this.$common.isEmpty( item ) ) {
				return null;
			}

			if ( this.$common.isNotEmpty( item.detailList ) ) {

				return this.getOpenedDetailClass( item.detailList );
			}
			else {

				let index = -1 < this.$route.fullPath.indexOf( item.linkUrl );
				return index ? "is-opened" : null;
			}
		},

		getOpenedDetailClass( itemList ) {
			let subResult = this.getSubMenuOpendClass( itemList )

			if ( this.$common.isNotEmpty( subResult ) ) {

				return subResult;
			}
			return null;
		},


		getSubMenuOpendClass( itemList ) {
			if ( this.$common.isEmpty( itemList ) ) {
				return null;
			}

			let subResult;
			itemList.forEach( item => {
				let index = -1 < this.$route.fullPath.indexOf( item.linkUrl );

				if ( index ) {
					subResult = "is-opened";
				}
			} )

			return subResult;
		},
	},
};
</script>
<style></style>
