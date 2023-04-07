package com.remarkablesoft.framework.web.module.notification.push.old;

import com.remarkablesoft.framework.model.vo.Entity;
import com.remarkablesoft.framework.util.StringUtils;
import org.json.simple.JSONObject;

/**
 * 		@주시스템		:	framework-web
 * 		@서브시스템	:	module-notification-push
 * 		@프로그램ID	:	FcmMessage
 * 		@프로그램개요 	:	안드로이드 PUSH 발송 JSON생성
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2018. 5. 08.	:	이균환		-	 안드로이드 FCM용 메세지 객체 JSON을 만들때 사용
 * 		============================================================================
 */
public class FcmMessage extends Entity {

	/**
	 *
	 */
	private static final long serialVersionUID = 354028921986386112L;


	private String title;			// 푸시 제목
	private String body;
	private String token;
	private String linkUrl;
	private String msgType;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@SuppressWarnings("unchecked")
	public String toJson() {

		JSONObject message = new JSONObject();

		JSONObject data = new JSONObject();
		data.put("msgType", getMsgType());
		data.put("title", getTitle());
		data.put("body", getBody());
		data.put("linkUrl", getLinkUrl());

		message.put("to", getToken());
		message.put("data", data);

		return StringUtils.replace( message.toString(), "\\/", "/");
//		return  message.toString();
	}
}
