package dividecomprehensive;

import java.io.*;

import jxl.Sheet;
import jxl.Workbook;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.dialogs.*;

public class Picture {
	static String b1;// ���Ҫ��ͬ���ô洢�ռ䣬��Ҫ��static����
	static String b2;
	static String b3;
	static String b4;
	static String b5;
	private String minzusheng;
	private Text text3;

	/**
	 * @param args
	 */

	public void picture() {
		final Display display = Display.getDefault();
		final Shell shell = new Shell(display, SWT.CLOSE | SWT.MIN
				| SWT.APPLICATION_MODAL);
		shell.setLocation(Display.getCurrent().getClientArea().width / 2
				- shell.getShell().getSize().x / 2, Display.getCurrent()
				.getClientArea().height / 2 - shell.getSize().y / 2);
		shell.setSize(538, 542);
		shell.setText("�ۺϲ���");
		Image image1 = new Image(display, "image/glass2.jpg");
		shell.setBackgroundImage(image1);
		shell.setImage(image1);

		final Button btn = new Button(shell, SWT.PUSH);
		btn.setText("���");
		btn.setBounds(450, 430, 60, 28);
		final Button btn2 = new Button(shell, SWT.PUSH);
		btn2.setText("���...");
		btn2.setBounds(450, 170, 60, 28);
		/*
		 * final Text text1=new Text(shell,SWT.MULTI); text1.setBounds(250, 100,
		 * 150, 25);
		 */
		final Text text1 = new Text(shell, SWT.BORDER);
		text1.setBounds(250, 100, 150, 25);
		final Text text2 = new Text(shell, SWT.BORDER);
		text2.setBounds(250, 175, 150, 25);
		final Text text4 = new Text(shell, SWT.BORDER);
		text4.setBounds(250, 316, 150, 25);

		final Button btn1 = new Button(shell, SWT.NONE);
		btn1.setText("\u6D4F\u89C8...");
		btn1.setBounds(450, 95, 60, 28);

		final Button btn4 = new Button(shell, SWT.NONE);
		btn4.setText("\u6D4F\u89C8...");
		btn4.setBounds(450, 311, 60, 28);

		text3 = new Text(shell, SWT.BORDER);
		text3.setBounds(250, 238, 150, 25);

		final Button btn3 = new Button(shell, SWT.NONE);
		btn3.setText("\u6D4F\u89C8...");
		btn3.setBounds(450, 234, 60, 28);

		btn4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a1) {
				if (a1.getSource() == btn4) {
					DirectoryDialog dlg = new DirectoryDialog(shell);
					dlg.setFilterPath(text4.getText());
					dlg.setText("�ļ�Ŀ¼ ");
					String dir = dlg.open();
					if (dir != null) {
						text4.setText(dir + "\\");
					}
				}
			}
		});

		btn3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a1) {
				if (a1.getSource() == btn3) {
					DirectoryDialog dlg = new DirectoryDialog(shell);
					dlg.setFilterPath(text3.getText());
					dlg.setText("�ļ�Ŀ¼ ");
					String dir = dlg.open();
					if (dir != null) {
						text3.setText(dir + "\\");
					}
				}
			}
		});
		btn2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a1) {
				if (a1.getSource() == btn2) {
					FileDialog fd = new FileDialog(shell);
					fd.setFilterPath(text2.getText());
					fd.setText("�ļ� ");
					String[] filrerExt = { "*.xls" };
					fd.setFilterExtensions(filrerExt);
					String fir = fd.open();
					if (fir != null) {
						text2.setText(fir);
					}
				}
			}
		});
		btn1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a1) {
				if (a1.getSource() == btn1) {
					FileDialog fd = new FileDialog(shell);
					fd.setFilterPath(text1.getText());
					fd.setText("�ļ� ");
					String[] filrerExt = { "*.xls" };
					fd.setFilterExtensions(filrerExt);
					String fir = fd.open();
					if (fir != null) {
						text1.setText(fir);
					}
				}
			}
		});
		btn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a2) {
				if (a2.getSource() == btn) {
					minzusheng = text2.getText();
					b5 = minzusheng;
					if (text1.getText() == "" || text2.getText() == ""
							|| text3.getText() == "" || text4.getText() == "") {
						MessageDialog.openError(shell, "error", "�����Ϣ�������������п�");
					} else {
						try {

							File file = new File(text3.getText());
							File file1[];

							file1 = file.listFiles();
							for (int i = 0; i < file1.length; i++) {
								if (file1[i].toString().contains("-")
										&& !file1[i].toString().contains("��")
										&& !file1[i].toString().contains("�γ�")) {
									b1 = file1[i].getAbsolutePath();
									b2 = text4.getText() + file1[i].getName();
									b3 = file1[i].getName();// �˷���ֻ����ļ�����ǰ��·��δ���
									b4 = text1.getText();

									Main.zhongheceping();
									System.out.println("ok");

								}
							}
							int m=0;//ָʾ��
							for(int j=0;j<file1.length;j++){
							Workbook book1=Workbook.getWorkbook(file1[j]);
							Sheet sheet1=book1.getSheet(0);
							if(sheet1.getColumns()==0&&sheet1.getRows()==0){
								MessageDialog.openInformation(shell, "information",
								"���������ʽ�Ƿ���ȷ��ο� help ");
								m++;
								break;
							}
							}
							if(m==0){
							MessageDialog.openInformation(shell, "information",
									"��ϲ�㣡 ���� ͳ�������");
							}
						} catch (Exception e) {
							System.out.println("sorry6��wrong");
							System.out.println(e);
						}

					}

					// TODO Auto-generated method stub

				}
			}
		});

		shell.layout();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {// ���������û�йرգ���ô����ѭ��
				display.sleep();// �����������仰ע���ˣ���ô���ڵ���Ӧ���ǣ�û����Ӧ���޷��Ŵ�
			}
		}

	}
}
