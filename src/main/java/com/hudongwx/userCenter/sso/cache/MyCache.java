package com.hudongwx.userCenter.sso.cache;

import com.baomidou.kisso.SSOCache;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.Token;
import com.jfinal.log.Log;

/**
 * Created by wuhongxu on 2016/11/8 0008.
 */
public class MyCache implements SSOCache {
    private Log log = Log.getLog(getClass());

    /**
     * <p>
     * 根据key获取SSO票据
     * </p>
     * <p>
     * 如果缓存服务宕机，返回 token 设置 flag 为 Token.FLAG_CACHE_SHUT
     * </p>
     *
     * @param key     关键词
     * @param expires 过期时间（延时心跳时间）
     * @return Token SSO票据
     */
    @Override
    public Token get(String key, int expires) {
        log.info("取出心跳" + expires);
        return null;
    }

    /**
     * 设置SSO票据
     *
     * @param key     关键词
     * @param token   SSO票据
     * @param expires 过期时间（延时心跳时间）
     */
    @Override
    public boolean set(String key, Token token, int expires) {
        log.info("存入心跳" + expires);
        return false;
    }

    /**
     * 删除SSO票据
     * <p>
     *
     * @param key 关键词
     */
    @Override
    public boolean delete(String key) {
        return false;
    }
}
