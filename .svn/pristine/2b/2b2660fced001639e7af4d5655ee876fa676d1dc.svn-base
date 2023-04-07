<template>
    <!-- Detail Market 추가 모달 -->
    <el-dialog title="Detail Market 추가" :visible.sync="dialogVisible" width="60rem">
        <div class="inner-content">
            <!--search area -->
            <div class="search-area">
                <div class="input-row">
                    <div class="data">
                        <el-select size="sm" v-model="value" placeholder="선택">
                            <el-option v-for="item in searchOptions" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                        </el-select>
                        <el-input size="large" placeholder="Market 명 검색" prefix-icon="el-icon-search" v-model="searchInput" clearable> </el-input>
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
                                <col style="width:%" />
                                <col style="width:15%" />
                            </colgroup>
                            <thead>
                                <tr class="bg-lgray">
                                    <th scope="col"><span>선택</span></th>
                                    <th scope="col"><span>Detail Market 명</span></th>
                                    <th scope="col"><span>Depth</span></th>
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
                                <tr v-for="(item, i) in detailMarketList" :key="i" class="list-item">
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
                                        <span v-text="item.detailMarketName"></span>
                                    </td>
                                    <td>
                                        <span v-text="item.depth"></span>
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
            <el-button size="default" type="primary">완료</el-button>
            <el-button size="default" @click="dialogVisible = false">취소</el-button>
        </div>
    </el-dialog>
</template>

<script>
import thePagination from "~/components/common/ThePagination.vue";

export default {
    props: {},
    components: {
        thePagination,
    },
    data() {
        return {
            dialogVisible: false,

            // 선택
            searchOptions: [
                {
                    value: "전체",
                    label: "전체",
                },
                {
                    value: "Depth",
                    label: "Depth",
                },
            ],
            value: "",

            // 검색창
            searchInput: "",

            // Detail Market 목록
            detailMarketList: [
                {
                    id: "detailMarket01",
                    detailMarketName: "DARM",
                    depth: "2",
                },
                {
                    id: "detailMarket02",
                    detailMarketName: "DARM",
                    depth: "3",
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
