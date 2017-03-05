package cn.json.demo;

import com.google.inject.Module;
import java.util.ArrayList;
import java.util.Properties;

;

//AuthCheckFilter:page=index.html,ignore=/auth;
@cn.json.quicknCore.annotation.Filter(name = "TimeFilter;StaticParamFilter;LoginCheckFilter:page=index,ignore=/index|/extui/user/listAll/login|/extui/verifycode|/extui/main/checkVerifyCode|/extui/main/login|/extui/main/logout|/autoUpdateFlow|/cms/index|/cms/sycn|/cms/flash/uploadImg|/wap")
public class Application extends cn.json.quicknCore.common.AbstractApplication {

	@Override
	public void onHibernateConfig(Properties properties) {

	}

	@Override
	public void onInitGuiceModules(ArrayList<Module> modules) {
	}

	@Override
	public void init(String rootPath) throws Exception {
		super.init(rootPath);
	}
}
