package com.hudongwx.userCenter.sso.statistic;

import com.baomidou.kisso.SSOStatistic;
import com.jfinal.log.Log;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuhongxu on 2016/11/8 0008.
 */
public class MyStatistic implements SSOStatistic {
    private static int count = 0;
    private Log log = Log.getLog(getClass());
    /**
     * 在线人数 +1
     *
     * @param request 登录请求
     * @return boolean
     */
    @Override
    public boolean increase(HttpServletRequest request) {
        count++;
        log.info("人数加一："+count);
        return true;
    }

    /**
     * 在线人数 -1
     *
     * @param request 退出请求
     * @return boolean
     */
    @Override
    public boolean decrease(HttpServletRequest request) {
        count--;
        log.info("人数减一："+count);
        return true;
    }

    /**
     * 在线人数（总数）
     *
     * @param request 查询请求
     * @return String
     */
    @Override
    public String count(HttpServletRequest request) {
        log.info("人数查询："+count);
        return count+"";
    }
}
