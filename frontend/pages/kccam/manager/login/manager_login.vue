<template>
	<div class="inner-wrapper">
		<!-- loading -->
		<!--        <div class="loading-container" v-if="loadingIndicator > 0">-->
		<!--            <the-loading />-->
		<!--        </div>-->

		<!-- login-container -->
		<div class="manager-login-container">
			<div class="login-content">
				<!-- 내용은 여기에. -->
				<!-- <h1 class="login-logo"></h1> -->
				<div class="login-logo">
					<img
						src="@/assets/images/logo/logo_am_blue.png"
						alt="KCC AM LOGO"
					/>
				</div>

				<!-- login form -->
				<form class="login-form">
					<div class="login-form-label">
						<div class="login-id">
							<label for="id-input" class="control-label"
								>관리자 아이디</label
							>
							<el-input
								id="id-input"
								v-model="loginId"
								placeholder="관리자 아이디"
							></el-input>
						</div>
						<div class="login-password">
							<label for="password-input" class="control-label"
								>비밀번호</label
							>
							<el-input
								id="password-input"
								v-model="loginPwd"
								placeholder="관리자 비밀번호"
								@keypress.native.enter="login"
								show-password
							></el-input>
						</div>
					</div>
					<div class="login-form-button">
						<button
							type="button"
							class="login-button"
							@click="login"
						>
							LOG IN
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</template>

<script>
import theLoading from "~/components/common/loading/TheLoading.vue";
import { mapActions } from "vuex";

export default {
	// layout: "managerLayout",
	components: {
		theLoading
	},
	data() {
		return {
			loginId: "",
			loginPwd: ""
		};
	},
	methods: {
		...mapActions({
			loginUser: "login/loginUser"
		}),

		async login() {
			if (
				this.$common.isEmpty(this.loginId) ||
				this.$common.isEmpty(this.loginPwd)
			) {
				this.$common.confirmSwal("아이디/비밀번호를 입력해주세요.","","warning");
				return;
			}

			try {
				const url =
					this.$urlConstant.API_URL_PREFIX.LOGIN_API +
					this.$urlConstant.API_URL_SUFFIX.LOGIN_API.LOGIN;

				let param = {
					userId: this.loginId,
					pwd: this.$common.encryptAesValue(this.loginPwd)
				};

				await this.$axios
					.post(url, param)
					.then(res => {
						console.log(res);

						if ("true" === res.data.success) {
							let loginUser = {};
							loginUser.userId = this.loginId;
							loginUser.userOid = res.data.userOid;
							loginUser.userName = res.data.userName;

							this.loginUser(loginUser);
							location.href = "/kccam/manager/main/manager_main";
						} else {
							this.loginError = true;
							this.errorMsg = res.data.errorMessage;
							this.$common.confirmSwal(
								"login",
								"아이디 혹은 패스워드가 일치하지 않습니다.",
								"warning"
							);
						}
					})
					.catch(error => {
						console.log(error);
					});
			} catch (e) {
				this.returnMsg = e.message;
			}
		}
	}
};
</script>

<style scoped></style>
