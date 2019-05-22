package com.zsd.module.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zsd.factory.AppFactory;
import com.zsd.module.BuffetLoreRelateInfo;
import com.zsd.module.LoreInfo;
import com.zsd.module.LoreRelateInfo;
import com.zsd.service.BuffetLoreRelateInfoManager;
import com.zsd.service.BuffetQueInfoManager;
import com.zsd.service.LoreRelateManager;
import com.zsd.util.Constants;

@SuppressWarnings({"unchecked" ,"rawtypes"})
public class LoreBuffetTreeMenuJson {
	public String loreIdStr1 = "";
	public String path = "";
	boolean existFlag = false;
	boolean existFlag1 = false;
	public List loreList = new ArrayList();
	public Integer num = 0;
	public Integer loreStep = 0;//多少级知识点
	public Integer loreNum = 0;//多少个知识点
	
	/**
	 * 知识树实体封装成list
	 * @author wm
	 * @date 2019-5-22 上午09:22:13
	 * @param ltMenu
	 * @return
	 * @throws Exception
	 */
	private List<LoreTreeMenu> getLoreTreeMenuList(LoreTreeMenu ltMenu) throws Exception{
		List<LoreTreeMenu> result = new ArrayList<LoreTreeMenu>();
		result.add(ltMenu);
		return result;
	}
	
