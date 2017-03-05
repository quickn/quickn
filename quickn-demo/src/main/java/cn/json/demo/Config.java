package cn.json.demo;

import org.apache.commons.configuration.XMLConfiguration;

public class Config implements cn.json.quicknCore.common.ApplicationConfig {
	// 在appconfig.xml文件中定义的配置可以反应在这里，建议设为public，以方便访问。
	public String itemImgPath;// 商品图片的存放路径
	// 电信itv授权地址
	public String spUrl;

	public String spServiceUrl;
	public String spServiceId;

	public String sporderip;

	public String spBaoyue;
	public String spBaoyue10;
	public String spDianbo;

	public String msgSPID;

	public String spKey;

	public String wsAddress;
	public String productID;
	public String serviceID;
	public String senderName;
	public String itvUrl;
	// 访问日志路径
	public String itvLog;
	// 访问备份路径
	public String itvLogC;

	/**
	 * 正式环境的ip地址
	 */
	public String itvIp;

	public String webRoot;

	public String model;

	public String ip;

	public Integer version;

	public String gatherIp;

	public String province;

	public void init(XMLConfiguration config) {

		model = config.getString("model");
		ip = config.getString("ip");
		province = config.getString("province");

		itemImgPath = config.getString("path.itemImg");
		webRoot = config.getString("path.webRoot");

		spServiceUrl = config.getString("sp.ip");
		spServiceId = config.getString("sp.id");
		spUrl = config.getString("sp.url");

		spBaoyue = config.getString("sp.baoyue");
		spBaoyue10 = config.getString("sp.baoyue10");
		spDianbo = config.getString("sp.dianbo");
		sporderip = config.getString("sp.orderip");
		spKey = config.getString("sp.key");

		msgSPID = config.getString("msg.spid");
		wsAddress = config.getString("msg.wsAddress");
		productID = config.getString("msg.productID");
		serviceID = config.getString("msg.serviceID");
		senderName = config.getString("msg.senderName");
		itvUrl = config.getString("itv.url");
		itvLog = config.getString("itv.log");
		itvLogC = config.getString("itv.logC");
		itvIp = config.getString("itv.ip");
		version = config.getInt("itv.version");
		gatherIp = config.getString("itv.gatherip");
	}

}
