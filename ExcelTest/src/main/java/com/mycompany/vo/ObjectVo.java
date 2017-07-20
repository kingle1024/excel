package com.mycompany.vo;

import java.util.ArrayList;

public class ObjectVo {
	private boolean success;
	private int total_count;
	private ArrayList<ListVo> list;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ArrayList<ListVo> getList() {
		return list;
	}

	public void setList(ArrayList<ListVo> list) {
		this.list = list;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
}
