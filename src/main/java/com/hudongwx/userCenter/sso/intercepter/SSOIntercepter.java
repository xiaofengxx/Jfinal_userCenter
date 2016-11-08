package com.hudongwx.userCenter.sso.intercepter;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.Token;
import com.baomidou.kisso.web.interceptor.KissoAbstractInterceptor;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.log.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wuhongxu on 2016/11/7 0007.
 */
public class SSOIntercepter extends KissoAbstractInterceptor implements Interceptor {
    private Log logger = Log.getLog(getClass());

    @Override
    public void intercept(Invocation inv) {
        /**
         * 正常执行
         */

        HttpServletRequest request = inv.getController().getRequest();
        HttpServletResponse response = inv.getController().getResponse();
        Token token = SSOHelper.getToken(request);
        if (token == null) {
            try {
                logger.info("logout. request url:" + request.getRequestURL());
                SSOHelper.clearRedirectLogin(request, response);
                inv.getController().renderNull();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            /*
			 * 正常请求，request 设置 token 减少二次解密
			 */
            request.setAttribute(SSOConfig.SSO_TOKEN_ATTR, token);
            inv.invoke();
        }
    }
}
