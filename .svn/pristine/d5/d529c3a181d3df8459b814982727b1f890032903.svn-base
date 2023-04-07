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
                    <h2>첨부문서 수정</h2>
                </div>
            </div>
            <div class="content-detail">
                <!-- btn -->
                <div class="btn-wrap-md">
                    <el-button type="primary" size="default" @click="saveDocInfo()">저장</el-button>
                    <el-button type="gray" size="default" @click="remove()">삭제</el-button>
                    <el-button type="st st-primary" size="default" @click="goList()">목록</el-button>
                </div>
                <div class="input-area" v-if="$common.isNotEmpty(docInfo)">
                    <div class="input-row-md lr">
                        <div class="left-area">
                            <div class="input-label">
                                <span>등록일</span>
                            </div>
                            <div class="input-data">
	                            <span v-text="$common.isNotEmpty(docInfo.inputDate) ? $common.formatDate(docInfo.inputDate) : ''"></span>
                            </div>
                        </div>
                        <div class="right-area">
                            <div class="input-label">
                                <span>최종 수정일</span>
                            </div>
                            <div class="input-data">
	                            <span v-text="$common.isNotEmpty(docInfo.modDate) ? $common.formatDate(docInfo.modDate) : ''"></span>
                            </div>
                        </div>
                    </div>
                    <div class="input-row-md">
                        <div class="input-label">
                            <span>문서명</span>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="docInfo.title" clearable> </el-input>
                        </div>
                    </div>
                    <div class="input-row-md">
                        <div class="input-label">
                            <span>문서타입</span>
                        </div>
                        <div class="input-data">
                            <!-- <el-select class="select-half" v-model="docInfo.docType" placeholder="선택">
                                <el-option v-for="item in docTypeMap" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                            </el-select> -->
                            <el-input placeholder="" v-model="$amConstant.SFA_DOC_KIND[docInfo.docType]" :readonly="true"></el-input>
                        </div>
                    </div>
                    <div class="input-row-md">
                        <div class="input-label">
                            <span>첨부대상 타입</span>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="docInfo.docType" :readonly="true"> </el-input>
                        </div>
                    </div>
                    <div class="input-row-md">
                        <div class="input-label">
                            <span>첨부대상</span>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="docInfo.docRelList[0].targetObject" :readonly="true"> </el-input>
                        </div>
                    </div>
                    <!-- <div class="input-row-md">
                        <div class="input-label">
                            <span>첨부파일</span>
                        </div>
                        <div class="input-data">
                            <the-dropzone
                                class="input-dropzone"
                                ref="dropzoneFile"
                                @setUploadFile="setStorageFileList"
                                :fileType="$amConstant.OBJECT_TYPE.ONE_TO_ONE"
                            />
                        </div>
                    </div> -->
	                <!-- 관련 문서 열기 -->
	                <the-related-doc ref="relDoc"
                        :doc-list="docList"
                        type="attachment"
                        @addDoc="addDoc"
                    />

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
    layout: "managerLayout",
    components: {
        theLoading,
        theDropzone,
	    theRelatedDoc,
    },
    data() {
        return {
            //문서명
            documentName: "",

            //문서타입
            documentType: [
                {
                    value: "INTRODUCTION",
                    label: "INTRODUCTION",
                },
                {
                    value: "Option2",
                    label: "Option2",
                },
                {
                    value: "Option3",
                    label: "Option3",
                },
            ],

            //첨부대상 타입
            attachmentType: "",

            //첨부대상
            attachmentTarget: "",

	        // 문서타입 Map
	        docTypeMap : {},

	        oid: "",

	        // 문서 정보 전체
	        docInfo : [],

	        // 관련 문서
	        docList : [],

	        //로딩 indicator
	        pageLoading : false,
        };
    },
	created() {
		if (this.$route.query.oid) {
			this.oid = this.$route.query.oid;
		}
	},
	async fetch() {

    	this.pageLoading = true;

		await this.getDocInfo();
		this.setDocumentType();

		this.pageLoading = false;
	},
    methods: {

        // 목록 페이지로 이동
        goList() {
            this.$router.push( this.localePath( "/kccam/manager/documentMgnt/attachment/attachment_list" ) );
        },

	    /**
	     *  문서타입 Map 을 생성합니다.
	     */
	    setDocumentType() {
		    let tempObj = this.$amConstant.SFA_DOC_KIND;
		    let tempList = [];
		    for( let items in tempObj ) {
			    let tempArr = {};
			    tempArr.value = items;
			    tempArr.label = tempObj[ items ];
			    tempList.push( tempArr );
		    }
		    this.docTypeMap = tempList;
	    },

	    /**
	     *  첨부 문서 관련 정보를 가져옵니다.
	     */
	    async getDocInfo() {
		    let cnd = {
		    	oid : this.oid,
			    fillFile: true,
			    onlyCurrentVersion: true,
			    docType : this.docType,
		    };
		    const url = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.GET;

		    await this.$axios.post( url, cnd ).then( res => {

			    if (this.$common.isEmpty( res.data ) ) {
                    this.docInfo = [];
				    return;
			    }
                this.docList.push(res.data);
			    this.docInfo = res.data;
		    });
	    },

        async saveDocInfo() {
			const URL_SAVE = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.SAVE

			let param = _.cloneDeep( this.docInfo );

			let doc = this.$refs.relDoc.getDocInfoList();
			if( this.$common.isEmpty( doc ) ) {
				this.$common.confirmSwal( "첨부할 문서 파일을 선택해주세요.", "", "warning" );
				return;
			}

			let newVersion = doc[0].currentDocVersionInfo;

			param.currentDocVersionInfo.docFileInfo = newVersion.docFileInfo;
			param.currentDocVersionInfo.docNo = newVersion.docNo;
			param.currentDocVersionInfo.outLinkUrl = newVersion.outLinkUrl;

			param.customField1 = doc[0].docType.substring(0,1);
			param.customField2 = doc[0].customField2;
			param.docType = doc[0].docType;

			await this.$axios.post( URL_SAVE, param ).then( ( res ) => {
				this.docInfo = res.data;

				this.goList();

			} ).catch();
		},

        async remove() {
		    await this.$common.swalWithOptions("삭제 확인", "이 첨부문서를 삭제하시겠습니까?", "info").then(isDelete => {
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

        addDoc( docList ) {

            if ( this.$common.isEmpty( docList ) ) {
                return;
            }

            this.docInfo.docType = docList[0].docType;
        }

    },
};
</script>

<style scoped></style>
