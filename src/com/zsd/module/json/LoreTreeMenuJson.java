package com.zsd.module.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zsd.factory.AppFactory;
import com.zsd.module.BuffetQueInfo;
import com.zsd.module.Chapter;
import com.zsd.module.LoreInfo;
import com.zsd.module.LoreRelateInfo;
import com.zsd.module.RelationZdResult;
import com.zsd.module.json.MyTreeNode;
import com.zsd.service.BuffetQueInfoManager;
import com.zsd.service.ChapterManager;
import com.zsd.service.LoreInfoManager;
import com.zsd.service.LoreRelateManager;
import com.zsd.service.RelationZdResultManager;
import com.zsd.util.Constants;

@SuppressWarnings("unchecked")
public class LoreTreeMenuJson {

	public String loreIdStr1 = "";
	public String path = "";
	boolean existFlag = false;
	boolean existFlag1 = false;
	public List loreList = new ArrayList();
	public Integer num = 0;
	public Integer loreStep = 0;//多少级知识点
	public Integer loreNum = 0;//多少个知识点
	
	/**
	 * 获取主知识点树形菜单
	 * @author wm
	 * @date 2019-5-28 下午04:06:23
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
	
	public List<LoreTreeMenu> getNoTreeMenuList(LoreInfo first_lore) throws Exception{
		List<LoreTreeMenu> result = new ArrayList<LoreTreeMenu>();
		LoreTreeMenu ltMenu = new LoreTreeMenu();
		if(first_lore != null){
			ltMenu.setId(first_lore.getId());
			ltMenu.setName(first_lore.getLoreName());
			ltMenu.setMenus(null);
			result.add(ltMenu);
		}	
		return result;
	}
	
	public List<LoreTreeMenu> getLoreTreeMenuList(LoreTreeMenu ltMenu) throws Exception{
		List<LoreTreeMenu> result = new ArrayList<LoreTreeMenu>();
		result.add(ltMenu);
		return result;
	}
	
	/**
	 * 获取树形子菜单
	 * @author wm
	 * @date 2019-5-28 下午04:06:42
	 * @param lrList
	 * @return
	 * @throws Exception
	 */
	public List<LoreTreeMenu> getSubTreeMenuList(List<LoreRelateInfo> lrList)throws Exception{
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
	
	@SuppressWarnings("rawtypes")
	public List<MyTreeNode> showTree(Integer loreId,Integer studyLogId,String orderOpt) throws Exception {
		LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
		LoreInfoManager lm = (LoreInfoManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
	    List<LoreTreeMenu> l = this.getTreeMenuList(lrm.listRelateInfoByOpt(loreId, 0, -1, orderOpt));
	    List<MyTreeNode> tree = new ArrayList<MyTreeNode>();
	    this.loreList = new ArrayList();
	    this.num = 0;
	    //2014年9月23日修改--wm
	    if(l.size() == 0){//表示没有记录，就用当前知识点记录
	    	l = this.getNoTreeMenuList(lm.getEntityById(loreId));
	    }
	    for (LoreTreeMenu t : l) {
	    	MyTreeNode node = tree1(t,t.getMenus(),true,studyLogId,orderOpt);
	    	if(node != null){
	    		tree.add(node);
	    	}
	    }
	    return tree;
	}
	
	//判断是否存在该知识点
	@SuppressWarnings("rawtypes")
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
	
	private MyTreeNode tree1(LoreTreeMenu t, List<LoreTreeMenu> n, boolean recursive,Integer studyLogId,String orderOpt) throws Exception {
		
	    MyTreeNode node = new MyTreeNode(); 
//	    node.setId(t.getId());
	    if(this.existFlag){
//	    	node.setText("<font color=red>"+t.getName()+"</font>");
//	    	node.setRepeatFlag(true);
	    	return null;
	    }else{
		    node.setId(t.getId());
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
	    		RelationZdResultManager rzrm = (RelationZdResultManager)AppFactory.instance(null).getApp(Constants.WEB_RELATION_ZD_RESULT_INFO);
	    		RelationZdResult rz = rzrm.getEntityByOpt(studyLogId, node.getId());
	    		if(rz != null){
	    			node.setZdxzdFlag(rz.getZdxzdFlag());
	    			node.setStudyFlag(rz.getStudyFlag());
		    		node.setZczdFlag(rz.getZczdFlag());
		    		node.setStudyTimes(rz.getStudyTimes());
		    		node.setZczdTimes(rz.getZczdTimes());
	    		}else{
	    			node.setZdxzdFlag(-1);
		    		node.setStudyFlag(-1);
		    		node.setZczdFlag(-1);
		    		node.setStudyTimes(0);
		    		node.setZczdTimes(0);
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
			    		lrList_new = lrm.listRelateInfoByOpt(loreId, 0, -1, orderOpt);//找下一级子节点
			    		this.existFlag = false;
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
					                MyTreeNode tn = tree1(r, nextMenuList, true,studyLogId,orderOpt);
					                children.add(tn);
					            }
					            node.setChildren(children);
				    		}  
					    }
		    		}else{//存在相同节点(直接终止查询，并让下一级子节点为空)
		    			this.existFlag = true;
		    		}
			    }
		    }
	    }
	    return node;
	}
	
	/**
	 * 获取针对性诊断的路线
	 * @author wm
	 * @date 2019-5-30 上午10:39:46
	 * @param nodeList
	 * @param buff
	 * @return loreId组合，loreName组合
	 */
	public void getPath(List<MyTreeNode> nodeList,StringBuilder buff,StringBuilder buffChi){
		if (nodeList == null || nodeList.size() == 0) {
			return;
		}
		List<MyTreeNode> nodeList_1 = new ArrayList<MyTreeNode>();
		List<MyTreeNode> nodeList_2 = new ArrayList<MyTreeNode>();
		for(MyTreeNode node : nodeList){
			// 记录当前层所有元素
			if(node != null){
				buff.append(node.getId()).append("|");
				buffChi.append(node.getText()).append("|");
				if(node.getChildren() != null){
					// 把每个元素的下一层放到一个list中
					nodeList_1.addAll(node.getChildren());
					nodeList_2.addAll(node.getChildren());
				}
			}
//			if(node.getRepeatFlag() == false){
//				buff.append(node.getId()).append("|");
//			}
//			if(node.getChildren() != null){
//				// 把每个元素的下一层放到一个list中
//				nodeList_1.addAll(node.getChildren());
//			}
		}
		if(buff.length() > 0){
			buff.delete(buff.length() - 1, buff.length());
			buffChi.delete(buffChi.length() - 1, buffChi.length());
		}
		buff.append(":");
		buffChi.append(":");
		// 递归调用
		getPath(nodeList_1, buff,buffChi);
	}
	
	public void getHwPath(Integer tjId,String hwTitle,List<MyTreeNode> nodeList,StringBuilder buff,StringBuilder buffChi){
		if (nodeList == null || nodeList.size() == 0) {
			return;
		}
		List<MyTreeNode> nodeList_1 = new ArrayList<MyTreeNode>();
		List<MyTreeNode> nodeList_2 = new ArrayList<MyTreeNode>();
		Integer i = 0;
		for(MyTreeNode node : nodeList){
			if(i.equals(0)){
				buff.append(tjId).append("|");
				buffChi.append(hwTitle).append("|");
				if(node.getChildren() != null){
					// 把每个元素的下一层放到一个list中
					nodeList_1.addAll(node.getChildren());
					nodeList_2.addAll(node.getChildren());
				}
			}else{
				// 记录当前层所有元素
				if(node != null){
					buff.append(node.getId()).append("|");
					buffChi.append(node.getText()).append("|");
					if(node.getChildren() != null){
						// 把每个元素的下一层放到一个list中
						nodeList_1.addAll(node.getChildren());
						nodeList_2.addAll(node.getChildren());
					}
				}
			}
		}
		if(buff.length() > 0){
			buff.delete(buff.length() - 1, buff.length());
			buffChi.delete(buffChi.length() - 1, buffChi.length());
		}
		buff.append(":");
		buffChi.append(":");
		// 递归调用
		getHwPath(tjId,hwTitle,nodeList_1, buff,buffChi);
	}
	
	/**
	 * 获取学习的路线(和针对性诊断相反)
	 * @author wm
	 * @date 2019-5-30 上午10:40:02
	 * @param path
	 * @param type(onlineStudy,buffet,hw)
	 * @return
	 * @throws Exception 
	 */
	public String[] getStudyPath(String path,String pathChi,String type) throws Exception{
		StringBuffer studyPath = new StringBuffer();
		StringBuffer studyChiPath = new StringBuffer();
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		String[] studyPathArr = new String[2];
		Integer firstId = 0;
		String firstName = "";
		String loreIdStr = "";
		String loreNameStr = "";
		if(!path.equals("")){
//			新版本--将诊断path全部降序升序
			path = path.replaceAll(":", ",").replaceAll("\\|", ",");
			pathChi = pathChi.replaceAll(":", ",").replaceAll("\\|", ",");
			if(type.equals("onlineStudy")){
				for(int i = 0 ; i < path.split(",").length ; i++){
					studyPath.append(path.split(",")[i]).append(",");
				}
			}else{
				for(int i = 0 ; i < path.split(",").length ; i++){
					if(i > 0){
						studyPath.append(path.split(",")[i]).append(",");
					}else{//第一个是自助餐编号
						firstId = Integer.parseInt(path.split(",")[i]);//自助餐编号或者家庭作业编号
						firstName = pathChi.split(",")[i];//自助餐标题或者家庭作业标题
					}
				}
			}
			if(studyPath.length() > 0){
				studyPath = studyPath.delete(studyPath.length() - 1, studyPath.length());
				List<LoreInfo> loreList = lm.listInfoInLoreId(studyPath.toString(),"asc");
				for(LoreInfo lore : loreList){
					loreIdStr += lore.getId() + ":";
					loreNameStr += lore.getLoreName() + ":";
				}
				if(!type.equals("onlineStudy")){
					loreIdStr += firstId + ":";
					loreNameStr += firstName + ":";
				}
				loreIdStr = loreIdStr.substring(0, loreIdStr.length() - 1);
				loreNameStr = loreNameStr.substring(0, loreNameStr.length() - 1);
				studyPathArr[0] = loreIdStr;
				studyPathArr[1] = loreNameStr;
			}
			//新版本--结束
//			之前版本
//			for(int i = path.split(":").length - 1 ; i >= 0 ; i--){
//				String[] reverseArray = path.split(":")[i].split("\\|");
//				String[] reverseChiArray = pathChi.split(":")[i].split("\\|");
////				String loreIdStr = "";
//				for(int j = reverseArray.length - 1 ; j >= 0 ; j--){
////					loreIdStr += reverseArray[j] + ",";
//					
//					studyPath.append(reverseArray[j]).append("|");
//					studyChiPath.append(reverseChiArray[j]).append("|");
//				}
////				List<LoreInfo> loreList = lm.listInfoInLoreId(loreIdStr.substring(0, loreIdStr.length() - 1));
////				for(LoreInfo lore : loreList){
////					studyPath.append(lore.getId()).append("|");
////					studyChiPath.append(lore.getLoreName()).append("|");
////				}
//					
//				studyPath.replace(studyPath.length() - 1, studyPath.length(), ":");
//				studyChiPath.replace(studyChiPath.length() - 1, studyChiPath.length(), ":");
//			}
//			if(studyPath.length() > 0){
//				studyPath = studyPath.delete(studyPath.length() - 1, studyPath.length());
//				studyChiPath = studyChiPath.delete(studyChiPath.length() - 1, studyChiPath.length());
//			}
//			之前版本
		}
//		studyPathArr[0] = studyPath.toString();
//		studyPathArr[1] = studyChiPath.toString();
		return studyPathArr;
	}
	
	/**
	 * 知识点简单树浏览
	 * @author wm
	 * @date 2019-5-13 上午10:49:55
	 * @param eduId
	 * @return
	 * @throws Exception
	 */
	public List<MyTreeNode> showLoreSimpleTree(Integer eduId,Integer ediId) throws Exception {
	    List<MyTreeNode> tree = new ArrayList<MyTreeNode>();
	  //根据教材获取章节
		ChapterManager cm = (ChapterManager)AppFactory.instance(null).getApp(Constants.WEB_CHAPTER_INFO);
		LoreInfoManager lm = (LoreInfoManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
		List<Chapter> cList = cm.ListInfoByEduId(eduId);
		String orderOpt = "";
		if(ediId > 1){//通用版无需知识点编码排序
			orderOpt = "desc";//其他版本需要知识点编码降序排列
		}
		for(Iterator<Chapter> it1 = cList.iterator() ;it1.hasNext();){
			MyTreeNode node1 = new MyTreeNode();
			List<MyTreeNode> tree1 = new ArrayList<MyTreeNode>();
			Chapter cpt = it1.next();
			node1.setId(cpt.getId());
			node1.setText(cpt.getChapterName());
			node1.setState("open");
			//根据章节获取该章节下所有知识点,-1表示条件不限
			List<LoreInfo> lList = lm.listPageInfoByCptId(cpt.getId(), 1, 100000);
			for(Iterator<LoreInfo> it2 = lList.iterator() ;it2.hasNext();){
				List<MyTreeNode> tree2 = new ArrayList<MyTreeNode>();
				LoreInfo lore = it2.next();
				MyTreeNode node2 = new MyTreeNode();
				Integer loreId = lore.getId();
				String loreName = lore.getLoreName();
				node2.setId(loreId);
				node2.setText(loreName+"&nbsp;<font color=blue>管理>></font>");
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("loreId", loreId);
				attributes.put("loreName", loreName);
				node2.setAttributes(attributes);
				node2.setState("open");
//				if(ediId > 1){//如果是其他版本
//					loreId = lore.getMainLoreId();//获取被引用的通用版知识点
//				}
				//根据知识点编号获取下级关联
				List<LoreRelateInfo> lrList = lrm.listRelateInfoByOpt(loreId, 0, -1,orderOpt);
				for(Iterator<LoreRelateInfo> it3 = lrList.iterator();it3.hasNext();){
					List<MyTreeNode> tree3 = new ArrayList<MyTreeNode>();
					LoreRelateInfo lr = it3.next();
					Integer rootLoreId = lr.getRootLoreInfo().getId();
					String rootLoreName = "";
//					if(ediId > 1){//其他版本
//						LoreInfo lore_root_other = lm.getLoreInfoByOpt(rootLoreId, ediId);//通过通用版知识点获取其他出版社下的知识点
//						if(lore_root_other != null){
//							rootLoreId = lore_root_other.getId();
//							rootLoreName = lore_root_other.getLoreName();
//						}else{
//							continue;
//						}
//					}else{//通用版
//						rootLoreName = lr.getRootLoreInfo().getLoreName();
//					}
					rootLoreName = lr.getRootLoreInfo().getLoreName();
					MyTreeNode node3 = new MyTreeNode();
					node3.setId(rootLoreId);
					node3.setText(rootLoreName);
					node3.setState("open");
					node3.setChildren(tree3);
					tree2.add(node3);
				}
				node2.setChildren(tree2);
				tree1.add(node2);
			}
			node1.setChildren(tree1);
			tree.add(node1);
		}
	    return tree;
	}
	
	/**
	 * 获取有多少个知识点
	 * @author wm
	 * @date 2019-6-2 上午10:07:04
	 * @param path
	 * @return
	 */
	public Integer getLoreNum(String path){
		Integer loreNum = 0;
		if(!path.equals("")){
			path = path.replace(":", "|");
			String[] pathArray = path.split("\\|");
			loreNum = pathArray.length;
		}
		return loreNum;
	}
}
