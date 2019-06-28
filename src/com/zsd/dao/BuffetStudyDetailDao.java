package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.BuffetStudyDetailInfo;

public interface BuffetStudyDetailDao {

	/**
	 * 根据主键获取自助餐学习详情实体信息
	 * @author wm
	 * @date 2019-6-26 上午09:06:17
	 * @param sess
	 * @param id
	 * @return
	 */
	BuffetStudyDetailInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加自助餐学习详情请表信息
	 * @author wm
	 * @date 2019-6-26 上午09:06:37
	 * @param sess
	 * @param bsd
	 */
	void save(Session sess,BuffetStudyDetailInfo bsd);
	
	/**
	 * 修改自助餐学习详情表
	 * @author wm
	 * @date 2019-6-26 上午09:07:14
	 * @param sess
	 * @param bsd
	 */
	void update(Session sess,BuffetStudyDetailInfo bsd);
	
	/**
	 * 根据自助餐发送编号获取自助餐学习详情表列表
	 * @author wm
	 * @date 2019-6-26 上午09:07:23
	 * @param sess
	 * @param bsId 自助餐发送编号
	 * @return
	 */
	List<BuffetStudyDetailInfo> findInfoByBsdId(Session sess,Integer bsId);
}
