package freepander.service.parse;

import org.jdom.Element;
import org.jdom.JDOMException;

import freepander.model.recv.WxRecvLinkMsg;
import freepander.model.recv.WxRecvMsg;

/**
 * 解析链接消息
 * 
 * @author freepander
 * 
 */
public class WxRecvLinkMsgParser extends WxRecvMsgBaseParser {

	@Override
	protected WxRecvLinkMsg parser(Element root, WxRecvMsg msg)
			throws JDOMException {

		String title = root.getChildText("Title");
		String description = root.getChildText("Description");
		String url = root.getChildText("Url");
		return new WxRecvLinkMsg(msg, title, description, url);
	}

}
