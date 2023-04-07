<template>
	<div>
		<el-button type="primary" size="small" class="is-arrow-icon" @click="previewDialogVisible = true"
		           style="margin-left: auto; display: block;">
			<span>추가 정보 미리보기</span>
		</el-button>
		<el-dialog title="추가 정보 미리보기" :visible.sync="previewDialogVisible" top="5vh" width="90vw">
			<!-- scroll area -->
			<div v-bar="{ preventParentScroll: true }" class="scroll-element editor"
			     v-if="$common.isNotEmpty( contentsList )">
				<!-- el1 -->
				<div class="desc-editor-wrap">
					<div v-for="contents in contentsList" style="margin-top:5rem;">
						<div class="inner-head">
							<div class="tit" v-text="contents.contentsTitle"><!-- 머릿말 일로들어오면 됩니다 --></div>
						</div>
						<div class="inner-body variable-context" v-html="contents.contents">
							<!-- 내용은 일로들어오면 됩니다 -->
						</div>
					</div>
				</div>
			</div>
			<div slot="footer" class="dialog-footer">
				<el-button size="default" @click="previewDialogVisible = false">닫기</el-button>
			</div>
		</el-dialog>
	</div>
</template>
<script>
export default {
	props : {
		lang : {
			type : String,
			required : false,
			default : "",
		},
		contentsList : {
			type : Array,
			required : false,
			default : () => {
				return [];
			}
		}
	},
	data() {
		return {
			// activeNames : [],

			// 미리보기 modal
			previewDialogVisible : false,
		}
	},
	created() {

	},

	fetch() {
		// console.log("ThePreview fetched : ", this.contentsList)
	},

	mounted() {
		this.previewMounted();
	},
	watch : {
		previewDialogVisible() {
			if ( this.previewDialogVisible ) {
				// console.log( "previewDialogVisible : watch : ",this.contentsList );
			}
		}
	},
	methods : {
		addInfo( data ) {
			// console.log( data );
		},
		previewMounted() {
			this.$emit( "previewMounted" );
		}
	},
}
</script>
<style scoped>
</style>
