<template>
	<div class="inner-wrapper">
		<!-- loading -->
        <div class="loading-container" v-if="pageLoading">
            <the-loading />
        </div>
		<!-- manager-content-body -->
		<div class="manager-content-body">
			<!-- contents 내용 -->
			<div class="content-title">
				<div class="sub-title">
					<h2>카탈로그 수정</h2>
				</div>
			</div>
			<div class="content-detail">
				<!-- btn -->
				<div class="btn-wrap-md">
					<el-button type="primary" size="default" @click="saveCatalogInfo()">저장</el-button>
					<el-button type="gray" size="default" @click="remove()">삭제</el-button>
					<el-button type="st st-primary" size="default" @click="goList()">목록</el-button>
				</div>
				<!-- 등록일 / 최종수정일 -->
				<div class="input-area" v-if="!$fetchState.pending">
					<div class="input-row-md lr">
						<div class="left-area">
							<div class="input-label">
								<span>등록일</span>
							</div>
							<div class="input-data">
								<span v-text="$common.formatDate(catalogInfo.inputDate)"></span>
							</div>
						</div>
						<div class="right-area">
							<div class="input-label">
								<span>최종 수정일</span>
							</div>
							<div class="input-data">
								<span v-if="catalogInfo.modDate" v-text="$common.formatDate(catalogInfo.modDate)"></span>
								<span v-else>-</span>
							</div>
						</div>
					</div>
					<!-- 카탈로그명 -->
					<div class="input-row-md">
						<div class="input-label">
							<span>카탈로그명</span>
						</div>
						<div class="input-data">
							<el-input placeholder="" v-model="catalogInfo.title" clearable></el-input>
						</div>
					</div>
					<!-- 첨부파일 -->
<!--					<div class="input-row-md">-->
<!--						<div class="input-label">-->
<!--							<span>첨부파일</span>-->
<!--						</div>-->
<!--						<div class="input-data">-->
<!--							&lt;!&ndash; dropzone &ndash;&gt;-->
<!--							<the-dropzone-->
<!--								class="input-dropzone"-->
<!--								ref="dropzoneFile"-->
<!--								@setUploadFile="setStorageFileList"-->
<!--								:fileType="$amConstant.OBJECT_TYPE.ONE_TO_ONE"-->
<!--							/>-->
<!--						</div>-->
<!--					</div>-->
<!--					&lt;!&ndash; 비고 &ndash;&gt;-->
<!--					<div class="input-row-md">-->
<!--						<div class="input-label">-->
<!--							<span>비고</span>-->
<!--						</div>-->
<!--						<div class="input-data note">-->
<!--							<el-input placeholder="" v-model="note" clearable></el-input>-->
<!--						</div>-->
<!--					</div>-->

					<div class="input-row-md">
						<div class="input-label">
							<span>대표 이미지</span>
						</div>
						<div class="input-data">
							<div class="w100">
								<p class="txt-info">* 이미지 최적화 사이즈 : 240X300, 최적화 사이즈에 맞게 올려주세요.</p>
								<!-- dropzone -->
								<the-dropzone
									class="input-dropzone"
									ref="dropzoneImage"
									acceptedFiles=".jpeg,.jpg,.png"
									@setUploadFile="setStorageImageFile"
									@dropzoneMounted="imageDropzoneMounted"
								/>
							</div>
						</div>
					</div>
<!--					<div class="input-row-md">-->
<!--						<div class="input-label">-->
<!--							<span>첨부파일</span>-->
<!--						</div>-->
<!--						<div class="input-data">-->
<!--							&lt;!&ndash; dropzone &ndash;&gt;-->
<!--							<the-dropzone-->
<!--								class="input-dropzone"-->
<!--								ref="dropzoneFile"-->
<!--								@setUploadFile="setStorageFileList"-->
<!--								@dropzoneMounted="fileDropzoneMounted"-->
<!--							/>-->
<!--						</div>-->
<!--					</div>-->
					<!-- 관련 문서 열기 -->
					<the-related-doc ref="relDoc" :doc-list="docList" type="catalog"/>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import theDropzone from "~/components/common/dropzone/TheDropZone.vue";
