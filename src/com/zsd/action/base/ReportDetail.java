package com.zsd.action.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zsd.module.StudyAllTjInfo;
import com.zsd.module.StudyStuTjInfo;



//将已答题和数据库中的原题中未答的题组合在一起
public class ReportDetail {
	//了解
	private Integer score_liaojie; //分数
	private Integer number_liaojie;//答题数
	private Integer success_scale_liaojie;//正确率
	private Integer all_success_scale_liaojie; //平均正确率
	private String step_liaojie; //级别（差，一般。。。。）
	private String explain_liaojie; //解释说明
	//理解
	private Integer score_lijie;
	private Integer number_lijie;
	private Integer success_scale_lijie;
	private Integer all_success_scale_lijie;
	private String step_lijie;
	private String explain_lijie;
	//应用
	private Integer score_yy;
	private Integer number_yy;
	private Integer success_scale_yy;
	private Integer all_success_scale_yy;
	private String step_yy;
	private String explain_yy;
	
	
	public Integer getScore_liaojie() {
		return score_liaojie;
	}


	public void setScore_liaojie(Integer score_liaojie) {
		this.score_liaojie = score_liaojie;
	}


	public Integer getNumber_liaojie() {
		return number_liaojie;
	}


	public void setNumber_liaojie(Integer number_liaojie) {
		this.number_liaojie = number_liaojie;
	}


	public Integer getSuccess_scale_liaojie() {
		return success_scale_liaojie;
	}


	public void setSuccess_scale_liaojie(Integer success_scale_liaojie) {
		this.success_scale_liaojie = success_scale_liaojie;
	}


	public Integer getAll_success_scale_liaojie() {
		return all_success_scale_liaojie;
	}


	public void setAll_success_scale_liaojie(Integer all_success_scale_liaojie) {
		this.all_success_scale_liaojie = all_success_scale_liaojie;
	}


	public String getExplain_liaojie() {
		return explain_liaojie;
	}


	public void setExplain_liaojie(String explain_liaojie) {
		this.explain_liaojie = explain_liaojie;
	}


	public Integer getScore_lijie() {
		return score_lijie;
	}


	public void setScore_lijie(Integer score_lijie) {
		this.score_lijie = score_lijie;
	}


	public Integer getNumber_lijie() {
		return number_lijie;
	}


	public void setNumber_lijie(Integer number_lijie) {
		this.number_lijie = number_lijie;
	}


	public Integer getSuccess_scale_lijie() {
		return success_scale_lijie;
	}


	public void setSuccess_scale_lijie(Integer success_scale_lijie) {
		this.success_scale_lijie = success_scale_lijie;
	}


	public Integer getAll_success_scale_lijie() {
		return all_success_scale_lijie;
	}


	public void setAll_success_scale_lijie(Integer all_success_scale_lijie) {
		this.all_success_scale_lijie = all_success_scale_lijie;
	}


	public String getExplain_lijie() {
		return explain_lijie;
	}


	public void setExplain_lijie(String explain_lijie) {
		this.explain_lijie = explain_lijie;
	}


	public Integer getScore_yy() {
		return score_yy;
	}


	public void setScore_yy(Integer score_yy) {
		this.score_yy = score_yy;
	}


	public Integer getNumber_yy() {
		return number_yy;
	}


	public void setNumber_yy(Integer number_yy) {
		this.number_yy = number_yy;
	}


	public Integer getSuccess_scale_yy() {
		return success_scale_yy;
	}


	public void setSuccess_scale_yy(Integer success_scale_yy) {
		this.success_scale_yy = success_scale_yy;
	}


	public Integer getAll_success_scale_yy() {
		return all_success_scale_yy;
	}


	public void setAll_success_scale_yy(Integer all_success_scale_yy) {
		this.all_success_scale_yy = all_success_scale_yy;
	}


	public String getExplain_yy() {
		return explain_yy;
	}


	public void setExplain_yy(String explain_yy) {
		this.explain_yy = explain_yy;
	}


	public String getStep_liaojie() {
		return step_liaojie;
	}


