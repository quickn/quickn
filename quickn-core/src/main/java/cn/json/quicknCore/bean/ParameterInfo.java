package cn.json.quicknCore.bean;

import java.util.*;


public class ParameterInfo {

	public static final String CALLBACK_NAME_DEFAULT = "callback";
	protected Map<String, String> parameters  = null;
	private String jsonpCallback = null;
	private String errorInfo = null;
	
	public ParameterInfo(Map<String,List<String>> params){
		parameters  = new HashMap<String,String>();
		jsonpCallback = null;
		errorInfo = null;
		String pv = null;
		for(String pn : params.keySet()){
			pv = params.get(pn).get(0).trim();
			parameters.put(pn, pv);
		}
	}
	public ParameterInfo(Map<String,List<String>> params, String callbackName){
		parameters  = new HashMap<String,String>();
		if(callbackName == null){
			callbackName = CALLBACK_NAME_DEFAULT;
		}
		jsonpCallback = null;
		errorInfo = null;
		String pv = null;
		for(String pn : params.keySet()){
			pv = params.get(pn).get(0).trim();
			if(callbackName.equals(pn)){
				jsonpCallback = pv;
			}
			parameters.put(pn, pv);
		}
	}
	public boolean checkError(final String[] requires){
		if(requires != null){
			String pn = null;
			List<String> emptyValueParams = new ArrayList<String>();
			List<String> lackedParams = new ArrayList<String>();
			for(int i=0,n=requires.length; i<n; i++){
				pn = requires[i];
				if(!parameters.containsKey(pn)){
					lackedParams.add(pn);
				}else{
					if(parameters.get(pn).isEmpty()){
						emptyValueParams.add(pn);
					}
				}
			}
			if(lackedParams.size() > 0){
				errorInfo = "PARAMTER_LACKED:"+lackedParams.toString();
				return true;
			}
			if(emptyValueParams.size() > 0){
				errorInfo = "PARAMTER_VALUE_EMPTY:"+emptyValueParams.toString();
				return true;
			}
		}
		return false;
	}
	
	public String getErrorInfo(){
		return errorInfo;
	}
	
	public String getString(String name){
		return parameters.get(name);
	}
	
	public Integer getInteger(String name){
		try{
			return Integer.valueOf(parameters.get(name));
		}catch(Exception e){}
		return null;
	}
	
	public Double getDouble(String name){
		try{
			return Double.valueOf(parameters.get(name));
		}catch(Exception e){}
		return null;
	}
	
	public String getCallbackName(){
		return jsonpCallback;
	}



	public String toString(){
		Set<String> ss=parameters.keySet();
		Iterator<String> it=ss.iterator();
		StringBuffer sb=new StringBuffer();
		while(it.hasNext()){
			String key=it.next();
			sb.append(key+"="+parameters.get(key));
			sb.append("&");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}


}
