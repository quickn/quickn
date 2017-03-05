package cn.json.demo.utils;

public class Constant {

	public static final String order_status = "test";

	// cookie名称
	public static final String cookie_itv_back_url = "ITV_BACK_URL";
	public static final String cookie_itv_buyer_id = "ITV_BUYER_ID";
	public static final String cookie_USER_ID = "USER_ID";
	public static final String cookie_itv_activity = "ITV_ACTIVITY";
	public static final String cookie_itv_returnUrl = "RETURN_URL";
	public static final String cookie_itv_INFO = "ITV_INFO";// info信息
	public static final String cookie_itv_INIT = "INIT";// info信息

	// 页面发布历史状态 1:待测试,2:待审核,3:已发布,4:已取消
	public static final Integer PUBLISH_STATUS_TEST = 1;// 待测试
	public static final Integer PUBLISH_STATUS_AUDIT = 2;// 待审核
	public static final Integer PUBLISH_STATUS_PUBLISH = 3;// 待发布
	public static final Integer PUBLISH_STATUS_FINISH = 4;// 已经发布
	public static final Integer PUBLISH_STATUS_CANCEL = 5;// 已取消
	// 页面状态
	public static final Integer PAGE_STATUS_ACTIVE = 1;// 有效
	public static final Integer PAGE_STATUS_UN_ACTIVE = 2;// 无效
	public static final Integer PAGE_STATUS_DELETE = -1;// 删除

	/**
	 * 图片状态:有效
	 */
	public static final Integer IMG_STATUS_ACTIVE = 1;// 有效
	/**
	 * 图片状态: 无效
	 */
	public static final Integer IMG_STATUS_UN_ACTIVE = 0;// 无效

	// 数据状态类型
	public static final Integer DATA_STATUS_VALID = 1;// 有效的数据(用于页面显示)
	public static final Integer DATA_STATUS_INVALID = 2;// 无效的的数据(页面不用显示)

	// 海报状态
	public static final Integer placard_status_audit = 0;// 审核状态
	public static final Integer placard_status_audited = 1;// 已审核状态
	public static final Integer placard_status_xiajia = 2;// 下架状态

	// 组件状态
	public static final Integer PAGECOMPONENT_STATUS_USED = 1;// 启用
	public static final Integer PAGECOMPONENT_STATUS_NOT_USED = 2;// 未启用

	// cms用户操作提醒
	public static final String OPERATE_MESSAGE_SUCCESS_DELETE = "删除成功";
	public static final String OPERATE_MESSAGE_PERMISSION = "无操作权限";

	// 设置一个有删除操作权限的用户
	public static final String OPERATE_USER_PERMISSION = "admin";

	// 页面生成存放路径
	public static final String PAGE_STORE_PATH = "/templates/pages/";

	public static final CharSequence TREE_CHILDRENS = "childrens_";
	// 组件数据发布状态
	public static final Integer PAGECOMPONENT_DATA_IS_PUBLISH = 1;
	public static final Integer PAGECOMPONENT_DATA_NOT_PUBLISH = 0;

	/**
	 * 每个分类Id
	 */
	public static final Integer cateId_index_cry = 3;
	public static final Integer cateId_index_right = 2;
	/**
	 * 优惠劵父类
	 */
	public static final Integer dict_parent_yhjzq = 4;// 优惠劵专区父类ID
	public static final Integer dict_parent_gwzn = 24;// 购物指南父类ID

	// 菜单值
	public static final String uri_index = "vitv2";// 首页
	public static final String uri_vip = "vip";// 会员专区
	public static final String uri_grzx = "grzx";// 个人中心
	public static final String uri_help = "help";// 帮助
	public static final String uri_tao = "tao";// 淘宝优惠大全
	public static final String uri_hot = "hot";// 餐饮热券
	public static final String uri_kind = "kind";// 分类
	public static final String uri_activity = "activity";// 活动页面
	public static final String uri_detail = "detail";// 详情页
	public static final String uri_order = "order";// 包月页面
	public static final String uri_dianbo = "dianboSure";// 点播确认
	public static final String uri_baoyue = "baoyueSure";// 包月确认

	// session值
	public static final String session_itvIp = "itvIp";// 图片引用地址
	public static final String session_shop = "shop";
	public static final String session_buyer = "buyer";
	public static final String session_city = "city";
	public static final String session_award = "award";
	public static final String session_user = "USER_IN_SESSION_KEY";
	public static final String session_province = "province";
	public static final String session_EPG = "EPG";
	public static final String session_GATHER_Url = "GATHER_URL";
	public static final String session_COUPON_NANE = "COUPON_NANE";

