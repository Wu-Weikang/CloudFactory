package com.neuedu.frame;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	String img;
	
	
	public ImagePanel(String img) {
		setLayout(null);
		setSize(900, 700);
		this.img = img;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("image/"+img);
		g.drawImage(icon.getImage(), 0, 0, 900, 700, null);
	}
}
