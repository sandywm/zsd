package com.zsd.action.studyrec;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zsd.factory.AppFactory;
import com.zsd.module.BuffetAbilityRelationInfo;
import com.zsd.module.BuffetMindRelationInfo;
import com.zsd.module.BuffetStudyDetailInfo;
import com.zsd.service.BuffetAbilityRelationInfoManager;
import com.zsd.service.BuffetMindRelationInfoManager;
import com.zsd.util.Constants;



public class BuffetStudyDetailInfoJson {
	private String currMindSuccRate;//当前巴菲特思维正确率数组xx,yy,....
	private String currMindNumber;//当前巴菲特思维题数量
	private String currAbilitySuccRate;//当前能力正确率数组
	private String currAbilityNumber;//当前巴菲特能力题数量
	private String allMindSuccRate;//所有思维正确率数组
	private String allMindNumber;//所有思维题数量
	private String allAbilitySuccRate;//所有能力正确率数组
	private String allAbilityNumber;//所有能力题数量
	public String getCurrMindSuccRate() {
		return currMindSuccRate;
	}
	public void setCurrMindSuccRate(String currMindSuccRate) {
		this.currMindSuccRate = currMindSuccRate;
	}
	public String getAllMindSuccRate() {
		return allMindSuccRate;
	}
	public void setAllMindSuccRate(String allMindSuccRate) {
		this.allMindSuccRate = allMindSuccRate;
	}
	public String getCurrAbilitySuccRate() {
		return currAbilitySuccRate;
	}
	public void setCurrAbilitySuccRate(String currAbilitySuccRate) {
		this.currAbilitySuccRate = currAbilitySuccRate;
	}
	public String getAllAbilitySuccRate() {
		return allAbilitySuccRate;
	}
	public void setAllAbilitySuccRate(String allAbilitySuccRate) {
		this.allAbilitySuccRate = allAbilitySuccRate;
	}
	
