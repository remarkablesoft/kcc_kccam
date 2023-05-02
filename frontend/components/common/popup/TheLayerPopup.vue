<template>
	<!-- overlay-container :: 레이어 팝업 -->
	<div :class="[layerPopupVisible ? 'visible' : '', pcimValue ? 'is-pcim' : '']" class="popup-container layer-popup" v-if="layerPopupVisible">
		<div class="inner-wrap">
			<!-- 컨텐츠 -->
			<div class="popup-content" v-for="item in popupList" :key="item.oid" :style="setPopupPosition(item)">
				<div class="inner-content" v-if="item.visible">
					<div class="content-body" >
						<!-- 여백이나 스크롤 이런것 때문에 두 분류로 구분 -->
						<!-- 이미지만 있을 경우 :: img-only 클래스 붙음 -->
						<div class="contents-box img-only" v-if="$common.isNotEmpty( item.fileList )">
							<!-- 에디터에서 입력한 내용은 아래 contents에. -->
							<div class="contents">
								<!-- <img
									src="@/assets/lms/images/common/sample/sample_01.jpeg"
									alt="팝업 이미지"
								/> -->
								<a @click="setImgUrl(item)" :style="setPopupSize(item)">
									<img alt="alert" :src="getImage(item)"/>
								</a>
							</div>
						</div>
						<!-- 이미지 + 글이나 글만 있을 경우 :: scroll 만드는 엘리먼트 있음. -->
						<!-- 2021.06.22 froala editor 컨텐츠는 출력 안함 -->
						<div class="contents-box" v-if="$common.isNotEmpty( item.contents ) " :style="setPopupSize(item)">
							<!-- scroll area -->
							<div class="scroll-element" v-bar="{ preventParentScroll: true }">
								<!-- el1 -->
								<div>
									<!-- 에디터에서 입력한 내용은 아래 contents에. -->
									<div class="contents">
										<!-- <img
											src="@/assets/lms/images/common/sample/sample_03.jpg"
											alt="팝업 이미지"
										/> -->
										<!-- 공지내용입니다. -->
										<!-- 시스템 점검 임시 -->
										<div class="tc">
											<!-- <i class="material-icons-round" style="font-size: 10rem;">
												construction
											</i> -->
											<p v-html="item.contents"></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="content-footer">
                        <div class="btn-row">
							<button class="btn-close-round" @click="hideToday(item)" round v-if="type !== 'preview'">
								<span class="txt">Don't open it today</span>
								<i class="material-icons">close</i>
							</button>
							<button class="btn-close-round" @click="close(item)" round>
								<span class="txt">close</span>
								<i class="material-icons">close</i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 모바일 버전일 때만 보이는 footer입니다 -->
<!--        v-if="type !== 'preview' || $common.isMobile()"-->
		<div class="container-footer mob-visible" v-if="false">
			<div class="btn-row">
				<button class="btn-close-round" round @click="hideAllToday()">
					<span class="txt">전체 팝업 하루동안 열지 않기</span>
					<i class="material-icons">close</i>
				</button>
				<button class="btn-close-round" round
				        @click="()=> popupList.forEach( popup => close( popup ) )">
					<span class="txt">전체 팝업 닫기</span>
					<i class="material-icons">close</i>
				</button>
			</div>
		</div>
		<!-- 배경 :: 클릭 시 닫히는 이벤트 동작 -->
		<!--        <div class="close-backdrop" @click="layerPopupVisible=false"></div>-->
	</div>
</template>
<script>
import Cookies from "js-cookie";

