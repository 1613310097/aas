<template>
	<div class="center-preview">
		<div class="center-title">{{ title }}</div>
		<div class="center-info">
			<div class="center-info-title">个人信息</div>
			<div class="img-box" v-if="userTableName=='jiaoshi'">
				<img :src="sessionForm.touxiang?baseUrl + sessionForm.touxiang:require('@/assets/avator.png')">
			</div>
			<div class="info-item1" v-if="userTableName=='jiaoshi'">
				<span class="icon iconfont "></span>
				<div class="label">教师工号：</div>
				<div class="text">{{sessionForm.jiaoshigonghao}}</div>
			</div>
			<div class="info-item2" v-if="userTableName=='jiaoshi'">
				<span class="icon iconfont "></span>
				<div class="label">教师姓名：</div>
				<div class="text">{{sessionForm.jiaoshixingming}}</div>
			</div>
			<div class="info-item3" v-if="userTableName=='jiaoshi'">
				<span class="icon iconfont "></span>
				<div class="label">性别：</div>
				<div class="text">{{sessionForm.xingbie}}</div>
			</div>
			<div class="info-item4" v-if="userTableName=='jiaoshi'">
				<span class="icon iconfont "></span>
				<div class="label">邮箱：</div>
				<div class="text">{{sessionForm.youxiang}}</div>
			</div>
			<div class="info-item5" v-if="userTableName=='jiaoshi'">
				<span class="icon iconfont "></span>
				<div class="label">联系手机：</div>
				<div class="text">{{sessionForm.lianxishouji}}</div>
			</div>
			<div class="info-item6" v-if="userTableName=='jiaoshi'">
				<span class="icon iconfont "></span>
				<div class="label">职称：</div>
				<div class="text">{{sessionForm.zhicheng}}</div>
			</div>
		
		</div>
	
		<el-tabs class="center-tabs" tab-position="left" @tab-click="handleClick">
			<el-tab-pane label="个人中心">
				<el-form class="center-preview-pv" ref="sessionForm" :model="sessionForm" :rules="rules" label-width="180px">
					<el-form-item class="center-item" v-if="userTableName=='jiaoshi'" label="教师工号" prop="jiaoshigonghao">
						<el-input v-model="sessionForm.jiaoshigonghao" placeholder="教师工号" readonly></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiaoshi'" label="教师姓名" prop="jiaoshixingming">
						<el-input v-model="sessionForm.jiaoshixingming" placeholder="教师姓名" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiaoshi'" label="性别" prop="xingbie">
						<el-select v-model="sessionForm.xingbie" placeholder="请选择性别" >
							<el-option v-for="(item, index) in dynamicProp.xingbie" :key="index" :label="item" :value="item"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiaoshi'" label="邮箱" prop="youxiang">
						<el-input v-model="sessionForm.youxiang" placeholder="邮箱" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiaoshi'" label="联系手机" prop="lianxishouji">
						<el-input v-model="sessionForm.lianxishouji" placeholder="联系手机" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiaoshi'" label="职称" prop="zhicheng">
						<el-select v-model="sessionForm.zhicheng" placeholder="请选择职称" >
							<el-option v-for="(item, index) in dynamicProp.zhicheng" :key="index" :label="item" :value="item"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiaoshi'" label="专业" prop="zhuanye">
						<el-select v-model="sessionForm.zhuanye" placeholder="请选择专业" >
							<el-option v-for="(item, index) in dynamicProp.zhuanye" :key="index" :label="item" :value="item"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiaoshi'" label="学期" prop="xueqi">
						<el-input v-model="sessionForm.xueqi" placeholder="学期" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiaoshi'" label="课程" prop="kecheng">
						<el-input v-model="sessionForm.kecheng" placeholder="课程" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiaoshi'" label="上课班级" prop="shangkebanji">
						<el-input v-model="sessionForm.shangkebanji" placeholder="上课班级" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiaoshi'" label="头像" prop="touxiang">
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="sessionForm.touxiang?sessionForm.touxiang:''"
							@change="jiaoshitouxiangHandleAvatarSuccess"
							></file-upload>
					</el-form-item>
					<el-form-item class="center-btn-item">
						<div class="updateBtn" type="primary" @click="onSubmit('sessionForm')">
							<span class="icon iconfont "></span>
							<span class="text">更新信息</span>
						</div>
						<div class="closeBtn" type="danger" @click="logout">
							<span class="icon iconfont "></span>
							<span class="text">取消</span>
						</div>
					</el-form-item>
				</el-form>
			</el-tab-pane>
			<el-tab-pane label="修改密码">
				<el-form class="center-preview-pv" ref="passwordForm" :model="passwordForm" :rules="passwordRules" label-width="180px">
					<el-form-item class="center-item" label="原密码" prop="password">
						<el-input type="password" v-model="passwordForm.password" placeholder="原密码"></el-input>
					</el-form-item>
					<el-form-item class="center-item" label="新密码" prop="newpassword">
						<el-input type="password" v-model="passwordForm.newpassword" placeholder="新密码"></el-input>
					</el-form-item>
					<el-form-item class="center-item" label="确认密码" prop="repassword">
						<el-input type="password" v-model="passwordForm.repassword" placeholder="确认密码"></el-input>
					</el-form-item>
					<el-form-item class="center-btn-item">
						<div class="updateBtn" type="primary" @click="updatePassword">
							<span class="icon iconfont "></span>
							<span class="text">修改密码</span>
						</div>
					</el-form-item>
				</el-form>
			</el-tab-pane>
			<el-tab-pane v-for="(item,index) in menuList" :key="index" v-if="hasBack(item.menu)" :label="item.child[0].menu" :name="item.child[0].tableName"></el-tab-pane>
		</el-tabs>

	</div>
