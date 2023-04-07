<template>
	<!-- 검색영역 -->
	<div class="search-area">
	    <div class="filter-search-box">
	        <div class="input-group">
	            <the-period ref="period" date-title="표시기간"  @setPeriodChangedDate="updateDate" @setPeriodDate="updateDate"/>
	            <the-text-search ref="textSearch" @searchQuery="getSearchQuery"/>
				<div class="btn-area right">
					<el-button size="medium bar-md" type="gray" @click="reset()">초기화</el-button>
					<el-button size="medium bar-md" type="primary" @click="clickSearchBtn()">검색</el-button>
				</div>
	        </div>
	    </div>
	</div>
</template>

<script>
import thePeriod from "~/components/common/search/period/periodSearch.vue";
import theTextSearch from "~/components/common/search/text/textSearch.vue";

export default {

	components : {

		thePeriod,
		theTextSearch,
	},
	data() {

		return {

			searchDate : null,
			searchQuery : null,
		}
	},
	methods : {

		updateDate( data ) {

			this.searchDate = data.searchDate;
		},

		/* 검색버튼을 눌렀을 때, 부모에게 검색어를 보내줍니다. */
		clickSearchBtn() {

			this.$emit( "searchObj", {

				searchDate  : this.searchDate,
				searchQuery : this.searchQuery
			} );
		},

		/* 초기화 버튼 클릭시 */
		reset() {

			this.$refs.period.resetPeriod();
			this.$refs.textSearch.reset();
		},

		getSearchQuery( data ) {

			this.searchQuery = data;
		},
	},
}
</script>