import theRelatedDoc from "~/components/common/board/manager/TheRelatedDoc.vue";

export default {
	layout     : "managerLayout",
	components : {
		theLoading,
		theDropzone,
	},
	data() {
		return {
			//카탈로그명
			catalogName : "",

			//비고
			note : "",

			catalogInfo : {},

			// 관련 문서 목록
			docList : [],

			// 이미지 파일 정보
			imgFile: [],

			dropzoneMount: false,

			//로딩 indicator
			pageLoading : false,

		};
	},
	async fetch() {

		this.pageLoading = true;
		this.catalogInfo = await this.getCatalogInfo();
		this.pageLoading = false;
	},

	watch: {
		dropzoneMount() {
			if ( this.dropzoneMount ) {
				if( this.$common.isNotEmpty( this.catalogInfo ) && this.$common.isNotEmpty( this.catalogInfo.currentDocVersionInfo ) && this.$common.isNotEmpty( this.catalogInfo.currentDocVersionInfo.iconFileInfo ) ) {
					let fileList = [];

					fileList.push( this.catalogInfo.currentDocVersionInfo.iconFileInfo );

					this.$refs.dropzoneImage.setUploadFileList( fileList );
				}
			}
		}
	},
	methods : {
		// 목록 페이지로 이동
		goList() {
			this.$router.push( this.localePath( "/kccam/manager/documentMgnt/catalog/catalog_list" ) );
		},

		async getCatalogInfo() {
			const URL_GET = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.GET

			let param = {
				oid : this.$route.query.oid,
			}

			return await this.$axios.post( URL_GET, param ).then( ( res ) => {
				this.docList = [];
				this.docList.push(res.data);
				return res.data;
			} ).catch();
		},

		async saveCatalogInfo() {
			const URL_SAVE = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.SAVE

			let param = _.cloneDeep( this.catalogInfo );

			let doc = this.$refs.relDoc.getDocInfoList();
			console.log("doc", doc)
			if( this.$common.isEmpty( doc ) ) {
				this.$common.confirmSwal( "첨부할 문서 파일을 선택해주세요.", "", "warning" );
				return;
			}

			let newVersion = doc[0].currentDocVersionInfo;

			param.currentDocVersionInfo.docFileInfo = newVersion.docFileInfo;
			param.currentDocVersionInfo.docNo = newVersion.docNo;
			param.currentDocVersionInfo.outLinkUrl = newVersion.outLinkUrl;

			param.customField1 = doc[0].customField1;
			param.customField2 = doc[0].customField2;
			param.docType = doc[0].docType;

			if ( this.$common.isNotEmpty( this.imgFile ) ) {
				param.currentDocVersionInfo.iconFileInfo = this.imgFile[0];
			}
			else {
				param.currentDocVersionInfo.iconFileInfo = {};
			}

			await this.$axios.post( URL_SAVE, param ).then( ( res ) => {
				// console.log("result", res.data)
				this.catalogInfo = res.data;

				this.goList();

			} ).catch();
		},

		/**
		 * 대표 이미지 설정
		 * @param data
		 */
		setStorageImageFile( data) {
			this.imgFile = data;
		},

		imageDropzoneMounted() {
			this.dropzoneMount = true;
		},

		/**
		 * 첨부파일 세팅
		 */
		setStorageFileList( data ) {
			this.catalogInfo.currentDocVersionInfo.docFileInfo = data;
			console.log( this.catalogInfo );
		},

		fileDropzoneMounted( data ) {

		},

		async remove() {
		    await this.$common.swalWithOptions("삭제 확인", "이 카탈로그를 삭제하시겠습니까?", "info").then(isDelete => {
			    if (isDelete) {
				    const URL_DELETE = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.DELETE;

				    let param = {
					    oid: this.$route.query.oid,
				    };

				    this.$axios.post(URL_DELETE, param).then(res => {
					    if (1 === res.data) {

							this.goList();
					    }
					    else {
						    console.log( res );
					    }
				    }).catch( error => {
					    console.log( error );
				    });
			    }
		    });
	    },

	},
};
</script>
<style scoped></style>
