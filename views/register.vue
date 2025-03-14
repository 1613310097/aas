<template>
	<div>
		<div class="register-container">
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">教学材料检查助手的设计与实现注册</div>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('jiaoshigonghao')?'required':''">教师工号：</div>
						<el-input  v-model="ruleForm.jiaoshigonghao"  autocomplete="off" placeholder="教师工号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('jiaoshixingming')?'required':''">教师姓名：</div>
						<el-input  v-model="ruleForm.jiaoshixingming"  autocomplete="off" placeholder="教师姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in jiaoshixingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('youxiang')?'required':''">邮箱：</div>
						<el-input  v-model="ruleForm.youxiang"  autocomplete="off" placeholder="邮箱"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('lianxishouji')?'required':''">联系手机：</div>
						<el-input  v-model="ruleForm.lianxishouji"  autocomplete="off" placeholder="联系手机"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('zhicheng')?'required':''">职称：</div>
						<el-select v-model="ruleForm.zhicheng" placeholder="请选择职称" >
							<el-option
								v-for="(item,index) in jiaoshizhichengOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('zhuanye')?'required':''">专业：</div>
						<el-select v-model="ruleForm.zhuanye" placeholder="请选择专业" >
							<el-option
								v-for="(item,index) in jiaoshizhuanyeOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('xueqi')?'required':''">学期：</div>
						<el-input  v-model="ruleForm.xueqi"  autocomplete="off" placeholder="学期"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('kecheng')?'required':''">课程：</div>
						<el-input  v-model="ruleForm.kecheng"  autocomplete="off" placeholder="课程"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('shangkebanji')?'required':''">上课班级：</div>
						<el-input  v-model="ruleForm.shangkebanji"  autocomplete="off" placeholder="上课班级"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="jiaoshitouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="login()">注册</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css'
