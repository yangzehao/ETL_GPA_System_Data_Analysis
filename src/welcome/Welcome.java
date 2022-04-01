package welcome;

import org.eclipse.jface.dialogs.MessageDialog;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import optionClasses.Option;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class Welcome {
	private static Image closeOverImage;

	private static Image closeDownImage;

	/**
	 * 
	 * Wait for a moment to splash
	 * 
	 */
	public static void main(String arg[]) {
		new Thread() {
			/*
			 * public void run(){ try{ SplashScreen
			 * splash=SplashScreen.getSplashScreen();
			 * System.out.println(splash); Graphics2D g
			 * =splash.createGraphics();
			 * g.drawString(splash.getBounds().toString(),10,30);
			 * //SplashScreen在屏幕的位置,大小
			 * g.drawString(splash.getSize().toString(),10,50);
			 * g.drawString(splash.getImageURL().toString(),10,70);
			 * splash.update(); Thread.sleep(10000);
			 * 
			 * }catch(Exception e){ e.printStackTrace(); } }
			 */
		}.start();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		/**
		 * 
		 * Construct welcome page
		 * 
		 */
		final Display display = Display.getDefault();

		final Shell shell = new Shell(display, SWT.CLOSE | SWT.MIN);

		shell.setToolTipText("Pluto");

		RGB rb = new RGB(255, 255, 255);

		Color color1 = new Color(display, rb);

		shell.setBackground(color1);

		Image image1 = new Image(display, "image/glass1.jpg");

		shell.setBackgroundImage(image1);

		shell.setImage(image1);

		shell.setText("Pluto");

		shell.setSize(500, 375);

		shell.setLocation(Display.getCurrent().getClientArea().width / 2
				- shell.getShell().getSize().x / 2, Display.getCurrent()
				.getClientArea().height / 2 - shell.getSize().y / 2);

		final Button btn1 = new Button(shell, SWT.FLAT);

		btn1.setToolTipText("\u7EFC\u5408\u6D4B\u8BC4");

		btn1.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));

		btn1.setBounds(207, 129, 80, 41);

		final Button btn2 = new Button(shell, SWT.FLAT);

		btn2.setToolTipText("\u603B\u6C47");

		btn2.setForeground(SWTResourceManager.getColor(51, 153, 204));

		btn2.setBounds(207, 203, 80, 41);

		closeOverImage = new Image(display, "icon/3.jpg");

		final Image closeOverImage1 = new Image(display, "icon/3.1.jpg");

		closeDownImage = new Image(display, "icon/2.jpg");

		final Image closeDownImage1 = new Image(display, "icon/2.1.jpg");

		btn1.setImage(closeOverImage);

		btn2.setImage(closeOverImage1);
		/** change the color of button when the button is pressed down */
		btn1.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				if (e.getSource() == btn1) {
					btn1.setImage(closeDownImage);
				}

			}

			/**
			 * change the color of button when the button is released
			 * 
			 */
			public void mouseUp(MouseEvent e1) {
				if (e1.getSource() == btn1) {
					btn1.setImage(closeOverImage);
				}

			}

		});
		/**
		 * change the color of button when the button is pressed down
		 * 
		 */
		btn2.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {

				if (e.getSource() == btn2) {
					btn2.setImage(closeDownImage1);
				}

			}

			/** change the color of button when the button is released */
			public void mouseUp(MouseEvent e1) {

				if (e1.getSource() == btn2) {
					btn2.setImage(closeOverImage1);
				}
			}

		});
		/**
		 * conduct the method of Picture().picture) to conduct the credit of
		 * each class
		 * 
		 */
		btn1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (e.getSource() == btn1) {
					try {

						new dividecomprehensive.Picture().picture();

					} catch (Exception m) {
						MessageDialog.openError(shell, "error",
								"您的格式可能有错误，请参照help");

					}
				}
			}
		});
		/**
		 * build the menu of the component
		 * 
		 */
		Menu menu = new Menu(shell, SWT.BAR);

		shell.setMenuBar(menu);

		MenuItem mntmSuggestion = new MenuItem(menu, SWT.CASCADE);

		mntmSuggestion.setText("suggestion");

		Menu menu_1 = new Menu(mntmSuggestion);

		mntmSuggestion.setMenu(menu_1);

		final MenuItem mntmHelp = new MenuItem(menu_1, SWT.NONE);

		mntmHelp.setText("help");
		/**
		 * read the help document of this software
		 * 
		 */
		mntmHelp.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent a1) {
				if (a1.getSource() == mntmHelp) {
					try {
						Runtime.getRuntime()
								.exec("cmd.exe /c start ReadMe.doc");
					} catch (Exception e) {
						MessageDialog.openError(shell, "error",
								"请确认未把ReadMe文件删除");
					}
				}
			}
		});
		/**
		 * 
		 * build the exit menuitem
		 */
		final MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);

		mntmExit.setText("exit");

		final MenuItem mntmAbout = new MenuItem(menu_1, SWT.NONE);
		/**
		 * build the introduction menuitem
		 */
		mntmAbout.setText("about Pluto");

		MenuItem mntmExtend = new MenuItem(menu, SWT.CASCADE);

		mntmExtend.setText("extends");

		Menu menu_2 = new Menu(mntmExtend);
		mntmExtend.setMenu(menu_2);

		final MenuItem mntmOptionclassCaculate = new MenuItem(menu_2, SWT.NONE);
		mntmOptionclassCaculate.setText("Optionclass Caculate");
		mntmOptionclassCaculate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a2) {
				if (a2.getSource() == mntmOptionclassCaculate){
					new Option();
					Option.optionClass();
				}
			}
		});

		/**
		 * running the allyearcomprehensive.YearComprehensive().zhonghuixinxi()
		 * method to aggregate each credit information in a formal way
		 */
		final MenuItem mntmAllYearComprehensive = new MenuItem(menu_2, SWT.NONE);
		mntmAllYearComprehensive.setText("All year comprehensive");
		mntmAllYearComprehensive.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a2) {
				if (a2.getSource() == mntmAllYearComprehensive) {
					new allyearcomprehensive.YearComprehensive()
							.zhonghuixinxi();
				}
			}
		});
		/**
		 * showing the information about this software
		 */
		mntmAbout.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a2) {
				if (a2.getSource() == mntmAbout) {
					final Display display1 = Display.getDefault();
					final Shell shlPluto = new Shell(display1, SWT.CLOSE
							| SWT.APPLICATION_MODAL);
					shlPluto.setLocation(
							Display.getCurrent().getClientArea().width / 2
									- shell.getShell().getSize().x / 2, Display
									.getCurrent().getClientArea().height
									/ 2
									- shell.getSize().y / 2);
					shlPluto.setText("Pluto ");

					shlPluto.setSize(480, 246);

					shlPluto.setImage(new Image(display1, "icon/4.jpg"));

					shlPluto.setBackgroundImage(new Image(display1,
							"icon/4.jpg"));
					Text txtPluto;

					txtPluto = new Text(shlPluto, SWT.BORDER | SWT.WRAP
							| SWT.V_SCROLL);

					txtPluto.setForeground(SWTResourceManager
							.getColor(SWT.COLOR_WIDGET_BORDER));

					txtPluto.setBackground(new Color(display1, 255, 255, 255));

					txtPluto.setEditable(false);

					txtPluto.setText("Pluto                                                                                               Version: 2.0\r\n\r\n(c) Copyright \u7693\u5C0F\u5929  2011. All rights reserved.\r\nIf you have any question about this software,you can send email to haoxiaotian1212@foxmail.com \r\n\r\nAttention:\r\n         When you send the email ,the name of your university and your college should be attached,or you may can't receive the answer.\r\n\r\n                                                                     Thank you!");

					txtPluto.setBounds(174, 0, 301, 220);

					shlPluto.layout();

					shlPluto.open();

					while (!shell.isDisposed()) {
						if (!display1.readAndDispatch()) {
							display1.sleep();
						}
					}

					display1.dispose();
				}
			}
		});
		/**
		 * close the program through this action
		 */
		mntmExit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent a2) {
				try {
					if (a2.getSource() == mntmExit) {
						shell.dispose();
						display.dispose();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/**
		 * running the method of lastcomprehensive.Main1().zhonghuixinxi() to
		 * combine two terms credits in a formal way
		 */
		btn2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (e.getSource() == btn2) {
					try {

						new lastcomprehensive.Main1().zhonghuixinxi();

					} catch (Exception m) {
						MessageDialog.openError(shell, "error",
								"您的格式可能有错误，请参照help");
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

		display.dispose();
	}
}
