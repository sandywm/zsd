package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.HwStudyDetailDao;
import com.zsd.dao.HwStudyTjDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.HwStudyDetailInfo;
import com.zsd.module.HwStudyTjInfo;
import com.zsd.service.HwStudyDetailManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class HwStudyDetailManagerImpl implements HwStudyDetailManager{

	HwStudyTjDao hstjDao = null;
	HwStudyDetailDao hwsdDao = null;
	Transaction tran = null;
	@Override
	public boolean addBatchHWSD(Integer hwTjId, String queIdStr, String queAreaStr)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			hstjDao = (HwStudyTjDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_STUDY_TJ_INFO);
			hwsdDao = (HwStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			if(!queIdStr.equals("")){
				String[] queIdArr = queIdStr.split(",");
				String[] queAreaArr = queAreaStr.split(",");
				HwStudyTjInfo hwsTj = hstjDao.getEntityById(sess, hwTjId);
				for(Integer i = 0 ; i < queIdArr.length ; i++){
					Integer queId = Integer.parseInt(queIdArr[i]);
					String queArea = queAreaArr[i];
					HwStudyDetailInfo hwsd = new HwStudyDetailInfo(hwsTj, queId,queArea, "", -1, "");
					hwsdDao.save(sess, hwsd);
					if(i % 10 == 0){//批插入的对象立即写入数据库并释放内存
						sess.flush();
						sess.clear();
						tran.commit();
						tran = sess.beginTransaction();
					}
				}
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("批量增加家庭作业题库（学生点击家庭作业时自动创建）时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateInfoById(Integer id, String myAnser, Integer result)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwsdDao = (HwStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			HwStudyDetailInfo hwsd = hwsdDao.getEntityById(sess, id);
			if(hwsd != null){
				hwsd.setMyAnswer(myAnser);
				hwsd.setResult(result);
				hwsd.setAddTime(CurrentTime.getCurrentTime());
				hwsdDao.update(sess, hwsd);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键修改答题记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public HwStudyDetailInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwsdDao = (HwStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwsdDao.getEntityById(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键获取实体信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwStudyDetailInfo> listInfoByOpt(Integer sendHwId,
			Integer hwTjId, Integer queId, String queArea) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwsdDao = (HwStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwsdDao.findInfoByOpt(sess, sendHwId, hwTjId, queId, queArea);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件获取做题记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
