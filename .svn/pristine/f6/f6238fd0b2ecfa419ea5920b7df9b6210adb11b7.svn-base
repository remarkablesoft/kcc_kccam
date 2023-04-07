<template>
	<div>
		<div class="table-wrap data-sheet" ref="dataSheet">
			<div class="inner-body">
				<!-- table 태그 공통 클래스 : table 스타일 구분 클래스 일반(th 검정) : table-normal
				스타일 구분 클래스 th 회색 : table-gray -->
				<table class="table table-gray" id="resultTable">
					<!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
					<caption>
						<!--						제품 리스트-->
					</caption>
					<!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용, 가로 스크롤 작동하려면
				col의 넓이 단위가 %가 아닌 px이어야 하고 모든 col에 각각 다 줘야 깨지지 않고 잘 나옴. -->
					<colgroup>
						<col style="width: 50px"/>
						<col style="width: 100px"/>
						<col style="width: 125px" v-for="(nameUnit, index) in datasheetNameUnitList" :key="index"/>
					</colgroup>
					<thead>
					<tr>
						<!-- th에만 해당 scope="" col(열) / row(행) -->
						<!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
						<th scope="col" rowspan="2">
							<span></span>
						</th>
						<th scope="col" rowspan="2">
							<span>제품명</span>
						</th>
						<!-- 정렬 클릭 이벤트 th에 걸기 -->
						<th scope="col" v-for="(nameUnit, index) in datasheetNameUnitList" :key="index" class="cursor"
						    @click="sortDataSheet( index )">
							<div class="align-btn-group">
								<span v-text="nameUnit.split('||')[0]"></span>
								<!-- 정렬 표시 -->
								<div class="btn-group">
									<!-- 선택 표시 :: material-icons-round에 active 클래스 추가, 나머지 하나 안보이기 -->
									<span class="material-icons-round"
									      :class="[ setActive(index , 'up') ]"
									      :style="{ visibility: setVisibility( index, 'up' ) }"
									>arrow_drop_up</span>
									<span class="material-icons-round"
									      :class="[ setActive(index , 'down') ]"
									      :style="{ visibility: setVisibility( index, 'down' ) }"
									>arrow_drop_down</span>
								</div>
							</div>
						</th>
					</tr>
					<tr>
						<th scope="col" v-for="(nameUnit, index) in datasheetNameUnitList" :key="index">
							<span v-text="nameUnit.split('||')[1]"></span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr v-for="(product, productIndex) in sortedProductList" :key="productIndex" v-show="product.showYn">
						<td>
							<div class="custom-checkbox">
								<input :id="product.oid + productIndex" v-model="product.inputCheck" type="checkbox"/>
								<label :for="product.oid + productIndex">
									<i></i>
									<span></span>
								</label>
							</div>
						</td>
						<td>
							<nuxt-link
								:to="localePath(
									'/kccam/user/product/product_view?depth=3&partOid=' + product.materialOid +
									'&classificationOid=' + classificationOid + '&classification=' + classification  + '&productOid=' + product.oid
									)"
								class="txt-link-underline"
							>
								{{ product.name }}
							</nuxt-link>
						</td>
						<td v-for="(nameUnit, index) in datasheetNameUnitList" :key="index">
							<span v-text="product[nameUnit]"></span>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 제품 비교하기 버튼 -->
		<div class="btn-wrap bottom right">
			<el-button type="st st-gray" size="medium" @click="excelDownDataSheet('resultTable' )">
				<span class="material-icons">download</span>
				<span class="txt">{{ $t( `datasheet_download` ) }}</span>
			</el-button>
			<el-button type="st st-gray" size="medium" @click="openModal()">
				<span class="material-icons-outlined">fullscreen</span>
				<span class="txt">{{ $t( `datasheet_fullScreen` ) }}</span>
			</el-button>
			<el-button type="st st-gray" size="medium" @click="createNotCheck()" v-show="btnShowYn">
				<span class="material-icons-outlined">format_list_bulleted</span>
				<span class="txt">{{ $t( `datasheet_fullList` ) }}</span>
			</el-button>
			<el-button type="st st-primary" size="medium" @click="removeNotCheck()">
				<span class="material-icons-outlined">assignment_turned_in</span>
				<span class="txt">{{ $t( `datasheet_compareProducts` ) }}</span>
			</el-button>
		</div>
		<!-- data-sheet-modal -->
		<the-data-sheet-modal :btn-show-yn="btnShowYn"
		                      :data-sheet-dialog-visible="dataSheetDialogVisible"
		                      :entire-data-sheet-list="entireDataSheetList"
		                      :checked-data-sheet-list="checkedList"
		                      @close="close"/>
		<!-- 다운로드 모달 :: TheDownloadModal.vue	-->
		<the-download-modal :download-dialog-visible="downloadDialogVisible"
		                    :excel-tab-text="excelTabText"
		                    :product-list="sortedProductList"
		                    @close="downloadDialogVisible = false"
		></the-download-modal>
	</div>
