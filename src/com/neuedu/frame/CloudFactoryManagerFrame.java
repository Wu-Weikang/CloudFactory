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
 * �ƹ�������Ա������
 * 
 * @author ��ΰ��
 * @date 2020-7-17
 */
public class CloudFactoryManagerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static CloudFactoryManagerFrame instance = null;

	private CloudFactoryManagerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("�ƹ�������Ա");
		setSize(900, 700);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel imagePanel = new ImagePanel("cloudFactoryManage.png");
		changeContentPane(imagePanel);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// �ҵĹ���
		JMenu menu1 = new JMenu("�ҵĹ���");
		menuBar.add(menu1);
		JMenuItem Item11 = new JMenuItem("�ҵ��豸");
		menu1.add(Item11);
		Item11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeContentPane(EquipmentManagePanel2.getInstance());
			}
		});

		// ��������
		JMenu menu2 = new JMenu("��������");
		menuBar.add(menu2);
		JMenuItem Item21 = new JMenuItem("�����ӵ�");
		menu2.add(Item21);
		Item21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// -----------------------?
			}
		});
		menu2.addSeparator();
		JMenuItem Item22 = new JMenuItem("�����Ų�");
		menu2.add(Item22);
		Item22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// -----------------------?
			}
		});

	}

	public static CloudFactoryManagerFrame getInstance() {
		if (instance == null) {
			instance = new CloudFactoryManagerFrame();
		}
		return instance;
	}

	// �л����
	public void changeContentPane(Container contentPane) {
		this.setContentPane(contentPane);
		this.revalidate(); // �൱��ˢ�µ�����
	}
}
