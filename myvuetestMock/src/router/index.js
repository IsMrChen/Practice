import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import MyTodoPage from "../views/MyTodoPage";//我的待办页面
import MyDraftPage from "../views/MyDraftPage";//我的已办页面
import ReceiptIssue from "../views/ReceiptIssue";//收据开具
import ReceiptQuiry from "../views/ReceiptQuiry";//收据查询
import Index from "../views/Index";

Vue.use(VueRouter)

const  routes=[
  {
    path:"/",
    name:"工作台",
    component:Index,
    redirect:"/ReceiptIssue",
    children:[
      {
        path:"/ReceiptIssue",
        name:"收据开具",
        component:ReceiptIssue
      },
      {
        path:"/ReceiptQuiry",
        name:"收据查询",
        component: ReceiptQuiry
      }
    ]
  },
  {
    path:"/",
    name:"我的",
    component:Index,
    children:[
      {
        path:"/MyTodoPage",
        name:"我的待办",
        component:MyTodoPage
      },
      {
        path:"/MyDraftPage",
        name:"我的草稿",
        component: MyDraftPage
      }
    ]
  }
]
/*const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/!* webpackChunkName: "about" *!/ '../views/About.vue')
  }
]*/

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
