import axios from "axios";

const state = {
    isAuthenticated: false,
    userInfo: null
}

const mutations = {
    logout (state) {
        delete sessionStorage.token
        state.isAuthenticated = false
    },
    getUserInfo (state, userInfo) {
        state.userInfo = userInfo
        state.isAuthenticated = true
    }
}

const actions = {
    login ({ dispatch, rootState }, {username, password}) {
        return axios.post(`${rootState.resourceHost}/user/login`, {username, password})
            .then(({data}) => {
                sessionStorage.setItem('token', data.accessToken)

                dispatch('getUserInfo')
            })
    },
    logout ({commit}) {
        axios.defaults.headers.common['Authorization'] = undefined
        commit('logout')
    },
    getUserInfo ({ commit, rootState}) {
        let token = sessionStorage.getItem('token')
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`

        axios.get(`${rootState.resourceHost}/user/me`)
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

const getters = {

}

export default {
    state,
    mutations,
    actions,
    getters
}