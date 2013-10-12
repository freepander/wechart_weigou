package freepander.model.recv;

/**
 * 接收的文字消息
 * 
 * @author freepander
 * 
 */
public class WxRecvTextMsg extends WxRecvMsg {
	// 文字内容
	private String content;

	public WxRecvTextMsg(WxRecvMsg msg, String content) {
		super(msg);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
