<template>
	<div class="inner-wrapper">
		<!-- loading -->
<!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
<!--            <the-loading />-->
<!--        </div>-->

		<!-- manager-content-body -->
		<div class="manager-content-body">
			<div class="content-title">
				<div class="sub-title">
					<h2>문서 다운/공유 이력</h2>
				</div>
				<!--                <div class="btn">-->
				<!--                    <el-button type="st st-primary" size="small" @click="docTypeSettingModalOpen()">문서타입설정</el-button>-->
				<!--                </div>-->
			</div>
			<div class="content-detail">
				<!-- table -->
				<div class="manager-table-normal">
					<table>
						<colgroup>
							<col style="width:5%" />
							<col style="width:20%" />
							<col style="width:15%" />
							<col style="width:10%" />
							<col style="width:20%" />
							<col style="width:8%" />
							<col style="width:15%" />
						</colgroup>
						<thead>
						<tr class="bg-lgray">
							<th><span>번호</span></th>
							<th><span>EMail</span></th>
							<th><span>공유/다운타입</span></th>
							<th><span>대상타입</span></th>
							<th><span>대상명</span></th>
							<th><span>접근IP</span></th>
							<th><span>일시</span></th>
						</tr>
						</thead>
						<tbody>
						<!-- no-data :: tr -->
						<tr v-if="$common.isEmpty( auditList )">
							<td colspan="7">
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
						<tr v-for="(item, i) in auditList" :key="i" class="list-item" @click="openModal(item)" >
							<td>
								<span v-text="item.no"></span>
							</td>
							<td>
								<span v-text="item.email"></span>
							</td>
							<td>
								<span v-text="getShareType( item )"></span>
							</td>
							<td>
								<span v-text="getTarget( item )"></span>
							</td>
							<td>
								<span v-text="getTargetNameText( item )"></span>
							</td>
							<td>
								<span v-text="$common.isEmpty( item.userIp )? '-' : item.userIp"></span>
							</td>
							<td>
								<span v-text="item.inputDate"></span>
<!--								<span v-text="$common.formatDate( item.inputDate )"></span>-->
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				<!-- 페이지네이션 -->
				<the-pagination
					v-if=" 0 < listCount "
					:visible-buttons-count="10"
					:total-count="listCount"
					:page-size="pageSize"
					:current-page="currentPage"
				/>
			</div>
		</div>
		<the-doc-share-audit-info-modal
			:info-visible="modalVisible"
			:audit-info="clickedAuditInfo"
			@close="close"
			@visibleSetting="close"
		/>
	</div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theDocShareAuditInfoModal from "~/components/kccam/manager/modal/TheDocShareAuditInfoModal.vue";

export default {
	layout: "managerLayout",
	components: {
		theLoading,
		thePagination,
	},
	data() {
		return {

			auditList:[],

			auditListLoading: false,
			listCount: 0,
			pageSize: 10,
			currentPage: 1,

			//모달창
			modalVisible : false,
			clickedAuditInfo : {},

		};
	},
	async fetch() {
		this.auditListLoading = true;
		this.auditList = [];
		await this.getAuditList();
		this.auditListLoading = false;
	},
	watch: {
		$route() {
			this.modalVisible = false;
			this.$fetch();
		},
	},
	computed : {
	},
	methods: {

		async getAuditList(){
			const urlList = this.$urlConstant.API_URL_PREFIX.DOC_SHARE_AUDIT + this.$urlConstant.API_URL_SUFFIX.DOC_SHARE_AUDIT.LIST;

			let startIndex = 1;

			if( this.$common.isNotEmpty( this.$route.query.page ) ){
				startIndex = this.getStartIndexFromPage( this.$route.query.page );
				this.currentPage = Number( this.$route.query.page );
			}

			let cnd = {
				startIndex : startIndex,
				pageSize : this.pageSize,
			};

			await this.$axios.post( urlList, cnd ).then( res => {
				if( res.data.totalCount == 0 ){
					this.auditList = [];
					this.listCount = 0;
					return;
				}

				this.auditList = res.data.list;
				this.listCount = res.data.totalCount;

				res.data.list.forEach( ( item, index ) => {
					item.no = this.$common.settingNo( this.listCount, startIndex, index );
				});

			} );
		},

		/**
		 * 페이지 번호로 StartIndex를 가져옵니다.
		 */
		getStartIndexFromPage(page) {
			let startIndex = (page - 1) * this.pageSize + 1;
			return startIndex;
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
				return docInfo.title;
			}

			//제품 데이터시트 다운일 경우 targetOidList는 데이터시트를 구성하는 productOid 로 구성되어있습니다.
			let productList = auditInfo.targetInfoMap[ auditInfo.shareType ];

			if( this.$common.isEmpty( productList ) ){
				return '-';
			}

			let str = productList[0].name;
			if ( productList.length > 1 ) {
				str += ' 외 '+ ( productList.length - 1 ) + '개';
			}
			return str;
		},

		//정보 모달창 닫기
		close(){
			this.modalVisible = false;
			// this.clickedAuditInfo = {};
		},

		openModal( auditInfo ){
			this.clickedAuditInfo = auditInfo;
			this.modalVisible = true;
		}

	},
};
</script>

<style scoped></style>
