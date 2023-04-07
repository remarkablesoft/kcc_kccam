<template>
    <div class="inner-wrapper">
        <!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
        <!--            <the-loading />-->
        <!--        </div>-->
        <!-- inner-container sub -->
        <div class="inner-container sub">
            <!-- content-header :: 클래스로 배경 변경. -->
            <div class="content-header product-support">
                <div class="inner">
                    <div class="tit-area">
                        <h2 class="tit">{{ $t(`integratedSearch_tecSupport_tit`) }}</h2>
                        <!-- <p class="sub-txt">KCC소개 상세내용입니다.</p> -->
                    </div>
                </div>
            </div>
            <!-- content-body -->
            <div class="content-body">
                <!-- 메뉴 경로 -->
                <the-breadcrumb />
                <!-- sub-content -->
                <div class="sub-content search">
                    <!-- 내용은 여기에. -->
                    <!-- inner-content :: 검색창 / 앵커 탭 -->
                    <div class="inner-content">
                        <div class="default-w">
                            <!-- 검색 영역 -->
                            <div class="sub-center-search-area">
                                <!-- 검색창 -->
                                <div class="search-box sub">
                                    <el-button type="icon-only" class="btn-search">
                                        <span class="icon custom-icon-search"></span>
                                    </el-button>
                                    <el-input
                                        :placeholder="$t(`integratedSearch_tecSupport_placeHolder`)"
                                        v-model="searchInput"
                                        clearable
                                        @keypress.enter.native="searchList"
                                    ></el-input>
                                </div>
                            </div>
                            <p class="search-desc" v-if="$common.isEmpty(lastSearchInput)">{{ $t(`integratedSearch_inputSearch`) }}</p>
                            <!-- 앵커 탭 버튼 -->
                            <div class="anchor-tab-btn alt" v-if="isDisplayListArea">
                                <ul class="btn-list">
                                    <li>
                                        <a :class="{ active: selected === 'a' }" @click="[goContent('sfaTypeADocList-content'), (selected = 'a')]"
                                            >{{ $t(`integratedSearch_tecSupport_commonDoc`) }}
                                        </a>
                                    </li>
                                    <li>
                                        <a :class="{ active: selected === 'b' }" @click="[goContent('sfaTypeBDocList-content'), (selected = 'b')]"
                                            >{{ $t(`integratedSearch_tecSupport_introductionDoc`) }}
                                        </a>
                                    </li>
                                    <li>
                                        <a :class="{ active: selected === 'c' }" @click="[goContent('sfaTypeCDocList-content'), (selected = 'c')]"
                                            >{{ $t(`integratedSearch_tecSupport_TQDoc`) }}
                                        </a>
                                    </li>
                                    <li>
                                        <a :class="{ active: selected === 'd' }" @click="[goContent('sfaTypeDDocList-content'), (selected = 'd')]"
                                            >{{ $t(`integratedSearch_tecSupport_crDoc`) }}
                                        </a>
                                    </li>
                                    <li>
                                        <a :class="{ active: selected === 'e' }" @click="[goContent('sfaTypeEDocList-content'), (selected = 'e')]"
                                            >{{ $t(`integratedSearch_tecSupport_ptDoc`) }}
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- inner-content ::  DOC_TYPE 'A' : 회사 공통 문서 -->
                    <div id="sfaTypeADocList-content" class="inner-content" v-if="isDisplayListArea">
                        <div class="default-w">
                            <div class="tit-area is-border">
                                <h3 class="tit">{{ $t(`integratedSearch_tecSupport_commonDoc`) }}</h3>
                            </div>
                            <div class="content-wrap">
                                <!-- no-data(loading) -->
                                <div class="no-data" v-if="false">
                                    <div class="loading-sm">
                                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                    </div>
                                    <p>{{ $t(`user_common_nowLoading`) }}</p>
                                </div>
                                <!-- no-data -->
                                <div class="no-data" v-show="false">
                                    <span class="material-icons">error_outline</span>
                                    <span>{{ $t(`user_common_noData`) }}</span>
                                </div>
                                <!-- table list -->
                                <div class="table-wrap">
                                    <div class="inner-head">
                                        <!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
                                    </div>
                                    <div class="inner-body">
                                        <!--
											table 태그 공통 클래스 : table
											스타일 구분 클래스 일반(th 검정) : table-normal
											스타일 구분 클래스 th 회색 : table-gray
											-->
                                        <table class="table table-normal thead-lg">
                                            <!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
                                            <caption>
                                                회사공통문서 목록 표
                                            </caption>
                                            <!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
                                            <colgroup>
                                                <col style="width: 10%" />
                                                <col style="width:" />
                                                <col style="width: 12%" />
                                                <col style="width: 12%" />
                                                <col style="width: 12%" />
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <!-- th에만 해당 scope="" col(열) / row(행) -->
                                                    <th scope="col">
                                                        <span>No.</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_fileName`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_fileType`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_download`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_share`) }}</span>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- no-data -->
                                                <tr v-if="$common.isEmpty(sfaTypeADocList)">
                                                    <!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
                                                    <td colspan="5">
                                                        <!-- no-data(loading) -->
                                                        <div class="no-data" v-if="$fetchState.pending">
                                                            <div class="loading-sm">
                                                                <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                                            </div>
                                                            <p>{{ $t(`user_common_nowLoading`) }}</p>
                                                        </div>
                                                        <!-- no-data -->
                                                        <div class="no-data" v-if="!$fetchState.pending">
                                                            <span class="material-icons">error_outline</span>
                                                            <span>{{ $t(`user_common_noData`) }}</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <!-- list-item -->
                                                <tr v-for="(doc, i) in sfaTypeADocList">
                                                    <td>
                                                        <span v-text="getNumber(sfaTypeADocListTotalCnt, sfaTypeADocListCurrentPage, i)"></span>
                                                    </td>
                                                    <td class="tl">
                                                        <span v-text="getDocName(doc)"></span>
                                                    </td>
                                                    <td>
                                                        <span v-text="checkFileExt(doc.currentDocVersionInfo)">Psd</span>
                                                    </td>
                                                    <td>
                                                        <el-button type="icon-only" @click="setDownloadDialog(doc)">
                                                            <span class="material-icons">arrow_circle_down</span>
                                                        </el-button>
                                                    </td>
                                                    <td>
                                                        <el-button type="icon-only" @click="setShareDialog(doc)">
                                                            <span class="material-icons">share</span>
                                                        </el-button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!--페이지네이션 -->
                                <thePagination
                                    v-if="sfaTypeADocListTotalCnt > 0"
                                    :visible-buttons-count="5"
                                    :total-count="sfaTypeADocListTotalCnt"
                                    :page-size="pageSize"
                                    :current-page="sfaTypeADocListCurrentPage"
                                    page-comp="sfaTypeADocListPage"
                                />
                            </div>
                        </div>
                    </div>
                    <!-- inner-content ::  DOC_TYPE 'B' : 제품소개자료 -->
                    <div id="sfaTypeBDocList-content" class="inner-content" v-if="isDisplayListArea">
                        <div class="default-w">
                            <div class="tit-area is-border">
                                <h3 class="tit">{{ $t(`integratedSearch_tecSupport_introductionDoc`) }}</h3>
                            </div>
                            <div class="content-wrap">
                                <!-- no-data(loading) -->
                                <div class="no-data" v-show="false">
                                    <div class="loading-sm">
                                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                    </div>
                                    <p>{{ $t(`user_common_nowLoading`) }}</p>
                                </div>
                                <!-- no-data -->
                                <div class="no-data" v-show="false">
                                    <span class="material-icons">error_outline</span>
                                    <span>{{ $t(`user_common_noData`) }}</span>
                                </div>
                                <!-- table list -->
                                <div class="table-wrap">
                                    <div class="inner-head">
                                        <!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
                                    </div>
                                    <div class="inner-body">
                                        <!--
											table 태그 공통 클래스 : table
											스타일 구분 클래스 일반(th 검정) : table-normal
											스타일 구분 클래스 th 회색 : table-gray
											-->
                                        <table class="table table-normal thead-lg">
                                            <!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
                                            <caption>
                                                INTRODUCTION 목록 표
                                            </caption>
                                            <!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
                                            <colgroup>
                                                <col style="width: 10%" />
                                                <col style="width:" />
                                                <col style="width: 12%" />
                                                <col style="width: 12%" />
                                                <col style="width: 12%" />
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <!-- th에만 해당 scope="" col(열) / row(행) -->
                                                    <th scope="col">
                                                        <span>No.</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_fileName`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_fileType`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_download`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_share`) }}</span>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- no-data -->
                                                <tr v-if="$common.isEmpty(sfaTypeBDocList)">
                                                    <!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
                                                    <td colspan="5">
                                                        <!-- no-data(loading) -->
                                                        <div class="no-data" v-if="$fetchState.pending">
                                                            <div class="loading-sm">
                                                                <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                                            </div>
                                                            <p>{{ $t(`user_common_nowLoading`) }}</p>
                                                        </div>
                                                        <!-- no-data -->
                                                        <div class="no-data" v-if="true !== $fetchState.pending">
                                                            <span class="material-icons">error_outline</span>
                                                            <span>{{ $t(`user_common_noData`) }}</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <!-- list-item -->
                                                <tr v-for="(doc, i) in sfaTypeBDocList">
                                                    <td>
                                                        <span v-text="getNumber(sfaTypeBDocListTotalCnt, sfaTypeBDocListCurrentPage, i)"></span>
                                                    </td>
                                                    <td class="tl">
                                                        <span v-text="getDocName(doc)"></span>
                                                    </td>
                                                    <td>
                                                        <span v-text="checkFileExt(doc.currentDocVersionInfo)">Psd</span>
                                                    </td>
                                                    <td>
                                                        <el-button type="icon-only" @click="setDownloadDialog(doc)">
                                                            <span class="material-icons">arrow_circle_down</span>
                                                        </el-button>
                                                    </td>
                                                    <td>
                                                        <el-button type="icon-only" @click="setShareDialog(doc)">
                                                            <span class="material-icons">share</span>
                                                        </el-button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!--페이지네이션 -->
                                <thePagination
                                    v-if="sfaTypeBDocListTotalCnt > 0"
                                    :visible-buttons-count="5"
                                    :total-count="sfaTypeBDocListTotalCnt"
                                    :page-size="pageSize"
                                    :current-page="sfaTypeBDocListCurrentPage"
                                    page-comp="sfaTypeBDocListPage"
                                />
                            </div>
                        </div>
                    </div>
                    <!-- inner-content ::  DOC_TYPE 'C' : 기술/품질자료 -->
                    <div id="sfaTypeCDocList-content" class="inner-content" v-if="isDisplayListArea">
                        <div class="default-w">
                            <div class="tit-area is-border">
                                <h3 class="tit">{{ $t(`integratedSearch_tecSupport_TQDoc`) }}</h3>
                            </div>
                            <div class="content-wrap">
                                <!-- no-data(loading) -->
                                <div class="no-data" v-show="false">
                                    <div class="loading-sm">
                                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                    </div>
                                    <p>{{ $t(`user_common_nowLoading`) }}</p>
                                </div>
                                <!-- no-data -->
                                <div class="no-data" v-show="false">
                                    <span class="material-icons">error_outline</span>
                                    <span>{{ $t(`user_common_noData`) }}</span>
                                </div>
                                <!-- table list -->
                                <div class="table-wrap">
                                    <div class="inner-head">
                                        <!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
                                    </div>
                                    <div class="inner-body">
                                        <!--
											table 태그 공통 클래스 : table
											스타일 구분 클래스 일반(th 검정) : table-normal
											스타일 구분 클래스 th 회색 : table-gray
											-->
                                        <table class="table table-normal thead-lg">
                                            <!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
                                            <caption>
                                                INTRODUCTION 목록 표
                                            </caption>
                                            <!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
                                            <colgroup>
                                                <col style="width: 10%" />
                                                <col style="width:" />
                                                <col style="width: 12%" />
                                                <col style="width: 12%" />
                                                <col style="width: 12%" />
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <!-- th에만 해당 scope="" col(열) / row(행) -->
                                                    <th scope="col">
                                                        <span>No.</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_fileName`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_fileType`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_download`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_share`) }}</span>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- no-data -->
                                                <tr v-if="$common.isEmpty(sfaTypeCDocList)">
                                                    <!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
                                                    <td colspan="5">
                                                        <!-- no-data(loading) -->
                                                        <div class="no-data" v-if="$fetchState.pending">
                                                            <div class="loading-sm">
                                                                <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                                            </div>
                                                            <p>{{ $t(`user_common_nowLoading`) }}</p>
                                                        </div>
                                                        <!-- no-data -->
                                                        <div class="no-data" v-if="true !== $fetchState.pending">
                                                            <span class="material-icons">error_outline</span>
                                                            <span>{{ $t(`user_common_noData`) }}</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <!-- list-item -->
                                                <tr v-for="(doc, i) in sfaTypeCDocList">
                                                    <td>
                                                        <span v-text="getNumber(sfaTypeCDocListTotalCnt, sfaTypeCDocListCurrentPage, i)"></span>
                                                    </td>
                                                    <td class="tl">
                                                        <span v-text="getDocName(doc)"></span>
                                                    </td>
                                                    <td>
                                                        <span v-text="checkFileExt(doc.currentDocVersionInfo)">Psd</span>
                                                    </td>
                                                    <td>
                                                        <el-button type="icon-only" @click="setDownloadDialog(doc)">
                                                            <span class="material-icons">arrow_circle_down</span>
                                                        </el-button>
                                                    </td>
                                                    <td>
                                                        <el-button type="icon-only" @click="setShareDialog(doc)">
                                                            <span class="material-icons">share</span>
                                                        </el-button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!--페이지네이션 -->
                                <thePagination
                                    v-if="sfaTypeCDocListTotalCnt > 0"
                                    :visible-buttons-count="5"
                                    :total-count="sfaTypeCDocListTotalCnt"
                                    :page-size="pageSize"
                                    :current-page="sfaTypeCDocListCurrentPage"
                                    page-comp="sfaTypeCDocListPage"
                                />
                            </div>
                        </div>
                    </div>
                    <!-- inner-content ::  DOC_TYPE 'D' : 인증서/성적서 -->
                    <div id="sfaTypeDDocList-content" class="inner-content" v-if="isDisplayListArea">
                        <div class="default-w">
                            <div class="tit-area is-border">
                                <h3 class="tit">{{ $t(`integratedSearch_tecSupport_crDoc`) }}</h3>
                            </div>
                            <div class="content-wrap">
                                <!-- no-data(loading) -->
                                <div class="no-data" v-show="false">
                                    <div class="loading-sm">
                                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                    </div>
                                    <p>{{ $t(`user_common_nowLoading`) }}</p>
                                </div>
                                <!-- no-data -->
                                <div class="no-data" v-show="false">
                                    <span class="material-icons">error_outline</span>
                                    <span>{{ $t(`user_common_noData`) }}</span>
                                </div>
                                <!-- table list -->
                                <div class="table-wrap">
                                    <div class="inner-head">
                                        <!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
                                    </div>
                                    <div class="inner-body">
                                        <!--
											table 태그 공통 클래스 : table
											스타일 구분 클래스 일반(th 검정) : table-normal
											스타일 구분 클래스 th 회색 : table-gray
											-->
                                        <table class="table table-normal thead-lg">
                                            <!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
                                            <caption>
                                                INTRODUCTION 목록 표
                                            </caption>
                                            <!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
                                            <colgroup>
                                                <col style="width: 10%" />
                                                <col style="width:" />
                                                <col style="width: 12%" />
                                                <col style="width: 12%" />
                                                <col style="width: 12%" />
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <!-- th에만 해당 scope="" col(열) / row(행) -->
                                                    <th scope="col">
                                                        <span>No.</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_fileName`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_fileType`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_download`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_share`) }}</span>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- no-data -->
                                                <tr v-if="$common.isEmpty(sfaTypeDDocList)">
                                                    <!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
                                                    <td colspan="5">
                                                        <!-- no-data(loading) -->
                                                        <div class="no-data" v-if="$fetchState.pending">
                                                            <div class="loading-sm">
                                                                <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                                            </div>
                                                            <p>{{ $t(`user_common_nowLoading`) }}</p>
                                                        </div>
                                                        <!-- no-data -->
                                                        <div class="no-data" v-if="true !== $fetchState.pending">
                                                            <span class="material-icons">error_outline</span>
                                                            <span>{{ $t(`user_common_noData`) }}</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <!-- list-item -->
                                                <tr v-for="(doc, i) in sfaTypeDDocList">
                                                    <td>
                                                        <span v-text="getNumber(sfaTypeDDocListTotalCnt, sfaTypeDDocListCurrentPage, i)"></span>
                                                    </td>
                                                    <td class="tl">
                                                        <span v-text="getDocName(doc)"></span>
                                                    </td>
                                                    <td>
                                                        <span v-text="checkFileExt(doc.currentDocVersionInfo)">Psd</span>
                                                    </td>
                                                    <td>
                                                        <el-button type="icon-only" @click="setDownloadDialog(doc)">
                                                            <span class="material-icons">arrow_circle_down</span>
                                                        </el-button>
                                                    </td>
                                                    <td>
                                                        <el-button type="icon-only" @click="setShareDialog(doc)">
                                                            <span class="material-icons">share</span>
                                                        </el-button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!--페이지네이션 -->
                                <thePagination
                                    v-if="sfaTypeDDocListTotalCnt > 0"
                                    :visible-buttons-count="5"
                                    :total-count="sfaTypeDDocListTotalCnt"
                                    :page-size="pageSize"
                                    :current-page="sfaTypeDDocListCurrentPage"
                                    page-comp="sfaTypeDDocListPage"
                                />
                            </div>
                        </div>
                    </div>
                    <!-- inner-content ::  DOC_TYPE 'E' : 제품교육자료 -->
                    <div id="sfaTypeEDocList-content" class="inner-content" v-if="isDisplayListArea">
                        <div class="default-w">
                            <div class="tit-area is-border">
                                <h3 class="tit">{{ $t(`integratedSearch_tecSupport_ptDoc`) }}</h3>
                            </div>
                            <div class="content-wrap">
                                <!-- no-data(loading) -->
                                <div class="no-data" v-show="false">
                                    <div class="loading-sm">
                                        <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                    </div>
                                    <p>{{ $t(`user_common_nowLoading`) }}</p>
                                </div>
                                <!-- no-data -->
                                <div class="no-data" v-show="false">
                                    <span class="material-icons">error_outline</span>
                                    <span>{{ $t(`user_common_noData`) }}</span>
                                </div>
                                <!-- table list -->
                                <div class="table-wrap">
                                    <div class="inner-head">
                                        <!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
                                    </div>
                                    <div class="inner-body">
                                        <!--
											table 태그 공통 클래스 : table
											스타일 구분 클래스 일반(th 검정) : table-normal
											스타일 구분 클래스 th 회색 : table-gray
											-->
                                        <table class="table table-normal thead-lg">
                                            <!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
                                            <caption>
                                                INTRODUCTION 목록 표
                                            </caption>
                                            <!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
                                            <colgroup>
                                                <col style="width: 10%" />
                                                <col style="width:" />
                                                <col style="width: 12%" />
                                                <col style="width: 12%" />
                                                <col style="width: 12%" />
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <!-- th에만 해당 scope="" col(열) / row(행) -->
                                                    <th scope="col">
                                                        <span>No.</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_fileName`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_fileType`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_download`) }}</span>
                                                    </th>
                                                    <th scope="col">
                                                        <span>{{ $t(`integratedSearch_tecSupport_share`) }}</span>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- no-data -->
                                                <tr v-if="$common.isEmpty(sfaTypeEDocList)">
                                                    <!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
                                                    <td colspan="5">
                                                        <!-- no-data(loading) -->
                                                        <div class="no-data" v-if="$fetchState.pending">
                                                            <div class="loading-sm">
                                                                <img alt="Loading" src="@/assets/images/loading/loading_sm.svg" />
                                                            </div>
                                                            <p>{{ $t(`user_common_nowLoading`) }}</p>
                                                        </div>
                                                        <!-- no-data -->
                                                        <div class="no-data" v-if="true !== $fetchState.pending">
                                                            <span class="material-icons">error_outline</span>
                                                            <span>{{ $t(`user_common_noData`) }}</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <!-- list-item -->
                                                <tr v-for="(doc, i) in sfaTypeEDocList">
                                                    <td>
                                                        <span v-text="getNumber(sfaTypeEDocListTotalCnt, sfaTypeEDocListCurrentPage, i)"></span>
                                                    </td>
                                                    <td class="tl">
                                                        <span v-text="getDocName(doc)"></span>
                                                    </td>
                                                    <td>
                                                        <span v-text="checkFileExt(doc.currentDocVersionInfo)"></span>
                                                    </td>
                                                    <td>
                                                        <el-button type="icon-only" @click="setDownloadDialog(doc)">
                                                            <span class="material-icons">arrow_circle_down</span>
                                                        </el-button>
                                                    </td>
                                                    <td>
                                                        <el-button type="icon-only" @click="setShareDialog(doc)">
                                                            <span class="material-icons">share</span>
                                                        </el-button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!--페이지네이션 -->
                                <thePagination
                                    v-if="sfaTypeEDocListTotalCnt > 0"
                                    :visible-buttons-count="5"
                                    :total-count="sfaTypeEDocListTotalCnt"
                                    :page-size="pageSize"
                                    :current-page="sfaTypeEDocListCurrentPage"
                                    page-comp="sfaTypeEDocListPage"
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 공유 모달 :: TheShareModal.vue -->
        <the-share-modal
            :share-dialog-visible="shareDialogVisible"
            :share-doc-info="shareDocInfo"
            @close="shareDialogVisible = false"
        ></the-share-modal>
        <!-- 다운로드 모달 :: TheShareModal.vue	-->
        <the-download-modal
            :download-dialog-visible="downloadDialogVisible"
            :download-doc-info="downloadDocInfo"
            @close="downloadDialogVisible = false"
        ></the-download-modal>
    </div>
</template>
<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import theBreadcrumb from "~/components/kccam/user/breadcrumb/TheBreadcrumb.vue";
import thePagination from "~/components/common/ThePagination.vue";
import theShareModal from "~/components/kccam/user/modal/TheShareModal.vue";
import theDownloadModal from "~/components/kccam/user/modal/TheDownloadModal.vue";

export default {
    head: {
        title: "KCC AM - 기술지원",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "기술지원 페이지입니다.",
            },
            { property: "og:title", content: "KCC AM - 기술지원" },
            { name: "twitter:title", content: "KCC AM - 기술지원" },
        ],
    },
    layout: "userLayout",
    components: {
        theLoading,
        theBreadcrumb,
        thePagination,
        theShareModal,
    },
    data() {
        return {
            // anchor select
            selected: "a",

            // 검색창
            searchInput: "",
            lastSearchInput: "",

            isDisplayListArea: false,

            pageSize: 5,

            // 공유 모달
            shareDialogVisible: false,
            shareDocInfo: {},

            // 다운 모달
            downloadDialogVisible: false,
            downloadDocInfo: {},

            sfaTypeADocList: [],
            sfaTypeBDocList: [],
            sfaTypeCDocList: [],
            sfaTypeDDocList: [],
            sfaTypeEDocList: [],

            sfaTypeADocListTotalCnt: 0,
            sfaTypeBDocListTotalCnt: 0,
            sfaTypeCDocListTotalCnt: 0,
            sfaTypeDDocListTotalCnt: 0,
            sfaTypeEDocListTotalCnt: 0,

            sfaTypeADocListCurrentPage: 0,
            sfaTypeBDocListCurrentPage: 0,
            sfaTypeCDocListCurrentPage: 0,
            sfaTypeDDocListCurrentPage: 0,
            sfaTypeEDocListCurrentPage: 0,
        };
    },
    mounted() {
        // this.$common.confirmSwal( "오픈 예정", "4월말 오픈 예정 기능입니다.", "info" );
    },

    computed: {
        loadingIndicator() {
            return this.$nuxt.$root.$loading.percent;
        },
    },

    watch: {
        $route() {
            this.$fetch();
        },
    },

    async fetch() {
        if (this.$common.isEmpty(this.$route.query.searchInput)) {
            return;
        }

        if (this.lastSearchInput !== this.$route.query.searchInput) {
            this.sfaTypeADocListCurrentPage = 0;
            this.sfaTypeBDocListCurrentPage = 0;
            this.sfaTypeCDocListCurrentPage = 0;
            this.sfaTypeDDocListCurrentPage = 0;
            this.sfaTypeEDocListCurrentPage = 0;
        }

        this.isDisplayListArea = true;
        this.searchInput = this.$route.query.searchInput;
        this.lastSearchInput = this.searchInput;
        await this.displayList();
    },
    methods: {
        // 앵커 메뉴 클릭 시 각 영역으로 스크롤
        goContent(area) {
            let domId = "#" + area;

            let scrollToElement = document.querySelector(domId);

            const scrollOptions = {
                verticalOffset: -150, // header 높이(58) + breadcrumb 높이(62) + 여백 조금(30)
            };

            animateScrollTo(scrollToElement, scrollOptions);
        },

        // 기술 지원 리스트 검색
        async searchList() {
            if (this.$common.isEmpty(this.searchInput)) {
                this.$common.confirmSwal(
                    this.$t(`user_common_confirmSwal_noSearchInput`),
                    this.$t(`user_common_confirmSwal_noSearchInput_desc`),
                    "warning",
                );
                return;
            }

            this.$router.push(
                this.localePath({
                    path: "/kccam/user/productSupport/productSupport_technicalSupport",
                    query: {
                        searchInput: this.searchInput,
                        depth: 1,

                        sfaTypeADocListPage: 1,
                        sfaTypeBDocListPage: 1,
                        sfaTypeCDocListPage: 1,
                        sfaTypeDDocListPage: 1,
                        sfaTypeEDocListPage: 1,
                    },
                }),
            );
        },

        // 기술 지원 리스트를 그립니다.
        async displayList() {
            this.sfaTypeADocList = await this.getSfaTypeADocList();
            this.sfaTypeBDocList = await this.getSfaTypeBDocList();
            this.sfaTypeCDocList = await this.getSfaTypeCDocList();
            this.sfaTypeDDocList = await this.getSfaTypeDDocList();
            this.sfaTypeEDocList = await this.getSfaTypeEDocList();
        },

        // 페이지 번호로 startIndex를 가져옵니다.
        getStartIndexFromPage(page) {
            let startIndex = (page - 1) * this.pageSize + 1;
            return startIndex;
        },

        // 'A'타입 문서 리스트를 가져옵니다. (customField1 ='A')
        async getSfaTypeADocList() {
            if (this.sfaTypeADocListCurrentPage === Number(this.$route.query.sfaTypeADocListPage)) {
                return this.sfaTypeADocList;
            }

            this.sfaTypeADocListCurrentPage = Number(this.$route.query.sfaTypeADocListPage);
            const startIndex = this.getStartIndexFromPage(this.sfaTypeADocListCurrentPage);

            const url = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.LIST;

            let resData = await this.getList(startIndex, url, "A");
            this.sfaTypeADocListTotalCnt = resData.totalCount;

            return resData.list;
        },

        // 'B'타입 문서 리스트를 가져옵니다.
        async getSfaTypeBDocList() {
            if (this.sfaTypeBDocListCurrentPage === Number(this.$route.query.sfaTypeBDocListPage)) {
                return this.sfaTypeBDocList;
            }

            this.sfaTypeBDocListCurrentPage = Number(this.$route.query.sfaTypeBDocListPage);
            const startIndex = this.getStartIndexFromPage(this.sfaTypeBDocListCurrentPage);

            const url = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.LIST;

            let resData = await this.getList(startIndex, url, "B");
            this.sfaTypeBDocListTotalCnt = resData.totalCount;

            return resData.list;
        },

        // 'C'타입 문서 리스트를 가져옵니다.
        async getSfaTypeCDocList() {
            if (this.sfaTypeCDocListCurrentPage === Number(this.$route.query.sfaTypeCDocListPage)) {
                return this.sfaTypeCDocList;
            }

            this.sfaTypeCDocListCurrentPage = Number(this.$route.query.sfaTypeCDocListPage);
            const startIndex = this.getStartIndexFromPage(this.sfaTypeCDocListCurrentPage);

            const url = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.LIST;

            let resData = await this.getList(startIndex, url, "C");
            this.sfaTypeCDocListTotalCnt = resData.totalCount;

            return resData.list;
        },

        // 'D'타입 문서 리스트를 가져옵니다.
        async getSfaTypeDDocList() {
            if (this.sfaTypeDDocListCurrentPage === Number(this.$route.query.sfaTypeDDocListPage)) {
                return this.sfaTypeDDocList;
            }

            this.sfaTypeDDocListCurrentPage = Number(this.$route.query.sfaTypeDDocListPage);
            const startIndex = this.getStartIndexFromPage(this.sfaTypeDDocListCurrentPage);

            const url = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.LIST;

            let resData = await this.getList(startIndex, url, "D");
            this.sfaTypeDDocListTotalCnt = resData.totalCount;

            return resData.list;
        },

        // 'E'타입 문서 리스트를 가져옵니다.
        async getSfaTypeEDocList() {
            if (this.sfaTypeEDocListCurrentPage === Number(this.$route.query.sfaTypeEDocListPage)) {
                return this.sfaTypeEDocList;
            }

            this.sfaTypeEDocListCurrentPage = Number(this.$route.query.sfaTypeEDocListPage);
            const startIndex = this.getStartIndexFromPage(this.sfaTypeEDocListCurrentPage);

            const url = this.$urlConstant.API_URL_PREFIX.DOC + this.$urlConstant.API_URL_SUFFIX.DOC.LIST;

            let resData = await this.getList(startIndex, url, "E");
            this.sfaTypeEDocListTotalCnt = resData.totalCount;

            return resData.list;
        },

        // 리스트를 가져옵니다.
        async getList(startIndex, url, sfaType) {
            if (this.$common.isEmpty(this.searchInput)) {
                return;
            }

            const param = {
                likeSearch: true,
                title: this.searchInput,
                pageSize: this.pageSize,
                startIndex: startIndex,
                customField1: sfaType,
                fillFile: true,
            };
            // console.log('param : ', param);
            let result = {};
            await this.$axios.post(url, param).then(res => {
                result = res.data;
            });
            //console.log( "result : ", result );
            return result;
        },

        // 게시글 번호 (역순)
        getNumber(listTotalcount, currentPage, index) {
            return listTotalcount - (currentPage - 1) * this.pageSize - index;
        },

        //파일명을 가져옵니다
        getDocName(doc) {
            if (
                this.$common.isEmpty(doc.currentDocVersionInfo) ||
                this.$common.isEmpty(doc.currentDocVersionInfo.docFileInfo) ||
                this.$common.isEmpty(doc.currentDocVersionInfo.docFileInfo.customField2)
            ) {
                return doc.title;
            }
            //파일 확장자를 제외한 파일명 추출
            let lastDotIndex = doc.customField2.lastIndexOf(".");

            return doc.customField2.substring(0, lastDotIndex);
        },

        //파일 형태를 가져옵니다. (jpg, pdf,,,)
        checkFileExt(currentVersion) {
            if (this.$common.isEmpty(currentVersion) || this.$common.isEmpty(currentVersion.docFileInfo)) {
                return this.$t(`filetype_externalFile`);
            }

            if (this.$common.isEmpty(currentVersion.docFileInfo.fileExt)) {
                return "-";
            }

            return currentVersion.docFileInfo.fileExt.toUpperCase();
        },

        //공유 정보를 설정합니다.
        setShareDialog(doc) {
            if (this.$common.isEmpty(doc)) {
                return;
            }
            this.shareDocInfo = doc;
            this.shareDialogVisible = true;
        },

        //다운로드 정보를 설정합니다.
        setDownloadDialog(doc) {
            if (this.$common.isEmpty(doc)) {
                return;
            }
            this.downloadDocInfo = doc;
            this.downloadDialogVisible = true;
        },
    },
};
</script>
<style></style>
