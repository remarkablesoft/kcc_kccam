<template>
	<!-- Contact Office 추가 모달 -->
	<el-dialog
		title="Contact Office 검색"
		:visible.sync="dialogVisible"
		width="45rem"
	>
		<div class="inner-content">
			<!--search area -->
			<div class="search-area">
				<div class="input-row">
					<div class="label">
						<span>지역</span>
					</div>
					<div class="data">
						<el-select
							size="medium"
							v-model="selectedCountry"
							placeholder="선택"
                            @change="getContactOfficeList"
						>
							<el-option
								v-for="(val, key) in countrySearchOptions"
								:key="key"
								:label="val"
								:value="key"
							>
							</el-option>
						</el-select>
					</div>
				</div>
			</div>

			<!-- list area -->
			<div class="list-area">
				<div class="manager-table-normal">
					<div class="table-body">
						<table>
							<colgroup>
								<col style="width:20%" />
								<col style="width:80%" />
							</colgroup>
							<thead>
								<tr class="bg-lgray">
									<th><span>선택</span></th>
									<th><span>Contact Office 명</span></th>
								</tr>
							</thead>

							<tbody>
								<!-- no-data :: tr -->
								<tr v-if="false">
									<td colspan="2">
										<!-- no-data(loading) -->
										<div class="no-data">
											<div class="loading-sm">
												<img
													src="@/assets/images/loading/loading_sm.svg"
													alt="Loading"
												/>
											</div>
											<p>데이터 로딩중입니다.</p>
										</div>
										<!-- no-data -->
										<div class="no-data" v-show="false">
											<i class="material-icons"
												>error_outline</i
											>
											<p>데이터가 없습니다.</p>
										</div>
									</td>
								</tr>
								<tr
									v-for="(item, i) in contactOfficeList"
									:key="i"
									class="list-item"
								>
									<td>
										<div class="custom-checkbox">
											<input
												:id="item.oid"
												type="checkbox"
												name="listCheckboxGroup"
                                                v-model="item.isChecked"
											/>
											<label :for="item.oid">
												<i></i>
												<!-- <span>텍스트</span> -->
											</label>
										</div>
									</td>
									<td>
										<span
											v-text="item.name"
										></span>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div slot="footer" class="dialog-footer">
			<el-button size="default" type="primary" @click="addContactOffice">확인</el-button>
			<el-button size="default" @click="dialogVisible = false">취소</el-button>
		</div>
	</el-dialog>
</template>

<script>

export default {
	props: {},
	components: {
	},
	data() {
		return {
			dialogVisible: false,

			// 선택
			countrySearchOptions: this.$amConstant.CONTACT_AREA_MAP,
			selectedCountry: "",

			// function 추가 목록
			contactOfficeList: []
		};
	},
	created() {},
	mounted() {},
	watch: {},
	methods: {
		open() {
			this.dialogVisible = true;
			this.contactOfficeList = [];
		},

        /**
         * Contact Office 목록을 불러옵니다.
         */
        getContactOfficeList() {
		    const url = this.$urlConstant.API_URL_PREFIX.BRANCH + this.$urlConstant.API_URL_SUFFIX.BRANCH.LIST_ALL;
		    let param = {
		        areaCode : this.selectedCountry
            };
		    
		    this.$axios.post( url, param ).then( res => {
		        
		        if ( this.$common.isNotEmpty( res.data ) ) {
                    _.each( res.data, function( item ) {
                        item['isChecked'] = false;
                    });
                    this.contactOfficeList = res.data;
                }
            });
        },

        /**
         * 선택된 ContactOffice 목록을 가져옵니다.
         */
        addContactOffice() {
            let checkedList = [];
            _.each( this.contactOfficeList, function( item ) {
                if ( item.isChecked ) {
                    checkedList.push( item );
                }
            });
            
            this.$emit( "addContactOffice", checkedList );
            this.dialogVisible = false;
        }
	}
};
</script>

<style></style>
