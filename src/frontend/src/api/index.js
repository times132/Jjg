import axios from 'axios'
import store from '../vuex/index'

const instance = axios.create({
    baseURL: 'http://localhost:9000',
    withCredentials: true,
})

instance.interceptors.request.use(
    function (config) {
        config.headers.post['Content-Type'] = 'application/json'
        return config
    },
    function (error) {
        return Promise.reject(error)
    }
)

instance.interceptors.response.use(
    function (response) {
        return response
    },
    function (error) {
        if (error.response.status === 401){
            store.dispatch("logout")
        }
        return Promise.reject(error)
    }
)

// 상품
function getDetail(subUrl, id) {
    return instance({
        url: subUrl + '/' + id,
        method: 'get'
    })
}

function writeGoods(goodsData) {
    return instance({
        url: '/goods',
        method: 'post',
        data: goodsData
    })
}

function getGoodsList(categoryNum) {
    return instance({
        url: '/goods/' + categoryNum,
        method: 'get'
    })
}

function uploadGoodsImage(file) {
    console.log(file)
    return instance({
        url: '/upload/goods',
        method: 'post',
        data: file
    })
}

// 회원
function userLogin(userData) {
    return instance({
        url: '/user/login',
        method: 'post',
        data: userData
    })
}

function userLogout() {
    return instance({
        url: '/user/logout',
        method: 'get'
    })
}

function getUserInfo() {
    return instance({
        url: '/user/me',
        method: 'get'
    })
}

export {
    getDetail,
    writeGoods,
    getGoodsList,
    uploadGoodsImage,
    userLogin,
    userLogout,
    getUserInfo
}