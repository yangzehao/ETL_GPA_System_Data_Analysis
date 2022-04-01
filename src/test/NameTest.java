package test;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

public class NameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("石工08级输出成绩目录");
		File file1[];

		file1 = file.listFiles();
		try{
			for(int i=0;i<file1.length;i++){
		Workbook book1=Workbook.getWorkbook(file1[i]);
			Sheet sheet1=book1.getSheet(0);
			for(int j=1;j<sheet1.getRows();j++){
				if(sheet1.getCell(2, j).getContents().contains("陈晓明")){
					System.out.println(sheet1.getCell(2, j).getContents());
					System.out.println(file1[i].getName());
				}
			}
			book1.close();
			}
		}catch(Exception a){
			System.out.print("wrong");
		}
	}

}
