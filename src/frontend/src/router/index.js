import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/HelloWorld.vue'
import Aircon from '../components/aircon.vue'

Vue.use(VueRouter)

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
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router