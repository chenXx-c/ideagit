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
    ]

  },
  {
    path: '/about',
    name: 'about',
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/LoginView',
    name: 'LoginView',
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue')
  }
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
