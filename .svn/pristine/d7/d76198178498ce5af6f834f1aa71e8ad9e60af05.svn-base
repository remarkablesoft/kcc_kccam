<template>
	<div class="input-row">
		<div class="item-label" v-text='textTitle'></div>
		<div class="item-data">
			<el-select v-if='selectYn' v-model="searchTypeOptionsValue" class="size-sm" placeholder="선택" size="">
				<el-option v-for="(item, index) in searchTypeOptions" :key="index" :label="item.label"
				           :value="item.value"></el-option>
			</el-select>
			<el-input v-model="searchText" class="size-lg" @change='getSearchTypeOptionsValue' @keypress.enter.native="search()"></el-input>
		</div>
	</div>
</template>
<script>
export default {
	props   : {
		selectYn  : {
			type     : Boolean,
			required : false,
		},
		textTitle : {
			type     : String,
			required : false,
			default  : "검색명"
		},
		paramName : {
			type     : String,
			required : false,
		},
		searchOption : {
			type : Array,
			required : false,
		},
	},
	data() {
		return {

			// 검색명 select
			searchTypeOptions      : [],
			searchTypeOptionsValue : "",
			searchText             : "",
		}
	},
	mounted() {

		this.initValue();

	},
	methods : {

		/* 초기값 설정 */
		initValue(){

			if( this.searchOption ){

				this.searchTypeOptions = this.searchOption;
				this.searchTypeOptionsValue = this.searchOption[0].value;
			}
			else {

				this.searchTypeOptions = this.$constant.SEARCH_TYPE_OPTION;
				this.searchTypeOptionsValue = this.$constant.SEARCH_TYPE_OPTION[ 0 ].value;
			}
		},

		/* 부모에게 searchQueryType과 검색어를 보내줍니다. */
		getSearchTypeOptionsValue( searchText ) {
			let param = {}

			param.searchQuery = searchText

			//셀렉트박스 사용시
			if ( this.selectYn ) {
				param.searchQueryType = this.searchTypeOptionsValue
			}

			this.$emit( "setTextData", param );
		},

		reset() {

			this.searchTypeOptionsValue = this.$constant.SEARCH_TYPE_OPTION[ 0 ].value;
			this.searchText = "";
			this.$emit( "setTextData", null );
		},

		search() {
			this.getSearchTypeOptionsValue(this.searchText);
			this.$emit( "search" );
		}
	},
}
</script>
