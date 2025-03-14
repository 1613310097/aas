<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="学期" prop="xueqi" >
					<el-input v-model="ruleForm.xueqi" placeholder="学期" clearable  :readonly="ro.xueqi"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="学期" prop="xueqi" >
					<el-input v-model="ruleForm.xueqi" placeholder="学期" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="课程" prop="kecheng" >
					<el-input v-model="ruleForm.kecheng" placeholder="课程" clearable  :readonly="ro.kecheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="课程" prop="kecheng" >
					<el-input v-model="ruleForm.kecheng" placeholder="课程" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.fengmian" label="封面" prop="fengmian" >
					<file-upload
						tip="点击上传封面"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.fengmian?ruleForm.fengmian:''"
						@change="fengmianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.fengmian" label="封面" prop="fengmian" >
					<img v-if="ruleForm.fengmian.substring(0,4)=='http'&&ruleForm.fengmian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.fengmian" width="100" height="100">
					<img v-else-if="ruleForm.fengmian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.fengmian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.fengmian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="检查项" prop="jianchaxiang" >
					<el-select :disabled="ro.jianchaxiang" v-model="ruleForm.jianchaxiang" placeholder="请选择检查项" multiple filterable>
						<el-option
							v-for="(item,index) in jianchaxiangOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="检查项" prop="jianchaxiang" >
					<el-input v-model="ruleForm.jianchaxiang"
						placeholder="检查项" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="检查对象" prop="jianchaduixiang" >
					<el-select :disabled="ro.jianchaduixiang" v-model="ruleForm.jianchaduixiang" placeholder="请选择检查对象" >
						<el-option
							v-for="(item,index) in jianchaduixiangOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="检查对象" prop="jianchaduixiang" >
					<el-input v-model="ruleForm.jianchaduixiang"
						placeholder="检查对象" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info'&& !ro.wenjian" label="文件" prop="wenjian" >
					<file-upload
						tip="点击上传文件"
						action="file/upload"
						:limit="1"
						:type="3"
						:multiple="true"
						:fileUrls="ruleForm.wenjian?ruleForm.wenjian:''"
						@change="wenjianUploadChange"
					></file-upload>
				</el-form-item>  
				<el-form-item v-else-if="ruleForm.wenjian" label="文件" prop="wenjian" >
					<el-button class="downBtn" type="text" size="small" @click="download($base.url+ruleForm.wenjian)">
						<span class="icon iconfont icon-xiazai6"></span>
						下载
					</el-button>
				</el-form-item>
				<el-form-item v-else-if="!ruleForm.wenjian" label="文件" prop="wenjian" >
					<el-button class="unBtn" type="text" size="small">
						<span class="icon iconfont icon-xihuan"></span>
						暂无
					</el-button>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="完成状态" prop="wanchengzhuangtai" >
					<el-select :disabled="ro.wanchengzhuangtai" v-model="ruleForm.wanchengzhuangtai" placeholder="请选择完成状态" >
						<el-option
							v-for="(item,index) in wanchengzhuangtaiOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="完成状态" prop="wanchengzhuangtai" >
					<el-input v-model="ruleForm.wanchengzhuangtai"
						placeholder="完成状态" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'" label="教师工号" prop="jiaoshigonghao" >
					<el-select :disabled="ro.jiaoshigonghao" @change="jiaoshigonghaoChange" v-model="ruleForm.jiaoshigonghao" placeholder="请选择教师工号">
						<el-option
							v-for="(item,index) in jiaoshigonghaoOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.jiaoshigonghao" label="教师工号" prop="jiaoshigonghao" >
					<el-input v-model="ruleForm.jiaoshigonghao" placeholder="教师工号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="批改老师" prop="jiaoshixingming" >
					<el-input v-model="ruleForm.jiaoshixingming" placeholder="批改老师" clearable  :readonly="ro.jiaoshixingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="批改老师" prop="jiaoshixingming" >
					<el-input v-model="ruleForm.jiaoshixingming" placeholder="批改老师" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="联系手机" prop="lianxishouji" >
					<el-input v-model="ruleForm.lianxishouji" placeholder="联系手机" clearable  :readonly="ro.lianxishouji"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="联系手机" prop="lianxishouji" >
					<el-input v-model="ruleForm.lianxishouji" placeholder="联系手机" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="专业" prop="zhuanye" >
					<el-input v-model="ruleForm.zhuanye" placeholder="专业" clearable  :readonly="ro.zhuanye"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="专业" prop="zhuanye" >
					<el-input v-model="ruleForm.zhuanye" placeholder="专业" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item v-if="type!='info'"  label="任务内容" prop="renwuneirong" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.renwuneirong" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.renwuneirong" label="任务内容" prop="renwuneirong" >
				<span class="text ql-snow ql-editor" v-html="ruleForm.renwuneirong"></span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	export default {
		data() {
			return {
				id: '',
				type: '',
			
			
				ro:{
					xueqi : false,
					kecheng : false,
					fengmian : false,
					jianchaxiang : false,
					jianchaduixiang : false,
					wenjian : false,
					wanchengzhuangtai : false,
					renwuneirong : false,
					jiaoshigonghao : false,
					jiaoshixingming : false,
					lianxishouji : false,
					zhuanye : false,
					userid : false,
				},
			
				ruleForm: {
					xueqi: '',
					kecheng: '',
					fengmian: '',
					jianchaxiang: [],
					jianchaduixiang: '',
					wenjian: '',
					wanchengzhuangtai: '未审核',
					renwuneirong: '',
					jiaoshigonghao: '',
					jiaoshixingming: '',
					lianxishouji: '',
					zhuanye: '',
					userid: '',
				},
				jianchaxiangOptions: [],
				jianchaduixiangOptions: [],
				wanchengzhuangtaiOptions: [],
				jiaoshigonghaoOptions: [],

				rules: {
					xueqi: [
						{ required: true, message: '学期不能为空', trigger: 'blur' },
					],
					kecheng: [
						{ required: true, message: '课程不能为空', trigger: 'blur' },
					],
					fengmian: [
					],
					jianchaxiang: [
						{ required: true, message: '检查项不能为空', trigger: 'blur' },
					],
					jianchaduixiang: [
						{ required: true, message: '检查对象不能为空', trigger: 'blur' },
					],
					wenjian: [
					],
					wanchengzhuangtai: [
					],
					renwuneirong: [
					],
					jiaoshigonghao: [
						{ required: true, message: '教师工号不能为空', trigger: 'blur' },
					],
					jiaoshixingming: [
					],
					lianxishouji: [
					],
					zhuanye: [
					],
					userid: [
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='xueqi'){
							this.ruleForm.xueqi = obj[o];
							this.ro.xueqi = true;
							continue;
						}
						if(o=='kecheng'){
							this.ruleForm.kecheng = obj[o];
							this.ro.kecheng = true;
							continue;
						}
						if(o=='fengmian'){
							this.ruleForm.fengmian = obj[o];
							this.ro.fengmian = true;
							continue;
						}
						if(o=='jianchaxiang'){
							this.ruleForm.jianchaxiang = obj[o];
							this.ro.jianchaxiang = true;
							continue;
						}
						if(o=='jianchaduixiang'){
							this.ruleForm.jianchaduixiang = obj[o];
							this.ro.jianchaduixiang = true;
							continue;
						}
						if(o=='wenjian'){
							this.ruleForm.wenjian = obj[o];
							this.ro.wenjian = true;
							continue;
						}
						if(o=='wanchengzhuangtai'){
							this.ruleForm.wanchengzhuangtai = obj[o];
							this.ro.wanchengzhuangtai = true;
							continue;
						}
						if(o=='renwuneirong'){
							this.ruleForm.renwuneirong = obj[o];
							this.ro.renwuneirong = true;
							continue;
						}
						if(o=='jiaoshigonghao'){
							this.ruleForm.jiaoshigonghao = obj[o];
							this.ro.jiaoshigonghao = true;
							continue;
						}
						if(o=='jiaoshixingming'){
							this.ruleForm.jiaoshixingming = obj[o];
							this.ro.jiaoshixingming = true;
							continue;
						}
						if(o=='lianxishouji'){
							this.ruleForm.lianxishouji = obj[o];
							this.ro.lianxishouji = true;
							continue;
						}
						if(o=='zhuanye'){
							this.ruleForm.zhuanye = obj[o];
							this.ro.zhuanye = true;
							continue;
						}
						if(o=='userid'){
							this.ruleForm.userid = obj[o];
							this.ro.userid = true;
							continue;
						}
					}
					this.ruleForm.wanchengzhuangtai = '未审核'; 				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(this.$storage.get("role")!="管理员") {
							this.ro.wanchengzhuangtai = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/jianchaxiang/jianchaxiang`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.jianchaxiangOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/jiaoshi/jiaoshigonghao`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.jianchaduixiangOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
				this.wanchengzhuangtaiOptions = "已审核,未审核".split(',')
				this.$http({
					url: `option/jiaoshi/jiaoshigonghao`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.jiaoshigonghaoOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 下二随
			jiaoshigonghaoChange () {
				this.$http({
					url: `follow/jiaoshi/jiaoshigonghao?columnValue=`+ this.ruleForm.jiaoshigonghao,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						if(data.data.jiaoshixingming){
							this.ruleForm.jiaoshixingming = data.data.jiaoshixingming
						}
						if(data.data.lianxishouji){
							this.ruleForm.lianxishouji = data.data.lianxishouji
						}
						if(data.data.zhuanye){
							this.ruleForm.zhuanye = data.data.zhuanye
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `kaohecailiao/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.jianchaxiang = this.ruleForm.jianchaxiang.split(",");
						this.ruleForm.renwuneirong = this.ruleForm.renwuneirong.replace(reg,'../../../springboottg274cp5/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.fengmian!=null) {
						this.ruleForm.fengmian = this.ruleForm.fengmian.replace(new RegExp(this.$base.url,"g"),"");
					}
					if(this.ruleForm.wenjian!=null) {
						this.ruleForm.wenjian = this.ruleForm.wenjian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
								}
							}
							this.ruleForm.jianchaxiang = this.ruleForm.jianchaxiang.join(",");
							
							await this.$http({
								url: `kaohecailiao/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.kaohecailiaoCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.kaohecailiaoCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			fengmianUploadChange(fileUrls) {
				this.ruleForm.fengmian = fileUrls;
			},
			wenjianUploadChange(fileUrls) {
				this.ruleForm.wenjian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 30px;
	}
	.add-update-preview {
		padding: 0px;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 26px 0;
		display: inline-block;
		width: 49%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #9E9E9E;
		font-weight: 600;
		width: 180px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		padding: 0 10px;
		color: #333;
		background: none;
		font-weight: 500;
		display: inline-block;
		font-size: 16px;
		line-height: 40px;
		min-width: 50%;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #333;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 1px solid #E8E8E8;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: #eee;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #333;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 1px solid #E8E8E8;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: #eee;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 1px solid #E8E8E8;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px;
		color: #666;
		background: #eee;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 1px solid #E8E8E8;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: #eee;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #e8e8e8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #4EF1DE;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.5;
	}
	.add-update-preview .downBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #4EF1DE;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.5;
	}
	.add-update-preview .unBtn {
		border: 1px solid #e8e8e8;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		outline: none;
		color: #999;
		background: #eee;
		width: auto;
		font-size: 16px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 1;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 1px dashed #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 150px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 1px dashed #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 150px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 1px dashed #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 150px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #999;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 12px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 14px;
		min-width: 400px;
		height: 120px;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 1px solid #E8E8E8;
				cursor: not-allowed;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: #eee;
				width: 100%;
				font-size: 14px;
				min-width: 400px;
				height: auto;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 5px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #6C71FD;
			width: 120px;
			font-size: 14px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.5;
		}
		.btn2 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 5px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #4EF1DE;
			width: 120px;
			font-size: 14px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.5;
		}
		.btn3 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 5px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #49AACE;
			width: 120px;
			font-size: 14px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.5;
		}
		.btn4 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 5px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #4ABCFF;
			width: 120px;
			font-size: 14px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.5;
		}
		.btn5 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 5px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #0977FD;
			width: 120px;
			font-size: 14px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
