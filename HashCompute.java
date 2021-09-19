package Chachong;

import com.hankcs.hanlp.HanLP;//导入外部包
import java.math.BigInteger;
import java.util.List;



public class HashCompute {

    private static int hashbit = 64;

    /**
     * 对单个的分词进行hash计算
     *
     * @param word 传入的String类型的单词
     * @return 返回word的hash值
     */
    public static String getHash(String word) {

        if (word == null || word.length() == 0) {
            return new BigInteger("0").toString(2);
        } else {
            /**
             * 当 str 的长度过短，会导致hash算法失效，需要对过短的词补偿
             */
            while (word.length() < 3) {
                word = word + word.charAt(0);
            }
            char[] Array = word.toCharArray();
            BigInteger x = BigInteger.valueOf(((long) Array[0]) << 7);
            BigInteger m = new BigInteger("1000003");
            BigInteger mask = new BigInteger("2").pow(hashbit).subtract(new BigInteger("1"));
            for (char item : Array) {
                BigInteger temp = BigInteger.valueOf((long) item);
                x = x.multiply(m).xor(temp).and(mask);
            }
            x = x.xor(new BigInteger(String.valueOf(word.length())));
            if (x.equals(new BigInteger("-1"))) {
                x = new BigInteger("-2");
            }
            return x.toString(2);
        }
    }

    /**
     * 计算整个字符串文本的hash值
     *
     * @param text 传入的字符串类型的文本
     * @return 返回text的hash值
     */
    public static String SimHash(String text) {

        int[] v = new int[hashbit];
        // 1、分词（使用了外部依赖hankcs包提供的接口）

        List<String> keywordList = HanLP.extractKeyword(text, text.length());//这一步是取出所有关键词
        int size = keywordList.size();
        int i = 0;
        for (String keyword : keywordList) {
        // 2、获取每个单词的hash值

            String keywordHash = getHash(keyword);
            if (keywordHash.length() < 64) {
                // hash值少于64位的话，就会在低位以0补齐
                int dif = 64 - keywordHash.length();
                for (int j = 0; j < dif; j++) {
                    keywordHash += "0";
                }
            }
        // 3、加权、合并

            for (int j = 0; j < v.length; j++) {
                // 对keywordHash的每一位与'1'进行比较
                if (keywordHash.charAt(j) == '1') {
                    //权重分10级，由词频从高到低，取权重10~0
                    v[j] += (10 - (i / (size / 10)));
                } else {
                    v[j] -= (10 - (i / (size / 10)));
                }
            }
            i++;
        }
        //降维
        String simHash = "";// 储存返回的hash值
        for (int j = 0; j < v.length; j++) {
            if (v[j] <= 0) {
                simHash += "0";
            } else {
                simHash += "1";
            }
        }
        return simHash;
    }
}
    
