package com.ldp.coolnews.domain;

public class NewsCenterContentCategory {

	private String id;
	
	private String title;
	
	private int type;
	
	private String url;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "NewsCenterContentCategory [id=" + id + ", title=" + title
				+ ", type=" + type + "]";
	}
	
}
