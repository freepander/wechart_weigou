package freepander.wechart;

import freepander.model.recv.WxRecvTextMsg;
import freepander.model.send.WxSendMsg;
import freepander.model.send.WxSendTextMsg;
import freepander.util.GetMessage;
import freepander.util.WeiXin;

/**
 * 
 * @author freepander
 * 
 */
public class ParseText {
	public WxSendMsg parse(WxRecvTextMsg recvMsg) {
		WxSendMsg sendMsgP = WeiXin.builderSendByRecv(recvMsg, "text");
		String recv = recvMsg.getContent();
		if (recv.equals("网页浏览")) {
			WxSendTextMsg sendMsg = new WxSendTextMsg(sendMsgP,
					"<a href='http://1.weigou.sinaapp.com/test/shop.html'>商品列表</a>");
			return sendMsg;
		} else if (recv.equals("段子")) {
			WxSendTextMsg sendMsg = new WxSendTextMsg(sendMsgP,
					GetMessage.getDuanzi());
			return sendMsg;
		} else if (recv.equals("笑话")) {
			WxSendTextMsg sendMsg = new WxSendTextMsg(sendMsgP,
					GetMessage.getJoke());
			return sendMsg;
		} else {
			WxSendTextMsg sendMsg = new WxSendTextMsg(sendMsgP,
					"发送'笑话'或者'段子'就可以看看别人的糗事。你刚刚说的是 "+recvMsg.getContent());
			return sendMsg;
		}
	}
}
