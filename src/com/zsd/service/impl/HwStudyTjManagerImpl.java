package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.HwStudyTjDao;
import com.zsd.dao.SendHwDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.HwStudyTjInfo;
import com.zsd.module.SendHwInfo;
import com.zsd.service.HwStudyTjManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class HwStudyTjManagerImpl implements HwStudyTjManager{

	SendHwDao shwDao = null;
	UserDao uDao = null;
	HwStudyTjDao hstjDao = null;
	Transaction tran = null;
	@Override
	public boolean addBatchHwStudyTj(Integer hwSendId, String stuIdStr,
			Integer allNum) throws WEBException {
		// TODO Auto-generated method stub
		try {
			shwDao = (SendHwDao) DaoFactory.instance(null).getDao(Constants.DAO_SEND_HW_INFO);
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			hstjDao = (HwStudyTjDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_STUDY_TJ_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			if(!stuIdStr.equals("")){
				String[] stuIdArr = stuIdStr.split(",");
				SendHwInfo shw = shwDao.getEntityById(sess, hwSendId);
				for(Integer i = 0 ; i < stuIdArr.length ; i++){
					Integer stuId = Integer.parseInt(stuIdArr[i]);
					HwStudyTjInfo hwTj = new HwStudyTjInfo(shw, uDao.get(sess,stuId), 0,"", 0, 0, 0,allNum,0);
					hstjDao.save(sess, hwTj);
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
			throw new WEBException("批量增加家庭作业做题统计表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateInfoById(Integer id, Integer conStatus,
			Integer hwScore, Integer succNum, Integer errorNum,Integer hwsdAddStatus)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			hstjDao = (HwStudyTjDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_STUDY_TJ_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			HwStudyTjInfo hwTj = hstjDao.getEntityById(sess, id);
			if(hwTj != null){
				if(conStatus >= 0){
					hwTj.setComStatus(conStatus);
					if(conStatus.equals(1) || conStatus.equals(2)){//全部完成的时候才能修改得分和完成日期
						hwTj.setComDate(CurrentTime.getCurrentTime());
						Integer succNum_db = hwTj.getSuccNum();
						Integer errorNum_db = hwTj.getErrorNum();
						Integer comNum_db = hwTj.getAllNum();
						if(succNum.equals(1)){//最后一题做对
							succNum_db += 1;
						}else{//最后一题做错
							errorNum_db += 1;
						}
						//计算得分
						if(comNum_db > 0){
							hwTj.setHwScore(succNum_db / comNum_db);
						}
					}
				}
				if(succNum.equals(1)){
					hwTj.setSuccNum(hwTj.getSuccNum() + 1);
				}
				if(errorNum.equals(1)){
					hwTj.setErrorNum(hwTj.getErrorNum() + 1);
				}
				if(hwsdAddStatus.equals(0) || hwsdAddStatus.equals(1)){
					hwTj.setHwsdAddStatus(hwsdAddStatus);
				}
				hstjDao.update(sess, hwTj);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改家庭作业做题统计表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwStudyTjInfo> listInfoByOpt(Integer hwSendId, Integer stuId,
			Integer comStatus,boolean pageFlag,Integer pageNo,Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hstjDao = (HwStudyTjDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_STUDY_TJ_INFO);
			Session sess = HibernateUtil.currentSession();
			return hstjDao.findPageInfoByOpt(sess, hwSendId, stuId, comStatus, pageFlag, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件获取家庭作业统计记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOpt(Integer hwSendId, Integer stuId,
			Integer comStatus) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hstjDao = (HwStudyTjDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_STUDY_TJ_INFO);
			Session sess = HibernateUtil.currentSession();
			return hstjDao.getCountByOpt(sess, hwSendId, stuId, comStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件获取家庭作业做题统计信息记录条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
