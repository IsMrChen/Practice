import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import VueCookies from 'vue-cookies'    //设置cookie引入
Vue.use(VueCookies)

import Vant from 'vant';
import 'vant/lib/index.css';
import {AxiosInstance as Axios} from "axios";
Vue.use(Vant);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

//这一部分暂时有问题：
Axios.defaults.headers.common['Authentication-Token'] = store.state.token;
//添加请求拦截请求
axios.interceptors.request.use(function (config) {
  //判断是否存在token，如果存在将每个页面的header添加token
  if(store.state.token){
    config.headers.common['Authorization']="Bearer"+this.$cookies.get("token");
  }
  return config
},function (error) {
      router.push('/login')
      return Promise.reject(error)
    }
)
//添加相应拦截器
axios.interceptors.response.use(function (response) {
  return response
},function (error) {
      switch (error.response.status) {
        case 401:
          router.push('/login')
      }
      return Promise.reject(error)
    }
)

