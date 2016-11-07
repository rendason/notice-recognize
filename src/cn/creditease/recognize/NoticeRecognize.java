package cn.creditease.recognize;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Dason on 2016/11/7.
 */
public class NoticeRecognize {

    private static final String BASE_PATH = "F:\\dason\\incision\\testcase1";

    public static void main(String[] args) throws IOException {
        File file = new File(BASE_PATH, "xyzg_1.jpg");
        boolean[][] data = ImageLoader.load(file);
        Map<String, List<Rectangle>> map = new ImageSegmentor(data).segment();
        for (String key : map.keySet()) {
            if ("nameRectangleList".equals(key)) continue;
            StringBuffer content = new StringBuffer(key);
            content.append('：');
            for (Rectangle word : map.get(key)) {
                save(data, word);
                String font = FontLib.getFont(data, word);
                if (font == null) {
                    BufferedImage bi = getBufferedImage(data, word);
                    font = getOcrContent(bi);
                }
                content.append(font);
            }
            System.out.println(content.toString());
            }
    }

    private static BufferedImage getBufferedImage(boolean[][] data, Rectangle rect){
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        BufferedImage bi = new BufferedImage(width , height, BufferedImage.TYPE_INT_BGR);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bi.setRGB(i, j, data[x+i][y+j] ? 0 : 0xFFFFFF);
            }
        }
        return bi;
    }

    private static String getOcrContent(BufferedImage bi) {
        Tesseract tess = new Tesseract();
        tess.setLanguage("chi_sim");
        String result = "";
        try {
            result = tess.doOCR(bi).replace(" ", "").trim();
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        if ("".equals(result)) result = "□";
        return result;
    }

    private static void save(boolean[][] data, Rectangle rect) throws IOException{
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        BufferedImage bi = new BufferedImage(width , height, BufferedImage.TYPE_INT_BGR);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bi.setRGB(i, j, data[x+i][y+j] ? 0 : 0xFFFFFF);
            }
        }
        ImageIO.write(bi, "jpg", new File(BASE_PATH, System.currentTimeMillis() + ".jpg"));
    }


}