	//图形报表数据(currDetialList当前巴菲特学习情况,allDetailList所有巴菲特学习情况)
	public String getCurrMindNumber() {
		return currMindNumber;
	}
	public void setCurrMindNumber(String currMindNumber) {
		this.currMindNumber = currMindNumber;
	}
	public String getCurrAbilityNumber() {
		return currAbilityNumber;
	}
	public void setCurrAbilityNumber(String currAbilityNumber) {
		this.currAbilityNumber = currAbilityNumber;
	}
	public String getAllMindNumber() {
		return allMindNumber;
	}
	public void setAllMindNumber(String allMindNumber) {
		this.allMindNumber = allMindNumber;
	}
	public String getAllAbilityNumber() {
		return allAbilityNumber;
	}
	public void setAllAbilityNumber(String allAbilityNumber) {
		this.allAbilityNumber = allAbilityNumber;
	}
	public List<BuffetStudyDetailInfoJson> getBuffetStudyInfoJson(List<BuffetStudyDetailInfo> currDetailList,List<BuffetStudyDetailInfo> allDetailList) throws Exception{
		List<BuffetStudyDetailInfoJson> result = new ArrayList<BuffetStudyDetailInfoJson>();
		BuffetMindRelationInfoManager bmrm = (BuffetMindRelationInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_MIND_RELATION_INFO);
		BuffetAbilityRelationInfoManager barm = (BuffetAbilityRelationInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ABILITY_RELATION_INFO);
		//当前巴菲特发布记录
		Integer currAbilitySuccNumber_lijie = 0 ;
		Integer currAbilitySuccNumber_fenxi = 0 ;
		Integer currAbilitySuccNumber_biaoda = 0 ;
		Integer currAbilitySuccNumber_shijian = 0 ;
		Integer currAbilitySuccNumber_zy = 0 ;
		Integer currAbilitySuccNumber_lx = 0 ;
		Integer currAbilitySuccNumber_zh = 0 ;
		Integer currAbilitySuccNumber_cz = 0 ;
		
		Integer currAbilityAllNumber_lijie = 0 ;
		Integer currAbilityAllNumber_fenxi = 0 ;
		Integer currAbilityAllNumber_biaoda = 0 ;
		Integer currAbilityAllNumber_shijian = 0 ;
		Integer currAbilityAllNumber_zy = 0 ;
		Integer currAbilityAllNumber_lx = 0 ;
		Integer currAbilityAllNumber_zh = 0 ;
		Integer currAbilityAllNumber_cz = 0 ;
		
		Integer currMindSuccNumber_dx = 0 ;
		Integer currMindSuccNumber_fs = 0 ;
		Integer currMindSuccNumber_nx = 0 ;
		Integer currMindSuccNumber_cx = 0 ;
		Integer currMindSuccNumber_nb = 0 ;
		Integer currMindSuccNumber_yz = 0 ;
		Integer currMindSuccNumber_xx = 0 ;
		Integer currMindSuccNumber_lx = 0 ;
		
		Integer currMindAllNumber_dx = 0 ;
		Integer currMindAllNumber_fs = 0 ;
		Integer currMindAllNumber_nx = 0 ;
		Integer currMindAllNumber_cx = 0 ;
		Integer currMindAllNumber_nb = 0 ;
		Integer currMindAllNumber_yz = 0 ;
		Integer currMindAllNumber_xx = 0 ;
		Integer currMindAllNumber_lx = 0 ;
		
		//汇总巴菲特发布记录
		Integer abilitySuccNumber_lijie = 0 ;
		Integer abilitySuccNumber_fenxi = 0 ;
		Integer abilitySuccNumber_biaoda = 0 ;
		Integer abilitySuccNumber_shijian = 0 ;
		Integer abilitySuccNumber_zy = 0 ;
		Integer abilitySuccNumber_lx = 0 ;
		Integer abilitySuccNumber_zh = 0 ;
		Integer abilitySuccNumber_cz = 0 ;
		
		Integer abilityAllNumber_lijie = 0 ;
		Integer abilityAllNumber_fenxi = 0 ;
		Integer abilityAllNumber_biaoda = 0 ;
		Integer abilityAllNumber_shijian = 0 ;
		Integer abilityAllNumber_zy = 0 ;
		Integer abilityAllNumber_lx = 0 ;
		Integer abilityAllNumber_zh = 0 ;
		Integer abilityAllNumber_cz = 0 ;
		
		Integer mindSuccNumber_dx = 0 ;
		Integer mindSuccNumber_fs = 0 ;
		Integer mindSuccNumber_nx = 0 ;
		Integer mindSuccNumber_cx = 0 ;
		Integer mindSuccNumber_nb = 0 ;
		Integer mindSuccNumber_yz = 0 ;
		Integer mindSuccNumber_xx = 0 ;
		Integer mindSuccNumber_lx = 0 ;
		
		Integer mindAllNumber_dx = 0 ;
		Integer mindAllNumber_fs = 0 ;
		Integer mindAllNumber_nx = 0 ;
		Integer mindAllNumber_cx = 0 ;
		Integer mindAllNumber_nb = 0 ;
		Integer mindAllNumber_yz = 0 ;
		Integer mindAllNumber_xx = 0 ;
		Integer mindAllNumber_lx = 0 ;
		//当前巴菲特发布情况
		for(Iterator<BuffetStudyDetailInfo> it = currDetailList.iterator() ; it.hasNext();){
			BuffetStudyDetailInfo bsd = it.next();
			Integer buffetId = bsd.getBuffetQueInfo().getId();//巴菲特学习情况编号
			//根据巴菲特编号获取该巴菲特（能力）类型关系列表
			List<BuffetAbilityRelationInfo> barList = barm.listBarInfoBybqId(buffetId);
			for(Iterator<BuffetAbilityRelationInfo> it_a = barList.iterator() ; it_a.hasNext();){
				BuffetAbilityRelationInfo bar = it_a.next();
				String ablityName = bar.getBuffetAbilityTypeInfo().getAbility();
				if(ablityName.equals("理解能力")){
					if(bsd.getResult().equals(1)){//正确
						currAbilitySuccNumber_lijie += 1;
					}
					currAbilityAllNumber_lijie += 1;
				}else if(ablityName.equals("分析能力")){
					if(bsd.getResult().equals(1)){//正确
						currAbilitySuccNumber_fenxi += 1;
					}
					currAbilityAllNumber_fenxi += 1;
				}else if(ablityName.equals("表达能力")){
					if(bsd.getResult().equals(1)){//正确
						currAbilitySuccNumber_biaoda += 1;
					}
					currAbilityAllNumber_biaoda += 1;
				}else if(ablityName.equals("实践能力")){
					if(bsd.getResult().equals(1)){//正确
						currAbilitySuccNumber_shijian += 1;
					}
					currAbilityAllNumber_shijian += 1;
				}else if(ablityName.equals("质疑能力")){
					if(bsd.getResult().equals(1)){//正确
						currAbilitySuccNumber_zy += 1;
					}
					currAbilityAllNumber_zy += 1;
				}else if(ablityName.equals("联想能力")){
					if(bsd.getResult().equals(1)){//正确
						currAbilitySuccNumber_lx += 1;
					}
					currAbilityAllNumber_lx += 1;
				}else if(ablityName.equals("综合能力")){
					if(bsd.getResult().equals(1)){//正确
						currAbilitySuccNumber_zh += 1;
					}
					currAbilityAllNumber_zh += 1;
				}else if(ablityName.equals("创造能力")){
					if(bsd.getResult().equals(1)){//正确
						currAbilitySuccNumber_cz += 1;
					}
					currAbilityAllNumber_cz += 1;
				}
			}
			//根据巴菲特编号获取该巴菲特（思维）类型关系列表
			List<BuffetMindRelationInfo> bmrList = bmrm.listBmrInfoBybqId(buffetId);
			for(Iterator<BuffetMindRelationInfo> it_m = bmrList.iterator() ; it_m.hasNext();){
				BuffetMindRelationInfo bmr = it_m.next();
				String mindName = bmr.getBuffetMindTypeInfo().getMind();
				if(mindName.equals("定向思维")){
					if(bsd.getResult().equals(1)){//正确
						currMindSuccNumber_dx += 1;
					}
					currMindAllNumber_dx += 1;
				}else if(mindName.equals("发散思维")){
					if(bsd.getResult().equals(1)){//正确
						currMindSuccNumber_fs += 1;
					}
					currMindAllNumber_fs += 1;
				}else if(mindName.equals("逆向思维")){
					if(bsd.getResult().equals(1)){//正确
						currMindSuccNumber_nx += 1;
					}
					currMindAllNumber_nx += 1;
				}else if(mindName.equals("抽象思维")){
					if(bsd.getResult().equals(1)){//正确
						currMindSuccNumber_cx += 1;
					}
					currMindAllNumber_cx += 1;
				}else if(mindName.equals("类比思维")){
					if(bsd.getResult().equals(1)){//正确
						currMindSuccNumber_nb += 1;
					}
					currMindAllNumber_nb += 1;
				}else if(mindName.equals("移植思维")){
					if(bsd.getResult().equals(1)){//正确
						currMindSuccNumber_yz += 1;
					}
					currMindAllNumber_yz += 1;
				}else if(mindName.equals("形象思维")){
					if(bsd.getResult().equals(1)){//正确
						currMindSuccNumber_xx += 1;
					}
					currMindAllNumber_xx += 1;
				}else if(mindName.equals("联想思维")){
					if(bsd.getResult().equals(1)){//正确
						currMindSuccNumber_lx += 1;
					}
					currMindAllNumber_lx += 1;
				}
			}
		}
		//全部巴菲特发布记录情况
		for(Iterator<BuffetStudyDetailInfo> it_all = allDetailList.iterator() ; it_all.hasNext();){
			BuffetStudyDetailInfo bsd_a = it_all.next();
			Integer buffetId = bsd_a.getBuffetQueInfo().getId();//巴菲特学习情况编号
			//根据巴菲特编号获取该巴菲特（能力）类型关系列表
			List<BuffetAbilityRelationInfo> barList = barm.listBarInfoBybqId(buffetId);
			for(Iterator<BuffetAbilityRelationInfo> it_a = barList.iterator() ; it_a.hasNext();){
				BuffetAbilityRelationInfo bar = it_a.next();
				String ablityName = bar.getBuffetAbilityTypeInfo().getAbility();
				if(ablityName.equals("理解能力")){
					if(bsd_a.getResult().equals(1)){//正确
						abilitySuccNumber_lijie += 1;
					}
					abilityAllNumber_lijie += 1;
				}else if(ablityName.equals("分析能力")){
					if(bsd_a.getResult().equals(1)){//正确
						abilitySuccNumber_fenxi += 1;
					}
					abilityAllNumber_fenxi += 1;
				}else if(ablityName.equals("表达能力")){
					if(bsd_a.getResult().equals(1)){//正确
						abilitySuccNumber_biaoda += 1;
					}
					abilityAllNumber_biaoda += 1;
				}else if(ablityName.equals("实践能力")){
					if(bsd_a.getResult().equals(1)){//正确
						abilitySuccNumber_shijian += 1;
					}
					abilityAllNumber_shijian += 1;
				}else if(ablityName.equals("质疑能力")){
					if(bsd_a.getResult().equals(1)){//正确
						abilitySuccNumber_zy += 1;
					}
					abilityAllNumber_zy += 1;
				}else if(ablityName.equals("联想能力")){
					if(bsd_a.getResult().equals(1)){//正确
						abilitySuccNumber_lx += 1;
					}
					abilityAllNumber_lx += 1;
				}else if(ablityName.equals("综合能力")){
					if(bsd_a.getResult().equals(1)){//正确
						abilitySuccNumber_zh += 1;
					}
					abilityAllNumber_zh += 1;
				}else if(ablityName.equals("创造能力")){
					if(bsd_a.getResult().equals(1)){//正确
						abilitySuccNumber_cz += 1;
					}
					abilityAllNumber_cz += 1;
				}
			}
			//根据巴菲特编号获取该巴菲特（思维）类型关系列表
			List<BuffetMindRelationInfo> bmrList = bmrm.listBmrInfoBybqId(buffetId);
			for(Iterator<BuffetMindRelationInfo> it_m = bmrList.iterator() ; it_m.hasNext();){
				BuffetMindRelationInfo bmr = it_m.next();
				String mindName = bmr.getBuffetMindTypeInfo().getMind();
				if(mindName.equals("定向思维")){
					if(bsd_a.getResult().equals(1)){//正确
						mindSuccNumber_dx += 1;
					}
					mindAllNumber_dx += 1;
				}else if(mindName.equals("发散思维")){
					if(bsd_a.getResult().equals(1)){//正确
						mindSuccNumber_fs += 1;
					}
					mindAllNumber_fs += 1;
				}else if(mindName.equals("逆向思维")){
					if(bsd_a.getResult().equals(1)){//正确
						mindSuccNumber_nx += 1;
					}
					mindAllNumber_nx += 1;
				}else if(mindName.equals("抽象思维")){
					if(bsd_a.getResult().equals(1)){//正确
						mindSuccNumber_cx += 1;
					}
					mindAllNumber_cx += 1;
				}else if(mindName.equals("类比思维")){
					if(bsd_a.getResult().equals(1)){//正确
						mindSuccNumber_nb += 1;
					}
					mindAllNumber_nb += 1;
				}else if(mindName.equals("移植思维")){
					if(bsd_a.getResult().equals(1)){//正确
						mindSuccNumber_yz += 1;
					}
					mindAllNumber_yz += 1;
				}else if(mindName.equals("形象思维")){
					if(bsd_a.getResult().equals(1)){//正确
						mindSuccNumber_xx += 1;
					}
					mindAllNumber_xx += 1;
				}else if(mindName.equals("联想思维")){
					if(bsd_a.getResult().equals(1)){//正确
						mindSuccNumber_lx += 1;
					}
					mindAllNumber_lx += 1;
				}
			}
		}
		
		DecimalFormat df  = new DecimalFormat("######0.00");
		//当前巴菲特发布记录下8种能力正确率
		String currAbilitySuccRate_lj = "0";
		String currAbilitySuccRate_fx = "0";
		String currAbilitySuccRate_bd = "0";
		String currAbilitySuccRate_sj = "0";
		String currAbilitySuccRate_zy = "0";
		String currAbilitySuccRate_lx = "0";
		String currAbilitySuccRate_zh = "0";
		String currAbilitySuccRate_cz = "0";
		if(currAbilitySuccNumber_lijie > 0){
			currAbilitySuccRate_lj = df.format(((double)currAbilitySuccNumber_lijie / (double)currAbilityAllNumber_lijie) * 100);
		}
		if(currAbilitySuccNumber_biaoda > 0){
			currAbilitySuccRate_fx = df.format(((double)currAbilitySuccNumber_fenxi / (double)currAbilityAllNumber_fenxi) * 100);
		}
		if(currAbilitySuccNumber_biaoda > 0){
			currAbilitySuccRate_bd = df.format(((double)currAbilitySuccNumber_biaoda / (double)currAbilityAllNumber_biaoda) * 100);
		}
		if(currAbilitySuccNumber_shijian > 0){
			currAbilitySuccRate_sj = df.format(((double)currAbilitySuccNumber_shijian / (double)currAbilityAllNumber_shijian) * 100);
		} 
		if(currAbilitySuccNumber_zy > 0){
			currAbilitySuccRate_zy = df.format(((double)currAbilitySuccNumber_zy / (double)currAbilityAllNumber_zy) * 100);
		} 
		if(currAbilitySuccNumber_lx > 0){
			currAbilitySuccRate_lx = df.format(((double)currAbilitySuccNumber_lx / (double)currAbilityAllNumber_lx) * 100);
		} 
		if(currAbilitySuccNumber_zh > 0){
			currAbilitySuccRate_zh = df.format(((double)currAbilitySuccNumber_zh / (double)currAbilityAllNumber_zh) * 100);
		} 
		if(currAbilitySuccNumber_cz > 0){
			currAbilitySuccRate_cz = df.format(((double)currAbilitySuccNumber_cz / (double)currAbilityAllNumber_cz) * 100);
		} 
		//当前巴菲特发不记录下8种思维正确率
		String currMindSuccRate_dx = "0";
		String currMindSuccRate_fs = "0";
		String currMindSuccRate_nx = "0";
		String currMindSuccRate_cx = "0";
		String currMindSuccRate_nb = "0";
		String currMindSuccRate_yz = "0";
		String currMindSuccRate_xx = "0";
		String currMindSuccRate_lx = "0";
		if(currMindSuccNumber_dx > 0){
			currMindSuccRate_dx = df.format(((double)currMindSuccNumber_dx / (double)currMindAllNumber_dx) * 100);
		}
		if(currMindSuccNumber_fs > 0){
			currMindSuccRate_fs = df.format(((double)currMindSuccNumber_fs / (double)currMindAllNumber_fs) * 100);
		} 
		if(currMindSuccNumber_nx > 0){
			currMindSuccRate_nx = df.format(((double)currMindSuccNumber_nx / (double)currMindAllNumber_nx) * 100);
		}
		if(currMindSuccNumber_cx > 0){
			currMindSuccRate_cx = df.format(((double)currMindSuccNumber_cx / (double)currMindAllNumber_cx) * 100);
		}
		if(currMindSuccNumber_nb > 0){
			currMindSuccRate_nb = df.format(((double)currMindSuccNumber_nb / (double)currMindAllNumber_nb) * 100);
		} 
		if(currMindSuccNumber_yz > 0){
			currMindSuccRate_yz = df.format(((double)currMindSuccNumber_yz / (double)currMindAllNumber_yz) * 100);
		} 
		if(currMindSuccNumber_xx > 0){
			currMindSuccRate_xx = df.format(((double)currMindSuccNumber_xx / (double)currMindAllNumber_xx) * 100);
		} 
		if(currMindSuccNumber_lx > 0){
			currMindSuccRate_lx = df.format(((double)currMindSuccNumber_lx / (double)currMindAllNumber_lx) * 100);
		} 
		
		//所有巴菲特发布记录下8种能力正确率
		String abilitySuccRate_lj = "0";
		String abilitySuccRate_fx = "0";
		String abilitySuccRate_bd = "0";
		String abilitySuccRate_sj = "0";
		String abilitySuccRate_zy = "0";
		String abilitySuccRate_lx = "0";
		String abilitySuccRate_zh = "0";
		String abilitySuccRate_cz = "0";
		if(abilitySuccNumber_lijie > 0){
			abilitySuccRate_lj = df.format(((double)abilitySuccNumber_lijie / (double)abilityAllNumber_lijie) * 100);
		}
		if(abilitySuccNumber_fenxi > 0){
			abilitySuccRate_fx = df.format(((double)abilitySuccNumber_fenxi / (double)abilityAllNumber_fenxi) * 100);
		}
		if(abilitySuccNumber_biaoda > 0){
			abilitySuccRate_bd = df.format(((double)abilitySuccNumber_biaoda / (double)abilityAllNumber_biaoda) * 100);
		}
		if(abilitySuccNumber_shijian > 0){
			abilitySuccRate_sj = df.format(((double)abilitySuccNumber_shijian / (double)abilityAllNumber_shijian) * 100);
		}
		if(abilitySuccNumber_zy > 0){
			abilitySuccRate_zy = df.format(((double)abilitySuccNumber_zy / (double)abilityAllNumber_zy) * 100);
		} 
		if(abilitySuccNumber_lx > 0){
			abilitySuccRate_lx = df.format(((double)abilitySuccNumber_lx / (double)abilityAllNumber_lx) * 100);
		} 
		if(abilitySuccNumber_zh > 0){
			abilitySuccRate_zh = df.format(((double)abilitySuccNumber_zh / (double)abilityAllNumber_zh) * 100);
		} 
		if(abilitySuccNumber_cz > 0){
			abilitySuccRate_cz = df.format(((double)abilitySuccNumber_cz / (double)abilityAllNumber_cz) * 100);
		} 
		
		//所有巴菲特发不记录下8种思维正确率
		String mindSuccRate_dx = "0";
		String mindSuccRate_fs = "0";
		String mindSuccRate_nx = "0";
		String mindSuccRate_cx = "0";
		String mindSuccRate_nb = "0";
		String mindSuccRate_yz = "0";
		String mindSuccRate_xx = "0";
		String mindSuccRate_lx = "0";
		if(mindSuccNumber_dx > 0){
			mindSuccRate_dx = df.format(((double)mindSuccNumber_dx / (double)mindAllNumber_dx) * 100);
		}
		if(mindSuccNumber_fs > 0){
			mindSuccRate_fs = df.format(((double)mindSuccNumber_fs / (double)mindAllNumber_fs) * 100);
		} 
		if(mindSuccNumber_nx > 0){
			mindSuccRate_nx = df.format(((double)mindSuccNumber_nx / (double)mindAllNumber_nx) * 100);
		}
		if(mindSuccNumber_cx > 0){
			mindSuccRate_cx = df.format(((double)mindSuccNumber_cx / (double)mindAllNumber_cx) * 100);
		}
		if(mindSuccNumber_nb > 0){
			mindSuccRate_nb = df.format(((double)mindSuccNumber_nb / (double)mindAllNumber_nb) * 100);
		} 
		if(mindSuccNumber_yz > 0){
			mindSuccRate_yz = df.format(((double)mindSuccNumber_yz / (double)mindAllNumber_yz) * 100);
		} 
		if(mindSuccNumber_xx > 0){
			mindSuccRate_xx = df.format(((double)mindSuccNumber_xx / (double)mindAllNumber_xx) * 100);
		} 
		if(mindSuccNumber_lx > 0){
			mindSuccRate_lx = df.format(((double)mindSuccNumber_lx / (double)mindAllNumber_lx) * 100);
		} 

		BuffetStudyDetailInfoJson bsdJson = new BuffetStudyDetailInfoJson();
		//当前能力正确率
		bsdJson.setCurrAbilitySuccRate(currAbilitySuccRate_lj + "," + currAbilitySuccRate_fx + "," + currAbilitySuccRate_bd + "," + currAbilitySuccRate_sj + "," +
				currAbilitySuccRate_zy + "," + currAbilitySuccRate_lx + "," + currAbilitySuccRate_zh + "," + currAbilitySuccRate_cz);
		//当前思维正确率
		bsdJson.setCurrMindSuccRate(currMindSuccRate_dx + "," + currMindSuccRate_fs + "," + currMindSuccRate_nx + "," + currMindSuccRate_cx + "," +
				currMindSuccRate_nb + "," + currMindSuccRate_yz + "," + currMindSuccRate_xx + "," + currMindSuccRate_lx);
		//当前能力总数量
		bsdJson.setCurrAbilityNumber(currAbilityAllNumber_lijie + "," + currAbilityAllNumber_fenxi + "," + currAbilityAllNumber_biaoda + "," + currAbilityAllNumber_shijian + "," +
				currAbilityAllNumber_zy + "," + currAbilityAllNumber_lx + "," + currAbilityAllNumber_zh + "," + currAbilityAllNumber_cz);
		//当前思维总数量
		bsdJson.setCurrMindNumber(currMindAllNumber_dx + "," + currMindAllNumber_fs + "," + currMindAllNumber_nx + "," + currMindAllNumber_cx + "," +
				currMindAllNumber_nb + "," + currMindAllNumber_yz + "," + currMindAllNumber_xx + "," + currMindAllNumber_lx);
		
		//所有能力正确率
		bsdJson.setAllAbilitySuccRate(abilitySuccRate_lj + "," + abilitySuccRate_fx + "," + abilitySuccRate_bd + "," + abilitySuccRate_sj + "," +
				abilitySuccRate_zy + "," + abilitySuccRate_lx + "," + abilitySuccRate_zh + "," + abilitySuccRate_cz);
		//所有思维正确率
		bsdJson.setAllMindSuccRate(mindSuccRate_dx + "," + mindSuccRate_fs + "," + mindSuccRate_nx + "," + mindSuccRate_cx + "," +
				mindSuccRate_nb + "," + mindSuccRate_yz + "," + mindSuccRate_xx + "," + mindSuccRate_lx);
		//所有能力总数量
		bsdJson.setAllAbilityNumber(abilityAllNumber_lijie + "," + abilityAllNumber_fenxi + "," + abilityAllNumber_biaoda + "," + abilityAllNumber_shijian + "," +
				abilityAllNumber_zy + "," + abilityAllNumber_lx + "," + abilityAllNumber_zh + "," + abilityAllNumber_cz);
		//所有思维总数量
		bsdJson.setAllMindNumber(mindAllNumber_dx + "," + mindAllNumber_fs + "," + mindAllNumber_nx + "," + mindAllNumber_cx + "," +
				mindAllNumber_nb + "," + mindAllNumber_yz + "," + mindAllNumber_xx + "," + mindAllNumber_lx);
		result.add(bsdJson);
		return result;
	}
}