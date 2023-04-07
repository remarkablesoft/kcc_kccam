<template>
		<div class="custom-popup-container wide-size">
			<div class="popup-header">
				<!-- 제목 -->
				<p class="tit">Newsroom 미리보기</p>
				<!-- 닫기 버튼 -->
				<el-button type="icon-only white" class="btn-close">
					<span class="icon material-icons" @click="close">clear</span>
				</el-button>
			</div>
			<div class="popup-body">
				<div class="content content-preview">
					<div class="content-header">
						<div class="tit-area">
							<span class="tit" v-text="$common.isEmpty( newsInfo.title )? '-' : newsInfo.title">뉴스제목</span>
						</div>
						<div class="sub-info-area">
							<div class="info-item">
								<span class="item-label" v-text="setProductTitLang()">관련제품</span>
								<span class="data" v-text="setProductText( newsInfo )">관련 제품</span>
							</div>
							<div class="sub-info-group">
								<div class="info-item">
									<span class="item-label" v-text="setInputDateTitLang()">작성일</span>
									<span class="data" v-text="setInputText( newsInfo )"></span>
								</div>
								<div class="info-item">
									<span class="item-label item-label-small" v-text="setViewHitTitLang()">조회수</span>
									<span class="data" v-text="$common.isEmpty( newsInfo.viewCnt )? 0 : newsInfo.viewCnt"></span>
								</div>
							</div>
						</div>

					</div>
					<div class="content-body">

						<!-- scroll area 1-->
						<div v-bar="{ preventParentScroll: true }" style="height: 40rem;" class="scroll-element">
							<!-- el1 -->
							<div>
								<div class="common-pre-wrap content-body-preview">
									<span class="data" v-html="newsInfo.newsroomContents"> </span>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

			<div class="popup-footer flex-end" >
				<!-- 닫기 버튼 -->
				<el-button type="txt-only primary" class="btn-close" @click="close">
					닫기
				</el-button>
			</div>

		</div>


</template>
<script>
import cookie from "js-cookie";

export default {
	props : {
		previewPopupVisible : {
			type     : Boolean,
			required : true,
		},

		newsInfo : {
			type :  Object,
			required : true,
		}
	},

	data() {
		return {
			popupShowForAWeek : false
		};
	},
	created() {
	},
	mounted() {
	},
	watch   : {
		// mainPopupVisible: function() {
		//     this.visible = this.mainPopupVisible;
		// },
	},
	methods : {
		close() {
			// console.log( this.newsInfo );
			this.$emit( "close" );
		},

		setInputText( newsInfo ){
			if( this.$common.isNotEmpty( newsInfo.inputDate) ){
				return this.$common.formatDate( newsInfo.inputDate );
			}
			else{
				let today = new Date();
				return today.getFullYear() + '-' + (today.getMonth()+1) + '-' + today.getDate();
			}
		},

		setProductText( newsInfo ){
			if( this.$common.isEmpty( newsInfo ) ){
				return '-';
			}
			if ( this.$common.isEmpty( newsInfo.productList ) ){
				return '-';
			}

			let text ='';
			_.forEach( newsInfo.productList,  product => {
				text += product.name + ', ';
			});

			return text.substring( 0 , text.length - 2 );
		},

		setProductTitLang(){
			// console.log( this.newsInfo.lang );
			switch ( this.newsInfo.lang ) {
				case 'EN' : return 'Related Product';
				case 'CN' : return '相關產品';
				default   : return '관련제품';
			}
		},

		setInputDateTitLang(){
			switch ( this.newsInfo.lang ) {
				case 'EN' : return 'Registration Date';
				case 'CN' : return '登記日';
				default   : return '작성일';
			}
		},

		setViewHitTitLang(){
			switch ( this.newsInfo.lang ) {
				case 'EN' : return 'Views';
				case 'CN' : return '查詢數';
				default   : return '조회수';
			}
		},
	},
};
</script>
<style></style>