</template>

<script>
	import config from '@/config/config';
	import menu from '@/config/menu';
	import Vue from 'vue';
	export default {
		//数据集合
		data() {
			return {
				title: '个人中心',
				baseUrl: config.baseUrl,
				sessionForm: {},
				passwordForm: {},
				passwordRules: {
					password: [
						{
							required: true,
							message: "密码不能为空",
							trigger: "blur"
						}
					],
					newpassword: [
						{
							required: true,
							message: "新密码不能为空",
							trigger: "blur"
						}
					],
					repassword: [
						{
							required: true,
							message: "确认密码不能为空",
							trigger: "blur"
						}
					]
				},
				rules: {},
				menuList: [],
				disabled: false,
				uploadUrl: config.baseUrl + 'file/upload',
				imageUrl: '',
				headers: {Token: localStorage.getItem('frontToken')},
				userTableName: localStorage.getItem('UserTableName'),
				dynamicProp: {},
			}
		},
		created() {
			let menus = menu.list()
			for(let x in menus){
				if(menus[x].tableName == this.userTableName){
					for(let i in menus[x].backMenu){
						if(menus[x].backMenu[i].menu=='考试管理'){
							menus[x].backMenu.splice(i,1)
						}
					}
					this.menuList = menus[x].backMenu
				}
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'jiaoshigonghao', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'mima', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'jiaoshixingming', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'xingbie', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'youxiang', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'lianxishouji', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'zhicheng', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'zhuanye', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'xueqi', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'kecheng', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'shangkebanji', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'touxiang', null);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.sessionForm, 'status', null);
			}

			if ('jiaoshi' == this.userTableName&&this.rules['jiaoshigonghao']){
				this.rules['jiaoshigonghao'].push({ required: true, message: '请输入教师工号', trigger: 'blur' })
			}else if('jiaoshi' == this.userTableName&&!this.rules['jiaoshigonghao']) {
				this.$set(this.rules, 'jiaoshigonghao', [{ required: true, message: '请输入教师工号', trigger: 'blur' }]);
			}
			if ('jiaoshi' == this.userTableName&&this.rules['mima']){
				this.rules['mima'].push({ required: true, message: '请输入密码', trigger: 'blur' })
			}else if('jiaoshi' == this.userTableName&&!this.rules['mima']) {
				this.$set(this.rules, 'mima', [{ required: true, message: '请输入密码', trigger: 'blur' }]);
			}
			if ('jiaoshi' == this.userTableName&&this.rules['jiaoshixingming']){
				this.rules['jiaoshixingming'].push({ required: true, message: '请输入教师姓名', trigger: 'blur' })
			}else if('jiaoshi' == this.userTableName&&!this.rules['jiaoshixingming']) {
				this.$set(this.rules, 'jiaoshixingming', [{ required: true, message: '请输入教师姓名', trigger: 'blur' }]);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.rules, 'youxiang', [{ required: false, validator: this.$validate.isEmail, trigger: 'blur' }]);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.rules, 'lianxishouji', [{ required: false, validator: this.$validate.isMobile, trigger: 'blur' }]);
			}
			if ('jiaoshi' == this.userTableName&&this.rules['zhicheng']){
				this.rules['zhicheng'].push({ required: true, message: '请输入职称', trigger: 'blur' })
			}else if('jiaoshi' == this.userTableName&&!this.rules['zhicheng']) {
				this.$set(this.rules, 'zhicheng', [{ required: true, message: '请输入职称', trigger: 'blur' }]);
			}
			if ('jiaoshi' == this.userTableName&&this.rules['zhuanye']){
				this.rules['zhuanye'].push({ required: true, message: '请输入专业', trigger: 'blur' })
			}else if('jiaoshi' == this.userTableName&&!this.rules['zhuanye']) {
				this.$set(this.rules, 'zhuanye', [{ required: true, message: '请输入专业', trigger: 'blur' }]);
			}
			if ('jiaoshi' == this.userTableName) {
				this.$set(this.rules, 'status', [{ required: false, validator: this.$validate.isIntNumer, trigger: 'blur' }]);
			}

			this.init();
			this.sessionForm = JSON.parse(localStorage.getItem('sessionForm'))
		},
		//方法集合
		methods: {
			init() {
				if ('jiaoshi' == this.userTableName) {
					this.dynamicProp.xingbie = '男,女'.split(',');
				}
				if ('jiaoshi' == this.userTableName) {
					this.dynamicProp.zhicheng = '高级讲师,讲师,助教'.split(',');
				}
				if ('jiaoshi' == this.userTableName) {
					this.dynamicProp.zhuanye = '软件工程,计算机科学,数字媒体技术'.split(',');
				}
			},
			setSession(){
				localStorage.setItem('sessionForm',JSON.stringify(this.sessionForm))
			},
			onSubmit(formName) {
				if(`jiaoshi` == this.userTableName && this.sessionForm.touxiang!=null){
					this.sessionForm.touxiang = this.sessionForm.touxiang.replace(new RegExp(this.$config.baseUrl,"g"),"");
				}
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$http.post(this.userTableName + '/update', this.sessionForm).then(res => {
							if (res.data.code == 0) {
								this.setSession()
								this.$message({
									message: '更新成功',
									type: 'success',
									duration: 1500
								});
							}
						});
					} else {
						return false;
					}
				});
			},
			jiaoshitouxiangHandleAvatarSuccess(fileUrls) {
				this.sessionForm.touxiang = fileUrls;
			},
			handleClick(tab, event) {
				switch(event.target.outerText) {
					case '个人中心':
						tab.$router.push('/index/center');
						break;
					case '修改密码':
						this.passwordForm = {
							password: '',
							newpassword: '',
							repassword: '',
						}
						this.$forceUpdate()
						break;
					default:
						tab.$router.push(`/index/${tab.name}?centerType=1`);
				}

				this.title = event.target.outerText;
			},
			async updatePassword(){
				this.$refs["passwordForm"].validate(async valid => {
					if (valid) {
						var password = "";
						if (this.sessionForm.mima) {
							password = this.sessionForm.mima;
						} else if (this.sessionForm.password) {
							password = this.sessionForm.password;
						}
						if (this.userTableName == 'jiaoshi') {
						}
						if (this.passwordForm.password != password) {
							this.$message.error("原密码错误");
							return;
						}
						if (this.passwordForm.newpassword != this.passwordForm.repassword) {
							this.$message.error("两次密码输入不一致");
							return;
						}
						if (this.passwordForm.newpassword == this.passwordForm.password) {
							this.$message.error("新密码与原密码相同！");
							return;
						}
						this.sessionForm.password = this.passwordForm.newpassword;
						this.sessionForm.mima = this.passwordForm.newpassword;
						this.$http.post(`${this.userTableName}/update`,this.sessionForm).then(({data})=>{
							if (data && data.code === 0) {
								this.$message({
									message: "修改密码成功,下次登录系统生效",
									type: "success",
									duration: 1500,
									onClose: () => {
									}
								});
								this.setSession()
							} else {
								this.$message.error(data.msg);
							}
						});
					}
				})
			},
			logout() {
				localStorage.clear();
				Vue.http.headers.common['Token'] = "";
				this.$router.push('/index/home');
				this.activeIndex = '0'
				localStorage.setItem('keyPath', this.activeIndex)
				this.$forceUpdate()
				this.$message({
					message: '登出成功',
					type: 'success',
					duration: 1500,
				});
			},
			hasBack(name){
				switch(name){
					default:
						return true
				}
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.center-preview {
		margin: 10px auto;
		color: #666;
		background: #fff;
		display: flex;
		width: 1200px;
		font-size: 15px;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.center-title {
			padding: 0;
			margin: 20px 0;
			color: #333;
			background: none;
			width: 110%;
			font-size: 26px;
			border-color: #0063CD;
			border-width: 0 0 2px;
			line-height: 50px;
			border-style: solid;
			text-align: left;
			order: 0;
		}
		.center-info {
			padding: 0 0 20px;
			margin: 0px 0 0 50px;
			display: none;
			line-height: 2;
			flex-wrap: wrap;
			border-radius: 0;
			box-shadow: none;
			background: none;
			width: 300px;
			justify-content: center;
			align-items: center;
			height: auto;
			order: 2;
			.center-info-title {
				color: #fff;
				background: radial-gradient(circle, rgba(237,94,91,1) 0%, rgba(209,6,2,1) 100%);
				width: 100%;
				font-size: 16px;
				line-height: 50px;
				text-align: center;
				height: 50px;
			}
			.img-box {
				display: inline-block;
				width: 94%;
				font-size: 0;
				border-color: #efefef;
				border-width: 0 0 0px 0;
				border-style: solid;
				height: auto;
				img {
					border-radius: 4px;
					margin: 20px auto 0;
					object-fit: cover;
					display: block;
					width: 100%;
					border-color: #efefef;
					border-width: 0 0 1px 0;
					border-style: solid;
					height: 150px;
				}
			}
			.info-item1 {
				padding: 5px 0;
				display: flex;
				width: 100%;
				border-color: #ddd;
				border-width: 0 0 1px 0;
				line-height: inherit;
				border-style: dashed;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
			.info-item2 {
				padding: 5px 0;
				display: flex;
				width: 100%;
				border-color: #ddd;
				border-width: 0 0 1px 0;
				line-height: inherit;
				border-style: dashed;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
			.info-item3 {
				padding: 5px 0;
				display: flex;
				width: 100%;
				border-color: #ddd;
				border-width: 0 0 1px 0;
				line-height: inherit;
				border-style: dashed;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
			.info-item4 {
				padding: 5px 0;
				display: flex;
				width: 100%;
				border-color: #ddd;
				border-width: 0 0 1px 0;
				line-height: inherit;
				border-style: dashed;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
			.info-item5 {
				padding: 5px 0;
				display: flex;
				width: 100%;
				border-color: #ddd;
				border-width: 0 0 1px 0;
				line-height: inherit;
				border-style: dashed;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
			.info-item6 {
				padding: 5px 0;
				display: flex;
				width: 100%;
				border-color: #ddd;
				border-width: 0 0 1px 0;
				line-height: inherit;
				border-style: dashed;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
				}
				.label {
					margin: 0 10px 0 0;
					color: inherit;
					font-weight: 500;
					font-size: inherit;
				}
				.text {
					color: inherit;
					flex: 1;
					font-size: inherit;
					text-align: left;
				}
			}
		}
		.center-tabs.el-tabs {
			margin: 0px auto;
			background: none;
			flex: 1;
			width: 100%;
			order: 1;
			/deep/ .el-tabs__header {
				padding: 10px 10px 0;
				margin: 0 0 20px;
				background: none;
				width: 100%;
				clear: both;
				border-color: #d10602;
				border-width: 0px;
				position: relative;
				border-style: inset;
				text-align: center;
			}
			/deep/ .el-tabs__header .el-tabs__item {
				padding: 0 20px 0 26px;
				margin: 0 10px 10px 0;
				color: #333;
				font-weight: 500;
				display: block;
				font-size: inherit;
				line-height: 44px;
				float: left;
				border-radius: 0px;
				background: #eee;
				position: relative;
				text-align: center;
				height: 44px;
			}
			/deep/ .el-tabs__header .el-tabs__item:hover {
				padding: 0 20px 0 26px;
				color: #fff;
				background: #0063CD;
				font-weight: 500;
				font-size: inherit;
				line-height: 44px;
				position: relative;
				text-align: center;
				height: 44px;
			}
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				padding: 0 20px 0 26px;
				margin: 0 10px 10px 0;
				color: #fff;
				font-weight: 500;
				display: block;
				font-size: inherit;
				line-height: 44px;
				float: left;
				border-radius: 0px;
				background: #0063CD;
				position: relative;
				text-align: center;
				height: 44px;
			}
			/deep/ .el-tabs__content {
				border: 1px solid #ddd;
				padding: 20px;
				background: none;
				width: 100%;
				clear: both;
			}
			/deep/ .el-tabs__content .el-tab-pane {
				background: none;
				width: 100%;
			}
			& /deep/ .el-tabs__header {
				.el-tabs__nav{
					overflow: auto;
				}
				::-webkit-scrollbar {
					-webkit-appearance: none;
					width: 6px;
					height: 6px;
				}
				::-webkit-scrollbar-track {
					background: rgba(0, 0, 0, 0.1);
					border-radius: 0;
				}
				::-webkit-scrollbar-thumb {
					cursor: pointer;
					border-radius: 5px;
					background: rgba(0, 0, 0, 0.15);
					transition: color 0.2s ease;
				}
				::-webkit-scrollbar-thumb:hover {
					background: rgba(0, 0, 0, 0.3);
				}
				.el-tabs__nav-wrap {
					margin: 0;
					&::after {
						content: none;
					}
				}
				.el-tabs__active-bar {
					display: none !important;
				}
			}
			.center-preview-pv {
				.center-item.el-form-item {
					border-radius: 0px;
					padding: 6px 0 0;
					margin: 0 0 20px 0;
					background: #fff;
					border-color: #ddd;
					border-width: 0px;
					border-style: solid;
					/deep/ .el-form-item__label {
						padding: 0 10px 0 0;
						color: inherit;
						font-weight: 500;
						width: 180px;
						font-size: inherit;
						line-height: 40px;
						text-align: right;
					}
					.el-form-item__content {
						margin-left: 180px;
					}
					.el-input {
						width: 100%;
					}
					.el-input /deep/ .el-input__inner {
						border: 1px solid #ddd;
						border-radius: 0px;
						padding: 0 12px;
						box-shadow: none;
						color: inherit;
						width: auto;
						font-size: 16px;
						height: 40px;
					}
					.el-input /deep/ .el-input__inner[readonly="readonly"] {
						border: 0;
						cursor: not-allowed;
						border-radius: 0px;
						padding: 0 12px;
						box-shadow: none;
						color: inherit;
						background: none;
						width: auto;
						font-size: 16px;
						height: 40px;
					}
					.el-select {
						width: auto;
					}
					.el-select /deep/ .el-input__inner {
						border: 1px solid #ddd;
						border-radius: 0px;
						padding: 0 10px;
						box-shadow: none;
						color: inherit;
						width: auto;
						font-size: 16px;
						height: 40px;
					}
					.el-select /deep/ .is-disabled .el-input__inner {
						border: 0;
						cursor: not-allowed;
						border-radius: 0px;
						padding: 0 10px;
						box-shadow: none;
						color: inherit;
						background: none;
						width: auto;
						font-size: 16px;
						height: 40px;
					}
					.el-date-editor {
						width: 100%;
					}
					.el-date-editor /deep/ .el-input__inner {
						border: 1px solid #ddd;
						border-radius: 0px;
						padding: 0 10px 0 30px;
						box-shadow: none;
						color: inherit;
						width: auto;
						font-size: 16px;
						height: 40px;
					}
					.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
						border: 0;
						cursor: not-allowed;
						border-radius: 0px;
						padding: 0 10px 0 30px;
						box-shadow: none;
						color: inherit;
						background: none;
						width: auto;
						font-size: 16px;
						height: 40px;
					}
					/deep/ .el-upload--picture-card {
						background: transparent;
						border: 0;
						border-radius: 0;
						width: auto;
						height: auto;
						line-height: initial;
						vertical-align: middle;
					}
					/deep/ .upload .upload-img {
						border: 1px solid #ddd;
						cursor: pointer;
						border-radius: 0px;
						color: #999;
						background: #fff;
						width: 80px;
						font-size: 32px;
						line-height: 80px;
						text-align: center;
						height: 80px;
					}
					/deep/ .el-upload-list .el-upload-list__item {
						border: 1px solid #ddd;
						cursor: pointer;
						border-radius: 0px;
						color: #999;
						background: #fff;
						width: 80px;
						font-size: 32px;
						line-height: 80px;
						text-align: center;
						height: 80px;
						font-size: 14px;
						line-height: 1.8;
					}
					/deep/ .el-upload .el-icon-plus {
						border: 1px solid #ddd;
						cursor: pointer;
						border-radius: 0px;
						color: #999;
						background: #fff;
						width: 80px;
						font-size: 32px;
						line-height: 80px;
						text-align: center;
						height: 80px;
					}
					/deep/ .el-upload__tip {
						color: #838fa1;
						font-size: 16px;
					}
					/deep/ .el-input__inner::placeholder {
						color: #999;
						font-size: inherit;
					}
				}
				.center-btn-item {
					padding: 0;
					margin: 20px 0;
					.updateBtn {
						border: 0;
						cursor: pointer;
						border-radius: 4px;
						padding: 0 24px 0 30px;
						margin: 0 20px 0 0;
						background: #0066D4;
						display: inline-block;
						width: auto;
						font-size: 16px;
						line-height: 44px;
						min-width: 120px;
						height: 44px;
						.icon {
							color: #fff;
						}
						.text {
							color: #fff;
						}
					}
					.updateBtn:hover {
						.icon {
						}
						.text {
						}
					}
					.closeBtn {
						border: 1px solid #0066D450;
						cursor: pointer;
						padding: 0 24px 0 30px;
						margin: 0 20px 0 0;
						color: #0066D4;
						display: inline-block;
						font-size: 16px;
						line-height: 44px;
						border-radius: 4px;
						background: #fff;
						width: auto;
						text-align: center;
						min-width: 110px;
						height: 44px;
						.icon {
							color: #0066D4;
						}
						.text {
							color: #0066D4;
						}
					}
					.closeBtn:hover {
						.icon {
						}
						.text {
						}
					}
				}
				.el-date-editor.el-input {
					width: auto;
				}
			}
		}
	}
</style>
