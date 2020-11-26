import Vue from 'vue'
import Vuex from 'vuex'
import SecureLS from 'secure-ls'
import LoginStore from './login'
import GoodsStore from './goods'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

const ls = new SecureLS({ isCompression: true })

export default new Vuex.Store({
    state: {
    },
    modules: {
        login: LoginStore,
        goods: GoodsStore
    },
    plugins:  [
        createPersistedState({
            paths: ['login', 'goods'],
            storage: {
                getItem: (key) => ls.get(key),
                setItem: (key, value) => ls.set(key, value),
                removeItem: (key) => ls.remove(key)
            }
        })
    ]
})