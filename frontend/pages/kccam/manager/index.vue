<template>
    <div class="container">
        <ul>
<!--            <li>-->
<!--                <nuxt-link to="/kccam/user/main/user_main">-->
<!--                    <h1>KCC AM 사용자 페이지(퍼블리싱 완료)</h1>-->
<!--                </nuxt-link>-->
<!--            </li>-->
<!--            <li>-->
<!--                <nuxt-link to="/kccam/manager/login/manager_login">-->
<!--                    <h1>KCC AM 관리자 페이지(퍼블리싱 작업중)</h1>-->
<!--                </nuxt-link>-->
<!--            </li>-->
        </ul>
    </div>
</template>

<script>
export default {
    data() {
        return {};
    },
    validate() {
        let redirectUrl = "/kccam/manager/login/manager_login";
        $nuxt.$router.push( $nuxt.localePath (redirectUrl ) );
        return true;
    },
    mounted() {
    },
};
</script>

<style></style>
