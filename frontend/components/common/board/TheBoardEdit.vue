<template>
	<div class="inner-wrapper">
		<div v-if="loadingIndicator > 0" class="loading-container">
			<loading/>
		</div>
		<div class="inner-container sub">
			<!-- content-header :: 클래스로 배경 변경. -->
			<div class="content-header sub intro">
				<div class="inner default-w">
					<div class="tit-area">
						<h3 class="tit" v-text="editModalTitle">공통 게시판 등록</h3>
					</div>
				</div>
            </div>
			<!-- content-body -->
            <div class="content-body">
                <!-- <div class="tit-area">
                    <div class="inner default-w">
                        <h3 class="tit">공지사항 등록</h3>
                    </div>
                </div> -->

                <!-- sub-content -->
                <div class="sub-content">
                    <div class="inner default-w"><!-- defalut-w 필요시 사용. -->
                        <div class="inner-content">
                            <!-- 내용은 여기에. -->
                            <table class="table-normal board-edit">
                                <colgroup>
                                    <col style="width:20%">
                                    <col style="width:30%">
                                    <col style="width:20%">
                                    <col style="width:30%">
                                </colgroup>
                                <tbody>
                                    <!--data-list-row-->
                                    <tr>
                                        <th scope="row">등록자</th>
                                        <td>
                                            <div class="info-area" v-text="inputUser"><!-- 관리자(admin) --></div>
                                        </td>
                                        <th scope="row">이메일</th>
                                        <td>
                                            <div class="info-area" v-text="email"><!-- admin@adimin.com --></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">제목<em class="required">*</em></th>
                                        <td colspan="3">
                                            <div class="input-area">
												<el-input v-model="title" class="size-full"></el-input>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr v-if="'Y'===$route.query.noticeUseYn">
                                        <th scope="row">공지여부</th>
										<td colspan="3">
											<div class="input-area">
												<el-radio-group v-model="radioType" class="ml10">
													<el-radio :label="1" v-if="'Y'===$route.query.noticeMustUseYn">필독</el-radio>
													<el-radio :label="2">공지</el-radio>
													<el-radio :label="3">일반</el-radio>
												</el-radio-group>
											</div>
										</td>
									</tr>
                                    <tr v-if="'Y'===$route.query.anonymousUseYn">
                                        <th scope="row">비밀글</th>
                                        <td colspan="3">
                                            <div class="input-area flex-item">
                                                <el-radio-group v-model="lockYnRadio" class="ml10">
                                                    <el-radio :label="1">사용</el-radio>
                                                    <el-radio :label="2">미사용</el-radio>
                                                </el-radio-group>
                                                <!-- 비밀글 사용일 시 비밀번호 입력창 -->
												<el-input v-model="passwordInput" class="size-sm"></el-input>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">내용<em class="required">*</em></th>
                                        <td colspan="3">
                                            <editor ref="froalaEditor"></editor>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">첨부파일</th>
                                        <td colspan="3">
											<theDropzone ref="dropzoneFile"
														 acceptedFiles=".doc,.docx,.xls,.xlsx,.ppt,.pptx,.hwp,.pdf,.txt,.jpeg,.jpg,.png,.gif"
														 @setUploadFile="setStorageFileList"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>

                            <!-- btn -->
							<div class="btn-area right">
								<el-button size="regular" type="primary" @click="save()">등록</el-button>
								<el-button size="regular" type="st" @click="cancel()">취소</el-button>
							</div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import loading from "~/components/common/TheLoading.vue";
import editor from "~/components/common/board/TheEditorScript.vue";
import theDropzone from "~/components/common/dropzone/TheDropZone.vue";

const URL_SAVE = "/lms/api/v1/lmsBoardApi_savePosting";
const URL_GET = "/lms/api/v1/lmsBoardApi_getPosting";

