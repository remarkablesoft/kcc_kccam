export const state = () => ({

	menuList : [
		{
			subMenuList:[
				{
					detailMenuBox : false,
					detailMenuItem : [
						{
							detailMenuList : [],
							menuTitle : "",
						},
						{
							detailMenuList : [],
							menuTitle : "",
						}
					],
					subMenuName : "",
					subMenuUrl : "",
				}
			]
		},
		{
			subMenuList:[
				{
					detailMenuBox : false,
					detailMenuItem : [
						{
							detailMenuList : [],
							menuTitle : "",
						},
						{
							detailMenuList : [],
							menuTitle : "",
						}
					],
					subMenuName : "",
					subMenuUrl : "",
				}
			]
		}
	],
	materialMap : {},

	materialMenuList: [],

});

export const mutations = {

	SET_MENU_LIST( state, menuList ) {
		state.menuList = menuList;
	},

	SET_MATERIAL_MAP( state, materialMap ) {
		state.materialMap = materialMap;
	},

	SET_MATERIAL_MENU_LIST( state, materialMenuList ) {
		state.materialMenuList = materialMenuList;
	},

}

export const actions = {

	async setMaterialMenuList( { commit }, data ) {
		let param = {};
		const urlList = this.$urlConstant.API_URL_PREFIX.MATERIAL + this.$urlConstant.API_URL_SUFFIX.MATERIAL.MENU_LIST;
		await this.$axios.post( urlList, param ).then(res => {
			if (this.$common.isEmpty(res.data)) {
				return;
			}

			commit( "SET_MATERIAL_MENU_LIST", res.data );
		});
	},

	async getMaterialMenuList( { state, dispatch } ) {

		if( this.$common.isEmpty( state.materialMenuList ) ){
			await dispatch( "setMaterialMenuList" );
		}
		return state.materialMenuList;

	},

	async setMaterialMap( { state, commit, dispatch }, data ) {

		//materialMap세팅을 위해 materialMenuList가 필요합니다.
		let materialMenuList = await dispatch( 'getMaterialMenuList' );

		let materialMap = {};

		_.cloneDeep( materialMenuList ).forEach( function(item) {
			materialMap[item.oid] = {
				materialClass: _.kebabCase(_.lowerCase(item.name)),
				materialName: item.name
			};
		});

		commit( "SET_MATERIAL_MAP", materialMap );
	},

	async getMaterialMap( { state, dispatch } ){

		if( this.$common.isEmpty( state.materialMap ) ){
			await dispatch( "setMaterialMap" );
		}
		return state.materialMap;
	},

	setMenuList( { commit }, data ) {
		// console.log( "setMenuList", data );
		commit( "SET_MENU_LIST", data );
	},

	async setMaterialMenuListEmpty( { commit }, data ){
		await commit( "SET_MATERIAL_MENU_LIST", [] );
	},


}

export const getters = {

	menuList( state ) {
		return state.menuList;
	},

	productMenu( state ) {
		return state.menuList[ 1 ].subMenuList;
	},

	materialMap( state ) {
		return state.materialMap;
	},

	materialMenuList( state ){
		return state.materialMenuList;
	},

}
