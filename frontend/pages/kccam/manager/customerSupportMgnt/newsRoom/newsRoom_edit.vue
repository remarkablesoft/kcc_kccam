<template>
    <div class="inner-wrapper">
        <!-- loading -->
<!--        <div class="loading-container" v-if="loadingIndicator > 0 || pageLoading">-->
<!--            <the-loading />-->
<!--        </div>-->

        <!-- manager-content-body -->
        <div class="manager-content-body">
            <div class="content-title mt">
                <div class="sub-title">
                    <h2>NEWSROOM 등록/수정</h2>
                </div>
            </div>
            <!--  content-detail -->
            <div class="content-detail">
                <!-- 언어 탭 버튼  -->
                <div class="btn-group bb no-tab">
                    <div class="btn-wrap tab-style">
                        <!-- 클릭한 버튼에 activ 클래스 추가 -->
<!--                            <el-button type="st st-gray" size="default" @click="menuActive($amConstant.LANG.KO)" :class="langObj[$amConstant.LANG.KO].active">KOR</el-button>-->
<!--                            <el-button type="st st-gray" size="default" @click="menuActive($amConstant.LANG.EN)" :class="langObj[$amConstant.LANG.EN].active">ENG</el-button>-->
<!--                            <el-button type="st st-gray" size="default" @click="menuActive($amConstant.LANG.CN)" :class="langObj[$amConstant.LANG.CN].active">CHN</el-button>-->
                    </div>
                    <div class="btn-wrap">
	                    <el-button type="st st-primary" size="default" @click="openPreview">미리보기</el-button>
                        <el-button type="primary" size="default" @click="save">저장</el-button>
                        <el-button type="gray" size="default" @click="deleteNewsroom" :class="[ $common.isEmpty($route.query.oid)? 'hidden': '' ]">삭제</el-button>
                        <el-button type="st st-primary" size="default" @click="goList()">목록</el-button>
                    </div>
                </div>
                <div class="input-area">
                    <div class="input-row-md lr" v-if="$route.query.oid">
                        <div class="left-area">
                            <div class="input-label">
                                <span>등록일</span>
                            </div>
                            <div class="input-data">
                                <span v-text=" $common.isEmpty(newsInfo.inputDate)? '-' : $common.formatDate(newsInfo.inputDate)"></span>
                            </div>
                        </div>
                        <div class="right-area">
                            <div class="input-label">
                                <span>최종 수정일</span>
                            </div>
                            <div class="input-data">
                                <span v-text=" $common.isEmpty(newsInfo.modDate)? '-' : $common.formatDate(newsInfo.modDate)"></span>
                            </div>
                        </div>
                    </div>

	                <div class="input-row-md">
		                <div class="input-label">
			                <span>언어<em class="required">*</em></span>
		                </div>
		                <div class="input-data">
			                <el-select size="sm" v-model="selectedLang" placeholder="선택">
				                <el-option v-for="item in langOptions" :key="item.value" :label="item.label"
				                           :value="item.value"></el-option>
			                </el-select>
		                </div>
	                </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>NEWS 명<em class="required">*</em></span>
                        </div>
                        <div class="input-data">
                            <el-input placeholder="" v-model="newsInfo.title" clearable> </el-input>
                        </div>
                    </div>

