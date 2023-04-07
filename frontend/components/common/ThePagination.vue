<template>
	<div>
		<!-- 웹용 페이지네이션 -->
		<div id="pagination" class="pagination-row web-visible">
			<nav>
				<ul class="pagination">
					<li>
						<a
							:disabled="isInFirstPage"
							aria-label="Go to first page"
							class="btn first"
							type="button"
							@click="onClickFirstPage"
							><span></span
						></a>
					</li>
					<li>
						<a
							:disabled="isInFirstPage"
							aria-label="Go to previous page"
							class="btn previous"
							type="button"
							@click="onClickPreviousPage"
							><span></span
						></a>
					</li>
					<li
						v-for="(page, i) in pages"
						:key="i"
						:aria-label="`Go to page number ${page.name}`"
						:class="{ active: isPageActive(page.name) }"
						:disabled="page.isDisabled"
						style="cursor: pointer"
						type="button"
						@click="onClickPage(page.name)"
					>
						<a>{{ page.name }}</a>
					</li>

					<li>
						<a
							:disabled="isInLastPage"
							aria-label="Go to next page"
							class="btn next"
							type="button"
							@click="onClickNextPage"
							><span></span
						></a>
					</li>
					<li>
						<a
							:disabled="isInLastPage"
							aria-label="Go to last page"
							class="btn end"
							type="button"
							@click="onClickLastPage"
							><span></span
						></a>
					</li>
				</ul>
			</nav>
		</div>

		<!-- 모바일용 페이지네이션 :: 더보기 버튼 -->
		<div class="mob-visible">
			<div class="mob-pagination">
				<el-button type="primary" round>더보기</el-button>
			</div>
			<div class="mob-page-descr">
				목록의 마지막 입니다.
			</div>
		</div>

		<!-- 모바일용 페이지네이션 :: 이전/다음 버튼 -->
		<div class="mob-visible" v-show="false">
			<div class="mob-pagination">
				<el-button type="st st-primary" round>이전</el-button>
				<el-button type="primary" round>다음</el-button>
			</div>
			<div class="mob-page-descr">
				목록의 마지막 입니다.
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: {
		visibleButtonsCount: {
			type: Number,
			required: false,
			default: 3
		},
		totalCount: {
			type: Number,
			required: true
		},
		pageSize: {
			type: Number,
			required: true
		},
		currentPage: {
			type: Number,
			required: true
		},
		name: {
			type: String,
			required: false
		},

		pageComp: {
			type: String,
			required: false,
			default: "page"
		}
	},
	data() {
		return {
			pageParam: ""
		};
	},

	created() {
		if (!process.browser) {
			return;
		}
	},
	mounted() {
		this.pageParam = this.pageComp + "=";
	},
	computed: {
		totalPages() {
			if (this.totalCount % this.pageSize == 0) {
				return parseInt(this.totalCount / this.pageSize);
			}
			return parseInt(this.totalCount / this.pageSize + 1);
		},
		startPage() {
			// When on the first page
			if (this.currentPage === 1) {
				return 1;
			}
			// When in between
			if (this.currentPage < this.visibleButtonsCount) {
				return 1;
			}
			if (
				this.currentPage >
				this.totalPages - this.halfOfVisibleButtons
			) {
				return this.totalPages - this.visibleButtonsCount + 1;
			}
			// When on the last page
			if (this.currentPage === this.totalPages) {
				return this.totalPages - this.visibleButtonsCount + 1;
			}
			return this.currentPage - this.halfOfVisibleButtons;
		},
		pages() {
			const range = [];

			for (
				let i = this.startPage;
				i <=
				Math.min(
					this.startPage + this.visibleButtonsCount - 1,
					this.totalPages
				);
				i++
			) {
				range.push({
					name: i,
					isDisabled: i === this.currentPage
				});
			}

			return range;
		},

		isInFirstPage() {
			return this.currentPage === 1;
		},
		isInLastPage() {
			return this.currentPage === this.totalPages;
		},
		halfOfVisibleButtons() {
			return parseInt(this.visibleButtonsCount / 2);
		}
	},
	methods: {
		onClickFirstPage() {
			this.goRouter(1);
		},
		onClickPreviousPage() {
			let goPage = this.currentPage - 1;
			if (1 >= this.currentPage) {
				goPage = 1;
			}

			this.goRouter(goPage);
		},
		onClickPage(page) {
			this.goRouter(page);
		},
		onClickNextPage() {
			let goPage = this.currentPage + 1;
			if (this.totalPages <= this.currentPage) {
				goPage = this.totalPages;
			}
			this.goRouter(goPage);
		},
		onClickLastPage() {
			this.goRouter(this.totalPages);
		},
		isPageActive(page) {
			return this.currentPage === page;
		},
		goRouter(page) {
			// url  파라미터가 없으면
			if (!this.isParam()) {

				this.$router.push({
					path: this.localePath(this.$router.currentRoute.path + "?" + this.pageParam + page)
				});
				return;
			}

			let fullPath = this.$router.currentRoute.fullPath;

			// page= 있을 때
			if (-1 < fullPath.indexOf(this.pageParam)) {
				let arrFullPath = fullPath.split("?");

				let arrParam = arrFullPath[1].split("&");

				//page param 어딘지 확인
				let pageIndex = this.getPageIndex(arrParam);

				arrParam[pageIndex] = this.pageParam + page;

				let fullParam = this.getFullParam(arrParam);

				let fullPath2 = arrFullPath[0] + "?" + fullParam;

				this.$router.push({
					path: this.localePath (fullPath2)
				});
			}
			// page= 없이 다른 파라미터만 있을 때
			else {
				this.$router.push({
					path: this.localePath(this.$router.currentRoute.fullPath + "&" + this.pageParam + page )
				});
			}
		},

		isParam() {
			let fullPath = this.$router.currentRoute.fullPath;
			if (-1 < fullPath.indexOf("?")) {
				return true;
			}
			return false;
		},

		isParamPage() {
			let fullPath = this.$router.currentRoute.fullPath;
			if (-1 < fullPath.indexOf(this.pageParam)) {
				return true;
			}
			return false;
		},

		getPageIndex(arrParam) {
			let pageIndex = -1;
			for (let i = 0; i < arrParam.length; i++) {
				if (arrParam[i].includes(this.pageParam)) {
					pageIndex = i;
				}
			}
			return pageIndex;
		},

		getFullParam(arrParam) {
			let fullParam = "";
			for (let i = 0; i < arrParam.length; i++) {
				if (arrParam.length > i) {
					fullParam += arrParam[i] + "&";
				} else {
					fullParam += arrParam[i];
				}
			}
			return fullParam;
		}
	}
};
</script>

