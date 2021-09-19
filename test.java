package Chachong;



public class test {
	/**
	 * 测试FileIO类
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "";
		FileIO.FileOut(1.08,str);
		String s = FileIO.FileIn(str);
		System.out.println("路径为" + str + "的文本的内容是：" + s);
		
	}
	
}