<!--                    <div class="input-row-md">-->
<!--                        <div class="input-label">-->
<!--                            <span>관련제품</span>-->
<!--                        </div>-->
<!--                        <div class="input-data">-->
<!--                            <div class="manager-search-box">-->
<!--                                <el-input placeholder="" v-model="relatedProductName" :disabled="true" clearable > </el-input>-->
<!--                                <el-button type="gray" class="btn-search" @click="addProductModalOpen()">-->
<!--                                    <span class="icon custom-icon-search"></span>-->
<!--                                </el-button>-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </div>-->

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>대표이미지</span>
                        </div>
                        <div class="input-data">
                            <!-- dropzone -->
                            <the-dropzone
	                            class="input-dropzone"
	                            ref="dropzoneFile"
	                            accepted-files=".jpeg,.jpg,.png"
	                            :maxFiles="1"
	                            :fileType="$amConstant.OBJECT_TYPE.NEWSROOM"
	                            @setUploadFile="setStorageImageFile"
	                            @dropzoneMounted="dropzoneMounted"
                            />
                        </div>
                    </div>

                    <div class="input-row-md">
                        <div class="input-label">
                            <span>내용</span>
                        </div>
                        <div class="input-data">
                            <!-- Froala editor -->
                            <editor ref="refContents"
                                    :html-contents="newsInfo.newsroomContents"
                                    @sendContentData="setContentData"
                            ></editor>
                        </div>
                    </div>
	                <div class="input-row-md">
		                <div class="input-label">
			                <span>관련 제품</span>
		                </div>
		                <div class="input-data">
			                <!-- table -->
			                <div class="manager-table-normal">
				                <div class="table-header">
					                <div class="btn-wrap-md">
						                <el-button type="primary" size="small" @click="addProductModalOpen()">추가</el-button>
						                <el-button type="gray" size="small" @click="removeProduct()">삭제</el-button>
					                </div>
				                </div>
				                <div class="table-body">
					                <table>
						                <colgroup>
							                <col style="width:8%" />
							                <col style="width:30%" />
							                <col style="width:30%" />
						                </colgroup>
						                <thead>
						                <tr class="bg-lgray">
							                <th scope="col"><span>선택</span></th>
							                <th scope="col"><span>제품명</span></th>
							                <th scope="col"><span>제품구분</span></th>
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
									                <col style="width:30%" />
									                <col style="width:30%" />
								                </colgroup>
								                <tbody>
								                <!-- no-data :: tr -->
								                <tr v-if="$common.isEmpty( newsInfo.productList )">
									                <td colspan="3">
										                <!-- no-data(loading) -->
										                <div class="no-data" v-if="$fetchState.pending">
											                <div class="loading-sm">
												                <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
											                </div>
											                <p>데이터 로딩중입니다.</p>
										                </div>
										                <!-- no-data -->
										                <div class="no-data" v-else>
											                <i class="material-icons">error_outline</i>
											                <p>데이터가 없습니다.</p>
										                </div>
									                </td>
								                </tr>
								                <tr v-for="(item, i) in newsInfo.productList" :key="item.oid + i" class="list-item">
									                <td>
										                <div class="custom-checkbox">
											                <input :id="item.oid" type="checkbox" v-model="item.isChecked" :value="item" />
											                <label :for="item.oid">
												                <i></i>
												                <!-- <span>텍스트</span> -->
											                </label>
										                </div>
									                </td>
									                <td @click="setChecked( item )">
										                <span v-text="item.name"></span>
									                </td>
									                <td @click="setChecked( item )">
										                <span v-text=" item.materialInfo.name "></span>
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

                    <!-- 팝업 기능 잠정적으로 사용하지 않음 -->
                    <div class="input-row-md " v-if="false">
                        <div class="input-label">
                            <span>메인 팝업 사용</span>
                        </div>
                        <div class="input-data">
                            <div class="radio-group no-bottom">
                                <div class="custom-radio" v-for="(item, i) in radioGroup" :key="i">
                                    <input :id="item.id" type="radio" name="popupYnRadioGroup" :value="item.id" v-if=" item.id === radioGroup[0].id " checked disabled/>
	                                <input :id="item.id" type="radio" name="popupYnRadioGroup" :value="item.id" v-else disabled/>
	                                <label :for="item.id">
                                        <i></i>
                                        <span v-text="item.text"><!--텍스트--></span>
                                    </label>
                                </div>
                            </div>
                            <div>
                                <el-button type="primary" size="small" @click="popupShowYn = true" disabled>미리보기</el-button>
                            </div>
                        </div>
	                    <div class="input-data">
		                    <div class="radio-group no-bottom">
			                    <div class="custom-radio" v-for="(item, i) in radioGroup" :key="i">
				                    <input :id="item.id" type="radio" name="popupYnRadioGroup" v-model="useMainPop" :value="item.id" />
				                    <label :for="item.id">
					                    <i></i>
					                    <span v-text="item.text"><!--텍스트--></span>
				                    </label>
			                    </div>
		                    </div>
		                    <div>
			                    <el-button type="primary" size="small" @click="popupShowYn = true" >미리보기</el-button>
		                    </div>
	                    </div>
                    </div>

	                <div v-if="useMainPop === radioGroup[1].id">
		                <div class="input-row-md lr">
		                    <div class="left-area">
		                        <div class="input-label">
		                            <span>팝업 노출 기간</span>
		                        </div>
		                        <div class="input-data">
		                            <el-date-picker size="large" v-model="popupInput" type="daterange" align="right" start-placeholder="시작일" end-placeholder="종료일" default-value="2010-10-01">
		                            </el-date-picker>
		                        </div>
		                    </div>
		                    <div class="right-area">
		                        <div class="input-label">
		                            <span>링크 URL</span>
		                        </div>
		                        <div class="input-data">
		                            <el-input placeholder="" v-model="linkInput" clearable> </el-input>
		                        </div>
		                    </div>
		                </div>

		                <div class="input-row-md">
		                    <div class="input-label">
		                        <span>팝업 내용</span>
		                    </div>
		                    <div class="input-data">
		                        <el-input type="textarea" :rows="5" placeholder="내용을 입력하세요." v-model="descr"> </el-input>
		                    </div>
		                </div>
	                </div>
                </div>
            </div>
        </div>
        <!-- 제품 추가 모달 -->
