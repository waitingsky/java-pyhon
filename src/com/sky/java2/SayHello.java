package com.sky.java2;

/**
 * ��python�ű����õ�java��
 * 
 * @author webim
 * 
 */
public class SayHello {
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void say(int i) {
		System.out.println(i + ":Hello " + userName);
	}
}