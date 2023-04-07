<template>
	<div class="input-row">
		<div class="item-label" v-text='checkBoxTitle'></div>
		<div class="item-data">
			<el-checkbox-group v-model="checkList" @change='setCheckBoxData'>
				<el-checkbox v-for='data in checkBoxData' :key="data.value" :label="data.label"></el-checkbox>
			</el-checkbox-group>
		</div>
	</div>
</template>
<script>
export default {
	name  : "checkBoxSearch",
	props : {
		checkBoxTitle : {
			type     : String,
			required : true,

		},
		checkBoxData  : {
			type     : Array,
			required : true
		},
		paramName     : {
			type     : String,
			required : false,
		}
	},

	data() {
		return {
			checkList : [],
		}
	},
	methods : {
		setCheckBoxData( data ) {
			this.$emit( "setCheckBoxData", data )

		},
		reset() {
			this.checkList = [];
			this.setCheckBoxData( null )
		}
	}
}
</script>
<style scoped></style>
