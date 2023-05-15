<template>
	<!-- 문의자에게 메일 보내기 모달 -->
	<el-dialog title="문의자에게 메일 보내기" :visible.sync="visible" @close="close" width="60rem">
		<div class="inner-content">
			<div class="input-area">
				<div class="input-row-md">
					<div class="input-label">
						<span class="input-tit">문의자 이메일</span>
					</div>
					<div class="input-data">
						<el-input placeholder="" v-text="receiverInfo.inputUserInfo.email" clearable></el-input>
					</div>
				</div>
				<div class="input-row-md">
					<div class="input-data">
						<el-input type="textarea" :rows="8" v-model="contentInput"></el-input>
					</div>
				</div>
			</div>
		</div>
		<div slot="footer" class="dialog-footer">
			<el-button type="primary" size="default" @click="sendEmail()">확인</el-button>
			<el-button size="default" @click="close()">취소</el-button>
		</div>
	</el-dialog>
</template>
<script>
export default {
	props      : {
		sendMailVisible : {
			type     : Boolean,
			required : true,
		},
		// emailReceiver   : {
		// 	type     : String,
		// 	required : true
		// },
		// clientMail:{
		// 	type:String,
		// 	required: true
		// },
		receiverInfo : {
			type     : Object,
			required : true
		}
	},
	components : {},
	data() {
		return {
			visible : this.sendMailVisible,

			emailInput   : "",
			contentInput : "",
		};
	},
	created() {
		console.log( "receiverInfo", this.receiverInfo )
	},
	mounted() {
	},
	watch   : {
		sendMailVisible() {
			this.visible = this.sendMailVisible;
		},
	},
	methods : {
		close() {
			this.$emit( "close" );
		},
		sendEmail() {
			if ( this.$common.isEmpty( this.contentInput ) ) {
				this.$common.confirmSwal( "내용을 입력하지 않았습니다.", "", "warning" );
				return;
			}

			const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE.SEND_RESPONSE_MAIL;

			let param = {
				// receiverEmailList : [
				// 	{
				// 		receiverEmail : this.receiverInfo.receiverEmailList[0].configReceiverEmail,
				// 		configReceiverEmail : this.receiverInfo.inputUserInfo.email,
				// 	}
				// ],
				receiverEmail : "kj@remarkablesoft.com",
				title         : this.receiverInfo.title,
				descr         : this.receiverInfo.descr,
				answer        : this.contentInput,

				// inputUserInfo     : this.receiverInfo.inputUserInfo,
			}

			// console.log( param )
			this.$axios.post( url, param ).then( res => {
				this.$common.confirmSwal( "답변 메일을 발송하였습니다.", "", "success" ).then(
					confirm => {
						this.close();
					}
				);
			} );
		}
	},
};
</script>
<style></style>
