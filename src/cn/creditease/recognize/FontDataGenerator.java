package cn.creditease.recognize;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dason on 2016/11/7.
 */
public class FontDataGenerator {

    public static void main(String[] args) {
        File path = new File("F:\\dason\\incision\\data");
        File[] files = path.listFiles();
        try {
            for (File file : files) {
                String font = file.getName();
                font = font.substring(0, font.lastIndexOf('.'));
                boolean[][] data = ImageLoader.load(file);
                String digest = FontLib.getDigest(data);
                System.out.println(String.format("FONT_MD5_MAP.put(\"%s\", \"%s\");", digest, font));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
