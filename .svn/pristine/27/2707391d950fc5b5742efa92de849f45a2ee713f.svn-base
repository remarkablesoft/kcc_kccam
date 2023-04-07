
<!-- 페이지 정보 -->
<template>
    <div class="page-cnt-info" v-if="0 < listCount">
		<span v-text="pageCountText"></span>
    </div>
</template>

<script>
export default {
	props : {

		currentPage : {
			type : Number,
			required : true,
		},
		lastPage : {
			type : Number,
			required : true,
		},
		listCount : {
			type : Number,
			required : true,
		}
	},

    computed : {

    	pageCountText(){

    		return `페이지(${this.currentPage}/${this.lastPage}) / 총 ${this.listCount}건`;
    	}
    },
};
</script>

<style>
</style>
