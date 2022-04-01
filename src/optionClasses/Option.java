package optionClasses;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.swtdesigner.SWTResourceManager;

public class Option {
	private static Text text1;
	private static Text text2;
	private static File[] fileOriginalSum;
	private static File fileOriginal;
	private static File[] fileOptionSum;
	private static File fileOption;
	private static Text text3;
	private static String nameOfNew;

	/**
	 * @param args
	 */
	public static void optionClass() {
		final Display display = Display.getDefault();
		final Shell shell = new Shell(display, SWT.CLOSE | SWT.MIN
				| SWT.APPLICATION_MODAL);
		shell.setText("考级融合");
		shell.setLocation(Display.getCurrent().getClientArea().width / 2
				- shell.getShell().getSize().x / 2, Display.getCurrent()
				.getClientArea().height / 2 - shell.getSize().y / 2);
		shell.setSize(488, 343);
		Image image1 = new Image(display, "image/optionclass.jpg");
		shell.setBackgroundImage(image1);
		shell.setImage(image1);
		text1 = new Text(shell, SWT.BORDER);
		text1.setToolTipText("\u539F\u59CB\u6210\u7EE9\u6587\u4EF6\u5939");
		text1.setBounds(155, 40, 103, 23);

		text2 = new Text(shell, SWT.BORDER);
		text2.setToolTipText("\u8003\u7EA7\u6210\u7EE9\u6587\u4EF6\u5939");
		text2.setBounds(155, 114, 103, 23);

		final Button btn3 = new Button(shell, SWT.NONE);
		btn3.setToolTipText("\u5BFC\u5165\u5176\u5B83\u516C\u9009");
		btn3.setBounds(360, 254, 93, 23);
		btn3.setText("\u5BFC\u5165\u5176\u5B83\u516C\u9009");

		final Button btn1 = new Button(shell, SWT.NONE);
		btn1.setBounds(285, 40, 51, 22);
		btn1.setText("\u6D4F\u89C8...");

		final Button btn2 = new Button(shell, SWT.NONE);
		btn2.setText("\u6D4F\u89C8...");
		btn2.setBounds(285, 114, 51, 22);

		text3 = new Text(shell, SWT.BORDER);
		text3.setToolTipText("\u65B0\u751F\u6210\u6210\u7EE9\u6587\u4EF6\u5939");
		text3.setBounds(155, 194, 101, 23);

		final Button btn4 = new Button(shell, SWT.NONE);
		btn4.setText("\u6D4F\u89C8...");
		btn4.setBounds(285, 199, 51, 22);
		btn1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a1) {
				if (a1.getSource() == btn1) {
					DirectoryDialog dlg = new DirectoryDialog(shell);
					dlg.setFilterPath(text1.getText());
					dlg.setText("文件目录 ");
					String dir = dlg.open();
					if (dir != null) {
						text1.setText(dir + "\\");
					}
				}
			}
		});
		btn2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a1) {
				if (a1.getSource() == btn2) {
					DirectoryDialog dlg = new DirectoryDialog(shell);
					dlg.setFilterPath(text2.getText());
					dlg.setText("文件目录 ");
					String dir = dlg.open();
					if (dir != null) {
						text2.setText(dir + "\\");
					}
				}
			}
		});
		btn4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a1) {
				if (a1.getSource() == btn4) {
					DirectoryDialog dlg = new DirectoryDialog(shell);
					dlg.setFilterPath(text3.getText());
					dlg.setText("文件目录 ");
					String dir = dlg.open();
					if (dir != null) {
						text3.setText(dir + "\\");

					}
				}
			}
		});
		btn3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a1) {

				if (a1.getSource() == btn3) {
				}
				if (text1.getText() == "" || text2.getText() == ""
						|| text3.getText() == "") {
					MessageDialog.openError(shell, "error", "请把信息填完整，不能有空");
				} else {

					fileOriginal = new File(text1.getText());
					fileOriginalSum = fileOriginal.listFiles();

					fileOption = new File(text2.getText());
					fileOptionSum = fileOption.listFiles();
					Shell shlPluto1 = new Shell(display, SWT.CLOSE
							| SWT.APPLICATION_MODAL);
					shlPluto1.setBackground(SWTResourceManager
							.getColor(SWT.COLOR_GRAY));
					shlPluto1.setText("information");
					shlPluto1.setSize(483, 101);

					org.eclipse.swt.widgets.Label lblItsRunningNow = new org.eclipse.swt.widgets.Label(
							shlPluto1, SWT.NONE);
					lblItsRunningNow.setBackground(SWTResourceManager
							.getColor(SWT.COLOR_GRAY));
					lblItsRunningNow.setFont(SWTResourceManager.getFont(
							"@Arial Unicode MS", 12, SWT.NORMAL));
					lblItsRunningNow.setBounds(177, 10, 188, 50);
					lblItsRunningNow
							.setText("It's running now\r\nWait for a moment...");
					shlPluto1.layout();
					shlPluto1.open();
					try {

						for (int i1 = 0; i1 < fileOriginalSum.length; i1++) {
							for (int i2 = 0; i2 < fileOptionSum.length; i2++) {
								if (fileOriginalSum[i1]
										.toString()
										.substring(
												fileOriginalSum[i1].toString()
														.lastIndexOf("\\") + 1)
										.equals(fileOptionSum[i2]
												.toString()
												.substring(
														fileOptionSum[i2]
																.toString()
																.lastIndexOf(
																		"\\") + 1))) {
									nameOfNew = text3.getText()
											+ fileOptionSum[i2]
													.toString()
													.substring(
															fileOptionSum[i2]
																	.toString()
																	.lastIndexOf(
																			"\\") + 1);
									add(i1, i2, nameOfNew);
								}
							}
						}
						shlPluto1.close();
						MessageDialog.openInformation(shell, "information",
								"恭喜你！ 各班 统计已完成");
					} catch (Exception m) {
						System.out.println("sorry,you are wrong");
						System.out.println(m);
						shlPluto1.close();
						MessageDialog.openInformation(shell, "information",
								"请确认整合原始文件数目与原始文件数目相等");
					}
				}

			}
		});

		shell.layout();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();// 如果把最后两句话注释了，那么窗口的相应就是：没有响应，无法放大
			}
		}
	}

	static void add(int i1, int i2, String newName) {

		try {

			Workbook book1 = Workbook.getWorkbook(fileOriginalSum[i1]);
			Sheet sheet1 = book1.getSheet(0);
			WritableWorkbook wbook1 = Workbook
					.createWorkbook(new File(newName));
			WritableSheet wsheet1 = wbook1.createSheet("原始成绩", 0);
			Workbook book2 = Workbook.getWorkbook(fileOptionSum[i2]);
			Sheet sheet2 = book2.getSheet(0);
			if (sheet2.getColumns() > 0) {
				for (int m = 0; m < sheet1.getColumns(); m++) {
					for (int m1 = 0; m1 < sheet1.getRows(); m1++) {

						wsheet1.addCell(new Label(m, m1, sheet1.getCell(m, m1)
								.getContents()));

					}
				}

				for (int m = 2; m < sheet2.getColumns(); m++) {
					wsheet1.insertColumn(wsheet1.getColumns() - 4);
					for (int m1 = 0; m1 < 2; m1++) {
						wsheet1.addCell(new Label(wsheet1.getColumns() - 5, m1,
								sheet2.getCell(m, m1).getContents()));

					}
					wsheet1.addCell(new Label(wsheet1.getColumns() - 5, 3,
							sheet2.getCell(m, 2).getContents()));
					for (int m3 = 4; m3 < wsheet1.getRows(); m3++) {
						for (int m4 = 3; m4 < sheet2.getRows(); m4++) {
							if (wsheet1
									.getCell(0, m3)
									.getContents()
									.equals(sheet2.getCell(0, m4).getContents())) {
								wsheet1.addCell(new Label(
										wsheet1.getColumns() - 5, m3, sheet2
												.getCell(m, m4).getContents()));
							}
						}
					}
				}
				book1.close();
				wbook1.write();
				wbook1.close();
			}

		} catch (Exception e) {
			System.out.println("sorry1，wrong");
			System.out.println(e);
		}

	}
}
