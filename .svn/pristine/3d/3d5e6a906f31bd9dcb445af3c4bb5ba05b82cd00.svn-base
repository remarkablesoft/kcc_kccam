<template>
    <!-- 공유 모달 -->
    <el-dialog :title="$t(`user_docDownload_tit`)" :visible.sync="visible" @close="close" width="50rem" append-to-body>
        <div class="inner-content">
            <p class="tit tc" v-text="getDocName()"></p>
            <div class="input-item">
                <div class="input-label">{{ $t(`user_shareModal_inputLabel`) }}</div>
                <div class="input-data">
                    <el-input :placeholder="$t(`user_shareModal_placeHolder`)" @keypress.enter.native="onClickDownload" v-model="emailInput"></el-input>
                </div>
            </div>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="onClickDownload()">{{ $t(`user_market_relatedDocu_download`) }}</el-button>
            <el-button @click="close">{{ $t(`user_shareModal_btnCancel`) }}</el-button>
        </div>
    </el-dialog>
</template>

<script>
export default {
    props: {
        downloadDialogVisible: {
            type : Boolean,
            required : true,
        },
	    //문서 다운로드일 경우 사용
	    downloadDocInfo: {
        	type : Object,
			required: false,
		},

	    //제품 데이터 시트 다운일 경우 사용
	    excelTabText : {
        	type : String,
		    required: false
	    },
	    productList : {
            type : Array,
		    required : false,
	    },
    },

    data() {
        return {
            // modal visible
            visible: false,

            // email input
            emailInput: "",

	        //제품 데이터 시트일 경우 사용
	        fileName : 'KCCMaterials_DataSheet.xls',
        };
    },
    created() {},
    mounted() {},
    watch: {
	    downloadDialogVisible: function() {
            this.visible = this.downloadDialogVisible;
        },
    },
    methods: {
        close() {
            this.visible = false;
            this.$emit("close");
	        this.emailInput = "";
        },

	    async onClickDownload(){

		    if ( this.$common.isEmpty( this.emailInput ) ){
			    this.$common.confirmSwal( this.$t(`user_shareModal_placeHolder`), this.$t(`user_shareModal_confirmSwal_content1`), "warning" );
			    return;
		    }
		    if ( this.$common.emailValidation( this.emailInput ) ){
			    this.$common.confirmSwal( this.$t(`user_shareModal_placeHolder`), this.$t(`user_shareModal_confirmSwal_content2`), "warning" );
			    return;
		    }

		    //문서 다운
		    if( this.$common.isNotEmpty( this.downloadDocInfo ) ){
			    this.docFileDown();
			    await this.insertDocShareAudit( this.$amConstant.DOC_SHARE_TYPE.SHARE_TYPE_DOWNLOAD , this.downloadDocInfo.currentDocVersionInfo.oid );
		    }

		    //제품 데이터 시트 엑셀 다운
		    if( this.$common.isNotEmpty( this.excelTabText ) ){
		    	this.excelDownDataSheet();
			    await this.insertDocShareAudit( this.$amConstant.DOC_SHARE_TYPE.SHARE_TYPE_EXCEL_DATASHEET, this.getProductOidList() );
		    }

	    },

	    //문서를 다운로드합니다.
	    docFileDown() {

        	if( this.$common.isEmpty( this.downloadDocInfo ) ){
				return;
			}

		    if ( this.$common.isNotEmpty( this.downloadDocInfo.currentDocVersionInfo.outLinkUrl ) ) {
			    this.$amCommon.downloadExternalLinkFile( this.downloadDocInfo.currentDocVersionInfo.outLinkUrl, this.downloadDocInfo.title )
			    return;
		    }

		    location = "/storage/storageFile_fileDown/" + this.downloadDocInfo.currentDocVersionInfo.docFileInfo.fileName + "/" + this.downloadDocInfo.currentDocVersionInfo.docFileInfo.storageFileUid;
	    },

	    //엑셀 데이터 시트를 다운로드합니다.
	    excelDownDataSheet(){

        	if( this.$common.isEmpty( this.excelTabText ) ){
        		return;
	        }

		    let data_type = 'data:application/vnd.ms-excel';
		    let ua = window.navigator.userAgent;
		    let msie = ua.indexOf( "MSIE " );

		    //Explorer 환경에서 다운로드
		    if ( msie > 0 || !!navigator.userAgent.match( /Trident.*rv\:11\./ ) ) {
			    if ( window.navigator.msSaveBlob ) {
				    let blob = new Blob( [ this.excelTabText ], {
					    type : "application/csv;charset=utf-8;"
				    } );
				    navigator.msSaveBlob( blob, this.fileName );
			    }
		    }
		    else {
			    let blob2 = new Blob( [ this.excelTabText ], {
				    type : "application/csv;charset=utf-8;"
			    } );
			    let elem = window.document.createElement( 'a' );
			    elem.href = window.URL.createObjectURL( blob2 );
			    elem.download = this.fileName;
			    document.body.appendChild( elem );
			    elem.click();
			    document.body.removeChild( elem );
		    }

	    },

	    //다운로드 로그를 남깁니다
	    async insertDocShareAudit( shareType, targetOidList ){

            let info = {
            	targetOidList : targetOidList,
	            email         : this.emailInput,
	            shareType     : shareType,
	            accessUrl     : this.$route.fullPath,
            }

		    const urlInsert = this.$urlConstant.API_URL_PREFIX.DOC_SHARE_AUDIT + this.$urlConstant.API_URL_SUFFIX.DOC_SHARE_AUDIT.INSERT;

	        await this.$axios.post( urlInsert, info ).then( res => {
	        	if ( res.status !==200 ){
	        		return;
		        }
	        });

		    this.close();
	    },

		//파일명을 가져옵니다
		getDocName(){
        	if ( this.$common.isNotEmpty( this.downloadDocInfo ) ){
		        if ( this.$common.isEmpty( this.downloadDocInfo.currentDocVersionInfo ) || this.$common.isEmpty( this.downloadDocInfo.currentDocVersionInfo.docFileInfo ) ) {
			        return this.downloadDocInfo.title;
		        }
		        else {
			        return this.downloadDocInfo.customField2;
		        }
	        }

        	return this.fileName;
		},

	    getProductOidList(){
        	if( this.$common.isEmpty( this.productList ) ){
        		return;
	        }
		    return this.productList.map( product => product.oid ).toString();
	    }
    },
};
</script>

<style></style>