<!--        <the-add-single-product-modal ref="addProductModal"-->
<!--                                      @addRelatedProductList ="addRelatedProductList">-->

<!--        </the-add-single-product-modal>-->

        <!-- 제품 추가 모달 -->
	    <the-add-product-modal ref="addProductModal"
	                           :product-check-type="productCheckType"
	                           :product-list="newsInfo.productList"
	                           :removed-product-list="removedProductList"
	                           :current-lang="$amConstant.LANG.KO"
	                           @addProductList="setAddProductList">
	    </the-add-product-modal>

        <!-- 팝업 -->
        <the-main-popup v-if="popupShowYn" :main-popup-visible="popupShowYn" @close="closePopup()" />

	    <the-newsroom-preview-modal v-if="previewVisible"
	                                :preview-popup-visible="previewVisible"
	                                :newsInfo="newsInfo"
	                                @close="closePreview()"/>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theDropzone from "~/components/common/dropzone/TheDropZone.vue";
import editor from "~/components/common/board/TheEditorScript.vue";
import theAddProductModal from "~/components/kccam/manager/modal/TheAddProductModal.vue";
import theAddSingleProductModal from "~/components/kccam/manager/modal/TheAddSingleProductModal";
import theMainPopup from "~/components/kccam/user/modal/TheMainPopup.vue";
import theNewsroomPreviewModal from "~/components/kccam/manager/modal/TheNewsroomPreviewModal.vue";