<style lang="scss" scoped>
// flex
@mixin flex-item($direction: row, $align: center, $justify: center) {
	display: flex;
	flex-direction: $direction;
	align-items: $align;
	justify-content: $justify;
}
// size
$heightSet: 35px;
@mixin define-size(
	$height: $heightSet,
	$lineHeight: $heightSet,
	$padding: 0 12px,
	$width: 33px
) {
	height: $height;
	line-height: $lineHeight;
	padding: $padding;
	width: $width;
}
// background
@mixin set-bg(
	$url: none,
	$color: transparent,
	$repeat: no-repeat,
	$size: initial,
	$attachment: local,
	$position: center
) {
	background-image: $url;
	background-color: $color;
	background-repeat: $repeat;
	background-size: $size;
	background-attachment: $attachment;
	background-position: $position;
}

// variables
$activeColor: #5551f7 !default;
$defaultColor: #686868;

// web
.pagination-row {
	width: 100%;
	margin: 2rem auto 0;
	text-align: center;

	.pagination {
		@include flex-item();

		li {
			a {
				@include define-size();
				position: relative;
				font-size: 16px;
				color: $defaultColor;
				cursor: pointer;
				min-width: auto;

				&.btn {
					@include define-size();
					font-size: 16px;
					color: $defaultColor;
					cursor: pointer;

					span {
						display: inline-block;

						&::before {
							content: "";
							position: absolute;
							display: block;
							@include define-size(
								$height: 10px,
								$width: 10px,
								$lineHeight: 10px,
								$padding: 0
							);
							@include set-bg(
								$url:
									url(../../assets/images/icon/icon_pagination.png),
								$position: -2px -2px
							);
							margin-top: 1px;
							top: 50%;
							left: 50%;
							transform: translate(-50%, -50%);
						}
					}
				}

				&.previous,
				&.next {
					span {
						&::before {
							background-position: -13px -2px;
						}
					}
				}

				&.next,
				&.end {
					span {
						&::before {
							transform: translate(-50%, -50%) rotate(180deg);
						}
					}
				}
			}

			&.active {
				a {
					color: $activeColor;
					font-weight: bold;
				}
			}
		}
	}
}

// mobile
.mob-pagination {
	margin-top: 3rem;
	text-align: center;

	.el-button {
		width: 10rem;
		height: 4rem;
		padding: 0.5rem 2.3rem;
		border-radius: 3rem;
		font-size: 1.6rem;
	}
}

.mob-page-descr {
	text-align: center;
	font-size: 1.7rem;
	color: #888;
	margin-top: 1rem;
}
</style>
