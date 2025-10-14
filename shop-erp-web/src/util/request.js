import axios from "axios";
import { message } from "ant-design-vue";
import router from "@/router/index.js";

const instance = axios.create({
    baseURL: import.meta.env.VITE_BASE_API || '/shop-erp',
    timeout: 10000,
    withCredentials: true
});

instance.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers['Authorization'] = token;
    }
    return config;
});

instance.interceptors.response.use((response) => {
    // 检查响应数据结构
    if (response.data.hasOwnProperty('success')) {
        // AI助手的响应格式
        return response.data;
    } else if (response.data.code !== 200) {
        // 原有系统的响应格式
        message.error(response.data.msg, 1);
    }
    return response.data;
}, (error) => {
    // 网络错误或服务器错误处理
    if (error.response) {
        // 服务器返回了错误状态码
        const data = error.response.data;
        if (data.msg) {
            message.error(data.msg, 1).then(() => {
                if (data.msg === '未登录') {
                    router.push('/login');
                }
            });
        } else {
            message.error('系统繁忙！', 1);
        }
        return Promise.reject(data);
    } else if (error.request) {
        // 请求已发出但没有收到响应
        message.error('网络连接失败，请检查网络设置', 1);
        return Promise.reject({ msg: '网络连接失败' });
    } else {
        // 其他错误
        message.error('请求配置错误', 1);
        return Promise.reject({ msg: '请求配置错误' });
    }
});

export default instance;