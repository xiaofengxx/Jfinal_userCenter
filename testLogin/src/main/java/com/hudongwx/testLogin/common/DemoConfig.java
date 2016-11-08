package com.hudongwx.testLogin.common;

import com.hudongwx.testLogin.controller.IndexController;
import com.hudongwx.testLogin.sso.plugin.KissoJFinalPlugin;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.render.ViewType;

/**
 * Created by wuhongxu on 2016/11/7 0007.
 */
public class DemoConfig extends JFinalConfig {
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
        me.add("/", IndexController.class, "/common");
    }

    /**
     * Config plugin
     *
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {
        me.add(new KissoJFinalPlugin());
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
        JFinal.start("testLogin/src/main/webapp", 8092, "/", 5);
    }
}
