<template>
    <div class="inner-wrapper">
	            <div class="loading-container" v-if="pageLoading">
	                <the-loading />
	            </div>
        <!-- inner-container -->
        <div class="inner-container">
            <div class="manager-content-body">
                <!-- contents 내용 -->
                <div class="content-title">
                    <div class="sub-title">
                        <h2 v-if="$common.isNotEmpty($route.query.oid)">Application 수정</h2>
                        <h2 v-else>Application 등록</h2>
                    </div>
                </div>
                <div class="content-detail">
                    <!-- 언어 탭 버튼  -->
                    <div class="btn-group bb">
                        <div class="btn-wrap tab-style">
                            <!-- 클릭한 버튼에 activ 클래스 추가 -->
                            <el-button type="st st-gray" size="default" @click="menuActive($amConstant.LANG.KO)" :class="langObj[$amConstant.LANG.KO].active">KOR</el-button>
                            <el-button type="st st-gray" size="default" @click="menuActive($amConstant.LANG.EN)" :class="langObj[$amConstant.LANG.EN].active">ENG</el-button>
                            <el-button type="st st-gray" size="default" @click="menuActive($amConstant.LANG.CN)" :class="langObj[$amConstant.LANG.CN].active">CHN</el-button>
                        </div>
                        <div class="btn-wrap">
                            <el-button type="primary" size="default" @click="saveApplicationInfo()">저장</el-button>
                            <el-button type="gray" size="default" v-if="$route.query.oid" @click="deleteApplicationInfo()">삭제</el-button>
                            <el-button type="st st-primary" size="default" @click="goList()">목록</el-button>
                        </div>
                    </div>
                    <div class="input-area">
                        <div class="input-row-md lr">
                            <div class="left-area">
                                <div class="input-label">
                                    <span>Application 명<em class="required">*</em></span>
                                </div>
                                <div class="input-data">
                                    <el-input placeholder="" v-model="applicationName" clearable></el-input>
                                </div>
                            </div>
                            <div class="right-area" v-if="$route.query.oid">
                                <div class="input-label">
                                    <span>최종 수정일</span>
                                </div>
                                <div class="input-data">
                                    <span v-text="$common.isEmpty( modDate )? '-': $common.formatDate( modDate ) "></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-row-md lr">
                            <div class="left-area">
                                <div class="input-label">
                                    <span>소재 구분<em class="required">*</em></span>
                                </div>
                                <div class="input-data">
                                    <el-select size="sm" v-model="selectedMatarialType" placeholder="선택">
                                        <el-option v-for="(item, key) in this.$store.state.menu.materialMap" :key="key" :label="item.materialName" :value="key"></el-option>
                                    </el-select>
                                </div>
                            </div>
                            <div class="right-area">
                                <div class="input-label">
                                    <span>상위 Application</span>
                                </div>
                                <div class="input-data">
                                    <div class="manager-search-box">
                                        <el-input title="검색창" placeholder="검색어를 입력하세요" v-model="searchInput" clearable></el-input>
                                        <el-button type="gray" class="btn-search" @click="openParentApplicationModal">
                                            <span class="icon custom-icon-search"></span>
                                        </el-button>
                                    </div>
                                </div>
                                <!-- <div class="input-label">
									<span>Depth<em class="required">*</em></span>
								</div>
								<div class="input-data">
									<el-input placeholder="" v-model="depthInput" clearable> </el-input>
								</div> -->
                            </div>
                        </div>
                        <div class="input-row-md">
                            <div class="input-label">
                                <span>설명</span>
                            </div>
                            <div class="input-data">
                                <el-input type="textarea" :rows="5" placeholder="내용을 입력하세요." v-model="descr"></el-input>
                            </div>
                        </div>
                        <div class="input-row-md">
                            <div class="input-label">
                                <span>Application 이미지</span>
                            </div>
                            <div class="input-data">
                                <div class="w100">
                                    <p class="txt-info">* 이미지 최적화 사이즈 : 450X350, 최적화 사이즈에 맞게 올려주세요.</p>
                                    <!-- dropzone -->
                                    <the-dropzone
                                        class="input-dropzone"
                                        ref="dropzoneFile"
                                        acceptedFiles=".jpeg,.jpg,.png"
                                        :maxFiles="1"
                                        @setUploadFile="setStorageImageFile"
                                        @dropzoneMounted="dropzoneMounted"
                                    />
                                </div>
                            </div>
                        </div>
                        <div class="input-row-md">
                            <div class="input-label">
                                <span>제품구성</span>
                            </div>
                            <div class="input-data">
                                <!-- table -->
                                <div class="manager-table-normal">
                                    <div class="table-header">
                                        <div class="btn-wrap-md">
                                            <el-button type="primary" size="small" @click="addProductModalOpen()">추가</el-button>
                                            <el-button type="gray" size="small" @click="removeProduct">삭제</el-button>
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
                                                    <th scope="col">
                                                        <span>선택</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>제품명</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>소재구분</span>
                                                    </th>
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
                                                        <tr v-if="$common.isEmpty(productList)">
                                                            <td colspan="3">
                                                                <!-- no-data(loading) -->
                                                                <div class="no-data" v-if="productListLoading">
                                                                    <div class="loading-sm">
                                                                        <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                                                    </div>
                                                                    <p>데이터 로딩중입니다.</p>
                                                                </div>
                                                                <!-- no-data -->
                                                                <div class="no-data" v-if="!productListLoading">
                                                                    <i class="material-icons">error_outline</i>
                                                                    <p>데이터가 없습니다.</p>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr v-for="(item, i) in productList" :key="i" class="list-item">
                                                            <td>
                                                                <div class="custom-checkbox">
                                                                    <input :id="item.oid" type="checkbox" v-model="item.isChecked" />
                                                                    <label :for="item.oid">
                                                                        <i></i>
                                                                        <!-- <span>텍스트</span> -->
                                                                    </label>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <span v-text="item.name"></span>
                                                            </td>
                                                            <td>
                                                                <span v-text="getMaterialInfoName(item)"></span>
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

	                    <div class="preview-area">
		                    <!-- 미리보기 화면 -->
		                    <the-preview :lang="$amConstant.LANG.KO"
		                                 v-show="currentLang === $amConstant.LANG.KO"
		                                 :contents-list="htmlContents"
		                                 @previewMounted="previewMounted = true"/>
		                    <the-preview :lang="$amConstant.LANG.EN"
		                                 v-show="currentLang === $amConstant.LANG.EN"
		                                 :contents-list="htmlContents"
		                                 @previewMounted="previewMounted = true"/>
		                    <the-preview :lang="$amConstant.LANG.CN"
		                                 v-show="currentLang === $amConstant.LANG.CN"
		                                 :contents-list="htmlContents"
		                                 @previewMounted="previewMounted = true"/>

		                    <!-- 클릭하여 정보추가 editor component :: TheAddInfoEditor.vue -->
		                    <the-add-info-editor ref="addInfoKO"
		                                         :lang="$amConstant.LANG.KO"
		                                         @editorMounted="editorMounted = true"
		                                         v-show="currentLang === $amConstant.LANG.KO"
		                                         @emitContents="setHtmlContents()"/>
		                    <the-add-info-editor ref="addInfoEN"
		                                         :lang="$amConstant.LANG.EN"
		                                         @editorMounted="editorMounted = true"
		                                         v-show="currentLang === $amConstant.LANG.EN"
		                                         @emitContents="setHtmlContents()"/>
		                    <the-add-info-editor ref="addInfoCN"
		                                         :lang="$amConstant.LANG.CN"
		                                         @editorMounted="editorMounted = true"
		                                         v-show="currentLang === $amConstant.LANG.CN"
		                                         @emitContents="setHtmlContents()"/>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Detail Application 추가 모달 -->
        <!--        <the-add-detail-application-modal ref="addDetailApplicationModal"/>-->
        <!-- 제품 추가 모달 -->
        <the-add-product-modal
            ref="addProductModal"
            :product-check-type="productCheckType"
            :product-list="productList"
            :removed-product-list="removedProductList"
			:current-lang="currentLang"
            @addProductList="setAddProductList"
        />
        <the-add-application-modal ref="addApplicationModal" :application-info="applicationInfo" @getApplication ="setParentApplicationInfo"> </the-add-application-modal>
    </div>
