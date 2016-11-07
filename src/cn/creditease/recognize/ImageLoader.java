package cn.creditease.recognize;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dason on 2016/11/7.
 */
public class ImageLoader {

    private static final int DEFAULT_THRESHOLD = 180;

    public static boolean[][] load(File file) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        int width = bi.getWidth();
        int height = bi.getHeight();
        boolean[][] data = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bi.getRGB(i, j);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                int gray = (int)(0.299 * red + 0.587 * green + 0.114 * blue) & 0xFF;
                data[i][j] = gray < DEFAULT_THRESHOLD ? true : false;
            }
        }

        return data;
    }
    public static boolean[][] load(String fileName) throws IOException{
        return load(new File(fileName));
    }

}