	/**
	 * 无效
	 */
	public static final Integer COUPON_STATUS_wuxiao = 0;
	/**
	 * 审核中(优惠券)
	 */
	public static final Integer COUPON_STATUS_AUDIT = 1;
	/**
	 * 活动中(优惠券)
	 */
	public static final Integer COUPON_STATUS_ACTIVITY = 2;
	/**
	 * 审核失败(优惠券)
	 */
	public static final Integer COUPON_STATUS_AUDIT_FAILED = 3;
	/**
	 * 下架(优惠券)
	 */
	public static final Integer COUPON_STATUS_ACTIVITY_ENDED = 4;
	/**
	 * 删除(优惠券)
	 */
	public static final Integer COUPON_STATUS_DELETE = 5;

	/**
	 * 专区优惠券
	 */
	public static final Integer COUPON_STATUS_special = 6;

	/**
	 * 优惠劵类型 商品劵
	 */
	public static final Integer coupon_type_item = 1;
	/**
	 * 优惠劵类型 店铺劵
	 */
	public static final Integer coupon_type_shop = 2;
	/**
	 * 优惠劵类型 淘宝劵
	 */
	public static final Integer coupon_type_tao = 3;
	/**
	 * 优惠劵类型 丁丁优惠券
	 */
	public static final Integer coupon_type_dd = 4;

	/**
	 * 优惠劵类型 熊猫优惠券
	 */
	public static final Integer coupon_type_xm = 5;

	/**
	 * 优惠劵类型 熊猫优惠券
	 */
	public static final Integer coupon_type_jd = 6;

	/**
	 * 优惠劵类型 熊猫优惠券
	 */
	public static final Integer coupon_type_kfc = 7;

	/**
	 * 商家券
	 */
	public static final Integer coupon_type_seller = 8;

	/**
	 * flash标签类型
	 */
	public static final Integer FLASH_TAG_TYPE_MY_TAG_MODEL = 0;// 我的标签模板
	public static final Integer FLASH_TAG_TYPE_SYS_TAG_MODEL = 1;// 系统标签模板
	public static final Integer FLASH_TAG_TYPE_SLT_TAG = 2;// 矢量图
	public static final Integer FLASH_TAG_TYPE_WT_TAG = 3;// 位图
	public static final Integer FLASH_TAG_TYPE_MY_UPLOAD_TAG = 4;// 我的上传标签
	public static final Integer FLASH_TAG_TYPE_PRICE_TAG = 5;// 价格标签
	public static final Integer TAG_IMG_UPLOAD_LEVEL = 9;
	public static final Integer TAG_IMG_UPLOAD_BY_LOCAL_LEVEL = 10;
	/**
	 * 店铺优惠码状态
	 */
	public static final Integer shop_code_status_wait = 0;// 等待领取
	public static final Integer shop_code_status_ling = 1;// 已经领取
	public static final Integer shop_code_status_used = 2;// 已经使用

	/**
	 * 店铺图片类型：店铺详情图
	 */
	public static final Integer SHOP_IMG_TYPE_DETAIL = 1;
	/**
	 * 店铺图片类型：logo图
	 */
	public static final Integer SHOP_IMG_TYPE_LOGO = 2;

	/**
	 * 店铺图片类型：优惠券图片
	 */
	public static final Integer SHOP_IMG_TYPE_COUPON = 3;
	/**
	 * 店铺图片类型：商品图片
	 */
	public static final Integer SHOP_IMG_TYPE_ITEM = 4;
	/**
	 * 店铺图片类型：海报图片
	 */
	public static final Integer SHOP_IMG_TYPE_PLACARD = 5;

	/**
	 * 商品状态：未审核
	 */
	public static final Integer ITEM_STATUS_AUDITING = 1;
	/**
	 * 商品状态：审核通过
	 */
	public static final Integer ITEM_STATUS_AUDIT = 2;
	/**
	 * 商品状态：审核失败
	 */
	public static final Integer ITEM_STATUS_AUDIT_FAILED = 3;
	/**
	 * 商品状态：删除
	 */
	public static final Integer ITEM_STATUS_DELETE = 4;

	/**
	 * 查询状态:查询全部
	 */
	public static final Integer SEARCH_STATUS_ALL = -1;

