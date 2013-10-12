package freepander.wechart;

import freepander.model.recv.WxRecvEventMsg;
import freepander.model.send.WxSendMsg;
import freepander.model.send.WxSendNewsMsg;
import freepander.model.send.WxSendTextMsg;
import freepander.util.WeiXin;

/**
 * 
 * @author freepander
 * 
 */
public class ParseEvent  {
	public WxSendMsg parse(WxRecvEventMsg recvEventMsg) {
		//构建回复消息的父类
		WxSendMsg sendMsg = WeiXin.builderSendByRecv(recvEventMsg, "text");
		if (recvEventMsg.getEvent().equals("subscribe")) {
			//用户关注
			WxSendNewsMsg newsMsg=new WxSendNewsMsg(sendMsg);
			newsMsg.addItem("欢迎关注微微狗", "描述", "http://2.freepander.sinaapp.com/index.jsp", "http://2.freepander.sinaapp.com/index.jsp");
			return newsMsg;
		} else if (recvEventMsg.getEvent().equals("unsubscribe")) {
			//用户取消关注
			WxSendTextMsg textMsg=new WxSendTextMsg(sendMsg,"取消了关注");
			System.out.println("用户取消了关注");
			return textMsg;
		} else {
			//自定义菜单事件
			String key = recvEventMsg.getEventKey();
			WxSendTextMsg textMsg=new WxSendTextMsg(sendMsg,"点击 "+key);
			System.out.println("用户 "+recvEventMsg.getFromUser()+" 点击了"+key);
			return textMsg;
		}
	}

}
