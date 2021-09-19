package Chachong;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIO {
	
	/**
	 * @param path 文件路径
	 * @return 字符串形式文件内容
	 */
	public static String FileIn(String path) {
		File file = new File(path);
		String str = "", str1 = "";
		try {
			FileInputStream file1 = new FileInputStream(file);
			InputStreamReader file2 = new InputStreamReader(file1,"UTF-8");
			BufferedReader file3 = new BufferedReader (file2);
			while ((str1 = file3.readLine()) != null) {
                str += str1;
            }
			file1.close();
			file2.close();
			file3.close();
			return str;
		} catch (Exception e) {
			System.out.println("输入文件错误！");
			e.printStackTrace();
		}
		return str;
	}
	
	
	
	/**
	 * @param d 相似度
	 * @param path 写入文件路径
	 */
	public static void FileOut(Double d, String path) {
		File file = new File(path);
		FileWriter filewriter = null;
		try {
			filewriter = new FileWriter(file,false);
			filewriter.write(d.toString(), 0, d.toString().length());
			filewriter.close();
		} catch (IOException e) {
			System.out.println("文本内容错误，异常！");
			e.printStackTrace();
		}		
	}
}
