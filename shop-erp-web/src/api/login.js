import request from '@/util/request.js'

export const login = (username, password) => {
    return request({
        url: 'login',
        method: 'POST',
        data: {username, password}
    })
}