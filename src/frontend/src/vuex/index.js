import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'axios'
import LoginStore from './login'
import GoodsStore from './goods'

Vue.use(Vuex)

// const resourceHost = 'http://localhost:9000'

export default new Vuex.Store({
    modules: {
        login: LoginStore,
        goods: GoodsStore
    }
})