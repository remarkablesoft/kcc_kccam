<template>
	<div class="inner-wrapper">
		<!-- loading -->
		<div class="loading-container" v-if="loadingIndicator > 0 || pageLoading">
			<the-loading/>
		</div>
		<!-- inner-container -->
		<div class="inner-container">
			<div class="manager-content-body">
				<!-- contents 내용 -->
				<div class="content-title">
					<div class="sub-title">
						<h2>소재구분 수정</h2>
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
							<el-button type="primary" size="default" @click="update">저장</el-button>
							<el-button type="st st-primary" size="default" @click="goList()">목록</el-button>
						</div>
					</div>
					<div class="input-area">
						<div class="input-row-md">
							<div class="input-label">
								<span>최종 수정일</span>
							</div>
							<div class="input-data">
								<span
									v-text=" $common.isEmpty(materialClassificationInfo.modDate)? '-' : $common.formatDate(materialClassificationInfo.modDate)"></span>
							</div>
						</div>
						<div class="input-row-md">
							<div class="input-label">
								<span>소재 구분명<em class="required">*</em></span>
							</div>
							<div class="input-data">
								<el-input title="소재 구분명" placeholder="" v-model="materialClassificationName" clearable></el-input>
							</div>
						</div>
						<div class="input-row-md">
							<div class="input-label">
								<span>소재 구분 부제 </span>
							</div>
							<div class="input-data">
								<el-input title="소재 구분 부제" placeholder="" v-model="materialClassificationSubtitle" clearable></el-input>
							</div>
						</div>
						<div class="input-row-md">
							<div class="input-label">
								<span>소재 구분 이미지</span>
							</div>
							<div class="input-data">
								<div class="w100">
									<p class="txt-info">* 이미지 최적화 사이즈 : 450X350, 최적화 사이즈에 맞게 올려주세요.</p>
									<!-- dropzone -->
									<the-dropzone class="input-dropzone" ref="dropzoneFile" acceptedFiles=".jpeg,.jpg,.png"
									              @setUploadFile="setStorageImageFile" @dropzoneMounted="dropzoneMounted"/>
								</div>
							</div>
						</div>
						<div class="input-row-md">
							<div class="input-label">
								<span>설명</span>
							</div>
							<div class="input-data">
								<el-input title="설명" type="textarea" :rows="5" placeholder="내용을 입력하세요." v-model="descr"></el-input>
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
						<!-- 통계(차트) -->
						<div class="chart-area">
							<div class="cnt-txt-group">
								<div class="cnt-item" v-for="(item, i) in cntChartList" :key="i">
									<div class="tit-icon-wrap">
										<span class="tit" v-text="item.title"></span>
										<el-button type="icon-only" @click="goRelatedList(item.url)">
											<span class="material-icons">add_circle</span>
										</el-button>
									</div>
									<div class="cnt-wrap">
										<span class="cnt" v-text="item.cnt"></span>
									</div>
								</div>
								<div class="cnt-item">
									<div class="tit-icon-wrap">
										<span class="tit" v-text="averageCount.title"></span>
									</div>
									<div class="cnt-wrap">
										<span class="cnt" v-text="averageCount.cnt"></span>
									</div>
								</div>
							</div>
							<!-- chart -->
							<div class="chart-wrap">
								<highcharts ref="monthChart" :options="monthChartOptions" class="chart"></highcharts>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 참고자료 추가 모달 -->
		<!--        <the-add-reference-modal ref="addReferenceModal"></the-add-reference-modal>-->
	</div>
</template>
<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import theDropzone from "~/components/common/dropzone/TheDropZone.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theAddRelatedDocumentModal from "~/components/kccam/manager/modal/TheAddRelatedDocumentModal.vue";
// import theAddReferenceModal from "~/components/kccam/manager/modal/TheAddReferenceModal.vue";
import theAddInfoEditor from "~/components/kccam/manager/editor/TheAddInfoEditor.vue";
import theRelatedDoc from "~/components/common/board/manager/TheRelatedDoc.vue";

