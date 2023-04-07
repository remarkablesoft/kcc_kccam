<template>
    <div class="inner-wrapper">
        <!-- loading -->
        <div class="loading-container" v-if="loadingIndicator > 0 || pageLoading">
            <the-loading />
        </div>

        <!-- inner-container -->
        <div class="inner-container">
            <div class="manager-content-body">
                <!-- contents 내용 -->
                <div class="content-title">
                    <div class="sub-title">
                        <h2 v-if="$common.isNotEmpty($route.query.oid)">제품상세 수정</h2>
                        <h2 v-else>제품상세 등록</h2>
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
                            <el-button type="primary" size="default" @click="save">저장</el-button>
                            <el-button type="gray" size="default" v-if="this.$route.query.oid" @click="remove">삭제</el-button>
                            <el-button type="st st-primary" size="default" @click="goList()">목록</el-button>
                        </div>
                    </div>
                    <div class="input-area">
                        <div class="input-row-md lr" v-if="inputDate">
                            <div class="left-area">
                                <div class="input-label">
                                    <span>등록일시</span>
                                </div>
                                <div class="input-data">
                                    <span v-text="$common.formatDate(inputDate)"></span>
                                </div>
                            </div>
                            <div class="right-area">
                                <div class="input-label" v-if="modDate">
                                    <span>최종 수정일시</span>
                                </div>
                                <div class="input-data">
                                    <span v-text="$common.formatDate(modDate)"></span>
                                </div>
                            </div>
                        </div>

                        <div class="input-row-md lr">
                            <div class="left-area">
                                <div class="input-label">
                                    <span>제품명<em class="required">*</em></span>
                                </div>
                                <div class="input-data">
                                    <el-input placeholder="" v-model="productNameInput" clearable> </el-input>
                                </div>
                            </div>
                            <div class="right-area">
                                <div class="input-label">
                                    <span>아이템 코드<em class="required">*</em></span>
                                </div>
                                <div class="input-data">
                                    <div class="manager-search-box">
                                        <el-input placeholder="" v-model="itemCodeInput" clearable> </el-input>
                                        <el-button type="gray" class="btn-search" @click="addItemCodeModalOpen()">
                                            <span class="icon custom-icon-search"></span>
                                        </el-button>
                                    </div>
                                    <!--                                    <el-button type="primary" size="small" @click="[]">임시코드</el-button>-->
                                </div>
                            </div>
                        </div>

                        <div class="input-row-md lr">
                            <div class="left-area">
                                <div class="input-label">
                                    <span>소재 구분<em class="required">*</em></span>
                                </div>
                                <div class="input-data">
                                    <el-select size="sm" v-model="materialSearchOptionsValue" placeholder="선택">
                                        <el-option v-for="(item, index) in materialSearchOptions" :key="index" :label="item.materialName" :value="index"> </el-option>
                                    </el-select>
                                </div>
                            </div>
                            <div class="right-area">
                                <div class="input-label">
                                    <span>제품 구분<em class="required">*</em></span>
                                </div>
                                <div class="input-data">
                                    <div class="manager-search-box">
                                        <el-input placeholder="버튼 클릭하여 검색" v-model="productClassificationInput" :disabled="true"> </el-input>
                                        <el-button type="gray" class="btn-search" @click="addProductClassificationModalOpen">
                                            <span class="icon custom-icon-search"></span>
                                        </el-button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="input-row-md lr">
                            <div class="left-area">
                                <div class="input-label">
                                    <span>Function<em class="required">*</em></span>
                                </div>
                                <div class="input-data">
                                    <div class="manager-search-box">
                                        <el-input placeholder="버튼 클릭하여 검색" v-model="functionInput" :disabled="true" clearable> </el-input>
                                        <el-button type="gray" class="btn-search" @click="addFunctionModalOpen()">
                                            <span class="icon custom-icon-search"></span>
                                        </el-button>
                                    </div>
                                </div>
                            </div>
                            <div class="right-area">
                                <div class="input-label">
                                    <span>제품 노출여부<em class="required">*</em></span>
                                </div>
                                <div class="input-data">
                                    <el-select size="sm" v-model="releaseYnValue">
                                        <el-option v-for="item in releaseYnOption" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                                    </el-select>
                                </div>
                            </div>
                        </div>

                        <div class="input-row-md" v-if="functionGroup.length > 0">
                            <div class="input-label">
                                <span></span>
                            </div>
                            <div class="input-data column">
                                <div class="square-dot-tit">Main Function 선택</div>
                                <div class="radio-group">
                                    <!-- radio-item 추가 :: 반복되는 부분 custom-radio에서 radio-item으로 옮김 -->
                                    <div class="radio-item" v-for="(item, i) in functionGroup" :key="i">
                                        <div class="custom-radio">
                                            <input :id="item.oid" type="radio" name="radioGroup" v-model="functionSelected" :value="item.oid" />
                                            <label :for="item.oid">
                                                <i></i>
                                                <span v-text="item.name"><!--텍스트--></span>
                                            </label>
                                        </div>
                                        <!-- 삭제 버튼 -->
                                        <el-button type="icon-only sm">
                                            <span class="material-icons" @click="removeFunctionGroupItem(i)">cancel</span>
                                        </el-button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="input-row-md">
                            <div class="input-label">
                                <span>설명</span>
                            </div>
                            <div class="input-data">
                                <el-input type="textarea" :rows="5" placeholder="내용을 입력하세요." v-model="descr"> </el-input>
                            </div>
                        </div>

                        <!-- 관련 문서 열기 -->
                        <the-related-doc ref="relDoc" :doc-list="docList" />

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


                        <!-- datasheet edit component :: TheDatasheetEdit.vue -->
                        <the-datasheet-edit ref="datasheetEdit" :datasheet-oid="datasheetInfo.oid"> </the-datasheet-edit>

                        <!-- contact edit component :: TheContactEdit.vue -->
                        <the-contact-edit ref="contactEdit"> </the-contact-edit>
                    </div>
                </div>
            </div>
        </div>

        <!-- Function 추가 모달 -->
        <the-add-function-modal ref="addFunctionModal" :except-function-list="functionGroup" :remove-function-list="removeFunctionList" @addFunctionList="addFunctionList"> </the-add-function-modal>

        <!-- 아이템 코드 추가 모달 -->
        <the-add-item-code-modal ref="addItemCodeModal" @getSelectedSFAProduct="getSelectedSFAProduct"></the-add-item-code-modal>

        <!-- 제품 구분 추가 모달 -->
        <the-add-parent-product-classification-modal ref="addProductClassificationModal" @getProductClassification="getProductClassification"> </the-add-parent-product-classification-modal>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theAddFunctionModal from "~/components/kccam/manager/modal/TheAddFunctionModal.vue";
