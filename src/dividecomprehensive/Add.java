package dividecomprehensive;

import java.io.*;
import jxl.write.Label;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

class Add {// ����ͷ��
	public static void Adding(String output1) { // ��������������File���ֺ����File����
		try {

			Workbook ibook1 = Workbook.getWorkbook(new File(output1));// File�����е�inputFile��·��������ǰ���new��Ϊibook���ٿռ�,inputFile����������
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
			WritableSheet wsheet2 = wbook1.createSheet(output1, 2);// ��ʵ��ʱ�ڶ���������0��1������ͬ������
			for (int i = basecolumnNum1; i < limitcolumnNum2; i++) {
				for (int j = baseNum2; j < limitrow2; j++) {
					wsheet2.addCell(new Label(i, j, sheet1.getCell(i, j)
							.getContents()));
				}
			}

			wsheet2.addCell(new Label(0, 0, "�༶"));
			wsheet2.addCell(new Label(1, 0, "ѧ��"));
			wsheet2.addCell(new Label(2, 0, "����"));
			wsheet2.addCell(new Label(wsheet2.getColumns() - 4, 0, "ѧ�ּ�"));
			wsheet2.addCell(new Label(wsheet2.getColumns() - 3, 0, "��ѡ��ѧ��"));
			wsheet2.addCell(new Label(wsheet2.getColumns() - 2, 0, "��ѧ�ּ�"));
			wsheet2.addCell(new Label(wsheet2.getColumns() - 1, 0, "���޼���ѡ��ѧ��"));

			// arg()ָ����������������������
			wbook1.write();
			wbook1.close();
			ibook1.close();
			/* ibook2.close(); */

		} catch (Exception e) {
			System.out.println("sorry1��wrong");
			System.out.println(e);
		}
	}
}
