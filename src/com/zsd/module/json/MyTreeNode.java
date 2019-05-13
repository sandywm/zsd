package com.zsd.module.json;

import java.util.List;
import java.util.Map;

public class MyTreeNode {
	private Integer id;
    private String text;// 树节点名称
    private Map<String, Object> attributes;// 其他参数
    private List<MyTreeNode> children;// 子节点
    private String state = "open";// 是否展开(open,closed)
    private boolean repeatFlag;
    private Integer zdxzdFlag;//关联知识点针对性诊断完成标记（-1未诊断，0未通过，1通过）
    private Integer studyFlag;//关联知识点学习完成标记（-1未学习，0未通过，1通过）
    private Integer zczdFlag;//关联知识点再次诊断完成标记（-1未诊断，0未通过，1通过）
    private Integer studyTimes; //关联知识点学习次数（默认0）
    private Integer zczdTimes; //关联知识点再次诊断次数（默认0）
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public List<MyTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<MyTreeNode> children) {
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public boolean getRepeatFlag() {
		return repeatFlag;
	}

	public void setRepeatFlag(boolean repeatFlag) {
		this.repeatFlag = repeatFlag;
	}

	public Integer getZdxzdFlag() {
		return zdxzdFlag;
	}

	public void setZdxzdFlag(Integer zdxzdFlag) {
		this.zdxzdFlag = zdxzdFlag;
	}

	public Integer getStudyFlag() {
		return studyFlag;
	}

	public void setStudyFlag(Integer studyFlag) {
		this.studyFlag = studyFlag;
	}

	public Integer getZczdFlag() {
		return zczdFlag;
	}

	public void setZczdFlag(Integer zczdFlag) {
		this.zczdFlag = zczdFlag;
	}

	public Integer getStudyTimes() {
		return studyTimes;
	}

	public void setStudyTimes(Integer studyTimes) {
		this.studyTimes = studyTimes;
	}

	public Integer getZczdTimes() {
		return zczdTimes;
	}

	public void setZczdTimes(Integer zczdTimes) {
		this.zczdTimes = zczdTimes;
	}

    
}