	/**
	 * 文本：显示
	 */
	public static final Integer PROMPT_TEXT_STATUS_SHOW = 1;
	/**
	 * 文本：不显示
	 */
	public static final Integer PROMPT_TEXT_STATUS_UN_SHOW = 2;
	/**
	 * 提示类型：收视提示
	 */
	public static final Integer PROMPT_TEXT_TYPE_VIEW = 1;
	/**
	 * 提示类型：购买提示
	 */
	public static final Integer PROMPT_TEXT_TYPE_BUY = 2;

	/**
	 * logo
	 */
	public static final Integer PICTURE_STATUS_ONE = 1;
	/**
	 * 舌尖上的中国gif
	 */
	public static final Integer PICTURE_STATUS_TWO = 2;
	/**
	 * 商品信息码状态:正在使用
	 */
	public static final Integer ITEM_MESSAGE_CODE_STATUS_USING = 1;
	/**
	 * 商品信息码状态:不可用
	 */
	public static final Integer ITEM_MESSAGE_CODE_STATUS_UN_USABLE = 2;
	/**
	 * 商品信息码状态:可用
	 */
	public static final Integer ITEM_MESSAGE_CODE_STATUS_USABLE = 3;

	/**
	 * 词典订购页面包月
	 */
	public static final String dict_order_by = "dgym_by";

	/**
	 * 买家类型：机顶盒用户
	 */
	public static final Integer BUYER_TYPE_BOX = 1;
	/**
	 * 买家类型：手机用户
	 */
	public static final Integer BUYER_TYPE_MOBILE = 2;
	/**
	 * 买家类型：其它
	 */
	public static final Integer BUYER_TYPE_OTHER = 3;

	/**
	 * 淘宝参数
	 */
	public static final String appKey = "21366161";
	public static final String appSecret = "26d5d76885baa3a5ddf821326d127d9e";
	public static final String url = "http://gw.api.taobao.com/router/rest";

	/**
	 * 手机短信状态
	 */
	public static final int message_status_un_send = 0;// 未发送
	public static final int message_status_sended = 1;// 已发送

	/**
	 * 路径
	 */
	public static final String path_coupon_map_img = "path/couponImg/";// 优惠券关联图片路径
	public static final String path_data_img = "path/dataImg/";// 页面图片路径
	public static final String path_coupon = "path/coupon/";// 优惠券图片路径
	public static final String path_activity = "path/activity/";// 优惠券图片路径
	public static final String path_dd_coupon = "path/dd_coupon/";// 优惠券图片路径
	public static final String path_dd_img = "path/dd_img/";// 优惠券图片路径
	public static final String path_dd_img_y = "path/dd_img_y/";// 优惠券图片路径
	public static final String path_xm_img = "path/xm_img/";// 优惠券图片路径
	public static final String path_dzdp = "path/dianp/";// 大众点评网图片
	public static final String path_nuom = "path/noum/";// 糯米网图片
	public static final String path_wwt = "path/wwt/";// 窝窝团图片
	public static final String path_coupon_kfc = "path/kfc/";// 肯德基图片

	/**
	 * 活动类型
	 */
	public static final Integer activity_type_activity = 1;// 活动
	public static final Integer activity_type_subject = 2;// 专题
	public static final Integer activity_type_special = 3;// 包月独享

	/**
	 * 订购方式
	 */
	public static final String order_type_dianbo = "dianbo";
	public static final String order_type_baoyue = "baoyue";

	/**
	 * 丁丁网
	 */
	public static final String dd_appkey = "jiangsuitv20130502itv";
	public static final String dd_coupon_url = "http://coupon.api.ddmap.com/";

	public static final Integer dd_coupon_type_nomal = 1;// 普通券
	public static final Integer dd_coupon_type_ping = 2;// 品牌
	public static final Integer dd_coupon_type_hot = 3;// 热门

	/**
	 * 优惠券来源
	 */

	public static final String coupon_sourse_tao = "tao";// 来自淘宝
	public static final String coupon_sourse_dd = "dd";// 来自当当网
	public static final String coupon_sourse_xm = "xm";// 来自当当网
	public static final String coupon_sourse_kfc = "kfc";// 肯德基

	/**
	 * 短信发送
	 */
	public static final Integer msg_status_fail = 3;
	public static final Integer msg_status_success = 0;

