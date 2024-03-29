package com.eflake.utils.library.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.util.EncodingUtils;

import android.os.Environment;

public class FileUtils {
	private static final String TAG = "FileUtils";

	private FileUtils() { /* cannot be instantiated */
	}
	
	
	/**
	 * 在SD卡上创建文件
	 * 
	 * @throws IOException
	 */
	public File createSDFile(String fileName) throws IOException {
		File file = new File(Environment.getExternalStorageDirectory().getPath() + "//" + fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 删除SD卡上的文件
	 * 
	 * @param fileName
	 */
	public boolean deleteSDFile(String fileName) {
		File file = new File(Environment.getExternalStorageDirectory().getPath() + "//" + fileName);
		if (file == null || !file.exists() || file.isDirectory())
			return false;
		return file.delete();
	}

	/**
	 * 写入内容到SD卡中的txt文本中 str为内容
	 */
	public void writeSDFile(String str, String fileName,boolean isAppend) {
		try {
			FileWriter fw = new FileWriter(Environment.getExternalStorageDirectory().getPath() + "//" + fileName,isAppend);
			File f = new File(Environment.getExternalStorageDirectory().getPath() + "//" + fileName);
			fw.write(str);
			FileOutputStream os = new FileOutputStream(f);
			DataOutputStream out = new DataOutputStream(os);
			out.writeShort(2);
			out.writeUTF("");
			System.out.println(out);
			fw.flush();
			fw.close();
			System.out.println(fw);
		} catch (Exception e) {
		}
	}

	/**
	 * 读取SD卡中文本文件
	 * 
	 * @param fileName
	 * @return
	 */
	public String readSDFileSec(String fileName) {
		  String res = "";
	        try {
	    		File file = new File(Environment.getExternalStorageDirectory().getPath() + "//" + fileName);
	            FileInputStream fin = new FileInputStream(file);
	            // FileInputStream fin = openFileInput(fileName);
	            // 用这个就不行了，必须用FileInputStream
	            int length = fin.available();
	            byte[] buffer = new byte[length];
	            fin.read(buffer);
	            res = EncodingUtils.getString(buffer, "UTF-8");////依Y.txt的编码类型选择合适的编码，如果不调整会乱码
	            fin.close();//关闭资源
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return res;
	}
}
