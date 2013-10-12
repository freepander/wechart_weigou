package freepander.model.recv;

import freepander.model.WxMsg;

/**
 * 接收消息的基类
 * 
 * @author freepander
 * 
 */
public class WxRecvMsg extends WxMsg {
	// 接收消息id
	private String msgId;

	public WxRecvMsg(String toUser, String fromUser, String createDt,
			String msgType, String msgId) {
		super(toUser, fromUser, createDt, msgType);
		this.msgId = msgId;
	}

	public WxRecvMsg(WxRecvMsg msg) {
		this(msg.getToUser(), msg.getFromUser(), msg.getCreateDt(), msg
				.getMsgType(), msg.getMsgId());
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	

}
