package freepander.model;

/**
 * 微信消息基类
 * 
 * @author freepander
 * 
 */
public class WxMsg {
	// 接受者
	private String toUser;
	// 发送者
	private String fromUser;
	// 创建时间
	private String createDate;
	// 消息类型
	private String msgType;

	public WxMsg() {

	}
	
	public WxMsg(String toUser, String fromUser, String createDate,
			String msgType) {
		this.toUser = toUser;
		this.fromUser = fromUser;
		this.createDate = createDate;
		this.msgType = msgType;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getCreateDt() {
		return createDate;
	}

	public void setCreateDt(String createDate) {
		this.createDate = createDate;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
}
