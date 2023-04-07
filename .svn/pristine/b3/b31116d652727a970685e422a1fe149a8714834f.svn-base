<template>
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
}
</script>
<style scoped>
</style>
