<template>
    <div class="inner-wrapper">
        <!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--            <the-loading />-->
        <!--        </div>-->

        <!-- inner-container sub -->
        <div class="inner-container sub">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header customer-support">
                <div class="inner">
                    <div class="tit-area">
                        <h2 class="tit">{{ $t("user_customerSupportContact_header_tit") }}</h2>
                        <!-- <p class="sub-txt">KCC소개 상세내용입니다.</p> -->
                    </div>
                </div>
            </div>

            <!-- content-body -->
            <div class="content-body">
                <!-- 메뉴 경로 -->
                <the-breadcrumb />

                <!-- sub-content -->
                <div class="sub-content">
                    <!-- 내용은 여기에. -->
                    <!-- inner-content -->
                    <div class="inner-content">
                        <div class="default-w">
                            <!-- 앵커 탭 버튼 -->
                            <div class="anchor-tab-btn alt">
                                <ul class="btn-list">
                                    <li v-for="(data, index) in branchNameHead" :key="index">
                                        <a
                                            :class="{ active: selected === index }"
                                            @click="[goContent(data.contentId), (selected = index)]"
                                            v-text="data.areaName"
                                        ></a>
                                    </li>
                                    <!-- <li>
                                        <a :class="{ active: selected === 'a' }" @click="[goContent('korea-content'), (selected = 'a')]">대한민국</a>
                                    </li>
                                    <li>
                                        <a :class="{ active: selected === 'b' }" @click="[goContent('usa-content'), (selected = 'b')]">미국</a>
                                    </li>
                                    <li>
                                        <a :class="{ active: selected === 'c' }" @click="[goContent('eu-content'), (selected = 'c')]">유럽</a>
                                    </li>
                                    <li>
                                        <a :class="{ active: selected === 'd' }" @click="[goContent('asia-content'), (selected = 'd')]">아시아</a>
                                    </li> -->
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- inner-content -->
                    <div class="inner-content contact-content">
                        <div class="default-w division-area">
                            <!-- division-item :: 국가/지역 리스트 -->
                            <div class="division-item">
                                <!-- scroll area -->
                                <div v-bar="{ preventParentScroll: true }" id="scroll-element" class="scroll-element">
                                    <!-- el1 -->
                                    <div>
                                        <!-- el2 -->
                                        <!-- 대한민국 -->
                                        <div :id="data.contentId" class="column-content" v-for="(data, index) in branchGroupedList" :key="index">
                                            <div class="tit-area">
                                                <h3 class="tit" v-text="data.areaName"><!-- 대한민국 --></h3>
                                            </div>
                                            <div class="info-area">
                                                <ul class="bottom-line-list">
                                                    <li v-for="(branch, index) in data.branchList" :key="index">
                                                        <div class="detail">
                                                            <div class="tit-area">
                                                                <h4 class="tit fw500" v-text="branch.name">
                                                                    <!-- 본사 -->
                                                                </h4>
                                                            </div>
                                                            <address class="address">
                                                                <p v-html="branch.addr">
                                                                    <!-- 서울 특별시 서초구 사평대로 344(서초동 1301_4) -->
                                                                </p>
                                                                <p v-text="branch.tel">
                                                                    <!-- + 82 - 02 - 3480 - 5000 -->
                                                                </p>
                                                            </address>
                                                        </div>
                                                        <!-- 버튼 -->
                                                        <div class="btn-wrap bottom right">
                                                            <button
                                                                type="button"
                                                                class="btn-circle-special"
                                                                @click="getManagerListByBranchOid(branch.oid)"
                                                            >
                                                                <span class="txt-box">{{ $t("user_customerSupportContact_manager_tit") }}</span>
                                                                <span class="material-icons">east</span>
                                                            </button>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- division-item :: contact manager 리스트 -->
                            <div class="division-item box box-gray">
                                <div class="tit-area">
                                    <h4 class="tit">{{ $t("user_customerSupportContact_manager_tit") }}</h4>
                                </div>
                                <!-- scroll area -->
                                <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                                    <!-- el1 -->
                                    <div>
                                        <!-- el2 -->
                                        <div class="bottom-line-list">
                                            <div class="no-data" v-if="$common.isEmpty(managerListInit)">
                                                <i class="material-icons">error_outline</i>
                                                <p>{{ $t(`user_common_noData`) }}</p>
                                            </div>
                                            <!-- list-item -->
                                            <div class="list-item" v-for="(item, index) in managerListInit" :key="index">
                                                <div class="tit-area">
                                                    <span class="tit label label-sm label-black" v-text="item.name"><!-- 최원준 --></span>
                                                    <span class="sub-txt sm" v-text="item.department"><!-- Sales Team --></span>
                                                </div>
                                                <ul class="info-list">
                                                    <li>
                                                        <span class="txt main-color">{{ $t("user_customerSupportContact_manager_tel") }}</span>
                                                        <span class="txt" v-text="item.tel"><!-- 02.1111.2222 --></span>
                                                    </li>
                                                    <li>
                                                        <span class="txt main-color">{{ $t("user_customerSupportContact_manager_email") }}</span>
                                                        <span class="txt" v-text="item.email"><!-- kccworld@kcc.com --></span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import theBreadcrumb from "~/components/kccam/user/breadcrumb/TheBreadcrumb.vue";

