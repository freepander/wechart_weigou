package freepander.service.parse;

import org.jdom.Element;
import org.jdom.JDOMException;

import freepander.model.recv.WxRecvGeoMsg;
import freepander.model.recv.WxRecvMsg;

/**
 * 解析地理位置消息
 * 
 * @author freepander
 * 
 */
public class WxRecvGeoMsgParser extends WxRecvMsgBaseParser {

	@Override
	protected WxRecvGeoMsg parser(Element root, WxRecvMsg msg)
			throws JDOMException {
		String locationX = root.getChildText("Location_X");
		String locationY = root.getChildText("Location_Y");
		int scale = parseInt(root.getChildText("Scale"), 0);
		String label = root.getChildText("Label");

		double latitude = parseDouble(locationX, 0.0);
		double longitude = parseDouble(locationY, 0.0);

		return new WxRecvGeoMsg(msg, latitude, longitude, scale, label);
	}

	private double parseDouble(String val, double def) {
		try {
			return Double.parseDouble(val);
		} catch (Exception e) {
			return def;
		}
	}

	private int parseInt(String val, int def) {
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			return def;
		}
	}

}
