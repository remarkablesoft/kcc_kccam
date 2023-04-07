<template>
    <div>
        <!-- 관련문서 추가 모달 -->
        <el-dialog title="관련문서 추가" :visible.sync="dialogVisible" width="90rem">
            <div class="radio-group">
                <div class="custom-radio" v-for="(item, i) in radioGroup" :key="i">
                    <input :id="item.id" type="radio" name="radioGroup" v-model="selected" :value="item.id" />
                    <label :for="item.id">
                        <i></i>
                        <span v-text="item.text"><!--텍스트--></span>
                    </label>
                </div>
            </div>
            <div class="inner-content-group">
                <!-- 직접등록 -->
                <div class="inner-content" v-show="selected === 'submitType01'">
                    <div class="search-area">
                        <!-- 문서타입 -->
                        <div class="input-row">
                            <div class="label" v-if="$common.isEmpty(type)">
                                <span class="input-tit">문서제목</span>
                            </div>
                            <div class="data" v-if="$common.isEmpty(type)">
                                <el-input size="large" placeholder="제목 입력" v-model="inputDocTitle"> </el-input>
                            </div>
                            <div class="label">
                                <span class="input-tit">문서타입</span>
                            </div>
                            <div class="data">
                                <el-select size="large" class="w100" v-model="selectedDocumentType" placeholder="선택">
                                    <el-option v-for="(item, index) in documentType" :key="index" :label="item" :value="index"> </el-option>
                                </el-select>
                            </div>
                        </div>
                    </div>
                    <div class="content-detail">
                        <!-- dropzone -->
                        <the-dropzone class="input-dropzone" ref="dropzoneFile" accepted-files=".pdf,.html,.xls" @setUploadFile="setStorageDocFile" @dropzoneMounted="dropzoneMounted" />
                    </div>
                </div>

                <!-- 제품정보에서 가져오기 -->
                <div class="inner-content" v-show="selected === 'submitType02'">
                    <!--search area -->
                    <div class="search-area">
                        <!-- 제품명 -->
                        <div class="input-row">
                            <div class="label">
                                <span class="input-tit">검색어</span>
                            </div>
                            <div class="data">
                                <el-input size="large" placeholder="제품명 또는 제품코드 입력" prefix-icon="el-icon-search" v-model="productName" @keypress.enter.native="search" clearable> </el-input>
                            </div>
                        </div>
                        <!-- 제품코드 -->
<!--                        <div class="input-row">-->
<!--                            <div class="label">-->
<!--                                <span class="input-tit">제품코드</span>-->
<!--                            </div>-->
<!--                            <div class="data">-->
<!--                                <el-input size="large" placeholder="" prefix-icon="el-icon-search" v-model="productCodeName" clearable> </el-input>-->
<!--                            </div>-->
<!--                        </div>-->
                        <!-- 검색 버튼 -->
                        <div class="btn-wrap">
                            <el-button class="search-btn" type="gray" size="default" @click="search">검색</el-button>
                        </div>
                    </div>

                    <!-- list-area -->
                    <div class="list-area">
                        <div class="manager-table-normal">
                            <div class="table-body">
                                <table>
                                    <colgroup>
                                        <col style="width:8%" />
                                        <col style="width:15%" />
                                        <col style="width:15%" />
                                        <col style="width:auto" />
                                    </colgroup>
                                    <thead>
                                        <tr class="bg-lgray">
                                            <th><span>선택</span></th>
                                            <th><span>제품명</span></th>
                                            <th><span>제품코드</span></th>
                                            <th><span>설명</span></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <!-- no-data :: tr -->
	                                    <tr v-if="$common.isEmpty( productInfoList )">
		                                    <td colspan="6">
