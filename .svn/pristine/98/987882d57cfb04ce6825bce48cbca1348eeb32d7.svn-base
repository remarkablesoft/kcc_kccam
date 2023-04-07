<template>
    <dropzone
        @vdropzone-file-added="onFileAdded"
        @vdropzone-error="onError"
        @vdropzone-success="onSuccess"
        @vdropzone-complete="onComplete"
        @vdropzone-duplicate-file="onDuplicate"
        @vdropzone-files-added="onFilesAdded"
        @vdropzone-success-multiple="onSuccessMultiple"
        @vdropzone-removed-file="onRemoveFile"
        @vdropzone-max-files-exceeded="onMaxFilesExceeded"
        @vdropzone-mounted="dropzoneMounted"
        ref="myDropzone"
        id="dropzone"
        :duplicateCheck="true"
        :options="dropzoneOptions"
        :useCustomSlot="true"
        :destroyDropzone="false"
    >
        <div class="dropzone-custom-content" :class="'fontSize' + fontSize">
            <!-- <h3 class="dropzone-custom-title">업로드 할 파일을 드래그하여 올려 주세요</h3> -->
            <!-- <div class="subtitle">(또는 여기를 클릭하셔서 PC에서 업로드 할 파일을 선택 하실수도 있습니다)</div> -->
            <h3 class="dropzone-custom-title">{{ $t('user_customerSupportInquiry_attach_dropzone_tit')}}</h3>
            <div class="subtitle">{{ $t('user_customerSupportInquiry_attach_dropzone_subtit')}}</div>
        </div>
    </dropzone>
</template>

<!-- https://www.npmjs.com/package/dropzone-nuxt -->
<!-- https://rowanwins.github.io/vue-dropzone/docs/dist/index.html#/installation -->

<script>
import swal from "sweetalert";

