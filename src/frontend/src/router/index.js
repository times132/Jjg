import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home'
import Goods from '../views/Goods'
import Login from '../views/Login'
import Me from '../views/Profile'
import Write from '../views/Write'

Vue.use(VueRouter)

const requireAuth = (from, to, next) => {
    let isAuthentication = sessionStorage.getItem('token')

    if (isAuthentication){ // 로그인 되있을 때
        return next()
    } else { // 로그인 안 돼있을 때
        next({
            path: '/login',
            query: { redirect: from.fullPath }
        })
    }
}

const routes = [
    {
        path: '/',
        name: 'home',
        component: Home
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/me',
        name: 'me',
        component: Me,
        beforeEnter: requireAuth
    },
    {
        path: '/goods/write',
        name: 'write',
        component: Write
        // beforeEnter: requireAuth
    },
    {
        path: '/goods/:categorynum',
        name: 'goods',
        component: Goods,
        props: true
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router