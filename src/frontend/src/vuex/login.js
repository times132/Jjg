import { userLogin, userLogout, getUserInfo } from "../api/index";

const state = {
    isAuthenticated: false,
    userInfo: null
}

const mutations = {
    login (state, value) {
        state.isAuthenticated = value
    },
    logout (state) {
        state.isAuthenticated = false
        state.userInfo = null
    },
    getUserInfo (state, userInfo) {
        state.userInfo = userInfo
        state.isAuthenticated = true
    }
}

const actions = {
    login ({ commit, dispatch }, {username, password}) {
        return userLogin({username, password})
            .then(() => {
                commit('login', true)
                dispatch('getUserInfo')
            })
            .catch((error) => {
                return Promise.reject(error.response.status)
            })
    },
    logout ({commit}) {
        userLogout()
            .then(() => {
                commit('logout')
            })
            .catch((error) => {
                console.log(error)
                commit('logout')
            })
    },
    getUserInfo ({ commit }) {
        getUserInfo()
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
    getIsAuth: state => {
        return state.isAuthenticated
    },
    getAuth: state => {
        return state.userInfo === null ? "" : state.userInfo.role
    }
}

export default {
    state,
    mutations,
    actions,
    getters
}