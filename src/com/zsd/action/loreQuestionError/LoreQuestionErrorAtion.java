/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.loreQuestionError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zsd.factory.AppFactory;
import com.zsd.module.LexInfo;
import com.zsd.module.LoreQuestion;
import com.zsd.module.LoreQuestionErrorInfo;
import com.zsd.module.LoreQuestionSubInfo;
import com.zsd.page.PageConst;
import com.zsd.service.LexInfoManager;
import com.zsd.service.LoreQuestionErrorManager;
import com.zsd.service.LoreQuestionManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 05-23-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoreQuestionErrorAtion extends DispatchAction {
	
	/**
	 * 导向知识点错题修改页面(默认最近30天)
	 * @author wm
	 * @date 2019-5-23 下午05:54:42
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goLqePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String eDate = CurrentTime.getStringDate();
		String sDate = CurrentTime.getFinalDate(eDate,-30);
		request.setAttribute("sDate", sDate);
		request.setAttribute("eDate", eDate);
		return mapping.findForward("lqePage");
	}
	
	/**
	 * 按照条件分页获取错题记录列表
	 * @author wm
	 * @date 2019-5-23 下午05:55:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getPageLqeData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreQuestionErrorManager lqem = (LoreQuestionErrorManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_QUESTION_ERROR_INFO);
		String errorType = CommonTools.getFinalStr("errorType", request);
		String sDate = CommonTools.getFinalStr("sDate", request);
		String eDate = CommonTools.getFinalStr("eDate", request);
		Integer checkStatus = CommonTools.getFinalInteger("checkStatus", request);//-1:全部,0:未修改,1:已修改
		String opt = CommonTools.getFinalStr("opt", request);//类型(stu,admin)
		Integer addUserId = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "暂无记录";
		if(opt.equals("stu")){
			addUserId = CommonTools.getLoginUserId(request);
		}
		Integer count = lqem.getCountByOptions(addUserId, sDate, eDate, errorType,checkStatus);
		if(count > 0){
			msg = "success";
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<LoreQuestionErrorInfo> lqeList = lqem.listPageInfoByOptions(addUserId, sDate, eDate, errorType, checkStatus, pageNo, pageSize);
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<LoreQuestionErrorInfo> it = lqeList.iterator() ; it.hasNext();){
				LoreQuestionErrorInfo lqe = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("lqeId", lqe.getId());
				map_d.put("loreName", lqe.getLoreQuestion().getLoreInfo().getLoreName());
				map_d.put("lqTitle", lqe.getLoreQuestion().getQueTitle());
				map_d.put("content", lqe.getContent());
				String errorType_text = lqe.getErrorType();
				if(errorType_text.equals("noPicError")){
					errorType_text = "图片错误";
				}else if(errorType_text.equals("contentError")){
					errorType_text = "内容错误";
				}else if(errorType_text.equals("anserError")){
					errorType_text = "答案错误";
				}else if(errorType_text.equals("otherError")){
					errorType_text = "其他错误";
				}
				map_d.put("errorType", errorType_text);
				map_d.put("addate", lqe.getAddDate());
				map_d.put("addUserName", lqe.getUser().getRealName());
				map_d.put("updateStatus", lqe.getCheckStatus().equals(0) ? "未修改" : "已修改");
				map_d.put("updateUserName", lqe.getOperateUserName());
				map_d.put("updateDate", lqe.getOperateDate());
				map_d.put("remark", lqe.getRemark());
				list_d.add(map_d);
			}
			map.put("data", list_d);
			map.put("count", count);
			map.put("code", 0);
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取错题详情
	 * @author wm
	 * @date 2019-5-23 下午06:39:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getLqeDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreQuestionErrorManager lqem = (LoreQuestionErrorManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_QUESTION_ERROR_INFO);
		LoreQuestionManager lqm = (LoreQuestionManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_QUESTION_INFO);
		LexInfoManager lexm = (LexInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LEX_INFO);
		Integer lqeId = CommonTools.getFinalInteger("lqeId", request);
		LoreQuestionErrorInfo lqe = lqem.getEntityById(lqeId);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		List<Object> list_d = new ArrayList<Object>();
		if(lqe != null){
			msg = "success";
			LoreQuestion lq = lqe.getLoreQuestion();
			Map<String,Object> map_d = new HashMap<String,Object>();
			map_d.put("lqeId", lqeId);
			map_d.put("lqId", lq.getId());
			map_d.put("lqTitle", lq.getQueTitle());
			map_d.put("loreName", lq.getLoreInfo().getLoreName());
			String queType = lq.getQueType();
			map_d.put("queType", queType);
			map_d.put("queType2", lq.getQueType2());
			Integer lexId = lq.getLexId();
			map_d.put("lexId", lexId);
			if(lexId > 0){
				LexInfo lex = lexm.getEntityById(lexId);
				if(lex != null){
					map_d.put("lexTitle", lex.getLexTitle());
					map_d.put("lexContent", lex.getLexContent());
				}
			}
			map_d.put("lqSub", lq.getQueSub());
			String answerA = lq.getA();
			String answerB = lq.getB();
			String answerC = lq.getC();
			String answerD = lq.getD();
			String answerE = lq.getE();
			String answerF = lq.getF();
			map_d.put("anserA", answerA);
			map_d.put("anserB", answerB);
			map_d.put("anserC", answerC);
			map_d.put("anserD", answerD);
			map_d.put("anserE", answerE);
			map_d.put("anserF", answerF);
			map_d.put("lqAnswer", lq.getQueAnswer());
			Integer queOptNum = 0;//问题选项
			Integer answerNum = 0;//答案数量
			if(queType.equals("单选题") || queType.equals("多选题") || queType.equals("填空选择题")){
				//有最大选项
				if(!answerA.equals("")){
					queOptNum++;
				}
				if(!answerB.equals("")){
					queOptNum++;
				}
				if(!answerC.equals("")){
					queOptNum++;
				}
				if(!answerD.equals("")){
					queOptNum++;
				}
				if(!answerE.equals("")){
					queOptNum++;
				}
				if(!answerF.equals("")){
					queOptNum++;
				}
				map_d.put("queOptNum", queOptNum);
				if(queType.equals("填空选择题")){
					//有最大选项和填空数量
					answerNum = lq.getQueAnswer().split(",").length;//多个答案用,隔开
					map_d.put("answerNum", answerNum);
				}
			}else if(queType.equals("填空题")){
				answerNum = lq.getQueAnswer().split(",").length;//多个答案用,隔开
				map_d.put("answerNum", answerNum);
			}
			map_d.put("lqResolution", lq.getQueResolution());
			Integer queTipId = lq.getQueTips();
			map_d.put("queTipId", queTipId);
			List<LoreQuestionSubInfo> lqsList = lqm.listInfoByLoreId(lq.getLoreInfo().getId());
			List<Object> list_d_1 = new ArrayList<Object>();
			if(lqsList.size() > 0){
				if(queTipId > 0){//提示为知识清单或者点拨指导的一内容
					for(Iterator<LoreQuestionSubInfo> it = lqsList.iterator() ; it.hasNext();){
						LoreQuestionSubInfo lqs = it.next();
						Map<String,Object> map_d_1 = new HashMap<String,Object>();
						map_d_1.put("lqsId", lqs.getId());
						map_d_1.put("lqsTitle", lqs.getLqsTitle());
						map_d_1.put("lqsContent", lqs.getLqsContent());
						map_d_1.put("lqsType", lqs.getLoreTypeName());
						if(queTipId.equals(lqs.getId())){
							map_d_1.put("selStatus", true);
						}else{
							map_d_1.put("selStatus", false);
						}
						list_d_1.add(map_d_1);
					}
				}else{
					for(Iterator<LoreQuestionSubInfo> it = lqsList.iterator() ; it.hasNext();){
						LoreQuestionSubInfo lqs = it.next();
						Map<String,Object> map_d_1 = new HashMap<String,Object>();
						map_d_1.put("lqsId", lqs.getId());
						map_d_1.put("lqsTitle", lqs.getLqsTitle());
						map_d_1.put("lqsContent", lqs.getLqsContent());
						map_d_1.put("lqsType", lqs.getLoreTypeName());
						map_d_1.put("selStatus", false);
						list_d_1.add(map_d_1);
					}
				}
			}
			map_d.put("tipsList", list_d_1);
			map_d.put("lqType", lq.getLoreTypeName());
			list_d.add(map_d);
			map.put("listIfo", list_d);
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}