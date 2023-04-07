<template>
    <!-- 글로벌 네트워크 컴포넌트 -->
    <div class="global-network-component">
        <!-- 탭 버튼 -->
        <!-- <div class="anchor-tab-btn" v-if="subContentsVisible">
            <ul class="btn-list">
                <li v-for="(item, index) in tabBtnList" :key="index">
                    <a v-text="item.title" :class="item.active"></a>
                </li>
            </ul>
        </div> -->

        <!-- 글로벌 네트워크 맵 & 리스트 -->
        <div class="global-network-map-list">
            <div class="global-network-map-box">
                <!-- no-data(loading) -->
                <div class="no-data" v-show="false">
                    <div class="loading-sm">
                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                    </div>
                    <p>{{ $t("user_common_nowLoading") }}</p>
                </div>
                <!-- no-data -->
                <div class="no-data" v-show="false">
                    <span class="material-icons">error_outline</span>
                    <span>{{ $t("user_common_noData") }}</span>
                </div>
                <div class="map">
                    <!-- <img src="@/assets/images/contents/map/contents_map_01.png" alt="KCC 글로벌 네트워크 지도" class="img" /> -->
                    <ul class="pin-list">
                        <!-- 클릭된 li에 active 클래스 추가. -->
                        <li class="kor column-type" :class="{ active: selectedCountryNm === '대한민국' }" v-on:click="changeCountry('KOR', '대한민국')">
                            <p class="txt">{{ $t("user_common_korea") }}</p>
                            <span class="dot"></span>
                        </li>
                        <li class="jpn right-type" :class="{ active: selectedCountryNm === '일본' }" v-on:click="changeCountry('JPN', '일본')">
                            <p class="txt">{{ $t("user_common_japan") }}</p>
                            <span class="dot"></span>
                        </li>
                        <li class="chn left-type" :class="{ active: selectedCountryNm === '중국' }" v-on:click="changeCountry('CHN', '중국')">
                            <p class="txt">{{ $t("user_common_china") }}</p>
                            <span class="dot"></span>
                        </li>
                        <li class="vnm right-type" :class="{ active: selectedCountryNm === '베트남' }" v-on:click="changeCountry('VN', '베트남')">
                            <p class="txt">{{ $t("user_common_veitnam") }}</p>
                            <span class="dot"></span>
                        </li>
                        <li class="mys left-type" :class="{ active: selectedCountryNm === '말레이시아' }" v-on:click="changeCountry('MAS', '말레이시아')">
                            <p class="txt">{{ $t("user_common_malaysia") }}</p>
                            <span class="dot"></span>
                        </li>
                        <li class="sgp right-type" :class="{ active: selectedCountryNm === '싱가포르' }" v-on:click="changeCountry('SG', '싱가포르')">
                            <p class="txt">{{ $t("user_common_singapore") }}</p>
                            <span class="dot"></span>
                        </li>
                        <li class="idn right-type" :class="{ active: selectedCountryNm === '인도네시아' }" v-on:click="changeCountry('ID', '인도네시아')">
                            <p class="txt">{{ $t("user_common_indonesia") }}</p>
                            <span class="dot"></span>
                        </li>
                        <li class="ind left-type" :class="{ active: selectedCountryNm === '인도' }" v-on:click="changeCountry('IND', '인도')">
                            <p class="txt">{{ $t("user_common_india") }}</p>
                            <span class="dot"></span>
                        </li>
                        <li class="uae left-type" :class="{ active: selectedCountryNm === 'UAE' }" v-on:click="changeCountry('UAE', 'UAE')">
                            <p class="txt">{{ $t("user_common_uae") }}</p>
                            <span class="dot"></span>
                        </li>
                        <li class="tur left-type" :class="{ active: selectedCountryNm === '터키' }" v-on:click="changeCountry('TUK', '터키')">
                            <p class="txt">{{ $t("user_common_turkey") }}</p>
                            <span class="dot"></span>
                        </li>
                        <li class="ger right-type" :class="{ active: selectedCountryNm === '독일' }" v-on:click="changeCountry('GER', '독일')">
                            <p class="txt">{{ $t("user_common_germany") }}</p>
                            <span class="dot"></span>
                        </li>
                        <li class="uk column-type" :class="{ active: selectedCountryNm === '영국' }" v-on:click="changeCountry('UK', '영국')">
                            <p class="txt">{{ $t("user_common_uk") }}</p>
                            <span class="dot"></span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="global-network-list-group">
                <div class="tit" v-text="selectedCountryNmLan"></div>
                <!-- no-data(loading) -->
                <div class="no-data" v-show="false">
                    <div class="loading-sm">
                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                    </div>
                    <p>{{ $t("user_common_nowLoading") }}</p>
                </div>
                <!-- no-data -->
                <div class="no-data" v-show="false">
                    <span class="material-icons">error_outline</span>
                    <span>{{ $t("user_common_noData") }}</span>
                </div>
                <el-collapse v-model="globalNetworkCollapseActive" accordion>
                    <el-collapse-item :title="item.title" :name="item.index" v-for="(item, index) in selectedCountryList" :key="index">
                        <!-- scroll area -->
                        <div v-bar="{ preventParentScroll: true }" class="scroll-element">
                            <!-- el1 -->
                            <div>
                                <!-- el2 -->
                                <div class="list-item" v-for="(innerItem, index) in item.innerList" :key="index">
                                    <el-button size="xsmall" round v-text="innerItem.title" @click="changeMarker(innerItem)"></el-button>
                                    <div>
                                        <span class="txt" v-text="innerItem.production"></span>
                                    </div>
                                    <address class="address">
                                        <span class="txt" v-text="innerItem.address"></span>
                                        <span class="txt" v-text="innerItem.subAddress"></span>
                                    </address>
                                    <div>
                                        <span class="call" v-text="innerItem.call"></span>
                                    </div>
                                    <div>
                                        <span class="email" v-text="innerItem.email"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </el-collapse-item>
                </el-collapse>
            </div>
        </div>

        <!-- 지도 -->
        <div class="global-network-map-container">
            <div class="map">
                <!-- 샘플 이미지 대신 실제 지도 들어갑니다. -->
                <!-- <img src="@/assets/images/contents/map/contents_map_sample.png" alt="KCC 글로벌 네트워크 지도" class="img" style="max-width: 100%" /> -->
                <div class="no-data" v-if="!showGmap">
                    <div class="loading-sm">
                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                    </div>
                    <p>{{ $t("user_common_nowLoading") }}</p>
                </div>
                <GMap v-if="showGmap" ref="gMap" language="en" :center="{ lat: locations.lat, lng: locations.lng }" :zoom="14">
                    <GMapMarker :position="{ lat: locations.lat, lng: locations.lng }"></GMapMarker>
                </GMap>
            </div>
        </div>
    </div>
