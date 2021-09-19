package Chachong;

import java.text.DecimalFormat;

public class Hamming {
    /**
     * 输入两个hash值，计算它们的海明距离
     * @param hash1
     * @param hash2
     * @return 海明距离
     */
    public static int getDistance(String hash1, String hash2) {
        int distance = 0;
        if (hash1.length() != hash2.length()) {
            // 不相等则返回-1
            distance = -1;
        } else {
            for (int i = 0; i < hash1.length(); i++) {
                // 每一位进行比较
                if (hash1.charAt(i) != hash2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }

    /**
     * @param hash1
     * @param hash2
     * @return 相似度
     */
    public static double getSimilarity(String hash1, String hash2) {
        int distance = getDistance(hash1, hash2);
        DecimalFormat format = new DecimalFormat("#.00");
        String similarity = format.format(0.01 * (100 - distance * 100 / 128));
        return Double.parseDouble(similarity);
    }
}