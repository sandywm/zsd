package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherStudentDao;
import com.zsd.module.NetTeacherStudent;
import com.zsd.tools.CommonTools;
import com.zsd.tools.CurrentTime;

public class NetTeacherStudentDaoImpl implements NetTeacherStudentDao {

	@Override
	public NetTeacherStudent get(Session sess, int id) {
		return (NetTeacherStudent) sess.load(NetTeacherStudent.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherStudent nts) {
		sess.save(nts);
	}

	@Override
	public void delete(Session sess, NetTeacherStudent nts) {
		sess.delete(nts);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, NetTeacherStudent nts) {
		sess.update(nts);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherStudent> findNTByStuId(Session sess, int stuId) {
		String hql = "from NetTeacherStudent as nts where nts.user.id="+stuId+"and nts.bindStatus!=2 and nts.bindStatus!=0 and nts.clearStatus=0";
		return  sess.createQuery(hql).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherStudent> findNTByntId(Session sess, int ntId) {
		String hql = "from NetTeacherStudent as nts where nts.netTeacherInfo.user.id="+ntId+"and nts.bindStatus!=2 and nts.bindStatus!=0 and nts.clearStatus=0";
		return  sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherStudent> findNTByntId(Session sess, int ntId,Integer bindSta) {
		String hql = "from NetTeacherStudent as nts where nts.netTeacherInfo.user.id="+ntId+"and nts.bindStatus="+bindSta;
		return  sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherStudent> findNTByStuNameOrBindSta(Session sess,Integer ntId,Integer paySta,
			Integer bindFlag, String stuName, Integer pageNo, Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql = "from NetTeacherStudent as nts where nts.netTeacherInfo.user.id="+ ntId;
		 if(bindFlag.equals(1)){//正在使用
			 hql+=" and nts.clearStatus =0  and  nts.endDate>'"+CurrentTime.getStringDate()+"' ";
			 if(paySta==0){//免费试用
				hql+="and ((nts.bindStatus=2) or (nts.bindStatus=-1)) and nts.payStatus="+paySta;
			 }else if(paySta==1){//付费
				hql+="and  nts.bindStatus=1   and nts.payStatus="+paySta;
			 }else{
				 hql+="and ((nts.bindStatus=1) or (nts.bindStatus= -1) or (nts.bindStatus=2) )" ;
			 }
		 }else if(bindFlag==2){//到期
			 hql+=" and nts.clearStatus =0  and  nts.endDate<'"+CurrentTime.getStringDate()+"'";
			 if(paySta==0){//免费试用
				hql+="and ((nts.bindStatus=2) or (nts.bindStatus=-1))  and nts.payStatus="+paySta;
			 }else if(paySta==1 ){//付费
				 hql+="and nts.bindStatus=1 and nts.payStatus="+paySta;
			 }else{
				hql+="and ((nts.bindStatus=1) or (nts.bindStatus=-1)or (nts.bindStatus=2))";
			 } 
		 }else if(bindFlag ==3){//取消
			 hql+="and nts.clearStatus =0";
			 if(paySta==0 ||paySta==1 ){//免费试用或者付费
				hql+="and nts.bindStatus=0 and nts.payStatus="+paySta;
			}else{
			   hql+="and nts.bindStatus=0";
			} 
		 }else if(bindFlag ==4){//升学
			 hql+="and  nts.clearStatus =1 ";
			 if(paySta==0 || paySta==1 ){//免费试用或者付费或者免费
				hql+="and nts.payStatus="+paySta;
			  }
		 }else{//全部
			 if(paySta==0 ||paySta==1){//免费试用或者付费或者免费
				hql+="and  nts.payStatus="+paySta ;
			}
		 }
		 if(!stuName.equals("")){
			hql +=" and nts.user.realName like '%"+stuName+"%'"; 
		 }
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		
	}

	@Override
	public Integer getNtsBystunameOrBindSta(Session sess,Integer ntId,Integer paySta,Integer bindFlag, String stuName) {
		String hql = "select count(nts.id) from NetTeacherStudent as nts where nts.netTeacherInfo.user.id="+ ntId;
		 if(bindFlag.equals(1)){//正在使用
			 hql+=" and nts.clearStatus =0  and  nts.endDate>'"+CurrentTime.getStringDate()+"' ";
			 if(paySta==0){//免费试用
				hql+="and ((nts.bindStatus=2) or (nts.bindStatus=-1)) and nts.payStatus="+paySta;
			 }else if(paySta==1){//付费
				hql+="and  nts.bindStatus=1   and nts.payStatus="+paySta;
			 }else{
				 hql+="and ((nts.bindStatus=1) or (nts.bindStatus= -1) or (nts.bindStatus=2) )" ;
			 }
		 }else if(bindFlag==2){//到期
			 hql+=" and nts.clearStatus =0  and  nts.endDate<'"+CurrentTime.getStringDate()+"'";
			 if(paySta==0){//免费试用
				hql+="and ((nts.bindStatus=2) or (nts.bindStatus=-1))  and nts.payStatus="+paySta;
			 }else if(paySta==1 ){//付费
				 hql+="and nts.bindStatus=1 and nts.payStatus="+paySta;
			 }else{
				hql+="and ((nts.bindStatus=1) or (nts.bindStatus=-1)or (nts.bindStatus=2))";
			 } 
		 }else if(bindFlag ==3){//取消
			 hql+="and nts.clearStatus =0";
			 if(paySta==0 ||paySta==1 ){//免费试用或者付费
				hql+="and nts.bindStatus=0 and nts.payStatus="+paySta;
			}else{
			   hql+="and nts.bindStatus=0";
			} 
		 }else if(bindFlag ==4){//升学
			 hql+="and  nts.clearStatus =1 ";
			 if(paySta==0 || paySta==1 ){//免费试用或者付费或者免费
				hql+="and nts.payStatus="+paySta;
			  }
		 }else{//全部
			 if(paySta==0 ||paySta==1){//免费试用或者付费或者免费
				hql+="and  nts.payStatus="+paySta ;
			}
		 }
		 if(!stuName.equals("")){
			hql +=" and nts.user.realName like '%"+stuName+"%'"; 
		 }
		 Object countObj = sess.createQuery(hql).uniqueResult();
		 return CommonTools.longToInt(countObj);
	}

	@Override
	public Integer getByStuNum(Session sess, Integer ntId, Integer bindFlag) {
		String hql = "select count(nts.id) from NetTeacherStudent as nts where nts.netTeacherInfo.user.id="+ ntId;
		 hql+=" and nts.clearStatus =0  and  nts.endDate>'"+CurrentTime.getStringDate()+"' ";
		 if(bindFlag.equals(-1)|| bindFlag.equals(2)){//免费试用或者免费
			hql+="and nts.bindStatus="+bindFlag+" and nts.payStatus=0";
		 }else if(bindFlag.equals(1)){//付费
			hql+=" and  nts.bindStatus="+bindFlag+" and nts.payStatus=1";
		 }
		Object countObj = sess.createQuery(hql).uniqueResult();
	    return CommonTools.longToInt(countObj);
	}

}
