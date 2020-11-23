import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: localStorage.getItem('token'),
        userInfo: JSON.parse(sessionStorage.getItem('userInfo'))
    },
    mutations: {
        //set
        SET_TOKEN: (state, token) => {
            state.token = token
            localStorage.setItem('token', token)
        },
        SET_USER_INFO: (state, userinfo) => {
            state.userInfo = userinfo
            sessionStorage.setItem('userInfo', JSON.stringify(userinfo))
        },
        REMOVE_INFO: (state, userinfo) => {
            state.token = ''
            state.userInfo = {}
            localStorage.setItem('token', '')
            sessionStorage.setItem('userInfo', JSON.stringify(''))
        }
    },
    getters: {
        //get
        getUser: state => {
            return state.userInfo
        },
        getToken: state => {
            return state.token
        }
    },
    actions: {},
    modules: {}
})
