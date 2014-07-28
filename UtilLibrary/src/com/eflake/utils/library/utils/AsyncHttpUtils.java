package com.eflake.utils.library.utils;

import android.content.Context;
import org.apache.http.entity.StringEntity;

import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * @see http://loopj.com/android-async-http/
 * 
 * @author tang
 * 
 */
public class AsyncHttpUtils {
	private static final String TAG = "HttpUtils";
	private static final String BASE_URL = "";
	private static AsyncHttpClient client = new AsyncHttpClient(); // 实例话对象

	static {
		client.setTimeout(11000); // 设置链接超时，如果不设置，默认为10s
	}

	private AsyncHttpUtils() { /* cannot be instantiated */
	}

	public static AsyncHttpClient getClient() {
		return client;
	}

	// -----------Get Method--------------
	public static void get(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
	{
		client.get(urlString, res);
	}

	/**
	 * RequestParams params = new RequestParams(); params.put("key", "value");
	 * params.put("more", "data");</p>
	 * 
	 * HashMap<String, String> paramMap = new HashMap<String, String>();
	 * paramMap.put("key", "value"); RequestParams params = new
	 * RequestParams(paramMap);</p>
	 */
	public static void get(String urlString, RequestParams params,
			AsyncHttpResponseHandler res) // url里面带参数
	{
		client.get(urlString, params, res);
	}

	public static void get(String urlString, JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组
	{
		client.get(urlString, res);
	}

	public static void get(String urlString, RequestParams params,
			JsonHttpResponseHandler res) // 带参数，获取json对象或者数组
	{
		client.get(urlString, params, res);
	}

	public static void get(String urlString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据，当前与总和
	{
		client.get(urlString, bHandler);
	}
	
	public static void get(String urlString,FileAsyncHttpResponseHandler handler){// 下载图片
		client.get(urlString,handler);
	}

	// -----------Post Method--------------

	/**
	 * ----Add an InputStream to the RequestParams to upload: ---- 
	 * InputStream myInputStream = blah; 
	 * RequestParams params = new RequestParams();
	 * params.put("secret_passwords", myInputStream,"passwords.txt");
	 * 
	 * ----Add a File object to the RequestParams to upload:---- 
	 * File myFile =new File("/path/to/file.png"); 
	 * RequestParams params = new
	 * RequestParams(); 
	 * try { 
	 * params.put("profile_picture", myFile);
	 * } 
	 * catch(FileNotFoundException e) {}
	 * 
	 * ----Add a byte array to the RequestParams to upload:----
	 * byte[] myByteArray = blah;
	 * RequestParams params = new RequestParams();
	 * params.put("soundtrack", new ByteArrayInputStream(myByteArray), "she-wolf.mp3");
	 * 
	 */
	public static void post(String url, RequestParams params,// 带参数
			AsyncHttpResponseHandler handler) {
		client.post(url, params, handler);
	}

	public static void post(String url, RequestParams params,
			JsonHttpResponseHandler handler) {
		client.post(url, params, handler);
	}

	public static void post(Context context, String url, StringEntity entity,// 以JSON字符串为参数，获取json对象或者数组
			String contentType, JsonHttpResponseHandler handler) {
		client.post(context, url, entity, contentType, handler);
	}

}
