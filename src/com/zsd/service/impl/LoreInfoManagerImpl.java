package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ChapterDao;
import com.zsd.dao.LoreInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.Chapter;
import com.zsd.module.Education;
import com.zsd.module.GradeSubject;
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
			String loreName,Integer ediId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lDao.findInfoByOpt(sess, lorePyCode, loreName,ediId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据出版社编号、知识点拼音码/名称模糊查询知识点列表时出现异常!");
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

	@Override
	public LoreInfo getLoreInfoByOpt(Integer mainLoreId, Integer ediId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lDao.getLoreInfoByOpt(sess, mainLoreId, ediId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据引用知识点编号（通用版），其他出版社获取知识点时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreInfo> listAllInfo() throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lDao.findAllInfo(sess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取全部数据（自动修改编码时用）--其他任何时候不要调用时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public  void updateBatchLoreCode(List<LoreInfo> lList)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			for(Integer i = 0 ; i < lList.size() ; i++){
				LoreInfo lore = lList.get(i);
				Integer loreId = lore.getId();
				Integer cptOrder = lore.getChapter().getChapterOrder();
				Integer loreOrder = lore.getLoreOrder();
				Education edu = lore.getChapter().getEducation();
				GradeSubject gs = edu.getGradeSubject();
				Integer subId = gs.getSubject().getId();
				String gradeName = gs.getGradeName();
				String eduVolume = edu.getEduVolume();
				Integer ediId = edu.getEdition().getId();
				String gradeCode = "";//年级号
				String subIdCode = "";
				if(subId < 10){
					subIdCode = "0" + subId;
				}
				String paraCode = "";//学段号
				Integer gradeNum = Integer.parseInt(gradeCode);
				if(gradeNum < 7){
					paraCode = "01";
				}else if(gradeNum >= 7 && gradeNum <= 9){
					paraCode = "02";
				}else{
					paraCode = "03";
				}
				String eduVolumeCode = "02";//教材编号
				if(eduVolume.equals("上册")){
					eduVolumeCode = "01";
				}
				
				String ediIdCode = "";//出版社号
				if(ediId < 10){
					ediIdCode = "0" + ediId;
				}
				String cptOrderCode = "";//章节排序号
				if(cptOrder < 10){
					cptOrderCode = "0" + cptOrder;
				}
				String loreOrderCode = "";
				if(loreOrder < 10){
					loreOrderCode = "0" + loreOrder;
				}
				String loreCode = subIdCode + "-" + paraCode + "-" + gradeCode + "-" + eduVolumeCode + "-" + ediIdCode + "-" + cptOrderCode + "-" + loreOrderCode;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改知识点的编码时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
