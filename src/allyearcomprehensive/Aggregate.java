package allyearcomprehensive;

import java.io.*;
import jxl.Workbook;
import jxl.Sheet;
import jxl.write.*;
import jxl.write.Number;

class Aggregate {

	public static void Aggregating(String xinxi1, String xinxi2, String zhonghui) {
		try {
			Workbook book1 = Workbook.getWorkbook(new File(xinxi1));

			Sheet sheet1 = book1.getSheet(0);

			Workbook book2 = Workbook.getWorkbook(new File(zhonghui));

			Sheet sheet2 = book2.getSheet(0);

			Workbook book3 = Workbook.getWorkbook(new File(xinxi2));

			Sheet sheet3 = book3.getSheet(0);

			WritableWorkbook wbook1 = Workbook
					.createWorkbook(new File(zhonghui));

			WritableSheet wsheet1 = wbook1.createSheet(zhonghui, 0);

			for (int m = 0; m < sheet2.getColumns(); m++) {
				for (int m1 = 0; m1 < sheet2.getRows(); m1++) {
					wsheet1.addCell(new Label(m, m1, sheet2.getCell(m, m1)
							.getContents()));
				}
			}
			/**
			 * the row of the curriculum table of this term
			 */
			int rowNum1;

			int rowNum2;
			/**
			 * the column of the curriculum table of this term
			 */
			int rowColumn;

			int rowColumn1;

			rowNum1 = wsheet1.getRows();

			rowNum2 = sheet1.getRows();

			rowColumn = sheet1.getColumns();

			rowColumn1 = wsheet1.getColumns();
			/**
			 * input the first year of your comprehensive table into the excel
			 */
			for (int i = 0; i < rowColumn - 4; i++) {
				for (int j = rowNum1; j < (rowNum1 + rowNum2) - 1; j++) {
					wsheet1.addCell(new Label(i, j, sheet1.getCell(i,
							j - rowNum1 + 1).getContents()));

				}
			}
			/**
			 * combine term1 with term2 by comparing with the student number
			 */
			for (int j = rowNum1; j < (sheet1.getRows() + rowNum1) - 1; j++) {
				for (int j1 = 1; j1 < sheet3.getRows(); j1++) {
					String studentNumber1;
					String studentNumber2;
					studentNumber1 = wsheet1.getCell(2, j).getContents();
					studentNumber2 = sheet3.getCell(2, j1).getContents();
					if (studentNumber1.equals(studentNumber2)) {

						int m = 0;
						for (int i1 = sheet1.getColumns() - 4; i1 < rowColumn1 - 4; i1++) {
							if (sheet3.getCell(m + 3, j1).getContents() == null)
								continue;
							wsheet1.addCell(new Label(i1, j, sheet3.getCell(
									m + 3, j1).getContents()));

							m++;
						}
						/**
						 * calculate the credit without option class
						 */
						int i2 = rowColumn1 - 4;
						wsheet1.addCell(new Number(
								i2,
								j,
								(Double.parseDouble(sheet1.getCell(
										sheet1.getColumns() - 4,
										j - rowNum1 + 1).getContents())
										* Double.parseDouble(sheet1.getCell(
												sheet1.getColumns() - 1,
												j - rowNum1 + 1).getContents()) + Double
										.parseDouble(sheet3.getCell(
												sheet3.getColumns() - 4, j1)
												.getContents())
										* Double.parseDouble(sheet3.getCell(
												sheet3.getColumns() - 1, j1)
												.getContents()))
										/ (Double.parseDouble(sheet1.getCell(
												sheet1.getColumns() - 1,
												j - rowNum1 + 1).getContents()) + Double.parseDouble(sheet3
												.getCell(
														sheet3.getColumns() - 1,
														j1).getContents()))));
						/**
						 * calculate the credit of option class
						 */
						double a = (Double.parseDouble(sheet1.getCell(
								sheet1.getColumns() - 4, j - rowNum1 + 1)
								.getContents())
								* Double.parseDouble(sheet1.getCell(
										sheet1.getColumns() - 1,
										j - rowNum1 + 1).getContents()) + Double
								.parseDouble(sheet3.getCell(
										sheet3.getColumns() - 4, j1)
										.getContents())
								* Double.parseDouble(sheet3.getCell(
										sheet3.getColumns() - 1, j1)
										.getContents()))
								/ (Double.parseDouble(sheet1.getCell(
										sheet1.getColumns() - 1,
										j - rowNum1 + 1).getContents()) + Double
										.parseDouble(sheet3.getCell(
												sheet3.getColumns() - 1, j1)
												.getContents()));
						/**
						 * input the credit without option class
						 */
						wsheet1.addCell(new Number(i2 + 1, j, Double
								.parseDouble(sheet1.getCell(
										sheet1.getColumns() - 3,
										j - rowNum1 + 1).getContents())));
						double b = Double.parseDouble(sheet1.getCell(
								sheet1.getColumns() - 3, j - rowNum1 + 1)
								.getContents());
						/**
						 * input the credit of option class
						 */
						wsheet1.addCell(new Number(i2 + 2, j, Double
								.parseDouble(sheet3.getCell(
										sheet3.getColumns() - 3, j1)
										.getContents())));
						double c = Double.parseDouble(sheet3.getCell(
								sheet3.getColumns() - 3, j1).getContents());
						/**
						 * input the sum of the credit in this year
						 */
						wsheet1.addCell(new Number(i2 + 3, j, a + b + c));

					}
				}
		}

			wbook1.write();
			wbook1.close();
			book1.close();
			book2.close();
			book3.close();
		} catch (Exception e) {
			System.out.println("sorry1£¬wrong");
			System.out.println(e);
		}
	}
}
