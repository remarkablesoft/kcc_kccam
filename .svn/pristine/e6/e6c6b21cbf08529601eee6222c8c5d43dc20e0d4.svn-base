<template>
    <div>
        <!-- **모바일** Contact 오픈 버튼 -->
        <div class="floating-btn mob-visible btn-open" @click="openContact()">
            <button type="button" class="btn">
                <span class="material-icons">
                    contacts
                </span>
            </button>
        </div>

        <div class="contact-container" :class="{ active: scroll, open: clickOpen }" @click.self="closeContact()">
            <div class="inner">
                <!-- 모바일에서만 사용되는 X 버튼 -->
                <div class="btn-wrap mob-visible">
                    <el-button type="icon-only" class="btn-close" @click="closeContact()">
                        <span class="icon material-icons main-color">clear</span>
                    </el-button>
                </div>
                <div class="tit-bb">{{ $t(`user_contact_tit`) }}</div>
                <div class="contact-list-group">
                    <div class="contact-list" v-for="(branch, branchIndex) in branchList" :key="branchIndex">
                        <div class="tit">{{ $t(`user_contact_businessOffice`) }}</div>
                        <div class="inner-list">
                            <div class="list-item">
                                <el-button type="black" size="xsmall" round v-text="branch.name" :disabled="false">
                                    <!-- 리마커블 영업소 --></el-button
                                >
                                <ul class="info-list">
                                    <li>
                                        <span class="txt main-color">{{ $t(`user_contact_tel`) }}</span>
                                        <a :href="`tel:${branch.tel}`" class="txt" v-text="branch.tel"><!-- 02.1111.2222 --></a>
                                    </li>
                                    <li>
                                        <span class="txt main-color">{{ $t(`user_contact_addr`) }}</span>
                                        <span class="txt" v-text="branch.addr"><!-- kccworld@kcc.com --></span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="contact-list" v-for="(manager, managerIndex) in managerList" :key="managerIndex + 10">
                        <div class="tit">{{ $t(`user_contact_manager`)}}</div>
                        <div class="inner-list">
                            <div class="list-item">
                                <el-button type="black" size="xsmall" round v-text="manager.name" :disabled="false">
                                    <!-- 리마커블 영업소 --></el-button
                                >
                                <ul class="info-list">
                                    <li>
                                        <span class="txt main-color">{{ $t(`user_contact_tel`) }}</span>
                                        <a :href="`tel:${manager.tel}`" class="txt" v-text="manager.tel"><!-- 02.1111.2222 --></a>
                                    </li>
                                    <li>
                                        <span class="txt main-color">{{ $t(`user_contact_email`) }}</span>
                                        <span class="txt" v-text="manager.email"><!-- kccworld@kcc.com --></span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    props: {
        branchList: {
            required: true,
        },
        managerList: {
            required: true,
        },
    },
    data() {
        return {
            // scroll
            scroll: false,

            // open
            clickOpen: false,

            // contact list
            contactList: [
                {
                    title: "영업소",
                    innerList: [
                        {
                            title: "리마커블 영업소",
                            tel: "02.1111.2222",
                            email: "kccworld@kcc.com",
                            disabled: false,
                        },
                    ],
                },
                {
                    title: "담당자",
                    innerList: [
                        {
                            title: "최원준",
                            tel: "02.1111.2222",
                            email: "kccworld@kcc.com",
                            disabled: true,
                        },
                        {
                            title: "최지원",
                            tel: "02.1111.2222",
                            email: "kccworld@kcc.com",
                            disabled: true,
                        },
                    ],
                },
            ],
        };
    },
    created() {
        if (!process.client) {
            return;
        }
        window.addEventListener("resize", this.onResize);
    },
    mounted() {
        if (!process.client) {
            return;
        }
        // 스크롤 관련
        window.addEventListener("scroll", this.onScroll);
    },
    destroyed() {
        if (!process.client) {
            return;
        }
        window.removeEventListener("resize", this.onResize);
    },
    beforeDestroy() {
        if (!process.client) {
            return;
        }
        // 스크롤 관련
        window.removeEventListener("scroll", this.onScroll);
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },

        // 스크롤 관련
        onScroll(e) {
            // let scrollPosition = window.scrollY || document.documentElement.scrollTop;

            let scrollPosition = document.querySelector("html").scrollTop;
            let contentHeaderHeight = document.querySelector(".content-header").scrollHeight;
            // console.log(scrollPosition, contentHeaderHeight);
            let windowWidth = document.documentElement.clientWidth;

            if (scrollPosition >= contentHeaderHeight && windowWidth >= 1024) {
                this.scroll = true;
            } else {
                this.scroll = false;
            }
        },

        openContact() {
            this.clickOpen = true;
            this.scroll = false;

            let windowWidth = document.documentElement.clientWidth;

            // 모달처럼 열릴 때 스크롤 막기 by lje
            if (windowWidth <= 1024) {
                document.documentElement.style.overflow = "hidden";
            }
        },

        closeContact() {
            this.clickOpen = false;

            let windowWidth = document.documentElement.clientWidth;

            // 스크롤 풀기 by lje
            if (windowWidth <= 1024) {
                document.documentElement.style = "auto";
            }
        },
    },
};
</script>
<style></style>
