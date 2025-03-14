<template>
	<div class="main-content" :style='{"padding":"20px 10px","fontSize":"15px"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"margin":"0 10px 20px"}' :inline="true" :model="searchForm">
				<el-row :style='{"padding":"10px","flexWrap":"wrap","background":"none","display":"flex"}' >
					<div :style='{"margin":"0 10px 10px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#9E9E9E","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">教师工号</label>
						<el-input v-model="searchForm.jiaoshigonghao" placeholder="教师工号" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 10px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#9E9E9E","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">教师姓名</label>
						<el-input v-model="searchForm.jiaoshixingming" placeholder="教师姓名" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 10px 0","display":"flex"}' class="select" label="职称" prop="zhicheng">
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#9E9E9E","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">职称</label>
						<el-select clearable v-model="searchForm.zhicheng" placeholder="请选择职称" >
							<el-option v-for="(item,index) in zhichengOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<div :style='{"margin":"0 10px 10px 0","display":"flex"}' class="select" label="专业" prop="zhuanye">
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#9E9E9E","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">专业</label>
						<el-select clearable v-model="searchForm.zhuanye" placeholder="请选择专业" >
							<el-option v-for="(item,index) in zhuanyeOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<el-button class="search" type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing07" :style='{"margin":"0 2px","fontSize":"16px","color":"#fff","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>

				<el-row class="actions" :style='{"padding":"10px","margin":"20px 0","flexWrap":"wrap","background":"none","display":"flex"}'>
					<el-button class="add" v-if="isAuth('jiaoshi','新增')" type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
						添加
					</el-button>
					<el-button class="del" v-if="isAuth('jiaoshi','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
						删除
					</el-button>

					<el-button class="btn18" v-if="isAuth('jiaoshi','导入')" type="success" @click="importHandler()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
						导入
					</el-button>
					<el-button class="btn18" v-if="isAuth('jiaoshi','上传模板')" type="success" @click="importClcik">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
						上传模板
					</el-button>
					<el-button class="btn18" v-if="isAuth('jiaoshi','下载模板')" type="success" @click="download('upload/jiaoshi_template.xlsx')">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
						下载模板
					</el-button>

					<download-excel v-if="isAuth('jiaoshi','导出')" class="export-excel-wrapper" :data = "dataList" :fields = "json_fields" name = "教师.xls">
						<!-- 导出excel -->
						<el-button class="btn18" type="success">
							<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
							导出
						</el-button>
					</download-excel>
				</el-row>
			</el-form>
			<div :style='{"width":"100%","padding":"0 10px","fontSize":"14px","color":"#000"}'>
				<el-table class="tables"
					:stripe='false'
					:style='{"padding":"10px 0","borderColor":"#eee","borderRadius":"20px 20px 0 0","borderWidth":"1px 0 0 1px","background":"#fff","width":"100%","fontSize":"inherit","borderStyle":"solid"}' 
					:border='true'
					v-if="isAuth('jiaoshi','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'  
						prop="jiaoshigonghao"
						label="教师工号">
						<template slot-scope="scope">
							{{scope.row.jiaoshigonghao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="jiaoshixingming"
						label="教师姓名">
						<template slot-scope="scope">
							{{scope.row.jiaoshixingming}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="xingbie"
						label="性别">
						<template slot-scope="scope">
							{{scope.row.xingbie}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="youxiang"
						label="邮箱">
						<template slot-scope="scope">
							{{scope.row.youxiang}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="lianxishouji"
						label="联系手机">
						<template slot-scope="scope">
							{{scope.row.lianxishouji}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="zhicheng"
						label="职称">
						<template slot-scope="scope">
							{{scope.row.zhicheng}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="zhuanye"
						label="专业">
						<template slot-scope="scope">
							{{scope.row.zhuanye}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="xueqi"
						label="学期">
						<template slot-scope="scope">
							{{scope.row.xueqi}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="kecheng"
						label="课程">
						<template slot-scope="scope">
							{{scope.row.kecheng}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="shangkebanji"
						label="上课班级">
						<template slot-scope="scope">
							{{scope.row.shangkebanji}}
						</template>
					</el-table-column>
					<el-table-column  :resizable='true' prop="touxiang" width="200" label="头像">
						<template slot-scope="scope">
							<div v-if="scope.row.touxiang">
								<img v-if="scope.row.touxiang.substring(0,4)=='http'&&scope.row.touxiang.split(',w').length>1" :src="scope.row.touxiang" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.touxiang)">
								<img v-else-if="scope.row.touxiang.substring(0,4)=='http'" :src="scope.row.touxiang.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.touxiang.split(',')[0])">
								<img v-else :src="$base.url+scope.row.touxiang.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView($base.url+scope.row.touxiang.split(',')[0])">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="content" label="状态">
						<template slot-scope="scope">
							<el-switch
								v-model="scope.row.status"
								:width="60"
								active-icon-class=""
								inactive-icon-class=""
								active-text=""
								inactive-text=""
								:active-value="1"
								:inactive-value="0"
								active-color="#6C71FD"
								inactive-color="#ccc"
								@change="(e)=>jiaoshistatusChange(e,scope.row)">
							</el-switch>
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button class="view" v-if=" isAuth('jiaoshi','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								查看
							</el-button>
							<el-button class="edit" v-if=" isAuth('jiaoshi','修改') " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								修改
							</el-button>




							<el-button class="del" v-if="isAuth('jiaoshi','删除') " type="primary" @click="deleteHandler(scope.row.id )">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								删除
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				background
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:layout="layouts.join()"
				:total="totalPage"
				prev-text="← "
				next-text="→ "
				:hide-on-single-page="false"
				:style='{"padding":"10px 0","boxShadow":"none","margin":"20px auto 0","whiteSpace":"nowrap","color":"#333","alignItems":"center","textAlign":"center","display":"flex","justifyContent":"center","background":"#fff","width":"60%","fontSize":"inherit","fontWeight":"500"}'
			></el-pagination>
		</template>
		
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>



		<el-dialog title="导入" :visible.sync="importVisiable" width="50%">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item class="upload" label="文件" prop="excelFile">
				  <excel-file-upload
				  tip="点击上传直接导入excel文件"
				  action="jiaoshi/importExcel"
				  :limit="1"
				  :fileUrls="importUrl"
				  @change="importChange"
				  ></excel-file-upload>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="importHandler()">关 闭</el-button>
			</span>
		</el-dialog>
		<el-dialog title="上传模板" :visible.sync="importVis" width="50%">
			<el-form ref="form" :model="importForm" label-width="80px">
				<el-form-item class="upload" label="文件" prop="excelFile">
					<el-upload class="upload-demo" drag :action="$base.url + 'file/upload?type=jiaoshi_template'"
						:show-file-list="false" :on-success="importSuccess">
						<i class="el-icon-upload"></i>
						<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
					</el-upload>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="importVis=false">关 闭</el-button>
			</span>
		</el-dialog>


		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>

<script>
	import axios from 'axios';
	import AddOrUpdate from "./add-or-update";
	import {
		Loading
	} from 'element-ui';
	export default {
		data() {
			return {
				indexQueryCondition: '',
				zhichengOptions: [],
				zhuanyeOptions: [],
				searchForm: {
					key: ""
				},
				form:{},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: true,
				importVisiable: false,
				importVis: false,
				importForm: {},
				importUrl: '',
				addOrUpdateFlag:false,
				layouts: ["prev","pager","next"],
//导出excel
				json_fields: {
					"教师工号": "jiaoshigonghao",    //常规字段
					"密码": "mima",    //常规字段
					"教师姓名": "jiaoshixingming",    //常规字段
					"性别": "xingbie",    //常规字段
					"邮箱": "youxiang",    //常规字段
					"联系手机": "lianxishouji",    //常规字段
					"职称": "zhicheng",    //常规字段
					"专业": "zhuanye",    //常规字段
					"学期": "xueqi",    //常规字段
					"课程": "kecheng",    //常规字段
					"上课班级": "shangkebanji",    //常规字段
					"头像": "touxiang",    //常规字段
					"状态": "status",    //常规字段
				},
				json_meta: [
					[
						{
							" key ": " charset ",
							" value ": " utf- 8 "
						}
					]
				],
				previewImg: '',
				previewVisible: false,
			};
		},
		created() {
			this.init();
			this.getDataList();
			this.contentStyleChange();
		},
		mounted() {
		},
		filters: {
			htmlfilter: function (val) {
				return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
			}
		},
		computed: {
			tablename(){
				return this.$storage.get('sessionTable')
			},
		},
		components: {
			AddOrUpdate,
		},
		methods: {
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
				
			},
			contentStyleChange() {
				this.contentPageStyleChange()
			},
			// 分页
			contentPageStyleChange(){
				let arr = []

				// if(this.contents.pageTotal) arr.push('total')
				// if(this.contents.pageSizes) arr.push('sizes')
				// if(this.contents.pagePrevNext){
				//   arr.push('prev')
				//   if(this.contents.pagePager) arr.push('pager')
				//   arr.push('next')
				// }
				// if(this.contents.pageJumper) arr.push('jumper')
				// this.layouts = arr.join()
				// this.contents.pageEachNum = 10
			},





			init () {
				this.zhichengOptions = "高级讲师,讲师,助教".split(',')
				this.zhuanyeOptions = "软件工程,计算机科学,数字媒体技术".split(',')
			},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},

			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				let params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: 'id',
					order: 'desc',
				}
 				if(this.searchForm.jiaoshigonghao!='' && this.searchForm.jiaoshigonghao!=undefined){
					params['jiaoshigonghao'] = '%' + this.searchForm.jiaoshigonghao + '%'
				}
 				if(this.searchForm.jiaoshixingming!='' && this.searchForm.jiaoshixingming!=undefined){
					params['jiaoshixingming'] = '%' + this.searchForm.jiaoshixingming + '%'
				}
				if(this.searchForm.xingbie!='' && this.searchForm.xingbie!=undefined){
					params['xingbie'] = this.searchForm.xingbie
				}
				if(this.searchForm.zhicheng!='' && this.searchForm.zhicheng!=undefined){
					params['zhicheng'] = this.searchForm.zhicheng
				}
				if(this.searchForm.zhuanye!='' && this.searchForm.zhuanye!=undefined){
					params['zhuanye'] = this.searchForm.zhuanye
				}
				let user = JSON.parse(this.$storage.getObj('userForm'))
				this.$http({
					url: "jiaoshi/page",
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			},
			// 添加/修改
			addOrUpdateHandler(id,type) {
				this.showFlag = false;
				this.addOrUpdateFlag = true;
				this.crossAddOrUpdateFlag = false;
				if(type!='info'&&type!='msg'){
					type = 'else';
				}
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(id,type);
				});
			},
			importChange(){
				this.importHandler()
				this.getDataList()
			},
			importClcik() {
				this.importVis = true
			},
			importSuccess(e) {
				if(e.code==0){
					this.$message.success('上传成功');
					this.importVis = false
					
				}
			},
			importHandler() {
				this.importUrl = ''
				this.importVisiable = !this.importVisiable;
			},
			// 下载
			download(file){
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.$base.url + 'file/download?fileName=' + arr, {
					headers: {
						token: this.$storage.get('Token')
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$base.name).length>1 ? location.href.split(this.$base.name)[0] :'') + this.$base.name + '/file/download?fileName=' + arr, {
						headers: {
							token: this.$storage.get('Token')
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},
			jiaoshistatusChange(e,row){
				if(row.status==0){
					row.passwordwrongnum = 0
				}
				this.$http({
					url:'jiaoshi/update',
					method:'post',
					data:row
				}).then(res=>{
					if(row.status==1){
						this.$message.error('该用户已锁定')
					}else{
						this.$message.success('该用户已解除锁定')
					}
				})
			},
			// 删除
			async deleteHandler(id ) {
				var ids = id? [Number(id)]: this.dataListSelections.map(item => {
					return Number(item.id);
				});
				await this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(async () => {
					await this.$http({
						url: "jiaoshi/delete",
						method: "post",
						data: ids
					}).then(async ({ data }) => {
						if (data && data.code === 0) {
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.search();
								}
							});
			
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			},


		}

	};
</script>
<style lang="scss" scoped>
	//导出excel
	.export-excel-wrapper{
		display: inline-block;
	}
	
	.center-form-pv {
		.el-date-editor.el-input {
			width: auto;
		}
	}
	
	.el-input {
		width: auto;
	}
	
	// form
	.center-form-pv .el-input {
		background: #fff;
		width: 100%;
	}
	.center-form-pv .el-input /deep/ .el-input__inner {
		border: 0px solid #ddd;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 170px;
		font-size: 16px;
		height: 34px;
	}
	.center-form-pv .el-select {
		width: 100%;
	}
	.center-form-pv .el-select /deep/ .el-input__inner {
		border: 0px solid #ddd;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		width: 170px;
		font-size: 16px;
		height: 34px;
	}
	.center-form-pv .el-date-editor {
		width: 100%;
	}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
		border: 0px solid #ddd;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		width: 170px;
		font-size: 16px;
		height: 34px;
	}
	
	.center-form-pv .search {
		border: 0;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 10px;
		margin: 0 0 0 10px;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: 16px;
		height: 34px;
	}
	
	.center-form-pv .search:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .add {
		border: 0px solid #ddd;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 12px;
		margin: 4px;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .add:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .del {
		border: 0px solid #ddd;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 12px;
		margin: 4px;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .del:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .statis {
		border: 0px solid #ddd;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 12px;
		margin: 4px;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .statis:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .btn18 {
		border: 0px solid #ddd;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 12px;
		margin: 4px;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .btn18:hover {
		opacity: 0.8;
	}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
		border-radius: 100%;
		color: #fff;
		font-weight: 500;
		width: 100%;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
		background: linear-gradient( 90deg, #696EFF 0%, #BDB6D6 100%);
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
		padding: 4px 0;
		background: none;
		border-color: #eee;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: center;
	}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
		padding: 0 0 0 5px;
		word-wrap: normal;
		color: #fff;
		white-space: normal;
		font-weight: bold;
		display: flex;
		vertical-align: middle;
		font-size: 14px;
		line-height: 24px;
		text-overflow: ellipsis;
		word-break: break-all;
		width: 100%;
		align-items: center;
		position: relative;
		min-width: 110px;
	}

	
	.el-table /deep/ .el-table__body-wrapper tbody {
		width: 100%;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
		background: #fff;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #9E9E9E;
		background: #fff;
		font-size: inherit;
		border-color: #eee;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
		padding: 4px 0;
		color: #333;
		background: #f0f0f0;
		border-color: #eee;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #9E9E9E;
		background: #fff;
		font-size: inherit;
		border-color: #eee;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
		padding: 0 0 0 5px;
		overflow: hidden;
		word-break: break-all;
		white-space: normal;
		font-size: inherit;
		line-height: 24px;
		text-overflow: ellipsis;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
		border: 0px solid #157ed2;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: 12px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add:hover {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
		border: 0px solid #157ed2;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: 12px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
		border: 0px solid #157ed2;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: 12px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8 {
		border: 0px solid #157ed2;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: 12px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8:hover {
		opacity: 0.8;
	}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
		margin: 0 10px 0 0;
		color: #666;
		font-weight: 400;
		display: inline-block;
		vertical-align: top;
		font-size: inherit;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev {
		border: none;
		border-radius: 50%;
		padding: 0;
		margin: 0 5px;
		color: #fff;
		background: #1A2C4F;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		min-width: 35px;
		height: 36px;
	}
	
	.main-content .el-pagination /deep/ .btn-next {
		border: none;
		border-radius: 50%;
		padding: 0;
		margin: 0 5px;
		color: #fff;
		background: #1A2C4F;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		min-width: 35px;
		height: 36px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 50%;
		padding: 0;
		margin: 0 5px;
		color: #fff;
		background: #1A2C4F50;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		height: 36px;
	}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 50%;
		padding: 0;
		margin: 0 5px;
		color: #fff;
		background: #1A2C4F50;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		height: 36px;
	}

	.main-content .el-pagination /deep/ .el-pager {
		padding: 0;
		margin: 0;
		display: flex;
		align-items: center;
	}

	.main-content .el-pagination /deep/ .el-pager .number {
		cursor: pointer;
		border-radius: 50%;
		padding: 0 15px;
		margin: 0 5px;
		color: #fff;
		background: #B9B9B9;
		display: inline-block;
		vertical-align: center;
		font-size: 16px;
		line-height: 36px;
		text-align: center;
		height: 36px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
		cursor: pointer;
		border-radius: 50%;
		padding: 0 15px;
		margin: 0 5px;
		color: #fff;
		background: #6C71FD;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 36px;
		text-align: center;
		height: 36px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
		cursor: pointer;
		border-radius: 50%;
		padding: 0 15px;
		margin: 0 5px;
		color: #fff;
		background: #6C71FD;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 36px;
		text-align: center;
		height: 36px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #C0C4CC;
		width: 25px;
		font-size: 14px;
		line-height: 48px;
		text-align: center;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #606266;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: 14px;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 3px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	// list one
	.one .list1-view {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 20px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: 12px;
		height: 32px;
	}
	
	.one .list1-view:hover {
		opacity: 0.5;
	}
	
	.one .list1-edit {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 20px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: 12px;
		height: 32px;
	}
	
	.one .list1-edit:hover {
		opacity: 0.5;
	}
	
	.one .list1-del {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 20px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: 12px;
		height: 32px;
	}
	
	.one .list1-del:hover {
		opacity: 0.5;
	}
	
	.one .list1-btn8 {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 20px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #6C71FD;
		width: auto;
		font-size: 12px;
		height: 32px;
	}
	
	.one .list1-btn8:hover {
		opacity: 0.5;
	}
	
	.main-content .el-table .el-switch {
		display: inline-flex;
		vertical-align: middle;
		line-height: 30px;
		position: relative;
		align-items: center;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--left {
		cursor: pointer;
		margin: 0 10px 0 0;
		color: #333;
		font-weight: 500;
		display: inline-block;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--right {
		cursor: pointer;
		margin: 0 0 0 10px;
		color: #333;
		font-weight: 500;
		display: inline-block;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core {
		border: 1px solid #75c0d6;
		cursor: pointer;
		border-radius: 15px;
		margin: 0;
		background: #75c0d6;
		display: inline-block;
		width: 42px;
		box-sizing: border-box;
		transition: border-color .3s,background-color .3s;
		height: 20px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core::after {
		border-radius: 100%;
		top: 1px;
		left: 1px;
		background: #fff;
		width: 16px;
		position: absolute;
		transition: all .3s;
		height: 16px;
	}
	.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after {
		margin: 0 0 0 -18px;
		left: 100%;
	}
	
	.main-content .el-table .el-rate /deep/ .el-rate__item {
		cursor: pointer;
		display: inline-block;
		vertical-align: middle;
		font-size: 0;
		position: relative;
	}
	.main-content .el-table .el-rate /deep/ .el-rate__item .el-rate__icon {
		margin: 0 3px;
		display: inline-block;
		font-size: 18px;
		position: relative;
		transition: .3s;
	}

</style>
