<template>
	<view>
		<image src="../../static/logo-1.png" class="logo" mode="widthFix"></image>
		<view class="logo-title">EMOS在线办公小程序</view>
		<view class="logo-subtitle">V1.0.0</view>
		<button class="login-btn" open-type="getUserInfo" @tap="login()">登陆系统</button>
		<view class="register-container">
			没有账号？
			<text class="register" @tap="toRegister()">立即注册</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			toRegister:function() {
				uni.navigateTo({
					url:"../register/register"
				})
			},
			login: function() {
				let that = this
				uni.login({
					provider: "weixin",
					success: function(resp) {
						let code = resp.code
						that.ajax(that.url.login, "POST", {"code": code}, function(resp) {
							let permission = resp.data.permission
							uni.setStorageSync("permission", permission)
						})
						// TODO 跳转到登陆页面
						console.log("跳转到登陆页面")
					},
					fail: function(e) {
						console.log(e)
						uni.showToast({
							icon: "none",
							title: "执行异常"
						})
					}
				})
			}
		}
	}
</script>

<style lang="less">
	@import url("login.less");
</style>
