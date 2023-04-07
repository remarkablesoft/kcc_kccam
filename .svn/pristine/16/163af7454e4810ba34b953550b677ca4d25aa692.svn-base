<template>
    <div class="input-row-md">
        <div class="input-label">
            <span>관련 문서</span>
        </div>
        <div class="input-data">
            <!-- table -->
            <div class="manager-table-normal">
                <div class="table-header">
                    <div class="btn-wrap-md">
                        <el-button type="primary" size="small" @click="addRelatedDocumentModalOpen()">추가</el-button>
                        <el-button type="gray" size="small" @click="removeDocList">삭제</el-button>
                    </div>
                </div>
                <div class="table-body">
                    <table>
                        <colgroup>
                            <col style="width:8%" />
                            <col style="width:%" v-if="$common.isEmpty(type)"/>
                            <col style="width:27%" />
                            <col style="width:15%" />
                            <col style="width:15%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th scope="col"><span>선택</span></th>
                                <th scope="col" v-if="$common.isEmpty(type)"><span>문서제목</span></th>
                                <th scope="col"><span>파일명</span></th>
                                <th scope="col"><span>파일크기</span></th>
                                <th scope="col"><span>문서타입</span></th>
                            </tr>
                        </thead>

                        <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if="$common.isEmpty( setDocList )">
                                <td colspan="5">
                                    <!-- no-data(loading) -->
                                    <div class="no-data" v-show="false">
                                        <div class="loading-sm">
                                            <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                        </div>
                                        <p>데이터 로딩중입니다.</p>
                                    </div>
                                    <!-- no-data -->
                                    <div class="no-data" v-show="true">
                                        <i class="material-icons">error_outline</i>
                                        <p>데이터가 없습니다.</p>
                                    </div>
                                </td>
                            </tr>
                            <tr v-for="(item, i) in setDocList" :key="i" class="list-item" v-else>
                                <td>
                                    <div class="custom-checkbox">
                                        <input :id="item.oid + i" v-model="item.isChecked" type="checkbox" :key="item.oid + i" />
                                        <label :for="item.oid + i">
                                            <i></i>
                                            <!-- <span>텍스트</span> -->
                                        </label>
                                    </div>
                                </td>
                                <td v-if="$common.isEmpty(type)" @click="setChecked( item )">
                                    <span v-text="item.title"></span>
                                </td>
                                <td @click="setChecked( item )">
                                    <span v-text="item.customField2"></span>
                                </td>
                                <td @click="setChecked( item )">
                                    <span v-text="setDocFileSize( item )"></span>
                                </td>
                                <td @click="setChecked( item )">
                                    <span v-text="$amConstant.SFA_DOC_KIND[item.docType]"></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- 관련문서 추가 모달 -->
        <the-add-related-document-modal ref="addRelatedDocumentModal"
                                        @addDocList="addDocList"
                                        @addCheckedSFADocList="addCheckedSFADocList"
                                        :type="type"
        >
        </the-add-related-document-modal>
    </div>
</template>

<script>
import theAddRelatedDocumentModal from "~/components/kccam/manager/modal/TheAddRelatedDocumentModal.vue";

