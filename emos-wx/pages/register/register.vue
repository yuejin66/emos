<template>
	<view>
		<image src="../../static/logo-2.png" mode="widthFix" class="logo"></image>
		<view class="register-container">
			<input type="text" placeholder="请输入你的邀请码" class="register-code"
					maxlength="6" v-model="registerCode"/>
			<view class="register-desc">管理员创建员工证账号之后，您可以从个人邮箱中获得注册邀请码</view>
			<button class="register-btn" open-type="getUserInfo" @tap="register()">执行注册</button>
		</view>
	</view>		
</template>

<script>
	export default {
		data() {
			return {
				registerCode: ""
			};
		},
		methods: {
			register:function() {
				//
				let that = this
				if (that.registerCode == null || that.registerCode.length == 0) {
					uni.showToast({
						icon: "none",
						title: "邀请码不能为空"
					})
					return
				} else if (/^[0-9]{6}$/.test(that.registerCode) == false) {
					uni.showToast({
						icon: "none",
						title: "邀请码必须为6位数字"
					})
					return
				}
				// 登陆
				uni.login({
					provider:"weixin",
					success:function(resp) {
						let code = resp.code
						// 获取用户信息
						uni.getUserInfo({
							provider:'weixin',
							success:function(resp) {
								let nickName = resp.userInfo.nickName
								let avatarUrl = resp.userInfo.avatarUrl
								let data = {
									code: code,
									nickName: nickName,
									photo: avatarUrl,
									registerCode: that.registerCode
								}
								that.ajax(that.url.register, "POST", data, function(resp) {
									let permission = resp.permission
									uni.setStorageSync("permission", permission)
									console.log(permission)
									console.log("跳转到index页面")
								})
							}
						});
					}
				});
			}
		}
	}
</script>

<style lang="less">
	@import url("register.less");
</style>
