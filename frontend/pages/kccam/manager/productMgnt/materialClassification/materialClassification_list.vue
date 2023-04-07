<template>
    <div class="inner-wrapper">
        <!-- loading -->
<!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
<!--            <the-loading />-->
<!--        </div>-->

        <!-- manager-content-body -->
        <div class="manager-content-body">
            <div class="content-title">
                <div class="sub-title">
                    <h2>소재구분 관리 목록</h2>
                </div>
                <div>
                    <p class="sub-detail">
                        * 신규 소재구분이 필요한 경우 관리자에게 문의 바랍니다.
                    </p>
                </div>
                <!-- <div class="btn-wrap">
                        <el-button class="search-btn" type="gray" size="default" @click="excelDownList()">엑셀 다운로드</el-button>
                </div> -->
            </div>
            <!-- table -->
            <div class="content-detail">
                <div class="manager-table-normal">
                    <table>
                        <colgroup>
                            <col style="width:8%" />
                            <col style="" />
                            <col style="width:15%" />
                            <col style="width:15%" />
                            <col style="width:15%" />
                            <col style="width:15%" />
                        </colgroup>
                        <thead>
                            <tr class="bg-lgray">
                                <th scope="col"><span>번호</span></th>
                                <th scope="col"><span>구분명</span></th>
                                <th scope="col"><span>제품 수</span></th>
                                <th scope="col"><span>Product Classification 수</span></th>
                                <th scope="col"><span>Appliction 수</span></th>
                                <th scope="col"><span>Function 수</span></th>
                            </tr>
                        </thead>

                        <tbody>
                            <!-- no-data :: tr -->
                            <tr v-if="$common.isEmpty( materialClassificationList )">
                                <td colspan="6">
                                    <!-- no-data(loading) -->
                                    <div class="no-data" v-if="$fetchState.pending">
                                        <div class="loading-sm">
                                            <img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
                                        </div>
                                        <p>데이터 로딩중입니다.</p>
                                    </div>
                                    <!-- no-data -->
                                    <div class="no-data" v-if=" true !== $fetchState.pending ">
                                        <i class="material-icons">error_outline</i>
                                        <p>데이터가 없습니다.</p>
                                    </div>
                                </td>
                            </tr>
                            <tr v-for="(item, i) in materialClassificationList" :key="i" class="list-item" @click="goEdit(item)">
                                <td>
                                    <span v-text="getNumber(i)"></span>
                                </td>
                                <td>
                                    <span v-text="item.name"></span>
                                </td>
                                <td>
                                    <span v-text="item.productCnt"></span>
                                </td>
                                <td>
                                    <span v-text="item.productClassificationCnt"></span>
                                </td>
                                <td>
                                    <span v-text="item.applicationCnt"></span>
                                </td>
                                <td>
                                    <span v-text="item.functionCnt"></span>
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
            //소재구분 관리 목록
            materialClassificationList: [],

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
                this.$fetch();
            }
        },
    },
    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    async fetch() {
        await this.getList(1);
    },
    methods: {
        // 수정/등록 페이지로 이동
        goEdit(item) {
            const urlEdit = this.$urlConstant.MENU_URL_PREFIX.MANAGER_PRODUCT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT_MGNT.MATERIAL_EDIT;
            this.$router.push(this.localePath({
                path: urlEdit,
                query: { oid: item.oid },
            }));
        },
        //게시글 번호 ( 역순 )
        getNumber(i) {
            return this.listCount - (this.thisPage - 1) * this.pageSize - i;
        },
        onChangePage(page) {
            this.currentPage = page;
            this.thisPage = (parseInt(this.currentPage) - 1) * parseInt(this.pageSize) + 1;
            this.getList(this.thisPage);
        },

        //소재구분 리스트 불러오기
        async getList(startIndex) {
            const urlList = this.$urlConstant.API_URL_PREFIX.MATERIAL + this.$urlConstant.API_URL_SUFFIX.MATERIAL.LIST;
            let cnd = {
                startIndex: startIndex,
                pageSize: this.pageSize,
                fillProductCnt: true,
            };

            await this.$axios.post(urlList, cnd).then(res => {
                if (this.$common.isEmpty(res.data)) {
                    this.materialClassificationList = [];
                    this.listCount = 0;
                    return;
                }

                this.materialClassificationList = res.data.list;
                this.listCount = res.data.totalCount;
                this.lastPage = this.$common.getLastPage(this.listCount, this.pageSize);
            });
        },

        //엑셀 파일 다운로드
        excelDownList() {
            const urlList = this.$urlConstant.API_URL_PREFIX.EXCEL + this.$urlConstant.API_URL_SUFFIX.EXCEL.MAKE_WITH_JSON;

			let param =[
				"fileName=소재구분 관리 목록.xlsx",
				"sheetName=시트명",
				"displayHeader=번호,구분명,제품 수,Product Classification 수,Appliction 수,Function 수",
			];

			let jsonData =[]

			this.materialClassificationList.forEach( (item, index)=> {

				let temp = {
					"번호"                       : ( this.materialClassificationList.length - index ),
					"구분명"                      : item.name,
					"제품 수"                     : item.productCnt,
					"Product Classification 수" : item.productClassificationCnt,
					"Appliction 수"             : item.applicationCnt,
					"Function 수"               : item.functionCnt,
				}
				jsonData.push(temp)
			})

			param.push("jsonData="+ JSON.stringify(jsonData));

			let form = "<form action='" + urlList +"' method='post'>";

			let stringSplit = "";
			$.each(param, function (index, item) {
				stringSplit = item.split("=");
				form +=
					"<input type='hidden' name='" +
					stringSplit[0] +
					"'value='" +
					stringSplit[1] +
					"'/>";
			});

			form += "</form>";
			$(form).appendTo("body").submit().remove();

        },



    },
};
</script>

<style scoped></style>
