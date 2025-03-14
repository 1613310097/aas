<template>
	<div class="main-content" :style='{"padding":"20px 10px","fontSize":"15px"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"margin":"0 10px 20px"}' :inline="true" :model="searchForm">
				<el-row :style='{"padding":"10px","flexWrap":"wrap","background":"none","display":"flex"}' >
					<div :style='{"margin":"0 10px 10px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#9E9E9E","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">学期</label>
						<el-input v-model="searchForm.xueqi" placeholder="学期" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 10px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#9E9E9E","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">论文名称</label>
						<el-input v-model="searchForm.lunwenmingcheng" placeholder="论文名称" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 10px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#9E9E9E","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">学生姓名</label>
						<el-input v-model="searchForm.xueshengxingming" placeholder="学生姓名" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 10px 0","display":"flex"}' class="select" label="检查项" prop="jianchaxiang">
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#9E9E9E","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">检查项</label>
						<el-select clearable v-model="searchForm.jianchaxiang" placeholder="请选择检查项" multiple>
							<el-option v-for="(item,index) in jianchaxiangOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<div :style='{"margin":"0 10px 10px 0","display":"flex"}' class="select" label="完成状态" prop="wanchengzhuangtai">
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#9E9E9E","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">完成状态</label>
						<el-select clearable v-model="searchForm.wanchengzhuangtai" placeholder="请选择完成状态" >
							<el-option v-for="(item,index) in wanchengzhuangtaiOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<div :style='{"margin":"0 10px 10px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#9E9E9E","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">专业</label>
						<el-input v-model="searchForm.zhuanye" placeholder="专业" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<el-button class="search" type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing07" :style='{"margin":"0 2px","fontSize":"16px","color":"#fff","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>

				<el-row class="actions" :style='{"padding":"10px","margin":"20px 0","flexWrap":"wrap","background":"none","display":"flex"}'>
					<el-button class="add" v-if="isAuth('lunwenxinxi','新增')" type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
						添加
					</el-button>
					<el-button class="del" v-if="isAuth('lunwenxinxi','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
						删除
					</el-button>

					<el-button class="btn18" v-if="isAuth('lunwenxinxi','导入')" type="success" @click="importHandler()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
						导入
					</el-button>
					<el-button class="btn18" v-if="isAuth('lunwenxinxi','上传模板')" type="success" @click="importClcik">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
						上传模板
					</el-button>
					<el-button class="btn18" v-if="isAuth('lunwenxinxi','下载模板')" type="success" @click="download('upload/lunwenxinxi_template.xlsx')">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
						下载模板
					</el-button>

					<download-excel v-if="isAuth('lunwenxinxi','导出')" class="export-excel-wrapper" :data = "dataList" :fields = "json_fields" name = "论文信息.xls">
						<!-- 导出excel -->
						<el-button class="btn18" type="success">
							<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
							导出
						</el-button>
					</download-excel>
					<el-button class="btn18" v-if="isAuth('lunwenxinxi','生成数据')" type="success" @click="genDataClick()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"34px"}'></span>
						生成数据
					</el-button>
				</el-row>
			</el-form>
			<div :style='{"width":"100%","padding":"0 10px","fontSize":"14px","color":"#000"}'>
				<el-table class="tables"
					:stripe='false'
					:style='{"padding":"10px 0","borderColor":"#eee","borderRadius":"20px 20px 0 0","borderWidth":"1px 0 0 1px","background":"#fff","width":"100%","fontSize":"inherit","borderStyle":"solid"}' 
					:border='true'
					v-if="isAuth('lunwenxinxi','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'  
						prop="xueqi"
						label="学期">
						<template slot-scope="scope">
							{{scope.row.xueqi}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="lunwenmingcheng"
						label="论文名称">
						<template slot-scope="scope">
							{{scope.row.lunwenmingcheng}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="xueshengxingming"
						label="学生姓名">
						<template slot-scope="scope">
							{{scope.row.xueshengxingming}}
						</template>
					</el-table-column>
					<el-table-column  :resizable='true' prop="fengmian" width="200" label="封面">
						<template slot-scope="scope">
							<div v-if="scope.row.fengmian">
								<img v-if="scope.row.fengmian.substring(0,4)=='http'&&scope.row.fengmian.split(',w').length>1" :src="scope.row.fengmian" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.fengmian)">
								<img v-else-if="scope.row.fengmian.substring(0,4)=='http'" :src="scope.row.fengmian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.fengmian.split(',')[0])">
								<img v-else :src="$base.url+scope.row.fengmian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView($base.url+scope.row.fengmian.split(',')[0])">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="jianchaxiang"
						label="检查项">
						<template slot-scope="scope">
							{{scope.row.jianchaxiang}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="jianchaduixiang"
						label="检查对象">
						<template slot-scope="scope">
							{{scope.row.jianchaduixiang}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' prop="wenjian" label="文件">
						<template slot-scope="scope">
							<el-button v-if="scope.row.wenjian" type="text" size="small" @click="download(scope.row.wenjian)">下载</el-button>
							<span v-else >无</span>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="wanchengzhuangtai"
						label="完成状态">
						<template slot-scope="scope">
							{{scope.row.wanchengzhuangtai}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="jiaoshigonghao"
						label="教师工号">
						<template slot-scope="scope">
							{{scope.row.jiaoshigonghao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="jiaoshixingming"
						label="批改老师">
						<template slot-scope="scope">
							{{scope.row.jiaoshixingming}}
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
						prop="zhuanye"
						label="专业">
						<template slot-scope="scope">
							{{scope.row.zhuanye}}
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button class="view" v-if=" isAuth('lunwenxinxi','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								查看
							</el-button>
							<el-button class="btn8" v-if="isAuth('lunwenxinxi','检查操作')" @click="jianchajiluCrossAddOrUpdateHandler(scope.row,'cross','','','wanchengzhuangtai','已审核','已审核,未审核'.split(',')[0])" type="success">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								检查操作
							</el-button>
							<el-button class="edit" v-if=" isAuth('lunwenxinxi','修改') " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								修改
							</el-button>




							<el-button class="del" v-if="isAuth('lunwenxinxi','删除') " type="primary" @click="deleteHandler(scope.row.id )">
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

		<jianchajilu-cross-add-or-update v-if="jianchajiluCrossAddOrUpdateFlag" :parent="this" ref="jianchajiluCrossaddOrUpdate"></jianchajilu-cross-add-or-update>


		<el-dialog title="导入" :visible.sync="importVisiable" width="50%">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item class="upload" label="文件" prop="excelFile">
				  <excel-file-upload
				  tip="点击上传直接导入excel文件"
				  action="lunwenxinxi/importExcel"
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
					<el-upload class="upload-demo" drag :action="$base.url + 'file/upload?type=lunwenxinxi_template'"
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

		<el-dialog :title="'生成数据'" :visible.sync="genDataVisible" width="50%">
			<el-form ref="form" :model="genDataForm" label-width="100px">
				<el-form-item label="数据量">
					<el-input-number v-model="genDataForm.recordNum" :min="1" :max="3000" label="数据量"></el-input-number>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="genDataVisible = false">关闭</el-button>
				<el-button @click="genDataSave" type="primary">生成</el-button>
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
	import jianchajiluCrossAddOrUpdate from "../jianchajilu/add-or-update";
	import {
		Loading
	} from 'element-ui';
	export default {
		data() {
			return {
				indexQueryCondition: '',
				jianchaxiangOptions: [],
				wanchengzhuangtaiOptions: [],
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
				jianchajiluCrossAddOrUpdateFlag: false,
				layouts: ["prev","pager","next"],
//导出excel
				json_fields: {
					"学期": "xueqi",    //常规字段
					"论文名称": "lunwenmingcheng",    //常规字段
					"学生姓名": "xueshengxingming",    //常规字段
					"封面": "fengmian",    //常规字段
					"检查项": "jianchaxiang",    //常规字段
					"检查对象": "jianchaduixiang",    //常规字段
					"文件": "wenjian",    //常规字段
					"完成状态": "wanchengzhuangtai",    //常规字段
					"任务内容": "renwuneirong",    //常规字段
					"教师工号": "jiaoshigonghao",    //常规字段
					"批改老师": "jiaoshixingming",    //常规字段
					"联系手机": "lianxishouji",    //常规字段
					"专业": "zhuanye",    //常规字段
					"用户id": "userid",    //常规字段
				},
				json_meta: [
					[
						{
							" key ": " charset ",
							" value ": " utf- 8 "
						}
					]
				],
				genDataVisible: false,
				genDataForm: {},
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
			jianchajiluCrossAddOrUpdate,
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
			jianchajiluCrossAddOrUpdateHandler(row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue){
				this.showFlag = false;
				this.addOrUpdateFlag = false;
				this.jianchajiluCrossAddOrUpdateFlag = true;
				this.$storage.set('crossObj',row);
				this.$storage.set('crossTable','lunwenxinxi');
				this.$storage.set('statusColumnName',statusColumnName);
				this.$storage.set('statusColumnValue',statusColumnValue);
				this.$storage.set('tips',tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$message({
								message: tips,
								type: "success",
								duration: 1500,
								onClose: () => {
									this.getDataList();
								}
							});
							this.showFlag = true;
							this.jianchajiluCrossAddOrUpdateFlag = false;
							return;
						}
					}
				}
				this.$nextTick(() => {
					this.$refs.jianchajiluCrossaddOrUpdate.init(row.id,type);
				});
			},





			init () {
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
				this.wanchengzhuangtaiOptions = "已审核,未审核".split(',')
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
 				if(this.searchForm.xueqi!='' && this.searchForm.xueqi!=undefined){
					params['xueqi'] = '%' + this.searchForm.xueqi + '%'
				}
 				if(this.searchForm.lunwenmingcheng!='' && this.searchForm.lunwenmingcheng!=undefined){
					params['lunwenmingcheng'] = '%' + this.searchForm.lunwenmingcheng + '%'
				}
 				if(this.searchForm.xueshengxingming!='' && this.searchForm.xueshengxingming!=undefined){
					params['xueshengxingming'] = '%' + this.searchForm.xueshengxingming + '%'
				}
				if(this.searchForm.jianchaxiang&&this.searchForm.jianchaxiang.length){
					let jianchaxiangArr = []
					for(let i in this.searchForm.jianchaxiang){
						jianchaxiangArr.push('%' + this.searchForm.jianchaxiang[i] + '%')
					}
					params['jianchaxiang'] = jianchaxiangArr.join(',')
				}
				if(this.searchForm.jianchaduixiang!='' && this.searchForm.jianchaduixiang!=undefined){
					params['jianchaduixiang'] = this.searchForm.jianchaduixiang
				}
				if(this.searchForm.wanchengzhuangtai!='' && this.searchForm.wanchengzhuangtai!=undefined){
					params['wanchengzhuangtai'] = this.searchForm.wanchengzhuangtai
				}
				if(this.searchForm.jiaoshigonghao!='' && this.searchForm.jiaoshigonghao!=undefined){
					params['jiaoshigonghao'] = this.searchForm.jiaoshigonghao
				}
 				if(this.searchForm.zhuanye!='' && this.searchForm.zhuanye!=undefined){
					params['zhuanye'] = '%' + this.searchForm.zhuanye + '%'
				}
				let user = JSON.parse(this.$storage.getObj('userForm'))
				this.$http({
					url: "lunwenxinxi/page",
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
						url: "lunwenxinxi/delete",
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


			// 数据生成
			genDataClick(){
				this.genDataVisible = true
				this.genDataForm = {
					recordNum: 0
				}
			},
			genDataSave(){
				this.genDataVisible = false
				let loading = null
				loading = Loading.service({
					target: this.$refs['roleMenuBox'],
					fullscreen: false,
					text: '数据生成中...'
				})
				this.$http({
					url: 'lunwenxinxi/batch/gen?recordNum=' + this.genDataForm.recordNum,
					method: 'post'
				}).then(res=>{
					if(res.data&&res.data.code==0){
						if (loading) loading.close()
						this.$message({
							message: "数据生成成功！",
							type: "success",
							duration: 1500,
							onClose: () => {
								
								this.getDataList()
							}
						});
						
					}
				})
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
