<template>
	<!-- 검색영역 -->
	<div class="search-area">
		<div class="filter-search-box">
			<div class="input-group">
				<div v-for='(search, index) in searchData' :key='index'>
					<!--날짜 기간-->
					<period-search v-if="'period' === search.type" ref="periodSearch" :date-radio-visible="true" :date-title="search.title"
								   :param-name='search.paramName' :ref-date-list='search.refDateList' @setPeriodChangedDate="setPeriodSearchData"
								   @setPeriodDate="setPeriodSearchData"/>
					<!-- text -->
					<text-search v-if="'text' === search.type" ref="textSearch" :param-name='search.paramName'
								 :select-yn="$common.isNotEmpty(search.searchTypeOptions)" :text-title='search.title' :search-option="search.searchTypeOptions"
								 @setTextData="setTextSearchData" @search="clickSearchBtn"/>
					<!-- select -->
					<select-search v-if="'select' === search.type" ref='selectSearch' :param-name='search.paramName'
								   :select-title='search.title' :select-value='search.data'
								   @setSelectData='setSelectSearchData'/>
					<!-- radio -->
					<radio-search v-if="'radio' === search.type" ref='radioSearch' :param-name='search.paramName'
								  :radio-data='search.data' :radio-title='search.title'
								  @setRadioData='setRadioSearchData'/>
					<!-- checkbox -->
					<check-box-search v-if="'checkbox' === search.type" ref='checkboxSearch'
									  :check-box-data='search.data' :check-box-title='search.title'
									  :param-name='search.paramName' @setCheckBoxData='setCheckBoxSearchData'/>
					<!-- tree -->
					<tree-search v-if="'tree' === search.type" ref='treeSearch' 
								:tree-type='search.treeType' :tree-title='search.title'
								:param-name='search.paramName' @setTreeData='setTreeSearchData' />
				</div>
				<div class="btn-area right">
					<el-button size="medium bar-md" type="gray" @click="reset()">초기화</el-button>
					<el-button size="medium bar-md" type="primary" @click="clickSearchBtn()">검색</el-button>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import periodSearch from "~/components/common/search/period/periodSearch.vue";
import textSearch from "~/components/common/search/text/textSearch.vue";
import selectSearch from "~/components/common/search/select/selectSearch";
import radioSearch from "~/components/common/search/radio/radioSearch";
import checkBoxSearch from "~/components/common/search/checkBox/checkBoxSearch";
import treeSearch from "~/components/common/search/tree/treeSearch";