	/**
	 * 买家的状态
	 */
	public static final Integer buyer_status_nomal = 1;// 正常的状态
	public static final Integer buyer_status_test = 2;// 测试的状态,
	public static final Integer buyer_status_blacklist = 3;// 加入黑名单的不进行自动订购
	public static final Integer buyer_status_vip = 4;// 虽然未订购，但直接可以跳过订购流程，

	/**
	 * 优惠券领取记录状态
	 */
	public static final Integer coupon_record_status_ling = 4;// 已领取(被点击过)
	public static final Integer coupon_record_status_load = 0;// 已下载(短信发送成功)
	public static final Integer coupon_record_status_used = 1;// 已使用
	public static final Integer coupon_record_status_load_fail = 3;// 下载失败
	public static final Integer coupon_record_status_auto = 5;// 自动生成的记录

	/**
	 * 活动uri
	 */
	public static final String activity_uri_special = "special";

	/**
	 * 数据状态
	 */
	/**
	 * 数据有效，无效
	 */
	public static final int status_wuxiao = 0;
	public static final int status_init = 0;// 初始状态(待上架)
	public static final Integer status_nomal = 1;// 正常状态（活动中）
	public static final int status_xiajia = 2;// 下架
	public static final int status_del = 3;// 删除
	public static final int status_test = 4;// 测试

	/**
	 * 三种模式
	 */
	public static final String model_local = "local";
	public static final String model_test = "test";
	public static final String model_nomal = "nomal";

	// 测试订购
	public static final String model_order = "nomal";

	/**
	 * 活动名称
	 */
	public static final String activity_name_summer = "暑期嗨翻天";
	public static final String activity_name_zhongqiu = "中秋邮礼";
	public static final String activity_name_perent = "关爱父母";
	public static final String activity_name_miao = "秒杀活动";
	public static final String activity_name_yuandan = "狂欢元旦";
	public static final String activity_name_haimiaobaobao = "海绵宝宝";
	public static final String activity_name_shujiayyl = "暑假摇摇乐";
	public static final String activity_name_health = "健康带回家";
	
	public static final String activity_name_current = "健康带回家";

	public static final String page_index = "首页";
	public static final String page_tao = "淘宝优惠大全";
	public static final String page_vip = "会员专区";
	public static final String page_hot = "餐饮热券";
	public static final String page_xxyl = "休闲娱乐";
	public static final String page_grzx = "个人中心";
	public static final String page_detail = "详情页";
	public static final String page_sure = "订购确认页";
	public static final String page_baoyue = "包月确认页";
	public static final String page_dianbo = "点播确认页";
	public static final String page_kind = "分类";
	public static final String page_help = "帮助页面";
	public static final String page_activity = "活动页面";
	public static final String page_zpms = "招牌美食";
	public static final String page_xxyz = "休闲驿站";
	public static final String page_lrsy = "丽人摄影";
	public static final String page_jdly = "酒店旅游";
	public static final String page_ettd = "儿童天地";

	/**
	 * 订购来源
	 */
	public static final String buyer_order_source_tao = "淘宝优惠券";
	public static final String buyer_order_source_dd = "丁丁优惠券";
	public static final String buyer_order_source_xm = "熊猫优惠券";
	public static final String buyer_order_source_dx_activity = "电信活动";
	public static final String buyer_order_source_activity = "内部推广";
	public static final String buyer_order_source_kfc = "餐饮热券";

	public static final Integer actType_index = 1;
	public static final Integer actType_order = 3;
	public static final String contentType_MAIN = "MAIN";
	public static final String contentType_COLUMN = "COLUMN";
	public static final String contentType_OTHER = "OTHER";

	/**
	 * 优化券类的名称
	 */
	public static final String class_name_coupon = "Coupon";
	public static final String class_name_CouponDd = "CouponDd";
	public static final String class_name_XmShop = "XmShop";
	public static final String class_name_CouponKfc = "CouponKfc";

	public static final String URL_jiangsu = "http://61.160.131.58:84/";
	public static final String URL_jiangsu_test = "http://61.160.131.58:8084/";
	//public static final String URL_jiangsu_test = "http://127.0.0.1/";
	public static final String URL_gansu = "http://gstest.jshwd.com:8090";// 测试服务器
	//public static final String URL_gansu = "http://127.0.0.1";// 测试服务器
	public static final String province_gansu = "甘肃";
	public static final String province_jiangsu = "江苏";

	/**
	 * 优惠券网站
	 */
	public static final String coupon_site_taobao = "淘宝";
	public static final String coupon_site_jd = "京东";
	public static final String coupon_site_seller = "商家券";

}
