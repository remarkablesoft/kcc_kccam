<template>
    <el-dialog :title="title" :visible.sync="dataSheetDialogVisible" @close="close" width="95%" top="5vh">
        <div class="inner-content" style="padding: 0rem; height: 100%; max-height: 69vh;">
            <div class="table-wrap data-sheet" ref="dataSheet">
                <div class="inner-head"></div>
                <div class="inner-body" style="max-height: 69vh;">
                    <table class="table table-gray">
                        <caption>
                            제품 리스트
                        </caption>
                        <colgroup>
                            <col style="width: 50px" />
                            <col style="width: 100px" />
                            <col style="width: 125px" v-for="(nameUnit, index) in datasheetNameUnitList" :key="index" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col" rowspan="2">
                                    <span></span>
                                </th>
                                <th scope="col" rowspan="2">
                                    <span>{{ $t(`user_common_product_similarProduct_name`) }}</span>
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
                                        <input type="checkbox" :id="product.oid + productIndex + 1" v-model="product.inputCheck" />
                                        <label :for="product.oid + productIndex + 1">
                                            <i> </i>
                                        </label>
                                    </div>
                                </td>
                                <td>
                                    <nuxt-link
                                        :to="localePath('/kccam/user/product/product_view?depth=3&productOid=' +
                                                product.oid +
                                                '&classificationOid=' +
                                                classificationOid +
                                                '&partOid=' +
                                                product.materialOid +
                                                '&classification=' +
                                                classification)"
                                        class="txt-link-underline"
                                    >
                                        {{ product.name }}</nuxt-link
                                    >
                                </td>
                                <td v-for="(nameUnit, index) in datasheetNameUnitList" :key="index">
                                    <span v-text="product[nameUnit]"></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="dialog-footer" slot="footer">
            <el-button type="st st-primary" size="medium" @click="removeNotCheck()">
                <span class="material-icons-outlined">assignment_turned_in</span>
                <span class="txt">{{ $t(`user_common_product_dataSheet_compareBtn`) }}</span>
            </el-button>

            <el-button type="st st-gray" size="medium" @click="createNotCheck()" v-show="ModalBtnShowYn">
                <span class="material-icons-outlined">format_list_bulleted</span>
                <span class="txt">{{ $t(`user_datasheetModal_showList`) }}</span>
            </el-button>

            <el-button size="default" @click="close">{{ $t(`user_main_popup_close`) }}</el-button>
        </div>
    </el-dialog>
</template>
<script>
export default {
    props: {
        dataSheetDialogVisible: {
            type: Boolean,
            required: true,
        },
        entireDataSheetList: {
            type: Array,
            required: true,
        },
	    checkedDataSheetList : {
        	type : Array,
		    required : true,
	    },
        btnShowYn: {
            type: Boolean,
            required: false,
        },
    },
    data() {
        return {
            title: this.$t(`user_common_product_productDataSheet`),
            classification : "",
            classificationOid: "",
	        selectedColumnName : '',

            productList: [],
            datasheetNameUnitList: [],
	        sortedProductList : [],

            ModalBtnShowYn: this.btnShowYn,
	        sortFlag           : null,
        };
    },
    created() {
    	this.setDataSheetList();
    },
    watch: {
        entireDataSheetList() {
            this.setDataSheetList();
            this.$forceUpdate();
        },
        btnShowYn() {
            this.ModalBtnShowYn = this.btnShowYn;
        },
	    checkedDataSheetList() {
		    if( this.$common.isNotEmpty( this.checkedDataSheetList ) ) {
			    this.sortedProductList.forEach( sortedItem => {
			        sortedItem.inputCheck = false;
				    this.checkedDataSheetList.forEach( checkedItem => {
					    if( sortedItem.oid === checkedItem.oid ) {
						    sortedItem.inputCheck = true;
					    }
				    })
			    });
			    this.$forceUpdate();
		    }
	    }
    },
	fetch() {
        this.setDataSheetList();
	},

    methods: {
        setDataSheetList() {
	        this.classification = this.$route.query.classification;
	        this.datasheetNameUnitList =  this.entireDataSheetList[0];
            this.classificationOid = this.entireDataSheetList[1];
            this.productList = this.entireDataSheetList[2];
            this.sortedProductList = _.cloneDeep( this.productList );
        },

        /**
         *  선택한부분을 제외한 tr을 지워줍니다
         */
        removeNotCheck() {
            const _self = this;

            // 선택된 check가 1개이하일경우 swalAlert를 띄워줍니다
            let result = 0;
            _.each(_self.sortedProductList, function(product) {
                if (product.inputCheck) {
                    result += 1;
                }
            });
            _.each(_self.sortedProductList, function(product) {
                if (2 > result) {
                    _self.$common.swalWithOptions( _self.$t(`user_datasheetModal_swalWithOptions_tit`), _self.$t(`user_datasheetModal_swalWithOptions_contents`), "warning");
                    return;
                }

                if (!product.inputCheck) {
                    product.showYn = false;
                    _self.ModalBtnShowYn = true;
                }
            });
        },

        /**
         *  제외된 tr을 다시 그려줍니다
         */
        createNotCheck() {
            const _self = this;

            _.each(_self.sortedProductList, function(product) {
                if (!product.inputCheck) {
                    product.showYn = true;
                    _self.ModalBtnShowYn = false;
                }
            });
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

        close() {
            this.$emit("close");
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

	    }
    },
};
</script>
<style></style>
