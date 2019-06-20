package com.demo;

import com.alibaba.fastjson.JSON;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class generatePdf {

    public static void main(String[] args) throws Exception {
        User user =new User();
        user.setId(10);
        user.setAge(34);
        user.setUsername("userName");
        user.setJsonStr("{\"id\": \"2133\",\"name\": \"testName\",\"age\": \"123a哈哈e\"}");
        Document document = new Document();
        BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        File file = new File("C:\\Users\\Administrator\\Desktop\\桌面文件1");
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        try {
            PdfWriter instance = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Administrator\\Desktop\\桌面文件1\\1.pdf"));
            document.open();
            String str = user.getJsonStr();
            Map mapType = JSON.parseObject(str,LinkedHashMap.class);
            int i = mapType.size();
            for (Object o : mapType.keySet()) {
                System.out.println(o+":" +mapType.get(o));
            }
            PdfPTable table = new PdfPTable(3+i);

            document.add(new Paragraph("hello word"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
            System.out.println("OK");
        }

    }
}
