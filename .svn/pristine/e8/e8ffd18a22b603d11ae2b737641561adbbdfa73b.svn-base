<template>
    <div class="inner-wrapper">
        <!-- loading -->
<!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
<!--            <the-loading />-->
<!--        </div>-->

        <!-- inner-container -->
        <div class="inner-container">
            <div class="manager-content-body">
                <!-- contents 내용 -->
                <div class="content-title">
                    <div class="sub-title">
                        <h2>Datasheet 관리 수정</h2>
                    </div>
                </div>

                <div class="content-detail">
                    <div class="btn-wrap-md">
                        <el-button type="primary" size="default" @click="[]">저장</el-button>
                        <el-button type="gray" size="default" @click="[]">삭제</el-button>
                        <el-button type="st st-primary" size="default" @click="goList()">목록</el-button>
                    </div>
                    <div class="input-area">
                        <div class="input-row-md lr">
                            <div class="left-area">
                                <div class="input-label">
                                    <span>등록일</span>
                                </div>
                                <div class="input-data">
                                    <span>2020.12.01</span>
                                </div>
                            </div>
                            <div class="right-area">
                                <div class="input-label">
                                    <span>최종 수정일</span>
                                </div>
                                <div class="input-data">
                                    <span>2020.12.08</span>
                                </div>
                            </div>
                        </div>

                        <!-- datasheet edit component :: TheDatasheetEdit.vue -->
                        <the-datasheet-edit></the-datasheet-edit>

                        <div class="input-row-md">
                            <div class="input-label">
                                <span>설명</span>
                            </div>
                            <div class="input-data">
                                <el-input type="textarea" :rows="5" placeholder="내용을 입력하세요." v-model="descr"> </el-input>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Function 추가 모달 -->
        <the-add-function-modal ref="addFunctionModal"></the-add-function-modal>

        <!-- 아이템 코드 추가 모달 -->
        <the-add-item-code-modal ref="addItemCodeModal"></the-add-item-code-modal>

        <!-- 관련문서 추가 모달 -->
        <the-add-related-document-modal ref="addRelatedDocumentModal"></the-add-related-document-modal>

        <!-- 참고자료 추가 모달 -->
        <the-add-reference-modal ref="addReferenceModal"></the-add-reference-modal>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theAddFunctionModal from "~/components/kccam/manager/modal/TheAddFunctionModal.vue";
import theAddItemCodeModal from "~/components/kccam/manager/modal/TheAddItemCodeModal.vue";
import theAddRelatedDocumentModal from "~/components/kccam/manager/modal/TheAddRelatedDocumentModal.vue";
import theAddReferenceModal from "~/components/kccam/manager/modal/TheAddReferenceModal.vue";
import theDatasheetEdit from "~/components/kccam/manager/datasheet/TheDatasheetEdit.vue";
import theContactEdit from "~/components/kccam/manager/contact/TheContactEdit.vue";
import theAddInfoEditor from "~/components/kccam/manager/editor/TheAddInfoEditor.vue";

export default {
    layout: "managerLayout",
    components: {
        theLoading,
        thePagination,
        theAddFunctionModal,
        theAddItemCodeModal,
        theAddRelatedDocumentModal,
        theAddReferenceModal,
        theDatasheetEdit,
        theContactEdit,
        theAddInfoEditor,
    },
    data() {
        return {
            productNameInput: "",
            itemCodeInput: "",

            // 선택
            materialSearchOptions: [
                {
                    value: "전체",
                    label: "전체",
                },
                {
                    value: "Option2",
                    label: "Option2",
                },
                {
                    value: "Option3",
                    label: "Option3",
                },
            ],
            materialSearchOptionsValue: "",

            productInput: "",

            functionInput: "",

            // 선택
            showSearchOptions: [
                {
                    value: "노출",
                    label: "노출",
                },
                {
                    value: "비노출",
                    label: "비노출",
                },
            ],
            showSearchOptionsValue: "",

            // radio
            radioGroup: [
                {
                    id: "mainFunction01",
                    text: "Low Stress",
                },
                {
                    id: "mainFunction02",
                    text: "Electric Insulation",
                },
                {
                    id: "mainFunction03",
                    text: "Freezing Resistance",
                },
            ],
            selected: "mainFunction01",

            descr: "",

            // 관련문서 목록
            relatedDocumentList: [
                {
                    id: "1",
                    fileName: "홈페이지 EMC 소재 구분.xlsx",
                    fileSize: "71KB",
                    fileType: "INTRODUCTION",
                },
                {
                    id: "2",
                    fileName: "홈페이지.xlsx",
                    fileSize: "51KB",
                    fileType: "INTRODUCTION",
                },
            ],

            // 참고자료 목록
            referenceList: [
                {
                    id: "01",
                    referenceName: "KTMC series selection guide(LSI devices)",
                    fileName: "홈페이지 EMC 소재 구분.xlsx",
                    fileSize: "71KB",
                },
                {
                    id: "02",
                    referenceName: "KTMC series selection guide(LSI devices)",
                    fileName: "홈페이지.xlsx",
                    fileSize: "51KB",
                },
            ],
        };
    },
    methods: {
        // 목록 페이지로 이동
        goList() {
            this.$router.push( this.localePath( "/kccam/manager/productMgnt/datasheet/datasheet_list" ));
        },

        // Function 추가 모달 열기
        addFunctionModalOpen() {
            this.$refs.addFunctionModal.open();
        },

        // 아이템 코드 추가 모달 열기
        addItemCodeModalOpen() {
            this.$refs.addItemCodeModal.open();
        },

        // 관련문서 추가 모달 열기
        addRelatedDocumentModalOpen() {
            this.$refs.addRelatedDocumentModal.open();
        },

        // 참고자료 추가 모달 열기
        addReferenceModalOpen() {
            this.$refs.addReferenceModal.open();
        },
    },
};
</script>

<style scoped></style>
