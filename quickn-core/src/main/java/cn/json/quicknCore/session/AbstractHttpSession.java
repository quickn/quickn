package cn.json.quicknCore.session;

import cn.json.quicknCore.common.Setting;
import cn.json.quicknCore.quickn.utils.QuickUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Random;

public abstract class AbstractHttpSession implements Session {
	protected String sessionId;
	protected long updateAccessTime;

	public void create(String sessionId,HttpServletRequest request) {
		updateAccessTime();
		this.sessionId = QuickUtils.md5(request.getRemoteAddr()
				+ System.currentTimeMillis() + (new Random().nextLong()));
	}

	public String getSessionId() {
		return sessionId;
	}



	public boolean isexpired() {
		return (Calendar.getInstance().getTimeInMillis() - updateAccessTime) > (Setting.sessionTimeOut*1000);
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public void updateAccessTime() {
		updateAccessTime = Calendar.getInstance().getTimeInMillis();
	}
}
