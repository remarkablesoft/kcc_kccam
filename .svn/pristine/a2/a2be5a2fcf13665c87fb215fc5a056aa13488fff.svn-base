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
					<h2>Application 관리 전체</h2>
				</div>
				<div class="btn">
					<el-button type="st st-primary" size="small" @click="goList()">Application 목록 보기</el-button>
					<el-button type="primary" size="small" @click="goEdit()">신규 Application 등록</el-button>
				</div>
			</div>
			<!-- table -->
			<div class="content-detail">
				<!-- 언어 탭 버튼  -->
				<div class="btn-group bb">
					<div class="btn-wrap tab-style">
						<el-button
							v-for="( item, index ) in materialMap"
							:key="index"
							type="st st-gray"
							size="default"
						    @click="setTreeParam(item, index)"
							:class="setActive( index )"
						>{{ item.materialName }}
						</el-button>
						<!-- 클릭한 버튼에 activ 클래스 추가 -->
						<!--                        <el-button type="st st-gray" size="default" class="active">EMC</el-button>-->
						<!--                        <el-button type="st st-gray" size="default">전자소재</el-button>-->
						<!--                        <el-button type="st st-gray" size="default">Ceramic Substrate</el-button>-->
						<!--                        <el-button type="st st-gray" size="default">Metalized Ceramic</el-button>-->
						<!--                        <el-button type="st st-gray" size="default">Glass fiber </el-button>-->
					</div>
					<!-- <div class="btn-wrap">
						<el-button type="gray" size="small" @click="goEdit()">신규 Application 등록</el-button>
					</div> -->
				</div>
				<!-- tree -->
				<div class="tree-area">
					<div v-if="!showTree" class="no-data">
						<div class="loading-sm">
							<img src="@/assets/images/loading/loading_sm.svg" alt="Loading" />
						</div>
						<p>데이터 로딩중입니다.</p>
					</div>
					<the-tree v-if="showTree" ref="tree" editType="view" :part-oid="partOid" :type="applicationType"></the-tree>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import theTree from "~/components/common/tree/TheTree.vue";


export default {
	layout     : "managerLayout",
	components : {
		theLoading,
		theTree,
	},
	data() {
		return {
			/*트리관련 data start*/
			applicationType : this.$amConstant.CATEGORY_TYPE.APPLICATION,
			partOid         : "",

			showTree: true,
			/*트리관련 data end*/
		};
	},
	fetch() {
		this.partOid = Object.keys( this.$store.state.menu.materialMap )[ 0 ]
	},
	computed : {
		materialMap() {
			return this.$store.state.menu.materialMap;
		}
	},

	methods : {
		// 목록 페이지로 이동
		goList() {
			this.$router.push( this.localePath("/kccam/manager/productMgnt/application/application_list" ));
		},

		// 수정/등록 페이지로 이동
		goEdit() {
			this.$router.push( this.localePath("/kccam/manager/productMgnt/application/application_edit" ));
		},

		async setTreeParam( item, partOid ) {

			this.showTree = false;

			this.partOid = partOid;

			setInterval(()=>{
				this.showTree = true;
			}, 500)


		},

		setActive( index ){

			if( index === this.partOid ){
				return 'active';
			}
			else{
				return '';
			}

		},
	},
};
</script>
<style scoped></style>