export default {
    props: {
        maxFiles: {
            type: Number,
            required: false,
            default: 100,
        },
        acceptedFiles: {
            type: String,
            required: false,
            default: "",
        },
        fileType: {
            type: String,
            required: false,
            default: "",
        },
        fontSize: {
            type: Number,
            required: false,
            default: 0,
        },
    },
    data() {
        return {
            dropzoneOptions: {
                url: "/storage/storageFile_upload.json",
                thumbnailWidth: 150,
                maxFilesize: 300, // MB 최대 용량
                headers: { "My-Awesome-Header": "header value" },
                addRemoveLinks: true,
                uploadMultiple: true,
                parallelChunkUploads: true,
                parallelUploads: 100,
                maxFiles: this.maxFiles, // 최대 파일 개수
                acceptedFiles: this.acceptedFiles, // 허용파일형식
                preventDuplicates: true,
                // dictRemoveFile:'파일 삭제하기',
                dictRemoveFile: '<i class="material-icons">close</i>',
            },
        };
    },
    methods: {
        onMaxFilesExceeded(file) {
            // console.log("onMaxFilesExceeded!!!!!!!!!!!", file);
            this.$refs.myDropzone.removeAllFiles();
            this.$refs.myDropzone.addFile(file);
        },
        onFileAdded(file) {
            // console.log ( file.name, file.size );

            let files = this.$refs.myDropzone.getAcceptedFiles();

            if (files.length) {
                files.forEach(fileData => {
                    if (fileData.name === file.name && fileData.size === file.size) {
                        swal("중복된 파일이 존재합니다", "", "warning");
                        this.$refs.myDropzone.removeFile(file);
                    }
                });
            }
        },
        onError(e) {
            // console.log("error", e);
        },
        onSuccess(e) {
            // console.log("success", e);
        },
        onComplete(e) {
            //console.log("complete", e);
        },
        onDuplicate(e) {
            // console.log("duplicate", e);
        },

        onFilesAdded(e) {
            // console.log("onFilesAdded", e);
        },

        // 서버에 파일을 올리고 리턴받은 리마커블의 스토리지파일정보
        onSuccessMultiple(dropzoneFiles, response) {
            // console.log("onSuccessMultiple", dropzoneFiles);
            // console.log("this.fileType", this.fileType);

            let fileInfoList = [];
            dropzoneFiles.forEach((file, index) => {
                file.fileInfo = this.dropzoneFileToRemarkableFile(response[index]);
                // console.log("file.fileInfo", file.fileInfo);
            });

            if (this.$refs.myDropzone.getAcceptedFiles().length > 0) {
                this.$refs.myDropzone.getAcceptedFiles().forEach(file => {
                    //console.log ( "남은파일 : " , file.fileInfo );
                    fileInfoList.push(file.fileInfo);
                });
            }

            this.$emit("setUploadFile", fileInfoList);
        },

        onRemoveFile(file) {
            // console.log("###### onRemoveFile", file);

            //삭제해서 남은 파일 리스트
            let fileInfoList = [];

            //삭제 하고 남은 파일들.
            if (this.$refs.myDropzone.getAcceptedFiles().length > 0) {
                //console.log ( "남은파일 갯수 : " + this.$refs.myDropzone.getAcceptedFiles().length  );
                this.$refs.myDropzone.getAcceptedFiles().forEach(file => {
                    //console.log ( "남은파일 : " , file.fileInfo );
                    fileInfoList.push(file.fileInfo);
                });
            }
	        this.$emit("setUploadFile", fileInfoList);
	        // console.log("onRemoveFile", fileInfoList);
        },

        /**
         * 업로드된 파일리스트를 가져와서 FileInfo 객체에 맞게 바꾸어서 반환합니다.
         */
        setUploadFileList: function(serverFileList) {
            var self = this;

            if (!Array.isArray(serverFileList) || serverFileList.length === 0) {
                return;
            }

            serverFileList.forEach(fileInfo => {
                //console.log ( fileInfo );
                var file = self.remarkableFileToDropzoneFile(fileInfo);

                if (file == null) {
                    return true; // continue;
                }

                //console.log ( self.getStorageThumbnailUrl( fileInfo ) );
                // 수동으로 파일을 넣어둔다.
                this.$refs.myDropzone.manuallyAddFile(file, self.getStorageThumbnailUrl(fileInfo));

                //this.$refs.myDropzone.emit("thumbnail", file, self.getStorageThumbnailUrl( fileInfo ) );
            });
        },

        // remarkableFile을 dropzoneFile로 변경
        remarkableFileToDropzoneFile: function(fileInfo) {
            if (this.$common.isEmpty(fileInfo) || this.$common.isEmpty(fileInfo.storageFileUid)) {
                return null;
            }

            let file = {};
            file.name = fileInfo.fileName;
            file.size = fileInfo.fileSize;

            file.accepted = true;
            file.processing = true;
            file.status = "success";
            file.lastModifiedDate = "";

            file.fileInfo = {};
            file.fileInfo.fileName = fileInfo.fileName;
            file.fileInfo.fileSize = fileInfo.fileSize;
            file.fileInfo.storageFileUid = fileInfo.storageFileUid;
            file.fileInfo.fileExt = fileInfo.fileExt;
            file.fileInfo.fileType = fileInfo.fileType;
            file.fileInfo.thumbUrl = fileInfo.thumbUrl;

            return file;
        },

        // dropzoneFile을 remarkableFile로 변경
        dropzoneFileToRemarkableFile: function(fileData) {
            if (this.$common.isEmpty(fileData) || this.$common.isEmpty(fileData.storageFileUid)) {
                return null;
            }

            let fileInfo = {};
            fileInfo.storageFileUid = fileData.storageFileUid;
            fileInfo.fileName = fileData.name;
            fileInfo.fileSize = fileData.fileSize;
            fileInfo.fileExt = fileData.type;
            fileInfo.thumbUrl = fileData.thumbUrl; //"/thumbnail_image/20201130000000000004/200_200/true";
            fileInfo.fileType = this.$common.isEmpty(fileData.fileType) ? this.fileType : "";

            return fileInfo;
        },

        getStorageThumbnailUrl: function(fileInfo, thumbSize) {
            var imageExts = "jpeg,jpg,bmp,gif,png";

            if (!fileInfo || !fileInfo.storageFileUid || imageExts.indexOf(fileInfo.fileExt.toLowerCase()) === -1) {
                return "";
            }

            return "/thumbnail_image/" + fileInfo.storageFileUid + "/200_200/true";
        },

        //외부에서 파일 정보 가져오기
        getFiles: function() {
            return this.$refs.myDropzone.getAcceptedFiles();
        },

        //외부버튼에서 파일지우기
        removeFile: function(file) {
            this.$refs.myDropzone.removeFile(file);
        },

        //외부에서 파일 모두 지우기
        removeAllFiles: function() {
            this.$refs.myDropzone.removeAllFiles();
        },

        // 드랍존 마운트 시점 확인
        dropzoneMounted() {
            this.$emit("dropzoneMounted");
        },

    },
};
</script>

