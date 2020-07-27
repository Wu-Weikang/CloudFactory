package com.neuedu.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.neuedu.controller.ProductController;
import com.neuedu.controller.ProductTypeController;
import com.neuedu.entity.Product;
import com.neuedu.entity.ProductType;
import com.neuedu.exception.AlreadyExistException;
import com.neuedu.utils.TextFieldUtil;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Font;

public class AddProductPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static AddProductPanel instance = null;

	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;

	private AddProductPanel() {
		setLayout(null);
		setSize(900, 700);

		// 框框
		JLabel line = new JLabel("");
		line.setBounds(253, 155, 381, 334);
		line.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		add(line);

		JLabel lblNewLabel = new JLabel("产品名称*：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(302, 202, 110, 30);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(436, 205, 145, 30);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("产品类别*:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(302, 263, 110, 30);
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
		comboBox.setBounds(436, 265, 145, 30);
		add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("产品规格：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(302, 322, 110, 30);
		add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(436, 325, 145, 30);
		add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("产品描述：");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(302, 392, 110, 30);
		add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(436, 392, 145, 30);
		add(textField_3);
		textField_3.setColumns(10);

		JButton btnNewButton_1 = new JButton("确定");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (TextFieldUtil.isEmpty(textField, "产品名称不能为空！"))
					return;
				String name = textField.getText();
				String type = (String) comboBox.getSelectedItem();
				String specification = textField_2.getText();
				String describe = textField_3.getText();
				Product p = new Product(name, type, specification, describe);
				
				ProductController pc = new ProductController("Product");
				try {
					pc.save(p);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "添加失败："+e);
					return;
				} catch (AlreadyExistException e2) {
					JOptionPane.showMessageDialog(null, "产品名称已存在！");
					return;
				}
				JOptionPane.showMessageDialog(null, "新增成功！");
				textField.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
		});
		btnNewButton_1.setBounds(302, 556, 110, 40);
		add(btnNewButton_1);

		JButton btnNewButton = new JButton("返回");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame systemmanageFrame = SystemManagerFrame.getInstance();
				systemmanageFrame.setVisible(true);
				systemmanageFrame.setContentPane(ProductManagePanel.getInstance());
			}
		});
		btnNewButton.setBounds(471, 556, 110, 40);
		add(btnNewButton);
	}

	public static AddProductPanel getInstance() {
		if (instance == null) {
			instance = new AddProductPanel();
		}
		return instance;
	}
}
