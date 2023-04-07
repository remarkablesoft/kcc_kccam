<template>
	<div class="container">
		<ul>
			<!--            <li>-->
			<!--                <nuxt-link to="/kccam/user/main/user_main">-->
			<!--                    <h1>KCC AM 사용자 페이지(퍼블리싱 완료)</h1>-->
			<!--                </nuxt-link>-->
			<!--            </li>-->
			<!--            <li>-->
			<!--                <nuxt-link to="/kccam/manager/login/manager_login">-->
			<!--                    <h1>KCC AM 관리자 페이지(퍼블리싱 작업중)</h1>-->
			<!--                </nuxt-link>-->
			<!--            </li>-->
		</ul>
	</div>
</template>
<script>
export default {
	beforeCreate() {
		if ( !process.client ) {
			return;
		}


		const url = window.location.hostname


		if ( -1 < url.indexOf( "kccmaterials.com" ) ) {
			$nuxt.$i18n.setLocale( 'en' );
		}
		else if ( -1 < url.indexOf( "kccmaterials.co.kr" ) ) {
			$nuxt.$i18n.setLocale( 'kr' );
		}
		else {
			$nuxt.$i18n.setLocale( 'kr' );
		}

		// 관리자 페이지의 언어는 한국어로 고정
		if ( -1 < url.indexOf( "/kccam/manager" ) ) {
			$nuxt.$i18n.setLocale( 'ko' );
		}

		const redirectUrl = "/kccam/user/main/user_main"
		$nuxt.$router.push( $nuxt.localePath( redirectUrl ) );

	},
};
</script>
<style></style>
