<template>
	<div>
		<div class="inner-content sub manager">
			<section class="division-section default-w sub-content survey-popup-edit">
				<!-- content-body -->
				<div class="content-body popup-edit">
					<!-- main-content -->
					<div class="sub-content">
						<div class="inner">
							<div class="inner-content-header">
								<div class="left-area flex">
									<h3 class="tit" v-text="popupInfo.oid ? '팝업 수정' : '팝업 등록'"></h3>
									<!--							<em class="em-txt ml0 required">* 필수 입력값입니다.</em>-->
								</div>
								<div class="right-area">
									<span><b>※ 팝업 보기 타입이 '일반 타입'일 경우에만, 미리보기가 가능합니다. </b></span>
									<el-button size="regular" type="default" @click="openPreviewModal()">
										<span class="material-icons-outlined">preview</span>
										<span>미리보기</span>
									</el-button>
									<el-button size="regular" type="default" @click="save()">
										<i v-if="popupInfo.oid === ''" class="material-icons">assignment_turned_in</i>
										<i v-else class="material-icons-outlined">create</i>
										<span v-text="popupInfo.oid === '' ? '등록' : '수정'">등록</span>
									</el-button>
									<el-button v-if="popupInfo.oid" class="flex-size min-wd8 red" size="regular" type="default"
									           @click="deleteOnePopUp()">
										<span class="material-icons">delete_outline</span>
										<span>삭제</span>
									</el-button>
									<el-button size="regular" type="default" @click="onClickGoList">
										<span class="material-icons">menu</span>
										<span>목록</span>
									</el-button>
								</div>
							</div>
							<div class="inner-content">
								<!-- table-center -->
								<div class="table-normal board-edit">
									<table>
										<caption class="hidden">
											팝업 등록
										</caption>
										<colgroup>
											<col style="width: 5%"/>
											<col style="width: 10%"/>
											<col style="width: 10%"/>
											<col style="width: auto"/>
										</colgroup>
										<tbody>
										<tr>
											<th scope="col">
												<span>제목<em class="required">*</em></span>
											</th>
											<td colspan="3">
												<el-input placeholder="제목을 입력하세요" v-model="popupInfo.name"/>
											</td>
										</tr>
										<tr>
											<th scope="col">
												<span>노출 기간<em class="required">*</em></span>
											</th>
											<td colspan="3">
												<el-date-picker
													v-model="displayPeriodValue"
													type="daterange"
													range-separator="~"
													start-placeholder="시작일"
													end-placeholder="종료일"
													class="size-md">
												</el-date-picker>
											</td>
										</tr>
										<tr>
											<th scope="col">노출 여부
												<em class="required">*</em>
											</th>
											<td colspan="3">
												<el-radio-group v-model="popupInfo.useYn">
													<el-radio label="Y">노출</el-radio>
													<el-radio label="N">비노출</el-radio>
												</el-radio-group>
											</td>
										</tr>
										<tr>
											<th scope="col">팝업 보기 타입
												<em class="required">*</em>
											</th>
											<td colspan="3">
												<el-radio-group v-model="popupInfo.popupViewTypeFlag">
													<el-radio :label="$amConstant.POPUP_TYPE.VIEW.LIST.KEY">리스트 타입</el-radio>
													<el-radio :label="$amConstant.POPUP_TYPE.VIEW.GENERAL.KEY">일반 타입 (사이즈, 위치 지정)</el-radio>
												</el-radio-group>
											</td>
										</tr>
										<tr v-show="$amConstant.POPUP_TYPE.VIEW.GENERAL.KEY === popupInfo.popupViewTypeFlag">
											<th scope="col">
												<div class="flex-item center flex center">
													<span>팝업 사이즈<em class="required">*</em></span>
													<div class="popover-item border ml5" style="line-height: 1;">
														<el-popover :append-to-body="false" placement="top-start" trigger="hover">
															<div class="flex">
																<em class="em-txt">※</em>
																팝업 사이즈를 pc의 화면사이즈보다 크게 지정할 경우, 화면 최대사이즈로 표시됩니다.
															</div>
															<i slot="reference" class="material-icons-outlined primary">error_outline</i>
														</el-popover>
													</div>
												</div>
											</th>
											<td colspan="3">
												<div class="flex-item align-center wd-40">
													<el-input placeholder="가로 (px)" class="size-sm" v-model.number="popupInfo.width"/>
													<span class="hyphen mr5">x</span>
													<el-input placeholder="세로 (px)" class="size-sm" v-model.number="popupInfo.height"/>
												</div>
											</td>
										</tr>
										<tr v-show="$amConstant.POPUP_TYPE.VIEW.GENERAL.KEY === popupInfo.popupViewTypeFlag">
											<th scope="col">
												<div class="flex-item center flex-center">
													<span>팝업 위치<em class="required">*</em></span>
													<div class="popover-item border ml5" style="line-height: 1;">
														<el-popover placement="top-start" trigger="hover" :append-to-body="false">
															<div class="flex">
																<em class="em-txt">※</em>
																화면 왼쪽 상단 기준입니다. px 단위로 작성해주세요.
															</div>
															<i slot="reference" class="material-icons-outlined primary">error_outline</i>
														</el-popover>
													</div>
												</div>
											</th>
											<td colspan="3">
												<div class="flex-item align-center wd-53">
													<el-input placeholder="가로 (px)" class="size-sm" v-model.number="popupInfo.positionX"
													          :disabled="$amConstant.FLAG_YN.YES === popupInfo.centerAlignmentYn"/>
													<span class="hyphen mr5">x</span>
													<el-input placeholder="세로 (px)" class="size-sm" v-model.number="popupInfo.positionY"
													          :disabled="$amConstant.FLAG_YN.YES === popupInfo.centerAlignmentYn"/>
													<div class="custom-checkbox">
														<input type="checkbox" id="checkAll" v-model="popupInfo.centerAlignmentYn"
														       @change="isFixed"
														       :true-value="$amConstant.FLAG_YN.YES"
														       :false-value="$amConstant.FLAG_YN.NO"/>
														<label for="checkAll">
															<i></i>
															<span class="txt csr-pointer">팝업 가운데 고정</span>
														</label>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th scope="col">팝업 콘텐츠 타입
												<em class="required">*</em>
											</th>
											<td colspan="3">
												<el-radio-group v-model="popupInfo.popupContentsTypeFlag">
													<el-radio :label="$amConstant.POPUP_TYPE.CONTENTS.IMAGE">이미지 팝업</el-radio>
													<el-radio :label="$amConstant.POPUP_TYPE.CONTENTS.EDITOR">에디터 팝업</el-radio>
												</el-radio-group>
											</td>
										</tr>
										<tr v-show="$amConstant.POPUP_TYPE.CONTENTS.IMAGE === popupInfo.popupContentsTypeFlag">
											<th scope="col">링크 URL
											</th>
											<td colspan="3">
												<el-input placeholder="URL 입력 시 http://(또는 https://)를 포함한 전체 URL을 입력해주세요"
												          v-model="popupInfo.linkUrl"></el-input>
											</td>
										</tr>
										<tr v-show="$amConstant.POPUP_TYPE.CONTENTS.IMAGE === popupInfo.popupContentsTypeFlag">
											<th scope="col">링크 타입
											</th>
											<td colspan="3">
												<el-radio-group v-model="popupInfo.linkTypeFlag">
													<el-radio :label="$amConstant.POPUP_TYPE.LINK.NEW">새창 띄우기</el-radio>
													<el-radio :label="$amConstant.POPUP_TYPE.LINK.PAGE">페이지 이동</el-radio>
												</el-radio-group>
											</td>
										</tr>
										<tr v-show="$amConstant.POPUP_TYPE.CONTENTS.IMAGE === popupInfo.popupContentsTypeFlag">
											<th scope="col">
												<div class="flex-item center flex-center">
													<span>대표 이미지</span>
													<div class="popover-item border ml5" style="line-height: 1;">
														<el-popover placement="top-start"
														            trigger="hover" :append-to-body="false">
															<div class="flex">
																<em class="em-txt">※</em>
																대표 이미지 한장만 넣어주세요.
															</div>
															<i slot="reference" class="material-icons-outlined primary">error_outline</i>
														</el-popover>
													</div>
												</div>
											</th>
											<td colspan="3">
												<the-drop-zone
													:drop-zone-style="dropZoneStyle"
													ref="dropzoneFile"
													:max-files="1"
													acceptedFiles=".doc,.docx,.xls,.xlsx,.ppt,.pptx,.hwp,.pdf,.txt,.jpeg,.jpg,.png,.gif"
													@setUploadFile="setStorageFileList"
												/>
											</td>
										</tr>
										<tr v-show="$amConstant.POPUP_TYPE.CONTENTS.EDITOR === popupInfo.popupContentsTypeFlag">
											<td colspan="4">
												<div class="editor-area">
													<the-editor-script ref="froalaEditor"></the-editor-script>
												</div>
											</td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
		<!-- 미리보기 모달 -->
		<!-- 팝업 (레이어) -->
		<the-layer-popup type="preview" :popup-info="popupInfo" :visible="layerPopupVisible" v-if="layerPopupVisible"
		                 @close="layerPopupVisible=false;"/>
	</div>
