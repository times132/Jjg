import Vue from 'vue'
import Vuex from 'vuex'
import LoginStore from './login'
import GoodsStore from './goods'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        resourceHost: 'http://localhost:9000'
    },
    modules: {
        login: LoginStore,
        goods: GoodsStore
    }
})