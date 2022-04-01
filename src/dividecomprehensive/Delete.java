package dividecomprehensive;

import java.io.*;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

class Delete {// ɾ���Ǳ�ѧ�ڿγ�,�Ͷ�������

	private static int russiacolumn;
	private static int englishcolumn;

	/**
	 * @param inputFile
	 * @param outputFile
	 */
	/*
	 * ��ס����������еĲ������ڼ�����ڴ��еĲ����� �������ڴ����޷������ģ��ڴ�ĳ�����ʽ���Լ��ж����籾�༭������һ��
	 * ����Ķ�ά�����Ҳ����˵������excel���������� ֻҪ����ľ���������ֵһ��Ҫͬ��new�������������ٿռ��
	 */
	public static void Deleting(String inputFile, String outputFile,
			String biaozhun) { // ��������������File���ֺ����File����*/
		try {
			Workbook ibook = Workbook.getWorkbook(new File(inputFile));// File�����е�inputFile��·��������ǰ���new��Ϊibook���ٿռ�,inputFile����������
			Sheet sheet = ibook.getSheet(0);
			/*
			 * getsheet(����Ĳ��������1�� ��2, �����������0����excel��1��)
			 */
			// row 2nd [1], the type of course

			int baseColumn = 0;

			// create the output file
			WritableWorkbook wbook = Workbook.createWorkbook(new File(
					outputFile));// ��outFile���������ݵ���wbook��ʹ֮��ʼ��
			WritableSheet wsheet = wbook.createSheet(inputFile, 0);// ���������sheet���󣬺���ͨ��createSheet����Ϊ֮��ֵ����������ǣ���������һ��

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
				if (exchangecolumn1.indexOf("��������") >= 0) {
					russiacolumn = i;
				}
				exchangecolumn = sheet.getCell(i, 0).getContents();
				if (exchangecolumn.indexOf("��������") >= 0) {
					englishcolumn = i;
				}
			}

			for (int i = 4; i < wsheet.getRows(); i++) {
				bijiao1 = wsheet.getCell(englishcolumn, i).getContents();
				bijiao2 = wsheet.getCell(russiacolumn, i).getContents();
				if (bijiao1 == "" && bijiao2 != "") {// ""����յĸ���
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
					if (type1.equals(type2) || type3.contains("��ѡ")
							|| type3.contains("��ѡ")) {// �����ַ����ͣ�����ֱ����ȣ������������˼����1�죬��������ǧ�Σ�����һ��Ҫ��ס����Ҫ��String
														// s1.equal(s2),������һ��������
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
			System.out.println("sorry��wrong");
			System.out.println(e);
		}

	}
}
