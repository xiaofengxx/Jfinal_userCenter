package com.hudongwx.userCenter.common;

import com.hudongwx.userCenter.controller.LoginController;
import com.hudongwx.userCenter.controller.LogoutController;
import com.hudongwx.userCenter.controller.VerifyCodeController;
import com.hudongwx.userCenter.sso.plugin.KissoJfinalPlugin;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.render.ViewType;

/**
 * Created by wuhongxu on 2016/11/7 0007.
 */
public class MainConfig extends JFinalConfig {
    /**
     * Config constant
     *
     * @param me
     */
    @Override
    public void configConstant(Constants me) {
        me.setDevMode(PropKit.use("config.properties").getBoolean("devMode"));
        me.setViewType(ViewType.FREE_MARKER);
    }

    /**
     * Config route
     *
     * @param me
     */
    @Override
    public void configRoute(Routes me) {
        me.add("login", LoginController.class,"/common");
        me.add("logout", LogoutController.class);
        me.add("verify", VerifyCodeController.class);
    }

    /**
     * Config plugin
     *
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {
        me.add(new KissoJfinalPlugin());
    }

    /**
     * Config interceptor applied to all actions.
     *
     * @param me
     */
    @Override
    public void configInterceptor(Interceptors me) {

    }

    /**
     * Config handler
     *
     * @param me
     */
    @Override
    public void configHandler(Handlers me) {

    }

    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 8080, "/", 5);
    }
}
