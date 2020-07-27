package com.neuedu.frame;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.neuedu.controller.ProductTypeController;
import com.neuedu.entity.ProductType;
import com.neuedu.exception.AlreadyExistException;
import com.neuedu.utils.TextFieldUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductTypeManagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static ProductTypeManagePanel instance = null;

	private JTable table;
	private DefaultTableModel model;
	private Vector<Object> vData;
	private Vector<Object> vRow;
	private Vector<String> vName;
	private JTextField textField;
	private Vector<Object> tempvData;
	
	private ProductTypeManagePanel() {
		setLayout(null);
		setSize(900, 700);
		
		table = new JTable();
		initTable();
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			// 发生了单击事件
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
		
		JLabel lblNewLabel = new JLabel("产品类别*：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(71, 55, 93, 27);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 15));
		textField.setBounds(157, 55, 117, 27);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("新增");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TextFieldUtil.isEmpty(textField, "产品类别不能为空！"))
					return;

				String name = textField.getText();

				ProductTypeController ptc = new ProductTypeController("ProductType");

				ProductType pt = new ProductType(name);

				try {
					ptc.save(pt);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "系统错误:" + e1, "错误", JOptionPane.ERROR_MESSAGE);
					return;
				} catch (AlreadyExistException e2) {
					JOptionPane.showMessageDialog(null, "产品类别已存在！");
					return;
				}
				
				JOptionPane.showMessageDialog(null, "新增成功！");

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
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确认?", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(n == JOptionPane.OK_OPTION) {
					int selectRowIndex = table.getSelectedRow(); // 获取所选行的索引值
					if (selectRowIndex != -1) {
						ProductTypeController ptc = new ProductTypeController("ProductType");
						String name = model.getValueAt(selectRowIndex, 1).toString();
						try {
							ptc.delete(name);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "删除失败:" + e1, "错误", JOptionPane.ERROR_MESSAGE);
							return;
						}
						model.removeRow(selectRowIndex);
						JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "请单击选中您需要删除的那一行！", "提示", JOptionPane.PLAIN_MESSAGE);
						return;
					}
				}
			}
		});
		btnNewButton_1.setBounds(401, 53, 100, 30);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("修改");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRowIndex = table.getSelectedRow();
				
				String newName = textField.getText();
				String oldName = model.getValueAt(selectRowIndex, 1).toString();
				
				ProductTypeController ptc = new ProductTypeController("ProductType");
				try {
					ptc.rename(oldName, newName);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "修改失败！"+ e1, "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				model.setValueAt(newName, selectRowIndex, 1);
				JOptionPane.showMessageDialog(null, "修改成功！");
			}
		});
		btnNewButton_2.setBounds(505, 53, 100, 30);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("检索");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TextFieldUtil.isEmpty(textField, "请输入产品类别进行检索！"))
					return;
				
				ProductTypeController ptc = new ProductTypeController("ProductType");
				ProductType pt = null;
				try {
					pt = (ProductType) ptc.queryOne(textField.getText());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "检索失败！"+ e1, "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (pt != null) {
					tempvData = new Vector<Object>();
					vRow = new Vector<Object>();
					vRow.add(tempvData.size()+1);
					vRow.add(pt.getName());
					tempvData.add(vRow);
					table.setModel(new DefaultTableModel(tempvData, vName));
				} else {
					JOptionPane.showMessageDialog(null, "未找到该产品类别!", "提示", JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		});
		btnNewButton_3.setBounds(607, 53, 100, 30);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("重置");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initTable();
			}
		});
		btnNewButton_4.setBounds(737, 606, 100, 30);
		add(btnNewButton_4);
		
		// 框框
		JLabel line = new JLabel("");
		line.setBounds(52, 41, 785, 57);
		line.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		add(line);
		
		JLabel lblNewLabel_1 = new JLabel("说明：修改功能的实现需要您先单击选中表格中待修改的那一行，然后向输入框中输入修改后的数据，单击修改即可。");
		lblNewLabel_1.setBounds(52, 100, 785, 15);
		add(lblNewLabel_1);

	}
	
	public void initTable() {
		vData = new Vector<Object>();
		vName = new Vector<String>();
		vName.add("序号");
		vName.add("产品类别");

		ProductTypeController ptc = new ProductTypeController("ProductType");
		List<Object> olist = ptc.queryAll();

		for (int i = 0; i < olist.size(); i++) {
			ProductType pt = (ProductType) olist.get(i);
			vRow = new Vector<Object>();
			vRow.add(vData.size()+1);
			vRow.add(pt.getName());
			vData.add(vRow);
		}

		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
	}
	
	public void updateTable() {
		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
	}
	
	public static ProductTypeManagePanel getInstance() {
		if(instance == null) {
			instance = new ProductTypeManagePanel();
		}
		return instance;
	}
}
