package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherStudentDao;
import com.zsd.module.NetTeacherStudent;
import com.zsd.tools.CommonTools;
import com.zsd.tools.CurrentTime;

@SuppressWarnings("unchecked")
public class NetTeacherStudentDaoImpl implements NetTeacherStudentDao {

	@Override
	public NetTeacherStudent get(Session sess, int id) {
		String hql = "from NetTeacherStudent as nts where nts.id = "+id;
		List<NetTeacherStudent> ntsList = sess.createQuery(hql).list();
		if(ntsList.size() > 0){
			return ntsList.get(0);
		}
		return null;
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

	@Override
	public List<NetTeacherStudent> findNTByStuId(Session sess, Integer stuId) {
		String hql = "from NetTeacherStudent as nts where nts.user.id="+stuId+" and nts.bindStatus!=0 and nts.endDate>'"+CurrentTime.getStringDate()+"'  and nts.clearStatus=0";
		return  sess.createQuery(hql).list();
	}
	@Override
	public List<NetTeacherStudent> findNTByntId(Session sess, Integer userId) {
		String hql = "from NetTeacherStudent as nts where nts.netTeacherInfo.user.id="+userId+" and nts.bindStatus!=0 and nts.endDate>'"+CurrentTime.getStringDate()+"' and nts.clearStatus=0";
		return  sess.createQuery(hql).list();
	}

	@Override
	public List<NetTeacherStudent> findNTByntId(Session sess, Integer userId,String stuName,Integer bindSta,Integer pageNo,Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql = "from NetTeacherStudent as nts where nts.netTeacherInfo.user.id="+userId+"  and nts.clearStatus=0";
		 if(bindSta.equals(3)){ // 绑定到期
			hql+=" and nts.bindStatus !=0  and nts.endDate<='"+CurrentTime.getStringDate()+"'";
		 }else if (bindSta.equals(0)) { //取消绑定
			 hql+="  and nts.bindStatus="+bindSta;
		 }else{//正在绑定
			 hql+="  and nts.bindStatus="+bindSta+" and nts.endDate>'"+CurrentTime.getStringDate()+"'"; 
		 }
		 if(!stuName.equals("")){
				hql +=" and nts.user.realName like '%"+stuName+"%'"; 
		  }
		return  sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}
	@Override
	public Integer getNTByNTIdCount(Session sess, Integer userId,String stuName, Integer bindSta) {
		String hql = "select count(nts.id) from NetTeacherStudent as nts where nts.netTeacherInfo.user.id="+userId+"  and nts.clearStatus=0";
		 if(bindSta.equals(3)){ // 绑定到期
			  hql+=" and nts.bindStatus !=0  and nts.endDate<='"+CurrentTime.getStringDate()+"'";
		 }else if (bindSta.equals(0)) { //取消绑定
			 hql+="  and nts.bindStatus="+bindSta;
		 }else{//正在绑定
			 hql+="  and nts.bindStatus="+bindSta+" and nts.endDate>'"+CurrentTime.getStringDate()+"'"; 
		 }
		 if(!stuName.equals("")){
				hql +=" and nts.user.realName like '%"+stuName+"%'"; 
		  }
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<NetTeacherStudent> findNTByStuNameOrBindSta(Session sess,Integer ntId,Integer paySta,
			Integer bindFlag, String stuName, Integer pageNo, Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql = "from NetTeacherStudent as nts where nts.netTeacherInfo.id="+ ntId;
		 if(bindFlag.equals(1)){//正在使用
			 hql+=" and nts.clearStatus =0  and  nts.endDate>'"+CurrentTime.getStringDate()+"' ";
			 if(paySta==0){//免费试用
				hql+=" and ((nts.bindStatus=2) or (nts.bindStatus=-1)) and nts.payStatus="+paySta;
			 }else if(paySta==1){//付费
				hql+=" and  nts.bindStatus=1   and nts.payStatus="+paySta;
			 }else{
				 hql+=" and ((nts.bindStatus=1) or (nts.bindStatus= -1) or (nts.bindStatus=2) )" ;
			 }
		 }else if(bindFlag==2){//到期
			 hql+=" and nts.clearStatus =0  and  nts.endDate<'"+CurrentTime.getStringDate()+"'";
			 if(paySta==0){//免费试用
				hql+=" and ((nts.bindStatus=2) or (nts.bindStatus=-1))  and nts.payStatus="+paySta;
			 }else if(paySta==1 ){//付费
				 hql+=" and nts.bindStatus=1 and nts.payStatus="+paySta;
			 }else{
				hql+=" and ((nts.bindStatus=1) or (nts.bindStatus=-1) or (nts.bindStatus=2))";
			 } 
		 }else if(bindFlag ==3){//取消
			 hql+=" and nts.clearStatus =0";
			 if(paySta==0 ||paySta==1 ){//免费试用或者付费
				hql+=" and nts.bindStatus=0 and nts.payStatus="+paySta;
			}else{
			   hql+=" and nts.bindStatus=0";
			} 
		 }else if(bindFlag ==4){//升学
			 hql+=" and  nts.clearStatus =1 ";
			 if(paySta==0 || paySta==1 ){//免费试用或者付费或者免费
				hql+=" and nts.payStatus="+paySta;
			  }
		 }else{//全部
			 if(paySta==0 ||paySta==1){//免费试用或者付费或者免费
				hql+=" and  nts.payStatus="+paySta ;
			}
		 }
		 if(!stuName.equals("")){
			hql +=" and nts.user.realName like '%"+stuName+"%'"; 
		 }
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		
	}

	@Override
	public Integer getNtsBystunameOrBindSta(Session sess,Integer ntId,Integer paySta,Integer bindFlag, String stuName) {
		String hql = "select count(nts.id) from NetTeacherStudent as nts where nts.netTeacherInfo.id="+ ntId;
		 if(bindFlag.equals(1)){//正在使用
			 hql+=" and nts.clearStatus =0  and  nts.endDate>'"+CurrentTime.getStringDate()+"' ";
			 if(paySta==0){//免费试用
				hql+=" and ((nts.bindStatus=2) or (nts.bindStatus=-1)) and nts.payStatus="+paySta;
			 }else if(paySta==1){//付费
				hql+=" and  nts.bindStatus=1   and nts.payStatus="+paySta;
			 }else{
				 hql+=" and ((nts.bindStatus=1) or (nts.bindStatus= -1) or (nts.bindStatus=2) )" ;
			 }
		 }else if(bindFlag==2){//到期
			 hql+=" and nts.clearStatus =0  and  nts.endDate<'"+CurrentTime.getStringDate()+"'";
			 if(paySta==0){//免费试用
				hql+=" and ((nts.bindStatus=2) or (nts.bindStatus=-1))  and nts.payStatus="+paySta;
			 }else if(paySta==1 ){//付费
				 hql+=" and nts.bindStatus=1 and nts.payStatus="+paySta;
			 }else{
				hql+=" and ((nts.bindStatus=1) or (nts.bindStatus=-1)or (nts.bindStatus=2))";
			 } 
		 }else if(bindFlag ==3){//取消
			 hql+=" and nts.clearStatus =0";
			 if(paySta==0 ||paySta==1 ){//免费试用或者付费
				hql+=" and nts.bindStatus=0 and nts.payStatus="+paySta;
			}else{
			   hql+=" and nts.bindStatus=0";
			} 
		 }else if(bindFlag ==4){//升学
			 hql+=" and  nts.clearStatus =1 ";
			 if(paySta==0 || paySta==1 ){//免费试用或者付费或者免费
				hql+=" and nts.payStatus="+paySta;
			  }
		 }else{//全部
			 if(paySta==0 ||paySta==1){//免费试用或者付费或者免费
				hql+=" and  nts.payStatus="+paySta ;
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
		String hql = "select count(nts.id) from NetTeacherStudent as nts where nts.netTeacherInfo.id="+ ntId;
		 hql+=" and nts.clearStatus =0  and  nts.endDate>'"+CurrentTime.getStringDate()+"' ";
		 if(bindFlag.equals(-1)|| bindFlag.equals(2)){//免费试用或者免费
			hql+=" and nts.bindStatus="+bindFlag+" and nts.payStatus=0";
		 }else if(bindFlag.equals(1)){//付费
			hql+=" and  nts.bindStatus="+bindFlag+" and nts.payStatus=1";
		 }
		Object countObj = sess.createQuery(hql).uniqueResult();
	    return CommonTools.longToInt(countObj);
	}

	@Override
	public boolean isBindTeaBySubIdAndSchType(Session sess, Integer stuID,
			Integer subID, Integer schoolType) {
	
			String hql="from NetTeacherStudent as nts where nts.user.id="+stuID+"and nts.netTeacherInfo.subject.id="+subID+"  and nts.netTeacherInfo.schoolType="+schoolType;
			List<NetTeacherStudent> list = sess.createQuery(hql).list();
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}
		
	}

	@Override
	public List<NetTeacherStudent> findValidInfoByOpt(Session sess,
			Integer stuId) {
		// TODO Auto-generated method stub
		String hql = " from NetTeacherStudent as nts where nts.user.id = "+stuId+ " and nts.clearStatus = 0";
		hql += "  and nts.bindStatus != 0 and  nts.endDate > '"+CurrentTime.getStringDate()+"'";
		return  sess.createQuery(hql).list();
	}

	@Override
	public List<NetTeacherStudent> findNtsByNtId(Session sess, Integer ntId,
			Integer bindSta) {
		String hql = "from NetTeacherStudent as nts where nts.netTeacherInfo.id="+ntId+" and nts.bindStatus="+bindSta+" and nts.clearStatus=0";
		return  sess.createQuery(hql).list();
	}

	@Override
	public List<NetTeacherStudent> findBindStu(Session sess, Integer userId/*,Integer bindFlag*/) {
		String hql = "from NetTeacherStudent as nts where nts.netTeacherInfo.user.id="+ userId;
	/*	 if(bindFlag.equals(1)){//正在使用
			 hql+=" and nts.clearStatus =0  and  nts.endDate>'"+CurrentTime.getStringDate()+"' ";
			 hql+=" and ((nts.bindStatus=1) or (nts.bindStatus= -1) or (nts.bindStatus=2) )" ;
			
		 }else if(bindFlag==2){//取消绑定关系
			 hql+=" (and nts.clearStatus =0  and  nts.endDate<'"+CurrentTime.getStringDate()+"'";
			 hql+=" and ((nts.bindStatus=1) or (nts.bindStatus=-1) or (nts.bindStatus=2)))";
			 hql+=" or ( and nts.clearStatus =0  and nts.bindStatus=0)";
			 hql+=" or ( and  nts.clearStatus =1)";
			
		 }*/
		return sess.createQuery(hql).list();
	}

	@Override
	public List<NetTeacherStudent> findBindNt(Session sess, Integer stuId) {
		String hql = " from NetTeacherStudent as nts where nts.user.id = "+stuId;
		return sess.createQuery(hql).list();
	}

	@Override
	public NetTeacherStudent getValidInfoByOpt(Session sess, Integer stuId,Integer subId) {
		// TODO Auto-generated method stub
		String hql = " from NetTeacherStudent as nts where nts.user.id = "+stuId+ " and nts.clearStatus = 0 and nts.netTeacherInfo.subject.id = "+subId;
		hql += "  and nts.bindStatus != 0 and  nts.endDate > '"+CurrentTime.getStringDate()+"'";
		List<NetTeacherStudent> ntsList = sess.createQuery(hql).list();
		if(ntsList.size() > 0){
			return ntsList.get(0);
		}
		return null;
	}

	@Override
	public NetTeacherStudent getEntityInfoByOpt(Session sess, Integer userId,Integer stuId) {
		// TODO Auto-generated method stub
		String hql = " from NetTeacherStudent as nts where nts.user.id = "+stuId + " and nts.netTeacherInfo.user.id = "+userId;
		List<NetTeacherStudent> ntsList = sess.createQuery(hql).list();
		if(ntsList.size() > 0){
			return ntsList.get(0);
		}
		return null;
	}
}
