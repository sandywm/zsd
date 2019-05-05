package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.ActRoleInfo;

public interface ActRoleInfoManager {

	/**
	 * 根据角色编号、模块动作编号获取动作角色列表
	 * @author Administrator
	 * @date 2019-5-5 下午04:38:18
	 * @param roleId 角色编号(0表示全部)
	 * @param actId 模块动作编号(0表示全部)
	 * @return
	 * @throws WEBException
	 */
	List<ActRoleInfo> listInfoByOpt(Integer roleId,Integer actId)throws WEBException;
}
