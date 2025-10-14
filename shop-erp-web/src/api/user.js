import request from '@/util/request.js'

export const updatePassword = (data) => {
    return request({
        url: 'sys/user/updatePassword',
        method: 'PUT',
        data
    })
}