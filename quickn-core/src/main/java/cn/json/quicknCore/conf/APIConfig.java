package cn.json.quicknCore.conf;

import java.util.HashMap;
import java.util.Map;


public interface APIConfig {
	
	public static final String[] ACCESS_CONTROL_ALLOW_ORIGIN_LIST = {
		"http://m.ubody.net",
		"http://wx.ubody.net",
	};
	
	public static final String[] INTERNAL_IP_LIST = {"127.0.0.1"};
	
	public abstract class Auth{
		
		public static final String MODULE_NAME = "net.ubody.weixin.logic.auth";
		
		public static  Map<String,String> getActionList(){
			Map<String, String> actionList = new HashMap<String, String>();
			actionList.put("state_check", "StateCheck");
			actionList.put("cooperation", "Cooperation");
			actionList.put("get_token_and_ticket", "GetTokenAndTicket");
			actionList.put("get_jssdk_config", "GetJSSDKConfig");
			actionList.put("get_token", "GetToken");
			actionList.put("get_ticket", "GetTicket");
			return actionList;
		}
		
		public static  Map<String,String[]> getWhiteActionList(){
			Map<String, String[]> actionList = new HashMap<String, String[]>();
			actionList.put("get_token_and_ticket", INTERNAL_IP_LIST);
			actionList.put("get_token", INTERNAL_IP_LIST);
			actionList.put("get_ticket", INTERNAL_IP_LIST);
			return actionList;
		}
	}
	
	public abstract class User{
		
		public static final String MODULE_NAME = "net.ubody.weixin.logic.user";
		
		public static  Map<String,String> getActionList(){
			Map<String, String> actionList = new HashMap<String, String>();
			actionList.put("bind_account", "BindAccount");
			actionList.put("get_info_by_code", "GetInfoByCode");
			actionList.put("get_info_by_openid", "GetInfoByOpenid");
			return actionList;
		}
		
		public static  Map<String,String[]> getWhiteActionList(){
			Map<String, String[]> actionList = new HashMap<String, String[]>();
			return actionList;
		}
	}
	
	public abstract class Notice{
		
		public static final String MODULE_NAME = "net.ubody.weixin.logic.notice";
		
		public static  Map<String,String> getActionList(){
			Map<String, String> actionList = new HashMap<String, String>();
			actionList.put("menu", "Menu");
			actionList.put("qrcode", "Qrcode");
			actionList.put("health", "Health");
			actionList.put("health", "Health");
			actionList.put("qyweixincallback", "QyWeixinCallback");
			actionList.put("wechatcallback", "WechatCallback");
			return actionList;
		}
		
		public static  Map<String,String[]> getWhiteActionList(){
			Map<String, String[]> actionList = new HashMap<String, String[]>();
			actionList.put("menu", INTERNAL_IP_LIST);
			return actionList;
		}
	}
	
}
