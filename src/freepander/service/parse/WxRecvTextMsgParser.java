package freepander.service.parse;

import org.jdom.Element;
import org.jdom.JDOMException;

import freepander.model.recv.WxRecvMsg;
import freepander.model.recv.WxRecvTextMsg;

/**
 * 解析文本消息
 * 
 * @author freepander
 * 
 */
public class WxRecvTextMsgParser extends WxRecvMsgBaseParser {

	@Override
	protected WxRecvTextMsg parser(Element root, WxRecvMsg msg)
			throws JDOMException {
		return new WxRecvTextMsg(msg, root.getChildText("Content"));
	}

}
