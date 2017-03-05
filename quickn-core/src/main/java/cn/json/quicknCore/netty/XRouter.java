package cn.json.quicknCore.netty;

import cn.json.quicknCore.conf.APIConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/*
 * @copyright (c) xhigher 2015 
 * @author xhigher    2015-3-26 
 */
public class XRouter {
	
	private static Logger logger = LoggerFactory.getLogger("syslog");
	
	private static final String pathRegEx = "^\\/([a-zA-Z]\\w+)\\/([a-zA-Z]\\w+)\\/?$";
	private static Map<String, String> routeModuleList = new HashMap<String, String>();
	private static Map<String, Map<String,String>> routeActionList = new HashMap<String, Map<String,String>>();
	
	private static Map<String, Map<String,String[]>> routeWhiteList = new HashMap<String, Map<String,String[]>>();
	
	public static void load(){
		
		routeModuleList.put("user", APIConfig.User.MODULE_NAME);
		routeActionList.put("user", APIConfig.User.getActionList());
		routeWhiteList.put("user", APIConfig.User.getWhiteActionList());
		
		routeModuleList.put("auth", APIConfig.Auth.MODULE_NAME);
		routeActionList.put("auth", APIConfig.Auth.getActionList());
		routeWhiteList.put("auth", APIConfig.Auth.getWhiteActionList());
		
		routeModuleList.put("notice", APIConfig.Notice.MODULE_NAME);
		routeActionList.put("notice", APIConfig.Notice.getActionList());
		routeWhiteList.put("notice", APIConfig.Notice.getWhiteActionList());
		
	}
	
	public static Object doLogic(String clientIP, String path, Map<String, List<String>> params){
		XLogic logic = new XLogic();
		
		Pattern pattern = Pattern.compile(pathRegEx);  
		Matcher matcher = pattern.matcher(path);
		if(!matcher.matches()){
			return logic.errorReqeustResult();
		}
		
		String reqModule = matcher.group(1);
		String reqAction = matcher.group(2);

		Map<String, String[]> whiteList = routeWhiteList.get(reqModule);
		if(whiteList != null){
			boolean isForbidden = false;
			String[] ipList = whiteList.get(reqAction);
			if(ipList!=null && ipList.length>0){
				isForbidden = true;
				for(int i=0,n=ipList.length; i<n; i++){
					if(ipList[i].equals(clientIP)){
						isForbidden = false;
						break;
					}
				}
			}
			if(isForbidden){
				System.out.println("IP FORBIDDEN! client IP="+clientIP);
				return logic.errorReqeustResult();
			}
		}
		
		String module  = routeModuleList.get(reqModule);
		if(module == null){
			return logic.errorReqeustResult();
		}
		Map<String,String> actionList = routeActionList.get(reqModule);
		if(actionList == null){
			return logic.errorReqeustResult();
		}
		String action = actionList.get(reqAction);
		if(action == null){
			return logic.errorReqeustResult();
		}
		Class<?> clazz = null;
		Constructor<?> constructor = null;
		Object logicObject = null;
		Object logicResult = null;
		try {
			clazz = Class.forName(module+"."+action);
			constructor = clazz.getDeclaredConstructor(new Class[]{Map.class});
			logicObject = constructor.newInstance(new Object[]{params});
			logicResult = logicObject.toString();
		} catch (Exception e) {
			logger.error("XRouter.doLogic:"+module+"."+action, e);
			return logic.errorReqeustResult();
		}finally{
			clazz = null;
			constructor = null;
			logicObject = null;
		}
		return logicResult;
	}


}
