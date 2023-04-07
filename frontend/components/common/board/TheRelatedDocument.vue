<template>
    <!-- Related Document desc -->
    <div class="table-wrap">
        <div class="inner-head"></div>
        <div class="inner-body">
            <!--
                table 태그 공통 클래스 : table
                스타일 구분 클래스 일반(th 검정) : table-normal
                스타일 구분 클래스 th 회색 : table-gray
            -->
            <table class="table table-normal thead-lg">
                <!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
                <caption>
                    관련 문서 표
                </caption>
                <!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
                <colgroup>
                    <col style="" />
                    <col style="width: 15%" />
                    <col style="width: 12%" />
                    <col style="width: 12%" />
                    <col style="width: 12%" />
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col"><span>{{ $t(`user_market_relatedDocu_tit`) }}</span></th>
                        <th scope="col"><span>{{ $t(`user_market_relatedDocu_docuName`) }}</span></th>
                        <th scope="col"><span>{{ $t(`user_market_relatedDocu_fileType`) }}</span></th>
                        <th scope="col"><span>{{ $t(`user_market_relatedDocu_download`) }}</span></th>
                        <th scope="col"><span>{{ $t(`user_market_relatedDocu_share`) }}</span></th>
                    </tr>
                </thead>
                <tbody>
                    <!-- no-data -->
                    <tr v-if="false">
                        <td colspan="5">
                            <!-- no-data(loading) -->
                            <div class="no-data" v-show="false">
                                <div class="loading-sm">
                                    <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                </div>
                                <p>
                                    데이터 로딩중입니다.
                                </p>
                            </div>
                            <!-- no-data -->
                            <div class="no-data" v-show="false">
                                <span class="material-icons">error_outline</span>
                                <span>데이터가 없습니다.</span>
                            </div>
                        </td>
                    </tr>
                    <tr v-for="(item, index) in list" :key="index">
                        <td>
                            <span v-text="item.docName"></span>
                        </td>
                        <td>
                            <span v-text="item.docType"></span>
                        </td>
                        <td>
                            <span v-text="item.fileType"></span>
                        </td>
                        <td>
                            <el-button type="icon-only" v-show="item.downYn">
                                <span class="material-icons">arrow_circle_down</span>
                            </el-button>
                        </td>
                        <td>
                            <el-button type="icon-only" v-show="item.sharingYn">
                                <span class="material-icons">share</span>
                            </el-button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
export default {
    components: {},
    computed: {},
    data() {
        return {
            list: [
                {
                    docName: "KCC EMC INTRODUCTION(Power Module FP)",
                    docType: "INTRODUCTION",
                    fileType: "Psd",
                    downYn: true,
                    sharingYn: true,
                },
                {
                    docName: "KCC EMC INTRODUCTION(Power Module FP)",
                    docType: "INTRODUCTION",
                    fileType: "Psd",
                    downYn: true,
                    sharingYn: true,
                },
            ],
        };
    },
};
</script>

<style></style>
