import axios from 'axios'
import store from '../vuex'

const instance = axios.create({
    baseURL: 'http://localhost:9000',
    withCredentials: true,
})

instance.interceptors.request.use(
    function (config) {
        config.headers.post['Content-Type'] = 'application/json'
        config.headers.put['Content-Type'] = 'application/json'
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
            console.log(error.response)
            if (error.response.data.message !== 'login error'){
                store.dispatch("logout")
            }
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

function writeBoard(boardData) {
    return instance({
        url: '/api/board',
        method: 'post',
        data: boardData
    })
}

function getBoardList(categoryNum, criteria) {
    return instance({
        url: '/api/board/' + categoryNum,
        method: 'get',
        params: {
            page: criteria.page,
            pageSize: criteria.pageSize,
            type: criteria.type,
            keyword: criteria.keyword
        }
    })
}

function updateBoard(boardData, bid) {
    return instance({
        url: '/api/board/' + bid,
        method: 'put',
        data: boardData
    })
}

function deleteBoard(bid) {
    return instance({
        url: '/api/board/' + bid,
        method: 'delete'
    })
}

function displayBoardImage(imageName) {
    console.log(imageName)
    return instance({
        url: '/api/display',
        method: 'get',
        params: {
            imageName: imageName
        }
    })
}

function uploadBoardImage(file) {
    return instance({
        url: '/api/upload/board',
        method: 'post',
        data: file,
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
        method: 'post'
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
    writeBoard,
    getBoardList,
    updateBoard,
    deleteBoard,
    displayBoardImage,
    uploadBoardImage,
    userLogin,
    userLogout,
    getUserInfo
}