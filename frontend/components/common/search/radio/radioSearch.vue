<template>
	<div class="input-row">
		<div class="item-label" v-text='radioTitle'></div>
		<div class="item-data">
			<el-radio-group v-model="completeYnRadio" @change='setRadioData'>
				<el-radio v-for="item in radioData" :key="item.value" :label="item.value">{{item.label}}</el-radio>
			</el-radio-group>
		</div>
	</div>
</template>
<script>
export default {
	name    : "radioSearch",
	props   : {
		radioTitle : {
			type     : String,
			required : true
		},
		radioData  : {
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
			completeYnRadio : 1
		}
	},
	methods : {
		setRadioData( data ) {
			this.$emit( "setRadioData", data )
		},
		reset() {
			this.completeYnRadio = 1
			this.setRadioData( null )
		}
	}
}
</script>
<style scoped></style>
