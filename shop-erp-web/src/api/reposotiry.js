import request from '@/util/request.js'

export const getStockOptions = () => {
    return request({
        url: 'repository/stocks',
        method: 'GET'
    })
}