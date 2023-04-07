<template>
    <!-- 공유 모달 -->
    <el-dialog :title="$t(`user_shareModal_tit`)" :visible.sync="visible" @close="close" width="50rem">
        <div class="inner-content">
            <p class="tit tc" v-text="getDocName( shareDocInfo )"></p>
            <div class="input-item">
                <div class="input-label">{{ $t(`user_shareModal_inputLabel`) }}</div>
                <div class="input-data">
                    <el-input :placeholder="$t(`user_shareModal_placeHolder`)" @keypress.enter.native="onClickSendMail" v-model="emailInput"></el-input>
                </div>
            </div>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="onClickSendMail()">{{ $t(`user_shareModal_btnConfirm`) }}</el-button>
            <el-button @click="close">{{ $t(`user_shareModal_btnCancel`) }}</el-button>
        </div>
    </el-dialog>
</template>

<script>
export default {
    props: {
        shareDialogVisible: {
            type: Boolean,
            required: true,
        },
		shareDocInfo: {
        	type : Object,
			required: false,
		},
    },

    data() {
        return {
            // modal visible
            visible: false,

            // email input
            emailInput: "",

			shareDocLink: "",
        };
    },
    created() {},
    mounted() {},
    watch: {
        shareDialogVisible: function() {
            this.visible = this.shareDialogVisible;
        },
    },
    methods: {
        close() {
            this.visible = false;
            this.$emit("close");
        },

		async onClickSendMail(){
			if ( this.$common.isEmpty( this.emailInput ) ){
				this.$common.confirmSwal( this.$t(`user_shareModal_placeHolder`), this.$t(`user_shareModal_confirmSwal_content1`), "warning" );
				return;
			}
			if ( this.$common.emailValidation( this.emailInput ) ){
				this.$common.confirmSwal( this.$t(`user_shareModal_placeHolder`), this.$t(`user_shareModal_confirmSwal_content2`), "warning" );
				return;
			}

			this.setShareDocLink( this.shareDocInfo );

			let fileName = this.getDocName( this.shareDocInfo );
			let param = {
				shareEmailAddress : this.emailInput,
				title : fileName,
				shareDocLink : this.shareDocLink,
			};

			const urlSendEmail = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.DOC_SEND_EMAIL_WITH_DOCLINK;
			this.close();
			await this.$axios.post( urlSendEmail, param ).then( res => {
				if ( res.status !== 200 ){
					return;
				}
				this.insertDocShareAudit();
			});

			this.emailInput = "";

		},

	    //공유 로그를 남깁니다
	    async insertDocShareAudit(){

            let info = {
            	targetOidList : this.shareDocInfo.currentDocVersionInfo.oid,
	            email     : this.emailInput,
	            shareType : this.$amConstant.DOC_SHARE_TYPE.SHARE_TYPE_SHARE,
	            accessUrl : this.$route.fullPath,
            }

		    const urlInsert = this.$urlConstant.API_URL_PREFIX.DOC_SHARE_AUDIT + this.$urlConstant.API_URL_SUFFIX.DOC_SHARE_AUDIT.INSERT;

	        await this.$axios.post( urlInsert, info ).then( res => {
	        	if ( res.status !==200 ){
	        		return;
		        }
	        });
	    },

		//파일명을 가져옵니다
		getDocName( doc ){
			if( this.$common.isEmpty(doc) ){
				return ;
			}
        	if ( this.$common.isEmpty( doc.currentDocVersionInfo) || this.$common.isEmpty( doc.currentDocVersionInfo.docFileInfo ) ){
				return doc.title;
			}
			//파일 확장자를 제외한 파일명 추출
			let lastDotIndex = doc.customField2.lastIndexOf('.');

			return doc.customField2.substring( 0, lastDotIndex );
		},

		//문서 다운로드 URL을 설정합니다.
		setShareDocLink( shareDocInfo ) {

        	if( this.$common.isEmpty(shareDocInfo.currentDocVersionInfo) ){
        		return;
	        }

			if ( this.$common.isEmpty( shareDocInfo.currentDocVersionInfo.docFileInfo ) ) {
				this.shareDocLink = shareDocInfo.currentDocVersionInfo.outLinkUrl;
				return;
			}

			this.shareDocLink = this.$amConstant.DOMAIN.COM + "/storage/storageFile_fileDown/" + shareDocInfo.currentDocVersionInfo.docFileInfo.fileName + "/" + shareDocInfo.currentDocVersionInfo.docFileInfo.storageFileUid;
		},
    },
};
</script>

<style></style>
