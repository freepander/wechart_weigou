package freepander.model.send;

/**
 * 回复的图文消息 子元素
 * 
 * @author freepander
 * 
 */

public class WxSendNewsMsgItem {
	// 消息标题
	private String title;
	// 消息描述
	private String description;
	// 图片链接
	private String picUrl;
	// 指向地址
	private String url;

	public WxSendNewsMsgItem(String title, String description, String picUrl,
			String url) {
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
