/**
 * Copyright (c) 2011-2014, hubin (243194995@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.hudongwx.userCenter.controller;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.common.IpHelper;
import com.baomidou.kisso.common.util.HttpUtil;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.hudongwx.userCenter.sso.Res;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 登录
 */
public class LoginController extends Controller {

    public void index() {
        String returnURL = null;
        try {
            returnURL = URLDecoder.decode(getPara("ReturnURL"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(returnURL);
        setAttr("url", returnURL);
        render("login.ftl");
    }

    @Before(POST.class)
    public void login() {
        /**
         * 登录 生产环境需要过滤sql注入
         */
        String url = getPara("url");
        WafRequestWrapper req = new WafRequestWrapper(getRequest());
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if ("kisso".equals(username) && "123".equals(password)) {
            /**
             * 系统定义 SSOToken st = new SSOToken();
             * <p>
             * 自定义 MyToken
             * </p>
             */
            SSOToken mt = new SSOToken();
            mt.setId(1000L);
            mt.setUid("1000");
            mt.setIp(IpHelper.getIpAddr(getRequest()));

            //记住密码，设置 cookie 时长 1 周 = 604800 秒 【动态设置 maxAge 实现记住密码功能】
            String rememberMe = req.getParameter("rememberMe");
            if ( "on".equals(rememberMe) ) {
            	setAttr(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
            }

            SSOHelper.setSSOCookie(getRequest(), getResponse(), mt, true);
            if (url == null)
                renderNull();
            else
                redirect(url);
        }
    }


    /**
     * <p>
     * 支持APP端登录
     * <br>
     * 调用时需要为请求Header设置PLATFORM=APP
     * 否则请求将不会被kisso处理，而直接视为jFinal的controller
     * </p>
     *
     * @author 成都瘦人  lendo.du@gmail.com
     */
    public void auth() {
        Res res = new Res();
        SSOToken token = SSOHelper.getToken(getRequest());
        if (token != null) {
            renderJson(res);
        } else {
            if (HttpUtil.isPost(getRequest())) {
                WafRequestWrapper req = new WafRequestWrapper(getRequest());
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                if ("admin".equals(username) && "admin".equals(password)) {
                    token = new SSOToken();
                    token.setUid("1000");
                    token.setIp(IpHelper.getIpAddr(getRequest()));
                    SSOHelper.setSSOCookie(getRequest(), getResponse(), token, true);
                    res.setData("已下发Cookies至响应");
                    renderJson(res);
                } else {
                    renderError(401);
                }
            } else {
                renderError(401);
            }
        }
    }
}
