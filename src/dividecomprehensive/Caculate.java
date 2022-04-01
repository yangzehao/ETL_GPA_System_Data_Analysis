package dividecomprehensive;

import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

class Caculate {// ����ɼ�
	private static double OptWeight = 0.003; // ѡ�޿α���
	private static double MajWeight = 1.0000; // ���޿α���
	private static double GOOD = 90; // ����ɼ������㡱Ϊ90
	private static double SOSO = 80;// ����ɼ������á�Ϊ80
	private static double D = 60; // ����ķ���
    private static double N=0;
	public static void Caculte(String output2, String addclass, String biaozhun) {
		try {
			Workbook ibook3 = Workbook.getWorkbook(new File(output2));// File�����е�inputFile��·��������ǰ���new��Ϊibook���ٿռ�,inputFile����������
			Sheet sheet3 = ibook3.getSheet(0);
			File filename=new File(output2);
			String type;
			int baseColumn = 0;// ������ö����������ô����ڲ��öദ�޸�
			WritableWorkbook wbook1 = Workbook
					.createWorkbook(new File(output2));// ��outFile���������ݵ���wbook��ʹ֮��ʼ��
			
			WritableSheet wsheet1 = wbook1.createSheet(filename.getName(), 0);
			int limitColumn = sheet3.getColumns();// ��ʱ���еĲ���Ӧ����������ڴ��в�������������ʵ�ʶ����в������˴�������ʵ�ʵ�excel
			double[] courseWeight = new double[limitColumn];
			for (int i2 = baseColumn + 3; i2 < limitColumn; i2++) {
				type = sheet3.getCell(i2, 1).getContents(); // col, row
				if (type.contains("��ѡ")){ // �жϡ����Ƿ�Ϊ��ѡ
					courseWeight[i2] = OptWeight;
				} else {
					courseWeight[i2] = MajWeight; // �������ö�Ӧ����
				}
			} // ���ֱ��ޣ���ѡ�� ��ѡ�ޣ�ȷ����Ӧ���أ�courseWeight�洢����

			// row 4th, [3], the credit of course
			double[] courseCredit = new double[limitColumn];
			String credit; // ˵����excel�е������������������ַ����͵�
			for (int i3 = baseColumn + 3; i3 < limitColumn; i3++) {
				credit = sheet3.getCell(i3, 3).getContents();
				courseCredit[i3] = Double.parseDouble(credit);
			}

			// top several lines
			int c = baseColumn;
			for (int i4 = baseColumn + 3; i4 < limitColumn; i4++) {
				if (courseWeight[i4] == MajWeight) {
					wsheet1.addCell(new Label(c + 2, 0, sheet3.getCell(i4, 0)
							.getContents()));
					/*
					 * Labelǰ��������Ϊ������λ�ã����һ������ ��õ����ݣ��������������string��������ǵ�����������
					 * eg:WritableCellFormat integerFormat = new
					 * WritableCellFormat (NumberFormats.INTEGER); Number
					 * number2 = new Number(0, 4, 3.141519, integerFormat);
					 * sheet.addCell(number2);��addCell�ķ���������������ݡ�
					 * ��sheet.getCell(i, 0).getContent()�Ǵ�
					 * sheet����������ͨ����Ӧ����������ݡ�
					 */

					c++;
				}
			}
			// for every student record
			int rowNum = sheet3.getRows();
			int baseRow = 0;
			for (int i5 = baseRow + 4; i5 < rowNum; i5++) {

				// left 5 columns
				for (int t = 0; t < 2; t++) {
					wsheet1.addCell(new Label(t, i5 - 3, sheet3.getCell(t, i5)
							.getContents()));

				}
			}

			for (int i6 = baseRow + 4; i6 < rowNum; i6++) {
				double maj = 0;
				double majbase = 0;
				double opt = 0;
				int cr = baseColumn + 3;

				// for every cell
				// ע�������if��������ַ����в�ͬ���������С��60,���ڵ���60,�ո��,
				for (int j = baseColumn + 3; j < sheet3.getColumns(); j++) {
					// long term to get the score and handle various cases
					Cell cell = sheet3.getCell(j, i6);
					String score = cell.getContents();
					if (courseWeight[j] == MajWeight) {
						wsheet1.addCell(new Label(cr - 1, i6 - 3, score));
						cr++;
					}// wsheet.addCell�����������cell��

					if (score.equals("")) {
						continue;
					}// continue�������ǲ������������䣬�ص������ѭ��
					double sco;
					if (score.contains("����"))
						sco = GOOD;
					// ��������㣬sco�д���90��
					else if (score.contains("����"))
						sco = SOSO;
					else if (score.contains("ͨ��"))
						sco=D;
					else if (score.contains("�е�"))
						sco=D;
					else if (score.contains("����"))
						sco=D;
					else if (score.contains("������"))
						sco=N;
					else {
						sco = Double.parseDouble(score);// С��90��תΪ˫����
					}
					// calculate
					if (courseWeight[j] == MajWeight) {
						if (sco >= D) {
							maj += courseCredit[j] * sco;
						}
						majbase += courseCredit[j];
					} else {
						if (sco >= D) {
							opt += courseCredit[j] * sco;
						}
					}
				}

				double result = opt * OptWeight + maj / majbase;// ����λ����jxl�е�NumberRecord�޸�#
				wsheet1.addCell(new Number(cr++ - 1, i6 - 3, maj / majbase));// ���ѧ�ּ�
				wsheet1.addCell(new Number(cr++ - 1, i6 - 3, opt * OptWeight));// �����ѡ�η���
				wsheet1.addCell(new Number(cr++ - 1, i6 - 3, result));// �����ѧ�ּ�
				wsheet1.addCell(new Number(cr++ - 1, i6 - 3, majbase));
				/* ��Number���������һ��������������Label���������һ���������ַ��� */
			}
			wsheet1.insertColumn(0);
			for (int i7 = 1; i7 < wsheet1.getRows(); i7++) {
				wsheet1.addCell(new Label(0, i7, addclass.substring(0,
						addclass.length() - 4)));
			}

			wbook1.write();
			wbook1.close();
			ibook3.close();
		} catch (Exception e) {
			System.out.println("sorry2��wrong");
			System.out.println(e);
		}
	}
}
