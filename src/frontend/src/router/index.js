import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home'
import Aircon from '../views/Aircon'
import Login from '../views/Login'
import Me from '../views/Profile'

Vue.use(VueRouter)

// const requireAuth = () => (from, to, next) => {
//     const isAuthentication = this.$store.state.isAuthenticated
//     if (isAuthentication) return next()
//     next('/login?returnPath=me')
// }

const routes = [
    {
        path: '/',
        name: 'home',
        component: Home
    },
    {
        path: '/aircon',
        name: 'aircon',
        component: Aircon
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
        // beforeEnter: requireAuth()
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router