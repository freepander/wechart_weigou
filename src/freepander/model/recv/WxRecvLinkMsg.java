package freepander.model.recv;

/**
 * 接收的链接消息
 * 
 * @author freepander
 * 
 */
public class WxRecvLinkMsg extends WxRecvMsg {
	// 消息标题
	private String title;
	// 消息描述
	private String description;
	// 消息链接
	private String url;

	public WxRecvLinkMsg(WxRecvMsg msg, String title, String descString,
			String url) {
		super(msg);
		this.title = title;
		this.description = descString;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}