	public void setStep_liaojie(String step_liaojie) {
		this.step_liaojie = step_liaojie;
	}


	public String getStep_lijie() {
		return step_lijie;
	}


	public void setStep_lijie(String step_lijie) {
		this.step_lijie = step_lijie;
	}


	public String getStep_yy() {
		return step_yy;
	}


	public void setStep_yy(String step_yy) {
		this.step_yy = step_yy;
	}
	
	public List<ReportDetail> getNLReport(List<StudyStuTjInfo> sstList,List<StudyAllTjInfo> satList){
		List<ReportDetail> result = new ArrayList<ReportDetail>();
		ReportDetail sdJson = new ReportDetail(); 
		//了解
		Integer succ_number_liaojie_new = 0;//了解正确数
		Integer score_liaojie_new = 0;//了解分数
		Integer number_liaojie_new = 0;//了解做题数
		String succ_stu_liaojie_rate = "0";//了解正确率

		Integer all_number_liaojie_new = 0;//了解做题数(全平台)
		Integer all_success_number_liaojie_new = 0;//了解正确数(全平台)
		String succ_all_liaojie_rate = "0";////了解正确率(全平台)
		
		String step_liaojie_new = "";//了解级别
		String explain_liaojie_new = "";//了解解释
		
		//理解
		Integer succ_number_lijie_new = 0;//理解正确数
		Integer score_lijie_new = 0;//理解分数
		Integer number_lijie_new = 0;//理解做题数
		String succ_stu_lijie_rate = "0";//理解正确率
		
		
		Integer all_number_lijie_new = 0;//理解做题数(全平台)
		Integer all_success_number_lijie_new = 0;//理解正确数(全平台)
		String succ_all_lijie_rate = "0";//理解正确率(全平台)
		
		String step_lijie_new = "";//理解级别
		String explain_lijie_new = "";//理解解释
		
		//应用
		Integer succ_number_yy_new = 0;//应用正确数
		Integer score_yy_new = 0;//应用分数
		Integer number_yy_new = 0;//应用做题数
		String succ_stu_yy_rate = "0";//应用正确率

		Integer all_number_yy_new = 0;//应用做题数(全平台)
		Integer all_success_number_yy_new = 0;//应用正确数(全平台)
		String succ_all_yy_rate = "0";//应用正确率(全平台)
		
		String step_yy_new = "";//应用级别
		String explain_yy_new = "";//应用解释
		
		//全平台
		for(Iterator<StudyAllTjInfo> it = satList.iterator() ; it.hasNext();){
			StudyAllTjInfo sat = it.next();
			all_number_liaojie_new += sat.getLiaojieTotalNum();
			all_number_lijie_new += sat.getLijieTotalNum();
			all_number_yy_new += sat.getYyTotalNum();
			
			all_success_number_liaojie_new += sat.getLiaojieSuccNum();
			all_success_number_lijie_new += sat.getLijieSuccNum();
			all_success_number_yy_new += sat.getYySuccNum();
		}

		if(all_number_liaojie_new > 0){
			succ_all_liaojie_rate = String.format("%.2f", (double)all_success_number_liaojie_new / (double)all_number_liaojie_new);
		}
		if(all_number_lijie_new > 0){
			succ_all_lijie_rate = String.format("%.2f", (double)all_success_number_lijie_new / (double)all_number_lijie_new);
		}
		if(all_number_yy_new > 0){
			succ_all_yy_rate = String.format("%.2f", (double)all_success_number_yy_new / (double)all_number_yy_new);
		}
		
		
		//学生个人
		for(Iterator<StudyStuTjInfo> it = sstList.iterator() ; it.hasNext();){
			StudyStuTjInfo sst = it.next();
			number_liaojie_new += sst.getLiaojieTotalNum();
			number_lijie_new += sst.getLijieTotalNum();
			number_yy_new += sst.getYyTotalNum();
			
			succ_number_liaojie_new += sst.getLiaojieSuccNum();
			succ_number_lijie_new += sst.getLijieSuccNum();
			succ_number_yy_new += sst.getYySuccNum();
		}
		
		if(number_liaojie_new > 0){
			succ_stu_liaojie_rate = String.format("%.2f", (double)succ_number_liaojie_new / (double)number_liaojie_new);
		}
		if(number_lijie_new > 0){
			succ_stu_lijie_rate = String.format("%.2f", (double)succ_number_lijie_new / (double)number_lijie_new);
		}
		if(number_yy_new > 0){
			succ_stu_yy_rate = String.format("%.2f", (double)succ_number_yy_new / (double)number_yy_new);
		}
		score_liaojie_new = (int) (Double.parseDouble(succ_stu_liaojie_rate) * 100);//了解分数
		score_lijie_new = (int) (Double.parseDouble(succ_stu_lijie_rate) * 100);//理解分数
		score_yy_new = (int) (Double.parseDouble(succ_stu_yy_rate) * 100);//应用分数
		
		if(score_liaojie_new < 50){
			explain_liaojie_new = "识记能力不太理想，解题正确率低于５０％，建议熟背知识点的“知识清单”！";
			step_liaojie_new = "弱项";
		}else if(score_liaojie_new >= 50 && score_liaojie_new <= 80){
			explain_liaojie_new = "解题正确率在５０％～８０％，建议再巩固一下知识点的“知识清单”！";
			step_liaojie_new = "一般";
		}else if(score_liaojie_new > 80){
			explain_liaojie_new = "解题正确率高于８０％，你很棒！";
			step_liaojie_new = "强项";
		}
		
		if(score_lijie_new < 50){
			explain_lijie_new = "理解能力不太理想，解题正确率低于５０％，建议看看、理解一下知识点的“点拨指导”！";
			step_lijie_new = "弱项";
		}else if(score_lijie_new >= 50 && score_lijie_new <=80){
			explain_lijie_new = "解题正确率在５０％～８０％，建议再巩固一下知识点的“点拨指导”！";
			step_lijie_new = "一般";
		}else if(score_lijie_new > 80){
			explain_lijie_new = "解题正确率高于８０％，你很棒！";
			step_lijie_new = "强项";
		}
		
		if(score_yy_new < 50){
			explain_yy_new = "实际应用能力不太理想，解题正确率低于50%，建议学习一下知识点的“解题示范”！";
			step_yy_new = "弱项";
		}else if(score_yy_new >= 50 && score_yy_new <=80){
			explain_yy_new = "解题正确率在５０％～８０％，建议再巩固一下知识点的“解题示范”！";
			step_yy_new = "一般";
		}else if(score_yy_new > 80){
			explain_yy_new = "解题正确率高于８０％，你很棒！";
			step_yy_new = "强项";
		}
		sdJson.setScore_liaojie(score_liaojie_new);
		sdJson.setNumber_liaojie(number_liaojie_new);
		sdJson.setSuccess_scale_liaojie(score_liaojie_new);
		sdJson.setAll_success_scale_liaojie((int) (Double.parseDouble(succ_all_liaojie_rate) * 100));
		sdJson.setStep_liaojie(step_liaojie_new);
		sdJson.setExplain_liaojie(explain_liaojie_new);
		
		sdJson.setScore_lijie(score_lijie_new);
		sdJson.setNumber_lijie(number_lijie_new);
		sdJson.setSuccess_scale_lijie(score_lijie_new);
		sdJson.setAll_success_scale_lijie((int) (Double.parseDouble(succ_all_lijie_rate) * 100));
		sdJson.setStep_lijie(step_lijie_new);
		sdJson.setExplain_lijie(explain_lijie_new);
		
		sdJson.setScore_yy(score_yy_new);
		sdJson.setNumber_yy(number_yy_new);
		sdJson.setSuccess_scale_yy(score_yy_new);
		sdJson.setAll_success_scale_yy((int) (Double.parseDouble(succ_all_yy_rate) * 100));
		sdJson.setStep_yy(step_yy_new);
		sdJson.setExplain_yy(explain_yy_new);
		result.add(sdJson);
		return result;
	}
}
	