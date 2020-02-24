package com.iis.pojo;

import java.io.Serializable;

public class Response implements Serializable {
	private static final long serialVersionUID = 6388320635984033183L;
	private boolean isSuccess;
	private String status;

	public Response(boolean isSuccess, String status) {
		this.isSuccess = isSuccess;
		this.status = status;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
