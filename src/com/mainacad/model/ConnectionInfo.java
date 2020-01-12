package com.mainacad.model;

import java.io.Serializable;

public class ConnectionInfo implements Serializable {

	private Long connectionTime;
	private Integer sessionId;
	private String ip;

	@Override
	public String toString() {
		return connectionTime + " " + sessionId + " " + ip;
	}

	public ConnectionInfo(Long connectionTime, Integer sessionId, String ip) {
		this.connectionTime = connectionTime;
		this.sessionId = sessionId;
		this.ip = ip;
	}

//    public ConnectionInfo(Integer sessionId, Long connectionTime) {
//		this.sessionId = sessionId;
//		this.connectionTime = connectionTime;
//	}
	public ConnectionInfo() {
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Long getConnectionTime() {
		return connectionTime;
	}

	public void setConnectionTime(Long connectionTime) {
		this.connectionTime = connectionTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
