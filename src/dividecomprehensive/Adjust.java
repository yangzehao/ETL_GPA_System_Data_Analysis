package dividecomprehensive;

import java.io.*;
import jxl.write.Label;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

class Adjust {// 标准化，解决全班不选某限选的问题
	public static void Adjusting(String output3, String biaozhun) {
		try {
			Workbook ibook4 = Workbook.getWorkbook(new File(output3));
			Sheet sheet4 = ibook4.getSheet(0);
			Workbook ibook5 = Workbook.getWorkbook(new File(biaozhun));
			Sheet sheet5 = ibook5.getSheet(0);
			WritableWorkbook wbook2 = Workbook
					.createWorkbook(new File(output3));
			File file = new File(output3);
			WritableSheet wsheet2 = wbook2.createSheet(file.getName(), 0);
			for (int i = 3; i < sheet5.getColumns(); i++) {
				wsheet2.addCell(new Label(i, 0, sheet5.getCell(i, 0)
						.getContents()));
			}

			String type3, type4;
			int m=0;
			int limitcolumn = sheet4.getColumns();
			for (int i1 = 3; i1 < wsheet2.getColumns(); i1++) {
				for (int i2 = 3; i2 < sheet4.getColumns(); i2++) {
					type3 = wsheet2.getCell(i1, 0).getContents();
					type4 = sheet4.getCell(i2, 0).getContents();

					if (type3.equals(type4)) {
						for (int j1 = 1; j1 < sheet4.getRows(); j1++) {
							wsheet2.addCell(new Label(i1, j1, sheet4.getCell(
									i2, j1).getContents()));

						}

					}
				}
			}

			// addLeft two columns
			for (int i3 = 0; i3 < 3; i3++) {
				for (int j2 = 0; j2 < sheet4.getRows(); j2++) {
					wsheet2.addCell(new Label(i3, j2, sheet4.getCell(i3, j2)
							.getContents()));

				}
			}
			// addCredit Right four columns

			int s = limitcolumn;
			int s1 = wsheet2.getColumns();
			int r1 = sheet4.getRows();
			for (int i4 = s1; i4 < s1 + 4; i4++) {
				for (int j3 = 0; j3 < r1; j3++) {

					wsheet2.addCell(new Label(i4, j3, sheet4.getCell(s - 4, j3)
							.getContents()));

				}
				s++;// ，我开始把s++写在了上一个循环里，在这一点我花了4个小时左右徘徊，告诉我一个道理，for循环日后一定要详细检查
			}// 还有就是一个for循环假设计算n1次，内嵌for循环假设计算n2次，那么将s++放在外for循环计算n1次，放在内for循环就会计算n1*n2次
			wbook2.write();
			wbook2.close();
			ibook4.close();
			ibook5.close();
		} catch (Exception e) {
			System.out.println("sorry3，wrong");
			System.out.println(e);
		}
	}
}
