package com.neuedu.frame;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 经销商窗口类
 * 
 * @author 伍伟康
 * @date 2020-7-17
 */
public class DealerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static DealerFrame instance = null;

	private DealerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("经销商");
		setSize(900, 700);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel imagePanel = new ImagePanel("dealer.png");
		changeContentPane(imagePanel);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// 订单管理
		JMenu menu1 = new JMenu("订单管理");
		menuBar.add(menu1);
		JMenuItem Item11 = new JMenuItem("我的订单");
		menu1.add(Item11);
		Item11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// -----------------------?
			}
		});
	}

	public static DealerFrame getInstance() {
		if (instance == null) {
			instance = new DealerFrame();
		}
		return instance;
	}

	// 切换面板
	public void changeContentPane(Container contentPane) {
		this.setContentPane(contentPane);
		this.revalidate(); // 相当于刷新的作用
	}
}
