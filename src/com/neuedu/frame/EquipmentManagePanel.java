package com.neuedu.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.neuedu.controller.EquipmentController;
import com.neuedu.controller.EquipmentTypeController;
import com.neuedu.entity.Equipment;
import com.neuedu.entity.EquipmentType;
import com.neuedu.utils.TextFieldUtil;

public class EquipmentManagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static EquipmentManagePanel instance = null;

	private JTable table;
	private DefaultTableModel model;
	private Vector<Object> vData;
	private Vector<Object> vRow;
	private Vector<String> vName;
	private Vector<Object> tempvData;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private EquipmentManagePanel() {
		setLayout(null);
		setSize(900, 700);
		
		// ���
		JLabel line = new JLabel("");
		line.setBounds(52, 22, 785, 65);
		line.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		add(line);
		
		JLabel lblNewLabel = new JLabel("�豸���ƣ�");
		lblNewLabel.setBounds(65, 40, 70, 21);
		add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(145, 40, 92, 21);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("�豸���:");
		lblNewLabel_1.setBounds(247, 43, 78, 15);
		add(lblNewLabel_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		EquipmentTypeController ptc = new EquipmentTypeController("EquipmentType");
		List<Object> list = ptc.queryAll();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				EquipmentType pt = (EquipmentType) list.get(i);
				comboBox.addItem(pt.getName());
			}
		}
		comboBox.setBounds(335, 40, 89, 21);
		add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("�豸���");
		lblNewLabel_2.setBounds(434, 43, 76, 15);
		add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(507, 40, 97, 21);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("�豸������");
		lblNewLabel_3.setBounds(614, 43, 81, 15);
		add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(692, 40, 103, 21);
		add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("�޸�");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectRowIndex = table.getSelectedRow();
				EquipmentController pc = new EquipmentController("Equipment");
				if (selectRowIndex != -1) {
					Equipment p = null;
					try {
						p = (Equipment) pc.queryOne(model.getValueAt(selectRowIndex, 2).toString());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "�޸�ʧ��:" + e1, "����", JOptionPane.ERROR_MESSAGE);
					}
					p.setName(textField_1.getText());
					p.setType(comboBox.getSelectedItem().toString());
					p.setSpecification(textField_3.getText());
					p.setDescribe(textField_4.getText());
					try {
						pc.modify(p);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "�޸�ʧ��:" + e1, "����", JOptionPane.ERROR_MESSAGE);
					}
					model.setValueAt(textField_1.getText(), selectRowIndex, 2);
					model.setValueAt(comboBox.getSelectedItem(), selectRowIndex, 3);
					model.setValueAt(textField_3.getText(), selectRowIndex, 4);
					model.setValueAt(textField_4.getText(), selectRowIndex, 5);
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "�뵥��ѡ������Ҫ�޸ĵ���һ�У�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(641, 97, 93, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ɾ��");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ��?", "ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(n == JOptionPane.OK_OPTION) {
					int selectRowIndex = table.getSelectedRow(); // ��ȡ��ѡ�е�����ֵ
					if (selectRowIndex != -1) {
						
						if(model.getValueAt(selectRowIndex, 8).equals("δ������")) {
							if(model.getValueAt(selectRowIndex, 7).equals("�����豸")) {
								EquipmentController pc = new EquipmentController("Equipment");
								String code = model.getValueAt(selectRowIndex, 1).toString();
								try {
									pc.delete(code);
								} catch (IOException e1) {
									JOptionPane.showMessageDialog(null, "ɾ��ʧ��:" + e1, "����", JOptionPane.ERROR_MESSAGE);
								}
								model.removeRow(selectRowIndex);
								JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null, "�����Լ����豸������ɾ����", "��ʾ", JOptionPane.PLAIN_MESSAGE);
								return;
							}
						}else {
							JOptionPane.showMessageDialog(null, "�����õ��豸������ɾ����", "��ʾ", JOptionPane.PLAIN_MESSAGE);
							return;
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "�뵥��ѡ������Ҫɾ������һ�У�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
						return;
					}
				}
			}
		});
		btnNewButton_1.setBounds(744, 97, 93, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("�½�");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame systemmanageFrame = SystemManagerFrame.getInstance();
				systemmanageFrame.setVisible(true);
				systemmanageFrame.setContentPane(AddEquipmentPanel.getInstance());
			}
		});
		btnNewButton_2.setBounds(52, 97, 93, 23);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("����");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (TextFieldUtil.isEmpty(textField_1, "�������豸���ƽ��м�����"))
					return;
				
				EquipmentController pc = new EquipmentController("Equipment");
				Equipment p = null;
				try {
					p = (Equipment) pc.queryOne(textField_1.getText());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�"+ e1, "����", JOptionPane.ERROR_MESSAGE);
				}

				if (p != null) {
					tempvData = new Vector<Object>();
					vRow = new Vector<Object>();
					vRow.add(tempvData.size()+1);
					vRow.add(p.getCode());
					vRow.add(p.getName());
					vRow.add(p.getType());
					vRow.add(p.getSpecification());
					vRow.add(p.getDescribe());
					vRow.add(p.getStatus());
					vRow.add(p.getFrom());
					vRow.add(p.getRent());
					vRow.add(p.getBelong());
					tempvData.add(vRow);
					table.setModel(new DefaultTableModel(tempvData, vName));
					setTableFormat();
				} else {
					JOptionPane.showMessageDialog(null, "δ�ҵ����豸����!", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		});
		btnNewButton_3.setBounds(538, 97, 93, 23);
		add(btnNewButton_3);
		
		table = new JTable();
		initTable();
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {//---------------------------------------?
			// �����˵����¼�
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				Object name = model.getValueAt(selectedRow, 2);
				Object type = model.getValueAt(selectedRow, 3);
				Object specification = model.getValueAt(selectedRow, 4);
				Object describe = model.getValueAt(selectedRow, 5);
				textField_1.setText(name.toString());
				comboBox.setSelectedItem(type);
				textField_3.setText(specification.toString());
				textField_4.setText((String)describe);
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(52, 130, 785, 466);
		add(scrollPane);
		
		JButton btnNewButton_4 = new JButton("����");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initTable();
			}
		});
		btnNewButton_4.setBounds(52, 606, 93, 23);
		add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("����");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectRowIndex = table.getSelectedRow();
				if (model.getValueAt(selectRowIndex, 6).equals("�ѹر�")) {
					model.setValueAt("������", selectRowIndex, 6);
					EquipmentController cfc = new EquipmentController("Equipment");
					String name = model.getValueAt(selectRowIndex, 2).toString();
					try {
						cfc.openEquipment(name);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "����ʧ��:" + e, "����", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "�豸�Ѿ�Ϊ����״̬");
					return;
				}
				JOptionPane.showMessageDialog(null, "�����ɹ���", "��ʾ", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnNewButton_5.setBounds(641, 606, 93, 23);
		add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("�ػ�");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectRowIndex = table.getSelectedRow();
				if (model.getValueAt(selectRowIndex, 6).equals("������")) {
					model.setValueAt("�ѹر�", selectRowIndex, 6);
					EquipmentController cfc = new EquipmentController("Equipment");
					String name = model.getValueAt(selectRowIndex, 2).toString();
					try {
						cfc.closeEquipment(name);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "�ػ�ʧ��:" + e, "����", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "�豸�Ѿ�Ϊ�ػ�״̬");
					return;
				}
				JOptionPane.showMessageDialog(null, "�ػ��ɹ���", "��ʾ", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnNewButton_6.setBounds(744, 606, 93, 23);
		add(btnNewButton_6);
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
	
	public static EquipmentManagePanel getInstance() {
		if(instance == null) {
			instance = new EquipmentManagePanel();
		}
		return instance;
	}

}
