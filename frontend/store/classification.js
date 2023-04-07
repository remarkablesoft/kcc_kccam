export const state = () => ({
	functionList: [],
	marketList: [],
	functionListSidebar: [],
});

export const mutations = {
	SET_FUNCTION_LIST(state, listData) {
		state.functionList = listData;
	},

	SET_MARKET_LIST(state, listData) {
		state.marketList = listData;
	},

	SET_FUNCTION_LIST_SIDEBAR(state, listData){
		state.functionListSidebar = listData;
	},
};

export const actions = {

	async setClassificationListData({ commit }, { parentOid, categoryType }) {
		let param = {
			parentOid: parentOid,
			categoryType: categoryType,
		};

		if ( this.$amConstant.CATEGORY_TYPE.MARKET === categoryType ){
			param.fillIconFile = true;
		}

		const url =
			this.$urlConstant.API_URL_PREFIX.CLASSIFICATION +
			this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST_ALL;
		await this.$axios.post(url, param).then(res => {
			if (this.$common.isEmpty(res.data)) {
				return;
			}

			let tempList = [];

			res.data.forEach((item, index) => {
				let linkUrl = "";

				if (this.$amConstant.CATEGORY_TYPE.MARKET === categoryType) {
					linkUrl =
						"/kccam/user/market/market/market_view?depth=2&classificationOid=" +
						item.oid +
						"&classification=market";
				} else if (
					this.$amConstant.CATEGORY_TYPE.FUNCTION === categoryType
				) {
					linkUrl =
						"/kccam/user/market/function/function_view?depth=2&classificationOid=" +
						item.oid +
						"&classification=function";
				}

				let data = {
					oid : item.oid,
					title: item.name,
					linkUrl: linkUrl,
					descr: item.descr,
					activeYn: false,
				};
				// console.log("data", data);
				if ( this.$amConstant.CATEGORY_TYPE.MARKET === categoryType ) {
					if ( this.$common.isNotEmpty( item.iconFile ) && this.$common.isNotEmpty( item.iconFile.storageFileUid ) ){
						data.storageFileUid = item.iconFile.storageFileUid;
					}
				}

				tempList.push(data);
			});

			if (this.$amConstant.CATEGORY_TYPE.MARKET === categoryType) {
				commit("SET_MARKET_LIST", tempList);
			} else if (
				this.$amConstant.CATEGORY_TYPE.FUNCTION === categoryType
			) {
				commit("SET_FUNCTION_LIST", tempList);
			}
		});
	},

	async setFunctionList({ commit }){
		let param = {
			parentOid: this.$amConstant.ROOT_CATEGORY_OID.FUNCTION,
			categoryType: this.$amConstant.CATEGORY_TYPE.FUNCTION,
		};

		let urlListAll = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION
						+ this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST_ALL;

		await this.$axios.post(urlListAll, param).then(res => {
			if (this.$common.isEmpty(res.data)) {
				return;
			}

			let tempList = [];

			res.data.forEach((item, index) => {
				let linkUrl = "/kccam/user/market/function/function_view?depth=2&classificationOid="
						+ item.oid
						+ "&classification=function";


				let data = {
					oid : item.oid,
					title: item.name,
					linkUrl: linkUrl,
					descr: item.descr,
					activeYn: false,
				};

				tempList.push(data);
			});

			commit("SET_FUNCTION_LIST", tempList);

		});
	},

	async getFunctionList( { state, dispatch }, data ) {
		if( this.$common.isEmpty( state.functionList ) ){
			await dispatch( "setFunctionList" );
		}
		return state.functionList;
	},

	async setMarketList({ commit }) {
		let param = {
			parentOid: this.$amConstant.ROOT_CATEGORY_OID.MARKET,
			categoryType: this.$amConstant.CATEGORY_TYPE.MARKET,
			fillIconFile: true,
		};

		const url =
			this.$urlConstant.API_URL_PREFIX.CLASSIFICATION +
			this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST_ALL;

		await this.$axios.post(url, param).then(res => {
			if (this.$common.isEmpty(res.data)) {
				return;
			}

			let tempList = [];

			res.data.forEach((item, index) => {
				let linkUrl = "/kccam/user/market/market/market_view?depth=2&classificationOid=" +
					item.oid +
					"&classification=market";

				let data = {
					oid : item.oid,
					title: item.name,
					linkUrl: linkUrl,
					descr: item.descr,
					activeYn: false,
				};

				if ( this.$amConstant.CATEGORY_TYPE.MARKET) {
					if ( this.$common.isNotEmpty( item.iconFile ) && this.$common.isNotEmpty( item.iconFile.storageFileUid ) ){
						data.storageFileUid = item.iconFile.storageFileUid;
					}
				}

				tempList.push(data);
			});

			commit("SET_MARKET_LIST", tempList);

		});
	},

	async getMarketList( { state, dispatch }, data ) {
		if( this.$common.isEmpty( state.marketList ) ){
			await dispatch( "setMarketList" );
		}
		return state.marketList;
	},

	async setFunctionListSidebar( { commit }, data ){
		let materialMenuList = await $nuxt.$store.dispatch("menu/getMaterialMenuList");

		let tempList = [];
		_.forEach( _.cloneDeep( materialMenuList ), item =>{
			let obj = {
				title : item.name,
				active : true, // slide active
				detailList : [],
			};

			let functionDetailList = [];

			item.functionList.forEach( ( func, funcIndex ) => {
				let obj = {
					oid      : func.oid,
					title    : func.name,
					linkUrl  : "/kccam/user/market/function/function_view?depth=2&classificationOid=" + func.oid + "&classification=function",
					activeYn : false,
				};

				functionDetailList.push( obj );
			} );

			obj.detailList = functionDetailList;
			tempList.push( obj );
		})

		commit("SET_FUNCTION_LIST_SIDEBAR", tempList);
	},

	async getFunctionListSidebar( { state, dispatch }, data ){
		if( this.$common.isEmpty( this.state.functionListSidebar ) ){
			await dispatch( "setFunctionListSidebar" );
		}
		return state.functionListSidebar;
	},

	setClassificationListEmpty( {commit} ) {
		commit("SET_MARKET_LIST", [] );
		commit("SET_FUNCTION_LIST", [] );
	}
};

export const getters = {
	functionList( state ) {
		return state.functionList;
	},

	marketList( state ) {
		return state.marketList;
	},

	functionListSidebar( state ) {
		return state.functionListSidebar;
	}
};