export default {
	props : {
		popupInfo : {
			type : Object,
			default() {
				return {}
			}
		},
		type      : {
			type    : String,
			default : "",
		},
	},
	data() {
		return {
			layerPopupVisible : false,
			popupList         : [],
            pcimValue         : false,
		};
	},
	watch : {
		// 모달 열릴 때 스크롤 막기 by lje
		layerPopupVisible() {
			this.setScrollOverflow();
		},
	},
	mounted() {
		this.setScrollOverflow();
	},
	async fetch() {

		// 관리자 등록, 수정화면에서 미리보기
		if ( this.$common.isNotEmpty( this.popupInfo ) ) {

			this.popupInfo.visible = true;
			this.popupList.push( this.popupInfo );
		}
		else {  // 사용자 메인 전체 활성화된 팝업 리스트 호출

			await this.listPopup();
		}
	},

	methods : {
		// 닫기
		close( item ) {

			item.visible = false;

			let visibleList = this.popupList.filter( popup => popup.visible );

			if ( this.$common.isEmpty( visibleList ) ) {
				this.layerPopupVisible = false;
			}

			if ( "preview" === this.type ) {

				this.$emit( "close" );
				this.$forceUpdate();
			}

			this.$forceUpdate();
		},

		hideAllToday() {
			this.popupList.forEach( popup => this.hideToday( popup ) );
		},

		// 오늘 하루 열지 않기
		hideToday( item ) {

			Cookies.set( this.$amConstant.POPUP_HIDE_TODAY + item.oid, true, { expires : 1 } );
			this.close( item );
		},

		checkCookie( item ) {
			if ( Cookies.get( this.$amConstant.POPUP_HIDE_TODAY + item.oid ) ) {
				this.close( item );
			}
		},

		async listPopup() {

			let param = {
				partOid : this.partOid,
				popupViewTypeFlag : this.$amConstant.POPUP_TYPE.VIEW.GENERAL.KEY,
			};

			await this.$axios.post( this.$urlConstant.API_URL_PREFIX.POPUP + this.$urlConstant.API_URL_SUFFIX.POPUP.OPERATION_LIST, param ).then( res => {

				if ( this.$common.isEmpty( res.data ) ) {
					return;
				}

				this.layerPopupVisible = true;

				this.popupList = res.data;

				this.popupList.forEach( popup => {

					popup.visible = true;
					this.checkCookie( popup );
					this.checkOverSize( popup );
				} );
			} );
		},

		getImage( item ) {
			if ( this.$common.isEmpty( item ) || this.$common.isEmpty( item.fileList ) ) {

				// return '@/assets/lms/images/common/notFound/default_ebook.png'
				return;
			}
			let url = "";

			return url + this.$common.getThumbnailPath( item.fileList[ 0 ].storageFileUid, item.width + "_" + item.height );
		},

		setPopupPosition( item ) {
			const { positionY, positionX, centerAlignmentYn } = item;
			if ( this.$common.isEmpty( centerAlignmentYn ) || this.$amConstant.FLAG_YN.NO === centerAlignmentYn ) {
				return `top: ${ positionY }px; left: ${ positionX }px;`;
			}
			else {
				return `top: 50%; left: 50%; transform : translate(-50% , -50%)`;
			}
		},

		setPopupSize( item ) {
            let { width } = item;

            if ( item.contents.includes("pcim") ) {
                this.pcimValue = item.contents.includes("pcim");
            }

            return `width : ${ width }px;  height : auto;`;
		},

		/**
		 *  팝업사이즈가 화면사이즈보다 큰경우를 감지해서 사이즈를 화면보다는 작게 재설정해줍니다.
		 */
		checkOverSize( item ) {
			let { width, height, positionY, positionX } = item;
			// 설정할 값
			let calcHeight = 300;
			let calcWidth = 140;

			const currentWidth = width + positionX;
			const currentHeight = height + positionY;

			let screenWidth = window.outerWidth;
			let screenHeight = window.outerHeight;
			let deadlineWidth = screenWidth - ( calcWidth / 2 );

			if ( this.$common.isMobile() ) {
				// screenHeight = window.outerHeight;
				calcHeight = 200;
			}

			if ( currentWidth >= deadlineWidth ) {
				item.width = ( screenWidth - calcWidth );
				item.positionX = 50;
			}

			if ( currentHeight >= screenHeight ) {
				item.height = ( screenHeight - calcHeight );
				item.positionY = 50;
			}
		},

		/* 링크 타입이 새창인지, 페이지 이동인지 구분하여 이동 */
		setImgUrl( item ) {

			if ( this.$common.isEmpty( item ) || this.$common.isEmpty( item.linkUrl ) ) {
				return;
			}

			// 페이지 이동
			if ( this.$amConstant.POPUP_LINK_TYPE.PAGE_MOVE === item.linkTypeFlag ) {

				window.location.href = item.linkUrl;
			}
			else if ( this.$amConstant.POPUP_LINK_TYPE.NEW === item.linkTypeFlag ) {  // 새창

				window.open( item.linkUrl );
			}
		},

		/**
		 *  모달열릴때 스크롤 설정
		 */
		setScrollOverflow() {
			if ( this.layerPopupVisible ) {
				if ( this.$common.isMobile() ) {
					document.querySelector( 'html' ).style.overflow = "hidden";
					return;
				}
				return;
			}
			else {
				if ( this.$common.isMobile() ) {
					document.querySelector( 'html' ).style.overflow = "";
					return;
				}
				else {
					document.querySelector( 'html' ).style.overflow = "";
					return;
				}
			}
		},
	},
};
</script>
<style></style>
