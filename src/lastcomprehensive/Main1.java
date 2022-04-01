package lastcomprehensive;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;

public class Main1 {

	private static String a1;
	private static String a2;
	private static String a3;
	private Text text4;

	/**
	 * @param args
	 */

	// TODO Auto-generated method stub

	public void zhonghuixinxi() {

		System.out.println("start");
		final Display display = Display.getDefault();
		final Shell shell = new Shell(display, SWT.CLOSE | SWT.MIN
				| SWT.APPLICATION_MODAL);
		shell.setLocation(Display.getCurrent().getClientArea().width / 2
				- shell.getShell().getSize().x / 2, Display.getCurrent()
				.getClientArea().height / 2 - shell.getSize().y / 2);
		shell.setSize(488, 492);
		shell.setText("总汇");
		shell.setImage(new Image(display, "image/glass3.jpg"));
		shell.setBackgroundImage(new Image(display, "image/glass3.jpg"));

		final Button btn1 = new Button(shell, SWT.PUSH);
		btn1.setText("总汇");
		btn1.setBounds(385, 395, 58, 20);
		final Text text2 = new Text(shell, SWT.BORDER);
		text2.setBounds(178, 116, 150, 25);
		final Text text3 = new Text(shell, SWT.BORDER);
		text3.setBounds(179, 187, 150, 25);

		final Button btn2 = new Button(shell, SWT.NONE);
		btn2.setBounds(378, 114, 65, 22);
		btn2.setText("\u6D4F\u89C8...");

		final Button btn3 = new Button(shell, SWT.NONE);
		btn3.setText("\u6D4F\u89C8...");
		btn3.setBounds(378, 185, 65, 22);

		text4 = new Text(shell, SWT.BORDER);
		text4.setBounds(178, 257, 150, 25);

		final Button btn4 = new Button(shell, SWT.NONE);
		btn4.setText("\u6D4F\u89C8...");
		btn4.setBounds(378, 255, 65, 22);

		btn2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a1) {
				if (a1.getSource() == btn2) {
					FileDialog fd = new FileDialog(shell);
					fd.setFilterPath(text2.getText());
					fd.setText("文件 ");
					String[] filrerExt = { "*.xls" };
					fd.setFilterExtensions(filrerExt);
					String fir = fd.open();
					if (fir != null) {
						text2.setText(fir);
					}
				}
			}
		});
		btn3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a1) {
				if (a1.getSource() == btn3) {
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
		btn4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a1) {
				if (a1.getSource() == btn4) {
					DirectoryDialog dlg = new DirectoryDialog(shell);
					dlg.setFilterPath(text4.getText());
					dlg.setText("文件目录 ");
					String dir = dlg.open();
					if (dir != null) {
						text4.setText(dir + "\\");
					}
				}
			}
		});
		btn1.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent a) {
				if (a.getSource() == btn1) {

					a1 = text2.getText();
					a2 = text3.getText();
					String zhonghui = a1;
					System.out.println(text4.getText().concat("成绩总汇表.xls"));

					try {
						Workbook book1 = Workbook
								.getWorkbook(new File(zhonghui));
						Sheet sheet1 = book1.getSheet(0);
						WritableWorkbook wbook1 = Workbook
								.createWorkbook(new File(text4.getText()
										.concat("成绩总汇表.xls")));
						WritableSheet wsheet1 = wbook1.createSheet("成绩总汇表", 0);
						for (int i1 = 3; i1 < sheet1.getColumns(); i1++) {
							wsheet1.addCell(new jxl.write.Label(i1, 0, sheet1
									.getCell(i1, 0).getContents()));
						}
						wsheet1.addCell(new jxl.write.Label(0, 0, "班级"));
						wsheet1.addCell(new jxl.write.Label(1, 0, "学号"));
						wsheet1.addCell(new jxl.write.Label(2, 0, "姓名"));
						wsheet1.addCell(new jxl.write.Label(wsheet1
								.getColumns(), 0, "学分绩"));
						wsheet1.addCell(new jxl.write.Label(wsheet1
								.getColumns(), 0, "公选课学分"));
						wsheet1.addCell(new jxl.write.Label(wsheet1
								.getColumns(), 0, "总学分绩"));
						wsheet1.addCell(new jxl.write.Label(wsheet1
								.getColumns(), 0, "必修加限选总学分"));
						wbook1.write();
						wbook1.close();
						book1.close();
					} catch (Exception e) {
						System.out.println(e);
					}

					if (text2.getText() == "" || text3.getText() == ""||text4.getText()=="") {
						MessageDialog.openError(shell, "error", "请把信息填完整，不能有空");
					} else {
						try {
							Shell shlPluto1 = new Shell(display, SWT.CLOSE
									| SWT.APPLICATION_MODAL);
							shlPluto1.setBackground(SWTResourceManager
									.getColor(SWT.COLOR_GRAY));
							shlPluto1.setText("information");
							shlPluto1.setSize(483, 101);

							Label lblItsRunningNow = new Label(shlPluto1,
									SWT.NONE);
							lblItsRunningNow.setBackground(SWTResourceManager
									.getColor(SWT.COLOR_GRAY));
							lblItsRunningNow.setFont(SWTResourceManager
									.getFont("@Arial Unicode MS", 12,
											SWT.NORMAL));
							lblItsRunningNow.setBounds(177, 10, 188, 50);
							lblItsRunningNow
									.setText("It's running now\r\nWait for a moment...");
							shlPluto1.layout();
							shlPluto1.open();
							File file = new File(a2);
							File file1[];

							file1 = file.listFiles();
							for (int i = 0; i < file1.length; i++) {
								if (file1[i].toString().contains("-")
										&& !file1[i].toString().contains("表")
										&& !file1[i].toString().contains("课程")) {
									a3 = file1[i].getAbsolutePath();
									String xinxi;
									xinxi = a3;
									System.out.println(xinxi);
									Aggregate.Aggregating(xinxi,
											(text4.getText() + "成绩总汇表.xls"));

								}
							}

							Sort.sort(text4.getText() + "成绩总汇表.xls");
							shlPluto1.close();
							System.out.println("done");
							MessageDialog.openInformation(shell, "information",
									"恭喜你！ 各班 统计已完成");
						} catch (Exception m) {
							System.out.println("sorry,you are wrong");
							System.out.println(m);
						}
					}
				}

			}

		});
		shell.layout();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {// 如果主窗口没有关闭，那么继续循环
				display.sleep();// 如果把最后两句话注释了，那么窗口的相应就是：没有响应，无法放大
			}
		}

	}
}
