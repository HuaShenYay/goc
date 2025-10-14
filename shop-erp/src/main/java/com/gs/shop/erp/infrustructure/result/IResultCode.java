package com.gs.shop.erp.infrustructure.result;


/**
 * 响应状态码接口
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023-11-15
 */
public interface IResultCode {
    /**
     * 获取编码
     *
     * @return java.lang.String
     * @author Pursuer
     * @date 2023/11/15
     * @since 1.0
     **/
    int getCode();

    /**
     * 获取提示信息
     *
     * @return java.lang.String
     * @author Pursuer
     * @date 2023/11/15
     * @since 1.0
     **/
    String getMsg();

}
