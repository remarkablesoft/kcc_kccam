<template>
	<div>
		<!-- 메인 팝업 -->
		<div class="custom-popup-container" v-if="mainPopupVisible">
			<div class="popup-header">
				<!-- 제목 -->
				<p class="tit">{{ $t(`user_main_popup_header`) }}</p>
				<!-- 닫기 버튼 -->
				<el-button type="icon-only white" class="btn-close" @click="close">
					<span class="icon material-icons">clear</span>
				</el-button>
			</div>
			<div class="popup-body">
				<div class="content">
					<!-- 대표 이미지 :: 없을 경우 표시 안함 -->
					<img src="@/assets/images/contents/market/contents_market_automotive.png" alt="마켓 관련 이미지"/>
					<!-- 팝업 내용 -->
					<p class="txt">
                        As a type of anaerobic adhesive, retaining compounds are essential for the precision assembly of press- and slip-fitted parts, as well as any component
                        that is used within systems subject to high vibration in challenging environmental conditions. Capable of filling spaces between individual components,
                        they cure to form a strong bond and allow for the transmission of high loads without the need for air. This means sealed components and machine designs
                        can still achieve high strength and temperature resistance across a variety of substrates.
                    </p>
				</div>
			</div>
			<div class="popup-footer">
				<div class="custom-checkbox circle-gray">
					<input id="checkbox" type="checkbox" v-model='popupShowForAWeek'/>
					<label for="checkbox">
						<i></i>
						<span>{{ $t(`user_main_popup_footer`) }}</span>
					</label>
				</div>
				<!-- 닫기 버튼 -->
				<el-button type="txt-only primary" class="btn-close" @click="close">
					{{ $t(`user_main_popup_close`) }}
				</el-button>
			</div>
		</div>
	</div>
</template>
<script>
import cookie from "js-cookie";

export default {
	props : {
		mainPopupVisible : {
			type     : Boolean,
			required : true,
		},
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
			//일주일간 보지 않기 하면 쿠키 세팅
			if ( this.popupShowForAWeek ) {
				cookie.set( "KAMMPSY", "Y", { expires : 6 } )
			}
			this.$emit( "close" );
		},
	},
};
</script>
<style></style>
