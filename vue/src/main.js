import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import axios from 'axios'
import VueAxios from "vue-axios";
import store from "./store/store.js"

Vue.config.productionTip = false
Vue.use(VueAxios , axios);


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
export default {

}
