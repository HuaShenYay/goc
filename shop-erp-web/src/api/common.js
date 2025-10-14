import request from '@/util/request.js'

export class BaseApi {

    path

    constructor(path) {
        this.path = path
    }

    /**
     * 分页查询
     * @param params
     * @returns {*}
     */
    findPage(params) {
        return request({
            url: `${this.path}/page`,
            method: 'GET',
            params
        })
    }

    /**
     * 列表查询
     * @param params
     * @returns {*}
     */
    findList(params) {
        return request({
            url: this.path,
            method: 'GET',
            params
        })
    }


    /**
     * 查询选项
     * @param url
     * @param url
     * @returns {*}
     */
    findOptions(url) {
        return request({
            url: url,
            method: 'GET'
        })
    }

    /**
     * 新增
     * @param data
     * @returns {*}
     */
    save(data) {
        return request({
            url: this.path,
            method: 'POST',
            data
        })
    }

    /**
     * 新增
     * @param path
     * @param data
     * @returns {*}
     */
    saveWithPath(path, data) {
        return request({
            url: path,
            method: 'POST',
            data
        })
    }

    /**
     * 修改
     * @param data
     * @returns {*}
     */
    modify(data) {
        return request({
            url: this.path,
            method: 'PUT',
            data
        })
    }

    /**
     * 根据ID查询
     * @param id
     * @returns {*}
     */
    findById(id) {
        return request({
            url: `${this.path}/${id}`,
            method: 'GET',
        })
    }

    /**
     * 删除
     * @param id
     * @returns {*}
     */
    removeById(id) {
        return request({
            url: this.path,
            method: 'DELETE',
            data: [id]
        })
    }
}