export default {
    layout: "managerLayout",
    components: {
        theLoading,
        thePagination,
        theDropzone,
        editor,
        theAddProductModal,
        theMainPopup,
    },
    data() {
        return {

        	//언어 select box용
	        langOptions: [
		        {
		        	value: "KO",
			        label: "KOR",
		        },
		        {
			        value: "EN",
			        label: "ENG",
		        },
		        {
			        value: "CN",
			        label: "CHN",
		        }
	        ],

	        selectedLang: "KO",

        	//기존 뉴스 info
        	newsInfo: {
        		oid: "",
				title: "",
				newsroomContents: "",
		        lang: "KO",
				modDate: "",
		        productList: [],
		        iconFile: {},
			},

	        dropzoneMount: false,

            // 관련제품
	        relatedProductName: "",

            // radio
            radioGroup: [
                {
                    id: "mainPopupN",
                    text: "미사용",
                },
                {
                    id: "mainPopupY",
                    text: "사용",
                },
            ],

	        productCheckType : "check",
	        // 체크된 제품 목록
	        checkedProductList: [],
	        // 삭제된 제품 목록
	        removedProductList: [],

            title: "",
	        newsroomContents: "",
            inputDate: "",
            modDate: "",

			//메인 팝업 사용여부
			useMainPop: "",
            // 팝업 노출 기간
            popupInput: "",

            // 링크 URL
            linkInput: "",

            // 팝업 내용
            descr: "",

            // 팝업 미리보기 (user와 동일)
            popupShowYn: false,


	        //로딩 indicator
	        pageLoading : false,

	        //미리보기
	        previewVisible : false,
        };
    },
	watch: {
		dropzoneMount() {
			if (this.dropzoneMount) {
				// 첨부파일 세팅
				if (this.$common.isNotEmpty(this.newsInfo.fileList)) {
					// let fileList = [];
					// fileList = this.newsInfo.fileList;

					// 드랍존의 파일을 모두 지운 후 다시 세팅
					this.$refs.dropzoneFile.removeAllFiles();
					this.$refs.dropzoneFile.setUploadFileList(this.newsInfo.fileList);
				}
			}
		},

		selectedLang(){
			this.setLang();
		},

	},

	async fetch(){

		this.pageLoading = true;
    	await this.getNewsInfo();
    	this.pageLoading = false;

	},

    methods: {
        //  페이지로 이동
        goList() {
        	const urlGoList = this.$urlConstant.MENU_URL_PREFIX.MANAGER_CUSTOMER_SUPPORT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.CUSTOMER_SUPPORT_MGNT.NEWSROOM;
            this.$router.push( this.localePath( urlGoList ) );
        },

		save() {

			if( !this.checkValidation() ){
				return;
			}

			const urlInsertOrUpdate = this.$urlConstant.API_URL_PREFIX.NEWSROOM + this.$urlConstant.API_URL_SUFFIX.NEWSROOM.INSERT_OR_UPDATE;

			this.sendContentData();

			this.$axios.post( urlInsertOrUpdate, this.newsInfo ).then( res => {
				if( this.$common.isEmpty( res.data ) ){
					return;
				}

				this.goList();
			});
		},

		// 뉴스 데이터를 불러옵니다.
		async getNewsInfo(){
        	//신규 등록일 경우 정보를 가져오지 않습니다.
        	if( this.$common.isEmpty(this.$route.query.oid) ){
        		return;
			}

        	const urlGet = this.$urlConstant.API_URL_PREFIX.NEWSROOM + this.$urlConstant.API_URL_SUFFIX.NEWSROOM.GET;

        	let cnd = {
				oid : this.$route.query.oid,
		        fillProductList: true,
		        fillIconFile : true,
			}

			// console.log( "cnd", cnd );
			await this.$axios.post( urlGet, cnd ).then( res => {
				if( this.$common.isEmpty( res.data ) ){
					return;
				}
				// console.log( "getNewsInfo", res.data );
				this.newsInfo = res.data;

				if( this.$common.isEmpty( this.newsInfo.productList ) ){
					this.newsInfo.productList = [];
				}

				this.selectedLang = this.newsInfo.lang;
				this.setFileInfo();
				this.$refs.refContents.setContent(res.data.newsroomContents);

			});
		},

	    //뉴스 포스팅 정보를 deleteFlag 처리합니다.
	    deleteNewsroom(){
        	if( this.$common.isEmpty( this.newsInfo.oid ) ){
				return;
	        }

		    const urlDelete = this.$urlConstant.API_URL_PREFIX.NEWSROOM + this.$urlConstant.API_URL_SUFFIX.NEWSROOM.DELETE_FLAG_UPDATE;

		    this.$common.swalWithOptions("삭제 확인", "해당 NEWS를 정말 삭제하시겠습니까?", "info").then((willDelete) => {
			    if ( willDelete ) {

				    let cnd = {
					    oid     : this.newsInfo.oid,
				    }

				    // console.log(cnd, urlDelete);

				    this.$axios.post( urlDelete, cnd ).then( res => {
					    if( res.status !== 200 ){
						    return;
					    }

					    this.goList();
				    });
			    }
		    });
	    },

	    // 관련 제품을 추가합니다
	    addRelatedProductList( data ){
        	this.newsInfo.productList = data;
        	// this.relatedProductName = data;
			// console.log("addRelatedProduct", data);
	    },


	    // 관련 제품 구성 체크 항목을 삭제합니다.
	    removeProduct() {
		    if (this.$common.isEmpty(this.newsInfo.productList)) {
			    return;
		    }

		    let _self = this;
		    let tempList = [];

		    this.newsInfo.productList.forEach(function(product) {
			    if (!product.isChecked) {
				    tempList.push(product);
			    } else {
				    product.isChecked = false;
				    _self.removedProductList.push(product);
			    }
		    });

		    this.newsInfo.productList = tempList;
	    },

	    setFileInfo() {
		    if (this.$common.isNotEmpty(this.newsInfo.iconFile) && this.dropzoneMount) {
			    // let fileList = [];
			    // fileList= this.newsInfo.fileList;

			    // 드랍존의 파일을 모두 지운 후 다시 세팅
			    this.$refs.dropzoneFile.removeAllFiles();
			    this.$refs.dropzoneFile.setUploadFileList([this.newsInfo.iconFile]);
		    }
	    },

	    // 대표 이미지
	    setStorageImageFile(data) {
        	// console.log("data[0]", data[0]);
;		    this.newsInfo.iconFile = data[0];
	    },

	    dropzoneMounted() {
		    this.dropzoneMount = true;
	    },

	    //Froala 에디터에서 데이터(내용)를 가져옵니다.
	    sendContentData(){
			this.$refs.refContents.sendContentData();
	    },
	    setContentData( data ){
	    	this.newsInfo.newsroomContents = data.contents;
	    },

	    checkValidation(){
		    if ( this.$common.isEmpty( this.newsInfo.title ) ) {
			    this.$common.confirmSwal("NEWS 명 누락", "NEWS 명을 입력해주세요.", "warning");
			    return false;
		    }
		    return true;
	    },

	    setLang(){
		    this.newsInfo.lang = this.selectedLang;
	    },

	    setChecked( item ) {
		    item.isChecked = !item.isChecked;
		    this.$forceUpdate();
	    },

	    setAddProductList(data) {
		    if (this.$common.isEmpty(data)) {
			    return;
		    }
		    this.newsInfo.productList = this.newsInfo.productList.concat(data);

		    //삭제되었다가 다시 추가된 제품을 삭제된 리스트에서 제외해줍니다.
		    let _self = this;

		    data.forEach(function(productFromModal) {
			    let removeIndex = _self.removedProductList.findIndex(item => item.oid === productFromModal.oid);
			    if (removeIndex > -1) {
				    _self.removedProductList.splice(removeIndex, 1);
			    }
		    });
	    },

	    // 제품 추가 모달 열기
	    addProductModalOpen() {
		    this.$refs.addProductModal.open();
	    },

	    // 팝업 닫기
	    closePopup() {
		    this.popupShowYn = false;
	    },

	    // 미리보기 열기
	    openPreview() {
		    this.sendContentData();
		    this.previewVisible = true;
	    },

	    // 미리보기 닫기
	    closePreview() {
		    this.previewVisible = false;
	    },


    },
};
</script>

<style scoped></style>
