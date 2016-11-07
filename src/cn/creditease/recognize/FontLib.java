package cn.creditease.recognize;

import org.apache.commons.codec.digest.DigestUtils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dason on 2016/11/7.
 */
public class FontLib {

    private static final Map<String, String> FONT_MD5_MAP = new HashMap<>();

    static {
        FONT_MD5_MAP.put("664366063db9ec2b343cd7af2c39269d", "0");
        FONT_MD5_MAP.put("151c72efe6d2be53a660241946e976ab", "1");
        FONT_MD5_MAP.put("19dd1eb58e07f925f043f0e32925202a", "2");
        FONT_MD5_MAP.put("88aac44eaacf1a9bb990543436f5e76e", "3");
        FONT_MD5_MAP.put("15e554671714e74b3efd663d14f34fc1", "4");
        FONT_MD5_MAP.put("06bbd73d9ba658f4a273d98246764379", "5");
        FONT_MD5_MAP.put("6a2ccfd0d0aa99bb21326f67fade869f", "6");
        FONT_MD5_MAP.put("0270940e971979eda6a481792c8030bb", "7");
        FONT_MD5_MAP.put("c131d91f15f0981aa0b0360fb8ef93fc", "8");
        FONT_MD5_MAP.put("d9740824faf731dfdbf8c17456009475", "9");
        FONT_MD5_MAP.put("0793779fd1d0fd2f86f627dc99caa394", "A");
        FONT_MD5_MAP.put("5553d730d9f83c4b72c4b3625fc8c804", "B");
        FONT_MD5_MAP.put("0944a08977b1a28c36a3ad2d4b50d8d7", "C");
        FONT_MD5_MAP.put("012b46c462020e9e24eae7bf6dbdf423", "D");
        FONT_MD5_MAP.put("61415c2a4b59bf0569de2a3234d84e2a", "E");
        FONT_MD5_MAP.put("b2ffda9712ca528a14fde15364df11e7", "F");
        FONT_MD5_MAP.put("fd1da52945556347ca94f7d11d4a5dd2", "G");
        FONT_MD5_MAP.put("d11d9c7caf981341f8e40196e05b65b3", "H");
        FONT_MD5_MAP.put("4052f0848f1b8de11db694ffec7adcd6", "J");
        FONT_MD5_MAP.put("c970c036b5e094c71d1414cc54afd2b6", "K");
        FONT_MD5_MAP.put("783e25f63ec136cbdead8acdf76cff4b", "L");
        FONT_MD5_MAP.put("1ee3569bc1fadaa50640b904037a23e2", "M");
        FONT_MD5_MAP.put("ec64ff07c0c0c899d5f0976d65706577", "N");
        FONT_MD5_MAP.put("d0d7b04d62176165353d25314b804726", "P");
        FONT_MD5_MAP.put("710fc58c8601ef68d0e02b23ea5a69e5", "Q");
        FONT_MD5_MAP.put("6a5cb56febdac659fb081213cee2cb1c", "R");
        FONT_MD5_MAP.put("67c73bd06cf4be424d5bb4083d7d33c3", "T");
        FONT_MD5_MAP.put("ea57575c2ed0dfcb22c13a5a545f47e4", "U");
        FONT_MD5_MAP.put("0c718c454dedad5edc64522e55f0ebc1", "W");
        FONT_MD5_MAP.put("6ae04fe1816c6a2c4f674cf5936aa39c", "X");
        FONT_MD5_MAP.put("1e748747bf1e51328b9afeff3a59405f", "Y");
        FONT_MD5_MAP.put("97af80a8b86003cbc73a25073996a543", "********");
        FONT_MD5_MAP.put("2844a9accde42f56d48e671c0fd3eb5b", "********4");
        FONT_MD5_MAP.put("0290a09d67739bb3a69914532acfaf2d", "-");
        FONT_MD5_MAP.put("45685ed202fb0aaf4931de86514e5a49", "/");
        FONT_MD5_MAP.put("7e7b569a8f23b84880714f59da17f049", "[");
        FONT_MD5_MAP.put("a3c6853d261217f2a0f142f464dc8289", "]");
        FONT_MD5_MAP.put("d508fee81597a2021fcc577646bbee62", ":");
        FONT_MD5_MAP.put("1348c8f428d9a718cf8dd9030aee928f", "：");
        FONT_MD5_MAP.put("2c58766856e3c406baf7f1eb9a87a859", "地方编码");
        FONT_MD5_MAP.put("6f122ef9339bec3c35c86636277830ab", "备注");
        FONT_MD5_MAP.put("1b85c9b04275f701f9218686a14c206f", "审批类别");
        FONT_MD5_MAP.put("9330096548aae4527920eb10881d2589", "居民身份证号");
        FONT_MD5_MAP.put("860a8b28233b66c2809926a751653f39", "工商登记码");
        FONT_MD5_MAP.put("2bf1ce01c0bc5cfd1b5536b7ccc040cc", "数据更新时间戳");
        FONT_MD5_MAP.put("7a1f0d91decf01e61fd8e8ba48335940", "法定代表人姓名");
        FONT_MD5_MAP.put("21e483b92fe7c0ad841a73b9352192eb", "税务登记码");
        FONT_MD5_MAP.put("23ad3bba34854b0ff068b8fff8829323", "第");
        FONT_MD5_MAP.put("00f2b57afc964bfd533081ff4bb7e394", "组织机构代码");
        FONT_MD5_MAP.put("f9cf69682c6db5da1ba498384d225693", "统一社会信用代码");
        FONT_MD5_MAP.put("4b9234e42189d79a4c4882cc0a929d16", "行政相对人名称");
        FONT_MD5_MAP.put("acdee27dc62f8327f953970b264afccc", "行政许可决定文书号");
        FONT_MD5_MAP.put("3e20ad956c1fd21c22219a8d8e124c28", "许可内容");
        FONT_MD5_MAP.put("e3f324b171eadc732a8872c6111a9bbe", "许可决定日期");
        FONT_MD5_MAP.put("eed00a591186fb2dbd0617c179d2a9e8", "许可截止期");
        FONT_MD5_MAP.put("d949d171ad745a58fc7ae8a559f64ad8", "许可机关");
        FONT_MD5_MAP.put("0ae4253bf7b9032102395238d282d9ce", "许可状态");
        FONT_MD5_MAP.put("7bc6c038e06d086bae4bbac6a698fff3", "项目名称");
        FONT_MD5_MAP.put("8b145294b293e216e01184e52fccc12b", "处罚事由");
        FONT_MD5_MAP.put("4e9118e7c57698ed6a6467df74f897b9", "处罚依据");
        FONT_MD5_MAP.put("58270eaff6fab500392d6f5b22e95816", "处罚决定日期");
        FONT_MD5_MAP.put("82671a6cb14909a255f8fc194372f7c3", "处罚名称");
        FONT_MD5_MAP.put("e5afcc1a86fd78744eabad316571ccb5", "处罚机关");
        FONT_MD5_MAP.put("c8f3ccf94b606d1d83996fdebd55175e", "处罚状态");
        FONT_MD5_MAP.put("8a709f4efb0f085777c90930a8a86bc9", "处罚类别1");
        FONT_MD5_MAP.put("f2486afd137bf2ebbc47d5a995fd8c56", "处罚类别2");
        FONT_MD5_MAP.put("7e32df7d4159b4b0a626be32bb1ba516", "处罚结果");
        FONT_MD5_MAP.put("e159a22d1a08c1d035c12106eaeb1a60", "行政处罚决定书文号");
    }

    public static String getFont(String digest) {
        return FONT_MD5_MAP.get(digest);
    }

    public static String getFont(boolean[][] data, Rectangle rect) {
        String digest = getDigest(data, rect);
        return getFont(digest);
    }

    public static String getDigest(boolean[][] data) {
        return getDigest(data, new Rectangle(0, 0, data.length, data[0].length));
    }

    public static String getDigest(boolean[][] data, Rectangle rect) {
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        byte[] arr = new byte[(int) Math.ceil(width * height)];
        for (int i = 0, count = 0; i < width; i++) {
            for (int j = 0; j < height; j++, count++) {
                if (data[x+i][y+j]) {
                    arr[count/8] |=  1 << (7 - count % 8);
                }
            }
        }
        return DigestUtils.md5Hex(arr);

    }
}
