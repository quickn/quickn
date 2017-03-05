package cn.json.quicknCore.bean;


public interface ErrorCode {
	
	public interface System{
		
		public static final String OK = "0";
		public static final String NOK = "1";
		
		public static final String INTERNAL_ERROR = "4001";
		public static final String REQEUST_ERROR = "4002";
		public static final String PARAMETER_ERROR = "4003";
		public static final String SERVICE_BUSY = "4004";
		public static final String MSGCODE_ERROR = "4005";
		
	}
	
	public interface Weixin{
		public static final String ACCESS_TOKEN_FAILED = "4100";
		public static final String JSSDK_CONFIG_URL_ERROR = "4101";
		public static final String ACCESS_TICKET_FAILED = "4102";
		public static final String JSSDK_CONFIG_FAILED = "4103";
		public static final String JSAPI_GET_TICKET_FAILED = "4104";
		public static final String USER_APPID_ERROR = "4105";

		public static final String USER_INFO_NULL = "4120";
		public static final String USER_ACCESS_TOKEN_FAILED = "4121";
		public static final String USER_GET_INFO_FAILED = "4122";
		
		
		public static final String REGIST_FAILED = "4123";
		
		

	}
	
	public interface User{
		
		public static final String EXISTED = "4200";
		public static final String USERNAME_ERROR = "4201";
		public static final String NOT_EXIST = "4202";
		public static final String REGIST_FAILED = "4223";
		
	}
}
