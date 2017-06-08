package me.zhongezhao.Chess.GUI;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import me.zhongezhao.Chess.Main.Main;

/**
 * the class that will hold the main frame of the application
 * @author zhongweizhao
 *
 */
public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1342122362626796292L;
	private JFrame frame;
	
	public Window(int width, int height, String title, Main chess) {
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(chess);
		
		frame.setVisible(true);
		this.requestFocus();
		chess.getTopBar().setWindow(this);
		
		chess.start();
	}

	public JFrame getFrame() {
		return frame;
	}
	
}
