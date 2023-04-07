<template>
    <!-- Contact edit -->
    <div class="contact-edit-area">
        <div class="input-row-md">
            <div class="input-label">
                <span>Contact</span>
            </div>
            <div class="input-data">
                <!-- add contact box -->
                <div class="contact-box-group">
                    <div class="box-item">
                        <div class="box-header">
                            <span class="tit">Contact Office</span>
                            <div class="btn-wrap">
                                <el-button type="primary" size="xsmall" @click="addContactOfficeModalOpen()">추가</el-button>
                                <el-button type="gray" size="xsmall" @click="removeContactOffice">삭제</el-button>
                            </div>
                        </div>
                        <div class="box-body">
                            <!-- scroll area -->
                            <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                <!-- el1 -->
                                <div>
                                    <!-- 내용은 여기에. -->
                                    <ul class="contact-list">
                                        <!-- li 클릭 시 active 클래스 추가 -->
                                        <li v-for="(item, index) in contactOfficeList"
                                            :key="index"
                                            :class="{ active: index === selectedOfficeIndex }"
                                            @click="selectedOfficeIndex = index"
                                        >
                                            <span class="tit" v-text="item.name"><!-- 대한민국 본사 --></span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btn-item">
                        <el-button type="secondary" size="circle" @click="addContactManagerModalOpen()">
                            <span class="material-icons">person_add_alt</span>
                        </el-button>
                    </div>
                    <div class="box-item">
                        <div class="box-header">
                            <span class="tit">Contact Manager</span>
                            <div class="btn-wrap">
                                <el-button type="gray" size="xsmall" @click="removeContactManager">삭제</el-button>
                            </div>
                        </div>
                        <div class="box-body">
                            <!-- scroll area -->
                            <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                <!-- el1 -->
                                <div>
                                    <!-- 내용은 여기에. -->
                                    <ul class="contact-list">
                                        <li v-for="(item, index) in contactManagerList"
                                            :key="index"
                                            :class="{ active: index === selectedManagerIndex }"
                                            @click="selectedManagerIndex = index"
                                        >
                                            <span class="tit" v-text="item.name"><!-- 홍길동 --></span>
                                            <span class="txt" v-text="item.tel"><!-- 010-1111-2222 --></span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Contact Office 추가 모달 -->
        <the-add-contact-office-modal ref="addContactOfficeModal"
                                      @addContactOffice="getContactOfficeList"
        ></the-add-contact-office-modal>

        <!-- Contact Manager 추가 모달 -->
        <the-add-contact-manager-modal ref="addContactManagerModal"
                                       @addContactManager="getContactManagerList"
        ></the-add-contact-manager-modal>
    </div>
</template>

<script>
import theAddContactOfficeModal from "~/components/kccam/manager/modal/TheAddContactOfficeModal.vue";
import theAddContactManagerModal from "~/components/kccam/manager/modal/TheAddContactManagerModal.vue";

export default {
    components: {
        theAddContactOfficeModal,
        theAddContactManagerModal,
    },
    data() {
        return {
            // 선택된 목록
            selectedOfficeIndex : "",
            selectedManagerIndex : "",

            // contact office list
            contactOfficeList: [],

            // contact manager list
            contactManagerList: [],
        };
    },
    methods: {
        // contact office 추가 모달 열기
        addContactOfficeModalOpen() {
            this.$refs.addContactOfficeModal.open();
        },

        // contact manager 추가 모달 열기
        addContactManagerModalOpen() {
            if ( this.$common.isEmpty( this.contactOfficeList ) || this.$common.isEmpty( String( this.selectedOfficeIndex ) ) ) {
                this.$common.swalWithOptions( "먼저 Contact Office를 선택해주세요.", "", "warning" );
                return;
            }

            this.$refs.addContactManagerModal.open( this.contactOfficeList[this.selectedOfficeIndex] );
        },

        /**
         * 리스트 설정
         * @param contactOfficeList
         * @param contactManagerList
         */
        setContactList( contactOfficeList, contactManagerList ) {
            if ( this.$common.isEmpty( contactOfficeList ) ) {
                return;
            }

            this.contactOfficeList = contactOfficeList;
            this.contactManagerList = contactManagerList;
        },

        /**
         * 체크된 Contact Office 리스트를 가져옵니다.
         */
        getContactOfficeList( contactOfficeList ) {
            this.contactOfficeList = [...this.contactOfficeList, ...contactOfficeList];
        },

        /**
         * 체크된 Contact Manager 리스트를 가져옵니다.
         */
        getContactManagerList( contactManagerList ) {
            this.contactManagerList = [...this.contactManagerList, ...contactManagerList];
        },

        /**
         * 선택된 Contact Office를 삭제합니다.
         */
        removeContactOffice() {
            this.contactOfficeList.splice( this.selectedOfficeIndex, 1 );
        },

        /**
         * 선택된 Contact Manager를 삭제합니다.
         */
        removeContactManager() {
            this.contactManagerList.splice( this.selectedManagerIndex, 1 );
        },

        /**
         * Contact Office 리스트를 반환합니다.
         */
        addContactOfficeList() {
            return this.contactOfficeList;
        },

        /**
         * Contact Manager 리스트를 반환합니다.
         */
        addContactManagerList() {
            return this.contactManagerList;
        },
    },
};
</script>

<style></style>
