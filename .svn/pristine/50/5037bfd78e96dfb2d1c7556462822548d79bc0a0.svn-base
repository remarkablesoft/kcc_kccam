<template>
    <!-- 문서 타입 설정 모달 -->
    <el-dialog title="문서 타입 설정" :visible.sync="dialogVisible" width="50rem">
        <div class="inner-content">
            <!-- 검색 버튼 -->
            <div class="btn-wrap top right">
                <el-button type="primary" size="small" @click="[]">추가</el-button>
            </div>

            <!-- list area -->
            <div class="list-area">
                <div class="manager-table-normal">
                    <div class="table-body">
                        <table>
                            <colgroup>
                                <col style="width:%" />
                                <col style="width:15%" />
                            </colgroup>
                            <thead>
                                <tr class="bg-lgray">
                                    <th><span>문서타입명</span></th>
                                    <th><span>삭제</span></th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- no-data :: tr -->
                                <tr v-if="false">
                                    <td colspan="2">
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
                                <tr v-for="(item, i) in docTypeList" :key="i" class="list-item">
                                    <td>
                                        <el-input class="tc" placeholder="" v-model="item.docTypeNameInput" clearable> </el-input>
                                    </td>
                                    <td>
                                        <el-button type="gray" size="small" @click="[]">삭제</el-button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <!-- 페이지네이션 -->
                        <thePagination />
                    </div>
                </div>
            </div>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button size="default" type="primary">저장</el-button>
            <el-button size="default" @click="dialogVisible = false">취소</el-button>
        </div>
    </el-dialog>
</template>

<script>
export default {
    props: {},
    components: {},
    data() {
        return {
            dialogVisible: false,

            // 문서타입 목록
            docTypeList: [
                {
                    docTypeNameInput: "INTRODUCTION",
                },
                {
                    docTypeNameInput: "Datasheet",
                },
                {
                    docTypeNameInput: "",
                },
            ],
        };
    },
    created() {},
    mounted() {},
    watch: {},
    methods: {
        open() {
            this.dialogVisible = true;
        },
    },
};
</script>

<style></style>
