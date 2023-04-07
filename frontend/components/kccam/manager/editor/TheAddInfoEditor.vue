<template>
	<!-- 추가 정보 에디터 -->
	<div class="editor-area">
		<div class="btn-wrap bottom">
			<el-button type="secondary" size="large bar" @click="dialogVisible = true">
				<span class="material-icons">add_circle_outline</span>
				<span class="txt">추가 정보 관리</span>
			</el-button>
		</div>
		<el-dialog :visible.sync="dialogVisible" top="5vh" width="90vw" @close="sendContents()">
			<div slot="title" class="dialog-header">
				<div class="tit-area">
					<span class="tit">추가 정보 관리</span>
				</div>
				<div class="btn-area">
					<el-button size="default" type="txt-only st-white-em" @click="dialogVisible = false">저장</el-button>
				</div>
			</div>
			<!-- scroll area -->
			<div v-bar="{ preventParentScroll: true }" class="scroll-element editor">
				<!-- el1 -->
				<div>
					<div class="inner-content">
						<!-- editor-item :: 클릭하여 정보 추가 클릭 시 반복되는 부분 -->
						<div class="editor-item" v-for="(item, index) in froalaList">
							<div class="input-area">
								<div class="input-row-md">
									<div class="input-label">
										<span>추가정보 제목</span>
									</div>
									<div class="input-data">
										<el-input placeholder="" v-model="item.contentsTitle" clearable></el-input>
										<!-- 삭제 버튼 -->
										<el-button type="icon-only lg" @click="removeContent( index )">
											<span class="material-icons">delete_forever</span>
										</el-button>
									</div>
								</div>
								<div class="input-row-md column">
									<div class="input-label">
										<span>추가정보 내용</span>
									</div>
									<div class="input-data">
										<!-- Froala editor -->
										<editor :ref="item.refName"
										        :html-contents="item.contents"
										        @sendContentData="getContentData"
										        @isMounted="mountEditor"
										        :order-no="item.orderNo"
										        :editor-style="edtiorStyle"
										/>
									</div>
								</div>
							</div>
						</div>
						<div class="btn-wrap bottom">

						</div>
					</div>
				</div>
			</div>
			<div slot="footer" class="dialog-footer">
				<el-button type="secondary" size="large bar" @click="addContent">
					<span class="material-icons">add_circle_outline</span>
					<span class="txt">문단 한 개 추가</span>
				</el-button>
			</div>
		</el-dialog>
	</div>
</template>
<script>
import editor from "~/components/common/board/TheEditorScript.vue";

export default {
	components : {
		editor,
	},
	props      : {
		containerOid : {
			type     : String,
			required : false,
			default  : ""
		},
		lang         : {
			type     : String,
			required : false,
			default  : ""
		}
	},
	data() {
		return {
			froalaList : [],

			dialogVisible : false,

			edtiorStyle : {
				minHeight : 500
			}
		};
	},

	watch: {
		// async dialogVisible() {
		// 	if( !this.dialogVisible ) {
		// 		await this.test();
		// 	}
		// }
	},

	methods : {
		/**
		 * 플로알라 에디터에 참조값을 만들어줍니다.
		 * @param index
		 * @returns {string}
		 */
		getFroalaRef( index ) {
			return "froalaEditor" + String( index );
		},
		/**
		 * 컨텐츠를 추가합니다.
		 */
		addContent() {

			this.froalaList.push( {
				contentsTitle : "",
				contents      : "",
				orderNo       : this.froalaList.length,
				refName       : this.getFroalaRef( this.froalaList.length ),
				containerOid  : this.containerOid,
			} );
		},
		/**
		 * 컨텐츠를 삭제합니다.
		 * @param index
		 */
		removeContent( index ) {

			for ( let i = index; i < this.froalaList.length; i++ ) {
				this.$refs[ this.froalaList[ i ].refName ][ 0 ].sendContentData();
			}

			this.froalaList.splice( index, 1 );

			if ( this.$common.isNotEmpty( this.froalaList ) ) {
				for ( let i = index; i < this.froalaList.length; i++ ) {
					this.froalaList[ i ].orderNo = i;
					this.froalaList[ i ].refName = this.getFroalaRef( i );
				}
			}


		},
		/**
		 * 부모 vue에 컨텐츠 리스트를 반환합니다.
		 */
		getContentsList() {
			const _self = this;

			if ( this.$common.isEmpty( this.froalaList ) ) {
				return this.froalaList;
			}
			_.each( _self.froalaList, function ( item, index ) {
				let eachRefName = item.refName;
				_self.froalaList[ index ].contents = _self.$refs[ eachRefName ][ 0 ].contents;
				if ( _self.$common.isNotEmpty( _self.lang ) ) {
					_self.froalaList[ index ].lang = _self.lang;
				}
			} );

			return this.froalaList;
		},

		/**
		 * froala 리스트를 그려줍니다.
		 * @param list
		 */
		displayList( list ) {
			const _self = this;

			if ( _self.$common.isNotEmpty( list ) ) {
				_.each( list, function ( item ) {
					item.oid = "";
					item[ 'refName' ] = "froalaEditor" + String( item.orderNo );
					_self.froalaList[ String( item.orderNo ) ] = item;
				} );
				_self.$forceUpdate();
			}

		},
		getContentData( data ) {
			this.froalaList[ String( data.orderNo ) ].contents = data.contents;
		},
		mountEditor() {
			this.$emit( "editorMounted" )
		},

		sendContents() {
			this.$emit("emitContents");
		},

	},
};
</script>
<style></style>
