<template>
    <div class="breadcrumb" :class="{ sticky: scroll }">
        <div class="inner">
            <!-- element ui NavMenu -->
            <el-menu :default-active="activeIndex" class="breadcrumb-menu-list" mode="horizontal" @select="handleSelect"
                     text-color="#808080" active-text-color="#5551f7">
                <!-- 홈 메뉴 -->
                <el-menu-item index="0">
                    <nuxt-link :to="localePath('/kccam/user/main/user_main')">
                        <span class="material-icons">home</span>
                    </nuxt-link>
                </el-menu-item>

                <!-- 1depth 메뉴 -->
                <el-submenu :index="menu.menuIndex" popper-class="breadcrumb-menu-popper" v-for="(menu, index) in breadMenuList">
                    <!-- 현재 위치 -->
                    <template slot="title">
                        <nuxt-link :to=localePath(menu.titleUrl) v-text="menu.title"></nuxt-link>
                    </template>

                    <!-- 메뉴 -->
                    <el-menu-item :index="subMenu.itemIndex" v-for="subMenu in menu.subMenuList">
                        <nuxt-link :to=localePath(subMenu.itemUrl) v-text="subMenu.itemName"></nuxt-link>
                    </el-menu-item>
                </el-submenu>
            </el-menu>
        </div>
    </div>
</template>

<script>

import Cookie from 'js-cookie'
import { AM_CONSTANT } from "~/constant/amConstant";

