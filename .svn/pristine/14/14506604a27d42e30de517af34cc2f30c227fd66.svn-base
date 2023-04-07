<template>
    <!-- Contact Manager 추가 모달 -->
    <el-dialog title="Contact Manager 추가" :visible.sync="dialogVisible" width="45rem">
        <div class="inner-content">
            <!--search area -->
            <div class="search-area">
                <div class="input-row">
                    <div class="label">
                        <span>Contact Office :</span>
                    </div>
                    <div class="data">
                        <span v-text="$amConstant.CONTACT_AREA_MAP[contactOffice.areaCode]"></span>
                    </div>
                </div>
            </div>

            <!-- list area -->
            <div class="list-area">
                <div class="manager-table-normal">
                    <div class="table-body">
                        <table>
                            <colgroup>
                                <col style="width:10%" />
                                <col style="width:45%" />
                                <col style="width:45%" />
                            </colgroup>
                            <thead>
                                <tr class="bg-lgray">
                                    <th><span>선택</span></th>
                                    <th><span>직원명</span></th>
                                    <th><span>연락처</span></th>
                                </tr>
                            </thead>

                            <tbody>
                                <!-- no-data :: tr -->
                                <tr v-if="false">
                                    <td colspan="3">
                                        <!-- no-data(loading) -->
                                        <div class="no-data">
                                            <div class="loading-sm">
                                                <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                            </div>
                                            <p>데이터 로딩중입니다.</p>
                                        </div>
                                        <!-- no-data -->
                                        <div class="no-data" v-show="false">
                                            <i class="material-icons">error_outline</i>
                                            <p>데이터가 없습니다.</p>
                                        </div>
                                    </td>
                                </tr>
                                <tr v-for="(item, i) in contactManagerList" :key="i" class="list-item">
                                    <td>
                                        <div class="custom-checkbox">
                                            <input :id="item.oid" v-model="item.isChecked" type="checkbox" name="listCheckboxGroup" />
                                            <label :for="item.oid">
                                                <i></i>
                                                <!-- <span>텍스트</span> -->
                                            </label>
                                        </div>
                                    </td>
                                    <td>
                                        <span v-text="item.name"></span>
                                    </td>
                                    <td>
                                        <span v-text="item.tel"></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button size="default" type="primary" @click="addContactManager">확인</el-button>
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

            // contact manager 추가 목록
            contactManagerList: [],
            contactOffice : {}
            
        };
    },
    created() {},
    mounted() {},
    watch: {},
    methods: {
        
        open( contactOffice ) {
            this.dialogVisible = true;
            this.contactOffice = contactOffice;
            this.getContactManagerList();
        },

        /**
         * Contact Manager 리스트를 가져옵니다.
         */
        getContactManagerList() {
            const url = this.$urlConstant.API_URL_PREFIX.USER + this.$urlConstant.API_URL_SUFFIX.USER.LIST_ALL;
            
            let param = {
                branchOid : this.contactOffice.oid,
                userType : this.$amConstant.USER_TYPE.CONTACT_MANAGER,
            };

            this.$axios.post( url, param ).then( res => {
                if ( this.$common.isEmpty( res.data ) ) {
                  return;
                }
    
                _.each( res.data, function( item ) {
                    item['isChecked'] = false;
                });

                this.contactManagerList = res.data;
            });
        },

        /**
         * Contact Manager 정보를 반환합니다.
         */
        addContactManager() {
            let checkedList = [];
            _.each( this.contactManagerList, function( item ) {
                if ( item.isChecked ) {
                    checkedList.push( item );
                }
            });

            this.$emit( "addContactManager", checkedList );
            this.dialogVisible = false;
        }
    },
};
</script>

<style></style>