<!--			                                     no-data(loading)-->
			                                    <div class="no-data" v-if="SFALoading">
				                                    <div class="loading-sm">
					                                    <img src="@/assets/images/loading/loading_sm.svg" alt="Loading"/>
				                                    </div>
				                                    <p>데이터 로딩중입니다.</p>
			                                    </div>
			                                    <!-- no-data -->
			                                    <div class="no-data" v-if="!SFALoading">
				                                    <i class="material-icons">error_outline</i>
				                                    <p>데이터가 없습니다.</p>
			                                    </div>
		                                    </td>
	                                    </tr>
                                        <tr v-for="(item, i) in productInfoList" :key="i" class="list-item" v-else>
                                            <td >
                                                <div class="custom-radio">
                                                    <input :id="item.com_base_seq" type="radio"  name="listRadioGroup" :value="item" v-model="selectedProduct" :key="item.com_base_seq"/>
                                                    <label :for="item.com_base_seq">
                                                        <i></i>
                                                        <!-- <span>텍스트</span> -->
                                                    </label>
                                                </div>
                                            </td>
                                            <td @click="setProductSelected(item)">
                                                <span v-text="item.main_items_nm"></span>
                                            </td>
                                            <td @click="setProductSelected(item)">
                                                <span v-text="item.items"></span>
                                            </td>
	                                        <td @click="setProductSelected(item)">
                                                <v-clamp :max-lines="2" class="txt">{{item.items_out_line}}</v-clamp>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <!-- 페이지네이션 -->
                                <thePagination
                                    v-if="listCount > 0"
                                    :visible-buttons-count="8"
                                    :total-count="listCount"
                                    :page-size="pageSize"
                                    :current-page="currentPage"
                                    page-comp="docPage"
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" size="default" v-show="selected === 'submitType01'" @click="addDocFile">확인</el-button>
                <el-button type="primary" size="default" v-show="selected === 'submitType02'" @click="getSFADocList">문서 목록 보기</el-button>
                <el-button size="default" @click="dialogVisible = false">취소</el-button>
            </div>
        </el-dialog>

        <!-- 문서 목록 -->
        <el-dialog :title="this.selectedProduct.main_items_nm + ' 문서 목록'" :visible.sync="docListDialogVisible" width="80rem">
            <div class="inner-content">
                <!-- list-area -->
                <div class="list-area">
                    <div class="manager-table-normal">
                        <div class="table-body">
                            <table>
                                <colgroup>
                                    <col style="width:8%" />
                                    <col style="width:20%" />
                                    <col style="width:13%" />
                                    <col style="width:28%" />
                                    <col style="width:%" />
                                </colgroup>
                                <thead>
                                    <tr class="bg-lgray">
                                        <th><span>선택</span></th>
                                        <th><span>문서타입</span></th>
                                        <th><span>언어</span></th>
                                        <th><span>문서제목</span></th>
                                        <th><span>문서명</span></th>
                                    </tr>
                                </thead>
                            </table>

                            <!-- scroll area -->
                            <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                <!-- el1 -->
                                <div>
                                    <!-- el2 -->
                                    <table>
                                        <colgroup>
                                            <col style="width:8%" />
                                            <col style="width:20%" />
                                            <col style="width:13%" />
                                            <col style="width:28%" />
                                            <col style="" />
                                        </colgroup>
                                        <tbody>
                                            <!-- no-data :: tr -->
                                            <tr v-if="$common.isEmpty(docList)">
                                                <td colspan="5">
                                                    <!-- no-data(loading) -->
                                                    <div class="no-data" v-if="docListLoading">
                                                        <div class="loading-sm">
                                                            <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                                        </div>
                                                        <p>데이터 로딩중입니다.</p>
                                                    </div>
                                                    <!-- no-data -->
                                                    <div class="no-data" v-if="!docListLoading">
                                                        <i class="material-icons">error_outline</i>
                                                        <p>데이터가 없습니다.</p>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr v-for="(item, index) in docList" :key="index" class="list-item" v-else>
                                                <td>
                                                    <div class="custom-checkbox">
                                                        <input :id="index" :key="index" v-model="item.isCheckedDoc" type="checkbox" name="docListCheckGroup" />
                                                        <label :for="index">
                                                            <i></i>
                                                            <!-- <span>텍스트</span> -->
                                                        </label>
                                                    </div>
                                                </td>
                                                <td @click="setDocSelected(item)">
													<span v-text="item.docKindNm"></span>
                                                </td>
												<td @click="setDocSelected(item)">
													<span v-text="item.lang"></span>
                                                </td>
												<td @click="setDocSelected(item)">
													<span v-text="item.docTitle"></span>
                                                </td>
												<td @click="setDocSelected(item)">
													<span v-text="item.docNm"></span>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" size="default" @click="addCheckedSFADocList">추가</el-button>
                <el-button size="default" @click="docListDialogVisible = false">취소</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import theDropzone from "~/components/common/dropzone/TheDropZone.vue";
import thePagination from "~/components/common/ThePagination.vue";
import VClamp from "vue-clamp";

