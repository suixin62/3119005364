package Chachong;

import java.io.File;
import java.text.DecimalFormat;

public class Main {
	public static void main(String[] args) {
		File file1 = new File (args[0]);
		File file2 = new File (args[1]);
		File file0 = new File (args[2]);
		double d ;
		String str = FileIO.FileIn(args[0]);
		String str1 = FileIO.FileIn(args[1]);
		String hash1 = HashCompute.SimHash(str);
		String hash2 = HashCompute.SimHash(str1);
		DecimalFormat format = new DecimalFormat("#.00");
		d = Hamming.getSimilarity( hash1,  hash2) ;
		FileIO.FileOut(d, args[2]);
		System.out.println(file1.getName() + "  和  " + file2.getName() + "  的相似度为：" + d);
	
	}
}
