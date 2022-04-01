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
					.createWorkbook(new File(zhonghui));// �����һ���ļ���д���ļ��Ļ���File�е����ֻ��Ļ�����ô���Ȱ�������ǰ���ڴ��ͷ���д�룬�����д��ͬһ�����ֵ��ļ��Ļ������ļ���Ķ�������ʧ��
			WritableSheet wsheet1 = wbook1.createSheet(zhonghui, 0);// WritableWorkbook��Workbook�ǹ����ģ���������Workbook��WritableWorkbook,ǰ�߲�������������һ���յı���Workbook��Ӧ��ǰ�߸�ֵ�����߿����޸�
			for (int m = 0; m < sheet2.getColumns(); m++) {
				for (int m1 = 0; m1 < sheet2.getRows(); m1++) {
					wsheet1.addCell(new Label(m, m1, sheet2.getCell(m, m1)
							.getContents()));
				}
			}
			int rowNum1, rowNum2, rowColumn;// �պ�������wsheet1.getRows(),��wsheet1.getColumns()һ��Ҫ�������ȸ���������������Ȼ�����ױ�����������
			rowNum1 = wsheet1.getRows();// 32
			rowNum2 = sheet1.getRows();// 28
			rowColumn = sheet1.getColumns();// 19
			for (int i = 0; i < rowColumn; i++) {
				for (int j = rowNum1; j < (rowNum1 + rowNum2) - 1; j++) {
					wsheet1.addCell(new Label(i, j, sheet1.getCell(i,
							j - rowNum1 + 1).getContents()));
					// ����ط��һ���һ���ϣ��պ���Ҫ����ѭ��
				}
			}

			wbook1.write();
			wbook1.close();
			book1.close();
			book2.close();

		} catch (Exception e) {
			System.out.println("sorry1��wrong");
			System.out.println(e);
		}
	}
}