export default {
    props: {
        type: {
            type: String,
            required: false,
            default : ""
        }
    },
    components: {
        theDropzone,
        thePagination,
        VClamp
    },
    data() {
        return {
            dialogVisible: false,
            docFile: {},
            dropzoneMount: false,

            // radio
            radioGroup: [
                {
                    id: "submitType01",
                    text: "직접등록",
                },
                {
                    id: "submitType02",
                    text: "제품정보에서 가져오기",
                },
            ],
            selected: "submitType01",

            // 검색 :: 문서타입
            documentType: this.$amConstant.SFA_DOC_KIND,
            selectedDocumentType : "",

            productName: "", // 검색 :: 제품명
            productInfoList: [], // 제품정보 리스트
            selectedProduct : {},   // 선택된 제품

            startIndex: 1,
            listCount: 0,
            pageSize: 8,
            currentPage: 1,
			thisPage : 1,

            // 문서 목록 모달
            docListDialogVisible: false,

            // 문서 리스트
            docList: [],
            inputDocTitle : "",
	        page : 0,
	        docListLoading : false,
	        SFALoading : false,

        };
    },
    created() {},
    mounted() {
        if ( this.type === "catalog" ) {
            this.documentType = {"B02" : "카탈로그"}
        }
    },
    watch: {
        $route() {
	        if ( this.$route.query.docPage && ( parseInt( this.$route.query.docPage ) !== this.currentPage ) ) {
				this.onChangePage( parseInt ( this.$route.query.docPage ) );
	        }
        }
    },
    computed : {
    },
    methods: {
        open() {
            this.dialogVisible = true;
        },

        /**
         * dropzone으로부터 파일을 설정합니다.
         * @param data
         */
        setStorageDocFile(data) {
            this.docFile = data[0];
        },

        /**
         * 문서정보를 가져옵니다.
         */
        addDocFile() {

            if ( this.$common.isEmpty( this.inputDocTitle ) && this.$common.isEmpty(this.type) ) {
                this.$common.confirmSwal( "문서 제목을 입력해주세요.", "", "warning" );
                return;
            }

            if ( this.$common.isEmpty( this.selectedDocumentType ) ) {
                this.$common.confirmSwal( "문서타입을 선택해주세요.", "", "warning" );
                return;
            }
			// console.log( "modal this.docFile",this.docFile );
            let docInfo = {
                currentDocVersionInfo : {
                    docFileInfo : this.docFile,
                    statusTypeFlag : this.$amConstant.YN.Y
                },

                docType : this.selectedDocumentType,
                customField1 : this.selectedDocumentType.substring( 0, 1 ),
                customField2 : this.docFile.fileName,
                isChecked : false,
                title : this.inputDocTitle
            }

            this.$emit("addDocList", docInfo ); // 담았다.
            this.dialogVisible = false;
        },

        dropzoneMounted() {
	        this.dropzoneMount = true;
        },

        removeAllFiles() {
            if ( this.dropzoneMount ) {
                this.$refs.dropzoneFile.removeAllFiles();
            }
        },
		search() {
        	this.$route.query.docPage = 1;
        	this.onChangePage(1);
		},
		/**
		 *  페이지 이동 처리
		 */
		onChangePage( page ) {
			this.currentPage = page;
			this.thisPage = ( parseInt ( this.currentPage ) - 1) * parseInt( this.pageSize ) + 1;
			this.getSFAItemList( this.thisPage );
		},

        /**
         * 제품정보 제품 검색 리스트를 가져옵니다.
         */
        getSFAItemList( startIndex ) {

            if ( startIndex < 1 ) {
                return;
            }

			let sfaSearchParam = {
                pageNum : startIndex,
                pageSize : this.pageSize,
                colLang : "kor",
                searchData : this.productName,
                isFavSearch : false
            }

            let url = this.$urlConstant.KCC_SFA_URL.ITEM_SEARCH;
            url += "?param=" + JSON.stringify( sfaSearchParam );
			this.productInfoList = [];
	        this.SFALoading = true;
	        this.$axios.post( url ).then( res => {
				// console.log( res.data.SCN_SMB_ITEMS_DESCR );
				// console.log( "total Count : " + res.data.SCN_SMB_ITEMS_DESCR_TOTAL_CNT );
				this.listCount = Number( res.data.SCN_SMB_ITEMS_DESCR_TOTAL_CNT );
	        	if( this.$common.isEmpty( res.data.SCN_SMB_ITEMS_DESCR ) ) {
			        this.productInfoList = [];
			        this.SFALoading = false;
			        return;
		        }
                if ( 200 === res.status && this.$common.isNotEmpty( res.data.SCN_SMB_ITEMS_DESCR ) ) {
                    this.productInfoList = res.data.SCN_SMB_ITEMS_DESCR;
                    this.SFALoading = false;
                }
            }).catch( error => {
	            // console.log( error );
	            this.SFALoading = false;
            });
        },

        /**
         * SFA 문서 리스트를 가져옵니다.
         */
        getSFADocList() {

            if ( this.$common.isEmpty( this.selectedProduct ) ) {
                this.$common.swalWithOptions("문서정보를 찾을 제품을 선택해주세요.", "", "warning");
                return;
            }
            this.docList = [];
            this.docListLoading = true;
            // 팝업 로드
            this.docListDialogVisible = true;

            // Param Object로 호출 시 정상 동작 하지 않아 문자열로 URL 호출
            let url = this.$urlConstant.KCC_SFA_URL.REL_DOC;
            url += "?comBaseSeq=" + this.selectedProduct.com_base_seq;
            url += "&startIndex=" + 1;
            url += "&pageSize=" + 9999;

	        this.$axios.post( url ).then( res => {
	        	if( this.$common.isEmpty( res.data.relDocList ) ) {
	        		this.docList = [];
	        		this.docListLoading = false;
	        		return;
		        }
	            if ( 200 === res.status && this.$common.isNotEmpty( res.data.relDocList ) ) {
                    _.each( res.data.relDocList, function( item ) {
                        item['isCheckedDoc'] = false;
                    });
		            this.docListLoading = false;
                    this.docList = res.data.relDocList;
                    if ( this.type === "catalog") {
                        this.filterCatalog( this.docList );
                    }
                }

            }).catch( error => {
            	// console.log( error );
		        this.docListLoading = false;
            });
        },

        /**
         * 체크된 SFA 문서를 반환합니다.
         */
        addCheckedSFADocList() {
            let checkedDocList = [];

            const _self = this;
            _.each( this.docList, function( doc ) {
                if ( doc.isCheckedDoc ) {
                    let formattedDoc = _self.setSFADocFormat( doc );
                    checkedDocList.push( formattedDoc );
                }
            });

            if ( this.$common.isEmpty( checkedDocList ) ) {
                return;
            }

            if ( this.$common.isNotEmpty(this.type) && checkedDocList.length > 1 ) {
                this.$common.confirmSwal( "첨부파일 선택", "다중 선택은 불가능합니다.", "info" )
                return;
            }

            this.docListDialogVisible = false;
            this.dialogVisible = false;

            this.$emit( "addCheckedSFADocList", checkedDocList );
        },

        /**
         * SFA 제품문서의 포맷을 지금의 문서에 맞게끔 설정해줍니다.
         * @param doc
         */
        setSFADocFormat( doc ) {

            let filepath = this.setFilePath( doc );

            let docInfo = {
                currentDocVersionInfo : {
                    statusTypeFlag : this.$amConstant.YN.Y,
                    outLinkUrl : filepath,
                    docNo : doc.docNo,
                    docFileInfo : {}
                },

                docType : doc.docKind,
                customField1 : doc.docType,
                customField2 : doc.docNm,
                isCheckedDoc : false,
                title : doc.docTitle
            }

            return docInfo;
        },

        /**
         * 각각 다른 FilePath를 일정한 문자열로 맞춰 반환합니다.
         *
         * @param doc
         * @returns {string|*|string|string}
         */
        setFilePath( doc ) {

            if ( "http://".indexOf( doc.filePath ) !== -1 ) {
                return doc.filePath;
            }

            let filePath = "https://smartbook.kccworld.info";
            if ( "/file_upload/".indexOf( doc.filePath ) !== -1 ) {
                filePath += doc.filePath;
                return filePath;
            }

            filePath += "/file_upload/doc/" + doc.filePath;
            return filePath;
        },
	    setProductSelected( item ) {
        	this.selectedProduct = item;
	    },
		setDocSelected( item ) {
			item.isCheckedDoc = !item.isCheckedDoc;
		},

        filterCatalog( docList ) {
            let temp = _.cloneDeep( docList );

            this.docList = [];

            temp.forEach( doc => {
                if ( doc.docKindNm === "카다로그" ) {
                    this.docList.push(doc);
                }
            } )

        },
    }
};
</script>

<style></style>
