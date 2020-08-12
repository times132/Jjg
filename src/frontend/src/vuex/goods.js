import API from '../api/index'

const state = {
    goods: null,
    totalElements: 0,
    totalPages: 0,
    pageSize: 0
}

const mutations = {
    getGoods (state, data) {
        state.goods = data.content
        state.totalElements = data.totalElements
        state.totalPages = data.totalPages
        state.pageSize = data.size
    }
}

const actions = {
    getGoods({ commit }, params) {
        return API.get(`/goods/`+params)
            .then(({data}) => {
                commit('getGoods', data)
            })
    },
}

const getters = {

}

export default {
    state,
    mutations,
    actions,
    getters
}