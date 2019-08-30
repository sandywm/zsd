package com.zsd.spring.config.timer;

import com.zsd.factory.AppFactory;
import com.zsd.service.StudentPayOrderInfoManager;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;

public class AutoDelUnComOrderTask {

	/**
	 * 自动删除每天未完成的学生购买订单记录
	 * @author wm
	 * @date 2019-8-30 上午11:41:59
	 * @throws Exception
	 */
	public void autoDel() throws Exception{
		StudentPayOrderInfoManager spom = (StudentPayOrderInfoManager) AppFactory.instance(null).getApp(Constants.WEB_STUDENT_PAY_ORDER_INFO);
		System.out.println("自动删除开始："+CurrentTime.getCurrentTime());
		spom.delBatchUnComPayOrder();
		System.out.println("自动删除结束："+CurrentTime.getCurrentTime());
	}
}
