package freepander.service.parse;

import org.jdom.Document;
import org.jdom.JDOMException;

import freepander.model.recv.WxRecvMsg;

/**
 * 
 * @author freepander
 * 
 */
public interface WxRecvMsgParser {
	WxRecvMsg parser(Document doc) throws JDOMException;
}
