<template>
    <div class="input-row">
        <div class="item-label" v-text="treeTitle"></div>
        <div class="item-data">
            <div class="search-ctr size-xxlg">
                <el-input
                    v-model="nodeName"
                    placeholder="돋보기 버튼을 클릭해 선택해주세요."
                    @focus="activeYn = true"
                    @blur="activeYn = false"
                    :class="[activeYn ? activeClass : '']"
                    :readonly="true"
                ></el-input>
                <button class="btn-search" type="button" @click="dialogVisible = true">
                    <i class="material-icons search-icon">search</i>
                </button>
            </div>
        </div>
        <el-dialog :visible.sync="dialogVisible" :title="treeTitle + '선택'" width="50rem" @close="reload()">
            <div v-show="dialogVisible">
                <!-- scroll area -->
                <div class="scroll-element tree-modal" v-bar="{ preventParentScroll: true }">
                    <!-- el1 -->
                    <div>
                        <!-- el2 -->
                        <!-- your scrollable content -->
                        <the-tree ref="tree" editType="select" :type="treeType"></the-tree>
                    </div>
                </div>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="setTreeData()">선택</el-button>
                <el-button @click="reset()">닫기</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
export default {
    props: {
        treeTitle: {
            type: String,
            required: false,
            default: "트리선택",
        },
        paramName: {
            type: String,
            required: false,
        },
        treeType: {
            type: String,
            required: false,
        },
    },
    data() {
        return {
            activeYn: false,
            dialogVisible: false,
            nodeName: "",
            selectedNode: {},
            activeClass: "active",
        };
    },
    mounted() {},
    methods: {
        /* 부모에게 선택한 노드 데이터를 보내줍니다. */
        setTreeData() {
            let node = this.$refs.tree.getSelectedData()[0];

            this.nodeName = node.data.text;

            this.$emit("setTreeData", node);

            this.reload();
        },

        reset() {
            this.$emit("setTreeData", null);

            this.nodeName = "";

            this.reload();
        },

        async reload() {
            this.dialogVisible = false;

            await this.$forceUpdate();
        },
    },
};
</script>
