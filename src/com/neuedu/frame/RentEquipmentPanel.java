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
		
		JLabel lblNewLabel = new JLabel("说明：选中表格中你想租用的设备，单击租用即可。");
		lblNewLabel.setBounds(52, 560, 785, 15);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("租用");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRowIndex = table.getSelectedRow();
				EquipmentController ec = new EquipmentController("Equipment");
				try {
					ec.rentEquipment(model.getValueAt(selectRowIndex, 2).toString());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "租用失败："+e1,"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				model.setValueAt("已被租用", selectRowIndex, 8);
				model.setValueAt(LoginFrame.loginAccount+"用户的云工厂", selectRowIndex, 9);
				JOptionPane.showMessageDialog(null, "租用成功！");
			}
		});
		btnNewButton.setBounds(608, 560, 100, 30);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回");
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
		vName.add("序号");
		vName.add("设备编号");
		vName.add("设备名称");
		vName.add("设备类别");
		vName.add("设备规格");
		vName.add("设备描述");
		vName.add("设备状态");
		vName.add("设备来源");
		vName.add("租用状态");
		vName.add("所属工厂");

		EquipmentController pc = new EquipmentController("Equipment");
		List<Object> olist = pc.queryAll();

		if(olist != null) {
			for (int i = 0; i < olist.size(); i++) {
				Equipment p = (Equipment) olist.get(i);
				if(p.getRent().equals("未被租用")) {
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
		table.getColumnModel().getColumn(0).setPreferredWidth(5);// 这个是设置列的宽度
		table.getColumnModel().getColumn(1).setPreferredWidth(80);// 这个是设置列的宽度
		table.getColumnModel().getColumn(2).setPreferredWidth(40);// 这个是设置列的宽度
		table.getColumnModel().getColumn(3).setPreferredWidth(25);// 这个是设置列的宽度
		table.getColumnModel().getColumn(4).setPreferredWidth(40);// 这个是设置列的宽度
		table.getColumnModel().getColumn(5).setPreferredWidth(40);// 这个是设置列的宽度
		table.getColumnModel().getColumn(6).setPreferredWidth(40);// 这个是设置列的宽度
		table.getColumnModel().getColumn(7).setPreferredWidth(40);// 这个是设置列的宽度
		table.getColumnModel().getColumn(8).setPreferredWidth(70);// 这个是设置列的宽度
		table.getColumnModel().getColumn(9).setPreferredWidth(70);// 这个是设置列的宽度
	}
	
	public static RentEquipmentPanel getInstance() {
		if(instance == null) {
			instance = new RentEquipmentPanel();
		}
		return instance;
	}

}