<style lang="scss">
// variables
$defaultGrayBorderColor: #ebebeb;
$gradientColor1: rgba(186, 152, 234, 1);
$gradientColor2: rgba(98, 96, 252, 1);

@mixin bg-gradient() {
    background: $gradientColor1;
    background: -moz-linear-gradient(-45deg, $gradientColor1 0%, $gradientColor2 100%);
    background: -webkit-gradient(
        left top,
        right bottom,
        color-stop(0%, $gradientColor1),
        color-stop(100%, $gradientColor2)
    );
    background: -webkit-linear-gradient(-45deg, $gradientColor1 0%, $gradientColor2 100%);
    background: -o-linear-gradient(-45deg, $gradientColor1 0%, $gradientColor2 100%);
    background: -ms-linear-gradient(-45deg, $gradientColor1 0%, $gradientColor2 100%);
    background: linear-gradient(135deg, $gradientColor1 0%, $gradientColor2 100%);
}

/*=== dropzone ===*/
.dropzone {
    min-height: 12rem;
    padding: 0 0.6rem;

    .dz-preview,
    .dz-image {
        min-height: 10rem;
        max-height: 10rem;
        width: 10rem;
        height: 10rem;
        border-radius: 1rem !important;
    }

    .dz-preview {
        margin: 1.1rem;

        .dz-image {
            img {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
            }
        }
        .dz-details {
            padding: 0.5rem;

            .dz-size {
                margin: 0;
                font-size: 1.4rem;
            }
        }
    }

    .dz-message {
        margin: 4rem 0;

        // font-size setting
        .dropzone-custom-content {
            &.fontSize0 {
                .dropzone-custom-title {
                    font-size: 1.8rem;
                }
                .subtitle {
                    font-size: 1.4rem;
                }
            }
            &.fontSize1 {
                .dropzone-custom-title {
                    font-size: 1.3rem;
                }
                .subtitle {
                    font-size: 1.1rem;
                }
            }
            &.fontSize2 {
                .dropzone-custom-title {
                    font-size: 1.5rem;
                }
                .subtitle {
                    font-size: 1.2rem;
                }
            }
            &.fontSize3 {
                .dropzone-custom-title {
                    font-size: 2rem;
                }
                .subtitle {
                    font-size: 1.6rem;
                }
            }
        }
    }
}

/*=== vue dropzone ===*/
.vue-dropzone {
    border-width: 0.2rem;
    border-color: $defaultGrayBorderColor;
    background: #f9f9f9;

    > .dz-preview {
        .dz-details {
            @include bg-gradient();
            border-radius: 1rem;
            transition: 0.3s;

            .dz-filename {
                font-size: 1.5rem;
            }
        }

        .dz-remove {
            display: flex;
            align-items: center;
            justify-content: center;
            bottom: 1rem;
            right: 1rem;
            width: 2.5rem;
            height: 2.5rem;
            padding: 0;
            border-width: 0.3rem;
            border-radius: 50%;
            cursor: pointer !important;

            i {
                font-size: 1.8rem;
                cursor: pointer !important;
            }

            &:hover {
                text-decoration: none;
            }
        }

        .dz-download {
            opacity: 0;
            display: inline-block;
            width: 3rem;
            height: 3rem;
            position: absolute;
            bottom: 0.7rem;
            right: 3.8rem;
            z-index: 100;
            color: #fff;

            i {
                font-size: 3rem;
                cursor: pointer !important;
            }
        }

        &:hover .dz-download {
            opacity: 1;
        }
    }
}
</style>
