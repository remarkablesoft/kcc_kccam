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
                        <h2 v-if="$common.isNotEmpty($route.query.oid)">Market 수정</h2>
                        <h2 v-else>Market 등록</h2>
                    </div>
                </div>

                <div class="content-detail">
                    <!-- 언어 탭 버튼  -->
                    <div class="btn-group bb">
                        <div class="btn-wrap tab-style">
                             <!-- 클릭한 버튼에 activ 클래스 추가 -->
                            <el-button type="st st-gray" size="default" @click="menuActive($amConstant.LANG.KO)"
							           :class="langObj[$amConstant.LANG.KO].active">KOR
							</el-button>
							<el-button type="st st-gray" size="default" @click="menuActive($amConstant.LANG.EN)"
							           :class="langObj[$amConstant.LANG.EN].active">ENG
							</el-button>
							<el-button type="st st-gray" size="default" @click="menuActive($amConstant.LANG.CN)"
							           :class="langObj[$amConstant.LANG.CN].active">CHN
							</el-button>
                        </div>
                        <div class="btn-wrap">
                            <el-button type="primary" size="default" @click="saveMarketInfo()">저장</el-button>
                            <el-button type="gray" size="default" @click="deleteInfo()" :class="[ $common.isEmpty($route.query.oid)? 'hidden': '']">삭제</el-button>
                            <el-button type="st st-primary" size="default" @click="goList()">목록</el-button>
                        </div>
                    </div>
                    <div class="input-area">
                        <div class="input-row-md lr">
                            <div class="left-area">
                                <div class="input-label">
                                    <span>등록일</span>
                                </div>
                                <div class="input-data">
                                    <span v-text="getFormatDate(inputDate)"></span>
                                </div>
                            </div>
                            <div class="right-area">
                                <div class="input-label">
                                    <span>최종 수정일</span>
                                </div>
                                <div class="input-data">
                                    <span v-text="getFormatDate(modDate)"></span>
                                </div>
                            </div>
                        </div>

                        <div class="input-row-md lr">
                            <div class="left-area">
                                <div class="input-label">
                                    <span>Market 명<em class="required">*</em></span>
                                </div>
                                <div class="input-data">
                                    <el-input placeholder="" v-model="marketName" clearable> </el-input>
                                </div>
                            </div>
                            <div class="right-area">
                                <div class="input-label">
                                    <span>상위 Market</span>
                                </div>
                                <div class="input-data">
                                    <div class="manager-search-box">
                                        <el-input title="검색창" placeholder="버튼 클릭하여 검색" v-model="searchInput" :disabled="true" clearable></el-input>
                                        <el-button type="gray" class="btn-search" @click="openParentMarketModal">
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
                                <el-input type="textarea" :rows="5" placeholder="내용을 입력하세요." v-model="descr"> </el-input>
                            </div>
                        </div>

                        <div class="input-row-md">
                            <div class="input-label">
                                <span>Market 이미지</span>
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
                                                        <tr v-if="$common.isEmpty( productList )">
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
                                                        <tr v-for="(item, i) in productList" :key="item.oid + i" class="list-item">
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
                                                                <span v-text="item.materialInfo.name"></span>
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

        <!-- Detail Market 추가 모달 -->
        <!-- <the-add-detail-market-modal ref="addDetailMarketModal"></the-add-detail-market-modal> -->
        <!-- 상위마켓 추가 모달 -->
        <the-add-market-modal ref="addMarketModal" :market-info="marketInfo" @getMarketClassification="setParentMarketInfo"> </the-add-market-modal>

        <!-- 제품 추가 모달 -->
        <the-add-product-modal ref="addProductModal" :product-check-type="productCheckType" :product-list="productList" :removed-product-list="removedProductList" @addProductList="setAddProductList">
        </the-add-product-modal>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import theDropzone from "~/components/common/dropzone/TheDropZone.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theAddDetailMarketModal from "~/components/kccam/manager/modal/TheAddDetailMarketModal.vue";
