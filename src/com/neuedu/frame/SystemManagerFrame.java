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
 * 超级管理员窗口类
 * 
 * @author 伍伟康
 * @date 2020-7-17
 */
public class SystemManagerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static SystemManagerFrame instance = null;

	private SystemManagerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("超级管理员");
		setSize(900, 700);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel imagePanel = new ImagePanel("superManage.png");
		changeContentPane(imagePanel);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// 用户管理（新建，修改，删除，查询）
		JMenu userManage = new JMenu("系统设置");
		menuBar.add(userManage);
		JMenuItem umItem = new JMenuItem("用户管理");
		userManage.add(umItem);
		umItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeContentPane(UserManagepanel.getInstance());
			}
		});

		// 云工厂信息
		JMenu cloudFactoryInfo = new JMenu("云工厂");
		menuBar.add(cloudFactoryInfo);
		JMenuItem cfiItem = new JMenuItem("云工厂信息");
		cloudFactoryInfo.add(cfiItem);
		cfiItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeContentPane(CloudFactoryInfoPanel.getInstance());
			}
		});

		// 产品管理
		JMenu productManage = new JMenu("产品管理");
		menuBar.add(productManage);
		JMenuItem pmItem1 = new JMenuItem("产品类别管理");
		productManage.add(pmItem1);
		pmItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeContentPane(ProductTypeManagePanel.getInstance());
			}
		});
		productManage.addSeparator();
		JMenuItem pmItem2 = new JMenuItem("产品信息管理");
		productManage.add(pmItem2);
		pmItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeContentPane(ProductManagePanel.getInstance());
			}
		});

		// 产能中心
		JMenu capacityCentre = new JMenu("产能中心");
		menuBar.add(capacityCentre);
		JMenuItem ccItem1 = new JMenuItem("设备类型管理");
		capacityCentre.add(ccItem1);
		ccItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeContentPane(EquipmentTypeManagePanel.getInstance());
			}
		});
		capacityCentre.addSeparator();
		JMenuItem ccItem2 = new JMenuItem("设备信息管理");
		capacityCentre.add(ccItem2);
		ccItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeContentPane(EquipmentManagePanel.getInstance());
			}
		});

		// 订单管理
		JMenu orderManage = new JMenu("订单管理");
		menuBar.add(orderManage);
		JMenuItem omItem = new JMenuItem("订单基本信息");
		orderManage.add(omItem);
		omItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeContentPane(CloudFactoryInfoPanel.getInstance());
			}
		});

	}

	public static SystemManagerFrame getInstance() {
		if (instance == null) {
			instance = new SystemManagerFrame();
		}
		return instance;
	}

	// 切换面板
	public void changeContentPane(Container contentPane) {
		this.setContentPane(contentPane);
		this.revalidate(); // 相当于刷新的作用
	}
}
