import { getGoodsList } from "../api/index";

const state = {
    pagination: null
}

const mutations = {
    getGoods (state, data) {
        state.pagination = data
    }
}

const actions = {
    getGoods({ commit }, data) {
        getGoodsList(data.categoryNum, data.criteria)
            .then(({data}) => {
                commit('getGoods', data.pagination)
                console.log(data.goodsList)
                return data.goodsList
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