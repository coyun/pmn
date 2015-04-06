package com.paimingniu.handle;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date 2015年4月8日
 * @version V1.0
 */
public class Node {

	private String k;

	private String m;

	private int t;

	public Node() {
	}

	public Node(String k, String m, int t) {
		this.k = k;
		this.m = m;
		this.t = t;
	}

	/**
	 * @return the k
	 */
	public String getK() {
		return k;
	}

	/**
	 * @param k
	 *            the k to set
	 */
	public void setK(String k) {
		this.k = k;
	}

	/**
	 * @return the m
	 */
	public String getM() {
		return m;
	}

	/**
	 * @param m
	 *            the m to set
	 */
	public void setM(String m) {
		this.m = m;
	}

	/**
	 * @return the t
	 */
	public int getT() {
		return t;
	}

	/**
	 * @param t
	 *            the t to set
	 */
	public void setT(int t) {
		this.t = t;
	}

}
