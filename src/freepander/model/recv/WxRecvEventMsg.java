package freepander.model.recv;

/**
 * 接收的事件消息
 * 
 * @author freepander
 * 
 */
public class WxRecvEventMsg extends WxRecvMsg {
	// 事件名称
	private String event;
	// 事件KEY值
	private String eventKey;

	public WxRecvEventMsg(WxRecvMsg msg, String event, String eventKey) {
		super(msg);
		this.event = event;
		this.eventKey = eventKey;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
