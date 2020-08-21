import axios from 'axios'

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
        return Promise.reject(error)
    }
)

export default instance;