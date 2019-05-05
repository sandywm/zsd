package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.ModuleActionInfo;

public interface ModuleActionInfoManager {

	/**
	 * 根据模块编号获取模块动作信息列表
	 * @author Administrator
	 * @date 2019-5-5 下午04:33:51
	 * @param modId 模块编号
	 * @return
	 * @throws WEBException
	 */
	List<ModuleActionInfo> listInfoByModId(Integer modId) throws WEBException;
}