export default {
    data () {
        return {

            MENU_1DEPTH_INDEX : 0,      // 1Depth 메뉴 인덱스
            PRODUCT_SUBMENU_INDEX : 1,  // 메뉴구조 리스트에서 Product 메뉴 인덱스
            MATERIAL_VIEW_DEPTH : 1,    // 소재 View Depth
            CLASSIFICATION_MENU_INDEX : 3, // 분류정보 적용 INDEX
            PRODUCT_MENU_INDEX : 4,         // 제품 상세화면 INDEX

            breadMenuList : [],

            // 현재 페이지 표시
            activeIndex : "",

            // scroll
            scroll : false,

            classificationMap : {},
            productList : [],
        };
    },
    watch : {
        $route () {
            this.$fetch();
        }
    },
    mounted () {
        // 스크롤 관련
        if (!process.client) {
            return;
        }
        window.addEventListener( "scroll", this.onScroll );
    },
    beforeDestroy () {
        // 스크롤 관련
        if (!process.client) {
            return;
        }
        window.removeEventListener( "scroll", this.onScroll );
    },
    async fetch() {
        this.breadMenuList = [];
        await this.getClassificationMap();
        await this.getProductSubmenuList();
        this.getMenuList();
    },
    methods : {
        handleSelect ( key, keyPath ) {
            // console.log( key, keyPath );
        },

        // 스크롤 관련
        onScroll ( e ) {

            let scrollPosition = document.querySelector( "html" ).scrollTop;
            let subPageDefaultY = 220;

            if ( scrollPosition >= subPageDefaultY ) {
                this.scroll = true;
            }
            else {
                this.scroll = false;
            }
        },

        /**
         * 빈 Param Object를 가져옵니다.
         */
        getEmptyParam() {

            let param = {
                menuNameMap : {},
                menuUrlMap : {},
                menuDepth : 0,
                urlKeyword : "",
                menuIndex : 0,

                isLastIndex : false
            };

            return param;
        },

        /**
         * 소재구분 하위의 Product Classification, Application 리스트를 불러옵니다.
         */
        async getClassificationMap() {

            let keywordIndex = 3;
            let lang = Cookie.get( this.$amConstant.AM_I18N_COOKIE_KEY );
            if ( this.$amConstant.LANG.KO !== lang ) {
                keywordIndex = keywordIndex + 1;
            }

            let menuDepth = this.$route.query.depth;
            let urlKeyword = this.$amCommon.getUrlKeyword( this.$route.path, keywordIndex );

            if ( this.MATERIAL_VIEW_DEPTH >= menuDepth || "product,market,function".indexOf( urlKeyword ) === -1 ) {
                return;
            }

            const classificationUrl = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.GET_BREADCRUMB_MAP;

            let reqParam = {
                oid : this.$route.query.classificationOid,
                partOid : this.$route.query.partOid
            };

            await this.$axios.post( classificationUrl, reqParam ).then( res => {
                this.classificationMap = res.data;
            });

        },

        /**
         * 동일한 분류체계의 제품 리스트를 가져옵니다.
         */
        async getProductSubmenuList() {
            let productOid = this.$route.query.productOid;
            if ( this.$common.isEmpty( productOid ) ) {
                return;
            }

            let classificationOid = this.$route.query.classificationOid;
            let classification = this.$route.query.classification;

            let reqParam = {
                targetOid : classificationOid,
                targetObject : this.$amConstant.OBJECT_TYPE_MAP[classification]
            };

            let productListUrl = this.$urlConstant.API_URL_PREFIX.PRODUCT + this.$urlConstant.API_URL_SUFFIX.PRODUCT.VIEW_LIST_All;
            await this.$axios.post( productListUrl, reqParam ).then( res => {
                this.productList = res.data;
            });
        },

        /**
         * 메뉴리스트를 가져옵니다.
         *
         */
        getMenuList () {
	        let menuDepth = this.$route.query.depth;

	        if ( this.$common.isEmpty( menuDepth ) ) {
                return;
            }

            const _self = this;
            let paramArr = [];

            for ( let i = 0; i <= menuDepth; i++ ) {
                // 2depth 메뉴 이후로는 TB_CATEGORY 데이터를 추가해줍니다.
                if ( i > 1 ) {
                    continue;
                }

                let param = _self.getEmptyParam();
                param.menuDepth = Number( menuDepth );
                param.menuIndex = Number( menuDepth ) + i;
                paramArr.push( _self.setMenuParam( param, i ) );
            }

            // TB_CATEGORY 데이터를 추가합니다.
            if ( this.$common.isNotEmpty( this.classificationMap ) ) {
                paramArr = _self.setClassificationParam( paramArr );
            }

            // 제품 리스트 데이터를 추가합니다.
            if ( this.productList.length > 0 ) {
                paramArr = _self.setProductListParam( paramArr );
            }
	        // Breadcrumb 포맷으로 가공하여 추가합니다.
            paramArr[paramArr.length - 1].isLastIndex = true;
            _.each( paramArr, function( param, index ) {
                _self.addBreadcrumbMenu( param );
            });

        },



        /**
         * 메뉴를 만들기 위한 파라미터를 설정합니다.
         */
        setMenuParam ( param, index ) {
	        let keywordIndex = 3;
            let lang = Cookie.get( this.$amConstant.AM_I18N_COOKIE_KEY );
            if ( this.$amConstant.LANG.KO !== lang ) {
                keywordIndex = keywordIndex + 1;
            }

            let urlKeyword = this.$amCommon.getUrlKeyword( this.$route.path, keywordIndex );

	        if ( this.MENU_1DEPTH_INDEX === index ) {
            	const menu10DepthNameMap = {
		            intro: this.$t(`user_menu_kccIntro`),
		            product: this.$t(`user_menu_product`),
		            market: this.$t(`user_menu_market`),
		            productSupport: this.$t(`user_menu_productSupport`),
		            customerSupport: this.$t(`user_menu_customerSupport`),
	            };
                param.menuNameMap = menu10DepthNameMap;
                param.menuUrlMap = this.$menuConstant.MENU_1DEPTH_URL_MAP;
                param.urlKeyword = urlKeyword;
            }
            else {
                this.setDeepDepthMenuParam( param, urlKeyword, keywordIndex );
            }

            return param;
        },

        /**
         * 1Depth 보다 깊은 메뉴 파리미터를 설정해줍니다.
         */
        setDeepDepthMenuParam( param, urlKeyword, keywordIndex) {
	        const menuIntroNameMap = {
				intro_kcc: this.$t(`user_menu_kccIntro`),
				intro_am: this.$t(`user_common_kccIntro_kccam`),
				intro_globalNetwork: this.$t(`user_common_kccIntro_globalNetwork`),
				newsroom_list: this.$t(`user_common_kccIntro_newsRoom`),
			};

			const menuProductSupportNameMap = {
				productSupport_integratedSearch: this.$t(`integratedSearch_tit`),
				productSupport_technicalSupport: this.$t(`integratedSearch_tecSupport_tit`),
				productSupport_productCatalog: this.$t(`productSupport_productCatalog_tit`),
			};

			const menuCustomerSupportNameMap = {
				customerSupport_inquiry: this.$t(`user_customerSupportInquiry_header_tit`),
				customerSupport_contact: this.$t(`user_customerSupportContact_header_tit`),
			};

            switch ( urlKeyword ) {
                case "intro" : {
                    param.menuNameMap = menuIntroNameMap;
                    param.menuUrlMap = this.$menuConstant.MENU_INTRO_URL_MAP;
                    param.urlKeyword = this.$amCommon.getUrlKeyword( this.$route.path, keywordIndex + 1 );
                    if ( "newsroom" === param.urlKeyword ) {
                        param.urlKeyword = this.$amCommon.getUrlKeyword( this.$route.path, keywordIndex + 2 );
                    }

                    break;
                }
                case "product" : {
                    if ( this.MATERIAL_VIEW_DEPTH > param.menuDepth ) {
                        break;
                    }

                    if ( "market,function".indexOf( this.$route.query.classification ) !== -1 ) {
                        this.setMarketFunctionMainParam( param );
                    }
                    else if ( this.CLASSIFICATION_MENU_INDEX >= param.menuIndex || this.PRODUCT_MENU_INDEX >= param.menuIndex ) {
                        this.setMaterialMap( param );
                    }
                    break;
                }
                case "market" : {
                    this.setMarketFunctionMainParam( param );
                    break;
                }
                case "productSupport" : {
                    param.menuNameMap = menuProductSupportNameMap;
                    param.menuUrlMap = this.$menuConstant.MENU_PRODUCT_SUPPORT_URL_MAP;
                    param.urlKeyword = this.$amCommon.getUrlKeyword( this.$route.path, keywordIndex + 1 );
                    break;
                }
                case "customerSupport" : {
                    param.menuNameMap = menuCustomerSupportNameMap;
                    param.menuUrlMap = this.$menuConstant.MENU_CUSTOMER_SUPPORT_URL_MAP;
                    param.urlKeyword = this.$amCommon.getUrlKeyword( this.$route.path, keywordIndex + 1 );
                    break;
                }
            }

        },

        /**
         * 소재구분 리스트를 만들어줍니다.
         */
        setMaterialMap ( param ) {

            const _self = this;

            let menuNameObj = {};
            let menuUrlObj = {};
            let materialList = this.$store.state.menu.menuList[this.PRODUCT_SUBMENU_INDEX].subMenuList;

            _.each( materialList, function( menu ) {
                let oid = _self.$common.getUrlParamValue( menu.subMenuUrl, "partOid" );
                menuNameObj[oid] = menu.subMenuName;
                menuUrlObj[oid] = menu.subMenuUrl;
            });

            param.menuNameMap = menuNameObj;
            param.menuUrlMap = menuUrlObj;
            param.urlKeyword = _self.$route.query.partOid;
        },

        /**
         * 마켓/Function 메인화면 파라미터를 만들어줍니다.
         */
        setMarketFunctionMainParam( param ) {

            let menuNameObj = {};
            let menuUrlObj = {};

            menuNameObj["market"] = this.$t(`user_marketMain`);
            menuNameObj["function"] = this.$t(`user_functionMain`);
            menuUrlObj["market"] = this.$urlConstant.MENU_URL_PREFIX.USER_MARKET + this.$urlConstant.MENU_URL_SUFFIX.MARKET.MARKET_LIST;
            menuUrlObj["function"] = this.$urlConstant.MENU_URL_PREFIX.USER_MARKET + this.$urlConstant.MENU_URL_SUFFIX.MARKET.FUNCTION_LIST;

            param.menuNameMap = menuNameObj;
            param.menuUrlMap = menuUrlObj;
            param.urlKeyword = this.$route.query.classification;

        },

        /**
         * 분류정보를 설정합니다.
         */
        setClassificationParam( paramArr ) {

            const _self = this;
            let classificationMapIndex = 0;
            let classificationParamArr = [];
            let urlKeywordOid = "";

            _.each( _self.classificationMap, function( classificationList, key ) {

                let param = _self.getEmptyParam();
                let menuNameObj = {};
                let menuUrlObj = {};

                param.menuDepth = Number( _self.$route.query.depth );
                param.menuIndex = param.menuDepth + classificationMapIndex + 2;

                _.each( classificationList, function( item, index ) {
                    menuNameObj[item.oid] = item.name;

                    let url = "";
                    let classification = _self.$route.query.classification;
                    if ( "product" === classification ) {
                        url = _self.$urlConstant.MENU_URL_PREFIX.USER_PRODUCT + _self.$urlConstant.MENU_URL_SUFFIX.PRODUCT.CLASSIFICATION_VIEW;
                    }
                    else if ( "application" === classification ) {
                        url = _self.$urlConstant.MENU_URL_PREFIX.USER_PRODUCT + _self.$urlConstant.MENU_URL_SUFFIX.PRODUCT.APPLICATION_VIEW;
                    }
                    else if ( "market" === classification ) {
                        url = _self.$urlConstant.MENU_URL_PREFIX.USER_MARKET +  _self.$urlConstant.MENU_URL_SUFFIX.MARKET.MARKET_VIEW;
                    }
                    else if ( "function" === classification ) {
	                    url = _self.$urlConstant.MENU_URL_PREFIX.USER_MARKET +  _self.$urlConstant.MENU_URL_SUFFIX.MARKET.FUNCTION_VIEW;
                    }

					if ( "function" !== classification ) {
	                    url += "?depth=2";
					}
	                if ( _self.$common.isNotEmpty( item.partOid ) && "function" !== classification ) {
	                    url += "&partOid=" + item.partOid;
	                }
	                url += "&classificationOid=" + item.oid + "&classification=" + classification;

                    menuUrlObj[item.oid] = url;

                    if ( _self.$route.query.classificationOid === item.oid ) {
                        urlKeywordOid = item.oid;
                    }
                });

                param.urlKeyword = urlKeywordOid;
                param.menuNameMap = menuNameObj;
                param.menuUrlMap = menuUrlObj;

                classificationParamArr.unshift( param );

                classificationMapIndex++;
                urlKeywordOid = key;
            });

            paramArr = [...paramArr, ...classificationParamArr];
            return paramArr;
        },

        /**
         * 제품 상세화면에서 제품목록을 설정해줍니다.
         */
        setProductListParam ( paramArr ) {

            const _self = this;
            let param = _self.getEmptyParam();
            let menuNameObj = {};
            let menuUrlObj = {};

            param.menuDepth = Number( _self.$route.query.depth );
            param.menuIndex = param.menuDepth + paramArr.length;

            _.each( this.productList, function( product, index ) {
                menuNameObj[product.oid] = product.name;

                let url = _self.$urlConstant.MENU_URL_PREFIX.USER_PRODUCT + _self.$urlConstant.MENU_URL_SUFFIX.PRODUCT.PRODUCT_VIEW;
                url += "?depth=3" + "&partOid=" + _self.$route.query.partOid + "&classificationOid=" + _self.$route.query.classificationOid
	                + "&classification=" + _self.$route.query.classification + "&productOid=" + product.oid;

                menuUrlObj[product.oid] = url;
            });

            param.urlKeyword = _self.$route.query.productOid;
            param.menuNameMap = menuNameObj;
            param.menuUrlMap = menuUrlObj;

            paramArr.push( param );

            return paramArr;
        },

        /**
         * Breadcrumb 포맷의 메뉴를 만들어줍니다.
         *
         * @param menuList
         */
        addBreadcrumbMenu ( param ) {

            if ( "newsroom_view" === param.urlKeyword ) {
	            param.urlKeyword = "newsroom_list";
            }

            let langCookie = Cookie.get( this.$amConstant.AM_I18N_COOKIE_KEY );
            let menuUrl = param.menuUrlMap[param.urlKeyword];
            if ( this.$amConstant.LANG.KO !== langCookie ) {
                menuUrl = menuUrl.replace( "/kccam/", "/" + langCookie + "/kccam/" );
            }

	        const menuName = param.menuNameMap[param.urlKeyword];
            const menuIndex = String( param.menuIndex );

	        const subMenuList = this.setSubMenuList( param.menuNameMap, param.menuUrlMap, menuIndex, param.isLastIndex );

            let menuObject = {
                menuIndex : menuIndex,
                title : menuName,
                titleUrl : menuUrl,
                subMenuList : subMenuList,
            }

            this.breadMenuList.push( menuObject );

        },

        /**
         * breadcrumb에 들어갈 subMenu 목록을 만들어줍니다.
         *
         * @param menuNameMap
         * @param menuUrlMap
         * @param menuIndex
         * @returns {[]}
         */
        setSubMenuList ( menuNameMap, menuUrlMap, menuIndex, isLastIndex ) {

            let subMenuList = [];
            let index = 0;
            const _self = this;

            let langCookie = Cookie.get( this.$amConstant.AM_I18N_COOKIE_KEY );

            _.each( menuNameMap, function( val, key ) {
                index++;
                let url =  menuUrlMap[key];

	            if ( _self.$amConstant.LANG.KO !== langCookie ) {
                    url = url.replace( "/kccam/", "/" + langCookie + "/kccam/" );
                }

                let subMenuObject = {
                    itemIndex : menuIndex + "-" + index,
                    itemName : val,
                    itemUrl : url
                };

	            if ( isLastIndex && _self.$route.fullPath.indexOf( menuUrlMap['newsroom_view']) !== -1) {
	                _self.activeIndex = subMenuObject.itemIndex;
	            }

	            if ( isLastIndex && _self.$route.fullPath.indexOf( subMenuObject.itemUrl ) !== -1 ) {
                    _self.activeIndex = subMenuObject.itemIndex;
	            }
                subMenuList.push( subMenuObject );
            });

            return subMenuList;
        },

    },

};
</script>

<style></style>
