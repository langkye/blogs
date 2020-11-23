import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "../views/Login";
import Blogs from "../views/Blogs";
import BlogDetail from "../views/BlogDetail";
import BlogEdit from "../views/BlogEdit";


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Blogs',
        component: Blogs
    },
    {
        path: '/index',
        name: "Blogs",
        redirect: {
            name: 'Blogs'
        }
    },
    {
        path: '/blogs',
        name: 'Blogs',
        component: Blogs
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/blog/add',
        name: 'BlogEdit',
        component: BlogEdit
    },
    {
        path: '/blog/:id',
        name: 'BlogDetail',
        component: BlogDetail
    },
    {
        path: '/blog/:id/edit',
        name: 'BlogEdit',
        component: BlogEdit
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
