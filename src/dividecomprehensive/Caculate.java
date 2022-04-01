package dividecomprehensive;

import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

class Caculate {// 计算成绩
	private static double OptWeight = 0.003; // 选修课比重
	private static double MajWeight = 1.0000; // 必修课比重
	private static double GOOD = 90; // 假设成绩“优秀”为90
	private static double SOSO = 80;// 假设成绩“良好”为80
	private static double D = 60; // 及格的分数
    private static double N=0;
	public static void Caculte(String output2, String addclass, String biaozhun) {
		try {
			Workbook ibook3 = Workbook.getWorkbook(new File(output2));// File函数中的inputFile是路径名；最前面的new是为ibook开辟空间,inputFile中已有数据
			Sheet sheet3 = ibook3.getSheet(0);
			File filename=new File(output2);
			String type;
			int baseColumn = 0;// 数字最好都给变量，好处在于不用多处修改
			WritableWorkbook wbook1 = Workbook
					.createWorkbook(new File(output2));// 将outFile的所有数据导入wbook，使之初始化
			
			WritableSheet wsheet1 = wbook1.createSheet(filename.getName(), 0);
			int limitColumn = sheet3.getColumns();// 此时所有的操作应在这个虚拟内存中操作，而不是在实际对象中操作，此处对象是实际的excel
			double[] courseWeight = new double[limitColumn];
			for (int i2 = baseColumn + 3; i2 < limitColumn; i2++) {
				type = sheet3.getCell(i2, 1).getContents(); // col, row
				if (type.contains("任选")){ // 判断“”是否为任选
					courseWeight[i2] = OptWeight;
				} else {
					courseWeight[i2] = MajWeight; // 用数组获得对应的数
				}
			} // 区分必修（限选） 和选修，确定对应比重，courseWeight存储比重

			// row 4th, [3], the credit of course
			double[] courseCredit = new double[limitColumn];
			String credit; // 说明从excel中导出来的数的类型是字符串型的
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
					 * Label前两个参数为导出的位置，最后一个是所 获得的内容（它里面所需的是string项），作用是导入数据名字
					 * eg:WritableCellFormat integerFormat = new
					 * WritableCellFormat (NumberFormats.INTEGER); Number
					 * number2 = new Number(0, 4, 3.141519, integerFormat);
					 * sheet.addCell(number2);；addCell的方法作用是添加数据。
					 * 而sheet.getCell(i, 0).getContent()是从
					 * sheet这个虚拟表中通过相应方法获得内容。
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
				// 注意下面的if语句是区分分数中不同的情况，如小于60,大于等于60,空格的,
				for (int j = baseColumn + 3; j < sheet3.getColumns(); j++) {
					// long term to get the score and handle various cases
					Cell cell = sheet3.getCell(j, i6);
					String score = cell.getContents();
					if (courseWeight[j] == MajWeight) {
						wsheet1.addCell(new Label(cr - 1, i6 - 3, score));
						cr++;
					}// wsheet.addCell作用是输出到cell中

					if (score.equals("")) {
						continue;
					}// continue的作用是不进行下面的语句，回到最里层循环
					double sco;
					if (score.contains("优秀"))
						sco = GOOD;
					// 如果是优秀，sco中存入90分
					else if (score.contains("良好"))
						sco = SOSO;
					else if (score.contains("通过"))
						sco=D;
					else if (score.contains("中等"))
						sco=D;
					else if (score.contains("及格"))
						sco=D;
					else if (score.contains("不及格"))
						sco=N;
					else {
						sco = Double.parseDouble(score);// 小于90分转为双精度
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

				double result = opt * OptWeight + maj / majbase;// 保留位数在jxl中的NumberRecord修改#
				wsheet1.addCell(new Number(cr++ - 1, i6 - 3, maj / majbase));// 输出学分绩
				wsheet1.addCell(new Number(cr++ - 1, i6 - 3, opt * OptWeight));// 输出公选课分数
				wsheet1.addCell(new Number(cr++ - 1, i6 - 3, result));// 输出总学分绩
				wsheet1.addCell(new Number(cr++ - 1, i6 - 3, majbase));
				/* 用Number方法，最后一个参数是数，用Label方法，最后一个参数是字符串 */
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
			System.out.println("sorry2，wrong");
			System.out.println(e);
		}
	}
}
