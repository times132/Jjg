import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const resourceHost = 'http://localhost:9000'

export default new Vuex.Store({
    state: {
        isAuthenticated: false,
        userInfo: null
    },
    getters: {

    },
    mutations: {
        // LOGIN (state, {accessToken}) {
        //     state.accessToken = accessToken
        //     state.isAuthenticated = true
        //     sessionStorage.token = accessToken
        // },
        logout (state) {
            delete sessionStorage.token
            state.isAuthenticated = false
        },
        getUserInfo (state, userInfo) {
            state.userInfo = userInfo
            state.isAuthenticated = true
        }
    },
    actions: {
        login ({ dispatch }, {username, password}) {
            return axios.post(`${resourceHost}/user/login`, {username, password})
                .then(({data}) => {
                    sessionStorage.setItem('token', data.accessToken)

                    dispatch('getUserInfo')
                })
        },
        logout ({commit}) {
            axios.defaults.headers.common['Authorization'] = undefined
            commit('logout')
        },
        getUserInfo ({commit}) {
            let token = sessionStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`

            axios.get('http://localhost:9000/user/me')
                .then(({data}) => {
                    let userInfo = {
                        name: data.name,
                        username: data.username,
                        email: data.email,
                        role: data.roles
                    }
                    commit('getUserInfo', userInfo)
                })
        }
    }
})