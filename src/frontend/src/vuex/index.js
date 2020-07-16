import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const resourceHost = 'http://localhost:9000'

export default new Vuex.Store({
    state: {
        accessToken: null,
        isAuthenticated: false
    },
    getters: {

    },
    mutations: {
        LOGIN (state, {accessToken}) {
            state.accessToken = accessToken,
            state.isAuthenticated = true
        },
        LOGOUT (state) {
            state.accessToken = null
        }
    },
    actions: {
        LOGIN ({commit}, {username, password}) {
            return axios.post(`${resourceHost}/user/login`, {username, password})
                .then(({data}) => {
                    commit('LOGIN', data)
                    axios.defaults.headers.common['Authorization'] = `Bearer ${data.accessToken}`;
                })
        },
        LOGOUT ({commit}) {
            commit('LOGOUT')
        },
    }
})