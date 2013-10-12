package freepander.model.recv;

/**
 * 接收的图片消息
 * 
 * @author freepander
 * 
 */
public class WxRecvPicMsg extends WxRecvMsg {
	// 图片链接
	private String picUrl;

	public WxRecvPicMsg(WxRecvMsg msg, String picUrl) {
		super(msg);
		this.picUrl = picUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
