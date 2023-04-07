<_route>
</_route>
<script>
export default {
	beforeCreate() {
		const param = this.$route.params.route

		if ( this.$common.isEmpty( param ) || "manager" !== param ) {
			this.$nuxt.error({ statusCode: 404, message: "Page Does Not Exist" })
		}
		else {
			this.$router.push("/kccam/manager/login/manager_login");
		}
	}
}
</script>
<style scoped>
</style>
