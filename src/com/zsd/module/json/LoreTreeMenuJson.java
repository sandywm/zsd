package com.zsd.module.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zsd.factory.AppFactory;
import com.zsd.module.Chapter;
import com.zsd.module.LoreInfo;
import com.zsd.module.LoreRelateInfo;
import com.zsd.module.json.MyTreeNode;
import com.zsd.service.ChapterManager;
import com.zsd.service.LoreInfoManager;
import com.zsd.service.LoreRelateManager;
import com.zsd.util.Constants;

public class LoreTreeMenuJson {

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
				if(ediId.equals(1)){//通用版才能设置
					node2.setText(loreName+"&nbsp;<font color=blue>管理>></font>");
				}else{
					node2.setText(loreName);
				}
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("loreId", loreId);
				attributes.put("loreName", loreName);
				node2.setAttributes(attributes);
				node2.setState("open");
				if(ediId > 1){//如果是其他版本
					loreId = lore.getMainLoreId();//获取被引用的通用版知识点
				}
				//根据知识点编号获取下级关联
				List<LoreRelateInfo> lrList = lrm.listRelateInfoByOpt(loreId, 0, -1);
				for(Iterator<LoreRelateInfo> it3 = lrList.iterator();it3.hasNext();){
					List<MyTreeNode> tree3 = new ArrayList<MyTreeNode>();
					LoreRelateInfo lr = it3.next();
					Integer rootLoreId = lr.getRootLoreInfo().getId();
					String rootLoreName = "";
					if(ediId > 1){//其他版本
						LoreInfo lore_root_other = lm.getLoreInfoByOpt(rootLoreId, ediId);//通过通用版知识点获取其他出版社下的知识点
						if(lore_root_other != null){
							rootLoreId = lore_root_other.getId();
							rootLoreName = lore_root_other.getLoreName();
						}else{
							continue;
						}
					}else{//通用版
						rootLoreName = lr.getRootLoreInfo().getLoreName();
					}
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
}
