package cn.itcast.bos.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

public class POITest {
	@Test
	public void demoTest() {
		try {
			//1.获取整个excel文档对象
			//2.获取HSSFWorkBook对象
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(new File("E:\\00000000000000000速运快递\\资料\\day04\\03_区域测试数据\\区域导入测试数据.xls")));
			//3.通过workbook对象获取sheet页
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				HSSFSheet sheetAt = wb.getSheetAt(i);
				//4.通过sheet页循环获取row行
				for (Row row : sheetAt) {
					//5.获取row行中的单元格
					if (row.getRowNum()==0) {
						continue;
					}
					String id = row.getCell(0).getStringCellValue();
					String province = row.getCell(1).getStringCellValue();
					String city = row.getCell(2).getStringCellValue();
					String district = row.getCell(3).getStringCellValue();
					String postcode = row.getCell(4).getStringCellValue();
					//6.打印结果
					System.out.println(id+":="+province+":="+city+":="+district+":="+postcode);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
