import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './vuex'
import {BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)
Vue.config.productionTip = false

new Vue({
  store,
  router,
  beforeCreate() {
    let token = sessionStorage.getItem('token')

    if (token) { // 세션스토리지에 토큰이 있으면 새로고침할 때 유저 정보를 불러옴
      this.$store.dispatch('getUserInfo')
    }
  },
  render: h => h(App),
}).$mount('#app')
