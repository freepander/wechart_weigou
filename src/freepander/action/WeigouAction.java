package freepander.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import freepander.model.recv.WxRecvMsg;
import freepander.model.send.WxSendMsg;
import freepander.util.Constants;
import freepander.util.WeiXin;
import freepander.wechart.Portal;

@Component("weigouAction")
@Scope("prototype")
public class WeigouAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String index() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (request.getMethod().equals("GET")) {
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			if (WeiXin.access(Constants.weiGoutoken, signature, timestamp,
					nonce)) {
				Logger.getAnonymousLogger().info("weigouAction 接收到了微信验证信息");
				PrintWriter pw;
				try {
					pw = response.getWriter();
					System.out.println(echostr);
					pw.println(echostr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Logger.getAnonymousLogger().info("weigouAction 接收到了非微信验证信息");
			}
		} else {
			// 获得输入流
			InputStream in = null;
			try {
				in = request.getInputStream();
			} catch (IOException e1) {
				Logger.getAnonymousLogger().info("weigouAction 获取输入流异常");
				e1.printStackTrace();
			}
			// 创建接收消息对象
			WxRecvMsg wxRecvMsg = null;
			try {
				wxRecvMsg = WeiXin.recv(in);
			} catch (Exception e) {
				Logger.getAnonymousLogger().info("weigouAction 解析输入流异常");
				e.printStackTrace();
			}
			// 将接收到的消息 转发给处理模块
			// 取得要恢复的消息

			WxSendMsg wxSendMsg = new Portal().main(wxRecvMsg);

			// 将要恢复的消息发送到输出流上
			try {
				WeiXin.send(wxSendMsg, response.getOutputStream());
			} catch (Exception e) {
				Logger.getAnonymousLogger().info("weigouAction 发送消息异常");
				e.printStackTrace();
			}
		}
		return null;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
