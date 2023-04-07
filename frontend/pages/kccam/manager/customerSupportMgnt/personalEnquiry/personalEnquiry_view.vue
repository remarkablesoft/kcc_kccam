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
                    <h2>1:1 문의 목록</h2>
                </div>
            </div>
            <div class="content-detail">
                <!-- table -->
                <div class="table-area">
                    <div class="table-row-md">
                        <div class="table-header">
                            <div class="left-area">
                                <span class="tit">문의 정보</span>
                            </div>
                            <div class="right-area">
                                <!-- btn -->
                                <el-button type="primary" size="default" @click="sendMailVisible = true">문의자에게 메일 보내기</el-button>
                                <el-button type="st st-primary" size="default" @click="goClientView">문의고객정보 확인하기</el-button>
                                <el-button type="st st-primary" size="default" @click="goList">목록</el-button>
                            </div>
                        </div>
                        <div class="table-body" v-if="$common.isNotEmpty(clientInfo)">
                            <div class="manager-table-custom">
                                <table>
                                    <colgroup>
                                        <col style="width: 15%" />
                                        <col style="width: 35%" />
                                        <col style="width: 15%" />
                                        <col style="width: 35%" />
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th scope="row"><span>문의제목</span></th>
                                            <td colspan="3" class="tl"><span v-text="clientInfo.title"></span></td>
                                        </tr>
                                        <tr>
                                            <th scope="row"><span>문의종류</span></th>
                                            <td><span v-text="clientInfo.customField1"></span></td>
                                            <th scope="row"><span>문의일시</span></th>
                                            <td><span v-text="clientInfo.inputDate"></span></td>
                                        </tr>
                                        <tr>
                                            <th scope="row"><span>문의자 이메일</span></th>
                                            <td><span v-text="clientInfo.inputUserInfo.email"></span></td>
                                            <th scope="row"><span>수신자 이메일</span></th>
                                            <td>
	                                            <span v-for="(item, index) in clientInfo.receiverEmailList" :key="index">{{ item.configReceiverEmail }}<br></span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row"><span>추가 문의 종류</span></th>
                                            <td><span v-text="clientInfo.oneToOneDetailList[0].detailQuestion"></span></td>
                                            <th scope="row"><span>추가 문의 내용</span></th>
                                            <td><span v-text="clientInfo.oneToOneDetailList[0].detailAnswer"></span></td>
                                        </tr>
                                        <tr>
                                            <th><span>문의 내용</span></th>
                                            <td colspan="3" class="tl">
                                                <p v-text="clientInfo.descr"></p>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="upload-area" v-if="$common.isNotEmpty(clientInfo.fileList)">
                    <div class="upload-header">
                        <div class="left-area">
                            <span class="tit">첨부파일</span>
                        </div>
                    </div>
                    <ul class="common-file-list">
                        <!-- list-item :: 반복되는 부분 li를 클릭하면 다운로드 -->
                        <li class="list-item" v-for="(item, index) in clientInfo.fileList" :key="item.oid" @click="fileDownload( item )">
                            <em class="file-name" v-text="item.fileName"></em>
                            <i class="material-icons">get_app</i>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- --문의자에게 메일 보내기 -->
        <the-send-mail-modal
	        v-if="sendMailVisible"
	        :sendMailVisible="sendMailVisible"
	        :receiver-info="clientInfo"
	        @close="sendMailVisible = false" />
    </div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
// import theDropzone from "~/components/common/dropzone/TheDropZone.vue";
import theSendMailModal from "~/components/kccam/manager/modal/TheSendMailModal.vue";

export default {
    layout: "managerLayout",
    components: {
        theLoading,
        // theDropzone,
        theSendMailModal,
    },
    data() {
        return {
            // 문의자에게 메일보내기
            sendMailVisible: false,

            // 고객 정보
            clientInfo: {},
            clientMail: "",
            receiverMail: "",
            detailQuestion: "",
            detailAnswer: "",

            oid: "",
        };
    },
    created() {
        // 넘어온 oid 로 fillClientInfo()
        if (this.$route.query.oid) {
            this.oid = this.$route.query.oid;
            this.fillClientInfo();
        }
    },
    methods: {
        // 목록 페이지로 이동
        goList() {
            this.$router.push( this.localePath( "/kccam/manager/customerSupportMgnt/personalEnquiry/personalEnquiry_list" ) );
        },
        /**
         * 문의 고객 정보 확인 버튼
         */
        goClientView() {
            const url = this.$urlConstant.MENU_URL_PREFIX.MANAGER_CUSTOMER_SUPPORT_MGNT + this.$urlConstant.MENU_URL_SUFFIX.CUSTOMER_SUPPORT_MGNT.INFORMATION_EDIT;

            let queryParam = {
                path: url,
                query: {
                    oid: this.clientInfo.inputUser,
                },
            };
            this.$router.push( this.localePath( queryParam ) );
        },
        /**
         * 고객 정보 가져오기
         */
        fillClientInfo() {
            const url = this.$urlConstant.API_URL_PREFIX.ONE_TO_ONE + this.$urlConstant.API_URL_SUFFIX.ONE_TO_ONE.GET;
            let cnd = {
                oid: this.oid,
                fillDetail: true,
            };
            let _self = this;
            this.$axios.post(url, cnd).then(res => {
                    if (200 === res.status && _self.$common.isNotEmpty(res.data)) {
                        _self.clientInfo = res.data;
                    }
                })
                .catch(error => { console.log(error); });
        },
	    /**
	     * 문의내역 내 첨부파일 다운로드
	     */
	    fileDownload( item ) {
		    location = "/storage/storageFile_fileDown/" + item.fileName + "/" + item.storageFileUid;
	    },
    },
};
</script>

<style scoped></style>
