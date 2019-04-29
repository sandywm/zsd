package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.School;

public interface SchoolDao {
	
	/**
	 * 根据主键加载学校信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的学校信息的主键值
	 * @return 加载的学校信息PO
	 */
	School get(Session sess,int id);
	
	/**
	 * 保存学校信息信息实体，新增一条学校信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param sch 保存的学校信息实例
	 */
	void save(Session sess,School sch);
	
	/**
	 * 删除学校信息实体，删除一条学校信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param sch 删除的学校信息实体
	 */
	void delete(Session sess,School sch);
	
	/**
	 * 根据主键删除学校信息实体，删除一条学校信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除学校信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条学校信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param sch 需要更新的学校信息
	 */
	void update(Session sess,School sch);
	
	/**
	 * 根据条件分页显示学校列表
	 * @author Administrator
	 * @date 2019-4-29 上午11:35:43
	 * @param sess
	 * @param schoolName 学校名字(""表示全部)
	 * @param prov 省(""表示全部)
	 * @param city 市(""表示全部)
	 * @param county 县(""表示全部)
	 * @param town 乡(""表示全部)
	 * @param schoolType 学段（0表示全部）
	 * @param showStatus 显示状态（-1：全部，0：显示，1：隐藏）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<School> findPageInfoByOpt(Session sess,String schoolName,String prov,String city,String county,
			String town,Integer schoolType,Integer showStatus,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据条件获取学校记录条数
	 * @author Administrator
	 * @date 2019-4-29 上午11:37:43
	 * @param sess
	 * @param schoolName 学校名字(""表示全部)
	 * @param prov 省(""表示全部)
	 * @param city 市(""表示全部)
	 * @param county 县(""表示全部)
	 * @param town 乡(""表示全部)
	 * @param schoolType 学段（0表示全部）
	 * @param showStatus 显示状态（-1：全部，0：显示，1：隐藏）
	 * @return
	 */
	Integer getCountByOpt(Session sess,String schoolName,String prov,String city,String county,
			String town,Integer schoolType,Integer showStatus);
	
	/**
	 * 根据学校编号获取学校详细信息
	 * @author Administrator
	 * @date 2019-4-29 上午11:38:10
	 * @param sess
	 * @param id 编号
	 * @return
	 */
	List<School> findSpecInfoById(Session sess,Integer id);
	
	/**
	 * 根据条件获取学校列表--正常显示的（下拉框时使用）
	 * @author Administrator
	 * @date 2019-4-29 上午11:38:51
	 * @param sess
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param town 乡
	 * @param schoolType 学段
	 * @return
	 */
	List<School> findInfoByOpt(Session sess,String prov,String city,String county,String town,Integer schoolType);
}
