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
 * �����̴�����
 * 
 * @author ��ΰ��
 * @date 2020-7-17
 */
public class DealerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static DealerFrame instance = null;

	private DealerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("������");
		setSize(900, 700);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel imagePanel = new ImagePanel("dealer.png");
		changeContentPane(imagePanel);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// ��������
		JMenu menu1 = new JMenu("��������");
		menuBar.add(menu1);
		JMenuItem Item11 = new JMenuItem("�ҵĶ���");
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

	// �л����
	public void changeContentPane(Container contentPane) {
		this.setContentPane(contentPane);
		this.revalidate(); // �൱��ˢ�µ�����
	}
}
