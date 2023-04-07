<template>
    <!-- 제품 추가 모달 -->
    <el-dialog title="EMail 불러오기" :visible.sync="visible" @close="close" width="60rem">
        <div class="inner-content">
            <!--search area -->
            <div class="search-area">
                <div class="input-row">
                    <div class="data">
                        <el-select size="sm" v-model="value" placeholder="회사명">
                            <el-option v-for="item in searchOptions" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                        </el-select>
                        <el-input size="large" placeholder="검색" prefix-icon="el-icon-search" v-model="searchInput" clearable> </el-input>
                        <!-- 검색 버튼 -->
                        <div class="btn-wrap">
                            <el-button class="search-btn" type="gray" size="default" @click="[]">검색</el-button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- list area -->
            <div class="list-area">
                <div class="manager-table-normal">
                    <div class="table-body">
                        <table>
                            <colgroup>
                                <col style="width:8%" />
                                <col style="width:30%" />
                                <col style="width:%" />
                            </colgroup>
                            <thead>
                                <tr class="bg-lgray">
                                    <th scope="col"><span>선택</span></th>
                                    <th scope="col"><span>회사명</span></th>
                                    <th scope="col"><span>이메일</span></th>
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
                                <tr v-for="(item, i) in list" :key="i" class="list-item">
                                    <td>
                                        <div class="custom-checkbox">
                                            <input :id="item.id" type="checkbox" />
                                            <label :for="item.id">
                                                <i></i>
                                                <!-- <span>텍스트</span> -->
                                            </label>
                                        </div>
                                    </td>
                                    <td>
                                        <span v-text="item.company"></span>
                                    </td>
                                    <td>
                                        <span v-text="item.email"></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <!-- 페이지네이션 -->
                        <!-- <thePagination /> -->
                    </div>
                </div>
            </div>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button size="default" type="primary">확인</el-button>
            <el-button size="default" @click="close">취소</el-button>
        </div>
    </el-dialog>
</template>

<script>
import thePagination from "~/components/common/ThePagination.vue";

export default {
    props: {
        loadEmailVisible: {
            type: Boolean,
            required: true,
        },
    },
    components: {
        thePagination,
    },
    data() {
        return {
            dialogVisible: false,

            // 선택
            searchOptions: [
                {
                    value: "회사명",
                    label: "회사명",
                },
                {
                    value: "리마커블 소프트",
                    label: "리마커블 소프트",
                },
                {
                    value: "김웅기 소프트",
                    label: "김웅기 소프트",
                },
            ],
            value: "",

            // 검색창
            searchInput: "",

            // 제품구성 목록
            list: [
                {
                    id: "dialog01",
                    company: "리마커블 소프트",
                    email: "abc@gmail.com",
                },
                {
                    id: "dialog02",
                    company: "리마커블 소프트",
                    email: "whong@gmail.com",
                },
            ],

            visible: this.loadEmailVisible,
        };
    },
    created() {},
    mounted() {},
    watch: {
        loadEmailVisible() {
            this.visible = this.loadEmailVisible;
        },
    },
    methods: {
        close() {
            this.visible = false;
            this.$emit("close");
        },
    },
};
</script>

<style></style>
