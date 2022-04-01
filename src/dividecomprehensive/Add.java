package dividecomprehensive;

import java.io.*;
import jxl.write.Label;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

class Add {// 加入头名
	public static void Adding(String output1) { // 出过来的是输入File名字和输出File名字
		try {

			Workbook ibook1 = Workbook.getWorkbook(new File(output1));// File函数中的inputFile是路径名；最前面的new是为ibook开辟空间,inputFile中已有数据
			Sheet sheet1 = ibook1.getSheet(0);
			/*
			 * Workbook ibook2=Workbook.getWorkbook(new File(biaozhun)); Sheet
			 * sheet2=ibook2.getSheet(0);
			 */

			int limitcolumnNum2 = sheet1.getColumns();
			int basecolumnNum1 = 0;
			int rowNum2 = sheet1.getRows();
			int limitrow2 = rowNum2;
			int baseNum2 = 0;
			WritableWorkbook wbook1 = Workbook
					.createWorkbook(new File(output1));
			WritableSheet wsheet2 = wbook1.createSheet(output1, 2);// 在实验时第二个参数给0或1均是相同输出结果
			for (int i = basecolumnNum1; i < limitcolumnNum2; i++) {
				for (int j = baseNum2; j < limitrow2; j++) {
					wsheet2.addCell(new Label(i, j, sheet1.getCell(i, j)
							.getContents()));
				}
			}

			wsheet2.addCell(new Label(0, 0, "班级"));
			wsheet2.addCell(new Label(1, 0, "学号"));
			wsheet2.addCell(new Label(2, 0, "姓名"));
			wsheet2.addCell(new Label(wsheet2.getColumns() - 4, 0, "学分绩"));
			wsheet2.addCell(new Label(wsheet2.getColumns() - 3, 0, "公选课学分"));
			wsheet2.addCell(new Label(wsheet2.getColumns() - 2, 0, "总学分绩"));
			wsheet2.addCell(new Label(wsheet2.getColumns() - 1, 0, "必修加限选总学分"));

			// arg()指的是填入变量，或变量常数
			wbook1.write();
			wbook1.close();
			ibook1.close();
			/* ibook2.close(); */

		} catch (Exception e) {
			System.out.println("sorry1，wrong");
			System.out.println(e);
		}
	}
}
