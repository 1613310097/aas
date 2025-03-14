<template>
	<div class="home-preview">




		<!-- 系统简介 -->
		<div id="system" class="animate__animated">
			<div class="system_item">
				<div class="system_title_box">
					<div class="system_title">{{systemIntroductionDetail.title}}</div>
					<div class="system_subtitle">{{systemIntroductionDetail.subtitle}}</div>
				</div>
				<div class="system_img">
					<img :src="baseUrl + systemIntroductionDetail.picture1">
					<img :src="baseUrl + systemIntroductionDetail.picture2">
					<img :src="baseUrl + systemIntroductionDetail.picture3">
				</div>
				<div class="system_content ql-snow ql-editor" v-html="systemIntroductionDetail.content"></div>
				<div class="system_idea1" />
				<div class="system_idea2" />
				<div class="system_idea3" />
				<div class="system_idea4" />
				<div class="system_more" @click="toDetail('systemintroDetail',systemIntroductionDetail)">
					<span>更多</span>
					<span class="icon iconfont icon-gengduo1"></span>
				</div>
			</div>
		</div>
		<!-- 系统简介 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				systemIntroductionDetail: {},
				newsList: [],





			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getSystemIntroduction();
			this.getList();
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},

			listIndexClick11(index, name) {
				this['listIndex11' + name] = index[this['listColumn11' + name]]
				this.getList()
			},

			handleScroll() {
				let arr = [
					{id:'about',css:'animate__'},
					{id:'system',css:'animate__'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getSystemIntroduction() {
				this.$http.get('systemintro/detail/1', {}).then(res => {
					if(res.data.code == 0) {
						this.systemIntroductionDetail = res.data.data;
					}
				})
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
			
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0px auto;
		flex-direction: column;
		background: #fff;
		display: flex;
		width: 100%;
		#system {
			padding: 0;
			margin: 15px 0;
			background: #fff;
			width: 100%;
			height: auto;
			order: 5;
			.system_item {
				padding: 0;
				margin: 0px auto;
				z-index: 9;
				overflow: hidden;
				background: none;
				display: block;
				width: 1200px;
				position: relative;
				flex-wrap: wrap;
				.system_title_box {
					padding: 0px;
					margin: 0 auto 10px;
					background: none;
					width: 1200px;
					position: relative;
					text-align: left;
					.system_title {
						margin: 0;
						color: #000;
						background: none;
						width: auto;
						font-size: 24px;
						line-height: 36px;
					}
					.system_subtitle {
						color: #999;
						display: none;
						font-size: 24px;
					}
				}
				.system_img {
					padding: 0;
					margin: 0;
					background: none;
					display: flex;
					width: 280px;
					float: left;
					flex-wrap: wrap;
					height: 210px;
					img:nth-child(1) {
						margin: 0;
						object-fit: cover;
						display: block;
						width: 100%;
						height: 100%;
					}
					img:nth-child(2) {
						object-fit: cover;
						display: none;
						width: 100%;
						height: 480px;
					}
					img:nth-child(3) {
						object-fit: cover;
						display: none;
						width: 330px;
						height: 120px;
					}
				}
				.system_content {
					border: 1px solid #eaeaea;
					padding: 20px;
					margin: 0;
					overflow: hidden;
					color: #333;
					text-indent: 2em;
					background: #fff;
					width: calc(100% - 320px);
					font-size: 15px;
					line-height: 30px;
					float: right;
					height: 210px;
				}
				.system_idea1 {
					z-index: -1;
					top: 0;
					background: #d10602;
					display: none;
					width: 50%;
					position: absolute;
					right: 0;
					height: 400px;
				}
				.system_idea2 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_idea3 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_idea4 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_more {
					border: 0px solid #999;
					margin: 0;
					top: 5px;
					background: none;
					display: block;
					width: auto;
					line-height: 32px;
					position: absolute;
					right: 0;
					text-align: right;
					span:nth-child(1) {
						color: #999;
						font-size: 14px;
					}
					span:nth-child(2) {
						color: #999;
						font-size: 14px;
					}
				}
				.system_more:hover {
					cursor: pointer;
					opacity: 1;
				}
			}
		}
	}
</style>
