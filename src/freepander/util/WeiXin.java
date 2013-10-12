package freepander.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.output.XMLOutputter;

import freepander.model.recv.WxRecvMsg;
import freepander.model.send.WxSendMsg;
import freepander.service.parse.WxMsgKit;

/**
 * 微信核心工具类 。
 * 
 * @author freepander
 * 
 */
public final class WeiXin {
	/**
	 * 用户接入微信平台，验证是否是微信平台发出的请求
	 * 
	 * @param token
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean access(String token, String signature,
			String timestamp, String nonce) {
		List<String> ss = new ArrayList<String>();
		ss.add(timestamp);
		ss.add(nonce);
		ss.add(token);

		Collections.sort(ss);

		StringBuilder builder = new StringBuilder();
		for (String s : ss) {
			builder.append(s);
		}
		return signature.equalsIgnoreCase(HashKit.sha1(builder.toString()));
	}

	/**
	 * 根据输入流构造一个接收到的消息类
	 * 
	 * @param in
	 *            输入流
	 * @return 一个接收消息类
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static WxRecvMsg recv(InputStream in) throws JDOMException,
			IOException {

		return WxMsgKit.parse(in);
	}

	/**
	 * 将要发送的消息发送到输出流
	 * 
	 * @param msg
	 *            要发送的消息
	 * @param out
	 *            目标输出流
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static void send(WxSendMsg msg, OutputStream out)
			throws JDOMException, IOException {
		Document doc = WxMsgKit.parse(msg);
		if (null != doc) {
			new XMLOutputter().output(doc, out);
		} else {
			Logger.getAnonymousLogger().warning("发送消息时,解析出dom为空 msg :" + msg);
		}
	}

	/**
	 * 根据接受到的消息构建一个回复的消息
	 * 
	 * @param msg
	 *            接受到的消息
	 * @return 回复的消息
	 */
	public static WxSendMsg builderSendByRecv(WxRecvMsg msg, String type) {
		WxRecvMsg m = new WxRecvMsg(msg);
		String from = m.getFromUser();
		m.setFromUser(m.getToUser());
		m.setToUser(from);
		m.setCreateDt((System.currentTimeMillis() / 1000) + "");
		m.setMsgType(type);
		return new WxSendMsg(m);
	}
}
