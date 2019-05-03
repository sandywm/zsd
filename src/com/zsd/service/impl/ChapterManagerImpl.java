/**
 * 
 */
package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ChapterDao;
import com.zsd.dao.EducationDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.Chapter;
import com.zsd.service.ChapterManger;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/**  
 *  @author  wm  
 *  @ClassName  : ChapterMangerImpl  
 *  @Version  V1.0
 *  @ModifiedBy wm  
 *  @date  2019-5-3 下午09:47:13 
 */

public class ChapterManagerImpl implements ChapterManger{

	EducationDao eDao = null;
	ChapterDao cDao = null;
	Transaction tran = null;
	@Override
	public Integer addChapter(Integer eduId, String chapterName,
			Integer chapterOrder) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eDao = (EducationDao) DaoFactory.instance(null).getDao(Constants.DAO_EDUCATION_INFO);
			cDao = (ChapterDao) DaoFactory.instance(null).getDao(Constants.DAO_CHAPTER_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			Chapter cpt = new Chapter(eDao.get(sess, eduId),chapterName,chapterOrder);
			cDao.save(sess, cpt);
			tran.commit();
			return cpt.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加章节信息出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateChapter(Integer chapterId, String chapterName,
			Integer chapterOrder) throws WEBException {
		// TODO Auto-generated method stub
		try {
			cDao = (ChapterDao) DaoFactory.instance(null).getDao(Constants.DAO_CHAPTER_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			Chapter cpt = cDao.get(sess, chapterId);
			if(cpt != null){
				cpt.setChapterName(chapterName);
				cpt.setChapterOrder(chapterOrder);
				cDao.update(sess, cpt);
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改指定章节信息出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Chapter> ListInfoByOpt(Integer subId, String gradeName,
			Integer ediId, String eduVolume) throws WEBException {
		// TODO Auto-generated method stub
		try {
			cDao = (ChapterDao) DaoFactory.instance(null).getDao(Constants.DAO_CHAPTER_INFO);
			Session sess = HibernateUtil.currentSession();
			return cDao.findInfoByOpt(sess, subId, gradeName, ediId, eduVolume);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据（科目、年级、出版社、上/下册）获取到教材编号--获取到章节列表出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Chapter> ListInfoByEduId(Integer eduId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			cDao = (ChapterDao) DaoFactory.instance(null).getDao(Constants.DAO_CHAPTER_INFO);
			Session sess = HibernateUtil.currentSession();
			return cDao.findInfoByEduId(sess, eduId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据教材编号获取到章节列表出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Chapter getEntityById(Integer cptId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			cDao = (ChapterDao) DaoFactory.instance(null).getDao(Constants.DAO_CHAPTER_INFO);
			Session sess = HibernateUtil.currentSession();
			return cDao.get(sess, cptId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据章节编号获取到章节详情出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
