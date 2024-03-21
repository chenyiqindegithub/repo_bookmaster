import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单跳转当前页面报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  { path: '/', name: 'Manager', component: () => import('@/views/Manager'), redirect: '/login',
    children: [
      { path: 'home', name: 'Home', component: () => import('@/views/manager/Home') },
      { path: 'user', name: 'User', component: () => import('@/views/manager/User')},
      { path: 'person', name: 'Person', component: () => import('@/views/manager/Person')},
      { path: 'password', name: 'Password', component: () => import('@/views/manager/Password')},
      { path: 'book', name: 'book', component: () => import('@/views/manager/Book')},
      { path: 'goods', name: 'Goods', component: () => import('@/views/manager/Goods')},
      { path: 'bookBorrow', name: 'BookBorrow', component: () => import('@/views/manager/BookBorrow')},
      { path: 'goodsExchange', name: 'GoodsExchange', component: () => import('@/views/manager/GoodsExchange')},
      { path: 'notice', name: 'Notice', component: () => import('@/views/manager/Notice')},
    ]
  },
  { path: '/login', name: 'Login', component: () => import('@/views/Login')},
  { path: '*', component: () => import('@/views/404') }
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})


export default router
