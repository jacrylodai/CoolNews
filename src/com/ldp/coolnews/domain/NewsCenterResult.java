package com.ldp.coolnews.domain;

import java.util.List;

public class NewsCenterResult {

	private List<NewsCenterMenuCategory> data;
	
	private int retcode;

	public List<NewsCenterMenuCategory> getData() {
		return data;
	}

	public void setData(List<NewsCenterMenuCategory> data) {
		this.data = data;
	}

	public int getRetcode() {
		return retcode;
	}

	public void setRetcode(int retcode) {
		this.retcode = retcode;
	}

	@Override
	public String toString() {
		return "NewsCenterResult [data=" + data + ", retcode=" + retcode + "]";
	}
	
}
