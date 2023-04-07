import createPersistedState from 'vuex-persistedstate';

export default ( { store } ) => {
    createPersistedState( {
	    storage: window.sessionStorage,
        key: "am",
	    //Todo : 서버 안정화시 classification 추가해서 반영
        paths: [ "menu", "classification" ],
	    // paths: [ "menu" ]
	    // paths : []
    } )( store )
}
