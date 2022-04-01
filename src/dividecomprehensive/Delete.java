package dividecomprehensive;

import java.io.*;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

class Delete {// 删除非本学期课程,和俄语问题

	private static int russiacolumn;
	private static int englishcolumn;

	/**
	 * @param inputFile
	 * @param outputFile
	 */
	/*
	 * 记住，计算机所有的操作是在计算机内存中的操作， 不读入内存是无法操作的（内存的出现形式可自己判定，如本编辑表中是一个
	 * 虚拟的二维数组表，也可以说是虚拟excel表）！！！； 只要是类的具体事例赋值一定要同过new，它是用来开辟空间的
	 */
	public static void Deleting(String inputFile, String outputFile,
			String biaozhun) { // 出过来的是输入File名字和输出File名字*/
		try {
			Workbook ibook = Workbook.getWorkbook(new File(inputFile));// File函数中的inputFile是路径名；最前面的new是为ibook开辟空间,inputFile中已有数据
			Sheet sheet = ibook.getSheet(0);
			/*
			 * getsheet(“表的参数，如表1， 表2, 现在这里面的0代表excel表1”)
			 */
			// row 2nd [1], the type of course

			int baseColumn = 0;

			// create the output file
			WritableWorkbook wbook = Workbook.createWorkbook(new File(
					outputFile));// 将outFile的所有数据导入wbook，使之初始化
			WritableSheet wsheet = wbook.createSheet(inputFile, 0);// 建立具体的sheet对象，后面通过createSheet方法为之赋值；括号里的是（表明，表一）

			for (int i = 0; i < sheet.getColumns() - 4; i++) {
				for (int j = 0; j < sheet.getRows(); j++) {
					wsheet.addCell(new Label(i, j, sheet.getCell(i, j)
							.getContents()));
				}
			}
			// delete
			Workbook ibook2 = Workbook.getWorkbook(new File(biaozhun));
			Sheet sheet2 = ibook2.getSheet(0);
			String exchangecolumn;
			String exchangecolumn1;
			String bijiao1;
			String bijiao2;

			for (int i = baseColumn + 3; i < wsheet.getColumns(); i++) {
				exchangecolumn1 = sheet.getCell(i, 0).getContents();
				if (exchangecolumn1.indexOf("基础俄语") >= 0) {
					russiacolumn = i;
				}
				exchangecolumn = sheet.getCell(i, 0).getContents();
				if (exchangecolumn.indexOf("基础外语") >= 0) {
					englishcolumn = i;
				}
			}

			for (int i = 4; i < wsheet.getRows(); i++) {
				bijiao1 = wsheet.getCell(englishcolumn, i).getContents();
				bijiao2 = wsheet.getCell(russiacolumn, i).getContents();
				if (bijiao1 == "" && bijiao2 != "") {// ""代表空的格子
					wsheet.addCell(new Label(englishcolumn, i, bijiao2));
				}
			}
			String type1;
			String type2;
			String type3;
			for (int m = baseColumn + 3; m < wsheet.getColumns(); m++) {

				int d = 0;
				for (int j = 3; j < sheet2.getColumns(); j++) {
					type1 = sheet.getCell(m, 0).getContents();

					type2 = sheet2.getCell(j, 0).getContents();

					type3 = sheet.getCell(m, 1).getContents();
					if (type1.equals(type2) || type3.contains("任选")
							|| type3.contains("限选")) {// 对于字符串型，不能直接相等，这个错误让我思索了1天，试验了上千次，所以一定要记住啊，要用String
														// s1.equal(s2),它返回一个布尔型
						d++;
					}
				}

				if (d == 0) {

					wsheet.addCell(new Label(m, 0, ""));

				}

			}
			String jiuxin;
			for (int i1 = baseColumn; i1 < wsheet.getColumns() - 5; i1++) {

				jiuxin = wsheet.getCell(i1, 0).getContents();
				if (jiuxin == "")
					wsheet.removeColumn(i1);
				

			}

			wbook.write();
			wbook.close();

			ibook.close();
			ibook2.close();
		} catch (Exception e) {
			System.out.println("sorry，wrong");
			System.out.println(e);
		}

	}
}
