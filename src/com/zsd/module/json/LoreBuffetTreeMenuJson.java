package com.zsd.module.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zsd.module.LoreInfo;
import com.zsd.module.LoreRelateInfo;

@SuppressWarnings("rawtypes")
public class LoreBuffetTreeMenuJson {
	public String loreIdStr1 = "";
	public String path = "";
	boolean existFlag = false;
	boolean existFlag1 = false;
	public List loreList = new ArrayList();
	public Integer num = 0;
	public Integer loreStep = 0;//多少级知识点
	public Integer loreNum = 0;//多少个知识点
	
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
	
	//获取子树节点
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
}
