package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ChapterDao;
import com.zsd.dao.LoreInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.Chapter;
import com.zsd.module.LoreInfo;
import com.zsd.service.LoreInfoManager;
import com.zsd.tools.Convert;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class LoreInfoManagerImpl implements LoreInfoManager{

	ChapterDao cDao = null;
	LoreInfoDao lDao = null;
	Transaction tran = null;
	@Override
	public Integer addLore(Integer cptId, String loreName, String lorePyCode,
			Integer loreOrder, Integer mainLoreId, String loreCode)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			cDao = (ChapterDao) DaoFactory.instance(null).getDao(Constants.DAO_CHAPTER_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreInfo lore = new LoreInfo(cDao.get(sess, cptId), loreName, lorePyCode,
					0, 0, loreOrder,mainLoreId, loreCode);
			lDao.save(sess, lore);
			tran.commit();
			return lore.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加知识点时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateLore(Integer id, String loreName, Integer cptId,
			Integer orders, Integer inUse, Integer isFree) throws WEBException {
		// TODO Auto-generated method stub
		try {
			cDao = (ChapterDao) DaoFactory.instance(null).getDao(Constants.DAO_CHAPTER_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreInfo lore = lDao.get(sess, id);
			if(lore != null){
				Integer upStatus = 0;
				if(!loreName.equals("")){
					lore.setLoreName(loreName);
					lore.setLorePyCode(Convert.getFirstSpell(loreName));
					upStatus++;
				}
				if(cptId > 0){
					lore.setChapter(cDao.get(sess, cptId));
					upStatus++;
				}
				if(!orders.equals(-1)){
					lore.setLoreOrder(orders);
					upStatus++;
				}
				if(!inUse.equals(-1)){
					lore.setInUse(inUse);
					upStatus++;
				}
				if(!isFree.equals(-1)){
					lore.setFreeStatus(isFree);
					upStatus++;
				}
				if(upStatus > 0){
					lDao.update(sess, lore);
					tran.commit();
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改知识点时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreInfo> listInfoByCptId(Integer cptId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lDao.findInfoByCptId(sess, cptId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定章节下知识点信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreInfo> listPageInfoByCptId(Integer cptId, Integer pageNo,
			Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lDao.findPageInfoByCptId(sess, cptId, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("分页获取指定章节下知识点信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByCptId(Integer cptId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lDao.getCountByCptId(sess, cptId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定章节下知识点信息列表记录条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCurrentMaxOrderByCptId(Integer cptId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lDao.getCountByCptId(sess, cptId) + 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定章节下排序最大序号时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean checkExistByCptId(Integer cptId, String loreName)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			List<LoreInfo> loreList = lDao.findInfoByOpt(sess, cptId, loreName);
			if(loreList.size() > 0){
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("检查指定章节下指定知识点名称是否重名时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public LoreInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lDao.getEntityById(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定知识点编号的实体信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreInfo> listInfoByMainLoreId(Integer mainLoreId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lDao.findInfoByMainLoreId(sess, mainLoreId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主知识点（通用版）获取所有子知识点信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreInfo> listInfoByLorePyOrName(String lorePyCode,
			String loreName) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lDao.findInfoByOpt(sess, lorePyCode, loreName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据知识点拼音码/名称模糊查询知识点列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateLoreCodeById(Integer loreId, String loreCode)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreInfo lore = lDao.getEntityById(sess, loreId);
			if(lore != null){
				lore.setLoreCode(loreCode);
				lDao.update(sess, lore);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改知识点的编码时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
