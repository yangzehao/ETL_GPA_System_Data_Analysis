package lastcomprehensive;

import java.io.*;
import jxl.Workbook;
import jxl.Sheet;
import jxl.write.*;

class Aggregate {
	WritableWorkbook wbook1;

	public static void Aggregating(String xinxi, String zhonghui) {
		try {
			Workbook book1 = Workbook.getWorkbook(new File(xinxi));
			Sheet sheet1 = book1.getSheet(0);
			Workbook book2 = Workbook.getWorkbook(new File(zhonghui));
			Sheet sheet2 = book2.getSheet(0);
			WritableWorkbook wbook1 = Workbook
					.createWorkbook(new File(zhonghui));// 多次在一个文件中写入文件的话，File中的名字换的话，那么就先把它的以前的内存释放再写入，如果是写入同一个名字的文件的话，该文件里的东西是消失的
			WritableSheet wsheet1 = wbook1.createSheet(zhonghui, 0);// WritableWorkbook与Workbook是共生的，但可以有Workbook无WritableWorkbook,前者才有数，后者是一个空的表，与Workbook对应，前者赋值，后者可作修改
			for (int m = 0; m < sheet2.getColumns(); m++) {
				for (int m1 = 0; m1 < sheet2.getRows(); m1++) {
					wsheet1.addCell(new Label(m, m1, sheet2.getCell(m, m1)
							.getContents()));
				}
			}
			int rowNum1, rowNum2, rowColumn;// 日后再引用wsheet1.getRows(),和wsheet1.getColumns()一定要把它们先付给几个参数，不然它容易本生数上增加
			rowNum1 = wsheet1.getRows();// 32
			rowNum2 = sheet1.getRows();// 28
			rowColumn = sheet1.getColumns();// 19
			for (int i = 0; i < rowColumn; i++) {
				for (int j = rowNum1; j < (rowNum1 + rowNum2) - 1; j++) {
					wsheet1.addCell(new Label(i, j, sheet1.getCell(i,
							j - rowNum1 + 1).getContents()));
					// 这个地方我花了一晚上，日后我要多练循环
				}
			}

			wbook1.write();
			wbook1.close();
			book1.close();
			book2.close();

		} catch (Exception e) {
			System.out.println("sorry1，wrong");
			System.out.println(e);
		}
	}
}
