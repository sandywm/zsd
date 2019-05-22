package com.zsd.module.json;

import java.util.List;

/**
 * 后台获取自助餐知识点关联树用
 * @author Administrator
 * @createDate 2019-5-22
 */
public class MySimpleTreeNode {
	
	private Integer id;
    private String text;// 树节点名称
    private List<MySimpleTreeNode> children;// 子节点
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<MySimpleTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<MySimpleTreeNode> children) {
		this.children = children;
	}
    
    
    
}
