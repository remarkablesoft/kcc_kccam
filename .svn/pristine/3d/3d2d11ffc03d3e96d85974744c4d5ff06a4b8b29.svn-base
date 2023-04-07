<template>
    <div class="inner-wrapper">
        <!-- loading -->
        <div class="loading-container" v-if="loadingIndicator > 0">
            <the-loading />
        </div>

        <!-- manager-content-body -->
        <div class="manager-content-body">
            <div class="content-title">
                <div class="sub-title">
                    <h2>Datasheet 관리 목록</h2>
                </div>
            </div>
            <!-- table -->
            <div class="content-detail">
                <!--search area -->
                <div class="search-area">
                    <!-- Depth -->
                    <div class="input-row">
                        <!-- <div class="label">
                            <span class="input-tit">검색항목</span>
                        </div> -->
                        <div class="data">
                            <el-input size="large" placeholder="Datasheet명 검색" prefix-icon="el-icon-search" v-model="searchInput" clearable> </el-input>
                        </div>
                    </div>

                    <!-- 검색 버튼 -->
                    <div class="btn-wrap">
                        <el-button class="search-btn" type="gray" size="default" @click="[]">검색</el-button>
                    </div>
                </div>

                <!-- 등록 버튼 -->
                <div class="btn btn-regist">
                    <el-button type="gray" size="small" @click="goEdit()">신규 Datasheet 등록</el-button>
                </div>

                <!-- table -->
                <div class="manager-table-normal">
                    <table>
                        <colgroup>
                            <col style="width:8%" />
                            <col style="width:38.5%" />
                            <col style="width:38.5%" />
                            <col style="width:15%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th scope="col"><span>번호</span></th>
                                <th scope="col"><span>Datasheet 명</span></th>
                                <th scope="col"><span>설명</span></th>
                                <th scope="col"><span>최종 수정일</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if="false">
                                <td colspan="4">
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
                            <tr v-for="(item, index) in datasheetList" :key="index" class="list-item" @click="goEdit()">
                                <td>
                                    <span v-text="listCount - index"></span>
                                </td>
                                <td>
                                    <span v-text="item.title"></span>
                                </td>
                                <td>
                                    <span v-text="item.descr"></span>
                                </td>
                                <td>
                                    <span v-text="item.modDate"></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <!-- 페이지네이션 -->
                <thePagination v-if="0 < listCount" :visible-buttons-count="10" :page-size="pageSize" :current-page="currentPage" :total-count="listCount" />
            </div>
        </div>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import thePagination from "~/components/common/ThePagination.vue";

export default {
    layout: "managerLayout",
    components: {
        theLoading,
        thePagination,
    },
    data() {
        return {
            // 선택
            searchOptions: [
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
            value: "",
            searchInput: "",

            // datasheet 목록
            datasheetList: [
                // {
                //     index: "3",
                //     datasheetName: "EMC 제품 시리즈 Datasheet",
                //     descr: "EMC 제품 KTMC 시리즈용 Datasheet",
                //     lastUpdate: "2020. 12. 01",
                // },
                // {
                //     index: "2",
                //     datasheetName: "AM 제품 시리즈 Datasheet",
                //     descr: "AM 제품 시리즈용 Datasheet",
                //     lastUpdate: "2020. 12. 01",
                // },
                // {
                //     index: "1",
                //     datasheetName: "EMC 제품 Datasheet",
                //     descr: "EMC 제품 Datasheet",
                //     lastUpdate: "2020. 12. 01",
                // },
            ],

            startIndex: 1,
            listCount: 0,
            pageSize: 10,
            currentPage: 1,
        };
    },
    // 비동기처리를 시작하는 것
    async fetch() {
        await this.getList();
    },
    methods: {
        // 수정/등록 페이지로 이동
        goEdit() {
            this.$router.push( this.localePath("/kccam/manager/productMgnt/datasheet/datasheet_edit"));
        },

        async getList() {
            // 애는단지 controller의 주소만 가지고왔어~
            const url = this.$urlConstant.API_URL_PREFIX.DATASHEET + this.$urlConstant.API_URL_SUFFIX.DATASHEET.LIST;
            //let param = {};

            if (this.$route.query.page) {
                this.startIndex = (this.$route.query.page - 1) * this.pageSize + 1;
            }

            console.log(this.$route.query.page);

            if (this.$route.query.searchInput) {
                this.searchInput = this.$route.query.searchInput;
            }

            // 여기서 선언을 해주고 할당해줬어
            let param = {
                pageSize: this.pageSize,
                startIndex: this.startIndex,
                searchInput: this.searchInput,
            };

            console.log(this.$axios.post(url, param));

            // $axios.post(url, param) 여기서 모든데이터를 가공해서 줘버리네
            await this.$axios.post(url, param).then(res => {
                console.log("res?? :", res);
                // 따라서 여기선 뿌려주기만 하고...
                this.datasheetList = res.data.list;
                this.listCount = res.data.totalCount;
            });
        },
    },
};
</script>
