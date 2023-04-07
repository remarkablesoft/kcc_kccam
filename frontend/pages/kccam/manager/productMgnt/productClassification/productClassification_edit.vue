<template>
	<div class="inner-wrapper">
		<!-- loading -->
        <div class="loading-container" v-if="pageLoading">
            <the-loading />
        </div>
		<!-- inner-container -->
		<div class="inner-container">
			<div class="manager-content-body">
				<!-- contents 내용 -->
				<div class="content-title">
					<div class="sub-title">
						<h2 v-if="$common.isNotEmpty($route.query.oid)">제품구분 수정</h2>
                        <h2 v-else>제품구분 등록</h2>
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
							<el-button type="primary" size="default" @click="regist">저장</el-button>
							<el-button type="gray" size="default" v-if="$route.query.oid" @click="remove">삭제</el-button>
							<el-button type="st st-primary" size="default" @click="goList">목록</el-button>
						</div>
					</div>
					<div class="input-area">
						<div class="input-row-md lr" v-if="$route.query.oid">
							<div class="left-area">
								<div class="input-label">
									<span>등록일</span>
								</div>
								<div class="input-data">
									<span v-text="$common.formatDate(inputDate)"></span>
								</div>
							</div>
							<div class="right-area">
								<div class="input-label">
									<span>최종 수정일</span>
								</div>
								<div class="input-data">
									<span v-text=" $common.isEmpty( modDate )? '-': $common.formatDate( modDate ) "></span>
								</div>
							</div>
						</div>
						<div class="input-row-md lr">
							<div class="left-area">
								<div class="input-label">
									<span>제품 구분명<em class="required">*</em></span>
								</div>
								<div class="input-data">
									<el-input placeholder="" v-model="productClassificationName" clearable></el-input>
								</div>
							</div>
							<div class="right-area">
								<div class="input-label">
									<span>상위 제품 구분</span>
								</div>
								<div class="input-data">
									<div class="manager-search-box">
										<el-input title="검색창" placeholder="검색어를 입력하세요" v-model="productParent" clearable
										          readonly></el-input>
										<el-button type="gray" class="btn-search" @click="addProductClassificationModalOpen">
											<span class="icon custom-icon-search"></span>
										</el-button>
									</div>
								</div>
							</div>
						</div>
						<div class="input-row-md">
							<div class="input-label">
								<span>소재 구분<em class="required">*</em></span>
							</div>
							<div class="input-data">
								<el-select size="sm" v-model="productClassification" placeholder="선택">
									<el-option v-for="item in searchOptions" :key="item.value" :label="item.label"
									           :value="item.value"></el-option>
								</el-select>
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
						<!-- 관련 문서 열기 -->
						<the-related-doc ref="relDoc" :doc-list="docList"/>

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
		<!-- 상위 제품 구분 모달 -->
		<the-add-parent-product-classification-modal ref="addProductClassificationModal"
		                                             @getProductClassification="getProductClassification"></the-add-parent-product-classification-modal>

	</div>
</template>
<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theAddInfoEditor from "~/components/kccam/manager/editor/TheAddInfoEditor.vue";
import theAddRelatedDocumentModal from "~/components/kccam/manager/modal/TheAddRelatedDocumentModal.vue";

import theAddParentProductClassificationModal from "~/components/kccam/manager/modal/TheAddParentProductClassificationModal";
import theRelateDoc from "~/components/common/board/manager/TheRelatedDoc.vue";
import ThePreview from "~/components/common/board/manager/ThePreview";

