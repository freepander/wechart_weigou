package freepander.wechart;

import java.util.logging.Logger;

import freepander.model.recv.WxRecvEventMsg;
import freepander.model.recv.WxRecvGeoMsg;
import freepander.model.recv.WxRecvLinkMsg;
import freepander.model.recv.WxRecvMsg;
import freepander.model.recv.WxRecvPicMsg;
import freepander.model.recv.WxRecvTextMsg;
import freepander.model.send.WxSendMsg;
import freepander.model.send.WxSendTextMsg;
import freepander.util.WeiXin;

/**
 * 微信消息处理程序入口
 * 
 * @author freepander
 *  流程：
 *   1、判断消息类型 
 *   2、想接收到的消息强转为正确类型消息 
 *   3、将消息传入具体逻辑运算类 
 *   4、获取恢复消息
 */
public class Portal {
	public WxSendMsg main(WxRecvMsg recvMsg) {
		if (recvMsg.getMsgType().equals("text")) {
			// 文本类型消息
			WxRecvTextMsg recvTextMsg = (WxRecvTextMsg) recvMsg;
			Logger.getAnonymousLogger().info(
					"接收文本消息" + recvTextMsg.getContent());
			return new ParseText().parse(recvTextMsg);
		} else if (recvMsg.getMsgType().equals("image")) {
			// 图片类型消息
			WxRecvPicMsg recvPicMsg = (WxRecvPicMsg) recvMsg;
			return new ParseImage().parse(recvPicMsg);
		} else if (recvMsg.getMsgType().equals("location")) {
			// 地理位置类型消息
			WxRecvGeoMsg recvGeoMsg = (WxRecvGeoMsg) recvMsg;
			return new ParseLocation().parse(recvGeoMsg);
		} else if (recvMsg.getMsgType().equals("link")) {
			// 链接类型消息
			WxRecvLinkMsg recvLinkMsg = (WxRecvLinkMsg) recvMsg;
			return new ParseLink().parse(recvLinkMsg);
		} else if (recvMsg.getMsgType().equals("event")) {
			// 事件消息
			WxRecvEventMsg recvEventMsg = (WxRecvEventMsg) recvMsg;
			Logger.getAnonymousLogger().info(
					"接收事件消息" + recvEventMsg.getEventKey() + "  "
							+ recvEventMsg.getFromUser());
			return new ParseEvent().parse(recvEventMsg);
		} else {
			// 未知消息类型
			WxSendMsg sendMsg = WeiXin.builderSendByRecv(recvMsg, "text");
			WxSendTextMsg sendTextMsg = new WxSendTextMsg(sendMsg,
					"你给我发的是什么？我听不懂");
			return sendTextMsg;
		}
	}

}
