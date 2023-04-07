<!-- 페이지 보기 개수 선택 박스 -->
<template>
    <div class="page-select-group">
        <!-- pageCntInfo.vue와 중복된 내용이라 주석처리.
            <span class="cnt-txt">전체건수: <span>10</span>건</span> 
        -->
        <el-select v-model="pageCntOptionsValue" size="small" class="size-mini" placeholder="선택" @change="getPageCntOptionsValue()">
            <el-option
            v-for="item in pageCntOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            >
            </el-option>
        </el-select>
    </div>
</template>

<script>
const GET_CONFIG = "/lms/api/v1/lmsEnvConfigApi_getPostingDisplayCnt";

export default {
	props: {
		
		pageSize : {
			type: Number,
			default: 10,
		}
		
	},
    data() {
        return {

            // 페이지 보기 개수 select
            pageCntOptions: [],
            pageCntOptionsValue: this.pageSize,

        }
    },
    mounted() {
    	
    	this.getPostingDisplayCnt();
    },
    methods : {
    	
    	/* 선택된 페이지 개수를 부모로 보내줍니다. */
		getPageCntOptionsValue(){
    		
	    	this.$emit( "pageCntOptionsValue", this.pageCntOptionsValue );
    	},
    	
    	/* 게시물 display 개수를 가져옵니다. */
    	getPostingDisplayCnt(){

			this.$axios.post( GET_CONFIG ).then( (res) => {
				
				if( this.$common.isEmpty( res.data ) ){
					return;
				}
				
				let envValue = res.data.envValue.split( "," );
				envValue.forEach( item => {
					
					let obj = {};
					obj.label = `${item}개씩`;
					obj.value = Number(item);
				
					this.pageCntOptions.push( obj );
				});
			});
		},
    },
};
</script>

<style>
</style>