export default {
	layout     : "managerLayout",
	components : {
		ThePreview,
		theLoading,
		thePagination,
		theAddRelatedDocumentModal,
		theAddInfoEditor,

		theAddParentProductClassificationModal,
	},
	data() {
		return {
			// 등록일
			inputDate : "",

			// 수정일
			modDate : "",

			// 제품 구분명
			productClassificationName : "",

			// 상위 제품 구분
			productParent : "",

			// 소재 구분 리스트 & 값
			searchOptions         : [],
			productClassification : "",

			// 설명
			descr : "",

			// 수정 상태
			oid : "",

			// 제품 구분 전체목록
			// => 용도 : 제품 구분명 입력 시 중복 체크
			productClassificationList : [],

			// 관련문서 목록
			docList : [],

			listCount   : 0,
			pageSize    : 6,
			currentPage : 1,

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
	async fetch() {
		this.langObj[this.$amConstant.LANG.KO].active = "active";
		// 검색 옵션 리스트 생성
		this.searchOptions = this.getProductClassificationMap();

		this.pageLoading = true;
		// 제품구분명 비교할 목적으로 호출
		await this.fillProductClassificationList();

		// 제품 구분 정보 가져오기
		await this.getData();
		this.pageLoading = false;
	},
	computed : {
		// 소재구분 Map 불러오기
		getMaterialMap() {
			return this.$store.state.menu.materialMap;
		},
	},
	methods  : {
		/**
		 * 목록 페이지로 이동
		 */
		goList() {
			this.$router.push( this.localePath( "/kccam/manager/productMgnt/productClassification/productClassification_list" ) );
		},

		/**
		 * 제품 구분명 중복 비교할 목적으로 전체 목록 생성
		 */
		async fillProductClassificationList() {
			let cnd = {
				categoryType          : this.$amConstant.CATEGORY_TYPE.PRODUCT,
				exceptCategoryOidList : [ this.$route.query.oid ],
			};

			const url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST_ALL;
			await this.$axios.post( url, cnd ).then( res => {
				if ( this.$common.isEmpty( res.data ) ) {
					return false;
				}
				this.productClassificationList = res.data;
			} );
		},

		/**
		 * 제품 구분 전체 정보를 요청합니다.
		 */
		async getData() {
			if ( this.$common.isEmpty( this.$route.query.oid ) ) {
				return;
			}
			const oid = this.$route.query.oid;
			this.$router.push( this.localePath( {
				path  : this.$router.currentRoute.path,
				query : {
					oid : oid,
				},
			} ) );

			const url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.GET;
			let param = {
				oid          : oid,
				fillLangList : true,
				fillDoc		: true,
			};
			await this.$axios.post( url, param ).then( res => {
				this.setLangObj( res.data );
                this.getLangSave( this.currentLang );
				this.$refs.relDoc.setDocListCheck();
				this.displayData( res.data );
			} ).catch( error => {
				console.log( error );
			});
		},

		/**
		 * 관련 문서 추가 모달 열기
		 */
		addRelatedDocumentModalOpen() {
			this.$refs.addRelatedDocumentModal.open();
			this.$refs.addRelatedDocumentModal.removeAllFiles();
		},

		/**
		 * 소재 구분 옵션 리스트을 세팅합니다.
		 */
		getProductClassificationMap() {
			let data = this.getMaterialMap;
			let tempArr = [];
			_.each( data, function ( item, index ) {
				let tempObj = {
					label : item.materialName,
					value : index,
				};
				tempArr.push( tempObj );
			} );

			return tempArr;
		},

		/**
		 *  가져온 제품 구분 정보를 그려줍니다.
		 */
		displayData( info ) {

			if ( this.$common.isEmpty( info ) ) {
				return;
			}
			this.inputDate = info.inputDate; // 등록일
			this.modDate = info.modDate; // 수정일
			this.productClassificationName = info.name; // 제품 구분명
			this.productParent = this.getParentName( info.parentOid ); // 상위 제품 구분
			this.productClassification = this.getPartName( info.partOid ); // 소재 구분
			this.descr = info.descr; // 비고
			this.docList = info.docList; // 관련 문서
			// this.$refs.addInfo.displayList( info.addContentsList ); // 추가 정보 채우기

			// 여기에 처음 값을 넣습니다
			// this.setLangSave( this.currentLang );
			this.langObj[ this.currentLang ].active = "active";
		},

		/**
		 * 제품 구분 모달 열기
		 */
		addProductClassificationModalOpen() {
			this.$refs.addProductClassificationModal.open();
		},

		/**
		 * 상위 제품 구분 모달에서 선택된 제품을 input 으로 가져옵니다.
		 */
		getProductClassification( productClassification ) {
			this.productParent = productClassification.name;
		},

		/**
		 *	제품 구분 정보 등록 및 수정
		 */
		regist() {
			let info = this.setProductInfo();

			if ( this.validation() === 1 ) {
				return false;
			}
			if ( this.$route.query.oid ) {
				this.update( info );
			}
			else {
				this.insert( info );
			}
		},

		/**
		 * 등록 전 유효성 검사
		 */
		validation() {
			let result = 0;
			// 제품 구분명 중복 검사
			if ( this.duplicateCheck() === 1 ) {
				result = 1;
			}
			let essentialList = [ this.productClassification, this.productClassificationName ];
			const _self = this;
			_.each( essentialList, function ( item ) {
				if ( _self.$common.isEmpty( item ) ) {
					_self.$common.confirmSwal( "필수값을 입력해주세요.", "", "warning" );
					result = 1;
					return false;
				}
			} );
			return result;
		},

		/**
		 *  제품 구분명 중복체크
		 */
		duplicateCheck() {
			// 전체 리스트 안에 입력받은 제품 구분명이 존재하면 1 를 return
			let inputVal = this.productClassificationName;
			let result = 0;
			let _self = this;
			_.each( this.productClassificationList, function ( item ) {
				if ( inputVal === item.name ) {
					_self.$common.confirmSwal( "제품 구분명 중복", "" , "warning" );
					result = 1;
				}
			} );
			return result;
		},

		/**
		 * 저장할 제품 구분 정보를 설정합니다.
		 */
		setProductInfo() {
			let oid = this.$route.query.oid ? this.$route.query.oid : "";

			let parentOid = "";
			this.$common.isEmpty( this.productParent ) ?
				parentOid = this.$amConstant.ROOT_CATEGORY_OID.PRODUCT
				: parentOid = this.getProductParent( this.productParent );

			// 전송할 전체 객체 포장
			let info = {
				oid                  : oid,
				name                 : this.productClassificationName, // 제품 구분 이름
				categoryType         : this.$amConstant.CATEGORY_TYPE.PRODUCT, // category타입은 AMCFPRDT
				classificationObject : this.$amConstant.OBJECT_TYPE.CLASSIFICATION_PRODUCT, // object타입은 AMCP
				partOid              : this.productClassification, // 소재 구분
				parentOid            : parentOid, // 상위제품 구분
				descr                : this.descr, // 설명
				addContentsList      : this.getContentsList(),
				docList              : this.getDocInfoList(),
				langCategoryList     : [],
			};
			this.setLangSave( this.currentLang );
			this.setLangCategoryList( info );

			return info;
		},

		/**
		 * 문서리스트에 체크된 문서를 삭제합니다.
		 */
		removeDocList() {
			if ( this.$common.isEmpty( this.docList ) ) {
				return;
			}
			let removedDocList = [];
			_.each( this.docList, function ( doc ) {
				if ( !doc.isChecked ) {
					removedDocList.push( doc );
				}
			} );

			this.docList = removedDocList;
		},

		/**
		 * 관련 문서 리스트에 SFA 문서정보를 추가합니다.
		 */
		addCheckedSFADocList( sfaDocList ) {
			if ( this.$common.isEmpty( sfaDocList ) ) {
				return;
			}
			if ( this.$common.isEmpty( this.docList ) ) {
				this.docList = sfaDocList;
				return;
			}
			this.docList = [...this.docList, ...sfaDocList];
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
		 * 관련 문서 파일정보를 DocInfo 포맷에 맞춰서 반환합니다.
		 */
		getDocInfoList() {
			return this.$refs.relDoc.getDocInfoList();
		},

		/**
		 * SFA 문서 정보를 DocInfo 포맷에 맞춰줍니다.
		 */
		setSFADocInfo( doc, docInfo ) {
			docInfo.currentDocVersionInfo.docFileInfo = {};
			docInfo.currentDocVersionInfo.outLinkUrl = doc.filePath;

			docInfo.customField1 = doc.customField1;
		},

		/**
		 * 관련 문서 리스트에 체크박스 값을 설정해줍니다.
		 */
		setDocListCheck( docList ) {

			const _self = this;

			if ( this.$common.isEmpty( docList ) ) {
				return [];
			}

			let docFileList = [];
			_.each( docList, function ( doc ) {
				let docFile = doc;
				if ( _self.$common.isNotEmpty( doc.currentDocVersionInfo ) ) {
					docFile = doc.currentDocVersionInfo.docFileInfo;
				}
				docFile.isChecked = false;
				docFileList.push( docFile );
			} );

			return docFileList;
		},

		/**
		 * 제품 구분 정보를 등록합니다.
		 */
		insert( info ) {
			let param = info;
			const url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.INSERT;
			this.$axios.post( url, param ).then( res => {
					this.goList();
				} ).catch( error => {
					console.log( error );
				} );
		},

		/**
		 * 제품 구분 정보를 수정합니다.
		 */
		update( info ) {
			let param = info;
			const url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.UPDATE;
			this.$axios.post( url, param ).then( res => {
				this.goList();
			} ).catch( error => {
				console.log( error );
			} );
		},

		/**
		 * 현재 제품 구분의 상위제품구분 이름을 가져옵니다.
		 */
		getParentName( parentOid ) {
			if ( this.$common.isEmpty( parentOid ) ) {
				return null;
			}
			let result = this.productClassificationList.filter( item => item.oid.includes( parentOid ) )[ 0 ].name;

			return result;
		},
		/**
		 *  상위제품 구분의 이름으로 oid 로 변환합니다.
		 */
		getProductParent( parentName ) {
			if ( this.$common.isEmpty( parentName ) ) {
				return null;
			}
			return this.productClassificationList.filter( item => item.name.includes( parentName ) )[ 0 ].oid;
		},

		/**
		 * 소재 구분 partOid 로 소재구분명을 가져옵니다.
		 */
		getPartName( partOid ) {
			if ( this.$common.isEmpty( partOid ) ) {
				return null;
			}
			let name = this.searchOptions.filter( item => item.value.includes( partOid ) )[ 0 ].value;
			return name;
		},

		/**
		 *  lang정보를 저장해 둡니다
		 */
		setLangSave( lang ) {
			let instanceInfo = {
				name      : this.productClassificationName,
				descr     : this.descr,
				lang      : lang,
				active    : "",
				inputDate : this.inputDate,
				modDate   : this.modDate,

				langAddContentsList : this.getContentsList( lang ),
				contentsTargetObject : this.$amConstant.OBJECT_TYPE.CLASSIFICATION_PRODUCT,
			};
			this.langObj[ lang ] = instanceInfo;
		},

		getLangSave( lang ) {
			this.productClassificationName = this.langObj[ lang ].name;
			this.descr = this.langObj[ lang ].descr;
			this.inputDate = this.langObj[ lang ].inputDate;
			this.modDate = this.langObj[ lang ].modDate;
		},

		/**
		 * active
		 */
		menuActive( lang ) {
			this.setLangSave( this.currentLang );
			this.langObj[ lang ].active = "active";
			this.currentLang = lang;
			this.getLangSave( lang );
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

		/**
		 * 제품 정보를 삭제합니다.
		 */
		remove() {
			this.$common.swalWithOptions( "삭제 확인", "이 제품 구분 정보를 삭제하시겠습니까?", "info" ).then( isDelete => {
				if ( isDelete ) {
					const url = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION
						+ this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.DELETE;
					let param = {
						oid : this.$route.query.oid,
					};
					this.$axios.post( url, param ).then( res => {
						if ( 1 === res.data.success ) {
							const redirectUrl = this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT
								+ this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.PRODUCT_CLASSIFICATION;
							this.$router.push( redirectUrl );
						}
						else {
							console.log( res );
						}
					} ).catch( error => {
						console.log( error );
					} );
				}
			} );
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
