package dividecomprehensive;

import java.io.*;//注意分数不用公式，因为有几项不兼容
import jxl.Workbook;
import jxl.Sheet;
import jxl.write.*;
import jxl.Cell;

class Minzu {
	public static void minzu(String minzusheng, String output1) {
		try {

			Workbook ibook5 = Workbook.getWorkbook(new File(output1));

			Sheet sheet = ibook5.getSheet(0);
			WritableWorkbook wbook = Workbook.createWorkbook(new File(output1));
			WritableSheet wsheet = wbook.createSheet(output1, 0);

			Workbook ibook6 = Workbook.getWorkbook(new File(minzusheng));

			Sheet sheet1 = ibook6.getSheet(0);

			int limitrow = sheet1.getRows();

			Cell[] cell1 = sheet1.getRow(limitrow - 1);//cell的长度和sheet中最长的一行的长度相等

			for (int i = 0; i < sheet.getColumns(); i++) {

				for (int j = 0; j < sheet.getRows(); j++) {

					wsheet.addCell(new Label(i, j, sheet.getCell(i, j)
							.getContents()));

				}
			}
			String type;
			double duibi;
			double duibi1;
			System.out.printf("thanks");
			for (int i1 = 0; i1 < cell1.length; i1++) {
				System.out.printf("goodbye");
				type = sheet1.getCell(i1, limitrow - 1).getContents();
				for (int j1 = 4; j1 < wsheet.getRows(); j1++) {
					System.out.printf(wsheet.getCell(1, j1).getContents());
					System.out.println(type);
					System.out.println(cell1.length);

					System.out.println(limitrow);
					if (type.equals(wsheet.getCell(1, j1).getContents())) {

						for (int i2 = 3; i2 < wsheet.getColumns(); i2++) {
							if ((wsheet.getCell(i2, 0)).getContents().contains(
									"体育")
									|| (wsheet.getCell(i2, 1)).getContents()
											.contains("任选")) {
								continue;
							} else if (wsheet.getCell(i2, 2).getContents()
									.contains("实践")
									&& wsheet.getCell(i2, 2).getContents()
											.contains("教学")) {
								continue;
							}

							else if (wsheet.getCell(i2, j1).getContents()
									.equals("")
									|| wsheet.getCell(i2, j1).getContents()
											.equals("良好")
									|| wsheet.getCell(i2, j1).getContents()
											.equals("优秀")) {
								continue;
							}

							else {
								duibi = Double.parseDouble(wsheet.getCell(i2,
										j1)

								.getContents());

								if (duibi < 45) {
									continue;
								} else if (duibi >= 45 && duibi < 60) {
									wsheet.addCell(new Label(i2, j1, "60"));
								} else {
									for (int minzufen = 4; minzufen < sheet1
											.getRows() - 3; minzufen++) {
										for (int minzufenlie = 0; minzufenlie < 8; minzufenlie += 2) {

											duibi1 = Double.parseDouble(sheet1
													.getCell(minzufenlie,
															minzufen)
													.getContents());
											if (duibi1 == duibi) {
												wsheet.addCell(new Label(
														i2,
														j1,
														sheet1.getCell(
																minzufenlie + 1,
																minzufen)
																.getContents()));
											}
											System.out.printf(sheet1.getCell(
													minzufenlie + 1, minzufen)
													.getContents());

										}
									}
								}
							}
						}
					}
				}
			}
			wbook.write();
			wbook.close();
			ibook5.close();
			ibook6.close();
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println("sorry7，wrong");
			System.out.println(e);
		}
	}
}