import theAddItemCodeModal from "~/components/kccam/manager/modal/TheAddItemCodeModal.vue";
import theAddParentProductClassificationModal from "~/components/kccam/manager/modal/TheAddParentProductClassificationModal";

import theRelatedDoc from "~/components/common/board/manager/TheRelatedDoc.vue";
import theDatasheetEdit from "~/components/kccam/manager/datasheet/TheDatasheetEdit.vue";
import theContactEdit from "~/components/kccam/manager/contact/TheContactEdit.vue";
import theAddInfoEditor from "~/components/kccam/manager/editor/TheAddInfoEditor.vue";
import ThePreview from "~/components/common/board/manager/ThePreview";

import { mapActions } from "vuex";

export default {
    layout: "managerLayout",
    components: {
        theLoading,
        thePagination,
        theAddFunctionModal,
        theAddItemCodeModal,
        theAddParentProductClassificationModal,

        theDatasheetEdit,
        theContactEdit,
        theAddInfoEditor,
	    ThePreview,
    },
    data() {
        return {
            productInfo: {},
            productNameInput: "",
            itemCodeInput: "",

            // 선택
            materialSearchOptions: [],
            materialSearchOptionsValue: "",
            productClassificationInput: "",
            functionInput: "",

            // 선택
            releaseYnOption: [
                {
                    value: this.$amConstant.YN.Y,
                    label: this.$amConstant.RELEASE_YN.Y,
                },
                {
                    value: this.$amConstant.YN.N,
                    label: this.$amConstant.RELEASE_YN.N,
                },
            ],
            releaseYnValue: "",

            inputDate: "",
            modDate: "",

            // radio
            functionGroup: [],
            removeFunctionList: [],
            functionSelected: "",

            descr: "",
            docList: [], // 관련문서 목록
            datasheetInfo: {},
            referenceList: [], // 참고자료 목록

            // 현재 페이지 언어
            currentLang: this.$amConstant.LANG.KO,
            langObj: {
                ko: {},
                en: {},
                cn: {},
            },

	        //로딩 indicator
	        pageLoading : false,
			editorMounted : false,

	        // 미리보기 컴포넌트 관련
	        htmlContents   : [],
	        previewMounted : false,
        };
    },
	watch : {
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
    computed: {
        loadingIndicator() {
            return this.$root.$loading.percent;
        },
    },
    async fetch() {
	    this.langObj[this.$amConstant.LANG.KO].active = "active"

	    this.materialSearchOptions = await this.getMaterialMap();

	    this.releaseYnValue = this.releaseYnOption[0].value;
	    if( this.$common.isNotEmpty( this.$route.query.oid ) ) {
	    	await this.getData();
	    }
    },
    methods: {
	    ...mapActions({
		    getMaterialMap: "menu/getMaterialMap",
	    }),

        // 목록 페이지로 이동
        goList() {
            this.$router.push("/kccam/manager/productMgnt/productView/productView_list");
        },

        // Function 추가 모달 열기
        addFunctionModalOpen() {
            this.$refs.addFunctionModal.open();
        },

        // 아이템 코드 추가 모달 열기
        addItemCodeModalOpen() {
            this.$refs.addItemCodeModal.open();
        },

        // 제품구분 모달 열기
        addProductClassificationModalOpen() {
            this.$refs.addProductClassificationModal.open();
        },

        // 참고자료 추가 모달 열기
        addReferenceModalOpen() {
            this.$refs.addReferenceModal.open();
        },

        /**
         * 제품 데이터를 가져옵니다.
         */
        async getData() {
	        // this.pageLoading = true;
            const oid = this.$route.query.oid;
            this.$router.push({
                path: this.$router.currentRoute.path,
                query: {
                    oid: oid,
                },
            });

            const url = this.$urlConstant.API_URL_PREFIX.PRODUCT + this.$urlConstant.API_URL_SUFFIX.PRODUCT.GET;
            let param = {
                oid: oid,
                fillLangList: true,
            };

            await this.$axios.post(url, param).then(res => {
                this.setLangObj( res.data );
                this.getLangSave( this.currentLang );

                this.displayData(res.data);
	            this.langObj[this.$amConstant.LANG.KO].active = "active"
            });
	        this.pageLoading = false;

        },

        /**
         * 제품 데이터를 그려줍니다.
         */
        displayData(info) {
            if (this.$common.isEmpty(info)) {
                return;
            }

            this.productInfo = info;
            this.productNameInput = info.name;
            this.itemCodeInput = info.productCode;
            this.functionGroup = info.functionList;
            this.functionSelected = info.mainFuncOid;
            this.materialSearchOptionsValue = info.materialOid;
            this.releaseYnValue = this.$amConstant.YN.N !== info.releaseYn ? this.$amConstant.YN.Y : this.$amConstant.YN.N;
            this.inputDate = info.inputDate; // 등록일
            this.modDate = info.modDate; // 수정일
            this.descr = info.descr; // 비고

            if (this.$common.isNotEmpty(info.productClassificationInfo)) {
                this.productClassificationInput = info.productClassificationInfo.name;
            }

            if (this.$common.isNotEmpty(info.datasheetInfo)) {
                this.datasheetInfo = info.datasheetInfo;
                this.$refs.datasheetEdit.setList(info.datasheetInfo.datasheetItemList);
            }

            this.$refs.contactEdit.setContactList(info.branchList, info.branchManagerList);
            this.$refs.relDoc.setDocListCheck();
            this.docList = info.docList;
            // this.$refs.addInfo.displayList(info.addContentsList);

            // lang값 할당
            // this.setLangSave(this.currentLang);
            this.langObj[this.currentLang].active = "active";
        },

        /**
         * 제품정보를 등록/저장합니다.
         */
        save() {
            let info = this.setProductInfo();

            if (!this.validateParam(info)) {
                return;
            }


            if (this.$route.query.oid) {
                this.update(info);
            } else {
                this.insert(info);
            }
        },

        /**
         * 제품 정보를 등록합니다.
         */
        insert(info) {
            let param = info;
            const url = this.$urlConstant.API_URL_PREFIX.PRODUCT + this.$urlConstant.API_URL_SUFFIX.PRODUCT.INSERT;

            this.$axios.post(url, param).then(res => {
                if (this.$common.isNotEmpty(res.data)) {
                    this.$common.confirmSwal("저장 완료", "성공적으로 저장되었습니다.", "info");
                    this.goList();
                } else {
                    this.$common.confirmSwal("저장 실패", "저장 중 에러가 발생하였습니다.", "warning");
                }
            }).catch( error => {
	            console.log( error );
            });
        },

        /**
         * 제품 정보를 수정합니다.
         */
        update(info) {
            let param = info;
            const url = this.$urlConstant.API_URL_PREFIX.PRODUCT + this.$urlConstant.API_URL_SUFFIX.PRODUCT.UPDATE;
	        // console.log(info);
            // return;
            this.$axios.post(url, param).then(res => {
                if (this.$common.isNotEmpty(res.data)) {
                    this.$common.confirmSwal("저장 완료", "성공적으로 저장되었습니다.", "info");
                    this.goList();
                } else {
                    this.$common.confirmSwal("저장 실패", "저장 중 에러가 발생하였습니다.", "warning");
                }
            });
        },

        /**
         * 저장할 제품 정보를 설정합니다.
         * @returns {{}}
         */
        setProductInfo() {
            let oid = this.$route.query.oid ? this.$route.query.oid : "";

            let info = {
                oid: oid,
                productCode: this.itemCodeInput,
                stdProductCode: this.itemCodeInput,
                name: this.productNameInput,
                materialOid: this.materialSearchOptionsValue,

                productClassificationInfo: this.productInfo.productClassificationInfo,
                functionList: this.functionGroup,
                mainFuncOid: this.functionSelected,
                releaseYn: this.releaseYnValue,
                descr: this.productInfo.descr,

                docList: this.getDocInfoList(),
                datasheetInfo: this.getDatasheet(),
                branchList: this.addContactOfficeList(),
                branchManagerList: this.addContactManagerList(),

                langProductList: [],
            };
            this.setLangSave( this.currentLang );
            this.setLangProductList( info );

            return info;
        },

        /**
         * 제품정보에 유효성을 체크합니다.
         */
        validateParam(param) {
            const _self = this;
            if (_self.$common.isEmpty(param.name)) {
                _self.$common.confirmSwal("제품명을 입력해주세요.", "", "warning");
                return false;
            }

            if (_self.$common.isEmpty(param.productClassificationInfo)) {
                _self.$common.confirmSwal("제품구분을 선택해주세요.", "", "warning");
                return false;
            }

	        if (_self.$common.isEmpty(param.stdProductCode)) {
		        _self.$common.confirmSwal("아이템 코드를 선택해주세요.", "", "warning");
		        return false;
	        }

            if (_self.$common.isEmpty(param.materialOid)) {
                _self.$common.confirmSwal("소재구분을 선택해주세요.", "", "warning");
                return false;
            }

            if (_self.$common.isEmpty(param.functionList)) {
                _self.$common.confirmSwal("Function을 설정해주세요.", "", "warning");
                return false;
            }

            if (_self.$common.isNotEmpty(param.functionList) && _self.$common.isEmpty(param.mainFuncOid)) {
                _self.$common.confirmSwal("Main Function을 선택해주세요.", "", "warning");
                return false;
            }

            if (_self.$common.isEmpty(param.releaseYn)) {
                _self.$common.confirmSwal("노출여부를 선택해주세요.", "", "warning");
                return false;
            }

            return true;
        },

        /**
         * SFA 제품코드 검색 모달에서 제품정보를 가져옵니다.
         */
        getSelectedSFAProduct(product) {
            this.itemCodeInput = product.items;
        },

        /**
         * 제품구분 리스트에서 선택된 제품을 가져옵니다.
         */
        getProductClassification(productClassification) {
            this.productInfo.productClassificationInfo = productClassification;
            this.productClassificationInput = productClassification.name;
        },

        /**
         * function 모달에서 리스트를 가져옵니다.
         */
        addFunctionList(list) {
            this.functionGroup = [...this.functionGroup, ...list];
        },

        /**
         * functionGroup 에서 x 클릭된 항목을 삭제합니다.
         */
        removeFunctionGroupItem(index) {
            this.functionGroup.splice(index, 1);
        },

        /**
         * 관련문서를 가져옵니다.
         */
        getDocInfoList() {
            return this.$refs.relDoc.getDocInfoList();
        },

        /**
         * Contact Office 리스트를 추가합니다.
         */
        addContactOfficeList() {
            return this.$refs.contactEdit.addContactOfficeList();
        },

        /**
         * Contact Manager 리스트를 추가합니다.
         */
        addContactManagerList() {
            return this.$refs.contactEdit.addContactManagerList();
        },

        /**
         * 데이터시트 정보를 가져옵니다.
         */
        getDatasheet() {
            let datasheetItemList = this.$refs.datasheetEdit.getDatasheetItemList();

            let datasheetInfo = this.$common.isEmpty(this.productInfo.datasheetInfo) ? {} : this.productInfo.datasheetInfo;
            datasheetInfo["title"] = this.productNameInput + " Datasheet";
            datasheetInfo["datasheetItemList"] = datasheetItemList;

            return datasheetInfo;
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

        /**
         * 제품 정보를 삭제합니다.
         */
        remove() {
            this.$common.swalWithOptions("삭제 확인", "해당 제품정보를 삭제하시겠습니까?", "info").then(isDelete => {
                if (isDelete) {
                    const url = this.$urlConstant.API_URL_PREFIX.PRODUCT + this.$urlConstant.API_URL_SUFFIX.PRODUCT.REMOVE;
                    let param = {
                        oid: this.$route.query.oid,
                    };

                    this.$axios.post(url, param).then(res => {
                        if (1 === res.data) {
                            const redirectUrl = this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.PRODUCT_LIST;
                            this.$router.push(redirectUrl);
                        }
                    });
                }
            });
        },

        /**
         *  lang정보를 저장해 둡니다
         */
        setLangSave(lang) {
            let instanceInfo = {
                name: this.productNameInput,
                descr: this.descr,
                lang: lang,
                active: "",
                inputDate: this.inputDate,
                modDate: this.modDate,

	            langAddContentsList : this.getContentsList( lang ),
            };
            this.langObj[lang] = instanceInfo;
        },

        getLangSave(lang) {
            this.productNameInput = this.langObj[lang].name;
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

        setLangProductList( info ) {
            if ( this.$common.isEmpty( info ) || this.$common.isEmpty( this.langObj ) ) {
                return;
            }

            if (  this.$common.isNotEmpty( this.langObj.ko ) ) {
                info.langProductList.push( this.langObj.ko );
            }

            if (  this.$common.isNotEmpty( this.langObj.en ) ) {
                info.langProductList.push( this.langObj.en );
            }

            if (  this.$common.isNotEmpty( this.langObj.cn ) ) {
                info.langProductList.push( this.langObj.cn );
            }
        },

        setLangObj( info ) {
            if ( this.$common.isEmpty( info ) || this.$common.isEmpty( info.langProductList ) ) {
                return;
            }

            info.langProductList.forEach( langProduct => {

                let lang = this.$amConstant.LANG[langProduct.lang];

                this.langObj[lang] = langProduct;

	            if( this.$common.isNotEmpty( langProduct.langAddContentsList ) ){
		            this.displayContentsList( lang, langProduct.langAddContentsList );
	            }
            } )

	        this.langObj[this.currentLang].active = "active";
        },
	    setHtmlContents() {
		    let htmlContents = this.getContentsList( this.currentLang );
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