	/**
	 * 判断是否存在该知识点
	 * @author wm
	 * @date 2019-5-22 上午09:19:16
	 * @param loreIdList
	 * @param currentLoreId
	 * @return
	 */
	private boolean checkExistLore(List loreIdList,Integer currentLoreId){
		boolean flag = false;
		for(int i = 0 ; i < loreIdList.size();i++){
			if(loreIdList.get(i).equals(currentLoreId)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 学习时用
	 * @author wm
	 * @date 2019-5-22 上午09:56:41
	 * @param t
	 * @param n
	 * @param recursive
	 * @param studyLogId
	 * @return
	 * @throws Exception
	 */
	private MyTreeNode tree1(LoreTreeMenu t, List<LoreTreeMenu> n, boolean recursive,Integer studyLogId) throws Exception {
	    MyTreeNode node = new MyTreeNode(); 
	    node.setId(t.getId());
	    if(this.existFlag){
	    	node.setText("<font color=red>"+t.getName()+"</font>");
	    	node.setRepeatFlag(true);
	    }else{
	    	node.setText(t.getName());
	    	if(this.loreList.size() == 0){
	    		this.loreList.add(this.num++,t.getId());
	    	}
	    	if(studyLogId.equals(0)){
	    		node.setZdxzdFlag(-1);
	    		node.setStudyFlag(-1);
	    		node.setZczdFlag(-1);
	    		node.setStudyTimes(0);
	    		node.setZczdTimes(0);
	    	}else{
	    		//先去掉诊断结果数据
//	    		RelationZdResultManager rzrm = (RelationZdResultManager)AppFactory.instance(null).getApp(Constants.WEB_RELATION_ZD_RESULT);
//	    		List<RelationZdResultVO> rzrList = rzrm.listInfoByLogAndLoreId(studyLogId, node.getId());
//	    		if(rzrList.size() > 0){
//	    			RelationZdResultVO rzrVO = rzrList.get(0);
//	    			node.setZdxzdFlag(rzrVO.getZdxzdFlag());
//	    			node.setStudyFlag(rzrVO.getStudyFlag());
//		    		node.setZczdFlag(rzrVO.getZczdFlag());
//		    		node.setStudyTimes(rzrVO.getStudyTimes());
//		    		node.setZczdTimes(rzrVO.getZczdTimes());
//	    		}else{
	    			node.setZdxzdFlag(-1);
		    		node.setStudyFlag(-1);
		    		node.setZczdFlag(-1);
		    		node.setStudyTimes(0);
		    		node.setZczdTimes(0);
//	    		}
	    	}
	    }
    	List<MyTreeNode> children = new ArrayList<MyTreeNode>();
    	LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
    	if(n != null){
    		for(Iterator<LoreTreeMenu> it = n.iterator() ; it.hasNext();){
		    	LoreTreeMenu ltMenu = it.next();
		    	Integer loreId = ltMenu.getId();
		    	List<LoreRelateInfo> lrList_new = new ArrayList<LoreRelateInfo>();
		    	if(this.checkExistLore(this.loreList, loreId) == false){//不存在相同节点
		    		this.loreList.add(this.num++,loreId);
		    		lrList_new = lrm.listRelateInfoByOpt(loreId, 0, -1, "desc");//找下一级子节点
		    		this.existFlag = false;
	    		}else{//存在相同节点(直接终止查询，并让下一级子节点为空)
	    			this.existFlag = true;
	    		}
		    	List<LoreTreeMenu> menuList = new ArrayList<LoreTreeMenu>();
		    	if(lrList_new.size() > 0){
		    		menuList = this.getTreeMenuList(lrList_new);
		    	}else{
		    		menuList = this.getLoreTreeMenuList(ltMenu);
		    	}
		    	if (menuList != null && menuList.size() > 0) {
		    		
		    		List<LoreTreeMenu> nextMenuList = menuList.get(0).getMenus();
		    		node.setState("open");
		    		if (recursive) {// 递归查询子节点
		    			List<LoreTreeMenu> l = new ArrayList<LoreTreeMenu>(menuList);
			            for (LoreTreeMenu r : l) {
			                MyTreeNode tn = tree1(r, nextMenuList, true,studyLogId);
			                children.add(tn);
			            }
			            node.setChildren(children);
		    		}  
			    }
		    }
	    }
	    return node;
	}
	
	/**
	 * 后台获取自助餐关联知识点时用
	 * @author wm
	 * @date 2019-5-22 上午09:58:05
	 * @param t
	 * @param n
	 * @param recursive
	 * @return
	 * @throws Exception
	 */
	private MySimpleTreeNode tree2(LoreTreeMenu t, List<LoreTreeMenu> n, boolean recursive) throws Exception {
		MySimpleTreeNode node = new MySimpleTreeNode(); 
	    node.setId(t.getId());
	    node.setText(t.getName());
    	if(this.loreList.size() == 0){
    		this.loreList.add(this.num++,t.getId());
    	}
    	List<MySimpleTreeNode> children = new ArrayList<MySimpleTreeNode>();
    	LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
    	if(n != null){
    		for(Iterator<LoreTreeMenu> it = n.iterator() ; it.hasNext();){
		    	LoreTreeMenu ltMenu = it.next();
		    	Integer loreId = ltMenu.getId();
		    	List<LoreRelateInfo> lrList_new = new ArrayList<LoreRelateInfo>();
		    	if(this.checkExistLore(this.loreList, loreId) == false){//不存在相同节点
		    		this.loreList.add(this.num++,loreId);
		    		lrList_new = lrm.listRelateInfoByOpt(loreId, 0, -1, "desc");//找下一级子节点
		    		this.existFlag = false;
	    		}else{//存在相同节点(直接终止查询，并让下一级子节点为空)
	    			this.existFlag = true;
	    		}
		    	List<LoreTreeMenu> menuList = new ArrayList<LoreTreeMenu>();
		    	if(lrList_new.size() > 0){
		    		menuList = this.getTreeMenuList(lrList_new);
		    	}else{
		    		menuList = this.getLoreTreeMenuList(ltMenu);
		    	}
		    	if (menuList != null && menuList.size() > 0) {
		    		
		    		List<LoreTreeMenu> nextMenuList = menuList.get(0).getMenus();
		    		if (recursive) {// 递归查询子节点
		    			List<LoreTreeMenu> l = new ArrayList<LoreTreeMenu>(menuList);
			            for (LoreTreeMenu r : l) {
			            	MySimpleTreeNode tn = tree2(r, nextMenuList, true);
			                children.add(tn);
			            }
			            node.setChildren(children);
		    		}  
			    }
		    }
	    }
	    return node;
	}
	
	/**
	 * 获取有巴菲特关联知识树节点
	 * @author wm
	 * @date 2019-5-22 上午09:14:01
	 * @param blrList
	 * @param buffetId
	 * @return
	 * @throws Exception
	 */
	private List<LoreTreeMenu> getBuffetTreeMenuList(List<BuffetLoreRelateInfo> blrList,Integer buffetId) throws Exception{
		LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		List<LoreTreeMenu> secondResult = new ArrayList<LoreTreeMenu>();
		List<LoreTreeMenu> result = new ArrayList<LoreTreeMenu>();
		LoreTreeMenu ltMenu_first = new LoreTreeMenu();
		ltMenu_first.setId(buffetId);
		String buffetName = bm.getEntityById(buffetId).getTitle();
		ltMenu_first.setName(buffetName);
		if(blrList.size() > 0){
			for(Iterator<BuffetLoreRelateInfo> it = blrList.iterator() ; it.hasNext();){
				LoreTreeMenu ltMenu = new LoreTreeMenu();
				BuffetLoreRelateInfo blr = it.next();
				Integer relateLoreId = blr.getLoreInfoByLoreId().getId();
				String relateLoreName = blr.getLoreInfoByLoreId().getLoreName();
				ltMenu.setId(relateLoreId);
				ltMenu.setName(relateLoreName);
				ltMenu.setMenus(this.getSubTreeMenuList(lrm.listRelateInfoByOpt(relateLoreId, 0, -1, "desc")));	
				secondResult.add(ltMenu);
			}
			ltMenu_first.setMenus(secondResult);
		}else{
			ltMenu_first.setMenus(null);
		}
		result.add(ltMenu_first);
		return result;
	}
	
	/**
	 * 获取树形菜单list
	 * @author wm
	 * @date 2019-5-22 上午09:23:57
	 * @param lrList
	 * @return
	 * @throws Exception
	 */
	public List<LoreTreeMenu> getTreeMenuList(List<LoreRelateInfo> lrList) throws Exception{
		List<LoreTreeMenu> result = new ArrayList<LoreTreeMenu>();
		LoreTreeMenu ltMenu = new LoreTreeMenu();
		if(lrList.size() > 0){
			LoreInfo first_lore = lrList.get(0).getLoreInfo();
			ltMenu.setId(first_lore.getId());
			ltMenu.setName(first_lore.getLoreName());
			ltMenu.setMenus(this.getSubTreeMenuList(lrList));
			result.add(ltMenu);
		}	
		return result;
	}
	
	/**
	 * 获取子树节点
	 * @author wm
	 * @date 2019-5-22 上午09:14:48
	 * @param lrList
	 * @return
	 * @throws Exception
	 */
	private List<LoreTreeMenu> getSubTreeMenuList(List<LoreRelateInfo> lrList)throws Exception{
		List<LoreTreeMenu> leMenu_sub = new ArrayList<LoreTreeMenu>();
		for(Iterator<LoreRelateInfo> it = lrList.iterator();it.hasNext();){
			LoreRelateInfo lr = it.next();
			//获取2级目录
			LoreInfo rootLore = lr.getRootLoreInfo();
			LoreTreeMenu ltMenu1 = new LoreTreeMenu();
			ltMenu1.setId(rootLore.getId());
			ltMenu1.setName(rootLore.getLoreName());
			
			leMenu_sub.add(ltMenu1);
		}
		return leMenu_sub;
	}
	
	/**
	 * 显示指定巴菲特题下的知识树
	 * @author wm
	 * @date 2019-5-22 上午08:57:08
	 * @param buffetId 自助餐编号
	 * @param studyLogId 学习记录编号（学习记录查看时用）
	 * @return
	 * @throws Exception
	 */
	public List<MySimpleTreeNode> showBuffetTree_1(Integer buffetId) throws Exception {
		BuffetLoreRelateInfoManager blrm = (BuffetLoreRelateInfoManager)AppFactory.instance(null).getApp(Constants.WEB_BUFFET_LORE_RELATE_INFO);
		List<BuffetLoreRelateInfo> blrList = blrm.listInfoByOpt(buffetId, 0);
		List<LoreTreeMenu> l = this.getBuffetTreeMenuList(blrList,buffetId);
	    List<MySimpleTreeNode> tree = new ArrayList<MySimpleTreeNode>();
	    this.loreList = new ArrayList();
	    this.num = 0;
	    for (LoreTreeMenu t : l) {
	        tree.add(tree2(t,t.getMenus(),true));
	    }
	    return tree;
	}
}
