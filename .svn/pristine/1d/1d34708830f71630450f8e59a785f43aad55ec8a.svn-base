<template>
	<div class="input-row">
		<div class="item-label" v-text='selectTitle'></div>
		<div class="item-data">
			<el-select v-model="selectedValue" class="size-sm" placeholder="전체" size="" @change='setSelectData'>
				<el-option v-for="item in selectValue" :key="item.value" :label="item.label"
						   :value="item.value"></el-option>
			</el-select>
		</div>
	</div>
</template>
<script>
export default {
	name    : "selectSearch",
	props   : {
		selectTitle : {
			type     : String,
			required : true,
		},
		selectValue : {
			type     : Array,
			required : true
		},
		paramName : {
			type     : String,
			required : false,
		}

	},
	data() {
		return {
			selectedValue : null,
		}
	},
	methods : {
		setSelectData( data ) {
			this.$emit( "setSelectData", data )
		},
		reset() {
			this.selectedValue = null;
			this.setSelectData( null )
		}
	}
}
</script>
