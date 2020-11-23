import axios from 'axios'
import Element from 'element-ui'

import router from './router'
import store from './store'

// axios全局默认地址，所有的请求都会加上这个地址
axios.defaults.baseURL = 'http://localhost:8080'

//拦截器 - 前置拦截
axios.interceptors.request.use(config => {
    return config
})

//拦截器 - 后置拦截
axios.interceptors.response.use(res => {
        let data = res.data
        if (data.code === 200) {
            return res
        }

        Element.Message.error(data.msg, {duration: 3 * 1000})

        return Promise.reject(data.msg)
    },
    error => {
        if (error.response.data){
            error.message = error.response.data.msg
        }

        if (error.response.data.code === 401) {
            store.commit('REMOVE_INFO')
            router.push('/login')
        }

        Element.Message.error(error.message, {duration: 3 * 1000})
        return Promise.reject(error.message)
    }
)