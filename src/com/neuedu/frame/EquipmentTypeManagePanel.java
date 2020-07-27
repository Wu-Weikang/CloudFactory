package com.neuedu.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.neuedu.controller.EquipmentTypeController;
import com.neuedu.entity.EquipmentType;
import com.neuedu.exception.AlreadyExistException;
import com.neuedu.exception.TypeIsReferencedException;
import com.neuedu.utils.TextFieldUtil;

public class EquipmentTypeManagePanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private static EquipmentTypeManagePanel instance = null;

	private JTable table;
	private DefaultTableModel model;
	private Vector<Object> vData;
	private Vector<Object> vRow;
	private Vector<String> vName;
	private JTextField textField;
	private Vector<Object> tempvData;
	
	private EquipmentTypeManagePanel() {
		setLayout(null);
		setSize(900, 700);
		
		table = new JTable();
		initTable();
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			// �����˵����¼�
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				Object name = model.getValueAt(selectedRow, 1);
				textField.setText(name.toString());
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(52, 125, 785, 477);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("�豸���*��");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel.setBounds(71, 55, 93, 27);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("����", Font.PLAIN, 15));
		textField.setBounds(157, 55, 117, 27);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TextFieldUtil.isEmpty(textField, "�豸�����Ϊ�գ�"))
					return;

				String name = textField.getText();

				EquipmentTypeController etc = new EquipmentTypeController("EquipmentType");

				EquipmentType et = new EquipmentType(name);

				try {
					etc.save(et);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "ϵͳ����:" + e1, "����", JOptionPane.ERROR_MESSAGE);
					return;
				} catch (AlreadyExistException e2) {
					JOptionPane.showMessageDialog(null, "�豸����Ѵ��ڣ�");
					return;
				}
				
				JOptionPane.showMessageDialog(null, "�����ɹ���");

				vRow = new Vector<Object>();
				vRow.add(vData.size()+1);
				vRow.add(name);
				vData.add(vRow);
				updateTable();

				textField.setText("");
			}
		});
		btnNewButton.setBounds(297, 53, 100, 30);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ɾ��");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ��?", "ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(n == JOptionPane.OK_OPTION) {
					int selectRowIndex = table.getSelectedRow(); // ��ȡ��ѡ�е�����ֵ
					if (selectRowIndex != -1) {
						EquipmentTypeController etc = new EquipmentTypeController("EquipmentType");
						String name = model.getValueAt(selectRowIndex, 1).toString();
						try {
							etc.delete(name);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "ɾ��ʧ��:" + e1, "����", JOptionPane.ERROR_MESSAGE);
							return;
						} catch (TypeIsReferencedException e2) {
							JOptionPane.showMessageDialog(null, "ɾ��ʧ��: ���ͱ��豸����" , "����", JOptionPane.ERROR_MESSAGE);
							return;
						}
						model.removeRow(selectRowIndex);
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "�뵥��ѡ������Ҫɾ������һ�У�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
						return;
					}
				}
			}
		});
		btnNewButton_1.setBounds(401, 53, 100, 30);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("�޸�");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRowIndex = table.getSelectedRow();
				
				String newName = textField.getText();
				String oldName = model.getValueAt(selectRowIndex, 1).toString();
				
				EquipmentTypeController ptc = new EquipmentTypeController("EquipmentType");
				try {
					ptc.rename(oldName, newName);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�"+ e1, "����", JOptionPane.ERROR_MESSAGE);
				}
				model.setValueAt(newName, selectRowIndex, 1);
				JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
			}
		});
		btnNewButton_2.setBounds(505, 53, 100, 30);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("����");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TextFieldUtil.isEmpty(textField, "�������豸�����м�����"))
					return;
				
				EquipmentTypeController ptc = new EquipmentTypeController("EquipmentType");
				EquipmentType pt = null;
				try {
					pt = (EquipmentType) ptc.queryOne(textField.getText());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�"+ e1, "����", JOptionPane.ERROR_MESSAGE);
				}

				if (pt != null) {
					tempvData = new Vector<Object>();
					vRow = new Vector<Object>();
					vRow.add(tempvData.size()+1);
					vRow.add(pt.getName());
					tempvData.add(vRow);
					table.setModel(new DefaultTableModel(tempvData, vName));
				} else {
					JOptionPane.showMessageDialog(null, "δ�ҵ����豸���!", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnNewButton_3.setBounds(607, 53, 100, 30);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("����");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initTable();
			}
		});
		btnNewButton_4.setBounds(737, 606, 100, 30);
		add(btnNewButton_4);
		
		// ���
		JLabel line = new JLabel("");
		line.setBounds(52, 41, 785, 57);
		line.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		add(line);
		
		JLabel lblNewLabel_1 = new JLabel("˵�����޸Ĺ��ܵ�ʵ����Ҫ���ȵ���ѡ�б���д��޸ĵ���һ�У�Ȼ����������������޸ĺ�����ݣ������޸ļ��ɡ�");
		lblNewLabel_1.setBounds(52, 100, 785, 15);
		add(lblNewLabel_1);

	}
	
	public void initTable() {
		vData = new Vector<Object>();
		vName = new Vector<String>();
		vName.add("���");
		vName.add("�豸���");

		EquipmentTypeController ec = new EquipmentTypeController("EquipmentType");
		List<Object> olist = ec.queryAll();

		for (int i = 0; i < olist.size(); i++) {
			EquipmentType et = (EquipmentType) olist.get(i);
			vRow = new Vector<Object>();
			vRow.add(vData.size()+1);
			vRow.add(et.getName());
			vData.add(vRow);
		}

		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
	}
	
	public void updateTable() {
		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
	}
	
	public static EquipmentTypeManagePanel getInstance() {
		if(instance == null) {
			instance = new EquipmentTypeManagePanel();
		}
		return instance;
	}

}
