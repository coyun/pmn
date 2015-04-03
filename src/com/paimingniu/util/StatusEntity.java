package com.paimingniu.util;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date 2015年4月1日
 * @version V1.0
 */
public class StatusEntity<R, N> {

	private int code;

	private String msg;

	private R status;

	private N nodes;

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
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
	 * @return the status
	 */
	public R getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(R status) {
		this.status = status;
	}

	/**
	 * @return the nodes
	 */
	public N getNodes() {
		return nodes;
	}

	/**
	 * @param nodes
	 *            the nodes to set
	 */
	public void setNodes(N nodes) {
		this.nodes = nodes;
	}

}
