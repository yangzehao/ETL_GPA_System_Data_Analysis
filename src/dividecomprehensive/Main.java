package dividecomprehensive;

import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.swtdesigner.SWTResourceManager;

class Main {// ������
	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void zhongheceping() {
		// TODO Auto-generated method stub
		final Display display2 = Display.getDefault();
		System.out.println("start...");

		// Braveharry.braveharry(�������ļ�xls������ļ�xls������ ������ͬ������ʽһ�¡�

		// input
		String input1 = Picture.b1;

		// output
		String output = Picture.b2;

		String addclass = Picture.b3;// ��һ����Ϊ�ַ������͵�addclass��ֵ
		String biaozhun = Picture.b4;
		Shell shlPluto2 = new Shell(display2, SWT.CLOSE | SWT.APPLICATION_MODAL);
		shlPluto2.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		shlPluto2.setText("information");
		shlPluto2.setSize(483, 101);

		Label lblItsRunningNow = new Label(shlPluto2, SWT.NONE);
		lblItsRunningNow.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_GRAY));
		lblItsRunningNow.setFont(SWTResourceManager.getFont(
				"@Arial Unicode MS", 12, SWT.NORMAL));
		lblItsRunningNow.setBounds(177, 10, 188, 50);
		lblItsRunningNow.setText("It's running now\r\nWait for a moment...");
		shlPluto2.setLocation(Display.getCurrent().getClientArea().width / 2
				- shlPluto2.getShell().getSize().x / 2, Display.getCurrent()
				.getClientArea().height / 2 - shlPluto2.getSize().y / 2);
		shlPluto2.layout();
		shlPluto2.open();
		Delete.Deleting(input1, output, biaozhun);/*
												 * ��ʾ��output cannot be resolved
												 * to a variable����˼���Ǳ���û������
												 */
		String minzu = Picture.b5;

		Minzu.minzu(minzu, output);

		Caculate.Caculte(output, addclass, biaozhun);

		Add.Adding(output);

		Adjust.Adjusting(output, biaozhun);
		/*while (!shlPluto2.isDisposed()) {
			if (!display2.readAndDispatch()) {
				display2.sleep();
			}
		}*/

		shlPluto2.close();
		
		System.out.println("done!");/*
									 * �������ִ�в�����һ�����ͻ���ʾ����Ͼ����﷨���󣬴�������˹��� -
									 * ��ǡ�"done!"�������﷨����ɾ����
									 */

	}
}
