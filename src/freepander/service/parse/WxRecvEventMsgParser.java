package freepander.service.parse;

import org.jdom.Element;
import org.jdom.JDOMException;

import freepander.model.recv.WxRecvEventMsg;
import freepander.model.recv.WxRecvMsg;

/**
 * 解析时间消息
 * 
 * @author freepander
 * 
 */
public class WxRecvEventMsgParser extends WxRecvMsgBaseParser {

	@Override
	protected WxRecvEventMsg parser(Element root, WxRecvMsg msg)
			throws JDOMException {
		String event = root.getChildText("Event");
		String eventKey = root.getChildText("EventKey");

		return new WxRecvEventMsg(msg, event, eventKey);
	}
}
