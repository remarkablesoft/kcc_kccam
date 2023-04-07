import Cookies from "js-cookie";

export const state = () => ({

	//로그인 유저
	loginUser : {

		userId       : "",
		userName     : "",
		userOid      : "",
		userEmail    : "",
		userRoleOids : "",

		userPhone : "",
		phonePart1 : "",
		phonePart2 : "",
		phonePart3 : "",

		userType  : "",
		orgName   : "",
		empNo     : "",

	}
});

export const mutations = {

	// 로그인하고 나면 로그인 유저로 loginUser를 맞춰준다
	LOGIN_USER( state, loginUser ) {
		state.loginUser = loginUser;
	},

}

export const actions = {

	logout( { commit } ) {
		const cookieArr = ['LUN', 'UDC', 'UAC', 'LURL', 'LUO', 'LUUT', 'LUI', 'LUBI'];

		_.each( cookieArr, function( cookieName ) {
			Cookies.remove( cookieName );
		});

		let loginUser = {
			userId       : "",
			userName     : "",
			userOid      : "",
			userEmail    : "",
			userRoleOids : "",

			userPhone : "",
			phonePart1 : "",
			phonePart2 : "",
			phonePart3 : "",

			userType  : "",
			orgName   : "",
			empNo     : "",
		};

		commit( "LOGIN_USER", loginUser );
		sessionStorage.clear();

		document.location.href = "/kccam/manager"

	},

	loginUser( { commit }, loginUser ) {
		commit( "LOGIN_USER", loginUser );
	},

}
