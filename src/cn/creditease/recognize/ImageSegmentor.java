package cn.creditease.recognize;

import java.awt.Rectangle;
import java.util.*;

/**
 * Created by Dason on 2016/11/7.
 */
public class ImageSegmentor {

    private final boolean[][] data;

    public ImageSegmentor(boolean[][] data) {
        this.data = data;
    }
    /*private static int[] histogramX(boolean [][] data) {
        return histogramX(data, new Rectangle(0, 0, data.length, data[0].length));
    }*/

    private int[] histogramX(Rectangle rect) {
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        int[] histogram = new int[width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                histogram[i] += data[x+i][y+j] ? 1 : 0;
            }
        }
        return histogram;
    }

    private int[] histogramY() {
        return histogramY(new Rectangle(0, 0, data.length, data[0].length));
    }

    private int[] histogramY(Rectangle rect) {
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        int[] histogram = new int[height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                histogram[j] += data[x+i][y+j] ? 1 : 0;
            }
        }
        return histogram;
    }

    private List<Rectangle> getRows() {
        int width = data.length;
        int height = data[0].length;
        int[] histogram = histogramY();
        List<Rectangle> rows = new ArrayList<>();
        int top = 0, bottom = 0;
        while (bottom < height) {
            top = bottom;
            while (top < height && histogram[top] == 0) top++;
            if (top == height) break;
            bottom = top;
            while (bottom < height && histogram[bottom] > 0) bottom++;
            rows.add(trimX(new Rectangle(0, top, width, bottom - top)));
        }
        return rows;
    }

    private List<Rectangle> getCells(Rectangle rect, int cellWidth) {
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        int[] histogram = histogramX(rect);
        List<Rectangle> cols = new ArrayList<>();
        int left = 0, right = 0;
        while (right < width) {
            left = right;
            while (left < width && histogram[left] == 0) left++;
            if (left == width) break;
            right = left;
            boolean contain = false;
            int lastBlank = left;
            while (right <= width) {
                if (right == width || histogram[right] == 0) {
                    Rectangle r = new Rectangle(x+lastBlank, y, right-lastBlank, height);
                    if (FontLib.getFont(data, trim(r)) != null) {
                        contain = true;
                        break;
                    }
                    lastBlank = right;
                }
                if (right == width) break;
                right++;
            }
            if (contain) {
                Rectangle r = new Rectangle(x+left, y, lastBlank-left, height);
                cols.addAll(getCols(r, cellWidth));
                r = new Rectangle(x + lastBlank, y, right - lastBlank, height);
                cols.add(trim(r));
            } else if (right - left >= 2 * cellWidth) {
                Rectangle r = new Rectangle(x+left, y, right-left, height);
                cols.addAll(getCols(r, cellWidth));
            }
        }
        return cols;
    }

    private List<Rectangle> getCols(Rectangle rect, int cellWidth) {
        rect = trimX(rect);
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        List<Rectangle> cells = new ArrayList<>();
        int off = 0;
        for (; off + cellWidth < width; off += cellWidth) {
            cells.add(trimY(new Rectangle(x + off, y, cellWidth, height)));
        }
        if (off < width - 1 && off + cellWidth >= width - 1) {
            cells.add(trimY(new Rectangle(x + off, y, width - off, height)));
        }
        return cells;
    }

    private Rectangle trimX(Rectangle rect) {
        int[] histogram = histogramX(rect);
        int left = 0, right = histogram.length - 1;
        while (left < histogram.length && histogram[left] == 0) left++;
        while (right > left && histogram[right] == 0) right--;
        return new Rectangle((int) rect.getX() + left, (int) rect.getY(),
                right - left + 1, (int) rect.getHeight());
    }

    private Rectangle trimY(Rectangle rect) {
        int[] histogram = histogramY(rect);
        int top = 0, bottom = histogram.length - 1;
        while (top < histogram.length && histogram[top] == 0) top++;
        while (bottom > top && histogram[bottom] == 0) bottom--;
        return new Rectangle((int) rect.getX(), (int) rect.getY() + top,
                (int) rect.getWidth(), bottom - top + 1);
    }

    private Rectangle trim(Rectangle rect) {
        return trimX(trimY(rect));
    }

    private int indexOfColon(Rectangle rect) {
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        //int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        final boolean[][] colon = {
                {false, false, false, false, false, false, false, false, false, false},
                {false, true , true , false, false, false, false, true , true , false},
                {false, true , true , false, false, false, false, true , true , false},
                {false, true , true , false, false, false, false, true , true , false}
        };
        int[] colonHist = new int[colon.length];
        for (int i = 0; i < colonHist.length; i++) {
            for (int j = 0; j < colon[i].length; j++) {
                if (colon[i][j]) colonHist[i]++;
            }
        }
        int[] rowHist = histogramX(rect);
        outer :
        for (int i = 0; i <= rowHist.length - colon.length; i++) {
            for (int j = 0; j < colonHist.length; j++) {
                if (rowHist[i+j] != colonHist[j]) continue outer;
            }
            inner :
            for (int k = 0; k <= height - colon[0].length; k++) {
                for (int s = 0; s < colon.length; s++) {
                    for (int r = 0; r < colon[s].length; r++) {
                        if (data[x+i+s][y+k+r] != colon[s][r]) continue inner;
                    }
                }
                return i;
            }
        }
        return -1;
    }

    public Map<String, List<Rectangle>> segment() {
        List<Rectangle> rows = getRows();
        Map<String, List<Rectangle>> map = new HashMap<>();
        List<Rectangle> nameRectList = new ArrayList<>();
        map.put("nameRectangleList", nameRectList);
        String lastName = "标题";
        for (Rectangle rect : rows) {
            int index = indexOfColon(rect);
            if (index != -1) {
                Rectangle nameRect = trim(new Rectangle((int) rect.getX(), (int) rect.getY(),
                                        index, (int) rect.getHeight()));
                nameRectList.add(nameRect);
                lastName = FontLib.getFont(data, nameRect);
                if (index + 4 < rect.getWidth()) {
                    List<Rectangle> list = new ArrayList<>();
                    Rectangle contentRect = trim(new Rectangle((int) rect.getX() + index + 4, (int) rect.getY(),
                                                        (int) rect.getWidth() - index - 4, (int) rect.getHeight()));
                    list.addAll(getCells(contentRect, 14));
                    map.put(lastName, list);
                } else {
                    map.put(lastName, new ArrayList<>());
                }
            } else {
                if (rect.getHeight() > 15) {
                    List<Rectangle> list = new ArrayList<>();
                    list.addAll(getCells(rect, 19));
                    map.put(lastName, list);
                }
                else {
                    List<Rectangle> lastRow = map.get(lastName);
                    if (lastName != null) {
                        lastRow.addAll(getCells(rect, 14));
                    }
                }
            }
        }
        return map;
    }
}
