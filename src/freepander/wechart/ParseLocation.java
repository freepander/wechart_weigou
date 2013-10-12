package freepander.wechart;

import freepander.model.recv.WxRecvGeoMsg;
import freepander.model.send.WxSendMsg;
import freepander.model.send.WxSendTextMsg;
import freepander.util.WeiXin;

/**
 * 
 * @author freepander
 * 
 */
public class ParseLocation {
	public WxSendMsg parse(WxRecvGeoMsg recvMsg) {
		WxSendTextMsg sendMsg = (WxSendTextMsg) WeiXin.builderSendByRecv(recvMsg, "text");
		sendMsg.setContent("你发的东西我还看不懂，但是我会保存起来的");
		return sendMsg;
	}
}
