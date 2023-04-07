export const state = () => ({
	loadingList : []

});

export const mutations = {
	ADD_LOADING_LIST( state, loadingId ) {
		state.loadingList.push( loadingId );
	},

	DELETE_LOADING_LIST( state, loadingId ) {
		state.loadingList.splice( state.loadingList.indexOf( loadingId ), 1 );
	},
	DELETE_ALL_LOADING_LIST( state, loadingId ) {
		state.loadingList = [];
	},
}

export const actions = {
	addLoadingList( { commit }, loadingId ) {

		commit( "ADD_LOADING_LIST", loadingId );
	},

	deleteLoadingList( { commit }, loadingId ) {

		commit( "DELETE_LOADING_LIST", loadingId );

	},

	deleteAllLoadingList( { commit } ) {
		commit( "DELETE_ALL_LOADING_LIST" );
	},
}