export default {
    head: {
        title: "KCC AM - Contact",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "고객지원 Contact 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - Contact" },
            { name: "twitter:title", content: "KCC AM - Contact" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
    },
    data() {
        return {
            // anchor select
            selected: "a",

            // Branch 전체정보 (branchInfo)
            branchListAll: [],

            // 브랜치 이름 목록
            branchNameHead: [],

            // 국가별 그룹된 목록
            branchGroupedList: [],

            // 관리자 전체 목록
            contactManagerList: [],

            // 관리자 목록 초기화 (한국)
            managerListInit: [],
        };
    },
    async fetch() {
        if (this.$common.isEmpty(this.$route.query.depth)) {
            return;
        }

        // Branch 전체정보 ( 전체 정보 (branchInfo))
        this.branchListAll = await this.getAllBranchInfo();

        // Branch 이름 목록 ( 각 국 (3) 별 areaName, areaCode, branchList )
        this.branchNameHead = await this.getAllBranchHead();

        // 국가별 그룹된 목록
        this.branchGroupedList = await this.groupingByAreaCode();

        // 관리자 목록
        this.contactManagerList = await this.getContactManagerList();

        // 초기화 목록 (한국으로)
        this.managerListInit = await this.getContactManagerInit();
    },

    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },
    methods: {
        // 앵커 메뉴 클릭 시 각 영역으로 스크롤
        goContent(area) {
            let domId = "#" + area;

            let scrollToElement = document.querySelector(domId);

            const scrollOptions = {
                elementToScroll: document.querySelector("#scroll-element > div"), // overflow scroll 되는 위치
            };

            animateScrollTo(scrollToElement, scrollOptions);
        },

        // Branch 전체정보 가져오기
        async getAllBranchInfo() {
            // make request url
            const getBranchListAll = this.$urlConstant.API_URL_PREFIX.BRANCH + this.$urlConstant.API_URL_SUFFIX.BRANCH.LIST_ALL;

            let reqParam = {
                fillUser: true,
            };

            return await this.$axios.post(getBranchListAll, reqParam).then(res => {
                let _self = this;
                if (200 === res.status && this.$common.isNotEmpty(res.data)) {
                    return res.data;
                }
            });
        },

        // 각국별 areaName, areaCode, branchList
        async getAllBranchHead() {
            let areaHeadArr = [];

            // make request url
            const getBranchListAll = this.$urlConstant.API_URL_PREFIX.BRANCH + this.$urlConstant.API_URL_SUFFIX.BRANCH.LIST_ALL;

            let reqParam = {
                fillUser: true,
            };

            return await this.$axios.post(getBranchListAll, reqParam).then(res => {
                let result = res.data;
                let _self = this;

                if (200 === res.status && this.$common.isNotEmpty(res.data)) {
                    // 빈 문자열 indexOf 를 중복값 검사에 활용
                    let nameVal = "";

                    // areaHeadArr 에 국가명 넣어주기
                    _.each(result, function(data, index) {
                        // amConstant.js 의 CONTACT_AREA_MAP 의 국가명을 가져와서 서로 다른 국가명만 반복 삽입
                        let areaName = _self.$amConstant.CONTACT_AREA_MAP[data.areaCode];

                        if (nameVal.indexOf(data.areaCode) === -1) {
                            let areaStr = data.areaCode;
                            let contentId = areaStr.substr(areaStr.length - 2, areaStr.length).toLowerCase();

                            areaHeadArr.push({
                                contentId: contentId,
                                areaName: areaName,
                                areaCode: data.areaCode,
                                branchList: [],
                            });

                            // 유효성 검사할 String 값에 areaCode 추가
                            nameVal += data.areaCode;
                        }
                    });
                    //console.log( areaHeadArr );
                    return areaHeadArr;
                }
            });
        },

        // areaCode 로 국가별 리스트 나누기
        async groupingByAreaCode() {
            const _self = this;

            // 모든 리스트들에 대해서 반복문
            _.each(this.branchListAll, function(data, index) {
                // 각 국에 해당하는 areaCode 가 일치할 경우 해당 국가 branchList 에 넣는다.
                // 한국.branchList 안에는 한국 브랜치 리스트 넣고.. (반복)

                _.each(_self.branchNameHead, function(branch, index) {
                    if (data.areaCode === branch.areaCode) {
                        branch.branchList.push({
                            oid: data.oid,
                            name: data.name,
                            postCode: data.postCode,
                            tel: data.tel,
                            addr: data.addr,
                        });
                    }
                });
            });
            //console.log(listSeparated);
            return this.branchNameHead;
        },

        // 관리자 전체 목록 가져오기
        async getContactManagerList() {
            let listAll = this.branchListAll;
            let tempList = [];

            _.each(listAll, function(data, index) {
                if (null == data.branchUserList) {
                    return;
                }
                tempList.push(data.branchUserList);
            });
            //console.log(tempList);
            return tempList;
        },

        // 관리자 목록 초기화 (한국으로)
        async getContactManagerInit() {
            return this.contactManagerList[0];
        },

        // 클릭한 브랜치로 관리자 목록 렌더링
        getManagerListByBranchOid(branchOid) {
            const _self = this;

            _.each(this.branchListAll, function(branch, index) {
                if (branchOid === branch.oid) {
                    _self.managerListInit = branch.branchUserList;
                    return;
                }
            });
        },
    },
};
</script>

<style></style>
