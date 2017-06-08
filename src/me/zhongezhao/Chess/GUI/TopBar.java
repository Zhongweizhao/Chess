package me.zhongezhao.Chess.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class TopBar extends MouseAdapter {
	
	private int height, width;
	private int x = 0, y = 0;
	private Point mouseDownCoords;
	private Window window;
	private Rectangle2D closeButtonBorderBox;
	private Rectangle2D minimizeButtonBorderBox;
	private Rectangle2D dragAreaBorderBox;
	private Color closeButtonBackgroundColor = MyColor.BLUE;
	private Color minimizeButtonBackgroundColor = MyColor.BLUE;
	
	public TopBar(int height, int width) {
		this.height = height;
		this.width = width;
		
		closeButtonBorderBox = new Rectangle(x + width - height, y, height, height);
		minimizeButtonBorderBox = new Rectangle(x + width - 2 * height, y, height, height);
		dragAreaBorderBox = new Rectangle(x, y, width - 2 * height, height);
	}
	
	public void render(Graphics g){
		
		// draw a blue background
		g.setColor(MyColor.BLUE);
		g.fillRect(x, y, width, height);
		
		// draw a close button
		int dx = (int) (height / 2 * 0.5);
		int c = height / 2;
		g.setColor(closeButtonBackgroundColor);
		g.fillRect(x + width - height, y, height, height);
		g.setColor(MyColor.WHITE);
		g.drawLine(x + width - c - dx, y + height - c - dx, x + width - c + dx, y + height - c + dx);
		g.drawLine(x + width - c - dx, y + height - c + dx, x + width - c + dx, y + height - c - dx);
		
		// draw a minimize button
		g.setColor(minimizeButtonBackgroundColor);
		g.fillRect(x + width - 2 * height, y, height, height);
		g.setColor(MyColor.WHITE);
		g.drawLine(x + width - c - dx - height, y + c, x + width - c + dx - height, y + c);
		
		// draw title
		g.setColor(MyColor.WHITE);
		g.setFont(new Font("Open Sans", Font.PLAIN, height - 10));
		FontMetrics metrics = g.getFontMetrics();
		int cx = (width - metrics.stringWidth("Chess")) / 2;
		int cy = ((height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.drawString("Chess", x + cx, y + cy);
		
	}
	
	public void tick() {
	}
	
	public void mouseMoved(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		
		if (closeButtonBorderBox.contains(new Point(x, y))) {
			closeButtonBackgroundColor = MyColor.RED;
		} else {
			closeButtonBackgroundColor = MyColor.BLUE;
		}
		if (minimizeButtonBorderBox.contains(new Point(x, y))) {
			minimizeButtonBackgroundColor = MyColor.RED;
		} else {
			minimizeButtonBackgroundColor = MyColor.BLUE;
		}
	}
	
	public void mouseExited(MouseEvent e){
		closeButtonBackgroundColor = MyColor.BLUE;
		minimizeButtonBackgroundColor = MyColor.BLUE;
	}
	
	public void mouseClicked(MouseEvent e) {
		if (closeButtonBorderBox.contains(e.getPoint())) {
			System.exit(1);
		}
		if (minimizeButtonBorderBox.contains(e.getPoint())) {
			window.getFrame().setExtendedState(JFrame.ICONIFIED);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if (dragAreaBorderBox.contains(e.getPoint())) {
			mouseDownCoords = e.getPoint();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		mouseDownCoords = null;
	}
	
	public void mouseDragged(MouseEvent e) {
		if (mouseDownCoords != null) {
			Point currCoords = e.getLocationOnScreen();
			window.getFrame().setLocation(currCoords.x - mouseDownCoords.x, currCoords.y - mouseDownCoords.y);
		}
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public Window getWindow() {
		return window;
	}
	
}
