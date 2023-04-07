<template>
    <div class="inner-wrapper">
        <div class="manager-content-body">
            <div class="content-title">
                <div class="sub-title">
                    <h2>Application 목록</h2>
                </div>
                <div class="btn">
                    <el-button type="st st-primary" size="small" @click="goAllView()">Application 전체 보기</el-button>
                    <el-button type="primary" size="small" @click="goEdit()">신규 Application 등록</el-button>
                </div>
            </div>
            <!-- table -->
            <div class="content-detail">
                <!--search area -->
                <div class="search-area">
                    <!-- Depth -->
                    <div class="input-row">
                        <div class="label" style="min-width: 14rem">
                            <span class="input-tit">소재구분</span>
                        </div>
                        <div class="data">
                            <el-select size="sm" v-model="selectedMaterial">
                                <el-option v-for="item in materialOptions" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                            </el-select>
                        </div>
                    </div>
                    <div class="input-row">
                        <div class="label" style="min-width: 14rem">
                            <span class="input-tit">Application 명</span>
                        </div>
                        <div class="data">
                            <el-input size="large" placeholder="검색어 입력" prefix-icon="el-icon-search" v-model="searchText" @keypress.enter.native="search" clearable> </el-input>
                        </div>
                    </div>
                    <!-- 검색 버튼 -->
                    <div class="btn-wrap">
                        <el-button class="search-btn" type="gray" size="default" @click="search">검색</el-button>
                    </div>
                </div>
                <!-- 등록 버튼 -->
                <!-- <div class="btn btn-regist">
					<el-button type="gray" size="small" @click="goEdit()">신규 Application 등록</el-button>
				</div> -->
                <!-- table -->
                <div class="manager-table-normal">
                    <table>
                        <colgroup>
                            <col style="width: 8%" />
							<col style="" />
                            <col style="width: 15%" />
                            <col style="width: 35%" />
                            <col style="width: 15%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th scope="col">
                                    <span>번호</span>
                                </th>
                                <th scope="col">
                                    <span>Application 명</span>
                                </th>
                                <th scope="col">
                                    <span>소재구분</span>
                                </th>
                                <th scope="col">
                                    <span>Full Path</span>
                                </th>
                                <th scope="col">
                                    <span>최종 수정일</span>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if="$common.isEmpty(applicationList)">
                                <td colspan="5">
                                    <!-- no-data(loading) -->
                                    <div class="no-data" v-if="applicationListLoading">
                                        <div class="loading-sm">
                                            <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                        </div>
                                        <p>데이터 로딩중입니다.</p>
                                    </div>
                                    <!-- no-data -->
                                    <div class="no-data" v-else>
                                        <i class="material-icons">error_outline</i>
                                        <p>데이터가 없습니다.</p>
                                    </div>
                                </td>
                            </tr>
                            <tr v-for="(item, i) in applicationList" :key="i" class="list-item" @click="goEdit(item)">
                                <td>
                                    <span v-text="item.no"></span>
                                </td>
                                <td>
                                    <span v-text="item.name"></span>
                                </td>
                                <td>
                                    <span v-text="item.materialInfo.name"></span>
                                </td>
                                <td>
                                    <span v-text="getProductFullPath(item.fullPathNameVC)"></span>
                                </td>
                                <td>
                                    <span v-text="$common.isEmpty(item.modDate) ? '-' : $common.formatDate(item.modDate)"></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- 페이지네이션 -->
                <thePagination v-if="0 < listCount" :visible-buttons-count="10" :total-count="listCount" :page-size="pageSize" :current-page="currentPage" />
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
            // 소재 구분 선택값
            selectedMaterial: "",

            // 소재 구분 옵션
            materialOptions: [],

            searchText: "",

            // application 목록
            applicationList: [],
            applicationListLoading : false,

            // 페이지네이션
            startIndex: 1,

            thisPage: 1,
            listCount: 0,
            pageSize: 10,
            currentPage: 1,
            lastPage: 0, // 마지막 페이지
        };
    },
    watch: {
        $route() {
            if (this.$route.query.page) {
                this.onChangePage(parseInt(this.$route.query.page));
            } else {
                this.onChangePage(1);
            }
        },
    },
    computed: {},
    async fetch() {
	    // 소재구분 옵션 리스트 생성 요청
	    this.getProductClassificationMap();
        // 전체 목록 요청
        await this.getList(1);
    },

    methods: {
        /**
         * 페이지 이동 처리
         */
        onChangePage(page) {
            this.currentPage = page;
            this.thisPage = (parseInt(this.currentPage) - 1) * parseInt(this.pageSize) + 1;
            this.getList(this.thisPage);
        },

        /**
         * 제품구분 전체보기 페이지로 이동
         */
        goAllView() {
            this.$router.push(this.localePath("/kccam/manager/productMgnt/application/application_allView"));
        },

        /**
         * 수정/등록 페이지로 이동
         */
        goEdit(item) {
            const url_edit = this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.APPLICATION_EDIT;

            let pushObj = {
                path: url_edit,
                query: {},
            };

            if (item) {
                //수정 페이지에서 필요
                pushObj.query.oid = item.oid;
            }

            if (this.searchText) {
                pushObj.query.searchText = this.searchText;
            }

            this.$router.push(this.localePath(pushObj));
        },

        /**
         * select option 용으로 사용할 소재 구분 리스트를 요청합니다.
         */
        getProductClassificationMap() {
            let param = {};
            let _self = this;
            const urlList = this.$urlConstant.API_URL_PREFIX.MATERIAL + this.$urlConstant.API_URL_SUFFIX.MATERIAL.MENU_LIST;
            this.$axios
                .post(urlList, param)
                .then((res) => {
                    if (200 === res.status && _self.$common.isNotEmpty(res.data)) {
                        _self.materialOptions = _self.makeList(res.data);
                        _self.selectedMaterial = _self.materialOptions[0].value;
                    }
                })
                .catch((error) => {
                    console.log(error);
                });
        },

        /**
         * 마켓 리스트를 가져옵니다.
         */
        async getList(startIndex) {
            let cnd = {
                startIndex: startIndex,
                pageSize: this.pageSize,
                categoryType: this.$amConstant.CATEGORY_TYPE.APPLICATION,
                exceptCategoryOidList: [this.$amConstant.ROOT_CATEGORY_OID.APPLICATION],

                fullPathNameSearch: true,
                fillMaterial: true,
                orderByPartOID: true,
            };
            const urlList = this.$urlConstant.API_URL_PREFIX.CLASSIFICATION + this.$urlConstant.API_URL_SUFFIX.CLASSIFICATION.LIST;

            cnd = this.addCondition(cnd);
            this.applicationList = [];
            this.applicationListLoading = true;

            await this.$axios.post(urlList, cnd).then((res) => {
                this.listCount = res.data.totalCount;
                this.applicationList = res.data.list;
                this.applicationList.forEach((item, i) => {
                    item.no = this.$common.settingNo(this.listCount, startIndex, i);
                });
            })
            .catch((error) => {
                console.log(error);
            });
	        this.applicationListLoading = false;
        },

        getProductFullPath(fullPath) {
            if (this.$common.isEmpty(fullPath)) {
                return;
            }

            return fullPath.toString().substring(fullPath.indexOf(">") + 1);
        },
        /**
         *  검색
         */
        search() {
            this.currentPage = 1;
            this.startIndex = 1;
            this.getList(this.startIndex);
        },

        /**
         *  getProductClassificationMap() 에서 받아온
         *  소재 구분 리스트를 가공하여 완성합니다. ( 마지막 )
         */
        makeList(list) {
            let tempArr = [];
            tempArr.push({
                value: "all",
                label: "전체",
            });
            _.each(list, function (item, index) {
                let tempObj = {
                    value: item.oid,
                    label: item.name,
                };
                tempArr.push(tempObj);
            });
            return tempArr;
        },

        /**
         * 검색조건에 추가 조건을 더합니다.
         */
        addCondition(cnd) {
            //검색하는 경우
            if (this.searchText) {
                cnd.searchText = this.searchText;
            }
            // 소재구분 처리
            switch (this.selectedMaterial) {
                case "all":
                    break;
                default:
                    cnd.partOid = this.selectedMaterial;
            }
            return cnd;
        },
    },
};
</script>
<style scoped></style>
