package cn.json;


import cn.json.quicknCore.common.AbstractApplication;
import cn.json.quicknCore.common.Application;
import cn.json.quicknCore.common.Setting;
import cn.json.quicknCore.quickn.utils.Dom4jUtils;
import junit.framework.TestCase;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class QuickjBaseTestCase extends TestCase {
	protected static Application app;

	public <T extends Object> T getInstance(Class<T> clazz) {
		return AbstractApplication.injector.getInstance(clazz);
	}

	public QuickjBaseTestCase() {
		if (app == null) {
			try {
				Setting.runMode = Setting.TEST_MODE;
				Setting.load("src/main/webapp/");
				SAXReader reader = new SAXReader(false);
				reader
						.setFeature(
								"http://apache.org/xml/features/nonvalidating/load-external-dtd",
								false);
				Document doc = reader.read(new File(
						"src/main/webapp/WEB-INF/web.xml"));
				Element e = Dom4jUtils
						.getElement(
								doc.getRootElement(),
								"/web-app/filter[filter-name='quickj']/init-param[param-name='application']/param-value");
				String clazz = "cn.json.quicknCore.WebApplication";
				if (e != null)
					clazz = e.getTextTrim();
				app = (Application) Class.forName(clazz).newInstance();
				app.init("src/main/webapp/");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
