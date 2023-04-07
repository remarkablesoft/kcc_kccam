<template>
    <!-- reference desc -->
    <!-- v-for는 애를 돌리면 됩니다 -->
    <div class="desc-editor-wrap">
        <div v-for="contents in addContentsList">
            <div class="inner-head">
                <div class="tit" v-text="contents.contentsTitle"><!-- 머릿말 일로들어오면 됩니다 --></div>
            </div>
            <div class="inner-body variable-context" v-html="contents.contents">
                <!-- 내용은 일로들어오면 됩니다 -->
            </div>
        </div>
    </div>

</template>

<script>
export default {
    props : {
        addContentsList : {
            type : Array,
            required : false,
            default : function() {
                return [];
            }
        },
    },
    computed: {},
};
</script>

<style></style>
