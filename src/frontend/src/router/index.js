import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home'
import Login from '../views/Login'
import Me from '../views/Profile'
import Write from '../views/Board/Write'
import Modify from '../views/Board/Modify'
import Detail from '../views/Board/Detail'
import List from '../views/Board/List'
import store from '../vuex'
import Signup from '../views/Signup'

Vue.use(VueRouter)

const requireAuth = (from, to, next) => {
    if (store.state.login.isAuthenticated){ // 로그인 되있을 때
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
        path: '/board/write',
        name: 'write',
        component: Write,
        beforeEnter: requireAuth
    },
    {
        path: '/board/modify/:gid',
        name: 'modify',
        component: Modify,
        beforeEnter: requireAuth
    },
    {
        path: '/board/:categoryNum',
        name: 'board',
        component: List,
        props: true
    },
    {
        path: '/board/:categoryNum/:bid',
        name: 'detail',
        component: Detail,
        props: true
    },
    {
        path: '/signup',
        name: 'signup',
        component: Signup
    },
    {
        path: '/display',
        name: 'display'
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router