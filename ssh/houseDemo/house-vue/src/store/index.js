import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

//对token状态进行监管
//这一部分暂时没用到
export default new Vuex.Store({
  state: {
    token:''
  },
  mutations: {
    set_token(state,token){
      state.token=token;
      sessionStorage.setItem("token",token);
    },
    del_token(state,token){
      state.token='',
      sessionStorage.removeItem('token')
    }
  },
  actions: {
  },
  modules: {
  }
})
