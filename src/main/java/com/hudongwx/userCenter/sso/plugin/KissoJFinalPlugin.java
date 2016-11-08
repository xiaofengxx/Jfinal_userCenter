package com.hudongwx.userCenter.sso.plugin;
import com.baomidou.kisso.web.WebKissoConfigurer;
import com.jfinal.plugin.IPlugin;
import java.util.logging.Logger;

public class KissoJFinalPlugin implements IPlugin {

	protected static final Logger logger = Logger.getLogger("WebKissoConfigurer");


	/**
	 * 初始化
	 */
	public boolean start() {
		WebKissoConfigurer ssoConfig = new WebKissoConfigurer("sso.properties");
		// 此处可以实现自己的 KISSO 插件，也可动态注入 SSO 配置属性。
		// ssoConfig.setPluginList(pluginList);
		
		//运行模式设置，可选择指定模式的配置
		ssoConfig.setRunMode("test_mode");
		ssoConfig.initKisso();
		return true;
	}


	/**
	 * 销毁
	 */
	public boolean stop() {
		logger.info("Uninstalling Kisso ");
		return true;
	}

}
