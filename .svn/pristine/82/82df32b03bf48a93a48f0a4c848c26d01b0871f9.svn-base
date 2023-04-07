<template>
    <footer class="footer">
        <div class="footer-top">
            <div class="inner">
                <ul class="policy-link-list">
                    <!-- <li>
                        <nuxt-link to="" class="link">이용약관</nuxt-link>
                    </li> -->
                    <li>
                        <nuxt-link :to="localePath('/kccam/user/policy/policy_privacy')" class="link">{{ $t("user_common_footer_policyPrivacy") }}</nuxt-link>
                    </li>
                </ul>

                <div class="dropdown-group">
                    <!-- 패밀리 사이트 -->
                    <div class="family-site">
                        <el-button type="black" class="btn-family-site" @click="slideToggle()">
                            <span class="txt">{{ $t("user_common_footer_familySite") }}</span>
                            <span class="material-icons" v-text="familySiteBtnIconToggle || 'add'"></span>
                        </el-button>

                        <!-- 박스 -->
                        <slide-up-down :active="active" :duration="700" class="family-site-box">
                            <div class="inner-box">
                                <!-- 닫기 버튼 -->
                                <el-button type="primary" class="btn-close-box" @click="active = false">
                                    <span class="material-icons">clear</span>
                                </el-button>

                                <!-- 내용 -->
                                <div class="tit-area">
                                    <span class="tit">{{ $t("user_common_footer_familySite") }}</span>
                                    <p class="sub-txt">
                                        {{ $t("user_common_footer_familySite_subTxt1") }}<br />
                                        {{ $t("user_common_footer_familySite_subTxt2") }}
                                    </p>
                                </div>

                                <div class="family-site-link">
                                    <!-- 링크 목록 -->
                                    <ul class="family-site-list">
	                                    <li v-show="currentLang === $amConstant.LANG.KO">
		                                    <a href="https://www.kccworld.co.kr/main.do" target="_blank">{{ $t("user_common_footer_familySite_kccCorporation") }}</a>
	                                    </li>
	                                    <li v-show="currentLang != $amConstant.LANG.KO">
		                                    <a href="https://www.kccworld.co.kr/eng/main.do" target="_blank">{{ $t("user_common_footer_familySite_kccCorporation") }}</a>
	                                    </li>
                                        <li>
                                            <a href="https://www.facebook.com/kccstory/" target="_blank">{{ $t("user_common_footer_familySite_facebook") }}</a>
                                        </li>
                                        <li>
                                            <a href="https://blog.naver.com/kcc_world" target="_blank">{{ $t("user_common_footer_familySite_blog") }}</a>
                                        </li>
                                        <li>
                                            <a href="http://www.kccrefinish.co.kr/" target="_blank">{{ $t("user_common_footer_familySite_automobileMt") }}</a>
                                        </li>
                                        <li>
                                            <a href="https://www.kccworld.co.kr/kccmarine/main.do" target="_blank">{{ $t("user_common_footer_familySite_shipPaint") }}</a>
                                        </li>
                                        <li>
                                            <a href="http://www.kccegis.com/main/main.asp" target="_blank">{{ $t("user_common_footer_familySite_basketballTeam") }}</a>
                                        </li>
                                        <li>
                                            <a href="https://kcccolorndesign.com/" target="_blank">{{ $t("user_common_footer_familySite_colorDesign") }}</a>
                                        </li>
                                    </ul>

                                    <!-- 링크 목록 -->
                                    <ul class="family-site-list">
                                        <li>
                                            <a href="http://www.kccglass.co.kr/main.do" target="_blank">{{ $t("user_common_footer_familySite_glass") }}</a>
                                        </li>
                                        <li>
                                            <a href="https://www.homecc.co.kr/" target="_blank">{{ $t("user_common_footer_familySite_homecc") }}</a>
                                        </li>
                                        <li>
                                            <a href="https://www.kccworld.net/main.do" target="_blank">{{ $t("user_common_footer_familySite_construction") }}</a>
                                        </li>
                                        <li>
                                            <a href="https://www.kccsilicone.com/main.do" target="_blank">{{ $t("user_common_footer_familySite_kccsilicone") }}</a>
                                        </li>
                                        <li>
                                            <a href="https://www.kccgolf.co.kr/" target="_blank">{{ $t("user_common_footer_familySite_genmgang") }}</a>
                                        </li>
                                        <li>
                                            <a href="https://www.kcc-basildon.com/" target="_blank">{{ $t("user_common_footer_familySite_basildon") }}</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </slide-up-down>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="inner">
                <!-- logo -->
                <div class="logo">
                    <img src="@/assets/images/logo/logo_kcc_gray.png" alt="KCC 로고" />
                </div>
                <div class="txt">
                    <address class="address">{{ $t("user_common_footer_address") }}</address>
                    <p class="copyright">{{ $t("user_common_footer_copyright") }}</p>
                </div>
            </div>
        </div>
    </footer>
</template>
<script>
import Cookie from "js-cookie";

export default {
    data() {
        return {
            // slide 관련
            active: false,

	        // 현재 페이지 언어
	        currentLang : Cookie.get( this.$amConstant.AM_I18N_COOKIE_KEY ),
        };
    },
    watch: {
    	$route() {
		    this.currentLang = Cookie.get( this.$amConstant.AM_I18N_COOKIE_KEY );
	    }
    },
	mounted() {
	},
	fetch() {
    },
    computed: {
        // 패밀리 사이트 버튼 아이콘 토글
        familySiteBtnIconToggle: function() {
            if (this.active) {
                return "remove";
            }

            return "add";
        },
    },

    methods: {
        // slide toggle
        slideToggle() {
            this.active = !this.active;
        },
    },
};
</script>
