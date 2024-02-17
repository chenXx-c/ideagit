import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'ManageView',
    component: () => import(/* webpackChunkName: "about" */ '../views/ManageView.vue'),
    redirect:"/HomeView",
    children:[
      {
        path: 'HomeView',
        name: '首页',
        component: ()=> import(/* webpackChunkName: "about" */ '../views/HomeView.vue')
      },
      {
        path: 'UserView',
        name: '用户管理',
        component: ()=> import(/* webpackChunkName: "about" */ '../views/UserView.vue')
      },
      {
        path: 'RoleView',
        name: '角色管理',
        component: ()=> import(/* webpackChunkName: "about" */ '../views/RoleView.vue')
      },
      {
        path: 'niganma',
        name: 'niganma管理',
        component: ()=> import(/* webpackChunkName: "about" */ '../views/niganma.vue')
      },
      {
        path: 'Menu',
        name: '菜单信息',
        component: ()=> import(/* webpackChunkName: "about" */ '../views/Menu.vue')
      },
      {
        path: 'Person',
        name: '个人信息',
        component: ()=> import(/* webpackChunkName: "about" */ '../views/PersonView.vue')
      },
      {
        path: '/File',
        name: '文件管理',
        component: () => import(/* webpackChunkName: "about" */ '../views/File.vue')
      }
    ]

  },
  {
    path: '/Register',
    name: '注册',
        component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
  },
  {
    path: '/LoginView',
    name: '登录',
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue')
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next)=>{
  localStorage.setItem("currentPathName",to.name)//设置当前路由名称
  store.commit("setPath")//触发store的数据更新
  next()//放行路由
})


export default router
