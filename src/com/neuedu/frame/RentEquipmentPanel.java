package com.neuedu.frame;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.neuedu.controller.EquipmentController;
import com.neuedu.entity.Equipment;

public class RentEquipmentPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private static RentEquipmentPanel instance = null;

	private JTable table;
	private DefaultTableModel model;
	private Vector<Object> vData;
	private Vector<Object> vRow;
	private Vector<String> vName;
	
	private RentEquipmentPanel() {
		setLayout(null);
		setSize(900, 700);
		
		table = new JTable();
		initTable();
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(52, 73, 785, 477);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("˵����ѡ�б�����������õ��豸���������ü��ɡ�");
		lblNewLabel.setBounds(52, 560, 785, 15);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRowIndex = table.getSelectedRow();
				EquipmentController ec = new EquipmentController("Equipment");
				try {
					ec.rentEquipment(model.getValueAt(selectRowIndex, 2).toString());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�"+e1,"����",JOptionPane.ERROR_MESSAGE);
					return;
				}
				model.setValueAt("�ѱ�����", selectRowIndex, 8);
				model.setValueAt(LoginFrame.loginAccount+"�û����ƹ���", selectRowIndex, 9);
				JOptionPane.showMessageDialog(null, "���óɹ���");
			}
		});
		btnNewButton.setBounds(608, 560, 100, 30);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame cloudFactoryManagerFrame = CloudFactoryManagerFrame.getInstance();
				cloudFactoryManagerFrame.setVisible(true);
				cloudFactoryManagerFrame.setContentPane(EquipmentManagePanel2.getInstance());
			}
		});
		btnNewButton_1.setBounds(737, 560, 100, 30);
		add(btnNewButton_1);

	}
	
	public void initTable() {
		vData = new Vector<Object>();
		vName = new Vector<String>();
		vName.add("���");
		vName.add("�豸���");
		vName.add("�豸����");
		vName.add("�豸���");
		vName.add("�豸���");
		vName.add("�豸����");
		vName.add("�豸״̬");
		vName.add("�豸��Դ");
		vName.add("����״̬");
		vName.add("��������");

		EquipmentController pc = new EquipmentController("Equipment");
		List<Object> olist = pc.queryAll();

		if(olist != null) {
			for (int i = 0; i < olist.size(); i++) {
				Equipment p = (Equipment) olist.get(i);
				if(p.getRent().equals("δ������")) {
					vRow = new Vector<Object>();
					vRow.add(vData.size()+1);
					vRow.add(p.getCode());
					vRow.add(p.getName());
					vRow.add(p.getType());
					vRow.add(p.getSpecification());
					vRow.add(p.getDescribe());
					vRow.add(p.getStatus());
					vRow.add(p.getFrom());
					vRow.add(p.getRent());
					vRow.add(p.getBelong());
					vData.add(vRow);
				}
			}
		}

		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		setTableFormat();
	}
	
	public void updateTable() {
		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
	}
	
	public void setTableFormat() {
		table.getColumnModel().getColumn(0).setPreferredWidth(5);// ����������еĿ��
		table.getColumnModel().getColumn(1).setPreferredWidth(80);// ����������еĿ��
		table.getColumnModel().getColumn(2).setPreferredWidth(40);// ����������еĿ��
		table.getColumnModel().getColumn(3).setPreferredWidth(25);// ����������еĿ��
		table.getColumnModel().getColumn(4).setPreferredWidth(40);// ����������еĿ��
		table.getColumnModel().getColumn(5).setPreferredWidth(40);// ����������еĿ��
		table.getColumnModel().getColumn(6).setPreferredWidth(40);// ����������еĿ��
		table.getColumnModel().getColumn(7).setPreferredWidth(40);// ����������еĿ��
		table.getColumnModel().getColumn(8).setPreferredWidth(70);// ����������еĿ��
		table.getColumnModel().getColumn(9).setPreferredWidth(70);// ����������еĿ��
	}
	
	public static RentEquipmentPanel getInstance() {
		if(instance == null) {
			instance = new RentEquipmentPanel();
		}
		return instance;
	}

}

