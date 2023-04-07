<template>
    <!-- Datasheet edit -->
    <div class="datasheet-edit-area">
        <!-- 제품상세 등록/수정 페이지에서 보임 -->
        <div class="table-wrap">
            <div class="inner-head">
                <!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
            </div>
            <div class="inner-body">
                <!--
                                            table 태그 공통 클래스 : table
                                            스타일 구분 클래스 일반(th 검정) : table-normal
                                            스타일 구분 클래스 th 회색 : table-gray
                                            -->
                <!-- 퍼블용(보여주기용 / 개발 시 자유롭게 하시면 됩니다. ) :: Datasheet 관리 수정 페이지 -->
                <table class="table table-gray">
                    <!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
                    <caption>
                        Technical Datasheet Properties 표
                    </caption>
                    <!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
                    <colgroup>
                        <col style="width: 5%" />
                        <col style="width: 35%" />
                        <col style="width: 15%" />
                        <col style="width: 15%" />
                        <col style="width: 15%" />
                        <col style="width: 15%" />
                    </colgroup>
                    <thead>
                        <tr>
                            <th scope="col" colspan="6" class="bg-lblue">
                                <div class="th-is-right-btn">
                                    <span class="">Datasheet</span>

                                    <!-- 추가/삭제 버튼 :: Datasheet 관리에서만 보임 / 제목 가운데 위치하기 위해서 position으로 위치  -->
                                    <div class="btn-wrap">
                                        <el-button type="primary" size="xsmall" @click="addDatasheetItem">추가</el-button>
                                        <el-button type="gray" size="xsmall" @click="removeDatasheetItem">삭제</el-button>
                                    </div>
                                </div>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">
                            </th>
                            <th scope="row">
                                <span>ITEM</span>
                            </th>
                            <th scope="row">
                                <span>Unit</span>
                            </th>
                            <th scope="row">
                                <span>Typical</span>
                            </th>
                            <th scope="row">
                                <span>Test Method</span>
                            </th>
                            <th scope="row">
                                <span>Value</span>
                            </th>
                        </tr>
                        <!-- list-item -->
                        <tr v-for="(item, index) in datasheetItemList" :key="index">
                            <td>
                                <div class="custom-checkbox">
                                    <input :id="$common.isNotEmpty( item.oid )? item.oid : index" v-model="item.isChecked" type="checkbox" :key="$common.isNotEmpty( item.oid )? item.oid : index"/>
                                    <label :for="$common.isNotEmpty( item.oid )? item.oid : index">
                                        <i></i>
                                        <!-- <span>텍스트</span> -->
                                    </label>
                                </div>
                            </td>
                            <td scope="row">
                                <el-input v-model="item.name"> </el-input>
                            </td>
                            <td>
                                <el-input v-model="item.itemUnit"> </el-input>
                            </td>
                            <td>
                                <el-input v-model="item.typical"> </el-input>
                            </td>
                            <td>
                                <el-input v-model="item.testMethod"> </el-input>
                            </td>
                            <td>
                                <el-input v-model="item.itemValue"> </el-input>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props : {
        datasheetOid : {
            type : String,
            required : true,
            default : "",
        },
    },
    data() {
        return {
            datasheetSearchInput: "",
            datasheetInput: "",
            defaultDatasheetSearchInput: "",
            newDatasheetNameInput: "",
            datasheetItemList : [],
        };
    },
    methods: {
        /**
         * 데이터 시트 아이템을 추가합니다.
         */
        addDatasheetItem() {
            let datasheetItem = {
                datasheetOid : this.datasheetOid,
                name : "",
                itemUnit : "",
                typical : "",
                testMethod : "",

                itemValue : "",
	            isChecked : false,
            }

            this.datasheetItemList.push( datasheetItem );
        },

        /**
         * 데이터 시트 체크 항목을 삭제합니다.
         */
        removeDatasheetItem() {

            if ( this.$common.isEmpty( this.datasheetItemList ) ) {
                return;
            }

            const _self = this;
            let removedItemList = [];
            let checkedItemList = [];
            _.each( _self.datasheetItemList, function( item ) {
                if ( !item.isChecked ) {
                    removedItemList.push( item );
                }
                else {
                	checkedItemList.push( item );
                }
            });

            if ( this.$common.isEmpty( checkedItemList ) ){
	            this.$common.confirmSwal( "삭제할 데이터 Row를 체크해주세요.", "", "warning" );
	            return;
            }

            this.datasheetItemList = removedItemList;
        },

        /**
         * 리스트를 가져옵니다.
         */
        setList( datasheetItemList ) {
            this.datasheetItemList = datasheetItemList;
            this.setDatasheetCheck();
        },

        /**
         * 데이터시트에 체크 속성을 부여합니다.
         */
        setDatasheetCheck() {
            if ( this.$common.isEmpty( this.datasheetItemList ) ) {
                return;
            }

            _.each( this.datasheetItemList, function( item ) {
                item['isChecked'] = false;
            });
        },

        /**
         * 데이터시트 리스트를 반환합니다.
         * @returns {[]}
         */
        getDatasheetItemList() {
            return this.datasheetItemList;
        }

    },
};
</script>

<style></style>
