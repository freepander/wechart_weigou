package freepander.service.parse;

import org.jdom.Element;
import org.jdom.JDOMException;

import freepander.model.recv.WxRecvMsg;
import freepander.model.recv.WxRecvPicMsg;

/**
 * 解析图片消息
 * 
 * @author freepander
 * 
 */
public class WxRecvPicMsgParser extends WxRecvMsgBaseParser {

	@Override
	protected WxRecvPicMsg parser(Element root, WxRecvMsg msg)
			throws JDOMException {
		return new WxRecvPicMsg(msg, root.getChildText("PicUrl"));
	}

}
