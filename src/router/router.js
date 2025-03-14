import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import payList from '../pages/pay'

import jiaoshiList from '../pages/jiaoshi/list'
import jiaoshiDetail from '../pages/jiaoshi/detail'
import jiaoshiAdd from '../pages/jiaoshi/add'
import jianchaxiangList from '../pages/jianchaxiang/list'
import jianchaxiangDetail from '../pages/jianchaxiang/detail'
import jianchaxiangAdd from '../pages/jianchaxiang/add'
import kaohecailiaoList from '../pages/kaohecailiao/list'
import kaohecailiaoDetail from '../pages/kaohecailiao/detail'
import kaohecailiaoAdd from '../pages/kaohecailiao/add'
import lunwenxinxiList from '../pages/lunwenxinxi/list'
import lunwenxinxiDetail from '../pages/lunwenxinxi/detail'
import lunwenxinxiAdd from '../pages/lunwenxinxi/add'
import jianchajiluList from '../pages/jianchajilu/list'
import jianchajiluDetail from '../pages/jianchajilu/detail'
import jianchajiluAdd from '../pages/jianchajilu/add'
import dianziqianzhangList from '../pages/dianziqianzhang/list'
import dianziqianzhangDetail from '../pages/dianziqianzhang/detail'
import dianziqianzhangAdd from '../pages/dianziqianzhang/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'jiaoshi',
					component: jiaoshiList
				},
				{
					path: 'jiaoshiDetail',
					component: jiaoshiDetail
				},
				{
					path: 'jiaoshiAdd',
					component: jiaoshiAdd
				},
				{
					path: 'jianchaxiang',
					component: jianchaxiangList
				},
				{
					path: 'jianchaxiangDetail',
					component: jianchaxiangDetail
				},
				{
					path: 'jianchaxiangAdd',
					component: jianchaxiangAdd
				},
				{
					path: 'kaohecailiao',
					component: kaohecailiaoList
				},
				{
					path: 'kaohecailiaoDetail',
					component: kaohecailiaoDetail
				},
				{
					path: 'kaohecailiaoAdd',
					component: kaohecailiaoAdd
				},
				{
					path: 'lunwenxinxi',
					component: lunwenxinxiList
				},
				{
					path: 'lunwenxinxiDetail',
					component: lunwenxinxiDetail
				},
				{
					path: 'lunwenxinxiAdd',
					component: lunwenxinxiAdd
				},
				{
					path: 'jianchajilu',
					component: jianchajiluList
				},
				{
					path: 'jianchajiluDetail',
					component: jianchajiluDetail
				},
				{
					path: 'jianchajiluAdd',
					component: jianchajiluAdd
				},
				{
					path: 'dianziqianzhang',
					component: dianziqianzhangList
				},
				{
					path: 'dianziqianzhangDetail',
					component: dianziqianzhangDetail
				},
				{
					path: 'dianziqianzhangAdd',
					component: dianziqianzhangAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
