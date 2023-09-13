import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex);

export default new Vuex.Store({
  state:{
    account: {
      username: '',
      password: '',
      email:'',
      phone:'',
      position:'',
      flag: true
    }
  },
})