export default {
    props: {
        docList: {
            type: Array,
            required: false,
            default : function() {
                return [];
            }
        },
        type: {
            type: String,
            required: false,
            default : ""
        }
    },
    components: {
        theAddRelatedDocumentModal,
    },
    data() {
        return {
            setDocList : [],

        }
    },
    mounted() {
        if ( this.$common.isEmpty( this.setDocList ) ) {
            this.setDocList = _.cloneDeep(this.docList);
        }
    },
    watch : {
        docList: {
            deep : true,
            handler() {
                this.setDocList = this.setDocListCheck( this.docList );
            }
        }
    },
    methods: {
        // 관련문서 추가 모달 열기
        addRelatedDocumentModalOpen() {
            this.$refs.addRelatedDocumentModal.open();
            this.$refs.addRelatedDocumentModal.removeAllFiles();
        },

        /**
         * 문서 리스트에 체크박스 값을 설정해줍니다.
         */
        setDocListCheck( docList ) {
            if ( this.$common.isEmpty( docList ) ) {
                return [];
            }

            _.each( docList, function( doc ) {
                doc["isChecked"] = false;
            });

            return docList;
        },

        /**
         * 파일 사이즈를 설정합니다.
         */
        setDocFileSize(doc) {
	        let fileSize = 0;
	        // console.log( "setDocFileSize", doc );
	        //추가 모달창에서 들어온 파일 세팅
	        if ( this.$common.isNotEmpty( doc.currentDocVersionInfo ) ){
	        	if ( this.$common.isNotEmpty( doc.currentDocVersionInfo.docFileInfo ) ) {
			        return this.$common.formatBytes( doc.currentDocVersionInfo.docFileInfo.fileSize );
		        }
	        	else if ( this.$common.isNotEmpty( doc.currentDocVersionInfo.outLinkUrl ) ){
					return this.$amConstant.OUTER_FILE;
		        }
	        }

            if ( doc.docFileSize === 0 ){
	            return this.$amConstant.OUTER_FILE;
            }

	        fileSize = doc.docFileSize;

            return this.$common.formatBytes(fileSize, 1);

        },

        /**
         * 문서 리스트에 문서정보를 추가합니다.
         * @param docFile
         */
        addDocList( docFile ) {
            if ( this.existFile( docFile ) ) {
                this.$common.confirmSwal( "중복 등록된 파일이므로 기존파일로 대체됩니다.", "", "info" );
                docFile['onlyDocRel'] = true;
            }

            if ( this.$common.isNotEmpty(this.type) ) {
                this.setDocList = [];
            }

            // console.log( "addDocList", docFile );
            // return;
            this.setDocList.push( docFile );
            this.setDocList =  this.setDocListCheck( this.setDocList );

            if ( this.$common.isNotEmpty(this.type) ) {
                this.$emit("addDoc", this.setDocList);
            }
        },

        /**
         * 중복파일 체크
         */
        existFile( docFile ) {

            const url = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.EXIST_FILE;

	        // console.log( docFile );
	        // return;

            let fileCnd = {
                fileName : docFile.currentDocVersionInfo.docFileInfo.fileName,
                fileSize : docFile.currentDocVersionInfo.docFileInfo.fileSize
            };

            let result = false;
            this.$axios.post( url, fileCnd ).then( ( res ) => {
                // console.log( res );
                if ( this.$common.isNotEmpty( res.data ) ) {
                    result = true;
                }
            });

            return result;
        },

        /**
         * 문서 리스트에 SFA 문서정보를 추가합니다.
         */
        addCheckedSFADocList( sfaDocList ) {

            if ( this.$common.isEmpty( sfaDocList ) ) {
                return;
            }

            if ( this.$common.isNotEmpty(this.type) ) {
                this.setDocList = [];
            }


            if ( this.setDocList.length > 0 ) {
                this.setDocList = [...this.setDocList, ...sfaDocList];
            } else {
                this.setDocList = sfaDocList;
            }

            if ( this.$common.isNotEmpty(this.type) ) {
                this.$emit("addDoc", this.setDocList);
            }
        },

        /**
         * 문서리스트에 체크된 문서를 삭제합니다.
         */
        removeDocList() {
            if ( this.$common.isEmpty( this.setDocList ) ) {
                return;
            }

            let removedDocList = [];
            _.each( this.setDocList, function( doc ) {
                if ( !doc.isChecked ) {
                    removedDocList.push( doc );
                }
            });

            this.setDocList = removedDocList;
        },

        /**
         * 문서 파일정보를 반환합니다.
         */
        getDocInfoList() {
            return this.setDocList;
        },
		setChecked( item ) {
        	item.isChecked = !item.isChecked;
        	this.$forceUpdate();
		}

    },
};
</script>
