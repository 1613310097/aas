<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'/'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div v-if="centerType" class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="list-preview">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="list-item">
					<div class="lable">学期：</div>
					<el-input v-model="formSearch.xueqi" placeholder="学期" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-form-item class="list-item">
					<div class="lable">课程：</div>
					<el-input v-model="formSearch.kecheng" placeholder="课程" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-form-item class="list-item">
					<div class="lable">专业：</div>
					<el-input v-model="formSearch.zhuanye" placeholder="专业" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-button class="list-search-btn" v-if=" true " type="primary" @click="getList(1, curFenlei)">
					查询
				</el-button>
				<el-button class="list-add-btn" v-if="btnAuth('kaohecailiao','新增')" type="primary" @click="add('/index/kaohecailiaoAdd')">
					添加
				</el-button>
			</el-form>
			<div class="select2">
				<div class="select2-list" v-for="(item,index) in selectOptionsList" :key="index">
					<div class="label">{{item.name}}：</div>
					<div class="item-body">
						<div class="item" @click="selectClick2(item,-1)" :class="item.check ==-1 ? 'active' : ''">全部</div>
						<div class="item" @click="selectClick2(item,index1)" :class="item.check == index1 ? 'active' : ''" v-for="item1,index1 in item.list" :key="index1">{{item1}}</div>
					</div>
				</div>
			</div>
			<div class="list">
				<!-- 样式一 -->
				<div class="list1 index-pv1">
					<div v-for="(item, index) in dataList" :key="index" @click.stop="toDetail(item)" class="list-item animation-box">
						<img class="image" @click.stop="imgPreView(item.fengmian)" v-if="item.fengmian && item.fengmian.substr(0,4)=='http'&& item.fengmian.split(',w').length>1" :src="item.fengmian" />
						<img class="image" @click.stop="imgPreView(item.fengmian.split(',')[0])" v-else-if="item.fengmian && item.fengmian.substr(0,4)=='http'" :src="item.fengmian.split(',')[0]" />
						<img class="image" @click.stop="imgPreView(baseUrl + (item.fengmian?item.fengmian.split(',')[0]:''))" v-else :src="baseUrl + (item.fengmian?item.fengmian.split(',')[0]:'')" />
						<div class="name">{{item.xueqi}}</div>
						<div class="name">完成状态:{{item.wanchengzhuangtai}}</div>
						<div class="time_item">
							<span class="icon iconfont icon-shijian21"></span>
							<span class="label">发布时间：</span>
							<span class="text">{{item.addtime}}</span>
						</div>
					</div>
				</div>
			</div>

	
			<el-pagination
				background
				id="pagination"
				class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				prev-text="上一页"
				next-text="下一页"
				:hide-on-single-page="false"
				:layout='["total","prev","pager","next","sizes","jumper"].join()'
				:total="total"
				:page-sizes="pageSizes"
				@current-change="curChange"
				@size-change="sizeChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>
			<div class="idea1"></div>
		</div>
		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>