</template>
<script>
import theDataSheetModal from "~/components/kccam/user/modal/TheDataSheetModal.vue";

export default {
	props      : {
		classification    : {
			type     : String,
			required : true,
		},
		classificationOid : {
			type     : String,
			required : true,
		},
		productList       : {
			type     : Array,
			required : true,
			default  : function () {
				return [];
			},
		},
	},
	components : {
		theDataSheetModal,
	},
	data() {
		return {
			datasheetNameUnitList : [],
			checkName             : "checkIndex",

			dataSheetDialogVisible : false,

			// 모달창에 넘길 전체 dataSheetList
			entireDataSheetList : [],
			btnShowYn           : false,

			sortedProductList : [],

			selectedColumnName : '',
			sortFlag           : null,

			//excel datasheet용
			excelTabText          : '',
			downloadDialogVisible : false,
			watchCount            : 0,

			checkedList : [],
		};
	},
	watch : {
		$route() {
			if ( this.$common.isNotEmpty( this.productList ) ) {
				this.sortedProductList = _.cloneDeep( this.productList );
				this.setDatasheetList();
				this.$forceUpdate();
			}
		},
		async productList() {

			if ( this.$common.isNotEmpty( this.productList ) ) {
				this.sortedProductList = await _.cloneDeep( this.productList );
				await this.setDatasheetList();
				this.$forceUpdate();
			}
		},
		sortedProductList : {
			deep:true,
			handler() {

			}
		}
	},
	async fetch() {
		this.sortedProductList = await _.cloneDeep( this.productList );
		await this.setDatasheetList();
	},

	methods : {
		/**
		 * 제품 데이터시트 리스트를 만들어줍니다.
		 * @param productInfo
		 */
		async setDatasheetList() {
			const _self = this;

			if ( _self.$common.isEmpty( _self.sortedProductList ) || _self.sortedProductList.length === 0 ) {
				return;
			}

			await _.each( _self.sortedProductList, async function ( product ) {
				if ( _self.$common.isEmpty( product.datasheetInfo ) || _self.$common.isEmpty( product.datasheetInfo.datasheetItemList ) ) {
					product.datasheetInfo = {
						datasheetItemList : []
					}
				}
				product.inputCheck = false;
				// productTr 껏다 키기
				product.showYn = true;

				await _.each( product.datasheetInfo.datasheetItemList, async function ( datasheetItem ) {
					// 이름에 특징을지어 줍니다
					let datasheetUnitName = datasheetItem.name + "||" + datasheetItem.itemUnit;
					// datasheetNameUnitList가 이미 가지고 있는지 확인합니다
					if ( !_self.datasheetNameUnitList.includes( datasheetUnitName ) ) {
						// 여기서 이름을 넣어줍니다
						await _self.datasheetNameUnitList.push( datasheetUnitName );
					}
				} );
			} );

			await _.each( _self.sortedProductList, async function ( product ) {
				_.each( _self.datasheetNameUnitList, function ( nameUnit ) {
					// 기본적으로 - 를 설정해주고
					product[ nameUnit ] = "-";
				} );

				if ( _self.$common.isEmpty( product.datasheetInfo ) || _self.$common.isEmpty( product.datasheetInfo.datasheetItemList ) ) {
					return;
				}

				await _.each( product.datasheetInfo.datasheetItemList, async function ( datasheetItem ) {
					// 여기서도 특징을 지어 줍니다
					let datasheetUnitName = datasheetItem.name + "||" + datasheetItem.itemUnit;
					// 값을 넣어줍니다
					product[ datasheetUnitName ] = await datasheetItem.itemValue;
				} );
			} );
			await this.sendDataList();
		},

		// 모달창에 넘길 dataList덩어리 입니다
		async sendDataList() {

				this.entireDataSheetList = []
				await this.entireDataSheetList.push( this.datasheetNameUnitList, this.classificationOid, this.sortedProductList );

		},

		/**
		 *  선택한부분을 제외한 tr을 지워줍니다
		 */
		removeNotCheck() {
			const _self = this;

			// 선택된 check가 1개이하일경우 swalAlert를 띄워줍니다
			let result = 0;
			_.each( _self.sortedProductList, function ( product ) {
				if ( product.inputCheck ) {
					result += 1;
				}
			} );
			_.each( _self.sortedProductList, function ( product ) {
				if ( 2 > result ) {
					_self.$common.swalWithOptions(
						_self.$t( `user_datasheetModal_swalWithOptions_tit` ),
						_self.$t( `user_datasheetModal_swalWithOptions_contents` ),
						"warning"
					);
					return;
				}

				if ( !product.inputCheck ) {
					product.showYn = false;
					_self.btnShowYn = true;
				}
			} );
		},

		/**
		 *  제외된 tr을 다시 그려줍니다
		 */
		createNotCheck() {
			const _self = this;

			_.each( _self.sortedProductList, function ( product ) {
				if ( !product.inputCheck ) {
					product.showYn = true;
					_self.btnShowYn = false;
				}
			} );
		},

		close( boolean ) {
			this.dataSheetDialogVisible = false;
			// this.btnShowYn = boolean;
		},

		excelDownDataSheet( id ) {
			let tab_text = '<html xmlns:x="urn:schemas-microsoft-com:office:excel">';
			tab_text = tab_text + '<head><meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
			tab_text = tab_text + '<xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>'
			tab_text = tab_text + '<x:Name>Sheet</x:Name>';
			tab_text = tab_text + '<x:WorksheetOptions><x:Panes></x:Panes></x:WorksheetOptions></x:ExcelWorksheet>';
			tab_text = tab_text + '</x:ExcelWorksheets></x:ExcelWorkbook></xml></head><body>';
			tab_text = tab_text + "<table border='1px'>";
			let exportTable = $( '#' + id ).clone();
			exportTable.find( 'input' ).each( function ( index, elem ) {
				$( elem ).remove();
			} );
			tab_text = tab_text + exportTable.html();
			tab_text = tab_text + '</table></body></html>';

			this.excelTabText = tab_text;
			this.downloadDialogVisible = true;
		},

		sortDataSheet( columnIndex ) {

			let key = this.datasheetNameUnitList[ columnIndex ];

			if ( this.sortFlag ) {
				this.sortedProductList = _.sortBy( this.sortedProductList, key );
				this.sortFlag = false;
			}
			else {
				this.sortedProductList = _.sortBy( this.sortedProductList, key ).reverse();
				this.sortFlag = true;
			}

			this.selectedColumnName = key;

		},

		setActive( columnIndex, upOrDown ) {

			let key = this.datasheetNameUnitList[ columnIndex ];

			if ( key !== this.selectedColumnName ) {
				return '';
			}

			//sortFlag = true : 내림차순 상태 / sortFlag = false : 오름차순 상태
			if ( 'up' === upOrDown && this.sortFlag ) {
				if ( key === this.selectedColumnName ) {
					return 'active';
				}
				else {
					return '';
				}

			}
			else if ( 'down' === upOrDown && !this.sortFlag ) {
				if ( key === this.selectedColumnName ) {
					return 'active';
				}
				else {
					return '';
				}
			}

		},

		setVisibility( columnIndex, upOrDown ) {

			let key = this.datasheetNameUnitList[ columnIndex ];

			if ( key !== this.selectedColumnName ) {
				return 'visible';
			}

			if ( 'up' === upOrDown && !this.sortFlag ) {
				return 'hidden';
			}
			else if ( 'down' === upOrDown && this.sortFlag ) {
				return 'hidden';
			}

		},
		openModal() {
			this.checkedList = this.sortedProductList.filter( product => product.inputCheck );
			this.dataSheetDialogVisible = true;
		}


	},
};
</script>