import Vue from "vue";
import Highcharts from "highcharts";
import HighchartsVue from "highcharts-vue";
import ThePreview from "~/components/common/board/manager/ThePreview";

Vue.use( HighchartsVue );

export default {
	layout     : "managerLayout",
	components : {
		ThePreview,
		theLoading,
		theDropzone,
		thePagination,
		theAddRelatedDocumentModal,
		// theAddReferenceModal,
		theAddInfoEditor,
		theRelatedDoc,
	},
	data() {
		return {
			materialClassificationInfo     : {},
			materialClassificationName     : "",
			materialClassificationSubtitle : "",
			descr                          : "",
			imgFile                        : [],
			dropzoneMount                  : false,

			averageCount : {
				title : "월 평균 조회 수",
				cnt   : 0,
			},

			// 관련문서 목록
			docList : [],

			// 참고자료 목록
			referenceList : [],

			// 수 차트 목록
			cntChartList : [],

			// 월간 통계
			monthChartOptions : {
				chart       : {
					type   : "line",
					width  : "880",
					height : "300",
					style  : {
						fontFamily : "Noto Sans KR, Noto Sans Korean, serif, sans-serif",
					},
				},
				title       : {
					text : "월별 소재 제품 조회수 변화",
				},
				credits     : {
					// highcharts.com 지우기
					enabled : false,
				},
				xAxis       : {
					categories : [],
				},
				yAxis       : {
					min          : 0,
					tickInterval : 100,
					title        : {
						text : "",
					},
					labels       : {
						// format: '{value}월',
					},
				},
				legend      : {
					itemStyle : {
						fontSize   : "13px",
						fontWeight : 400,
					},
				},
				plotOptions : {
					series : {
						column      : true,
						borderWidth : 0,
						dataLabels  : {
							enabled : true,
							format  : "{point.y:,.0f}",
						},
					},
				},
				series      : [
					{
						name  : "View Count",
						data  : [],
						color : "#5551f7",
					},
				],
			},

			// 현재 페이지 언어
			currentLang : this.$amConstant.LANG.KO,
			langObj     : {
				ko : {},
				en : {},
				cn : {},
			},

			//로딩 indicator
			pageLoading   : false,
			editorMounted : false,

			// 미리보기 컴포넌트 관련
			htmlContents   : [],
			previewMounted : false,
		};
	},
	computed : {
		loadingIndicator() {
			return this.$root.$loading.percent;
		},
	},
	async fetch() {
		if ( !process.client ) {
			return;
		}

		this.pageLoading = true;
		await this.getMaterialInfo();
		this.pageLoading = false;
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

	methods : {
		// 목록 페이지로 이동
		goList() {
			const urlList = this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.MATERIAL;
			this.$router.push( this.localePath( urlList ) );
		},

		//통계 차트의 + 버튼을 누르면 해당 리스트 화면으로 이동
		goRelatedList( url ) {
			if ( this.$common.isEmpty( url ) ) {
				return;
			}
			this.$router.push( this.localePath( url ) );
		},
		/**
		 * 소재정보를 수정합니다.
		 */
		update() {
			if ( this.$common.isEmpty( this.$route.query.oid ) ) {
				return;
			}
			const url = this.$urlConstant.API_URL_PREFIX.MATERIAL + this.$urlConstant.API_URL_SUFFIX.MATERIAL.UPDATE;

			let info = this.setSaveInfo();

			// 들어오는 값이 lang에관한 값이들어오면 됩니다
			this.$axios.post( url, info ).then( res => {
				// console.log(res);
				// this.$common.confirmSwal("소재구분 수정", "저장완료", "success");
				this.goList();
			} );
		},

		/**
		 * 저장
		 */
		setSaveInfo() {
			let info = {
				oid              : this.$route.query.oid || "",
				docList          : this.getDocInfoList(),
				langMaterialList : [],
			};

			this.setLangSave( this.currentLang );
			this.setLangMaterialList( info );

			if ( this.$common.isNotEmpty( this.imgFile ) ) {
				info.mainImg = this.imgFile[ 0 ];
			}
			return info;
		},


		// material 구분 정보 데이터를 불러옵니다
		async getMaterialInfo() {
			const urlGet = this.$urlConstant.API_URL_PREFIX.MATERIAL + this.$urlConstant.API_URL_SUFFIX.MATERIAL.GET;
			let cnd = { oid : this.$route.query.oid, fillProductCnt : true, fillLangList : true, };

			await this.$axios.post( urlGet, cnd ).then( res => {
				if ( this.$common.isEmpty( res.data ) ) {
					this.materialClassificationInfo = {};
					return;
				}
				this.materialClassificationInfo = res.data;
				this.materialClassificationName = res.data.name;
				this.materialClassificationSubtitle = res.data.className;
				this.descr = res.data.descr;
				this.docList = res.data.docList;
				this.$refs.relDoc.setDocListCheck();


				this.setCntChartList( res.data );
				this.setFileInfo();
				this.getCountList( res.data.oid );
				// this.$refs.addInfo.displayList(this.materialClassificationInfo.addContentsList);
				// lang값 할당
				this.setLangObj( res.data );
				// this.setLangSave(this.currentLang);
				this.langObj[ this.currentLang ].active = "active";
			} );
		},

		/**
		 * 관련문서를 가져옵니다.
		 */
		getDocInfoList() {
			return this.$refs.relDoc.getDocInfoList();
		},

		/**
		 * 차트 리스트 정보를 설정해줍니다
		 */
		setCntChartList( info ) {
			this.cntChartList = [];

			let pushObj = {};

			if ( info.productCnt != 0 ) {
				pushObj = {
					title : "제품 수",
					cnt   : info.productCnt,
					url   : this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.PRODUCT_LIST
				};
				this.cntChartList.push( pushObj );
			}
			if ( info.productClassificationCnt != 0 ) {
				pushObj = {
					title : "제품 구분 수",
					cnt   : info.productClassificationCnt,
					url   : this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.PRODUCT_CLASSIFICATION,
				};
				this.cntChartList.push( pushObj );
			}
			if ( info.applicationCnt != 0 ) {
				pushObj = {
					title : "Application 수",
					cnt   : info.applicationCnt,
					url   : this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.APPLICATION_LIST,
				};
				this.cntChartList.push( pushObj );
			}
			if ( info.functionCnt != 0 ) {
				pushObj = {
					title : "Function 수",
					cnt   : info.functionCnt,
					url   : this.$urlConstant.MENU_URL_PREFIX.MANAGER_MARKET_MGNT + this.$urlConstant.MENU_URL_SUFFIX.MARKET.FUNCTION_LIST
				};
				this.cntChartList.push( pushObj );
			}
		},

		setFileInfo() {
			if ( this.$common.isNotEmpty( this.materialClassificationInfo.mainImg ) && this.dropzoneMount ) {
				let fileList = [];
				fileList.push( this.materialClassificationInfo.mainImg );

				// 드랍존의 파일을 모두 지운 후 다시 세팅합니다
				this.$refs.dropzoneFile.removeAllFiles();
				this.$refs.dropzoneFile.setUploadFileList( fileList );
				this.imgFile = [ this.materialClassificationInfo.mainImg ];
			}
		},

		// 대표 이미지
		setStorageImageFile( data ) {
			this.imgFile = data;
		},

		dropzoneMounted() {
			this.dropzoneMount = true;
		},

		// 관련문서 추가 모달 열기
		addRelatedDocumentModalOpen() {
			this.$refs.addRelatedDocumentModal.open();
		},

		// // 참고자료 추가 모달 열기
		// addReferenceModalOpen() {
		//     this.$refs.addReferenceModal.open();
		// },

		/**
		 * Froala 에디터에서 데이터를 가져옵니다.
		 */
		getContentsList( lang ) {
			switch ( lang ) {
				case this.$amConstant.LANG.KO :
					return this.$refs.addInfoKO.getContentsList();
				case this.$amConstant.LANG.EN :
					return this.$refs.addInfoEN.getContentsList();
				case this.$amConstant.LANG.CN :
					return this.$refs.addInfoCN.getContentsList();
			}
		},

		displayContentsList( lang, addContentsList ) {
			switch ( lang ) {
				case this.$amConstant.LANG.KO :
					this.$refs.addInfoKO.displayList( addContentsList );
					return;
				case this.$amConstant.LANG.EN :
					this.$refs.addInfoEN.displayList( addContentsList );
					return;
				case this.$amConstant.LANG.CN :
					this.$refs.addInfoCN.displayList( addContentsList );
					return;
			}
		},

		/**
		 * 조회수 리스트를 가져옵니다.
		 */
		async getCountList( oid ) {
			const url = this.$urlConstant.API_URL_PREFIX.AUDIT_VIEW + this.$urlConstant.API_URL_SUFFIX.AUDIT_VIEW.GROUP_BY_COUNT_LIST;
			let groupByList = [ "VIEW_YEAR", "VIEW_MONTH" ];
			let orderByList = [ "VIEW_YEAR", "VIEW_MONTH" ];

			let param = {
				targetOid    : oid,
				targetObject : this.$amConstant.OBJECT_TYPE.MATERIAL,
				groupByList  : groupByList,
				orderByList  : orderByList,
			};

			await this.$axios.post( url, param ).then( res => {
				let list = res.data;
				this.displayHighChart( list );
				this.setAverageCount( list );
				// console.log( res.data );
			} );

		},

		setAverageCount( list ) {
			if ( this.$common.isEmpty( list ) ) {
				return;
			}
			let latestCount = list[ list.length - 1 ];
			this.averageCount = {
				title : "월 평균 조회수",
				cnt   : latestCount.countVC,
			};
		},
		/**
		 * 하이차트를 그려줍니다.
		 */
		displayHighChart( list ) {
			if ( this.$common.isEmpty( list ) ) {
				return;
			}

			let cntArr = [];
			let xAxisArr = [];
			_.each( list, function ( item, index ) {
				cntArr.push( item.countVC );
				xAxisArr.push( item.viewYear + "-" + item.viewMonth );
			} );

			this.monthChartOptions.xAxis.categories = xAxisArr;
			this.monthChartOptions.series[ 0 ].data = cntArr;
		},

		/**
		 *  lang정보를 저장해 둡니다
		 */
		setLangSave( lang ) {
			let instanceInfo = {
				name      : this.materialClassificationName,
				className : this.materialClassificationSubtitle,
				descr     : this.descr,
				lang      : lang,
				active    : "",
				inputDate : this.inputDate,
				modDate   : this.modDate,

				langAddContentsList : this.getContentsList( lang ),
			};
			this.langObj[ lang ] = instanceInfo;
		},

		getLangSave( lang ) {
			this.materialClassificationName = this.langObj[ lang ].name;
			this.materialClassificationSubtitle = this.langObj[ lang ].className;
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

		setLangMaterialList( info ) {
			if ( this.$common.isEmpty( info ) || this.$common.isEmpty( this.langObj ) ) {
				return;
			}

			if ( this.$common.isNotEmpty( this.langObj.ko ) ) {
				info.langMaterialList.push( this.langObj.ko );
			}

			if ( this.$common.isNotEmpty( this.langObj.en ) ) {
				info.langMaterialList.push( this.langObj.en );
			}

			if ( this.$common.isNotEmpty( this.langObj.cn ) ) {
				info.langMaterialList.push( this.langObj.cn );
			}
		},

		setLangObj( info ) {
			if ( this.$common.isEmpty( info ) || this.$common.isEmpty( info.langMaterialList ) ) {
				return;
			}

			info.langMaterialList.forEach( langMaterial => {

				let lang = this.$amConstant.LANG[ langMaterial.lang ];


				this.langObj[ lang ] = langMaterial;

				if ( this.$common.isNotEmpty( langMaterial.langAddContentsList ) ) {
					this.displayContentsList( lang, langMaterial.langAddContentsList );
				}
			} )

			this.langObj[ this.currentLang ].active = "active";
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

.chart-area {
	margin-top: 3rem;
}
</style>
