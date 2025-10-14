import request from '@/util/request.js'

export const findPurchaseSaleCount = (startDate, endDate) => {
    return request({
        url: 'purchase/sale/order/count',
        method: 'GET',
        params: {startDate, endDate}
    })
}