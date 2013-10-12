package freepander.model.send;

import org.jdom.Document;

/**
 * 回复的文字消息
 * 
 * @author freepander
 * 
 */
public class WxSendTextMsg extends WxSendMsg {
	// 消息内容
	private String content;

	public WxSendTextMsg(WxSendMsg msg, String content) {
		super(msg);
		setMsgType("text");
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public Document toDocument() {
		Document doc = super.toDocument();
		createElement(doc.getRootElement(), "Content", getContent());
		return doc;
	}
}
