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
                        <h2 v-if="$common.isNotEmpty($route.query.oid)">Function 수정</h2>
                        <h2 v-else>Function 등록</h2>
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
                            <el-button type="primary" size="default" @click="saveInfo()">저장</el-button>
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
                                    <span v-text="$common.formatDate(inputDate)">/</span>
                                </div>
                            </div>
                            <div class="right-area">
                                <div class="input-label">
                                    <span>최종 수정일</span>
                                </div>
                                <div class="input-data">
                                    <span v-if="modDate" v-text="$common.formatDate(modDate)"></span>
									<span v-else>-</span>
                                </div>
                            </div>
                        </div>

                        <div class="input-row-md">
                            <div class="input-label">
                                <span>Function 명<em class="required">*</em></span>
                            </div>
                            <div class="input-data">
                                <el-input placeholder="" v-model="functionName" clearable> </el-input>
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
                        </div>

                        <div class="input-row-md">
                            <div class="input-label">
                                <span>설명</span>
                            </div>
                            <div class="input-data">
                                <el-input type="textarea" :rows="5" placeholder="내용을 입력하세요." v-model="functionDescr"> </el-input>
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
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theAddInfoEditor from "~/components/kccam/manager/editor/TheAddInfoEditor.vue";
import ThePreview from "~/components/common/board/manager/ThePreview";

export default {
    layout: "managerLayout",
    components: {
	    ThePreview,
        theLoading,
        thePagination,
        theAddInfoEditor,
    },
    data() {
        return {
            functionInfo: {},
            functionName: "", //Function명
            functionDescr: "", //설명
            docList: [], //추가정보 리스트

            inputDate: "",
            modDate: "",

            //소재구분 선택
            materialSearchOptions: [],
            materialSearchOptionsValue: "",

            // 현재 페이지 언어
			currentLang : this.$amConstant.LANG.KO,
			langObj     : {
				ko : {},
				en : {},
				cn : {},
			},

	        //로딩 indicator
	        pageLoading : false,
	        editorMounted : false,

	        // 미리보기 컴포넌트 관련
	        htmlContents   : [],
	        previewMounted : false,
        };
    },
	async fetch() {
		this.langObj[this.currentLang].active = "active";

	    // this.pageLoading = true;

	    await this.getFunctionInfo();

	    // this.pageLoading = false;
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
	watch : {
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
    methods: {
        //Function data를 불러옵니다.
        async getFunctionInfo() {

            const urlGet = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.GET;
            this.materialSearchOptions = this.$store.getters["menu/materialMap"];

            //신규 등록일 경우 Function 정보를 불러오지 않습니다.
            if ( this.$common.isEmpty( this.$route.query.oid ) ) {
                return;
            }

            let cnd = {
                oid: this.$route.query.oid,
                categoryType: this.$amConstant.CATEGORY_TYPE.FUNCTION,
                fillMaterial: true,
                fillLangList : true,
            };
            await this.$axios.post(urlGet, cnd).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.functionInfo = {};
                    return;
                }
                this.functionInfo = res.data;
                this.functionName = res.data.name;
                this.functionDescr = res.data.descr;
                this.materialSearchOptionsValue = res.data.materialInfo.oid;

                this.setLangObj( res.data );
                this.getLangSave( this.currentLang );

                // lang값 할당
                // this.setLangSave(this.currentLang);
	            this.langObj[this.$amConstant.LANG.KO].active = "active";
            });
        },

        //수정 사항을 저장합니다.
        saveInfo() {
            if (this.$common.isEmpty(this.functionName)) {
                this.$common.confirmSwal("function 명을 입력해주세요.", "", "warning");
                return;
            }

            if (this.$common.isEmpty(this.materialSearchOptionsValue)) {
                this.$common.confirmSwal("소재 구분을 선택해주세요", "", "warning");
                return;
            }

            if (this.$common.isEmpty(this.functionInfo)) {
                this.insertInfo();
            } else {
                this.updateInfo();
            }
        },

        //신규 Function을 등록합니다.
        insertInfo() {
            const urlInsert = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.INSERT;

            let info = {
                categoryType: this.$amConstant.CATEGORY_TYPE.FUNCTION,
                name: this.functionName,
                descr: this.functionDescr,
                classificationObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_FUNCTION,
                langCategoryList : [],

                partOid: this.materialSearchOptionsValue,
            };

            this.setLangSave( this.currentLang );
			this.setLangCategoryList( info );
            this.$axios.post(urlInsert, info).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }
                this.goList();
            });
        },

        //Function 정보를 update합니다.
        updateInfo() {
            const urlUpdate = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.UPDATE;

            let info = {
                oid: this.$route.query.oid,
                categoryType: this.$amConstant.CATEGORY_TYPE.FUNCTION,
                name: this.functionName,
                descr: this.functionDescr,
                classificationObject: this.$amConstant.OBJECT_TYPE.CLASSIFICATION_FUNCTION,
                langCategoryList: [],

                partOid: this.materialSearchOptionsValue,
            };

            this.setLangSave( this.currentLang );
			this.setLangCategoryList( info );

            this.$axios.post(urlUpdate, info).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.functionInfo = {};
                    this.$common.confirmSwal("Function 정보 저장", "저장실패", "warning");
                    return;
                }
                this.functionInfo = res.data;
                this.functionName = res.data.name;
                this.functionDescr = res.data.descr;
                this.goList();
            });
        },

        async deleteInfo() {

            await this.$common.swalWithOptions("삭제 확인", "해당 마켓 정보를 삭제하시겠습니까?", "info").then(isDelete => {
			    if (isDelete) {
				    const URL_DELETE = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.DELETE;

				    let param = {
					    oid: this.$route.query.oid,
				    };
				    this.$axios.post( URL_DELETE, param).then(res => {
					    if (1 === res.data.success) {
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
        //Function 목록으로 이동합니다.
        goList() {
            const urlList = this.$urlConstant.MENU_URL_PREFIX.MANAGER_MARKET_MGNT + this.$urlConstant.MENU_URL_SUFFIX.MARKET.FUNCTION_LIST;

            let pushObj = {
                path: urlList,
                query: {},
            };

            if (this.$route.query.searchQuery) {
                pushObj.query.searchQuery = this.$route.query.searchQuery;
            }
            if (this.$route.query.page) {
                pushObj.query.page = this.$route.query.page;
            }

            this.$router.push( this.localePath( pushObj ) );
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
	     * active
	     */
	    menuActive(lang) {
		    this.setLangSave(this.currentLang);
		    this.langObj[lang].active = "active";
		    this.currentLang = lang;
		    this.getLangSave(lang);

		    this.inputDate = this.langObj[ lang ].inputDate;
		    this.modDate = this.langObj[ lang ].modDate;
	    },

        /**
         *  lang정보를 저장해 둡니다
         */
        setLangSave(lang) {
            let instanceInfo = {
                name: this.functionName,
                descr: this.functionDescr,
                lang: lang,
                active: "",
                inputDate : this.inputDate,
				modDate   : this.modDate,

	            langAddContentsList : this.getContentsList( lang ),
	            contentsTargetObject : this.$amConstant.OBJECT_TYPE.CLASSIFICATION_FUNCTION,
            };

            this.langObj[lang] = instanceInfo;
        },

        getLangSave(lang) {
            this.functionName = this.langObj[lang].name;
            this.functionDescr = this.langObj[lang].descr;
            this.inputDate = this.langObj[ lang ].inputDate;
			this.modDate = this.langObj[ lang ].modDate;
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
