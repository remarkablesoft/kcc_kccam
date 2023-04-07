<template>
	<div>
		<!-- 문서 다운/공유 이력 정보 모달 -->
		<el-dialog title="문서 다운/공유 이력 정보" :visible.sync="visibleFlag" width="90rem" >
			<div class="inner-content-group" v-if="$common.isNotEmpty( auditInfo )">
				<div class="search-area">
					<div class="input-row">
						<div class="label">
							<span class="input-tit">IP</span>
						</div>
						<div class="data">
							<span v-text="$common.isEmpty( auditInfo.userIp )? '-' : auditInfo.userIp"></span>
						</div>
					</div>
					<div class="input-row">
						<div class="label">
							<span class="input-tit">Email</span>
						</div>
						<div class="data">
							<span v-text="auditInfo.email"></span>
						</div>
					</div>
					<div class="input-row">
						<div class="label">
							<span class="input-tit">공유/다운타입</span>
						</div>
						<div class="data">
							<span v-text="getShareType( auditInfo )"></span>
						</div>
					</div>
					<div class="input-row">
						<div class="label">
							<span class="input-tit">접근Url</span>
						</div>
						<div class="data" v-if="$common.isNotEmpty(auditInfo.accessUrl)">
							{{ auditInfo.accessUrl}}
							<nuxt-link :to="localePath(auditInfo.accessUrl)" class="accessUrl">[해당 페이지로 이동]</nuxt-link>
						</div>
						<div class="data" v-else>-</div>
					</div>
					<div class="input-row">
						<div class="label" style="background-color: #f7f7f7;">
							<span class="input-tit">대상</span>
						</div>
						<div class="data">
							<span v-text="getTarget( auditInfo )"></span>
						</div>
					</div>
					<div class="input-row">
						<div class="label">
							<span class="input-tit">해당 화면</span>
						</div>
						<div class="data">
							<span v-text="getBreadCrumbTree( auditInfo.accessUrl )"></span>
						</div>
					</div>
					<div class="input-row">
						<div class="label">
							<span class="input-tit">대상명</span>
						</div>
						<div class="data">
							<span v-text="getTargetNameText( auditInfo )"></span>
						</div>
					</div>
				</div>
			</div>
			<div slot="footer" class="dialog-footer">
				<el-button size="default" @click="close">닫기</el-button>
			</div>
		</el-dialog>
	</div>