</template>
<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theDropzone from "~/components/common/dropzone/TheDropZone.vue";
import theAddDetailApplicationModal from "~/components/kccam/manager/modal/TheAddDetailApplicationModal.vue";
import theAddProductModal from "~/components/kccam/manager/modal/TheAddProductModal.vue";

import theAddInfoEditor from "~/components/kccam/manager/editor/TheAddInfoEditor.vue";
import theAddApplicationModal from "~/components/kccam/manager/modal/TheAddApplicationModal.vue";
import ThePreview from "~/components/common/board/manager/ThePreview";

export default {
    layout: "managerLayout",
    components: {
	    ThePreview,
        theLoading,
        thePagination,
        theDropzone,
        theAddDetailApplicationModal,
        theAddProductModal,
        theAddInfoEditor,
        theAddApplicationModal,
    },
    data() {
        return {
            applicationInfo: {},
            applicationName: "",
            descr: "",
            inputDate: "",
            modDate: "",
            applicationList: [],
            parentApplicationInfo: {},

            imgFile: [],

            // 제품구성 목록
            productList: [],
            // 체크된 제품 목록
            checkedProductList: [],
            // 삭제된 제품 목록
            removedProductList: [],

            dropzoneMount: false,

            productCheckType: "check",

            selectedMatarialType: "",

            depthInput: "",

            // Detail Application 목록
            detailApplicationList: [
                {
                    id: "detailApplication01",
                    detailApplicationName: "DARM",
                    depth: "2",
                },
                {
                    id: "detailApplication02",
                    detailApplicationName: "DARM",
                    depth: "3",
                },
            ],


            searchInput: "",

            // 현재 페이지 언어
            currentLang: this.$amConstant.LANG.KO,
            langObj: {
                ko: {},
                en: {},
                cn: {},
            },

	        productListLoading : false,

	        //로딩 indicator
	        pageLoading : false,
	        editorMounted : false,

	        // 미리보기 컴포넌트 관련
	        htmlContents   : [],
	        previewMounted : false,
        };
    },
    async fetch() {
	    this.langObj[this.$amConstant.LANG.KO].active = "active"

	    this.pageLoading = true;
        await this.getApplicationInfo();
        this.pageLoading = false;
    },
    watch: {
        dropzoneMount() {
            if (this.dropzoneMount) {
                // 첨부파일 세팅
                if (this.$common.isNotEmpty(this.applicationInfo.iconFile)) {
                    let fileList = [];
                    fileList.push(this.applicationInfo.iconFile);

                    // 드랍존의 파일을 모두 지운 후 다시 세팅
                    this.$refs.dropzoneFile.removeAllFiles();
                    this.$refs.dropzoneFile.setUploadFileList(fileList);
                }
            }
        },
	    editorMounted() {
		    if ( this.editorMounted ) {
			    this.setLangSave( this.currentLang );
		    }
	    },
	    previewMounted() {
		    if ( this.previewMounted ) {
			    this.setHtmlContents( this.currentLang );
		    }
	    }
    },

    methods: {
        openParentApplicationModal() {
            this.$refs.addApplicationModal.open();
        },

        // 목록 페이지로 이동
        goList() {
            this.$router.push(this.localePath("/kccam/manager/productMgnt/application/application_list"));
        },

        //  Detail Application 추가 모달 열기
        addDetailApplicationModalOpen() {
            this.$refs.addDetailApplicationModal.open();
        },

        // 제품 추가 모달 열기
        addProductModalOpen() {
            this.$refs.addProductModal.open();
        },

        //application 정보를 저장합니다
        saveApplicationInfo() {
            if (this.$common.isEmpty(this.applicationName)) {
                this.$common.confirmSwal("Application 명을 입력해주세요.", "", "warning");
                return;
            }

            if ( this.$common.isEmpty( this.selectedMatarialType ) ) {
            	this.$common.confirmSwal( "소재 구분을 선택해주세요", "", "warning" );
            	return;
			}

            if (this.$common.isEmpty(this.applicationInfo)) {
                this.insertInfo();
            } else {
                this.updateInfo();
            }
        },

        //application 정보를 등록합니다.
        insertInfo() {
            const urlInsert = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.INSERT;

            let info = {
                name: this.applicationName,
                categoryType: this.$amConstant.CATEGORY_TYPE.APPLICATION,
                descr: this.descr,
                parentOid: this.$amConstant.ROOT_CATEGORY_OID.APPLICATION,
                classificationObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_APPLICATION,

				partOid: this.selectedMatarialType,

                // addContentsList: this.getContentsList(),
                langCategoryList: [],
            };

            this.setLangSave( this.currentLang );
            this.setLangCategoryList( info );

            if (this.imgFile) {
                info.iconFile = this.imgFile[0];
            }

            let productRelList = this.setProductRelList();

            if (this.$common.isNotEmpty(productRelList)) {
                info.productRelList = productRelList;
            }

            if (this.$common.isNotEmpty(this.parentApplicationInfo)) {
                info.parentOid = this.parentApplicationInfo.oid;
            }

            this.$axios.post(urlInsert, info).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.applicationInfo = {};
                    this.$common.confirmSwal("Aapplication 정보 저장", "저장실패", "warning");
                    return;
                }

                this.applicationInfo = res.data;
                // console.log("등록 정보 : ", res.data);
                this.$common.confirmSwal("Aapplication 정보 저장", "저장완료", "success").then(result => {
					this.$router.push( this.localePath( { path : this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.APPLICATION_EDIT,
						query : { oid : this.applicationInfo.oid } }) );
                });
            });
            this.goList();
        },

		//deleteFlag 처리
		deleteApplicationInfo() {
			const urlDelete = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.DELETE;

			let info = {
				oid: this.$route.query.oid,
			};

			this.$common.swalWithOptions("삭제 확인", "해당 Application을 삭제하시겠습니까?", "info").then(isDelete => {
				if (isDelete){
					this.$axios.post(urlDelete, info).then(res => {
						if (this.$common.isEmpty(res.data)) {
							return;
						}
						//console.log(res.data);

						this.goList();


					});
				}
			});
		},

        //마켓정보를 update합니다.
        updateInfo() {
            const urlUpdate = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.UPDATE;
            let info = {
                oid: this.$route.query.oid,
                name: this.applicationName,
                categoryType: this.$amConstant.CATEGORY_TYPE.APPLICATION,
                descr: this.descr,
                classificationObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_APPLICATION,

                partOid: this.selectedMatarialType,

                // addContentsList: this.getContentsList(),
                langCategoryList: [],
            };
            this.setLangSave( this.currentLang );
            this.setLangCategoryList( info );

            if (this.$common.isNotEmpty(this.imgFile)) {
                info.iconFile = this.imgFile[0];
            }

            let productRelList = this.setProductRelList();

            if (this.$common.isNotEmpty(productRelList)) {
                info.productRelList = productRelList;
            }

            if (this.$common.isNotEmpty(this.parentApplicationInfo)) {
                info.parentOid = this.parentApplicationInfo.oid;
            }

            this.$axios.post(urlUpdate, info).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.applicationInfo = {};
                    return;
                }

                this.getApplicationInfo();

				this.$common.confirmSwal("Aapplication 정보 저장", "저장완료", "success").then(result => {
					this.$router.push( this.localePath( { path : this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.APPLICATION_LIST,
						query : { oid : this.applicationInfo.oid } }) );
				});
                // this.$common.confirmSwal("Aapplication 정보 저장", "저장완료", "success").then(result => {
                //     this.goList();
                // });
            });
            // this.goList();
        },

		setParentApplicationInfo(data) {
        	this.parentApplicationInfo = data;
        	this.setSearchInput(data);
		},

        // 대표 이미지
        setStorageImageFile(data) {
            this.imgFile = data;
        },

	    /**
	     * Froala 에디터에서 데이터를 가져옵니다.
	     */
	    getContentsList( lang ) {
		    switch ( lang ){
			    case this.$amConstant.LANG.KO : return this.$refs.addInfoKO.getContentsList();
			    case this.$amConstant.LANG.EN : return this.$refs.addInfoEN.getContentsList();
			    case this.$amConstant.LANG.CN : return this.$refs.addInfoCN.getContentsList();
		    }
	    },

	    displayContentsList( lang, addContentsList ){
		    switch ( lang ){
			    case this.$amConstant.LANG.KO : this.$refs.addInfoKO.displayList(addContentsList); return;
			    case this.$amConstant.LANG.EN : this.$refs.addInfoEN.displayList(addContentsList); return;
			    case this.$amConstant.LANG.CN : this.$refs.addInfoCN.displayList(addContentsList); return;
		    }
	    },

        dropzoneMounted() {
            this.dropzoneMount = true;
        },

        async getApplicationInfo() {
            const urlGet = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION
				+ this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.GET;

            //등록페이지인 경우 application정보를 불러오지 않습니다.
            if ( this.$common.isEmpty( this.$route.query.oid) ) {
                return;
            }

            let cnd = {
                oid: this.$route.query.oid,
                categoryType: this.$amConstant.CATEGORY_TYPE.APPLICATION,
                fillIconFile: true,
                fillProduct: true,
                fillMaterial: true,
                fillLangList: true,
	            fillRelateMaterial: true,
            };
			this.productListLoading = true;
            await this.$axios.post(urlGet, cnd).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.applicationInfo = {};
	                this.productListLoading = false;
                    this.productList = [];
                    return;
                }

                this.applicationInfo = res.data;
                this.applicationName = res.data.name;
                this.modDate = res.data.modDate;
                this.descr = res.data.descr;
                this.setFileInfo();

	            this.productListLoading = false;
                this.productList = res.data.productList;
                this.parentApplicationInfo = { oid: res.data.parentOid };
                this.setSearchInput({ oid: res.data.parentOid, name: res.data.parentName });

                this.selectedMatarialType = res.data.materialInfo.oid;

                // lang값 할당
                this.setLangObj( res.data );
                this.getLangSave( this.currentLang );
                // this.setLangSave(this.currentLang);
                this.langObj[this.currentLang].active = "active";
            }).catch( error => {
	            console.log( error );
	            this.productListLoading = false;
            });
        },

        setProductRelList() {
            if (this.$common.isEmpty(this.productList)) {
                return;
            }

            let productRelList = [];
            let _self = this;
            this.productList.forEach(function(product) {
                productRelList.push({
                    productOid: product.oid,
                    targetObject: _self.$amConstant.OBJECT_TYPE.CLASSIFICATION_APPLICATION,
                });
            });

            return productRelList;
        },

        setAddProductList(data) {
            if (this.$common.isEmpty(data)) {
                return;
            }
	        this.productList = this.productList.concat(data);

            //삭제되었다가 다시 추가된 제품을 삭제된 리스트에서 제외해줍니다.
            let _self = this;

            data.forEach(function(productFromModal) {
                let removeIndex = _self.removedProductList.findIndex(item => item.oid === productFromModal.oid);
                if (removeIndex > -1) {
                    _self.removedProductList.splice(removeIndex, 1);
                }
            });
        },

		/**
		 * 제품 구성 체크 항목을 삭제합니다.
		 */
		removeProduct() {
			if (this.$common.isEmpty(this.productList)) {
				return;
			}

			let _self = this;
			let tempList = [];

			this.productList.forEach(function(product) {
				if (!product.isChecked) {
					tempList.push(product);
				} else {
					product.isChecked = false;
					_self.removedProductList.push(product);
				}
			});

			this.productList = tempList;
		},

        setFileInfo() {
            if (this.$common.isNotEmpty(this.applicationInfo.iconFile) && this.dropzoneMount) {
                let fileList = [];
                fileList.push(this.applicationInfo.iconFile);

                // 드랍존의 파일을 모두 지운 후 다시 세팅
                this.$refs.dropzoneFile.removeAllFiles();
                this.$refs.dropzoneFile.setUploadFileList(fileList);
                this.imgFile = this.applicationInfo.iconFile;
            }
        },

        setSearchInput(applicationInfo) {
            if (this.$common.isEmpty(applicationInfo)) {
                return;
            }
            if (applicationInfo.oid === this.$amConstant.ROOT_CATEGORY_OID.APPLICATION) {
                this.searchInput = "상위 마켓이 없습니다";
            } else {
                this.searchInput = applicationInfo.name;
            }
        },

        /**
         *  lang정보를 저장해 둡니다
         */
        setLangSave(lang) {
            let instanceInfo = {
                name: this.applicationName,
                descr: this.descr,
                lang: lang,
                active: "",
                inputDate: this.inputDate,
                modDate: this.modDate,

	            langAddContentsList : this.getContentsList( lang ),
	            contentsTargetObject : this.$amConstant.OBJECT_TYPE.CLASSIFICATION_APPLICATION,
            };
            this.langObj[lang] = instanceInfo;

        },

        getLangSave(lang) {
            this.applicationName = this.langObj[lang].name;
            this.descr = this.langObj[lang].descr;
            this.inputDate = this.langObj[lang].inputDate;
            this.modDate = this.langObj[lang].modDate;
        },

        /**
         * active
         */
        menuActive(lang) {
            this.setLangSave(this.currentLang);
            this.langObj[lang].active = "active";
            this.currentLang = lang;
            this.getLangSave(lang);
        },

        setLangCategoryList( info ) {
            if ( this.$common.isEmpty( info ) || this.$common.isEmpty( this.langObj ) ) {
                return;
            }

            if (  this.$common.isNotEmpty( this.langObj.ko ) ) {
                info.langCategoryList.push( this.langObj.ko );
            }

            if (  this.$common.isNotEmpty( this.langObj.en ) ) {
                info.langCategoryList.push( this.langObj.en );
            }

            if (  this.$common.isNotEmpty( this.langObj.cn ) ) {
                info.langCategoryList.push( this.langObj.cn );
            }
        },

        setLangObj( info ) {
            if ( this.$common.isEmpty( info ) || this.$common.isEmpty( info.langCategoryList ) ) {
                return;
            }

            info.langCategoryList.forEach( langCategory => {

                let lang = this.$amConstant.LANG[langCategory.lang];


                this.langObj[lang] = langCategory;

	            if( this.$common.isNotEmpty( langCategory.langAddContentsList ) ){
		            this.displayContentsList( lang, langCategory.langAddContentsList );
	            }
            } )

	        this.langObj[this.currentLang].active = "active";
        },

        getMaterialInfoName(item) {

			if ( this.$common.isEmpty(item) || this.$common.isEmpty(item.materialInfo) || this.$common.isEmpty(item.materialInfo.name) ) {
                return '-'
            }

			return item.materialInfo.name;
        },
	    setHtmlContents() {
		    let htmlContents = this.getContentsList( this.currentLang );
		    // console.log("htmlContents : ", htmlContents );
		    this.htmlContents = [];
		    this.htmlContents = htmlContents;
		    ;
	    }
    },
};
</script>
<style scoped>

.preview-area {
	margin: 1rem 0rem;
	padding: 2rem 0 6rem 0;
	border: 1px solid #ddd;
	border-right: 0 none;
	border-left: 0 none;
}
</style>