</template>

<script>
import Cookie from "js-cookie";

export default {
    props: {
        // subContentsVisible: {
        //     type: Boolean,
        //     required: true,
        //     default: false,
        // },
    },
    data() {
        return {
            // 탭 버튼
            tabBtnList: [
                {
                    title: "본사/생산기지",
                    active: "active",
                },
                {
                    title: "연구개발센터",
                    active: "",
                },
                {
                    title: "판매법인",
                    active: "",
                },
                {
                    title: "판매사무소",
                    active: "",
                },
            ],

            // 글로벌 네트워크 collapse
            globalNetworkCollapseActive: "1",

            //현재 선택된 국가의 이름
            selectedCountryNm: "대한민국",
            selectedCountryNmLan: "Republic of Korea",

            //현재 선택된 국가의 데이터
            selectedCountryList: [],

            globalNetworkItemList: [
                //KOR
                {
                    country: "KOR",
                    networkList: [
                        //본사
                        {
                            title: this.$t(`user_globalNetwork_Headquarters_tit`),
                            index: "1",
                            innerList: [
                                {
                                    title: this.$t(`user_globalNetwork_Headquarters_seoul`),
                                    address: this.$t(`user_globalNetwork_Headquarters_address`),
                                    call: this.$t(`user_globalNetwork_Headquarters_call`),
                                },
                            ],
                        },
                        //중앙연구소
                        {
                            title: this.$t(`user_globalNetwork_CentralResearch_tit`),
                            index: "2",
                            innerList: [
                                {
                                    title: this.$t(`user_globalNetwork_CentralResearch_subtit`),
                                    address: this.$t(`user_globalNetwork_CentralResearch_address`),
                                    call: this.$t(`user_globalNetwork_CentralResearch_call`),
                                },
                            ],
                        },
                        {
                            title: this.$t(`user_globalNetwork_Factory_tit`),
                            index: "3",
                            innerList: [
	                            {
                                    title: this.$t(`user_globalNetwork_Factory_Gapyeong`),
                                    production: this.$t(`user_globalNetwork_Factory_Gapyeong_production`),
                                    address: this.$t(`user_globalNetwork_Factory_Gapyeong_address`),
                                    call: this.$t(`user_globalNetwork_Factory_Gapyeong_call`),
                                },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_Gimcheon`),
		                            production : this.$t(`user_globalNetwork_Factory_Gimcheon_production`),
		                            address: this.$t(`user_globalNetwork_Factory_Gimcheon_address`),
		                            call: this.$t(`user_globalNetwork_Factory_Gimcheon_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_DaejukGypsumBoard`),
		                            production: this.$t(`user_globalNetwork_Factory_DaejukGypsumBoard_production`),
		                            address: this.$t(`user_globalNetwork_Factory_DaejukGypsumBoard_address`),
		                            call: this.$t(`user_globalNetwork_Factory_DaejukGypsumBoard_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_DaejukSilicone`),
		                            production: this.$t(`user_globalNetwork_Factory_DaejukSilicone_production`),
		                            address: this.$t(`user_globalNetwork_Factory_DaejukSilicone_address`),
		                            call: this.$t(`user_globalNetwork_Factory_DaejukSilicone_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_Munmak`),
		                            production: this.$t(`user_globalNetwork_Factory_Munmak_production`),
		                            address: this.$t(`user_globalNetwork_Factory_Munmak_address`),
		                            call: this.$t(`user_globalNetwork_Factory_Munmak_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_Sejong`),
		                            production: this.$t(`user_globalNetwork_Factory_Sejong_production`),
		                            address: this.$t(`user_globalNetwork_Factory_Sejong_address`),
		                            call: this.$t(`user_globalNetwork_Factory_Sejong_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_Anseong`),
		                            production: this.$t(`user_globalNetwork_Factory_Anseong_production`),
		                            address: this.$t(`user_globalNetwork_Factory_Anseong_address`),
		                            call: this.$t(`user_globalNetwork_Factory_Anseong_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_Asan`),
		                            production: this.$t(`user_globalNetwork_Factory_Asan_production`),
		                            address: this.$t(`user_globalNetwork_Factory_Asan_address`),
		                            call: this.$t(`user_globalNetwork_Factory_Asan_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_Yeocheon`),
		                            production: this.$t(`user_globalNetwork_Factory_Yeocheon_production`),
		                            address: this.$t(`user_globalNetwork_Factory_Yeocheon_address`),
		                            call: this.$t(`user_globalNetwork_Factory_Yeocheon_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_Yeoju`),
		                            production: this.$t(`user_globalNetwork_Factory_Yeoju_production`),
		                            address: this.$t(`user_globalNetwork_Factory_Yeoju_address`),
		                            call: this.$t(`user_globalNetwork_Factory_Yeoju_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_Yeongwol`),
		                            production: this.$t(`user_globalNetwork_Factory_Yeongwol_production`),
		                            address: this.$t(`user_globalNetwork_Factory_Yeongwol_address`),
		                            call: this.$t(`user_globalNetwork_Factory_Yeongwol_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_Ulsan`),
		                            production: this.$t(`user_globalNetwork_Factory_Ulsan_production`),
		                            address: this.$t(`user_globalNetwork_Factory_Ulsan_address`),
		                            call: this.$t(`user_globalNetwork_Factory_Ulsan_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_JeonjuBuilding`),
		                            production: this.$t(`user_globalNetwork_Factory_JeonjuBuilding_production`),
		                            address: this.$t(`user_globalNetwork_Factory_JeonjuBuilding_address`),
		                            call: this.$t(`user_globalNetwork_Factory_JeonjuBuilding_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_JeonjuPainting`),
		                            production: this.$t(`user_globalNetwork_Factory_JeonjuPainting_production`),
		                            address: this.$t(`user_globalNetwork_Factory_JeonjuPainting_address`),
		                            call: this.$t(`user_globalNetwork_Factory_JeonjuPainting_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_Factory_JeonjuSilicone`),
		                            production: this.$t(`user_globalNetwork_Factory_JeonjuSilicone_production`),
		                            address: this.$t(`user_globalNetwork_Factory_JeonjuSilicone_address`),
		                            call: this.$t(`user_globalNetwork_Factory_JeonjuSilicone_call`),
	                            },

                            ],
                        },
                        //영업소
                        {
                            title: this.$t(`user_globalNetwork_businessOffice_tit`),
                            index: "4",
                            innerList: [
                                {
                                    title: this.$t(`user_globalNetwork_businessOffice_Seongnam`),
                                    address: this.$t(`user_globalNetwork_businessOffice_Seongnam_address`),
                                    call: this.$t(`user_globalNetwork_businessOffice_Seongnam_call`),
                                },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_GyeonggiNorth`),
		                            address: this.$t(`user_globalNetwork_businessOffice_GyeonggiNorth_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_GyeonggiNorth_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Namyangju`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Namyangju_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Namyangju_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Suwon`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Suwon_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Suwon_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Ansan`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Ansan_address`),
		                            subAddress: this.$t(`user_globalNetwork_businessOffice_Ansan_subAddress`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Ansan_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Hwaseong`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Hwaseong_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Hwaseong_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Wonju`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Wonju_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Wonju_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Gangneung`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Gangneung`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Gangneung_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Daejeon`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Daejeon_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Daejeon_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Cheongju`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Cheongju_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Cheongju_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Cheonan`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Cheonan_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Cheonan_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Dangjin`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Dangjin_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Dangjin_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Jeonju`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Jeonju_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Jeonju_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Gwangju`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Gwangju_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Gwangju_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Mokpo`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Mokpo_address`),
		                            subAddress: this.$t(`user_globalNetwork_businessOffice_Mokpo_subAddress`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Mokpo_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Suncheon`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Suncheon_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Suncheon_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Daegu`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Daegu_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Daegu_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Gumi`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Gumi_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Gumi_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Pohang`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Pohang_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Pohang_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Ulsan`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Ulsan_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Ulsan_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Busan`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Busan_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Busan_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Changwon`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Changwon_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Changwon_call`),
	                            },
	                            {
		                            title: this.$t(`user_globalNetwork_businessOffice_Jeju`),
		                            address: this.$t(`user_globalNetwork_businessOffice_Jeju_address`),
		                            call: this.$t(`user_globalNetwork_businessOffice_Jeju_call`),
	                            },
                            ],
                        },
                    ],
                }, //END KOR

                //JPN
                {
                    country: "JPN",
                    networkList: [
                        {
                            title: "Overseas Subsidiaries",
                            index: "1",
                            innerList: [
                                {
                                    title: "KCC Japan Co., Ltd.",
                                    address:
                                        "Yurakucho Denki Bldg. South wing 12F, 1-7-1, Yurakucho, Chiyoda-ku,<br>Tokyo, Japan (Zip code : 100-0006)",
                                    call: "(81)-3-6895-9361~3",
                                },
                            ],
                        },
                        {
                            title: "Overseas Research Center",
                            index: "2",
                            innerList: [
                                {
                                    title: "KCC Japan Research Center.",
                                    address:
                                        "Yokohama Joint Research Center, Experiment Building A, 1-1-40,uehiro-Cho, Tsurumi-Ku, Yokohama-Shi, Kanagawa, Japan,(zip code : 230-0045)",
                                    call: "(81)-45-834-9172-3",
                                },
                            ],
                        },
                    ],
                },

                //CHN
                {
                    country: "CHN",
                    networkList: [
                        //상하이
                        {
                            title: this.$t(`globalNetwork_china_Shanghai`),
                            index: "1",
                            innerList: [
                                {
                                    title: "KCC (KUNSHAN) Co., Ltd. Plants 1",
                                    address: "No.1388 Huangpujiang Rd, Qiandeng Town ,",
                                    subAddress: "Kunshan City, Jiangsu, China (Zip code : 215341)",
                                    call: "(86)-512-5746-9000",
                                    email: "kcckunshan@kccworld.co.kr",
                                },
                                {
                                    title: "KCC (KUNSHAN) Co., Ltd. Plants 2",
                                    address: "No.198 Shanpu East Rd, Qiandeng Town, ",
                                    subAddress: "Kunshan City, Jiangsu, China (Zip code: 215341)",
                                    call: "(86)-512-5017-2006",
                                    email: "kcckunshan@kccworld.co.kr",
                                },
                                {
                                    title: "Shanghai Branch Office of KCC (KUNSHAN) Co., Ltd.",
                                    address: "11F, Shanghai HNA Building, No.898 Puming Road, Shanghai, China",
                                    subAddress: "(Zip code : 200120)",
                                    call: "(86)-21-6235-1540",
                                    email: "kccshanghai@kccworld.co.kr",
                                },
                                {
                                    title: "Qingdao Branch Office of KCC (KUNSHAN) Co., Ltd.",
                                    address: "Room 1506, Ban Dao International Bldg, No.182-8, Haier Rd. LaoShan ",
                                    subAddress: "District, Qingdao, China (Zip : 266100)",
                                    call: "(86)-532-5569-0655",
                                    email: "kccqingdao@kccworld.co.kr",
                                },
                                {
                                    title: "Yancheng Branch Office of KCC (KUNSHAN) Co., Ltd.",
                                    address: "Room 1207, 2 Building, Huabang International East Mansion, Tinghu ",
                                    subAddress: "District, Yancheng City, Jiangsu, China (zip : 224000)",
                                    call: "(86)-515-8990-9768",
                                    email: "kccshanghai@kccworld.co.kr",
                                },
                                {
                                    title: "Hefei Branch Office of KCC (KUNSHAN) Co., Ltd.",
                                    address: "Room1307, 11 Building, Paramount famous square, NO.588 Lianhua ",
                                    subAddress: "Road Economic development District, Hefei City Anhui, China (Zip code : 230000)",
                                    call: "(86)-551-6261-8116",
                                    email: "kcchefei@kccworld.co.kr",
                                },
                            ],
                        },
                        //베이징
                        {
                            title: this.$t(`globalNetwork_china_Beijing`),
                            index: "2",
                            innerList: [
                                {
                                    title: "KCC (BEIJING) Co., Ltd.",
                                    address: "No. 51, West of Shuntong Road, Renhe-Town, ",
                                    subAddress: "Shunyi-District, Beijing, China (Zip code : 101300)",
                                    call: "(86)-10-8949-8181 (102)",
                                    email: "kccbeijing@kccworld.co.kr",
                                },
                                {
                                    title: "Dalian Branch Office of KCC (BEIJING) Co., Ltd.",
                                    address: "Room 703, International Business Building, Bonded Zones, Dalian City, ",
                                    subAddress: "LiaoNing Province, China (zip : 116600)",
                                    call: "(86)-411-3924-2272",
                                    email: "kccbeijing@kccworld.co.kr",
                                },
                            ],
                        },
                        //광저우
                        {
                            title: this.$t(`globalNetwork_china_Guangzhou`),
                            index: "3",
                            innerList: [
                                {
                                    title: "KCC (GUANGZHOU) Co., Ltd.",
                                    address: "No.9 Doutang street, Yonghe Economic Zone, Guangzhou, P.R. China (zip code : 511356)",
                                    subAddress: "",
                                    call: "(86)-20-3222-1111",
                                    email: "kccguangzhou@kccworld.co.kr",
                                },
                                {
                                    title: "Guangzhou Branch Office of KCC (GUANGZHOU) Co., Ltd.",
                                    address:
                                        "ZhongTai International square, NO.161 Linhe West Road, Guangzhou, China (Zip code : 510613)",
                                    subAddress: "",
                                    call: "(86)-20-3831-9780",
                                    email: "kccguangzhou@kccworld.co.kr",
                                },
                            ],
                        },
                        //충칭
                        {
                            title: this.$t(`globalNetwork_china_Chongqing`),
                            index: "4",
                            innerList: [
                                {
                                    title: "KCC (Chongqing) Co., Ltd.",
                                    address: "No.26 Huanan Road, Yanjia Economic Development, Changshou District, Chongqing City, China",
                                    subAddress: "(Zip code : 401220)",
                                    call: "Tel (86) 23–4076–6611, Fax (86) 23–4076–6708",
                                    email: "kccguangzhou@kccworld.co.kr",
                                },
                                {
                                    title: "Chongqing Branch Office of KCC (Chongqing) Co., Ltd.",
                                    address: "Room 1106, Block C, Dongli International Building, No.62",
                                    subAddress: "East Taishan Road, Northern New District, Chongqing City, China (Zip code : 401122)",
                                    call: "Tel (86) 23–6701–9021, Fax (86) 23–6785–3414",
                                    email: "kccchongqing@kccworld.co.kr",
                                },
                                {
                                    title: "Chengdu Branch Office of KCC (Chongqing) Co., Ltd.",
                                    address: "No.15 Xiu Yuan East Road, Chenghua District, Chengdu",
                                    subAddress: "City, Sichuan Province, China (Zip code : 610000)",
                                    call: "Tel (86) 155–2806–5199",
                                    email: "kccchengdu@kccworld.co.kr",
                                },
                            ],
                        },
                    ],
                },
                //VIT
                {
                    country: "VN",
                    networkList: [
                        //Overseas Subsidiaries
                        {
                            title: "Overseas Subsidiaries",
                            index: "1",
                            innerList: [
                                {
                                    title: "KCV KCC(Vietnam) Co., Ltd.",
                                    address: "Road 1, Long Thanh Industrial Zone, Long Thanh",
                                    subAddress: "District, Dong Nai Province, Vietnam",
                                    call: "(84)-61-351-4678",
                                    email: "vietnam@kccworld.co.kr",
                                },
                                {
                                    title: "KCC (HA NOI) Co., Ltd.",
                                    address: "Yen Phong Industrial Zone, Dong Phong Ward,Yen Phong Dist, Bac Ninh",
                                    subAddress: "Province,Vietnam",
                                    call: "(84)-241-3699-388",
                                    email: "crekim@kccworld.co.kr",
                                },
                                {
                                    title: "KCC (VIETNAM NHON TRACH) Co., Ltd.",
                                    address: "Nhon Trach 06 IZ, Long Tho commune, Nhon Trach district, Dong Nai",
                                    subAddress: " province, Vietnam",
                                    call: "(84)-2513-683-585",
                                    email: "kccvn@kccworld.co.kr",
                                },
                            ],
                        },
                        //Overseas Branch Offices
                        {
                            title: "Overseas Branch Offices",
                            index: "2",
                            innerList: [
                                {
                                    title: "KCV Hochimin Office.",
                                    address: "Room 4, 12th Floor, Pearl Plaza, 561 Dien Bien Phu Str., Ward 25, Binh",
                                    subAddress: "Thanh Dist., HCMC, Vietnam",
                                    call: "(84)-8-6269-0305",
                                    email: "cjh9871@kccworld.co.kr",
                                },
                                {
                                    title: "KCC (Hanoi) Hanoi Office.",
                                    address: "VINACONEX 9 TOWER AT PHAM HUNG ROAD, ME TRI - TU LIEM-HA NOI (10 floor)",
                                    call: "(84)-4-3787-8647",
                                    email: "crekim@kccworld.co.kr",
                                },
                            ],
                        },
                    ],
                },
                //싱가포르
                {
                    country: "SG",
                    networkList: [
                        {
                            title: "Overseas Subsidiaries",
                            index: "1",
                            innerList: [
                                {
                                    title: "KCS KCC(Singapore) Pte. Ltd.",
                                    address: "31 Tuas South Link4, Level 2M PLG Building,",
                                    subAddress: "Singapore 636834",
                                    call: "(65)-6862-0100",
                                    email: "ssong@kccpaint.com.sg",
                                },
                            ],
                        },
                    ],
                },
                //인도네시아
                {
                    country: "ID",
                    networkList: [
                        {
                            title: "Overseas Subsidiaries",
                            index: "1",
                            innerList: [
                                {
                                    title: "KCID PT. KCC Indonesia.",
                                    address: "Belleza Permata Hijau Office Tower, Lantai 17 Unit 9 Jl Letjen Soepeno 34,",
                                    subAddress: "Permata Hijau, Jakarta, Indonesia (Zip code : 12210)",
                                    call: "(62)21-3002-7185~6",
                                    email: "sanghyuk@kccworld.co.kr",
                                },
                            ],
                        },
                    ],
                },
                //말레이시아
                {
                    country: "MAS",
                    networkList: [
                        {
                            title: "Overseas Subsidiaries",
                            index: "1",
                            innerList: [
                                {
                                    title: "KCC Paints Sdn. Bhd.",
                                    address: "Lot13/No.1, Jalan Anggerik Mokara 31/54~55, Seksyen 31, Kota Kemuning, Shah Alam, Selangor,",
                                    subAddress: "Darul Ehsan, Malaysia (Zip code : 40460)",
                                    call: "(603)-5122-2900",
                                    email: "kcc@kccpaint.com.my",
                                },
                            ],
                        },
                    ],
                },
                //인도
                {
                    country: "IND",
                    networkList: [
                        {
                            title: "Overseas Subsidiaries",
                            index: "1",
                            innerList: [
                                {
                                    title: "KCC Paint (India) Pvt. Ltd.",
                                    address: "Plot No K-20 Sipcot Industrial Park, Phase II, Mambakkam Sriperumbudur Tamil Nadu, India ",
                                    subAddress: "(Zip code : 602106)",
                                    call: "(91)-44-2714-2000",
                                    email: "sales@kccindia.in",
                                },
                            ],
                        },
                        {
                            title: "Overseas Branch Offices",
                            index: "2",
                            innerList: [
                                {
                                    title: "Pune Branch Office of KCC Paint (India) Pvt. Ltd.",
                                    address: "Plot No. C-22, MIDC Industrial Area Phase-Ⅰ, Mahalunge Chakan Tal-Khed, ",
                                    subAddress: "Dist-Pune, Maharashtra, India (Zip code : 410501)",
                                    call: "(91)-22-3069-8254~5",
                                    email: "sales@kccindia.in",
                                },
                            ],
                        },
                    ],
                },
                //두바이
                {
                    country: "UAE",
                    networkList: [
                        {
                            title: "Overseas Subsidiaries",
                            index: "1",
                            innerList: [
                                {
                                    title: "KCC Dubai Office.",
                                    address: "Unit No. 501, Indigo Icon Tower, Cluster - F, Jumeirah Lakes Towers,",
                                    subAddress: "Dubai, U.A.E. (P.O. Box 184453)",
                                    call: "(971)-4453-4115~6",
                                    email: "dubai@kccworld.co.kr",
                                },
                            ],
                        },
                    ],
                },
                //터키
                {
                    country: "TUK",
                    networkList: [
                        {
                            title: "Overseas Subsidiaries",
                            index: "1",
                            innerList: [
                                {
                                    title: "KCT KCC Boya Sanayi Ve Ticaret Ltd. Sti.",
                                    address: "Gebze Organize Sanayi Bolgesi(Tembelova mevki) 3100 Cad.",
                                    subAddress: "No:3107, Gebze / Kocaeli / Turkey (Zip code : 41400)",
                                    call: "(90)-262-645-0200",
                                    email: "turkey@kccworld.co.kr",
                                },
                            ],
                        },
                    ],
                },
                //독일
                {
                    country: "GER",
                    networkList: [
                        {
                            title: "Overseas Subsidiaries",
                            index: "1",
                            innerList: [
                                {
                                    title: "KCC Europe GmbH.",
                                    address: "Hansaring 61, 50670, Köln/Cologne, Germany",
                                    subAddress: "(Zip code : 50670)",
                                    call: "(49)-221-6694270",
                                    email: "contact@kccworld.eu",
                                },
                            ],
                        },
                    ],
                },
                //영국
                {
                    country: "UK",
                    networkList: [
                        {
                            title: "Overseas Subsidiaries",
                            index: "1",
                            innerList: [
                                {
                                    title: "KCC Basildon Chemical Co., Ltd",
                                    address: "7 Kimber Road, Abingdon, Oxon OX14 1RZ, England",
                                    subAddress: "(Zip code : OX14)",
                                    call: "(44)-1235-205003",
                                    email: "hukim@kccworld.co.kr",
                                },
                            ],
                        },
                    ],
                },
            ], //END globalNetworkItemList


            //google Map Settings
            showGmap: false,
            locations : {
                lat : 0,
                lng : 0
            },
        }; //END DATA RETURN
    }, //END default
    async fetch () {
        this.selectedCountryList = this.globalNetworkItemList[0].networkList;

        // 구글 maps marker 세팅
        await this.locateMarker();
        await this.setCountryNameLang();
    },

	mounted() {
	},
	computed : {
    	mapLan(){
		    // console.log("lang : ", $nuxt.$i18n.localeProperties.code );
		    return $nuxt.$i18n.localeProperties.code;
	    }
	},
    watch : {
        'locations'(check) {
            // console.log('클릭됨');
        },

    },
    methods: {
        //선택된 국가의 데이터를 가져온다.
        changeCountry(country, countryNm) {
            let vm = this;

            //init
            vm.selectedCountryList = [];

            //데이터에서 선택된 데이터를 가져온다. globalNetworkItemList
            let selectValue = vm.globalNetworkItemList
                .filter(el => {
                    return el.country === country;
                })
                .map(el => el.networkList);
            selectValue[0].forEach(item => vm.selectedCountryList.push(item));

            //나라이름 설정
            vm.selectedCountryNm = countryNm;

            // 지도 marker 변경
            let target = selectValue[0][0].innerList[0];
            vm.changeMarker( target );

            vm.setCountryNameLang();
        },

        // 구글 maps marker 초기화 ( 전체List > depth0 > depth0 > depth0 => 즉 한국 KCC 본사 )
        locateMarker() {
            let _this = this;
            let initTarget = _this.globalNetworkItemList[0].networkList[0].innerList[0];
            _this.changeMarker( initTarget );
        },

        async changeMarker( target ) { // 지도에서 선택한 국가의 depth0 로
            // address 생성
            let thisAddr = target.address;
            if( target.hasOwnProperty('subAddress')) {
                thisAddr = target.address.toString() + target.subAddress.toString();
            }

            // request Google Maps geocode API
            let _this = this;
            const key = this.$amConstant.GMAP_DATA.KEY;
            const url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + thisAddr + "&key=" + key

		        _this.showGmap = false;

            await _this.$axios.post(url).then((res) => {
                //success
                if( 200 === res.status ){
                    let latVal = parseFloat(res.data.results[0].geometry.location.lat);
                    let lngVal = parseFloat(res.data.results[0].geometry.location.lng);

                    _this.locations = {
                        lat : latVal,
                        lng : lngVal
                    }
                    // console.log('axios 메소드 내부 ' + _this.locations.lat + ' / ' + _this.locations.lng);

	                _this.showGmap = true;

                }
            }).catch((error)=> { console.log(error); });
        },

	    setCountryNameLang(){
        	switch ( this.selectedCountryNm ){
				case '대한민국': this.selectedCountryNmLan = this.$t(`user_common_korea`); break;
				case '일본': this.selectedCountryNmLan = this.$t(`user_common_japan`); break;
				case '중국': this.selectedCountryNmLan = this.$t(`user_common_china`); break;
				case '베트남': this.selectedCountryNmLan = this.$t(`user_common_veitnam`); break;
				case '말레이시아': this.selectedCountryNmLan = this.$t(`user_common_malaysia`); break;
				case '싱가포르': this.selectedCountryNmLan = this.$t(`user_common_singapore`); break;
				case '인도네시아': this.selectedCountryNmLan = this.$t(`user_common_indonesia`); break;
				case '인도': this.selectedCountryNmLan = this.$t(`user_common_india`); break;
				case 'UAE': this.selectedCountryNmLan = this.$t(`user_common_uae`); break;
				case '터키': this.selectedCountryNmLan = this.$t(`user_common_turkey`); break;
				case '독일': this.selectedCountryNmLan = this.$t(`user_common_germany`); break;
				case '영국': this.selectedCountryNmLan = this.$t(`user_common_uk`); break;
	        }
	    },
    },
};
</script>
<style lang="scss"></style>
