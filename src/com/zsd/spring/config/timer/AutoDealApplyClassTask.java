package com.zsd.spring.config.timer;

import java.util.List;

import com.zsd.factory.AppFactory;
import com.zsd.module.ApplyClassInfo;
import com.zsd.service.ApplyClassManager;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;

public class AutoDealApplyClassTask {

	/**
	 * 自动处理每天没被处理的申请
	 * @author wm
	 * @date 2019-8-23 上午11:21:42
	 * @throws Exception
	 */
	public void autoDeal() throws Exception{
		System.out.println("自动处理每天没被处理的申请开始："+CurrentTime.getCurrentTime());
		ApplyClassManager acm = (ApplyClassManager) AppFactory.instance(null).getApp(Constants.WEB_APPLY_CLASS_INFO);
		List<ApplyClassInfo> acList = acm.listAllUnCheckApplyInfo();
		if(acList.size() > 0){
			for(ApplyClassInfo ac : acList){
				Integer acId = ac.getId();
				acm.setCancleInfo(acId, 2, "系统自动拒绝");
			}
		}
		System.out.println("自动处理每天没被处理的申请结束："+CurrentTime.getCurrentTime());
	}
}
