<!-- 목록 정렬 선택 박스 -->
<template>
    <div class="list-sort-group">
        <el-select v-model="listSortOptionsValue" size="small" class="size-mini" placeholder="선택" @change="getListSortOptionsValue()">
            <el-option
            v-for="item in listSortOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>
    </div>
</template>

<script>
export default {
    data() {
        return {

            listSortOptions: this.$constant.LIST_SORT_OPTION,
            listSortOptionsValue: this.$constant.LIST_SORT_OPTION[0].value,
        }
    },
	methods: {
    	
		/* 선택된 정렬을 부모로 보내줍니다. */
		getListSortOptionsValue(){
    		
	    	this.$emit( "listSortOptionsValue", this.listSortOptionsValue );
    	}
    },
};
</script>

<style>
</style>