export default {
	data() {
		return {
			ruleForm: {
			},
			forgetForm: {},
            pageFlag : '',
			tableName:"",
			rules: {},
            jiaoshixingbieOptions: [],
            jiaoshizhichengOptions: [],
            jiaoshizhuanyeOptions: [],
		};
	},
	mounted(){
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='jiaoshi'){
				this.ruleForm = {
					jiaoshigonghao: '',
					mima: '',
					jiaoshixingming: '',
					xingbie: '',
					youxiang: '',
					lianxishouji: '',
					zhicheng: '',
					zhuanye: '',
					xueqi: '',
					kecheng: '',
					shangkebanji: '',
					touxiang: '',
					status: '',
				}
			}
			if ('jiaoshi' == this.tableName) {
				this.rules.jiaoshigonghao = [{ required: true, message: '请输入教师工号', trigger: 'blur' }]
			}
			if ('jiaoshi' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('jiaoshi' == this.tableName) {
				this.rules.jiaoshixingming = [{ required: true, message: '请输入教师姓名', trigger: 'blur' }]
			}
			if ('jiaoshi' == this.tableName) {
				this.rules.zhicheng = [{ required: true, message: '请输入职称', trigger: 'blur' }]
			}
			if ('jiaoshi' == this.tableName) {
				this.rules.zhuanye = [{ required: true, message: '请输入专业', trigger: 'blur' }]
			}
			this.jiaoshixingbieOptions = "男,女".split(',')
			this.jiaoshizhichengOptions = "高级讲师,讲师,助教".split(',')
			this.jiaoshizhuanyeOptions = "软件工程,计算机科学,数字媒体技术".split(',')
		}
	},
	created() {
	},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        jiaoshitouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
			if((!this.ruleForm.jiaoshigonghao) && `jiaoshi` == this.tableName){
				this.$message.error(`教师工号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `jiaoshi` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `jiaoshi` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.jiaoshixingming) && `jiaoshi` == this.tableName){
				this.$message.error(`教师姓名不能为空`);
				return
			}
			if(`jiaoshi` == this.tableName && this.ruleForm.youxiang &&(!this.$validate.isEmail(this.ruleForm.youxiang))){
				this.$message.error(`邮箱应输入邮件格式`);
				return
			}
			if(`jiaoshi` == this.tableName && this.ruleForm.lianxishouji &&(!this.$validate.isMobile(this.ruleForm.lianxishouji))){
				this.$message.error(`联系手机应输入手机格式`);
				return
			}
			if((!this.ruleForm.zhicheng) && `jiaoshi` == this.tableName){
				this.$message.error(`职称不能为空`);
				return
			}
			if((!this.ruleForm.zhuanye) && `jiaoshi` == this.tableName){
				this.$message.error(`专业不能为空`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			this.$http({
				url: url,
				method: "post",
				data:this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.register-container {
	position: relative;
	background: url(http://codegen.caihongy.cn/20240918/4ad46f15b24a40c29f768df225899df5.png);
	background-repeat: no-repeat;
	background-size: 100% 100%;
	background: url(http://codegen.caihongy.cn/20240918/4ad46f15b24a40c29f768df225899df5.png);
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: center;
	background-position: center center;
	.rgs-form {
		.rgs-form2 {
		width: 100%;
		}
		padding: 40px;
		margin: 0;
		z-index: 1000;
		display: flex;
		min-height: 100vh;
		flex-wrap: wrap;
		border-radius: 0;
		box-shadow: inset 0px 0px 0px 0px #000;
		flex-direction: column;
		background: none;
		width: 40%;
		justify-content: center;
		align-items: center;
		position: relative;
		height: auto;
		.title {
			border: 1px solid #fff;
			padding: 0 0 20px;
			margin: 0;
			color: #fff;
			background: none;
			font-weight: 600;
			width: 100%;
			font-size: 28px;
			border-width: 0 0 2px;
			line-height: 40px;
			text-align: center;
		}
		.list-item {
			padding: 0 0 0 0px;
			margin: 0 0 ;
			width: 100%;
			position: relative;
			height: auto;
			/deep/ .el-form-item__content {
				border: 1px solid #fff;
				padding: 0;
				margin: 0;
				display: flex;
				width: calc(100% - 0px);
				border-width: 0 0 2px;
				align-items: center;
				position: relative;
			}
			.lable {
				border: 1px solid #fff;
				padding: 0;
				color: #fff;
				width: 120px;
				font-size: 16px;
				border-width: 0 2px 0 0;
				line-height: 60px;
				text-align: center;
				height: 100%;
			}
			.el-input {
				flex: 1;
				width: 100%;
			}
			.el-input /deep/ .el-input__inner {
				border: none;
				border-radius: 0;
				padding: 0 10px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-input /deep/ .el-input__inner:focus {
				border: none;
				border-radius: 0;
				padding: 0 10px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 14px;
				height: 38px;
			}
			.el-input-number {
				flex: 1;
				width: 100%;
			}
			.el-input-number /deep/ .el-input__inner {
				text-align: center;
				border: none;
				border-radius: 0;
				padding: 0 10px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-input-number /deep/ .el-input__inner:focus {
				border: none;
				border-radius: 0;
				padding: 0 10px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 14px;
				height: 38px;
			}
			.el-input-number /deep/ .el-input-number__decrease {
				display: none;
			}
			.el-input-number /deep/ .el-input-number__increase {
				display: none;
			}
			.el-select {
				flex: 1;
				width: 100%;
			}
			.el-select /deep/ .el-input__inner {
				border: none;
				border-radius: 0;
				padding: 0 10px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-select /deep/ .el-input__inner:focus {
				border: none;
				border-radius: 0;
				padding: 0 10px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-date-editor {
				flex: 1;
				width: 100%;
			}
			.el-date-editor /deep/ .el-input__inner {
				border: 0;
				border-radius: 0;
				padding: 0 10px 0 30px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-date-editor /deep/ .el-input__inner:focus {
				border: 1px solid #efeff7;
				border-radius: 4px;
				padding: 0 10px 0 30px;
				color: #666;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-date-editor.el-input {
				width: 100%;
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
				border: 1px solid #fff;
				cursor: pointer;
				border-radius: 0px;
				color: #fff;
				background: none;
				width: 130px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload-list .el-upload-list__item {
				border: 1px solid #fff;
				cursor: pointer;
				border-radius: 0px;
				color: #fff;
				background: none;
				width: 130px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload .el-icon-plus {
				border: 1px solid #fff;
				cursor: pointer;
				border-radius: 0px;
				color: #fff;
				background: none;
				width: 130px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload__tip {
				color: #666;
				display: none;
				font-size: 15px;
			}
			/deep/ .el-input__inner::placeholder {
				color: #fff;
				font-size: 14px;
			}
			.required {
				position: relative;
			}
			.required::after{
				color: #fff;
				left: 0;
				position: inherit;
				content: "*";
				order: -1;
			}
			.editor {
				background: #fff;
				flex: 1;
				width: 100%;
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border: none;
				border-radius: 0;
				padding: 0 10px;
				margin: 0;
				color: #fff;
				background: none;
				flex: 1;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			input:focus {
				border: 0;
				border-radius: 0;
				padding: 0 10px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			input::placeholder {
				color: #fff;
				font-size: 14px;
			}
			button {
				border: 0px solid #efeff7;
				cursor: pointer;
				border-radius: 0;
				padding: 0;
				margin: 1px 0 0;
				color: #000;
				background: #fff;
				width: 120px;
				font-size: 15px;
				height: 38px;
			}
			button:hover {
				opacity: 0.5;
			}
		}
		.register-btn {
			display: flex;
			width: 100%;
			flex-wrap: wrap;
		}
		.register-btn1 {
			width: 100%;
			order: 2;
		}
		.register-btn2 {
			padding: 20px 0;
			width: 100%;
		}
		.r-btn {
			border: 1px solid #fff;
			cursor: pointer;
			border-radius: 0;
			padding: 0 10px;
			margin: 20px auto 5px;
			color: #fff;
			background: none;
			display: block;
			width: 30%;
			font-size: 20px;
			height: 60px;
		}
		.r-btn:hover {
			border: 0px solid rgba(0, 0, 0, 1);
			opacity: 0.8;
		}
		.r-login {
			cursor: pointer;
			padding: 0;
			color: #fff;
			display: inline-block;
			text-decoration: none;
			width: 100%;
			font-size: 15px;
			line-height: 40px;
			text-align: center;
		}
		.r-login:hover {
			opacity: 0.5;
		}
	}
}
	
	::-webkit-scrollbar {
	  display: none;
	}
</style>