export default {
    components: {
        loading,
        editor,
        theDropzone,
    },
    props: {
        boardOid: {
            type: String,
            default: "",
        },
        boardType: {
            type: String,
            default: "",
        },
        boardName: {
            type: String,
            default: "",
        },
    },
	data() {
		return {
			editModalTitle : `${ this.boardName } 등록`,
			oid            : "",
			title          : "",

			radioType    : "notice_level_no",
			fileInfoList : [],
			noticeLevel  : 0,
			inputUser    : "",							// 등록자 - 이름(id)
			email        : "",								// 이메일

			lockYnRadio: 1,
            passwordInput: "",
        };
    },
    created() {
        if (!process.browser) {
            return;
        }

	},
    computed: {
        loadingIndicator() {
            // window는 ssr에서는 사용하지 않기 위해
            if (!process.browser) {
                return;
            }

            return this.$nuxt.$root.$loading.percent;
        },
    },
    mounted() {

		let oid = this.$route.query.oid;

        if (this.$common.isNotEmpty(oid)) {
            this.getData(oid);
        }

		//처음부터 로그인한 정보로 입력되어 있도록!!!
        this.inputUser = `${this.$store.state.login.loginUser.userName}(${this.$store.state.login.loginUser.userId})`;
       	this.email = this.$store.state.login.loginUser.userEmail;
    },
    methods: {
        // 파일리스트에서 Dropzone 파일 업로드 후 호출
        setStorageFileList(data) {
            this.fileInfoList = data;
        },

        /* 공지타입(noticeLevel) int타입을 리턴해줍니다. */
        settingRadioType() {

			if ("notice_level_no" === this.radioType) {

				this.noticeLevel = this.noticeLevel;
			}
            else {

				this.noticeLevel = 1;
            }

			return this.noticeLevel;
        },

        /**
         * 등록 버튼 클릭시
         */
        save() {
            if (this.$common.isEmpty(this.title)) {

                this.$common.confirmSwal("제목을 입력해주세요.", "", "warning" );
                return false;
            }

            if (this.$common.isEmpty(this.$refs.froalaEditor.contents)) {

				this.$common.confirmSwal("내용을 입력해주세요.", "", "warning" );
                return false;
            }

            let noticeLevel = this.settingRadioType();

            let fileList = this.fileInfoList;
            let contentsFileList = this.getEditorImageList();

            let param = {
                oid: this.oid,
                inputUser: this.$store.state.login.loginUser.userOid,
                title: this.title,
                noticeLevel: noticeLevel,
                contents: this.$refs.froalaEditor.contents,
                boardOid: this.boardOid,
                fileList: fileList,
                contentsFileList: contentsFileList,
            };

            this.$axios.post(URL_SAVE, param).then((res) => {
                this.$common.confirmSwal("등록 완료!", this.boardName + "이 등록되었습니다.", "success");
                this.initData();
                this.goList();
            });
        },

        /* 목록 화면으로 이동합니다. */
        goList() {
//             if (this.$constant.BOARD_TYPE.NOTICE.TYPE === this.boardType) {
//                 this.$router.push("/survey/notice/surveyNotice_list");
//             } else if (this.$constant.BOARD_TYPE.DATA.TYPE === this.boardType) {
//                 this.$router.push("/survey/dataRoom/surveyDataRoom_list");
//             }

        	this.$router.push("/lms/boardTemplate/boardList/boardList");
        },

        /* 데이터를 초기화해줍니다. */
        initData() {
            this.title = "";
            this.fileInfoList = [];
            this.$refs.froalaEditor.contents = "";
        },

        /**
         * 취소버튼 클릭시
         */
        cancel() {
            this.initData();
            this.goList();
        },

        /**
         * 수정을 위한 데이터를 가져옵니다.
         */
        getData(oid) {
            this.editModalTitle = `${this.boardName} 수정`;

            let param = {
                oid: oid,
            };

            this.$axios.post(URL_GET, param).then((res) => {
                if (this.$common.isEmpty(res.data)) {
                    return;
                }

                this.oid = res.data.oid;
                this.title = res.data.title;
                this.radioType = 0 === res.data.noticeLevel ? "notice_level_no" : "notice_level_noti";
                this.$refs.froalaEditor.contents = res.data.contents;
                this.$refs.dropzoneFile.setUploadFileList(res.data.fileList);
                this.fileInfoList = res.data.fileList;
            });
        },

        /* 에디터에 이미지 리스트를 추출합니다. */
        getEditorImageList() {
            let content = this.$refs.froalaEditor.contents;
            let imgs = $(content).find("img");
            let imageList = [];
            let contentsFileList = [];

            //해당 이미지의 파일명과 storageFileUid 가져오기
            imgs.each(function (index, item) {
                let array = item.attributes[0].value.split("/");
                let size = array.length;
                let temp = array[size - 2] + "/" + array[size - 1];

                imageList.push(temp);
            });

            //FileInfo 형태로 만들어주기.
            imageList.forEach((item, index) => {
                let temp = item.split("/");
                let fileExt = temp[0].split(".");

                let fileInfo = {};
                fileInfo.fileName = temp[0];
                fileInfo.storageFileUid = temp[1];
                fileInfo.fileExt = fileExt[1];
                fileInfo.fileType = this.$constant.FILE_TYPE_CONTENTS;

                contentsFileList.push(fileInfo);
            });

            return contentsFileList;
        },
    },
};
</script>
