<template>
    <div>
        <froala id="edit" :tag="'textarea'" :config="config" v-model="contents" ref="test"></froala>
    </div>
</template>

<script>
export default {
    props : {
        htmlContents : {
            type : String,
            required : false,
            default : ""
        },
	    orderNo : {
		    type : Number,
		    required : false,
		    default : -1,
	    },
	    editorStyle : {
        	type: Object,
		    required : false,
		    default() {
        		return { minHeight: 300 }
		    }
	    }
    },
    data() {
        return {
            contents: "",
            config: {},
        };
    },
	watch : {
		htmlContents(){
			this.contents = this.htmlContents;
		},
	},
    created() {
        if (!process.browser) {
            return;
        }

//         var FroalaEditor = require("wysiwyg-editor-node-sdk/lib/froalaEditor.js");

        this.config = {
            key: "kRB4zB1B1E1E1F1B1qYFa1UQRFQIVc2MSMd1IWPNb1IFd1yD2I2D1A2C7E2D2C5F1G2",
            theme: "gray",
            language: "ko", // https://froala.com/wysiwyg-editor/languages/
            attribution: false, //  하단 Powered by Froala 제거
            fontFamily: {
                // https://froala.com/wysiwyg-editor/examples/font-family/
                "맑은 고딕": "맑은 고딕",
                굴림: "굴림",
                dotum: "돋움",
                나눔고딕: "나눔고딕",
                바탕: "바탕",
                궁서: "궁서",
                "Malgun Gothic": "Malgun Gothic",
                gulim: "gulim",
            },

            heightMin: this.editorStyle.minHeight,
            //heightMax: 300,
            //height : 300,

            quickInsertTags: "null", // quickInsertTags는 안나오도록

            imageEditButtons: ["imageDisplay", "imageAlign", "imageInfo", "imageRemove"],

            imageUploadURL: "/storage/storageFile_froalaUpload.json",
            imageAllowedTypes: ["jpeg", "jpg", "png", "gif"],
            imageMaxSize: 20 * 1024 * 1024, // 20M

            fileUploadURL: "/storage/storageFile_froalaUpload.json",
            fileAllowedTypes: [
                "text/plain",
                "application/pdf",
                "application/x-pdf",
                "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/vnd.openxmlformats-officedocument.presentationml.presentation",
                "application/vnd.hancom.hwp",
                "application/x-hwp",
                "application/haansofthwp",
                "application/vnd.ms-excel",
                "application/vnd.ms-powerpoint",
            ],
            fileMaxSize: 50 * 1024 * 1024, // 50M

            // 동영상업로드는 주석처리
            videoUpload: false,
            //videoUploadURL : '/image/storageFile_upload3.json',
            //videoAllowedTypes: ['webm', 'mp4', 'ogg'],
            //videoMaxSize: 200 * 1024 * 1024, // 300M

            // 		   immediateVueModelUpdate : true,
            events: {
                "image.error": function (error, response) {
                    console.log("이미지 에러");
                    console.log(error);
                    console.log(response);
                },
                "file.unlink": function (link) {
                    // 			    	 console.log( "첨부파일 삭제" );
                    var $target = $(link);

                    let targetSrc = $target.attr("href");
                    // 				     console.log( targetSrc );

                    let storageFileUid = urlToStorageFileUid(targetSrc);
                    //console.log ( storageFileUid );

                    let param = {
                        storageFileUid: storageFileUid,
                    };

                    // 					  this.$axios.post( {
                    // 						url : "/storage/storageFile_remove.json",
                    // 						data : param
                    // 					 });
                },
                "image.removed": function ($img) {
                    // 					console.log( "이미지 삭제" );

                    let targetSrc = $img.attr("src");
                    //console.log( targetSrc );

                    let storageFileUid = urlToStorageFileUid(targetSrc);
                    //console.log ( storageFileUid );

                    let param = {
                        storageFileUid: storageFileUid,
                    };

                    // 				    this.$axios.post( {
                    // 						url : "/storage/storageFile_remove.json",
                    // 						data : param
                    // 					});
                },
                "image.uploaded": function (response) {
                    console.log("response", response);
                },

                "imageManager.imagesLoaded": function (data) {
                    // Do something here.
                    // this is the editor instance.
                    console.log("imagesLoaded", data);
                },

	            "initialized": function ( ) {
                	$nuxt.$emit("isMounted");
	            }

                /* 'video.removed': function ($video) {

			        console.log( "비디오 삭제" );

			        let targetSrc = $video.attr('src');
					console.log( targetSrc );

					let storageFileUid = urlToStroageFileUid( targetSrc );
					console.log ( storageFileUid );

					let param = {
						"storageFileUid" : storageFileUid
					};

					this.$axios.post( {
						url : "/storage/storageFile_remove.json",
						data : param
					});

			      } */
            },
        };

        function urlToStorageFileUid(targetSrc) {
            if (targetSrc == "" || targetSrc == null) {
                return;
            }

            var start = targetSrc.lastIndexOf("/");
            var storageFileUid = targetSrc.substring(start + 1, targetSrc.length);

            return storageFileUid;
        }

        this.contents = this.htmlContents;
    },

    methods: {
        sendContentData() {
        	let sendData = {
		        contents : this.contents,
		        orderNo : this.orderNo,
	        }
            this.$emit("sendContentData" , sendData );
        },

        setContent(param) {
            this.contents = param;
        },
    }
};
</script>
