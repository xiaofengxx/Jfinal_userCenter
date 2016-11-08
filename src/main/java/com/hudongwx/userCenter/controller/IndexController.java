package com.hudongwx.userCenter.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.hudongwx.userCenter.sso.intercepter.SSOIntercepter;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * Created by wuhongxu on 2016/11/7 0007.
 */
@Before(SSOIntercepter.class)
public class IndexController extends Controller {
    public void index(){
        SSOToken token = SSOHelper.getToken(getRequest());
        setAttr("word",token.getUid());
        render("index.ftl");
    }
}
