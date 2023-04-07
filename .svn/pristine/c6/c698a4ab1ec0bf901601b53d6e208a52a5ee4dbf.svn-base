<template>
    <!-- 참고자료 추가 모달 -->
    <el-dialog title="참고자료 추가" :visible.sync="dialogVisible" width="80rem">
        <div class="inner-content">
            <div class="input-area">
                <div class="input-row-md">
                    <div class="input-label">
                        <span>참고자료 제목<em class="required">*</em></span>
                    </div>
                    <div class="input-data">
                        <el-input placeholder="" v-model="referenceTitle" clearable> </el-input>
                    </div>
                </div>
                <!-- dropzone -->
                <the-dropzone
                    class="input-dropzone"
                    ref="dropzoneFile"
                    @setUploadFile="setStorageFileList"
                    :fileType="$amConstant.OBJECT_TYPE.ONE_TO_ONE"
                />
            </div>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button size="default" type="primary">확인</el-button>
            <el-button size="default" @click="dialogVisible = false">취소</el-button>
        </div>
    </el-dialog>
</template>

<script>
import theDropzone from "~/components/common/dropzone/TheDropZone.vue";

export default {
    props: {},
    components: {
        theDropzone,
    },
    data() {
        return {
            dialogVisible: false,

            // 참고자료 제목
            referenceTitle: "",
        };
    },
    created() {},
    mounted() {},
    watch: {},
    methods: {
        open() {
            this.dialogVisible = true;
        },
    },
};
</script>

<style></style>
