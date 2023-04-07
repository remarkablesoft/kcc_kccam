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
						<h2 class="tit">{{ $t( `integratedSearch_tit` ) }}</h2>
						<!-- <p class="sub-txt">KCC소개 상세내용입니다.</p> -->
					</div>
				</div>
			</div>
			<!-- content-body -->
			<div class="content-body">
				<!-- 메뉴 경로 -->
				<the-breadcrumb/>
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
									<el-input title="검색창" :placeholder="$t('integratedSearch_inputSearch')" v-model="searchInput" clearable
									          @keypress.enter.native="searchList"></el-input>
								</div>
								<!-- 빠른링크 :: 기획서엔 없고 디자인에는 있어서 일단 넣어놓고 안보이게 해놨는데 필요없으시면 지우셔도 됩니다. -->
								<div class="quick-link-box" v-show="false">
									<span class="label label-xsm label-black">{{ $t( 'integratedSearch_quickLink' ) }}</span>
									<ul class="quick-link-list">
										<li v-for="(item, index) in quickLinkList" :key="index">
											<a href="#" v-text="item.linkTitle"></a>
										</li>
									</ul>
								</div>
							</div>
							<!-- 앵커 탭 버튼 -->
							<div class="anchor-tab-btn alt" v-if="isDisplayListArea">
								<ul class="btn-list">
									<li>
										<a :class="{ active: selected === 'a' }"
										   @click="[goContent('products-content'), (selected = 'a')]">{{ $t( 'user_common_product' ) }}
										</a>
									</li>
									<li>
										<a :class="{ active: selected === 'b' }"
										   @click="[goContent('application-content'), (selected = 'b')]">{{ $t( 'user_common_application' ) }}
										</a>
									</li>
									<li>
										<a :class="{ active: selected === 'c' }"
										   @click="[goContent('markets-content'), (selected = 'c')]">{{ $t( 'user_common_market' ) }}
										</a>
									</li>
									<li>
										<a :class="{ active: selected === 'd' }"
										   @click="[goContent('function-content'), (selected = 'd')]">{{ $t( 'user_common_function' ) }}
										</a>
									</li>
									<!--                                    <li>-->
									<!--                                        <a :class="{ active: selected === 'e' }" @click="[goContent('technical-support-content'), (selected = 'e')]"-->
									<!--                                            >기술지원</a-->
									<!--                                        >-->
									<!--                                    </li>-->
									<!--                                    <li>-->
									<!--                                        <a :class="{ active: selected === 'f' }" @click="[goContent('product-catalog-content'), (selected = 'f')]"-->
									<!--                                            >제품 카탈로그</a-->
									<!--                                        >-->
									<!--                                    </li>-->
								</ul>
							</div>
						</div>
					</div>
					<!-- inner-content :: Products -->
					<div id="products-content" class="inner-content" v-if="isDisplayListArea">
						<div class="default-w">
							<div class="tit-area is-border">
								<h3 class="tit">{{ $t( 'user_common_product' ) }}</h3>
								<p class="sub-txt alt sm">'
									<strong v-text="lastSearchInput"></strong>
								                          ' {{ $t( 'integratedSearch_searchResults' ) }}
									<strong v-text="productListTotalCnt"></strong>
								                          {{ $t( 'integratedSearch_founded' ) }}
								</p>
							</div>
							<div class="content-wrap">
								<!-- no-data(loading) -->
								<div class="no-data" v-show="false">
									<div class="loading-sm">
										<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>
									</div>
									<p>{{ $t( 'user_common_nowLoading' ) }}</p>
								</div>
								<!-- no-data -->
								<div class="no-data" v-show="productListTotalCnt === 0">
									<span class="material-icons">error_outline</span>
									<span>{{ $t( 'user_common_noData' ) }}</span>
								</div>
								<div class="table-wrap" v-show="productListTotalCnt > 0">
									<div class="inner-head">
										<!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
									</div>
									<div class="inner-body">
										<!--
											table 태그 공통 클래스 : table
											스타일 구분 클래스 일반(th 검정) : table-normal
											스타일 구분 클래스 th 회색 : table-gray
											-->
										<table class="table table-gray">
											<!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
											<caption>
												제품 리스트
											</caption>
											<!--
													colgroup :: column(열) 각각 넓이 지정 필요할 때 사용,
													가로 스크롤 작동하려면 col의 넓이 단위가 %가 아닌 px이어야 하고 모든 col에 각각 다 줘야 깨지지 않고 잘 나옴.
												-->
											<colgroup>
												<col style="width: 11%"/>
												<col style="width: 21%"/>
												<col style="width: 22.25%"/>
												<col style="width: 23.5%"/>
												<col style="width: 22.25%"/>
											</colgroup>
											<thead>
											<tr>
												<!-- th에만 해당 scope="" col(열) / row(행) -->
												<!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
												<th scope="col">
													<span>{{ $t( 'integratedSearch_ProductName' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_Material' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'user_common_application' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'user_common_market' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'user_common_function' ) }}</span>
												</th>
											</tr>
											</thead>
											<tbody>
											<!-- no-data -->
											<tr v-if="$common.isEmpty(productList)">
												<!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
												<td colspan="5">
													<!-- no-data(loading) -->
													<div class="no-data" v-if="productListLoading">
														<div class="loading-sm">
															<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>
														</div>
														<p>{{ $t( 'user_common_nowLoading' ) }}</p>
													</div>
													<!-- no-data -->
													<div class="no-data" v-else>
														<span class="material-icons">error_outline</span>
														<span>{{ $t( 'user_common_noData' ) }}</span>
													</div>
												</td>
											</tr>
											<!-- list-item -->
											<tr v-for="product in productList">
												<td class="tl">
													<nuxt-link :to="localePath(setProductLinkUrl(product))" class="txt-link-underline"
													           v-text="product.productName"></nuxt-link>
												</td>
												<td>
													<nuxt-link :to="localePath(setMaterialLinkUrl(product.materialOid))"
													           class="txt-link-underline" v-text="product.materialName"></nuxt-link>
												</td>
												<td>
													<span v-for="application in product.applicationList"
													      v-text="setApplicationFullPath(application.fullPathNameVC)"></span>
												</td>
												<!--                                                    <td class="tl" v-if="product.marketList.length < 4">-->
												<td class="tl">
													<span v-text="setListNames(product.marketList)"></span>
												</td>
												<!--                                                    <td v-else>-->
												<!--                                                        <a class="txt-link-underline main-color" v-popover="product.oid">Show List</a>-->
												<!--                                                        <el-popover-->
												<!--                                                            :ref="product.oid"-->
												<!--                                                            placement="right-start"-->
												<!--                                                            width=""-->
												<!--                                                            trigger="click"-->
												<!--                                                            popper-class="popover-border-left"-->
												<!--                                                        >-->
												<!--                                                            <div class="popover-content">-->
												<!--                                                                <ul class="more-list">-->
												<!--                                                                    <li v-for="market in product.marketList">-->
												<!--                                                                        <span v-text="market.name"></span>-->
												<!--                                                                    </li>-->
												<!--                                                                </ul>-->
												<!--                                                            </div>-->
												<!--                                                        </el-popover>-->
												<!--                                                    </td>-->
												<td class="tl">
													<span v-text="setListNames(product.functionList)"></span>
												</td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
								<!-- 페이지네이션 -->
								<thePagination
									v-if="productListTotalCnt > 0"
									:visible-buttons-count="10"
									:total-count="productListTotalCnt"
									:page-size="pageSize"
									:current-page="productListCurrentPage"
									page-comp="productListPage"
								/>
							</div>
						</div>
					</div>
					<!-- inner-content :: Application -->
					<div id="application-content" class="inner-content" v-if="isDisplayListArea">
						<div class="default-w">
							<div class="tit-area is-border">
								<h3 class="tit">{{ $t( 'user_common_application' ) }}</h3>
								<p class="sub-txt alt sm">'
									<strong v-text="lastSearchInput"></strong>
								                          ' {{ $t( 'integratedSearch_searchResults' ) }}
									<strong v-text="applicationListTotalCnt"></strong>
								                          {{ $t( 'integratedSearch_founded' ) }}
								</p>
							</div>
							<div class="content-wrap">
								<!-- no-data(loading) -->
								<div class="no-data" v-show="false">
									<div class="loading-sm">
										<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>
									</div>
									<p>{{ $t( 'user_common_nowLoading' ) }}</p>
								</div>
								<!-- no-data -->
								<div class="no-data" v-show="applicationListTotalCnt === 0">
									<span class="material-icons">error_outline</span>
									<span>{{ $t( 'user_common_noData' ) }}</span>
								</div>
								<div class="table-wrap" v-show="applicationListTotalCnt > 0">
									<div class="inner-head">
										<!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
									</div>
									<div class="inner-body">
										<!--
											table 태그 공통 클래스 : table
											스타일 구분 클래스 일반(th 검정) : table-normal
											스타일 구분 클래스 th 회색 : table-gray
											-->
										<table class="table table-gray">
											<!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
											<caption>
												Application 리스트
											</caption>
											<!--
													colgroup :: column(열) 각각 넓이 지정 필요할 때 사용,
													가로 스크롤 작동하려면 col의 넓이 단위가 %가 아닌 px이어야 하고 모든 col에 각각 다 줘야 깨지지 않고 잘 나옴.
												-->
											<colgroup>
												<col style="width: 14%"/>
												<col style="width: 21.5%"/>
												<col style="width: 21.5%"/>
												<col style="width: 21.5%"/>
												<col style="width: 21.5%"/>
											</colgroup>
											<thead>
											<tr>
												<!-- th에만 해당 scope="" col(열) / row(행) -->
												<!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
												<th scope="col">
													<span>{{ $t( 'integratedSearch_ApplicationName' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_ApplicationClassification' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_Material' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_DetailApplication' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_Product' ) }}</span>
												</th>
											</tr>
											</thead>
											<tbody>
											<!-- no-data -->
											<tr v-if="$common.isEmpty(applicationList)">
												<!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
												<td colspan="5">
													<!-- no-data(loading) -->
													<div class="no-data" v-if="applicationListLoading">
														<div class="loading-sm">
															<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>
														</div>
														<p>{{ $t( 'user_common_nowLoading' ) }}</p>
													</div>
													<!-- no-data -->
													<div class="no-data" v-else>
														<span class="material-icons">error_outline</span>
														<span>{{ $t( 'user_common_noData' ) }}</span>
													</div>
												</td>
											</tr>
											<!-- list-item -->
											<tr v-for="application in applicationList">
												<td class="tl">
													<nuxt-link :to="localePath(setApplicationLinkUrl(application))"
													           class="txt-link-underline" v-text="application.applicationName">Memory
													</nuxt-link>
												</td>
												<td>
													<!--                                                        <span><em class="search-keyword">DRAM</em> > Memory</span>-->
													<span v-text="setApplicationFullPath(application.fullPathName)"></span>
												</td>
												<td>
													<nuxt-link :to="localePath(setMaterialLinkUrl(application.partOid))"
													           class="txt-link-underline" v-text="application.materialName">EMC
													</nuxt-link>
												</td>
												<td class="tl">
													<span v-text="setListNames(application.detailClassificationList)"></span>
												</td>
												<td class="tl">
													<span v-text="setListNames(application.productList)"></span>
												</td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
								<!-- 페이지네이션 -->
								<thePagination
									v-if="applicationListTotalCnt > 0"
									:visible-buttons-count="10"
									:total-count="applicationListTotalCnt"
									:page-size="pageSize"
									:current-page="applicationListCurrentPage"
									page-comp="applicationListPage"
								/>
							</div>
						</div>
					</div>
					<!-- inner-content :: Markets -->
					<div id="markets-content" class="inner-content" v-if="isDisplayListArea">
						<div class="default-w">
							<div class="tit-area is-border">
								<h3 class="tit">{{ $t( 'user_common_market' ) }}</h3>
								<p class="sub-txt alt sm">'
									<strong v-text="lastSearchInput"></strong>
								                          ' {{ $t( 'integratedSearch_searchResults' ) }}
									<strong v-text="marketListTotalCnt"></strong>
								                          {{ $t( 'integratedSearch_founded' ) }}
								</p>
							</div>
							<div class="content-wrap">
								<!-- no-data(loading) -->
								<div class="no-data" v-show="false">
									<div class="loading-sm">
										<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>
									</div>
									<p>{{ $t( 'user_common_nowLoading' ) }}</p>
								</div>
								<!-- no-data -->
								<div class="no-data" v-show="marketListTotalCnt === 0">
									<span class="material-icons">error_outline</span>
									<span>{{ $t( 'user_common_noData' ) }}</span>
								</div>
								<div class="table-wrap" v-show="marketListTotalCnt > 0">
									<div class="inner-head">
										<!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
									</div>
									<div class="inner-body">
										<!--
											table 태그 공통 클래스 : table
											스타일 구분 클래스 일반(th 검정) : table-normal
											스타일 구분 클래스 th 회색 : table-gray
											-->
										<table class="table table-gray">
											<!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
											<caption>
												Market 리스트
											</caption>
											<!--
													colgroup :: column(열) 각각 넓이 지정 필요할 때 사용,
													가로 스크롤 작동하려면 col의 넓이 단위가 %가 아닌 px이어야 하고 모든 col에 각각 다 줘야 깨지지 않고 잘 나옴.
												-->
											<colgroup>
												<col style="width: 14%"/>
												<col style="width: 21%"/>
												<col style="width: 65%"/>
											</colgroup>
											<thead>
											<tr>
												<!-- th에만 해당 scope="" col(열) / row(행) -->
												<!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
												<th scope="col">
													<span>{{ $t( 'integratedSearch_MarketName' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_Material' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_Product' ) }}</span>
												</th>
											</tr>
											</thead>
											<tbody>
											<!-- no-data -->
											<tr v-if="$common.isEmpty(marketList)">
												<!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
												<td colspan="3">
													<!-- no-data(loading) -->
													<div class="no-data" v-if="marketListLoading">
														<div class="loading-sm">
															<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>
														</div>
														<p>{{ $t( 'user_common_nowLoading' ) }}</p>
													</div>
													<!-- no-data -->
													<div class="no-data" v-else>
														<span class="material-icons">error_outline</span>
														<span>{{ $t( 'user_common_noData' ) }}</span>
													</div>
												</td>
											</tr>
											<!-- list-item -->
											<tr v-for="market in marketList">
												<td class="tl">
													<nuxt-link :to="localePath(setMarketLinkUrl(market.oid))" class="txt-link-underline"
													           v-text="market.marketName">Automotive
													</nuxt-link>
												</td>
												<td>
													<span v-text="setListNames(market.materialList)">Automotive </span>
												</td>
												<td class="tl">
													<span v-text="setListNames(market.productList)"></span>
												</td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
								<!-- 페이지네이션 -->
								<thePagination
									v-if="marketListTotalCnt > 0"
									:visible-buttons-count="10"
									:total-count="marketListTotalCnt"
									:page-size="pageSize"
									:current-page="marketListCurrentPage"
									page-comp="marketListPage"
								/>
							</div>
						</div>
					</div>
					<!-- inner-content :: Function -->
					<div id="function-content" class="inner-content" v-if="isDisplayListArea">
						<div class="default-w">
							<div class="tit-area is-border">
								<h3 class="tit">{{ $t( 'user_common_function' ) }}</h3>
								<p class="sub-txt alt sm">'
									<strong v-text="lastSearchInput">DRAM</strong>
								                          ' {{ $t( 'integratedSearch_searchResults' ) }}
									<strong v-text="functionListTotalCnt">4</strong>
								                          {{ $t( 'integratedSearch_founded' ) }}
								</p>
							</div>
							<div class="content-wrap">
								<!-- no-data(loading) -->
								<div class="no-data" v-show="false">
									<div class="loading-sm">
										<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>
									</div>
									<p>{{ $t( 'user_common_nowLoading' ) }}</p>
								</div>
								<!-- no-data -->
								<div class="no-data" v-show="functionListTotalCnt === 0">
									<span class="material-icons">error_outline</span>
									<span>{{ $t( 'user_common_noData' ) }}</span>
								</div>
								<div class="table-wrap" v-show="functionListTotalCnt > 0">
									<div class="inner-head">
										<!-- <div class="tit">KCC series selection guide (Memory devices)</div> -->
									</div>
									<div class="inner-body">
										<!--
											table 태그 공통 클래스 : table
											스타일 구분 클래스 일반(th 검정) : table-normal
											스타일 구분 클래스 th 회색 : table-gray
											-->
										<table class="table table-gray">
											<!-- caption :: table 제목 - 웹접근성 위해 필요하지만 구조가 어떻게 될지 몰라 실제로 보이진 않음 -->
											<caption>
												Market 리스트
											</caption>
											<!--
													colgroup :: column(열) 각각 넓이 지정 필요할 때 사용,
													가로 스크롤 작동하려면 col의 넓이 단위가 %가 아닌 px이어야 하고 모든 col에 각각 다 줘야 깨지지 않고 잘 나옴.
												-->
											<colgroup>
												<col style="width: 14%"/>
												<col style="width: 21%"/>
												<col style="width: 65%"/>
											</colgroup>
											<thead>
											<tr>
												<!-- th에만 해당 scope="" col(열) / row(행) -->
												<!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
												<th scope="col">
													<span>{{ $t( 'integratedSearch_FunctionName' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_Material' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_Product' ) }}</span>
												</th>
											</tr>
											</thead>
											<tbody>
											<!-- no-data -->
											<tr v-if="$common.isEmpty(functionList)">
												<!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
												<td colspan="3">
													<!-- no-data(loading) -->
													<div class="no-data" v-if="functionListLoading">
														<div class="loading-sm">
															<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>
														</div>
														<p>{{ $t( 'user_common_nowLoading' ) }}</p>
													</div>
													<!-- no-data -->
													<div class="no-data" v-else>
														<span class="material-icons">error_outline</span>
														<span>{{ $t( 'user_common_noData' ) }}</span>
													</div>
												</td>
											</tr>
											<!-- list-item -->
											<tr v-for="item in functionList">
												<td class="tl">
													<nuxt-link :to="localePath(setFunctionLinkUrl(item.oid))" class="txt-link-underline"
													           v-text="item.functionName">Low stress
													</nuxt-link>
												</td>
												<td>
													<span v-text="setListNames(item.materialList) || item.materialName"></span>
												</td>
												<td>
													<span v-text="setListNames(item.productList)"></span>
												</td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
								<!-- 페이지네이션 -->
								<thePagination
									v-if="functionListTotalCnt > 0"
									:visible-buttons-count="10"
									:total-count="functionListTotalCnt"
									:page-size="pageSize"
									:current-page="functionListCurrentPage"
									page-comp="functionListPage"
								/>
							</div>
						</div>
					</div>
					<!-- inner-content :: 기술지원 -->
					<div id="technical-support-content" class="inner-content" v-if="false">
						<div class="default-w">
							<div class="tit-area is-border">
								<h3 class="tit">기술지원</h3>
								<p class="sub-txt alt sm">
									<strong>‘DRAM’</strong>
									{{ $t( 'integratedSearch_searchResults' ) }}
									<strong>4건</strong>
									이 검색되었습니다.
								</p>
							</div>
							<div class="content-wrap">
								<!-- no-data(loading) -->
								<div class="no-data" v-show="false">
									<div class="loading-sm">
										<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>
									</div>
									<p>{{ $t( 'user_common_nowLoading' ) }}</p>
								</div>
								<!-- no-data -->
								<div class="no-data" v-show="false">
									<span class="material-icons">error_outline</span>
									<span>{{ $t( 'user_common_noData' ) }}</span>
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
												기술지원 목록 표
											</caption>
											<!-- colgroup :: column(열) 각각 넓이 지정 필요할 때 사용 -->
											<colgroup>
												<col style="width: 10%"/>
												<col style="width:"/>
												<col style="width: 12%"/>
												<col style="width: 12%"/>
												<col style="width: 12%"/>
											</colgroup>
											<thead>
											<tr>
												<!-- th에만 해당 scope="" col(열) / row(행) -->
												<th scope="col">
													<span>No.</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_tecSupport_fileName' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_tecSupport_fileType' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_tecSupport_download' ) }}</span>
												</th>
												<th scope="col">
													<span>{{ $t( 'integratedSearch_tecSupport_share' ) }}</span>
												</th>
											</tr>
											</thead>
											<tbody>
											<!-- no-data -->
											<tr v-if="false">
												<!-- colspan :: column(열) 합치기 / rowspan :: row(행) 합치기 -->
												<td colspan="5">
													<!-- no-data(loading) -->
													<div class="no-data" v-show="false">
														<div class="loading-sm">
															<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>
														</div>
														<p>{{ $t( 'user_common_nowLoading' ) }}</p>
													</div>
													<!-- no-data -->
													<div class="no-data" v-show="false">
														<span class="material-icons">error_outline</span>
														<span>{{ $t( 'user_common_noData' ) }}</span>
													</div>
												</td>
											</tr>
											<!-- list-item -->
											<tr>
												<td>
													<span>1</span>
												</td>
												<td class="tl">
													<span>KCC EMC INTRODUCTION(Power Module FP)</span>
												</td>
												<td>
													<span>Psd</span>
												</td>
												<td>
													<el-button type="icon-only">
														<span class="material-icons">arrow_circle_down</span>
													</el-button>
												</td>
												<td>
													<el-button type="icon-only">
														<span class="material-icons">share</span>
													</el-button>
												</td>
											</tr>
											<!-- list-item -->
											<tr>
												<td>
													<span>2</span>
												</td>
												<td class="tl">
													<span>KCC EMC INTRODUCTION(Power Module FP)</span>
												</td>
												<td>
													<span>Psd</span>
												</td>
												<td>
													<el-button type="icon-only">
														<span class="material-icons">arrow_circle_down</span>
													</el-button>
												</td>
												<td>
													<el-button type="icon-only">
														<span class="material-icons">share</span>
													</el-button>
												</td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
								<!-- 페이지네이션 -->
								<thePagination/>
							</div>
						</div>
					</div>
					<!-- inner-content :: 제품 카탈로그 -->
					<div id="product-catalog-content" class="inner-content" v-if="false">
						<div class="default-w">
							<div class="tit-area is-border">
								<h3 class="tit">제품 카탈로그</h3>
								<p class="sub-txt alt sm">
									<strong>‘DRAM’</strong>
									{{ $t( 'integratedSearch_searchResults' ) }}
									<strong>4건</strong>
									이 검색되었습니다.
								</p>
							</div>
							<div class="content-wrap">
								<!-- no-data(loading) -->
								<div class="no-data" v-show="false">
									<div class="loading-sm">
										<img alt="Loading" src="@/assets/images/loading/loading_sm.svg"/>
									</div>
									<p>{{ $t( 'user_common_nowLoading' ) }}</p>
								</div>
								<!-- no-data -->
								<div class="no-data" v-show="true">
									<span class="material-icons">error_outline</span>
									<span>{{ $t( 'user_common_noData' ) }}</span>
								</div>
								<!-- 페이지네이션 -->
								<thePagination v-if="false"/>
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
import thePagination from "~/components/common/ThePagination.vue";

export default {
	head: {
        title: "KCC AM - 통합검색",
        meta: [
            {
                hid: "description",
                name: "description",
                content: "통합검색 페이지입니다.",
            },
			{ property: "og:title", content: "KCC AM - 통합검색" },
            { name: "twitter:title", content: "KCC AM - 통합검색" },
        ],
    },
	layout     : "userLayout",
	components : {
		theLoading,
		theBreadcrumb,
		thePagination,
	},
	computed   : {
		loadingIndicator() {
			return this.$root.$loading.percent;
		},
	},
	data() {
		return {
			// anchor select
			selected : "a",

			// 검색창
			searchInput     : "",
			lastSearchInput : "",

			isDisplayListArea : false,

			// 빠른 링크
			quickLinkList : [],

			// popover
			visible : false,

			pageSize : 5,

			productList     : [],
			applicationList : [],
			marketList      : [],
			functionList    : [],

			productListTotalCnt     : 0,
			applicationListTotalCnt : 0,
			marketListTotalCnt      : 0,
			functionListTotalCnt    : 0,

			productListCurrentPage     : 0,
			applicationListCurrentPage : 0,
			marketListCurrentPage      : 0,
			functionListCurrentPage    : 0,

			// 로딩바
			productListLoading : false,
			applicationListLoading : false,
			marketListLoading : false,
			functionListLoading : false,

		};
	},
	watch : {
		$route() {
			this.$fetch();
		},
	},
	async fetch() {
		if ( this.$common.isEmpty( this.$route.query.searchInput ) ) {
			return;
		}

		if ( this.lastSearchInput !== this.$route.query.searchInput ) {
			this.productListCurrentPage = 0;
			this.applicationListCurrentPage = 0;
			this.marketListCurrentPage = 0;
			this.functionListCurrentPage = 0;
		}

		this.isDisplayListArea = true;
		this.searchInput = this.$route.query.searchInput;
		this.lastSearchInput = this.searchInput;
		await this.displayList();
	},
	methods : {
		// 앵커 메뉴 클릭 시 각 영역으로 스크롤
		goContent( area ) {
			let domId = "#" + area;

			let scrollToElement = document.querySelector( domId );

			const scrollOptions = {
				verticalOffset : -100, // 컨텐츠 덮지 않게.
			};

			animateScrollTo( scrollToElement, scrollOptions );
		},

		/**
		 * 통합검색 리스트를 검색합니다.
		 */

		async searchList() {
			if ( this.$common.isEmpty( this.searchInput ) ) {
				this.$common.confirmSwal( this.$t( `user_common_confirmSwal_noSearchInput` ), this.$t( `user_common_confirmSwal_noSearchInput_desc` ), "warning" );
				return;
			}
			this.applicationList = [];
			this.functionList = [];
			this.marketList = [];
			this.productList = [];

			this.$router.push( this.localePath( {
				path  : "/kccam/user/productSupport/productSupport_integratedSearch",
				query : {
					searchInput : this.searchInput,

					depth               : 1,
					productListPage     : 1,
					applicationListPage : 1,
					marketListPage      : 1,
					functionListPage    : 1,
				},
			} ) );
		},

		/**
		 * 통합검색 리스트를 그려줍니다.
		 */

		async displayList() {
			this.productList = await this.getProductList();
			this.applicationList = await this.getApplicationList();
			this.marketList = await this.getMarketList();
			this.functionList = await this.getFunctionList();
		},

		/**
		 * 페이지 번호로 StartIndex를 가져옵니다.
		 */
		getStartIndexFromPage( page ) {
			let startIndex = ( page - 1 ) * this.pageSize + 1;
			return startIndex;
		},

		/**
		 * 통합검색 제품 리스트를 가져옵니다.
		 */
		async getProductList() {
			if ( this.productListCurrentPage === Number( this.$route.query.productListPage ) ) {
				return this.productList;
			}

			this.productListCurrentPage = Number( this.$route.query.productListPage );
			const startIndex = this.getStartIndexFromPage( this.productListCurrentPage );

			const url = this.$urlConstant.API_URL_PREFIX.INTEGRATED_SEARCH + this.$urlConstant.API_URL_SUFFIX.INTEGRATED_SEARCH.PRODUCT_LIST;
			this.productList = [];
			this.productListLoading = true;
			let resData = await this.getList( startIndex, url );
			this.productListLoading = false;
			this.productListTotalCnt = resData.totalCount;

			return resData.list;
		},

		/**
		 * 통합검색 application 리스트를 가져옵니다.
		 */
		async getApplicationList() {
			if ( this.applicationListCurrentPage === Number( this.$route.query.applicationListPage ) ) {
				return this.applicationList;
			}

			this.applicationListCurrentPage = Number( this.$route.query.applicationListPage );
			const startIndex = this.getStartIndexFromPage( this.applicationListCurrentPage );

			const url = this.$urlConstant.API_URL_PREFIX.INTEGRATED_SEARCH + this.$urlConstant.API_URL_SUFFIX.INTEGRATED_SEARCH.APPLICATION_LIST;
			this.applicationList = [];
			this.applicationListLoading = true;
			let resData = await this.getList( startIndex, url );
			this.applicationListLoading = false;
			this.applicationListTotalCnt = resData.totalCount;

			return resData.list;
		},

		/**
		 * 통합검색 마켓 리스트를 가져옵니다.
		 */
		async getMarketList() {
			if ( this.marketListCurrentPage === Number( this.$route.query.marketListPage ) ) {
				return this.marketList;
			}

			this.marketListCurrentPage = Number( this.$route.query.marketListPage );
			const startIndex = this.getStartIndexFromPage( this.marketListCurrentPage );
			const url = this.$urlConstant.API_URL_PREFIX.INTEGRATED_SEARCH + this.$urlConstant.API_URL_SUFFIX.INTEGRATED_SEARCH.MARKET_LIST;
			this.marketList = [];
			this.marketListLoading = true;
			let resData = await this.getList( startIndex, url );
			this.marketListTotalCnt = resData.totalCount;
			this.marketListLoading = false;

			return resData.list;
		},

		/**
		 * 통합검색 function 리스트를 가져옵니다.
		 */
		async getFunctionList() {
			if ( this.functionListCurrentPage === Number( this.$route.query.functionListPage ) ) {
				return this.functionList;
			}

			this.functionListCurrentPage = Number( this.$route.query.functionListPage );
			const startIndex = this.getStartIndexFromPage( this.functionListCurrentPage );
			const url = this.$urlConstant.API_URL_PREFIX.INTEGRATED_SEARCH + this.$urlConstant.API_URL_SUFFIX.INTEGRATED_SEARCH.FUNCTION_LIST;
			this.functionList = [];
			this.functionListLoading = true;
			let resData = await this.getList( startIndex, url );
			this.functionListTotalCnt = resData.totalCount;
			this.functionListLoading = false;

			return resData.list;
		},

		/**
		 * 리스트를 가져옵니다.
		 */
		async getList( startIndex, url ) {
			if ( this.$common.isEmpty( this.searchInput ) ) {
				return;
			}

			const param = {
				searchText : this.searchInput,
				pageSize   : this.pageSize,
				startIndex : startIndex,
			};

			let result = {};
			await this.$axios.post( url, param ).then( res => {
				result = res.data;
			} );

			return result;
		},

		/**
		 * 소재 링크를 설정합니다.
		 */
		setMaterialLinkUrl( oid ) {
			if ( this.$common.isEmpty( oid ) ) {
				return "";
			}

			let url = this.$urlConstant.MENU_URL_PREFIX.USER_PRODUCT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT.MATERIAL_VIEW;
			url += "?depth=1&partOid=" + oid;

			return url;
		},

		/**
		 * application FullPath를 설정해줍니다.
		 */
		setApplicationFullPath( fullPathName ) {
			if ( this.$common.isEmpty( fullPathName ) ) {
				return "";
			}

			return fullPathName.toString().substring( fullPathName.indexOf( ">" ) + 1 );
		},

		/**
		 * 제품 URL을 설정해줍니다.
		 */
		setProductLinkUrl( product ) {
			if ( this.$common.isEmpty( product ) || this.$common.isEmpty( product.applicationList[ 0 ] ) ) {
				return;
			}

			let url = this.$urlConstant.MENU_URL_PREFIX.USER_PRODUCT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT.PRODUCT_VIEW;
			url += "?depth=3 " + "&partOid=" + product.materialOid + + "&classification=application" + "&classificationOid=" + product.applicationList[ 0 ].oid + "&productOid=" + product.oid;

			return url;
		},

		/**
		 * application 이동 URL을 설정해줍니다.
		 */
		setApplicationLinkUrl( application ) {
			let url = this.$urlConstant.MENU_URL_PREFIX.USER_PRODUCT + this.$urlConstant.MENU_URL_SUFFIX.PRODUCT.APPLICATION_VIEW;
			url += "?depth=2" + "&partOid=" + application.materialOid + "&classificationOid=" + application.oid + "&classification=application";

			return url;
		},

		/**
		 * market 이동 URL을 설정해줍니다.
		 */
		setMarketLinkUrl( oid ) {
			let url = this.$urlConstant.MENU_URL_PREFIX.USER_MARKET + this.$urlConstant.MENU_URL_SUFFIX.MARKET.MARKET_VIEW;
			url += "?depth=2&classificationOid=" + oid + "&classification=market";
			return url;
		},

		/**
		 * function 이동 URL을 설정해줍니다.
		 */
		setFunctionLinkUrl( oid ) {
			let url = this.$urlConstant.MENU_URL_PREFIX.USER_MARKET + this.$urlConstant.MENU_URL_SUFFIX.MARKET.FUNCTION_VIEW;
			url += "&classificationOid=" + oid + "&classification=function";
			return url;
		},

		/**
		 * 리스트를 , 구분자로 된 하나의 문자열로 반환합니다.
		 * @param list
		 * @returns {string}
		 */
		setListNames( list ) {
			if ( this.$common.isEmpty( list ) ) {
				return "";
			}

			let marketNames = "";
			_.each( list, function ( item ) {
				marketNames += item.name + ", ";
			} );

			return marketNames.substring( 0, marketNames.length - 2 );
		},
	},
};
</script>
<style></style>
