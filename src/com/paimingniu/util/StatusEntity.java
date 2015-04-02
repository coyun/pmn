package com.paimingniu.util;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date 2015年4月1日
 * @version V1.0
 */
public class StatusEntity<T> {

	private int status;

	private String msg;

	private T nodes;

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the nodes
	 */
	public T getNodes() {
		return nodes;
	}

	/**
	 * @param nodes
	 *            the nodes to set
	 */
	public void setNodes(T nodes) {
		this.nodes = nodes;
	}

}
