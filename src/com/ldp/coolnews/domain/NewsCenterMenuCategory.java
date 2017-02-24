package com.ldp.coolnews.domain;

import java.util.List;

public class NewsCenterMenuCategory {

	private String id;
	
	private String title;
	
	private int type;
	
	private List<NewsCenterContentCategory> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<NewsCenterContentCategory> getChildren() {
		return children;
	}

	public void setChildren(List<NewsCenterContentCategory> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "NewsCenterMenuCategory [id=" + id + ", title=" + title
				+ ", type=" + type + ", children=" + children + "]";
	}
	
}
