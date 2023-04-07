<template>
	<div class="inner-wrapper">
		<!-- loading -->
			<div class="loading-container" v-if="isSending">
				<the-loading />
			</div>
		<!-- manager-content-body -->
		<div class="manager-content-body">
			<div class="content-title mt">
				<div class="sub-title">
					<h2>단체 메일 발송</h2>
				</div>
				<!-- <div class="btn">
					<el-button type="primary" size="default" @click="sendEmail">발송</el-button>
				</div> -->
			</div>
			<!--  content-detail -->
			<div class="content-detail">
				<!-- <div class="btn-group bb btn-only">
					<div class="btn-wrap">
						<el-button type="primary" size="default" @click="sendEmail"
						>발송
						</el-button
						>
					</div>
				</div> -->
				<div class="division-area email">
					<div class="division-item">
						<div class="tit-area-default">
							<div class="left-area">
								<span class="tit">EMAIL</span>
							</div>
							<div class="right-area">
								<el-button
									type="primary"
									size="small"
									@click="loadEmailVisible = true"
									style="display:none;"
								>
									EMAIL 불러오기
								</el-button>
								<el-button type="gray" size="small" @click="removeEmail">
									삭제
								</el-button>
							</div>
						</div>
						<div class="check-area bg-gray">
							<!-- scroll area -->
							<div
								v-bar="{ preventParentScroll: true }"
								class="scroll-element"
							>
								<!-- el1 -->
								<div>
									<ul class="check-row-wrap">
										<li
											li
											class="check-row-md"
											v-for="(item, index) in list"
											:key="index"
										>
											<div class="custom-checkbox">
												<input
													:id="index"
													type="checkbox"
													v-model="item.check"
												/>
												<label :for="index">
													<i></i>
													<span v-text="item.email">
														텍스트
													</span>
												</label>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="input-data">
								<el-input
									placeholder="받는 사람의 EMAIL을 입력하세요"
									v-model="emailInput"
									@keypress.enter.native="addEmail"
									clearable
								></el-input>
								<el-button
									type="primary"
									size="small"
									@click="addEmail"
								>
									등록
								</el-button>
							</div>
						</div>
					</div>
					<div class="division-item">
						<div class="tit-area-default">
							<span class="tit">내용</span>
						</div>
						<div class="editor-area">
							<div class="editor-item">
								<div class="input-area">
									<el-input
										placeholder="보내는 사람의 EMAIL을 입력하세요."
										v-model="senderEmail"
										clearable
									></el-input>
									<el-input
										placeholder="제목을 입력하세요."
										v-model="emailTitleInput"
										clearable
									></el-input>
									<editor ref="froalaEditor"></editor>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="btn-wrap right">
					<el-button type="primary" size="default" @click="sendEmail">발송</el-button>
				</div>
			</div>
		</div>
		<theLoadEmailModal
			:loadEmailVisible="loadEmailVisible"
			@close="loadEmailVisible = false"
		/>
	</div>
</template>
<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import editor from "~/components/common/board/TheEditorScript.vue";
import theLoadEmailModal from "~/components/kccam/manager/modal/TheLoadEmailModal.vue";

export default {
	layout     : "managerLayout",
	components : {
		theLoading,
		editor,
		theLoadEmailModal
	},
	data() {
		return {
			list : [],

			emailInput : "",

			// EMail 불러오기창
			loadEmailVisible : false,

			emailTitleInput : '',
			senderEmail     : '',
			isSending : false,

		};
	},
	methods : {

		addEmail() {

			if ( this.$common.emailValidation( this.emailInput ) ) {
				this.$common.confirmSwal( "올바른 이메일을 입력해 주세요.", "", "warning" );
				return;
			}

			let sendEmail = {

				check : false,
				email : this.emailInput
			}

			this.list.push( sendEmail );

			this.emailInput = "";
		},

		removeEmail() {

			let _self = this;

			this.list.forEach( function ( item, index ) {
				if ( item.check ) {

					_self.list.splice( index, 1 )
				}
			} )

		},

		validateBeforeSend() {

			let result = "";

			if ( this.$common.isEmpty( this.list ) ) {
				result = "받을 이메일";
			}

			if ( this.$common.isEmpty( this.senderEmail ) ) {
				result = "보내는 이메일";
			}
			if ( this.$common.isEmpty( this.emailTitleInput ) ) {
				result = "이메일 제목";
			}
			if ( this.$common.isEmpty( this.$refs.froalaEditor.contents ) ) {
				result = "이메일 내용"
			}

			return result;

		},


		sendEmail() {

			let _self = this;

			if ( this.$common.isNotEmpty( this.validateBeforeSend() ) ) {
				this.$common.confirmSwal( this.validateBeforeSend() + "을 입력해 주세요.", "", "warning" );
				return;
			}

			const url = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.USER_REGISTER_AND_SEND_EMAILS_WITH_DOCFILE;

			let paramList = [];

			this.list.forEach( function ( item, index ) {

				let param = {
					name    : "",
					from    : _self.senderEmail,
					to      : item.email,
					cc      : "",
					tar     : "html",
					title   : _self.emailTitleInput,
					content : _self.$refs.froalaEditor.contents
				};

				paramList.push( param );
			} );


			// console.log( paramList );
			this.isSending = true;

			this.$axios.post( url, paramList ).then( ( res ) => {

				console.log( res );
				if ( this.$common.isNotEmpty( res.data ) ) {

					this.$common.confirmSwal("성공", res.data.success + "건 전송에 성공했습니다.", "success").then( res => {
						this.sending = false;
						this.$forceUpdate();
					} )

				}
			}).catch( error => {
				this.isSending = false;
				this.$forceUpdate();
				this.$common.confirmSwal("전송 실패!", "이메일 전송에 실패했습니다. \n 지속적으로 같은 현상이 발생할 경우 관리자에게 문의바랍니다.", "error")
			});
		}

	}
};
</script>
<style scoped></style>
