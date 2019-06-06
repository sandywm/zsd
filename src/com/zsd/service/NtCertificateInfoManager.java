package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.NetTeacherCertificateInfo;

/**
 * @author zong
 * @version 2019-5-13 下午05:45:15
 */
public interface NtCertificateInfoManager {
	/**
	 * 添加网络导师证件信息
	 * @author zong
	 * 2019-5-13下午05:47:57
	 * @param ntId  老师编号
	 * @param icardImgFrontBig 身份证正面大
	 * @param icardImgBackBig 身份证背面大
	 * @param icardImgFrontSmall 身份证正面小
	 * @param icardImgBackSmall 身份证背面小
	 * @param icardName  身份证上名字
	 * @param icardNum 身份证号
	 * @param zgzImgBig 资格证图片大
	 * @param zgzImgSmall 资格证图片小
	 * @param xlzImgBig  学历证图片大
	 * @param xlzImgSmall 学历证图片小
	 * @param checkUserId 审核人员编号
	 * @param checkUserAccount 审核人员
	 * @param checkStatus  审核状态
	 * @param checkTime 审核时间
	 * @param checkReasonICard  身份证审核原因
	 * @param checkReasonZgz 资格证审核原因
	 * @param checkReasonXlz 学历证审核原因
	 * @return
	 * @throws WEBException
	 */
	Integer addNtcInfo(Integer ntId, String icardImgFrontBig,
			String icardImgBackBig, String icardImgFrontSmall,
			String icardImgBackSmall, String icardName, String icardNum,
			String zgzImgBig, String zgzImgSmall, String xlzImgBig,
			String xlzImgSmall, Integer checkUserId, String checkUserAccount,
			Integer checkStatus, String checkTime, String checkReasonICard,
			String checkReasonZgz, String checkReasonXlz) throws WEBException;
	/**
	 * 修改网络导师证件信息(网络导师)
	 * @author zong
	 * 2019-5-14上午09:07:10
	 * @param id 老师证件主键
	 * @param ntId  老师编号
	 * @param icardImgFrontBig 身份证正面大
	 * @param icardImgBackBig 身份证背面大
	 * @param icardImgFrontSmall 身份证正面小
	 * @param icardImgBackSmall 身份证背面小
	 * @param icardName  身份证上名字
	 * @param icardNum 身份证号
	 * @param zgzImgBig 资格证图片大
	 * @param zgzImgSmall 资格证图片小
	 * @param xlzImgBig  学历证图片大
	 * @param xlzImgSmall 学历证图片小
	 * @return
	 * @throws WEBException
	 */
	boolean  updateNtcInfo(Integer id,String icardImgFrontBig,
			String icardImgBackBig, String icardImgFrontSmall,
			String icardImgBackSmall, String icardName, String icardNum,
			String zgzImgBig, String zgzImgSmall, String xlzImgBig,
			String xlzImgSmall) throws WEBException;
	/**
	 * 修改网络导师证件审核信息(审核人员)
	 * @author zong
	 * 2019-5-14上午09:08:34
	 * @param id 老师证件主键
	 * @param checkUserAccount 审核人员
	 * @param checkStatus  审核状态
	 * @param checkTime 审核时间
	 * @param checkReasonICard  身份证审核原因
	 * @param checkReasonZgz 资格证审核原因
	 * @param checkReasonXlz 学历证审核原因
	 * @return
	 * @throws WEBException
	 */
	boolean updateNtcByCheck(Integer id,Integer checkUserId, String checkUserAccount,
			Integer checkStatus, String checkTime, String checkReasonICard,
			String checkReasonZgz, String checkReasonXlz)throws WEBException;
	/**
	 * 根据主键获取网络导师证件信息
	 * @author zong
	 * 2019-5-14上午09:11:15
	 * @param id 主键值
	 * @return
	 */
	List<NetTeacherCertificateInfo> listEntityByid(Integer id)throws WEBException;
	/**
	 * 根据网络导师编号获取证件信息
	 * @author zong
	 * 2019-6-5上午10:39:33
	 * @param teaId 网络导师主键
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherCertificateInfo> getNtcByTeaId(Integer teaId)throws WEBException;
}
