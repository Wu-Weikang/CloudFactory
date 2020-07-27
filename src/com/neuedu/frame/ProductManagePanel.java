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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.neuedu.controller.ProductController;
import com.neuedu.controller.ProductTypeController;
import com.neuedu.entity.Product;
import com.neuedu.entity.ProductType;
import com.neuedu.utils.TextFieldUtil;
import javax.swing.JComboBox;

public class ProductManagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static ProductManagePanel instance = null;

	private JTable table;
	private DefaultTableModel model;
	private Vector<Object> vData;
	private Vector<Object> vRow;
	private Vector<String> vName;
	private Vector<Object> tempvData;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private ProductManagePanel() {
		setLayout(null);
		setSize(900, 700);
		
		// 框框
		JLabel line = new JLabel("");
		line.setBounds(52, 22, 785, 65);
		line.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		add(line);
		
		JLabel lblNewLabel = new JLabel("产品名称：");
		lblNewLabel.setBounds(65, 40, 70, 21);
		add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(145, 40, 92, 21);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("产品类别:");
		lblNewLabel_1.setBounds(247, 43, 78, 15);
		add(lblNewLabel_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		ProductTypeController ptc = new ProductTypeController("ProductType");
		List<Object> list = ptc.queryAll();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ProductType pt = (ProductType) list.get(i);
				comboBox.addItem(pt.getName());
			}
		}
		comboBox.setBounds(335, 40, 89, 21);
		add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("产品规格：");
		lblNewLabel_2.setBounds(434, 43, 76, 15);
		add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(507, 40, 97, 21);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("产品描述：");
		lblNewLabel_3.setBounds(614, 43, 81, 15);
		add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(692, 40, 103, 21);
		add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectRowIndex = table.getSelectedRow();
				ProductController pc = new ProductController("Product");
				if (selectRowIndex != -1) {
					Product p = null;
					try {
						p = (Product) pc.queryOne(model.getValueAt(selectRowIndex, 2).toString());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "修改失败:" + e1, "错误", JOptionPane.ERROR_MESSAGE);
					}
					p.setName(textField_1.getText());
					p.setType(comboBox.getSelectedItem().toString());
					p.setSpecification(textField_3.getText());
					p.setDescribe(textField_4.getText());
					try {
						pc.modify(p);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "修改失败:" + e1, "错误", JOptionPane.ERROR_MESSAGE);
					}
					model.setValueAt(textField_1.getText(), selectRowIndex, 2);
					model.setValueAt(comboBox.getSelectedItem(), selectRowIndex, 3);
					model.setValueAt(textField_3.getText(), selectRowIndex, 4);
					model.setValueAt(textField_4.getText(), selectRowIndex, 5);
					JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "请单击选中您需要修改的那一行！", "提示", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(641, 97, 93, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n = JOptionPane.showConfirmDialog(null, "确认?", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(n == JOptionPane.OK_OPTION) {
					int selectRowIndex = table.getSelectedRow(); // 获取所选行的索引值
					if (selectRowIndex != -1) {
						ProductController pc = new ProductController("Product");
						String code = model.getValueAt(selectRowIndex, 1).toString();
						try {
							pc.delete(code);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "删除失败:" + e1, "错误", JOptionPane.ERROR_MESSAGE);
						}
						model.removeRow(selectRowIndex);
						JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "请单击选中您需要删除的那一行！", "提示", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		btnNewButton_1.setBounds(744, 97, 93, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("新建");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame systemmanageFrame = SystemManagerFrame.getInstance();
				systemmanageFrame.setVisible(true);
				systemmanageFrame.setContentPane(AddProductPanel.getInstance());
			}
		});
		btnNewButton_2.setBounds(52, 97, 93, 23);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("检索");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (TextFieldUtil.isEmpty(textField_1, "请输入产品名称进行检索！"))
					return;
				
				ProductController pc = new ProductController("Product");
				Product p = null;
				try {
					p = (Product) pc.queryOne(textField_1.getText());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "检索失败！"+ e1, "错误", JOptionPane.ERROR_MESSAGE);
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
					tempvData.add(vRow);
					table.setModel(new DefaultTableModel(tempvData, vName));
				} else {
					JOptionPane.showMessageDialog(null, "未找到该产品名称!", "提示", JOptionPane.PLAIN_MESSAGE);
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
			// 发生了单击事件
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				Object name = model.getValueAt(selectedRow, 2);
				Object type = model.getValueAt(selectedRow, 3);
				Object specification = model.getValueAt(selectedRow, 4);
				Object describe = model.getValueAt(selectedRow, 5);
				textField_1.setText(name.toString());
				comboBox.setSelectedItem(type);
				textField_3.setText(specification.toString());
				textField_4.setText(describe.toString());
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(52, 130, 785, 466);
		add(scrollPane);
		
		JButton btnNewButton_4 = new JButton("重置");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initTable();
			}
		});
		btnNewButton_4.setBounds(744, 606, 93, 23);
		add(btnNewButton_4);
	}
	
	public void initTable() {
		vData = new Vector<Object>();
		vName = new Vector<String>();
		vName.add("序号");
		vName.add("产品编号");
		vName.add("产品名称");
		vName.add("产品类别");
		vName.add("产品规格");
		vName.add("产品描述");

		ProductController pc = new ProductController("Product");
		List<Object> olist = pc.queryAll();

		if(olist != null) {
			for (int i = 0; i < olist.size(); i++) {
				Product p = (Product) olist.get(i);
				vRow = new Vector<Object>();
				vRow.add(vData.size()+1);
				vRow.add(p.getCode());
				vRow.add(p.getName());
				vRow.add(p.getType());
				vRow.add(p.getSpecification());
				vRow.add(p.getDescribe());
				vData.add(vRow);
			}
		}

		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
	}
	
	public void updateTable() {
		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
	}
	
	public static ProductManagePanel getInstance() {
		if(instance == null) {
			instance = new ProductManagePanel();
		}
		return instance;
	}
}
