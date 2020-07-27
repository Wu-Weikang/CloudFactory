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
 * ��������Ա������
 * 
 * @author ��ΰ��
 * @date 2020-7-17
 */
public class SystemManagerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static SystemManagerFrame instance = null;

	private SystemManagerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("��������Ա");
		setSize(900, 700);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel imagePanel = new ImagePanel("superManage.png");
		changeContentPane(imagePanel);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// �û������½����޸ģ�ɾ������ѯ��
		JMenu userManage = new JMenu("ϵͳ����");
		menuBar.add(userManage);
		JMenuItem umItem = new JMenuItem("�û�����");
		userManage.add(umItem);
		umItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeContentPane(UserManagepanel.getInstance());
			}
		});

		// �ƹ�����Ϣ
		JMenu cloudFactoryInfo = new JMenu("�ƹ���");
		menuBar.add(cloudFactoryInfo);
		JMenuItem cfiItem = new JMenuItem("�ƹ�����Ϣ");
		cloudFactoryInfo.add(cfiItem);
		cfiItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeContentPane(CloudFactoryInfoPanel.getInstance());
			}
		});

		// ��Ʒ����
		JMenu productManage = new JMenu("��Ʒ����");
		menuBar.add(productManage);
		JMenuItem pmItem1 = new JMenuItem("��Ʒ������");
		productManage.add(pmItem1);
		pmItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeContentPane(ProductTypeManagePanel.getInstance());
			}
		});
		productManage.addSeparator();
		JMenuItem pmItem2 = new JMenuItem("��Ʒ��Ϣ����");
		productManage.add(pmItem2);
		pmItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeContentPane(ProductManagePanel.getInstance());
			}
		});

		// ��������
		JMenu capacityCentre = new JMenu("��������");
		menuBar.add(capacityCentre);
		JMenuItem ccItem1 = new JMenuItem("�豸���͹���");
		capacityCentre.add(ccItem1);
		ccItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeContentPane(EquipmentTypeManagePanel.getInstance());
			}
		});
		capacityCentre.addSeparator();
		JMenuItem ccItem2 = new JMenuItem("�豸��Ϣ����");
		capacityCentre.add(ccItem2);
		ccItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeContentPane(EquipmentManagePanel.getInstance());
			}
		});

		// ��������
		JMenu orderManage = new JMenu("��������");
		menuBar.add(orderManage);
		JMenuItem omItem = new JMenuItem("����������Ϣ");
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

	// �л����
	public void changeContentPane(Container contentPane) {
		this.setContentPane(contentPane);
		this.revalidate(); // �൱��ˢ�µ�����
	}
}