import theAddProductModal from "~/components/kccam/manager/modal/TheAddProductModal.vue";
import theAddInfoEditor from "~/components/kccam/manager/editor/TheAddInfoEditor.vue";
import theAddMarketModal from "~/components/kccam/manager/modal/TheAddMarketModal.vue";
import ThePreview from "~/components/common/board/manager/ThePreview";
import { mapActions } from "vuex";

export default {
    layout: "managerLayout",
    components: {
	    ThePreview,
        theLoading,
        theDropzone,
        thePagination,
        theAddDetailMarketModal,
        theAddProductModal,

        theAddInfoEditor,
        theAddMarketModal,
    },
    data() {
        return {
	        //로딩 indicator
	        pageLoading : false,

            marketInfo: {},
            marketName: "",
            materialClassificationSubtitle: "",
            descr: "",
            inputDate: "",
            modDate: "",
            imgFile: [],

            parentMarketInfo: {},
            searchInput: "",

            // Detail market 목록
            // detailMarketList: [
            //     {
            //         id: "detailMarket01",
            //         detailMarketName: "DARM",
            //         depth: "2",
            //     },
            //     {
            //         id: "detailMarket02",
            //         detailMarketName: "DARM",
            //         depth: "3",
            //     },
            // ],

            // 제품구성 목록
            productList: [],
            // 체크된 제품 목록
            checkedProductList: [],
            // 삭제된 제품 목록
            removedProductList: [],

            dropzoneMount: false,

            productCheckType: "check",

            // 현재 페이지 언어
			currentLang : this.$amConstant.LANG.KO,
			langObj     : {
				ko : {},
				en : {},
				cn : {},
			},
	        editorMounted : false,

	        // 미리보기 컴포넌트 관련
	        htmlContents   : [],
	        previewMounted : false,
        };
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    watch: {
        dropzoneMount() {
            if (this.dropzoneMount) {
                // 첨부파일 세팅
                if (this.$common.isNotEmpty(this.marketInfo.iconFile)) {
                    let fileList = [];
                    fileList.push(this.marketInfo.iconFile);

                    // 드랍존의 파일을 모두 지운 후 다시 세팅
                    this.$refs.dropzoneFile.removeAllFiles();
                    this.$refs.dropzoneFile.setUploadFileList(fileList);
                }
            }
        },
	    editorMounted() {
        	if ( this.editorMounted ) {
        		this.setLangSave(this.currentLang);
	        }
	    },
	    previewMounted() {
		    if ( this.previewMounted ) {
			    this.setHtmlContents( this.currentLang );
		    }
	    }
    },
    async fetch() {

	    this.langObj[this.$amConstant.LANG.KO].active = "active";
        await this.getMarketInfo();

    },
    methods: {
	    ...mapActions ( {
		    setClassificationListEmpty : "classification/setClassificationListEmpty"
	    } ),

        // 상위 마켓 모달 오픈
        openParentMarketModal() {
            this.$refs.addMarketModal.open();
        },

        // 목록 페이지로 이동
        goList() {
            const urlList = this.$urlConstant.MENU_URL_PREFIX.MANAGER_MARKET_MGNT + this.$urlConstant.MENU_URL_SUFFIX.MARKET.MARKET_LIST;
            if (this.$route.query.searchText) {
                this.$router.push( this.localePath({
	                path: urlList,
	                query: { searchText: this.$route.query.searchText },
                }));
            }
            else {
                this.$router.push( this.localePath (urlList) );
            }
        },

        //마켓 정보 데이터를 불러옵니다.
        async getMarketInfo() {
            const urlGet = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.GET;

            //등록페이지인 경우 마켓정보를 불러오지 않습니다.
            if (!this.$route.query.oid) {
                return;
            }

            let cnd = {
                oid: this.$route.query.oid,
                categoryType: this.$amConstant.CATEGORY_TYPE.MARKET,
                fillIconFile: true,
                fillProduct: true,
                fillMaterial: true,
                fillLangList: true,
            };

            await this.$axios.post(urlGet, cnd).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.marketInfo = {};
                    this.productList = [];
                    return;
                }

                this.marketInfo = res.data;
	            this.marketName = res.data.name;
                this.descr = res.data.descr;
                this.setFileInfo();
                // this.$refs.addInfo.displayList(this.marketInfo.addContentsList);

                this.productList = res.data.productList;
                this.parentMarketInfo = { oid: res.data.parentOid };
                this.setSearchInput({ oid: res.data.parentOid, name: res.data.parentName });

                this.setLangObj( res.data );
                this.getLangSave( this.currentLang );
                // lang값 할당
                // this.setLangSave(this.currentLang);
                this.langObj[this.currentLang].active = "active";

            });
        },

        // modal에서 추가된 제품들을 제품구성 리스트에 추가해줍니다.
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
            if (this.$common.isNotEmpty(this.marketInfo.iconFile) && this.dropzoneMount) {
                let fileList = [];
                fileList.push(this.marketInfo.iconFile);

                // 드랍존의 파일을 모두 지운 후 다시 세팅
	            if ( this.$common.isNotEmpty( this.$refs.dropzoneFile ) ) {
		            this.$refs.dropzoneFile.removeAllFiles();
		            this.$refs.dropzoneFile.setUploadFileList(fileList);
		            this.imgFile = this.marketInfo.iconFile;
	            }
            }
        },

        //마켓 정보를 저장합니다
        saveMarketInfo() {
            if (this.$common.isEmpty(this.marketName)) {
                this.$common.confirmSwal("Market 명을 입력해주세요.", "", "warning");
                return;
            }

            if (this.$common.isEmpty(this.marketInfo)) {
                this.insertInfo();
            } else {
                this.updateInfo();
            }
        },

        //마켓정보를 update합니다.
        updateInfo() {
            const urlUpdate = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.UPDATE;
            let info = {
                oid: this.$route.query.oid,
                name: this.marketName,
                categoryType: this.$amConstant.CATEGORY_TYPE.MARKET,
                descr: this.descr,
                classificationObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_MARKET,
                langCategoryList : [],

                // addContentsList: this.getContentsList(),
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

            if (this.$common.isNotEmpty(this.parentMarketInfo)) {
                info.parentOid = this.parentMarketInfo.oid;
            }

	        this.$axios.post(urlUpdate, info).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.marketInfo = {};
                    return;
                }
		        this.setClassificationListEmpty();
                this.goList();
            });
        },

        //새로운 마켓정보를 등록합니다.
        insertInfo() {
            const urlInsert = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.INSERT;

            let info = {
                name: this.marketName,
                categoryType: this.$amConstant.CATEGORY_TYPE.MARKET,
                descr: this.descr,
                parentOid: this.$amConstant.ROOT_CATEGORY_OID.MARKET,
                classificationObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_MARKET,
                langCategoryList: [],

                // addContentsList: this.getContentsList(),
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

            if (this.$common.isNotEmpty(this.parentMarketInfo)) {
                info.parentOid = this.parentMarketInfo.oid;
            }

            this.$axios.post(urlInsert, info).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.marketInfo = {};
                    this.$common.confirmSwal("Market 정보 저장", "저장실패", "warning");
                    return;
                }

                this.marketInfo = res.data;
	            this.setClassificationListEmpty();
                // this.$common.confirmSwal("Market 정보 저장", "저장완료", "success").then(result => {
                //     this.goList();
                // });
                this.goList();
            });
        },

        setProductRelList() {
            if (this.$common.isEmpty(this.productList)) {
                return;
            }

            let productRelList = [];
            let _self = this;
            this.productList.forEach(function(product) {
                productRelList.push({ productOid: product.oid, targetObject: _self.$amConstant.OBJECT_TYPE.CLASSIFICATION_MARKET });
            });

            return productRelList;
        },

        async deleteInfo() {

            await this.$common.swalWithOptions("삭제 확인", "해당 마켓 정보를 삭제하시겠습니까?", "info").then(isDelete => {
			    if (isDelete) {
				    const URL_DELETE = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.DELETE;

				    let param = {
					    oid: this.$route.query.oid,
				    };
				    this.$axios.post(URL_DELETE, param).then(res => {
					    if (1 === res.data.success) {

							this.setClassificationListEmpty();
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
        setParentMarketInfo(data) {
            this.parentMarketInfo = data;
            this.setSearchInput(data);
        },

        setSearchInput(marketInfo) {
            if (this.$common.isEmpty(marketInfo)) {
                return;
            }
            if (marketInfo.oid === this.$amConstant.ROOT_CATEGORY_OID.MARKET) {
                this.searchInput = "상위 마켓이 없습니다";
            } else {
                this.searchInput = marketInfo.name;
            }
        },

        // 대표 이미지
        setStorageImageFile(data) {
            this.imgFile = data;
        },

        //  Detail Application 추가 모달 열기
        addDetailMarketModalOpen() {
            this.$refs.addDetailMarketModal.open();
        },

        // 제품 추가 모달 열기
        addProductModalOpen() {
            this.$refs.addProductModal.open();
        },

        dropzoneMounted() {
            this.dropzoneMount = true;
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

	    displayContentsList( lang, addContentsList ) {
		    switch ( lang ){
			    case this.$amConstant.LANG.KO : this.$refs.addInfoKO.displayList(addContentsList); return;
			    case this.$amConstant.LANG.EN : this.$refs.addInfoEN.displayList(addContentsList); return;
			    case this.$amConstant.LANG.CN : this.$refs.addInfoCN.displayList(addContentsList); return;
		    }
	    },

        // 날짜 포맷
        getFormatDate(date) {
            if (this.$common.isEmpty(date)) {
                return;
            }
            let datefmt = new Date(date);
            let year = datefmt.getFullYear();
            let month = 1 + datefmt.getMonth();
            let day = datefmt.getDate();

            month = month >= 10 ? month : "0" + month;
            day = day >= 10 ? day : "0" + day;

            return [year, month, day].join("-");
        },

        /**
         *  lang정보를 저장해 둡니다
         */
        setLangSave(lang) {
            let instanceInfo = {
                name: this.marketName,
                descr: this.descr,
                lang: lang,
                active: "",
                inputDate: this.inputDate,
                modDate: this.modDate,
	            langAddContentsList : this.getContentsList( lang ),
	            contentsTargetObject : this.$amConstant.OBJECT_TYPE.CLASSIFICATION_MARKET,
            };
            this.langObj[lang] = instanceInfo;
        },

        getLangSave(lang) {
            this.marketName = this.langObj[lang].name;
            this.descr = this.langObj[lang].descr;
            this.inputDate = this.langObj[ lang ].inputDate;
			this.modDate = this.langObj[ lang ].modDate;
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

			if ( this.$common.isNotEmpty( this.langObj.ko ) ) {
				info.langCategoryList.push( this.langObj.ko );
			}

			if ( this.$common.isNotEmpty( this.langObj.en ) ) {
				info.langCategoryList.push( this.langObj.en );
			}

			if ( this.$common.isNotEmpty( this.langObj.cn ) ) {
				info.langCategoryList.push( this.langObj.cn );
			}
		},

		setLangObj( info ) {
			if ( this.$common.isEmpty( info ) || this.$common.isEmpty( info.langCategoryList ) ) {
				return;
			}

			info.langCategoryList.forEach( langCategory => {

				let lang = this.$amConstant.LANG[ langCategory.lang ];


				this.langObj[ lang ] = langCategory;

				if( this.$common.isNotEmpty( langCategory.langAddContentsList ) ){
					this.displayContentsList( lang, langCategory.langAddContentsList );
				}

			} )

			this.langObj[this.currentLang].active = "active";
		},
		setChecked( item ) {
			item.isChecked = !item.isChecked;
			this.$forceUpdate();
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