export default {
	name       : "TheSearch",
	props      : {
		searchData : {
			type     : Array,
			required : true
		},
		paramName  : {
			type     : String,
			required : false,
			default  : "title"
		}

	},
	components : {
		checkBoxSearch,
		periodSearch,
		textSearch,
		selectSearch,
		radioSearch,
		treeSearch,
	},

	data() {
		return {
			textSearchData     : {},
			periodSearchData   : {},
			selectSearchData   : {},
			radioSearchData    : {},
			checkboxSearchData : {},

			treeSearchData: {},
			selectData   : [],
			radioData    : [],
			checkboxData : []

		}
	},
	mounted() {
	},
	methods : {
		

		async clickSearchBtn() {

			let param = await this.getSearchParams();

			/* 검색버튼을 눌렀을 때, 부모에게 검색어를 보내줍니다. */
			// 2021-03-15 직접 값을 보내주지 않고 라우트의 값을 받아 사용하도록 변경
			//this.$emit( "searchObj", param );

			this.setRouter( param );
		},

		setRouter( param ) {
			if ( this.$common.isEmpty( param ) ) {
				return;
			}

			console.log( "theSearch", param );

			this.$router.push( {
				path  : this.$router.currentRoute.path,
				query : param,
			} );
		},

		getSearchParams() {

			let param = {}
			if ( this.$common.isNotEmpty( this.textSearchData ) ) {
				param[ this.textSearchData.paramName ] = this.textSearchData.data[ this.textSearchData.paramName ]

				if ( this.$common.isNotEmpty( this.textSearchData.data.searchQueryType ) ) {
					param[ "selectedSearchOption" ] = this.textSearchData.data.searchQueryType;
				}

				// this.$common.renameKey( param, "tempKey", this.textSearchData.paramName )
			}
			if ( this.$common.isNotEmpty( this.periodSearchData ) && this.$common.isNotEmpty( this.periodSearchData.data ) ) {
				console.log("this.periodSearchData", this.periodSearchData)
				// param[ this.periodSearchData.paramName ] = this.periodSearchData.data
				param[ "inputDateFrom" ]= this.$common.formatDateToString( this.periodSearchData.data.searchDate[ 0 ] );
				param[ "inputDateTo" ] = this.$common.formatDateToString( this.periodSearchData.data.searchDate[ 1 ] );

				// 선택한 기간 명을 같이 보내준다.
				if ( this.$common.isNotEmpty( this.periodSearchData.data.refDate ) ) {
					param.refDate = this.periodSearchData.data.refDate
				}
				// this.$common.renameKey( param, "tempKey", this.periodSearchData.paramName )
			}
			if ( this.$common.isNotEmpty( this.selectSearchData ) ) {
				param[ this.selectSearchData.paramName ] = this.selectSearchData.data
				// this.$common.renameKey( param, "tempKey", this.selectSearchData.paramName )
			}
			if ( this.$common.isNotEmpty( this.radioSearchData ) ) {
				param[ this.radioSearchData.paramName ] = this.radioSearchData.data
				// this.$common.renameKey( param, "tempKey", this.radioSearchData.paramName )
			}
			if ( this.$common.isNotEmpty( this.checkboxSearchData ) ) {
				param[ this.checkboxSearchData.paramName ] = this.checkboxSearchData.data
				// this.$common.renameKey( param, "tempKey", this.checkboxSearchData.paramName )
			}
			if ( this.$common.isNotEmpty( this.treeSearchData ) ) {
				param[ this.treeSearchData.paramName ] = this.treeSearchData.data
			}
			return param;
		},

		/* 초기화 버튼 클릭시 */
		reset() {

			this.$refs.periodSearch[ 0 ].resetPeriod();
			this.$refs.textSearch[ 0 ].reset();
			this.$refs.selectSearch[ 0 ].reset();
			this.$refs.radioSearch[ 0 ].reset();
			this.$refs.checkboxSearch[ 0 ].reset();
			this.$refs.treeSearch[ 0 ].reset();
		},

		setTextSearchData( data ) {
			console.log( "setTextSearchData", data )
			this.textSearchData.data = data;
			this.textSearchData.paramName = this.$refs.textSearch[ 0 ].paramName;
		},
		setPeriodSearchData( data ) {
			console.log( "setPeriodSearchData", data )
			this.periodSearchData.data = data;
			this.periodSearchData.paramName = this.$refs.periodSearch[ 0 ].paramName;

			// this.periodSearchData.inputDateFrom = this.$common.formatDateToString( data.searchData[ 0 ] );
			// this.periodSearchData.inputDateTo = this.$common.formatDateToString( data.searchData[ 1 ] );

		},
		setSelectSearchData( data ) {
			// console.log( "setSelectSearchData", data )
			this.selectSearchData.data = data;
			this.selectSearchData.paramName = this.$refs.selectSearch[ 0 ].paramName;
		},
		setRadioSearchData( data ) {
			// console.log( "setRadioSearchData", data )
			this.radioSearchData.data = data;
			this.radioSearchData.paramName = this.$refs.radioSearch[ 0 ].paramName;
		},
		setCheckBoxSearchData( data ) {
			// console.log( "setCheckBoxSearchData", data )
			this.checkboxSearchData.data = data;
			this.checkboxSearchData.paramName = this.$refs.checkboxSearch[ 0 ].paramName;
		},

		setTreeSearchData( data ) {
			console.log( "treeSearchData", data )
			this.treeSearchData.data = data;
			this.treeSearchData.paramName = this.$refs.treeSearch[ 0 ].paramName;
			
		},
	},
}
</script>
