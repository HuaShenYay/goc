import request from '@/util/request.js'

/**
 * AI助手问答接口
 * @param query 用户的查询
 * @returns {*}
 */
export function aiChat(query) {
    return request({
        url: '/ai/chat',
        method: 'POST',
        data: { query }
    })
}

/**
 * AI助手健康检查
 * @returns {*}
 */
export function aiHealthCheck() {
    return request({
        url: '/ai/health',
        method: 'GET'
    })
}