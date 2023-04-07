package com.remarkablesoft.framework.common;

import com.remarkablesoft.framework.service.org.user.vo.UserInfo;

/**
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	core
 * 		@프로그램 ID		:	TestUserInfo.java
 * 		@프로그램 개요 		:	사용자객체
 *
 */
public class TestUserInfo extends UserInfo {


	/**
	 *
	 */
	private static final long serialVersionUID = 8407182954201882353L;


	private String aaa ;
	private String bbb ;


	public String getAaa() {
		return aaa;
	}
	public void setAaa(String aaa) {
		this.aaa = aaa;
	}
	public String getBbb() {
		return bbb;
	}
	public void setBbb(String bbb) {
		this.bbb = bbb;
	}


	@Override
	public String toString() {
		return "TestUserInfo [aaa=" + aaa + ", bbb=" + bbb + "]";
	}



}
