package com.neuedu.frame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.neuedu.controller.CloudFactoryController;
import com.neuedu.entity.CloudFactoryManage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

/**
 * 云工厂信息面板类
 * 
 * @author 伍伟康
 * @date 2020-7-17
 */
public class CloudFactoryInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static CloudFactoryInfoPanel instance = null;

	private JTextField textField;
	private JTable table;
	private DefaultTableModel model;
	private Vector<Object> vData;
	private Vector<Object> vRow;
	private Vector<String> vName;
	private Vector<Object> tempvData;

	private CloudFactoryInfoPanel() {
		setLayout(null);
		setSize(900, 700);

		textField = new JTextField();
		textField.setBounds(84, 50, 174, 21);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("工厂名称：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(84, 32, 80, 18);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("提示：单击选中表格中需要开启/关停的工厂，单击按钮即可！");
		lblNewLabel_1.setBounds(54, 586, 504, 15);
		add(lblNewLabel_1);
		
		// 框框
		JLabel line = new JLabel("");
		line.setBounds(54, 21, 785, 64);
		line.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		add(line);

		// 创建表格（带滚动条）
		table = new JTable();
		initTable();
		table.setRowHeight(30);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(54, 96, 785, 476);
		add(scrollPane);
		
		JButton btnNewButton_2 = new JButton("开启");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectRowIndex = table.getSelectedRow();
				if (model.getValueAt(selectRowIndex, 6).equals("关停")) {
					model.setValueAt("正常", selectRowIndex, 6);
					CloudFactoryController cfc = new CloudFactoryController("CloudFactory");
					String account = model.getValueAt(selectRowIndex, 5).toString();
					try {
						cfc.openFactory(account);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "开启失败:" + e, "错误", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "工厂已经为正常状态");
					return;
				}
				JOptionPane.showMessageDialog(null, "开启成功！", "提示", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnNewButton_2.setBounds(636, 582, 93, 23);
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("关停");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectRowIndex = table.getSelectedRow();
				if (model.getValueAt(selectRowIndex, 6).equals("正常")) {
					model.setValueAt("关停", selectRowIndex, 6);
					CloudFactoryController cfc = new CloudFactoryController("CloudFactory");
					String account = model.getValueAt(selectRowIndex, 5).toString();
					try {
						cfc.closeFactory(account);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "关停失败:" + e, "错误", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "工厂已经为关停状态");
					return;
				}
				JOptionPane.showMessageDialog(null, "关停成功！", "提示", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnNewButton_3.setBounds(746, 582, 93, 23);
		add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initTable();
			}
		});
		btnNewButton_1.setBounds(402, 49, 93, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CloudFactoryController cfc = new CloudFactoryController("CloudFactory");
				CloudFactoryManage cfm = null;
				try {
					cfm = (CloudFactoryManage) cfc.queryOne(textField.getText().toString());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "检索失败:" + e, "错误", JOptionPane.ERROR_MESSAGE);
				}
				tempvData = new Vector<Object>();
				if (cfm != null) {
					vRow = new Vector<Object>();
					vRow.add(tempvData.size()+1);
					vRow.add(cfm.getFactory().getName());
					vRow.add(cfm.getFactory().getIntro());
					vRow.add(cfm.getName());
					vRow.add(cfm.getContactNumber());
					vRow.add(cfm.getAccount());
					vRow.add(cfm.getFactory().getStatus());
					tempvData.add(vRow);
					table.setModel(new DefaultTableModel(tempvData, vName));
				} else {
					JOptionPane.showMessageDialog(null, "未找到该名称工厂!", "提示", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton.setBounds(299, 49, 102, 23);
		add(btnNewButton);
	}

	public void initTable() {
		vData = new Vector<Object>();
		vName = new Vector<String>();
		vName.add("序号");
		vName.add("工厂名称");
		vName.add("工厂简介");
		vName.add("负责人");
		vName.add("联系方式");
		vName.add("登录账号");
		vName.add("工厂状态");

		CloudFactoryController cfc = new CloudFactoryController("CloudFactory");
		List<Object> olist = cfc.queryAll();
		for (int i = 0; i < olist.size(); i++) {
			CloudFactoryManage cfm = (CloudFactoryManage) olist.get(i);
			vRow = new Vector<Object>();
			vRow.add(vData.size()+1);
			vRow.add(cfm.getFactory().getName());
			vRow.add(cfm.getFactory().getIntro());
			vRow.add(cfm.getName());
			vRow.add(cfm.getContactNumber());
			vRow.add(cfm.getAccount());
			vRow.add(cfm.getFactory().getStatus());
			vData.add(vRow);
		}
		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
	}

	public static CloudFactoryInfoPanel getInstance() {
		if (instance == null) {
			instance = new CloudFactoryInfoPanel();
		}
		return instance;
	}
}
