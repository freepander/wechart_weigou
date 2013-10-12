package freepander.service.parse;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Text;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import freepander.model.recv.WxRecvMsg;

/**
 * 解析消息类的基类
 * 
 * @author freepander
 * 
 */
public abstract class WxRecvMsgBaseParser implements WxRecvMsgParser {

	public WxRecvMsg parser(Document doc) throws JDOMException {
		try {
			new XMLOutputter().output(doc, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Element root = doc.getRootElement();
		String toUserName = root.getChildText("ToUserName");
		String fromUserName = root.getChildText("FromUserName");
		String createTime = root.getChildText("CreateTime");
		String msgType = root.getChildText("MsgType");
		String msgId = root.getChildText("MsgId");

		return parser(root, new WxRecvMsg(toUserName, fromUserName, createTime,
				msgType, msgId));
	}

	protected abstract WxRecvMsg parser(Element root, WxRecvMsg msg)
			throws JDOMException;

	/**
	 * 得到xpath的子元素
	 * 
	 * @param elem
	 * @param xpath
	 * @return
	 * @throws JDOMException
	 */
	protected String getElementText(Element elem, String xpath)
			throws JDOMException {

		Text text = ((Text) XPath.selectSingleNode(elem, xpath + "/text()"));
		if (null == text) {
			return "";
		}
		return text.getText();
	}

}
