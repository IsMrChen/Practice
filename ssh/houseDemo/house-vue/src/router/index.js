import Vue from 'vue'
import VueRouter from 'vue-router'
import Register from "../views/Register";
import Login from "../views/Login";
import index from "../views/index";
import ContactsList from "../components/ContactsList";
import ContactsIssue from "../components/ContactsIssue";
Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    name:'login',
    component:Login
  },
    {
    path:'/register',
    name: 'register',
    component:Register
  },{
    path:'/index',
    name:'用户管理',
    component:index,
    redirect:"/contacts",
    children:[
      {
        path:'/contacts',
        name:'用户列表',
        component:ContactsList
      },
      {
        path:'/issue',
        name:'用户流程',
        component:ContactsIssue
      },
    ]
  }
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
//页面刷新时，重新赋值token
/*if(sessionStorage.getItem("token")){
  store.commit("set_token",sessionStorage.getItem("token"));
}*/
export default router