</template>
<script>
export default {
	props : {
		infoVisible : {
			type     : Boolean,
			required : true,
		},
		auditInfo : {
			type    : Object,
			required : false,
		},
	},

	data() {
		return {
			// test : this.infoVisible,
		};
	},
	created() {
	},
	mounted() {
		// console.log( this.test )
	},
	computed : {
		visibleFlag : {
			get() {
				return this.infoVisible;
			},
			set( newValue ) {
				this.$emit('visibleSetting', newValue );
				// console.log( newValue );
			}
		}
	},
	watch   : {

	},
	methods : {
		close() {
			this.$emit( "close" );
		},
		getShareType( auditInfo ){
			if( this.$common.isEmpty( auditInfo ) || this.$common.isEmpty( auditInfo.shareType ) ){
				return '-';
			}
			switch ( auditInfo.shareType ){
				case this.$amConstant.DOC_SHARE_TYPE.SHARE_TYPE_SHARE   : return '메일공유'; break;
				case this.$amConstant.DOC_SHARE_TYPE.SHARE_TYPE_DOWNLOAD : return '일반다운'; break;
				case this.$amConstant.DOC_SHARE_TYPE.SHARE_TYPE_EXCEL_DATASHEET : return '데이터시트 엑셀다운'; break;
				default: return '-';
			}
		},
		getTarget( auditInfo ){
			if( this.$common.isEmpty( auditInfo ) || this.$common.isEmpty( auditInfo.shareType ) ){
				return '-';
			}

			switch ( auditInfo.shareType ){
				case this.$amConstant.DOC_SHARE_TYPE.SHARE_TYPE_SHARE   : return '문서'; break;
				case this.$amConstant.DOC_SHARE_TYPE.SHARE_TYPE_DOWNLOAD : return '문서'; break;
				case this.$amConstant.DOC_SHARE_TYPE.SHARE_TYPE_EXCEL_DATASHEET : return '제품'; break;
				default: return '-';
			}
		},

		getTargetNameText( auditInfo ){
			if( this.$common.isEmpty( auditInfo ) || this.$common.isEmpty( auditInfo.shareType ) || this.$common.isEmpty( auditInfo.targetOidList )){
				return '-';
			}

			if( this.$common.isEmpty( auditInfo.targetInfoMap ) ){
				return '-';
			}

			//문서 다운/공유 일경우 targetOidList는 문서 oid 한 개만 담겨있습니다.
			if( auditInfo.shareType === this.$amConstant.DOC_SHARE_TYPE.SHARE_TYPE_SHARE || auditInfo.shareType === this.$amConstant.DOC_SHARE_TYPE.SHARE_TYPE_DOWNLOAD){
				let docInfo = auditInfo.targetInfoMap[ auditInfo.shareType ];
				// console.log(auditInfo.targetInfoMap[ auditInfo.shareType ]);
				return docInfo.title;
			}

			//제품 데이터시트 다운일 경우 targetOidList는 데이터시트를 구성하는 productOid 로 구성되어있습니다.
			let productList = auditInfo.targetInfoMap[ auditInfo.shareType ];

			if( this.$common.isEmpty( productList ) ){
				return '-';
			}

			let str = '';
			_.forEach( productList, ( product, index) => {
				if( index != 0 && index<= productList.length-1 ){
					str += ', ';
				}
				str += product.name;
				// console.log( index, str );
			} )

			return str;
		},
		getBreadCrumbTree( auditUrl ) {
			if( this.$common.isEmpty( auditUrl ) ) {
				return '-';
			}
			let urlPrefixObj = this.$urlConstant.MENU_URL_PREFIX;
			let urlSuffixObj = this.$urlConstant.MENU_URL_SUFFIX;
			let str = '';
			const _self = this;

			// 1뎁스 만들기 ( prefix )
			for( let preFixKey in urlPrefixObj ) {
				if ( auditUrl.indexOf( urlPrefixObj[ preFixKey ]) !=-1 ) {
					let matchKey = preFixKey.split('USER_')[1];
					str += _self.$amConstant.MENU_NAME[matchKey].TITLE;
					break;
				}
			}
			// 2뎁스 만들기 ( suffix )
			for( let suffixKey in urlSuffixObj ) {
				let deepObj = urlSuffixObj[suffixKey];
				for( let deepKey in deepObj ) {
					let deepValue = deepObj[deepKey];
					if ( typeof( deepValue ) == 'string' ) {
						let compareStr = deepValue.split('?').length > 1 ? deepValue.split('?')[0]
							: deepValue.split('?')[0];

						if ( url.indexOf( compareStr ) != -1 ) {
							if ( _self.$common.isEmpty(_self.$amConstant.MENU_NAME[suffixKey] ) ) {
								break;
							}
							if( typeof _self.$amConstant.MENU_NAME[suffixKey][deepKey] != 'undefined') {
								str += ' > ';
								str += _self.$amConstant.MENU_NAME[suffixKey][deepKey];
							}
						}
					}
					else if ( typeof( deepValue ) == 'object' ) {
						if ( url.indexOf( "newsroom" ) != -1 ) {
							str += ' > ';
							str += "뉴스룸";
						}

					}
				}
			}
			return str;
		},

	},
};
</script>
<style scoped>
.search-area {
	background: none;
}
.manager-container .search-area .input-row .label {
	min-width : 13rem;
	background-color : #f7f7f7;
}
.input-tit {
	width:100%;
}
.manager-container .search-area .input-row .input-tit {
	width: 100%;
	text-align: center;
	margin-right: 0;
}
.manager-container .search-area .input-row .data {
	padding-left: 2rem;
	display: block;
}
.accessUrl {
	color : #5551f7;
}
.accessUrl:hover {
	color: #0400ff;
	font-weight : bold;
}
</style>
