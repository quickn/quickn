package cn.json.quicknCore.netty;

import cn.json.quicknCore.bean.ErrorCode;
import cn.json.quicknCore.bean.ParameterInfo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;




/*
 * @copyright (c) xhigher 2015 
 * @author xhigher    2015-3-26 
 */
public class XLogic {

	public static final String DATE_TYPE_ARRAY = "array";
	public static final String DATE_TYPE_JSON = "json";
	
	public static final String REQUEST_KEY_CALLBACK = "callback";
	public static final String RESULT_KEY_ERRCODE = "errcode";
	public static final String RESULT_KEY_ERRINFO = "errinfo";
	public static final String RESULT_KEY_DATATYPE = "datatype";
	public static final String RESULT_KEY_DATA = "data";
	
	private String jsonpCallback = null;
	
	public String jsonResult(String code,String info, JSONObject obj){
		if(info == null){
			info = "";
		}
		if(obj == null){
			obj = new JSONObject();
		}
		JSONObject json = new JSONObject();
		json.put(RESULT_KEY_ERRCODE, code);
		json.put(RESULT_KEY_ERRINFO, info);
		json.put(RESULT_KEY_DATATYPE, DATE_TYPE_JSON);
		json.put(RESULT_KEY_DATA, obj);
		return json.toJSONString();
	}
	
	public String jsonResult(String code, String info, JSONArray arr){
		if(info == null){
			info = "";
		}
		if(arr == null){
			arr = new JSONArray();
		}
		JSONObject json = new JSONObject();
		json.put(RESULT_KEY_ERRCODE, code);
		json.put(RESULT_KEY_ERRINFO, info);
		json.put(RESULT_KEY_DATATYPE, DATE_TYPE_ARRAY);
		json.put(RESULT_KEY_DATA, arr);
		return json.toJSONString();
	}
	
	public String successResult(JSONObject data){
		String result = jsonResult(ErrorCode.System.OK, null, data);
		if(jsonpCallback != null){
			return jsonpCallback+"("+result+");";
		}
		return result;
	}
	public String successResult(JSONArray data){
		String result = jsonResult(ErrorCode.System.OK, null, data);
		if(jsonpCallback != null){
			return jsonpCallback+"("+result+");";
		}
		return result;
	}
	
	public String errorResult(String code, String info){
		String result = jsonResult(code, info, new JSONObject());
		if(jsonpCallback != null){
			return jsonpCallback+"("+result+");";
		}
		return result;
	}
	
	public String errorResult(String info){
		String result = jsonResult(ErrorCode.System.NOK, info, new JSONObject());
		if(jsonpCallback != null){
			return jsonpCallback+"("+result+");";
		}
		return result;
	}
	
	public String errorReqeustResult(){
		return errorResult(ErrorCode.System.REQEUST_ERROR, "ERROR_REQUEST");
	}
	
	public String errorParamterResult(String info){
		return errorResult(ErrorCode.System.PARAMETER_ERROR, info);
	}
	
	public String errorInternalResult(){
		return errorResult(ErrorCode.System.INTERNAL_ERROR, "INTERNAL_ERROR");
	}
	
	protected ParameterInfo initParameters(Map<String,List<String>> params){
		ParameterInfo paramInfo = new ParameterInfo(params, null);
		jsonpCallback = paramInfo.getCallbackName();
		return paramInfo;
	}
}