<script>
	export default {
		//数据集合
		data() {
			return {
				selectIndex2: 0,
				selectOptionsList: [],
				layouts: '',
				swiperIndex: -1,
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '考核材料'
					}
				],
				formSearch: {
					xueqi: '',
					kecheng: '',
					jianchaxiang: '',
					wanchengzhuangtai: '',
					zhuanye: '',
				},
				fenlei: [],
				feileiColumn: '',
				dataList: [],
				total: 1,
				pageSize: 20,
				pageSizes: [],
				totalPage: 1,
				curFenlei: '全部',
				isPlain: false,
				indexQueryCondition: '',
				jianchaxiangOptions: [],
				wanchengzhuangtaiOptions: [],
				timeRange: [],
				centerType:false,
				previewImg: '',
				previewVisible: false,
				sortType: 'id',
				sortOrder: 'desc',
			}
		},
		async created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0){
				this.centerType = true
			}
			this.baseUrl = this.$config.baseUrl;
			await this.$http.get('option/jianchaxiang/jianchaxiang').then(res => {
				if (res.data.code == 0) {
					this.jianchaxiangOptions = res.data.data;
					this.selectOptionsList.push({name:'检查项',list:this.jianchaxiangOptions,tableName: 'jianchaxiang',check: -1})
				}
			});
			this.wanchengzhuangtaiOptions = '已审核,未审核'.split(',');
			this.selectOptionsList.push({name:'完成状态',list:this.wanchengzhuangtaiOptions,tableName: 'wanchengzhuangtai',check: -1})
			await this.getFenlei();
			let fenlei = '全部'
			if(this.$route.query.homeFenlei){
				fenlei = this.$route.query.homeFenlei
			}
			this.getList(1, fenlei);
		},
		watch:{
			$route(newValue){
				this.getList(1, newValue.query.homeFenlei);
			}
		},
		//方法集合
		methods: {
			selectClick2(row,index) {
				row.check = index
				if(index == -1){
					this.formSearch[row.tableName] = ''
				}else {
					this.formSearch[row.tableName] = row.list[index]
				}
				this.getList()
			},
			add(path) {
				let query = {}
				if(this.centerType){
					query.centerType = 1
				}
				this.$router.push({path: path,query:query});
			},
			async getFenlei() {
			},
			getList(page, fenlei, ref = '') {
				let params = {
					page,
					limit: this.pageSize,
				};
				let searchWhere = {};
				if (this.formSearch.xueqi != '') searchWhere.xueqi = '%' + this.formSearch.xueqi + '%';
				if (this.formSearch.kecheng != '') searchWhere.kecheng = '%' + this.formSearch.kecheng + '%';
				if (this.formSearch.jianchaxiang != '') searchWhere.jianchaxiang = '%' + this.formSearch.jianchaxiang + '%';
				if (this.formSearch.wanchengzhuangtai != '') searchWhere.wanchengzhuangtai = this.formSearch.wanchengzhuangtai;
				if (this.formSearch.zhuanye != '') searchWhere.zhuanye = '%' + this.formSearch.zhuanye + '%';
				let user = JSON.parse(localStorage.getItem('sessionForm'))
				if (this.sortType) searchWhere.sort = this.sortType
				if (this.sortOrder) searchWhere.order = this.sortOrder
				this.$http.get(`kaohecailiao/${this.centerType?'page':'list'}`, {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.dataList = res.data.data.list;
						this.total = Number(res.data.data.total);
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			curChange(page) {
				this.getList(page);
			},
			prevClick(page) {
				this.getList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getList(1);
			},
			nextClick(page) {
				this.getList(page);
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			toDetail(item) {
				let params = {
					id: item.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/kaohecailiaoDetail', query: params});
			},
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			backClick() {
				this.$router.push({path: '/index/center'});
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.list-preview {
		margin: 0px auto;
		color: #333;
		background: none;
		display: flex;
		width: 1200px;
		font-size: 16px;
		justify-content: flex-start;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.list-form-pv {
			padding: 0;
			margin: 20px 0;
			color: inherit;
			background: none;
			display: flex;
			width: 100%;
			font-size: inherit;
			flex-wrap: wrap;
			height: auto;
			.list-item {
				padding: 0;
				margin: 0 0px 10px 0;
				display: flex;
				font-size: inherit;
				align-items: center;
				flex-wrap: wrap;
				/deep/.el-form-item__content {
					display: flex;
				}
				.lable {
					padding: 0 10px;
					color: #333;
					white-space: nowrap;
					display: inline-block;
					width: auto;
					font-size: 16px;
					line-height: 36px;
				}
				.el-input {
					width: auto;
				}
				.datetimerange {
					border: 1px solid #0066D450 !important;
					border-radius: 8px;
					padding: 3px 3px;
					background: #fff;
					width: auto;
					justify-content: center;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #0066D450;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					color: #333;
					width: auto;
					font-size: 16px;
					line-height: 36px;
					height: 36px;
				}
				.el-select {
					width: 100%;
				}
				.el-select /deep/ .el-input__inner {
				}
				.el-date-editor {
					width: auto;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #0066D450;
					border-radius: 4px;
					padding: 0 0px 0 30px;
					margin: 0;
					color: #333;
					width: auto;
					font-size: 16px;
					line-height: 36px;
					height: 36px;
				}
			}
			.list-search-btn {
				cursor: pointer;
				border: 0;
				border-radius: 4px;
				padding: 0px 15px;
				margin: 0 10px 0 10px;
				color: #fff;
				background: #0066D4;
				width: auto;
				font-size: inherit;
				line-height: 36px;
				height: 36px;
				i {
					margin: 0 10px 0 0;
					color: #fff;
					font-size: inherit;
				}
			}
			.list-add-btn {
				cursor: pointer;
				border: 1px solid #0066D4;
				border-radius: 4px;
				padding: 0px 15px;
				margin: 0 10px 0 0;
				color: #0066D4;
				background: #fff;
				width: auto;
				font-size: inherit;
				line-height: 36px;
				height: 36px;
				i {
					margin: 0 10px 0 0;
					color: #fff;
					font-size: inherit;
				}
			}
		}
		.select2 {
			padding: 10px 0;
			margin: 10px auto 0;
			background: none;
			width: 100%;
			font-size: 15px;
			height: auto;
			.select2-list {
				padding: 5px 5px;
				margin: 0 0 10px;
				background: none;
				width: 100%;
				height: auto;
				.label {
					padding: 0 5px;
					color: #333;
					font-weight: 500;
					display: inline-block;
					font-size: inherit;
					line-height: 32px;
				}
				.item-body {
					display: inline-block;
					width: auto;
					flex-wrap: wrap;
					height: auto;
					.item {
						border-radius: 4px;
						padding: 0 5px;
						color: inherit;
						background: none;
						display: inline-block;
						font-size: inherit;
						line-height: 32px;
						text-align: center;
						min-width: 50px;
					}
					.item:hover {
						cursor: pointer;
						color: #333;
						background: #0066D430;
					}
					.item.active {
						cursor: pointer;
						color: #333;
						background: #0066D430;
						display: inline-block;
						min-width: 50px;
						text-align: center;
					}
				}
			}
		}
		.list {
			margin: 20px 0;
			overflow: hidden;
			background: #fff;
			width: calc(100% - 0px);
			clear: both;
			font-size: 15px;
			order: 8;
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
				
			.index-pv1 .animation-box:hover {
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
				
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list1 {
				padding: 0;
				align-content: flex-start;
				background: #fff;
				display: flex;
				width: 100%;
				border-color: #ddd;
				border-width: 1px 0 0 1px;
				justify-content: flex-start;
				align-items: flex-start;
				border-style: solid;
				flex-wrap: wrap;
				height: auto;
				.list-item {
					cursor: pointer;
					padding: 10px;
					margin: 0;
					background: #fff;
					display: flex;
					width: 20%;
					border-color: #ddd;
					border-width: 0 1px 1px 0;
					position: relative;
					border-style: solid;
					flex-wrap: wrap;
					height: auto;
					.image {
						margin: 5px 0;
						object-fit: cover;
						display: block;
						width: 100%;
						height: 160px;
						order: -1;
					}
					.price {
						padding: 0 10px;
						color: #f00;
						font-size: 14px;
						line-height: 1.5;
					}
					.name {
						padding: 0 10px;
						overflow: hidden;
						color: #333;
						white-space: nowrap;
						width: 100%;
						font-size: 15px;
						line-height: 30px;
						text-overflow: ellipsis;
						text-align: center;
						order: -1;
					}
					.time_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.publisher_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.like_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.collect_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.view_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
				}
			}
		}
		.idea1 {
			background: #fff;
			width: 100%;
			order: 6;
			height: 1px;
		}
	}
</style>
