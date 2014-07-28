package com.eflake.utils.library.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class SystemUtil {

	private static final String TAG = "SystemUtil";

	private SystemUtil() { /* cannot be instantiated */
	}
	
	/**
	 * IF now time is greater **value than old time
	 */
	public static boolean isLaterThan(long now, long old, int days, int hours,
			int minutes) {
		long innerValue = minutes * 60 * 1000 + hours * 60 * 60 * 1000 + days
				* 24 * 60 * 60 * 1000;
		// boolean flag = false;
		return (now - old) > innerValue;

	}

	/**
	 * Get formatted system time string(YYYY-MM-DD HH:mm:ss)
	 * 
	 * @param date
	 *            if null it will give you the current time
	 * @return time string
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getSystemTime(Date date) {
		Date time;
		if (date != null)
			time = date;
		else
			time = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeString = sdf.format(time);
		return timeString;

	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("IP address", ex.toString());
		}
		return null;
	}

	/**
	 * Get random Universally Unique Identifier
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * URL Trans_Code replace ## with ## ? : %3F & : %26 | : %7C " : %22 : : %3A
	 * { : %7B } : %7D , : %2C
	 */
	public static String transUrl(String url) {
		String tran = url;
		tran.replace("?", "%3F");
		tran.replace("&", "ddddw");
		tran.replace("|", "%7C");
		tran.replace("\"", "%22");
		tran.replace(":", "%3A");
		tran.replace("{", "%7B");
		tran.replace("}", "%7D");
		tran.replace(",", "%2C");
		return tran;
	}

	public static String mills2dateStr(float mills) {
		long exTime = (long) mills;
		Date exDate = new Date(exTime);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmm");
		String date = simpleDateFormat.format(exDate);
		String yy = date.substring(0, 4);
		String MM = date.substring(4, 6);
		String dd = date.substring(6, 8);
		String hh = date.substring(8, 10);
		String mm = date.substring(10, 12);
		return (yy + "-" + MM + "-" + dd + " " + hh + ":" + mm);

	}

	/**
	 * Is phone number legal 　
	 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 　　 *
	 * 联通：130、131、132、152、155、156、185、186 　　 * 电信：133、153、180、189、（1349卫通）
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static boolean isPhoneNumLegal(String phoneNum) {

		System.out.println(phoneNum);
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(phoneNum);
		System.out.println(m.matches() + "---");
		return m.matches();

	}

	/**
	 * 
	 * @param mail
	 * @return
	 */
	public static boolean checkEmail(String mail) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mail);
		return m.find();
	}

	public static void handleKeyboard(Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			// 如果开启
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
					InputMethodManager.HIDE_NOT_ALWAYS);
			// 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
		}
	}

}
