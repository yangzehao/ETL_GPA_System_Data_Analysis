package lastcomprehensive;

import jxl.Workbook;
import jxl.Sheet;
import jxl.write.*;
import jxl.write.Number;
import java.io.File;

class Sort {

	@SuppressWarnings("deprecation")
	public static void sort(String input) {
		try {
			Workbook ibook1 = Workbook.getWorkbook(new File(input));
			Sheet sheet1 = ibook1.getSheet(0);
			WritableWorkbook wbook1 = Workbook.createWorkbook(new File(input));
			WritableSheet wsheet1 = wbook1.createSheet("成绩总汇表", 0);
			WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),
					10, WritableFont.NO_BOLD, false);
			WritableCellFormat CBwcfF = new WritableCellFormat(wf);
			CBwcfF.setAlignment(Alignment.CENTRE);
			// 设置垂直对齐为居中对齐
			CBwcfF.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableCellFormat CwcfF1 = new WritableCellFormat(wf);
			CwcfF1.setAlignment(Alignment.CENTRE);
			CBwcfF.setBorder(Border.ALL, BorderLineStyle.THIN);
			CwcfF1.setVerticalAlignment(VerticalAlignment.CENTRE);
			CwcfF1.setBackground(Colour.GRAY_25);
			CwcfF1.setBorder(Border.ALL, BorderLineStyle.THIN);
			CwcfF1.setWrap(true);
			int column = sheet1.getColumns();
			int row = sheet1.getRows();
			double[] bijiao = new double[row - 1];
			for (int j1 = 0; j1 < sheet1.getRows() - 1; j1++) {
				bijiao[j1] = Double.parseDouble(sheet1.getCell(column - 2,
						j1 + 1).getContents());
			}
			double temp;
			int i2;
			for (int i1 = 0; i1 < bijiao.length - 1; i1++) {
				for (i2 = i1 + 1; i2 < bijiao.length; i2++) {
					if (bijiao[i1] < bijiao[i2]) {
						temp = bijiao[i1];
						bijiao[i1] = bijiao[i2];
						bijiao[i2] = temp;

					}
				}
			}
			for (int j2 = 0; j2 < bijiao.length; j2++) {
				for (int j3 = 1; j3 < sheet1.getRows(); j3++) {
					if (bijiao[j2] == Double.parseDouble(sheet1.getCell(
							column - 2, j3).getContents())) {
						for (int i3 = 0; i3 < column; i3++) {
							wsheet1.addCell(new Label(i3, j2 + 1, sheet1
									.getCell(i3, j3).getContents(), CBwcfF));
							wsheet1.addCell(new Label(i3, 0, sheet1.getCell(i3,
									0).getContents(), CwcfF1));
						}
					}
				}
			}
			for (int j2=1;j2<sheet1.getRows()-2;j2++){
				for(int j3=j2+1;j3<sheet1.getRows()-1;j3++){
					if(wsheet1.getCell(1,j2).getContents().equals(wsheet1.getCell(1, j3).getContents())){
						wsheet1.removeRow(j3);
					}
				}
			}
			wsheet1.insertColumn(0);
			wsheet1.addCell(new Label(0, 0, "专业排名", CwcfF1));
			int m = 0;
			for (int j4 = 1; j4 < wsheet1.getRows(); j4++) {
				m++;
				wsheet1.addCell(new Number(0, j4, m, CBwcfF));

			}
			wbook1.write();
			wbook1.close();
			ibook1.close();
		} catch (Exception e1) {
			System.out.println("SORRY,WRONG");
			System.out.println(e1);
		}
	}

}
