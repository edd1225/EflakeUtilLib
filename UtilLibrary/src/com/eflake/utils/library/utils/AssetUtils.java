package com.eflake.utils.library.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


/**
 * <p>Title: AssetUtils</p>
 * <p>Description: </p>
 * @author	Eflake
 * @date	2014-7-29
 */
public class AssetUtils {
	private static final String TAG = "AssetUtils";

	private AssetUtils() { /* cannot be instantiated */
	}

	/**
	 * 通过文件名从Assets中获得资源,以输入流的形式返回
	 * 
	 * @param context
	 * @param fileName
	 *            文件名应为assets文件下载绝对路径
	 * @return 以InputStream的形式返回
	 */
	public static InputStream getInputStreamForName(Context context,
			String fileName)
	{
		AssetManager assetManager = context.getAssets();
		InputStream inputStream = null;
		try
		{
			inputStream = assetManager.open(fileName);
		} catch (IOException e)
		{
			Log.d(TAG, e.getMessage());
		}
		return inputStream;
	}

	/**
	 * 通过文件名从Assets中获得资源，以字符串的形式返回
	 * 
	 * @param context
	 * @param fileName
	 *            文件名应为assets文件下载绝对路径
	 * @return 以字符串的形式返回
	 */
	public static String getStringForName(Context context, String fileName)
	{
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte buf[] = new byte[1024];
		int len;
		try
		{
			inputStream = getInputStreamForName(context, fileName);
			while ((len = inputStream.read(buf)) != -1)
			{
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
			inputStream.close();
		} catch (IOException e)
		{
			Log.d(TAG, e.getMessage());
		}
		return outputStream.toString();
	}

	/**
	 * 通过文件名从Assets中获得资源，以位图的形式返回
	 * 
	 * @param context
	 * @param fileName
	 *            文件名应为assets文件下载绝对路径
	 * @return 以位图的形式返回
	 */
	public static Bitmap getBitmapForName(Context context, String fileName)
	{
		Bitmap bitmap = null;
		InputStream inputStream = null;
		try
		{
			inputStream = getInputStreamForName(context, fileName);
			bitmap = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
		} catch (IOException e)
		{
			Log.d(TAG, e.getMessage());
		}
		return bitmap;
	}
}
