package freepander.util;

import java.io.InputStream;
import java.net.URL;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

/**
 * 从外部接口获取信息
 * 
 * @author freepander
 * 
 * 其余接口参照： http://api.xiaojianjian.net/
 */
public class GetMessage {
	/**
	 * 获取一个笑话
	 * 
	 * @return 笑话内容
	 */
	public static String getJoke() {
		try {

			URL newsurl = new URL(
					"http://api.xiaojianjian.net/api/show.action?m=joke");
			InputStream news = newsurl.openStream();
			String newss = IOUtils.toString(news);
			JSONObject jsonObject = JSONObject.fromObject(newss);
			return jsonObject.getString("contextText");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "网络故障";
	}

	/**
	 * 获取一个段子（不是黄段子）
	 * 
	 * @return 返回段子内容
	 */
	public static String getDuanzi() {
		try {

			URL newsurl = new URL(
					"http://api.xiaojianjian.net/api/show.action?m=duanzi");
			InputStream news = newsurl.openStream();
			String newss = IOUtils.toString(news);
			JSONObject jsonObject = JSONObject.fromObject(newss);
			return jsonObject.getString("context");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "网络故障";

	}
}
