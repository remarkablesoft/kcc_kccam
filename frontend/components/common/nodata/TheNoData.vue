<template>
    <div class="no-data-container">
<!-- 너무 깜빡임 현상이 있어서 임시로 주석처리 -->
<!--         no-data(loading)-->
        <div class="no-data" v-if="loadingIndicator">
            <div class="loading-sm">
                <img src="@/assets/images/common/loading/loading_sm.svg" alt="Loading" />
            </div>
            <span class="txt">데이터 로딩중입니다.</span>
        </div>

        <!-- no data -->
        <div :style="styleStr" class="no-data" v-if="!fetchState && 1 > listCount && !loadingIndicator">
            <span class="material-icons">error_outline</span>
            <span class="txt" v-text="noDataSentence"></span>
        </div>
    </div>
</template>

<script>
export default {
	props : {
		fetchState : {
      type : Boolean,
      required : false,
      default : false,
    },
		listCount : {
      type : Number,
      required : false,
      default : 0,
    },
		noDataSentence : {
			type: String,
			required: false,
			default : "데이터가 없습니다.",
		},
    styleObj : {
      type: Object,
      required: false,
      default(){
        return {}
      }
    },
    loadingIndicator : {
		  type : Boolean,
    }
	},
  computed : {
    styleStr() {
      let str = "";
      if(!this.$common.isEmpty(this.styleObj.height)) str = str + `height:${this.styleObj.height}`;
      return str;
    }
  },
  data() {
      return {
      };
  },
};
</script>

<style></style>
