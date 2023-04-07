//UUID 생성
function getUUID() {
	return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace( /[xy]/g, function ( c ) {
		var r = Math.random() * 16 | 0, v = c == 'x' ? r : ( r & 0x3 | 0x8 );
		return v.toString( 16 );
	} );
}


export default function({ $axios, redirect }) {

    $axios.onRequest(config => {

	    //로딩을 사용하지 않고싶은 axios호출은 param에 isLoading = false로 넘겨주면 됨
	    if (  $nuxt.$common.isNotEmpty(config.data)
		    && false !== config.data.isLoading ) {
		    // axios request시 난수 생성하여 reference로 사용
		    // config 에 값을 넣어 response에서 해당 값을 확인 할 수 있음.
		    // store에 reference값을 넣어 현재 몇 개의 request가 진행중인지 확인 할 수 있음
		    config.aixosReference = getUUID();
		    $nuxt.$store.dispatch( "loading/addLoadingList", config.aixosReference );
	    }

        // console.log("========== axios Request ==========")
        // console.log(config);
    })

    $axios.onError(error => {
         console.log("========== axios Error ==========")
         console.log(error);

        const code = parseInt(error.response && error.response.status);

        if (401 === code) {
			// redirect("/survey/login/survey_login");
        }
		/*else if ( 500 === code ) {

			console.log ( error );
			console.log ( error.response );
			console.log ( error.response.status );
			console.log ( error.response.message );
		}*/

	    // 통신 완료된 axios reference clear
	    $nuxt.$store.dispatch( "loading/deleteAllLoadingList" );

    })

    $axios.onResponse(response => {
        // console.log("========== axios Response ==========")
        // console.log(response);

	    // 통신 완료된 axios reference clear
	    $nuxt.$store.dispatch( "loading/deleteLoadingList", response.config.aixosReference );
    })

}