</template>
<script>
import TheEditorScript from "~/components/common/board/TheEditorScript.vue";
import TheDropZone from "~/components/common/dropzone/TheDropZone.vue";
import theLayerPopup from "~/components/common/popup/TheLayerPopup.vue";

export default {
	// layout     : "managerLayout",
	components : {
		TheEditorScript,
		TheDropZone,
		theLayerPopup
	},
	data() {
		return {
			// 표시기간
			displayPeriodValue : [],

			popupInfo : {
				oid                   : "",
				popupContentsTypeFlag : this.$amConstant.POPUP_TYPE.CONTENTS.IMAGE,
				popupViewTypeFlag     : this.$amConstant.POPUP_TYPE.VIEW.LIST.KEY,
				useYn                 : "Y",
				linkTypeFlag          : this.$amConstant.POPUP_TYPE.LINK.NEW,
				centerAlignmentYn     : "N",
				fileList              : [],
			},

			layerPopupVisible : false,               // 미리보기 모달
			// popupZoneVisible : false,               // 미리보기 모달
			dropZoneStyle : {
				fullSize : "large",
				fontSize : 3,
			},
		};
	},
	computed : {
	},
	watch    : {

		"popupInfo.popupContentsTypeFlag"() {

			if ( this.$amConstant.POPUP_TYPE.CONTENTS.IMAGE === this.popupInfo.popupContentsTypeFlag ) {

				this.popupInfo.contents = "";
			}
			else if ( this.$amConstant.POPUP_TYPE.CONTENTS.EDITOR === this.popupInfo.popupContentsTypeFlag ) {

				this.popupInfo.fileList = [];
			}
		},
	},
	fetch() {

		if ( this.$common.isNotEmpty( this.$route.query.popupOid ) ) {
			this.popupInfo.oid = this.$route.query.popupOid;
			this.getPopup();
		}
	},
	methods : {

		// 목록 버튼을 클릭 이벤트
		onClickGoList() {
			this.$common.swalWithOptions( "정보 손실", "현재 작성 중인 정보는 저장되지 않습니다.\n" +
				"목록으로 이동하시겠습니까?", "warning" ).then( confirm => {

				if ( confirm ) {

					this.goList();
				}
				else {
					return false;
				}
			} );
		},
		// 목록 페이지로 이동.
		goList() {

			const urlList = this.$urlConstant.MENU_URL_PREFIX.MANAGER_POPUP_MGNT + this.$urlConstant.MENU_URL_SUFFIX.POPUP_MGNT.POPUP;
			this.$router.push( this.localePath( urlList ) );
		},

		// 드랍존의 파일리스트를 가져옵니다.
		setStorageFileList( data ) {

			this.popupInfo.fileList = data;
		},

		validation() {

			if ( this.$common.isEmpty( this.popupInfo.name ) ) {
				this.$common.confirmSwal( "필수 입력", "제목을 입력해주세요.", "warning" );
				return false;
			}

			if ( this.$common.isEmpty( this.displayPeriodValue ) ) {
				this.$common.confirmSwal( "필수 선택", "노출 기간을 선택해주세요.", "warning" );
				return false;
			}

			if ( this.$amConstant.POPUP_TYPE.VIEW.GENERAL.KEY === this.popupInfo.popupViewTypeFlag ) {

				if ( !this.popupInfo.width ) {
					this.$common.confirmSwal( "필수 입력", "팝업 가로 사이즈를 입력해주세요.", "warning" );
					return false;
				}

				if ( !this.popupInfo.height ) {
					this.$common.confirmSwal( "필수 입력", "팝업 세로 사이즈를 입력해주세요.", "warning" );
					return false;
				}

				// 가운데 정렬이 아닌 경우
				if ( !this.popupInfo.centerAlignmentYn ) {

					if ( !this.popupInfo.positionX ) {
						this.$common.confirmSwal( "필수 입력", "팝업 가로 위치를 입력해주세요.", "warning" );
						return false;
					}

					if ( !this.popupInfo.positionY ) {
						this.$common.confirmSwal( "필수 입력", "팝업 세로 위치를 입력해주세요.", "warning" );
						return false;
					}
				}
			}

			// 팝업 콘텐츠 타입이 본문 팝업이라면..?
			if ( this.$amConstant.POPUP_TYPE.CONTENTS.EDITOR === this.popupInfo.popupContentsTypeFlag ) {

				if ( this.$common.isEmpty( this.$refs.froalaEditor.contents ) ) {
					this.$common.confirmSwal( "필수 입력", "내용을 입력해주세요.", "warning" );
					return false;
				}

				this.popupInfo.contents = this.$refs.froalaEditor.contents;
			}
			return true;
		},

		save() {

			if ( !this.validation() ) {
				return;
			}

			this.popupInfo.startDate = this.$common.formatDateToString( this.displayPeriodValue[ 0 ] );
			this.popupInfo.endDate = this.$common.formatDateToString( this.displayPeriodValue[ 1 ] );
			this.popupInfo.inputUser = this.$store.state.login.loginUser.userOid;

			// 저장 api 호출 후 목록 페이지로 이동
			this.$axios.post( this.$urlConstant.API_URL_PREFIX.POPUP + this.$urlConstant.API_URL_SUFFIX.POPUP.SAVE, this.popupInfo ).then( res => {

				this.goList();
			} );
		},

		getPopup() {

			if ( this.$common.isEmpty( this.popupInfo.oid ) ) {
				return;
			}

			let popupCnd = {
				oid : this.popupInfo.oid,
			};

			let agent = this.$common.checkBrowser();

			this.$axios.post( this.$urlConstant.API_URL_PREFIX.POPUP + this.$urlConstant.API_URL_SUFFIX.POPUP.GET, popupCnd ).then( res => {

				this.popupInfo = res.data;

				this.popupInfo.positionX = 0 > this.popupInfo.positionX ? '' : this.popupInfo.positionX;
				this.popupInfo.positionY = 0 > this.popupInfo.positionY ? '' : this.popupInfo.positionY;

				if ( this.$amConstant.POPUP_TYPE.CONTENTS.EDITOR === this.popupInfo.popupContentsTypeFlag ) {

					this.$refs.froalaEditor.contents = res.data.contents;
				}

				this.$refs.dropzoneFile.setUploadFileList( this.popupInfo.fileList );

				let start = this.popupInfo.startDate.substring( 0, 10 );
				let end = this.popupInfo.endDate.substring( 0, 10 );

				if ( agent === "ie" ) {
					start = this.$common.ieDateConversion( start );
					end = this.$common.ieDateConversion( end );
				}

				this.displayPeriodValue.push( new Date( start ) );
				this.displayPeriodValue.push( new Date( end ) );
			} );
		},

		/* 미리보기 버튼 클릭시 */
		openPreviewModal() {

			// 팝업 콘텐츠 타입이 본문 팝업이라면..?
			if ( this.$amConstant.POPUP_TYPE.CONTENTS.EDITOR === this.popupInfo.popupContentsTypeFlag ) {

				if ( this.$common.isEmpty( this.$refs.froalaEditor.contents ) ) {
					this.$common.confirmSwal( "필수 입력", "내용을 입력해주세요.", "warning" );
					return false;
				}

				this.popupInfo.contents = this.$refs.froalaEditor.contents;
			}

			if ( this.$amConstant.POPUP_TYPE.VIEW.LIST.KEY === this.popupInfo.popupViewTypeFlag ) {

				return this.$common.confirmSwal( "팝업 보기 타입 확인", "팝업 보기 타입이 '일반 타입'일 경우에만 미리보기가 가능합니다.", "warning" );
			}
			else {

				if ( this.$amConstant.POPUP_TYPE.CONTENTS.IMAGE === this.popupInfo.popupContentsTypeFlag ) {

					if ( this.$common.isEmpty( this.popupInfo.fileList ) ) {

						return this.$common.confirmSwal( "필수 삽입", "대표 이미지를 먼저 첨부해주세요.", "warning" );
					}
				}

				this.layerPopupVisible = true;
			}
		},

		isFixed( e ) {
			this.popupInfo.centerAlignmentYn = e.target.checked ? this.$amConstant.FLAG_YN.YES : this.$amConstant.FLAG_YN.NO;
			this.$forceUpdate();
		},
		/**
		 * 팝업 설정 1건을 삭제합니다.
		 */
		deleteOnePopUp() {

			this.$common.swalWithOptions( "삭제 확인", "정말 삭제하시겠습니까?", "warning" ).then( willDelete => {

				if ( !willDelete ) {
					return;
				}

				let param = {
					oid : this.$route.query.popupOid,
				};

				this.deletePost( param );
			} );
		},
		deletePost( param ) {

			this.$axios.post( this.$urlConstant.API_URL_PREFIX.POPUP + this.$urlConstant.API_URL_SUFFIX.POPUP.DELETE, param ).then( res => {

				if ( 1 !== res.data ) {
					this.$common.confirmSwal( "삭제 실패", "삭제에 실패했습니다.", "error" );
					return;
				}
				this.$common.confirmSwal( "삭제 성공!", "성공적으로 삭제 되었습니다.", "success" );
				this.goList();

			} ).catch( error => {
				console.log( error );
			} );
		}
	},
};
</script>
<style></style>
